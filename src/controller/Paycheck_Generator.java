package controller;

import java.io.IOException;
import java.io.PrintWriter;
import model.ModelFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class paymodecontroller
 */
@WebServlet("/paymodecontroller")
public class Paycheck_Generator extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
PrintWriter pw=response.getWriter();
		
		double  normal =Double.parseDouble(request.getParameter("normal"));
		
		double extra =Double.parseDouble(request.getParameter("extra"));
		
		ModelFacade obj=new ModelFacade();
		
		
	String result=obj.SalaryaddPayMode(normal, extra);
		
		if(result.equals("success"))
		{
			pw.print("<script language='javascript'>window.alert('Pay mode added');window.location.replace('paymode.jsp');</script>");
		}
		else
		{
			response.sendRedirect("error.jsp?msg="+result);
		}
		
	}

	

}
