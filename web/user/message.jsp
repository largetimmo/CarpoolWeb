<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Message</title>
    <%@include file="/include/header.jsp"%>
    <style>
        #message {
            background-color: #ffffff;
            margin-top: 2em;
            height: 90vh;
        }
        #msg_list {
            border: solid 1px;
            width: 33.3%;
            float: left;
            height: inherit;
        }

        #msg_detail {
            width: 66.6%;
            float: right;
            height: inherit;
            background-color: #efefef;
        }

        #msg_text {
            border-bottom: solid 1px;
            height: 55%;
            padding-left: 10px;
        }

        #msg_send {
            padding-top: 1em;
            text-align: center;
        }

        #send_text {
            width: 95%;
            height: 100px;
            resize: none;
            margin-bottom: 10px;
        }
        #msg_info{
            float: left;
            border-bottom: solid 1px;
            width:100%;
            padding-bottom: 5px;
            padding-top: 5px;
        }
        #msg_sender_div{
            float: left;
            padding-left: 3px;
        }
        #msg_ref_div{
            float: right;
            padding-right: 3px;
        }
        #msg_info{
            font-size: 24px;
        }
        #send{
            width: 95%;
            height: 30px;
        }
        .msg_srt_link:nth-child(even){
            background-color: #efefef;
        }
        .msg_srt_link{
            border-top: solid 1px;
            /*margin-bottom: 7px;*/
        }
        .msg_srt_txt{
            padding-left: 5px;
            text-overflow: ellipsis;
            white-space: nowrap;
            overflow: hidden;
        }
        .msg_header{
            padding-left: 5px;
            font-size: 18px;
        }
        .msg_unread{
            font-weight: bold;
        }
        h3{
            text-align: left;
            padding-left: 2.5%;
        }
    </style>
    <script>
        var message_list;
        $(document).ready(function () {
            $.get("/user/message_list",function (data) {
                message_list = JSON.parse(data).array;
                for(var i = 0; i<message_list.length;i++){
                    var msg_s_l = document.createElement("div");
                    var msg_h = document.createElement("div");
                    var msg_s_t = document.createElement("div");
                    msg_s_l.appendChild(msg_h);
                    msg_s_l.appendChild(msg_s_t);
                    msg_h.innerHTML = "From"+ message_list[i].from;
                    msg_s_t.innerHTML = message_list[i].message;
                    if (message_list[i].read===0){
                        msg_s_l.attr("class","msg_unread msg_srt_link")
                    }else{
                        msg_s_l.attr("class","msg_srt_link")
                    }
                    msg_s_l.attr("index", i);
                    msg_h.attr("class","msg_header");
                    msg_s_t.attr("class","msg_srt_txt");
                    document.getElementById("msg_list").appendChild(msg_s_l);
                }
            });
            $("#msg_list").on("click",".msg_srt_link",function () {
                var i = $(this).attr("index");
                $("#msg_sender_div").text(message_list[i].from);
                $("#msg_ref_span").text(message_list[i].ref);
                $("#msg_text").text(message_list[i].message);
                $("#send").attr("uid",message_list[i].uid);
            });
            $("#msg_ref_span").onclick(function () {
                //jump to carpool checking page
            });
            $("#send").onclick(function () {
                var uid = $(this).attr("uid");
                var ref = $("#msg_ref_span").innerHTML;
                var message = $("#send_text").text();
                var data = {"uid":uid,"ref":ref,"message":message};
                $.post("/user/message_send",data,function (rtdata) {
                    var rtdata_JSON = JSON.parse(rtdata);
                    if (rtdata.code === "1"){
                        alert("message send successfully")
                    }else{
                        alert("message send failed")
                    }
                })
            })
            
        })
    </script>
</head>
<body>

<%@include file="/include/navtop.jsp"%>
<%@include file="/include/user/message.jsp"%>
<%@include file="/include/footer.jsp"%>
</body>
</html>