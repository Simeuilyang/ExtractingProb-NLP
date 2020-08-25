
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset= UTF-8"
   pageEncoding="UTF-8" session="false"%>

<!doctype html>

<html>

<head>

<title>__Blank__</title>

<meta charset="utf-8">

<meta name="viewport"
   content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">


<link href="${pageContext.request.contextPath}/resources/css/custom.css" rel="stylesheet">

<!-- 제이쿼리 자바스크립트 추가하기 -->

<script src="${pageContext.request.contextPath}/resources/js/jquery-3.4.1.js"></script>

<!-- Popper 자바스크립트 추가하기 -->

<script src= "${pageContext.request.contextPath}/resources/js/popper.min.js"></script>

<!-- 부트스트랩 자바스크립트 추가하기 -->

<script src = "${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

<!-- Custom javascript-->
<script src="${pageContext.request.contextPath}/resources/js/index.js"></script>

</head>
<style>
.navbar-brand {
   font-family: 'Lobster', cursive;
}
</style>
<body>

   <nav class="navbar navbar-expand-lg navbar-light bg-light"
      style="height: 200px; width: 100%">

      <a class="navbar-brand" style="width: 100%; text-align: center;">&nbsp;&nbsp;__Blank__</a>

      <button class="navbar-toggler" type="button" data-toggle="collapse"
         data-target="#navbar">

         <span class="navbar-toggler-icon"></span>

      </button>

   </nav>
   <div style="width: 100%; text-align: center;">

      <div class="container mt-5" style="text-align: center; width: 500px">
         <div class="form-group" style="text-align: left; width: 500px">
            <label>User ID</label> <input type="text" name="userID"
               class="form-control" id="userID">

         </div>

         <div class="form-group" style="text-align: left; width: 500px">

            <label>Password</label> <input type="password" name="userPassword"
               class="form-control" id="userPW">

         </div>

         <!--         <button type="submit" class="btn btn-primary" >Sign in</button> -->
         <div class="form-group" style="text-align: right;">
            <input class="btn btn-primary" type="button" id="signIn"
               style="text-align: right" value="Sign In">
            <button class="btn btn-primary" type="button" id="signUp"
               style="text-align: right">Join US</button>
         </div>
      </div>
   </div>
</body>

</html>