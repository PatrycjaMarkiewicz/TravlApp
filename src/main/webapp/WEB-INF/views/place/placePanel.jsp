
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${place.locationName}</title>
    <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/5.3.0/css/bootstrap.min.css" />">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        .spacer {
            margin-bottom: 30px;
        }
    </style>
</head>
<body>
<div class="container mt-5">

    <div class="spacer">
        <h1>
            ${place.locationName}
            <a href="http://localhost:8080/" class="btn btn-primary">Return</a>
        </h1>
    </div>
    <div class="spacer"><i class="bi bi-geo-alt" style="font-size: x-large;color: #3b0075"></i>Address : ${place.address}</div>
    <div class="spacer"><img src="${map}" alt="Google Map" /></div>
</div>
<script src="<c:url value="/webjars/bootstrap/5.3.0/js/bootstrap.bundle.min.js" />"></script>
</body>
</html>
