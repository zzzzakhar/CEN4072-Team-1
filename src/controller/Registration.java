package controller;
import model.ModelFacade;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmpAdd
 */
@WebServlet("/EmpAdd")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		ModelFacade obj=new ModelFacade();
		
		
	String result=obj.EmployeeaddEmployee(eid, fname, lname, gen, dob, job, contact, email, addr, accno, bname);
		
		if(result.equals("success"))
		{
			//response.sendRedirect("emplogin.jsp");
			pw.print("<script language='javascript'>window.alert('Employee Detials Addded.');window.location='emplrhome.jsp';</script>");
		}
		else
		{
			response.sendRedirect("error.jsp?msg=Employee Registration Failed");
		}
		
	
	}

	
}
