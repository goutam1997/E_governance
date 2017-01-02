package Codes;

import java.security.NoSuchAlgorithmException;
import java.io.*;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SOUMYADEEP
 */
public class ElectionStart extends HttpServlet
{
    private static final long serialVersionUID=1L;//important line
    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
	{
        try 
		{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("Driver Loaded");
        }
		catch (Exception ex) 
		{
            System.out.println("SQL Driver not Found");
        }
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
		try
		{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connection is being created");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/sis_db?autoReconnect=true&useSSL=false","root","papan2202");
            System.out.println("Connection done");
            PreparedStatement stmt=conn.prepareStatement("update election_start set flag = ? where id = ?");
			stmt.setString(1,"T");
			stmt.setInt(2,1);
			stmt.executeUpdate();
			conn.close();
        
        }
		catch (Exception ex)
		{
			System.out.println(ex);
		}
		request.setAttribute("result","VC T");
		getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
    }
}
