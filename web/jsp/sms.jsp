<%-- 
    Document   : sms
    Created on : Apr 25, 2014, 9:03:46 PM
    Author     : MAX
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
          <title>SMS sent</title>
    </head>
    <body BGCOLOR="#DDF5E6" TEXT="#000000" LINK="#0000EE"
      VLINK="#551A8B" ALINK="#FF0000">
        <h3 align="center">SMS was successfully sent to 
            <jsp:expression>request.getSession().getAttribute("recipient")</jsp:expression></h3>
        <hr/>
        <hr>
        <a href="/TelephoneStation2/jsp/user.jsp">Return to main menu</a>
    </body>
</html>
