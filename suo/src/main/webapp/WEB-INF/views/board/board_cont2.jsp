<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header/header.jsp"%>

<section class="page-section">
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
 <table class="table" >
  <tr class="table-active">
     <td><strong>${b.title }</strong></td>
  </tr>
  <tr>
      <td>작성자:${b.writer }</td>
  </tr>
   <tr>
     <td>조회수:${b.viewcnt }</td>
  </tr>
  <tr>
     <td>등록일 :${b.regdate}</td>
  </tr>
  <tr>
     <td><pre>
     <c:forEach var="upload" items="${upload}">
             <img src="/resources/upload/board/${upload.file_name}" class="d-block w-100" alt="...">
    </c:forEach>
${b.content }
     </pre></td>
  </tr>
  <tr>
     <th>
        <c:if test="${(sessionid == b.writer)|| (id == b.writer)}">
           <input type="button" value="수정" onclick="location='/main/board_edit?bno=${b.bno}&page=${page }';"/>
           <input type="button" value="삭제" onclick="location='/main/board_del?bno=${b.bno}&page=${page }';"/>
        </c:if>
        <input type="button" value="목록" onclick="location='/main/board?cate=${b.category}&page=${page }';"/>
     </th>
  </tr>
  <tr>
     <c:if test="${!empty id || !empty sessionid}">
     <th colspan="2"><input type="text" name="newReplyText" id="newReplyText"
          size="36" placeholder="내용을 입력하세요">
         <input type="button" style="float:right;" id="replyAddBtn" onclick="replyCheck();" value="댓글등록"/>
         
   </th>
   </c:if>
   
   <c:if test="${empty id && empty sessionid}">
     <th colspan="2"><input type="text" name="newReplyText" id="newReplyText"
          size="36" placeholder="로그인을 하시면 댓글을 입력 하실 수 있습니다." readonly="readonly">
   </th>
   </c:if>
   
   
  </tr>
 </table>
 
 <br><hr><br>
 

 <ul id="replies"></ul>
  
 <!-- 댓글 기능 추가 -->
  <div id="modDiv" style="display:none;"><%--수정화면을 안나오게 한다. --%>
  <div class="modal-title" style="display:none;"></div><%--댓글 번호 출력  --%>
   <div>
    <textarea rows="3" cols="30" id="replytext"></textarea>
   </div>
   <div>
    <button type="button" id="replyModBtn">댓글 수정</button>
    <button type="button" id="replyDelBtn">댓글 삭제</button>
    <button type="button" id="closeBtn" onclick="modDivClose();">닫기</button>
   </div>
 </div>
 </div>
 </div>
 <%--댓글 수정 화면 --%>
 </div>
 </section>
 
 
         <script>
         
function replyCheck(){
   var r = document.getElementById("newReplyText")
   if(r.value == ""){
   Swal.fire(
   '댓글 내용을 입력하세요! ',
   '',
   'warning'
   )
   return false;
}
}
         
         </script>
         
<script>


 var bno=${b.bno}; //게시판 번호 저장, 자바스크립트에서 JSTL OR EL을 사용가능함.
 
  var id = '<%=(String)session.getAttribute("id")%>';
  var nid = '<%=(String)session.getAttribute("sessionid")%>';

  getAllList(); //댓글목록함수 호출
  function getAllList(){
    
     $.getJSON("/replies/all/"+bno,function(data){ //비동기식으로 받아온 데이터는 data매개변수에 저장함.
       var str="";
         $(data).each(function(){ //each()함수로 댓글목록을 반복
            str += "<li data-rno='"+this.rno+"' class='replyLi mt-2'>"
            +this.replyer+" : <span class='com' style='color:blue;font-weight:bold;'>"+this.replytext+"</span>";
            if((id===this.replyer) || (nid === this.replyer)){
               str += "<button class='r_edit' style='float:right'>댓글수정</button></li>";
               
               
            }else{
               str += "</li><br/>";
            }
         });
         $('#replies').html(str); //jQuery html()함수로 ul영역 replies에 문자와 태그를 함께 변경적용한다.
        
        
     });
}//getAllList()함수 정의 -> 댓글목록함수

