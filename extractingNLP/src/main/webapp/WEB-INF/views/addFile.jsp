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
    
    <!-- 부트스트랩 CSS 추가하기 -->

    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- 커스텀 CSS 추가하기 -->

    <link href="${pageContext.request.contextPath}/resources/css/custom.css" rel="stylesheet">

   <!-- 제이쿼리 자바스크립트 추가하기 -->
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.4.1.js"></script>
    
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
    
    <!-- Popper 자바스크립트 추가하기 -->
    <script src= "${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
    
    <!-- 부트스트랩 자바스크립트 추가하기 -->
    <script src = "${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    
    <!-- form 자바스크립트 추가하기 -->
    <script src = "${pageContext.request.contextPath}/resources/js/jquery.form.min.js"></script>
    
    <!-- Custom javascript-->
    <script src="${pageContext.request.contextPath}/resources/js/file.js" charset="utf-8"></script>
    <script src="${pageContext.request.contextPath}/resources/js/dir.js" charset="utf-8"></script>
   
    <script src="http://code.jquery.com/jquery-3.2.0.min.js"></script>
	<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
	
</head>

<style>

    .navbar-brand {
        font-family: 'Lobster', cursive;
    }

    #borderimg2 {
        text-align: right;
        width: 1000px;
        border-top: 10px solid transparent;
        border-image: url(http://i.stack.imgur.com/bZ8Lk.png) 30 stretch;
    }
    .img{
    	position:relative;
    }
    .container {
		  position: relative;
		}
		.container input[type="checkbox"] {
		  position: absolute;
		}
		
</style>
<nav class="navbar navbar-expand-lg navbar-light bg-light" style="height:200px; width:100%">

    <a class="navbar-brand" href="index.jsp" style="width:100%; text-align:center;">&nbsp;&nbsp;__Blank__</a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar">

        <span class="navbar-toggler-icon"></span>

    </button>

</nav>
<body>
    <div id="showbox" style="width:100%; text-align:center; padding-top:30px; padding-left:11%;padding-right:2%">
        <div style="float:right">
           <div style="padding-bottom:5px">
                <button class="btn btn-warning" id="choice" value = "0"  onclick="choice_click(this.value)" >선택</button>
            </div>
            <div style="text-align:left">
                <label for="make_radio" id ="label" style="color:white"><input type="radio" onclick="editProb.jsp" name="chk_info" style="display:none" id = "make_radio"> 문제생성 </label>
            </div>
            <div style="text-align:left">
                <label for="move_radio" id ="label2" style="color:white"><input type="radio" name="chk_info" style="display:none" id = "move_radio"> 이동 </label>
            </div>
            <div style="text-align:left">
                <label for="delete_radio" id ="label3" style="color:white"><input type="radio" name="chk_info" style="display:none" id = "delete_radio"> 삭제 </input>
            </div>
            <div style="text-align:left">
                <button class="btn btn-warning" style="display:none" onclick="location.href='editProb'" id="confirm">확인</button>

            </div>
			
        </div>
		
        
            
		<div id="filelist">
        	
   
        	<script>filelist();</script>
        	
            <%-- <div name="shelf" style="text-align:left" >
            	<script>filelist();</script>
                <img src="<%=request.getContextPath()%>/resources/images/add_folder.svg" alt="" style="width:200px; height:260px;padding-right:15px;padding-left:15px">
                <img src="<%=request.getContextPath()%>/resources/images/add_folder.svg" alt="" style="width:200px; height:260px;padding-right:15px;padding-left:15px">
                <img src="<%=request.getContextPath()%>/resources/images/add_folder.svg" alt="" style="width:200px; height:260px;padding-right:15px;padding-left:15px">
                <img src="<%=request.getContextPath()%>/resources/images/add_folder.svg" alt="" style="width:200px; height:260px;padding-right:15px;padding-left:15px">
                <img src="<%=request.getContextPath()%>/resources/images/add_folder.svg" alt="" style="width:200px; height:260px;padding-right:15px;padding-left:15px">
                <p id="borderimg2" style="text-align:center;">
            </div>
            <div name="shelf" style="text-align:left" >
                <img src="<%=request.getContextPath()%>/resources/images/add_folder.svg" alt="" style="width:200px; height:260px;padding-right:15px;padding-left:15px">
                <img src="<%=request.getContextPath()%>/resources/images/add_folder.svg" alt="" style="width:200px; height:260px;padding-right:15px;padding-left:15px">
                <img src="<%=request.getContextPath()%>/resources/images/add_folder.svg" alt="" style="width:200px; height:260px;padding-right:15px;padding-left:15px">
                <img src="<%=request.getContextPath()%>/resources/images/add_folder.svg" alt="" style="width:200px; height:260px;padding-right:15px;padding-left:15px">
                <img src="<%=request.getContextPath()%>/resources/images/add_folder.svg" alt="" style="width:200px; height:260px;padding-right:15px;padding-left:15px">
                <p id="borderimg2" style="text-align:center;">
            </div>
            <div name="shelf" style="text-align:left" >
                <img src="<%=request.getContextPath()%>/resources/images/add_folder.svg" alt="" style="width:200px; height:260px;padding-right:15px;padding-left:15px">
                <img src="<%=request.getContextPath()%>/resources/images/add_folder.svg" alt="" style="width:200px; height:260px;padding-right:15px;padding-left:15px">
                <img src="<%=request.getContextPath()%>/resources/images/add_folder.svg" alt="" style="width:200px; height:260px;padding-right:15px;padding-left:15px">
                <img src="<%=request.getContextPath()%>/resources/images/add_folder.svg" alt="" style="width:200px; height:260px;padding-right:15px;padding-left:15px">
                <img src="<%=request.getContextPath()%>/resources/images/add_folder.svg" alt="" style="width:200px; height:260px;padding-right:15px;padding-left:15px">
                <p id="borderimg2" style="text-align:center;">
            </div> --%>

        </div>
        
        
        <!--  <div>
            <div name="shelf" style="text-align:left">
                <img id="addFolder" src="<%=request.getContextPath()%>/resources/images/add_folder.svg" alt="" style="cursor:pointer;width:130px; height:150px;padding-right:15px;padding-left:15px">
                <span id="add_image1"></span>
                <p id="borderimg2" style="text-align:center;">
            </div>
        </div> 
        -->

    </div>
    
    <div id="input" style="width:100%; text-align:center; padding-top:30px;padding-left:5%; padding-bottom:50px">
     	
          
              
           <!--
         <input type="file" class="form-control" id="inputFiles" placeholder="파일을 추가해주세요" style="display:inline-block; width:600px; text-align:center">
        <button class="btn btn-primary" type="button" id="addButton" name="addButton" style="display:inline-block;text-align:center">추가</button>
        -->
         <form action="/extractingNLP/file/upload" id="uploadForm" method="POST" enctype="multipart/form-data">
             
            <input type="file" name="file" class="form-control" id="inputFile" placeholder="파일을 추가해주세요" style="display:inline-block; width:600px; text-align:center"/>
           <!--  <input type="submit" value="추가" class="btn btn-primary" id="addButton" name="addButton" style="display:inline-block;text-align:center"> -->
         </form>
         <button id="addButton" class="btn btn-primary" name="addButton" style="display:inline-block;text-align:center">추가</button>    
    </div>
    
   
   
    
</body>
<script>
   
	function add_image() {
	    //이미지또는 폴더 추가하는 방법 구현
	}
</script>
</html>