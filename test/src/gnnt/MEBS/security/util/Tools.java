package gnnt.MEBS.security.util;

/**
 * 
 * @author kyletang
 * @version 1.0.3
 */
public class Tools {
	
	public static boolean isAllowXiaoKuoHao = false;

	public static String RegEx_RegularString = "[0-9a-zA-Z\\-]*";
	
	private static String RegEx_InputNameString = "[!0-9a-zA-Z_<>=\\-\\[\\]\\.]+";
	
	private static String RegEx_InputNameString_Special = "[a-zA-Z0-9_]*trunc\\([a-zA-Z0-9\\.]+\\)\\[[<>=]+\\]\\[[a-zA-Z0-9]+\\]";
	//_trunc(a.registerDate)
	//_trunc(a.registerDate)[><=][date]
	//gnnt_trunc(a.registerDate)[><=][date]
	//gnnt.trunc(a.registerDate)[><=][date]
	private static String RegEx_InputNameString_trunc = "[0-9a-zA-Z_\\.]*trunc\\([0-9a-zA-Z\\._]+\\)[!0-9a-zA-Z_<>=\\-\\[\\]\\.]*";
	
	//trunc(sysdate) 
	// trunc(sysdate-1) 
	// trunc( sysdate + 1 ) 
	// trunc(a.b_date) 
	// trunc(a.cleardate) 
	// trunc(a.b_date + 1 ) 
	// trunc(a.cleardate-1)
	private static String RegEx_ValueString_trunc = "[ ]*trunc\\([0-9a-zA-Z\\.\\-\\+_ ]+\\)[ ]*";
	
	// trim(a.id2) 
	// _trim(a.id2) 
	private static String RegEx_ValueString_trim = "[ _]*trim\\([0-9a-zA-Z\\.\\-\\+_ ]+\\)[ ]*";
		
	// to_number(a.stockid)
	private static String RegEx_ValueString_to_number = "[ ]*to_number\\([0-9a-zA-Z\\.\\-\\+_ ]+\\)[ ]*";

	// to_date('2016/05/30','YYYY/MM/DD')
	// to_date('2016/05/30 13:00:00','YYYY/MM/DD HH24:MI:SS')
	private static String RegEx_ValueString_to_date = "[ ]*to_date\\('[0-9/:_ \\.\\-]+','[0-9a-zA-Z/:_ \\.\\-]+'\\)[ ]*";
	
	// to_char(a.b_date,'YYYY/MM/DD')
	// to_char(a.cleardate,'YYYY/MM/DD')
	private static String RegEx_ValueString_to_char = "[ ]*to_char\\([0-9a-zA-Z\\.\\-\\+_ ]+,'[0-9a-zA-Z/:_ \\.\\-]+'\\)[ ]*";
	
	// ('123','abc','xyz')
	//private static String RegEx_ValueString_inString = "\\([0-9a-z0-9\\-'\\,\\.]+\\)";
	
	// (1,2,3,4.0)
	// ( 1, 2, 3, 4.0 )
	private static String RegEx_ValueString_inNumber = "[ ]*\\([0-9\\,\\. ]+\\)[ ]*";
	
	// a.b_date
	private static String RegEx_TableColumnName = "[ ]*[0-9a-zA-Z_\\.]+[ ]*";
	
	// data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAMCAg
	private static String Regex_base64_img = "data:image/(jpeg|jpg|png);base64,[a-zA-Z0-9+/=]+";
	
	// /9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAMCAg
	private static String Regex_base64 = "[a-zA-Z0-9+/=]+";
	
	/*
	 * name="bankCardPassword"
name="bankPassword"
name="cfmPassword"
name="confirmPassword"
name="confirmpwd"
name="entity.password"
name="newpwd"
name="newPwd"
name="newpwd1"
name="obj.old"
name="obj.password"
name="obj.phonePWD"
name="oldpass"
name="oldPassword"
name="oldpwd"
name="oldPwd"
name="password"
name="passWord"
name="password1"
name="password2"
name="password3"
name="passwords"
name="phonePWD1"
name="pwd"
name="pwd1"
name="rnewpwd"
name="specialforAudit.password1"
name="tpassword"
name="truePassword"
name="upassword"

	 */
	private static String KeyWord_KeyPassword = ",pwd,password,newpwd,newpassword,changepwd,changepassword,userpassword,"
			+ "entity.password,oldpassword,oldpass,password1,password2,oldpwd,rnewpwd,pwd1,bankpassword,bankpwd,"
			+ "obj.password,obj.phonePWD,obj.old,"
			+ "bankCardPassword,specialforAudit.password1,"
			+ "cfmPassword,confirmPassword,confirmpwd,tpassword,upassword,truePassword,passwords,password3,"; 
	
