<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>SUO.KR ADMIN</title>
	
	<script
  src="https://code.jquery.com/jquery-3.6.0.js"
  integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
  crossorigin="anonymous"></script>
  
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
  
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<style>
	 body { font-family:'맑은 고딕', verdana; padding:0; margin:0; }
	 ul { padding:0; margin:0; list-style:none;  }
	
	 div#root { width:90%; margin:0 auto; }
	 
	 header#header { font-size:60px; padding:20px 0; }
	 header#header h1 a { color:#000; font-weight:bold; }
	 
	 nav#nav { padding:10px; text-align:right; }
	 nav#nav ul li { display:inline-block; margin-left:10px; }
	
	 section#container { padding:20px 0; border-top:2px solid #eee; border-bottom:2px solid #eee; }
	 section#container::after { content:""; display:block; clear:both; }
	 aside { float:left; width:200px; }
	 div#container_box { float:right; width:calc(100% - 200px - 20px); }
	 
	 aside ul li { text-align:center; margin-bottom:10px; }
	 aside ul li a { display:block; width:100%; padding:10px 0;}
 	 aside ul li a:hover { background:#eee; }
	 
	 footer#footer { background:#f9f9f9; padding:20px; }
	 footer#footer ul li { display:inline-block; margin-right:10px; }
</style>

</head>
<body>

<div id="root">
	<header id="header">
		<div id="header_box">
			<%@ include file="include/header.jsp" %>
		</div>
	</header>

	<nav id="nav">
		<div id="nav_box">
			<%@ include file="include/nav.jsp" %>
		</div>
	</nav>
	
	<section id="container">
		<aside>
		 	<%@ include file="include/aside.jsp" %>
		</aside>
		<div id="container_box">
			본문 영역
		</div>
	</section>

	<footer id="footer">
		<div id="footer_box">
			<%@ include file="include/footer.jsp" %>
		</div>		
	</footer>

</div>
</body>
</html>