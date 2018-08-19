//package mypackage1;
//import java.io.InputStream;
//import java.sql.Connection;
//import java.sql.DriverManager;
//
//import java.util.Properties;
//
//
//public class DBConnection
//{
//  private static javax.sql.DataSource dataSource = null;
//  private static Connection conn = null;
//  private static String jndiName = "myAngelJNDI";
//  static
//  {
//        try
//        {
//            Class.forName ("oracle.jdbc.OracleDriver");
//            Properties pro = new Properties();
//            InputStream in = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");
//            pro.load(in);
//            String url = pro.getProperty("url");
//            String username = pro.getProperty("username");
//            String password = pro.getProperty("password"); 
//            if(conn==null || conn.isClosed())
//            {
//                conn = DriverManager.getConnection(url, username, password);
//            }
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();;
//        }        
//  }
//  
//	/* To get Connection thru JNDI */
//	public static java.sql.Connection getConn() throws Exception
//	{
//		if(conn!=null && !conn.isClosed())
//        {
//            return conn;
//        }
//        if((conn!=null && conn.isClosed()) || conn==null)
//        {
//            conn=null;
//            try
//            {
//                Class.forName ("oracle.jdbc.OracleDriver");
//                Properties pro = new Properties();
//                InputStream in = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");
//                pro.load(in);
//                String url = pro.getProperty("url");
//                String username = pro.getProperty("username");
//                String password = pro.getProperty("password"); 
//                if(conn==null || conn.isClosed())
//                {
//                    conn = DriverManager.getConnection(url, username, password);
//                }
//                return conn;
//            }
//            catch(Exception e)
//            {
//                e.printStackTrace();;
//            }        
//        }
//        return null;
//	}
//  
//  public void finalize()
//  {
//      if(conn!=null)
//      {
//          try
//          {
//            conn.close();
//          }
//          catch(Exception e)
//          {
//              e.printStackTrace();
//          }
//          
//      }
//  }
//} 