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
 * Servlet implementation class empregistration
 */
@WebServlet("/empregistration")
public class empregistrationcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public empregistrationcontroller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		
		String eid = request.getParameter("eid");
		
		String userid = request.getParameter("uid");
		String password = request.getParameter("pwd");
		String s1 = request.getParameter("s1");
		String a1 = request.getParameter("a1");
		
		String s2 = request.getParameter("s2");
		String a2 = request.getParameter("a2");
		
		String s3 = request.getParameter("s3");
		String a3 = request.getParameter("a3");
		
		//pw.print("ok");
		ModelFacade obj=new ModelFacade();
		
		
	String result=obj.Security_Questionregisteremployee(eid, userid, password, s1, a1, s2, a2, s3, a3);
		
		if(result.equals("success"))
		{
			response.sendRedirect("emplogin.jsp");
		}
		else
		{
			response.sendRedirect("error.jsp?msg="+result);
		}
		
		
		
		
		
	}

}
