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

// TABLE REGULAR
function UI_RecipedeleteRow(row){
    if($(row).attr("data-editstate") != userTable_STATE_deleteReady ){
        $(row).find('.dto-table-column-DTO-formElement').hide();
        $(row).find('.DTO_PROD_DropButton').hide();
        $(row).attr("data-editstate",userTable_STATE_deleteReady)

    }else{
        $(row).find('.dto-table-column-DTO-formElement').show();
        $(row).find('.DTO_PROD_DropButton').hide();
        $(row).attr("data-editstate",userTable_STATE_NOCHANGE)
    }
}
function REST_recipeDeleteRow(row){

    var RecDTO = {
        recipeId: $(row).find('.recipeId').attr("data-value"),
        recipeEndDate: $(row).find('.recipeEndDate').attr("data-value"),
        recipeName: $(row).find('.recipeName').attr("data-value"),
        productAmount: $(row).find('.productAmount').attr("data-value"),
        authorUserId: $(row).find('.authorUserId').attr("data-value"),
    };

    Delete(null,"rest/recipe/delete/"+RecDTO.recipeId+"/"+RecDTO.recipeEndDate,function () {
    })
}
function CommitRecipeTableChanges(){
    $('#pageContent').find(' tr').each(function () {

        var commitState = $(this).find('.commit-state').prop("checked");

        if(commitState){
            switch ( $(this).attr("data-editstate") ) {
                case userTable_STATE_deleteReady:
                    REST_recipeDeleteRow( $(this) );
                    break;
                case userTable_STATE_NOCHANGE:
                    break;
            }
        }
    })
}

function HTML_CreateTableHeader(Recipe) {
    return '    ' +
        '<tr id="'+'RowHeader'+'" class="DTO_Table_Row DTO_RECI_GRID"  >\n' +
        '        <td>Prepare Commit</td>\n' +
        '        <td><p class="dto-table-column-DTO-formElement     recipeId"	  	data-value="'+Recipe.recipeId+'"                         > '+Recipe.recipeId+'</p></td>\n' +
        //'        <td><p class="dto-table-column-DTO-formElement     recipeName"     data-value="'+Recipe.recipeEndDate+'" style="display: none;visibility: hidden;width: 0;height: 0;">'+Recipe.recipeEndDate+'</p></td>\n' +
        '        <td><p class="dto-table-column-DTO-formElement     recipeEndDate" 	data-value="'+Recipe.recipeName+'"                       >'+Recipe.recipeName+'</p></td>\n' +
        '        <td><p class="dto-table-column-DTO-formElement     productAmount"  data-value="'+Recipe.productAmount+'"                    >'+Recipe.productAmount+' kg</p></td>\n' +
        '        <td><p class="dto-table-column-DTO-formElement     authorUserId"   data-value="'+Recipe.authorUserId+'"                     >'+Recipe.authorUserId+'</p></td>\n' +
        ' </tr>';
}

function HTML_CreateRecipeBach_Row(Recipe ,RowName){
    return '    ' +
        '<tr id="'+RowName+'" class="DTO_Table_Row DTO_RECI_GRID" data-editstate="'+userTable_STATE_NOCHANGE+'" data-aktiveediting="false"  >\n' +
        '        <td><input class="commit-state" type="checkbox" checked="unchecked"                                                         > </td>\n' +
        '        <td><p class="dto-table-column-DTO-formElement     recipeId"	  	  data-value="'+Recipe.recipeId+'"                         >'+Recipe.recipeId+'</p></td>\n' +
        '        <td style="display: none;visibility: hidden;width: 0;height: 0;" ><p class="dto-table-column-DTO-formElement     recipeEndDate"  data-value="'+Recipe.recipeEndDate+'" >'+Recipe.recipeEndDate+'</p></td>\n' +
        '        <td><p class="dto-table-column-DTO-formElement     recipeName" 	  data-value="'+Recipe.recipeName+'"                       >'+Recipe.recipeName+'</p></td>\n' +
        '        <td><p class="dto-table-column-DTO-formElement     productAmount"  data-value="'+Recipe.productAmount+'"                    >'+Recipe.productAmount+'kg</p></td>\n' +
        '        <td><p class="dto-table-column-DTO-formElement     authorUserId"   data-value="'+Recipe.authorUserId+'"                     >'+Recipe.authorUserId+'</p></td>\n' +
        '        <td class="DTO_Table_Row_beneathRow" style="grid-row: 2/3; grid-column: 1/7;"                                               >\n' +
        '            <button class="DTO_PROD_DropButton" onclick="toggleDropDowns(this)" data-hidden="1"                                     > Resources included.</button>\n' +
        '            <table  class="DTO_PROD_DropDown" style="display:none; position:relative; z-index:5;"                                   >\n' +
        '            </table>\n' +
        '        </td>\n' +
        '        <td class="DTO_Table_Row_MenuBox" style="grid-row: 1/3;">\n' +
        '            <button class="dto-table-button" name="update" onclick="pop_recipeForm_update(this.parentElement.parentElement)" style="grid-row: 1/2;">update</button>\n' +
        '            <button class="dto-table-button" name="delete" onclick="UI_RecipedeleteRow(this.parentElement.parentElement)" style="grid-row: 2/3;">delete</button>\n' +
        '        </td>\n' +
        ' </tr>';
}
function HTML_CreateRecipeRelResRow(resource,resourceRelation){
    return'\n' +
        '               <tr class="_1x4grid" >\n' +
        '                    <td class="resourceId"									> ' + resource.resourceId + ' 			</td>\n' +
        '                    <td class="resourceName"								     > ' + resource.resourceName + ' 		</td>\n' +
        '                    <td class="resourceAmount"								 > ' + resourceRelation.resourceAmount + ' 	</td>\n' +
        '                    <td class="tolerance"      							     > ' + resourceRelation.tolerance + ' 		</td>\n' +
        '                    <td class="reorder  absHidden"   				  data-content="'+ resource.reorder + '">   			</td>\n' +
    '                        <td class="inactive abshidden"                 data-content="'+ resource.inactive+ '">  			</td>\n' +
    '                </tr>' +
    '\n';
}

