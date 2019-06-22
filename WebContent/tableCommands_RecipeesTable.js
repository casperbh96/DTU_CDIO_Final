

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

function HTML_CreateRecipeBach_Row(Recipe, ResourcesContent){
    return '    ' +
        '<tr class="DTO_Table_Row DTO_RECI_GRID" data-editstate="'+userTable_STATE_NOCHANGE+'" data-aktiveediting="false" >\n' +
        '        <td ><input class="commit-state" type="checkbox" checked="unchecked"                                                        > </td>\n' +
        '        <td><p class="dto-table-column-DTO-formElement     recipeId"                                                                >'+Recipe.recipeId+'</p></td>\n' +
        '        <td><p class="dto-table-column-DTO-formElement     recipeName"  style="display: none;visibility: hidden;width: 0;height: 0;">'+Recipe.recipeEndDate+'</p></td>\n' +
        '        <td><p class="dto-table-column-DTO-formElement     recipeEndDate"                                                           >'+Recipe.recipeName+'</p></td>\n' +
        '        <td><p class="dto-table-column-DTO-formElement     productAmount"                                                           >'+Recipe.productAmount+'</p></td>\n' +
        '        <td><p class="dto-table-column-DTO-formElement     authorUserId"                                                            >'+Recipe.authorUserId+'</p></td>\n' +
        '        <td class="DTO_Table_Row_beneathRow" style="grid-row: 2/3; grid-column: 1/7;"                                               >\n' +
        '            <button class="DTO_PROD_DropButton" onclick="toggleDropDowns(this)" data-hidden="1"                                     > Resources included.</button>\n' +
        '            <table  class="DTO_PROD_DropDown" style="display:none; position:relative; z-index:1;"                                   >\n' +
        '' + ResourcesContent +
        '            </table>\n' +
        '        </td>\n' +
        '        <td class="DTO_Table_Row_MenuBox" style="grid-row: 1/3;">\n' +
        '            <button class="dto-table-button" name="update" onclick="dto_table_row_updateToggle(this.parentElement.parentElement)" style="grid-row: 1/2;">update</button>\n' +
        '            <button class="dto-table-button" name="delete" onclick="dto_table_row_deleteToggle(this.parentElement.parentElement)" style="grid-row: 2/3;">delete</button>\n' +
        '        </td>\n' +
        ' </tr>';
}
function HTML_CreateRecipeRelResRow(Relation_Rec_Res){
     var html = '' +
        '               <tr class="_1x4grid" >\n' +
        '                   <td class="resourceId"     > '+Relation_Rec_Res.resourceId+'         </td>\n' +
        '                   <td class="resourceName"   > '+Relation_Rec_Res.resourceName+'        </td>\n' +
        '                   <td class="resourceAmount" > '+Relation_Rec_Res.resourceAmount+'      </td>\n' +
        '                   <td class="tolerance"      > '+Relation_Rec_Res.tolerance+'    </td>\n' +
        '                </tr>';
     return html;
}
function loadRecipeTable(){

    get('/rest/recipe/get',function (data) {
        $.each(data, function (u, Recipe){

            var relationalTable ="";
            get('/rest/reciperesources/get',function (data) {
                $.each(data, function (i, RelRecRes){

                    var htmlRelation_RecipeResource={
                        resourceId:RelRecRes.resouceId,
                        resourceName:"",
                        resourceAmount:RelRecRes.resourceAmount,
                        tolerance:RelRecRes.tolerance
                    };

                    get('/rest/resources/get/'+RelRecRes.resouceId,function (data) {
                        $.each(data, function (i, Resource){
                            alert("resName " + RelRecRes.resouceId + ":" + Resource.resourceName);
                            htmlRelation_RecipeResource.resourceName = Resource.resourceName;
                        });
                    },);

                    relationalTable = relationalTable + HTML_CreateRecipeRelResRow(htmlRelation_RecipeResource);

                });
            },);

            var html = HTML_CreateRecipeBach_Row(Recipe,relationalTable);
            $("#dto-table-container").append(html);
        });
    },)

}