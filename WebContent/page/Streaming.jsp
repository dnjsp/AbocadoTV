<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String mail = (String)session.getAttribute("mail");
	String nickname = (String)request.getAttribute("nickname");
	MemberVO memVO = (MemberVO)request.getAttribute("memberVO");
	boolean master = (boolean)request.getAttribute("master");
	int seed = 0;
	if(master){
		
	}else{
		seed = (int)request.getAttribute("seed");
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
<link rel="stylesheet" href="../css/reset.css">
<link rel="stylesheet" href="../css/common.css">
<link rel="stylesheet" href="../css/streaming.css">
<link rel="stylesheet" href="../css/csshake-crazy.css">
</head>
	<body>
		<div class="main">
			<img src="../img/seed.png" class="donateImg hidden">
			<iframe src="http://172.20.10.6:5000/viewer.html" style="width:680px; height:500px;"> </iframe>
	   		<div id="chatArea">
	             <textarea id="messageTextArea" readonly="readonly" ></textarea><br><br>
	             <div style="display: flex; gap: 10px;">
		             <input type="text" id="messageText" size="40" onkeydown="enterkey()"> 
		             <input type="button" value="Send" onclick="sendMessage()">
	             </div>
	             <%if(!master) {%>
	             <br>
	             <span>내 씨앗</span><span class="seedTotal"><%=seed %></span>
	             <input type="number" name="donate" class="donate" placehole="후원할 씨앗">
	             <button type="button" class="seed">씨앗 선물하기</button>
	             <%}else{ %>
	             <button type="button" class="close">방송 종료하기</button>
	             <%} %>
	        </div>
		</div>
	         <div class="profile">
                  <img src="../memImg/<%=memVO.getProfileimg() %>" alt="" class="profile-img">
                  <div class="nickname"><%=memVO.getNickname() %></div>
                  <div class="today-box">
                      <div class="followerList">
                          <span>Follower </span>
                          <span class="follow-total number"><%=memVO.getFollowcount() %></span>
                      </div>
                  </div>
             </div>
	<input type="hidden" class="nicknameHidden" value="<%=nickname%>">
	<input type="hidden" class="mailHidden" value="<%=memVO.getMember_mail()%>">
	</body>
<script src="../js/jquery-3.6.0.min.js"></script>
<script src="../js/webSocket.js"></script>
<script src="../js/Streaming.js"></script>
<script>
<% if(!master) {%>
const seedBtn = document.querySelector(".seed");
const seedTotal = document.querySelector(".seedTotal");
const donate = document.querySelector(".donate");
const mailHidden = document.querySelector(".mailHidden");
seedBtn.addEventListener("click",function(){
	if(seedTotal.innerText*1 >= donate.value*1){
	    $.ajax({
			type:'POST',
			url:'/Streaming',
			data: {
				cmd : 'donate',
				donate : donate.value,
				streamer : mailHidden.value
			},	
			dataType: 'json',
			success: function(res){
			    webSocket.send(createMessage('donate',nickname,donate.value));
			    seedTotal.innerText = seedTotal.innerText*1 - donate.value*1;
			},
			error: function(request,status,error){
	            alert('code:'+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	        }
		});
	}else{
		alert("보유한 씨앗 내에서 후원 가능합니다.");
	}
});
<%}else{%>
const closeBtn = document.querySelector(".close");
closeBtn.addEventListener("click",function(){
	$.ajax({
		type:'POST',
		url:'/Streaming',
		data: {
			cmd : 'close'
		},	
		dataType: 'json',
		success: function(res){
			webSocket.send(createMessage('end',nickname,'end'));
			window.close();				
		},
		error: function(request,status,error){
            alert('code:'+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
	});
});

<%}%>
</script>
</html>