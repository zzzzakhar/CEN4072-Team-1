package subSystemTests;

import java.sql.*;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.ModelFacade;
import utilities.DBConnection;

public class SalaryTest {
	
	private ResultSet resultSet = null;
	
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

	/*
	 * Test Case ID: SUBSYSTEM_SALARY_01
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
	public void testCalculateSalary() {
		try {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Test Case ID: SUBSYSTEM_SALARY_02
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
	 * Test Case ID: SUBSYSTEM_SALARY_03
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
	 * Test Case ID: SUBSYSTEM_SALARY_04
	 * Purpose: Testing method to see if works as intended.
	 * Preconditions: Database set up.
	 * Input: None
	 * Expected Output: 
	 *  normal_pay = 6
	 *  extra_pay = 20
	 */
	@Test
	public void testAddPayMode() {
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

}
