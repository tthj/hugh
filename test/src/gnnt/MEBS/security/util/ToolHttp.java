package gnnt.MEBS.security.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author kyletang
 * @version 1.0.3
 */
public class ToolHttp {
	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 禁止外部引用
	 * @param request
	 * @return
	 */
	public static boolean checkCSRF_ExternalReferer(HttpServletRequest request){

		String ref = request.getHeader("Referer");
		if (ref!=null){
			String host = request.getHeader("Host");
			if (ref.indexOf(host)<0){
				return false;
			}
		}
		
		return true;
	} 
	
	/**
	 * 如果网址参数超过3个，则禁止直接打开网址
	 * @param request
	 * @return
	 */
	public static boolean checkCSRF_DirectOpenUrlMax3Parmeters(HttpServletRequest request){
		//超过3个参数，检查是否是外链接，如果是外链接，则拒绝
		if (request.getParameterMap().keySet().size()>3){
			String ref = request.getHeader("Referer");
			if (ref==null ){
				return false;
			}else{
				String host = request.getHeader("Host");
				//外链接禁止多个参数
				if (ref.indexOf(host)<0){
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 禁止非put方法
	 * @param request
	 * @return
	 */
	public static boolean checkCSRF_NotPutMethod(HttpServletRequest request){
		String method = request.getMethod();
		if (method==null){
			return false;
		}
		if (!method.equals("PUT")){
			return false;
		}
		return true;
	}
	
	public static boolean isMultipartContent(HttpServletRequest request) {
		if (!"post".equals(request.getMethod().toLowerCase())) {
			return false;
		}
		String contentType = request.getContentType();
		if (contentType == null) {
			return false;
		}
		if (contentType.toLowerCase().startsWith("multipart/")) {
			return true;
		}
		return false;
	}
	

	public static boolean isEcsideAJAXRequest(HttpServletRequest request){
		return (Boolean.TRUE.toString().equalsIgnoreCase(request.getHeader("isAjaxRequest"))) || (Boolean.TRUE.toString().equalsIgnoreCase(request.getHeader("useAjaxPrep")));
	}
	
	public static boolean isXMLHttpRequest(HttpServletRequest request){
		//X-Requested-With: XMLHttpRequest
		return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
	}
	
	/**
	 * 生成日志字符串
	 * @param tag
	 * @param value
	 * @param request
	 * @return
	 */
	public static String genLogString(String tag, String value, HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		sb.append("SecurityLog,ip=").append(request.getRemoteAddr()).append(",url=").append(request.getRequestURI())
				.append("?").append(request.getQueryString()==null?"":request.getQueryString()).append(",[tag]=").append(tag).append("[EOFtag],[v]=" + value+"[EOFv]");
		return sb.toString();
	}
}
