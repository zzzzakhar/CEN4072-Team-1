package controller;
import model.ModelFacade;
import java.io.IOException;
import java.io.PrintWriter;

import model.Employer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class emplrlogincontroller extends HttpServlet {



protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	PrintWriter pw=response.getWriter();
	//pw.print("ok");
	

	String username = request.getParameter("uname");
	String password = request.getParameter("pwd");
	
	ModelFacade obj=new ModelFacade();
	
String result=obj.Employerauthenticate(username, password);
	
	if(result.equals("success"))
	{
		response.sendRedirect("emplrhome.jsp");
	}
	else
	{
		response.sendRedirect("error.jsp?msg=Admin account not found");
	}
	



}

}