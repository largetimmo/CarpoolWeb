<%--
  Created by IntelliJ IDEA.
  User: chenjunhao
  Date: 2018/1/27
  Time: 下午11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/include/header.jsp"%>
    <c:if test="${!empty msg}">
        <script>
            alert(${msg});
        </script>
    </c:if>
</head>
<body>
<%@include file="/include/navtop.jsp"%>
<div class="container">
    <form action="/user_user_update" method="post">
        <div class="form-group">
            <label for="emailaddr">Email:</label>
            <input type="email" name ="email" id="emailaddr" class="form-control" value=${userinfo.email}>
        </div>
        <div class="form-group">
            <label for="cellnum">Cell:</label>
            <input id="cellnum" name="cell" type="number" class="form-control" value="${userinfo.cell}">
        </div>
        <div class="form-group">
            <label for="nickname">Nickname:</label>
            <input id="nickname" name = "nickname" class="form-control" type="text" value="${userinfo.nickname}">
        </div>
        <input type="submit" class="btn btn-primary">
    </form>
</div>
<%@include file="/include/footer.jsp"%>
</body>
</html>
