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
    <script src="js/GeneralFunction.js"></script>
    <script>
        if (getParameterByName('code')=== '-1'){
            alert("The password that you provided is not meet the regulation");
            //Todo:alert --> dialog
        }
    </script>
    <link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<form action="resetpass.do" method="post">
    <div class=" container">
        <div class="col-sm-6">
            <h1> Hello, <%=request.getSession().getAttribute("USERNAME_TEMP")%></h1>
            <div class="col-sm-12">
                <br>
                <input id = "password" type="password" name="password" class="form-control" autocomplete="off" placeholder="Password(between 6 to 16 characters)">
                <br>
            </div>
            <div class="col-sm-12">
                <input type="submit" class = "center-block btn-primary">
            </div>

        </div>
    </div>
</form>
</body>
</html>
