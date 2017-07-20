/*******************************************************************
 * Test13.java   2017-6-1
 * Copyright 2016 by GNNT Company. All Rights Reserved.
 * Author:	hugh
 ******************************************************************/
package test;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test13
{
	private static NumberFormat ddf1=NumberFormat.getNumberInstance() ; 
	
	public static void main( String[] args )
	{
		ddf1.setMaximumFractionDigits(1);
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext())
		{
			String[] ss = scanner.nextLine().split(" ");
			int N = Integer.parseInt(ss[0]);
			int M = Integer.parseInt(ss[1]);
			ss = scanner.nextLine().split(" ");
			int[] mNum = new int[M];
			List<double[]> listPeople = new ArrayList<double[]>();
			for (int i = 0; i < M; i++)
			{
				mNum[i] = Integer.parseInt(ss[i]);
			}
			for (int i = 0; i < N ; i++)
			{
				ss = scanner.nextLine().split(" ");
				double[] people = new double[M];
				for (int j = 0; j < M; j++)
				{
					people[j] = Double.parseDouble(ss[j]);
				}
				listPeople.add(people);
			}
			calculate(N,M,mNum,listPeople);
		}

	}

	private static void calculate( int n ,int m ,int[] mNum ,List<double[]> listPeople )
	{
		double allprobabilityNum = 0;
		double allNum = 0;
		for (int i = 0; i < m; i++)
		{
			allNum += mNum[i];
			double [] nums = new double[mNum[i]];
			nums[mNum[i]-1] = 1.0;
			for (int j = 0; j < listPeople.size(); j++)
			{
				nums = getnums(nums, listPeople.get(j)[i]);
			}
			allprobabilityNum += getnum(nums, mNum[i]);
		}
		System.out.println(ddf1.format(allNum - allprobabilityNum));
	}
	
	private static double[] getnums(double [] nums,double probability){
		for (int i = nums.length-1; i >= 0; i--)
		{
			if(i == nums.length-1)
			{
				nums[i] = nums[i]*(1-probability);
			}
			else
			{
				nums[i] = nums[i]*(1-probability) + nums[i+1]*probability;
			}
			if(nums[i] == 0)
			{
				break;
			}
		}
		
		return nums;
	} 

	public static double getnum(double [] nums,int num)
	{
		double probabilityNum = 0;
		for (int i = 0; i < nums.length; i++)
		{
			probabilityNum += (num-i) * nums[i];
		}
		return probabilityNum;
	}
}
