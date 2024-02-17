<%--
  Created by IntelliJ IDEA.
  User: patrycja
  Date: 14.02.2024
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Welcome to my app</h1>
<a>Sign in or</a>
<a href="/create-user"> sign up</a>
<form method="post">
  <div><label> User Name : <input type="text" name="username"/> </label></div>
  <div><label> Password: <input type="password" name="password"/> </label></div>
  <div><input type="submit" value="Sign In"/></div>
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>


</body>
</html>
