<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header/header.jsp"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">


<script src="../resources/js/jquery.js"></script>
<script type="text/javascript">
        var lastID = 0;
        function submitFunction() {
            var chatName = $('#chatName').val();
            var chatContent = $('#chatContent').val();

            $.ajax({
                type:"post",
                url:"/chatSubmitServlet",
                data:{
                    chatName : encodeURIComponent(chatName),
                    chatContent : encodeURIComponent(chatContent)
                },
                success:function (result) {
                    if(result==1){
                        autoClosingAlert('#successMessage',2000);
                    }else if (result==0){
                        autoClosingAlert('#dangerMessage',2000);
                    }else{
                        autoClosingAlert('#warningMessage',2000);
                    }
                }
            })
            $('#chatContent').val('');

        }
        function autoClosingAlert(selector, delay){
            var alert = $(selector).alert();
            alert.show();
            setTimeout(function () {alert.hide()},delay)
        }

        function chatListfunction(type) {
            $.ajax({
                type:"post",
                url:"/chatListServlet",
                data:{
                   listType : type,
                },
                success:function (data) {
                    if(data =="") {
                        return;
                    }
                    var parsed = JSON.parse(data);
                    var result = parsed.result;
                    for(var i = 0; i <result.length ;i++){
                        addChat(result[i][0].value,result[i][1].value,result[i][2].value)
                    }
                    lastID = Number(parsed.last);
                }
            })
        }
        
        function addChat(chatName,chatContent,chatTime) {
            $('#chatList').append('<div class="row">' +
                '<div class="col-lg-12">'+
                '<div class="media">'+
                '<a class="pull-left" href="#">'+
                '<img class="media-object img-circle" src="/resources/img/zzz.jpg" alt="" width="50" height="50">'+
                '</a>'+
                '<div class="media-body">' +
                '<h4 class="media-heading">'+
                chatName +
                '<span class="small float-sm-right">' +
                chatTime +
                '</span>'+
                '</h4>'+
                '<p>' +
                chatContent+
                '</p>'+
                '</div>'+
                '</div>'+
                '</div>'+
                '</div>'+
                '<hr/>'


            )
            $('#chatList').scrollTop($('#chatList')[0].scrollHeight);
        }

        function getInfiniteChat() {
            setInterval(function () {
                chatListfunction(lastID)
            },1000);
        }
    </script>
<script>
	function chat(){
		 $url="/chat";
		 window.open($url,"실시간 채팅","width=800px,height=800px,scrollbars=yes");
 	}
	

</script>

<title>JSP AJAX 실시간 익명 채팅 사이트</title>
</head>

<body>


	<section class="page-section">


		<div class="container">
			<div class="col-xs-4 text-center mt-5 mb-5">
				<h1>
					<a href="javascript:void(0);" onclick="chat()"><i
						class="fa fa-comments" aria-hidden="true"></i>회원 간 실시간 채팅</a>
				</h1>
			</div>

			<div class="container bootstrap snippet mt-3">
				<div class="row">
					<div class="col-xl-12">
						<table border="0" class="table col-xl-7"
							style="float: none; margin: 0 auto">
							<tr>
								<th colspan="2">
									<div class="portlet-heading">
										<div class="portlet-title">
											<h4>
												<i class="fa fa-circle text-green"></i>비회원 실시간 채팅
											</h4>
										</div>
										<div class="clearfix"></div>
									</div>
								</th>
							</tr>
							<tr>
								<th>
									<div id="chat" class="collapse show">
										<div id="chatList" class="portlet-body chat-widget"
											style="overflow-y: auto; overflow-x: hidden; width: auto; height: 300px; margin-left: 10px; margin-right: 10px">

										</div>
									</div>
								</th>
							</tr>
							<tr>
								<th>
									<div class="portlet-footer">
										<div class="row">
											<div class="form-group col-xs-4">
												<input style="height: 40px;" type="text" id="chatName"
													class="form-control" placeholder="이름" maxlength="8">
											</div>
										</div>
										<div class="row" style="height: 90px;">
											<div class="form-group col-xs-12">
												<input style="height: 40px; width: 600px;" id="chatContent"
													class="form-control"
													onkeydown="if(event.keyCode==13) javascript:submitFunction();"
													placeholder="내용을 입력해 주세요" maxlength="100" />
											</div>

										</div>
									</div>
								</th>
							</tr>
							<tr>
								<th>
									<div class="alert alert-success" id="successMessage"
										style="display: none;">
										<strong>메시지 전송에 성공하였습니다!</strong>
									</div>
									<div class="alert alert-danger" id="dangerMessage"
										style="display: none;">
										<strong>이름과 내용을 모두 입력해주세요!</strong>
									</div>
									<div class="alert alert-warning" id="warningMessage"
										style="display: none;">
										<strong>데이터베이스 오류가 발생하였습니다.</strong>
									</div>
								</th>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</section>
	<script>
    $(document).ready(function () {
        chatListfunction('ten');
        getInfiniteChat();
    });
</script>




	<jsp:include page="../footer/footer.jsp" />