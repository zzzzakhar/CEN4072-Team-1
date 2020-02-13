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
 * Servlet implementation class forgotpasswordcontroller
 */

public class forgotpasswordcontroller extends HttpServlet {
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			PrintWriter pw=response.getWriter();
			String eid = request.getParameter("eid");
			
			String userid = request.getParameter("uid");
			String s1 = request.getParameter("s1");
			String a1 = request.getParameter("a1");
			
			String s2 = request.getParameter("s2");
			String a2 = request.getParameter("a2");
			
			String s3 = request.getParameter("s3");
			String a3 = request.getParameter("a3");
			
			ModelFacade obj=new ModelFacade();
		String result=obj.EmployeegetPassword(eid, userid, s1, a1, s2, a2, s3, a3);
		if(result.contains("fail"))
			pw.print("<script language='javascript'>window.alert('user details not found');window.location.replace('emplogin.jsp');</script>");
		else if(result.contains("Error"))
		
			response.sendRedirect("error.jsp?msg="+result);
		else	
		{
			String s[]=result.split(",");
			response.sendRedirect("error.jsp?msg=Your password="+s[1]);
		}
		
			
	}

}
