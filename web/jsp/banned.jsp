<%-- 
    Document   : banned
    Created on : Apr 24, 2014, 1:43:54 AM
    Author     : MAX
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ban Confirmation</title>
    </head>
    <body align="center" BGCOLOR="#EDF5E6" TEXT="#000000" LINK="#0000EE"
          VLINK="#551A8B" ALINK="#FF0000">
        <c:if test="${not empty error}">
            <c:out value="${error}"/>
        </c:if>
        <c:if test="${empty error}"> 
            <c:if test="${not empty bannedUsers}">
                <c:forEach var="item" items="${bannedUsers}">
                    <c:out value="${item.idUser}"/>, 
                </c:forEach> banned.
            </c:if>
        </c:if>
        <c:if test="${not empty alreadyBanned}">
            <c:forEach var="item" items="${alreadyBanned}">
                <c:out value="${item.idUser}"/>, 
            </c:forEach> already banned.       
        </c:if>
        <c:if test="${not empty wrongUser}">
            <c:forEach var="item" items="${wrongUser}">
                <c:out value="${item.idUser}"/>, 
            </c:forEach> can't be banned.       
        </c:if>
        <br/>
        <button type="button" name="back" onclick="history.back();">Go back</button>
    </body>
</html>