	public static boolean isValidValueTimeStr(String s){
		//Wed Mar 22 2017 16:25:36 GMT+0800 (�й���׼ʱ��)
		//Wed Mar 22 2017 16:25:36 GMT+0800
		if (s==null||s.length()==0){
			return false;
		}
		s = s.trim();
		s = s.replaceAll("\\(�й���׼ʱ��\\)","");
		
		if (s.matches("[A-Za-z0-9:+. ]+")){
			return true;
		}

		return false;
	}
	
	/**
	 * �����/���ж��Ƿ�Ϊbase64�����ַ���
	 * @param s
	 * @return
	 */
	public static boolean isValidBase64String(String s){
		if (s==null||s.length()==0){
			return false;
		}
		s = s.trim();
		if (s.indexOf("/") >=0 && (s.matches(Tools.Regex_base64)||s.matches(Tools.Regex_base64_img))){
			return true;
		}
		return false;
	}
	
	/**
	 * ���input��name������Ĺؼ��֣�����Ϊ���ֶ�Ϊ���룬����
	 * @param s
	 * @return
	 */
	public static boolean isValidInputElementNamePassword(String s){
		if (s==null||s.length()==0){
			return false;
		}
		s = s.trim();
		s = ","+s.toLowerCase()+",";
		if (KeyWord_KeyPassword.indexOf(s)>=0){
			return true;
		}
		return false;
	}
	
	/*
	private static String sqlBadStr = "'|and|exec|execute|insert|select|delete|update|count|drop|*|%|chr|mid|master|truncate|" +
            "char|declare|sitename|net user|xp_cmdshell|;|or|-|+|,|like'|and|exec|execute|insert|create|drop|" +
            "table|from|grant|use|group_concat|column_name|" +
            "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|*|" +
            "chr|mid|master|truncate|char|declare|or|;|-|--|+|,|like|//|/|%|#";//���˵���sql�ؼ��֣������ֶ����
            */

	public static boolean isValidInputElementName(String s){
		if (s==null||s.length()==0){
			return true;
		}
		s = s.trim();
		if (s.matches(RegEx_InputNameString)&&s.indexOf("--")<0){
			return true;
		}else if (s.matches(RegEx_InputNameString_Special)){
			return true;
		}else if (isValidInputElementNameEx(s)){
			return true;
		}
		
		return false;
	}
	
	public static boolean isValidInputElementNameEx(String s){
		if (s == null || s.length() == 0) {
			return true;
		}

		if (s.indexOf("--") >= 0){
			return false;
		}
		// gnnt_a.b_date[=][date]
		// _trunc(a.registerDate)[><=][date]
		// gnnt.a.b_date[=][date]
		// gnnt.a.b_date[=]
		
		//        v------------kuohao1 
		//        v          v---------kuohao2
		// colName[condition][type]
		// colName:  
		//	  b_date
		//	  gnnt.b_date  
		//    gnnt_a.b_date
		//  _trunc(     to_date(    to_char    to_number

		
		//��ͨ�ֶ���
		int fangKuoHao1 = s.indexOf("[");
		String colName = null;
		
		if (fangKuoHao1>0){
			//�з�����

			int fangKuoHao2 = s.indexOf("[",fangKuoHao1+1);

			String condition = null;
			String type = null;
			if (fangKuoHao2>0){
				type = s.substring(fangKuoHao2+1,s.length()-1);
				if (!type.matches("[a-zA-Z]+")){
					return false;
				}
			}else{
				fangKuoHao2=s.length();
			}
			
			condition = s.substring(fangKuoHao1+1,fangKuoHao2-1);
			if (condition.equals("=") || condition.equals(">=") || condition.equals("<=") || condition.equals("!=") || condition.equals("<>") || condition.equals("like") || condition.equals("in"))
			{
				// OK 
			}else{
				return false;
			}
			
			colName = s.substring(0,fangKuoHao1);
			
		}else{
			//û������
			colName = s;
		}

		//�ֶ����к���ԭ���ţ����к���
		if (colName.indexOf("(")<0){
			if (colName.matches(RegEx_TableColumnName)){
				return true;
			}else{
				return false;
			}
		}else{
			//
			int posUnderLine = colName.indexOf("_");
			String colNamePrefix = null;
			if (posUnderLine>=0){
				colNamePrefix=colName.substring(0,posUnderLine);
				colName=colName.substring(posUnderLine+1);
			}
			if (colNamePrefix!=null&&colNamePrefix.length()>0){
				if (!colNamePrefix.matches(RegEx_TableColumnName)){
					return false;
				}
			}
			if (colName.matches(RegEx_TableColumnName)||
					colName.matches(RegEx_ValueString_trunc) ||
					colName.matches(RegEx_ValueString_trim) ||					
					colName.matches(RegEx_ValueString_to_number) ||
					colName.matches(RegEx_ValueString_to_char) ||
					colName.matches(RegEx_ValueString_to_date)){
				return true;
			}else{
				return false;
			}
		}
		
	}

