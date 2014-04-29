<%-- 
    Document   : login
    Created on : 22.07.2013, 12:53:51
    Author     : Maksym
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Please register</title>
    </head>
    <body BGCOLOR="#DDF5E6" TEXT="#000000" LINK="#0000EE"
      VLINK="#551A8B" ALINK="#FF0000">
        <h3 align="center">Please register</h3>
        <hr/>
        <form align="center" name="registerForm" method="POST" action="controller">
            <input type="hidden" name="command" value="login">
            Name:<br/>
            <input type="text" name="name" value=""><br/>
            Password:<br/>
            <input type="password" name="password" value=""><br/>
            <input type="submit" value="Register">
        </form>
        <hr/>
        <a href="controller">Return to login</a>            
    </body>
</html>