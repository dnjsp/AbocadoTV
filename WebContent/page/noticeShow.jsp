<%@page import="kr.or.ddit.vo.NoticeVO"%>
<%@page import="kr.or.ddit.vo.RealCommentVO"%>
<%@page import="kr.or.ddit.vo.BoardCommentVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.RealBoardVO"%>
<%@page import="kr.or.ddit.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%
	NoticeVO vo = (NoticeVO)request.getAttribute("vo");
	String mail = (String)session.getAttribute("mail");
	boolean flag = true;
	if(mail == null) flag = false;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- include summernote css/js -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
<link rel="stylesheet" href="../css/reset.css">
<link rel="stylesheet" href="../css/layout.css"/>
<link rel="stylesheet" href="../css/blogShow.css">
</head>
<body>
	<div class="body__container">
		<header></header>
		<nav></nav>
		<main>
			<div class="main">
				<div class="boardShow" style="margin-left: 210px;">
					<div class="board-title">
						<div><%=vo.getNotice_title() %></div>
					</div>
					<%if(flag && mail.equals(vo.getMember_mail())) {%>
						<div id="btnList">
							<a id="update">수정</a>
							<a id ="delete" href="/NoticeController?cmd=delete&noticeIdx=<%=vo.getNotice_index() %>">삭제</a>
						</div>
						<%} %>
						<div class="board-head">
							<div><%=vo.getNotice_date() %></div>
							<div>조회수:<%=vo.getNotice_count() %></div>	
						</div>
						<div class="board-main">
							<div><%=vo.getNotice_content() %></div>
						</div>
					</div>
					<%if(flag&& mail.equals(vo.getMember_mail())) {%>
						<div class="boardModify hidden">
							<form method="post" id="modify_form" action="/NoticeController?cmd=update&noticeIdx=<%=vo.getNotice_index() %>">
								<label for="updateTitle">제목:</label>	
								<input id="updateTitle" type="text" name="title" value="<%=vo.getNotice_title() %>"><br>
								<textarea id="summernote" rows="40" cols="100" name="editordata"><%=vo.getNotice_content() %></textarea>
								<div class="submit-btn">
									<button id="submit">수정 완료</button>	
								</div>
							</form>
						</div>
						<%} %>
					</div>
				</main>
				<footer></footer>
				<input type="hidden" class="flag" value="<%=flag%>">
			</div>
			
			<script src="../js/jquery-3.6.0.min.js"></script>
			<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
			<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
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
const updateTitle = document.querySelector('#updateTitle');
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