<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="navbar" class="navbar">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand hidden-sm navtext" href="/index.jsp">Title</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <!--Add more tags use <li></li>-->
            </ul>
            <c:if test = "${empty uid}">
                <ul class="nav navbar-right navbar-nav">
                    <li>
                        <a class="navtext" href="login.jsp">Login</a>
                    </li>
                </ul>
            </c:if>
            <c:if test="${!empty uid}">
                <ul class="nav navbar-right navbar-nav">
                    <li>
                        <a class="navtext" href="/user/usermanagement.html">User Management</a>
                    </li>
                </ul>
                <ul class="nav navbar-right navbar-nav">
                    <li>
                        <a class="navtext" id = "a_checkmsg" href="/user_message_list">Messages</a>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a class="navtext" id = "a_logout" href="/user/logout">Logout</a>
                    </li>
                </ul>
            </c:if>
        </div>
    </div>
</div>