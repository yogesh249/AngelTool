package mypackage1;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class getBlob extends HttpServlet 
{
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    
//        InputStream in = getBlob.class.getClassLoader().getResourceAsStream("db.properties");
//        Properties pro = new Properties();
//        pro.load(in);
//        String attachmentUploadLocation = pro.getProperty("AttachmentUploadLocation");
        Resource r = new ClassPathResource("applicationContext.xml");
        BeanFactory factory = new XmlBeanFactory(r);
        SolutionDAO sdao = (SolutionDAO)factory.getBean("solutiondao");        
        String attachmentUploadLocation = sdao.getAttachmentUploadLocation();
        if(attachmentUploadLocation==null)
        {
            response.getWriter().println(attachmentUploadLocation);
            response.getWriter().flush();
            return;
        }
        else
        {
            response.getWriter().println(attachmentUploadLocation);
        }
        String solutionNo = request.getParameter("solutionNo");
        attachmentUploadLocation = attachmentUploadLocation + "\\" + solutionNo;
        File targetDir = new File(attachmentUploadLocation);
        File[] files = targetDir.listFiles();
        // Assuming that there will be only 1 file in this directory.
        File targetFile = files[0];
        InputStream bodyOut = new FileInputStream(targetFile);
        response.setHeader("Content-Disposition: ", "attachment; filename=" + targetFile.getName());
        int ch=0;
        ServletOutputStream sos = response.getOutputStream();
        while((ch=bodyOut.read())!=-1)
        {
            sos.write(ch);
        }
        sos.flush();
        sos.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}