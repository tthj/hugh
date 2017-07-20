package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TestFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File f = new File("D:\\gnntzyh\\webapps\\gnntzyh_grab1\\Json\\ZY0004.Json");
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(f));
			String tempString = "";
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				System.out.println(tempString);
			tempString = tempString.replaceAll("null", "1314");
			String[] S1 = tempString.split(",");
			String s="";
			for (int i = 0; i < S1.length; i++) {
				String[] s2 = S1[i].split("\\^");
				for (int j = 0; j < s2.length; j++) {
					if(s2[j].contains("\\.")){
						s2[j] = s2[j].split("\\.")[0]+s2[j].split("\\.")[1].substring(0,1);
					}
					if(j!=9&&j!=10&&j!=11&&j!=12){
						s += s2[j];
					}
				}
			}
			try {
				 
				FileWriter fw;
				fw = new FileWriter("D:\\gnntzyh\\webapps\\gnntzyh_grab1\\Json\\Json\\ZY0004.Json");
				PrintWriter out = new PrintWriter(fw);  
				out.write(s);  
				out.println();  
				fw.close();  
				out.close(); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			}
		}catch(Exception e){
			
		}
	}

}
