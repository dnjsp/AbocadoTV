<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%
   String email = request.getParameter("mem_mail") + "";
   
   String mail = (String)session.getAttribute("mail");
	boolean flag = true;	
	if(mail==null) flag = false;
   %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Block</title>
<link rel="stylesheet" href="../css/management.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
<link rel="stylesheet" href="../css/layout.css">
<link rel="stylesheet" href="../css/jquery-ui.min.css">
<link rel="stylesheet" href="../css/reset.css">

</head>
<body>
<div class="body__container">
	<header></header>
	<nav></nav>
	<div class="main">
		
		<h1>USER BLOCK</h1>
		<div class="manage">
			<div class="mem-list">
				<div class="user-block">
					<form class="block" action="/management.do">
						유저 메일주소 : <%=email %>
						<div class="user-state">
							<label><input type="radio" name="state" value="7" checked="checked">7일 정지</label><br>
							<label><input type="radio" name="state" value="30">30일 정지</label><br>
							<label><input type="radio" name="state" value="99999">영구 정지</label>
						</div>	
						<input type="hidden" value="<%=email %>" name="member_mail">
					</form>
				</div>
				<div class="">
<!-- 					<button type="button" class="save-btn" onclick="location.href='/page/memberList.jsp';">저장</button> -->
					<button class="save-btn" name= "savestate" value="저장">저장</button>
				</div>
			</div>
		</div>
	</div>
	<footer></footer>
</div>
<input type="hidden" class="flag" value="<%=flag%>"> 
<script src="../js/jquery-3.6.0.min.js"></script>
<script src="../js/jquery-ui.min.js"></script>
<script src="../js/layout.js"></script>  
<script>
const form = document.querySelector(".block");	
const saveBtn = document.querySelector(".save-btn");	
	saveBtn.addEventListener("click",function(){	
	form.submit();								
});
</script>
</body>
</html>