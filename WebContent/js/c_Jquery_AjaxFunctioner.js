

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


function createUser(){
    var data = $('#UserDTO').serializeJSON();
    $(function (){
        $.ajax({
            type:'POST',
            url:'/rest/users/create',
            contentType:'application/json',
            data:data,
            success: function (data) {
                $("#UsersContentFlasher").html( JSON.stringify(data) );
            },
            error: function () {
                $("#UsersContentFlasher").html(" Error ");
            }
        });
    });

}
function getUsers(){
    var data = $('#searchField').serializeJSON();
    alert(data);
    $(function () {
        $.ajax({
            type:'POST',
            url:'/rest/users/get',
            contentType:'application/json',
            data:data,
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
            data:restUser,
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
            data:restUser,
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



















