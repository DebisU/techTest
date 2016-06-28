<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Detail page</title>
</head>
<body>
<%@ include file="menu.jsp" %>
<h1>Hello <%= request.getAttribute("username") %>, you are on page <%= request.getAttribute("pageName") %></h1>
</body>
</html>