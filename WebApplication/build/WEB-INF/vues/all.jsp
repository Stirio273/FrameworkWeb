<%@ page import="modele.RouteNational,java.util.*" %>
<%
List<RouteNational> liste = (List<RouteNational>) request.getAttribute("listeRN");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>All</h1>
        <ul>
            <% for(int i = 0; i < liste.size(); i++) { %>
                <li><% out.println(liste.get(i).getNom()); %></li>
            <% } %>
        </ul>
    </body>
</html>
