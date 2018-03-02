/**
 * Created by admin on 2017/8/27.
 */
$(document).ready(function () {
    $.get("/user/newvehicaluser.do",function (data) {
        var json_result = JSON.parse(data);
        if (json_result.code === '-1'){
            $("#dialog").modal();
        }
    });
    $("#send").click(function(){
        var jsonobj = {"from":$("#from").val(),"to":$("#to").val(),"date":$("#date").val(),"passengers":$("#passenger").val(),"price":$("#price").val()};
        console.log(jsonobj);
        $.post("postcarpool", jsonobj, function (data) {
            var data_json = JSON.parse(data);
            if(data_json.code === 1){
                $("#message").attr("class","label-primary");
            }else if(data_json.code === -100){
                $("#dialog").modal();
            } else {
                $("#message").attr("class","label-danger");
            }
            switch (data_json.code){
                case 1:
                    $("#message").text("Post Carpool Info Successful!");
                    break;
                case -1:
                    $("#message").text("Sorry, we currently do not support the city");
                    break;
                case -2:
                    $("#message").text("The passenger you have input is not valid");
                    break;
                case -3:
                    $("#message").text("The departure date is not valid");
                    break;
                default:
                    $("#message").text("Server is busy. Please try again in few minutes");
            }

        })
    });
});
