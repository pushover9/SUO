<%@ page import="java.sql.*" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="../header/header.jsp" %>
<link rel="stylesheet" type="text/css" href="../../resources/css/main_board.css">


<div class="container">
    <div class="item item1">게시판 글머리</div>
    <div class="container2">
        <div class="item item2">
            <nav>
              <ul>
                   <li><a href="../../board/board_list">1번 게시판</a></li>
                   <li><a href="board2.jsp">2번 게시판</a></li>
                    <li><a href="board3.jsp">3번 게시판</a></li>
                    <li><a href="#">4번 게시판#</a></li>
                   <li><a href="#">5번 게시판#</a></li>
                   <li><a href="#">6번 게시판#</a></li>
                   <li><a href="#">7번 게시판#</a></li>
                   <li><a href="#">8번 게시판#</a></li>
               </ul>
            </nav>
        </div>
        <div class="item item3">게시판 작성</div>
    </div>

</div>




<jsp:include page="../footer/footer.jsp"/>