<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>User Panel</title>
  <link rel="stylesheet" href="<c:url value="/webjars/bootstrap/5.3.0/css/bootstrap.min.css" />">
</head>
<body>
<div class="container mt-5">
  <div><a>${error}</a></div>
  <div class="row">
    <div class="col">
      <h4>Your Travels</h4>
      <a href="startTravel" class="btn btn-dark mb-3">Start Travel</a>
      <table class="table table-bordered">
        <tbody>
        <c:forEach items="${travels}" var="travel">
          <tr>
            <td><a href="travelDetails/${travel.id}" class="btn btn-light">${travel.title}</a></td>
            <td><a href="deleteTravel/${travel.id}" class="btn btn-danger">Delete</a></td>
            <td><a href="editTravel/${travel.id}" class="btn btn-secondary">Edit</a></td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
    <div class="col">
      <h4>Your Vehicles</h4>
      <a href="addVehicle" class="btn btn-dark mb-3">Add</a>
      <table class="table table-bordered">
        <tbody>
        <c:forEach items="${vehicles}" var="vehicle">
          <tr>
            <td><a href="vehicleDetails/${vehicle.id}" class="btn btn-light">${vehicle.name}</a></td>
            <td><a href="deleteVehicle/${vehicle.id}" class="btn btn-danger">Delete</a></td>
            <td><a href="editVehicle/${vehicle.id}" class="btn btn-secondary">Edit</a></td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
    <div class="col">
      <h4>Your Places</h4>
      <a href="addPlace" class="btn btn-dark mb-3">Add</a>
      <table class="table table-bordered">
        <tbody>
        <c:forEach items="${places}" var="place">
          <tr>
            <td><a href="placeDetails/${place.id}" class="btn btn-light">${place.locationName}</a></td>
            <td><a href="deletePlace/${place.id}" class="btn btn-danger">Delete</a></td>
            <td><a href="editPlace/${place.id}" class="btn btn-secondary">Edit</a></td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>

  <form action="<c:url value="/logout"/>" method="post">
    <button type="submit" class="btn btn-primary">Log out</button>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
  </form>
</div>
<script src="<c:url value="/webjars/bootstrap/5.3.0/js/bootstrap.bundle.min.js" />"></script>
</body>
</html>
