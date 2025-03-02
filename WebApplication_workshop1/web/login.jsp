<%-- 
    Document   : login
    Created on : Mar 1, 2025, 9:25:05 PM
    Author     : phucl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <%
            String message = request.getAttribute("messageError") + "";
        %>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="login" />
            Username: <input type="text" name="username" /><br/>
            Password: <input type="password" name="password" /><br/>
            <br/>
            <button type="submit">Login</button>
            <p style="color: red;"><%=message.equals("null") ? "" : message%></p>
        </form>
    </body>
</html>
