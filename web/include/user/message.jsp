<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="message">
    <c:if test="${!empty msg}">
        <script>
            alert(${msg});
        </script>
    </c:if>
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