<%-- 
    Document   : expired
    Created on : Apr 25, 2014, 7:13:24 PM
    Author     : MAX
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <title>All users</title>
    </head>
    <body align="center" BGCOLOR="#EDF5E6" TEXT="#000000" LINK="#0000EE"
          VLINK="#551A8B" ALINK="#FF0000">
        <h3 >All users with expired checks list</h3>
        <hr/>

        <table align="center" border="1">
            <th>Select</th>
            <th>Phone number</th>
            <th>User type</th>
            <th>User name</th>
            <th>User password</th>
            <th>Banned</th>
            <!--not done yet-->
            <!--<th>Logged-in</th>-->
            <form name="banForm" method="POST" action="controller">
                <c:forEach var="item" items="${expiredUsersList}">
                    <tr>
                        <td><input type="checkbox" name="idUser" value="${item.idUser}"/></td>
                        <td>${item.idUser}</td>
                        <td>${item.userTypeId.typeName}</td>
                        <td>${item.name}</td>
                        <td>${item.password}</td>
                        <td>${item.banned}</td>
                    </tr>
                </c:forEach>
        </table>
        <input type="hidden" name="command" value="ban">
        <input type="submit" value="Ban selected users">   
    </form>
</body>
</html>
