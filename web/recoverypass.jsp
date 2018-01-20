<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: chenjunhao
  Date: 2017/11/6
  Time: AM10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="include/header.jsp"%>
    <script>
        if (getParameterByName('code')=== '-1'){
            alert("The password that you provided is not meet the regulation");
            //Todo:alert --> dialog
        }
    </script>
</head>
<body>
<%@include file="include/navtop.jsp"%>
<%@include file="include/passrec.jsp"%>
<%@include file="include/footer.jsp"%>
</body>
</html>
