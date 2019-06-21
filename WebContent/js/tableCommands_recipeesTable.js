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

var userTable_STATE_deleteReady   ="delete";
var userTable_STATE_editReady     ="edit";
var userTable_STATE_createReady   ="create";
var userTable_STATE_NOCHANGE      ="none";
// plan!
// FOR HVER Recipe
// se dens Resources og REL resources.
// UI
// data-editstate
// data-aktiveediting
function UI_RecipedeleteRow(self){


}

function HTML_CreateRecipeBach_Row(Recipe ,RowName){
    return '    ' +
        '<tr id="'+RowName+'" class="DTO_Table_Row DTO_RECI_GRID" data-editstate="'+userTable_STATE_NOCHANGE+'" data-aktiveediting="false"  >\n' +
        '        <td><input class="commit-state" type="checkbox" checked="unchecked"                                                         > </td>\n' +
        '        <td><p class="dto-table-column-DTO-formElement     recipeId"	  	data-value="'+Recipe.recipeId+'"                         > Id :'+Recipe.recipeId+'</p></td>\n' +
        '        <td><p class="dto-table-column-DTO-formElement     recipeName"     data-value="'+Recipe.recipeEndDate+'" style="display: none;visibility: hidden;width: 0;height: 0;">'+Recipe.recipeEndDate+'</p></td>\n' +
        '        <td><p class="dto-table-column-DTO-formElement     recipeEndDate" 	data-value="'+Recipe.recipeName+'"                       >Recipe Name:'+Recipe.recipeName+'</p></td>\n' +
        '        <td><p class="dto-table-column-DTO-formElement     productAmount"  data-value="'+Recipe.productAmount+'"                    >Produces Ammount '+Recipe.productAmount+'kg</p></td>\n' +
        '        <td><p class="dto-table-column-DTO-formElement     authorUserId"   data-value="'+Recipe.authorUserId+'"                     >Author Id '+Recipe.authorUserId+'</p></td>\n' +
        '        <td class="DTO_Table_Row_beneathRow" style="grid-row: 2/3; grid-column: 1/7;"                                               >\n' +
        '            <button class="DTO_PROD_DropButton" onclick="toggleDropDowns(this)" data-hidden="1"                                     > Resources included.</button>\n' +
        '            <table  class="DTO_PROD_DropDown" style="display:none; position:relative; z-index:5;"                                   >\n' +
        '            </table>\n' +
        '        </td>\n' +
        '        <td class="DTO_Table_Row_MenuBox" style="grid-row: 1/3;">\n' +
        '            <button class="dto-table-button" name="update" onclick="dto_table_row_updateToggle(this.parentElement.parentElement)" style="grid-row: 1/2;">update</button>\n' +
        '            <button class="dto-table-button" name="delete" onclick="dto_table_row_deleteToggle(this.parentElement.parentElement)" style="grid-row: 2/3;">delete</button>\n' +
        '        </td>\n' +
        ' </tr>';
}
function HTML_CreateRecipeRelResRow(resource,resourceBatch){
    return'\n' +
        '               <tr class="_1x4grid">\n' +
        '                    <td class="rel_resourceId"     > ' + resource.resourceId + ' </td>\n' +
        '                    <td class="recipe_name"        > ' + resource.resourceName + ' </td>\n' +
        '                    <td class="rel_Amount"         > ' + resourceBatch.resourceAmount + ' </td>\n' +
        '                    <td class="rel_tolerance"      > ' + resourceBatch.tolerance + ' </td>\n' +
        '                </tr>' +
        '\n';

}

function loadRecipeTable() {

    var RowNameInput = "rowNumber_";
    var myhtml_Ingredients;

    get('/rest/recipe/get', function (data) {
        $.each(data, function (u, Recipe) {

            var RowName = RowNameInput + u;
            alert("inside resource for " + RowName);
            $('#pageContent').append(HTML_CreateRecipeBach_Row(Recipe, RowName));

            get('/rest/recipe/get/resources/resourcebatches/' + Recipe.recipeId + '', function (data) {


                var Row = $('#' + RowName + '');

                var ResourcesArr = data[0];
                var ResourceBathcesArr = data[1];

                for (var q = 0; q < ResourcesArr.length; q++) {
                    myhtml_Ingredients = HTML_CreateRecipeRelResRow( ResourcesArr[q] , ResourceBathcesArr[q] );
                    Row.find('.DTO_PROD_DropDown').append( myhtml_Ingredients );
                }

            });

        });


    })

}