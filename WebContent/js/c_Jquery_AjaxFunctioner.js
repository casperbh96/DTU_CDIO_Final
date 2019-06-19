/* Document Wide Variables */
var HTML_RolesDropDownListElements ="";
var HTML_Pro_Res_DropDownhtml ="";
var ROWINDEX = 1;

var TABLENAME_USERADMIN = "userTable";
var TABLENAME_PRODUCTION = "Production";
TABLENAME_PRODUCTION
/* Generel Rest Funktions */
// these are used by all CRUDE functions
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

// as soon as loaded, Generate a Rolls DropDown List for all Users.
$( document ).ready(function(){
    /*get('/rest/roles/get/false', function (data) {
        $.each(data, function (i, role) {
            HTML_RolesDropDownListElements = HTML_RolesDropDownListElements + '    <li><input class="dto-table-drop-check roleId_'+ role.roleId +'" type="checkbox" checked="false" data-roleName="'+ role.rolename +'" data-roleId="'+ role.roleId +'" ><span>'+role.rolename+'</span></li> \n';
        });
    });*/


});

function CreateNew_UserRow(){
    var createRowId = "createRow_"+ROWINDEX;
    var userDTOHeaders = {
        userId:"userId",
        username:"username",
        initials:"initials",
        inactive:" aktive "
    };
    $('#dto-table-container').append(HTML_generateUserDTO( userDTOHeaders , createRowId));
    $('#'+createRowId+'').css("background-color", "green");
    $('#'+createRowId+'').attr("data-aktiveEditing","create");
    $('#'+createRowId+'').find(' form').find(' .dto-table-column-DTO-formElement').each(function () {
        $(this).prop('disabled', false);
    });
    $('#'+createRowId+'').attr("data-editstate","create");
    $('#'+createRowId+'').find('.activeColumn').hide();
    ROWINDEX = ROWINDEX +1;


}
function HTML_generateUserDTO(userDto, RowId )  {

    return '<tr id="'+RowId+'" data-editState="edit" data-aktiveEditing="false" >\n' +
        '                <td class="dto-table-column dto-table-selected">\n' +
        '                    <input class="commit-state" type="checkbox" name="checkBox" checked="unchecked" >\n' +
        '                </td>\n' +
        '                <td class="dto-table-column" >\n' +
        '                    <form class="dto-table-column-DTO _1x5grid">\n' +
        '                        <input class="dto-table-column-DTO-formElement userId"   name="userId"    value="' + userDto.userId + '" type="text" disabled>\n' +
        '                        <input class="dto-table-column-DTO-formElement username" name="username"  value="' + userDto.username + '"disabled>\n' +
        '                        <input class="dto-table-column-DTO-formElement initials" name="initials"  value="' + userDto.initials + '" disabled>\n' +
        '                        <ul style="display: none;" class="dto-table-column-DTO-formElement activeColumn" >\n' +
        '                            <li>\n' +
        '                                <button class="dto-table-drop-btn" onclick="toggleDropDown(this)" type="button" > ' + userDto.inactive + ' </button>\n' +
        '                                <ul>\n' +
        '                                    <li> <input class="dto-table-drop-check" type="radio" name="aktivity" checked="false"> <span> aktive    </span> </li>\n' +
        '                                    <li> <input class="dto-table-drop-check" type="radio" name="aktivity" checked="false"> <span> in-aktive </span> </li>\n' +
        '                                </ul>\n' +
        '                            </li>\n' +
        '                        </ul>\n' +
        '\n' +
        '                        <ul class="dto-table-column-DTO-formElement" >\n' +
        '                            <li>\n' +
        '                                <button class="dto-table-drop-btn" onclick="toggleDropDown(this)" type="button" > -- Roles -- </button>\n' +
        '                                <ul class="RolesDropDown" >\n ' +
        '                                   '+ HTML_RolesDropDownListElements +' \n '+
        '                                </ul>\n' +
        '                            </li>\n' +
        '                        </ul>\n' +
        '\n' +
        '                    </form>\n' +
        '                </td>\n' +
        '                <td class="dto-table-column dto-table-menu _1x3grid" name="hejcolumn" >\n' +
        '                    <button class="dto-table-button" name="update" onclick="dto_table_row_updateToggle(this.parentElement.parentElement)" >update</button>\n' +
        '                    <button class="dto-table-button" name="delete" onclick="dto_table_row_deleteToggle(this.parentElement.parentElement)">delete</button>\n' +
        '                </td>\n' +
        '            </tr>';
}
function HTML_generateProductionDTO()  {
    return '' +
        '           <tr data-editState="edit" data-aktiveEditing="false" style="min-width:100%;" >\n' +
        '                <td class="dto-table-column dto-table-selected">\n' +
        '                    <input class="commit-state" type="checkbox" name="checkBox" checked="unchecked">\n' +
        '                </td>\n' +
        '                <td class="dto-table-column">\n' +
        '                    <form class="dto-table-column-DTO" >\n' +
        '                        <input class="dto-table-column-DTO-formElement P_Id"            name="P_Id"             value="P_Id"            >\n' +
        '                        <input class="dto-table-column-DTO-formElement P_creation_Date" name="P_creation_Date"  value="P_creation_Date" >\n' +
        '                        <input class="dto-table-column-DTO-formElement R_Name"          name="R_Name"           value="R_Name"          >\n' +
        '                        <input class="dto-table-column-DTO-formElement R_EndDate"       name="R_EndDate"        value="R_EndDate"       >\n' +
        '                        <input class="dto-table-column-DTO-formElement P_Status"        name="Status"           value="Status"          >\n' +
        '                        <ul class="dto-table-column-DTO-formElement" >\n' +
        '                            <li>\n' +
        '                                <button class="dto-table-drop-btn" onclick="toggleDropDown(this)" type="button" > -- Roles -- </button>\n' +
        '                                <ul class="ResourceBatchDropDown" style="display: none">\n' +
        '                                    <table>\n' +
        '                                        <tr class="_1x4grid"><td>resourceName</td><td>resBatchId</td><td>afMåltMængde</td><td>remaining</td></tr>\n' +
        '                                        <tr class="_1x4grid"><td>resourceName</td><td>resBatchId</td><td>afMåltMængde</td><td>remaining</td></tr>\n' +
        '                                        <tr class="_1x4grid"><td>resourceName</td><td>resBatchId</td><td>afMåltMængde</td><td>remaining</td></tr>\n' +
        '                                    </table>\n' +
        '                                </ul>\n' +
        '\n' +
        '                            </li>\n' +
        '                        </ul>\n' +
        '                    </form>\n' +
        '                </td>\n' +
        '            </tr>';
}



