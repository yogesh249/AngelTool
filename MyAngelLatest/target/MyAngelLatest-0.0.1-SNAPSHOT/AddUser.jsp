<%@ page contentType="text/html;charset=windows-1252"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
    <title>Add User - Developed by Yogesh Gandhi.</title>
  </head>
  <body>
     <h1>Add User</h1>
     <form action="adduser" method="POST">
        Username : <input type="text" name="username" id="username" value=""/><BR>
        Password : <input type="password" id="password" name="password" value=""/><BR>
        <input type="button" onclick="encrypt()" value="Add User"/>
     </form>
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
  </body>
</html>
