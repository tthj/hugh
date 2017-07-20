import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*******************************************************************
 * TestSet.java   2017-7-3
 * Copyright 2016 by GNNT Company. All Rights Reserved.
 * Author:	hugh
 ******************************************************************/

/**
 * TestSet.java∂‘œÛ.
 *
 * <p><a href="TestSet.java.html"><i>View Source</i></a></p>
 *
 * @version 1.0.0.1
 * @author <a href="mailto:hugh@gnnt.com.cn">HuGenHao</a>
 */
public class TestSet
{
	
	public static void main( String[] args )
	{
		Set<String> set = new HashSet<String>();
		
		List<String> list = new ArrayList<String>();
		list.add("123");
		list.add("123");
		list.add("123");
		set.addAll(list);
		for(String x:set)
		{
			System.out.println(x);
		}
	}
}
