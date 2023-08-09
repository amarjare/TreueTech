package Listner;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/park")
public class Park extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Park() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			ServletContext sc=getServletContext();
			UserDetails ud=(UserDetails) sc.getAttribute("userdetails");
			String fullname=ud.getFname()+" "+ud.getLname();
			String mobile=ud.getMobile();
			String vehiclenumber=request.getParameter("vn");
			String vehicletype=request.getParameter("vt");
			LocalTime now = LocalTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
			String parkTime= now.format(formatter);
			LocalDate currentDate = LocalDate.now();
			String parkdate = currentDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			try {
				
				Connection con=(Connection) sc.getAttribute("oracle");
				PreparedStatement pstmt=con.prepareStatement("insert into parkedvehicle values(?,?,?,?,?,?,?)");
				pstmt.setString(1, fullname);
				pstmt.setString(2, mobile);
				pstmt.setString(3, vehiclenumber);
				pstmt.setString(4, vehicletype);
				pstmt.setString(5, parkTime);
				pstmt.setString(6, "parked");
				pstmt.setString(7, parkdate);
				pstmt.execute();
			 String successMessage = "Your vehicle parked successfully. Thanks for using our parking system.";
			 response.setContentType("text/html");
			 PrintWriter out = response.getWriter();
			 out.println("<!DOCTYPE html>");
		        out.println("<html>");
		        out.println("<head>");
		        out.println("<meta charset=\"UTF-8\">");
		        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		        out.println("<title>Parking Success</title>");
		        out.println("</head>");
		        out.println("<body style=\"background-image: url(images/opa1.png);background-size:cover;background-repeat: no-repeat;\">");
		        out.println("<div style=\"margin:200px 150px; background-color:pink; border:5px solid black; border-radius:10px; height:150px;width:1200px; text-align:center;\">");
		        out.println("<h2>" + successMessage + "</h2>");
		        out.println("<a href='login.html'>CLICK HERE TO LOG OUT</a>");
		        out.println("</div>");
		        out.println("</body>");
		        out.println("</html>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

}
