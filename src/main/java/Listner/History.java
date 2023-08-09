package Listner;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/history")
public class History extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public History() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter pw=response.getWriter();
			ServletContext sc= getServletContext();
			Connection con=(Connection) sc.getAttribute("oracle");
			UserDetails ud =(UserDetails) sc.getAttribute("userdetails");
			String fullname=ud.getFname()+" "+ud.getLname();
			String query="select * from parkedvehicle where fullname=?";
			PreparedStatement pstmt=con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, fullname);
			ResultSet rs=pstmt.executeQuery();
			String fn=fullname.toUpperCase();
			pw.print("<html><head><style>div{margin:100px 400px;padding:10px 30px;background-color:pink;text-align:center;border-radius:5px;width:650px;} body{background-image: url(images/opa1.png);\r\n"
					+ "	background-size:cover;\r\n"
					+ "	background-repeat: no-repeat;}h1{ marging:200px; color:black; font-family:forte;}td {\r\n"
					+ "  text-align: center;\r\n"
					+ "}</style></head><body>");
			pw.print("<center><h1 color=red>PARKING HISTORY FOR " + fn +" </h1></center>");
			pw.print("<div><table border=3px>");
			pw.print("<tr><th>SR NO</th><th>DATE</th><th>VEHICLE NO</th><th>VEHICLE TYPE</th><th>CHECK IN TIME</th><th>CHECK OUT TIME</th></tr>");
			int i=1;
			while(rs.next()) {
				String vn=rs.getString(3);
				String pd=rs.getString(7);
				String vt=rs.getString(4);
				String pit=rs.getString(5);
				String pot=rs.getString(6);
				pw.print("<tr><td>"+ i + "</td><td>"+ pd +"</td><td>"+ vn +"</td><td>"+vt+"</td><td>"+pit+"</td><td>"+ pot+"</td></tr>");
				
				i++;
			}
			pw.print("</table>");
			pw.print("<center><a href='login.html'>CLICK HERE TO LOG-OUT</a></center></div>");
			pw.print("</body></html>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
