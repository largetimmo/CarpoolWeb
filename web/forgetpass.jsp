<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Forget Password</title>
    <%@include file="include/header.jsp" %>
    <style>
        .form {
            border: 1px solid #afd0f1;
        }

        h2 {
            text-align: center;
        }

        #submit {
            align-items: center;
        }
    </style>
    <script>
        $(document).ready(function () {
            if (getParameterByName('code') === '-1') {
                alert("Sorry. We cannot find the user match the information you provided")
                //Todo:Replace with dialog
            }
        })
    </script>
</head>
<body>
<%@include file="include/navtop.jsp" %>
<%@include file="include/forgetpassform.jsp"%>
<%@include file="include/footer.jsp" %>
</body>
</html>