package Listner;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    PrintWriter out;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id=request.getParameter("username");
			String pass=request.getParameter("password");
			ServletContext sc=getServletContext();
			Connection con= (Connection) sc.getAttribute("oracle");
			PreparedStatement pstmt=con.prepareStatement("select * from parkingussers where email=? and pword=?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			ResultSet rs=pstmt.executeQuery();
			out = response.getWriter();
			if(rs.next()) {
				UserDetails ud=new UserDetails();
				ud.setFname(rs.getString(1));
				ud.setLname(rs.getString(2));
				ud.setDob(rs.getString(3));
				ud.setGender(rs.getString(4));
				ud.setLicence(rs.getString(5));
				ud.setMobile(rs.getString(6));
				ud.setEmail(rs.getString(7));
				ud.setPassword(rs.getString(8));
				sc.setAttribute("userdetails", ud);
				response.setContentType("text/html");
		        out.println("<!DOCTYPE html>");
		        out.println("<html>");
		        out.println("<head>");
		        out.println("<title>Customer Homepage</title>");
		        out.println("<style> div{margin:200px 500px;height:300px;width:500px; border-radius:10px;border:5px solid black;background-color:pink;text-align:center; }"
		        		+ "body{background-image: url(images/opa1.png);background-size:cover;background-repeat: no-repeat; }a{text-decoration:none;}a:hover{color:red;font-size:30px;}</style>");
		        out.println("</head>");
		        out.println("<body ><div>");
		        out.println("<h1>Welcome, " + ud.getFname()+" "+ ud.getLname()+ "!</h1>");
		        out.println("<p>Date Of Birth: " + ud.getDob() + "	&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp			Email:"+ud.getEmail()+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp			Mobile:"+ud.getMobile()+  "</p>");
		        out.println("<a href='park.html'>PARK-IN VEHICLE </a><br><bR>");
		        out.println("<a href='checkout.html'>CHECK-OUT VEHICLE</a><br><bR>");
		        out.println("<a href='history'>WATCH PARKING HISTORY </a><br><br>");
		        out.println("<a href='login.html'> LOG OUT </a>");
		        out.println("</div></body>");
		        out.println("</html>");
			}
			else {
				out.println("<html><body ><h3 text=red><center>INVALID CREDENTIALS..</center></h3></body></html>");
				RequestDispatcher rd=sc.getRequestDispatcher("/login.html");
				rd.include(request, response);    
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
