package gnnt.MEBS.security.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import gnnt.MEBS.security.I_Logger;

public class LogUtil {
	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat dfDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static DateFormat dfTime = new SimpleDateFormat("HH:mm:ss");
	private static String infoFileName = "LogUtil_Info.log";

	private static BufferedWriter fw = null;
	private static I_Logger g_logger;
	private static long lastDayStartTime = System.currentTimeMillis();
	private static long lastWriteLogTime = 0;
	
	private static String tagName = null;
	
	private static LogUtilThread thread = null;
	
	public static void setTagName(String _tagName){
		tagName = _tagName;
	}

	public static void log(String s) {
		s = new StringBuilder().append("LogUtil[").append(dfDateTime.format(new Date())).append("] ").append(s).toString();
		if (g_logger != null)
			g_logger.log(s);
		else
			System.out.println(s);
	}

	public static void setLogger(I_Logger logger) {
		g_logger = logger;
		
		if (thread==null){
			thread = new LogUtilThread();
			thread.start();
			log("LogUtilThread-start");
		}
	}
	
	private static String getFileName(){
		if (new File("../logs/").isDirectory()){
			return "../logs/"+infoFileName+"."+tagName;
		}else{
			return infoFileName;
		}
	}

	public static void info(String s) {
		lastWriteLogTime = System.currentTimeMillis();
		try{
			// System.out.println("TEST:"+df.format(new Date(lastDayStartTime)));
			if (fw == null) {
				lastDayStartTime = new java.sql.Date(lastWriteLogTime).getTime();
				try {
					fw = new BufferedWriter(new FileWriter(getFileName(),true));
				} catch (IOException e) {
					log("LogUtil.info, BufferedWriter error, " + new java.util.Date());
					// e.printStackTrace();
				}
			}
	
			if (lastWriteLogTime - lastDayStartTime > 24 * 3600 * 1000) {
				synchronized (LogUtil.class) {
					if (lastWriteLogTime - lastDayStartTime > 24 * 3600 * 1000) {
						try {
							close();
							File f = new File(getFileName());
							if (f.exists()){
								f.renameTo(new File(getFileName()+"." + df.format(new Date(lastDayStartTime))));
							}
							fw = new BufferedWriter(new FileWriter(getFileName(),true));
						} catch (IOException e) {
							log("LogUtil.info, BufferedWriter error, " + new java.util.Date());
							// e.printStackTrace();
						}
			
						lastDayStartTime = new java.sql.Date(lastWriteLogTime).getTime();
					}
				}
			}
	
			try {
				fw.append("LogUtil[").append(dfTime.format(new Date())).append("] ").append(s).append("\n");
			} catch (IOException e) {
				log("LogUtil.info, write error, " + new java.util.Date());
				// e.printStackTrace();
			}
		}catch(Exception e){
			log(e.getMessage());
		}
	}

	public static void close() {
		try {
			if (fw!=null){
				try{
					fw.flush();
				} catch (Exception e) {
					//e.printStackTrace();
				}
				try{
					fw.close();
				} catch (Exception e) {
					//e.printStackTrace();
				}
				fw = null;
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
	
	private static class LogUtilThread extends Thread{
		public void run(){
			while(true){
				try {
					Thread.sleep(10000);
					if (fw!=null){
						try {
							fw.flush();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
