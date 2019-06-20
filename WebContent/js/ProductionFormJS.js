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


var NAME_PROD_FORM="";
var NAME_RESO_FORM="";
var NAME_RECI_FORM="";
var RECIPEROW = 0;
var RECIPE_OPTION_CLASSNAME = "RecipeOption_";


// HTML functions --- --- --- --- --- --- --- --- ---
function HTML_setUpIngredients_Opt(FormContainer){


    var sel = $('<select>').prop("size","10");
    sel.prop("multiple","no");
    sel.css("width","100%");
    sel.appendTo(container);


    get('/rest/recipe/get', function (data) {

        $.each(data, function (u, Recipe) {

            //var tag = '<option value="test"> test </option>';
            var tag = '<option class="'+RECIPE_OPTION_CLASSNAME+RECIPEROW+'" ' +
                'value="'+RECIPE_OPTION_CLASSNAME+RECIPEROW+'" ' +
                'data-recipeId="'+ Recipe.recipeId +'" ' +
                'data-recipeEndDate="'+ Recipe.recipeEndDate +'" ' +
                'data-recipeName="'+ Recipe.recipeName +'" ' +
                'data-productAmount="'+ Recipe.productAmount +'" ' +
                'data-authorUserId="'+ Recipe.authorUserId +'">'+ Recipe.recipeName +'</option>\n';
            RECIPEROW = RECIPEROW + 1;
            sel.append($(tag));

        });
    });
}
function HTML_setUpRecipe_Options(container){

    var sel = $('<select>').prop("size","10");

    sel.prop("multiple","yes");
    sel.css("width","100%");
    sel.appendTo(container);


    get('/rest/recipe/get', function (data) {

           $.each(data, function (u, Recipe) {

                //var tag = '<option value="test"> test </option>';
                var tag = '<option onclick="switchResourcesView(this)" class="'+RECIPE_OPTION_CLASSNAME+RECIPEROW+'" ' +
                               'value="'+RECIPE_OPTION_CLASSNAME+RECIPEROW+'" ' +
                               'data-recipeId="'+ Recipe.recipeId +'" ' +
                               'data-recipeEndDate="'+ Recipe.recipeEndDate +'" ' +
                               'data-recipeName="'+ Recipe.recipeName +'" ' +
                               'data-productAmount="'+ Recipe.productAmount +'" ' +
                               'data-authorUserId="'+ Recipe.authorUserId +'">'+ Recipe.recipeName +'</option>\n';
               RECIPEROW = RECIPEROW + 1;
               sel.append($(tag));

           });
   });
}
// tables
function HTML_CreateProduction_Form(){
    return '<form class="ProductionFormContainer1">\n' +
        '    <h1> title </h1>\n' +
        '    <table class="Creation_subPartContainer">\n' +
        '       <tr><td> <span>id</span> </td><td>   <input type="number" value="x"> </td></tr>\n' +
        '    </table>\n' +
        '    <div class="Creation_subPartContainer " style="grid-column-gap:15px;" >\n' +
        '        <!--// column 1 -->\n' +
        '        <h1 style="margin-top: 15px;">Recipes Available</h1>\n' +
        '        <div class="ProductionForm_ListContainer" id="ProductionForm_RecipeContainer" style="" >\n' +
        ''+
        '        </div>\n' +
        '    </div>\n' +
        '    <div class="Creation_subPartContainer " style="grid-column-gap:15px;" >\n' +
        '        <!--// column 1 -->\n' +
        '        <h1> ResourceBatches Assigned </h1>\n' +
        '        <div class="ProductionForm_ListContainer" id="ProductionForm_ResourceContainer" style="" >\n' +
        '            <select size="10" name="selectionField" multiple="yes" style="width: 100%">\n' +
        '                <option id="Ing_opt_1" value="CA" >California -- CA </option>\n' +
        '            </select>\n' +
        '        </div>\n' +
        '    </div>\n' +
        '    <div class="Creation_subPartContainer" style="grid-column-gap:15px;" >\n' +
        '        <!--// column 1 -->\n' +
        '        <table style="width: 100%" >\n' +
        '            <tr class="_1x2grid" style="width: 100%" >\n' +
        '                <td><span> Production Leader Id </span></td>\n' +
        '                <td><input id="ProductionForm_produktLeaderID" type="text" style="float:right;"></td>\n' +
        '            </tr>\n' +
        '\n' +
        '        </table>\n' +
        '    </div>\n' +
        '</form>';

}
function HTML_CreateResourceBach_Form(){
    return '';
}
function HTML_CreateRecipeBach_Form(){
    return '';
}

//Commits --- --- --- --- --- --- --- --- --- ---
function Commit(self, container){
    switch (self.attr("CreationTableName")) {
        case NAME_PROD_FORM:

            break;
        case NAME_RESO_FORM:

            break;
        case NAME_RECI_FORM:

            break;
    }
}
// Rest


// Useability Functions
//production
function switchResourcesView(self){
// get/resources/resourcebatches/{recipeid}

    alert("recipe ID : "+ $(self).attr("data-recipeId") );

    get('/rest/recipe/get/resources/resourcebatches/'+$(self).attr("data-recipeId")+'', function (data) {

        $.each(data, function (u, ResourceAndBath) {

            alert("got some");
            /*//var tag = '<option value="test"> test </option>';
            var tag = '<option onclick="switchResourcesView(this)" class="'+RECIPE_OPTION_CLASSNAME+RECIPEROW+'" ' +
                'value="'+RECIPE_OPTION_CLASSNAME+RECIPEROW+'" ' +
                'data-recipeId="'+ Recipe.recipeId +'" ' +
                'data-recipeEndDate="'+ Recipe.recipeEndDate +'" ' +
                'data-recipeName="'+ Recipe.recipeName +'" ' +
                'data-productAmount="'+ Recipe.productAmount +'" ' +
                'data-authorUserId="'+ Recipe.authorUserId +'">'+ Recipe.recipeName +'</option>\n';
            RECIPEROW = RECIPEROW + 1;
            sel.append($(tag));*/

        });
    });
}


// Recipe

// Resource


function start(){

    $('#generatedForm').append(HTML_CreateProduction_Form());
    HTML_setUpRecipe_Options($('#ProductionForm_RecipeContainer'));
    var SelectTag = $('#ProductionForm_RecipeContainer').find(' option');
    SelectTag.change();

   // $('#ProductionForm_RecipeContainer').append(GetRecipes());


}

