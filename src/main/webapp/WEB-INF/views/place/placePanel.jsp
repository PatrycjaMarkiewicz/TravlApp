<%--
  Created by IntelliJ IDEA.
  User: patrycja
  Date: 15.02.2024
  Time: 00:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${place.locationName}</title>
</head>
<body>
<div> Place name: ${place.locationName}</div>
<div> Place address : ${place.address}</div>
</body>
</html>
