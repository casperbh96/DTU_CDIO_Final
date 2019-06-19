/* Document Wide Variables */
var tableName_UserAdmin = "userTable";
var ROWINDEX = 1;

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

    get('/rest/roles/get', function (data) {

        $.each(data, function (i, role) {
            HTML_RolesDropDownListElements = HTML_RolesDropDownListElements + '    <li><input class="dto-table-drop-check roleId_'+ role.roleId +'" type="checkbox" checked="false" data-roleName="'+ role.rolename +'" data-roleId="'+ role.roleId +'" ><span>'+role.rolename+'</span></li> \n';
        });
    });

});
var HTML_RolesDropDownListElements ="";
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
    changeTable_Data(tableName_UserAdmin);
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













function getUsersAll(aktivity){

    $(function () {
        $.ajax({
            type:'GET',
            url:'/rest/users',
            data:{ searchMethod:searchMethod , String:Id },
            contentType:'application/json',
            dataType: 'json',
            success: function (data) {
                $("#UsersContentFlasher").html( JSON.stringify(data) );
            },
            error: function () {
                $("#UsersContentFlasher").html(" Error ");
            }
        });
    });

}
function getUsersById(id){

    $(function () {
        $.ajax({
            type:'GET',
            url:'/rest/users',
            data:{ searchMethod:searchMethod , String:Id },
            contentType:'application/json',
            dataType: 'json',
            success: function (data) {
                $("#UsersContentFlasher").html( JSON.stringify(data) );
            },
            error: function () {
                $("#UsersContentFlasher").html(" Error ");
            }
        });
    });

}
function searchUsersByRow(userTableRow, aktivity){
    $(function () {
        $.ajax({
            type:'GET',
            url:'/rest/users/search', // SearchId or SearchRow
            data:{ searchMethod:searchMethod , keyword:keyword },
            contentType:'application/json',
            dataType: 'json',
            success: function (data) {
                $("#UsersContentFlasher").html( JSON.stringify(data) );
            },
            error: function () {
                $("#UsersContentFlasher").append(" Error ");
            }
        });
    });
}



function createRoles(role){

    $(function (){
        $.ajax({
            type:'GET',
            url:'/rest/roles/create',
            data:restRoleDTO,
            contentType:'application/json',
            dataType: 'json',
            success: function (data) {
                $("#RolesContentFlasher").html( JSON.stringify(data) );
            },
            error: function () {
                $("#RolesContentFlasher").html(" Error ");
            }
        });
    });

}
function getRolesAll(aktivity){

    $(function () {
        $.ajax({
            type:'GET',
            url:'/rest/roles/get',
            data:{ searchMethod:searchMethod , Id:Id },
            contentType:'application/json',
            dataType: 'json',
            success: function (data) {
                $("#RolesContentFlasher").html( JSON.stringify(data) );
            },
            error: function () {
                $("#RolesContentFlasher").html(" Error ");
            }
        });
    });

}
function getRolesById(id){

    $(function () {
        $.ajax({
            type:'GET',
            url:'/rest/roles/get',
            data:{ searchMethod:searchMethod , Id:Id },
            contentType:'application/json',
            dataType: 'json',
            success: function (data) {
                $("#RolesContentFlasher").html( JSON.stringify(data) );
            },
            error: function () {
                $("#RolesContentFlasher").html(" Error ");
            }
        });
    });

}
function searchRoles(keyword, aktivity){
    $(function () {
        $.ajax({
            type:'GET',
            url:'/rest/roles/search', // SearchId or SearchRow
            data:{keyword:keyword },
            contentType:'application/json',
            dataType: 'json',
            success: function (data) {
                $("#RolesContentFlasher").html( JSON.stringify(data) );
            },
            error: function () {
                $("#RolesContentFlasher").html(" Error ");
            }
        });
    });
}
function updateRoles(role){
    $(function () {
        $.ajax({
            type:'GET',
            url:'/rest/roles/update', // SearchId or SearchRow
            data:restRoleDTO,
            contentType:'application/json',
            dataType: 'json',
            success: function (data) {
                $("#RolesContentFlasher").html( JSON.stringify(data) );
            },
            error: function () {
                $("#RolesContentFlasher").append(" Error ");
            }
        });
    });
}
function deleteRoles(role){
    $(function () {
        $.ajax({
            type:'GET',
            url:'/rest/roles/delete',
            data:restRoleDTO,
            contentType:'application/json',
            dataType: 'json',
            success: function (data) {
                $("#RolesContentFlasher").html( JSON.stringify(data) );
            },
            error: function () {
                $("#RolesContentFlasher").html(" Error ");
            }
        });
    });
}

