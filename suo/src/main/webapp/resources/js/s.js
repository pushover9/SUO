/**
 * 
 */
 //아이디 중복검색
 function id_check(){
	var mem_id=$("#mem_id").val();
    $('.idcheck').hide();
    $mem_id=$.trim($('#mem_id').val());
    if($.trim($("#mem_id").val()) == ""){
       alert("아이디를 입력하세요");
       $("#mem_id").val("").focus();
       return false;
    }else if($mem_id.length < 5){
       $mark='<font color="red" size="3">아이디는 5자 이상이어야 합니다.</font>';
       $('.idcheck').text('');
       $('.idcheck').show();
       $('.idcheck').append($mark);
       $('#mem_id').val('').focus();
       return false;
    }else if($mem_id.length > 15){
       $mark='<font color="red" size="3">아이디는 15자 이하이어야 합니다.</font>';
       $('.idcheck').text('');
       $('.idcheck').show();
       $('.idcheck').append($mark);
       $('#mem_id').val('').focus();
       return false;
	 }else{
		 $.ajax({
			url:"/member_idcheck",
			data: {mem_id:mem_id},
			datatype:"int",
			type:"POST",
			success : function(re){
				if(re == 1){
					alert("사용 불가능한 아이디 입니다.");
				}else{
					alert("사용 할수 있는 아디디 입니다.");
				}
			},
			error:function(){
				alert('DATA ERROR');
			}
			});
		 return false;
	 }	 
	 }
	 
	//member_edit.jsp 유효성 검증
	function edit_check(){
	$mem_pwd=$.trim($("#mem_pwd").val());
	$mem_pwd2=$.trim($("#mem_pwd2").val());
	if($.trim($("#mem_name").val())==""){
		alert("회원이름을 입력하세요!");
		$("#mem_name").val("").focus();
		return false;
	}
	if($mem_pwd == ""){
		alert("비번을 입력하세요!");
		$("#mem_pwd").val("").focus();
		return false;
	}
	if($mem_pwd2 == ""){
		alert("비번확인을 입력하세요!");
		$("#mem_pwd2").val("").focus();
		return false;
	}
	if($mem_pwd != $mem_pwd2){
		alert("비번이 다릅니다!");
		$("#mem_pwd").val("");//비번 입력박스를 초기화
		$("#mem_pwd2").val("");
		$("#mem_pwd").focus();
		return false;
	}
	if($.trim($("#mem_phone02").val())==""){
		alert("폰번호를 입력하세요!");
		$("#mem_phone02").val("").focus();
		return false;
	}
	if($.trim($("#mem_phone03").val())==""){
		alert("폰번호를 입력하세요!");
		$("#mem_phone03").val("").focus();
		return false;
	}
	if($.trim($("#mem_email_id").val())==""){
		alert("전자우편을 입력하세요!");
		$("#mem_email_id").val("").focus();
		return false;
	}
	if($.trim($("#mem_email").val())==""){
		alert("도메인을 입력하세요!");		
		return false;
	}
}//edit_check()
	 
	 