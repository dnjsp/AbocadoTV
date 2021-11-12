<%@page import="kr.or.ddit.vo.RealCommentVO"%>
<%@page import="kr.or.ddit.vo.BoardCommentVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.RealBoardVO"%>
<%@page import="kr.or.ddit.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%
	RealBoardVO vo = (RealBoardVO)request.getAttribute("board");
	List<RealCommentVO> list = (List<RealCommentVO>)request.getAttribute("list");
	String mail = (String)session.getAttribute("mail");
	String board_idx = request.getParameter("boardIdx");
	boolean likeFlag = (boolean)request.getAttribute("like");	
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
<link rel="stylesheet" href="../css/layout.css"/>
<link rel="stylesheet" href="../css/boardShow.css">

</head>
<body>
	<div class="body__container">
		<header></header>
		<nav></nav>
		<main>
			<div class="main">
				<div class="boardShow">
					<div id="title">자유게시판</div>
					<div class="main-title">
						<div class="board-title">
							<div class="board-title-box"><%=vo.getBoard_title() %></div>
						</div>
						<%if(flag && mail.equals(vo.getMember_mail())) {%>
						<div id="btnList">
							<a id="update">수정</a>
							<a id ="delete" href="/pagingList?cmd=delete&boardIdx=<%=vo.getBoard_index() %>">삭제</a>
						</div>
						<%} %>
					</div>
					<div class="board-head">
						<div class="username">
							<div><%=vo.getNickname() %></div>
							<div><%=vo.getBoard_date() %></div>
						</div>
						<div class="count">
							<div class="count-box">
								조회 수<span class=""><%=vo.getBoard_count() %></span>
							</div>
							<div class="count-box">
								좋아요 수<span class="likeTotal"><%=vo.getBoard_like() %></span>
							</div>
						</div>	
					</div>
					<div class="board-main">
						<div><%=vo.getBoard_content() %></div>
					</div>
					<%if(flag){ %>
					<div id="like">
						LIKE 
						<%if(likeFlag) {%>
							<img src="../img/fullheart.JPG" class="fullheart heart">
						<%}else{ %>
							<img src="../img/emptyheart.JPG" class="emptyheart heart">							
						<%} %>
					</div>
					<%} %>
					<div class="comment-list">
						<table id="comment-ul">
							<tbody class="comment-ul">
						<% for(int i = 0; i < list.size(); i ++){ %>
							<tr id="com<%=list.get(i).getBcomment_index() %>" class="comment-tr">
								<td class="comment-date">
									<%=list.get(i).getBcomment_date() %>
								</td>
								<td class="comment-name">
									<%=list.get(i).getNickname() %>
								</td>
								<td class="td-comment">
									<%=list.get(i).getBcomment_content() %>
								</td>
								<td>
									<%if(list.get(i).getMember_mail().equals(mail)){ %>
										<button class="delete-btn">삭제</button>
									<%} %>								
								</td>
							</tr>
						<% } %>
							</tbody>
						</table>
					</div>
					<%if(flag){ %>
					<div class="comment-textarea">
						<textarea class="comment-area" spellcheck="false" placeholder="댓글을 입력하세요." name="comment_content"></textarea>
						<button type="button" class="comment-button">등록</button>
					</div>
					<%} %>
					
				</div>
				<%if(flag&& mail.equals(vo.getMember_mail())) {%>
				<div class="boardModify hidden" style="margin: 50px;">
					<h1 class="main-h1">글 수정</h1>
					<form method="post" id="modify_form" action="/pagingList?cmd=update&boardIdx=<%=vo.getBoard_index() %>">
						<div class="title-box">
							<label for="updateTitle" style="width : 50px;">제목:</label>	
							<input id="updateTitle" type="text" name="title" value="<%=vo.getBoard_title() %>">
						</div>
						<textarea id="summernote" rows="40" cols="100" name="editordata"><%=vo.getBoard_content() %></textarea>
						<div class="submit-btn">
							<button id="submit">수정완료</button>	
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
<script src="../js/jquery-ui.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<script src="../js/layout.js"></script>
<script>
	$('#delete').on('click', function(){
	alert('삭제가 완료되었습니다.')
});

const deleteBTNs = document.querySelectorAll(".delete-btn");

deleteBTNs.forEach(deleteBTN =>{
	deleteBTN.addEventListener("click",deleteComment);
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
	$('.comment-button').on('click',function(){
		const param = {
			writer: '<%=mail %>',
			content: $('.comment-area').val(),
			boardIdx: <%=vo.getBoard_index() %>,
			cmd		: 'insertComment'
		};
		$.ajax({
			url : '/pagingList',
			type: 'post',
			data: param,
			success: function(res){
				if(res.result === '성공') {
					var code = "" ;
					const commentList = res.comment;
					const tr = document.createElement("tr");
					tr.classList.add("comment-tr");
					for (var i = commentList.length-1; i < commentList.length; i++) {
						code += "<td class='comment-date'>" +commentList[0].date+ "</td>";
						code += "<td>" +commentList[0].nickname+ "</td>";
						code += "<td class='td-comment'>" +commentList[0].content+ "</td>";	
						code += "<td><button class='delete-btn'>삭제</button></td>"
						tr.id = "com"+commentList[0].idx;
					}
					const commentul = document.querySelector(".comment-ul");
					tr.innerHTML = code;
					const deleteBtn = tr.querySelector(".delete-btn");
					deleteBtn.addEventListener("click",deleteComment);
					console.log(tr);
					commentul.append(tr);
				} else {
					alert('댓글 등록 실패')
				}
			},
			error  : function(xhr){
				console.log(xhr);
			},
			dataType: 'json'
		});
	});
});

function deleteComment(event){
	const parent = event.target.parentNode.parentNode;
	const idx = parent.id.substr(3);
	$.ajax({
		url : '/pagingList',
		type: 'post',
		data: {
			idx : idx,
			cmd : 'deleteComment'
		},
		success: function(res){
				parent.remove();
		},
		error  : function(xhr){
			console.log(xhr);
		},
		dataType: 'json'
	});
}

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
	
	$('#updateAll').on('click', function(){
		alert('수정이 완료되었습니다.');
	});
<%}%>

const heart = document.querySelector(".heart");
heart.addEventListener("click",function(){
	const likeTotal = document.querySelector(".likeTotal");
	if(heart.classList.contains("fullheart")){
		heart.classList.add("emptyheart");
		heart.classList.remove("fullheart");
		heart.src = "../img/emptyheart.JPG";
		likeTotal.innerText = likeTotal.innerText * 1 -1 ;
		likeControl('hate');
	}else{
		heart.classList.remove("emptyheart");
		heart.classList.add("fullheart");
		heart.src = "../img/fullheart.JPG";
		likeTotal.innerText = likeTotal.innerText * 1 +1 ;
		likeControl('like');
	}
});

function likeControl(cmd){
	$.ajax({
		url : '/pagingList',
		type: 'post',
		data: {
			cmd : cmd,
			board_idx : '<%=board_idx%>'
		},
		success: function(res){
			console.log("success");
		},
		error  : function(xhr){
			console.log(xhr);
		},
		dataType: 'json'
	});
}

</script>
</body>
</html>