
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create user</title>
    <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/5.3.0/css/bootstrap.min.css" />">
</head>
<body>
<div class="container mt-5">
    <h1>Create user</h1>
    <form:form modelAttribute="user" method="post" class="mt-4">
        <div class="mb-3">
            <label for="username" class="form-label">User Name:</label>
            <form:input path="username" id="username" class="form-control"/>
            <div class="text-danger">
                <form:errors path="username"/>
            </div>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password:</label>
            <form:password path="password" id="password" class="form-control"/>
            <div class="text-danger">
                <form:errors path="password"/>
            </div>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email:</label>
            <form:input path="email" id="email" class="form-control"/>
            <div class="text-danger">
                <form:errors path="email"/>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Create</button>
    </form:form>
</div>
<script src="<c:url value="/webjars/bootstrap/5.3.0/js/bootstrap.bundle.min.js" />"></script>
</body>
</html>
