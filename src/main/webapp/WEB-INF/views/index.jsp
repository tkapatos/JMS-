<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HelloWorld page</title>
</head>
<body>
    Greeting : ${greeting}
    <form method="POST" action="/jms/index">
   <table>
    <tr>
        <td><label for="message">Message</label>:</td>
        <td><input type="text" id="message" name="message"/></td>
    </tr>
    
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form>
</body>
</html>