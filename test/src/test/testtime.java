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
		// calendar���Խ�ʱ�侫ȷ������
		Calendar calendar = new GregorianCalendar();
		System.out.println("�꣺" + calendar.get(Calendar.YEAR));
		System.out.println("�£�" + (calendar.get(Calendar.MONTH) + 1));
		System.out.println("�գ�" + calendar.get(Calendar.DAY_OF_MONTH));
		System.out.println("ʱ��" + calendar.get(Calendar.HOUR_OF_DAY));
		System.out.println("�֣�" + calendar.get(Calendar.MINUTE));
		System.out.println("�룺" + calendar.get(Calendar.SECOND));
		System.out.println("���룺" + calendar.get(Calendar.MILLISECOND));
		System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

		
	}

}
