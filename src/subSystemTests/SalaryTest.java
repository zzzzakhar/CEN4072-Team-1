package subSystemTests;

import java.sql.*;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Salary;

public class SalaryTest {

	@Test
	public void testCalculateSalary() {
		String test = new Salary().calculateSalary();
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetEmpPays() {
		ResultSet test = new Salary().getEmpPays();
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetEmpPay() {
		String empid = "";
		ResultSet test = new Salary().getEmpPay(empid);
		fail("Not yet implemented");
	}
	
	@Test
	public void testAddPayMode() {
		double normal = 0;
		double extra = 0;
		String test = new Salary().addPayMode(normal,extra);
		fail("Not yet implemented");
	}

}
