/*******************************************************************
 * TestExcel.java   2016-11-29
 * Copyright 2016 by GNNT Company. All Rights Reserved.
 * Author:	hugh
 ******************************************************************/
package test;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import jxl.Cell;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * TestExcel.java对象.
 *
 * <p><a href="TestExcel.java.html"><i>View Source</i></a></p>
 *
 * @version 1.0.0.1
 * @author <a href="mailto:hugh@gnnt.com.cn">HuGenHao</a>
 */
public class TestExcel
{

	/**
	 * 功能：<br/>
	 * @param args
	 * @exception   无
	 * @since   	首次创建（hugh  2016-11-29)<br/>
	 * 
	 */
	public static void main( String[] args )
	{
		 int i;
	        Sheet sheet;
	        Workbook book;
	        Cell cell1,cell2,cell3,cell4,cell5,cell6,cell7;
	        Statement sttt = sss.getOracle1();
	        ResultSet  rs;
	        ResultSet  rs1;
	        try { 
	            //t.xls为要读取的excel文件名
	            book= Workbook.getWorkbook(new java.io.File("C:\\Users\\admin\\Desktop\\123456789.xls")); 
	            
	            //获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
	            sheet=book.getSheet(0); 
	            //获取左上角的单元格
	            cell1=sheet.getCell(0,0);
	            System.out.println("标题："+cell1.getContents()); 
	            
	            i=1;
	            String sql = "update ZYH_ARTICLE set author='佚名',source='互联网' where source in (";
	            SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd");  
	            while(true)
	            {
	                //获取每一行的单元格 
	            	try
					{
	            		cell1=sheet.getCell(0,i);//（列，行）
					}
					catch (Exception e)
					{
						System.out.println(i);
						break;
					}
	         /*       cell2=sheet.getCell(1,i);
	                cell3=sheet.getCell(2,i);//（列，行）
	                cell4=sheet.getCell(3,i);*/
	                if("".equals(cell1.getContents())==true)    //如果读取的数据为空
	                    break;
	                try
					{	
	                	sql += "'"+cell1.getContents().toString()+"',";
					}
					catch (Exception e)
					{
						e.printStackTrace();
						System.out.println(sql);
					}
	                i++;
	            }
	            try
				{
        			//sttt.execute(sql1);
        			sttt.execute(sql);
				}
				catch (Exception e)
				{
					e.printStackTrace();
					System.out.println(sql);
				}
	            book.close(); 
	        }
	        catch(Exception e)  { 
	        	e.printStackTrace();
	        } 

	}
	
	

}
