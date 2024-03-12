import java.io.IOException;  
import java.io.PrintWriter;  

import jakarta.servlet.*;
import jakarta.servlet.http.*;
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
public class LoginServlet extends HttpServlet 
{  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
    {  
        response.setContentType("text/html");
        
        PrintWriter out=response.getWriter();
        
        request.getRequestDispatcher("link.html").include(request, response);  
          
        String password = request.getParameter("password");
        String phoneno = request.getParameter("username");
        
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ebs","root","password");
                Statement stmt=con.createStatement();

                PreparedStatement ps=con.prepareStatement("select * from customer_details where phoneno=? and password=?");
                ps.setString(1,phoneno);  
                ps.setString(2,password);
                ResultSet rs = ps.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                int total = rsmd.getColumnCount();
                Cookie ck = new Cookie("uname",request.getParameter("username"));
                response.addCookie(ck);
          
        if(rs.next())
        {  
            rs.getString(1);
            String name=rs.getString(2);
            out.print("<center>");
            out.print("<h1>");
            out.print("Welcome, "+name);
            out.print("</h1>");
            out.print("</center>");
            HttpSession session=request.getSession();  
            session.setAttribute("name",name);  
        }  
        else
        {  
            out.print("Sorry, username or password error!");  
            request.getRequestDispatcher("login.html").include(request, response);  
        } 
        }
        catch (Exception e2) 
        {
            out.print(e2);
        }
        
        out.close();  
    }  
}
