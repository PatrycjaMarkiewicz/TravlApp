<%--
  Created by IntelliJ IDEA.
  User: patrycja
  Date: 14.02.2024
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add Vehicle</title>
</head>
<body>
<form:form method="post" modelAttribute="vehicle">
    <div><label for="name">Name<form:input path="name" id="name"></form:input> </label> </div>
    <div><label for="avgSpeed">avgSpeed<form:input path="avgSpeed" id="avgSpeed" type="number"></form:input> </label> </div>
    <input type="submit">
</form:form>

</body>
</html>
