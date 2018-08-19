package mypackage1;
import java.util.Enumeration;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class searchServlet extends HttpServlet 
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
//    if(username==null)
//    {
//          response.sendRedirect("login.jsp");
//    }  
    response.setContentType(CONTENT_TYPE);
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html><HTML>");
    out.println("<head><title>searchServlet</title></head>");
    out.println("<body>");
    out.println("<STYLE type=text/css>");
    out.println("tr.d0{");
    out.println("background-color: #F3F5BC; color: black;");
    out.println("}");
    out.println("tr.d1{");
    out.println("background-color: #FAFAF7; color: black;");
    out.println("}  ");  
//    out.println("a {text-decoration: none; color: black; }");
//    out.println("a:hover {background-color: #123456; color: white;}");
    out.println("tr:hover{");
    out.println("background-color: #123456; color: white;");
    out.println("}  ");      
    out.println("</STYLE>");    
    String searchString = request.getParameter("search");
//    StringBuffer whereClause = new StringBuffer(" where upper(description) like '%?%'");
//    whereClause.append(" OR upper(problem) like '%?%'");
//    whereClause.append(" OR upper(logs) like '%?%' order by solutionNo");
//    String searchQuery = "select solutionNo, description, submittedby from angel  " + whereClause.toString();
    
//    Connection conn=null;
//    PreparedStatement prepStmt=null;
//    ResultSet rs=null;
//    boolean status = false;
    try
    {
//      conn = DBConnection.getConn();
    //      prepStmt =  conn.prepareStatement(searchQuery);
    //      prepStmt.setString(1, "%"+searchString.toUpperCase()+"%");
    //      prepStmt.setString(2, "%"+searchString.toUpperCase()+"%");
    //      prepStmt.setString(3, "%"+searchString.toUpperCase()+"%");
    //      rs = prepStmt.executeQuery();
        Resource r = new ClassPathResource("applicationContext.xml");
        BeanFactory factory = new XmlBeanFactory(r);
        SolutionDAO sdao = (SolutionDAO)factory.getBean("jdbcsolutiondao");        
      List<Solution> solutions = sdao.getSolutions(searchString.toUpperCase());
      out.println("<table border=1>");
      int i=0;
      String strClass="";
      for(Solution s : solutions)
      {
          if(i%2==0)
              strClass="d0";
          else
              strClass="d1";
          out.println("<tr class=\""+strClass+"\">");
//          onMouseOver=\"this.className=\'highlight\'\" onMouseOut=\"this.className=\'normal\'\">");          
//          String desc = (String)rs.getString("description");          
          String desc = s.getDescription();

          out.println("\t<td width=5%>");
//          int solutionNo = rs.getInt("solutionNo");
          int solutionNo = s.getSolutionno();
          out.println(solutionNo);
          out.println("</td>");


          String targetPage = "viewSolution?solutionNo="+solutionNo;
          // <td width=70% onclick="javascript:window.open('viewSolution?solutionNo=3','_self');">
          out.println("\t<td width=70% onclick=\"javascript:window.open('"+targetPage+"','_self' );\"");
          out.println(" onmouseover=\"document.body.style.cursor='pointer';\"");
          out.println(" onmouseout= \"document.body.style.cursor='default';\">" );
          out.println(desc);
          out.println("\t</td>");
          if(username!=null && username.equalsIgnoreCase(s.getSubmittedby()))
          {              
              out.println("\t<td width=25% align=center>");
              out.println("<a href=\"editSolution?solutionNo="+solutionNo+"\">Edit</a>");
              out.println("\t</td>");
          }
          else
          {
              out.println("\t<td width=25% align=center>");
              out.println("Submitted by " + s.getSubmittedby());
              out.println("\t</td>");
          }
          out.println("</tr>");          
          i++;
      }
      if(i==0)
      {
          out.println("No records found");
      }

      out.println("</table>");
      
      out.println("<BR><BR><a href=\"searchSolution.jsp\">Back to Search page</a>");      
      out.println("<BR><BR><a href=\"index.jsp\">HOME PAGE</a>");      
    }
    catch(Exception e)
    {
        e.printStackTrace(out);
        out.close();
        //throw new ServletException(e);
    }
//    finally
//    {
//          
//          try
//          { 
//              if(rs!=null) rs.close();
//              if(prepStmt!=null) prepStmt.close();
//              if(conn!=null) conn.close();
//          }
//          catch(Exception e)
//          {
//              e.printStackTrace(out);              
//          }
//    }
    out.println("</body></html>");
    out.close();
  }
}