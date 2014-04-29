<%-- 
    Document   : payConfirmation
    Created on : Apr 26, 2014, 11:09:53 PM
    Author     : MAX
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment Confirmation</title>
    </head>
    <body align="center" BGCOLOR="#DDF5E6" TEXT="#000000" LINK="#0000EE"
          VLINK="#551A8B" ALINK="#FF0000">
        <h2>You have paid ${price}</h2>
        <br/>
        <button type="button" name="back" onclick="history.back();">Go back</button>
    </body>
</html>
