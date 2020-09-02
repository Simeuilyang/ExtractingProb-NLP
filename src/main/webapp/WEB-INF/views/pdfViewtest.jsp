<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>test</title>
</head>
<body>
	<button id="testButton" onclick="testButton()" style="display:inline-block;text-align:center">pdf새창띄우기</button>
	
</body>
<script>
	function testButton(){
		console.log("js!!");
		window.open('pdftest', '_blank');
	}
</script>
</html>