package gnnt.MEBS.security.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;


/**
 * date:2017-05-03
 * auth:yujun
 * ��������ύ�ĸ��ı������еķǷ�xss��䡣
 * 
 * */
public class HtmlCleanXss {
	
	
	/**
	 * ������ʹ��
	 * */
	public static void cleanXss(){
		Whitelist whitelist=initWhiteList();
		try {
			InputStream input=new FileInputStream("C:\\Users\\admin\\Desktop\\test\\test.html");
			OutputStream out=new FileOutputStream("C:\\Users\\admin\\Desktop\\test\\result.html");
			BufferedReader reader=new BufferedReader(new InputStreamReader(input,"UTF-8"));
			String line=null;
			StringBuffer buffer=new StringBuffer();
			StringBuffer resultsb=new StringBuffer();
			line = reader.readLine(); // ��ȡ��һ��
		    while (line != null) { // ��� line Ϊ��˵��������
		          buffer.append(line); // ��������������ӵ� buffer ��
//		          buffer.append("\n"); // ��ӻ��з�
//		          resultsb.append(Jsoup.clean(line, whitelist)+"\n");
		          line = reader.readLine(); // ��ȡ��һ��
		    }
		    reader.close();
			
		    String result=Jsoup.clean(buffer.toString(),whitelist);
					
			
			out.write(result.getBytes());
			out.close();
			System.out.println("clean");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 2017-05-03
	 * yujun
	 * param:��Ҫ���������
	 * return:������ȫ����֮�������
	 * */
	public static String cleanXss(String dirty){
//		System.out.println("DIRTY:"+dirty);
		Whitelist whitelist=initWhiteList();
//		System.out.println("init:"+dirty);
		String result=Jsoup.clean(dirty,whitelist);
//		System.out.println("CLEAN:"+result);
		return result;
	}
	
	public static void main(String args[]){
		String testHtml = "'\" onclick=javascript:alert(12)";
//		Whitelist whitelist=new Whitelist();
//		whitelist.addTags("p","div");
//		String result=Jsoup.clean(testHtml, whitelist);
//		System.out.println(result);
		System.out.println(cleanXss(testHtml));
	}
	
	/**
	 * 2017-05-03
	 * yujun
	 * ��ʼ��������
	 * return:������
	 * */
	public static Whitelist initWhiteList(){
		/*try {
			InputStream input=new FileInputStream("whitelist.json");
			JsonReader rdr=Json
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		Whitelist whitelist = null;
//		whitelist=Whitelist.basicWithImages();
		whitelist=Whitelist.relaxed();
//		whitelist=new Whitelist();
		whitelist.addAttributes(":all", "style");
		whitelist.addTags("s");
		whitelist.addAttributes("img", "border");
		
		return whitelist;
	}

}
