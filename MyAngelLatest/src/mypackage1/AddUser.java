package mypackage1;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.io.IOException;

public class AddUser extends HttpServlet 
{
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        out.println("<html>");
        out.println("<head><title>AddUser</title></head>");
        out.println("<body>");
        
        Properties pro = new Properties();
        InputStream in = new FileInputStream("D:\\MyAngelLatest\\usernames.properties");
        pro.load(in);
        String pwdInFile = pro.getProperty(username);
        if(!pro.keySet().contains(username))
        {
            pro.setProperty(username, password);
            // We need to set the proper path of usernames.properties file here
            // so that it can write.
            pro.store(new FileOutputStream("D:\\MyAngelLatest\\usernames.properties"),null);
            out.println("User created.");
            out.println("<BR><a href=\"index.jsp\">Back to home page</a>");
        }
        else
        {
            out.println("The specified user already exists.");            
        }

        out.println("</body></html>");
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}