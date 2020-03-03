package unitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.ModelFacade;
import utilities.DBConnection;

import java.rmi.ConnectException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModelFacadeTest {

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
	public void testTimeSheetaddTimeSheet() {
		// Valid data, expect success
	    String result = ModelFacade.TimeSheetaddTimeSheet("1", "1", "Monday", 
	    		"2020-03-02", "09:00:00", "12:00:00", "13:00:00", "17:00:00");
	    
		assertEquals("success", result);
		
	    Connection con = DBConnection.createConnection();
	    
	    try {
	    	Statement st = con.createStatement();
		    ResultSet rs = st.executeQuery("select * from save_ts");
		    
		    int numRows = 0;
		    while (rs.next()) {
		    	numRows++;
		    }

		    // Should be 2 rows in save_ts, 1 of which was newly added
		    assertEquals(2, numRows);

		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		}
		
		// Invalid data, expect failure
	    result = ModelFacade.TimeSheetaddTimeSheet("1", "1", "2020/22-aa", 
	    		"2020-03-02", "09:00:00", "12 noon", "13:00:00", "5 pm");
	    
		assertFalse(result.equals("success"));
	}

	@Test
	public void testTimeSheetupdateTimeSheet() {
		// Valid data, expect success
	    String result = ModelFacade.TimeSheetupdateTimeSheet("1144", 
	    		"10:00:00","12:00:00","13:00:00","22:00:00", "11");
	    
		assertEquals("success", result);
		
		// Invalid data, expect failure
		result = ModelFacade.TimeSheetupdateTimeSheet("1144", 
	    		"10:00:00","aaaaaaa","6:00 pm","22:00:00", "yes");
	    
		assertFalse(result.equals("success"));
	}

	@Test
	public void testTimeSheetsubmitTimeSheet() {
		// Valid data, expect success
		// This should move the time sheet with ID 1126 from save_ts to emp_ts
	    ModelFacade.TimeSheetsubmitTimeSheet("1");
	    
	    Connection con = DBConnection.createConnection();
	    
	    try {
	    	Statement st = con.createStatement();
		    ResultSet rs = st.executeQuery("select * from save_ts where ets_id = '1126'");
		    // Equivalent to asserting that rs must be empty
			assertFalse(rs.next());
			
			rs=st.executeQuery("select * from emp_ts where ets_id = '1126'");
			assertTrue(rs.next());
		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		}
	}

	@Test
	public void testTimeSheetgetEmpTimeSheetNotApproved() {
		try {
			// Valid data, expect success
		    ResultSet rs = ModelFacade.TimeSheetgetEmpTimeSheetNotApproved("1");
		    
		    // Count the number of rows in the response
		    int numRows = 0;
		    while (rs.next())
		    	numRows++;
		    
		    // Expecting 2 rows of not-approved time sheets for employee 1
			assertEquals(2, numRows);
			
			// Invalid data, expect fail
		    rs = ModelFacade.TimeSheetgetEmpTimeSheetNotApproved("aaaaaaaaa");
		    
			assertFalse(rs.next());
		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		}
	}

	@Test
	public void testTimeSheetgetTimeSheetApproved() {
		try {
			// Valid data, expect success
		    ResultSet rs = ModelFacade.TimeSheetgetTimeSheetApproved("1");
		    
		    // Count the number of rows in the response
		    int numRows = 0;
		    while (rs.next())
		    	numRows++;
		    
		    // Expecting 3 rows of approved time sheets for employee 1
			assertEquals(3, numRows);
			
			// Invalid data, expect fail
		    rs = ModelFacade.TimeSheetgetEmpTimeSheetNotApproved("aaaaaaaaa");
		    
			assertFalse(rs.next());
		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		}
	}

	@Test
	public void testTimeSheetgetTimeSheetNotApproved() {
		try {
			// Valid data, expect success
		    ResultSet rs = ModelFacade.TimeSheetgetTimeSheetNotApproved();
		    
		    // Count the number of rows in the response
		    int numRows = 0;
		    while (rs.next())
		    	numRows++;
		    
		    // Expecting 3 rows of not-approved time sheets across all employees
			assertEquals(3, numRows);
		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		}
	}

	@Test
	public void testTimeSheetgetTimeSheetApprovedEmpIds() {
		try {
			// Valid data, expect success
		    ResultSet rs = ModelFacade.TimeSheetgetTimeSheetApprovedEmpIds();
		    
		    // Count the number of rows in the response
		    int numRows = 0;
		    while (rs.next())
		    	numRows++;
		    
		    // Expecting 2 distinct employees with approved time sheets
			assertEquals(2, numRows);
		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		}
	}

	@Test
	public void testSalarycalculateSalary() {
		// Valid data, expect success
		// This should populate the salaries table with employee salaries
		ModelFacade.SalarycalculateSalary();
		
	    Connection con = DBConnection.createConnection();
	    
	    try {
	    	Statement st = con.createStatement();
		    ResultSet rs = st.executeQuery("select * from salaries");
		    
		    int numRows = 0;
		    while (rs.next()) {
		    	numRows++;
		    }

		    // Should be 4 rows in salaries, 2 of which were newly calculated
		    assertEquals(4, numRows);

		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		}
	}

	@Test
	public void testSalarygetEmpPays() {
		try {
			// Valid data, expect success
		    ResultSet rs = ModelFacade.SalarygetEmpPays();
		    
		    // Count the number of rows in the response
		    int numRows = 0;
		    while (rs.next())
		    	numRows++;
		    
		    // Expecting 2 rows of salaries from all employees
			assertEquals(2, numRows);
		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		}
	}

	@Test
	public void testSalarygetEmpPay() {
		try {
			// Valid data, expect success
		    ResultSet rs = ModelFacade.SalarygetEmpPay("2");
		    
		    // Count the number of rows in the response
		    int numRows = 0;
		    while (rs.next())
		    	numRows++;
		    
		    // Expecting 1 rows of salaries from employee 2
			assertEquals(1, numRows);
		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		}
	}

	@Test
	public void testSalaryaddPayMode() {
		// Valid data, expect success
		ModelFacade.SalaryaddPayMode(20, 40);
		
	    Connection con = DBConnection.createConnection();
	    
	    try {
	    	Statement st = con.createStatement();
		    ResultSet rs = st.executeQuery("select * from paymode");
		    
		    int numRows = 0;
		    while (rs.next()) {
		    	numRows++;
		    }

		    // Should be 2 rows in paymode, 1 of which was newly added
		    assertEquals(2, numRows);

		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		}
	}

	@Test
	public void testEmployeeaddEmployee() {
		// Valid employee data
		String result = ModelFacade.EmployeeaddEmployee("4", "Hunter", "Biden", "M", "1986-04-21", 
				"Mailman", "3059032234", "test@email.com", "900 Walker Street", 
				"1234567890", "Bank of America");
		assertEquals("success", result);
		
	    Connection con = DBConnection.createConnection();
	    
	    try {
	    	Statement st = con.createStatement();
		    ResultSet rs = st.executeQuery("select * from employees");
		    
		    int numRows = 0;
		    while (rs.next()) {
		    	numRows++;
		    }

		    // Should be 3 rows in employees, 1 of which was newly added
		    assertEquals(3, numRows);

		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		}
		
		// Invalid employee DoB, should fail
		result = ModelFacade.EmployeeaddEmployee("5", "Hunter", "Biden", "M", "aaaaaaaaaaaaa", // invalid date,  
				"Mailman", "3059032234", "test@email.com", "900 Walker Street", 
				"1234567890", "Bank of America");
		assertEquals("fail", result);
	}

	@Test
	public void testEmployeechangePassword() {
		// Valid employee data
		String result = ModelFacade.EmployeechangePassword("1", "adam", "Favorite Color?", "pink", 
				"First PEt Name?", "adam", "Favorite movie?" , "adam", "adam", "swordfish");
		assertEquals("success", result);
		
	    Connection con = DBConnection.createConnection();
	    
	    try {
	    	Statement st = con.createStatement();
		    ResultSet rs = st.executeQuery("select password from users where user_id = 'adam'");
		    rs.next(); // Move the cursor to the first row
		    
		    String pass = rs.getString(1);
		    // Password was changed to swordfish, so this should be true
		    assertEquals(pass, "swordfish");
		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		}
		
		// Invalid employee DoB, should fail
	    result = ModelFacade.EmployeechangePassword("1", "adam", "Favorite Color?", "blue", 
				"First PEt Name?", "a", "Favorite movie?" , "a", "adam", "swordfish");
		assertFalse(result.equals("success"));
	}

	@Test
	public void testEmployeedeleteEmp() {
		// Valid employee data
		String result = ModelFacade.EmployeedeleteEmp("2");
		assertEquals("success", result);
		
	    Connection con = DBConnection.createConnection();
	    
	    try {
	    	Statement st = con.createStatement();
		    ResultSet rs = st.executeQuery("select * from employees");
		    
		    int numRows = 0;
		    while (rs.next()) {
		    	numRows++;
		    }

		    // Should be 1 row in employees, since we just deleted 1
		    assertEquals(1, numRows);

		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		}
		
		// Invalid employee ID, should fail
		result = ModelFacade.EmployeedeleteEmp("42");
		assertEquals("fail", result);
	}

	@Test
	public void testEmployeegetPassword() {
		// Valid employee data
		String result = ModelFacade.EmployeegetPassword("1", "adam", "Favorite Color?", "pink", 
				"First PEt Name?", "adam", "Favorite movie?" , "adam");
		assertTrue(result.contains("success"));
		
		// Invalid security question answers, should fail
		result = ModelFacade.EmployeegetPassword("1", "adam", "Favorite Color?", "yellow", 
				"First PEt Name?", "fido", "Favorite movie?" , "aaaa");
		assertFalse(result.contains("success"));
	}

	@Test
	public void testEmployeegetEmployee() {
		fail("Not yet implemented");
	}

	@Test
	public void testEmployeegetAllEmployees() {
		fail("Not yet implemented");
	}

	@Test
	public void testEmployeeupdateEmployee() {
		fail("Not yet implemented");
	}

	@Test
	public void testEmployerauthenticate() {
		fail("Not yet implemented");
	}

	@Test
	public void testUserauthenticate() {
		fail("Not yet implemented");
	}

	@Test
	public void testSecurity_Questionregisteremployee() {
		fail("Not yet implemented");
	}

}
