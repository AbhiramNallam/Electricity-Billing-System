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
public class RegisterServlet extends HttpServlet 
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
            String phoneno = request.getParameter("phone");
            
            PreparedStatement ps=con.prepareStatement("insert into customer_details values(?,?,?,?,?,?)");  

            ps.setString(1,phoneno);  
            ps.setString(2,name);  
            ps.setString(3,emailid);  
            ps.setString(4,addline1);
            ps.setString(5,addline2);
            ps.setString(6,password);

            int i = ps.executeUpdate();
            
            if(i>0)
            {
                out.print("You are successfully registered...");
                request.getRequestDispatcher("login.html").include(request, response);
            }
            else
            {
                out.print("Unsuccessfull");
            }
        }
        catch (Exception e2) 
        {
            out.print(e2);
        }
        
        out.close();  
    }  
}
