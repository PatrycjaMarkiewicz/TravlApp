
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${travel.title}</title>
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
        <h1>${travel.title}
            <a href="http://localhost:8080/" class="btn btn-primary">
                Return
            </a>
        </h1>
    </div>
    <div class="spacer" style="color: #3b0075; font-size:large">
        The day before your trip you will receive an e-mail remainder.
        <i class="bi bi-envelope-exclamation" style="font-size: x-large"></i>
    </div>
<div class="spacer">
    Origin Address: ${travel.originAddress}
</div>
<div class="spacer">
    Destination Address: ${travel.destinationAddress}
</div>
<div class="spacer">
    Planned Date: ${travel.plannedDate}
</div>
<div class="spacer">
    Distance: ${travel.distance} km
</div>
<div class="spacer">
    Travel Time: ${travel.hours}h ${travel.minutes}min
</div>
    <div class="spacer">Take with you: ${travel.waterInLiters} liters of water</div>
    <c:forEach var="i" begin="1" end="${travel.waterInLiters}">
        <i class="bi bi-droplet-fill spacer" style="font-size: xxx-large;color: blue"></i>
    </c:forEach>
    <div style="font-size: x-large">${travel.originAddress}</div>
    <div class="spacer"><img src="${map2}" alt="Google Map" /></div>

    <div style="font-size: x-large">${travel.destinationAddress}</div>
    <div class="spacer"><img src="${map1}" alt="Google Map" /></div>

</div>

</body>
</html>
