package Listner;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/registration")
public class Registration extends HttpServlet{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    PrintWriter pw;
    ServletContext sc;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			sc=getServletContext();
			pw=response.getWriter();
			String fname=request.getParameter("firstname");
			String lname=request.getParameter("lastname");
			String dob =request.getParameter("dob");
			String gender = request.getParameter("gender");
			String licenceNo=request.getParameter("license");
			String mobileNo=request.getParameter("mobile");
			String email = request.getParameter("email");
			String pword1=request.getParameter("password");
			String pword=request.getParameter("confirmPassword");
			ServletContext sc=getServletContext();
			Connection con=(Connection)sc.getAttribute("oracle");
			PreparedStatement pstmt=con.prepareStatement("insert into parkingussers values(?,?,?,?,?,?,?,?)", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, fname);
			pstmt.setString(2, lname);
			pstmt.setString(3, dob);
			pstmt.setString(4, gender);
			pstmt.setString(5, licenceNo);
			pstmt.setString(6, mobileNo);
			pstmt.setString(7, email);
			pstmt.setString(8, pword);
			pstmt.execute();
			response.sendRedirect("account_created.html");
			}catch(SQLIntegrityConstraintViolationException ex){
				pw.println("<html><body ><h3 text=red><center>EMAID ID ALREADY  EXIST.USE ANOTHER EMAILID.</center></h3></body></html>");
				RequestDispatcher rd=sc.getRequestDispatcher("/register.html");
				rd.include(request, response);    				
			}catch(Exception e)
			{
			System.err.println(e);
			}
			}
	}


