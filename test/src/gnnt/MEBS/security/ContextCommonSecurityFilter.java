package gnnt.MEBS.security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import gnnt.MEBS.security.util.LogUtil;

/**
 * webapp公共安全过滤器，统一检查struts风险和sql注入风险
 * 
 * @author KyleTang
 * @version 1.0.3
 */
public class ContextCommonSecurityFilter implements Filter,I_Logger {
	protected transient final Log logger = LogFactory.getLog(this.getClass());
	
	private GnntCommonSecurityTool filter = new GnntCommonSecurityTool("ContextCommonSecurityFilter");

	public void destroy() {
		filter.destroy();
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		filter.doFilter(request, response, chain);
	}

	public void init(FilterConfig filterCfg) throws ServletException {
		LogUtil.setLogger(this);
		String initLog = "==========启动"+this.getClass().getName()+Version.version+"===日志输出方式："+(this.logger.isDebugEnabled()?"logger":"System.out")+"===";
		log(initLog);
		if(this.logger.isDebugEnabled()){
			System.out.println(initLog);
		}
		
		filter.init(filterCfg);
		try{
			Class.forName("org.apache.commons.fileupload.Version");
		}catch(Exception e){
			
		}
		
		String initLogOK = "==========启动"+this.getClass().getName()+Version.version+"===成功=======================";
		log(initLogOK);
		if(this.logger.isDebugEnabled()){
			System.out.println(initLogOK);
		}
	}

	public void log(String s){
		//if (this.logger.isDebugEnabled()){
		//	this.logger.debug(s);
		//}else{
			System.out.println("[OUT]"+s);
		//}
	}
}
