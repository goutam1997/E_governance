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
public class ECCreate extends HttpServlet
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
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/sis_db?autoReconnect=true&useSSL=false","root","abcdefgh");
			System.out.println("Connection done");
			PreparedStatement stmt;
			for (int i=1; i<=4; i++)
			{
				String name=request.getParameter("name"+i);
				String cno=request.getParameter("cno"+i);
				String ut=request.getParameter("utype"+i);
				if (i==1)
				{
					if (name==null || !(ut.equals("DoS"))) // not working!!!
					{
						out.println("<html><body>ERROR</body></html>");
						throw new NullPointerException("Data field kept empty");
					}
				}
				else // not working!!!
				{
					if (ut.equals("Dos"))
					{
						out.println("<html><body>DOS not unique</body></html>");
						throw new Exception("DoS not unique");
					}
				}
				int sno=i;
				stmt=conn.prepareStatement("update election_committee set name = ? , contact = ? , user_type = ? where sno = ?");
				stmt.setString(1,name);
				stmt.setString(2,cno);
				stmt.setString(3,ut);
				stmt.setInt(4,sno);
				stmt.executeUpdate();
			}
			conn.close();

        }
		catch (Exception ex)
		{
			System.out.println(ex);
		}
		out.print("<html><body>");
		out.print("Election Committee Created\n");
		out.print("<br />");
		out.print("</body></html>");
    }
}