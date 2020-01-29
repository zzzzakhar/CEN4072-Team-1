package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import utilities.DBConnection;

public class Employee 
{
	public String addEmployee(String eid,String fname,String lname,String gen,String dob, String job,String contact,String email,String addr,String accno,String bname)
	{
		String result="fail";
		try
		{
			Connection con=DBConnection.createConnection();
			Statement st=con.createStatement();
			String qry="insert into employees values('"+eid+"','"+fname+"','"+lname+"','"+gen+"','"+dob+"','"+job+"',"+contact+",'"+email+"','"+addr+"',"+accno+",'"+bname+"',CURDATE())";
			int n=st.executeUpdate(qry);
			
			if(n==1)
				result="success";
			else
				result="fail";
			
			if (fname == "failure")
			{
				result = "fail";
			}
			
		}
		catch(Exception e)
		{
		}
		
		return result;
	}
	
	
	public String changePassword(String empid,String uid,String s1,String a1,String s2,String a2,String s3,String a3,String oldpass,String newpass)
	{
		String result="fail";
		String qry="";
		try
		{
			Connection con=DBConnection.createConnection();
			Statement st=con.createStatement();
			 qry="update users set password='"+newpass+"' where emp_id='"+empid+"' and user_id='"+uid+"' and sec_que1='"+s1+"' and ans1='"+a1+"' and sec_que2='"+s2+"' and ans2='"+a2+"' and sec_que3='"+s3+"' and ans3='"+a3+"'";
			int n=st.executeUpdate(qry);
			
			if(n==1)
				result="success";
			else
				result="fail : Account detials incorrect";
			
		}
		catch(Exception e)
		{
		}
		
		return result;
		
	}
	
	public String deleteEmp(String empid)
	{
		String result="fail";
		String qry="";
		try
		{
			Connection con=DBConnection.createConnection();
			Statement st=con.createStatement();
			 qry="delete from employees where emp_id='"+empid+"'";
			int n=st.executeUpdate(qry);
			
			if(n==1)
				result="success";
			else
				result="fail";
			
		}
		catch(Exception e)
		{
		}
		
		return result;
		
	}
	
	
	public String getPassword(String empid,String uid,String sq1,String a1,String sq2, String a2,String sq3,String a3)
	{
		String result="fail ";
		try
		{
			Connection con=DBConnection.createConnection();
			String qry="select password from users where emp_id='"+empid+"' and user_id='"+uid+"' and sec_que1='"+sq1+"' and ans1='"+a1+"' and sec_que2='"+sq2+"' and ans2='"+a2+"' and sec_que3='"+sq3+"' and ans3='"+a3+"'";
			
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(qry);
			if(rs.next())
			{
				result="success,"+rs.getString("password");
			}
			else
			{
				//result+=qry;
			}
			st.close();
			con.close();
		
		}
		catch(Exception e)
		{
			result="Error :"+result;
		}
		return result;
			
	}
	
	
	public ResultSet getEmployee(String empid)
	{
		ResultSet rs=null;
		
		try
		{
			Connection con=DBConnection.createConnection();
			Statement st=con.createStatement();
			String qry="select * from employees where emp_id='"+empid+"'";
			rs=st.executeQuery(qry);
		}
		catch(Exception  e)
		{
		   rs=null;
		}
		return rs;
		
	}
	
	
	public ResultSet getAllEmployees()
	{
		ResultSet rs=null;
		
		try
		{
			Connection con=DBConnection.createConnection();
			Statement st=con.createStatement();
			String qry="select * from employees";
			rs=st.executeQuery(qry);
		}
		catch(Exception  e)
		{
		   rs=null;
		}
		return rs;
		
	}
	
	
	public String updateEmployee(String eid,String fname,String lname,String gen,String dob, String job,String contact,String email,String addr,String accno,String bname)
	{
		String result="fail";
		try
		{
			Connection con=DBConnection.createConnection();
			Statement st=con.createStatement();
			String qry="update employees set first_name='"+fname+"',last_name='"+lname+"',gender='"+gen+"',dob='"+dob+"',job='"+job+"',phone="+contact+",email_id='"+email+"',address='"+addr+"',accno='"+accno+"',bankname='"+bname+"' where emp_id='"+eid+"'";
			int n=st.executeUpdate(qry);
			
			if(n==1)
				result="success";
			else
				result="fail";
			
		}
		catch(Exception e)
		{
			result="fail : "+e.toString();
		}
		
		return result;
	}
	
	
	
	
	
	

}
