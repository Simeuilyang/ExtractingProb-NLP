var id = null;
var ipadr = "localhost";
var host = "8080";

$(document).on('click', '#idcheck', function() {
    id = $("#signupID").val();
    console.log(id);
    $.ajax({
        url: "http://"+ipadr+":" + host + "/extractingNLP/account/checkid/" + id,
        type: 'GET',
        contentType: "application/json; charset=utf-8",
        data: {}
    }).done(function (data) {
        console.log(data);

        if (data == true)
            alert("사용가능한 아이디입니다");
        else {
            alert("이미 사용 중인 아이디입니다");
            id = undefined;
        }

    });
});

$(document).on('click', '#signUp', function(){
    var pw = $("#signupPW").val();
    var name = $("#signupNAME").val();
    console.log(id);
    console.log(pw);
    console.log(name);
    if (id == undefined){
        alert("아이디는 필수 입력값입니다.");
        return;
    }
    
    $.ajax({
        url: "http://" + ipadr + ":"+ host + "/extractingNLP/account/add/" + id + "/" + pw + "/" + name,
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        data: {}
    }).done(function (data) {
        console.log(data);
        alert("회원가입이 완료되었습니다.");
        location.href = "/extractingNLP/";
    });

});

$(document).on('click', '#title', function(){
    location.href = "/extractingNLP";
});