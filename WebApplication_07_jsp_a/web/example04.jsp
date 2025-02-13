<%-- 
    Document   : example04
    Created on : Feb 10, 2025, 1:37:03 PM
    Author     : phucl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bảng cửu chương</title>
    </head>
    <body>
        <!-- In Bang Cuu Chuong -->
        <%
            for (int i = 2; i < 10; i++) {
                    %>
                    <h3> Bảng cửu chương <%=i%> </h3> </br>
                    <%
                for (int j = 1; j <= 10; j++) {
                    %>
                        <%=i%> * <%=j%> = <%=i*j%> </br>
                    <%
                }
                    %>
                        <hr/>
                    <%
            }
        %>
    </body>
</html>
