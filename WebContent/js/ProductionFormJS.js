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

var RECIPE_OPTION_CLASSNAME = "RecipeOption_";
var RESOURCE_OPTION_CLASSNAME="Resource_Opt_";
var NAME_PROD_FORM="ProductionForm";
var NAME_RESO_FORM="ResourceForm";
var NAME_RECI_FORM="RecipeForm";
var CreateROW = 0;



// HTML functions --- --- --- --- --- --- --- --- ---
function HTML_setUpIngredients_Opt( container ){
    CreateROW = 0;

    var sel = $('<select>').prop("size","10");
    $(sel).prop("multiple","no");
    $(sel).css("width","100%");
    $(sel).appendTo(container);


    get('/rest/resources/get', function (data) {

         $.each(data, function (u, Resource ) {

             //var tag = '<option value="test"> test </option>';
            // var tag = '<option value="test"> test </option> \n ';
             var tag = '<option value="'+ Resource.resourceId +'"  data-ResourceId="'+ Resource.resourceId +'" ' +
                 'data-resourceName="'+ Resource.resourceName +'" data-reorder="'+ Resource.reorder +'" ' +
                 'data-inactive="'+ Resource.inactive +'">'+ Resource.resourceName +'</option>';
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

           $.each(data, function (i, Recipe) {

                //var tag = '<option value="test"> test </option>';
                var tag = '<option onclick="switchResourcesView(this)" class="'+RECIPE_OPTION_CLASSNAME+CreateROW+'" ' +
                               'value="'+RECIPE_OPTION_CLASSNAME+CreateROW+'" ' +
                               'data-recipeId="'+ Recipe.recipeId +'" ' +
                               'data-recipeEndDate="'+ Recipe.recipeEndDate +'" ' +
                               'data-recipeName="'+ Recipe.recipeName +'" ' +
                               'data-productAmount="'+ Recipe.productAmount +'" ' +
                               'data-authorUserId="'+ Recipe.authorUserId +'">'+ Recipe.recipeName +'</option>\n';
               CreateROW = CreateROW + 1;
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
    return '<form class="Create_FormContainer" data-table="'+NAME_RESO_FORM+'" >\n' +
        '    <h1> RESOURCE </h1>\n' +
        '    <table class="Creation_subPartContainer">\n' +
        '        <tr >\n' +
        '            <td>BatchId</td>\n' +
        '            <td><input class="ResourceDTO_BatchID" type="number " value="" placeholder="Enter Batch Id "></td>\n' +
        '        </tr>\n' +
        '        <tr>\n' +
        '            <td>Ammount</td>\n' +
        '            <td><input class="ResourceDTO_Amount" type="number "></td>\n' +
        '        </tr>\n' +
        '        <tr>\n' +
        '            <td>Supplier</td>\n' +
        '            <td><input class="ResourceDTO_Supplier" type="text"></td>\n' +
        '        </tr>\n' +
        '    </table>\n' +
        '    <div class="Creation_subPartContainer " style="grid-column-gap:15px;">\n' +
        '        <!--// column 1 -->\n' +
        '        <h1 style="margin-top: 15px;margin-bottom: -15px;">Select as a Batch of a Resource</h1>\n' +
        '        <div class="CreationForm_ListContainer ResourceForm_RecipeContainer" id="CreationForm_SelectContainer" data-test="test" >\n' +

        '        </div>\n' +
        '    </div>\n' +
        '    <button type="button" onclick="commitCreateTable(this)" > commit </button>\n' +
        '</form>';
}
function HTML_CreateRecipeBach_Form(){
    return '';
}

//Commits --- --- --- --- --- --- --- --- --- ---
function commitCreateTable(self){

    var table = $(self).parent('.Create_FormContainer');

    switch (table.attr("data-Table")) {
        case NAME_PROD_FORM:
            alert("Commit Production");
            break;
        case NAME_RESO_FORM:
            commitResourceTable(table);
            alert("Commit Resource");
            break;
        case NAME_RECI_FORM:
            alert("Commit Recipe");
            break;
    }
}
// Rest
function commitResourceTable(table){

   var ResourceDTO ={
       resourceBatchId:  table.find('.ResourceDTO_BatchID').val(),
       resourceBatchAmount:table.find('.ResourceDTO_Amount').val(),
       supplierName:     table.find('.ResourceDTO_Supplier').val(),
       isLeftover: "false",
       resourceId:    table.find('#CreationForm_SelectContainer').find('select').val(),
    };


     get(JSON.stringify(ResourceDTO),'rest/resources/create',function (data){
       alert("succes");
     },function (data) {
         alert(data);
     });
}

// Useability Functions
//production
function switchResourcesView(self){
// get/resources/resourcebatches/{recipeid}

    alert("recipe ID : "+ $(self).attr("data-recipeId") );

    get('/rest/recipe/get/resources/resourcebatches/'+$(self).attr("data-recipeId")+'', function (data) {

        $.each(data, function (u, ResourceAndBath) {

            alert("got some");
            /*//var tag = '<option value="test"> test </option>';
            var tag = '<option onclick="switchResourcesView(this)" class="'+RECIPE_OPTION_CLASSNAME+CreateROW+'" ' +
                'value="'+RECIPE_OPTION_CLASSNAME+CreateROW+'" ' +
                'data-recipeId="'+ Recipe.recipeId +'" ' +
                'data-recipeEndDate="'+ Recipe.recipeEndDate +'" ' +
                'data-recipeName="'+ Recipe.recipeName +'" ' +
                'data-productAmount="'+ Recipe.productAmount +'" ' +
                'data-authorUserId="'+ Recipe.authorUserId +'">'+ Recipe.recipeName +'</option>\n';
            CreateROW = CreateROW + 1;
            sel.append($(tag));*/

        });
    });
}


// Recipe

// Resource

// Initiators
function pop_resourceForm(self){
    if($(self).attr("data-active") === "true"){
        $('#overlay').empty();
        $(self).attr("data-active","false");
    }else{
        var html = HTML_CreateResourceBach_Form();
        $('#overlay').append(html);
        var SelectContainer = $('#CreationForm_SelectContainer');
        HTML_setUpIngredients_Opt(SelectContainer);
        $(self).attr("data-active","true");
    }
}

function start(){

    $('#generatedForm').append(HTML_CreateProduction_Form());
    HTML_setUpRecipe_Options($('#ProductionForm_RecipeContainer'));
    var SelectTag = $('#ProductionForm_RecipeContainer').find(' option');
    SelectTag.change();

   // $('#ProductionForm_RecipeContainer').append(GetRecipes());


}

