<%@page import="kr.or.ddit.vo.NoticeVO"%>
<%@page import="kr.or.ddit.vo.RealBoardVO"%>
<%@page import="kr.or.ddit.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	int pageCnt = (int)request.getAttribute("pageCnt");	
	int pagingList = (int)request.getAttribute("pagingList");
	List<NoticeVO> list = (List<NoticeVO>)request.getAttribute("list");
	
	boolean flag2 = false;
	try{
		flag2 = (boolean)session.getAttribute("authority");
	}catch(Exception e){
	}
	String mail = (String)session.getAttribute("mail");
	
	boolean flag = true;	
	if(mail==null) flag = false;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
<link rel="stylesheet" href="../css/layout.css"/>
<link rel="stylesheet" href="../css/common.css">
<link rel="stylesheet" href="../css/reset.css">
<link rel="stylesheet" href="../css/boardList.css"/>
</head>
<body>
	<div class="body__container">
		<header></header>
		<nav></nav>
		<main>
			<div class="main">
				<h1>공지사항</h1>
				<%if(flag && flag2){ %>
				<div id="content">
					<a id="write" href='/page/notice.jsp'>글 쓰기</a>
				</div>
				<%} %>
				<% if(!list.isEmpty()) {  %>
				<div class="table-list">
					<table id="list">
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>조회수</th>
							<th>등록일</th>
						</tr>
					<%
						for(NoticeVO item : list){
					%>
						<tr id="detail-list">
							<td><%=item.getNotice_index() %></td>
							<td class="title"><a href="NoticeController?noticeIdx=<%=item.getNotice_index() %>&cmd=see"><%=item.getNotice_title() %></a></td>
							<td><%=item.getNotice_count() %></td>
							<td><%=item.getNotice_date() %></td>
						</tr>
					<%} %>
					</table>
				</div>
				<%}else{ %>
					<div class="nothing" style="padding: 0 47%; width: 130px;">글이 없습니다.</div>
				<%} %>
				<div id="paging">
					<% for(int i = pagingList*10; i < pageCnt; i++){ 
						if(i>pagingList*10+9) break;%>
					<a id="page" href="/NoticeController?cmd=paging&page=<%=i + 1 %>"><%=i+1 %></a>
					<%}%>
				</div>
			</div>
		</main>
  		<footer></footer>
  		<input type="hidden" class="flag" value="<%=flag%>">
  		<input type="hidden" class="flag2" value="<%=flag2%>">
	</div>
<script src="../js/jquery-3.6.0.min.js"></script>
<script src="../js/jquery-ui.min.js"></script>
<script src="../js/layout.js"></script>
<script>
	const paging = document.querySelector('#paging');
	const page = document.querySelector('#page');
	if(<%=pagingList *10 + 10%> < <%=pageCnt%>){
		const next = document.createElement("img");
		next.src = '../img/next.png';
		next.classList.add("btn");
		next.addEventListener("click",function(){
			location.href="/NoticeController?cmd=paging&page=<%=pagingList *10 + 11 %>";
		});
		paging.append(next);
	}
	if(<%=pagingList%> > 0 ){
		const pre = document.createElement("img");
		pre.src = '../img/prev.png';
		pre.classList.add("btn");
		pre.addEventListener("click",function(){
			location.href="/NoticeController?cmd=paging&page=<%=pagingList *10 - 9 %>";
		});
		paging.prepend(pre);
	}
</script>
</body>
</html>
