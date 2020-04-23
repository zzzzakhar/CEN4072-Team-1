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
public class TimeSheetTest {
	
	// Mock database connections
	@Mock
	Statement st;
	@Mock
	ResultSet rs;
	@Mock
	Connection con;

	// Inject mocks into mock objects for testing
	@InjectMocks
	TimeSheet timesheet;
	
	@Before
	public void setUp() throws Exception {
		PowerMockito.mockStatic(DBConnection.class);
		
		when(DBConnection.createConnection()).thenReturn(con);
		when(con.createStatement()).thenReturn(st);
		when(st.executeQuery(anyString())).thenReturn(rs);
		when(rs.next()).thenReturn(true).thenReturn(false);
	}

	/* Test Case ID: testTimeSheetaddTimeSheet_sunny
	 * 
	 * Purpose: To test if the TimeSheet.addTimeSheet() method is
	 * 			properly making database calls to add new time sheets
	 * 			and checking response codes to verify addition of sheet
	 * 
	 * Test Setup: None
	 * 
	 * Input: TimeSheet.addTimeSheet("1", "1", "Monday", 
	 *  		"2020-03-02", "09:00:00", "12:00:00", "13:00:00", "17:00:00");
	 * 
	 * Expected Output: addTimeSheet() should return "success"; executeUpdate 
	 * 					and executeQuery database calls should have been performed
	 */
	@Test
	public void testTimeSheetaddTimeSheet_sunny() {
		try {
			when(rs.getString("tot")).thenReturn("22:00");
			when(st.executeUpdate(anyString())).thenReturn(1);
		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};

	    String result = timesheet.addTimeSheet("1", "1", "Monday", 
	    		"2020-03-02", "09:00:00", "12:00:00", "13:00:00", "17:00:00");
	    
	    assertEquals("success", result);
	    
	    // Verify database calls are being made
 		try {
 			verify(st).executeUpdate(anyString());
 			verify(st).executeQuery(anyString());
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
	}
	
	/* Test Case ID: testTimeSheetaddTimeSheet_rainy
	 * 
	 * Purpose: To test if the TimeSheet.addTimeSheet() method is
	 *			gracefully handling invalid input
	 * 
	 * Test Setup: None
	 * 
	 * Input: TimeSheet.addTimeSheet("1", "aaaaaa", "Monday", 
	    		"2020-03&02", "09:00:00", "", "13:00:00", "25:00:00");
	 * 
	 * Expected Output: addTimeSheet() should return either "fail" or an error message starting with "fail"
	 */
	@Test
	public void testTimeSheetaddTimeSheet_rainy() {
		try {
			when(st.executeUpdate(anyString())).thenReturn(1);
		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};

		// Invalid input, expect graceful fail
	    String result = timesheet.addTimeSheet("1", "aaaaaa", "Monday", 
	    		"2020-03&02", "09:00:00", "", "13:00:00", "25:00:00");
	    
	    assertTrue(result.startsWith("fail"));
	}

	/* Test Case ID: testTimeSheetupdateTimeSheet_sunny
	 * 
	 * Purpose: To test if the TimeSheet.updateTimeSheet() method is
	 * 			sending update queries to the database when called
	 * 
	 * Test Setup: None
	 * 
	 * Input: TimeSheet.updateTimeSheet("1144", 
	 *  		"10:00:00","12:00:00","13:00:00","22:00:00", "11");
	 * 
	 * Expected Output: updateTimeSheet() should return success;
	 * 					executeUpdate database call should have been performed
	 */
	@Test
	public void testTimeSheetupdateTimeSheet_sunny() {
		try {
			when(st.executeUpdate(anyString())).thenReturn(1);
		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};

	    String result = timesheet.updateTimeSheet("1144", 
	    		"10:00:00","12:00:00","13:00:00","22:00:00", "11");
	    
		assertEquals("success", result);
		
		// Verify executeUpdate calls are being made
		try {
			verify(st).executeUpdate(anyString());
		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
	}
	
	/* Test Case ID: testTimeSheetupdateTimeSheet_rainy
	 * 
	 * Purpose: To test if the TimeSheet.updateTimeSheet() method is
	 * 			gracefully handling invalid input
	 * 
	 * Test Setup: Set mocks to throw SQLExceptions on executeUpdate and executeQuery
	 * 
	 * Input: TimeSheet.updateTimeSheet("aaaa", 
	    		"10:00:00","12&00-00","13:00:00","22:00:00", "0");
	 * 
	 * Expected Output: updateTimeSheet() should return anything OTHER than "success"
	 */
	@Test
	public void testTimeSheetupdateTimeSheet_rainy() {
		try {
			// Mock behavior of invalid SQL request
			when(st.executeUpdate(anyString())).thenThrow(new SQLException());
		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};

		// Invalid input
	    String result = timesheet.updateTimeSheet("aaaa", 
	    		"10:00:00","12&00-00","13:00:00","22:00:00", "0");
	    
	    // Method should not return success
		assertFalse(result.contentEquals("success"));
	}

	/* Test Case ID: testTimeSheetsubmitTimeSheet_sunny
	 * 
	 * Purpose: To test if the TimeSheet.submitTimeSheet() method is
	 * 			making proper database calls to update sheet
	 * 
	 * Test Setup: None
	 * 
	 * Input: TimeSheet.submitTimeSheet("1");
	 * 
	 * Expected Output: executeUpdate and executeQuery database calls should be made
	 */
	@Test
	public void testTimeSheetsubmitTimeSheet_sunny() {
		try {
			when(st.executeUpdate(anyString())).thenReturn(1);
		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
		
		// Valid data, expect success
	    timesheet.submitTimeSheet("1");
	    
	    // Verify two executeUpdate calls are being made
 		try {
 			verify(st, times(2)).executeUpdate(anyString());
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
	}

	/* Test Case ID: testTimeSheetsubmitTimeSheet_rainy
	 * 
	 * Purpose: To test if the TimeSheet.submitTimeSheet() method properly
	 * 			handles invalid input and does not crash
	 * 
	 * Test Setup: Set mocks to throw SQLExceptions on executeUpdate and executeQuery
	 * 
	 * Input: TimeSheet.submitTimeSheet("aaaaaaaaaaaaaaaaaaaaaaa");
	 * 
	 * Expected Output: submitTimeSheet() should handle exception properly
	 */
	@Test
	public void testTimeSheetsubmitTimeSheet_rainy() {
		try {
			// Mock behavior of invalid SQL request
			when(st.executeUpdate(anyString())).thenThrow(new SQLException());
		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
		
	    timesheet.submitTimeSheet("aaaaaaaaaaaaaaaaaaaaaaa");
	}
	
	/* Test Case ID: testTimeSheetgetEmpTimeSheetNotApproved_sunny
	 * 
	 * Purpose: To test if the TimeSheet.getEmpTimeSheetNotApproved() method
	 * 			is making the proper database calls and returning properly
	 * 
	 * Test Setup: None
	 * 
	 * Input: TimeSheet.getEmpTimeSheetNotApproved("1");
	 * 
	 * Expected Output: Method should return the proper ResultSet;
	 * 					it should also make proper executeQuery calls to DB
	 */
	@Test
	public void testTimeSheetgetEmpTimeSheetNotApproved_sunny() {
		// Valid data, expect success
	    ResultSet temp = timesheet.getEmpTimeSheetNotApproved("1");
	    
	    assertNotNull(temp);
	
	    // Verify executeQuery call is being made
 		try {
 			verify(st).executeQuery(anyString());
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
	}

	/* Test Case ID: testTimeSheetgetEmpTimeSheetNotApproved_rainy
	 * 
	 * Purpose: To test if the TimeSheet.getEmpTimeSheetNotApproved() method
	 * 			is handling error conditions gracefully
	 * 
	 * Test Setup: Set mocks to throw SQLExceptions on executeUpdate and executeQuery
	 * 
	 * Input: TimeSheet.getEmpTimeSheetNotApproved("aaaaaaaaaaaaaaaaaa");
	 * 
	 * Expected Output: Method should handle exception and not crash
	 */
	@Test
	public void testTimeSheetgetEmpTimeSheetNotApproved_rainy() {
		try {
			// Simulate statement execution throwing an exception
			when(st.executeQuery(anyString())).thenThrow(new SQLException());
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
		
		// Ignore return value; checking if exception is thrown
	    timesheet.getEmpTimeSheetNotApproved("aaaaaaaaaaaaaaaaaa");
	}
	
	/* Test Case ID: testTimeSheetgetTimeSheetApproved_sunny
	 * 
	 * Purpose: To test if the TimeSheet.getTimeSheetApproved() method
	 * 			is making the proper database calls and returning properly
	 * 
	 * Test Setup: None
	 * 
	 * Input: TimeSheet.getTimeSheetApproved("1");
	 * 
	 * Expected Output: Method should return the proper ResultSet;
	 * 					it should also make proper executeQuery calls to DB
	 */
	@Test
	public void testTimeSheetgetTimeSheetApproved_sunny() {
		// Valid data, expect success
	    ResultSet temp = timesheet.getTimeSheetApproved("1");
	    
	    assertNotNull(temp);

		 // Verify executeQuery call is being made
 		try {
 			verify(st).executeQuery(anyString());
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
	}
	
	/* Test Case ID: testTimeSheetgetTimeSheetApproved_rainy
	 * 
	 * Purpose: To test if the TimeSheet.getTimeSheetApproved() method
	 * 			is properly handling error conditions
	 * 
	 * Test Setup: Set mocks to throw SQLExceptions on executeUpdate and executeQuery
	 * 
	 * Input: TimeSheet.getTimeSheetApproved("aaaaaaaaaaaaaaaaaa");
	 * 
	 * Expected Output: Method should return a null ResultSet and not throw
	 * 					an unhandled exception
	 */
	@Test
	public void testTimeSheetgetTimeSheetApproved_rainy() {
		try {
			// Simulate statement execution throwing an exception
			when(st.executeQuery(anyString())).thenThrow(new SQLException());
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
		
		// Ignore return value; checking if exception is thrown
	    timesheet.getTimeSheetApproved("aaaaaaaaaaaaaaaaaa");
	}

	/* Test Case ID: testTimeSheetgetTimeSheetNotApproved_sunny
	 * 
	 * Purpose: To test if the TimeSheet.getTimeSheetNotApproved() method
	 * 			is making the proper database calls and returning properly
	 * 
	 * Test Setup: None
	 * 
	 * Input: TimeSheet.getTimeSheetNotApproved();
	 * 
	 * Expected Output: Method should return the proper ResultSet;
	 * 					it should also make proper executeQuery calls to DB
	 */
	@Test
	public void testTimeSheetgetTimeSheetNotApproved_sunny() {
		// Valid data, expect success
	    ResultSet temp = timesheet.getTimeSheetNotApproved();
	    
	    assertNotNull(temp);

		 // Verify executeQuery call is being made
 		try {
 			verify(st).executeQuery(anyString());
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
	}
	
	/* Test Case ID: testTimeSheetgetTimeSheetNotApproved_rainy
	 * 
	 * Purpose: To test if the TimeSheet.getTimeSheetNotApproved() method
	 * 			is properly handling error conditions
	 * 
	 * Test Setup: Set mocks to throw SQLExceptions on executeUpdate and executeQuery
	 * 
	 * Input: TimeSheet.getTimeSheetNotApproved();
	 * 
	 * Expected Output: Method should return a null ResultSet and not throw
	 * 					an unhandled exception
	 */
	@Test
	public void testTimeSheetgetTimeSheetNotApproved_rainy() {
		try {
			// Simulate statement execution throwing an exception
			when(st.executeQuery(anyString())).thenThrow(new SQLException());
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
		
		// Ignore return value; checking if exception is thrown
	    timesheet.getTimeSheetNotApproved();
	}

	/* Test Case ID: testTimeSheetgetTimeSheetApprovedEmpIds_sunny
	 * 
	 * Purpose: To test if the TimeSheet.getTimeSheetApprovedEmpIds() method
	 * 			is making the proper database calls and returning properly
	 * 
	 * Test Setup: None
	 * 
	 * Input: TimeSheet.getTimeSheetApprovedEmpIds();
	 * 
	 * Expected Output: Method should return the proper ResultSet;
	 * 					it should also make proper executeQuery calls to DB
	 */
	@Test
	public void testTimeSheetgetTimeSheetApprovedEmpIds_sunny() {
		// Valid data, expect success
	    ResultSet temp = timesheet.getTimeSheetApprovedEmpIds();
	    
	    assertNotNull(temp);

		 // Verify executeQuery call is being made
 		try {
 			verify(st).executeQuery(anyString());
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
	}
	
	/* Test Case ID: testTimeSheetgetTimeSheetApprovedEmpIds_rainy
	 * 
	 * Purpose: To test if the TimeSheet.getTimeSheetApprovedEmpIds() method
	 * 			is properly handling error conditions
	 * 
	 * Test Setup: Set mocks to throw SQLExceptions on executeUpdate and executeQuery
	 * 
	 * Input: TimeSheet.getTimeSheetApprovedEmpIds();
	 * 
	 * Expected Output: Method should return a null ResultSet and not throw
	 * 					an unhandled exception
	 */
	@Test
	public void testTimeSheetgetTimeSheetApprovedEmpIds_rainy() {
		try {
			// Simulate statement execution throwing an exception
			when(st.executeQuery(anyString())).thenThrow(new SQLException());
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
		
		// Ignore return value; checking if exception is thrown
	    timesheet.getTimeSheetApprovedEmpIds();
	}
}
