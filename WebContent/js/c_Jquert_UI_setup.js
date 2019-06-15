/**
 * jQuery serializeObject
 * @copyright 2014, macek <paulmacek@gmail.com>
 * @link https://github.com/macek/jquery-serialize-object
 * @license BSD
 * @version 2.5.0
 */
(function(root, factory) {

    // AMD
    if (typeof define === "function" && define.amd) {
        define(["exports", "jquery"], function(exports, $) {
            return factory(exports, $);
        });
    }

    // CommonJS
    else if (typeof exports !== "undefined") {
        var $ = require("jquery");
        factory(exports, $);
    }

    // Browser
    else {
        factory(root, (root.jQuery || root.Zepto || root.ender || root.$));
    }

}(this, function(exports, $) {

    var patterns = {
        validate: /^[a-z_][a-z0-9_]*(?:\[(?:\d*|[a-z0-9_]+)\])*$/i,
        key:      /[a-z0-9_]+|(?=\[\])/gi,
        push:     /^$/,
        fixed:    /^\d+$/,
        named:    /^[a-z0-9_]+$/i
    };

    function FormSerializer(helper, $form) {

        // private variables
        var data     = {},
            pushes   = {};

        // private API
        function build(base, key, value) {
            base[key] = value;
            return base;
        }

        function makeObject(root, value) {

            var keys = root.match(patterns.key), k;

            // nest, nest, ..., nest
            while ((k = keys.pop()) !== undefined) {
                // foo[]
                if (patterns.push.test(k)) {
                    var idx = incrementPush(root.replace(/\[\]$/, ''));
                    value = build([], idx, value);
                }

                // foo[n]
                else if (patterns.fixed.test(k)) {
                    value = build([], k, value);
                }

                // foo; foo[bar]
                else if (patterns.named.test(k)) {
                    value = build({}, k, value);
                }
            }

            return value;
        }

        function incrementPush(key) {
            if (pushes[key] === undefined) {
                pushes[key] = 0;
            }
            return pushes[key]++;
        }

        function encode(pair) {
            switch ($('[name="' + pair.name + '"]', $form).attr("type")) {
                case "checkbox":
                    return pair.value === "on" ? true : pair.value;
                default:
                    return pair.value;
            }
        }

        function addPair(pair) {
            if (!patterns.validate.test(pair.name)) return this;
            var obj = makeObject(pair.name, encode(pair));
            data = helper.extend(true, data, obj);
            return this;
        }

        function addPairs(pairs) {
            if (!helper.isArray(pairs)) {
                throw new Error("formSerializer.addPairs expects an Array");
            }
            for (var i=0, len=pairs.length; i<len; i++) {
                this.addPair(pairs[i]);
            }
            return this;
        }

        function serialize() {
            return data;
        }

        function serializeJSON() {
            return JSON.stringify(serialize());
        }

        // public API
        this.addPair = addPair;
        this.addPairs = addPairs;
        this.serialize = serialize;
        this.serializeJSON = serializeJSON;
    }

    FormSerializer.patterns = patterns;

    FormSerializer.serializeObject = function serializeObject() {
        return new FormSerializer($, this).
        addPairs(this.serializeArray()).
        serialize();
    };

    FormSerializer.serializeJSON = function serializeJSON() {
        return new FormSerializer($, this).
        addPairs(this.serializeArray()).
        serializeJSON();
    };

    if (typeof $.fn !== "undefined") {
        $.fn.serializeObject = FormSerializer.serializeObject;
        $.fn.serializeJSON   = FormSerializer.serializeJSON;
    }

    exports.FormSerializer = FormSerializer;

    return FormSerializer;
}));


var data = userGetMessage = {
    action:"action",
    tableCategories:"userId;username;initials;inactive",
    tableValues:"23;Candish Marshmelowsa;CM;false",
    userRolesNames:"x;x2;x3;x4",
    userRoleId:"1;2;3;4",
    range:"x-y",
    aktive:"true",
    inaktive:"false",
};

function SetupUsersLog(){


        $(function () {
            $.ajax({
                type:'GET',
                url:'/rest/data',
                data:data,
                contentType:'application/json',
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