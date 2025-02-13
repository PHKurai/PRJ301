<%-- 
    Document   : output
    Created on : Feb 10, 2025, 2:12:10 PM
    Author     : phucl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            int n = (int) request.getAttribute("n");
            %>
                <h3> Bang cuu chuong <%=n%> </h3> </br> 
            <%
            for (int i = 1; i <= 10; i++) {
                %>
                    <%=n%> * <%=i%> = <%=i*n%> </br>
                <%
            }
        %>
    </body>
</html>
