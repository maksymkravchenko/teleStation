<%-- 
    Document   : login
    Created on : 22.07.2013, 12:53:51
    Author     : Maksym
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <h3 align="center">Login</h3>
        <hr/>
        <form align="center" name="loginForm" method="POST" action="controller">
            <input type="hidden" name="command" value="login">
            Login:<br/>
            <input type="text" name="login" value=""><br/>
            Password:<br/>
            <input type="password" name="password" value=""><br/>
            <input type="submit" value="Login">               
        </form>
        <hr/>
        <!--<a href="/TelephoneStation2/jsp/register.jsp">Register</a>--> 
    </body>
</html>
