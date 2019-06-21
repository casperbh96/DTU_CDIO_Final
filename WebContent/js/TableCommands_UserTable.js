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

var userTable_ROLEidClassName= 'ROLEID_';
var userTable_ROWINDEX = 1;
var userTable_HTML_SingleROLE ="\n";
var userTable_HTML_GeneralRoles="\n";
// CREATING GENERAL ROLE HTML
$( document ).ready(function(){
    get('/rest/roles/get', function (data) {
        $.each(data, function (i, role) {
            userTable_HTML_GeneralRoles = userTable_HTML_GeneralRoles + '' +
                '<li class="dto-table-RowBeneath-field '+userTable_ROLEidClassName+role.roleId+'" style="float:left;">\n' +
                '    <input class="UserRoleCheckBox" type="checkbox" checked="false" data-roleName="'+role.rolename+'" data-roleId="'+role.roleId+'" disabled>\n' +
                '    <span> '+role.rolename+' </span>\n' +
                '</li>';
        });
    });
});
function userTable_HTML_GenerateUserRow(user, rowId, editState){
    return '' +
        '       <tr id="'+rowId+'"class="DTO_Table_Row DTO_USER_GRID" data-editstate="'+editState+'" data-aktiveediting="false" data-inactive-User="'+user.inactive+'">\n' +
        '            <td><input  class="dto-table-column-DTO-formElement commit-state" type="checkbox" name="checkBox" checked="unchecked" ></td>\n' +
        '            <td><div    class="dto-table-column-DTO-formElement userId"     name="'+user.userId+'" > '+user.userId+' </div>          </td>\n' +
        '            <td><input  class="dto-table-column-DTO-formElement username"   name="'+user.username+'" value="'+user.username+'" disabled>   </td>\n' +
        '            <td><input  class="dto-table-column-DTO-formElement initials"   name="'+user.initials+'"  value="'+user.initials+'"  disabled>   </td>\n' +
        '            <td class="dto-table-column-DTO-formElement DTO_Table_Row_beneathRow" style="grid-row: 2/3; grid-column: 1/5;" >\n' +
        '               <ul class="_1x4grid">\n' + userTable_HTML_GeneralRoles +
        '               </ul></td>\n' +
        '            <td class="DTO_Table_Row_MenuBox" style="grid-row: 1/3; grid-column: 5/6;">\n' +
        '                <button class="dto-table-button User_UpdateBtn" name="update" onclick="userTable_UI_updateRow(this.parentElement.parentElement)" style="grid-row: 1/2;">update</button>\n' +
        '                <button class="dto-table-button User_DeleBtn" name="delete" onclick="userTable_UI_deleteRow(this.parentElement.parentElement)" style="grid-row: 2/3;">delete</button>\n' +
        '            </td>\n' +
        '        </tr>';
}

//UI functions
function userTable_UI_updateRow(row){
    if($(row).attr("data-editstate") !== userTable_STATE_deleteReady){
        $(row).attr("data-editstate",userTable_STATE_editReady);
        $(row).find(' input').prop('disabled', false);
        $(row).css("background-color","yellow");
    }
}
function userTable_UI_deleteRow(row){
    switch ($(row).attr("data-editstate")) {
        case userTable_STATE_createReady:
            $(row).remove();
            break;
        case userTable_STATE_deleteReady:
            $(row).attr("data-editstate",userTable_STATE_editReady);
            $(row).children(' .dto-table-column-DTO-formElement').show();
            break;
        default:
            $(row).attr("data-editstate",userTable_STATE_deleteReady);
            $(row).children(' .dto-table-column-DTO-formElement').hide();
    }
}
function userTable_CREATE_insertCreaterRow(){
    // Create the Row
    var rowId = 'rowNumber_'+userTable_ROWINDEX;
    var user = {
        userId: "x",
        username:"newUserName",
        initials:"Initals",
        inactive:false
    }
    $('.Page_Content_pasterTable').append(userTable_HTML_GenerateUserRow(user,rowId,userTable_STATE_createReady ));
    userTable_ROWINDEX = userTable_ROWINDEX + 1 ;
    // uncheck all Users Roles
    var thisRow = $('#'+rowId+'');
    thisRow.children('.DTO_Table_Row_beneathRow').find(' input').prop("checked",false);
    thisRow.css("background-color","green");
    thisRow.find('.User_UpdateBtn').hide();
    thisRow.find(' input').prop('disabled', false);
    // Get All Roles Belonging to the User. and for each, check the Belonging checkbox if he has it.

}

