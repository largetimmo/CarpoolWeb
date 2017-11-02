/**
 * Created by admin on 2017/8/25.
 */

$(document).ready(function () {
    $("#content").load("/postbookingpage.html");

    $("#checkingbooking").click(function () {
        $("#content").load("/checkbookingpage.html");
    });
    $("#checkingposting").click(function () {
        $("#content").load("/checkpostingpage.html")
    })
   $("#postbooking").click(function () {
       $("#content").load("/postbookingpage.html");
   })
});