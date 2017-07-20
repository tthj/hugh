package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class Test14
{
	public static String[] cts = new String[]{"A","B","C","D","E","F"};
	public static Map<String,Integer> ctsNum = new HashMap<String, Integer>();
	public static String[] distance = new String[]{"AB=3","AC=2","AD=1","AE=3","AF=4","BC=5","BD=4","BE=2","BF=1","CD=3","CE=4","CF=3","DE=2","DF=5","EF=8"};
	public static Map<String,Integer> ctsDistance = new HashMap<String, Integer>();
	public static int[][] message = new int[6][6];
	public static List<String> saveMessage = new ArrayList<String>();
	public static  Random random = new Random();
	
	
	public static void main( String[] args )
	{
		List<String> purposes = new ArrayList<String>();
		for (int i = 0; i < cts.length; i++)
		{
			ctsNum.put(cts[i], i);
			if(i!=0)
			purposes.add(cts[i]);
		}
		for (int i = 0; i < distance.length; i++)
		{
			String s = distance[i].split("=")[0];
			ctsDistance.put(s, Integer.parseInt(distance[i].split("=")[1]));
			 String ss="";  
			    for(int j=1;j>=0;j--){  
			        ss+=s.charAt(j);  
			}  
			    ctsDistance.put(ss, Integer.parseInt(distance[i].split("=")[1]));
		}
		
		
		int M = 5000;
		String start = "";
		String purpose = "";
		List<String> atPresentPurposes;
		int purposeId = 0;
		String saveMessageFor0 = "";
		for (int i = 0; i < M; i++)
		{
			start = "";
			atPresentPurposes = new ArrayList<String>(purposes);
			for (int j = 0; j < cts.length-1; j++)
			{
				if("".equals(start))
				{
					start = cts[0];
				}
				purposeId = change(start, atPresentPurposes);
				purpose = atPresentPurposes.get(purposeId);
				atPresentPurposes.remove(purposeId);
				
				message[ctsNum.get(start)][ctsNum.get(purpose)]++;
				message[ctsNum.get(purpose)][ctsNum.get(start)]++;
				saveMessage.add(start+","+purpose);
				start = purpose;
			}
			while(saveMessage.size() > 50*cts.length)
			{
				saveMessageFor0 = saveMessage.get(0);
				message[ctsNum.get(saveMessageFor0.split(",")[0])][ctsNum.get(saveMessageFor0.split(",")[1])]--;
				message[ctsNum.get(saveMessageFor0.split(",")[1])][ctsNum.get(saveMessageFor0.split(",")[0])]--;
				saveMessage.remove(0);
			}
		}
		System.out.println(message);
	}
	
	
	public static int change(String start,List<String> purpose){
		int i = random.nextInt(100);
		if(i<=5)
		{
			return random.nextInt(purpose.size());
		}
		else
		{
			int returnCt = 0;
			int sum = 0;
			int[] probability = new int[purpose.size()];
			for (int j = 0; j < purpose.size(); j++)
			{
				if(!start.equals(purpose.get(j)))
				{
					if(message[ctsNum.get(start)][ctsNum.get(purpose.get(j))] == 0)
					{
						sum += (1*120/ctsDistance.get(start+purpose.get(j)));
					}
					else
					{
						sum += ((message[ctsNum.get(start)][ctsNum.get(purpose.get(j))]+1)
								*(message[ctsNum.get(start)][ctsNum.get(purpose.get(j))]+1)
								*120/ctsDistance.get(start+purpose.get(j)));
					}
				}
				probability[j] = sum;
			}
			i = random.nextInt(sum);
			for (int j = 0; j < purpose.size(); j++)
			{
				if(i<=probability[j])
				{
					returnCt = j;
					break;
				}
			}
			return returnCt;
		}
		
	}

}
