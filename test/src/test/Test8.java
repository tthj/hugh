package test;

import java.awt.List;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Test8 {

	
	public static class testt extends Thread{
		

		@Override
		public void run() { 
			 try
			{
				 Long start = new Date().getTime();
				 java.net.URL l_url = new java.net.URL("http://172.18.1.12:8080/zyh_hq_server/quotation/getQuotation?code=2&industryid=1&start=0&end=100");
				 java.net.HttpURLConnection l_connection = (java.net.HttpURLConnection) l_url.openConnection();    
		         l_connection.connect();    
		         InputStream l_urlStream = l_connection.getInputStream();    
		         java.io.BufferedReader l_reader = new java.io.BufferedReader(new java.io.InputStreamReader(l_urlStream));   
		         String str="";
		      /*   while (l_reader.ready()) {
		        	 str=l_reader.readLine();
		        	 if (!"".equals(str))
		        	 System.out.println(str);
				}*/
		        System.out.println(new Date().getTime() - start);
			}
			catch (MalformedURLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
		}
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		for (int i = 0; i < 100; i++)
		{
			new testt().start();
		}
		
		// TODO Auto-generated method stub
		/*while(true){
		// java.net.URL l_url = new java.net.URL("http://ta.shscce.com:8080/front/hq/delay_hq.json?callback=jsonp1461660535000&_=1461660567693&stockPreCodes=&mainIndexCode=&dataIndexCode="); 
			 java.net.URL l_url = new java.net.URL("http://118.244.200.215:16996/hqweb/hq/hqV_mobile.jsp");    
		 java.net.HttpURLConnection l_connection = (java.net.HttpURLConnection) l_url.openConnection();    
         l_connection.connect();    
         InputStream l_urlStream = l_connection.getInputStream();    
         java.io.BufferedReader l_reader = new java.io.BufferedReader(new java.io.InputStreamReader(l_urlStream));   
         String str=l_reader.readLine();
         while (l_reader.ready()) {
        	 str=l_reader.readLine();
        	 if (!"".equals(str))
        	 System.out.println(str);
		}
         String str1[] = str.split("<tr");
         Map map = new HashMap();
         List list = new List();
         for (int i = 1; i < str1.length; i++) {
        	 if (i==6){
        		 continue;
        	 }
			String str2[] = str1[i].split("'>");
			Test8 ts1 =new Test8();
			test11 ts = ts1.new test11();
			ts.setLaima(str2[3].split("<")[0]);
			ts.setName(str2[4].split("<")[0]);
			ts.setZuo(Double.parseDouble(str2[5].split("<")[0]));
			if(str2.length>6){
			ts.setJin(Double.parseDouble(str2[6].split("<")[0]));
			ts.setZuix(Double.parseDouble(str2[7].split("<")[0]));
			ts.setZhangdie(Double.parseDouble(str2[8].split("<")[0].substring(0,str2[8].split("<")[0].length()-1)));
			ts.setChengjiao(Integer.parseInt(str2[9].split("<")[0]));
			ts.setChengjiaoe(Double.parseDouble(str2[10].split("<")[0]));
			ts.setZuigao(Double.parseDouble(str2[11].split("<")[0]));
			ts.setZuidi(Double.parseDouble(str2[12].split("<")[0]));
			}
			System.out.println(ts.toString());
		}
         try{
				Thread.sleep(3000);
			}catch( InterruptedException e){
				
			}*/
		
	}
	
	public class test11{
		private String name;
		private String laima;
		private double zuo;
		private double jin;
		private double zuix;
		private double zhangdie;
		private long chengjiao;
		private double chengjiaoe;
		private double zuigao;
		private double zuidi;
		public String toString(){
			return name+"   "+"   "+"   "+"   "+" \t"+laima+"\t"+zuo+"\t"+jin+"\t"+zuix+"\t"+zhangdie+"\t"+
					chengjiao+"\t"+chengjiaoe+"\t"+zuigao+"\t"+zuidi;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getLaima() {
			return laima;
		}
		public void setLaima(String laima) {
			this.laima = laima;
		}
		public double getZuo() {
			return zuo;
		}
		public void setZuo(double zuo) {
			this.zuo = zuo;
		}
		public double getJin() {
			return jin;
		}
		public void setJin(double jin) {
			this.jin = jin;
		}
		public double getZuix() {
			return zuix;
		}
		public void setZuix(double zuix) {
			this.zuix = zuix;
		}
		public double getZhangdie() {
			return zhangdie;
		}
		public void setZhangdie(double zhangdie) {
			this.zhangdie = zhangdie;
		}
		public long getChengjiao() {
			return chengjiao;
		}
		public void setChengjiao(long chengjiao) {
			this.chengjiao = chengjiao;
		}
		public double getChengjiaoe() {
			return chengjiaoe;
		}
		public void setChengjiaoe(double chengjiaoe) {
			this.chengjiaoe = chengjiaoe;
		}
		public double getZuigao() {
			return zuigao;
		}
		public void setZuigao(double zuigao) {
			this.zuigao = zuigao;
		}
		public double getZuidi() {
			return zuidi;
		}
		public void setZuidi(double zuidi) {
			this.zuidi = zuidi;
		}
		
	}
}
