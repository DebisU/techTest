<%@ page import="org.caamano.domain.authentication.UserSession" %>
<%
    UserSession currentSession = (UserSession) request.getSession().getAttribute("userSession");
    if (currentSession != null) {
%>
        <p>You are logged as "<%= currentSession.username() %>"</p>
<%
    }
%>
<p></p>
<ul>
    <li><a href="login">Login</a></li>
    <li><a href="logout">Logout</a></li>
    <li><a href="page?page=page1">Page 1</a></li>
    <li><a href="page?page=page2">Page 2</a></li>
    <li><a href="page?page=page3">Page 3</a></li>
</ul>
