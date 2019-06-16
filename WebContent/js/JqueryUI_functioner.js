$(document).ready(function () {
    $(".SelectionOptions").selectmenu();
    $(".SelectionRangeSlider").slider();

});

function writeForUser(text){
    $("#UsersContentFlasher").text(text);
}