/* -- Menu Functions -- */
function changeTable_Data(identifyer){
    $('#dto-table-container').attr("data-activetable",identifyer);
}
function dto_table_row_updateToggle(row){
    switch ($(row).attr("data-aktiveEditing")) {
        case "false":

            $(row).find(' form').find(' .dto-table-column-DTO-formElement').each(function () {
                $(this).prop('disabled', false);

            });
            $(row).css("background-color", "yellow");
            $(row).attr("data-aktiveEditing","true");
            break;

        case "true":

            $(row).find(' form').find(' .dto-table-column-DTO-formElement').each(function () {
                $(this).prop('disabled', true);
            });
            $(row).attr("data-aktiveEditing","false");
            break;
    }
}
function dto_table_row_deleteToggle(row){
    switch ( $(row).attr("data-editState") ) {
        case "delete":
            $(row).find('.dto-table-column-DTO').find(' .dto-table-column-DTO-formElement').each(function () {
                $(this).show();
            });
            $(row).attr("data-editState","edit");
            break;
        case "edit":
            $(row).find('.dto-table-column-DTO').find(' .dto-table-column-DTO-formElement').each(function () {
                $(this).hide();
            });
            $(row).attr("data-editState","delete");
            break;
        case "create":
            $(row).remove();
            break;
    }
}

/* -- CRUDE -- */
/* -- Producktions -- */
/*CREATE*/

/*RUD*/

