package mypackage1;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.io.IOException;
public class editSolution extends HttpServlet 
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
    if(username==null)
    {
          response.sendRedirect("login.jsp");
    }  
    response.setContentType(CONTENT_TYPE);
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<head><title>EditSolution -- Developed by Yogesh Gandhi</title>");
    out.println("<script language=\"javascript\" type=\"text/javascript\">");
    out.println("function limitText(limitField, limitCount, limitNum) {");
    out.println("if (limitField.value.length > limitNum) {");
    out.println("alert(\"Maximum Limit is \" + limitNum);");
    out.println("limitField.value = limitField.value.substring(0, limitNum);");
    out.println("} else {");
    out.println("limitCount.value = limitNum - limitField.value.length;");
    out.println("}");
    out.println("}");
    out.println("</script>  ");
    
    
    out.println("</head>");
    out.println("<body>");
    
    int solutionNo = Integer.valueOf(request.getParameter("solutionNo")).intValue();
    
    SolutionDAO sdao = SolutionDAO.getjdbcsolutiondao();
    Solution solution = (Solution) sdao.getSolution(solutionNo);
    if(!username.equalsIgnoreCase(solution.getSubmittedby()))
    {
          response.sendRedirect("cantEdit.jsp");
          return;
    }  
    out.println("<form action=\"updateSolution\" method=POST>");
    out.println("<input type=\"hidden\" name=\"solutionNo\" value=\""+solutionNo+"\"/>");
    out.println("<table>");
    out.println("<tr>");
    out.println("<td>");
    out.println("Description : ");
    out.println("</td>");
    out.println("<td>");
    out.println("<input size=100 type=text name=desc value=\""+solution.getDescription()+"\">");
    out.println("</td>");
    out.println("</tr>");


    out.println("<tr>");
    out.println("<td>Characters Remaining </td>");
    out.println("<td span=2><input readonly type=\"text\" name=\"countdownProblem\" size=\"4\" value=\"4000\"></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td>");
    out.println("Problem : ");
    out.println("</td>");
    out.println("<td>");
    out.println("<textarea type=text rows=10 cols=100 name=problem ");
    out.println("onKeyDown=\"limitText(this.form.problem,this.form.countdownProblem,4000);\" ");
    out.println("onKeyUp=\"limitText(this.form.problem,this.form.countdownProblem,4000);\" ");   
    out.println(">"+solution.getProblem()+"</textarea>");
    out.println("</td>");
    out.println("</tr>");

    out.println("<tr>");
    out.println("<td>Characters Remaining </td>");
    out.println("<td span=2><input readonly type=\"text\" name=\"countdownLogs\" size=\"5\" value=\"40000\"></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td>");
    out.println("Logs : ");
    out.println("</td>");
    out.println("<td>");
    out.println("<textarea type=text rows=10 cols=100 name=logs ");
    out.println("onKeyDown=\"limitText(this.form.logs,this.form.countdownLogs,40000);\" ");
    out.println("onKeyUp=\"limitText(this.form.logs,this.form.countdownLogs,40000);\" ");   
    out.println(">"+solution.getLogs()+"</textarea>");
    
    out.println("</td>");
    out.println("</tr>");



    out.println("<tr>");
    out.println("<td>Characters Remaining </td>");
    out.println("<td span=2><input readonly type=\"text\" name=\"countdownSolution\" size=\"4\" value=\"4000\"></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td>");
    out.println("Solution : ");
    out.println("</td>");
    out.println("<td>");
    out.println("<textarea type=text rows=10 cols=100 name=solution  ");
    out.println("onKeyDown=\"limitText(this.form.solution,this.form.countdownSolution,4000);\" ");
    out.println("onKeyUp=\"limitText(this.form.solution,this.form.countdownSolution,4000);\" ");   
    out.println(">"+solution.getSolution()+"</textarea>");
    out.println("</td>");
    out.println("</tr>");
    out.println("</table>");
    
    if(solution.getFilename()!=null)
    {
        out.println("<a href=\"getBlob?solutionNo=" + solutionNo + "\">Get Attachment</a><br/><br/>");
    }
    
    out.println("<input type=submit value=\"Update Solution\">");
    out.println("</form>");
    out.println("<a href=\"searchSolution.jsp\">Back to Search page</a>");
    out.println("<BR><BR><a href=\"index.jsp\">HOME PAGE</a>");
    out.println("</body></html>");
    out.close();
  }
}