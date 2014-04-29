<%-- 
    Document   : pay
    Created on : Apr 26, 2014, 9:57:34 PM
    Author     : MAX
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pay form</title>
    </head>
    <body align="center" BGCOLOR="#DDF5E6" TEXT="#000000" LINK="#0000EE"
          VLINK="#551A8B" ALINK="#FF0000">
        <h2>Confirm payments</h2>
        <c:if test="${not empty checksToPay}">
            Pay checks id:
                <c:forEach var="item" items="${checksToPay}">
                    <c:out value="${item.checkId}"/>, 
                </c:forEach> 
                    for ${price}.
            </c:if>
        <br/>
        <c:if test="${not empty paidChecksIdList}">
                Check id <c:forEach var="item" items="${paidChecksIdList}">
                    <c:out value="${item}"/>, 
                </c:forEach> already paid.
            </c:if>
        <table align="center">
            <tr>
                <td>
                    <form align="center" name="payForm" method="POST" action="controller">
                        <input type="hidden" name="command" value="payConfirm">
                        <input type="submit" value="Pay"><br/>  
                </td>
                <td>
                    <button type="button" name="back" onclick="history.back();">Cancel</button>
<!--                    <form align="center" name="payForm" method="POST" action="controller">
                        <input type="hidden" name="command" value="cancel">
                        <input type="submit" value="Cancel"><br/>  -->
                </td>
            </tr>
        </table>
    </body>
</html>
