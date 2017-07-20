package gnnt.MEBS.security;

import java.lang.reflect.Method;
import gnnt.MEBS.security.util.LogUtil;
import gnnt.MEBS.security.util.ToolHttp;
import gnnt.MEBS.security.util.Tools;
import org.directwebremoting.AjaxFilter;
import org.directwebremoting.AjaxFilterChain;
import org.directwebremoting.WebContextFactory;

/*
<?xml version="1.0" encoding="UTF-8"?>   
<!DOCTYPE dwr PUBLIC  "-//GetAhead Limited//DTD Direct Web Remoting 1.0//EN"  "http://www.getahead.ltd.uk/dwr/dwr10.dtd">
<dwr>   
    <allow>              
        <create javascript="..." creator="..." scope="...">   
            ...
        </create> 
        
        <filter class="gnnt.MEBS.security.DwrSecurityFilter"></filter>
        
    </allow>   
</dwr>
*/
public class DwrSecurityFilter implements AjaxFilter {
	//protected transient final Log logger = LogFactory.getLog(this.getClass());

	public Object doFilter(Object object, Method method, Object[] params, AjaxFilterChain chain) throws Exception {
		try{
			org.directwebremoting.WebContext web= WebContextFactory.get();   
			javax.servlet.http.HttpServletRequest request = web.getHttpServletRequest(); 
			if (params!=null){
				Object param = null;
				for (int i=0;i<params.length;i++){
					param = params[i];
					if (param!=null && param instanceof String){
						String paramStr = ((String)param).trim();
						if (!Tools.checkSqlInjectionValue(paramStr)){
							LogUtil.log(ToolHttp.genLogString("DwrInjection", paramStr, request));
							return null;
						}
						
						params[i] = Tools.xssSqlCharEncode(paramStr);
						if (!paramStr.equals((String)params[i])){
							LogUtil.log(ToolHttp.genLogString("DwrInjection", paramStr+",newValue="+(String)params[i], request));
						}
					}
				}
			}
			
			return chain.doFilter(object, method, params);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
