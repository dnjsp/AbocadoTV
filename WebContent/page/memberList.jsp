<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 String state = request.getParameter("state");/////
 String mail = (String)session.getAttribute("mail");
	boolean flag = true;	
	if(mail==null) flag = false;
 %>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User List-Manage</title>
<link rel="stylesheet" href="../css/memberList.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
<link rel="stylesheet" href="../css/layout.css">
<link rel="stylesheet" href="../css/jquery-ui.min.css">
<link rel="stylesheet" href="../css/reset.css">

<style>
.tb{
text-align: center;
}
</style>
</head>
<%
	List<MemberVO> memList = (List<MemberVO>) request.getAttribute("memberList");
	Object obj = request.getAttribute("memberState");
%>
<body>
<div class="body__container">
	<header></header>
	<nav></nav>
	<div class="main">
		<h1>USER LIST-MANAGE</h1>
		<div class="table-list">
			<table class="tb" border="1">
	 				<thead class="th"> 
						<tr>
							<th>유저메일주소</th>
							<th>가입일</th>
							<th>닉네임</th>
							<th>정지기간</th>
							<th>관리</th>
						</tr>
 				</thead>
	 			<tbody class="tbody"> 
						<%
						for(MemberVO memberVO : memList){
							if(memberVO.getFreezedate()==null) memberVO.setFreezedate("없음");
						%>
					<tr>
						<td><%=memberVO.getMember_mail() %></td>
						<td><%=memberVO.getMember_date()%></td>
						<td><%=memberVO.getNickname() %></td>
						<td><%=memberVO.getFreezedate() %></td>
						<td><button type="button" id="<%=memberVO.getMember_mail()%>" class="ManageBtn">관리</button></td>
					</tr>
						<%
						}
						%>
				</tbody>
			</table>
		</div>
	</div>
	<footer></footer>
</div>
<input type="hidden" class="flag" value="<%=flag%>"> 
<script src="../js/jquery-3.6.0.min.js"></script>
<script src="../js/jquery-ui.min.js"></script>
<script src="../js/layout.js"></script>  
</body>
<script>
const manageBtns = document.querySelectorAll(".ManageBtn");
manageBtns.forEach(manageBtn =>{
	manageBtn.addEventListener("click",function(event){
		const idx = event.target.id;
		const locationTarget = "/management.do?cmd=manage&mem_mail=${idx}"+idx;
		location.href= locationTarget;
	});
});

</script>
</html>