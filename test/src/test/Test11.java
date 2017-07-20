/*******************************************************************
 * Test11.java   2017-5-26
 * Copyright 2016 by GNNT Company. All Rights Reserved.
 * Author:	hugh
 ******************************************************************/
package test;

import java.util.Scanner;

/**
 * Test11.java对象.
 *
 * <p><a href="Test11.java.html"><i>View Source</i></a></p>
 *
 * @version 1.0.0.1
 * @author <a href="mailto:hugh@gnnt.com.cn">HuGenHao</a>
 */
public class Test11
{

	/**
	 * 功能：<br/>
	 * @param args
	 * @exception   无
	 * @since   	首次创建（hugh  2017-5-26)<br/>
	 * 
	 */
	public static void main( String[] args )
	{
		Scanner scanner =new Scanner(System.in);  
		try
		{
			while(scanner.hasNext()){
				String num = scanner.nextLine();
				//scanner.nextLine();
				String log = scanner.nextLine();
				getzhis(log, num);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	private static String getzhis(String log,String num)
	{
		if("0".equals(num))
		{
			log = log.replaceAll("S", " ");
			log = log.replaceAll("E", " ");
			System.out.println(log.substring(1,log.length()-1).toString());
			return null;
		}
		for (int i = 0; i < log.length(); i++)
		{
			i = log.indexOf(num,i);
			if(i == -1)
			{
				break;
			}
			if((log.indexOf(num+"E", i) != i && log.indexOf("S"+num, i-1) != i-1) || i == 0)
			{
				getzhis(log.substring(0, i)+"S"+num+"E"+log.substring(i+num.length(), log.length()), (Integer.parseInt(num)-1)+"");
			}
		}
		
		return null;
	}
	
}
