/*******************************************************************
 * Test18.java   2017-7-11
 * Copyright 2016 by GNNT Company. All Rights Reserved.
 * Author:	hugh
 ******************************************************************/
package test;

import org.apache.commons.lang3.StringEscapeUtils;


/**
 * Test18.java∂‘œÛ.
 *
 * <p><a href="Test18.java.html"><i>View Source</i></a></p>
 *
 * @version 1.0.0.1
 * @author <a href="mailto:hugh@gnnt.com.cn">HuGenHao</a>
 */
public class Test18
{
	public static void main( String[] args )
	{
		String ss = "[{&quot;amount&quot;:&quot;1111.11&quot;,&quot;fundChannel&quot;:&quot;ALIPAYACCOUNT&quot;}]";
		ss = StringEscapeUtils.unescapeHtml3(ss);
		System.out.println(ss);
	}
}
