<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="pojo.BookedCarpoolInfo" %><%--
  Created by IntelliJ IDEA.
  User: chenjunhao
  Date: 2017/10/17
  Time: PM10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <table id = "table" class= "table table-bordered">
        <tr class="bg-primary">
            <th id = "reference">Reference Number</th>
            <th id = "departure">Departure</th>
            <th id = "destination">Destination</th>
            <th id = "seat">Seat(s)</th>
            <th id = "date">Date</th>
            <th id = "vehicletype">Vehicle</th>
            <th id = "name">Name</th>
        </tr>
    </table>
</div>
<c:set var="list" value="${booked_carpool_list}" scope="request"></c:set>
<c:if test="${empty list}">
    <h2>Your booked carpool list is empty.</h2>
</c:if>
<c:if test="${!empty list}">

</c:if>

<c:forEach var="current" items="${list}">
    <tr>
        <td><c:out value="${current.}"</td>
    </tr>
</c:forEach>

</body>
</html>
