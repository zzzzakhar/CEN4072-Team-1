package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class updateempcontroller
 */
@WebServlet("/Get_Profile")
public class Get_Profile extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter pw=response.getWriter();
		
		String eid = request.getParameter("eid");
		
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String gen = request.getParameter("gen");
		String dob = request.getParameter("dob");
		
		String job = request.getParameter("job");
		String contact = request.getParameter("contact");
		
		String email = request.getParameter("email");
		String addr = request.getParameter("addr");
		
		String accno = request.getParameter("accno");
		
		String bname = request.getParameter("bname");
		
		
		
		//pw.print("ok");
		model.Employee obj=new model.Employee();
		
		
	String result=obj.updateEmployee(eid, fname, lname, gen, dob, job, contact, email, addr, accno, bname);
		
		if(result.equals("success"))
		{
			pw.print("<script language='javascript'>window.alert('Employee Details Updated');window.location.replace('update_emp.jsp');</script>");
		}
		else
		{
			response.sendRedirect("error.jsp?msg="+result);
		}
		
	
	
	}

}
