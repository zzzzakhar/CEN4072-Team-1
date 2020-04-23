package subSystemTests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Employee;
import model.Employer;
import model.ModelFacade;
import model.Salary;
import model.Security_Question;
import model.TimeSheet;
import model.User;
import utilities.DBConnection;

public class ModelFacadeTest 
{
	private String qry1 = "drop database if exists PMS;", 
			qry2 = "create database PMS;",
			qry3 = "use PMS;",
			qry4 = "create table employees(" + 
				"  emp_id VARCHAR(500)," + 
				"  first_name VARCHAR(500)," + 
				"  last_name VARCHAR(500)," + 
				"  gender VARCHAR(500)," + 
				"  dob DATE," + 
				"  job VARCHAR(500)," + 
				"  phone VARCHAR(500)," + 
				"  email_id VARCHAR(500)," + 
				"  address VARCHAR(500)," + 
				"  accno VARCHAR(500)," + 
				"  bankname VARCHAR(500)," + 
				"  joindate DATE" + 
				");",
			qry5 = "create table employer(" + 
				"  username VARCHAR(500)," + 
				"  password VARCHAR(500)" + 
				");",
			qry6 = "create table users(" + 
				"  emp_id VARCHAR(500)," + 
				"  user_id VARCHAR(500)," + 
				"  password VARCHAR(500)," + 
				"  sec_que1 VARCHAR(500)," + 
				"  ans1 VARCHAR(500)," + 
				"  sec_que2 VARCHAR(500)," + 
				"  ans2 VARCHAR(500)," + 
				"  sec_que3 VARCHAR(500)," + 
				"  ans3 VARCHAR(500)," + 
				"  createDate DATE" + 
				");",
			qry7 = "create table paymode(" + 
				"  normal_pay DOUBLE," + 
				"  extra_pay DOUBLE" + 
				");", 
			qry8 = "create table emp_ts(" + 
				"  ets_id VARCHAR(500)," + 
				"  emp_id VARCHAR(500)," + 
				"  day VARCHAR(500)," + 
				"  wdate DATE," + 
				"  intime TIME," + 
				"  lunch_out TIME," + 
				"  lunch_in TIME," + 
				"  outtime TIME," + 
				"  total_hours DOUBLE," + 
				"  status VARCHAR(500)," + 
				"  date1 DATE" + 
				");",
			qry9 = "create table save_ts(" + 
				"  ets_id VARCHAR(500)," + 
				"  emp_id VARCHAR(500)," + 
				"  day VARCHAR(500)," + 
				"  date1 DATE," + 
				"  intime TIME," + 
				"  lunch_out TIME," + 
				"  lunch_in TIME," + 
				"  outtime TIME," + 
				"  total_hours DOUBLE," + 
				"  status VARCHAR(500)," + 
				"  createDate DATE" + 
				");",
			qry10 = "create table salaries(" + 
				"  emp_id VARCHAR(500)," + 
				"  total_hours DOUBLE," + 
				"  tax DOUBLE," + 
				"  gross_sal DOUBLE," + 
				"  net_sal DOUBLE," + 
				"  date1 DATE" + 
				");",
			qry11 = "INSERT INTO `employer` (username, password) VALUES ('user1', 'user1');",
			qry12 = "INSERT INTO `users` (emp_id, user_id, password, createDate) VALUES ('1', 'user1', " + 
				"'user1', '2020-01-12');",
			qry13 = "INSERT INTO `employees` VALUES ('1','Adam','Sandler','on','1901-01-01','Movie " + 
				"Star','0','adam.sandler@email.com','2121 SW 12TH ST','1','Bank of America','2020-" + 
				"01-14')," +
				"('2', 'Dave', 'Grohl', 'yes', '1986-04-22', 'Rock Star', '0', 'test@email.com', " +
				"'900 West St', '2', 'CitiBank', '2020-03-03');",
			qry14 = "INSERT INTO `users` VALUES ('1','adam','adam','Favorite Color?','pink','First PEt " + 
				"Name?','adam','Favorite movie?','adam','2020-01-14');", 
			qry15 = "INSERT INTO `emp_ts` VALUES ('1144','1','Monday','2020-01-" + 
					"01','10:00:00','12:00:00','13:00:00','22:00:00',11,'approved','2020-01-14')," + 
					"('1114','1','Tuesday','2020-01-" + 
					"02','10:00:00','12:00:00','13:00:00','22:00:00',11,'approved','2020-01-14')," + 
					"('1172','1','Wednesday','2020-01-" + 
					"03','10:00:00','12:00:00','13:00:00','22:00:00',11,'approved','2020-01-14')," + 
					"('1341','1','Thursday','2020-01-" + 
					"04','10:00:00','12:00:00','13:00:00','22:00:00',11,'not approved','2020-01-14')," + 
					"('1776','2','Thursday','2020-01-" + 
					"04','10:00:00','12:00:00','13:00:00','22:00:00',11,'approved','2020-01-14')," + 
					"('1337','2','Friday','2020-01-" + 
					"04','10:00:00','12:00:00','13:00:00','22:00:00',11,'not approved','2020-01-14')," + 
					"('1800','1','Friday','2020-01-" + 
					"05','10:00:00','12:00:00','13:00:00','22:00:00',11,'not approved','2020-01-14');",
			qry16 = "INSERT INTO `save_ts` VALUES ('1126','1','Monday','2020-03-04'," + 
				"'10:00:00','12:00:00','13:00:00','22:00:00',11,'approved','2020-03-07');",
			qry17 = "INSERT INTO `paymode` VALUES (10,15);",
			qry18 = "INSERT INTO `salaries` VALUES ('1',55,165,550,385,'2020-01-14')," +
					"('2',22,30,220,285,'2020-01-14');";
	
