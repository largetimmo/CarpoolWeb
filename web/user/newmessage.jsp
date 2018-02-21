<%--
  Created by IntelliJ IDEA.
  User: chenjunhao
  Date: 2018/2/19
  Time: 下午8:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="/include/header.jsp" %>
<body>
<%@include file="/include/navtop.jsp" %>
<link rel="stylesheet" href="/css/bootstrap.min.css">
<c:if test="${!empty ref}">
    <form method="POST" action="/user_message_send?ref=${ref}">
</c:if>
 <c:if test="${!empty id}">
    <form method="POST" action="/user_message_send?id=${id}">
 </c:if>

    <div class="form-group">
        <label class="label" for="sendto">To:</label>
        <input name="to" class="form-control" id="sendto" placeholder="Send To:" value="${targetusername}">
    </div>
    <textarea name="content" style="width: 100vw;height: 30%;"></textarea>
    <input type="submit" class="btn-primary btn" style="width: 100vw;margin-top: 5px">
</form>


<%@include file="/include/footer.jsp" %>
</body>
</html>
