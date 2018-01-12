<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Log in</title>
    <%@include file="include/header.jsp"%>
    <script src="js/login.js"></script>
    <script>
        if (getParameterByName('code') === '1') {
            alert("Reset password successful");
        }
    </script>
    <link href="css/loginpage.css" rel="stylesheet">
</head>
<body>
<%@include file="include/navtop.jsp"%>
<%@include file="include/loginform.jsp"%>
<%@include file="include/footer.jsp"%>
<%@include file="include/registerdialog.jsp"%>
</body>
</html>