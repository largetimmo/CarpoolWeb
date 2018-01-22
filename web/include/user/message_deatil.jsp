<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="msg_detail" class="container">
    <!-- detail here-->
    <style>
        #msg_info{
            font-size: 30px;
        }
        .msg_sender_list{
            width: 100vw;

        }
        #msg_sender_div{
            float: left;
            width:50%
        }
        #msg_ref_div{
            float: right;
        }
        #send_text{
            margin-bottom: 5px;
        }
    </style>

    <div id="msg_info" class="col-lg-12">
        <div id="msg_sender_div" class="msg_sender_list">
            <label id="msg_sender_label">From: ${message.sender_name}</label>
        </div>
        <div id="msg_ref_div">
            <label>Related Carpool Reference#:<a id="msg_ref_span" href="#"> ${message.ref}</a></label>
        </div>
    </div>

    <div id="msg_text" class="col-lg-12">${message.message}</div>

    <form id="msg_send" method="post" action="/user_message_send">
        <h3>Reply:</h3>
        <textarea name = "message_send" id="send_text" rows="10" class="col-lg-11"></textarea>
        <input id="send" type="submit" class="btn btn-primary col-lg-11">
    </form>
</div>