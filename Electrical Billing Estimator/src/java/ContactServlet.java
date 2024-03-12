import jakarta.servlet.RequestDispatcher;
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
public class ContactServlet extends HttpServlet 
{  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {  
        response.setContentType("text/html");
        
        PrintWriter out=response.getWriter();
          
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ebs","root","password");
            Statement stmt=con.createStatement();

            String name = request.getParameter("name");  
            String emailid = request.getParameter("email");  
            String subject = request.getParameter("subject");  
            String message = request.getParameter("message");
            String phoneno = request.getParameter("mobile");
            
            PreparedStatement ps=con.prepareStatement("insert into contactform values(?,?,?,?,?)");  
  
            ps.setString(1,name);
            ps.setString(2,phoneno);
            ps.setString(3,emailid);  
            ps.setString(4,subject);
            ps.setString(5,message);

            int i = ps.executeUpdate();
            
            if(i>0)
            {
                out.print("<center>");
                out.print("<h2>");
                out.print("Thanks for filling the form");
                out.print("</h2>");
                out.print("<h2>");
                out.print("We will contact you shortly...");
                out.print("</h2>");
                out.print("</center>");
            }
            else
            {
                out.print("Unsuccessfull");
            }
        }
        catch (Exception e2) 
        {
           out.println(e2);
        }
        
        out.close();  
    }  
}
