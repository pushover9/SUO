<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../header/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>실시간 채팅</title>
</head>
<script src="../resources/js/jquery.js"></script>
<link
	href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css"
	rel="stylesheet">
<body onload="openSocket();" onunload="closeSocket();">
	<!-- Server responses get written here -->
<section>

	<article class="container">
		<div class="page-header">
			<div class="col-md-6 col-md-offset-3">
				<h3>회원 채팅방</h3>
			</div>
		</div>
		<div id="messages" class="portlet-body chat-widget col-md-12 col-md-offset-3"
			style="overflow-y: auto; overflow-x: hidden; width:100%; height:500px;">
	
		</div>
	
	<div class="portlet-footer">
		<div class="row" style="height: 90px;">
			<div class="form-group col-lg-12 col-mt-5">
				<input type="text" class="form-contol " id="sender" value="${sessionScope.id}" style="display: none;"> 
		<input type="text" placeholder="메시지를 입력해주세요" id="messageinput" class="form-control chatting-input"
			onkeydown="if(event.keyCode==13) javascript:send();">
			</div>
	
		</div>
	</div>
	</article>
</section>
	<!-- websocket javascript -->
	<script type="text/javascript">
        var ws;
        var messages = document.getElementById("messages");
        
        function openSocket(){
            if(ws !== undefined && ws.readyState !== WebSocket.CLOSED ){
                writeResponse("WebSocket is already opened.");
                return;
            }
            //웹소켓 객체 만드는 코드
            ws = new WebSocket("ws://localhost:8099/echo");
            
            ws.onopen = function(event){
                if(event.data === undefined){
              		return;
                }
                writeResponse(event.data);
            };
            
            ws.onmessage = function(event){
                console.log('writeResponse');
                console.log(event.data)
                writeResponse(event.data);
            };
            
            ws.onclose = function(event){
                writeResponse("대화 종료");
            }
            
        }
        
        function send(){
           // var text=document.getElementById("messageinput").value+","+document.getElementById("sender").value;
            var text = document.getElementById("messageinput").value+","+document.getElementById("sender").value;
            ws.send(text);
            text = "";
            $('#messages')
            .stop()
            .animate({ scrollTop: $('#messages')[0].scrollHeight },0);
            $('#messageinput').val('');
            
        }
        
        function closeSocket(){
            ws.close();
        }
        
        function writeResponse(text){
            messages.innerHTML += "<br/>"+text;
            $('#messages')
            .stop()
            .animate({ scrollTop: $('#messages')[0].scrollHeight },0);
        }
        
        
  </script>
</body>
</html>