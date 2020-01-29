package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class deleteempcontroller
 */
public class deleteempcontroller extends HttpServlet {
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			PrintWriter pw=response.getWriter();
			
			String eid = request.getParameter("eid");
			
			
			
			//pw.print("ok");
			model.Employee obj=new model.Employee();
			
			
		String result=obj.deleteEmp(eid);
			
			if(result.equals("success"))
			{
				pw.print("<script language='javascript'>window.alert('Employee Details Deleted');window.location.replace('emplrhome.jsp');</script>");
			}
			else
			{
				response.sendRedirect("error.jsp?msg="+result);
			}
			
	}

	

}
