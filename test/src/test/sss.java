package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sss {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		Statement stat = sss.getOracle();
		Statement stat1 = sss.getOracle1();
		ResultSet s = stat.executeQuery("Select distinct(marketid),marketname from zyh_commodity");
			while(s.next()){try {
				String sql = "insert into o_marketstatus values('"+s.getString("marketId")+"','"+s.getString("marketname")+"','E')";
				stat1.executeUpdate(sql);
			} catch (Exception e) {
			}
			}

	}
	
	public  static Statement  getOracle1(){ 
		try { 
		//1,加载驱动程序 
		Class.forName("oracle.jdbc.driver.OracleDriver"); 
		//2,连接字符串 
		String s = "jdbc:oracle:thin:@127.0.0.1:1521:gnnt"; 
		//3,获得数据库连接 
		Connection con = DriverManager.getConnection(s,"zyhweb_new","ZyhLtqwe"); 
		Statement stat = con.createStatement();
		return stat;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	public  static Statement  getOracle(){ 
		try { 
		//1,加载驱动程序 
		Class.forName("oracle.jdbc.driver.OracleDriver"); 
		//2,连接字符串 
		String s = "jdbc:oracle:thin:@172.18.3.51:1521:gnnt"; 
		//3,获得数据库连接 
		Connection con = DriverManager.getConnection(s,"trade_zyh","zyhsjk"); 
		Statement stat = con.createStatement();
		return stat;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
}
