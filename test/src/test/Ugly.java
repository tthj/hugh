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
				System.out.println("年龄定义，初始值18");
				String sex = "女";
				System.out.println("性别定义，女");
				String info = "这是一个" + sex + "孩子" + "年龄为" + age;
				System.out.println("整合了两个变量" + info);
				System.setOut(out);
				System.out.println("程序运行完毕");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

}
