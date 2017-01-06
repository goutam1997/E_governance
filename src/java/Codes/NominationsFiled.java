/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
//show all nomination filed
public class NominationsFiled extends HttpServlet
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
		try
		{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connection is being created");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/sis_db?autoReconnect=true&useSSL=false","root","abcdefgh");
            System.out.println("Connection done");
            PreparedStatement stmt=conn.prepareStatement("select * from nominations_filed");
            int id;
            String name,post;
			ResultSet rs=stmt.executeQuery();
            PrintWriter out=response.getWriter();
            out.print("<html><body>");
			out.print("<br />");
            out.print("id post name ");
            out.print("<br />");
            while(rs.next())
				{
                    id=rs.getInt("id");
                    post=rs.getString("post");
                    PreparedStatement stmt2=conn.prepareStatement("select * from student_details where student_main_id = ?");
                    stmt2.setInt(1,id);
                    ResultSet rs2=stmt2.executeQuery();
                    if(rs2.next()){
                        name=rs2.getString("first_name")+" "+rs2.getString("last_name");
                        System.out.println(name);
                        out.print(""+id+"\t"+name+"\t"+post+"\n");
                    }
                    if(rs2.next())
                    {
                        name=rs2.getString("first_name")+" "+rs2.getString("last_name");
                        System.out.println(name);
                        out.print(""+id+"\t"+name+"\t"+post+"\n");
                    }
                    out.print("<br />");
            }
            out.print("</body></html>");
            conn.close();
        }
		catch (Exception ex)
		{
			System.out.println(ex);
		}
		//request.setAttribute("result","DoS T T");
		//getServletContext().getRequestDispatcher("/dos.jsp").forward(request, response);//back to jsp page
    }
}
