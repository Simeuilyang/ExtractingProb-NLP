
var userid = new URLSearchParams(window.location.search).get('userid');
var did = new URLSearchParams(window.location.search).get('did');
// var userID = "dmlfid1348";
// var pdid = "root";
var fname;
var popupdir = did;// 팝업창에서의 현재 dir

//sessionStorage.clear();
console.log("userid: " + userid + " did: " + did);

$('#element').css('margin', '5px');
$(document).ready(function(){
	$('#loadingImg').hide();
})


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
	    console.log(data);
// alert(data);
	   $.ajax({
		   url: "/extractingNLP/file/upload/" + data,
		   data: form,
		   dataType: 'json',
		   processData: false,
		   contentType: false,
		   type: 'POST' || 'GET',
		   success: function(response){
			   // alert("success");
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
			            // console.log(response);
			         },error: function(e){
			            alert("파일 업로드 실패");
			            // console.log(e.responseText);
			         }
			      }); 
			   
		   },error: function(e){
			   alert("fail");
			   // console.log(e.responseText);
		   }
	   }); 
	 })
   

});


function filelist()
{
	console.log("fileList()");
	///file/get/{aid}/{did}
	if(did == "root"){
       did = -1;
    }

	 $.ajax(
       {
          url: "http://localhost:8080/extractingNLP/file/get/"+userid+"/"+did,
          type: 'GET',
          contentType: "application/json; charset=utf-8",
          dataType: 'json',
		  success : function(data){
			var str="";
			var str2='<tr style="border-bottom: solid 10px #D59F71;">';
			if(data.length==0){
				for(var i=0;i<6;i++)
				{
					str+='<td class="container" style ="width:150px; height:220px; word-break:break-all; ><span id = "file" style="width:150px; height: 200px;"</span></td>';
					
				}
				$("#shelf").find("td:eq(0)").after(str);
				
				
			}else{
				console.log("iam in ajax");
				var num=1;
				var num2=0;
				$.each(data, function(){

					var rname = this.fname;
					var name = this.fid+"_"+this.fname;
					var imagename = name.replace(".pdf","1.gif");
					var imagename2 = rname.replace(".pdf","");
					var fid = this.fid;
/*					<td class="container"><span id="file4"><p>김소빈짱</p><img src="../image/add_folder.svg" style="width:150px; height:200px;"><input type="checkbox"></span></td>
*/
					if(num>0&&num<6){
						str+='<td class="container" style ="width:150px; height:220px; word-break:break-all;" ><p style = "font-size:13px;">'+imagename2+'</p><img src="http://via.placeholder.com/150x200/000000/ffffff?text='+imagename2+'" style="width:150px; height:200px; word-break:break-all"><input type="checkbox" name = "chk" id = "chk" disabled value = "'+name+'"></td>';
					}
					else
					{
						str2+='<td class="container" style ="width:150px; height:220px; word-break:break-all;" ><p style = "font-size:13px;">'+imagename2+'</p><img src="http://via.placeholder.com/150x200/000000/ffffff?text='+imagename2+'" style="width:150px; height:200px; word-break:break-all"><input type="checkbox"></td>';
						
					}			
					num=num+1;

			});
			if(num<=6)
			{
				for(var i=0;i<6-num;i++)
				{
					str+='<td class="container" width = 150 style ="width:150px; height:220px; word-break:break-all; ><span id = "file" style="width:150px; height: 200px;"</span></td>';
							
				}
			}
			else
			{
				for(var i=0;i<6-num%6;i++)
				{
					str2+='<td class="container" style ="width:150px; height:220px; word-break:break-all; ><span id = "file" style="width:150px; height: 200px;"</span></td>';
							
				}
				str2+='</tr>';
				
			}
			$("#shelf").find("td:eq(0)").after(str);
			$("#filelist").append(str2);
			}
		}
       }
    ).done((data) => {

	})
}


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



