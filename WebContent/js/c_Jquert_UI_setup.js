

var data ={userId:"122",username:"Mulla Tjabauvs",initials:"MT",inactive:"false"};
function SetupUsersLog(){


    $(function () {
        $.ajax({
            type:'GET',
            url:'/rest/data',
            data: data,
            contentType:'application/json',
            dataType: 'json',
            succes: function (data) {
                console.log('succes', data);
            }
        });
    })

}



















/*

function handleShape(e) {
    $(".shape")
        .removeClass("circle pill square rectangle")
        .addClass($(e.target).val());
};
function handleToggle(e) {
    var target = $(e.target);

    if (target.is(".brand-toggle")) {
        var checked = target.is(":checked"),
            value = $("[name='brand']")
                .filter(":checked")
                .attr("data-" + target[0].id)
        $(".shape").css(target[0].id, checked ? value : "");
    } else {
        $(".shape").toggleClass(target[0].id, target.is(":checked"));
    }
}
function updateBrand() {
    handleShape({target: $("[name='shape']:checked")});
    $(".toggle:checked").each(function () {
        handleToggle({target: $(this)});
    });
}
$(document).ready(function () {

    $(".SelectionOptions").selectmenu();
    $(".SelectionRangeSlider").slider();
    var MenuRadio = $("#MenuElement_radioContentContainer");
    MenuRadio.controlgroup({
        direction: "vertical"
    });
    MenuRadio.on("change", handleToggle);


});*/