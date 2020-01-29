package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import utilities.DBConnection;

public class TimeSheet {
	public String addTimeSheet(String tid,String eid,String day,String dt,String intime,String lout, String lin,String cout)
	{
		String result="fail";
		String qry="";
		try
		{
			
			
			Connection con=DBConnection.createConnection();
			Statement st=con.createStatement();
						
			ResultSet rs=st.executeQuery("select addtime(timediff('"+lout+"','"+intime+"'),timediff('"+cout+"','"+lin+"')) tot");
			double thours=0.0;
			if(rs.next())
			{
				String time=rs.getString("tot");
				String s[]=time.split(":");
				thours=Double.parseDouble(s[0]);
				double min=Double.parseDouble(s[1]);
						
				min=min/100.0;
				thours+=min;
			}
				
			qry="insert into save_ts values('"+tid+"','"+eid+"','"+day+"','"+dt+"','"+intime+"','"+lout+"','"+lin+"','"+cout+"',"+thours+",'not approved',CURDATE())";
			int n=st.executeUpdate(qry);
			
			if(n==1)
				
				result="success";
			else
				result="fail";
			
		}
		catch(Exception e)
		{
			result="fail : "+qry+" | " +e.toString();
		}
		
		return result;
	}
	
	
	public String updateTimeSheet(String tid,String intime,String lout, String lin,String cout,String thours)
	{
		String result="success";
		
		try
		{
			Connection con=DBConnection.createConnection();
			Statement st=con.createStatement();
						
			String qry="update emp_ts  set intime='"+intime+"',lunch_out='"+lout+"',lunch_in='"+lin+"',outtime='"+cout+"',total_hours="+thours+" where ets_id='"+tid+"'";
			int n=st.executeUpdate(qry);
			
		}
		catch(Exception e)
		{
			result=e.toString();
			
		}
		
		return result;
		
	}
	
	
	public void  submitTimeSheet(String empid)
	{
		
		String qry="";
		
		try
		{
			Connection con=DBConnection.createConnection();
			Statement st=con.createStatement();
			
			int n=st.executeUpdate("insert into emp_ts (select * from save_ts where emp_id='"+empid+"')");
			n=st.executeUpdate("delete from save_ts  where emp_id='"+empid+"'");
			
		}
		catch(Exception e)
		{
		
		}
		
		
	}
	
	
	
	
	
	
	
	
	public ResultSet getTimeSheetNotApproved()
	{
		ResultSet rs=null;
		
		try
		{
			Connection con=DBConnection.createConnection();
			Statement st=con.createStatement();
			String qry="select * from emp_ts where status='not approved' order by emp_id";
			rs=st.executeQuery(qry);
		}
		catch(Exception  e)
		{
		
		}
		return rs;
		
	}
	
	
	public ResultSet getEmpTimeSheetNotApproved(String empid)
	{
		ResultSet rs=null;
		
		try
		{
			Connection con=DBConnection.createConnection();
			Statement st=con.createStatement();
			String qry="select * from emp_ts where status='not approved' and emp_id='"+empid+"'";
			rs=st.executeQuery(qry);
		}
		catch(Exception  e)
		{
		
		}
		return rs;
		
	}
	
	public ResultSet getTimeSheetApproved(String empid)
	{
		ResultSet rs=null;
		
		try
		{
			Connection con=DBConnection.createConnection();
			Statement st=con.createStatement();
			String qry="";
			if(empid.equals("All"))
				qry="select * from emp_ts where status='approved' order by emp_id";
			else
			   qry="select * from emp_ts where status='approved' and emp_id='"+empid+"' order by emp_id";
			rs=st.executeQuery(qry);
		}
		catch(Exception  e)
		{
		
		}
		return rs;
		
	}
	
	
	public ResultSet getTimeSheetApprovedEmpIds()
	{
		ResultSet rs=null;
		
		try
		{
			Connection con=DBConnection.createConnection();
			Statement st=con.createStatement();
			String qry="select distinct emp_id from emp_ts where status='approved'";
			rs=st.executeQuery(qry);
		}
		catch(Exception  e)
		{
		
		}
		return rs;
		
	}
	
	
	
