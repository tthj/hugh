/*******************************************************************
 * Threads.java   2016-11-21
 * Copyright 2016 by GNNT Company. All Rights Reserved.
 * Author:	hugh
 ******************************************************************/
package test;

/**
 * Threads.java∂‘œÛ.
 *
 * <p><a href="Threads.java.html"><i>View Source</i></a></p>
 *
 * @version 1.0.0.1
 * @author <a href="mailto:hugh@gnnt.com.cn">HuGenHao</a>
 */
public class Threads extends Thread{
	private String id;
	public Threads(String id)
	{
		this.id = id;
	}
	@Override
	public void run()
	{
		for (int i = 0; i < 1000; i++)
		{
			
			TestThread.map.put("a", id);
		}
		try
		{
			Thread.sleep(1000);
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
		System.out.println(TestThread.map.get("a"));
	}
}