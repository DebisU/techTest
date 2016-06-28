<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<%@ include file="menu.jsp" %>
<h1>Login page!!</h1>
<div>
    <p>Login with user1 with role PAG_1</p>
    <form action="login" method=POST id="user1">
        <input type="hidden" name="user" value="user1">
        <input type="submit" value="Login">
    </form>
</div>

<div>
    <p>Login with user2 with role PAG_2</p>
    <form action="login" method=POST id="user2">
        <input type="hidden" name="user" value="user2">
        <input type="submit" value="Login">
    </form>
</div>

<div>
    <p>Login with user3 with role PAG_3</p>
    <form action="login" method=POST id="user3">
        <input type="hidden" name="user" value="user3">
        <input type="submit" value="Login">
    </form>
</div>

</body>
</html>