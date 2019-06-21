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
var NAME_RESBatch_FORM="ResourceForm";
var NAME_RECI_FORM="RecipeForm";

var POPUP_STATE_update      ="update";
var POPUP_STATE_createReady ="create";



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
function HTML_recipeResourceRow(container , ResourceDTO){


    var html ='' +
        '<tr>' +
        '   <td class="resourceDataHolder" data-R_ID="'+ResourceDTO.resourceId+'" data-ReORder="'+ResourceDTO.reorder+'" data-inactive="'+ResourceDTO.inactive+'" >'+ResourceDTO.resourceName+'</td>' +
        '   <td><input class="Recipe_REL_resource Ammount" step="any" type="number" placeholder="ammount Kg"></td>' +
        '   <td><input class="Recipe_REL_resource Tolerance" step="any" type="number" placeholder="Tolerance%"></td>' +
        '</tr>';
    container.append(html);


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
    return '<form class="Create_FormContainer" data-table="'+NAME_RESBatch_FORM+'" >\n' +
        '    <h1> Resource Batch </h1>\n' +
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
        '    <button type="button" onclick="commitResourceTable(this.parentElement.parentElement)" > commit </button>\n' +
        '</form>';
}
function HTML_CreateRecipeBach_Form(editState){
    return '' +
        '<form class="Create_FormContainer" data-test="jeg er parent Containeren"  data-editstate="'+editState+'">\n' +
        '    <h1> RECIPE </h1>\n' +
        '    <table class="Creation_subPartContainer">\n' +
        '        <tr>\n' +
        '            <td>Recipe Name</td>\n' +
        '            <td><input class="Recipe_name" type="text"></td>\n' +
        '        </tr>\n' +
        '        <tr>\n' +
        '            <td>Ammount</td>\n' +
        '            <td><input class="Recipe_Ammount"  step="any" type="number "></td>\n' +
        '        </tr>\n' +
        '        <tr>\n' +
        '            <td>Author id</td>\n' +
        '            <td><input class="Recipe_Author"  type="number"></td>\n' +
        '        </tr>\n' +
        '    </table>\n' +
        '    <div class="Creation_subPartContainer " style="grid-column-gap:15px;">\n' +
        '        <h1 style="margin-top: 15px;">Recipes Available</h1>\n' +
        '        <div class="ProductionForm_ListContainer ProductionForm_ResourceContainer" style="">\n' +
        '       </div>\n' +
        '    <div class="Creation_subPartContainer " style="grid-column-gap:15px;">\n' +
        '\n' +
        '        <Button type="button" onclick="RecepyPop_AddResource(this.parentElement.parentElement.parentElement)" > add ingredient </Button>\n' +
        '        <div class="ProductionForm_ListContainer ProductionForm_ResourceRelContainer" style="">\n' +
        '           <table size="10" name="selectionField" multiple="yes" style="width: 100%">\n' +
        '           </table>\n' +
        '        </div>\n' +
        '    </div>\n' +
        '    <div class="Creation_subPartContainer" style="grid-column-gap:15px;">\n' +
        '\n' +
        '    </div>\n' +
        '    <button type="button" onclick="commitRecipeTable(this.parentElement.parentElement)" > commit </button>\n' +
        '</form>';
}

//Commits --- --- --- --- --- --- --- --- --- ---
function commitResourceTable(table){

    var ResourceDTO ={
        resourceBatchId:  $(table).find('.ResourceDTO_BatchID').val(),
        resourceBatchAmount:$(table).find('.ResourceDTO_Amount').val(),
        supplierName:     $(table).find('.ResourceDTO_Supplier').val(),
        isLeftover: "false",
        resourceId:    $(table).find('#CreationForm_SelectContainer').find('select').val(),
    };

    get(JSON.stringify(ResourceDTO),'rest/resources/create',function (data){
        alert("succes");
    },function (data) {
        alert(data);
    });

}

function commitRecipeTable(container) {
    var switchStatement = $(container).attr("data-editstate");
    switch(switchStatement){
        case POPUP_STATE_update:
            break;
        case POPUP_STATE_createReady:
            commitCreateRecipe(container);
            break;
    }
}
    function commitCreateRecipe(container){
        alert("create");
        get("rest/recipe/get/newid", function (newIdRecipe) {

            var d = new Date();
            var month = d.getMonth() + 1;
            var dateString = d.getFullYear() +"-" + month +"-"+d.getUTCDate();
            alert(  dateString );

            var newID = newIdRecipe.recipeId;
            var RecipeDTO = {
                recipeId: newID,
                recipeEndDate: dateString,
                recipeName: $(container).find('.Recipe_name').val(),
                productAmount: $(container).find('.Recipe_Ammount').val(),
                authorUserId: $(container).find('.Recipe_Author').val(),
            };

            post(JSON.stringify(RecipeDTO), "rest/recipe/create", function () {

                //FOR ALLE RELATIONER TIL RESOURCES
                $(container).find('.ProductionForm_ResourceRelContainer table').children(' tr').each(function () {
                    var row = $(this);

                    var RelationRecipeResource = {
                        resourceId: row.find('.resourceDataHolder').attr("data-R_ID"),
                        recipeId: newID,
                        recipeEndDate: dateString,
                        resourceAmount: row.find('.Ammount').val(),
                        tolerance: row.find('.Tolerance').val()
                    };

                    post(JSON.stringify(RelationRecipeResource), "/rest/reciperesources/create", function (newData) {

                    });
                });
            });
            alert("Created Recipe");
        });
    }

// Useability Functions --- --- --- --- --- --- --
function switchResourcesView(self){
// get/resources/resourcebatches/{recipeid}

    // alert("recipe ID : "+ $(self).attr("data-recipeId") );

    get('/rest/recipe/get/resources/resourcebatches/'+$(self).attr("data-recipeId")+'', function (data) {

        $.each(data, function (u, ResourceAndBath) {

            //alert("got some");
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

// Useability Functions --- --- --- --- --- --- --
function RecepyPop_AddResource(container){

    var resource = $(container).find('.ProductionForm_ResourceContainer select option:selected');

    var ResourceDTO = {
        resourceId: resource.attr("data-resourceid"),
        resourceName: resource.attr("data-resourceName"),
        reorder: resource.attr("data-reorder"),
        inactive: resource.attr("data-inactive")
    };
    if(typeof resource.attr("data-resourceid") !== "undefined") {
        HTML_recipeResourceRow($(container).find('.ProductionForm_ResourceRelContainer table'), ResourceDTO);
    }
}
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

function pop_recipeForm_create(self){
    if($(self).attr("data-active") === "true"){

        $('#overlay').empty();
        $(self).attr("data-active","false");

    }else{

        var html = HTML_CreateRecipeBach_Form(POPUP_STATE_createReady);
        $('#overlay').append(html);
        var SelectContainer = $('.ProductionForm_ResourceContainer');
        HTML_setUpIngredients_Opt(SelectContainer);
        $(self).attr("data-active","true");

    }
}
function pop_recipeForm_update(self){

    if($(self).attr("data-active") === "true"){
        $('#overlay').empty();
        $(self).attr("data-active","false");
    }else{
        var html = HTML_CreateRecipeBach_Form(POPUP_STATE_update);
        $('#overlay').append(html);

        var SelectContainer = $('.ProductionForm_ResourceContainer');
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

