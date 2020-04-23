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
public class SalaryTest {
	
	// Mock database connections
	@Mock
	Statement st;
	@Mock
	ResultSet rs;
	@Mock
	Connection con;

	// Inject mocks into mock objects for testing
	@InjectMocks
	Salary salary;
	
	@Before
	public void setUp() throws Exception {
		PowerMockito.mockStatic(DBConnection.class);
		
		when(DBConnection.createConnection()).thenReturn(con);
		when(con.createStatement()).thenReturn(st);
		when(st.executeQuery(anyString())).thenReturn(rs);
		when(rs.next()).thenReturn(true).thenReturn(false);
	}

	/* Test Case ID: testSalarycalculateSalary_sunny
	 * 
	 * Purpose: To test if the Salary.calculateSalary() method
	 * 			is making the proper database calls and returning the
	 * 			correct success value
	 * 
	 * Test Setup: None
	 * 
	 * Input: Salary.calculateSalary();
	 * 
	 * Expected Output: Method should return "success";
	 * 					it should also make proper executeQuery calls to DB
	 */
	@Test
	public void testSalarycalculateSalary_sunny() {
		try {
			when(st.executeUpdate(anyString())).thenReturn(1);
 			when(rs.getDouble("normal_pay")).thenReturn(4.0);
 			when(rs.getDouble("double_pay")).thenReturn(8.0);
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
		
		// Valid data, expect success
		// This should populate the salaries table with employee salaries
		String result = salary.calculateSalary();
		
		assertEquals("success", result);
		
		 // Verify executeQuery call is being made
		try {
			verify(st, times(2)).executeQuery(anyString());
		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
	}
	
	/* Test Case ID: testSalarycalculateSalary_rainy
	 * 
	 * Purpose: To test if the Salary.calculateSalary() method
	 * 			is properly handling error conditions and exceptions
	 * 
	 * Test Setup: Set mocks to throw SQLExceptions on executeUpdate and executeQuery
	 * 
	 * Input: Salary.calculateSalary();
	 * 
	 * Expected Output: Method should return a failure string and not throw
	 * 					an unhandled exception
	 */
	@Test
	public void testSalarycalculateSalary_rainy() {
		try {
			when(st.executeUpdate(anyString())).thenThrow(new SQLException());
			when(st.executeQuery(anyString())).thenThrow(new SQLException());
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
		
		String result = salary.calculateSalary();
		
		assertTrue(result.startsWith("fail"));
	}

	/* Test Case ID: testSalarygetEmpPays_sunny
	 * 
	 * Purpose: To test if the Salary.getEmpPays() method
	 * 			is making the proper database calls and returning the
	 * 			correct result set
	 * 
	 * Test Setup: None
	 * 
	 * Input: Salary.getEmpPays()
	 * 
	 * Expected Output: Method should return "success";
	 * 					it should also make proper executeQuery calls to DB
	 */
	@Test
	public void testSalarygetEmpPays_sunny() {
		try {
			when(st.executeUpdate(anyString())).thenReturn(1);
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
		
		ResultSet temp = salary.getEmpPays();
		
		assertNotNull(temp);
		
		 // Verify executeQuery call is being made
		try {
			verify(st).executeQuery(anyString());
		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
	}

	/* Test Case ID: testSalarygetEmpPays_rainy
	 * 
	 * Purpose: To test if the Salary.getEmpPays() method
	 * 			is properly handling error conditions and exceptions
	 * 
	 * Test Setup: Set mocks to throw SQLExceptions on executeUpdate and executeQuery
	 * 
	 * Input: Salary.getEmpPays();
	 * 
	 * Expected Output: Method should return gracefully and not throw an unhandled exception
	 */
	@Test
	public void testSalarygetEmpPays_rainy() {
		try {
			when(st.executeUpdate(anyString())).thenThrow(new SQLException());
			when(st.executeQuery(anyString())).thenThrow(new SQLException());
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
		
		// Ignore return value; checking if exception is thrown
		salary.getEmpPays();
	}
	
	/* Test Case ID: testSalarygetEmpPay_sunny
	 * 
	 * Purpose: To test if the Salary.getEmpPay() method
	 * 			is making the proper database calls and returning the
	 * 			correct result set
	 * 
	 * Test Setup: None
	 * 
	 * Input: Salary.getEmpPay("2")
	 * 
	 * Expected Output: Method should return "success";
	 * 					it should also make proper executeQuery calls to DB
	 */
	@Test
	public void testSalarygetEmpPay_sunny() {
		try {
			when(st.executeUpdate(anyString())).thenReturn(1);
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
		
		ResultSet temp = salary.getEmpPay("2");
		
		assertNotNull(temp);
		
		 // Verify executeQuery call is being made
		try {
			verify(st).executeQuery(anyString());
		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
	}

	/* Test Case ID: testSalarygetEmpPay_rainy
	 * 
	 * Purpose: To test if the Salary.getEmpPay() method
	 * 			is properly handling error conditions and exceptions
	 * 
	 * Test Setup: Set mocks to throw SQLExceptions on executeUpdate and executeQuery
	 * 
	 * Input: Salary.getEmpPay("aaaaaaaaaaaaaaaa");
	 * 
	 * Expected Output: Method should return gracefully and not throw an unhandled exception
	 */
	@Test
	public void testSalarygetEmpPay_rainy() {
		try {
			when(st.executeUpdate(anyString())).thenThrow(new SQLException());
			when(st.executeQuery(anyString())).thenThrow(new SQLException());
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
		
		// Ignore return value; checking if exception is thrown
		salary.getEmpPay("aaaaaaaaaaaaaaaa");
	}

	/* Test Case ID: testSalaryaddPayMode_sunny
	 * 
	 * Purpose: To test if the Salary.addPayMode() method
	 * 			is making the proper database calls and returning the
	 * 			correct success value
	 * 
	 * Test Setup: None
	 * 
	 * Input: Salary.addPayMode(20, 40);
	 * 
	 * Expected Output: Method should return "success";
	 * 					it should also make proper executeQuery calls to DB
	 */
	@Test
	public void testSalaryaddPayMode_sunny() {
		try {
			when(st.executeUpdate(anyString())).thenReturn(1);
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
		
		// Valid data, expect success
		// This should populate the salaries table with employee salaries
		String result = salary.addPayMode(20, 40);
		
		assertEquals("success", result);
		
		 // Verify executeQuery call is being made
		try {
			verify(st).executeUpdate(anyString());
		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
	}
	
	/* Test Case ID: testSalaryaddPayMode_rainy
	 * 
	 * Purpose: To test if the Salary.addPayMode() method
	 * 			is properly handling error conditions and exceptions
	 * 
	 * Test Setup: Set mocks to throw SQLExceptions on executeUpdate and executeQuery
	 * 
	 * Input: Salary.addPayMode(-3, -10);
	 * 
	 * Expected Output: Method should return a failure string and not throw
	 * 					an unhandled exception
	 */
	@Test
	public void testSalaryaddPayMode_rainy() {
		try {
			when(st.executeUpdate(anyString())).thenThrow(new SQLException());
			when(st.executeQuery(anyString())).thenThrow(new SQLException());
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
		
		String result = salary.addPayMode(-3, -10);
		
		assertTrue(result.startsWith("fail"));
	}
}