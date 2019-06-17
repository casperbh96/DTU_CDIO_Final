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

function get(url, sFunc, eFunc){
    $.ajax({
        type:'GET',
        url: url,
        success: function (data) {
            sFunc(data);
        },
        error: function (data) {
            eFunc(data);
        }
    });
}

function post(data, url, sFunc, eFunc){
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

function put(data, url, sFunc, eFunc){
    $.ajax({
        type:'DELETE',
        url: url,
        contentType:'application/json',
        success: function (data) {
            sFunc(data);
        },
        error: function (data) {
            eFunc(data);
        }
    });
}
