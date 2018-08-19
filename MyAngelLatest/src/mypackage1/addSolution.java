package mypackage1;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.io.IOException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class addSolution extends HttpServlet 
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
          return;
    }
    response.setContentType(CONTENT_TYPE);
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<head><title>Angel -- Developed by Yogesh Gandhi</title></head>");
    out.println("<body>");
    Solution solutionObj = new Solution();
    
    Resource r = new ClassPathResource("applicationContext.xml");
    BeanFactory factory = new XmlBeanFactory(r);
    SolutionDAO sdao = (SolutionDAO)factory.getBean("solutiondao");
    SolutionDAO obj = (SolutionDAO) factory.getBean("maxSolutionCount");
    solutionObj.setSolutionno(obj.getMaxSolutionNo());  
    solutionObj.setAttachmentUploadLocation(obj.getAttachmentUploadLocation());
    populateData(solutionObj, request);    
    sdao.save(solutionObj);
    
    out.println("Thanks. Your solution added successfully");
    out.println("<BR>Please keep updating your solutions as you know more about the problem.");
    out.println("<BR><BR><a href=\"searchSolution.jsp\">Search page</a>");          
    out.println("<BR><BR><a href=\"index.jsp\">HOME PAGE</a>");
    out.println("</body></html>");
    out.close();
  }
  public void populateData(Solution solution, HttpServletRequest request)
  {
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");  
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try 
        {
			List fields = upload.parseRequest(request);
			Iterator it = fields.iterator();
			if (!it.hasNext()) {
				return;
			}
			while (it.hasNext()) 
            {
				FileItem fileItem = (FileItem)it.next();
				boolean isFormField = fileItem.isFormField();
				if (isFormField) 
                {
                    if(fileItem.getFieldName().equals("desc"))
                    {
                          solution.setDescription(fileItem.getString());
                    }
                    else if(fileItem.getFieldName().equals("problem"))
                    {
                          solution.setProblem(fileItem.getString());
                    }
                    else if(fileItem.getFieldName().equals("solution"))
                    {
                          solution.setSolution(fileItem.getString());
                    }
                    else if(fileItem.getFieldName().equals("logs"))
                    {
                          solution.setLogs(fileItem.getString());
                    }
                    else
                    {
                          solution.setSubmittedby(username);
                    }

				} 
                else 
                {
                    // Special handling for Blob case.
//                    if(fileItem.getSize()>3*1024*1024)
//                    {
//                        // if the size of file is greater than 3 MB.
//                        fi=null;
//                    }
                    String filePath = fileItem.getName();
                    String fileName = null;
                    if(filePath!=null)
                    {
                        int indexOfLastSlash = filePath.lastIndexOf("\\");
                        fileName = filePath.substring(indexOfLastSlash+1);
                        solution.setFilename(fileName);
                        String attachmentUploadLocation = solution.getAttachmentUploadLocation();
                        // This solution no. has been set before calling this method.
                        attachmentUploadLocation = attachmentUploadLocation + "\\" + (solution.getSolutionno());
                        File dir = new File(attachmentUploadLocation);
                        if(!dir.exists())
                        {
                            dir.mkdirs();
                        }
                        FileInputStream fi = (FileInputStream)fileItem.getInputStream();
                        FileOutputStream fos = new FileOutputStream(new File(attachmentUploadLocation + "\\" + fileName));
                        int ch=0;
                        while((ch=fi.read())!=-1)
                        {
                            fos.write(ch);
                        }
                        if(fos!=null)
                        {
                            fos.close();
                            fi.close();
                        }
                    }
                    else
                    {
                          solution.setFilename("");
                    }
				}
			}
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

  }
}