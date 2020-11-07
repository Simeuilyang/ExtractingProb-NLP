<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<title>__Blank__</title>

<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
   content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/dropdown.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/popup.css">
    
<!-- 부트스트랩 CSS 추가하기 -->

<link rel="stylesheet"
   href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">

<!-- 커스텀 CSS 추가하기 -->

<link rel="stylesheet"
   href="${pageContext.request.contextPath}/resources/css/custom.css">

<script
   src="${pageContext.request.contextPath}/resources/js/jquery-3.4.1.js"></script>
<script
   src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
<script
   src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<script
   src="${pageContext.request.contextPath}/resources/js/transferQuestion.js"></script>

</head>
<style>
#question{
	disabled;
}
.navbar-brand {
   font-family: 'Lobster', cursive;
}

#borderimg2 {
   text-align: right;
   width: 1000px;
   border-top: 10px solid transparent;
   border-image: url(http://i.stack.imgur.com/bZ8Lk.png) 30 stretch;
}

.pt-3-half {
   padding-top: 1.4rem;
}
</style>
<script>
function goHome(){
	var account = sessionStorage.getItem("account_id");
	location.href = "/extractingNLP/addFile?userid="+ account +"&did=root";
}

</script>
<nav class="navbar navbar-expand-lg navbar-light bg-light"
   style="height: 80px; width: 100%">
<div style="position:absolute;width:120px ;left:95%;bottom:55%;"><a href="/extractingNLP" id = "signout" style="color:black">로그아웃</a></div>
   <a class="navbar-brand" 
      style="width: 100%; text-align: center;" href="javascript:goHome();">&nbsp;&nbsp;__Blank__</a>

   <button class="navbar-toggler" type="button" data-toggle="collapse"
      data-target="#navbar">

      <span class="navbar-toggler-icon"></span>

   </button>

</nav>
<body>
   <div class="card" style="width: 70%; float: right">
      <!--                            -->
      <h4
         class="card-header text-center font-weight-bold text-uppercase py-4">Editable
         table</h4>
      <div class="card-body" style="padding: 30px">
            <div id="table">
            <span class="table-add float-right mb-3 mr-2"> <a href="#!"
               class="text-success"> <i class="fas fa-plus fa-2x"
                  aria-hidden="true" id = "add_question"></i>
            </a>
            </span>
            <table
               class="table table-bordered table-responsive-md table-striped text-center"
               id="table">
               <thead><!--   class="table-editable"id="editable_table_head" -->
                  <tr>
                     <th class="text-center" style="width: 80%">Question</th>
                     
                     <th class="text-center">Sort</th>
                     <th class="text-center">Remove</th>
                     <th class="text-center" style="display:none">Answer</th>
                  </tr>
               </thead>
               <tbody id="editable_table_item">
 					<!-- tr 동적 생성 -->
               </tbody>
            </table>
            <div style="padding: 10px; text-align: right">
               
               <button class="btn btn-primary" id = "solve" >풀어보기</button>
            </div>
         </div>
      </div>


   </div>
   <div class="card" style="width: 30%; display: inline-block">
      <h4
         class="card-header text-center font-weight-bold text-uppercase py-4">removed
         table</h4>
      <div class="card-body" style="padding: 30px">
         <div id="table" class="table-editable">
            <table
               class="table table-bordered table-responsive-md table-striped text-center">
               <thead id="removable_table_head">
                  <tr>
                     <th class="text-center" style="width: 90%">Question</th>
                     <th class="text-center">Add</th>
                  </tr>
               </thead>
               <tbody id="removable_table_item">
               </tbody>
            </table>
            
         </div>
      </div>

   </div>
   
      <div id="myModal" class="modal">
 
      <!-- Modal content -->
      <div class="modal-content" style="width: 30%;">
                                                                     
        <p align="center"><span id = "backdir" style="float: left; display:inline-block; opacity: 0.5;"></span><span class="close">&times;</span>  </p>
 		<div style = "margin:auto">
        <div id = "collection" style="overflow-x:hidden; width:380px; height:250px; float:left;  display: inline-block;left-padding:50px;" >
			<ul class="collection">
				<label id = "question"><b>새로 생성할 문제를 입력해주세요.</b></label>
				<textarea type="text" id="i_question" style="width:300px;height:100px";></textarea><br><br>
				<label id = "answer"><b>빈칸으로 만들고 싶은 정답을 입력해주세요. </b></label>
				<input type="text" placeholder="정답이 여러개일 경우 쉼표(,)로 나눠주세요." style="width:300px" id="i_answer">
		    </ul>
		    
        </div>
      
		<div style = "margin:auto">
        <button id = "createButton" class="btn btn-primary" name="createButton" style="width : 100px; display:inline-block;text-align:center">문제 만들기</button>
      </div>
      </div>
   
    </div>
   
   </div>


