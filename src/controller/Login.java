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
import javax.servlet.http.HttpSession;


public class Login extends HttpServlet {



protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	PrintWriter pw=response.getWriter();
	pw.print("ok");
	
	String eid = request.getParameter("eid");
	
	String username = request.getParameter("uname");
	String password = request.getParameter("pwd");
	//pw.print("ok");
	ModelFacade obj=new ModelFacade();
	
String result=obj.Userauthenticate(eid,username, password);
	
	if(result.equals("success"))
	{
		HttpSession sn=request.getSession();
		sn.setAttribute("empid",eid);
		sn.setAttribute("userid",username);
		response.sendRedirect("employeehome.jsp");
	}
	else
	{
		response.sendRedirect("error.jsp?msg="+result);
	}
	



}

}