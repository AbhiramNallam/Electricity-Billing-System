import java.io.IOException;  
import java.io.PrintWriter;  
import jakarta.servlet.ServletException;  
import jakarta.servlet.http.HttpServlet;  
import jakarta.servlet.http.HttpServletRequest;  
import jakarta.servlet.http.HttpServletResponse;  
import jakarta.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
public class ProfileServlet extends HttpServlet 
{  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {  
        response.setContentType("text/html");
        
        PrintWriter out=response.getWriter();
        
        request.getRequestDispatcher("link.html").include(request, response);  
          
        HttpSession session=request.getSession(false);
        try
        {
            if(session!=null)
            {  
                String name=(String)session.getAttribute("name");
                out.print("<center>");
                out.print("<h3>");
                out.print("Hello "+name+", Welcome to Profile");
                out.print("</h3>");
                out.print("<h3>");
                out.println("Your details are:");
                out.print("</h3>");
                out.print("<br>");
                out.print("</center>");
            }
            String name1=(String)session.getAttribute("name");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ebs","root","password");
            Statement stmt=con.createStatement();

            PreparedStatement ps=con.prepareStatement("select * from customer_details where name=?");  
            ps.setString(1,name1);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int total = rsmd.getColumnCount();
          
            if(rs.next())
            {  
                out.print("<center>");
                out.print("<h3>");
                out.print("Name: "+rs.getString(2));
                out.print("</h3>");
                out.print("<h3>");
                out.print("Phone Number: "+rs.getString(1));
                out.print("</h3>");
                out.print("<h3>");
                out.print("Email ID: "+rs.getString(3));
                out.print("</h3>");
                out.print("<h3>");
                out.print("Address: "+rs.getString(4)+", "+rs.getString(5));
                out.print("</h3>");
                out.print("</center>");
            }
            else
            {  
                out.print("Please login first");  
                request.getRequestDispatcher("login.html").include(request, response);  
            }
        }
        catch (Exception e2) 
        {
            System.out.println(e2);
        }
        
        out.close();  
    }  
}  