function createResource(resource){

    $(function (){
        $.ajax({
            type:'GET',
            url:'/rest/resource/create',
            data:ResourceDTO,
            contentType:'application/json',
            dataType: 'json',
            success: function (data) {
                $("#IngredientContentFlasher").html( JSON.stringify(data) );
            },
            error: function () {
                $("#IngredientContentFlasher").html(" Error ");
            }
        });
    });

}
function getResourcesAll(aktivity){

    $(function () {
        $.ajax({
            type:'GET',
            url:'/rest/resource/get',
            data:{ searchMethod:searchMethod , Id:Id },
            contentType:'application/json',
            dataType: 'json',
            success: function (data) {
                $("#IngredientContentFlasher").html( JSON.stringify(data) );
            },
            error: function () {
                $("#IngredientContentFlasher").html(" Error ");
            }
        });
    });

}
function getResourcesById(id){

    $(function () {
        $.ajax({
            type:'GET',
            url:'/rest/resource/get',
            data:{ searchMethod:searchMethod , Id:Id },
            contentType:'application/json',
            dataType: 'json',
            success: function (data) {
                $("#IngredientContentFlasher").html( JSON.stringify(data) );
            },
            error: function () {
                $("#IngredientContentFlasher").html(" Error ");
            }
        });
    });

}
function searchResourceByRow(resourceRow ,aktivity){
    $(function () {
        $.ajax({
            type:'GET',
            url:'/rest/resource/search', // SearchId or SearchRow
            data:{searchMethod:searchMethod,keyword:keyword },
            contentType:'application/json',
            dataType: 'json',
            success: function (data) {
                $("#IngredientContentFlasher").html( JSON.stringify(data) );
            },
            error: function () {
                $("#IngredientContentFlasher").html(" Error ");
            }
        });
    });
}
function searchResourceByKey(keyword ,aktivity){
    $(function () {
        $.ajax({
            type:'GET',
            url:'/rest/resource/search', // SearchId or SearchRow
            data:{searchMethod:searchMethod,keyword:keyword },
            contentType:'application/json',
            dataType: 'json',
            success: function (data) {
                $("#IngredientContentFlasher").html( JSON.stringify(data) );
            },
            error: function () {
                $("#IngredientContentFlasher").html(" Error ");
            }
        });
    });
}
function updateResource(resource){
    $(function () {
        $.ajax({
            type:'GET',
            url:'/rest/resource/update', // SearchId or SearchRow
            data:ResourceDTO,
            contentType:'application/json',
            dataType: 'json',
            success: function (data) {
                $("#IngredientContentFlasher").html( JSON.stringify(data) );
            },
            error: function () {
                $("#IngredientContentFlasher").append(" Error ");
            }
        });
    });
}
function deleteResource(resource){
    $(function () {
        $.ajax({
            type:'GET',
            url:'/rest/resource/delete',
            data:ResourceDTO,
            contentType:'application/json',
            dataType: 'json',
            success: function (data) {
                $("#IngredientContentFlasher").html( JSON.stringify(data) );
            },
            error: function () {
                $("#IngredientContentFlasher").html(" Error ");
            }
        });
    });
}
function getReOrdersResource(aktivity){
    $(function () {
        $.ajax({
            type:'GET',
            url:'/rest/resource/reorders',
            contentType:'application/json',
            dataType: 'json',
            success: function (data) {
                $("#IngredientContentFlasher").html( JSON.stringify(data) );
            },
            error: function () {
                $("#IngredientContentFlasher").html(" Error ");
            }
        });
    });
}

function createRecipe(recipe, resources){

    $(function (){
        $.ajax({
            type:'GET',
            url:'/rest/resource/create',
            data:ResourceDTO,
            contentType:'application/json',
            dataType: 'json',
            success: function (data) {
                $("#IngredientContentFlasher").html( JSON.stringify(data) );
            },
            error: function () {
                $("#IngredientContentFlasher").html(" Error ");
            }
        });
    });

}
function getRecipeAll(aktivity){

    $(function () {
        $.ajax({
            type:'GET',
            url:'/rest/resource/get',
            data:{ searchMethod:searchMethod , Id:Id },
            contentType:'application/json',
            dataType: 'json',
            success: function (data) {
                $("#IngredientContentFlasher").html( JSON.stringify(data) );
            },
            error: function () {
                $("#IngredientContentFlasher").html(" Error ");
            }
        });
    });

}
function getRecipeAllVersion(id){}
function getRecipeSpecifik(id, date){}
function getRecipeResources(recipe){}
function searchRecipesKeyword(keyword){
    $(function () {
        $.ajax({
            type:'GET',
            url:'/rest/resource/search', // SearchId or SearchRow
            data:{searchMethod:searchMethod,keyword:keyword },
            contentType:'application/json',
            dataType: 'json',
            success: function (data) {
                $("#IngredientContentFlasher").html( JSON.stringify(data) );
            },
            error: function () {
                $("#IngredientContentFlasher").html(" Error ");
            }
        });
    });
}
function searchRecipesFromResources(resources){}
function searchRecipeByRow(recipeRow){}
function updateRecipe(recipe, resources){
    $(function () {
        $.ajax({
            type:'GET',
            url:'/rest/resource/update', // SearchId or SearchRow
            data:ResourceDTO,
            contentType:'application/json',
            dataType: 'json',
            success: function (data) {
                $("#IngredientContentFlasher").html( JSON.stringify(data) );
            },
            error: function () {
                $("#IngredientContentFlasher").append(" Error ");
            }
        });
    });
}
function deleteRecipe(recipe){
    $(function () {
        $.ajax({
            type:'GET',
            url:'/rest/resource/delete',
            data:ResourceDTO,
            contentType:'application/json',
            dataType: 'json',
            success: function (data) {
                $("#IngredientContentFlasher").html( JSON.stringify(data) );
            },
            error: function () {
                $("#IngredientContentFlasher").html(" Error ");
            }
        });
    });
}
















