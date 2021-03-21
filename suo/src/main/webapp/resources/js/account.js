/**
 * 
 */
 
 function id_check(){
    var user_id=$("#user_id").val();
    $('.idcheck').hide();
    $user_id=$.trim($('#user_id').val());
    if($.trim($("#user_id").val()) == ""){
       Swal.fire(
        '경고! 아이디 미입력 ',
        '',
        'warning'
        )
       $("#user_id").val("").focus();
       return false;
       
       
    }else if($user_id.length < 5){
       $mark='<font color="red" size="3">아이디는 5자 이상이어야 합니다.</font>';
       $('.idcheck').text('');
       $('.idcheck').show();
       $('.idcheck').append($mark);
       $('#user_id').val('').focus();
       return false;
    }else if($user_id.length > 15){
       $mark='<font color="red" size="3">아이디는 15자 이하이어야 합니다.</font>';
       $('.idcheck').text('');
       $('.idcheck').show();
       $('.idcheck').append($mark);
       $('#user_id').val('').focus();
       return false;
    }else{
       $.ajax({
         url:"/overlab",
         data: {user_id:user_id},
         datatype:"int",
         type:"POST",
         success : function(result){
            if(result == 1){
            Swal.fire(
                 '사용할 수 있는 아이디 입니다!',
                 '',
                 'success'
                 )
               $.trim($('#hidden_id').val('1'));
            }else{
               Swal.fire(
                 '사용할 수 없는 아이디 입니다!',
                 '',
                 'warning'
                 )
               $.trim($('#hidden_id').val('2'));
            }
         },
         error:function(){
            alert('DATA ERROR');
         }
         });
       return false;
    }    
}//id_check()

function join_check(){
    if($.trim($('#user_id').val()) == ''){
      Swal.fire(
        '경고! 아이디 미입력 ',
        '',
        'warning'
        )
      $('#user_id').val('').focus();
      return false;
   }
   if($.trim($('#hidden_id').val())!='1'){
      Swal.fire(
        '아이디 중복체크를 확인해주세요! ',
        '',
        'warning'
        )
      $('#user_id').val('').focus();
      return false;
   }

   $user_pw=$.trim($('.user_pw').val());
   $user_pw2=$.trim($('.user_pw2').val());
   if($user_pw == ''){
      Swal.fire(
        '경고! 비밀번호 미입력 ',
        '',
        'warning'
        )
      $('.user_pw').val('').focus();
      return false;
   }
   
   if($user_pw2 == ''){
      Swal.fire(
        '경고! 비밀번호 확인 미입력 ',
        '',
        'warning'
        )
      $('.user_pw2').val('').focus();
      return false;
   }

   if($user_pw != $user_pw2){
      Swal.fire(
        '경고! 비밀번호가 다릅니다! ',
        '',
        'warning'
        )
      $('.user_pw').val('');//비번 입력 박스 초기화
        $('.user_pw2').val('');
        $('.user_pw').focus();
        return false;      
   }
   
   if($.trim($('.user_name').val()) == ''){
      Swal.fire(
        '경고! 회원 이름 미입력 ',
        '',
        'warning'
        )
      $('.user_name').val('').focus();
      return false;
   }
   
   if($.trim($('.user_email01').val()) == ''){
      Swal.fire(
        '경고! 이메일 아이디 미입력 ',
        '',
        'warning'
        )
      $('.user_email01').val('').focus();
      return false;
   }
   
   if($.trim($('.user_email02').val()) == ''){
      Swal.fire(
        '경고! 이메일 주소 미입력 ',
        '',
        'warning'
        )
      
      return false;
   }
   
   if($.trim($('.user_phone02').val()) == ''){
      Swal.fire(
        '경고! 전화번호 미입력 ',
        '',
        'warning'
        )
      $('.user_phone02').val('').focus();
      return false;
   }

   if($.trim($('.user_phone03').val()) == ''){
      Swal.fire(
        '경고! 전화번호 미입력 ',
        '',
        'warning'
        )
      $('.user_phone03').val('').focus();
      return false;
   }
}//join check()

function edit_check(){

   
   $user_pw=$.trim($('.user_pw').val());
   $user_pw2=$.trim($('.user_pw2').val());
   if($user_pw == ''){
      Swal.fire(
        '경고! 비밀번호 미입력 ',
        '',
        'warning'
        )
      $('.user_pw').val('').focus();
      return false;
   }
   
   if($user_pw2 == ''){
      Swal.fire(
        '경고! 비밀번호 확인 미입력 ',
        '',
        'warning'
        )
      $('.user_pw2').val('').focus();
      return false;
   }

   if($user_pw != $user_pw2){
      Swal.fire(
        '경고! 비밀번호가 다릅니다! ',
        '',
        'warning'
        )
      $('.user_pw').val('');//비번 입력 박스 초기화
        $('.user_pw2').val('');
        $('.user_pw').focus();
        return false;      
   }
   
    if($.trim($('.user_email01').val()) == ''){
      Swal.fire(
        '경고! 이메일 아이디 미입력 ',
        '',
        'warning'
        )
      $('.user_email01').val('').focus();
      return false;
   }
   
   if($.trim($('.user_email02').val()) == ''){
      Swal.fire(
        '경고! 이메일 주소 미입력 ',
        '',
        'warning'
        )
      
      return false;
   }
   
   
    if($.trim($('.user_phone02').val()) == ''){
      Swal.fire(
        '경고! 전화번호 미입력 ',
        '',
        'warning'
        )
      $('.user_phone02').val('').focus();
      return false;
   }

   if($.trim($('.user_phone03').val()) == ''){
      Swal.fire(
        '경고! 전화번호 미입력 ',
        '',
        'warning'
        )
      $('.user_phone03').val('').focus();
      return false;
   }
}//edit check()