function loadtable_Productions() {
    /* change table Data to show it is the UsersAdmin that is Active */
    changeTable_Data(TABLENAME_USERADMIN);
   // get('/rest/productbatchresourcebatch/get', function (data) { 	// Get ALL UserDto's
        ROWINDEX = 1;
        $("#dto-table-container").empty();
      //  $.each(data, function (i,production){  		// for Each User
        for(var i = 0; i < 10 ; i++){
            // Create HTML
            var rowId = 'rowNumber_' +ROWINDEX;
            $('#dto-table-container').append(HTML_generateProductionDTO()); // Create all HTML for a Single User.
/*
            var row = $('#'+rowId+'');
            row.find('.commit-state').prop("checked",false);					 // make sure the Commit button isent activated
            row.find('.RolesDropDown').find(' input').prop("checked",false);// make sure all Roles are Deactivated. the plan is to later activate those referenced to the user

            //Activating Checkboxes to the roles, this user has
            loadRolesPrUser(User,row);;*/
            ROWINDEX = ROWINDEX + 1
        }
      //  });
   // });
}



/* -- USERS -- */
function createUser(self) {
    var userdto = UserDTO = {
        userId: self.find('.userId').val(),
        username:self.find('.username').val(),
        initials:self.find('.initials').val(),
        inactive: false
    };
    post( JSON.stringify(userdto) ,"rest/users/create" , function (data) {

    });
   // updateRolesForUser(self);
}
function loadtable_User() {
    /* change table Data to show it is the UsersAdmin that is Active */
    ROWINDEX = 1;
    changeTable_Data(TABLENAME_USERADMIN);
    get('/rest/users/get', function (data) { 	// Get ALL UserDto's

        $("#dto-table-container").empty();
        $.each(data, function (i,User){  		// for Each User

            // Create HTML
            var rowId = 'rowNumber_' +ROWINDEX;
            $('#dto-table-container').append(HTML_generateUserDTO(User, rowId)); // Create all HTML for a Single User.

            var row = $('#'+rowId+'');
            row.find('.commit-state').prop("checked",false);					 // make sure the Commit button isent activated
            row.find('.RolesDropDown').find(' input').prop("checked",false);// make sure all Roles are Deactivated. the plan is to later activate those referenced to the user

            //Activating Checkboxes to the roles, this user has
            loadRolesPrUser(User,row);
            ROWINDEX = ROWINDEX + 1;

        });
    });
}
function loadRolesPrUser(User, row) {
    get('/rest/roleuser/get/'+ User.userId +'', function (UserRoles) {	// Get ALL RolesDto's
        $.each(UserRoles, function (u,Role) {									// for every role
            row.find('.roleId_'+ Role.roleId +'').prop("checked", true );
        });
    });
}
function updateUser(self){
    var userdto = UserDTO ={
        userId: self.find('.userId').val(),
        username:self.find('.username').val(),
        initials:self.find('.initials').val(),
        inactive: false
    };
    put( JSON.stringify(userdto), "rest/users/update" , function (data) {

    });

    updateRolesForUser(self);
}
function deleteUser(self) {
    var userdto = UserDTO ={
        userId: self.find('.userId').val(),
        username:self.find('.username').val(),
        initials:self.find('.initials').val(),
        inactive: false
    };
    Delete( JSON.stringify(userdto), "rest/users/delete" , function (data) {

    });
}
// CRUDE for USERROLES
function updateRolesForUser(self){

    var userId = self.find('.userId').val();
    Delete(JSON.stringify(null), "rest/roleuser/delete/"+userId,function () {
        alert("deleted Roles from " + userId);
    });

    $(self).find('.RolesDropDown').find(' input').each(function () {
        setTimeout(1000);
        var roleId = $(this).attr("data-roleId");


          var REL_RoleUserDTO ={
              userId:self.find('.userId').val(),
              roleId:roleId
        };

          post(JSON.stringify(REL_RoleUserDTO), "rest/roleuser/create/", function (data) {
              alert("added Role " + roleId + "from " + userId);
          });
    });
}

/* Commit Changes Functions */
function commit_tableUsersChanges(){

    //$(row).attr("data-editState")
    $('#dto-table-container').children('tr').each( function () {
        var commitState = $(this).find('.commit-state').prop("checked");
        var id = $(this).attr("id");

        if(commitState){
            switch ( $(this).attr("data-editstate") ) {
                case "delete":
                    deleteUser( $(this) );
                    break;
                case "edit":
                    updateUser( $(this) );
                    break;
                case "create":
                    //alert("create row"+ $(this).find('.userId').val() +","+ $(this).find('.username').val() +","+ $(this).find('.initials').val() +"");
                    createUser( $(this) );
                    break;
            }
        }
        //  $('#dto-table-container').empty();
        //  loadtable_User();
    });
}
function commitTableChanges(){




}























