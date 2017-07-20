package test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Test3 {
	
	public static String getHttp(String myurl) throws Exception {
		URL url = new URL(myurl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("user-agent",
				"mozilla/4.0 (compatible; msie 6.0; windows 2000)");
		connection.setConnectTimeout(15000);
		connection.setReadTimeout(30000);
		connection.connect();
		System.out.println("--debug myurl:" + myurl + ",length:"
				+ connection.getContentLength() + ",status:"
				+ connection.getResponseCode());
		StringBuffer sb = new StringBuffer();
		if (connection.getResponseCode() == 200) {
			InputStream in = connection.getInputStream();
			BufferedReader breader = new BufferedReader(new InputStreamReader(
					in, "gbk"));
			String str = breader.readLine();
			while (str != null) {
				sb.append(str);
				str = breader.readLine();
			}
			in.close();
		}
		connection.disconnect();
		connection = null;
		url = null;
		return sb.toString();
	}
	public static void main(String[] args) throws Throwable {
		StringBuffer sb = new StringBuffer();
/*		String randomNum = "123456";
		String content = "���㽭���������Ŀ�����֤����:"+randomNum+".��������벦��0571-85263518.��ȷ������������ɿ�������ע��������Ʒ�������ġ�΢�Ź��ںţ�Ӯ���֣������";;
		sb.append("http://sms.weiyingjia.cn:8080/dog3/httpUTF8SMS.jsp");
		sb.append("?username=" + "donghaishangpin");//�û���
		sb.append("&pwd=" + "y7t6r5e4");//����
		sb.append("&mobile=15982473896");//�ֻ�����
		sb.append("&msg=" + URLEncoder.encode(content, "utf-8"));*/
		sb.append("http://sms.weiyingjia.cn:8080/dog3/httpUTF8Report.jsp?username=donghaishangpin&pwd=y7t6r5e4");
		String result = Test3.getHttp(sb.toString());
		System.out.println(result);
	}


}
