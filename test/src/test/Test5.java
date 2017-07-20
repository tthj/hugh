package test;

import java.math.BigInteger;
import java.util.Scanner;

public class Test5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner a = new Scanner(System.in);
		int b = a.nextInt();
		if(b<0||b>10000){
			System.out.println(" ‰»Î∑«∑®£°");
		}else{
			BigInteger c = new BigInteger("1");
			for (int i = 1; i <= b; i++) {
				String d = i+"";
				c = c.multiply(new BigInteger(d));
			}
			System.out.println(c);
		}
		
	}

}