</body>
<link rel="stylesheet" type="text/css" href="http://www.shieldui.com/shared/components/latest/css/light/all.min.css" />

<script src="http://www.shieldui.com/shared/components/latest/js/shieldui-all.min.js"></script>

<script src="http://www.shieldui.com/shared/components/latest/js/jszip.min.js"></script>


<script>

var tmp_filename = sessionStorage.getItem("filename");
var filename = JSON.parse(tmp_filename);
var question = [];
var answer = [];

for(var i = 0; i<filename.length; i++)
{
	var result = readTextFile('${pageContext.request.contextPath}/resources/file/'+filename[i]+'_out.txt', '${pageContext.request.contextPath}/resources/file/questions/' +filename[i]+ '_keword.txt');
	question.push(result["question"]);
	answer.push(result["answer"]);
	
}
	

//var tmp_answer = sessionStorage.getItem("answer");
//var answer = JSON.parse(tmp_answer);

console.log(result);
console.log(question);
console.log(answer);

var solve_question = [];
var solve_answer = [];
$('#editable_table_item').empty();

$(document).on('click', '#solve', function(){
   console.log("solve click!");
   console.log($('#editable_table_item tr').length);
   
   for(var i = 0; i< $('#editable_table_item tr').length; i++)
   {   
      var tr = $('#editable_table_item tr:eq(' + i +')');
       var td = tr.children();

      var itemObj = new Object()
      itemObj.question = td.eq(0).text();
      itemObj.order = td.eq(1).text();
	  itemObj.answer = td.eq(3).text();
	  
	  var split_arr = itemObj.answer.split('_');
      console.log(itemObj.question);
	  console.log(itemObj.answer);
	  console.log(split_arr);
	  
	  console.log(itemObj.question);
      solve_question.push(itemObj.question);
      solve_answer.push(split_arr);
      
      //sessionStorage.setItem("question" + i, itemObj.question);  
   }
   
   
   //sessionStorage.setItem("length", i);
   sessionStorage.setItem("question", JSON.stringify(solve_question));
   sessionStorage.setItem("answer", JSON.stringify(solve_answer));
   
   console.log(solve_answer);
   location.href = "solveProb";
});

