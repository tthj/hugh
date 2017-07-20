/*******************************************************************
 * Test16.java   2017-7-11
 * Copyright 2016 by GNNT Company. All Rights Reserved.
 * Author:	hugh
 ******************************************************************/
package test;

import java.util.Scanner;

/**
 * Test16.java∂‘œÛ.
 *
 * <p><a href="Test16.java.html"><i>View Source</i></a></p>
 *
 * @version 1.0.0.1
 * @author <a href="mailto:hugh@gnnt.com.cn">HuGenHao</a>
 */
public class Test16
{
	public static void main( String[] args )
	{
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext())
		{
			int num = scanner.nextInt();
			int[] group = new int[num];
			int[] bonus = new int[num];
			for (int i = 0; i < num; i++)
			{
				group[i] = scanner.nextInt();
				bonus[i] = 1;
			}
			boolean ischange = true;
			while(ischange)
			{
				ischange = false;
				for (int i = 0; i < bonus.length-1; i++)
				{
					if(group[i]<group[i+1] && bonus[i+1] <= bonus[i])
					{
						bonus[i+1] = bonus[i] + 1;
						ischange = true;
					}
					else if(group[i]>group[i+1] && bonus[i+1] >= bonus[i])
					{
						bonus[i] = bonus[i+1] + 1;
						ischange = true;
					}
				}
			}
			int sum = 0;
			for (int i = 0; i < bonus.length; i++)
			{
				sum += bonus[i];
			}
			System.out.println(sum);
		}
	}
}
