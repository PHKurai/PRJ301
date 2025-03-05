<%-- 
    Document   : project_dashboard
    Created on : Mar 1, 2025, 9:57:46 PM
    Author     : phucl
--%>

<%@page import="utils.AuthUtils"%>
<%@page import="dto.StartupProjectDTO"%>
<%@page import="java.util.List"%>
<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project Dashboard</title>
    </head>
    <body>
        <%
            if (AuthUtils.isLoggedIn(session)) {
                UserDTO user = AuthUtils.getUser(session);
                boolean isFounder = AuthUtils.isAdmin(session);
        %>
        Welcome <b> <%=user.getName()%> </b><br/>

        <form action="MainController">
            <input type="hidden" name="action" value="logout"/>
            <input type="submit" value="Logout"/>
        </form>
        <br/><br/>
        
        <%
            if(isFounder) {
                %>
                <form action="MainController" method="post">
                    <input type="hidden" name="action" value="add"/>
                    <input type="submit" value="Add Project"/>
                </form>
                <br/>
                <%
            }

        
            String searchTerm = null;
            if (isFounder) {
                searchTerm = request.getAttribute("searchTerm") + "";
                searchTerm = searchTerm.equals("null") ? "" : searchTerm;

        %>
        <form action="MainController" method="get">
            <input type="hidden" name="action" value="search"/>
            Search Startup Project: <input type="text" name="searchTerm" value="<%=searchTerm%>"/>
            <input type="submit" value="Search"/>
        </form>
        <br/><br/>
        <%
            }

            if (request.getAttribute("startupProject") != null) {
                List<StartupProjectDTO> startupProjectList = (List<StartupProjectDTO>) request.getAttribute("startupProject");
        %>
        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Estimated Launch</th>
                        <%
                            if (isFounder) {
                        %>
                    <th>Update</th>
                        <%
                            }
                        %>
                </tr>
            </thead>
            <tbody>
                <%            for (StartupProjectDTO sp : startupProjectList) {
                %>
                <tr>
                    <td><%=sp.getName()%></td>
                    <td><%=sp.getDescription()%></td>
                    <td><%=sp.getStatus()%></td>
                    <td><%=sp.getEstimatedLaunch()%></td>

                    <%
                        if (isFounder) {
                    %>
                    <td>
                        <a href="MainController?action=update&id=<%=sp.getId()%>" >
                            <img src="assets/images/update-ic.png" />
                        </a>
                    </td>
                    <%
                        }
                    %>

                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <%
            }
        } else {
        %>
        You do not have permission to access this content.
        <form action="MainController">
            <input type="hidden" name="action" value="logout"/>
            <input type="submit" value="Login"/>
        </form>
        <%
            }
        %>
    </body>
</html>
