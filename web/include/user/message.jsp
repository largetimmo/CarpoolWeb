<div id="message">
    <div id="msg_list">
        <!-- message shortlink here-->
        <c:forEach items="${messages}" var="m">
            <c:if test="${m.readmsg == '0'}">
                <div class="msg_unread msg_srt_link">
                        ${m.sender_name}
                </div>
            </c:if>
            <c:if test="${!m.readmsg == '0'}">
                <div class="msg_srt_link">
                        ${m.sender_name}
                </div>
            </c:if>
            <div class="msg_srt_txt">
                    ${m.message}
            </div>
            <div class="msg_detail">
                <a href="/user_message_read?mid=${m.M_ID}" class="btn btn-primary">Detail</a>
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