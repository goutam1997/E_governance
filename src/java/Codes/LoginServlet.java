/*
MAKE NECESSARY CHANGES TO THE CODE WHEREVER MENTIONED
*/

package Codes;

import java.security.NoSuchAlgorithmException;
import java.io.*;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
<<<<<<< HEAD
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
=======
import java.sql.*;
>>>>>>> edits_st
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
<<<<<<< HEAD
class Details{
    boolean flag;
    int id;
    String name,roll_no,user_type;
    Details(){
        flag=false;
        id=-1;
        name="";
        roll_no="";
        user_type="";
    }
}
public class LoginServlet extends HttpServlet{
=======
public class LoginServlet extends HttpServlet
{
>>>>>>> edits_st
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
<<<<<<< HEAD
        } catch (Exception e) {
=======
        }
		catch (Exception ex) 
		{
>>>>>>> edits_st
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
<<<<<<< HEAD
        catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
       }
        Details val=isPresent(password,name);
        if(val.flag){
            try {
                /*
                 * String site="xyz.html";
                 * response.setStatus(response.SC_MOVED_TEMPORARILY);
                 * response.setHeader("Location", site);
                 */
                out.print("<html><body>");
                out.print("welcome "+val.name);
                out.print("<br />");
                out.print("password - "+password);
                out.print("<br />");
                out.print("roll_no - "+val.roll_no);
                out.print("<br />");
                out.print("</body></html>");
            }catch(Exception e) {
                out.close();
             }
=======
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
				getServletContext().getRequestDispatcher("/hello.jsp").forward(request, response);
			}
			else
			{
				if (val.user_type.equals("VC"))
				{
					String ele=status();
					val.user_type=val.user_type+" "+ele;
				}
				request.setAttribute("result",val.user_type);
				getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
			}
>>>>>>> edits_st
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
<<<<<<< HEAD
    public Details isPresent(String passwrord,String name){
        Details obj=new Details();
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sis_db","root","abcdefgh");
            //here sis_db is database name, root is username and password
            System.out.println("Connection done");
            java.sql.Statement stmt=myConn.createStatement();
            System.out.println("query being done");
            //ResultSet rs=stmt.executeQuery("select id from sis_db.student_main where roll_no like "+name);
            String query1="select id from student_main where roll_no = '"+name+"'";
            ResultSet rs1=stmt.executeQuery(query1);
            int count=0;
            System.out.println("queried");
            while(rs1.next()) {
                java.sql.Statement stmt2=myConn.createStatement();
            //if(true){
                //System.out.println("juju");
=======
	
	public String status()
	{
		String x="";
		try
		{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connection is being created");
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost/sis_db?autoReconnect=true&useSSL=false","root","papan2202");
            System.out.println("Connection done");
			//System.out.println("Hollo");
            Statement stmt=myConn.createStatement();
			//System.out.println("KHollo");
            ResultSet rs1=stmt.executeQuery("select flag from election_start where id = 1");
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
	
    public Details isPresent(String password,String name)
	{
		
        Details obj=new Details();
        try
		{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connection is being created");
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost/sis_db","root","papan2202");
            //here sonoo is database name, root is username and password
            System.out.println("Connection done");
            Statement stmt=myConn.createStatement();
            System.out.println("query being done on "+name);
            ResultSet rs1=stmt.executeQuery("select id from student_main where roll_no like '"+name+"' and pass_hash like '"+password+"'");
            System.out.println("queried");
            
            System.out.println("Connection closed");
			int count=0;
            String garbage="", u_type="";
			if(rs1.next())
			{
				count++;
				//Statement stmt2=myConn.createStatement();
>>>>>>> edits_st
                obj.id=rs1.getInt(1);//error line if executed forcefully
                obj.roll_no=name;
                System.out.println(obj.id);
                String query2="select * from student_details where student_main_id = '"+obj.id+"'";
<<<<<<< HEAD
                ResultSet rs2=stmt2.executeQuery(query2);
                if(rs2.next()){
                    String garbage=rs2.getString(1);//impoartant else cant work
                    garbage=rs2.getString(2);
                    obj.name=rs2.getString("first_name");
                    garbage=rs2.getString(4);
                    obj.name=obj.name+" "+rs2.getString("last_name");
                    //obj.usertype=.....
                    rs2.close();
                }
                System.out.println("id: " + obj.id);
                obj.flag=true;
                count++;
            }
            if (count==0){
                obj.flag=false;
            }
            rs1.close();
            myConn.close();
            System.out.println("Connections closed");
        }catch(Exception e){
            System.out.println(e);}
        return obj;
=======
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
>>>>>>> edits_st
    }
}

