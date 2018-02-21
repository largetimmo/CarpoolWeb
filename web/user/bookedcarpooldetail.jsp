<%--
  Created by IntelliJ IDEA.
  User: chenjunhao
  Date: 2018/2/2
  Time: 下午12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@include file="/include/header.jsp" %>
</head>
<body>
<%@include file="/include/navtop.jsp" %>
<div class="row">
    <div class="col-lg-8">
        <label>Booking Reference:</label>
        <span>${ref}${id}</span>
    </div>
    <div class="col-lg-4">
        <label>Date</label>
        <span>${c.date}</span>
    </div>
</div>
<table class="table table-bordered">
    <tbody>
    <tr>
        <td>Departure</td>
        <td>${c.departure}</td>
    </tr>
    <tr>
        <td>Destination</td>
        <td>${c.destination}</td>
    </tr>
    <tr>
        <td>Passenger List:</td>
    </tr>
    <c:forEach items="${c.users}" var="user">
        <tr>
            <td>${user.first}</td>
            <c:if test="${!empty ref}">
                <td><a class="btn btn-primary" href="/user_message_newmessage?targetuid=${user.second}&ref=${ref}">Send Message</a></td>
            </c:if>
            <c:if test="${!empty id}">
                <td><a class="btn btn-primary" href="/user_message_newmessage?targetuid=${user.second}&id=${id}">Send Message</a></td>
            </c:if>

        </tr>
    </c:forEach>
    </tbody>

</table>

<%@include file="/include/footer.jsp" %>
</body>
</html>