$(document).on('click', '#add_question', function(){
   console.log("add_question click!");
   
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
   
   $(document).on('click', '#createButton', function(){
	  console.log("create click") ;
	  
	  var new_q = $("#i_question").val()
	  var new_a = $("#i_answer").val()
	  
	  console.log(new_q)
	  console.log(new_a)
	  
	  var new_answer = new_a.split(',');
	  console.log(new_answer);
	  
	  for(var i = 0; i<new_answer.length; i++)
		{
			if(new_answer[i][0] == " ")//단어의 처음에 빈칸 있으면 제거	
				new_answer[i] = new_answer[i].slice(1);
		 		//new_answer[i].splice(0, 1);
			if(new_answer[i][new_answer[i].length-1] == " ")//단어의 끝에 빈칸 있으면 제거
				new_answer[i] = new_answer[i].slice(0, new_answer[i].length-1);
				//new_answer[i].splice(new_answer[i].length-1, 1);
			
			var n = new_q.indexOf(new_answer[i]);
			
			if(n != -1){
				console.log(new_answer[i]);

          		new_q = new_q.substr(0, n) + empty_tag + new_q.substr(n + new_answer[i].length);
          	
          	}
			
		}		

	  var modal = document.getElementById('myModal');
	  modal.style.display = "none";
	  
	  
	  
	  var rowItem = "<tr>"
      rowItem +=  "<td class = 'pt-3-half' id = 'question' name = 'question'>" + new_q + "</td>"
      rowItem += "<td class = 'pt-3-half'>"
      rowItem += "<span class = 'table-up'>"
      rowItem += "<a href = '#!' class = 'indigo-text'>"
      rowItem += "<i class = 'fas fa-long-arrow-alt-up' aria-hidden = 'true' style='color:black'></i></a></span>"
      rowItem += "<span class = 'table-down'>"
      rowItem += "<a href = '#!' class = 'indigo-text'>"
      rowItem += "<i class = 'fas fa-long-arrow-alt-down' aria-hidden = 'true' style='color:black'></i></a></span>"
      rowItem += "</td>"
      rowItem += "<td><button type = 'button' class = 'btn btn-danger btn-rounded btn-sm my-0' >Remove</button></td>"
      
    var answer_str = "";
   	  for(var j = 0; j< new_answer.length; j++)
   	  {
   			  answer_str += new_answer[j];
   			  if(j != new_answer.length -1)
   			  	answer_str += "_";
   	  }
   	  
      
    	  rowItem += "<td class = 'pt-3-half' name = 'answer' style = 'display: none'>" + answer_str + "</td>"
          rowItem += "</tr>"
      
   	  console.log(answer_str);
   	  
      
      $('#editable_table_item').append(rowItem)
      
      alert("문제가 생성되었습니다!");
   });
});

var empty_tag = "______";

for(var k = 0; k<question.length; k++)
{
	for (var i = 0; i < question[k].length; i++) {

		var total_str = "";
		var str_arr = question[k][i].split('_');
		for (var j = 0; j < str_arr.length; j++) {
			if (str_arr[j + 1] != null) {
				str_arr.splice(j + 1, 0, empty_tag);
				j++;
			}
		}
		
		for (var j = 0; j < str_arr.length; j++) {
			total_str += str_arr[j];
		}

      var rowItem = "<tr>"
      rowItem += "<td class = 'pt-3-half' name = 'question' id = 'question' >" + total_str + "</td>"
      rowItem += "<td class = 'pt-3-half'>"
      rowItem += "<span class = 'table-up'>"
      rowItem += "<a href = '#!' class = 'indigo-text'>"
      rowItem += "<i class = 'fas fa-long-arrow-alt-up' aria-hidden = 'true' style='color:black'></i></a></span>"
      rowItem += "<span class = 'table-down'>"
      rowItem += "<a href = '#!' class = 'indigo-text'>"
      rowItem += "<i class = 'fas fa-long-arrow-alt-down' aria-hidden = 'true' style='color:black'></i></a></span>"
      rowItem += "</td>"
      rowItem += "<td><button type = 'button' class = 'btn btn-danger btn-rounded btn-sm my-0' >Remove</button></td>"
   	  rowItem += "<td class = 'pt-3-half' name = 'answer' style = 'display: none'>"
   	  
   	  var answer_str = "";
   	  for(var j = 0; j<answer[k][i].length; j++)
   	  {
   			  answer_str += answer[k][i][j];
   			  if(j != answer[k][i].length -1)
   			  	answer_str += "_";
   	  }
   	  rowItem += answer_str;
   	  rowItem += "</td>"
      rowItem += "</tr>"
      
      $('#editable_table_item').append(rowItem)
}
}


$('#removable_table_item').on("click", "button", function() {
    $(this).closest("tr").remove();
    
    var tr = $(this).closest("tr");
    var td = tr.children();

   var itemObj = new Object()
   itemObj.question = td.eq(0).text();
   itemObj.answer = td.eq(2).text();

   console.log(tr);
   console.log(td);
   console.log(itemObj.question);
   console.log(itemObj.answer);
   
   var rowItem = "<tr>"
      rowItem += "<td class = 'pt-3-half' id = 'question' >" +itemObj.question + "</td>"
      rowItem += "<td class = 'pt-3-half'>"
      rowItem += "<span class = 'table-up'>"
      rowItem += "<a href = '#!' class = 'indigo-text'>"
      rowItem += "<i class = 'fas fa-long-arrow-alt-up' aria-hidden = 'true' style='color:black'></i></a></span>"
      rowItem += "<span class = 'table-down'>"
      rowItem += "<a href = '#!' class = 'indigo-text'>"
      rowItem += "<i class = 'fas fa-long-arrow-alt-down' aria-hidden = 'true' style='color:black'></i></a></span>"
      rowItem += "</td>"
      rowItem += "<td><button type = 'button' class = 'btn btn-danger btn-rounded btn-sm my-0' >Remove</button></td>"
      rowItem += "<td class = 'pt-3-half' name = 'answer'  style = 'display: none'>" + itemObj.answer + "</td>"
      rowItem += "</tr>"
         
   console.log(rowItem);
   $('#editable_table_item').append(rowItem);
});

