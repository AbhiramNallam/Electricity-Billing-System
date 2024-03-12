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
import java.sql.Statement;
public class DeleteServlet extends HttpServlet 
{  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {  
        response.setContentType("text/html");
        
        PrintWriter out=response.getWriter();
           
        try
        {
            Cookie ck[]=request.getCookies();  
            String phoneno = ck[0].getValue();  
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ebs","root","password");
            Statement stmt=con.createStatement();

            String password = request.getParameter("password");
            
            String pass = "select * from customer_details where phoneno=? and password=?";
            PreparedStatement p=con.prepareStatement(pass);
            
            p.setString(1, phoneno);
            p.setString(2, password);
            
            ResultSet rs = p.executeQuery();
            
            if(rs.next())
            {
                String q = "delete from customer_details where phoneno=?";
                PreparedStatement ps=con.prepareStatement(q);  

                ps.setString(1,phoneno);

                int i = ps.executeUpdate();

                if(i>0)
                {
                    out.print("<center>");
                    out.print("<h1>");
                    out.print("Account Successfully Deleted...");
                    out.print("</h1>");
                    out.print("</center>");
                    request.getRequestDispatcher("index.html").include(request, response);
                }
                else
                {
                    out.print("Account Deletion Unsuccessfull");
                    request.getRequestDispatcher("delete.html").include(request, response);
                }
            }
            else
            {
                out.print("Incorrect Password");
                request.getRequestDispatcher("delete.html").include(request, response);
            }
        }
        catch (Exception e2) 
        {
            out.print(e2);
        }
        
        out.close();  
    }  
}
