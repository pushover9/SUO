<%@ page import="java.sql.*" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>

 function b_search(){
    $.ajax({
      url:"/main/board",
       data:{"find_field":$("#find_field").val(),
            "find_name":$("#find_name").val()},
       type:"POST"
    });
 }

 </script>
 


<section class="page-section">



<form method="post" action="board">

    <div class="container">
      <div class="row">
     <div class="col-lg-2">

   <h3 class="my-4 text-center">Category</h3>
   <div class="list-group mb-4">
      <a href="/main/board?cate=자유"
      class="list-group-item list-group-item-action text-center font-weight-bold border border-dark">
      자유게시판</a>
      
      <a href="/main/board?cate=유머"
      class="list-group-item list-group-item-action text-center font-weight-bold border border-dark">
      유머게시판</a>
      
      <a href="/main/board?cate=패치노트" 
      class="list-group-item list-group-item-action text-center font-weight-bold border border-dark">
      패치노트</a>
      
      <a href="/main/board?cate=뉴스" 
      class="list-group-item list-group-item-action text-center font-weight-bold border border-dark">
      e-sport뉴스</a>
      
      </div>

   </div>
       <div class="col-lg-10 my-4 mb-4 border border-dark rounded-lg">
        <input type="hidden" name="cate" id="cate" value="${cate}">
        
          <table class="table table-striped">
             <thead>
             <tr>
                <th scope="col" class="text-center" style="width:10%">카테고리</th>
                <th scope="col" class="text-center" style="width:10%">번호</th>
                <th scope="col" style="width:50%">제목</th>
                <th scope="col" class="text-center" style="width:10%">글쓴이</th>
                <th scope="col" class="text-center" style="width:10%">조회수</th>
                <th scope="col" class="text-center" style="width:10%">등록날짜</th>
             </tr>
          </thead>
          
          <tbody>
          <c:if test="${!empty list }">
             <c:forEach var="b" items="${list}"  varStatus="list">
                <tr>
                   <td class="text-center">${b.category }</td>
                   <td class="text-center">
                     ${totalCount - ((page-1)*10+list.index)}
                  </td>
                   <td><a class="b_title" style="color:black; text-decoration: none;"href="/main/board_cont?bno=${b.bno }&page=${page}">${b.title }
                   <c:if test="${b.reply_cnt != 0}">
                   [${b.reply_cnt}]
                   </c:if>
                   </a></td>
                   
                   <td class="text-center">${b.writer }</td>
                   <td class="text-center">${b.viewcnt }</td>
                   <td class="text-center">
                   
                   <c:set var="today" value="<%=new java.util.Date() %>" />
                   <c:set var="day"><fmt:formatDate value="${today}" pattern="MM-dd" /></c:set> 
                   
                   <fmt:parseDate value="${b.regdate }" var="regdate1" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
                   <fmt:formatDate value="${regdate1}" var="reg" pattern="MM-dd" />
                   
                   
                   <c:if test="${day == reg}">
                   <fmt:formatDate value="${regdate1}" pattern="HH:mm"/>
                   </c:if>
                   
                   <c:if test="${day != reg}">
                   <fmt:formatDate value="${regdate1}" pattern="yy-MM-dd"/>
                  </c:if>
                   </td>
                   
                </tr>
             </c:forEach>
          </c:if>
          <c:if test="${empty list }">
          <tr>
             <th colspan="6"> 목록이 없습니다.!</th>
          </tr>
          </c:if>
          </tbody>
          </table>
          <%--페이징 쪽 나누기, 쪽번호 출력 --%>
         
           
                 <%--검색전후 페이징(쪽나누기) --%>
                 
         <div class="text-center">
          
            
             <ul class="pagination" style="justify-content: center;">
               
               <%--검색전 페이징 --%>
               <c:if test="${(empty find_field)&&(empty find_name)}">  
               
                <c:if test="${page <=1}">
                <li class="page-item disabled">   
                     <a class="page-link" href="#" tabindex="-1" aria-disabled="true"><<</a>
               </li>



               </c:if>
               <c:if test="${page >1}">
               <li class="page-item" class="active">
                   <a class="page-link" href="/main/board?cate=${cate}&page=${page-1}">
                   << </a>
                   </li>
               </c:if>
                  
                 <%--쪽번호 출력부분 --%>
                 <c:forEach var="a" begin="${startpage}" end="${endpage}" step="1">
                    <c:if test="${a == page}">
                    <li class="page-item active" aria-current="page" >
                    <a class="page-link" >${a} </a>
                    </li>
                    </c:if>
                    
                    <c:if test="${a != page}">
                       <li class="page-item">
                       <a class="page-link" href="/main/board?cate=${cate}&page=${a}">${a}</a>
                       </li>
                    </c:if>
                   </c:forEach>
               
                  <c:if test="${page>=maxpage}">
                   <li class="page-item disabled">   
                     <a class="page-link" href="#" tabindex="-1" aria-disabled="true">>></a>
                  </li>
                  </c:if>
                  <c:if test="${page<maxpage}">
                        <li class="page-item">
                        <a  class="page-link" href="/main/board?cate=${cate}&page=${page+1}">
                        >>
                        </a>
                     </li>
                  </c:if>
                  </c:if>   
                  
               <%--검색 후 페이징 --%>
               
               <c:if test="${(!empty find_field) || (!empty find_name)}">  
                 <c:if test="${page <=1}">
                 <li class="page-item disabled">   
                     <a class="page-link" href="#" tabindex="-1" aria-disabled="true"><<</a>
               </li>
                </c:if>
                <c:if test="${page >1}">
                <li class="page-item" class="active">
                   <a class="page-link" href="/main/board?cate=${cate}&page=${page-1}">
                   << </a>
                  </li>
                </c:if>
               
                <%--쪽번호 출력부분 --%>
                <c:forEach var="a" begin="${startpage}" end="${endpage}" step="1">
                 <c:if test="${a == page}">
                 <li class="page-item active" aria-current="page" >
                    <a class="page-link" >${a} </a>
                   </li>
                 </c:if>
                 <c:if test="${a != page}">
                  <li class="page-item">
                       <a class="page-link" href="/main/board?cate=${cate}&page=${a}">${a}</a>
                       </li>
                 </c:if>
                </c:forEach>
                
                <c:if test="${page>=maxpage}">
                   <li class="page-item disabled">   
                     <a class="page-link" href="#" tabindex="-1" aria-disabled="true">>></a>
                  </li>
                  </c:if>
                  <c:if test="${page<maxpage}">
                        <li class="page-item">
                        <a  class="page-link" href="/main/board?cate=${cate}&page=${page+1}">
                        >>
                        </a>
                     </li>
                  </c:if>
                </c:if>   
                
                
                     
               
               
            </ul>
         </div>
         
               
          
         <div class="container text-center">
          <div style="margin:0 auto;">
          
             <select class="form-select form-select-lg-mb-3" aria-label="Default select example" id="find_field" name="find_field">
                <option value="title" <c:if test="${find_field == 'title'}">${'selected'}</c:if>>제목</option>
               <option value="cont" <c:if test="${find_field == 'cont'}">${'selected'}</c:if>>내용</option>
             </select>
                <input name="find_name" id="find_name" size="15" value="${find_name}"/>
                <input class="btn btn-secondary" type="submit" value="검색">
             
         </div>
         </div>
          
          <%--페이징 끝 --%>
          
          <div class="text-right">
          <c:if test="${!empty id || !empty sessionid}">
           
              <input type="button" class="btn btn-info mb-3" value="글쓰기" onclick="location='board_write';"/>
            
           
            </c:if>
         </div>
         
         <script>
          var msg="${msg}";
          if(msg== 'SUCCESS'){
             Swal.fire(
                       '처리가 완료되었습니다! ',
                       '',
                       'success'
                  )
          }
         </script>
              </div>
          </div>
          </div>
      <div class="clear"></div>
</form>
</section>
<jsp:include page="../footer/footer.jsp"/>