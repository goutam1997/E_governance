/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Codes;

import java.security.NoSuchAlgorithmException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MANOSIJ
 */
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
    private static final long serialVersionUID=1L;//important line
    @Override
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException{
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("Driver Loaded");
        } catch (Exception e) {
            System.out.println("SQL Driver not Found");
        }
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        String name=request.getParameter("username");
        String password=request.getParameter("passwords");
        try
        {
            password = hash(password);
        }
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
        }
        else{
            try {
                out.print("<html><body>");
                out.print("Incorrect credentials "+name);
                out.print("<br />");
                out.print("</body></html>");
            }catch(Exception e) {
                out.close();
            }
        }
    }
    public String hash(String password) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.reset();
        m.update(password.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        String hashtext = bigInt.toString(16);
        // Now we need to zero pad it if you actually want the full 32 chars.
        while(hashtext.length() < 32 ){
          hashtext = "0"+hashtext;
        }
        return hashtext;
    }
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
                obj.id=rs1.getInt(1);//error line if executed forcefully
                obj.roll_no=name;
                System.out.println(obj.id);
                String query2="select * from student_details where student_main_id = '"+obj.id+"'";
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
    }
}