	@Before
	public void setUp() throws Exception {
		// For setup, we add rows to tables to use to test methods that involve updating, deleting,
		// or requesting data from those tables. We do this directly through DB.connection to avoid using
		// the same code that we're testing
		Connection con = DBConnection.createConnection();
		Statement st = con.createStatement();
				
		// Set up database using the data from pmsdb.sql
		st.addBatch(qry1);
		st.addBatch(qry2);
		st.addBatch(qry3);
		st.addBatch(qry4);
		st.addBatch(qry5);
		st.addBatch(qry6);
		st.addBatch(qry7);
		st.addBatch(qry8);
		st.addBatch(qry9);
		st.addBatch(qry10);
		st.addBatch(qry11);
		st.addBatch(qry12);
		st.addBatch(qry13);
		st.addBatch(qry14);
		st.addBatch(qry15);
		st.addBatch(qry16);
		st.addBatch(qry17);
		st.addBatch(qry18);
		
		int[] res = st.executeBatch();
		
		// Make sure all responses are good
		for (int i : res) {
			if (i == Statement.EXECUTE_FAILED)
				throw new Exception("Error while setting up database for tests, please check statements.");
		}
	}

	@After
	public void tearDown() throws Exception {
		// Same as setup for now to ensure consistency
		Connection con = DBConnection.createConnection();
		Statement st = con.createStatement();
		
		st.addBatch(qry1);
		st.addBatch(qry2);
		st.addBatch(qry3);
		st.addBatch(qry4);
		st.addBatch(qry5);
		st.addBatch(qry6);
		st.addBatch(qry7);
		st.addBatch(qry8);
		st.addBatch(qry9);
		st.addBatch(qry10);
		st.addBatch(qry11);
		st.addBatch(qry12);
		st.addBatch(qry13);
		st.addBatch(qry14);
		st.addBatch(qry15);
		st.addBatch(qry16);
		st.addBatch(qry17);
		st.addBatch(qry18);
		
		int[] res = st.executeBatch();
		
		// Make sure all responses are good
		for (int i : res) {
			if (i == Statement.EXECUTE_FAILED)
				throw new Exception("Error while setting up database for tests, please check statements.");
		}
	}

	private ResultSet resultSet = null; 
	 
