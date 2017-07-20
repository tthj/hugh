package gnnt.MEBS.security;

import gnnt.MEBS.security.util.LogUtil;
import gnnt.MEBS.security.util.RequestParameterHandler;
import gnnt.MEBS.security.util.ToolHttp;
import gnnt.MEBS.security.util.Tools;
import gnnt.MEBS.security.vo.UriParamVO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 公共安全过滤器，统一检查struts风险和sql注入风险
 * 
 * @author KyleTang
 * @version 1.0.3
 */
public class GnntCommonSecurityTool{
	protected FilterConfig filterConfig=null; 
	
	//忽略的扩展名
	protected String ignore_exts_String=null;
	//忽略的扩展名 set
	protected ArrayList<String> ignore_exts=null;
	
	//忽略的URI
	protected String ignore_uris_String=null;
	//忽略的URI set
	protected ArrayList<String> ignore_uris=null;
	
	//忽略的参数名
	protected String ignore_params_String=null;
	//忽略的参数名 set
	protected ArrayList<UriParamVO> ignore_params=null;
	
	//富文本参数 add by yujun 20170503
	protected String rtext_String;
	protected ArrayList<UriParamVO> rtexts=null;
	
	private int debugCount = 0;
	
	private static boolean isFirstRequest = true;
	
	private String characterEncoding = "GBK";
	
	private String filterName ;
	
	/**
	 * 配置在tomcat里和配置在工程里的logger对象不同，这里需要在构造的时候传递过来实例
	 */
	
	public GnntCommonSecurityTool(){
	}
	
