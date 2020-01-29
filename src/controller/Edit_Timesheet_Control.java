package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class updatetimesheetcontroller
 */
@WebServlet("/updatetimesheetcontroller")
public class Edit_Timesheet_Control extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		String result="success";
		HttpSession sn=request.getSession();
		
		for(int i=1;i<=5;i++)
		{
			String tid=(String)request.getParameter("etsid"+i);
		
		String intime = request.getParameter("intime"+i);
		String lout = request.getParameter("lunchout"+i);
		String lin = request.getParameter("lunchin"+i);
		String cout = request.getParameter("outtime"+i);	
		String thours = request.getParameter("thours"+i);
		
		
		
		model.TimeSheet obj=new model.TimeSheet();
		
	result=obj.updateTimeSheet(tid, intime, lout, lin, cout,thours);
		if(!result.equals("success"))
			pw.print("<h1>Error :"+result); 
		
		   
	
	}
		pw.print("<script language='javascript'>window.alert('time sheet updated');history.back();</script>");

}
}
