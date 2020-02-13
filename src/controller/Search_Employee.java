package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ModelFacade;

/**
 * Servlet implementation class submittimesheetcontroller
 */
@WebServlet("/submittimesheetcontroller")
public class Search_Employee extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		String empid=request.getParameter("empid");
		
		
		ModelFacade.TimeSheetsubmitTimeSheet(empid);
		
		pw.print("<script language='javascript'>window.alert('time sheet submitted');history.back();</script>");
	}

	
}
