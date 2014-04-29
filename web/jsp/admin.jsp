<%-- 
    Document   : admin
    Created on : Apr 24, 2014, 1:32:28 AM
    Author     : MAX
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Admin page</title>
    </head>
    <body BGCOLOR="#EDF5E6" TEXT="#000000" LINK="#0000EE"
          VLINK="#551A8B" ALINK="#FF0000">
        <h3 align="center">Hello   
            <c:out value="${name}"/> 
            <hr/>
            <form align="center" name="adminForm" method="POST" action="controller">
                <input type="hidden" name="command" value="find_user">
                Enter user ID<br/>
                <input type="text" name="user_id" value=""><br/>
                <input type="submit" value="Find user by id"><br/>
            </form>

            <form align="center" name="adminForm" method="POST" action="controller">
                <input type="hidden" name="command" value="show_all_users">
                <input type="submit" value="Show all users">
            </form>
            <form align="center" name="adminForm" method="POST" action="controller">
                <input type="hidden" name="command" value="show_banned_users">
                <input type="submit" value="Show all banned">
            </form>
            <form align="center" name="adminForm" method="POST" action="controller">
                <input type="hidden" name="command" value="show_expired_users">
                <input type="submit" value="Show all users with expired checks">
            </form>
<!--            <hr/>
            <form align="center" name="adminForm" method="POST" action="controller">
                <input type="hidden" name="command" value="confirm_registration">
                <input type="submit" value="Show all not registered">
            </form>-->
    </body>
</html>
