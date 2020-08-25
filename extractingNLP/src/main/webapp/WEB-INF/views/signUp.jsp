
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset= UTF-8"
   pageEncoding="UTF-8" session="false"%>
   
<!DOCTYPE html>
<html>
<head>

    <title>__Blank__</title>

    <meta charset="utf-8">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <!-- 부트스트랩 CSS 추가하기 -->

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">

    <!-- 커스텀 CSS 추가하기 -->

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/custom.css">
    
    <!-- 제이쿼리 자바스크립트 추가하기 -->

    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.4.1.js"></script>

    <!-- Popper 자바스크립트 추가하기 -->

    <script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>

    <!-- 부트스트랩 자바스크립트 추가하기 -->

    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    
    <script src="${pageContext.request.contextPath}/resources/js/signup.js"></script>
    
    <link href="${pageContext.request.contextPath}/resources/resources/js/signup.js">
</head>
    <style>
        .navbar-brand {
            font-family: 'Lobster', cursive;
        }
    </style>
    <body>

        <nav class="navbar navbar-expand-lg navbar-light bg-light" style="height:200px; width:100%">

            <a class="navbar-brand" style="width:100%; text-align:center;">&nbsp;&nbsp;__Blank__</a>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar">

                <span class="navbar-toggler-icon"></span>

            </button>

        </nav>
        <div style="width:100%; text-align:center;"><!--class="col-sm-6 col-md-offset-3" -->
            <div class="container mt-5" style="text-align:center;width:500px">
                <div class="form-group" style="text-align:left;width:500px">
                    <label for="inputName">성명</label>
                    <input type="text" class="form-control" id="signupNAME" placeholder="이름을 입력해주세요">
                </div>
                <div class="form-group" style="text-align:left;width:500px">
                    <label for="InputEmail">아이디</label>
                    <div>
                        <input type="text" class="form-control" id="signupID" placeholder="아이디를 입력해주세요" style="display:inline-block; width:400px">
                        <button class="btn btn-warning" id="idcheck" style="display:inline-block;">중복확인</button>
                    </div>
                </div>
                <div class="form-group" style="text-align:left;width:500px">
                    <label for="inputPassword">비밀번호</label>
                    <input type="password" class="form-control" id="signupPW" placeholder="비밀번호를 입력해주세요">
                </div>
                <div class="form-group" style="text-align:left;width:500px">
                    <label for="inputPasswordCheck">비밀번호 확인</label>
                    <input type="password" class="form-control" id="signupPWcheck" placeholder="비밀번호 확인을 위해 다시 한번 입력해주세요">
                </div>


                <div class="form-group text-center">
                    <button id="signUp" class="btn btn-primary">
                        회원가입<i class="fa fa-check spaceLeft"></i>
                    </button>
                    <button class="btn btn-warning" >
                        가입취소<i class="fa fa-times spaceLeft"></i>
                    </button>
                </div>
            </div>
        </div>
    </body>
</html>