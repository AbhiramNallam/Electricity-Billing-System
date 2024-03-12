import java.io.IOException;  
import java.io.PrintWriter;  
  
import jakarta.servlet.ServletException;  
import jakarta.servlet.http.HttpServlet;  
import jakarta.servlet.http.HttpServletRequest;  
import jakarta.servlet.http.HttpServletResponse;  
import jakarta.servlet.http.HttpSession;  
public class CalculateServlet extends HttpServlet 
{  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {  
        response.setContentType("text/html");
        
        PrintWriter out=response.getWriter();

                String tl_no_s = request.getParameter("tl_no");  
                String tl_hr_s = request.getParameter("tl_hr");
                String fan_no_s = request.getParameter("fan_no");  
                String fan_hr_s = request.getParameter("fan_hr");
                String b_no_s = request.getParameter("b_no");  
                String b_hr_s = request.getParameter("b_hr");
                String ac_no_s = request.getParameter("ac_no");  
                String ac_hr_s = request.getParameter("ac_hr");
                String pc_no_s = request.getParameter("pc_no");  
                String pc_hr_s = request.getParameter("pc_hr");
                String fridge_no_s = request.getParameter("fridge_no");  
                String fridge_hr_s = request.getParameter("fridge_hr");
                String wm_no_s = request.getParameter("cook_no");  
                String wm_hr_s = request.getParameter("cook_hr");
                String o_no_s = request.getParameter("o_no");  
                String o_hr_s = request.getParameter("o_hr");
                String cook_no_s = request.getParameter("cook_no");  
                String cook_hr_s = request.getParameter("cook_hr");

                int tl_no = Integer.parseInt(tl_no_s);
                int tl_hr = Integer.parseInt(tl_hr_s);
                int fan_no = Integer.parseInt(fan_no_s);
                int fan_hr = Integer.parseInt(fan_hr_s);
                int b_no = Integer.parseInt(b_no_s);
                int b_hr = Integer.parseInt(b_hr_s);
                int ac_no = Integer.parseInt(ac_no_s);
                int ac_hr = Integer.parseInt(ac_hr_s);
                int pc_no = Integer.parseInt(pc_no_s);
                int pc_hr = Integer.parseInt(pc_hr_s);
                int fridge_no = Integer.parseInt(fridge_no_s);
                int fridge_hr = Integer.parseInt(fridge_hr_s);
                int wm_no = Integer.parseInt(wm_no_s);
                int wm_hr = Integer.parseInt(wm_hr_s);
                int o_no = Integer.parseInt(o_no_s);
                int o_hr = Integer.parseInt(o_hr_s);
                int cook_no = Integer.parseInt(cook_no_s);
                int cook_hr = Integer.parseInt(cook_hr_s);

                int tl = tl_hr * tl_no * 30 * 100;
                int fan = fan_hr * fan_no * 30 * 70;
                int b = b_hr * b_no * 30 * 70;
                int ac = ac_hr * ac_no * 30 * 1000;
                int pc = pc_hr * pc_no * 30 * 150;
                int fridge = fridge_hr * fridge_no * 30 * 540;
                int wm = wm_hr * wm_no * 30 * 500;
                int o = o_hr * o_no * 30 * 1000;
                int cook = cook_hr * cook_no * 30 * 1400;
                

                int sum = tl+fan+b+ac+pc+fridge+wm+o+cook;

                int total = sum/1000;
                out.print("<center>");
                out.print("<h2>");
                out.print("Total Units consumed are "+total);
                out.print("</h2>");
                out.print("<br>");
                
                int temp = total;
                double cost;
                
                if(temp<=30)
                {
                    cost = temp * 1.90;
                    out.print("<h2>");
                    out.print("The Estimated electricity bill is: Rs."+cost);
                    out.print("</h2>");
                }
                else if(30<temp && temp<=75)
                {
                    temp = temp - 30;
                    cost = 30 * 1.9;
                    cost = temp*3 + cost;
                    out.print("<h2>");
                    out.print("The Estimated electricity bill is: Rs."+cost);
                    out.print("</h2>");
                }
                else if(75<temp && temp<=125)
                {
                    temp = temp - 30;
                    cost = 30 * 1.9;
                    temp = temp - 45;
                    cost = cost + 45 * 3;
                    cost = temp*4.5 + cost;
                    out.print("<h2>");
                    out.print("The Estimated electricity bill is: Rs."+cost);
                    out.print("</h2>");
                }
                else if(125<temp && temp<=225)
                {
                    temp = temp - 30;
                    cost = 30 * 1.9;
                    temp = temp - 45;
                    cost = cost + 45 * 3;
                    temp = temp - 50;
                    cost = cost + 50 * 4.5;
                    cost = temp*6 + cost;
                    out.print("<h2>");
                    out.print("The Estimated electricity bill is: Rs."+cost);
                    out.print("</h2>");
                }
                else if(225<temp && temp<=400)
                {
                    temp = temp - 30;
                    cost = 30 * 1.9;
                    temp = temp - 45;
                    cost = cost + 45 * 3;
                    temp = temp - 50;
                    cost = cost + 50 * 4.5;
                    temp = temp - 100;
                    cost = cost + 100 * 6;
                    cost = temp*8.75 + cost;
                    out.print("<h2>");
                    out.print("The Estimated electricity bill is: Rs."+cost);
                    out.print("</h2>");
                }
                else
                {
                    temp = temp - 30;
                    cost = 30 * 1.9;
                    temp = temp - 45;
                    cost = cost + 45 * 3;
                    temp = temp - 50;
                    cost = cost + 50 * 4.5;
                    temp = temp - 100;
                    cost = cost + 100 * 6;
                    temp = temp - 175;
                    cost = cost * 8.75;
                    cost = temp*9.75 + cost;
                    out.print("<h2>");
                    out.print("The Estimated electricity bill is: Rs."+cost);
                    out.print("</h2>");
                }
                
                double tempcost = cost;
                             
                out.print("<h2>");
                out.print("Fixed charges are Rs.10");
                out.print("</h2>");
                
                double duty = (tempcost*6)/100;
                
                out.print("<h2>");
                out.print("Electricity Duty charges are Rs."+duty);
                out.print("</h2>");
                
                double totalcost = tempcost + 10 + duty;
                
                out.print("<br>");
                
                out.print("<h2>");
                out.print("Total Estimated Bill Amount is Rs."+totalcost);
                out.print("</h2>");
                
                out.print("</center>");

        out.close();  
    }  
}
