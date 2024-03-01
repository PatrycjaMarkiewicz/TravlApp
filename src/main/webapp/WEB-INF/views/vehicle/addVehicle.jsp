
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vehicle</title>
    <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/5.3.0/css/bootstrap.min.css" />">
</head>
<body>
<%--<form:form method="post" modelAttribute="vehicle">--%>
<%--    <div><label for="name">Name<form:input path="name" id="name"></form:input> </label> </div>--%>
<%--    <div><label for="avgSpeed">avgSpeed<form:input path="avgSpeed" id="avgSpeed" type="number"></form:input> </label> </div>--%>
<%--    <input type="submit">--%>
<%--</form:form>--%>
<div class="container mt-5">
    <h1>Vehicle</h1>
    <form:form method="post" modelAttribute="vehicle" class="mt-4">
        <div class="mb-3">
            <label for="name" class="form-label">Name:</label>
            <form:input path="name" id="name" class="form-control"/>
            <div class="text-danger">
                <form:errors path="name"/>
            </div>
        </div>
        <div class="mb-3">
            <label for="avgSpeed" class="form-label">Average Speed:</label>
            <form:input path="avgSpeed" id="avgSpeed" type="number" class="form-control"/>
            <div class="text-danger">
                <form:errors path="avgSpeed"/>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form:form>
</div>
<script src="<c:url value="/webjars/bootstrap/5.3.0/js/bootstrap.bundle.min.js" />"></script>
</body>
</html>
