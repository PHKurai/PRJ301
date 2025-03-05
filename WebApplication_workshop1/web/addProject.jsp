<%-- 
    Document   : addProject
    Created on : Mar 1, 2025, 11:45:00 PM
    Author     : phucl
--%>

<%@page import="utils.AuthUtils"%>
<%@page import="dto.StartupProjectDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Project</title>
    </head>
    <body>
        <%
            if (AuthUtils.isAdmin(session)) {
                StartupProjectDTO sp;
                try {
                    sp = (StartupProjectDTO) request.getAttribute("project");
                } catch (Exception e) {
                    sp = new StartupProjectDTO();
                }
                if (sp == null) {
                    sp = new StartupProjectDTO();
                }

                String name_error = request.getAttribute("name_error") + "";
                name_error = name_error.equals("null") ? "" : name_error;

                String status_error = request.getAttribute("status_error") + "";
                status_error = status_error.equals("null") ? "" : status_error;

                String date_error = request.getAttribute("date_error") + "";
                date_error = date_error.equals("null") ? "" : date_error;
        %>

        <form action="MainController" method="post">
            <input type="hidden" name="action" value="getAdd"/>

            Project Name: <input type="text" name="name" value="<%=sp.getName() == null ? "" : sp.getName()%>"/>
            <% if (!name_error.isEmpty()) {%>
            <div class="error-message" style="color: red"><%=name_error%></div>
            <% }%>
            <br/>
            Description: <input type="text" name="description" value="<%=sp.getDescription() == null ? "" : sp.getDescription()%>"/>
            <br/>
            Status: <input type="text" name="status" value="<%=sp.getStatus() == null ? "" : sp.getStatus()%>"/>
            <% if (!status_error.isEmpty()) {%>
            <div class="error-message" style="color: red"><%=status_error%></div>
            <% }%>
            <br/>
            Launch Date: <input type="text" name="date" value="<%=sp.getEstimatedLaunch() == null ? "" : sp.getEstimatedLaunch()%>"/>
            <% if (!date_error.isEmpty()) {%>
            <div class="error-message" style="color: red"><%=date_error%></div>
            <% }%>
            <br/>
            <input type="submit" value="Create" />
        </form>
        <%} else {
        %>You do not have permission to access this content.<%
            }

        %>
    </body>
</html>