function loadRecipeTable(){

    // standard for naming the Rows.
    var RowNameInput = "rowNumber_";

    // For Each Recipe, getting oll the Recipes, with an iteration index of u
    get('/rest/recipe/get', function (data) {
        var newRecipe = {
            recipeId:"Opskrift ID",
            recipeEndDate:"Slutdato",
            recipeName:"Opskrift Navn",
            productAmount:"Mængde",
            authorUserId:"Bruger ID"
        };
        $('.Page_Content_pasterTable').append(HTML_CreateTableHeader(newRecipe));
        $.each(data, function (u, Recipe) {

            var RowName = RowNameInput + u;
            //alert("inside resource for " + RowName);
            $('.Page_Content_pasterTable').append(HTML_CreateRecipeBach_Row(Recipe, RowName));
            var Row = $('#' + RowName + '');
            Row.find('.commit-state').prop("checked",false);

            // Getting a double array arr[0] Resources and arr[1] is the RelationResourceRecipe
            // rest/recipe/get/relreciperesources/resources/{recipeid}
            get('rest/recipe/get/relreciperesources/resources/' + Recipe.recipeId + '', function (data) {

                var ResourceRelations = data[0];
                var ResourcesArr = data[1];

                if(ResourcesArr.length > 0){
                    var newResource = {
                        resourceId:"Råvare ID",
                        resourceName:"Råvare Navn",
                        reorder:"Genbestilling",
                        inactive:"Inaktiv"
                    };

                    var newResourceRelation = {
                        resourceId:"Råvare ID",
                        recipeId:"Opskrift ID",
                        recipeEndDate:"Opskrift Slutdato",
                        resourceAmount:"Råvare Mængde",
                        tolerance:"Tolerance"
                    };

                    var header_ingredients = HTML_CreateRecipeRelResRow( newResource , newResourceRelation );
                    Row.find('.DTO_PROD_DropDown').append( header_ingredients );
                }

                // for all Resources, add a them as HTML to the drop down
                for (var q = 0; q < ResourcesArr.length; q++) {
                    var myhtml_Ingredients = HTML_CreateRecipeRelResRow( ResourcesArr[q] , ResourceRelations[q] );
                    Row.find('.DTO_PROD_DropDown').append( myhtml_Ingredients );
                }

                // updating number of the button, shows how many elements are in the dropdown.
                var btn = Row.find('.DTO_PROD_DropButton');
                var text1 =btn.text();
                btn.text( text1 + ResourcesArr.length );

            });
        });
    })
}


// MODAL functions
// POPUP METHODS
function pop_recipeForm_create(self){
    if($(self).attr("data-active") === "true"){

        $('#overlay').empty();
        $(self).attr("data-active","false");

    }else{

        var html = HTML_CreateRecipeBach_Form(POPUP_STATE_createReady);
        $('#overlay').append(html);
        var SelectContainer = $('.RecipeForm_ResourceContainer');
        HTML_setUpIngredients_Opt(SelectContainer);
        $(self).attr("data-active","true");

    }
}
function pop_recipeForm_update(container){


    if( $(container).attr("data-active") === "true"){
        $('#overlay').empty();
        $(container).attr("data-active","false");


    }else{

        var RecipeDTO = {
            recipeId: $(container).find('.recipeId').val(),
            recipeEndDate: $(container).find('.recipeEndDate').val(),
            recipeName: $(container).find('.Recipe_name').val(),
            productAmount: $(container).find('.Recipe_Ammount').val(),
            authorUserId: $(container).find('.Recipe_Author').val(),
        };


        var html = HTML_CreateRecipePop_filled(RecipeDTO);
        $('#overlay').append(html);

        var SelectContainer = $('.RecipeForm_ResourceContainer');
        HTML_setUpIngredients_Opt(SelectContainer);
        $(container).attr("data-active","true");

        var rowRelationResources = $(container).find('.DTO_PROD_DropDown');
        var container = $('#ModalResourceContainer table');
        $(rowRelationResources).children(' tr').each(function () {

            var ResourceDTO ={
                resourceId:"",
                resourceName:"",
                reorder:"",
                inactive:""
            };
            var RelationDTO ={
                resourceId:$(this).children('.rel_resourceId').text(),
                recipeId:$(this).children('.recipe_name').text(),
                recipeEndDate:RecipeDTO.recipeEndDate,
                resourceAmount:$(this).children('.rel_Amount').text(),
                tolerance:$(this).children('.rel_tolerance').text()
            };


            RecepyPop_AddResourceDTO(container,RelationDTO);
        });

    }

}

