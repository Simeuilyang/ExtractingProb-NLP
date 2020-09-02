
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset= UTF-8"
	pageEncoding="UTF-8" session="false"%>

<!DOCTYPE html>
<html>

<head>

<title>__Blank__</title>


<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- 부트스트랩 CSS 추가하기 -->

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">

<!-- 커스텀 CSS 추가하기 -->

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/custom.css">

<link href="${pageContext.request.contextPath}/resources/js/file.js">

<script
	src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-3.4.1.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>


</head>
<style>
td.check {
	background-image: url(../image/yes.png);
	background-repeat: no-repeat;
	background-position: left top;
	vertical-align: top;
	height: 80px;
	width: 20px;
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
<div style="position:absolute;width:120px ;left:95%;bottom:55%; top-margin:0px"><a href="/extractingNLP" id = "signout" style="color:black">로그아웃</a></div>
	<a class="navbar-brand" href="javascript:goHome()"
		style="width: 100%; text-align: center;">&nbsp;&nbsp;__Blank__</a>

	
</nav>
<body>
	<div class="card">
		<div class="card-body" style="padding: 30px">
			<div id="table" class="table-editable">
				<span class="table-add float-right mb-3 mr-2"> <a href="#!"
					class="text-success"> <i class="fas fa-plus fa-2x"
						aria-hidden="true"></i>
				</a>
				</span>
				<table
					class="table table-bordered table-responsive-md table-striped text-center">
					<thead>
						<tr>
							<th class="text-center" style="width: 5%">&nbsp;</th>
							<th class="text-center" style="width: 90%">Question</th>
							<th id = "title_answer"  style="width: 5%" class="text-center">정답</th>
						</tr>
					</thead>
					<tbody id="final_table_item"></tbody>
				</table>
			</div>
		</div>



	</div>
	</div>
	<div
		style="float: right; padding: 2px; text-align: right; display: inline-block">
		<button class="btn btn-primary" onclick="checkAnswer()">채점하기</button>
	</div>
	<div
		style="float: right; padding: 2px; text-align: right; display: inline-block">
		<button class="btn btn-primary" onclick="createPDF()" id=exportButton>다운로드</button>
	</div>
</body>


<link rel="stylesheet" type="text/css"
	href="http://www.shieldui.com/shared/components/latest/css/light/all.min.css" />
<script type="text/javascript"
	src="http://www.shieldui.com/shared/components/latest/js/shieldui-all.min.js"></script>
<script type="text/javascript"
	src="http://www.shieldui.com/shared/components/latest/js/jszip.min.js"></script>

<script>
	//var len = sessionStorage.getItem("length");
	//var tmp_question = [];
	//for (var i = 0; i < len; i++) {
		//tmp_question.push(sessionStorage.getItem("question" + i));

	//}
	
	var tmp_answer = sessionStorage.getItem("answer");
	var answer = JSON.parse(tmp_answer);
	
	var tmp_question2 = sessionStorage.getItem("question");
	var tmp_question = JSON.parse(tmp_question2);
	
	
	console.log(tmp_question);
	console.log(answer);
	
	var empty1 = "&nbsp;<input type='text' id='answer_";
    var empty2 = "' style='width:100px; text-align:center;'></input>&nbsp;";
	var question = tmp_question;
	//var answer = [ [ "김소빈" ], [ "천재" ], [ "짱", "최고" ] ]//답 받는 배열

	function start() {

		
		
		for(var i = 0; i < answer.length; i++)
		{
			for(var j = 0; j < answer[i].length; j++)
			{
				console.log(answer[i][j]);
				if(answer[i][j][answer[i][j].length - 1] == "\\n")
				{
					answer[i][j] = answer[i][j].slice(0, -1);
				}
				
			}	
			
		}
		
		console.log(answer);
		var empty_tag1 = "<input type='text' id='answer_";
		var empty_tag2 = "' style='width:100px; text-align:center;'></input>&nbsp;";
		//var question = ["전자책도 독자가 ‘사고 싶은’ &nbsp;<input type='text' id='answer_0' style='width:100px; text-align:center;'></input>&nbsp;를 담은 책이 많이 팔립니다.", "우리는 &nbsp;<input type='text' id='answer_1' style='width:100px; text-align:center;'></span>&nbsp;를 상품으로 만드는 일이 본업인 ‘책을 내는 출판사’입니다.", "책은 인간의 생각과 문화를 담은 &nbsp;<input type='text' id='answer_2'style='width:100px; text-align:center;'></span> &nbsp;저장소이다."];//textrank로 만든 question을 이 array에 담기
		$('#final_table_item').empty();
		for ( var i in question) {

			var total_str = "";
			var str_arr = question[i].split('______');
			var tmp_j = 0;
			for (var j = 0; j < str_arr.length; j++) {
				if (str_arr[j + 1] != null) {
					var empty_tag = "";
					empty_tag += empty_tag1;
					empty_tag += i + "_" + tmp_j++;
					empty_tag += empty_tag2;
					
					str_arr.splice(j + 1, 0, empty_tag);
					j++;
				}
			}
			
			for (var j = 0; j < str_arr.length; j++) {
				total_str += str_arr[j];
			}

			var num = i;

			var rowItem = "<tr>"
			rowItem += "<td id='check"
					+ i
					+ "' style='background-repeat: no-repeat; background-position: left top; vertical-align: top; height:80px; width:20px;background-size: 100%'>"
					+ (++num) + ".</td>"
			rowItem += "<td class = 'pt-3-half' >"
					+ total_str
					+ "<span id = 'data_"+i+"' style='color:red;float:right; font-size:small'>&nbsp;</span></td>"
			rowItem += "<td id = 'show_answer"+i+"'><span class = 'table-remove'><button type = 'button' class = 'btn btn-danger btn-rounded btn-sm my-0' value = '"
					+ i
					+ "' id = '"
					+ i
					+ "' onclick = 'showAnswer(this.value)'>정답보기</button></span></td>"

			$('#final_table_item').append(rowItem)
		}
	}

	function showAnswer(i) {

		if ($("#" + i).text() == "정답보기") {
			$("#" + i).html("정답숨김")

			var tmp_str = "";

			for (var j = 0; j < answer[i].length; j++) {
				tmp_str += ("  * " + answer[i][j])
			}
			console.log(tmp_str);

			$("#data_" + i).text(tmp_str);
		} else {
			$("#" + i).html("정답보기")
			$("#data_" + i).text(" ")
		}

		//var sentence = $("#data_"+i).text()

	}
	function checkAnswer() {
		var title_answer = document.getElementById("title_answer");
		title_answer.setAttribute("style", "display: none");
		console.log(answer);
		
		for ( var i in answer) {
			var flag = 0;//성공 = 1 실패 = 0
			var show_answer = document.getElementById("show_answer"+i);
			show_answer.setAttribute("style", "display: none");
			var tmp_str = "";

			for (var j = 0; j < answer[i].length; j++) {
				console.log("i:" + i + " j: " + j);
				var check = $("#answer_" + i + "_" + j).val();
				console.log(check)
				console.log(typeof(check));
				tmp_str += ("  * " + answer[i][j])

				answer[i][j]=answer[i][j].trim();				
				
				console.log(answer[i][j]);

				if (check==answer[i][j]) {
					flag = 1;
				}

				console.log(tmp_str);

			}

			$("#data_" + i).text(tmp_str);

			if (flag == 1) {
				console.log("성공")
				document.getElementById('check' + i).style.backgroundImage = "url(${pageContext.request.contextPath}/resources/images/yes.png)"

			} else {
				console.log("실패")
				document.getElementById('check' + i).style.backgroundImage = "url(${pageContext.request.contextPath}/resources/images/no.png)"
			}
		}

	}

	function createPDF() {
        var sTable = document.getElementById('table').innerHTML;
    
        var style = "<style>";
        style = style + "table {width: 100%;font: 12px Calibri;}";
        style = style + "table, th, td {border: solid 1px #DDD; border-collapse: collapse; height:20px;";
        style = style + "padding: 2px 3px;text-align: center;}";
        style = style + "</style>";

        // CREATE A WINDOW OBJECT.
        var win = window.open('', '', 'height=450,width=700');
        
        win.document.write('<html><head>');
        win.document.write('<title>_blank_</title>');    //<title> FOR PDF HEADER.
        win.document.write(style);          // ADD STYLE INSIDE THE HEAD TAG.
        win.document.write('</head>');
        win.document.write('<body>');
        win.document.write(sTable);         // THE TABLE CONTENTS INSIDE THE BODY TAG.
        win.document.write('</body></html>');

        win.document.close(); 	// CLOSE THE CURRENT WINDOW.

        win.print();    // PRINT THE CONTENTS.
    }
	
	$("#signout").click(()=>{
	   
	    sessionStorage.clear();
	});
	
	start()
</script>


</html>
<!-- 참고 사이트: https://mdbootstrap.com/docs/jquery/tables/editable/#! -->
<!-- 동적 테이블 생성 : https://lasdri.tistory.com/594 -->
<!-- 드래그앤드롭(sort) 참고 사이트: https://developer.mozilla.org/ko/docs/Web/API/HTML_%EB%93%9C%EB%9E%98%EA%B7%B8_%EC%95%A4_%EB%93%9C%EB%A1%AD_API  -->
<!-- remove 클릭시 테이블에서는 요소 빼고 변수에 저장해뒀다가 리무브드테이블에 다시 작성-->
<!-- add 클릭시 테이블에서는 요소 빼고 변수에 저장해뒀다가 에디터블테이블에 다시 작성 -->