

function SetupUsersLog(){
    $(function () {
        $.ajax({
            type: 'GET',
            url: '/rest/DataType1',
            succes: function (data) {
                console.log('succes', data);
            }
        });
    })
}




$(document).ready(function () {

    $(".SelectionRangeOptions").selectmenu();
    $(".SelectionRangeSlider").slider();
    SetupUsersLog()

});