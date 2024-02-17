<%--
  Created by IntelliJ IDEA.
  User: patrycja
  Date: 14.02.2024
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add Vehicle</title>
</head>
<body>
<form:form method="post" modelAttribute="place">
    <div><label for="locationName">LocationName<form:input path="locationName" id="locationName"></form:input> </label> </div>
    <div><label for="address">Address<form:input path="address" id="address"></form:input> </label> </div>
    <input type="submit">
</form:form>

</body>
</html>
