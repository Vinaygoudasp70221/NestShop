package Com.shopNest.customer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/log")
public class LoginServlet extends HttpServlet
{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		
		String uname = req.getParameter("uname");
		String psw=req.getParameter("pass");
		Boolean val = Validator.isValid(uname, psw);
		//checking password is correct and user name is admin or custamer
		if(val==true && uname.equals("admin"))
		{
			res.sendRedirect("admin.jsp");
		}
		//checking password is correct and user name is customer
		else if(val==true)
		{
			res.sendRedirect("home.jsp");
		}
		
		//password is wrong
		else {
			res.sendRedirect("login.jsp");
		}
	}
	
}
