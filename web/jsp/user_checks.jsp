<%-- 
    Document   : user_checks
    Created on : Apr 26, 2014, 6:13:42 PM
    Author     : MAX
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your checks</title>
    </head>
    <body align="center" BGCOLOR="#DDF5E6" TEXT="#000000" LINK="#0000EE"
           VLINK="#551A8B" ALINK="#FF0000">
        <h2 align="center">Your checks</h2>
        <table align="center" border="1">
            <th>Select</th>
            <th>Check id</th>
            <th>Service</th>
            <th>Price</th>
            <th>Expired</th>
            <th>Paid</th>
            <th>Creation Date</th>
            <th>Expire Date</th>
            <form align="center" name="payForm" method="POST" action="controller">
                <c:forEach var="item" items="${checkList}">
                    <tr>
                        <td><input type="checkbox" name="checkid" value="${item.checkId}"></td>
                        <td>${item.checkId}</td>
                        <td>${item.serviceId.serviceTypeId.typeName}</td>
                        <td>${item.price}</td>
                        <td>${item.expired}</td>
                        <td>${item.paid}</td>
                        <td>${item.creationDate}</td>
                        <td>${item.expireDate}</td
                    </tr>
                </c:forEach>
        </table>
        <input type="hidden" name="command" value="pay_selected_checks">
        <input type="submit" value="Pay"><br/>    
<!--        <p aline="center">
            <a onclick="history.back();">Return to main menu</a>
        </p>-->
    </body>
</html>
