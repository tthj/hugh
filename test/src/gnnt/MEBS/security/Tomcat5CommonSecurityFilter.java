package gnnt.MEBS.security;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * tomcat������ȫ��������ͳһ���struts���պ�sqlע�����
 * 
 * Ҫ��tomcat 5 
 * 
 * @author KyleTang
 * @version 1.0.3
 */
public class Tomcat5CommonSecurityFilter extends ContextCommonSecurityFilter{
	protected transient final Log logger = LogFactory.getLog(this.getClass());
}
