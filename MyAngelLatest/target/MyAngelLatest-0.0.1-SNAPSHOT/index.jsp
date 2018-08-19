<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=windows-1252"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
    <title>Angel -- Developed by Yogesh Gandhi</title>
  </head>
  <body  bgcolor="Teal">
  
  <STYLE>
<!--
  tr { background-color: "Teal"}
  .initial { background-color: "Teal";  color:#fffff }
  .normal { background-color: "Teal";color:#ffffff }
  .highlight { background-color: #8888FF ;color:#ffffff}
//-->
</style>
<%-- set the username by reading session --%>
<c:set var="username" value="${sessionScope.username}"/>
<%-- if username is null --%>
<c:if test="${username eq null}">
    <c:redirect url="login.jsp"/>
</c:if>

<h1>Welcome : <c:out value="${username}" /></h1><br>
<table>
<tr onMouseOver="this.className='highlight'" onMouseOut="this.className='normal'">
    <td>
      <a href="AddSolution.jsp">Add Solution</a><BR>
    </td>
</tr>
<tr onMouseOver="this.className='highlight'" onMouseOut="this.className='normal'">
    <td>
      <a href="searchSolution.jsp">View/Update solution</a>
    </td>
</tr>
<%-- if the username is yogesh.gandhi" --%>
<c:if test="${username=='yogesh.gandhi'}">
    <tr onMouseOver="this.className='highlight'" onMouseOut="this.className='normal'">
        <td>
          <a href="AddUser.jsp">Add User</a>
        </td>
    </tr>
</c:if>



<tr onMouseOver="this.className='highlight'" onMouseOut="this.className='normal'">
    <td>
      <a href="logout.jsp">Logout</a>
    </td>
</tr>
</table>

  </body>
</html>
