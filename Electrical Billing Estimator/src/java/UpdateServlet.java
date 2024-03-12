import java.io.IOException;  
import java.io.PrintWriter;  
  
import jakarta.servlet.ServletException;  
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;  
import jakarta.servlet.http.HttpServletRequest;  
import jakarta.servlet.http.HttpServletResponse;    
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
public class UpdateServlet extends HttpServlet 
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
            String addline1 = request.getParameter("addline1");  
            String addline2 = request.getParameter("addline2");
            String password = request.getParameter("password");

            Cookie ck[]=request.getCookies();  
            String phoneno = ck[0].getValue();
            
            String q = "update customer_details set name = ?, email = ?, addline1 = ?, addine2 = ?, password = ? where phoneno = ?";
            PreparedStatement ps=con.prepareStatement(q);  
            
            ps.setString(1,name);  
            ps.setString(2,emailid);  
            ps.setString(3,addline1);
            ps.setString(4,addline2);
            ps.setString(5,password);
            ps.setString(6,phoneno);

            int i = ps.executeUpdate();
            
            if(i>0)
            {
                out.print("<center>");
                out.print("<h1>");
                out.print("Successfully updated details...");
                out.print("</h1>");
                out.print("</center>");
            }
            else
            {
                out.print("<center>");
                out.print("<h1>");
                out.print("Unsuccessfull");
                out.print("</h1>");
                out.print("<h1>");
                out.print("Please check your details and try again!!");
                out.print("</h1>");
                out.print("</center>");
                
            }
        }
        catch (Exception e2) 
        {
            out.print(e2);
        }
        
        out.close();  
    }  
}
