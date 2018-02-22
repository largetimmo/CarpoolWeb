<%--
  Created by IntelliJ IDEA.
  User: chenjunhao
  Date: 2018/2/21
  Time: 下午3:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/include/header.jsp" %>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@include file="/include/navtop.jsp" %>

<h2>Please provide your vehicle information</h2>

<form action="/user_user_updatevehicle" method="post">
    <div class="form-group">
        <label for="vehicletype">Vehicle type:</label>
        <input id="vehicletype" name="vehicletype" placeholder="Vehicle type:" class="form-control">
    </div>
    <div class="form-group">
        <label for="platenum">Plate Number:</label>
        <input id="platenum" name="platenum" placeholder="Plate Number" class="form-control">
    </div>
    <input class="btn btn-primary" type="submit">
</form>

<%@include file="/include/footer.jsp" %>
</body>
</html>
