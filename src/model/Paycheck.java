package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import utilities.DBConnection;

public class Paycheck {
	
	public String addPayMode(double normal,double extra)
	{
		String result="fail";
		try
		{
			Connection con=DBConnection.createConnection();
			Statement st=con.createStatement();
			String qry="insert into paymode values("+normal+","+extra+")";
			int n=st.executeUpdate(qry);
			
			
			if(n==1){
				result="success";
			}

			
		}
		catch(Exception e)
		{
			result="fail";
		}
		
		return result;
	}
	
	
	
	public ResultSet getEmpPays()
	{
		ResultSet rs=null;
		try
		{
			Connection con=DBConnection.createConnection();
			Statement st=con.createStatement();
			String qry="select A.*,B.first_name,B.last_name from salaries A, employees B where A.emp_id=B.emp_id"; 
			rs=st.executeQuery(qry);
		}
		catch(Exception  e)
		{
		
		}
		return rs;
		
	}
	

}

