<%-- 
    Document   : error
    Created on : Apr 28, 2014, 9:31:53 PM
    Author     : MAX
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Error</title>
    </head>
    <body BGCOLOR="#FDF5E6" TEXT="#000000" LINK="#0000EE"
          VLINK="#551A8B" ALINK="#FF0000">
        <h3>Error</h3>
        <hr/>
        <c:choose>
        <c:when test="${not empty param.error}">
            ${error}
        </c:when>
        <c:otherwise>
            unknown error
        </c:otherwise>
        </c:choose>
            <hr/>
        <a href="controller">Return to login page</a>
    </body>
</html>
