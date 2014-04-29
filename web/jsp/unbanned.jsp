<%-- 
    Document   : unban
    Created on : Apr 28, 2014, 2:00:18 AM
    Author     : MAX
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unban Confirmation</title>
    </head>
    <body align="center" BGCOLOR="#EDF5E6" TEXT="#000000" LINK="#0000EE"
          VLINK="#551A8B" ALINK="#FF0000">
        <c:if test="${not empty error}">
            <c:out value="${error}"/>
        </c:if>
        <c:if test="${empty error}">
            <c:if test="${not empty unBannedUsers}">
                <c:forEach var="item" items="${unBannedUsers}">
                    <c:out value="${item.idUser}"/>, 
                </c:forEach> unbanned.
            </c:if>
        </c:if>
        <c:if test="${not empty alreadyUnBanned}">
            <c:forEach var="item" items="${alreadyUnBanned}">
                <c:out value="${item.idUser}"/>, 
            </c:forEach> already unbanned.       
        </c:if>
        <c:if test="${not empty wrongUnBanUser}">
            <c:forEach var="item" items="${wrongUnBanUser}">
                <c:out value="${item.idUser}"/>, 
            </c:forEach> can't be unbanned.       
        </c:if>
        <br/>
        <button type="button" name="back" onclick="history.back();">Go back</button>
    </body>
</html>
