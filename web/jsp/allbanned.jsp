<%-- 
    Document   : allbanned
    Created on : Apr 28, 2014, 3:24:32 AM
    Author     : MAX
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/countBannedTag.tld" prefix="cbt" %>

<!DOCTYPE html>
<html>
    <head>
        <title>All banned</title>
    </head>
    <body align="center" BGCOLOR="#EDF5E6" TEXT="#000000" LINK="#0000EE"
          VLINK="#551A8B" ALINK="#FF0000">
        <h3 align="center">All banned users list </h3>
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
            <form name="unbanForm" method="POST" action="controller">
                <c:forEach var="item" items="${bannesList}">
                    <tr>
                        <td><input type="checkbox" name="idUser" value="${item.idUser}"/></td>
                        <td>${item.idUser}</td>
                        <td>${item.userTypeId.typeName}</td>
                        <td>${item.name}</td>
                        <td>${item.password}</td>
                        <td>${item.banned}</td>

<!--<td>"${item.loggedin}"</td-->
                    </tr>
                </c:forEach>
            </table>
                <input type="hidden" name="command" value="unban">
                <input type="submit" value="UnBan selected users">   
            </form>
                <p>
                    <cbt:countbanned/> 
                </p>    
    </body>
</html>
