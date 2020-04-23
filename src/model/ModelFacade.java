package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import utilities.DBConnection;

/**
 * Model facade class to ease testing the Model package
 * 
 * Facade for Following methods called from corresponding places
 * In the following format
 * OriginalClass.orginalFunction() -> ModelFacade.OriginalClassorginalFunction()
 * 
 * !
 * !note the lack of camel-case between the facade function orginal class and orginal function name
 * !
 * 
 * Functions by Class:
 * -----------------------------------------------------------
 * TimeSheet.submitTimeSheet()
 *	- Search_Employee.java
 *	
 *TimeSheet.updateTimeSheet()
 *	- Edit_Timesheet_Control.java
 *	
 *TimeSheet.getTimeSheetNotApproved()
 *	- approve_ts.jsp
 *	
 *TimeSheet.addTimeSheet()
 *	- Timesheet_Control.java
 *	
 *TimeSheet.getEmpTimeSheetNotApproved()
 *	- viewemptimesheet.jsp
 *	
 *TimeSheet.getTimeSheetApprovedEmpIds()
 *	- view_timesheet.jsp
 *	
 *-----------------------------------------------------------
 *
 *Salary.calculateSalary()
 *	- calsal.jsp
 *	
 *Salary.getEmpPays()
 *	- payslip.jsp
 *	
 *Salary.getEmpPay()
 *	- viewemppayslip.jsp
 *	
 *Salary.addPayMode()
 *	- Paycheck_Generator.java
 *	
 *-----------------------------------------------------------
 *	
 *Employee.addEmployee()
 *	- Registration.java
 *	
 *Employee.deleteEmp()
 *	- delete_emp.jsp
 *	- deleteempcontroller.java
 *	
 *Employee.getEmployee()
 *	- show_emp_rec.jsp
 *	- update_emp_rec.jsp
 *	
 *Employee.getAllEmployees()
 *	- view_emp.jsp
 *	
 *Employee.changePassword()
 *	- Authentication.java
 *	
 *Employee.getPassword()
 *	- forgotpasswordcontroller.java
 * 	
 *Employee.updateEmployee()
 *	- Get_Profile.java
 *	
 *-----------------------------------------------------------
 *
 *	
 *Employer.authenticate()
 *	- emplrlogincontroller.java
 *
 *-----------------------------------------------------------
 *
 *User.authenticate()
 *	- Login.java
 *
 *-----------------------------------------------------------
 *
 *Security_Question.registeremployee()
 *	- empregistrationcontroller.java
 * 
 */
public class ModelFacade
{

	//TimeSheet Methods-----------------------------------------------------------
	public static String TimeSheetaddTimeSheet(String tid,String eid,String day,String dt,String intime,String lout, String lin,String cout)
	{		
		return (new TimeSheet()).addTimeSheet( tid, eid, day, dt, intime, lout, lin, cout);
	}
		
	public static String TimeSheetupdateTimeSheet(String tid,String intime,String lout, String lin,String cout,String thours)
	{
		return (new TimeSheet()).updateTimeSheet( tid, intime, lout,  lin, cout, thours);		
	}
		
	public static void  TimeSheetsubmitTimeSheet(String empid)
	{
		(new TimeSheet()).submitTimeSheet(empid);			
	}
	
	public static ResultSet TimeSheetgetEmpTimeSheetNotApproved(String empid)
	{
		return (new TimeSheet()).getEmpTimeSheetNotApproved( empid);
	}
	
	public static ResultSet TimeSheetgetTimeSheetApproved(String empid)
	{
		return (new TimeSheet()).getTimeSheetApproved( empid);
	}	
	
	public static ResultSet TimeSheetgetTimeSheetNotApproved()
	{
		return (new TimeSheet()).getTimeSheetNotApproved();
	}
		
	public static ResultSet TimeSheetgetTimeSheetApprovedEmpIds()
	{

		return (new TimeSheet()).getTimeSheetApprovedEmpIds();
		
	}
	
	public static ResultSet TimeSheetgetSavedTimeSheet(String empid)
	{
		return (new TimeSheet()).getSavedTimeSheet(empid);
	}
	
	public static String TimeSheetisSubmitted(String empid)
	{
		return (new TimeSheet()).isSubmitted(empid);
	}
	
	//Salary Methods-----------------------------------------------------------
	public static String SalarycalculateSalary()
	{
		return (new Salary()).calculateSalary();
	}
		
	public static ResultSet SalarygetEmpPays()
	{
		return (new Salary()).getEmpPays();
	}
		
	public static ResultSet SalarygetEmpPay(String empid)
	{
		return (new Salary()).getEmpPay(empid);
	}
		
	public static String SalaryaddPayMode(double normal,double extra)
	{
		return (new Salary()).addPayMode( normal, extra);
	}
	
	//Employee Methods-----------------------------------------------------------
	public  static String EmployeeaddEmployee(String eid,String fname,String lname,String gen,String dob, String job,String contact,String email,String addr,String accno,String bname)
	{
		return (new Employee()).addEmployee( eid, fname, lname, gen, dob,  job, contact, email, addr, accno, bname);
	}
	
	public static String EmployeechangePassword(String empid,String uid,String s1,String a1,String s2,String a2,String s3,String a3,String oldpass,String newpass)
	{
		return (new Employee()).changePassword( empid, uid, s1, a1, s2, a2, s3, a3, oldpass, newpass);
	}

	public static String EmployeedeleteEmp(String empid)
	{
		return (new Employee()).deleteEmp( empid);
	}
	
	public static String EmployeegetPassword(String empid,String uid,String sq1,String a1,String sq2, String a2,String sq3,String a3)
	{
		return  (new Employee()).getPassword( empid, uid, sq1, a1, sq2,  a2, sq3, a3);	
	}
	
	public static ResultSet EmployeegetEmployee(String empid)
	{
		return (new Employee()).getEmployee( empid);
	}
	
	public static ResultSet EmployeegetAllEmployees()
	{
		return (new Employee()).getAllEmployees();
	}
	
	public static String EmployeeupdateEmployee(String eid,String fname,String lname,String gen,String dob, String job,String contact,String email,String addr,String accno,String bname)
	{
		return (new Employee()).updateEmployee( eid, fname, lname, gen, dob,  job, contact, email, addr, accno, bname);
	}
	
	//Employer Methods-----------------------------------------------------------
	public static String Employerauthenticate(String username,String password)
	{
		return (new Employer()).authenticate( username, password);
	}
	
	//User Methods-----------------------------------------------------------
	public static String Userauthenticate(String eid,String username,String password)
	{
		return (new User()).authenticate( eid, username, password);
	}
	
	//Security_Question Methods-----------------------------------------------------------
	public static String Security_Questionregisteremployee(String empid,String userid,String password,String sq1,String ans1,String sq2, String ans2,String sq3,String ans3 )
	{
		return (new Security_Question()).registeremployee( empid, userid, password, sq1, ans1, sq2,  ans2, sq3, ans3 ); 
	}
}
