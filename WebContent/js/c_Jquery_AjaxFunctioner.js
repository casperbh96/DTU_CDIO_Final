

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

function createUser(){

    $(function (){
        $.ajax({
            type:'GET',
            url:'/rest/users/create',
            data:restUserNRolesDTO,
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
function getUsers(searchMethod, Id){

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
function searchUser(searchMethod, keyword){
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
function updateUser(){
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
function deleteUser(){
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
function getInaktiveUsers(){
    $("#UsersContentFlasher").html( JSON.stringify("NOT IMPLEMENTED") );
}

function createRoles(){

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
function getRoles(searchMethod,Id){

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
function searchRoles(keyword){
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
function updateRoles(){
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
function deleteRoles(){
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

function createResource(){

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
function getResource(searchMethod,Id){

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
function searchResource(searchMethod,keyword){
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
function updateResource(){
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
function deleteResource(){
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
function getReOrdersResource (){
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
/*
function createRecipe(){

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
function getRecipe(searchMethod,Id){

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
function searchRecipe(searchMethod,keyword){
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
function updateRecipe(){
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
function deleteRecipe(){
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
function getReOrdersRecipe (){
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
}*/














