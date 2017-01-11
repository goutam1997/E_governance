package Codes;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MANOSIJ
 */
//starts the election process
public class ElectionStart extends HttpServlet
{
    private static final long serialVersionUID=1L;//important line
    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
	{
        System.out.println("electionStart page opened");
        try
		{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("Driver Loaded");
        }
		catch (Exception ex) 
		{
            System.out.println("SQL Driver not Found");
        }
		try
		{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connection is being created");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/sis_db?autoReconnect=true&useSSL=false","root","abcdefgh");
            System.out.println("Connection done");
            PreparedStatement stmt=conn.prepareStatement("update election_status set flag = ? where id = ?");
			stmt.setString(1,"T");
			stmt.setInt(2,1);
			stmt.executeUpdate();
			conn.close();
        
        }
		catch (Exception ex)
		{
			System.out.println(ex);
		}
		request.setAttribute("result","VC T F");
		getServletContext().getRequestDispatcher("/vc.jsp").forward(request, response);//back to jsp page
    }
}
