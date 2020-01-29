package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import utilities.DBConnection;

public class Salary
{
	public String calculateSalary()
	{
		String result="fail";
		
		
		
		try
		{
			Connection con=DBConnection.createConnection();
			Statement st=con.createStatement();
			String qry="select emp_id,sum(total_hours) tot from emp_ts where status='approved' group by emp_id";
			ResultSet rs=st.executeQuery(qry);
			double normalpay=0.0;
			double extrapay=0.0;
			Statement st2=con.createStatement();
			ResultSet rs1=st2.executeQuery("select * from paymode");
			if(rs1.next())
			{
				normalpay=rs1.getDouble("normal_pay");
				extrapay=rs1.getDouble("extra_pay");
			}
			else
			{
				result="Pay mode table is empty";
				return result;
			}
			
			while(rs.next())
			{
				double tot=rs.getDouble("tot");
				
				String qry1="";
				double amount=0.0;
				if(tot<=80)
				{
					
					
					
					amount=tot*normalpay;
					 
				}
				else
				{
					double extra=tot-80;
					
					amount=80.0*normalpay + (extra*40.0);
					
				}
					
				qry1="insert into salaries values('"+rs.getString("emp_id")+"',"+tot+","+(amount*0.3)+","+amount+","+(amount-(amount*0.3))+",curdate())";
				Statement st1=con.createStatement();
				int m=st1.executeUpdate(qry1);
				
			}
			
			result="success";
			
		}
		catch(Exception  e)
		{
			result+="Error: "+e;
		}
		return result;
	}
	//select A.*,B.first_name,B.last_name from salaries A, employees B where A.emp_id=B.emp_id
	
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
	
	
	
	public ResultSet getEmpPay(String empid)
	{
		ResultSet rs=null;
		try
		{
			Connection con=DBConnection.createConnection();
			Statement st=con.createStatement();
			String qry="select A.*,B.first_name,B.last_name from salaries A, employees B where A.emp_id='"+empid+"' and B.emp_id='"+empid+"'"; 
			rs=st.executeQuery(qry);
		}
		catch(Exception  e)
		{
		
		}
		return rs;
		
	}
	
	
	public String addPayMode(double normal,double extra)
	{
		String result="fail";
		try
		{
			Connection con=DBConnection.createConnection();
			Statement st=con.createStatement();
			String qry="insert into paymode values("+normal+","+extra+")";
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

		
	/*	try
		{
			Connection con=DBConnection.createConnection();
			Statement st=con.createStatement();
			String qry="select emp_id,sum(total_hours) tot from emp_ts where status='approved' group by emp_id";
			ResultSet rs=st.executeQuery(qry);
			double normalpay=0.0;
			double extrapay=0.0;
			Statement st2=con.createStatement();
			ResultSet rs1=st2.executeQuery("select * from paymode");
			if(rs1.next())
			{
				normalpay=rs1.getDouble("normal_pay");
				extrapay=rs1.getDouble("extra_pay");
			}

			while(rs.next())
			{
				double tot=rs.getDouble("tot");
				
				String qry1="";
				double amount=0.0;
				if(tot<=80)
				{
					
					
					
					amount=tot*normalpay;
					 
				}
				

				
			}
			
			result="success";
			
		}
		catch(Exception  e)
		{
		}
		return result;
	}
	//select A.*,B.first_name,B.last_name from salaries A, employees B where A.emp_id=B.emp_id
	
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
	
	
	
	public ResultSet getEmpPay(String empid)
	{
		ResultSet rs=null;
		try
		{
			Connection con=DBConnection.createConnection();
			Statement st=con.createStatement();
			String qry="select A.*,B.first_name,B.last_name from salaries A, employees B where A.emp_id='"+empid+"' and B.emp_id='"+empid+"'"; 
			rs=st.executeQuery(qry);
		}
		catch(Exception  e)
		{
		
		}
		return rs;
		
	}
	
	
	public String addPayMode(double normal,double extra)
	{
		String result="fail";
		try
		{
			Connection con=DBConnection.createConnection();
			Statement st=con.createStatement();
			String qry="insert into paymode values("+normal+","+extra+")";
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
	
	
	

}*/
