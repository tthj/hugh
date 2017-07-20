/*******************************************************************
 * TestList.java   2016-9-20
 * Copyright 2016 by GNNT Company. All Rights Reserved.
 * Author:	hugh
 ******************************************************************/
package test;

import java.util.ArrayList;
import java.util.List;

/**
 * TestList.java对象.
 *
 * <p><a href="TestList.java.html"><i>View Source</i></a></p>
 *
 * @version 1.0.0.1
 * @author <a href="mailto:hugh@gnnt.com.cn">HuGenHao</a>
 */
public class TestList
{

	/**
	 * 功能：<br/>
	 * @param args
	 * @exception   无
	 * @since   	首次创建（hugh  2016-9-20)<br/>
	 * 
	 */
	public static void main( String[] args )
	{
		/*List list = new ArrayList();
		list.add("123");
		list.add("1234");
		System.out.println(list.toString());
		 for (int i = list.size() - 1; i >= 0; i--) {
			System.out.println((list.get(i)));
			list.remove(i);
		   }
		 System.out.println(list.size());*/
		String[] ss = new String[]{"1","2","3"};
		System.out.println(ss.toString());
		/*String otherFirmID="012345678912";
		System.out.println("****"+otherFirmID.substring(4, otherFirmID.length()-3));*/
	}

}
