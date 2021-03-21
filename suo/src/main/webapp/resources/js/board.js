/**
 * 
 */
 
 function check(){
 
  if($.trim($('#title').val())==''){
	Swal.fire(
  		'경고! 제목 미입력! ',
  		'제목을 입력해주세요!',
  		'warning'
	)
	$('#title').val('').focus();
	return false;  
  }
  
   if($.trim($('#writer').val())==''){
	Swal.fire(
  		'경고! 작성자 미입력! ',
  		'작성자를 입력해주세요!',
  		'warning'
	)
	$('#writer').val('').focus();
	return false;  
  }
  if($.trim($('#content').val())==''){
	Swal.fire(
  		'경고! 내용 미입력 ',
  		'내용을 입력해주세요!',
  		'warning'
	)
	$('#content').val('').focus();
	return false;  
  }
 }