	public GnntCommonSecurityTool(String filterName){
		this.filterName = filterName;
	}

	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest _req, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)_req;
		
		if (isFirstRequest){
			synchronized (GnntCommonSecurityTool.class) {
				if (isFirstRequest){
					String tagName = request.getContextPath().replaceAll("/", "");
					LogUtil.setTagName(tagName);
					LogUtil.info("init LogUtil : "+ tagName);
					LogUtil.log("filterName="+filterName);
					isFirstRequest = false;
				}
			}
		}

		//如果没有设置字符集，则设置为GBK
		if (request.getCharacterEncoding() == null){
			request.setCharacterEncoding(characterEncoding);
	    }
		
		if (ToolHttp.isEcsideAJAXRequest(request)){
			request.setCharacterEncoding("UTF-8");
		}
		
		try{
			//防范struts2执行漏洞 https://cwiki.apache.org/confluence/display/WW/S2-045
			String contentType = request.getContentType();
			//System.out.println("[OUT] request.getContentType="+request.getContentType());
			boolean errorFlag = false;
			if (contentType!=null){
				// 60长度能满足：application/x-www-form-urlencoded; charset=utf-8;
				// multipart/form-data; boundary=
				// multipart/mixed; 
				if (contentType.length()>60 && !contentType.trim().startsWith("multipart/")){
					errorFlag = true;
				}
				
				//放过application/x-www-form-urlencoded;开头的吧
				if (contentType.toLowerCase().startsWith("application/x-www-form-urlencoded;")){
					errorFlag = false;
				}
				
				if (contentType.indexOf("#")>0 || contentType.indexOf("java.lang.")>0 || contentType.indexOf(".ognl.")>0 ){
					errorFlag = true;
				}
			}
			
			if (errorFlag){
				LogUtil.log(ToolHttp.genLogString("StrutsAttack:contentType",contentType,(HttpServletRequest) request));
				throw new RuntimeException("StrutsAttack: contentType error, contentType="+contentType);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("getContentType error");
		}
		
		//增加debug工具
		if (Version.isDebug){
			if (debugCount==0){
				LogUtil.log("*****GnntCommonSecurityFilter.isSecurityFilterEnable="+isSecurityFilterEnable() +"********************");
			}
			debugCount ++ ;
			if (debugCount>10){
				debugCount=0;
			}
			
			try{
				String ttt = request.getParameter("ttt");
				if (ttt!=null){
					if (ttt.equals("true")){
						//开启过滤器
						setSecurityFilterEnable(true);
						//isSecurityFilterEnable = true;
					}else if (ttt.equals("false")){
						//关闭过滤器
						setSecurityFilterEnable(false);
						//isSecurityFilterEnable = false;
					}else{
						
					}
					returnMsgToRequest(request,response,"Gnnt通用安全过滤器当前状态："+(isSecurityFilterEnable()?"启用中":"已关闭"));
					return ;
				}
				
			}catch(Exception e){
				
			}
			
			//如果关闭过滤器，直接跳过
			if (!isSecurityFilterEnable()){
				doFilterInner(request, response, chain);
				return;
			}
		}

		//检查危险的URL查询字符串，屏蔽struts漏洞
		String queryString = ((HttpServletRequest) request).getQueryString();
		if (!Tools.checkStrutsAttackValue(queryString)){
			LogUtil.log(ToolHttp.genLogString("StrutsAttack",queryString,(HttpServletRequest) request));
			return;
		}
		
		//spy: X-cmd
		String x_cmd = request.getHeader("X-CMD");
		if (x_cmd!=null){
			LogUtil.log(ToolHttp.genLogString("spy:X-CMD="+x_cmd,queryString,(HttpServletRequest) request));
			return;
		}
		
		String uri = ((HttpServletRequest) request).getRequestURI();
		if (uri!=null){
			//检查忽略的uri
			Iterator<String> itr = ignore_uris.iterator();
			String item = null;
			while(itr.hasNext()){
				item = itr.next();
				if (uri.equalsIgnoreCase(item)){
					LogUtil.info(ToolHttp.genLogString("IGN:uris","", request));
					doFilterInner(request, response, chain);
					return;
				}
			}
			
			//检查忽略的扩展名
			itr = ignore_exts.iterator();
			item = null;
			while(itr.hasNext()){
				item = itr.next();
				if (uri.endsWith(item)){
					LogUtil.info(ToolHttp.genLogString("IGN:exts","", request));
					doFilterInner(request, response, chain);
					return;
				}
			}
			
		}

		//处理参数map，返回三种处理结果，正常、警告、禁止
//		int resultType = RequestParameterHandler.handleParameterMap(request,ignore_params);
		int resultType = RequestParameterHandler.handleParameterMap(request,ignore_params,rtexts);
		boolean isMultipartContent = ToolHttp.isMultipartContent(request);
		if (!isMultipartContent && resultType==RequestParameterHandler.HandleResultType_Normal){
			//正常请求，放行
			doFilterInner(request, response, chain);
		}else if (isMultipartContent || resultType==RequestParameterHandler.HandleResultType_WARN){
			try{
				//警告，走封装
				doFilterInner(new SecurityHttpServletRequestWrapper((HttpServletRequest)request), response, chain);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else{
			//禁止，直接拒绝 HandleResultType_FORBID
			returnMsgTextToRequest(request,response,"Error：请求数据错误（非法请求）");
			return ;
		}
	}
	
	@SuppressWarnings("unchecked")
	private void doFilterInner(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		RequestParameterHandler.setRequest((HttpServletRequest)request);
		chain.doFilter(request, response);
		RequestParameterHandler.removeRequest();
	}
	
	public void init(FilterConfig filterCfg) throws ServletException {
		//默认开启
		setSecurityFilterEnable(true);
		
		try{
			filterConfig = filterCfg;    
			if (filterConfig!=null){
				//小括号放行
				String isAllowXiaoKuoHao = filterConfig.getInitParameter("IS_ALLOW_XIAO_KUO_HAO");
				if (isAllowXiaoKuoHao!=null && "TRUE".equalsIgnoreCase(isAllowXiaoKuoHao)){
					Tools.isAllowXiaoKuoHao = true;
				}
				
				//支持用户自己设置
				String encoding = filterConfig.getInitParameter("CharacterEncoding");
				if (encoding!=null){
					characterEncoding = encoding;
				}
				LogUtil.log("*****GnntCommonSecurityFilter.characterEncoding="+characterEncoding+","+"user set: CharacterEncoding="+encoding+"********************");
				
				ignore_exts_String = filterConfig.getInitParameter("IGNORE_EXTS");
				ignore_exts=new ArrayList<String>();
				if(ignore_exts_String!=null){
					String[] exts=ignore_exts_String.split(",");
					String exts_str = null;
					for(int i=0;i<exts.length;i++)
					{
						exts_str = exts[i].trim();
						if (exts_str.length()>0){
							ignore_exts.add(exts_str);
						}
					}
				}
		
				ignore_uris_String=filterConfig.getInitParameter("IGNORE_URIS");
				ignore_uris=new ArrayList<String>();
				if(ignore_uris_String!=null){
					String[] uris=ignore_uris_String.split(",");
					String uris_str = null;
					for(int i=0;i<uris.length;i++)
					{
						uris_str = uris[i].trim();
						if (uris_str.length()>0){
							ignore_uris.add(uris_str);
						}
					}
				}
				
				ignore_params_String=filterConfig.getInitParameter("IGNORE_PARAMS");
				ignore_params=new ArrayList<UriParamVO>();
				if(ignore_params_String!=null){
					String[] params=ignore_params_String.split(",");
					for(int i=0;i<params.length;i++)
					{
						String[] arr = params[i].split(":");
						if (arr!=null && arr.length==2){
							if (arr[0].trim().length()>0 && arr[1].trim().length()>0 ){
								UriParamVO vo = new UriParamVO(arr[0].trim(),arr[1].trim());
								ignore_params.add(vo);
							}
						}
					}
				}
				
				//初始化富文本参数列表
				rtext_String=filterConfig.getInitParameter("RTEXT");
				rtexts=new ArrayList<UriParamVO>();
				if(rtext_String!=null){
					String []params=rtext_String.split(",");
					for(int i=0;i<params.length;i++){
						String []arr=params[i].split(":");
						if(arr!=null && arr.length==2){
							UriParamVO vo=new UriParamVO(arr[0],arr[1]);
							rtexts.add(vo);
						}
					}
					//初始化RequestParameterHandler中的富文本参数，用于判断multipart/most提交的富文本参数 add by yujun 20170505
					RequestParameterHandler.rtext_params=rtexts;
				}
				
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void destroy() {

	}

	protected void returnMsgToRequest(ServletRequest _request, ServletResponse _response, String msg) 
			throws IOException, ServletException{
		HttpServletRequest request=(HttpServletRequest)_request;
		HttpServletResponse response=(HttpServletResponse)_response;
		response.setCharacterEncoding("GBK");
		response.getWriter().write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\"><HTML><HEAD><META content=\"text/html; charset=gb2312\" http-equiv=Content-Type></HEAD><BODY>");
		response.getWriter().write("<script type=\"text/javascript\">");
		
		response.getWriter().append("alert('"+msg+"');");
//				
//				if (new Random().nextInt(5)<2){
//					response.getWriter().write("alert('欢迎访问公安部网站^-^');");
//					response.getWriter().write("window.location=\"http://www.mps.gov.cn/\"");
//				}
		
		response.getWriter().write("</script></BODY></HTML>");
		response.getWriter().flush();
	}
	
	protected void returnMsgTextToRequest(ServletRequest _request, ServletResponse _response, String msg) 
			throws IOException, ServletException{
		HttpServletRequest request=(HttpServletRequest)_request;
		HttpServletResponse response=(HttpServletResponse)_response;
		response.setCharacterEncoding("GBK");
		response.getWriter().append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\"><HTML><HEAD><META content=\"text/html; charset=gb2312\" http-equiv=Content-Type></HEAD><BODY>");
		response.getWriter().append("<h3>"+msg+"</h3>");
		response.getWriter().append("</BODY></HTML>");
		response.getWriter().flush();
	}
	
	private boolean isSecurityFilterEnable(){
		try{
			if (new File("GnntCommonSecurityFilter.securityFilterDisabled.flag").exists()){
				return false;
			}else{
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}
	
	private void setSecurityFilterEnable(boolean isTrue){
		try{
			File f = new File("GnntCommonSecurityFilter.securityFilterDisabled.flag");
			if (isTrue){
				f.delete();
			}else{
				try {
					f.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}catch(Exception e){
			
		}
	}
}
