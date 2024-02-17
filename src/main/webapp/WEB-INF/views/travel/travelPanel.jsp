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
    <title>${travel.title}</title>
</head>
<body>
<div> Travel title: ${travel.title}</div>
<div> Origin Address: ${travel.originAddress}</div>
<div> Destination Address: ${travel.destinationAddress}</div>
<div> Planned Date: ${travel.plannedDate}</div>
<div> Distance: ${travel.distance}</div>
<div> Travel Time: ${travel.timeInSeconds}</div>
</body>
</html>
