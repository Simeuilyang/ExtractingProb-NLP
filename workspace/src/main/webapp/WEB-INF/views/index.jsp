
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset= UTF-8" pageEncoding="UTF-8" session="false" %>

<!doctype html>

<html>

<head>

    <title>Sophia's Harmony</title>

    <meta charset="utf-8">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">
    <!-- 占쏙옙트占쏙옙트占쏙옙 CSS 占쌩곤옙占싹깍옙 -->

    <link rel="stylesheet" href="../../../resources/static/css/bootstrap.min.css">

    <!-- 커占쏙옙占쏙옙 CSS 占쌩곤옙占싹깍옙 -->

    <link rel="stylesheet" href="../../../resources/static/css/custom.css">
    <!-- Popper 占쌘바쏙옙크占쏙옙트 占쌩곤옙占싹깍옙 -->

    <script src="../../../resources/static/js/popper.min.js"></script>

    <!-- <link href="../../../resources/py/signInUp.py"> -->
</head>
  <style>
      .navbar-brand{
          font-family: 'Lobster', cursive;
      }
  </style>
  <body>

      <nav class="navbar navbar-expand-lg navbar-light bg-light"  style="height:200px; width:100%">

          <a class="navbar-brand" href="index.jsp" style="width:100%; text-align:center;">&nbsp;&nbsp;Sophia's Harmony</a>

          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar">

              <span class="navbar-toggler-icon"></span>

          </button>

      </nav>
      <div style="width:100%; text-align:center;">

          <div class="container mt-5" style="text-align:center;width:500px">
                <div class="form-group" style="text-align:left; width:500px">
                    <label>占쏙옙占싱듸옙</label>

                    <input type="text" name="userID" class="form-control" id="userID">
                    
                </div>

                <div class="form-group" style="text-align:left; width:500px">

                      <label>占쏙옙橘占싫�</label>

                      <input type="password" name="userPassword" class="form-control" id="userPW">
                      
                </div>

                  <!--         <button type="submit" class="btn btn-primary" >Sign in</button> -->
                <div class="form-group" style="text-align:right;">
                    <input class="btn btn-primary" type="button" value="占싸깍옙占쏙옙" onclick="location.href='pdfToText.html' " id="signIn" style="text-align:right">
                    <input class="btn btn-primary" type="button" value="회占쏙옙占쏙옙占쏙옙" onclick="location.href='signUp.html'"id="signUp" style="text-align:right">
                </div>
          </div>
      </div>

      
  </body>

</html>
