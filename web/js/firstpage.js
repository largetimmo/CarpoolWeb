/**
 * Created by admin on 2017/8/15.
 */
$(document).ready(function () {
    $("#results_area").on('click', '.book', function () {
        console.log($(this).attr("bookid"));
        $.post("user/booking", {"bookid": $(this).attr("bookid")}, function (data) {
            var data_json = JSON.parse(data);
            if (data_json.code === 1) {
                alert("Booking success");
            } else if (data_json.code === -1) {
                alert("Please login first");
                window.location.href = "/login.jsp";
            }
            else {
                alert("Booking failed");
            }
        })
    });

    $("#send").click(function () {
        $("#results_area").html("");
        var jsonobj = {
            "from": $("#from").val(),
            "to": $("#to").val(),
            "date": $("#date").val(),
            "passengers": $("#passenger").val()
        };
        $.post("search", jsonobj, function (data) {
            console.log("ajax post start");
            var data_json = JSON.parse(data);
            var result_array = data_json.results;
            if (data_json.code != undefined) {
                //TODO: different code switch ref CODE 1
                $("#results_area").html("<label class = 'text-info'>Your departure date cannot be today before</label>");
            } else {
                if (result_array.length === 0) {
                    console.log(result_array);
                    $("#results_area").html("<label class = 'text-info'>Cannot find any vehicle matches your search.</label>");
                } else {
                    for (var count = 0; count < result_array.length; count++) {
                        var result_json = result_array[count];
                        $("#results_area").append(
                            "<div class = 'list-group-item result'>" +
                            "<div class='row'> " +
                            "<div class = 'col-sm-8'>" +
                            "<label class = 'username'> NickName:" + result_json.nickname + "</label>" +
                            "<br>" +
                            "<label class = 'userlevel'>Userlevel:" + result_json.userlevel + "</label>" +
                            "<br>" +
                            "<label class = 'vehicle'>Vehicle:" + result_json.vehicle + "</label>" +
                            "<br>" +
                            "<label class = 'remainseat'>Remain Seats:" + result_json.remainseat + "</label>" +
                            "</div>" +
                            "<div class='col-sm-4'>" +
                            "<label class = 'price'> Price: $" + result_json.price + "</label>" +
                            "<br>" +
                            "<label class = 'departuretime'>Departure Time: " + result_json.date + "</label>" +
                            "<br>" +
                            "<button bookid = '" + result_json.id + "' class = 'book btn btn-success'>Book</button>" +
                            "</div>" +
                            "</div>" +
                            "</div>"
                        )
                    }
                }
            }

        })
    });
});
