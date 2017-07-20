package test;

import java.io.InputStream;
import java.util.Scanner;

public class Test4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner s1 = new Scanner(System.in);
		int s = s1.nextInt();
		int[] a = new int[s];
		for (int i = 0; i < a.length; i++) {
			a[i] = s1.nextInt();
			if(a[i]>1000000000||a[i]<-1000000000){
				System.out.println("输入错误！请重新输入");
				i--;
			}
		}
		if(s>1){
			int[] b = new int[s-1];
			for (int i = 0; i < b.length; i++) {
				b[i] = a[i] - a[i+1];
				if(b[i]<0){
					b[i] = -b[i];
				}
			}
			int t2 = 0;
			int t3 = b.length-1;
			for (int i = 0; i < b.length; i++) {
				int t1 = 0;
				for (int j = 0; j < t3; j++) {
					if(b[j]>b[j+1]){
						b[j] = b[j+1] +b[j];
						b[j+1] = b[j] - b[j+1];
						b[j] = b[j] - b[j+1];
						t1=1;
						t2=j;
					}
				}
				t3 = t2;
				if(t1 == 0){
					break;
				}
			}
			boolean f = true;
			for (int i = 0; i < b.length; i++) {
				if(b[i]!=i+1){
					System.out.println("No Jolly");
					f = false;
					break;
				}
			}	
			if(f){
				System.out.println("Jolly");
			}
		}else if(s==0){
			System.out.println("No Jolly");
		}else{
			System.out.println("Jolly");
		}
	}

}
