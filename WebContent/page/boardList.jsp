<%@page import="kr.or.ddit.vo.RealBoardVO"%>
<%@page import="kr.or.ddit.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	int pageCnt = (int)request.getAttribute("pageCnt");	
	int pagingList = (int)request.getAttribute("pagingList");
	List<RealBoardVO> list = (List<RealBoardVO>)request.getAttribute("list");
	String search = (String)request.getAttribute("search");
	String condition = (String)request.getAttribute("condition");
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
<link rel="stylesheet" href="../css/reset.css">
<link rel="stylesheet" href="../css/common.css">
<link rel="stylesheet" href="../css/boardList.css"/>
</head>
<body>
	<div class="body__container">
		<header></header>
		<nav></nav>
		<main>
			<div class="main">
				<h1>자유게시판</h1>
				<%if(flag){ %>
				<div id="content">
					<a id="write" href='/page/board.jsp'>글 쓰기</a>
				</div>
				<%} %>
				<% if(!list.isEmpty()) {  %>
				<div class="table-list">
					<table id="list">
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>조회수</th>
							<th>좋아요</th>
							<th>등록일</th>
						</tr>
					<%
						for(RealBoardVO item : list){
					%>
						<tr id="detail-list">
							<td><%=item.getBoard_index() %></td>
							<td class="title"><a href="pagingList?boardIdx=<%=item.getBoard_index() %>&cmd=see"><%=item.getBoard_title() %></a></td>
							<td><%=item.getNickname() %></td>
							<td><%=item.getBoard_count() %></td>
							<td><%=item.getBoard_like() %></td>
							<td><%=item.getBoard_date() %></td>
						</tr>
					<%} %>
					</table>
				</div>
				<%}else{ %>
					<div class="nothing" style="padding: 0 47%; width: 130px;">글이 없습니다.</div>
				<%} %>
				<div id="paging">
					<%if(search==null) {for(int i = pagingList*10; i < pageCnt; i++){ 
						if(i>pagingList*10+9) break; %>
					<a id="page" href="/pagingList?cmd=paging&page=<%=i + 1 %>"><%=i+1 %></a>
					<%}}else{ for(int i = pagingList*10; i < pageCnt; i++){ 
						if(i>pagingList*10+9) break;%>
					<a id="page" href="/pagingList?cmd=search&page=<%=i + 1 %>&search=<%=search%>&condition=<%=condition%>"><%=i+1 %></a>
					<%}}%>
				</div>
				
				<form class="searchForm" action="/pagingList">
					<select name="condition" style="margin-left: 48%;">
						<option value="board_title">제목</option>
						<option value="nickname">작성자</option>
					</select>
					<div class="searchBox">
			            <input class="searchInput" type="text" name="search" placeholder="Search">
			            <button type="button" class="searchButton" href="#">
			                search
			            </button>
			  		</div>
			  		<input type="hidden" name="cmd" value="search">
				</form>
			</div>
		</main>
  		<footer></footer>
  		<input type="hidden" class="flag" value="<%=flag%>">
	</div>
<script src="../js/jquery-3.6.0.min.js"></script>
<script src="../js/jquery-ui.min.js"></script>
<script src="../js/layout.js"></script>
<script src="../js/boardList.js"></script>
<script>
	const paging = document.querySelector('#paging');
	const page = document.querySelector('#page');
	if(<%=pagingList *10 + 10%> < <%=pageCnt%>){
		const next = document.createElement("img");
		next.src = '../img/next.png';
		next.classList.add("btn");
		next.addEventListener("click",function(){
			location.href="/pagingList?cmd=paging&page=<%=pagingList *10 + 11 %>";
		});
		paging.append(next);
	}
	if(<%=pagingList%> > 0 ){
		const pre = document.createElement("img");
		pre.src = '../img/prev.png';
		pre.classList.add("btn");
		pre.addEventListener("click",function(){
			location.href="/pagingList?cmd=paging&page=<%=pagingList *10 - 9 %>";
		});
		paging.prepend(pre);
	}
</script>
</body>
</html>
