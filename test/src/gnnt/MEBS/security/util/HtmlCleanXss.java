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
 * 用于清除提交的富文本数据中的非法xss语句。
 * 
 * */
public class HtmlCleanXss {
	
	
	/**
	 * 供测试使用
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
			line = reader.readLine(); // 读取第一行
		    while (line != null) { // 如果 line 为空说明读完了
		          buffer.append(line); // 将读到的内容添加到 buffer 中
//		          buffer.append("\n"); // 添加换行符
//		          resultsb.append(Jsoup.clean(line, whitelist)+"\n");
		          line = reader.readLine(); // 读取下一行
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
	 * param:需要处理的数据
	 * return:经过安全处理之后的数据
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
	 * 初始化白名单
	 * return:白名单
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
