package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ModelFacade;

/**
 * Servlet implementation class addtimesheetcontroller
 */
@WebServlet("/addtimesheetcontroller")
public class Timesheet_Control extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter pw=response.getWriter();
		String result="success";
		HttpSession sn=request.getSession();
		String empid=(String)sn.getAttribute("empid");
		for(int i=1;i<=5;i++)
		{
		
		String day = request.getParameter("day"+i);
		String dt = request.getParameter("dt"+i);
		String intime = request.getParameter("in"+i);
		String lout = request.getParameter("lout"+i);
		String lin = request.getParameter("lin"+i);
		String cout = request.getParameter("cout"+i);	
		
		Random r=new Random();
		
		String tid=empid+""+r.nextInt(9)+r.nextInt(9)+r.nextInt(9);
		
		//pw.print("ok");
		ModelFacade obj=new ModelFacade();
		
		
		result=obj.TimeSheetaddTimeSheet(tid, empid, day, dt, intime, lout, lin, cout);
		if(!result.equals("success"))
		{
			response.sendRedirect("error.jsp?msg="+result);
		}
		}
		
		pw.print("<script language='javascript'>history.back();</script>");
		
	
	}

	

}
