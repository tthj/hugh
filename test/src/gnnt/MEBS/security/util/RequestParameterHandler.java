package gnnt.MEBS.security.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import gnnt.MEBS.security.vo.UriParamVO;

public class RequestParameterHandler {
	
	public static final int HandleResultType_Normal = 0;
	public static final int HandleResultType_WARN = 1;
	public static final int HandleResultType_FORBID = 2;
	
	//富文本参数列别，主要用于multipar/post提交的参数验证
	public static  List<UriParamVO> rtext_params=null;
	
	private static ThreadLocal<HttpServletRequest> threadLocal = new ThreadLocal<HttpServletRequest>();
	public static class RequestParameterHandlerResult {
		private boolean isPassed;
		private int resultType;
		private String newValue;
		public RequestParameterHandlerResult(boolean isPassed, String newValue) {
			this(isPassed,newValue,HandleResultType_Normal);
		}
		public RequestParameterHandlerResult(boolean isPassed, String newValue, int resultType) {
			this.isPassed = isPassed;
			this.newValue = newValue;
			this.resultType = resultType;
		}
		
		public boolean isPassed() {
			return isPassed;
		}
		public String getNewValue() {
			return newValue;
		}
		public int getResultType() {
			return resultType;
		}
	}
	
	public static void setRequest(HttpServletRequest request){
		threadLocal.set(request);
	}
	
	public static HttpServletRequest getRequest(){
		return (HttpServletRequest)threadLocal.get();
	}
	
	public static void removeRequest(){
		threadLocal.remove();
	}
	
	public static RequestParameterHandlerResult handleParameterNameAndValue(String name, String value){
		HttpServletRequest request = getRequest();
		//System.out.println("request.get.hashCode="+request.hashCode());
		
		/* base64编码没有问题，不需要跳过检查
		if (name!=null && name.startsWith("b64")){
			LogUtil.log(ToolHttp.genLogString("k:warn", name+",value="+value, request));
			return new RequestParameterHandlerResult(true,value);
		}
		*/
		
		//如果参数名不符合规则，则移除掉
		if (!Tools.isValidInputElementName(name)){
			LogUtil.log(ToolHttp.genLogString("k[:NameValue]", name, request));
			return new RequestParameterHandlerResult(false,null);
		}
		
		/*
		//处理name
		String tag=null;
		name = name.trim(); //去掉两端空格
		if (!Tools.checkSqlInjectionValue(name)){
			tag = "checkSqlk:"+name;
			LogUtil.log(ToolHttp.genLogString(tag, "", request));
			return new RequestParameterHandlerResult(false,null);
		}
		String v = Tools.xssSqlCharEncode(name);
		if (!v.equals(name)){
			tag = "xssSqlk:"+name;
			LogUtil.log(ToolHttp.genLogString(tag, v, request));
			return new RequestParameterHandlerResult(false,null);
		}
		*/
		
		if (value==null){
			return new RequestParameterHandlerResult(true,null);
		}
		
		//处理value
		String tag=null;
		value = value.trim(); //去掉两端空格
		
		if (value.length()==0){
			return new RequestParameterHandlerResult(true,value);
		}
		
		//如果是纯粹的base64编码，没有问题，可以放行
		if (Tools.isValidBase64String(value)){
			/* 注释调试日志
			tag = "[debugInfo]v[x]base64:"+name;
			String outValue = value;
			if (value.length()>20){
				outValue = value.substring(0, 20);
			}
			LogUtil.log(ToolHttp.genLogString(tag, outValue+"......", request));
			*/
			return new RequestParameterHandlerResult(true,value);
		}
		
		//如果name是密码，则放行
		if (Tools.isValidInputElementNamePassword(name)){
			return new RequestParameterHandlerResult(true,value);
		}
		
		if (!Tools.checkSqlInjectionValue(value)){
			tag = "v[x]:"+name;
			LogUtil.log(ToolHttp.genLogString(tag, value, request));
			return new RequestParameterHandlerResult(false,null);
		}
		
		
		//判断参数是否在富文本参数列表中 add by yujun 20170505
		boolean isRtext=false;
		if(rtext_params!=null){
			Iterator<UriParamVO> itr = rtext_params.iterator();
			UriParamVO item = null;
//			boolean isRtext=false;
			while(itr.hasNext()){
				item=itr.next();
				if( item.getParam().equals(name)){
//					System.out.println("===============================================isRtext========================================");
					LogUtil.log("The multipart rtext.");
					isRtext=true;
					break;
				}
			}
		}
		String v ="";
		if(isRtext){//在富文本参数列表中则进行富文本操作 add by yujun 20170505
			v=HtmlCleanXss.cleanXss(value);
			if (!v.equals(value)){
				tag = "v[x]:"+name;
				LogUtil.log(ToolHttp.genLogString(tag, value+",newValue="+v, request));
			}
		}else{//否则则进行普通xss去除操作 add by yujun 20170505
		
			v = Tools.xssSqlCharEncode(value);
			if (!v.equals(value)){
				tag = "v[x]:"+name;
				LogUtil.log(ToolHttp.genLogString(tag, value+",newValue="+v, request));
			}
		}
		
		return new RequestParameterHandlerResult(true,v);
	}
	
