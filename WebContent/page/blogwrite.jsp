<%@page import="kr.or.ddit.vo.BlogContentVO"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="kr.or.ddit.vo.BlogCategoryVO"%>
<%@page import="kr.or.ddit.vo.CategoryVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String mail = (String)session.getAttribute("mail");
	boolean flag = true;	
	if(mail==null) flag = false;
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
<link rel="stylesheet" href="../css/reset.css">
<link rel="stylesheet" href="../css/blogMenu.css">
<link rel="stylesheet" href="../css/layout.css"/>
<style>
.main {
	display: flex;
	flex-direction: row;
    width: 100%;
}
.main h1 {
    text-align: center;
    padding-bottom: 70px;
    font-size: 1.4rem;
}
.submit-btn{
	text-align : right;
}
#title {
	width: 570px;
}
#title:focus {
	outline: none;
}
.category {
    width: 130px;
    height: 30px;
    padding-left: 10px;
    border: 1px solid #999;
}
.category:focus {
    outline: none;
}
.title-box {
	display: flex;
	gap: 5px;
}
.textform {
	display: flex;
	flex-direction: column;
	gap: 15px;
}
#submit {
    width: 55px;
    height: 30px;
    background-color: #f5f5f5;
    border: 1px solid #808080;
    border-radius: 5px;
    cursor: pointer;
}
#submit:hover {
    opacity: .7;
}
</style>
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
                <div style="margin: 50px">
					<h1>글쓰기</h1>
					<form method="post" class="textform" action="/blogContentWrite.do" onsubmit="return check()">
						<div class="title-box">
							<select name="category-list" class="category">
								<option value="NONE">카테고리 선택</option>
								<%for(int i = 0; i < list.size(); i++){ %>
								<option value="<%=list.get(i).getCategory_index() %>"><%=list.get(i).getCategory_name() %></option>
								<%} %>
							</select>
							<label for="title"></label>
							<input type="text" id="title" name="title">
						</div>
						<textarea id="summernote" rows="40" cols="100" name="editordata"></textarea>
						<div class="submit-btn">
							<button id="submit">등록</button>	
						</div>
					</form>
                </div>
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
});

const title = document.querySelector("#title");

function check(){
	if(title.value==""){
		alert("제목을 적어줘야합니다.");
		return false;
	}
}

</script>
</body>
</html>