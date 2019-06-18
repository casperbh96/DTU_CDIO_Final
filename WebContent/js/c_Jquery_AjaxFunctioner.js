

function get(url, sFunc){
    $.ajax({
        type:'GET',
        url: url,
        success: function (data) {
            sFunc(data);
        },
        error: function() {
            alert("connection Error with url" + url+ ";");
        }
    });
}
function post(data, url, sFunc, eFunc){
    alert(data + url);
    $.ajax({
        type:'POST',
        url: url,
        contentType:'application/json',
        data:data,
        success: function (data) {
            sFunc(data);
        },
        error: function (data) {
            eFunc(data);
        }
    });
}
function put(data, url, sFunc, eFunc){
    $.ajax({
        type:'PUT',
        url: url,
        contentType:'application/json',
        data:data,
        success: function (data) {
            sFunc(data);
        },
        error: function (data) {
            eFunc(data);
        }
    });
}
function Delete(data, url, sFunc, eFunc){
    $.ajax({
        type:'DELETE',
        url: url,
        contentType:'application/json',
        data:data,
        success: function (data) {
            sFunc(data);
        },
        error: function (data) {
            eFunc(data);
        }
    });
}

var HTML_RolesDropDownListElements ="";
$( document ).ready(function(){

    get('/rest/roles/get', function (data) {

        $.each(data, function (i, role) {
            HTML_RolesDropDownListElements = HTML_RolesDropDownListElements + '    <li><input class="dto-table-drop-check" type="checkbox" checked="false" data-roleId="'+ role.roleId +'"><span>'+role.rolename+'</span></li> \n';
        });
    });
});

var tableName_UserAdmin = "userTable";

var restUser = {
    userDTO:'userId=1;username=beg;initials=b;inactive=false',
    roleDTOs:'1=rolename;2=rolename;3=rolename'
};
var restUserSearchRow = {
    userDTO:'userId=1;username=beg;initials=b;inactive=false'
};
var restUserKeyWord = {
    keyWord:'keyword'
};




function loadUser() {
    /* change table Data to show it is the UsersAdmin that is Active */
    changeTable_Data(tableName_UserAdmin);

    var index = 1;
    get('/rest/users/get', function (data) {
        $("#dto-table-container").empty();
        $.each(data, function (i,User){

           // Create HTML
            var rowId = "rowNumber_"+index;
           // alert(rowId);

            $('#dto-table-container').append(HTML_generateUserDTO(User, rowId));
            $('#'+rowId+'').find('.commit-state').prop( "checked", false );

            // Update RolesDropDown to Current Roles
            /*get('/rest/roleuser/get/'+ User.userId +'', function (UserRoles) {
                $.each(UserRoles, function (i, roles) {
                    //     HTML_update_UserRolesPrUser( rowId, roles );
                });
            });
*/
            index = index + 1;
        });
    });

}

function HTML_generateUserDTO(userDto, RowId ) {

    return '<tr id="'+RowId+'">\n' +
        '                <td class="dto-table-column dto-table-selected">\n' +
        '                    <input class="commit-state" type="checkbox" name="checkBox" checked="unchecked" >\n' +
        '                </td>\n' +
        '                <td class="dto-table-column">\n' +
        '                    <input class="dele-state" type="checkbox" checked="false" style="visibility: hidden; display: none;" >\n' +
        '                    <input class="edit-state" type="checkbox" checked="false" style="visibility: hidden; display: none;" >\n' +
        '                    <form class="dto-table-column-DTO _1x5grid">\n' +
        '                        <input  class="dto-table-column-DTO-formElement" name="userId"    value="' + userDto.userId + '" type="text" disabled>\n' +
        '                        <input  class="dto-table-column-DTO-formElement" name="username"  value="' + userDto.username + '"disabled>\n' +
        '                        <input  class="dto-table-column-DTO-formElement" name="initials"  value="' + userDto.initials + '" disabled>\n' +
        '                        <ul class="dto-table-column-DTO-formElement" >\n' +
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
        '                    <button class="dto-table-button" name="find" >look at</button>\n' +
        '                </td>\n' +
        '            </tr>';
}
/*function HTML_update_UserRolesPrUser( rowId, rowData){
    alert(rowId);
    $('#'+rowId+'').find('.RolesDropDown').css("background-color", "yellow");
  //  $(container).css("background-color","blue");
  //  container.children('li').each(function () {

        //$(this).parent('.RolesDropDown')

   //     var roleId = $(this).children(' input').attr("data-roleid");
   //     var roleName = $(this).children(' span').text();
    //    alert(roleId + " ," + roleName);

   // })
}*/

