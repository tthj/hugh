package test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class testtime {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Date d = new Date("2016-09-19");
		
		
		System.out.println(new Date());
		// calendar可以将时间精确到毫秒
		Calendar calendar = new GregorianCalendar();
		System.out.println("年：" + calendar.get(Calendar.YEAR));
		System.out.println("月：" + (calendar.get(Calendar.MONTH) + 1));
		System.out.println("日：" + calendar.get(Calendar.DAY_OF_MONTH));
		System.out.println("时：" + calendar.get(Calendar.HOUR_OF_DAY));
		System.out.println("分：" + calendar.get(Calendar.MINUTE));
		System.out.println("秒：" + calendar.get(Calendar.SECOND));
		System.out.println("毫秒：" + calendar.get(Calendar.MILLISECOND));
		System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

		
	}

}
