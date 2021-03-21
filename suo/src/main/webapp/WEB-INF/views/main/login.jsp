<%@ page import="java.sql.*"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header/header.jsp"%>


         

<!DOCTYPE html>
<html lang="en">
<head>
   <title>Login V9</title>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1">

<!--===============================================================================================-->
   <link rel="stylesheet" type="text/css" href="../resources/assets/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
   <link rel="stylesheet" type="text/css" href="../resources/assets/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
   <link rel="stylesheet" type="text/css" href="../resources/assets/fonts/iconic/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
   <link rel="stylesheet" type="text/css" href="../resources/assets/vendor/animate/animate.css">
<!--===============================================================================================-->   
   <link rel="stylesheet" type="text/css" href="../resources/assets/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
   <link rel="stylesheet" type="text/css" href="../resources/assets/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
   <link rel="stylesheet" type="text/css" href="../resources/assets/vendor/select2/select2.min.css">
<!--===============================================================================================-->   
   <link rel="stylesheet" type="text/css" href="../resources/assets/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
   <link rel="stylesheet" type="text/css" href="../resources/assets/css/util.css">
   <link rel="stylesheet" type="text/css" href="../resources/assets/css/main.css">
<!--===============================================================================================-->


<script>
 function id_check(){
    if($.trim($("#user_id").val()) == ""){
       alert("아이디를 입력하세요!");
       $("#user_id").val("").focus();
       return false;
    }
    if($.trim($("#user_pw").val()) == ""){
       alert("비번을 입력하세요!");
       $("#user_pwd").val("").focus();
       return false;
    }
 }
 function find_id(){
    $url="/find_id";
    window.open($url,"아이디 찾기","width=500px,height=600px,scrollbars=yes");
 }
 function find_pw(){
    $url="/find_pw";
    window.open($url,"비밀번호 찾기","width=500px,height=600px,scrollbars=yes");
 }
</script>


</head>
<body>
   
   
   <div class="container-login100" style="background-image: url('../resources/assets/images/bg-02.png');">
      <div class="wrap-login100 p-l-55 p-r-55 p-t-80 p-b-30">
         <form class="login100-form validate-form" role="form" 
         method="post" action="/login_ok"   onsubmit="return id_check();">
         
            <span class="login100-form-title p-b-37">
               로그인
            </span>

            <div class="wrap-input100 validate-input m-b-20" data-validate="아이디를 입력하세요!">
               <input class="input100" type="text" name="user_id" id="user_id" placeholder="아이디를 입력하세요.">
               <span class="focus-input100"></span>
            </div>

            <div class="wrap-input100 validate-input m-b-25" data-validate = "비밀번호를 입력하세요!">
               <input class="input100" type="password" name="user_pw" id="user_pw" placeholder="비밀번호를 입력하세요.">
               <span class="focus-input100"></span>
            </div>

            <div class="container-login100-form-btn">
               <button class="login100-form-btn">
                  Sign In
               </button>
               <br/>
               <p class="mt-3"> 
               <a href="/main/join">아직 회원이 아니신가요?</a>
               </p>
            </div>

            <div class="text-center p-t-57 p-b-20">
               <span class="txt1">
                  Or login with
               </span>
            </div>

            <div class="flex-c p-b-112">
               <!-- 네이버 로그인 창으로 이동 -->
   <div id="naver_id_login" style="text-align:center"><a href="${url}">
   <img width="223" src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png"/></a></div>
            </div>
            <div class="text-center">
               
   <a href="javascript:void(0);" onclick="find_id()">아이디 찾기</a> 
   <a   href="javascript:void(0);" onclick="find_pw()">비밀번호 찾기</a> 


               
            </div>
         </form>

         
      </div>
   </div>
   
   

   <div id="dropDownSelect1"></div>
   
<!--===============================================================================================-->
   <script src="../resources/assets/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
   <script src="../resources/assets/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
   <script src="../resources/assets/vendor/bootstrap/js/popper.js"></script>
   <script src="../resources/assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
   <script src="../resources/assets/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
   <script src="../resources/assets/vendor/daterangepicker/moment.min.js"></script>
   <script src="../resources/assets/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
   <script src="../resources/assets/vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
   <script src="../resources/assets/js/main.js"></script>

</body>
</html>