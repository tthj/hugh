package gnnt.MEBS.security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.catalina.filters.FilterBase;
import gnnt.MEBS.security.util.LogUtil;

/**
 * tomcat公共安全过滤器，统一检查struts风险和sql注入风险
 * 
 * 要求tomcat版本不低于6.0
 * 
 * @author KyleTang
 * @version 1.0.3
 */
public class TomcatCommonSecurityFilter extends FilterBase implements Filter,I_Logger{

	protected final org.apache.juli.logging.Log logger = org.apache.juli.logging.LogFactory
			.getLog(TomcatCommonSecurityFilter.class);
	
	private GnntCommonSecurityTool filter = new GnntCommonSecurityTool("TomcatCommonSecurityFilter");

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
		if (this.logger.isDebugEnabled()){
			System.out.println(initLog);
		}
		
		filter.init(filterCfg);
		
		String initLogOK = "==========启动"+this.getClass().getName()+Version.version+"===成功=======================";
		log(initLogOK);
		if (this.logger.isDebugEnabled()){
			System.out.println(initLogOK);
		}
	}

	@Override
	protected org.apache.juli.logging.Log getLogger() {
		return logger;
	}

	public void log(String s){
		//if (this.logger.isDebugEnabled()){
		//	this.logger.debug(s);
		//}else{
			System.out.println("[OUT]"+s);
		//}
	}
}
