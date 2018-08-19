package mypackage1;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.http.HttpSession;
public class LoginServlet extends HttpServlet 
{
  private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

  public void init(ServletConfig config) throws ServletException
  {
    super.init(config);
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
      doPost(request, response);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
      HttpSession session = request.getSession();
      RequestDispatcher index = request.getRequestDispatcher("index.jsp");  
      
        // Get the username that user has entered.
      String username = request.getParameter("username");
      String password = request.getParameter("password");      
      if( (username==null && password==null) || ("".equals(username)))
      {
          Cookie[] cookies = request.getCookies();
          if(cookies!=null)
          {
              for(int i=0;i<cookies.length;i++)
              {
                  if(cookies[i].getName().equals("username"))
                  {
                      session.setAttribute("username", cookies[i].getValue());
                      index.forward(request, response);
                      return;
                  }
              }
          }
          RequestDispatcher loginjsp = request.getRequestDispatcher("login.jsp");  
          session.setAttribute("username", "");
          loginjsp.forward(request, response);
          return;
      }
      

      PrintWriter out = response.getWriter();
      Properties pro = new Properties();
      //InputStream in = this.getClass().getClassLoader().getResourceAsStream("usernames.properties");
      InputStream in = new FileInputStream("D:\\MyAngelLatest\\usernames.properties");
      pro.load(in);
      String pwdInFile = pro.getProperty(username);
      if(!pro.keySet().contains(username))
      {
         out.println("No such username exists<BR>");
         out.println("<a href=\"login.jsp\">Back to login Page</a>");
         return;
      }

      
      if(password.equals(pro.getProperty(username)))
      {

           Cookie cookie = new Cookie("username",username);
           cookie.setMaxAge(30*24*60*60);
           response.addCookie(cookie);
           session.setAttribute("username", username);
           index.forward(request, response);
      }
      else
      {
           out.println("Invalid username / password ");
           return;
      }
  }
}