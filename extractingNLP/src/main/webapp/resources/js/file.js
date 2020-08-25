
var userid = new URLSearchParams(window.location.search).get('userid');
var did = new URLSearchParams(window.location.search).get('did');
//var userID = "dmlfid1348";
//var pdid = "root";
var fname;

console.log("userid: " + userid + " did: " + did);

$(document).on('click', '#addButton', function() {
   console.log("addButton click !!");
   
   var form = new FormData(document.getElementById('uploadForm'));
   
	 let nBytes = 0;
	 let oFiles = document.getElementById("inputFile").files;
	 fname = oFiles[0].name
	 console.log("fname: " + fname);
	 if(did == "root"){
	    did = -1;
	 }
	 
	 console.log("userid: " + userid);
	 console.log("did: " + did);
	 $.ajax(
	    {
	       url: "http://localhost:8080/extractingNLP/file/add/"+did+"/"+fname+"/"+userid,
	       type: 'GET',
	       contentType: "application/json; charset=utf-8",
	       data: {},
	       dataType: 'text'
	    }
	 ).done((data) => {
//	    console.log(data);
//	    alert(data);
	   $.ajax({
		   url: "/extractingNLP/file/upload/" + data,
		   data: form,
		   dataType: 'json',
		   processData: false,
		   contentType: false,
		   type: 'POST' || 'GET',
		   success: function(response){
			   //alert("success");
			   $.ajax({
			         url: "/extractingNLP/file/image/" + data,
			         data: form,
			         dataType: 'json',
			         processData: false,
			         contentType: false,
			         type: 'POST' || 'GET',
			         success: function(response){
			            alert("파일 업로드 성공");
						location.reload(true);
			            //console.log(response);
			         },error: function(e){
			            alert("fail");
			            //console.log(e.responseText);
			         }
			      }); 
			   
		   },error: function(e){
			   alert("fail");
			   //console.log(e.responseText);
		   }
	   }); 
	 })
   

});

var checklist=[];
function filelist()
{
	///file/get/{aid}/{did}
	if(did == "root"){
       did = -1;
    }
	console.log("herehere");
	 $.ajax(
       {
          url: "http://localhost:8080/extractingNLP/file/get/"+userid+"/"+did,
          type: 'GET',
          contentType: "application/json; charset=utf-8",
          data: checklist,
          dataType: 'json',
		  success : function(data){
			var str="";
			if(data.length==0){
				console.log("no consequence");
				
			}else{
				var num=0;
				var num2=0;
				$.each(data, function(){
					if(num%5==0){
						str+='<div class = "container" name="shelf" style="text-align:left" >';

					}
					//dir이미지 추가
					//str+='<img id="addFolder" src="extractingNLP/resources/images/add_folder.svg" alt="" style="cursor:pointer;">';
					//	
					num2 = 182+(num%5)*200;
					num3 = num2.toString();
					var rname = this.dname;
					var name = this.did+"_"+this.dname;
					var imagename = name.replace(".pdf","1.gif");
					var imagename2 = rname.replace(".pdf","");
/*					str +='<img src="<spring:url value = '+"\'"+"/fileImage/"+imagename+"\'/>\"" + ' style="width:200px; height:260px;padding-right:15px;padding-left:15px"/>';*/
					num2 = 30*(num%5)+(num%5-1);
					str+='<a href="https://placeholder.com"><img src="http://via.placeholder.com/200x260/000000/ffffff?text='+imagename2+'" alt="'+imagename2+'" style="width:200px; height:260px;padding-right:15px;padding-left:15px;">';
					str+='<input type="checkbox" name="chk" id = "chk" disabled value="blue"  style = "top:5px;left:'+num3+'px;" visibility = "hidden">';
				    /*console.log(str);*/
					num=num+1;
					if(num%5==0){
						str+='<p id="borderimg2" style="text-align:center;"></div>'
					}
			
			});
			$("#filelist").html(str);
			}
		}
       }
    ).done((data) => {

	})
	console.log("herehere2")
}

function getContextPath() {
	var hostIndex = location.href.indexOf( location.host ) + location.host.length;
	return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
};

function choice_click(v) {
	if(v == "0")
	{
		document.getElementById("choice").innerHTML = "취소"
		document.getElementById("make_radio").style.display = ""; // 보여줌
		document.getElementById("move_radio").style.display = ""; // 보여줌
		document.getElementById("delete_radio").style.display = ""; // 보여줌
		document.getElementById("confirm").style.display = ""; // 보여				
		document.getElementById("choice").value = "1";
		document.getElementById("label").style.color = "black"
		document.getElementById("label2").style.color = "black"
		document.getElementById("label3").style.color = "black"
		var x = document.getElementsByName('chk');
		var len = x.length;

		for(var i=0; i<len; i++){
			x[i].disabled = false;
		}
	}
	else if(v == "1")
	{
		document.getElementById("choice").innerHTML = "선택"
		document.getElementById("make_radio").style.display = "none"; 
		document.getElementById("move_radio").style.display = "none"; 
		document.getElementById("delete_radio").style.display = "none"; 
		document.getElementById("confirm").style.display = "none"; 
		document.getElementById("choice").value = "0";
		document.getElementById("label").style.color = "white"
		document.getElementById("label2").style.color = "white"
		document.getElementById("label3").style.color = "white"
		var x = document.getElementsByName('chk');
		var len = x.length;

		for(var i=0; i<len; i++){
			x[i].disabled = true;
		}
				
	}
}
