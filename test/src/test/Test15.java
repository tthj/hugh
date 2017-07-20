/*******************************************************************
 * Test15.java   2017-7-10
 * Copyright 2016 by GNNT Company. All Rights Reserved.
 * Author:	hugh
 ******************************************************************/
package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Test15.java∂‘œÛ.
 *
 * <p><a href="Test15.java.html"><i>View Source</i></a></p>
 *
 * @version 1.0.0.1
 * @author <a href="mailto:hugh@gnnt.com.cn">HuGenHao</a>
 */
public class Test15
{
	private static String newS = "2345678910JQKA";
	public static void main( String[] args )
	{
		Scanner scanner = new Scanner(System.in);
		int statusA = 0;
		int statusB = 0;
		while(scanner.hasNext())
		{
			try
			{
				String[] ss = scanner.nextLine().split(" ");
				String[] A = getOrderly(ss[0]);
				String[] B = getOrderly(ss[1]);
				statusA = getStatus(A);
				statusB = getStatus(B);
				if((statusA == 4 && statusB ==2) || (statusB == 4 && statusA == 2))
				{
					if(getEquivalence(A) == getEquivalence(B))
					{
						System.out.println(-2);
						continue;
					}
				}
				if(statusA > statusB)
				{
					System.out.println(1);
				}
				else if (statusA < statusB)
				{
					System.out.println(-1);
				}
				else
				{
					if(statusA == 4)
					{
						System.out.println(-2);
					}else if (statusA == 3)
					{
						if(newS.indexOf(A[2]) > newS.indexOf(B[2]))
						{
							System.out.println(1);
						}
						else if(newS.indexOf(A[2]) < newS.indexOf(B[2]))
						{
							System.out.println(-1);
						}
						else
						{
							System.out.println(0);
						}
					}
					else if (statusA == 2)
					{
						if(getEquivalence(A) > getEquivalence(B))
						{
							System.out.println(1);
						}
						else if(getEquivalence(A) < getEquivalence(B))
						{
							System.out.println(-1);
						}
						else
						{
							System.out.println(0);
						}
					}
					else
					{
						if(newS.indexOf(A[2]) > newS.indexOf(B[2]))
						{
							System.out.println(1);
						}
						else if(newS.indexOf(A[2]) < newS.indexOf(B[2]))
						{
							System.out.println(-1);
						}
						else
						{
							if(newS.indexOf(A[1]) > newS.indexOf(B[1]))
							{
								System.out.println(1);
							}
							else if(newS.indexOf(A[1]) < newS.indexOf(B[1]))
							{
								System.out.println(-1);
							}
							else
							{
								if(newS.indexOf(A[0]) > newS.indexOf(B[0]))
								{
									System.out.println(1);
								}
								else if(newS.indexOf(A[0]) < newS.indexOf(B[0]))
								{
									System.out.println(-1);
								}
								else
								{
									System.out.println(0);
								}
							}
						}
					}
				}
			} 
			catch (Exception e)
			{
				System.out.println(-2);
			}
			
		}

	}
	public static String[] getOrderly(String ss)
	{
		String[] A = new String[3];
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < ss.length(); i++)
		{
				list.add(newS.indexOf(ss.toCharArray()[i]));
		}
		for (int i = 0; i < list.size()-1; i++)
		{
			if(list.get(i) > list.get(i+1))
			{
				list.add(list.get(i));
				list.remove(i);
				i = -1;
			}
		}
		int y = 0;
		for (int i = 0; i < list.size(); i++)
		{
			if(list.get(i) == 8)
			{
				A[y] = newS.substring(list.get(i), list.get(i)+2);
			}
			else if(list.get(i) == 9)
			{
				continue;
			}
			else
			{
				A[y] = newS.substring(list.get(i),list.get(i)+1);
			}
			y++;
		}
		return A;
	}
	
	public static int getStatus(String[] ss){
		if(newS.indexOf(ss[0]+ss[1]+ss[2]) != -1 || "A23".equals(ss[0]+ss[1]+ss[2]))
		{
			return 3;
		}
		if(ss[0].equals(ss[1]) && ss[1].equals(ss[2]))
		{
			return 4;
		}
		if(ss[0].equals(ss[1])|| ss[1].equals(ss[2]))
		{
			return 2;
		}
		return 1;
	}
	
	public static int getEquivalence(String[] ss)
	{
		if(ss[0].equals(ss[1]))
		{
			return newS.indexOf(ss[0]);
		}
		else
		{
			return newS.indexOf(ss[2]);
		}
	}
}
