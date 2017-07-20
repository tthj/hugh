package gnnt.MEBS.security;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class SecurityHttpServletRequestWrapper extends HttpServletRequestWrapper{
	public SecurityHttpServletRequestWrapper(HttpServletRequest servletRequest) {
		super(servletRequest);
	}
	public Map getParameterMap(){
		return super.getParameterMap();
	}

	public String[] getParameterValues(String parameter) {
		return (String[])(super.getParameterMap().get(parameter));
	}

	public String getParameter(String parameter) {
		String[] values = this.getParameterValues(parameter);
		if (values==null){
			return null;
		}else if(values.length==0){
			return "";
		}else{
			return values[0];
		}
	}
	
	public Enumeration getParameterNames() {
		return Collections.enumeration(super.getParameterMap().keySet());
	}
	
}