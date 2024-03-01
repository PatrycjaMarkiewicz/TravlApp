
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Place</title>
    <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/5.3.0/css/bootstrap.min.css" />">
</head>
<body>

<div class="container mt-5">
    <h1>Place</h1>
    <form:form method="post" modelAttribute="place" class="mt-4">
        <div class="mb-3">
            <label for="locationName" class="form-label">Location Name:</label>
            <form:input path="locationName" id="locationName" class="form-control"/>
            <div class="text-danger">
                <form:errors path="locationName"/>
            </div>
        </div>
        <div class="mb-3">
            <label for="address" class="form-label">Address:</label>
            <form:input path="address" id="address" class="form-control"/>
            <div class="text-danger">
                <form:errors path="address"/>
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form:form>
</div>
<script src="<c:url value="/webjars/bootstrap/5.3.0/js/bootstrap.bundle.min.js" />"></script>
</body>
</html>
