package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import utilities.DBConnection;

public class Security_Question 
{
	
	public String registeremployee(String empid,String userid,String password,String sq1,String ans1,String sq2, String ans2,String sq3,String ans3 )
	{
		String result="fail";
		try
		{
			Connection con=DBConnection.createConnection();
			Statement st=con.createStatement();
			String qry="insert into users values('"+empid+"','"+userid+"','"+password+"','"+sq1+"','"+ans1+"','"+sq2+"','"+ans2+"','"+sq3+"','"+ans3+"',CURDATE())";
			int n=st.executeUpdate(qry);
			
			if(n==1)
				result="success";
			else
				result="fail";
			
		}
		catch(Exception e)
		{
			result="fail";
		}
		
		return result;
	}


}
