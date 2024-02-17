<%--
  Created by IntelliJ IDEA.
  User: patrycja
  Date: 14.02.2024
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
<a>Your travels</a>
<a href="startTravel">Start Travel</a>
<table border="2">
  <tbody>
  <c:forEach items="${travels}" var="travel">
    <tr>
      <td><a href="travelDetails/${travel.id}"><c:out value="${travel.title}"></c:out></a></td>
      <td><a href="deleteTravel/${travel.id}">Delete</a></td>
      <td><a href="editTravel/${travel.id}">Edit</a> </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<br>
<a>Your vehicles</a>
<a href="addVehicle">Add</a>
<table border="2">
  <tbody>
  <c:forEach items="${vehicles}" var="vehicle">
    <tr>
      <td><a href="vehicleDetails/${vehicle.id}"><c:out value="${vehicle.name}"></c:out></a> </td>
      <td><a href="deleteVehicle/${vehicle.id}">Delete</a> </td>
      <td><a href="editVehicle/${vehicle.id}">Edit</a> </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<br>
<a>Your Places</a>
<a href="addPlace">Add</a>
<table border="2">
  <tbody>
  <c:forEach items="${places}" var="place">
    <tr>
      <td><a href="placeDetails/${place.id}"><c:out value="${place.locationName}"></c:out></a> </td>
      <td><a href="deletePlace/${place.id}">Delete</a> </td>
      <td><a href="editPlace/${place.id}">Edit</a> </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<br>

<form action="<c:url value="/logout"/>" method="post">
  <input class="fa fa-id-badge" type="submit" value="Wyloguj">
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

</body>
</html>
