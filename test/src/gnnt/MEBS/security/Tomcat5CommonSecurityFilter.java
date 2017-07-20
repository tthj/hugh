package gnnt.MEBS.security;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * tomcat公共安全过滤器，统一检查struts风险和sql注入风险
 * 
 * 要求tomcat 5 
 * 
 * @author KyleTang
 * @version 1.0.3
 */
public class Tomcat5CommonSecurityFilter extends ContextCommonSecurityFilter{
	protected transient final Log logger = LogFactory.getLog(this.getClass());
}
