package controller;

import java.io.IOException;
import model.ModelFacade;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class changepasswordcontroller
 */
@WebServlet("/changepasswordcontroller")
public class Authentication extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
PrintWriter pw=response.getWriter();
		
		
		String eid = request.getParameter("eid");
		String uid = request.getParameter("uid");
		String s1 = request.getParameter("s1");
		String a1 = request.getParameter("a1");
		String s2 = request.getParameter("s2");
		String a2 = request.getParameter("a2");
		String s3 = request.getParameter("s3");
		String a3 = request.getParameter("a3");
		String oldpass = request.getParameter("oldpass");
		String newpass = request.getParameter("newpass");
		String cpass = request.getParameter("cpass");
		
		if(cpass.equals(newpass))
		{
			
		
		
		//pw.print("ok");
		ModelFacade obj=new ModelFacade();
		
		
		String result=obj.EmployeechangePassword(eid, uid, s1,a1,s2,a2,s3,a3, oldpass, newpass);		
		if(result.equals("success"))
		{
			pw.print("<script language='javascript'>window.alert('Password changed');window.location.replace('employeehome.jsp');</script>");

		}
		else
		{
			response.sendRedirect("error.jsp?msg=Error :"+result);
		//	pw.print("<script language='javascript'>window.alert('Password not changed');window.location='.jsp';</script>");
		}
		}
		else
			pw.print("<script language='javascript'>window.alert('Passwords not matched');window.location.replace('changepassword.jsp');</script>");
		
	
	}
}
