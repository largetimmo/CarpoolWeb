<%--
  Created by IntelliJ IDEA.
  User: chenjunhao
  Date: 2018/2/2
  Time: 上午11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/include/header.jsp"%>
</head>
<body>
<%@include file="/include/navtop.jsp"%>


<div class="row" style="padding-top: 6px">
    <div class="col-lg-8">
        <h3>Riding List:</h3>
    </div>
    <div class = "col-lg-4">
        <div class="btn-group">
            <label class="btn btn-primary">
                <a href="/user_carpool_drivelist">Driving</a>
            </label>
            <label class="btn btn-primary active">
                <a href="/user_carpool_ridelist">Riding</a>
            </label>
        </div>
    </div>
</div>
<table class="table table-bordered">
    <thead>
        <tr>
            <th>Booking ref#</th>
            <th>Trip#</th>
            <th>Departure</th>
            <th>Destination</th>
            <th>Date</th>
            <th>Booked seat</th>
            <th>Remain seat</th>
            <th>Price(Each)</th>
            <th>Driver's name</th>
            <th></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${carpools}" var="c">
            <tr>
                <th class="row">${c.ref}</th>
                <td>${c.carpoolInfo.id}</td>
                <td>${c.carpoolInfo.departure}</td>
                <td>${c.carpoolInfo.destination}</td>
                <td>${c.carpoolInfo.dateTime}</td>
                <td>${c.seats}</td>
                <td>${c.carpoolInfo.remainseat}</td>
                <td>${c.carpoolInfo.price}</td>
                <td>${c.carpoolInfo.user.nickname}</td>
                <td>
                    <a class="btn btn-primary" href="/user_carpool_detail?ref=${c.ref}">Detail</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<%@include file="/include/footer.jsp"%>
</body>

</html>
