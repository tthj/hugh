/*******************************************************************
 * Test10.java   2016-9-18
 * Copyright 2016 by GNNT Company. All Rights Reserved.
 * Author:	hugh
 ******************************************************************/
package test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Test10.java����.
 *
 * <p><a href="Test10.java.html"><i>View Source</i></a></p>
 *
 * @version 1.0.0.1
 * @author <a href="mailto:hugh@gnnt.com.cn">HuGenHao</a>
 */
public class Test10
{
	public static void main( String[] args )
	{
		getObjectFieldFormat(new test());
	}
	
	public static class test{
		public static final String sh = "123";
	}
	/**
	 * ���ܣ���һ�����õ�ʵ���ഫ�룬����һ��map��key���������֣�value��ֵ
	 * 
	 * @param model
	 * @return
	 */
	public static Map<String, String> getObjectFieldFormat( Object model )
	{
		Map<String, String> map = new HashMap<String, String>();
		Field[] field = model.getClass().getDeclaredFields();
		for (int j = 0; j < field.length; j++)
		{ // ������������
			String name = field[j].getName(); // ��ȡ���Ե�����
			java.lang.reflect.Method m;
			try
			{
				m = model.getClass().getMethod(name);
				map.put(name.toLowerCase(), m.invoke(model).toString());
			}
			catch (NoSuchMethodException e)
			{
				e.printStackTrace();
			}
			catch (SecurityException e)
			{
				e.printStackTrace();
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}
			catch (IllegalArgumentException e)
			{
				e.printStackTrace();
			}
			catch (InvocationTargetException e)
			{
				e.printStackTrace();
			}
		}// Enf for
		return map;
	}
}
