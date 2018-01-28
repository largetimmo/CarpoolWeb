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
<%@include file="/include/user/reg_info.jsp"%>
<%@include file="/include/footer.jsp"%>
</body>
</html>
