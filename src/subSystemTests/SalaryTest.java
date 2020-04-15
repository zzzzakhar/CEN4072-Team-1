package subSystemTests;

import java.sql.*;

import static org.junit.Assert.*;

import org.junit.Test;

import model.ModelFacade;
import utilities.DBConnection;

public class SalaryTest {
	
	private ResultSet resultSet = null;

	@Test
	public void testCalculateSalary() {
		Connection con = DBConnection.createConnection();
		try {
			Statement statement = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String test = ModelFacade.SalarycalculateSalary();
	}
	
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
	
	@Test
	public void testAddPayMode() {
		Connection con = DBConnection.createConnection();
		try {
			Statement statement = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double normal = 0;
		double extra = 0;
		String test = ModelFacade.SalaryaddPayMode(normal,extra);
	}

}
