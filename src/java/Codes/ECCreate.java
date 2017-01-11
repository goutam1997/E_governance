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
            stmt=conn.prepareStatement("truncate election_committee");//clears the table
            stmt.executeUpdate();
            boolean flag=false;
            int size=Integer.parseInt(request.getParameter("size"));
			for (int i=1; i<=size; i++)
			{
				String name=request.getParameter("name"+i);
				String cno=request.getParameter("cno"+i);
				String ut="";
                if (i==1) ut="DoS";
                else ut="EC";
				if (name.equals(""))
				{
                    request.setAttribute("result","VC T");
                    getServletContext().getRequestDispatcher("/vc.jsp").forward(request, response);//back to jsp page
				}
                /*
				else
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
                 * */
				//int sno=i;
                System.out.println("name - "+name+" contact - "+cno+" user_type - "+ut+" sno - "+i);
				/*stmt=conn.prepareStatement("update election_committee set name = ? , contact = ? , user_type = ? where sno = ?");
				stmt.setString(1,name);
				stmt.setString(2,cno);
				stmt.setString(3,ut);
				stmt.setInt(4,sno);
				stmt.executeUpdate();*/
                String query = "insert into election_committee(id, name, contact, user_type)"+ " values (?, ?, ?, ?)";
                stmt=conn.prepareStatement(query);
                stmt.setInt(1,i);
				stmt.setString(2,name);
				stmt.setString(3,cno);
				stmt.setString(4,ut);
				stmt.execute();
			}
            stmt=conn.prepareStatement("update election_status set flag = ? where id = ?");
			stmt.setString(1,"T");
			stmt.setInt(2,3);
			stmt.executeUpdate();
			conn.close();

        }
		catch (Exception ex)
		{
			System.out.println(ex);
		}
        request.setAttribute("result","VC T T");
		getServletContext().getRequestDispatcher("/vc.jsp").forward(request, response);//back to jsp page
        /*
		out.print("<html><body>");
		out.print("Election Committee Created\n");
		out.print("<br />");
		out.print("</body></html>");
        */
    }
}