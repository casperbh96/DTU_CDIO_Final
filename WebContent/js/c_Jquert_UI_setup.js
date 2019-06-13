

function SetupUsersLog(){
    $.ajax({
        url : "http://localhost:8080/rest/hello",
        data : $('#form').serializeJSON(),
        contentType: "application/json",
        method: 'POST',
        success : function(data){
            alert(data);
        },
        error: function(jqXHR, text, error){
            alert(jqXHR.status + text + error);
        }
    });



}




$(document).ready(function () {

    $(".SelectionRangeOptions").selectmenu();
    $(".SelectionRangeSlider").slider();







} );