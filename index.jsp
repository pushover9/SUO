<%--
  Created by IntelliJ IDEA.
  User: lee
  Date: 2021-02-27
  Time: 오전 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ include file="../header/header.jsp"%>

<!-- Masthead-->
<header class="masthead" id="#masthead1">
	<div class="container" >
		<div class="masthead-subheading"></div>
		<div class="masthead-heading text-uppercase">SUO.KR</div>
		
		<form id="search" action="/Main" method="GET">
		<div class="input-group input-group-newsletter col-xl-5 col-lg-5"
			style="float: none; margin: 0 auto;">
			<input type="text" class="form-control" placeholder="유저명 검색" name="title"
				aria-label="Enter email..." aria-describedby="submit-button">
			<div class="input-group-append">
				<button class="btn btn-primary" type="submit" id="submit-button">검색!</button>
			</div>
		</div>
		</form>
	</div>

	
</header>
<!-- Services-->
<section class="page-section">
	<div class="container">
		<div class="text-center">
			<h2 class="section-heading text-uppercase">Services</h2>
			<h3 class="section-subheading text-muted">SUO는 이러한 서비스를 제공합니다.</h3>
		</div>
		<div class="row text-center">
			<div class="col-md-3">
				<span class="fa-stack fa-4x"> <i
					class="fas fa-circle fa-stack-2x text-primary"></i> <i
					class="fas fa-search fa-stack-1x fa-inverse"></i>
				</span>
				<h4 class="my-3">랭킹 및 전적검색 기능</h4>
			</div>
			<div class="col-md-3">
				<span class="fa-stack fa-4x"> <i
					class="fas fa-circle fa-stack-2x text-primary"></i> <i
					class="fas fa-columns fa-stack-1x fa-inverse"></i>
				</span>
				<h4 class="my-3">게시판</h4>
			</div>
			<div class="col-md-3">
				<span class="fa-stack fa-4x"> <i
					class="fas fa-circle fa-stack-2x text-primary"></i> <i
					class="fas fa-comments fa-stack-1x fa-inverse"></i>
				</span>
				<h4 class="my-3">회원/비회원 실시간 채팅기능</h4>
			</div>
			
			<div class="col-md-3">
				<span class="fa-stack fa-4x"> <i
					class="fas fa-circle fa-stack-2x text-primary"></i> <i
					class="fas fa-shopping-cart fa-stack-1x fa-inverse"></i>
				</span>
				<h4 class="my-3">굿즈 판매</h4>
			</div>
		</div>
	</div>
</section>
<%@ include file="../footer/footer.jsp"%>

