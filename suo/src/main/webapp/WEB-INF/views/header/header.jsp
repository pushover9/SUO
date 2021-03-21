<%--
  Created by IntelliJ IDEA.
  User: lee
  Date: 2021-02-27
  Time: 오전 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
   content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Suo - 커뮤니티 사이트</title>

<!-- 제이쿼리 cdn -->
<script src="https://code.jquery.com/jquery-3.6.0.js"
   integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
   crossorigin="anonymous"></script>

<!-- sweetalert2 -->
<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>

<link rel="icon" type="image/x-icon"
   href="../resources/assets/img/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js"
   crossorigin="anonymous"></script>
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
   rel="stylesheet" type="text/css" />
<link
   href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic"
   rel="stylesheet" type="text/css" />
<link
   href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700"
   rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="../resources/css/styles.css" rel="stylesheet" />




</head>
<body id="page-top">
   <!-- Navigation-->
   <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"
      id="mainNav">
      <div class="container">
         <a class="navbar-brand js-scroll-trigger" href="/index">SUO.KR</a>
         <button class="navbar-toggler navbar-toggler-right" type="button"
            data-toggle="collapse" data-target="#navbarResponsive"
            aria-controls="navbarResponsive" aria-expanded="false"
            aria-label="Toggle navigation">
            Menu <i class="fas fa-bars ml-1"></i>
         </button>
         <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav text-uppercase ml-auto">
               <li class="nav-item"><a class="nav-link js-scroll-trigger"
                  href="../main/ranking">랭킹</a></li>
               <li class="nav-item"><a class="nav-link js-scroll-trigger"
                  href="../main/board">게시판</a></li>
               <li class="nav-item"><a class="nav-link js-scroll-trigger"
                  href="../main/chatting">실시간 채팅</a></li>
                  <li class="nav-item"><a class="nav-link js-scroll-trigger"
                  href="../shop/list?c=104&l=2">굿즈</a></li>
            </ul>
         </div>
         </div>
         
            
            <div class="text-right">   
                  <c:if test="${empty id && empty sessionid}">
                  <a href="/main/login"> login</a>
                  </c:if>
                        
                        
                  <c:if test="${!empty id && empty sessionid}">
                  <a href="/user_edit">회원정보 수정</a> | <a href="/logout">logout</a>
                  </c:if>
                  
                  <c:if test="${!empty sessionid}">
                  <%--로그인 이후 화면 --%>
                  <span style="color:lightblue; "><strong >${sessionid}</strong>님 로그인을 환영합니다.</span>
                  <a href="/logout"> logout</a>
                  </c:if>
            </div>   
            
   </nav>