	public ResultSet getSavedTimeSheet(String empid)
	{
		ResultSet rs=null;
		
		try
		{
			Connection con=DBConnection.createConnection();
			Statement st=con.createStatement();
			String qry="";
			
			   qry="select * from save_ts where emp_id='"+empid+"'";
			rs=st.executeQuery(qry);
		}
		catch(Exception  e)
		{
			
		}
		return rs;
		
	}
	
	
	public String isSubmitted(String empid)
	{
		String result="fail";
	try
	{
		Connection con=DBConnection.createConnection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select max(date1) into @maxdate from emp_ts where emp_id='"+empid+"'");
		rs=st.executeQuery("select datediff(curdate(),@maxdate) days");
		if(rs.next())
		{
		
		int n=rs.getInt("days");
		String str=rs.getString("days") ;


		if(n>7 || str==null)
		{
		  	result="success";
		}
		}
	}catch(Exception e){
		
	}
		return result;
	}


}
		/*try
		{
			Connection con=DBConnection.createConnection();
			Statement st=con.createStatement();
			
			ResultSet rs=st.executeQuery("select addtime(timediff('"+lout+"','"+intime+"'),timediff('"+cout+"','"+lin+"')) tot");
			double thours=0.0;
			if(rs.next())
			{
				String time=rs.getString("tot");
				String s[]=time.split(":");
				thours=Double.parseDouble(s[0]);
				double min=Double.parseDouble(s[1]);
						
				min=min/100.0;
				thours+=min;
			}
				
			qry="insert into save_ts values('"+tid+"','"+eid+"','"+day+"','"+dt+"','"+intime+"','"+lout+"','"+lin+"','"+cout+"',"+thours+",'not approved',CURDATE())";
			int n=st.executeUpdate(qry);
			
			if(n==1)
				
				result="success";
			else
				result="fail";
			
		}
		catch(Exception e)
		{
			result="fail : "+qry+" | " +e.toString();
		}
		
		return result;
	}
	
	
	public String updateTimeSheet(String tid,String intime,String lout, String lin,String cout,String thours)
	{
		String result="success";
		
		try
		{
			Connection con=DBConnection.createConnection();
			Statement st=con.createStatement();
						
			String qry="update emp_ts  set intime='"+intime+"',lunch_out='"+lout+"',lunch_in='"+lin+"',outtime='"+cout+"',total_hours="+thours+" where ets_id='"+tid+"'";
			int n=st.executeUpdate(qry);
			
		}
		catch(Exception e)
		{
			result=e.toString();
			
		}
		
		return result;
		
	}
	
	
	public void  submitTimeSheet(String empid)
	{
		
		String qry="";
		
		try
		{
			Connection con=DBConnection.createConnection();
			Statement st=con.createStatement();
			
			int n=st.executeUpdate("insert into emp_ts (select * from save_ts where emp_id='"+empid+"')");
			n=st.executeUpdate("delete from save_ts  where emp_id='"+empid+"'");
			
		}
		catch(Exception e)
		{
		
		}
		
		
	}

	public ResultSet getTimeSheetNotApproved()
	{
		ResultSet rs=null;
		
		try
		{
			Connection con=DBConnection.createConnection();
			Statement st=con.createStatement();
			String qry="select * from emp_ts where status='not approved' order by emp_id";
			rs=st.executeQuery(qry);
		}
		catch(Exception  e)
		{
		
		}
		return rs;
		
	}
	
	
	public ResultSet getEmpTimeSheetNotApproved(String empid)
	{
		ResultSet rs=null;
		
		try
		{
			Connection con=DBConnection.createConnection();
			Statement st=con.createStatement();
			String qry="select * from emp_ts where status='not approved' and emp_id='"+empid+"'";
			rs=st.executeQuery(qry);
		}
		catch(Exception  e)
		{
		
		}
		return rs;
		
	}
	
	public ResultSet getTimeSheetApproved(String empid)
	{
		ResultSet rs=null;
		
		try
		{
			Connection con=DBConnection.createConnection();
			Statement st=con.createStatement();
			String qry="";
			if(empid.equals("All"))
				qry="select * from emp_ts where status='approved' order by emp_id";
			else
			   qry="select * from emp_ts where status='approved' and emp_id='"+empid+"' order by emp_id";
			rs=st.executeQuery(qry);
		}
		catch(Exception  e)
		{
		
		}
		return rs;
		
	}
	
	
	public ResultSet getTimeSheetApprovedEmpIds()
	{
		ResultSet rs=null;
		
		try
		{
			Connection con=DBConnection.createConnection();
			Statement st=con.createStatement();
			String qry="select distinct emp_id from emp_ts where status='approved'";
			rs=st.executeQuery(qry);
		}
		catch(Exception  e)
		{
		
		}
		return rs;
		
	}


}
*/