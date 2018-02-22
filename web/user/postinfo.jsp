<%--
  Created by IntelliJ IDEA.
  User: chenjunhao
  Date: 2018/2/21
  Time: 下午4:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="/include/header.jsp" %>
    <script src ="/js/moment-with-locales.js"></script>
    <script src="/js/bootstrap-datetimepicker.js"></script>
    <link href="/css/bootstrap-datetimepicker.css" rel="stylesheet">
</head>
<body>
<%@include file="/include/navtop.jsp" %>


<div>
    <h2>Poat your carpool information</h2>
    <form action="/user_carpool_add" method="post" style="padding-bottom: 30px">
        <div class="form-group col-lg-3">
            <label for="departure">Departure</label>
            <input id="departure" class="form-control" placeholder="Departure" name="departure">
        </div>
        <div class="form-group col-lg-3">
            <label for="destination">Destination</label>
            <input class="form-control" id="destination" placeholder="Destination:" name="destination">
        </div>
        <div class="form-group col-lg-2">
            <label for="price">Price:</label>
            <input class="form-control" id="price" name="price" placeholder="Price:">
        </div>
        <div class="form-group col-lg-2">
            <label for="seat">Seat:</label>
            <input class="form-control" id="seat" name="seat" placeholder="Seat:">
        </div>
        <div class="form-group col-sm-2">
            <label for="date">Date:</label>
            <input class="form-control" id="date" name="date" placeholder="Date:" type="text">
            <script type="text/javascript">
                $(function () {
                    $('#date').datetimepicker({
                        format: 'YYYY-MM-DD HH:mm'
                    });
                })
            </script>
        </div>
        <input class="btn-primary btn" type="submit">

    </form>
</div>

<%@include file="/include/footer.jsp" %>
</body>
</html>
