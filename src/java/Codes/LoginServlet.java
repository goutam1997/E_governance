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
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException
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
        String name=request.getParameter("uname");
        String password=request.getParameter("rno");
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
			request.setAttribute("result",val.name);
			getServletContext().getRequestDispatcher("/hello.jsp").forward(request, response);
			/*
            try 
			{
                out.print("<html><body>");
                out.print("welcome "+name);
                out.print("<br />");
                out.print("password - "+password);
                out.print("</body></html>");
            }
			catch(Exception e) 
			{
                out.close();
            }
			*/
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
    public Details isPresent(String passwrord,String name)
	{
        Details obj=new Details();
        try
		{
            Class.forName("com.mysql.jdbc.Driver");
				//Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mysql","root","papan2202");
				//Statement stmt = conn.createStatement();

            System.out.println("Connection is being created");
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost/sis_db","root","papan2202");
            //here sonoo is database name, root is username and password
            System.out.println("Connection done");
            Statement stmt=myConn.createStatement();
            System.out.println("query being done on "+name);
            ResultSet rs1=stmt.executeQuery("select id from student_main where roll_no like '"+name+"'");
            System.out.println("queried");
            
            System.out.println("Connection closed");
			int count=0;
            String garbage="", u_type="";
			if(rs1.next())
			{
				count++;
				Statement stmt2=myConn.createStatement();
                obj.id=rs1.getInt(1);//error line if executed forcefully
                obj.roll_no=name;
                System.out.println(obj.id);
                String query2="select * from student_details where student_main_id = '"+obj.id+"'";
                ResultSet rs2=stmt2.executeQuery(query2);
                if(rs2.next())
				{
                    obj.name=rs2.getString("first_name")+" "+rs2.getString("last_name");
					System.out.println(obj.name);
                }
                System.out.println("id: " + obj.id);
                obj.flag=true;
				
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
