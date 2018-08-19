/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mypackage1;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
/**
 *
 * @author yogesh.gandhi
 */
public class SolutionDAO {
    private NamedParameterJdbcTemplate template;
    private JdbcTemplate jdbcTemplate;
    private String attachmentUploadLocation;

    public String getAttachmentUploadLocation() {
        return attachmentUploadLocation;
    }

    public void setAttachmentUploadLocation(String attachmentUploadLocation) {
        this.attachmentUploadLocation = attachmentUploadLocation;
    }
    
    public SolutionDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public SolutionDAO(JdbcTemplate jdbcTemplate, String attachmentUploadLocation) {
        this.jdbcTemplate = jdbcTemplate;
        this.attachmentUploadLocation=attachmentUploadLocation;
    }    
    public SolutionDAO(NamedParameterJdbcTemplate npjt) {
        this.template = npjt;
    }
    private static SolutionDAO sdao;
    public static SolutionDAO getjdbcsolutiondao()
    {
        if(sdao==null)
        {
            Resource r = new ClassPathResource("applicationContext.xml");
            BeanFactory factory = new XmlBeanFactory(r);
            SolutionDAO sd = (SolutionDAO)factory.getBean("jdbcsolutiondao");     
            return sd;
        }
        else
        {
            return sdao;
        }
    }
    SolutionDAO() {
    }
    public int getMaxSolutionNo()
    {
        String query = "select max(solutionno) maxCount from angel";
        Integer number = jdbcTemplate.queryForObject(query, Integer.class);
        return number+1;
    }
    public void save(Solution s)
    {
        String query = "insert into angel(solutionno,description,problem,solution,logs,submittedby,filename)";
        query = query.concat("values(:solutionno,:description,:problem,:solution,:logs,:submittedby,:filename)");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("solutionno", s.getSolutionno());
        map.put("description", s.getDescription());
        map.put("problem", s.getProblem());
        map.put("solution", s.getSolution());
        map.put("logs", s.getLogs());
        map.put("submittedby", s.getSubmittedby());
        map.put("filename", s.getFilename());
        
        PreparedStatementCallback pscb = new PreparedStatementCallback()
        {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
            
        };
        template.execute(query, map, pscb);
    }
    public Object getSolution(int solutionno)
    {
        String query = "select description, problem, solution, logs, submittedby, filename from angel where solutionno=?";
        //In Spring 2.5, comes with a handy RowMapper implementation called ‘BeanPropertyRowMapper’, 
        // which can maps a row’s column value to a property by matching their names. Just make sure 
        // both the property and column has the same name, e.g property ‘custId’ will match to column 
        // name ‘CUSTID’ or with underscores ‘CUST_ID’.        
        return jdbcTemplate.queryForObject(query, new Object[] { solutionno }, new BeanPropertyRowMapper(Solution.class));
    }
    public List<Solution> getSolutions(String searchString)
    {
        StringBuilder whereClause = new StringBuilder(" where upper(description) like ?");
        whereClause.append(" OR upper(problem) like ?");
        whereClause.append(" OR upper(logs) like ? order by solutionNo");
        String searchQuery = "select * from angel  " + whereClause.toString();
        searchString = "%" + searchString + "%";
        List<Solution> o = jdbcTemplate.query(searchQuery, new Object[] {searchString, searchString, searchString},
                            new BeanPropertyRowMapper(Solution.class));
        return o;

    }     
    public int update(String description, String problem, String logs, String solution, int solutionno)
    {
        return jdbcTemplate.update(
            "update angel set description=?, problem=?, logs=?, solution=? where solutionNo=?",
            new Object[] {description, problem, logs, solution, solutionno}
        );        
    }
}