	public static int handleParameterMap(HttpServletRequest request, List<UriParamVO> ignore_params,List<UriParamVO> rtext_params){
		//此段代码针对struts直接访问entrySet的方式
		//检查提交表单数据中的危险关键字
		int resultType = HandleResultType_Normal;
		String uri = request.getRequestURI();
		Map<String, String[]> parameterMap = request.getParameterMap();
		try{
			Set<Entry<String, String[]>> entrySet = parameterMap.entrySet();
			Iterator<Entry<String, String[]>> entryItr = entrySet.iterator();
			Entry<String, String[]> entry;
			String key = null;
			String[] values = null;
			Iterator<UriParamVO> itr = null;
			UriParamVO item = null;
			while(entryItr.hasNext()){
				entry = entryItr.next();
				key = entry.getKey();
				
				if (ignore_params!=null){
					//检查是否是忽略的字段名，如果是，则忽略
					itr = ignore_params.iterator();
					item = null;
					boolean isIgnore = false;
					while(itr.hasNext()){
						item = itr.next();
						if (item.getUri().equalsIgnoreCase(uri) && item.getParam().equals(key)){
							isIgnore = true;
							break;
						}
					}
					
					if (isIgnore){
						LogUtil.info(ToolHttp.genLogString("IGN:param","", request));
						continue;
					}
				}
				
				//判断是否富文本参数
				if(rtext_params!=null){
					//检查是否是富文本参数 20170503 by yujun
					itr=rtext_params.iterator();
					item=null;
					boolean isRtext=false;
					while(itr.hasNext()){
						item=itr.next();
						if(item.getUri().equalsIgnoreCase(uri) && item.getParam().equals(key)){
//							System.out.println("===============================================isRtext========================================");
							isRtext=true;
							break;
						}
					}
					if(isRtext){
//						System.out.println("**************************************************");
						values = entry.getValue();
						if (values!=null){
							int count = values.length;
//							System.out.println(values);
							String[] encodedValues = new String[count];
							String value = null;
							String tag = null;
							for (int i = 0; i < count; i++) {
								//对值进行富文本xss净化
								value = values[i];
//								System.out.println(value);
								if (value==null){
//									System.out.println("GET OUT");
									continue;
								}
								/*value = value.trim(); //去掉两端空格
								if (!Tools.checkSqlInjectionValue(value)){
									tag = "v["+i+"]:"+key;
									LogUtil.log(ToolHttp.genLogString(tag, value, request));
									resultType = HandleResultType_FORBID;
									return resultType;
								}*/
//								String v = Tools.xssSqlCharEncode(value);
								String v=HtmlCleanXss.cleanXss(value);
//								LogUtil.log(ToolHttp.genLogString("=============RText================", value+",newValue="+v, request));
								if (!v.equals(value)){
									tag = "v["+i+"]:"+key;
									LogUtil.log(ToolHttp.genLogString(tag, value+",newValue="+v, request));
									resultType = HandleResultType_WARN;
								}
//								resultType = HandleResultType_Normal;
								encodedValues[i] = v;
							}
							entry.setValue(encodedValues);
						}
						continue;
					}
				}
				
				//如果name是密码，则放行
				if (Tools.isValidInputElementNamePassword(key)){
					continue;
				}
				
				//如果参数名不符合规则，则移除掉
				if (!Tools.isValidInputElementName(key)){
					entryItr.remove();
					LogUtil.log(ToolHttp.genLogString("k[:remove]", key, request));
					resultType = HandleResultType_WARN;
					continue;
				}
				
				/*
				String tagName=null;
				if (!Tools.checkSqlInjectionValue(key)){
					tagName = "checkSql:k:"+key;
					LogUtil.log(ToolHttp.genLogString(tagName, "", request));
					resultType = HandleResultType_WARN;
					continue;
				}
				String keyV = Tools.xssSqlCharEncode(key);
				if (!keyV.equals(key)){
					tagName = "xssSql:k:"+key;
					LogUtil.log(ToolHttp.genLogString(tagName, keyV, request));
					resultType = HandleResultType_WARN;
					continue;
				}
				*/		
				
				values = entry.getValue();
				if (values!=null){
					int count = values.length;
					String[] encodedValues = new String[count];
					String value = null;
					String tag = null;
					for (int i = 0; i < count; i++) {
						//对值进行编码处理，转换有风险的字符为全角
						value = values[i];
						if (value==null){
							continue;
						}
						value = value.trim(); //去掉两端空格
						if (!Tools.checkSqlInjectionValue(value)){
							tag = "v["+i+"]:"+key;
							LogUtil.log(ToolHttp.genLogString(tag, value, request));
							resultType = HandleResultType_FORBID;
							return resultType;
						}
						String v = Tools.xssSqlCharEncode(value);
						if (!v.equals(value)){
							tag = "v["+i+"]:"+key;
							LogUtil.log(ToolHttp.genLogString(tag, value+",newValue="+v, request));
							resultType = HandleResultType_WARN;
						}
						encodedValues[i] = v;
					}
					entry.setValue(encodedValues);
				}
			}
		}catch(Exception e){
			LogUtil.log("[ERROR]SecurityHttpServletRequestWrapper,err="+e.getMessage());
			e.printStackTrace();
		}
		return resultType;
	}
}
