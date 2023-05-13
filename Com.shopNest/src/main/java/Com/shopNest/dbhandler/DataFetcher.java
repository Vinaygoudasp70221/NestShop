package Com.shopNest.dbhandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Com.shopNest.product.Product;

public class DataFetcher 
{
	//featch user password
	public static String featchPassword(String usern)
	{
		String path = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String usn = "system";
		String psw = "system";
		String query = "select pass from customers where uname=? ";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbpassw = "";
		try
		{
			Class.forName(path);
			con = DriverManager.getConnection(url, usn, psw);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, usern);
			rs = pstmt.executeQuery();
			
			while (rs.next()) 
			{
				dbpassw = rs.getString("PASS");
			}

		} 
		catch (Exception e) 
		{
			System.out.println("Username and password not found/login issues");
			e.printStackTrace();
		}

		return dbpassw;

	}
	
	//fetch customer info
	public static List<String> fetchCustomerinfo()
	{
		String path = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String usn = "system";
		String psw = "system";
		String query = "SELECT uname,mail,gender,address FROM customers";
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List ulist=new ArrayList();
		try 
		{
			Class.forName(path);
			con = DriverManager.getConnection(url, usn, psw);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next())
			{
			String temp=rs.getString(1)+":"+rs.getString(2)+":"+rs.getString(3)+":"+rs.getString(4);
			ulist.add(temp);
			}
			
		} 
		catch (Exception e)
		{
			System.out.println("login issue");
		}
		
		return ulist;
	}
	
//admin functionality to display product 
	public static List fetchProductsInfo() {
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user="system";
		String password="system";
		String sql="SELECT pid,pname,pdesc,pprice,pimg FROM products";
		List<String> ilist=new ArrayList<String>();
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection(url, user, password);
			Statement st=con.createStatement();
			
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				String temp=""+rs.getInt(1)+":"+rs.getString(2)+":"+rs.getString(3)+":"+rs.getInt(4)+":"+rs.getString(5);
				ilist.add(temp);
			}
			
		}catch(Exception e){
			System.out.println("Problem in fetching product");
			e.printStackTrace();
		}			
		return ilist;
	}
	
	
	
	
	public static Product getProductById(int pid) {
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user="system";
		String password="system";
		String sql="SELECT pname,pprice FROM products WHERE pid=?";
		Product p=null;
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection(url, user, password);
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, pid);
			ResultSet rs=ps.executeQuery();
			rs.next();
			String pname=rs.getString(1);
			int pprice=rs.getInt(2);
			
			p=new Product(pid,pname,pprice);
		}
		catch(Exception e)
		{
			System.out.println("Problem in fetching product by id");
			e.printStackTrace();
		}			
		finally
		{
			return p;
		}
	}

}
