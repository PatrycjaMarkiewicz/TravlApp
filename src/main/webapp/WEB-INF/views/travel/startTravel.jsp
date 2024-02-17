<%--
  Created by IntelliJ IDEA.
  User: patrycja
  Date: 15.02.2024
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Start Travel</title>



</head>
<body>
<form:form method="post" modelAttribute="travel">
      <div><label for="title">Title of your travel: <form:input path="title" id="title"></form:input> </label></div>
    <div><label for="originAddress"><form:select path="originAddress" items="${user.places}" itemValue="address" itemLabel="locationName" id="originAddress"></form:select> </label> </div>
    <div><label for="destinationAddress"><form:select path="destinationAddress" items="${user.places}" itemValue="address" itemLabel="locationName" id="destinationAddress"></form:select> </label> </div>
    <div><label for="vehicleSpeed"><form:select path="vehicleSpeed" items="${user.vehicles}" itemValue="avgSpeed" itemLabel="name" id="vehicleSpeed"></form:select> </label> </div>
    <div><label for="plannedDate">Planned Date:<form:input type="date" path="plannedDate" id="plannedDate"></form:input> </label> </div>
    <dov><input type="submit" ></dov>
</form:form>
</body>
</html>
