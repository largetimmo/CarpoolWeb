<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- description of this web-->
    <meta name="description" content="xxxxxxx">

    <!--a perfect name is required-->
    <title>Safe & Sound Carpool</title>

    <%@include file="include/header.jsp" %>
    <link href="css/firstpage.css" rel="stylesheet">
    <link href="css/bootstrap-datetimepicker.css" rel="stylesheet">
    <script src="js/bootstrap-datepicker.js"></script>
    <script src="js/firstpage.js"></script>
    <script>
        if (getParameterByName('code') === '1') {
            alert("Logout success");
        }
    </script>
</head>
<body>
<%@include file="include/navtop.jsp" %>
<%@include file="include/searchform.jsp" %>
<%@include file="include/searchresult.jsp" %>
<%@include file="include/footer.jsp" %>
</body>
</html>