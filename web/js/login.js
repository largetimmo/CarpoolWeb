/**
 * Created by admin on 2017/8/20.
 */
$(document).ready(function () {
    $("#submit").click(function () {
        $("#return_message").text("");
        $("#return_message").css("display","none");
        var submit_form = {'username':$("#reg_username").val()};
        submit_form.password = $("#reg_password").val();
        submit_form.email = $("#reg_email").val();
        submit_form.nickname = $("#reg_nickname").val();
        submit_form.cell = $("#reg_cell").val();
        $.post("/register",submit_form,function (data) {
            var data_json = JSON.parse(data);
            var message = "";
            switch (data_json.code){
                case 1:
                    message = "success register";
                    $("#return_message").attr('class','label-success');
                    break;
                case 0:
                    message = "Unknown errors";
                    break;
                case -1:
                    message = "Username exists";
                    break;
                case -2:
                    message = "Username contain invalid characters";
                    break;
                case -6:
                    message = "Invalid username length";
                    break;
                case -3:
                    message = "Password contain invalid characters";
                    break;
                case -4:
                    message = "Invalid password length";
                    break;
                case -5:
                    message = "invalid email address";
                    break;
                case -7:
                    message = "Incalid nickname";
                    break;
                default:
                    message = "unknown error";
                    break
            }
            $("#return_message").text(message);
            $("#return_message").css("display","block");
        })
    });

    $("#login").click(function () {
        var userinfo ={"username":$("#username").val()};
        userinfo.password = $("#password").val();
        $.post("/login",userinfo,function (data) {
            var data_json = JSON.parse(data);
            if(data_json.code === 1){
                //success login
                alert("Login Successful");
                window.location.href="/index.html"

            }
            else if(data_json.code === -1){
                //wrong pass/username
                $("#login_message").text("Log in unsuccessful. Please check your username or password");
            }
            else {
                //whats going to happen???
                //emmmm
                //no idea
                alert("bilibala, pilipala,booom boom boom")
            }
        })
    })
})