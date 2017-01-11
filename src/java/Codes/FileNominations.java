/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Codes;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MANOSIJ
 */
public class FileNominations extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
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
        String name=request.getParameter("name");
        //String id=request.getParameter("id");
        int id=Integer.parseInt(request.getParameter("id"));
		System.out.println ("id passesd - "+id);
        String post=request.getParameter("post");
        try
		{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connection is being created");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/sis_db?autoReconnect=true&useSSL=false","root","abcdefgh");
            System.out.println("Connection done");
            PreparedStatement stmt=(PreparedStatement) conn.prepareStatement("select post from nominations_filed where id = ?");
            stmt.setInt(1,id);
			ResultSet rs=stmt.executeQuery();
            if(rs.next())
            {
                String postf=rs.getString("post");
                if(postf.equals(post))
                {
                    out.print("<html><body>");
					out.print("Already Filed");
					out.print("<br />");
					out.print("</body></html>");
                }
                else
                {
                    stmt=(PreparedStatement) conn.prepareStatement("update nominations_filed set post = ? where id = ?");
                    stmt.setString(1,post);
                    stmt.setInt(2,id);
                    stmt.executeUpdate();
                    out.print("<html><body>");
					out.print("Updated to "+post);
					out.print("<br />");
					out.print("</body></html>");
                }
            }
            else
            {
                String query = " insert into nominations_filed (id,post)"+ " values (?, ?)";
                // create the mysql insert preparedstatement
                stmt=(PreparedStatement) conn.prepareStatement(query);
                stmt.setInt (1, id);
                stmt.setString (2,post);
                // execute the preparedstatement
                stmt.execute();
                out.print("<html><body>");
				out.print("Nomination Filed");
				out.print("<br />");
				out.print("</body></html>");
            }
            conn.close();
        }
		catch (Exception ex)
		{
            System.out.println("Error in database access");
        }
    } 
}
