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
public class UserTest {
	
	// Mock database connections
	@Mock
	Statement st;
	@Mock
	ResultSet rs;
	@Mock
	Connection con;

	// Inject mocks into mock objects for testing
	@InjectMocks
	User user;
	
	@Before
	public void setUp() throws Exception {
		PowerMockito.mockStatic(DBConnection.class);
		
		when(DBConnection.createConnection()).thenReturn(con);
		when(con.createStatement()).thenReturn(st);
		when(st.executeQuery(anyString())).thenReturn(rs);
		when(rs.next()).thenReturn(true).thenReturn(false);
	}

	/* Test Case ID: testUserauthenticate_sunny
	 * 
	 * Purpose: To test if the User.authenticate() method
	 * 			is making the proper database calls and returning the
	 * 			correct success value
	 * 
	 * Test Setup: None
	 * 
	 * Input: User.authenticate("1", "adam", "adam");
	 * 
	 * Expected Output: Method should return "success";
	 * 					it should also make proper executeQuery calls to DB
	 */
	@Test
	public void testUserauthenticate_sunny() {
		try {
			when(st.executeUpdate(anyString())).thenReturn(1);
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
		
		// Valid data, expect success
		String result = user.authenticate("1", "adam", "adam");
		assertTrue(result.startsWith("success"));
		
		 // Verify executeQuery call is being made
		try {
			verify(st).executeQuery(anyString());
		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
	}
	
	/* Test Case ID: testUserauthenticate_rainy
	 * 
	 * Purpose: To test if the User.authenticate() method
	 * 			is properly handling error conditions and exceptions
	 * 
	 * Test Setup: Set mocks to throw SQLExceptions on executeUpdate and executeQuery
	 * 
	 * Input: User.authenticate("1", "adam", "swordfish");
	 * 
	 * Expected Output: Method should return a failure string and not throw
	 * 					an unhandled exception
	 */
	@Test
	public void testUserauthenticate_rainy() {
		try {
			when(st.executeUpdate(anyString())).thenThrow(new SQLException());
			when(st.executeQuery(anyString())).thenThrow(new SQLException());
 		} catch (SQLException e) {
			fail("SQLException encountered: " + e.toString());
		};
		
		String result = user.authenticate("1", "adam", "swordfish");
		
		assertTrue(result.startsWith("fail"));
	}
}