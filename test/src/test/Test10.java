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
 * Test10.java对象.
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
	 * 功能：将一个填充好的实体类传入，返回一个map，key是属性名字，value是值
	 * 
	 * @param model
	 * @return
	 */
	public static Map<String, String> getObjectFieldFormat( Object model )
	{
		Map<String, String> map = new HashMap<String, String>();
		Field[] field = model.getClass().getDeclaredFields();
		for (int j = 0; j < field.length; j++)
		{ // 遍历所有属性
			String name = field[j].getName(); // 获取属性的名字
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
