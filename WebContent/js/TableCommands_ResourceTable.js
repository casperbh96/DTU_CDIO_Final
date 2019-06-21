

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

var Res_STATE_deleteReady   ="delete";
var Res_STATE_editReady     ="edit";
var Res_STATE_createReady   ="create";
var Res_STATE_NOCHANGE      ="none";


var RES_ID_NAMING = "resource_";
var ROWINDEX = 1;

function HTML_GenerateResourceRow(Resource,RowName){
    return '\n' +
        '<tr class="DTO_Table_Row DTO_RESOURCE_GRID" id="'+RowName +'" data-editstate="'+Res_STATE_NOCHANGE+'" data-aktiveediting="false" >\n' +
        '            <td ><input  class="commit-state" type="checkbox"        name="checkBox" checked="unchecked" ></td>\n' +
        '            <td class="dto-table-column-DTO-formElement"><div      class="resourceId"> '+Resource.resourceId+'</div>          </td>\n' +
        '            <td class="dto-table-column-DTO-formElement"><input    class="resourceName" value="'+Resource.resourceName+'" disabled>   </td>\n' +
        '            <td class="dto-table-column-DTO-formElement"><select   class="reorder" disabled> \n' +
        '                <option value="true"> Re-Order </option>\n' +
        '                <option value="false"> No Re-Order </option>\n' +
        '            </select>\n' +
        '            <td class="dto-table-column-DTO-formElement "><p       class="active" data-activestate=""  > INACTIVE DATA HERE </p></td>\n' +
        '            <td class="DTO_Table_Row_MenuBox" style="grid-row: 1/3; grid-column: 6/7;">\n' +
        '                <button class="dto-table-button resTableBtn_update" name="update" onclick="ResTable_UI_updateRow(this.parentElement.parentElement)" style="grid-row: 1/2;">update</button>\n' +
        '                <button class="dto-table-button resTableBtn_delete" name="delete" onclick="ResTable_UI_deleteRow(this.parentElement.parentElement)" style="grid-row: 2/3;">delete</button>\n' +
        '            </td>\n' +
        '        </tr>';





    /*'\n' +
        '<tr class="DTO_Table_Row DTO_RESOURCE_GRID" id="'+RowName +'" data-editstate="'+Res_STATE_NOCHANGE+'" data-aktiveediting="false" >\n' +
        '            <td ><input  class="commit-state" type="checkbox"        name="checkBox" checked="unchecked" ></td>\n' +
        '            <td class="dto-table-column-DTO-formElement"><div      class="resourceId"> '+Resource.resourceId+'</div>          </td>\n' +
        '            <td class="dto-table-column-DTO-formElement"><input    class="resourceName" value="'+Resource.resourceName+'" disabled>   </td>\n' +
        '            <td class="dto-table-column-DTO-formElement"><select   class="reorder" disabled> \n' +
        '                <option value="true"> Re-Order </option>\n' +
        '                <option value="false"> No Re-Order </option>\n' +
        '            </select>\n' +
        '            <td class="dto-table-column-DTO-formElement "><p       class="active" data-activestate=""  > INACTIVE DATA HERE </p></td>\n' +
        '            <td class="DTO_Table_Row_MenuBox" style="grid-row: 1/3; grid-column: 6/7;">\n' +
        '                <button class="dto-table-button resTableBtn_update" name="update" onclick="ResTable_UI_updateRow(this.parentElement.parentElement)" style="grid-row: 1/2;">update</button>\n' +
        '                <button class="dto-table-button resTableBtn_delete" name="delete" onclick="ResTable_UI_deleteRow(this.parentElement.parentElement)" style="grid-row: 2/3;">delete</button>\n' +
        '            </td>\n' +
        '        </tr>';*/
}

//UI functions
function ResTable_UI_updateRow(row){
    if($(row).attr("data-editstate") !== Res_STATE_deleteReady){
        $(row).attr("data-editstate",Res_STATE_editReady);
        $(row).find(' input').prop('disabled', false);
        $(row).find(' select').prop('disabled', false);
        $(row).css("background-color","yellow");
    }
}
function ResTable_UI_deleteRow(row){
    switch ($(row).attr("data-editstate",Res_STATE_createReady)) {
        case Res_STATE_createReady:
            $(row).remove();
            break;
        case Res_STATE_deleteReady:
            $(row).attr("data-editstate",Res_STATE_editReady);
            $(row).children(' .dto-table-column-DTO-formElement').show();
            break;
        default:
            $(row).attr("data-editstate",userTable_STATE_deleteReady);
            $(row).children(' .dto-table-column-DTO-formElement').hide();
    }
}
function ResTable_UI_insertCreaterRow(){

    RES_ID_NAMING = "resource_";
    // Create the Row
    var RowName = RES_ID_NAMING + ROWINDEX ;
    var Resource = {
        resourceId: "Id to come",
        resourceName:"resourceName",
        reorder: "reOrder",
        inactive:false
    };
    $('#pageContent').append(HTML_GenerateResourceRow(Resource,RowName))

    ROWINDEX = ROWINDEX + 1 ;
    // uncheck all Users Roles
    var thisRow = $('#'+RowName+'');
    thisRow.attr("data-editstate",Res_STATE_createReady);

    thisRow.find('.resTableBtn_update').hide();
    thisRow.find(' input').prop('disabled', false);
    thisRow.find(' select').prop('disabled', false);
    thisRow.css("background-color","green");


    // Get All Roles Belonging to the User. and for each, check the Belonging checkbox if he has it.
}

