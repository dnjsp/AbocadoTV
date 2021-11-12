<%@page import="kr.or.ddit.vo.BlogCategoryVO"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="kr.or.ddit.vo.BlogContentVO"%>
<%@page import="kr.or.ddit.vo.NoticeVO"%>
<%@page import="kr.or.ddit.vo.RealCommentVO"%>
<%@page import="kr.or.ddit.vo.BoardCommentVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.RealBoardVO"%>
<%@page import="kr.or.ddit.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
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
	String mail = (String)session.getAttribute("mail");
	boolean flag = true;
	if(mail == null) flag = false;
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<script src="../resources/js/summernote/lang/summernote-ko-KR.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
<link rel="stylesheet" href="../css/reset.css">
<link rel="stylesheet" href="../css/layout.css">
<link rel="stylesheet" href="../css/blogShow.css">
<link rel="stylesheet" href="../css/blogMenu.css">
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
                <div>
					<div class="boardShow">
						<div id="board-title">
							<div class="board-title">
								<div><%=vo.getBlog_title() %></div>
							</div>
							<%if(flag && mail.equals(vo.getMember_mail())) {%>
							<div id="btnList">
								<a id="update">수정</a>
								<a id ="delete" href="/blogContent.do?cmd=delete&blogIdx=<%=vo.getBlog_index() %>">삭제</a>
							</div>
							<%} %>
						</div>
						<div class="board-head">
							<div><%=vo.getBlog_date() %></div>
							<div>조회수:<%=vo.getBlog_count() %></div>	
						</div>
						<div class="board-main">
							<div><%=vo.getBlog_content() %></div>
						</div>
					</div>
					<%if(flag&& mail.equals(vo.getMember_mail())) {%>
					<div class="boardModify hidden">
						<form method="post" id="modify_form" action="/blogContent.do?cmd=update&blogIdx=<%=vo.getBlog_index() %>">
							<label for="updateTitle">제목:</label>	
							<input id="updateTitle" type="text" name="title" value="<%=vo.getBlog_title() %>"><br>
							<textarea id="summernote" rows="40" cols="100" name="editordata"><%=vo.getBlog_content() %></textarea>
							<div class="submit-btn">
								<button id="submit">수정 완료</button>	
							</div>
						 </form>
					</div>
					<%} %>
                </div>
			</div>
		</main>
		<footer></footer>
		<input type="hidden" class="flag" value="<%=flag%>">
	</div>
	
<script src="../js/jquery-3.6.0.min.js"></script>
<script src="../js/jquery-ui.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<script src="../js/layout.js"></script>

<script>
$('#delete').on('click', function(){
	alert('삭제가 완료되었습니다.')
});

$(document).ready(function(){
	$('#summernote').summernote({
		height: 300,
		minHeight: null,
		maxHeight: null,
		focus: true,
		tabsize: 2,
		lang: 'ko-KR'
	});	

});

const show = document.querySelector('.boardShow');
const modify = document.querySelector('.boardModify');
const updateTitle = document.querySelector('#updateTitle');e
const summernote = document.querySelector('#summernote');
<%if(flag&& mail.equals(vo.getMember_mail())){ %>
	const update = document.querySelector('#update');
	update.addEventListener('click', function(){
		show.classList.add("hidden");
		modify.classList.remove("hidden");
	});
	
	$('#submit').on('click', function(){
		alert('수정이 완료되었습니다.');
	});
<%}%>

</script>
</body>
</html>