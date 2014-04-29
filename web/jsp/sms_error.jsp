<%-- 
    Document   : sms_error
    Created on : Apr 25, 2014, 9:27:26 PM
    Author     : MAX
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>SMS Error</title>
    </head>
    <body BGCOLOR="#FDF5E6" TEXT="#000000" LINK="#0000EE"
          VLINK="#551A8B" ALINK="#FF0000">
        <h3>Error</h3>
        <hr/>
            <jsp:expression>(request.getSession().getAttribute("error") != null) 
                    ? (String) request.getSession().getAttribute("error") : "unknown error"</jsp:expression>
            <hr/>
        <a href="/TelephoneStation2/jsp/user.jsp">Return to main menu</a>
    </body>
</html>
