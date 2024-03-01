
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Start travel</title>
    <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/5.3.0/css/bootstrap.min.css" />">
</head>
<body>
<div class="container mt-5">
    <h1>Travel</h1>
    <form:form method="post" modelAttribute="travel" class="mt-4">
        <div class="mb-3">
            <label for="title" class="form-label">Title of your travel:</label>
            <form:input path="title" id="title" class="form-control"/>
            <div class="text-danger">
                <form:errors path="title"/>
            </div>
        </div>
        <div class="mb-3">
            <label for="originAddress" class="form-label">Origin:</label>
            <form:select path="originAddress" items="${user.places}" itemValue="address" itemLabel="locationName" id="originAddress" class="form-select">
                <option value="">Select origin address</option>
            </form:select>
        </div>
        <div class="mb-3">
            <label for="destinationAddress" class="form-label">Destination:</label>
            <form:select path="destinationAddress" items="${user.places}" itemValue="address" itemLabel="locationName" id="destinationAddress" class="form-select">
                <option value="">Select destination address</option>
            </form:select>
        </div>
        <div class="mb-3">
            <label for="vehicleSpeed" class="form-label">Vehicle:</label>
            <form:select path="vehicleSpeed" items="${user.vehicles}" itemValue="avgSpeed" itemLabel="name" id="vehicleSpeed" class="form-select">
                <option value="">Select vehicle speed</option>
            </form:select>
        </div>
        <div class="mb-3">
            <label for="plannedDate" class="form-label">Planned Date:</label>
            <form:input type="date" path="plannedDate" id="plannedDate" class="form-control"/>
            <div class="text-danger">
                <form:errors path="plannedDate"/>
            </div>
        </div>
        <div id="map"></div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form:form>
</div>
<script src="<c:url value="/webjars/bootstrap/5.3.0/js/bootstrap.bundle.min.js" />"></script>
</body>
</html>
