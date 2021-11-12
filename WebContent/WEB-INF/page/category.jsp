<%@page import="java.util.HashSet"%>
<%@page import="kr.or.ddit.vo.BlogCategoryVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<BlogCategoryVO> list = (List<BlogCategoryVO>)request.getAttribute("list");
    HashSet<String> set = new HashSet<String>();
    for(BlogCategoryVO vo : list){
    	set.add(vo.getCategory_index());
    }
	String mail = (String)session.getAttribute("mail");
	boolean flag = true;	
	if(mail==null) flag = false;
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>CATEGORY</title>
	<link rel="stylesheet" href="../css/jquery-ui.min.css">
	<link rel="stylesheet" href="../css/category.css"><link rel="stylesheet" href="../css/layout.css">
	<link rel="stylesheet" href="../css/reset.css">
	<link rel="stylesheet" href="../css/common.css">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
</head>
<body>
	<div class="body__container">
		<header></header>
		<nav></nav>
		<main>
			<div class="main">
				<h1>EDIT CATEGORY</h1>
				<div class="category-wrap">
					<div>
						<div>
							<ul class="sort categories">
								<% if(list!=null){
									for(int i=0;i<list.size();i++){
										if(list.get(i).getBlog_category().equals("B")){%>
								<li>
									<div class="category main-category <%=list.get(i).getCategory_index()%>">
										<img class="list-img" src="../img/list.jpg">
										<img class="drag-img" src="../img/drag.jpg">
										<span><%=list.get(i).getCategory_name() %></span>
										<div class="buttons hidden">
											<button class="addBtn" type="button">추가</button>
											<button class="updateBtn" type="button">수정</button>
											<button class="removeBtn" type="button">삭제</button>
										</div>
									</div>
									<ul class="sort">
										<%for(int j=i+1;j<list.size();j++){ 
											if(list.get(j).getBlog_category().equals("B")) break;%>
											<li class="category sub-category <%=list.get(j).getCategory_index()%>">
												<img class="drag-img" src="../img/drag.jpg">
												<span><%=list.get(j).getCategory_name() %></span>
												<div class="buttons hidden">
													<button class="updateBtn" type="button">수정</button>
													<button class="removeBtn" type="button">삭제</button>
												</div>
											</li>
										<%}%>
									</ul>
								</li>
								<% 		}
									}
								}%>
							</ul>
						</div>
						<div class="add-category">
							<img src="../img/add.jpg">
							<span>카테고리 추가</span>
						</div>
					</div>
					<div class="edit-btn">
						<button class="change-btn" type="button">수정완료</button>
					</div>
				</div>
			</div>
      </main>
      <footer></footer>	
	</div>
<input type="hidden" class="flag" value="<%=flag%>"> 
<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
<script src="../js/jquery-ui.min.js"></script>
<script src="../js/layout.js"></script>
<script src="../js/category.js"></script>
<script>
<% if(set!=null){ %>
	var arr = [];
	<%for(String str :set){%>
		arr.push(<%=str%>);
<% }
}%>
</script>
</body>
</html>