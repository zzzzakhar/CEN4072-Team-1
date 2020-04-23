package unitTests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import model.*;
import utilities.DBConnection;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DBConnection.class)
public class EmployeeTest {
	
	// Mock database connections
	@Mock
	Statement st;
	@Mock
	ResultSet rs;
	@Mock
	Connection con;

	// Inject mocks into mock objects for testing
	@InjectMocks
	Employee employee;
	
	@Before
	public void setUp() throws Exception {
		PowerMockito.mockStatic(DBConnection.class);
		
		when(DBConnection.createConnection()).thenReturn(con);
		when(con.createStatement()).thenReturn(st);
		when(st.executeQuery(anyString())).thenReturn(rs);
		when(rs.next()).thenReturn(true).thenReturn(false);
	}

	/* Test Case ID: testEmployeeaddEmployee_sunny
	 * 
	 * Purpose: To test if the Employee.addEmployee() method
	 * 			is making the proper database calls and returning the
	 * 			correct success value
	 * 
	 * Test Setup: None
	 * 
	 * Input: Employee.addEmployee("4", "Hunter", "Biden", "M", "1986-04-21", 
	 *			"Mailman", "3059032234", "test@email.com", "900 Walker Street", 
	 *			"1234567890", "Bank of America");
	 * 
	 * Expected Output: Method should return "success";
	 * 					it should also make proper executeQuery calls to DB
	 */
	@Test
	public void testEmployeeaddEmployee_sunny() {
		try {
			when(st.executeUpdate(anyString())).thenReturn(1);
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
		
		// Valid data, expect success
		// This should populate the salaries table with employee salaries
		String result = employee.addEmployee("4", "Hunter", "Biden", "M", "1986-04-21", 
				"Mailman", "3059032234", "test@email.com", "900 Walker Street", 
				"1234567890", "Bank of America");
		
		assertEquals("success", result);
		
		 // Verify executeQuery call is being made
		try {
			verify(st).executeUpdate(anyString());
		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
	}
	
	/* Test Case ID: testEmployeeaddEmployee_rainy
	 * 
	 * Purpose: To test if the Employee.addEmployee() method
	 * 			is properly handling error conditions and exceptions
	 * 
	 * Test Setup: Set mocks to throw SQLExceptions on executeUpdate and executeQuery
	 * 
	 * Input: Employee.addEmployee("5", "Hunter", "Biden", "M", "aaaaaaaaaaaaa", // invalid date,  
	 *			"Mailman", "3059032234", "test@email.com", "900 Walker Street", 
	 *			"1234567890", "Bank of America");
	 * 
	 * Expected Output: Method should return a failure string and not throw
	 * 					an unhandled exception
	 */
	@Test
	public void testEmployeeaddEmployee_rainy() {
		try {
			when(st.executeUpdate(anyString())).thenThrow(new SQLException());
			when(st.executeQuery(anyString())).thenThrow(new SQLException());
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
		
		String result = employee.addEmployee("5", "Hunter", "Biden", "M", "aaaaaaaaaaaaa", // invalid date,  
				"Mailman", "3059032234", "test@email.com", "900 Walker Street", 
				"1234567890", "Bank of America");
		
		assertTrue(result.startsWith("fail"));
	}
	
	/* Test Case ID: testEmployeechangePassword_sunny
	 * 
	 * Purpose: To test if the Employee.changePassword() method
	 * 			is making the proper database calls and returning the
	 * 			correct success value
	 * 
	 * Test Setup: None
	 * 
	 * Input: Employee.changePassword("1", "adam", "Favorite Color?", "pink", 
	 *	 		"First PEt Name?", "adam", "Favorite movie?" , "adam", "adam", "swordfish");
	 * 
	 * Expected Output: Method should return "success";
	 * 					it should also make proper executeQuery calls to DB
	 */
	@Test
	public void testEmployeechangePassword_sunny() {
		try {
			when(st.executeUpdate(anyString())).thenReturn(1);
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
		
		// Valid data, expect success
		// This should populate the salaries table with employee salaries
		String result = employee.changePassword("1", "adam", "Favorite Color?", "pink", 
				"First PEt Name?", "adam", "Favorite movie?" , "adam", "adam", "swordfish");
		
		assertEquals("success", result);
		
		 // Verify executeQuery call is being made
		try {
			verify(st).executeUpdate(anyString());
		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
	}
	
	/* Test Case ID: testEmployeechangePassword_rainy
	 * 
	 * Purpose: To test if the Employee.changePassword() method
	 * 			is properly handling error conditions and exceptions
	 * 
	 * Test Setup: Set mocks to throw SQLExceptions on executeUpdate and executeQuery
	 * 
	 * Input: Employee.changePassword("1", "adam", "Favorite Color?", "blue", 
	 *		 "First PEt Name?", "a", "Favorite movie?" , "a", "adam", "swordfish");
	 * 
	 * Expected Output: Method should return a failure string and not throw
	 * 					an unhandled exception
	 */
	@Test
	public void testEmployeechangePassword_rainy() {
		try {
			when(st.executeUpdate(anyString())).thenThrow(new SQLException());
			when(st.executeQuery(anyString())).thenThrow(new SQLException());
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
		
		String result = employee.changePassword("1", "adam", "Favorite Color?", "blue", 
				"First PEt Name?", "a", "Favorite movie?" , "a", "adam", "swordfish");
		
		assertTrue(result.startsWith("fail"));
	}

	/* Test Case ID: testEmployeedeleteEmp_sunny
	 * 
	 * Purpose: To test if the Employee.deleteEmp() method
	 * 			is making the proper database calls and returning the
	 * 			correct success value
	 * 
	 * Test Setup: None
	 * 
	 * Input: Employee.deleteEmp("2");
	 * 
	 * Expected Output: Method should return "success";
	 * 					it should also make proper executeQuery calls to DB
	 */
	@Test
	public void testEmployeedeleteEmp_sunny() {
		try {
			when(st.executeUpdate(anyString())).thenReturn(1);
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
		
		// Valid data, expect success
		// This should populate the salaries table with employee salaries
		String result = employee.deleteEmp("2");
		
		assertEquals("success", result);
		
		 // Verify executeQuery call is being made
		try {
			verify(st).executeUpdate(anyString());
		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
	}
	
	/* Test Case ID: testEmployeedeleteEmp_rainy
	 * 
	 * Purpose: To test if the Employee.deleteEmp() method
	 * 			is properly handling error conditions and exceptions
	 * 
	 * Test Setup: Set mocks to throw SQLExceptions on executeUpdate and executeQuery
	 * 
	 * Input: Employee.deleteEmp("42");
	 * 
	 * Expected Output: Method should return a failure string and not throw
	 * 					an unhandled exception
	 */
	@Test
	public void testEmployeedeleteEmp_rainy() {
		try {
			when(st.executeUpdate(anyString())).thenThrow(new SQLException());
			when(st.executeQuery(anyString())).thenThrow(new SQLException());
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
		
		String result = employee.deleteEmp("42");
		
		assertTrue(result.startsWith("fail"));
	}

	/* Test Case ID: testEmployeegetPassword_sunny
	 * 
	 * Purpose: To test if the Employee.getPassword() method
	 * 			is making the proper database calls and returning the
	 * 			correct success value
	 * 
	 * Test Setup: None
	 * 
	 * Input: Employee.getPassword("1", "adam", "Favorite Color?", "pink", 
	 *			"First PEt Name?", "adam", "Favorite movie?" , "adam");
	 * 
	 * Expected Output: Method should return "success";
	 * 					it should also make proper executeQuery calls to DB
	 */
	@Test
	public void testEmployeegetPassword_sunny() {
		try {
			when(st.executeUpdate(anyString())).thenReturn(1);
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
		
		// Valid data, expect success
		// This should populate the salaries table with employee salaries
		String result = employee.getPassword("1", "adam", "Favorite Color?", "pink", 
				"First PEt Name?", "adam", "Favorite movie?" , "adam");
		
		assertTrue(result.startsWith("success"));
		
		 // Verify executeQuery call is being made
		try {
			verify(st).executeQuery(anyString());
		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
	}
	
	/* Test Case ID: testEmployeegetPassword_rainy
	 * 
	 * Purpose: To test if the Employee.getPassword() method
	 * 			is properly handling error conditions and exceptions
	 * 
	 * Test Setup: Set mocks to throw SQLExceptions on executeUpdate and executeQuery
	 * 
	 * Input: Employee.getPassword("1", "adam", "Favorite Color?", "yellow", 
	 *			"First PEt Name?", "fido", "Favorite movie?" , "aaaa");
	 * 
	 * Expected Output: Method should return a failure string and not throw
	 * 					an unhandled exception
	 */
	@Test
	public void testEmployeegetPassword_rainy() {
		try {
			when(st.executeUpdate(anyString())).thenThrow(new SQLException());
			when(st.executeQuery(anyString())).thenThrow(new SQLException());
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
		
		String result = employee.getPassword("1", "adam", "Favorite Color?", "yellow", 
				"First PEt Name?", "fido", "Favorite movie?" , "aaaa");
		
		assertTrue(result.startsWith("Error"));
	}

	/* Test Case ID: testEmployeegetEmployee_sunny
	 * 
	 * Purpose: To test if the Employee.getEmployee() method
	 * 			is making the proper database calls and returning the
	 * 			correct result set
	 * 
	 * Test Setup: None
	 * 
	 * Input: Employee.getEmployee("2");
	 * 
	 * Expected Output: Method should return a result set;
	 * 					it should also make proper executeQuery calls to DB
	 */
	@Test
	public void testEmployeegetEmployee_sunny() {
		try {
			when(st.executeUpdate(anyString())).thenReturn(1);
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
		
		ResultSet temp = employee.getEmployee("2");
		
		assertNotNull(temp);
		
		 // Verify executeQuery call is being made
		try {
			verify(st).executeQuery(anyString());
		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
	}

	/* Test Case ID: testEmployeegetEmployee_rainy
	 * 
	 * Purpose: To test if the Employee.getEmployee() method
	 * 			is properly handling error conditions and exceptions
	 * 
	 * Test Setup: Set mocks to throw SQLExceptions on executeUpdate and executeQuery
	 * 
	 * Input: Employee.getEmployee("42");
	 * 
	 * Expected Output: Method should return gracefully and not throw an unhandled exception
	 */
	@Test
	public void testEmployeegetEmployee_rainy() {
		try {
			when(st.executeUpdate(anyString())).thenThrow(new SQLException());
			when(st.executeQuery(anyString())).thenThrow(new SQLException());
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
		
		// Ignore return value; checking if exception is thrown
		employee.getEmployee("42");
	}

	/* Test Case ID: testEmployeegetAllEmployees_sunny
	 * 
	 * Purpose: To test if the Employee.getAllEmployees() method
	 * 			is making the proper database calls and returning the
	 * 			correct result set
	 * 
	 * Test Setup: None
	 * 
	 * Input: Employee.getAllEmployees();
	 * 
	 * Expected Output: Method should return a result set;
	 * 					it should also make proper executeQuery calls to DB
	 */
	@Test
	public void testEmployeegetAllEmployees_sunny() {
		try {
			when(st.executeUpdate(anyString())).thenReturn(1);
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
		
		ResultSet temp = employee.getAllEmployees();
		
		assertNotNull(temp);
		
		 // Verify executeQuery call is being made
		try {
			verify(st).executeQuery(anyString());
		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
	}

	/* Test Case ID: testEmployeegetAllEmployees_rainy
	 * 
	 * Purpose: To test if the Employee.getAllEmployees() method
	 * 			is properly handling error conditions and exceptions
	 * 
	 * Test Setup: Set mocks to throw SQLExceptions on executeUpdate and executeQuery
	 * 
	 * Input: Employee.getAllEmployees();
	 * 
	 * Expected Output: Method should return gracefully and not throw an unhandled exception
	 */
	@Test
	public void testEmployeegetAllEmployees_rainy() {
		try {
			when(st.executeUpdate(anyString())).thenThrow(new SQLException());
			when(st.executeQuery(anyString())).thenThrow(new SQLException());
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
		
		// Ignore return value; checking if exception is thrown
		employee.getAllEmployees();
	}

	/* Test Case ID: testEmployeeupdateEmployee_sunny
	 * 
	 * Purpose: To test if the Employee.updateEmployee() method
	 * 			is making the proper database calls and returning the
	 * 			correct success value
	 * 
	 * Test Setup: None
	 * 
	 * Input: Employee.updateEmployee("2", "Hunter", "Biden", "M", "1986-04-21", 
	 *			"Mailman", "3059032234", "test@email.com", "900 Walker Street", 
	 *			"1234567890", "Bank of America");
	 * 
	 * Expected Output: Method should return "success";
	 * 					it should also make proper executeQuery calls to DB
	 */
	@Test
	public void testEmployeeupdateEmployee_sunny() {
		try {
			when(st.executeUpdate(anyString())).thenReturn(1);
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
		
		// Valid data, expect success
		String result = employee.updateEmployee("2", "Hunter", "Biden", "M", "1986-04-21", 
				"Mailman", "3059032234", "test@email.com", "900 Walker Street", 
				"1234567890", "Bank of America");
		
		assertTrue(result.startsWith("success"));
		
		 // Verify executeQuery call is being made
		try {
			verify(st).executeUpdate(anyString());
		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
	}
	
	/* Test Case ID: testEmployeeupdateEmployee_rainy
	 * 
	 * Purpose: To test if the Employee.getPassword() method
	 * 			is properly handling error conditions and exceptions
	 * 
	 * Test Setup: Set mocks to throw SQLExceptions on executeUpdate and executeQuery
	 * 
	 * Input: Employee.updateEmployee("42", "Hunter", "Biden", "M", "1986-04-21", 
	 *			"Mailman", "3059032234", "test@email.com", "900 Walker Street", 
	 *			"1234567890", "Bank of America");
	 * 
	 * Expected Output: Method should return a failure string and not throw
	 * 					an unhandled exception
	 */
	@Test
	public void testEmployeeupdateEmployee_rainy() {
		try {
			when(st.executeUpdate(anyString())).thenThrow(new SQLException());
			when(st.executeQuery(anyString())).thenThrow(new SQLException());
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
		
		String result = employee.updateEmployee("42", "Hunter", "Biden", "M", "1986-04-21", 
				"Mailman", "3059032234", "test@email.com", "900 Walker Street", 
				"1234567890", "Bank of America");
		
		assertTrue(result.startsWith("fail"));
	}
}