// popup commit
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
    get("rest/recipe/get/newid", function (newIdRecipe) {

        var d = new Date();
        var month = d.getMonth() + 1;
        var dateString = d.getFullYear() +"-" + month +"-"+d.getUTCDate();

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
            $(container).find('.RecipeForm_ResourceRelContainer table').children(' tr').each(function () {
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

//HTML Creates
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
        '        <div class="ProductionForm_ListContainer RecipeForm_ResourceContainer" style="">\n' +
        '       </div>\n' +
        '    <div class="Creation_subPartContainer " style="grid-column-gap:15px;">\n' +
        '\n' +
        '        <Button type="button" onclick="RecepyPop_AddResource(this.parentElement.parentElement.parentElement)" > add ingredient </Button>\n' +
        '        <div class="ProductionForm_ListContainer RecipeForm_ResourceRelContainer" style="">\n' +
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
function HTML_CreateRecipePop_filled(DTO){
    return '' +
        '<form class="Create_FormContainer"  data-editstate="'+POPUP_STATE_update+'">\n' +
        '    <h1> RECIPE </h1>\n' +
        '    <table class="Creation_subPartContainer" data-id="'+DTO.recipeId+'" data-date="'+DTO.recipeEndDate+'">\n' +
        '        <tr>\n' +
        '            <td>Recipe Name</td>\n' +
        '            <td><input class="Recipe_name" value="'+DTO.recipeName+'" type="text"></td>\n' +
        '        </tr>\n' +
        '        <tr>\n' +
        '            <td>Ammount</td>\n' +
        '            <td><input class="Recipe_Ammount"  value="'+DTO.productAmount+'" step="any" type="number "></td>\n' +
        '        </tr>\n' +
        '        <tr>\n' +
        '            <td>Author id</td>\n' +
        '            <td><input class="Recipe_Author" value="'+DTO.authorUserId+'" type="number"></td>\n' +
        '        </tr>\n' +
        '    </table>\n' +
        '    <div class="Creation_subPartContainer " style="grid-column-gap:15px;">\n' +
        '        <h1 style="margin-top: 15px;">Recipes Available</h1>\n' +
        '        <div class="ProductionForm_ListContainer RecipeForm_ResourceContainer" style="">\n' +
        '       </div>\n' +
        '    <div class="Creation_subPartContainer " style="grid-column-gap:15px;">\n' +
        '\n' +
        '        <Button type="button" onclick="RecepyPop_AddResource(this.parentElement.parentElement.parentElement)" > add ingredient </Button>\n' +
        '        <div id="ModalResourceContainer" class="ProductionForm_ListContainer RecipeForm_ResourceRelContainer" style="">\n' +
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
function HTML_recipeResourceRow(ResourceDTO, RelationDTO){
    return '' +
        '<tr>' +
        '   <td class="resourceDataHolder" data-r_id="'+ResourceDTO.resourceId+'" >'+ResourceDTO.resourceName+'</td>' +
        '   <td><input class="Recipe_REL_resource Ammount" value="" step="any" type="number" placeholder="ammount Kg"></td>' +
        '   <td><input class="Recipe_REL_resource Tolerance" value="" step="any" type="number" placeholder="Tolerance%"></td>' +
        '</tr>';

}
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

// Useability Functions --- --- --- --- --- --- --
function RecepyPop_AddResource(container){
    var resource = $(container).find('.RecipeForm_ResourceContainer select option:selected');
    var ResourceDTO = {
        resourceId: resource.attr("data-resourceid"),
        resourceName: resource.attr("data-resourceName"),
        reorder: resource.attr("data-reorder"),
        inactive: resource.attr("data-inactive")
    };

    if(typeof resource.attr("data-resourceid") !== "undefined") {
        $(container).find('.RecipeForm_ResourceRelContainer table').append(HTML_recipeResourceRow(ResourceDTO));
    }

}
function RecepyPop_AddResourceDTO(container , ResourceDTO){
    alert("child detected");
    $(container).append(HTML_recipeResourceRow(ResourceDTO, RelationDTO));
}


