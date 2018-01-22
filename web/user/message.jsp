<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Message</title>
    <%@include file="/include/header.jsp" %>
    <style>
        #message {
            background-color: #ffffff;
            margin-top: 5em;
            height: 90vh;
        }

        #msg_list {

            float: left;
            height: inherit;
            width: 100%;
        }

        .msg_srt_link:nth-child(even) {
            background-color: #efefef;
        }

        .msg_srt_link {
            border-top: solid 1px;
            /*margin-bottom: 7px;*/
        }

        .msg_srt_txt {
            padding-left: 5px;
            text-overflow: ellipsis;
            white-space: nowrap;
            overflow: hidden;
        }

        .msg_unread {
            font-weight: bold;
        }

        h3 {
            text-align: left;
            padding-left: 2.5%;
        }

        .msg_left {
            width: 70%;
            float: left;
            margin-right: 5px;
        }
        .msg_right{
            padding-top: 1px;
            padding-right: 10px;

        }
        .msg_remove_btn{
            float: right;
            width: 10vw;

        }
        .msg_read_btn{
            width: 10vw;
        }
        .msg{
            border: 1px solid #2b2b2b;
            height: 40px;
        }
    </style>
</head>
<body>

<%@include file="/include/navtop.jsp" %>
<div id="message">
    <div id="msg_list">
        <!-- message shortlink here-->
        <c:forEach items="${messages}" var="m">
            <div class="msg">
                <div class="msg_left">
                    <c:if test="${m.read eq '0'}">
                        <div class="msg_unread msg_srt_link">
                                From: ${m.sender_name}
                        </div>
                    </c:if>
                    <c:if test="${m.read ne '0'}">
                        <div class="msg_srt_link">
                                From: ${m.sender_name}
                        </div>
                    </c:if>
                    <div class="msg_srt_txt">
                            ${m.message}
                    </div>
                </div>
                <div class="msg_right">
                    <a href="/user_message_read?mid=${m.ID} " class="btn btn-primary msg_read_btn">Read</a>
                    <a href="/user_message_remove?mid=${m.ID}" class="btn btn-danger msg_remove_btn">Delete</a>
                </div>
            </div>
        </c:forEach>
        <!--
        <div class="msg_srt_link" index="4">
            <div class="msg_header">
                XXXX3
            </div>
            <div class="msg_srt_txt">
                XXXX3
            </div>
        </div>
        -->
    </div>

</div>
<%@include file="/include/footer.jsp" %>
</body>
</html>