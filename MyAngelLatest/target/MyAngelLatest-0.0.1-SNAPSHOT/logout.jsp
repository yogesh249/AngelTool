<%@ page contentType="text/html;charset=windows-1252"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
    <title>untitled</title>
  </head>
  <body  bgcolor="Teal">
      <img src="Angel.jpg"/><BR>
      <% session.invalidate(); 
          Cookie[] cookies = request.getCookies();
          if(cookies!=null)
          {
              for(int i=0;i<cookies.length;i++)
              {
                  if(cookies[i].getName().equals("username"))
                  {
                        cookies[i].setMaxAge(0);
                        response.addCookie(cookies[i]);
                  }
              }
          }      
      
      %>
      You have successfully logged out.
      <a href="login.jsp">Login Again</a>
  </body>
</html>
