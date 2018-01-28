<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav id="navbar" class="navbar-expand-lg">
    <script src="/js/jquery3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand hidden-sm navtext" href="/index.jsp">Title</a>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <!--Add more tags use <li></li>-->
            </ul>
            <c:if test="${empty uid}">
                <ul class="nav navbar-right navbar-nav">
                    <li>
                        <a class="navtext" href="login.jsp">Login</a>
                    </li>
                </ul>
            </c:if>
            <c:if test="${!empty uid}">
                <ul class="nav navbar-right navbar-nav">
                    <li class="dropdown">
                        <a id="userManagementDW" class="navtext dropdown-toggle" data-toggle="dropdown">User
                            Management</a>
                        <div class="dropdown-menu" aria-labelledby="userManagementDW">
                            <a class="dropdown-item" href="/user_user_changeinfo">Profile</a>
                            <a class="dropdown-item" href="/user_carpool_ridelist">Ride list</a>
                            <a class="dropdown-item" href="/user_carpool_drivelist">Drive list</a>
                            <
                        </div>
                    </li>
                    <li>
                        <a class="navtext" id="a_checkmsg" href="/user_message_list">Messages</a>
                    </li>
                    <li>
                        <a class="navtext" id="a_logout" href="/user_user_logout">Logout</a>
                    </li>
                </ul>
            </c:if>
        </div>
    </div>
</nav>