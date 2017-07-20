/*******************************************************************
 * TestThread24.java   2017-1-13
 * Copyright 2016 by GNNT Company. All Rights Reserved.
 * Author:	hugh
 ******************************************************************/
package test;

/**
 * TestThread24.java对象.
 *
 * <p><a href="TestThread24.java.html"><i>View Source</i></a></p>
 *
 * @version 1.0.0.1
 * @author <a href="mailto:hugh@gnnt.com.cn">HuGenHao</a>
 */
public class TestThread24 extends Thread
{

	/**
	 * 功能：<br/>
	 * @param args
	 * @exception   无
	 * @since   	首次创建（hugh  2017-1-13)<br/>
	 * 
	 */
	public static void main( String[] args )
	{
		for (int i = 0; i < 24; i++)
		{
			new TestThread24().start();
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run()
	{
		while(true)
		{
			System.out.println("1");
		}
	}
}
