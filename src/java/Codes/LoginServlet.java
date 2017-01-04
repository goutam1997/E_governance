/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


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
 * @author MANOSIJ
 */
public class LoginServlet extends HttpServlet
{
    private static final long serialVersionUID=1L;//important line
	class Details
	{
		boolean flag;
		int id;
		String name,roll_no,user_type;
		Details()
		{
			flag=false;
			id=-1;
			name="";
			roll_no="";
			user_type="";
		}
	}
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
        String name=request.getParameter("username");
        String password=request.getParameter("passwords");
		System.out.println (password);
        try
        {
            password = hash(password);
        }
        catch (NoSuchAlgorithmException ex)
		{
			Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        Details val=isPresent(password,name);
        if(val.flag)
		{
			if (val.user_type.equals("STUDENT"))
			{
				request.setAttribute("result",val.name);
				getServletContext().getRequestDispatcher("/student.jsp").forward(request, response);
			}
			else
			{
				/*out.print("<html><body>");
				out.print("Welcome "+val.name+" "+val.user_type+"\n");
				out.print("<br />");
				out.print("</body></html>");*/
				//CODE FOR SEPERATE PAGES
				String page="";
                String ele=status(1);
				if (val.user_type.equals("VC")){
                    page="/vc.jsp";
                    val.user_type=val.user_type+" "+ele;
                }
				else if (val.user_type.equals("DoS")){
                    page="/dos.jsp";
                    val.user_type=val.user_type+" "+ele;
                    ele=status(2);
                    val.user_type+=" "+ele;
                }
				else if (val.user_type.equals("EC")){
                    page="/ec.jsp";
                    val.user_type=val.user_type+" "+ele;
                }
				else
				{
					out.print("<html><body>");
					out.print("ERROR");
					out.print("<br />");
					out.print("</body></html>");
				}
				request.setAttribute("result",val.user_type);
				getServletContext().getRequestDispatcher(page).forward(request, response);//error
			}
        }
        else
		{
            try
			{
                out.print("<html><body>");
                out.print("Incorrect credentials "+name);
                out.print("<br />");
                out.print("</body></html>");
            }
			catch(Exception e)
			{
                out.close();
            }
        }
    }
    public String status(int id)
	{
		String x="";
		try
		{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connection is being created");
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost/sis_db?autoReconnect=true&useSSL=false","root","abcdefgh");
            System.out.println("Connection done");
			//System.out.println("Hollo");
            Statement stmt=myConn.createStatement();
			//System.out.println("Hello");
            ResultSet rs1=stmt.executeQuery("select flag from election_status where id = '"+id+"'");
			if (rs1.next())
			x=rs1.getString("flag");
			myConn.close();
        }
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		return x;
	}
    public String hash(String password) throws NoSuchAlgorithmException
	{
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.reset();
        m.update(password.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        String hashtext = bigInt.toString(16);
        // Now we need to zero pad it if you actually want the full 32 chars.
        while(hashtext.length() < 32 )
		{
          hashtext = "0"+hashtext;
        }
        return hashtext;
    }
    public Details isPresent(String password,String name)
	{
        Details obj=new Details();
        try
		{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connection is being created");
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost/sis_db","root","abcdefgh"); // change needed here
            //here sonoo is database name, root is username and password
            System.out.println("Connection done");
            Statement stmt=myConn.createStatement();
            System.out.println("query being done on "+name);
            ResultSet rs1=stmt.executeQuery("select id from student_main where roll_no like '"+name+"' and pass_hash like '"+password+"'");
            System.out.println("queried");

            System.out.println("Connection closed");
			int count=0;
			if(rs1.next())
			{
				count++;
				//Statement stmt2=myConn.createStatement();
                obj.id=rs1.getInt(1);//error line if executed forcefully
                obj.roll_no=name;
                System.out.println(obj.id);
                String query2="select * from student_details where student_main_id = '"+obj.id+"'";
                ResultSet rs2=stmt.executeQuery(query2);
                if(rs2.next())
				{
                    obj.name=rs2.getString("first_name")+" "+rs2.getString("last_name");
					System.out.println(obj.name);
                }
                System.out.println("id: " + obj.id);
				obj.user_type="STUDENT";
                obj.flag=true;

			}
			else
			{
				ResultSet rs3=stmt.executeQuery("select * from admin_details where username like '"+name+"'and pass_hash like '"+password+"'");
				if (rs3.next())
				{
					obj.id=rs3.getInt(1);
					obj.roll_no=name;
					obj.user_type=rs3.getString("admin_type");
					System.out.println(obj.user_type);
					obj.flag=true;
					obj.name="ADMIN"; // changes required
				}
			}
			myConn.close();
        }
		catch(Exception e)
		{
			System.out.println(e);
		}
		return obj;
    }
}