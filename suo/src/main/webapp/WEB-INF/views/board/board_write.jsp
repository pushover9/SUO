<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="../../resources/js/board.js"></script>
<script>
$("#category option:selected").val();
</script>

<section class="page-section">

    <div class="container">
		<div class="row">
     <div class="col-lg-2">

	<h3 class="my-4 text-center">Category</h3>
	<div class="list-group mb-4">
		<a href="/main/board?cate=자유"
		class="list-group-item list-group-item-info text-center font-weight-bold border border-dark">
		자유게시판</a>
		
		<a href="/main/board?cate=유머"
		class="list-group-item list-group-item-action text-center font-weight-bold border border-dark">
		일반 게시판</a>
		
		<a href="/main/board?cate=패치노트" 
		class="list-group-item list-group-item-action text-center font-weight-bold border border-dark">
		패치노트</a>
		
		<a href="/main/board?cate=뉴스" 
		class="list-group-item list-group-item-action text-center font-weight-bold border border-dark">
		e-sport뉴스</a>
		
		</div>

	</div>
	
	<div class="col-lg-10 my-4 mb-4 border border-dark rounded-lg">
		<form method="post" action="board_write_ok" onsubmit="return check();" enctype="multipart/form-data">
			
				
						<select class="mt-3"id="category" name="category">
								<option value="자유" selected="selected">자유</option>
								<option value="유머">유머</option>
								<option value="패치노트" >패치노트</option>
								<option value="뉴스">뉴스</option>
						</select>
				
			<input class="form-control mt-4 mb-2" name="title" id="title" class="form-control mt-4 mb-2" placeholder="제목을 입력하세요"/>
			
 			<input class="form-control mt-4 mb-2" type="text" name="writer" id="writer" size="34" readonly="readonly" value="${id}">
 			
 			<input class="form-control mt-4 mb-2" type="file" name="uploadFile" multiple>
 		
			<textarea class="form-control mt-4" name="content" id="content"
			rows="10" cols="36" placeholder="내용을 입력하세요"></textarea>
			
			<div class="mt-2 text-center">
				<input  class="btn btn-secondary mb-3" type="submit" value="작성완료" />
				<input  class="btn btn-secondary mb-3" type="reset" value="등록 취소" onclick="$('#writer').focus();" />
			</div>
		</form>
	</div>
	</div>
	</div>
	</section>
</body>
</html>
<jsp:include page="../footer/footer.jsp" />