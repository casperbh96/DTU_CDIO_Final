

function SetupUsersLog(){
    $.ajax({
        url : "http://localhost:8080/rest/DataType1",
        data : $('#form').serializeJSON(),
        contentType: "application/x-www-form-urlencoded",
        method: 'GET',
        success : function(data){
            alert(data);
            if(data=='true'){
                $('#CONTENTHERERJEG').append(data);
            }
        }
    });
}


$(function () {
    $.ajax({
        type:'GET',
        url:'/rest/',
        succes: function(data){
            console.log('succes',data);
        }
    });
})





$(document).ready(function () {

    $(".SelectionRangeOptions").selectmenu();
    $(".SelectionRangeSlider").slider();
    SetupUsersLog()

});