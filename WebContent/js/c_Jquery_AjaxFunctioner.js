

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
}


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

function createProduct(){

    $(function (){
        $.ajax({
            type:'GET',
            url:'/rest/production/create',
            data:restRoleDTO,
            contentType:'application/json',
            dataType: 'json',
            success: function (data) {
                $("#ProductionContentFlasher").html( JSON.stringify(data) );
            },
            error: function () {
                $("#ProductionContentFlasher").html(" Error ");
            }
        });
    });

}
function getProduct(searchMethod,Id){

    $(function () {
        $.ajax({
            type:'GET',
            url:'/rest/production/get',
            data:{ searchMethod:searchMethod , Id:Id },
            contentType:'application/json',
            dataType: 'json',
            success: function (data) {
                $("#ProductionContentFlasher").html( JSON.stringify(data) );
            },
            error: function () {
                $("#ProductionContentFlasher").html(" Error ");
            }
        });
    });

}
function searchProduct(keyword){
    $(function () {
        $.ajax({
            type:'GET',
            url:'/rest/production/search', // SearchId or SearchRow
            data:{keyword:keyword },
            contentType:'application/json',
            dataType: 'json',
            success: function (data) {
                $("#ProductionContentFlasher").html( JSON.stringify(data) );
            },
            error: function () {
                $("#ProductionContentFlasher").html(" Error ");
            }
        });
    });
}
function updateProduct(){
    $(function () {
        $.ajax({
            type:'GET',
            url:'/rest/production/update', // SearchId or SearchRow
            data:restRoleDTO,
            contentType:'application/json',
            dataType: 'json',
            success: function (data) {
                $("#ProductionContentFlasher").html( JSON.stringify(data) );
            },
            error: function () {
                $("#ProductionContentFlasher").append(" Error ");
            }
        });
    });
}
function deleteProduct(){
    $(function () {
        $.ajax({
            type:'GET',
            url:'/rest/production/delete',
            data:restRoleDTO,
            contentType:'application/json',
            dataType: 'json',
            success: function (data) {
                $("#ProductionContentFlasher").html( JSON.stringify(data) );
            },
            error: function () {
                $("#ProductionContentFlasher").html(" Error ");
            }
        });
    });
}