// UI AND REST FUNCTIONS
function userTable_READ_loadUserTable(){
    get('/rest/users/true', function (data) {
        $.each(data, function (i, user) {
            // Create the Row
            var rowId = 'rowNumber_'+userTable_ROWINDEX;
            $('.Page_Content_pasterTable').append(userTable_HTML_GenerateUserRow(user,rowId, userTable_STATE_NOCHANGE ));
            userTable_ROWINDEX = userTable_ROWINDEX + 1 ;
            // uncheck all Users Roles
            var thisRow = $('#'+rowId+'');
            $('#'+rowId+'').children('.DTO_Table_Row_beneathRow').find(' input').prop("checked",false);
            $('#'+rowId+'').find('.commit-state').prop("checked",false);
            // Get All Roles Belonging to the User. and for each, check the Belonging checkbox if he has it.

            get('/rest/roleuser/get/'+user.userId+'', function (data) {
                $.each(data, function (i, role) {
                    $('#'+rowId+'').children('.DTO_Table_Row_beneathRow').find('.'+userTable_ROLEidClassName+role.roleId+'').children(' input').prop("checked",true);
                });
            });


        });
    });
}

// REST Functions

function userTable_REST_addUserRoles_toUser(row, userdto){
    $(row).find('.UserRoleCheckBox').each(function () {
        if($(this).prop("checked")) {
            //alert($(this).attr("data-roleName") + " is active on " + userdto.username + "," + userdto.userId);

            var REL_RoleUserDTO = {
                userId: userdto.userId,
                roleId: $(this).attr("data-roleId")
            };

            post(JSON.stringify(REL_RoleUserDTO), "rest/roleuser/create", function (data) {
                alert("added Role " + REL_RoleUserDTO.roleId + " for " + userdto.userId);
            });

        }

    });
}
function userTable_REST_createUser(row){
    userTable_getNewId(function (data) {

        var userdto ={
            userId:  data ,
            username:$(row).find('.username').val(),
            initials:$(row).find('.initials').val(),
            inactive:$(row).attr("data-inactive-User")
        };

        //alert( userdto.userId +","+ newId);
        //alert("Create" +  + " , " + $(row).find('.username').val()  + " , " + $(row).find('.initials').val()  + " , " + $(row).attr("data-inactive-User"));
        post( JSON.stringify(userdto) ,"rest/users/create" , function (data) {
            userTable_REST_addUserRoles_toUser(row, userdto)
        });

    });
}
function userTable_REST_updateUser(row){
    var userdto ={
        userId:  row.find('.userId').text(),
        username:row.find('.username').val(),
        initials:row.find('.initials').val(),
        inactive: row.attr("data-inactive-User")
    };

    put( JSON.stringify(userdto), "rest/users/update" , function (data) {


        // First Delete all RoleRelations to this User
        Delete(JSON.stringify(null), "rest/roleuser/delete/"+userdto.userId.toString().replace(/\s/g, '')   ,function () {
            userTable_REST_addUserRoles_toUser(row, userdto)
        });


    });
}
function userTable_REST_deleteUser(row){
    var userdto ={
        userId:  row.find('.userId').text(),
        username:row.find('.username').val(),
        initials:row.find('.initials').val(),
        inactive: row.attr("data-inactive-User")
    };
    Delete( JSON.stringify(userdto), "rest/users/delete" , function (data) {

    });
}
function userTable_commit_tableUsersChanges(){
    //$(row).attr("data-editState")
    $('.Page_Content_pasterTable').children('tr').each( function () {
        var commitState = $(this).find('.commit-state').prop("checked");
        var id = $(this).attr("id");

        if(commitState){
            switch ( $(this).attr("data-editstate") ) {
                case userTable_STATE_NOCHANGE:
                    break;
                case userTable_STATE_deleteReady:
                    userTable_REST_deleteUser( $(this) );
                    break;
                case userTable_STATE_editReady:
                    userTable_REST_updateUser( $(this) );
                    break;
                case userTable_STATE_createReady:
                    //alert("create row"+ $(this).find('.userId').val() +","+ $(this).find('.username').val() +","+ $(this).find('.initials').val() +"");
                    userTable_REST_createUser( $(this) );
                    break;
            }
        }
    });
}
function userTable_getNewId( sfunc ){
    get("/rest/users/get/newid", function (data) {
        //alert(JSON.parse(data,1));;
        sfunc( data.userId );

    });
}


function userTable_loadTable(){
    userTable_READ_loadUserTable()
}