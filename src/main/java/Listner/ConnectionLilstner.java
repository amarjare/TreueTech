package Listner;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class ConnectionLilstner implements ServletContextListener {

  
    public ConnectionLilstner() {
        // TODO Auto-generated constructor stub
    }

	
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	Connection con;
    public void contextInitialized(ServletContextEvent sce) {
    		try{
    		Class.forName("oracle.jdbc.driver.OracleDriver");
    		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
    		ServletContext sc=sce.getServletContext();
    		sc.setAttribute("oracle", con);
    		}catch(Exception e)
    		{
    		System.err.println(e);
    		}
    }
}
	

