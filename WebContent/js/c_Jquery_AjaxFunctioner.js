

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

    $(function (){
        $.ajax({
            type:'GET',
            url:'/rest/users/create',
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
function getUsers(searchMethod, Id){
    $(function () {
        $.ajax({
            type:'GET',
            url:'/rest/users',
            data:{ searchMethod:searchMethod , String:Id },
            contentType:'application/json',
            succes: function (data) {
                $("#UsersContentFlasher").html( JSON.stringify(data) );
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
            succes: function (data) {
                $("#UsersContentFlasher").html( JSON.stringify(data) );
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
            succes: function (data) {
                $("#UsersContentFlasher").html( JSON.stringify(data) );
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
            succes: function (data) {
                $("#UsersContentFlasher").html( JSON.stringify(data) );
            }
        });
    });
}



