$('#editable_table_item').on("click", "button", function() {
    $(this).closest("tr").remove();
    
    var tr = $(this).closest("tr");
    var td = tr.children();
    
    
    var trNum = $(this).closest('tr').prevAll().length; 
    console.log('trNum : ' + trNum);

    //const $row = $(this).parents("tr");
    var index = tr.rowIndex;

    console.log(index);
    
    answer.splice(index, 1);
    
    console.log("지운후 :");
    console.log(answer);
    
   var itemObj = new Object()
   itemObj.question = td.eq(0).text();
   itemObj.order = td.eq(1).text();
   itemObj.answer = td.eq(3).text();
   
   console.log(tr);
   console.log(td);
   console.log(itemObj.question);
   // contenteditable = 'true'
   var rowItem = "<tr>"
   rowItem += "<td class = 'pt-3-half' id = 'question'>" + itemObj.question + "</td>"
   rowItem += "<td><button type = 'button' class = 'btn btn-danger btn-rounded btn-sm my-0'>Add</button></td>"
   rowItem += "<td class = 'pt-3-half' name = 'answer' id = 'data' style = 'display: none'>" + itemObj.answer + "</td>"
   rowItem += "</tr>"
         
   console.log(rowItem);
   $('#removable_table_item').append(rowItem);
});

const $tableID = $('#editable_table_item');
$tableID.on('click', '.table-up', function () {

const $row = $(this).parents('tr');

if ($row.index() === 0) {
    return;
}

$row.prev().before($row.get(0));
});

$tableID.on('click', '.table-down', function () {

const $row = $(this).parents('tr');
$row.next().after($row.get(0));
});

jQuery(function ($) {
    $("#exportButton").click(function () {
        // parse the HTML table element having an id=exportTable
        var dataSource = shield.DataSource.create({
            data: "#exportTable",
            schema: {
                type: "table",
                fields: {
                    Question: { type: String }
                    //Sort: {type:String},
                    //remove: {type: String}

                }
            }
        });

        // when parsing is done, export the data to PDF
        dataSource.read().then(function (data) {
            console.log(dataSource)
            var pdf = new shield.exp.PDFDocument({
                author: "PrepBootstrap",
                created: new Date()
            });

            pdf.addPage("a4", "portrait");

            pdf.table(
                50,
                50,
                data,
                [
                    { field: "Question", title:"__Blank__", width: 450}
                ],
                {
                    margins: {
                        top: 50,
                        left: 50
                    }
                }
            );

            pdf.saveAs({
                fileName: "__Blank__"
            });
        });
    });
});

</script>
<script>
$("#signout").click(()=>{
	   
    sessionStorage.clear();
});
</script>
</html>
<!-- 참고 사이트: https://mdbootstrap.com/docs/jquery/tables/editable/#! -->
<!-- 동적 테이블 생성 : https://lasdri.tistory.com/594 -->
<!-- 드래그앤드롭(sort) 참고 사이트: https://developer.mozilla.org/ko/docs/Web/API/HTML_%EB%93%9C%EB%9E%98%EA%B7%B8_%EC%95%A4_%EB%93%9C%EB%A1%AD_API  -->
<!-- remove 클릭시 테이블에서는 요소 빼고 변수에 저장해뒀다가 리무브드테이블에 다시 작성-->
<!-- add 클릭시 테이블에서는 요소 빼고 변수에 저장해뒀다가 에디터블테이블에 다시 작성 -->
