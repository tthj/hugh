/*******************************************************************
 * TestThread.java   2016-11-21
 * Copyright 2016 by GNNT Company. All Rights Reserved.
 * Author:	hugh
 ******************************************************************/
package test;

import java.util.HashMap;
import java.util.Map;

/**
 * TestThread.java对象.
 *
 * <p><a href="TestThread.java.html"><i>View Source</i></a></p>
 *
 * @version 1.0.0.1
 * @author <a href="mailto:hugh@gnnt.com.cn">HuGenHao</a>
 */
public class TestThread
{

	public static Map<String,String> map = new HashMap<String, String>();
	/**
	 * 功能：<br/>
	 * @param args
	 * @exception   无
	 * @since   	首次创建（hugh  2016-11-21)<br/>
	 * 
	 */
	public static void main( String[] args )
	{
		new  Threads("1").start();
		new  Threads("2").start();
		new  Threads("3").start();
		new  Threads("4").start();
		new  Threads("5").start();
		new  Threads("6").start();
		new  Threads("7").start();
		new  Threads("8").start();

	}
}


