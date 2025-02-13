<%-- 
    Document   : example03
    Created on : Feb 10, 2025, 1:26:50 PM
    Author     : phucl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>If - else</title>
    </head>
    <body>
        <%! int a = 100; %>
        
        a = <%= a %> is 
        <%
            if (a%2 == 0) {
                %>
                even
                <%
            } else {
                %>
                odd
                <%
            }
        %>
    </body>
</html>
