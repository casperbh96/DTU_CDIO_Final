

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

var ResBatch_STATE_deleteReady   ="delete";
var ResBatch_STATE_editReady     ="edit";
var ResBatch_STATE_createReady   ="create";
var ResBatch_STATE_NOCHANGE      ="none";


var RESBatch_ID_NAMING = "resource_";
var ROWINDEX = 1;
var ResourceBatch = {
    resourceBatchId:"",
    resourceBatchAmount:"",
    supplierName:"",
    isLeftover:"",
    resourceId:""
};
function HTML_GenerateResourceBatchRow(ResourceBatch, RecipeName, rowName){
    return '' +
        '       <tr class="DTO_Table_Row DTO_RESOURCE_BATCH_GRID '+rowName+'"       data-editstate="'+ResBatch_STATE_NOCHANGE+'" data-aktiveediting="false" >\n' +
        '            <td><input class="commit-state" type="checkbox"        name="checkBox" checked="unchecked"></td>\n' +
        '            <td><p  class="dto-table-column-DTO-formElement ResBatch_Id" value="Res_active">        '+ResourceBatch.resourceBatchId+' </p></td>\n' +
        '            <td><p  class="dto-table-column-DTO-formElement ResBatch_ResName" value="Res_active">   '+RecipeName+' </p></td>\n' +
        '            <td><p  class="dto-table-column-DTO-formElement ResBatch_Ammount" value="Res_active">   '+ResourceBatch.resourceBatchAmount+' </p></td>\n' +
        '            <td><p  class="dto-table-column-DTO-formElement ResBatch_supplier" value="Res_active">  '+ResourceBatch.supplierName+' </p></td>\n' +
        '            <td class="DTO_Table_Row_MenuBox" style="display: block" >\n' +
        '                <button class="dto-table-button resTableBtn_delete" name="delete" style="width: 100%" onclick=" ResBatchTable_UI_deleteRow(this.parentElement.parentElement)"  >delete</button>\n' +
        '            </td>\n' +
        '			 <td><p  class="dto-table-column-DTO-formElement ResBatch_isLeftover" value="Res_active" style="display:none;width:0;height:0;overflow: hidden;">  '+ResourceBatch.isLeftover+' </p></td>\n' +
        '			 <td><p  class="dto-table-column-DTO-formElement ResBatch_resourceId" value="Res_active" style="display:none;width:0;height:0;overflow: hidden;">  '+ResourceBatch.resourceId+' </p></td>\n' +
        '        </tr>';
}

//UI functions
function ResBatchTable_UI_deleteRow(row){
    switch ($(row).attr("data-editstate")) {
        case ResBatch_STATE_createReady:
            alert("create");
            $(row).remove();
            break;
        case ResBatch_STATE_deleteReady:
            $(row).attr("data-editstate",ResBatch_STATE_editReady);
            $(row).find(' .dto-table-column-DTO-formElement').show();
            break;
        default:
            $(row).attr("data-editstate", ResBatch_STATE_deleteReady );
            $(row).find('.dto-table-column-DTO-formElement').hide();
    }
}
function ResBatchTable_UI_insertCreaterRow(ResourceBatch,RecipeName){
    var rowName = RESBatch_ID_NAMING + ROWINDEX;
    $('#pageContent').append(HTML_GenerateResourceBatchRow(ResourceBatch,RecipeName, rowName));

    ROWINDEX = ROWINDEX + 1 ;
    // uncheck all Users Roles
    var thisRow = $('#'+RowName+'');
    thisRow.attr("data-editstate",Res_STATE_createReady);
    thisRow.css("background-color","green");

    // Get All Roles Belonging to the User. and for each, check the Belonging checkbox if he has it.
}
// UI AND REST FUNCTIONS
function LoadResourcesBatches(){
    // GET all ProductBatcehs
    get("/rest/resourcebatch/get",function (data) {
        $.each(data, function (i, Resource) {
            get("/rest/resources/get/"+Resource.resourceId+"",function (resources) {

                var RowName = RESBatch_ID_NAMING + ROWINDEX;
                $('#pageContent').append(HTML_GenerateResourceBatchRow(Resource,resources.resourceName,RowName));
                $('.'+RowName+'').find('.commit-state').prop("checked",false);
                ROWINDEX = ROWINDEX +1;

            });
        });
    })
}

// REST Functions
function ResourceBatchTable_REST_createResource(row){

    var ResourceBatch = {
        resourceBatchId:$(row).find('.ResBatch_Id'),
        resourceBatchAmount:$(row).find('.ResBatch_Ammount'),
        supplierName:$(row).find('.ResBatch_supplier'),
        isLeftover:$(row).find('.ResBatch_isLeftover'),
        resourceId:$(row).find('.ResBatch_resourceId')
    };
    post( JSON.stringify( ResourceBatch ) ,"rest/resourcebatch/create" , function (data) {
        alert("created " + ResourceBatch.toString());
    });
}
function ResourceBatchTable_REST_DeleteResource(row){

    var ResourceBatch = {
        resourceBatchId:$(row).find('.ResBatch_Id'),
        resourceBatchAmount:$(row).find('.ResBatch_Ammount'),
        supplierName:$(row).find('.ResBatch_supplier'),
        isLeftover:$(row).find('.ResBatch_isLeftover'),
        resourceId:$(row).find('.ResBatch_resourceId')
    };
    Delete( JSON.stringify(ResourceDTO), "rest/resources/delete" , function (data) {

    });
}
function ResourceBatchTable_commitTable(){
    //$(row).attr("data-editState")
    $('#pageContent').children('tr').each( function () {
        var commitState = $(this).find('.commit-state').prop("checked");
        var id = $(this).attr("id");

        if(commitState){
            switch ( $(this).attr("data-editstate") ) {
                case Res_STATE_NOCHANGE:
                    break;
                case Res_STATE_deleteReady:
                    ResourceBatchTable_REST_DeleteResource( $(this) );
                    break;
                case Res_STATE_editReady:
                    break;
                case Res_STATE_createReady:
                    ResourceBatchTable_REST_createResource( $(this) );
                    break;
            }
        }
    });
}
