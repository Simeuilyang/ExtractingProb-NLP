
var ipadr = "localhost";
var host = "8080"

console.log("in index.js");

$(document).on('click', '#signIn', function(){
   console.log("sign in click!");
    $.ajax(
        {
            url: "http://"+ ipadr+":" + host + "/extractingNLP/account/login/" + $("#userID").val() + "/" + $("#userPW").val(),
            type: 'GET',
            contentType: "application/json; charset=utf-8",
            data: {}
        }
    ).done((data) => {
        console.log(data);

      if(data == true)
      {
          $.ajax(
              {
                  url: "http://"+ ipadr+":" + host + "/extractingNLP/account/get/" + $("#userID").val(),
                  type: 'GET',
                  contentType: "application/json; charset=utf-8",
                  data: {}
              }
          ).done((data) => {
              alert(data["name"] +"님, " +"안녕하세요 !");
               location.href="addFile?userid="+ $("#userID").val() + "&did=root";
           });
      }
        else {
            alert("잘못된 로그인 정보입니다.");
            location.reload();
        }
    });
});

$(document).on('click', '#signUp', function(){
   console.log("signup clicked");
    location.href='signUp';
});

$("#title").click((event)=>{
    location.href='#';
})