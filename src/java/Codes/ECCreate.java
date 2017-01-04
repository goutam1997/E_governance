package Codes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MANOSIJ
 */
//chages to be done -
/*
 * connect with admin details table
 * ensure only 1 dos
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
            boolean flag=false;
			for (int i=1; i<=4; i++)
			{
				String name=request.getParameter("name"+i);
				String cno=request.getParameter("cno"+i);
				String ut=request.getParameter("utype"+i);
				if (name.equals(""))
				{
                    request.setAttribute("result","VC T");
                    getServletContext().getRequestDispatcher("/vc.jsp").forward(request, response);//back to jsp page
				}
				else // not working!!!
				{
					if (ut.equals("DoS")&&(!flag))
					{
						flag=true;
					}
                    else if(ut.equals("DoS"))
                    {
                        out.println("<html><body>DOS not unique</body></html>");
                        request.setAttribute("result","VC T");
                        getServletContext().getRequestDispatcher("/vc.jsp").forward(request, response);//back to jsp page
                    }
				}
				int sno=i;
                System.out.println("name - "+name+" contact - "+cno+" user_type - "+ut+" sno - "+sno);
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