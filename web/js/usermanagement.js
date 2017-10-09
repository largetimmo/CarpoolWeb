/**
 * Created by admin on 2017/8/25.
 */

$(document).ready(function () {
    $("#content").load("/postbookingpage.html");

    $("#checkingbooking").click(function () {
        $("#content").load("/checkingpage.html");
    });

   $("#postbooking").click(function () {
       $("#content").load("/postbookingpage.html");
   })
});