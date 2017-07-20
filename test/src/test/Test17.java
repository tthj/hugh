/*******************************************************************
 * Test17.java   2017-7-11
 * Copyright 2016 by GNNT Company. All Rights Reserved.
 * Author:	hugh
 ******************************************************************/
package test;

import java.util.Scanner;

/**
 * Test17.java∂‘œÛ.
 *
 * <p><a href="Test17.java.html"><i>View Source</i></a></p>
 *
 * @version 1.0.0.1
 * @author <a href="mailto:hugh@gnnt.com.cn">HuGenHao</a>
 */
public class Test17
{
	public static void main( String[] args )
	{
		Scanner scanner = new Scanner(System.in);
		int A = 0;
		int N = 0;
		while(scanner.hasNext())
		{
			N = scanner.nextInt();
			A = scanner.nextInt();
			int[] monster = new int[N];
			for (int i = 0; i < monster.length; i++)
			{
				monster[i] = scanner.nextInt();
			}
			for (int i = 0; i < monster.length; i++)
			{
				if(A >= monster[i])
				{
					A+=monster[i];
					continue;
				}
				else
				{
					A+= getRemainder(monster[i],A);
				}
			}
			System.out.println(A);
		}
	}
	
	public static int getRemainder(int a,int b)
	{
		if(a%b != 0)
		{
			return getRemainder(b,a%b);
		}
		else
		{
			return b;
		}
		
	}
}
