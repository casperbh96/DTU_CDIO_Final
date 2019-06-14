function SetupUsersLogOld() {
    $(function () {
        $.ajax({
            type: 'GET',
            url: '/rest/DataType1/Users',
            succes: function (data) {
                console.log('succes', data);
            }
        });
    })
}

var restDtoMessage = {
    system: 'web',
    action: 'create',
    value_1: 'users',
    value_2: 'userid=x;username=x;initials=x;inaktive=false',
    value_3: '1=hej;2=bub du er en fish',
    value_4: 'empty',
    value_5: 'empty'
};

function SetupUsersLog() {

    var data = restDtoMessage = {
        userId: "122",
        username: "Mulla Tjabauvs",
        initials: "MT",
        inactive: "false"
    };

    //  var data = $(restDtoMessage).serializeJSON();
    $(function () {
        $.ajax({
            type: 'GET',
            url: '/rest/data/users',
            contentType: "application/json",
            data: {
                "userDTO": data,
                "action": "hello",
                "aktive": true,
                "in_aktive": false,
            },
            success: function (data) {
                console.log('succes', data);
            }
        });
    })
}

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


});