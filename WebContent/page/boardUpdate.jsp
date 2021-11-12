<%@page import="kr.or.ddit.service.BoardServiceImp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String mail = (String)session.getAttribute("mail");
	boolean flag = true;	
	if(mail==null) flag = false;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- include libraries(jQuery, bootstrap) -->
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
<link rel="stylesheet" href="../css/layout.css"/>
<style>
.main {
	padding : 100px 0 50px 300px;
    width: 70%;
    min-height: 525px;
    min-width: 1100px;
}
.submit-btn{
	text-align : right;
}

#title {
	width: 200px;
}
</style>
</head>
<body>
	<div class="body__container">
		<header></header>
		<nav></nav>
		<main>
			<div class="main">
				 <form method="post" class="textform" action="<%=request.getContextPath() %>/BoardWriteController">
					<label for="title">제목:  </label>
					<input type="text" id="title" name="title"><br>
					<textarea id="summernote" rows="40" cols="100" name="editordata"></textarea>
					<div class="submit-btn">
						<button id="submit" type="submit">등록</button>	
					</div>
				 </form>
		 	</div>
		 </main>
		 <footer></footer>
	 </div>




<input type="hidden" class="flag" value="<%=flag%>">
<script src="../js/layout.js"></script>
<script>
$(document).ready(function(){
	$('#summernote').summernote({
		height: 300,
		minHeight: null,
		maxHeight: null,
		focus: true,
		placeholder: '내용을 입력해주세요',
		tabsize: 2,
		lang: 'ko-KR'
	});
	
	$('#submit').on('click',function(){
		alert('게시물 등록이 완료되었습니다.');
	});
});

</script>
</body>
</html>