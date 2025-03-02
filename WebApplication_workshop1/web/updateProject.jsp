<%-- 
    Document   : updateProject
    Created on : Mar 1, 2025, 10:32:42 PM
    Author     : phucl
--%>

<%@page import="dto.StartupProjectDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Project</title>
    </head>
    <body>
        <%
            StartupProjectDTO sp = (StartupProjectDTO) request.getAttribute("project");
            String message = request.getAttribute("messageError") + "";
            boolean isSuccessful = (boolean) request.getAttribute("isSuccessful");
        %>
    </body>
    <strong>Update Project: </strong> <%=sp.getName()%> <br/>
    Old Status: <%=sp.getStatus()%>
    <form action="MainController" method="post">
        <input type="hidden" name="action" value="getUpdate"/>
        <input type="hidden" name="project" value="<%=sp.getId()%>"/>
        New Status: <input type="text" name="newStatus" ><br/>
        <input type="submit" value="Update"/>
        <p style="color: red;"><%=message.equals("null") ? "" : message%></p>
        <%
            if (isSuccessful) {
        %>
        <p style="color: blue;">Update Successfully. </p><a href="projectDashboard.jsp">Go to Project Dashboard</a>

        <%
            }
        %>
    </form>
</body>
</html>
