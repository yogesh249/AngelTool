package mypackage1;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.io.IOException;
public class ViewSolution extends HttpServlet 
{
  private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

  @Override
  public void init(ServletConfig config) throws ServletException
  {
    super.init(config);
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
      doPost(request, response);
  }

    @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    HttpSession session = request.getSession();
    String username = (String)session.getAttribute("username");
//    if(username==null)
//    {
//          response.sendRedirect("login.jsp");
//    }  
    response.setContentType(CONTENT_TYPE);
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<head><title>ViewSolution -- Developed by Yogesh Gandhi.</title></head>");
    out.println("<body>");
    
    int solutionNo = Integer.valueOf(request.getParameter("solutionNo")).intValue();
    
    SolutionDAO sdao = SolutionDAO.getjdbcsolutiondao();
    Solution solution = (Solution) sdao.getSolution(solutionNo);    
    out.println("<table>");
    out.println("<tr>");
    out.println("<td>");
    out.println("Description : ");
    out.println("</td>");
    out.println("<td>");
    out.println("<input readonly size=100 type=text name=Description value=\""+solution.getDescription()+"\">");
    out.println("</td>");
    out.println("</tr>");


    out.println("<tr>");
    out.println("<td>");
    out.println("Problem : ");
    out.println("</td>");
    out.println("<td>");
    out.println("<textarea readonly type=text rows=10 cols=100 name=problem>"+solution.getProblem()+"</textarea>");
    out.println("</td>");
    out.println("</tr>");

    out.println("<tr>");
    out.println("<td>");
    out.println("Logs : ");
    out.println("</td>");
    out.println("<td>");
    out.println("<textarea readonly type=text rows=10 cols=100 name=logs>"+solution.getLogs()+"</textarea>");
    out.println("</td>");
    out.println("</tr>");


    out.println("<tr>");
    out.println("<td>");
    out.println("Solution : ");
    out.println("</td>");
    out.println("<td>");
    out.println("<textarea readonly type=text rows=10 cols=100 name=solution>"+solution.getSolution()+"</textarea>");
    out.println("</td>");
    out.println("</tr>");
    out.println("</table>");
    
    if(solution.getFilename()!=null)
    {
        out.print("<a href=\"getBlob?solutionNo=" + solutionNo + "\">");
        out.print(solution.getFilename());
        out.print("</a></br>");
    }
    
    out.println("<a href=\"searchSolution.jsp\">Back to Search page</a>");
    out.println("<BR><BR><a href=\"index.jsp\">HOME PAGE</a>");    
    
    out.println("</body></html>");
    out.close();
  }
}