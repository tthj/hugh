package test;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Ugly {
	 public static void main(String[] args) {
			try {
				PrintStream out = System.out;
				PrintStream ps = new PrintStream("e:/log.txt");
				System.setOut(ps);
				int age = 18;
				System.out.println("���䶨�壬��ʼֵ18");
				String sex = "Ů";
				System.out.println("�Ա��壬Ů");
				String info = "����һ��" + sex + "����" + "����Ϊ" + age;
				System.out.println("��������������" + info);
				System.setOut(out);
				System.out.println("�����������");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

}
