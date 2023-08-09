package Listner;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/checkOut")
public class CheckOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CheckOut() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc=getServletContext();
		PrintWriter out=response.getWriter();
		Connection con=(Connection) sc.getAttribute("oracle");
		UserDetails ud=(UserDetails) sc.getAttribute("userdetails");
		String mobile=ud.getMobile();
		String vn=request.getParameter("vehicleNumber");
		try {
			PreparedStatement pstmt=con.prepareStatement("select parkouttime from parkedvehicle where vehiclenumber=? and parkouttime=? and mobile=?",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, vn);
			pstmt.setString(2, "parked");
			pstmt.setString(3, mobile);
			ResultSet rs=pstmt.executeQuery();
			LocalTime now = LocalTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
			String pot= now.format(formatter);
			if(rs.next()) {
				rs.updateString("parkouttime", pot);
				rs.updateRow();
				out.println("<!DOCTYPE html>");
		        out.println("<html>");
		        out.println("<head>");
		        out.println("<meta charset=\"UTF-8\">");
		        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		        out.println("<title>Parking Success</title>");
		        out.println("</head>");
		        out.println("<body style=\"background-image: url(images/opa1.png);background-size:cover;background-repeat: no-repeat;\" >");
		        out.println("<div style=\"margin:200px 150px; background-color:pink; border:5px solid black; border-radius:10px; height:150px;width:1200px; text-align:center;\">");
		        out.println("<h2>YOU CAN TAKE YOUR VEHICLE. THANKS FOR USING OUR PARKING SERVICE.</h2>");
		        out.println("<a href='login.html'>CLICK HERE TO LOG-OUT</a>");
		        out.println("</div>");
		        out.println("</body>");
		        out.println("</html>");
			}
			else {
				out.println("<html><body ><h3 text=red><center>VEHICLE IS NOT PARKED.</center></h3></body></html>");
				RequestDispatcher rd=sc.getRequestDispatcher("/checkout.html");
				rd.include(request, response);    
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}

}
