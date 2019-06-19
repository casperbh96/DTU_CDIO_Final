function get(url, sFunc, eFunc){
    $.ajax({
        type:'GET',
        url: url,
        success: function (data) {
            sFunc(data);
        },
        error: function(data) {
            alert("connection Error with url" + url+ ";");
        }
    });
}
function post(data, url, sFunc){
    $.ajax({
        type:'POST',
        url: url,
        contentType:'application/json',
        data:data,
        success: function (data) {
            sFunc(data);
        }
    });
}
function put(data, url, sFunc){
    $.ajax({
        type:'PUT',
        url: url,
        contentType:'application/json',
        data:data,
        success: function (data) {
            sFunc(data);
        }
    });
}
function Delete(data, url, sFunc){
    $.ajax({
        type:'DELETE',
        url: url,
        contentType:'application/json',
        data:data,
        success: function (data) {
            sFunc(data);
        },
    });
}

var RECIPE_OPTIONS ="\n";
get('/rest/recipe/get', function (data) {
    $.each(data, function (i, role) {
        RECIPE_OPTIONS = RECIPE_OPTIONS + '<option value="volvo">Volvo</option> \n';
    });
});

function HTML_GenerateForm(){
    return
    '    <form id="ProductionFormContainer">\n' +
        '        <h1> title </h1>\n' +
        '        <table class="ProductionForm_subPartContainer">\n' +
        '            <tr><td> <span>id</span> </td><td>   <input type="number" value="x"> </td></tr>\n' +
        '            <tr><td>\n' +
        '                <span>Recipe_id</span>\n' +
        '            </td><td>\n' +
        '                <select>\n' +
        ''+  RECIPE_OPTIONS +
        '                </select>\n' +
        '            </td></tr>\n' +
        '        </table>\n' +
        '        <div class="ProductionForm_subPartContainer _1x2grid" style="grid-column-gap:15px;" >\n' +
        '            <!--// column 1 -->\n' +
        '            <div class="ProductionForm_ListContainer" id="ProductionForm_ResourceContainer" style="" >\n' +
        '                <select size="10" name="selectionField" multiple="yes" style="width: 100%">\n' +
        '\n' +
        '                </select>\n' +
        '            </div>\n' +
        '            <!--// column 2 -->\n' +
        '            <div class="ProductionForm_ListContainer" id="ProductionForm_BatchContainer" >\n' +
        '                <select size="10" name="selectionField" multiple="yes" style="width: 100%" data-refere="Ing_opt_1">\n' +
        '\n' +
        '                </select>\n' +
        '            </div>\n' +
        '        </div>\n' +
        '        <div class="ProductionForm_subPartContainer" style="grid-column-gap:15px;" >\n' +
        '            <!--// column 1 -->\n' +
        '            <table style="width: 100%" >\n' +
        '                <tr class="_1x2grid" style="width: 100%" >\n' +
        '                    <td><span> Production Leader Id </span></td>\n' +
        '                    <td><input id="ProductionForm_produktLeaderID" type="text" style="float:right;"></td>\n' +
        '                </tr>\n' +
        '\n' +
        '            </table>\n' +
        '        </div>\n' +
        '    </form>';


}
$( document ).ready(function(){
    $('#generatedForm').append(HTML_GenerateForm);
});



// get Recipes that you can choose from.
// setting up a radio selection of options