<%@ page contentType="text/html;charset=windows-1252"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
    <title>Login Page</title>
    <script language="javascript">
        window.defaultStatus = "Developed by Yogesh Gandhi";
    </script>
    <script type="text/javascript" src="jquery.js"></script>
    <script type="text/javascript" src="jquery.md5.js"></script>
    <script type="text/javascript">
        function encrypt()
        {
            // Get the password
            var password = $('#password').val();
            // md5 encrypt the password.
            $('#password').val($.md5(password));
            document.forms[0].submit();
        }
    </script>
  </head>
  <body bgcolor="Teal">
    <img src="Angel.jpg"/>
      <form action="login" method="POST">
            <table>
            <tr>
              <td>UserName :</td><td> <input type="text" name="username"/></td>
            </tr>
            <tr>
              <td>Password :</td><td> <input type="password" name="password" id="password"/></td>
            </tr>
            <tr>
              <td><input type="button" onclick="encrypt();" value="submit"/></td>
            </tr>
            </table>
      </form>
      <BR><BR>
      <a href="searchSolution.jsp">Search solutions</a>
  </body>
</html>
