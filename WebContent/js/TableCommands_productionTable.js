
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

//CRUDE
function LoadProductions(){
    // GET all ProductBatcehs
    get("/rest/productbatch/get",function (data) {
        $.each(data, function (i, productbatch) {

            get("/rest/recipe/get/"+productbatch.recipeId+"/"+productbatch.recipeEndDate+" ",function (recipe) {

            var RelResourceList = "\n";
            var intResourceCounters = 0;
            // GET the product bath's Resource Batches.
            get("/rest/productbatch/get/resources/"+ productbatch.productBatchId+"",function (data) {
                $.each(data, function (i, RelResourceBatch) {
                    //todo GET Ingredient Name aswell.
                    RelResourceList = RelResourceList +HTML_generateResourceBatchRow(RelResourceBatch);
                    intResourceCounters = intResourceCounters + 1;
                });
            });

            $('#dto-table-container').append(HTML_generateProductionRow( productbatch, recipe , RelResourceList, intResourceCounters));




            });
        });
    })
}

/*
productBatchId;
Date creationDate;
String productionStatus;
Date productionEndDate;
boolean inactive;
int recipeId;
Date recipeEndDate;
int productionLeaderUserId;

*/
// UI generation.
function HTML_generateProductionRow(productbatch, Recipe ,HTMLResourceLines, resourceNumber ){
    return '' +
        '<tr class="DTO_Table_Row DTO_PROD_GRID">\n' +
        '    <td><div    class="dto-table-column-DTO-formElement "   name="'+productbatch.productBatchId+'" > '+productbatch.productBatchId+' </div>          </td>\n' +
        '    <td><p  type="text" class="dto-table-column-DTO-formElement "   name="'+Recipe.recipeName+'" data-RecipeId="'+Recipe.recipeId+'" data-RecipeEndDate="'+Recipe.recipeEndDate+'"></p>R_name</td>\n' +
        '    <td><p  type="date" class="dto-table-column-DTO-formElement "   name="'+productbatch.creationDate+'"  >'+productbatch.creationDate+'</p>  </td>\n' +
        '    <td><p  class="dto-table-column-DTO-formElement "               name="'+productbatch.productionStatus+'"> '+productbatch.productionStatus+' </p>\n' +
        '    <td><p  type="number" class="dto-table-column-DTO-formElement"  name="'+productbatch.productionLeaderUserId+'" >'+productbatch.productionLeaderUserId+'</p></td>\n' +
        '    <td class="DTO_Table_Row_beneathRow" style="grid-row: 2/3; grid-column: 1/7;" >\n' +
        '        <button class="DTO_PROD_DropButton" onclick="toggleDropDowns(this)" data-hidden="1" > Resources Used '+resourceNumber+' </button>\n' +
        '        <table  class="DTO_PROD_DropDown" style="display:none;"  >\n' +
        '            \n' + HTMLResourceLines +
        '        </table>\n' +
        '    </td>\n' +
        '</tr>';
}
function HTML_generateResourceBatchRow(RelResourceBatch){

    return '\n' +
        '                <tr class="_1x4grid" >\n' +
        '                    <td class="resourceBatch_Id"    name="RB_id"> '+RelResourceBatch.resourceBatchId+' </td>\n' +
        '                    <td class="recipe_name"         name="I_Name"> I_Name </td>\n' + //todo ResourceName
        '                    <td class="rel_net_Amount"      name="Net_Amount"> '+netAmount+' </td>\n' +
        '                    <td class="rel_tara"            name="Tara"> '+RelResourceBatch.tara+' </td>\n' +
        '                </tr>';
}

//UI interaction.
function toggleDropDowns(self){

        if($(self).attr("data-hidden") === "1"){
            $(self).siblings('.DTO_PROD_DropDown').each(function () {
                $(this).show();
            });
            $(self).attr("data-hidden",0);

        }
        else {
            $(self).siblings('.DTO_PROD_DropDown').each(function () {
                $(this).hide();
            });
            $(self).attr("data-hidden", 1);
        }
}