<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="kr.or.ddit.vo.BlogCategoryVO"%>
<%@page import="kr.or.ddit.vo.BlogContentVO"%>
<%@page import="kr.or.ddit.vo.RealBoardVO"%>
<%@page import="kr.or.ddit.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	MemberVO memVO = (MemberVO)session.getAttribute("memberVO");
	List<BlogCategoryVO> list = (List<BlogCategoryVO>)session.getAttribute("categoryList");
	String mailId = "";
	try{
		mailId = (String)session.getAttribute("mailId");
		if(mailId== null) mailId="";
	}catch(Exception e){
	}
	BlogContentVO vo = (BlogContentVO)request.getAttribute("vo");
	int total=0;
	for(BlogCategoryVO blogVO:list){
		total += Integer.parseInt(blogVO.getCommentcount());
	}
	int pageCnt = (int)request.getAttribute("pageCnt");	
	int pagingList = (int)request.getAttribute("pagingList");
	List<BlogContentVO> blogList = (List<BlogContentVO>)request.getAttribute("list");
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
<link rel="stylesheet" href="../css/blogMenu.css"/>
<link rel="stylesheet" href="../css/reset.css">
<link rel="stylesheet" href="../css/blogAllCountList.css"/>
</head>
<body>
	<div class="body__container">
		<header></header>
		<main>
			<div class="main">
				<div class="blog-menu">
                    <a href="#" class="live-view">
                        <span>방송보기</span>
                        <span class="live">LIVE ●</span>
                    </a>
                    <a href="#">다시보기</a>
                    <%if(mail.equals(mailId)) {%>
                    <a href="/blogContent.do?cmd=write">글쓰기</a>
                    <%} %>
                    <button type="button" class="category">
                        <i class="fas fa-align-justify"></i>카테고리
                    </button>
                    <ul class="categories">
                        <li class="category-menu">
                            <a href="/blogContent.do?email=<%=mailId%>">전체보기
                                <span>(<%=total %>)</span>
                            </a>
                            <%if(!list.isEmpty()){ %>
                            <ul class="category-menu-ul">
                            	<%for(int i=0;i<list.size();i++){ 
                            		if(list.get(i).getBlog_category().equals("B")){%>
                                <li class="category-menu-ul-li">
                                    <a href="/blogContent.do?cmd=categoryPaging&blogCategory=<%=list.get(i).getCategory_index() %>"><%=list.get(i).getCategory_name() %>
                                        <span>(<%=list.get(i).getCommentcount() %>)</span>
                                    </a>
                                    <ul class="category-menu-ul-li-ul">
                                    	<%for(int j=i+1;j<list.size();j++){ 
							    		if(list.get(j).getBlog_category().equals("B")) break;%>
                                        <li>
                                            <a href="/blogContent.do?cmd=categoryPaging&blogCategory=<%=list.get(j).getCategory_index() %>" class="subcategory"><%=list.get(j).getCategory_name() %>
                                                <span>(<%=list.get(j).getCommentcount() %>)</span>
                                            </a>
                                        </li>
                                        <% }%>
                                    </ul>
                                </li>
                                <%}} %>
                            </ul>
                            <% }%>
                        </li>
                    </ul>
                </div>
                <div style="margin-left: 110px">
                
				<h1><%=memVO.getNickname() %>의 게시판</h1>
				<% if(!list.isEmpty()) {  %>
				<table id="list">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>조회수</th>
						<th>좋아요</th>
						<th>등록일</th>
					</tr>
				<%
					for(BlogContentVO item : blogList){
				%>
					<tr id="detail-list">
						<td><%=item.getBlog_index() %></td>
						<td class="title"><a href="blogContent.do?blogIdx=<%=item.getBlog_index() %>&cmd=see"><%=item.getBlog_title() %></a></td>
						<td><%=item.getBlog_count() %></td>
						<td><%=item.getBlog_like() %></td>
						<td><%=item.getBlog_date() %></td>
					</tr>
				<%} %>
				</table>
				<%}else{ %>
					<div class="nothing" style="padding: 0 47%; width: 130px;">글이 없습니다.</div>
				<%} %>
				<div id="paging">
					<%if(search==null) {for(int i = pagingList*10; i < pageCnt; i++){ 
						if(i>pagingList*10+9) break; %>
					<a id="page" href="/blogContent.do?cmd=paging&page=<%=i + 1 %>&email=yyj161091@gmail.com"><%=i+1 %></a>
					<%}}else{ for(int i = pagingList*10; i < pageCnt; i++){ 
						if(i>pagingList*10+9) break;%>
					<a id="page" href="/pagingList?cmd=search&page=<%=i + 1 %>&search=<%=search%>&condition=<%=condition%>"><%=i+1 %></a>
					<%}}%>
				</div>
                </div>
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
			location.href="/blogContent.do?cmd=paging&page=<%=pagingList *10 - 9 %>";
		});
		paging.prepend(pre);
	}
</script>
</body>
</html>
