
var userid = new URLSearchParams(window.location.search).get('userid');
var did = new URLSearchParams(window.location.search).get('did');

console.log("userid:" + userid + " did:" + did);

$(document).on('click', '#addFolder', function() {
	var dname = prompt("Please enter a name for the new directory.");
	//alert(dname);
	if(dname != null){
		var pdid;
		console.log("1: pdid: " + pdid + " dname:" + dname + " did: " + did);
		if((did == -1) || did == "root"){
		    pdid = -1;
		 }else{
			 pdid = did;
		 }
		
		console.log("2: pdid: " + pdid + " dname:" + dname);
		
		$.ajax(
			    {
			       url: "http://localhost:8080/extractingNLP/dir/add/"+pdid+"/"+dname+"/"+userid,
			       type: 'GET',
			       contentType: "application/json; charset=utf-8",
			       data: {},
			       dataType: 'text'
			    }
			 ).done((data) => {
				 //alert("new directory success!");
				 location.reload(true);
			});
	}
});


function makedropdown()
{
	if(did == "root"){
       did = -1;
    }
	$.ajax(
       {
          url: "http://localhost:8080/extractingNLP/dir/get/"+userid+"/"+did,
          type: 'GET',
          contentType: "application/json; charset=utf-8",
          dataType: 'json',
		  success : function(data){
			var str="";
			var str2="";
			var nowdname;
			$.each(data[0], function()
			{

				if(this.dname == "root")
				{
					nowdname = "home";
				}
				else
				{
					nowdname = this.dname;
				}

				str+= '<button class = "dropbtn">'+nowdname+'</button>';
				str2+= '<button class = "dropbtn">∨</button>';
				if(this.dname != "root"){
					str+='<div class="dropdown-content">';
				}
				str2+='<div class="dropdown-content">';
				
				
			});

			$.each(data[1], function()
			{
				str2+='<div><div style="float: left; width: 80%;"><a href= /extractingNLP/addFile?userid='+userid+'&did='+this.did+'>'+this.dname+'</a></div><div style="float: left; width: 20%;"><button id = "deleteButton" value = "'+this.did+'" style = "cursor:pointer;background:#00ff0000; border:0; padding-top:8px; padding-right:12px;">x</button></div></div>';
				
			});
	
			$.each(data[2], function()
			{
				if(nowdname != "home"){
					str+='<a href= /extractingNLP/addFile?userid='+userid+'&did='+this.did+'>'+this.dname+'</a>';
				}
					
			});
			if(nowdname!="home"){
				str+='</div>';
			}
			str+='</div>'
			$("#dropdown1").append(str);
			$("#dropdown2").append(str2);
			$("#linktohome").attr("href", "/extractingNLP/addFile?userid="+userid+"&did=root");
			$("#linktoback").attr("href", "javascript:history.back()");
		}
	}
       
    ).done((data) => {

	})
}
$(document).on('click', '#deleteButton', function() 
{
	if (confirm("정말 삭제하시겠습니까?\n폴더 안의 파일도 함께 삭제됩니다.") == true){
		$.ajax(
			{
				url: "http://localhost:8080/extractingNLP/dir/delete/"+userid+"/"+this.value,
                type: 'GET',
                contentType: "application/json; charset=utf-8",
                data: {},
                dataType: 'text'
            }).done((data) => {
                    alert("선택한 폴더가 삭제되었습니다.");
                    location.reload(true);
                 });
		
	}else{ 
     return false;
	}
});


