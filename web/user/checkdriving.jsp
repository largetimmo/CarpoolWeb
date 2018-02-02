<%--
  Created by IntelliJ IDEA.
  User: chenjunhao
  Date: 2018/2/1
  Time: 下午9:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="/include/header.jsp" %>
    <link rel="stylesheet" href="/css/bootstrap.css">
</head>
<body>
    <%@include file="/include/navtop.jsp" %>
    <div class="row" style="padding-top: 6px">
        <div class="col-lg-8">
            <h3>Riding List:</h3>
        </div>
        <div class = "col-lg-4">
            <div class="btn-group">
                <label class="btn btn-primary active">
                    <a href="/user_carpool_drivelist">Driving</a>
                </label>
                <label class="btn btn-primary ">
                    <a href="/user_carpool_ridelist">Riding</a>
                </label>
            </div>
        </div>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Trip#</th>
            <th>departure</th>
            <th>destination</th>
            <th>Time</th>
            <th>Seat</th>
            <th>Remainseat</th>
            <th>Price</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${carpools}" var="c">
            <tr>
                <th scope="row">${c.id}</th>
                <td>${c.departure}</td>
                <td>${c.destination}</td>
                <td>${c.dateTime}</td>
                <td>${c.capacity}</td>
                <td>${c.remainseat}</td>
                <td>${c.price}</td>
                <td>
                    <a class="btn btn-primary" href="/user_carpool_detail?id=${c.id}">Detail</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <%@include file="/include/footer.jsp" %>
</body>
</html>