	public static boolean isValidValueTrunc(String s) {
		//�������trunc(�ҷ���������ʽ���򷵻�true
		if (s.indexOf("trunc(") >= 0 && s.matches(RegEx_ValueString_trunc)) {
			return true;
		}
	
		return false;
	}
	
	public static boolean isValidValueToNumber(String s) {
		//�������trunc(�ҷ���������ʽ���򷵻�true
		if (s.indexOf("to_number(") >= 0 && s.matches(RegEx_ValueString_to_number)) {
			return true;
		}
	
		return false;
	}
	
	public static boolean isValidValueToChar(String s) {
		//�������trunc(�ҷ���������ʽ���򷵻�true
		if (s.indexOf("to_char(") >= 0 && s.matches(RegEx_ValueString_to_char)) {
			return true;
		}
	
		return false;
	}
	
	public static boolean isValidValueToDate(String s) {
		//�������trunc(�ҷ���������ʽ���򷵻�true
		if (s.indexOf("to_date(") >= 0 && s.matches(RegEx_ValueString_to_date)) {
			return true;
		}
	
		return false;
	}
	
	public static boolean isValidValueIn(String s) {
		//�������(, �ҷ��� in��������ʽ���򷵻�true
		if (s.indexOf("(") < 0){
			return false;
		}
		
		if (s.matches(RegEx_ValueString_inNumber)){
			return true;
		}
		
		//('a','b','c,d')
		//'a','b','c,d'
		//'����a','b','c,d'
		if (s.charAt(0)=='('){
			if (s.charAt(s.length()-1)!=')'){
				return false;
			}else{
				s = s.substring(1,s.length()-1);
			}
		}
		
		if (s.charAt(0)=='\'' && s.charAt(s.length()-1)!='\''){
			return false;
		}
				
		//��鵥���ſ�������
		boolean isYinHaoOpen = false;
		char c ;
		for (int i=0;i<s.length();i++){
			c = s.charAt(i);
			if (c=='\''){
				isYinHaoOpen = !isYinHaoOpen;
			}
			if (c==',' && isYinHaoOpen==true){
				return false;
			}
		}
	
		return false;
	}

