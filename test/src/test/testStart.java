package test;

import java.util.Date;
import java.util.Random;

public class testStart {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long s = new Date().getTime();
		//for (int i = 0; i < 10000; i++) {
			new Thread1().start();
		//}
		System.out.println(new Date().getTime() - s);
	}
	
	

}
class Thread1 extends Thread{
	
	@Override
	public void run() {
		Random random = new Random();
		System.out.println(random.nextInt(2));
	}
}