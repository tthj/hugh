package test;

import java.util.Scanner;

public class Test6 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		String s1 = s.nextLine();
		String[] s2 = s1.split(" ");
		int a1 = Integer.parseInt(s2[0]);
		int a2 = Integer.parseInt(s2[1]);
		String[] s3 = new String[a1];
		String c = "";
		int d =0;
		for(int i = 0; i < s3.length; i++) {
			s3[i] = s.next();
			char[] a = s3[i].toCharArray();
			char[] e = c.toCharArray();
			c="";
			for (int j = 0; j < a.length; j++) {
				if(a[j]=='#'){
					d++;
					if(j<a.length-1 && a[j+1]=='#'){
						d--;
					}
					for (int k = 0; k < e.length; k++) {
						if(j == Integer.parseInt(String.valueOf(e[k]))){
							d--;
						}
					}
					c += j+"";
				}
			}
		}
		System.out.println(d);
	}

}
