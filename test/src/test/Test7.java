package test;

import java.util.Scanner;

public class Test7 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner a = new Scanner(System.in);
		String s1 = a.next();
		String s2 = a.next();
		
		char[] b1 = s1.toCharArray();
		char[] b2 = s2.toCharArray();
		
		int c = b2.length;
		
		Boolean f = false;
		for (int i = 0; i < b1.length; i++) {
			if(b1[i] == b2[0]){
				if(b1.length - i > b2.length){
					g: for (int k = 1; k < b2.length; k++) {
						if(b1[i+k] != b2[k]){
							f = true;
							break g;
						} 
					}
					if(!f){
						System.out.println(true);
						break;
					}else {
						f = false;
					}
				}else{
					h: for (int k = 1; k < b2.length; k++) {
						if(k + i < b1.length - 1 && b1[i+k] != b2[k]){
							f = true;
							break h;
						}else if(k + i > b1.length -1 && b1[k + i - b1.length] != b2[k]){
							f = true;
							break h;
						}
					}
					if(!f){
						System.out.println(true);
						break;
					}else {
						f = false;
					}
				}
			}
			if(i+1 == b1.length){
				System.out.println(false);
			}
		}
	}

}
