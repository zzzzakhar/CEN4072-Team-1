package subSystemTests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.ModelFacade;
import utilities.DBConnection;

public class SubSystemTests {
	// Defined here to avoid having to define them twice for setup and tear-down
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

	@Test
	public void testTimeSheet() 
	{
		String result = ModelFacade.TimeSheetaddTimeSheet("1", "1", "Monday", //Add a TS
	    		"2020-03-02", "09:00:00", "12:00:00", "13:00:00", "17:00:00");
	    assertEquals("success", result);	//Test added time sheet
	    
		result = ModelFacade.TimeSheetupdateTimeSheet("1", 
		    		"10:00:00","12:00:00","13:00:00","22:00:00", "11");		//Update the TS
	    assertEquals("success", result);	///Test update
	    ModelFacade.TimeSheetsubmitTimeSheet("1");		//Submit TS
		try 
		{
			ResultSet rs = ModelFacade.TimeSheetgetEmpTimeSheetNotApproved("1"); //Get the TS' for that emp (Not approved)
			
			int numRows = 0;
			while (rs.next()) 
			    {
			    	numRows++;
			    }
			
			assertEquals(3, numRows); //3 unapproved TS for EMP 1
			
			ModelFacade.TimeSheetaddTimeSheet("4", "4", "Monday", 				//Add a TS
			    		"2020-03-02", "09:00:00", "12:00:00", "13:00:00", "17:00:00");
			ModelFacade.TimeSheetaddTimeSheet("5", "5", "Monday", 				//Add a TS
			    		"2020-03-02", "09:00:00", "12:00:00", "13:00:00", "17:00:00");
			ModelFacade.TimeSheetsubmitTimeSheet("4"); 
			ModelFacade.TimeSheetsubmitTimeSheet("5"); 
			//Added new TS to increase size of Not Approved TS, making total of 5
			
			rs = ModelFacade.TimeSheetgetTimeSheetNotApproved();				//Get all unapproved TS
			rs.next();	
			numRows = 0;
			while (rs.next())
			{
				numRows++;
			}
			assertEquals(5, numRows);	

			rs = ModelFacade.TimeSheetgetTimeSheetApproved("1");				//Get the TS' for that emp (Approved)
			rs.next();
			numRows = 0;
			while (rs.next())
			{
				numRows++;
			}
			assertEquals(3, numRows);
			
			
			rs = ModelFacade.TimeSheetgetTimeSheetApprovedEmpIds();				//Get all EMPs with approved TS
			numRows = 0;
		    while (rs.next())
		    {
		    	numRows++;
		    }
			assertEquals(2, numRows);
		}
		catch (SQLException e) 
		{
			fail("SQLException encountered: " + e.toString());
		}
		
		

	}
	
	@Test
	public void testSalary()
	{
		ModelFacade.SalarycalculateSalary();
		
	    Connection con = DBConnection.createConnection();
	    
	    try 
	    {
	    	Statement st = con.createStatement();
		    ResultSet rs = st.executeQuery("select * from salaries");
		    
		    int numRows = 0;
		    while (rs.next()) 
		    {
		    	numRows++;
		    }

		    // Should be 4 rows in salaries, 2 of which were newly calculated
		    assertEquals(4, numRows);
		    
		    rs = ModelFacade.SalarygetEmpPays();
		    // Count the number of rows in the response
		    numRows = 0;
		    while (rs.next())
		    {
		    	numRows++;
		    }
		    
		    // Expecting 4 rows of salaries from all employees
			assertEquals(4, numRows);
			
		    rs = ModelFacade.SalarygetEmpPay("2");	    
		    // Count the number of rows in the response
		    numRows = 0;
		    while (rs.next())
		    {
		    	numRows++;
		    }
		    
		    // Expecting 2 rows of salaries from employee 2
			assertEquals(2, numRows);

		} 
	    
	    catch (SQLException e) 
	    {
			fail("SQLException encountered: " + e.toString());
		}
		
		String result = ModelFacade.SalaryaddPayMode(20, 40);
		assertEquals("success", result);	
	}
	
	@Test
	public void testEmployee()
	{
		String result = ModelFacade.EmployeeaddEmployee("4", "Hunter", "Biden", "M", "1986-04-21", //Add EMP "4"
				"Mailman", "3059032234", "test@email.com", "900 Walker Street", 
				"1234567890", "Bank of America");
		assertEquals("success", result);
		
		result = ModelFacade.EmployeechangePassword("1", "adam", "Favorite Color?", "pink", 	//Change pass to swordfish
				"First PEt Name?", "adam", "Favorite movie?" , "adam", "adam", "swordfish");
		assertEquals("success", result);
		
		result = ModelFacade.EmployeeupdateEmployee("2", "Hunter", "Biden", "M", "1986-04-21", 	//Add EMP "2"
				"Mailman", "3059032234", "test@email.com", "900 Walker Street", 
				"1234567890", "Bank of America");
		assertEquals("success", result);
		result = ModelFacade.EmployeedeleteEmp("2");											//Del EMP "2"
		assertEquals("success", result);
		result = ModelFacade.EmployeeupdateEmployee("2", "Hunter", "Biden", "M", "1986-04-21", 	//get EMP "2" (FAIL)
				"Mailman", "3059032234", "test@email.com", "900 Walker Street", 
				"1234567890", "Bank of America");
		assertEquals("fail", result);
		
		result = ModelFacade.EmployeegetPassword("1", "adam", "Favorite Color?", "pink", 		//Get "swordfish" pass
				"First PEt Name?", "adam", "Favorite movie?" , "adam");
		assertEquals("success,swordfish", result);
		
		try
		{
			ResultSet rs = ModelFacade.EmployeegetEmployee("4");							//Get EMP ID '1'
			int numRows = 0;
		    while (rs.next()) {
		    	numRows++;
		    }

		    // Should be 1 employee with the ID '1'
		    assertEquals(1, numRows);
		    
		    rs = ModelFacade.EmployeegetAllEmployees();										 // Should be 2 employees total
		    																				 // ID 1 and ID 4
		    numRows = 0;
		    while (rs.next()) 
		    {
		    	numRows++;
		    }

		   
		    assertEquals(2, numRows);
		}
		catch (SQLException e) 
	    {
			fail("SQLException encountered: " + e.toString());
		}
		
	}
	
	@Test
	public void testAuthenticate()
	{
			// Valid data, expect success
		    String result = ModelFacade.Employerauthenticate("user1", "user1");
		    
			assertEquals("success", result);

			// Valid data, expect success
		    result = ModelFacade.Userauthenticate("1", "adam", "adam");
		    
			assertEquals("success", result);
			// Valid employee data
			result = ModelFacade.Security_Questionregisteremployee("3", "joe", "scallop123", "Favorite Color?", "pink", 
					"First PEt Name?", "adam", "Favorite movie?" , "adam");
			assertEquals("success", result);
			
		    Connection con = DBConnection.createConnection();
		    
		    try {
		    	Statement st = con.createStatement();
			    ResultSet rs = st.executeQuery("select password from users where user_id = 'joe'");
			    rs.next(); // Move the cursor to the first row
			    
			    String pass = rs.getString(1);
			    // Password was set to scallop123, so this should be true
			    assertEquals(pass, "scallop123");
			} catch (SQLException e) {
				fail("SQLException encountered: " + e.toString());
			}
		

	}

}
