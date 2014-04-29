<%-- 
    Document   : user_id
    Created on : Apr 27, 2014, 12:20:22 PM
    Author     : MAX
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User found</title>
    </head>
    <body align="center" BGCOLOR="#EDF5E6" TEXT="#000000" LINK="#0000EE"
          VLINK="#551A8B" ALINK="#FF0000">
        <h1>User ${user.idUser} info</h1> 
        <hr/>
        <h3>User ${user.idUser} data:</h3>
        <table align="center" border="1">
            <th>User id</th>
            <th>User name</th>
            <th>User type</th>
            <th>User password</th>
            <th>Banned</th>
            <tr>
                <td>${user.idUser}</td>
                <td>${user.name}</td>
                <td>${user.userTypeId.typeName}</td>
                <td>${user.password}</td>
                <td>${user.banned}</td>
            </tr>
        </table>
        <hr/>
        <h3>User ${user.idUser} checks:</h3>
        <c:choose>
            <c:when test="${empty userCheckList}">
                User have no checks!
            </c:when>
            <c:otherwise>
                <table align="center" border="1">
                    <th>Check id</th>
                    <th>Service name</th>
                    <th>Price</th>
                    <th>Expired</th>
                    <th>Paid</th>
                    <th>Creation Date</th>
                    <th>Expire Date</th>
                        <c:forEach var="item" items="${userCheckList}">
                        <tr>
                            <td>${item.checkId}</td>
                            <td>${item.serviceId.serviceTypeId.typeName}</td>
                            <td>${item.price}</td>
                            <td>${item.expired}</td>
                            <td>${item.paid}</td>
                            <td>${item.creationDate}</td>
                            <td>${item.expireDate}</td>
                        </tr>
                    </c:forEach>
                </table>    
            </c:otherwise>
        </c:choose>
        <hr/>
        <table align="center">
            <tr>
                <td>
                    <form align="center" name="banForm" method="POST" action="controller">
                        <input type="hidden" name="idUser" value="${user.idUser}">
                        <input type="hidden" name="command" value="ban">
                        <input type="submit" value="Ban user">
                    </form>
                </td>
                <td>
                    <form align="center" name="unbanForm" method="POST" action="controller">
                        <input type="hidden" name="idUser" value="${user.idUser}">
                        <input type="hidden" name="command" value="unban">
                        <input type="submit" value="Unban user">
                    </form>
                </td>
            </tr>
        </table>
        <!--        <h3>User services:</h3>-->
    </body>
</html>
