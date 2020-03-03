package unitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.ModelFacade;

public class ModelFacadeTest {

	@Before
	public void setUp() throws Exception {
		// For setup, we add rows to tables to use to test methods that involve updating, deleting,
		// or requesting data from those tables. We do this directly through DB.connection to avoid using
		// the same code that we're testing
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTimeSheetaddTimeSheet() {
		// Valid data, expect success
	    String result = ModelFacade.TimeSheetaddTimeSheet("1", "1", "2020-03-02", 
	    		"2020-03-02", "09:00:00", "12:00:00", "13:00:00", "17:00:00");
	    
		assertEquals("success", result);
		
		// Invalid data, expect failure
	    result = ModelFacade.TimeSheetaddTimeSheet("1", "1", "2020/22-aa", 
	    		"2020-03-02", "09:00:00", "12 noon", "13:00:00", "5 pm");
	    
		assertFalse(result.equals("success"));
	}

	@Test
	public void testTimeSheetupdateTimeSheet() {
		fail("Not yet implemented");
	}

	@Test
	public void testTimeSheetsubmitTimeSheet() {
		fail("Not yet implemented");
	}

	@Test
	public void testTimeSheetgetEmpTimeSheetNotApproved() {
		fail("Not yet implemented");
	}

	@Test
	public void testTimeSheetgetTimeSheetApproved() {
		fail("Not yet implemented");
	}

	@Test
	public void testTimeSheetgetTimeSheetNotApproved() {
		fail("Not yet implemented");
	}

	@Test
	public void testTimeSheetgetTimeSheetApprovedEmpIds() {
		fail("Not yet implemented");
	}

	@Test
	public void testSalarycalculateSalary() {
		fail("Not yet implemented");
	}

	@Test
	public void testSalarygetEmpPays() {
		fail("Not yet implemented");
	}

	@Test
	public void testSalarygetEmpPay() {
		fail("Not yet implemented");
	}

	@Test
	public void testSalaryaddPayMode() {
		fail("Not yet implemented");
	}

	@Test
	public void testEmployeeaddEmployee() {
		// Valid employee data
		String result = ModelFacade.EmployeeaddEmployee("1", "Hunter", "Biden", "M", "1986-04-21", 
				"Mailman", "3059032234", "test@email.com", "900 Walker Street", 
				"1234567890", "Bank of America");
		assertEquals("success", result);
		// Invalid employee DoB, should fail
		result = ModelFacade.EmployeeaddEmployee("1", "Hunter", "Biden", "M", "aaaaaaaaaaaaa", // invalid date,  
				"Mailman", "3059032234", "test@email.com", "900 Walker Street", 
				"1234567890", "Bank of America");
		assertEquals("fail", result);
	}

	@Test
	public void testEmployeechangePassword() {
		fail("Not yet implemented");
	}

	@Test
	public void testEmployeedeleteEmp() {
		fail("Not yet implemented");
	}

	@Test
	public void testEmployeegetPassword() {
		fail("Not yet implemented");
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
