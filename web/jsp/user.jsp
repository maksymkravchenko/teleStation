<%-- 
    Document   : user
    Created on : Apr 24, 2014, 1:32:20 AM
    Author     : MAX
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <title>User page</title>
    </head>
    <body BGCOLOR="#DDF5E6" TEXT="#000000" LINK="#0000EE"
          VLINK="#551A8B" ALINK="#FF0000">
        <h3 align="center">Hello 
            <c:out value="${name}"/> 
        <hr/>
        <table align="center">
            <td>
                <form align="center" name="userForm" method="POST" action="controller">
                <input type="hidden" name="command" value="show_all_checks">
                <input type="submit" value="Show all checks"><br/>
            </form>
<!--            <form align="center" name="userForm" method="POST" action="controller">
                <input type="hidden" name="command" value="pay">
                <input type="submit" value="Pay all checks"><br/>
            </form>-->
                
<!--            <td align="center">
                Enter phone number:<br/>
                <form align="center" name="userForm" method="POST" action="controller">
                    <input type="text" name="number" value=""><br/>
                </form>
                        <form align="center" name="userForm" method="POST" action="controller">
                            <input type="hidden" name="command" value="call">
                            <input type="submit" value="Call"><br/>
                        </form
                    <td>
                        <form align="center" name="userForm" method="POST" action="controller">
                            <input type="hidden" name="command" value="sms">
                            Enter text:<br/>
                            <input type="text" name="sms" value=""><br/>
                            <input type="submit" value="Send SMS"><br/>
                        </form>
                    </td>-->
        </table>
    </body>
</html>
