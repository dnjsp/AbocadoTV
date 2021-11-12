<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%
	boolean authorith = false;
	try{
		authorith = (boolean)session.getAttribute("authority");
	}catch(Exception e){
	}
%>

<div class="header__container">
    <a href="../page/index.html">
        <img class ="logo">
    </a>
    <div class="right-menu">
    	<%if(authorith){ %>
		<a href="/memberList.do">
			<span class="user-management">유저관리</span>
		</a>
		<%} %>    	
        <a href="/Blog">
            <img class="broadcast hidden" src="../img/broadcast.jpg" alt="broadcast">
            <span class="arrow-box arraw-broadcast hidden">내 방송국</span>
        </a>
        <a href="#" class="startStream">
            <img class="onair hidden" src="../img/onair.jpg" alt="onair">
            <span class="arrow-box arraw-onair hidden">방송하기</span>
        </a>
        <a href="#">
            <img class="login" src="../img/login.jpg" alt="login">
        </a>
    </div>
</div>
<script>
const startStreaming = document.querySelector(".startStream");
startStreaming.addEventListener("click",function(){
	window.open("/page/startStreaming.jsp");
});
</script>