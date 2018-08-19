<%@ page contentType="text/html;charset=windows-1252"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
    <title>Angel -- Developed by Yogesh Gandhi</title>
    <script language="javascript" type="text/javascript">
    function limitText(limitField, limitCount, limitNum) {
      if (limitField.value.length > limitNum) {
        alert("Maximum Limit is " + limitNum);
        limitField.value = limitField.value.substring(0, limitNum);
      } else {
        limitCount.value = limitNum - limitField.value.length;
      }
    }
    function validateForm()
    {
        var x=document.forms[0];
        var desc = x.desc.value;
        var problem = x.problem.value;
        var logs = x.logs.value;
        var solution=x.solution.value;
//        if(desc.indexOf("\'")!=-1)
//        {
//            alert("Description cannot contain single quote");
//            return false;
//        }
//        if(problem.indexOf("\'")!=-1)
//        {
//            alert("problem cannot contain single quote");
//            return false;
//        }
//        if(logs.indexOf("\'")!=-1)
//        {
//            alert("logs cannot contain single quote");
//            return false;
//        }
        /*if(solution.indexOf("\'")!=-1)
        {
            alert("solution cannot contain single quote");
            return false;
        }*/
        
    }    
    </script>    
  </head>
  <body  bgcolor="Teal">
        <form action="addSolution" method=post onsubmit="return validateForm()" enctype="multipart/form-data" >
              <table>
                  <tr>
                      <td>Description :</td>
                      <td><input type="text" name="desc" size=100></input></td>
                  </tr>
                  <tr>
                    <td>Characters Remaining </td>
                    <td span=2><input readonly type="text" name="countdownProblem" size="4" value="4000"></td>
                  </tr>
                  <tr>
                    <td>Detailed Problem : </td>
                    <td><textarea name="problem" rows=10 cols=100 
                    onKeyDown="limitText(this.form.problem,this.form.countdownProblem,4000);" 
                    onKeyUp="limitText(this.form.problem,this.form.countdownProblem,4000);"></textarea><td>
                  </tr>
                  <tr>
                    <td>Characters Remaining </td>
                    <td span=2><input readonly type="text" name="countdownLogs" size="5" value="40000"></td>
                  </tr>
                  <tr>
                    <td>Logs :</td>
                    <td><textarea name="logs" rows=10 cols=100                     
                    onKeyDown="limitText(this.form.logs,this.form.countdownLogs,40000);" 
                    onKeyUp="limitText(this.form.logs,this.form.countdownLogs,40000);"></textarea><td>

                  </tr>
                  <tr>
                    <td>Characters Remaining </td>
                    <td span=2><input readonly type="text" name="countdownSolution" size="4" value="4000"></td>
                  </tr>
                  <tr>
                    <td>Solution :</td>
                    <td><textarea name="solution" rows=15 cols=100                     
                    onKeyDown="limitText(this.form.solution,this.form.countdownSolution,4000);" 
                    onKeyUp="limitText(this.form.solution,this.form.countdownSolution,4000);"></textarea><td>
                  </tr>
                  <tr>
                    <td>File :</td>
                    <td><input type="file" size="100" name="uploadedfile">
                  </tr>
                  <input type="hidden" name="filename" value=""/>
                  <tr>
                    <td><input type="submit"></input></td>
                  </tr>
              </table>
        </form>
  </body>
</html>
