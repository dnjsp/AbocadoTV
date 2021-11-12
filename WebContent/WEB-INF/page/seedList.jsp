<%@page import="kr.or.ddit.vo.SeedVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<SeedVO> list = (List<SeedVO>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="../css/reset.css">
	<link rel="stylesheet" href="../css/seedList.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
</head>
<body>
<div class="body__container">

<div class="title">
	<img alt="" src="../img/abocado1.jpg">
	씨앗 충전 목록
	<img alt="" src="../img/abocado2.jpg">
	</div>
		<div class="seedlist">
			<ul class="seed-list">
	 		<% for(int i = 0; i < list.size(); i++){ %>
				<li class="list">
					<div>씨앗  <%=list.get(i).getSeed() %> 개</div>
					<div><%=Integer.parseInt(list.get(i).getSeed()) * 100 %> 원</div>
					<div><%=list.get(i).getSeed_date() %></div>
				</li>
			<%} %>
			</ul>
		</div>
</div>
</body>
</html>