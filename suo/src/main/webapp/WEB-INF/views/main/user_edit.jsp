<%@ page import="java.sql.*"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../header/header.jsp"%>

<script type="text/javascript" src="/resources/js/jquery.js"></script>
<script type="text/javascript" src="/resources/js/account.js"></script>
<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
<script>
   $(function(){
      var pw = $(".user_pw");
      var pw2 = $(".user_pw2");
      $('.pwcheck').hide();
      pw2.on("keyup",function(){   
         if(pw.val() ==pw2.val()){
            $mark="<font color='blue' size='3'>비밀번호 일치.</font>";
            $('.pwcheck').text('');
            $('.pwcheck').show();
            $('.pwcheck').append($mark);
            return false;
         }else{
            $mark="<font color='blue' size='3'>비밀번호 불일치.</font>";
            $('.pwcheck').text('');
            $('.pwcheck').show();
            $('.pwcheck').append($mark);
            return false;
         }   
      });
      
   });
   $(function(){
      $(".mail_list").change(function(){
         var mail_list = $(this).val();
         if(mail_list == "직접입력"){
            $("input[name='user_email02']").val('');
            $("input[name='user_email02']").attr('readonly',false);
         }else{
            $("input[name='user_email02']").val(mail_list);
            $("input[name='user_email02']").attr('readonly',true);
         }
      });
   });
   
   
</script>
<section>

   <article class="container">
      <div class="page-header">
         <div class="col-md-6 col-md-offset-3">
            <h3>회원정보 수정</h3>
         </div>
      </div>
      <div class="col-sm-6 col-md-offset-3">
         <form role="form" method="post"action="/user_edit_ok" name="edit_form" onsubmit="return edit_check(this)">
            <div class="form-group">
               <label for="user_name">아이디</label> 
               <input type="text" name="user_id" class="form-control col-xs-5" id="user_id" readonly="readonly" value="${u.user_id}">
            </div>
            <div class="form-group">
               <label for="user_name">성명</label> 
               <input type="text" name="user_name" class="form-control user_name" readonly="readonly"value="${u.user_name}">
            </div>
            <div class="form-group">
               <label for="user_email">이메일 주소</label> 
               <input type="text" name="user_email01" class="form-control user_email01" placeholder="이메일 아이디를 입력해주세요">
               <div style="display:flex; flex-direction:row;">   
                  <input name="user_email02" class="form-control user_email02" value="dreamwiz.com" style="width:50%;"size="18" onchange="mail_list();"readonly />
                  <select   name="mail_list" class="form-control mail_list" style="width:50%;" aria-label="Default select example">
                     <option value="dreamwiz.com" selected="selected">dreamwiz.com</option>
                     <option value="google.com">google.com</option>
                     <option value="naver.com">naver.com</option>
                     <option value="daum.net">daum.net</option>
                     <option value="직접입력">직접 입력</option>
                  </select>   
               </div>
               
            </div>
            <div class="form-group">
               <label for="user_pw">비밀번호</label> 
               <input type="password" name="user_pw" class="form-control user_pw" placeholder="비밀번호를 입력해주세요">
            </div>
            <div class="form-group">
               <label for="user_pw2">비밀번호 확인</label> <input
                  type="password" class="form-control user_pw2"
                  placeholder="비밀번호 확인을 위해 다시한번 입력 해 주세요">
               <span class="pwcheck"></span>   
            </div>
            <div class="form-group">
               <label for="user_phone">휴대폰 번호</label>
               <div style="display:flex; flex-direction:row;">
                  <select name="user_phone01" style="width:40%;" class="form-control user_phone01" >
                     <option value="010" selected="selected">010</option>
                     <option value="011">011</option>
                     <option value="017">017</option>
                     <option value="018">018</option>
                  </select>
                  <input type="text" name="user_phone02" style="width:30%;"class="form-control user_phone02"size="4" maxlength="4" />
                  <input type="text" name="user_phone03" style="width:30%;"class="form-control user_phone03" size="4" maxlength="4" />
               </div>      
            </div>
            


            <div class="form-group text-center">
               <button type="submit" id="join-submit" class="btn btn-primary">
                  회원수정<i class="fa fa-check spaceLeft"></i>
               </button>
               <button type="reset" class="btn btn-warning">
                  수정취소<i class="fa fa-times spaceLeft"></i>
               </button>
            </div>
         </form>
      </div>

   </article>

</section>

<jsp:include page="../footer/footer.jsp" />