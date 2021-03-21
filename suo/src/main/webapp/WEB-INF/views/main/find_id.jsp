<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../header/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<script type="text/javascript" src="/resources/js/jquery.js"></script>
<script type="text/javascript" src="/resources/js/account.js"></script>
<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
<script>
   function check(form){
      if(form.user_name.value == ""){
         Swal.fire(
                 '경고! 이름 미입력 ',
                 '',
                 'warning'
                 )
         form.user_name.focus();
         return false;
      }
      if(form.user_email01.value == ""){
         Swal.fire(
                 '경고! 이메일 아이디 미입력 ',
                 '',
                 'warning'
                 )
         form.user_email01.focus();
         return false;
      }
      if(form.user_email02.value == ""){
         Swal.fire(
                 '경고! 이메일 주소 미입력 ',
                 '',
                 'warning'
                 )
         return false;
      }
      
   }
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
</head>
<body>
<section class="page-section">

   <article class="container mt-3">
      <form method="post" action="/find_id_ok" onsubmit="return check(this)">
      <div class="page-header">
         <div class="col-md-6 col-md-offset-3">
            <h3>아이디 찾기</h3>
         </div>
      </div>
      <div class="col-sm-6 col-md-offset-3">
            <div class="form-group mb-6">
               <label for="user_name">성명</label> 
               <input type="text" name="user_name" class="form-control user_name" placeholder="이름을 입력해 주세요">
            </div>
            <div class="form-group">
               <label for="user_email">이메일 주소</label> 
               <input type="text" name="user_email01" class="form-control user_email01" placeholder="이메일 아이디를 입력해주세요">
               <div style="display:flex; flex-direction:row;">   
                  <input name="user_email02" value="dreamwiz.com" class="form-control user_email02" style="width:50%;"size="18" onchange="mail_list();"readonly />
                  <select   name="mail_list" class="form-control mail_list" style="width:50%;" aria-label="Default select example">
                     <option value="dreamwiz.com" selected="selected">dreamwiz.com</option>
                     <option value="google.com">google.com</option>
                     <option value="naver.com">naver.com</option>
                     <option value="daum.net">daum.net</option>
                     <option value="직접입력">직접 입력</option>
                  </select>   
               </div>
               <c:if test="${!empty user_id}">
                  
                  <label for="find_id" class="mt-3">아이디는 "${user_id}" 입니다!</label>
                  
               </c:if>   
            </div>
            
            <div class="form-group text-center">
               <button type="submit" id="join-submit" class="btn btn-primary">
                  아이디 찾기<i class="fa fa-check spaceLeft"></i>
               </button>
               <button type="reset" class="btn btn-warning">
                  취소<i class="fa fa-times spaceLeft"></i>
               </button>
            </div>
            
      </div>
      </form>
   </article>

</section>



</body>
</html>