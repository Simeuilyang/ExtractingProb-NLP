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
    
    
    <!-- 드롭다운 CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/dropdown.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/popup.css">
    
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
	.container p{
		position:absolute;
		top:-30px;
	}
    
</style>

<nav class="navbar navbar-expand-lg navbar-light bg-light" style="height:200px; width:100%">
	
	<div style="position:absolute;width:120px ;left:95%;bottom:80%; top-margin:0px"><a href="/extractingNLP" id="signout" style="color:black">로그아웃</a></div>
    <a class="navbar-brand" href="#" style="width:100%; text-align:center;">&nbsp;&nbsp;__Blank__</a>

    

</nav>
<body>
<!-- dropdown 시작 -->
    <div id="dropdown" style="margin-bottom:0px">
        <div style="width:170px; height:40px; text-align:right; display:inline-block;margin-bottom:0px">
            <a id ="linktohome" href="" data-lnb="home"><img src="<%=request.getContextPath()%>/resources/images/icon_home.svg" alt="" style="width:35px; height:35px; padding:5px"></a>
        
        </div>
        <div style="width:80px; height:40px; text-align:center; display:inline-block;margin-bottom:0px">
            <a id = "linktoback" href="" data-lnb="home"><img src="<%=request.getContextPath()%>/resources/images/back.png" alt="" style="width:35px; height:35px; padding:5px"></a>
            
        </div>

        <div style = "display:inline-block;margin-bottom:0px">
            <script>makedropdown()</script>
            <div id="dropdown1" class="dropdown" style = "margin-bottom:0px">

            </div>
            <div id = "dropdown2" class="dropdown"  style = "margin-bottom:0px">

            </div>
        </div>
        <hr style = "margin-top:0px">
    </div>
<!-- dropdown  --> 


	<div id='loadingImg' style='position:absolute; z-index:900000; stop:50%; left:39%'><img src="<%=request.getContextPath()%>/resources/images/loading.gif" style='position: absolute; display: block; margin: 0px auto;'/></div>
	
	
    <div id="showbox" style="width:100%; text-align:center; padding-top:30px; padding-left:11%;padding-right:2%">
        <div style="float:right">
           <div style="padding-bottom:5px; display:inline-block">
                <button class="btn btn-primary" style="display:none" id="confirm">확인</button>
                <button class="btn btn-warning" id="choice" value = "0"  onclick="choice_click(this.value)" >선택</button>
            </div>
            <div style="text-align:left">
                <label for="make_radio" id ="label" style="color:white"><input type="radio"  name="chk_info" style="display:none" id = "make_radio"> 문제생성 </label>
            </div>
            <div style="text-align:left">
                <label for="move_radio" id ="label2" style="color:white"><input type="radio" name="chk_info" style="display:none" id = "move_radio"> 이동 </label>
            </div>
            <div style="text-align:left">
                <label for="delete_radio" id ="label3" style="color:white"><input type="radio" name="chk_info" style="display:none" id = "delete_radio"> 삭제 </input>
            </div>
            
			
        </div>
		
        
            
		<table>


        		
                <thead style="display:none">
                    <tr>
                        <th id="one"></th><th id="two"></th><th id="three"></th><th id="four"></th><th id="five"></th><th id="six"></th>
                    </tr>
                </thead>
                <tbody id="filelist">
                    
                    <tr id = "shelf"  style="border-bottom: solid 10px #D59F71;">
                    
                        <td class="container" style ="width:150px; height:220px; word-break:break-all;" ><span id="file1"><p></p><img id = "addFolder" src="<%=request.getContextPath()%>/resources/images/add_folder.svg" style="cursor:pointer; width:150px; height:200px; word-break:break-all"></span></td>
                    </tr>

                </tbody>
            </table>
		<script>filelist();</script>

        

        	
        
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
        <div style="display:inline-block">
        
         <form action="/extractingNLP/file/upload" id="uploadForm" method="POST" enctype="multipart/form-data" accept-charset="UTF-8" style="display:inline-block">
             
            <input type="file" name="file" class="form-control" id="inputFile" placeholder="파일을 추가해주세요" style="display:inline-block; width:600px; text-align:center"/>
           <!--  <input type="submit" value="추가" class="btn btn-primary" id="addButton" name="addButton" style="display:inline-block;text-align:center"> -->
         </form>
         <button id="addButton" class="btn btn-primary" name="addButton" style="display:inline-block;text-align:center">추가</button>  
         </div>  
    </div>
    
   <div id="myModal" class="modal">
 
      <!-- Modal content -->
      <div class="modal-content" style="width: 30%;">
                                                                     
        <p align="center"><span id = "backdir" style="float: left; display:inline-block; opacity: 0.5;"><img src="<%=request.getContextPath()%>/resources/images/back.png" style="width:25px; height:25px;"></img></span><b>이동할 폴더를 선택해주세요</b><span class="close">&times;</span>  </p>
 		<div style = "margin:auto">
        <div id = "folder1" style="overflow-x:hidden; width:380px; height:250px; float:left;  display: inline-block;left-padding:50px;" >
			<ul class="mylist" id = "mylist">
				
		    </ul>
        </div>
      
		<div style = "margin:auto">
        <button id = "changeButton" class="btn btn-primary" name="changeButton" style="width : 100px; display:inline-block;text-align:center">파일 이동</button>
      </div>
      </div>
   
    </div>
   
   </div>
    
</body>
<script>	
$("#signout").click(()=>{
	   
    sessionStorage.clear();
    
});
</script>
</html>