/*function userTable_CREATE_insertCreaterRow(){
    // Create the Row
    var rowId = 'rowNumber_'+userTable_ROWINDEX;
    var user = {
        userId: "x",
        username:"newUserName",
        initials:"Initals",
        inactive:false
    }
    $('#pageContent').append(userTable_HTML_GenerateUserRow(user,rowId,userTable_STATE_createReady ));
    userTable_ROWINDEX = userTable_ROWINDEX + 1 ;
    // uncheck all Users Roles
    var thisRow = $('#'+rowId+'');
    thisRow.children('.DTO_Table_Row_beneathRow').find(' input').prop("checked",false);
    thisRow.css("background-color","green");
    thisRow.find('.User_UpdateBtn').hide();
    thisRow.find(' input').prop('disabled', false);
    // Get All Roles Belonging to the User. and for each, check the Belonging checkbox if he has it.

}*/

// UI AND REST FUNCTIONS
function LoadResources(){
    // GET all ProductBatcehs
    get("/rest/resources/get",function (data) {
        $.each(data, function (i, Resource) {
            var RowName = RES_ID_NAMING + ROWINDEX;
            $('#pageContent').append(HTML_GenerateResourceRow(Resource,RowName));

            $('#'+RowName+'').find('.commit-state').prop("checked",false);
            var ActiveTag  = $('#'+RowName+'').find('.active');
            if(Resource.inactive == false) {
                ActiveTag.text("active");
                ActiveTag.prop("data-activestate","false");
            }else{
                ActiveTag.text("in active ");
                ActiveTag.prop("data-activestate","true");
            }
            ROWINDEX = ROWINDEX +1;
        });
    })
}

// REST Functions
function ResourceTable_REST_createResource(row){
    //ResTable_getNewId(function (data) {

    var ResourceDTO ={
        resourceId:  1123,
        resourceName:$(row).find('.resourceName').val(),
        reorder:$(row).find('.reorder').val(),
        inactive:false
    };

    alert("resourceId :"+ ResourceDTO.resourceId +" , resourceName:"+ ResourceDTO.resourceName +" , reorder:"+ ResourceDTO.reorder +" ,inactive :" + ResourceDTO.inactive);
    post( JSON.stringify( ResourceDTO ) ,"rest/resources/create" , function (data) {
        alert("created " + ResourceDTO.toString());
    });

    //});
}
function ResourceTable_REST_updateUser(row){


    var ResourceDTO ={
        resourceId:  $(row).find('.resourceId').text(),
        resourceName:$(row).find('.resourceName').val(),
        reorder:$(row).find('.reorder').val(),
        inactive:false
    };

    alert("resourceId :"+ ResourceDTO.resourceId +" , resourceName:"+ ResourceDTO.resourceName +" , reorder:"+ ResourceDTO.reorder +" ,inactive :" + ResourceDTO.inactive);
    put( JSON.stringify(ResourceDTO), "rest/resources/update" , function (data) {

    });
}
function ResourceTable_REST_deleteUser(row){
    var ResourceDTO ={
        resourceId:  $(row).find('.resourceId').text(),
        resourceName:$(row).find('.resourceName').val(),
        reorder:$(row).find('.reorder').val(),
        inactive:false
    };
    Delete( JSON.stringify(ResourceDTO), "rest/resources/delete" , function (data) {

    });
}

function ResourceTable_commitTable(){
    //$(row).attr("data-editState")
    $('#pageContent').children('tr').each( function () {
        var commitState = $(this).find('.commit-state').prop("checked");
        var id = $(this).attr("id");

        if(commitState){
            switch ( $(this).attr("data-editstate") ) {
                case Res_STATE_NOCHANGE:
                    break;
                case Res_STATE_deleteReady:
                    ResourceTable_REST_deleteUser( $(this) );
                    break;
                case Res_STATE_editReady:
                    ResourceTable_REST_updateUser( $(this) );
                    break;
                case Res_STATE_createReady:
                    ResourceTable_REST_createResource( $(this) );
                    break;
            }
        }
    });
}





function ResTable_getNewId( sfunc ){
    get("/rest/users/get/newid", function (data) {
        //alert(JSON.parse(data,1));;
        sfunc( data.userId );
    });
}