/* Commit Data things . */
function commitChanges_btn(){

    var activeTable = $('#dto-table-container').attr('data-ActiveTable');
    switch(activeTable) {
        case tableName_UserAdmin:
            commit_tableUsersChanges();
        break;
        default:

    }

}
function commit_tableUsersChanges() {
    $('#dto-table-container').children('tr').each( function () {
        var commitState = $(this).find('.commit-state').prop("checked");
        if(commitState){
            if($(this).find('.dele-state').prop("checked")){

            }
            else if($(this).find('.edit-state').prop("checked")){

            }
        }
    });
}
/* commit Dette */


function changeTable_Data(identifyer){
    $('#dto-table-container').attr("data-activetable",identifyer);
}























function dto_table_row_updateToggle(row){
   if ($(row).find('.edit-state').is(':checked')) {

        $(row).find(' form').find(' .dto-table-column-DTO-formElement').each(function () {
            $(this).prop('disabled', false);

        });
        $(row).find('.edit-state').prop( "checked", false );

    }else{

        $(row).find(' form').find(' .dto-table-column-DTO-formElement').each(function () {
            $(this).prop('disabled', true);
        });
        $(row).find('.edit-state').prop( "checked", true );

    }

}
function dto_table_row_deleteToggle(row){

    if ($(row).find('.dele-state').is(':checked')) {
        $(row).find('.dto-table-column-DTO').find(' .dto-table-column-DTO-formElement').each(function () {
            $(this).hide();

        });
        $(row).find('.dele-state').prop( "checked", false );
    }else{

        $(row).find('.dto-table-column-DTO').find(' .dto-table-column-DTO-formElement').each(function () {

            $(this).show();

        });
        $(row).find('.dele-state').prop( "checked", true );
    }

}
function commitTableChanges(){
    $( "#dto-table-container" ).find(' tr').each(function () {
        if($(this).find(' .commit-state').prop('checked')) {
            var table = $(this).find('.dto-table-column-DTO');
            alert("commit "+ CreateTextDTO(table)); // todo replace with a get function
        }
    });
}







function CreateTextDTO(form){
    var text = "{";
    $(form).children('.dto-table-column-DTO-formElement').each(function () {
        var name = $(this).attr("name");
        var value =$(this).val();

        text = text + name + ":" + value+ ";"
    })
    text = text + "}"
    return text;
}

function getTableDTO_UserTable() {
    var Counter = 0;

    $('#userDTO_Table tr').each(function(){

        var $checkBox = $(this).find('.DTO_TABLE_selected');

        if ($checkBox.prop('checked')) {
            var UserDTO =  { userDTO:'' +
                    'userId=' + $(this).find('.userId').val() + ';' +
                    'username='+ $(this).find('.username').val() +';' +
                    'initials='+ $(this).find('.initials').val() +';' +
                    'inactive='+ $(this).find('.inactive').val() +' '
            };
        }

        //todo METHOD here.
    });
}

var restUserNRolesDTO = {
    userDTO:'userId=1;username=beg;initials=b;inactive=false',
    roleDTOs:'1=rolename;2=rolename;3=rolename'
};
var restUserDTO = {
    userDTO:'userId=1;username=beg;initials=b;inactive=false'
};
var restKeywordDTO = {
    keyWord:'keyword'
};
var restRoleDTO = {
    roleDTOs:'1=rolename;2=rolename;3=rolename'
};
var RecipeDTO = {

};
var ResourceDTO = {ResourceDTO:'resourceId=1;resourceName=resource;reorder=false;inactive=false'};

function createUser(user){
    $(function (){
        $.ajax({
            type:'GET',
            url:'/rest/users/create',
            data:user,
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
function updateUserByKey(keyword ,aktivity){
    $(function () {
        $.ajax({
            type:'GET',
            url:'/rest/users/update', // SearchId or SearchRow
            data:restUserNRolesDTO,
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
function deleteUser(user){
    $(function () {
        $.ajax({
            type:'GET',
            url:'/rest/users/delete',
            data:restUserNRolesDTO,
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
