	/*
	 * Test Case ID: Subsystem_TimeSheet_001
	 * Purpose: Test if timeSheetaddTimeSheet works properly
	 * Precondition: Have dummy Database setup
	 * Input: "1652","2","Monday","2020-04-14","8:00:00","12:00:00", "13:00:00","16:00:00"
	 * Expected Output: "1652","2","Monday","2020-04-14","8:00:00","12:00:00", "13:00:00","16:00:00" in the DB
	 */
	@Test
	public void addTimeSheetTest() 
	{
		try 
		{
			Connection con = DBConnection.createConnection();	//Makes Connection
			Statement statement = con.createStatement();		//Statement to use .executeQuery()
			ModelFacade.TimeSheetaddTimeSheet("1652","2","Monday","2020-04-14","8:00:00","12:00:00", "13:00:00","16:00:00");
			//Method being tested ^
			
			resultSet = statement.executeQuery("select * from save_ts where emp_id= '2'");
			//ResultSet of ALL (*) timesheets in save_ts with employee id 2 (the one added from method)
			resultSet.next(); //Select the added data
			
			//Test each data column below
			assertTrue(resultSet.getString("ets_id").equals("1652"));
			assertTrue(resultSet.getString("emp_id").equals("2"));
			assertTrue(resultSet.getString("day").equals("Monday"));
			assertTrue(resultSet.getString("date1").equals("2020-04-14"));
			assertTrue(resultSet.getString("intime").equals("08:00:00"));
			assertTrue(resultSet.getString("lunch_out").equals("12:00:00"));
			assertTrue(resultSet.getString("lunch_in").equals("13:00:00"));
			assertTrue(resultSet.getString("outtime").equals("16:00:00"));
			assertTrue(resultSet.getString("status").equals("not approved"));
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Test Case ID: Subsystem_TimeSheet_002
	 * Purpose: Test if timeSheetaddTimeSheet branches if invalid input is entered
	 * Precondition: Have dummy Database setup
	 * Input: "1652","2","Monday","4/14/2020","8hr","12hr", "13hr","16hr"
	 * Expected Output: fail; Due to invalid input
	 */
	@Test
	public void addTimeSheetFailTest() 
	{
		try 
		{
			Connection con = DBConnection.createConnection();	//Makes Connection
			Statement statement = con.createStatement();		//Statement to use .executeQuery()
			String result = ModelFacade.TimeSheetaddTimeSheet("1652","2","Monday","4/14/2020","8hr","12hr", "13hr","16hr");
			//Incorrect inputs

			assertFalse(result.equals("fail"));

			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}


	/*
	 * Test Case ID: Subsystem_TimeSheet_003
	 * Purpose: Test if timeSheetupdateTimeSheet correctly updates a time sheet
	 * Precondition: Have dummy Database setup
	 * Input: "1172","09:00:00","12:00:00", "13:00:00","16:00:00", "6"
	 * Expected Output: The time sheet with id of "1172" should be changed to "1172","09:00:00","12:00:00", "13:00:00","16:00:00", "6"
	 */
	@Test
	public void updateTimeSheetTest() 
	{
		try 
		{
			ModelFacade.TimeSheetupdateTimeSheet("1172","09:00:00","12:00:00", "13:00:00","16:00:00", "6");

			Connection con= DBConnection.createConnection();
			Statement statement=con.createStatement();
			resultSet = statement.executeQuery("select * from emp_ts where ets_id = '1172'");
			resultSet.next();
			
			assertEquals("1172",resultSet.getString("ets_id"));
			assertEquals("09:00:00",resultSet.getString("intime"));
			assertEquals("12:00:00",resultSet.getString("lunch_out"));
			assertEquals("13:00:00",resultSet.getString("lunch_in"));
			assertEquals("16:00:00",resultSet.getString("outtime"));
			assertEquals("6",resultSet.getString("total_hours"));
			
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Test Case ID: Subsystem_TimeSheet_004
	 * Purpose: Test if timeSheetsubmitTimeSheet correctly updates a time sheet
	 * Precondition: Have dummy Database setup
	 * Input: "1"
	 * Expected Output: Employee ID 1's time sheet goes from not approved to approved
	 */
	@Test
	public void submitTimeSheetTest() 
	{
		ModelFacade.TimeSheetsubmitTimeSheet("1");
		try 
		{
			Connection con= DBConnection.createConnection();
			Statement statement=con.createStatement();
			resultSet = statement.executeQuery("select * from emp_ts where ets_id = '1126'");
			resultSet.next();
			
			assertEquals("1",resultSet.getString("emp_id"));
			assertEquals("1126",resultSet.getString("ets_id"));
			assertEquals("approved",resultSet.getString("status"));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Test Case ID: Subsystem_TimeSheet_005
	 * Purpose: Test if timeSheetgetEmpTimeSheetNotAppoved correctly returns time sheets
	 * Precondition: Have dummy Database setup
	 * Input: "1"
	 * Expected Output: 2 not approved time sheets
	 */
	@Test
	public void empNotApprovedTimeSheetTest() 
	{
		
		try 
		{
			Connection con= DBConnection.createConnection();
			Statement statement=con.createStatement();
			int notApprovedCounter = 0;
			resultSet = ModelFacade.TimeSheetgetEmpTimeSheetNotApproved("1");
			resultSet.next();
			
			while(!resultSet.isAfterLast())
			{
				assertTrue(resultSet.getString("status").equals("not approved"));	
				notApprovedCounter++;
				resultSet.next();
			}
			assertEquals(2, notApprovedCounter);
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Test Case ID: Subsystem_TimeSheet_006
	 * Purpose: Test if TimeSheetgetTimeSheetApproved correctly returns time sheets
	 * Precondition: Have dummy Database setup
	 * Input: "1"
	 * Expected Output: 3 approved time sheets
	 */
	@Test
	public void empApprovedTimeSheetTest() 
	{
		
		try 
		{
			Connection con= DBConnection.createConnection();
			Statement statement=con.createStatement();
			int approvedCounter = 0;
			resultSet = ModelFacade.TimeSheetgetTimeSheetApproved("1");
			resultSet.next();
			
			while(!resultSet.isAfterLast())
			{
			assertEquals("approved",resultSet.getString("status"));
			approvedCounter++;
			resultSet.next();
			}
			assertEquals(3, approvedCounter);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Test Case ID: Subsystem_TimeSheet_007
	 * Purpose: Test if TimeSheetgetTimeSheetApproved correctly returns time sheets using the "All" input
	 * Precondition: Have dummy Database setup
	 * Input: "All"
	 * Expected Output: 4 approved time sheets
	 */
	@Test
	public void empApprovedTimeSheetAllTest() 
	{
		
		try 
		{
			Connection con= DBConnection.createConnection();
			Statement statement=con.createStatement();
			int approvedCounter = 0;
			resultSet = ModelFacade.TimeSheetgetTimeSheetApproved("All");
			resultSet.next();
			
			while(!resultSet.isAfterLast())
			{
			assertEquals("approved",resultSet.getString("status"));
			approvedCounter++;
			resultSet.next();
			}
			assertEquals(4, approvedCounter);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Test Case ID: Subsystem_TimeSheet_008
	 * Purpose: Test if TimeSheetgetTimeSheetApprovedEmpIds correctly returns employee IDs
	 * Precondition: Have dummy Database setup
	 * Input: N/A
	 * Expected Output: Emp ID 1 and 2 have approved time sheets
	 */
	@Test
	public void appprovedTimeSheetTest() 
	{
		try 
		{
			Connection con= DBConnection.createConnection();
			Statement statement=con.createStatement();
			int approvedCounter = 0;
			resultSet = ModelFacade.TimeSheetgetTimeSheetApprovedEmpIds();
			resultSet.next();
			
			while(!resultSet.isAfterLast())
			{
			if(approvedCounter==0)
			{assertTrue(resultSet.getString("emp_id").equals("1"));}
			if(approvedCounter==1)
			{assertTrue(resultSet.getString("emp_id").equals("2"));}
			if(approvedCounter==2)//Should be no more approved time sheets
			{assertTrue(false);}
			approvedCounter++;
			resultSet.next();
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
		
	/*
	 * Test Case ID: Subsystem_TimeSheet_009
	 * Purpose: Test if TimeSheetgetTimeSheetApprovedEmpIds correctly returns employee IDs
	 * Precondition: Have dummy Database setup
	 * Input: N/A
	 * Expected Output: Emp ID 1 and 2 have approved time sheets
	 */
	@Test
	public void notAppprovedTimeSheetTest() 
	{
			try 
			{
				Connection con= DBConnection.createConnection();
				Statement statement=con.createStatement();
				int notApprovedCounter = 0;
				resultSet = ModelFacade.TimeSheetgetTimeSheetNotApproved();
				resultSet.next();
				
				while(!resultSet.isAfterLast())
				{
				if(notApprovedCounter==0)
				{assertTrue(resultSet.getString("emp_id").equals("1"));}
				if(notApprovedCounter==1)
				{assertTrue(resultSet.getString("emp_id").equals("1"));}
				if(notApprovedCounter==2)
				{assertTrue(resultSet.getString("emp_id").equals("2"));}
				if(notApprovedCounter==3)//Should be no more non-approved time sheets
				{assertTrue(false);}
				notApprovedCounter++;
				resultSet.next();
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
	}
	
	/*
	 * Test Case ID: Subsystem_TimeSheet_010
	 * Purpose: Test if TimeSheetgetSavedTimeSheet correctly returns time sheets
	 * Precondition: Have dummy Database setup
	 * Input: "1"
	 * Expected Output: 1 saved time sheet with the data "1126", "1", "Monday", "2020-03-04", "10:00:00",
	 *  "12:00:00", "13:00:00", "22:00:00", "11", "approved"
	 */
	@Test
	public void getSavedTimeSheetTest() 
	{
		try 
		{
			Connection con= DBConnection.createConnection();
			Statement statement=con.createStatement();
			int approvedCounter = 0;
			resultSet = ModelFacade.TimeSheetgetSavedTimeSheet("1");
			resultSet.next();
			
			while(!resultSet.isAfterLast())
			{
			if(approvedCounter==0)
			{
				assertTrue(resultSet.getString("ets_id").equals("1126"));
				assertTrue(resultSet.getString("emp_id").equals("1"));
				assertTrue(resultSet.getString("day").equals("Monday"));
				assertTrue(resultSet.getString("date1").equals("2020-03-04"));
				assertTrue(resultSet.getString("intime").equals("10:00:00"));
				assertTrue(resultSet.getString("lunch_out").equals("12:00:00"));
				assertTrue(resultSet.getString("lunch_in").equals("13:00:00"));
				assertTrue(resultSet.getString("outtime").equals("22:00:00"));
				assertTrue(resultSet.getString("total_hours").equals("11"));
				assertTrue(resultSet.getString("status").equals("approved"));
				
			}
			if(approvedCounter==1)//Should be no more approved time sheets
			{assertTrue(false);}
			approvedCounter++;
			resultSet.next();
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * Test Case ID: Subsystem_Employee_011
	 * Purpose: Test if EmployeeaddEmployee correctly adds a new employee into the DB
	 * Precondition: Have dummy Database setup
	 * Input: "3","Kristian","Perez","male","1998-05-11","CEO", "0","kpere192@fiu.edu", "Fake st", "5695012", "Chase"
	 * Expected Output:"3","Kristian","Perez","male","1998-05-11","CEO", "0","kpere192@fiu.edu", "Fake st", "5695012", "Chase" is added to the DB
	 */
	@Test
	public void addEmployeeTest() 
	{
		try 
		{
			Connection con = DBConnection.createConnection();	
			Statement statement = con.createStatement();		
			ModelFacade.EmployeeaddEmployee("3","Kristian","Perez","male","1998-05-11","CEO", "0","kpere192@fiu.edu", "Fake st", "5695012", "Chase");
			
			resultSet = statement.executeQuery("select * from employees where emp_id= '3'");
			resultSet.next(); 
			
			assertTrue(resultSet.getString("emp_id").equals("3"));
			assertTrue(resultSet.getString("first_name").equals("Kristian"));
			assertTrue(resultSet.getString("last_name").equals("Perez"));
			assertTrue(resultSet.getString("gender").equals("male"));
			assertTrue(resultSet.getString("dob").equals("1998-05-11"));
			assertTrue(resultSet.getString("job").equals("CEO"));
			assertTrue(resultSet.getString("phone").equals("0"));
			assertTrue(resultSet.getString("email_id").equals("kpere192@fiu.edu"));
			assertTrue(resultSet.getString("address").equals("Fake st"));
			assertTrue(resultSet.getString("accno").equals("5695012"));
			assertTrue(resultSet.getString("bankname").equals("Chase"));
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Test Case ID: Subsystem_Employee_012
	 * Purpose: Test if EmployeechangePassword correctly updates an employee's password
	 * Precondition: Have dummy Database setup
	 * Input: "1","adam","Favorite Color?","pink","First PEt Name?","adam","Favorite movie?","adam","adam","newPass"
	 * Expected Output: adam's password is changed from adam to newPass
	 */
	@Test
	public void changePasswordTest() 
	{

		try 
		{
			Connection con = DBConnection.createConnection();	
			Statement statement = con.createStatement();		
			ModelFacade.EmployeechangePassword("1","adam","Favorite Color?","pink","First PEt Name?","adam","Favorite movie?","adam","adam","newPass");
			
			resultSet = statement.executeQuery("select * from users where user_id= 'adam'");
			resultSet.next(); 
			
			assertTrue(resultSet.getString("password").equals("newPass"));
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Test Case ID: Subsystem_Employee_013
	 * Purpose: Test if EmployeedeleteEmp correctly removes the employee from the DB
	 * Precondition: Have dummy Database setup
	 * Input: "1"
	 * Expected Output: adam is no longer in the DB
	 */
	@Test
	public void delEmployeeTest() 
	{

		try 
		{
			Connection con = DBConnection.createConnection();	
			Statement statement = con.createStatement();		
			
			resultSet = statement.executeQuery("select * from users where user_id= 'adam'");
			resultSet.next();
			assertTrue(resultSet.getString("user_id").equals("adam"));	//"adam" in DB before del method
			
			ModelFacade.EmployeedeleteEmp("1");
			resultSet = statement.executeQuery("select * from users where emp_id= '1'");
			resultSet.next(); 
			
			assertFalse(resultSet.getString("user_id").equals("adam"));	//"adam" no longer in DB after del method
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Test Case ID: Subsystem_Employee_014
	 * Purpose: Test if EmployeegetPassword correctly gets the employee's password
	 * Precondition: Have dummy Database setup
	 * Input: "1","adam","Favorite Color?","pink","First PEt Name?","adam","Favorite movie?","adam"
	 * Expected Output: "success, adam"
	 */
	@Test
	public void getPasswordTest() 
	{

		try 
		{
			Connection con = DBConnection.createConnection();	
			Statement statement = con.createStatement();	
			
			String Password = ModelFacade.EmployeegetPassword("1","adam","Favorite Color?","pink","First PEt Name?","adam","Favorite movie?","adam");
			resultSet = statement.executeQuery("select * from users where user_id= 'adam'");
			resultSet.next(); 
			
			assertEquals("success,adam", Password);			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Test Case ID: Subsystem_Employee_015
	 * Purpose: Test if EmployeegetPassword correctly fails to get the employee's password
	 * Precondition: Have dummy Database setup
	 * Input: "1","adam","Favorite Color?","red","First Pet Name?","adam","Favorite movie?","adam"
	 * Expected Output: "fail"
	 */
	@Test
	public void getPasswordFailTest() 
	{

		try 
		{
			Connection con = DBConnection.createConnection();	
			Statement statement = con.createStatement();	
			
			String Password = ModelFacade.EmployeegetPassword("1","adam","Favorite Color?","red","First Pet Name?","adam","Favorite movie?","adam");
			resultSet = statement.executeQuery("select * from users where user_id= 'adam'");
			resultSet.next(); 
			
			assertEquals("fail ", Password);			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Test Case ID: Subsystem_Employee_016
	 * Purpose: Test if EmployeegetEmployee correctly gets the employee's data
	 * Precondition: Have dummy Database setup
	 * Input: "1"
	 * Expected Output: "1", "Adam", "Sandler", "1901-01-01", "Movie Star", "0", 
	 * "adam.sandler@email.com", "2121 SW 12TH ST", "1", "Bank of America"
	 */
	@Test
	public void getEmployeeTest() 
	{

		try 
		{
			Connection con = DBConnection.createConnection();	
			Statement statement = con.createStatement();	
			
			resultSet = ModelFacade.EmployeegetEmployee("1");
			resultSet.next();
			
			assertTrue(resultSet.getString("emp_id").equals("1"));			
			assertTrue(resultSet.getString("first_name").equals("Adam"));	
			assertTrue(resultSet.getString("last_name").equals("Sandler"));
			assertTrue(resultSet.getString("dob").equals("1901-01-01"));
			assertTrue(resultSet.getString("job").equals("Movie Star"));
			assertTrue(resultSet.getString("phone").equals("0"));
			assertTrue(resultSet.getString("email_id").equals("adam.sandler@email.com"));
			assertTrue(resultSet.getString("address").equals("2121 SW 12TH ST"));
			assertTrue(resultSet.getString("accno").equals("1"));
			assertTrue(resultSet.getString("bankname").equals("Bank of America"));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}

	/*
	 * Test Case ID: Subsystem_Employee_017
	 * Purpose: Test if EmployeegetAllEmployees correctly gets all employee's data
	 * Precondition: Have dummy Database setup
	 * Input: N/A
	 * Expected Output: "1", "Adam", "Sandler", "1901-01-01", "Movie Star", "0", 
	 * "adam.sandler@email.com", "2121 SW 12TH ST", "1", "Bank of America",
	 * "2", "Dave", "Grohl", "1986-04-22", "Rock Star", "0", "test@email.com", 
	 * "900 West St", "2", "CitiBank"
	 */
	@Test
	public void getAllEmployeesTest() 
	{

		try 
		{
			Connection con = DBConnection.createConnection();	
			Statement statement = con.createStatement();	
			int empCounter = 0;
			
			resultSet = ModelFacade.EmployeegetAllEmployees();
			resultSet.next();
			
			while(!resultSet.isAfterLast())
			{
				if(empCounter == 0)
				{
				assertTrue(resultSet.getString("emp_id").equals("1"));			
				assertTrue(resultSet.getString("first_name").equals("Adam"));	
				assertTrue(resultSet.getString("last_name").equals("Sandler"));
				assertTrue(resultSet.getString("dob").equals("1901-01-01"));
				assertTrue(resultSet.getString("job").equals("Movie Star"));
				assertTrue(resultSet.getString("phone").equals("0"));
				assertTrue(resultSet.getString("email_id").equals("adam.sandler@email.com"));
				assertTrue(resultSet.getString("address").equals("2121 SW 12TH ST"));
				assertTrue(resultSet.getString("accno").equals("1"));
				assertTrue(resultSet.getString("bankname").equals("Bank of America"));
				}
				if(empCounter == 1)
				{
				assertTrue(resultSet.getString("emp_id").equals("2"));			
				assertTrue(resultSet.getString("first_name").equals("Dave"));	
				assertTrue(resultSet.getString("last_name").equals("Grohl"));
				assertTrue(resultSet.getString("dob").equals("1986-04-22"));
				assertTrue(resultSet.getString("job").equals("Rock Star"));
				assertTrue(resultSet.getString("phone").equals("0"));
				assertTrue(resultSet.getString("email_id").equals("test@email.com"));
				assertTrue(resultSet.getString("address").equals("900 West St"));
				assertTrue(resultSet.getString("accno").equals("2"));
				assertTrue(resultSet.getString("bankname").equals("CitiBank"));
				}
				if(empCounter == 3) //Should be no more employees
				{
					assertTrue(false);
				}
				
				empCounter++;
				resultSet.next();
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Test Case ID: Subsystem_Employee_018
	 * Purpose: Test if EmployeeupdateEmployee correctly updates employee's data
	 * Precondition: Have dummy Database setup
	 * Input: "1","Kristian","Perez","male","1998-05-11","CEO", "0","kpere192@fiu.edu", "Fake st", "5695012", "Chase"
	 * Expected Output: Adam's data is replace with 
	 * "1","Kristian","Perez","male","1998-05-11","CEO", "0","kpere192@fiu.edu", "Fake st", "5695012", "Chase"
	 */
	@Test
	public void updateEmployeeTest() 
	{
		try 
		{
			Connection con = DBConnection.createConnection();	
			Statement statement = con.createStatement();		
			ModelFacade.EmployeeupdateEmployee("1","Kristian","Perez","male","1998-05-11","CEO", "0","kpere192@fiu.edu", "Fake st", "5695012", "Chase");
			
			resultSet = statement.executeQuery("select * from employees where emp_id= '1'");
			resultSet.next(); 
						
			assertTrue(resultSet.getString("emp_id").equals("1"));
			assertTrue(resultSet.getString("first_name").equals("Kristian"));
			assertTrue(resultSet.getString("last_name").equals("Perez"));
			assertTrue(resultSet.getString("gender").equals("male"));
			assertTrue(resultSet.getString("dob").equals("1998-05-11"));
			assertTrue(resultSet.getString("job").equals("CEO"));
			assertTrue(resultSet.getString("phone").equals("0"));
			assertTrue(resultSet.getString("email_id").equals("kpere192@fiu.edu"));
			assertTrue(resultSet.getString("address").equals("Fake st"));
			assertTrue(resultSet.getString("accno").equals("5695012"));
			assertTrue(resultSet.getString("bankname").equals("Chase"));
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Test Case ID: Subsystem_Employer_019
	 * Purpose: Test if Employerauthenticate correctly authenticates employer's username and password
	 * Precondition: Have dummy Database setup
	 * Input: "user1", "user1"
	 * Expected Output: "success"
	 */
	@Test
	public void employerAuthenticateTest() 
	{

		try 
		{
			Connection con = DBConnection.createConnection();	
			Statement statement = con.createStatement();	
			
			String result = ModelFacade.Employerauthenticate("user1","user1");
			
			assertEquals("success", result);			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Test Case ID: Subsystem_Employer_020
	 * Purpose: Test if Employerauthenticate correctly authenticates employer's username and password, this time failing
	 * Precondition: Have dummy Database setup
	 * Input: "user1", "user2"
	 * Expected Output: "fail"
	 */
	@Test
	public void employerAuthenticateFailTest() 
	{

		try 
		{
			Connection con = DBConnection.createConnection();	
			Statement statement = con.createStatement();	
			
			String result = ModelFacade.Employerauthenticate("user1","user2");
			
			assertEquals("fail", result);			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Test Case ID: Subsystem_User_021
	 * Purpose: Test if Userauthenticate correctly authenticates user's username and password
	 * Precondition: Have dummy Database setup
	 * Input: "1", "adam", "adam"
	 * Expected Output: "success"
	 */
	@Test
	public void userAuthenticateTest() 
	{

		try 
		{
			Connection con = DBConnection.createConnection();	
			Statement statement = con.createStatement();	
			
			String result = ModelFacade.Userauthenticate("1","adam","adam");
			
			assertEquals("success", result);			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Test Case ID: Subsystem_User_022
	 * Purpose: Test if Userauthenticate correctly authenticates user's username and password, this time failing
	 * Precondition: Have dummy Database setup
	 * Input: "1", "adam", "adm"
	 * Expected Output: "fail"
	 */
	@Test
	public void userAuthenticateFailTest() 
	{

		try 
		{
			Connection con = DBConnection.createConnection();	
			Statement statement = con.createStatement();	
			
			String result = ModelFacade.Userauthenticate("1","adam","adm");
			
			assertEquals("fail", result);			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Test Case ID: Subsystem_User_023
	 * Purpose: Test if Security_Questionregisteremployee correctly adds data to DB
	 * Precondition: Have dummy Database setup
	 * Input: "3","adam","adam","Favorite Color?","red","First Pet Name?","adam","Favorite movie?","adam"
	 * Expected Output: "3","adam","adam","Favorite Color?","red","First Pet Name?","adam","Favorite movie?","adam"
	 * is inputted in the "user" table
	 */
	@Test
	public void securityQuestionRegisterTest() 
	{

		try 
		{
			Connection con = DBConnection.createConnection();	
			Statement statement = con.createStatement();	
			
			String test = ModelFacade.Security_Questionregisteremployee("3","adam","adam","Favorite Color?","red","First Pet Name?","adam","Favorite movie?","adam");
			resultSet = statement.executeQuery("select * from users where emp_id= '3'");
			resultSet.next(); 
			
			assertTrue(resultSet.getString("emp_id").equals("3"));
			assertTrue(resultSet.getString("user_id").equals("adam"));
			assertTrue(resultSet.getString("password").equals("adam"));
			assertTrue(resultSet.getString("sec_que1").equals("Favorite Color?"));
			assertTrue(resultSet.getString("ans1").equals("red"));
			assertTrue(resultSet.getString("sec_que2").equals("First Pet Name?"));
			assertTrue(resultSet.getString("ans2").equals("adam"));
			assertTrue(resultSet.getString("sec_que3").equals("Favorite movie?"));
			assertTrue(resultSet.getString("ans3").equals("adam"));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}

	/*
	 * Test Case ID: SUBSYSTEM_SALARY_024
	 * Purpose: Testing method to see if works as intended.
	 * Preconditions: Database set up.
	 * Input: None
	 * Expected Output:
	 * 	emp_id = 1
	 * 	total_hours = 55
	 * 	tax = 165
	 *  gross_sal = 550
	 *  net_sal = 385
	 */
	@Test
	public void testCalculateSalary() 
	{
		try 
		{
			Connection con = DBConnection.createConnection();
			Statement statement = con.createStatement();

			String test = ModelFacade.SalarycalculateSalary();
			resultSet = statement.executeQuery("select * from salaries where emp_id = '1'");
			resultSet.next();

			assertTrue(resultSet.getString("emp_id").equals("1"));
			assertTrue(resultSet.getString("total_hours").equals("55"));
			assertTrue(resultSet.getString("tax").equals("165"));
			assertTrue(resultSet.getString("gross_sal").equals("550"));
			assertTrue(resultSet.getString("net_sal").equals("385"));
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Test Case ID: SUBSYSTEM_SALARY_025
	 * Purpose: Testing method to see if works as intended.
	 * Preconditions: Database set up.
	 * Input: None
	 * Expected Output: 
	 *  emp_id = 1
	 * 	total_hours = 55
	 * 	tax = 165
	 *  gross_sal = 550
	 *  net_sal = 385
	 *  date1 = 2020-01-14
	 */
	@Test
	public void testGetEmpPays() {
		try {
			Connection con = DBConnection.createConnection();
			Statement statement = con.createStatement();
			resultSet = ModelFacade.SalarygetEmpPays();
			
			resultSet.next();
			
			assertTrue(resultSet.getString("emp_id").equals("1"));
			assertTrue(resultSet.getString("total_hours").equals("55"));
			assertTrue(resultSet.getString("tax").equals("165"));
			assertTrue(resultSet.getString("gross_sal").equals("550"));
			assertTrue(resultSet.getString("net_sal").equals("385"));
			assertTrue(resultSet.getString("date1").equals("2020-01-14"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Test Case ID: SUBSYSTEM_SALARY_026
	 * Purpose: Testing method to see if works as intended.
	 * Preconditions: Database set up.
	 * Input: empid = 1
	 * Expected Output: 
	 *  emp_id = 1
	 * 	total_hours = 55
	 * 	tax = 165
	 *  gross_sal = 550
	 *  net_sal = 385
	 *  date1 = 2020-01-14
	 */
	@Test
	public void testGetEmpPay() {
		try {
			Connection con = DBConnection.createConnection();
			Statement statement = con.createStatement();
			String empid = "1";
			resultSet = ModelFacade.SalarygetEmpPay(empid);
			
			resultSet.next();
			
			assertTrue(resultSet.getString("emp_id").equals("1"));
			assertTrue(resultSet.getString("total_hours").equals("55"));
			assertTrue(resultSet.getString("tax").equals("165"));
			assertTrue(resultSet.getString("gross_sal").equals("550"));
			assertTrue(resultSet.getString("net_sal").equals("385"));
			assertTrue(resultSet.getString("date1").equals("2020-01-14"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Test Case ID: SUBSYSTEM_SALARY_027
	 * Purpose: Testing method to see if works as intended.
	 * Preconditions: Database set up.
	 * Input: None
	 * Expected Output: 
	 *  normal_pay = 6
	 *  extra_pay = 20
	 */
	@Test
	public void testAddPayMode() 
	{
		try {
			Connection con = DBConnection.createConnection();
			Statement statement = con.createStatement();
			double normal = 6;
			double extra = 20;

			String test = ModelFacade.SalaryaddPayMode(normal,extra);
			resultSet = statement.executeQuery("select * from paymode where normal_pay = '6'");
			resultSet.next();

			assertTrue(resultSet.getString("normal_pay").equals("6"));
			assertTrue(resultSet.getString("extra_pay").equals("20"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Test Case ID: Subsystem_Employee_028
	 * Purpose: Test if EmployeeupdateEmployee correctly updates employee's data upon recieving invalid data
	 * Precondition: Have dummy Database setup
	 * Input: "1","Kristian","Perez","male","05/11/1998","CEO", "0","kpere192@fiu.edu", "Fake st", "5695012", "Chase"
	 * Expected Output: fail
	 */
	@Test
	public void updateEmployeeFailTest() 
	{
		try 
		{
			Connection con = DBConnection.createConnection();	
			Statement statement = con.createStatement();		
			String test = ModelFacade.EmployeeupdateEmployee("1","Kristian","Perez","male","05/11/1998","CEO", "0","kpere192@fiu.edu", "Fake st", "5695012", "Chase");
			
						
			assertFalse(test.equals("success"));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Test Case ID: Subsystem_Employee_029
	 * Purpose: Test if EmployeedeleteEmp fails when attempting to delete an employee that does not exist
	 * Precondition: Have dummy Database setup
	 * Input: "20"
	 * Expected Output: fail
	 */
	@Test
	public void delEmployeeFailTest() 
	{

		try 
		{
			Connection con = DBConnection.createConnection();	
			Statement statement = con.createStatement();		

			
			String test = ModelFacade.EmployeedeleteEmp("20");
			
			assertEquals("fail", test);	
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}

	/*
	 * Test Case ID: Subsystem_Employee_030
	 * Purpose: Test if EmployeechangePassword fails when invalid credentials are entered
	 * Precondition: Have dummy Database setup
	 * Input: "1","adam","Favorite Color?","red","First PEt Name?","adam","Favorite movie?","adam","adam","newPass"
	 * Expected Output: Passowrd unchanged due to invalid credentials.
	 */
	@Test
	public void changePasswordFailTest() 
	{

		try 
		{
			Connection con = DBConnection.createConnection();	
			Statement statement = con.createStatement();		
			String test = ModelFacade.EmployeechangePassword("1","adam","Favorite Color?","red","First PEt Name?","adam","Favorite movie?","adam","adam","newPass");
			
			assertEquals("fail : Account detials incorrect", test);
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}

}
