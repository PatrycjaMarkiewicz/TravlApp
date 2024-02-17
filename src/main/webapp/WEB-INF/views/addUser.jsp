<%--
  Created by IntelliJ IDEA.
  User: patrycja
  Date: 14.02.2024
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add user</title>
</head>
<body>
<h1>Create user</h1>
<form:form modelAttribute="user" method="post">
    <div><label for="username">User Name: <form:input path="username" id="username"></form:input> </label> </div>
    <div><label for="password">Password:<form:password path="password" id="password"></form:password> </label> </div>
  <div><input type="submit" value="Create"/></div>
</form:form>
</body>
</html>