$(document).on('click', '#confirm', function() 
{
	var len = $("input[name=chk]:checked").length;
	if(len == 0)
	{
		alert("파일을 선택해주세요");
	}
	else
	{
		if($("#make_radio").is(":checked"))
		{
			var check = false;
			var radioval = $('input[name="item"]:checked').val();
			var list=[];
			var list2=[];
			if (confirm("문제를 생성하시겠습니까?") == true){
				$("input[name=chk]:checked").each(function() 
				{
					var clickedfile = $(this).val();
					real_filename = clickedfile.replace(".pdf","");
					list.push(clickedfile);
					list2.push(real_filename);
					
				});
				$('#loadingImg').show();
				console.log(list);
					$.ajax({
						url: "http://localhost:8080/extractingNLP/textRank",
					    type: 'GET',
					    contentType: false,
						dataType : 'json',
						data: {'list': list.join(',')},
						complete : function(){
							$('#loadingImg').hide();
							
						}
						
									
					}).done((data) => {
						sessionStorage.setItem("filename", JSON.stringify(list2));
						location.href="editProb";
					          	
					});
						

   			}
		}
		else if($("#move_radio").is(":checked")){
            var modal = document.getElementById('myModal');
	        var span = document.getElementsByClassName("close")[0];                                    
	        modal.style.display = "block";
	        // When the user clicks on <span> (x), close the modal
	        span.onclick = function() {
	            modal.style.display = "none";
	        }
	        // When the user clicks anywhere outside of the modal, close it
	        window.onclick = function(event) {
	            if (event.target == modal) {
	                modal.style.display = "none";
	            }
	        }
			finddir(did);
			
			$(document).on('click','#changeButton',function()
			{
				
				var isradiochecked = $('input[name="item"]:checked').is(':checked');
				if(isradiochecked)
				{
					var check = false;
					var radioval = $('input[name="item"]:checked').val();
					if (confirm("파일을 이동하시겠습니까?") == true){
						$("input[name=chk]:checked").each(function() 
						{
							var clickedfile = $(this).val();
							var strArray = clickedfile.split('_');
							var fid_file = strArray[0];     
	  
							$.ajax(
							{
								url: "http://localhost:8080/extractingNLP/file/move/"+fid_file+"/"+radioval+"/"+userid,
					            type: 'GET',
					            contentType: "application/json; charset=utf-8",
					            data: {},
					            dataType: 'text'
					         }).done((data) => {
								check = true;
								location.reload(true);
					                    
					         });
							
						});
						
					}else{ 
				     return false;
					}
					
					
					
				}
				else
				{
					alert("선택된 폴더가 없습니다. ");
				}
			
			}
		);
		}else if($("#delete_radio").is(":checked"))
		{     
			$("input[name=chk]:checked").each(function() {
				var clickedfile = $(this).val();
				var strArray = clickedfile.split('_');
				var fid_file = strArray[0];     
			if (confirm("파일을 삭제하시겠습니까?") == true){
             $.ajax(
                    {
                       url: "http://localhost:8080/extractingNLP/file/delete/"+fid_file,
                       type: 'GET',
                       contentType: "application/json; charset=utf-8",
                       data: {},
                       dataType: 'text'
                    }
                 ).done((data) => {
                    //alert("선택한 파일이 삭제되었습니다.");
                    location.reload(true);
                 });
			}
			});
			
		}else{
			alert("옵션을 선택해주세요");
		}
	
 }
 });


$(document).on('click','#backdir',function()
{
	if(popupdir == -1)
	{
		alert("현재 폴더가 최상위 폴더 입니다.");
	}
	else
	{
		$.ajax({
			url: "http://localhost:8080/extractingNLP/dir/now/"+userid+"/"+popupdir,
          	type: 'GET',
          	contentType: "application/json; charset=utf-8",
          	dataType: 'json',
		  	success : function(data){
				finddir(data[0].pdid);
			}
		});
	}

}
);

function finddir(directoryid)
{
	popupdir = directoryid;
	var str = new String("");

	$.ajax(
       		{
          		url: "http://localhost:8080/extractingNLP/dir/find/"+userid+"/"+directoryid,
          		type: 'GET',
          		contentType: "application/json; charset=utf-8",
          		dataType: 'json',
		  		success : function(data){
					
					if(data.length==0)
					{

						str+='<p style = "color: rgb(155, 154, 155); padding:auto;"> 현재 폴더 안에 생성된 폴더가 없습니다.</p>';
					}
					else
					{
						$.each(data, function(){
	
							str+='<li><div class = "listdiv" style ="float:left;width:90%; height:40px;" onclick="finddir('+this.did+')">'+this.dname;
							str+='</div><div style="float:right; width:10%;" class="checkmark"><input type="radio" class="radio_item" value="'+this.did+'" name="item" id="'+this.did+'">';
							str+='<label class="label_item" for="'+this.did+'">';
							str+='<span class="checkmark_stem"></span><span class="checkmark_kick"></span></label></div></li>';
						});
						
					}
					$("#mylist").html(str);
			
				}
			});
	
}