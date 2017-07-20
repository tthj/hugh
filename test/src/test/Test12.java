/*******************************************************************
 * Test12.java   2017-5-27
 * Copyright 2016 by GNNT Company. All Rights Reserved.
 * Author:	hugh
 ******************************************************************/
package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Test12.java∂‘œÛ.
 *
 * <p><a href="Test12.java.html"><i>View Source</i></a></p>
 *
 * @version 1.0.0.1
 * @author <a href="mailto:hugh@gnnt.com.cn">HuGenHao</a>
 */
public class Test12
{

	public static List<int[]> list = new ArrayList<int[]>();
	
	public static int total;
	
	public static int val;

	public static void main( String[] args )
	{
		Scanner scanner =new Scanner(System.in);  
		while(scanner.hasNext()){
			list = new ArrayList<int[]>();
			total = scanner.nextInt();
			val = scanner.nextInt();
			for (int i = 0; i < val; i++)
			{
				int[] ss = new int[3];
				for (int j = 0; j < 3; j++)
				{
					ss[j] = scanner.nextInt();
				}
				list.add(ss);
				
			}
			System.out.println(getMax(1,0,0).get(1));
		}
	}
	
	private static Map<Integer,Integer> getMax(int Start,int k,int sum){
		Map<Integer,Integer>  returnMap = new HashMap<Integer, Integer>();
		if(Start == total)
		{
			returnMap.put(0, sum);
			returnMap.put(1, k);
			return returnMap;
		}
		Map<Integer,Integer>  zhisMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < list.size(); i++)
		{
			if(Start == list.get(i)[0])
			{
				if( k <= list.get(i)[2])
				{
					returnMap =getMax(list.get(i)[1],list.get(i)[2],sum+list.get(i)[2]);
					if(zhisMap.size() != 0 && returnMap.size() != 0)
					{
						if(returnMap.get(0) <= zhisMap.get(0) )
						{
							zhisMap = returnMap;
						}
					}
					else if(returnMap.size() != 0)
					{
						zhisMap = returnMap;
					}
				}
				else
				{
					returnMap =getMax(list.get(i)[1],k,sum+list.get(i)[2]);
					if(zhisMap.size() != 0 && returnMap.size() != 0)
					{
						if(returnMap.get(0) <= zhisMap.get(0))
						{
							zhisMap = returnMap;
						}
					}
					else if(returnMap.size() != 0)
					{
						zhisMap = returnMap;
					}
				}
			}
		}
		return zhisMap; 
	}

}