//댓글 삭제
$('#replyDelBtn').on('click',function(){
  var rno=$('.modal-title').html(); //댓글번호를 구함
  
  Swal.fire({
	  title: '삭제하시겠습니까?',
	  text: "",
	  icon: 'warning',
	  showCancelButton: true,
	  confirmButtonColor: '#3085d6',
	  cancelButtonColor: '#d33',
	  confirmButtonText: '확인',
	  cancelButtonText: '취소'
  }).then((result) => {
	  if (result.isConfirmed) {
		  $.ajax({
			     type:'delete',
			     url:'/replies/'+rno,
			     headers:{
			        "Content-Type" : "application/json",
			        "X-HTTP-Method-Override" : "DELETE"
			     },
			     dataType:'text',
			     success:function(data){
			        if(data == 'SUCCESS'){
			        	Swal.fire(
			        		      '삭제되었습니다!',
			        		      '',
			        		      'success'
			        		    )
			           $('#modDiv').hide();
			           getAllList();
			        }
			     }
			  });
	  }
	})
  
  
  
  
});

//댓글 수정화면 닫기
function modDivClose(){
  $('#modDiv').hide(); //수정화면을 천천히 닫는다.
}

//댓글수정화면
$('#replies').on('click','.r_edit',function(){
  var reply=$(this).parent(); //parent() 는 부모요소를 선택 -> li태그를 가리킴, this는 버튼
  var rno=reply.attr('data-rno'); //data-rno속성값 댓글번호를 저장함.
  var replytext=reply.children('.com').text(); //댓글내용
  
  $('.modal-title').html(rno); //댓글번호 표시
  $('#replytext').val(replytext); //댓글내용
  $('#modDiv').show(); //수정화면을 천천히 보게 함.
});

//댓글 수정 완료
$('#replyModBtn').on('click',function(){
    var rno=$('.modal-title').html();//댓글번호
    var replytext=$('#replytext').val();//수정할 댓글 내용
    $.ajax({
      type:'put',
      url:'/replies/'+rno,
      headers:{
         "Content-type":"application/json",
         "X-HTTP-Method-Override":"PUT"
      },
      data:JSON.stringify({replytext:replytext}),
      datatype:'text',
      success:function(data){
         if(data == 'SUCCESS'){
            Swal.fire('댓글 수정 성공!','','success');
            $('#modDiv').hide();//댓글 수정화면을 닫는다.
            getAllList();//댓글 목록 함수 호출
         }
      }
    });
 });

 
 //댓글추가
 $('#replyAddBtn').on('click',function(){
   var replyer=$('#newReplyWriter').val(); //댓글 작성자
   var replytext=$('#newReplyText').val(); //댓글내용
   
   $.ajax({
      type:'post', //자료를 보내는법
      url:'/replies', //서버 매핑주소
      headers:{ //Http 헤더앞에 붙는 형식
         "Content-Type":"application/json","X-HTTP-Method-Override":"POST"
      },
      dataType:'text',//자료형식
      data:JSON.stringify({ //보내지는 자료가 JSON형태인 키,값,쌍
         bno:bno,         //게시판 번호
         replyer:replyer,   //댓글 작성자
         replytext:replytext //댓글내용
      }),
      success:function(data){
         //비동기시으로 받아오는 것이 성공시 호출되는 콜백함수이다. 받아온 데이터는 data매개변수에 저장됨
         if(data == 'SUCCESS'){
            Swal.fire('댓글 등록 성공','','success')
            $('#newReplyText').val('');
            getAllList(); //댓글 목록함수 호출
         }
      }
   });
});
 </script>
<jsp:include page="../footer/footer.jsp" />