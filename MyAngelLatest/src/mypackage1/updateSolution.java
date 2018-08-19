package mypackage1;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.io.IOException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class updateSolution extends HttpServlet 
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
    String username = (String)session.getAttribute("username");
    if(username==null)
    {
          response.sendRedirect("login.jsp");
    }  
    response.setContentType(CONTENT_TYPE);
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<head><title>update Solution -- Developed by Yogesh Gandhi.</title></head>");
    out.println("<body>");
    
    int solutionNo = Integer.valueOf(request.getParameter("solutionNo")).intValue();
    
    String description = request.getParameter("desc");
    String  problem = request.getParameter("problem");
    String solution = request.getParameter("solution");
    String logs = request.getParameter("logs");
    
    String query = "update angel set description=?, problem=?, logs=?, solution=? where solutionNo=?";

    SolutionDAO sdao = SolutionDAO.getjdbcsolutiondao();   
    
    int rowsUpdated = sdao.update(description, problem, logs, solution, solutionNo);

    out.println(rowsUpdated + " rows updated successfully");
    
    out.println("<BR><BR><a href=\"searchSolution.jsp\">Back to Search page</a>");          
    out.println("<BR><a href=\"index.jsp\">HOME PAGE</a>");
    
    out.println("</body></html>");
    out.close();
  }
}