	public static boolean isValidOrderByString(String s){
		s = s.toLowerCase();
		if (s.indexOf("order ")>=0){
			// order by a.b_date asc , trunc(a.date) , to_number (x.stockid), to_char(sysdate,''), to_date('','')
			s = s.toLowerCase();
			s = s.replace("order ", " ");
			s = s.replace(" by ", " ");
			
			boolean hasToDate=false;
			if (s.indexOf("to_date")>=0){
				hasToDate=true;
				s = s.replaceAll("','", "___");
			}
			
			boolean hasToChar=false;
			if (s.indexOf("to_char")>=0){
				hasToChar=true;
				s = s.replaceAll(",'", "###");
			}
			
			s = s.replaceAll(",'", "___");
			s = s.replace(" desc", " ");
			s = s.replace(" asc", " ");
			String[] arr = s.split(",");
			if (arr.length>0){
				for ( int i=0; i<arr.length ; i++){
					String v = arr[i].trim();
					if (hasToChar){
						v = v.replaceAll("###", ",'");
					}
					if (hasToDate){
						v = v.replaceAll("___", "','");
					}
					
					if (v.matches(RegEx_TableColumnName) ||
							v.matches(RegEx_ValueString_trunc) ||
							v.matches(RegEx_ValueString_trim) ||
							v.matches(RegEx_ValueString_to_number) ||
							v.matches(RegEx_ValueString_to_char) ||
							v.matches(RegEx_ValueString_to_date)){
						//OK
					}else{
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}
	/**
	 * ������sqlע��value
	 * 
	 * @param value
	 * @return false δͨ��(��鵽�������·��) true ͨ��
	 */
	public static boolean checkSqlInjectionValue(String value) {
		if (value==null){
			return true;
		}
		// ���⴦��result.jsp --- 
//		if (value.equalsIgnoreCase("---")){
//			return true;
//		}
		
		value = value.toLowerCase();
		
		if (value.indexOf("chr(") >= 0
				|| value.indexOf("exec") >= 0
				|| value.indexOf("/*") >= 0 
				|| value.indexOf("*/") >= 0
				|| value.indexOf("\\") >= 0
				|| value.indexOf("execute") >= 0
				|| value.indexOf("processbuilder") >= 0
				|| value.indexOf("dbms_") >= 0
				|| value.indexOf("newcontext") >= 0
				|| value.indexOf("declare") >= 0
				|| value.indexOf("pragma") >= 0
				|| value.indexOf("autonomous_transaction") >= 0
				|| value.indexOf("grant") >= 0
				|| value.indexOf("commit") >= 0
				//|| value.indexOf("!=") >= 0  // 20170310 ע�ͣ���߶��ϰ汾ϵͳ�ļ�����
				|| value.indexOf("--") >= 0  // ֱ�Ӿܾ� added by tangzy 20160718
				//|| value.indexOf("<>") >= 0  // 20170310 ע�ͣ���߶��ϰ汾ϵͳ�ļ�����
				){
			return false;
		}
		
//		value = value.replaceAll("\\s", " ");
		value = value.replace('\t', ' ');
		value = value.replace('\r', ' ');
		value = value.replace('\n', ' ');
		value = value.replace('\f', ' ');
		value = value.replace('(', ' ');
		value = value.replace(')', ' ');
		value = value.replace('/', ' ');
		value = value.replace('\\', ' ');    //     */select/* 

		if (value.indexOf(" begin ") >= 0
				|| value.indexOf(" end ") >= 0
				
				|| value.indexOf(" and ") >= 0
				|| value.indexOf(" union ") >= 0
				|| value.indexOf(" or ") >= 0
				
				//|| (value.indexOf("order ") >= 0 && value.indexOf(" by ") >= 0)
				
				|| value.indexOf(" select ") >= 0
				|| value.indexOf(" update ") >= 0
				|| value.indexOf(" delete ") >= 0
				|| value.indexOf(" drop ") >= 0
				|| value.indexOf(" alter ") >= 0){
			return false;
		}
		
		return true;
	}
	
	/**
	 * ���Struts����value
	 * 
	 * @param value
	 * @return false δͨ��(��鵽�������·��) true ͨ��
	 */
	public static boolean checkStrutsAttackValue(String value) {
		// ©�������ο� http://www.2cto.com/Article/201307/228712.html
		if (value==null){
			return true;
		}
		if (value.length()<32){
			return true;
		}
		value = value.toLowerCase();
		if (value.startsWith("%25")
				|| value.indexOf("redirect:") >= 0
				|| value.indexOf("redirectaction:") >= 0
				|| value.indexOf("action:") >= 0
				//|| value.indexOf("getruntime") >= 0
				|| value.indexOf("java.lang.") >= 0    // java.lang.Runtime   java.lang.ProcessBuilder
				|| value.indexOf("classloader") >= 0   // struts 1.x
				|| value.indexOf("getruntime") >= 0
				|| (value.indexOf("process") >= 0 && value.indexOf("(") >=0)  //�������settleProcesDate
				|| value.indexOf("processbuilder") >= 0
				|| value.indexOf("java.nio.") >= 0
				|| value.indexOf("java.io.") >= 0      // java.io.FileOutputStream
				|| value.indexOf(".exec(") >= 0
				)
			return false;
		return true;
	}
	
	/**
	 * ���xss����
	 * @param value
	 * @return
	 */
	public static boolean checkXssValue(String value) {
		if (value==null){
			return true;
		}
		
		if (value.length()<16){
			return true;
		}
		
		if (value.matches("[0-9a-zA-Z]")){
			return true;
		}
		
		// /(?:javascript|jav\s+ascript|\&#\d+|\&#x)/i
		// (?i)javascript|jav\s+ascript|\&#\d+|\&#x
		/*
		if (value.matches("(?i)javascript|jav\\s+ascript|\\&#\\d+|\\&#x")){
			return false;
		}*/
		
		value = value.toLowerCase();
		
		if (value.indexOf("</") >= 0                //input��ֵ����Ӧ�ð���</
				|| value.indexOf("<script") >= 0    //input��ֵ����Ӧ�ð���<script
				|| value.indexOf("\"") >= 0         //input��ֵ����Ӧ�ð���˫����"
				|| value.indexOf("src=") >= 0       //input��ֵ����Ӧ�ð���src=
				|| value.indexOf("onload=") >= 0    //input��ֵ����Ӧ�ð���onload=
				|| value.indexOf("onerror=") >= 0   //input��ֵ����Ӧ�ð���onerror=
				|| (value.indexOf(".js") >= 0 &&  value.indexOf(".jsp")<=0)  //input��ֵ����Ӧ�ð���.js
				)
			return false;
		return true;
	}
	
	/**
	 * ����ϴ��ļ���չ��
	 * @param value
	 * @return
	 */
	public static boolean checkUploadImageFileName(String value) {
		if (value==null){
			return true;
		}
		value = value.toLowerCase();
		
		if (value.indexOf(".png.") >= 0
				|| value.indexOf(".jpg.") >= 0
				|| value.indexOf(".jpeg.") >= 0
				|| value.indexOf(".bmp") >= 0  //��ֹ�ϴ�bmp�ļ����ļ�̫��
				|| value.indexOf(".gif.") >= 0
				)
			return false;
		return true;
	}

	/**
	 * ת���з��յ��ַ�Ϊȫ�ǣ����ⲿ��xss��sqlע�����
	 * @param s
	 * @return
	 */
	public static String xssSqlCharEncode(String s) {
		if (s == null || s.length()==0) {
			return s;
		}
		
		// ���⴦��result.jsp --- 
//		if (s.equalsIgnoreCase("---")){
//			return s;
//		}
		
		// ����� order by �򲻴���
		if (isValidOrderByString(s)){
			return s;
		}
		
		s = s.replaceAll("--", "����"); // sql, ����ʹ��һ�����ߣ����ţ������ǲ���ʹ�����������Ķ���
		StringBuilder sb = new StringBuilder(s.length()*2);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '>':
				if (i+1<s.length()){
					// [>=]  [>]
					if (s.charAt(i+1)=='=' || s.charAt(i+1)==']'){
						sb.append(">").append(s.charAt(i+1));
						i++;
						break;
					}
				}
				sb.append("&gt;");//xssȫ�Ǵ��ں�
				break;
			case '<':
				if (i+1<s.length()){
					// [<=]  [<>]  [<]
					if (s.charAt(i+1)=='=' || s.charAt(i+1)=='>' || s.charAt(i+1)==']'){
						sb.append('<').append(s.charAt(i+1));
						i++;
						break;
					}
				}
				sb.append("&lt;");//xssȫ��С�ں�
				break;
			case '\'':
				sb.append("&#x27;");//xss��sqlȫ�ǵ�����
				break;
			case '\"':
				sb.append("&quot;");//xssȫ��˫����
				break;
			case '&':
				sb.append("&amp;");//xssȫ��
				break;
			case '\\':
				sb.append('��');//xssȫ��б��
				break;
			case '#':
				sb.append('��');//xssȫ�Ǿ���
				break;
			case '|':
				sb.append('��');//sqlȫ��
				break;
			case '(':
				if (isAllowXiaoKuoHao){
					sb.append('(');
				}else{
					sb.append('��');//xss��sqlȫ��
				}
				break;
			case ')':
				if (isAllowXiaoKuoHao){
					sb.append(')');
				}else{
					sb.append('��');//xss��sqlȫ��
				}
				break;
//			case '=':
//				sb.append('��');//sqlȫ��
//				break;
//			case ' ':
//				sb.append('��');//sqlȫ��
//				break;
			case '\t':   //sql
				sb.append(' ');
				break;
			case '\n':   //sql
				break;
			case '\r':   //sql
				break;
			default:
				sb.append(c);
				break;
			}
		}
		return sb.toString();
	}

}
