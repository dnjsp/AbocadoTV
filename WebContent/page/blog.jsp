<%@page import="kr.or.ddit.vo.BlogCategoryVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MemberVO memVO = (MemberVO)session.getAttribute("memberVO");
	List<BlogCategoryVO> list = (List<BlogCategoryVO>)session.getAttribute("categoryList");
	boolean flag = true;
	String mail = "";
	try{
		mail = (String)session.getAttribute("mail");
		if(mail == null) flag = false;
		if(mail== null) mail="";
	}catch(Exception e){
	}
	String mailId = "";
	try{
		mailId = (String)session.getAttribute("mailId");
		if(mailId== null) mailId="";
	}catch(Exception e){
	}
	boolean followFlag = false;
	try{
		followFlag = (boolean)request.getAttribute("followflag");
	}catch(Exception e){
	}
	int total=0;
	for(BlogCategoryVO vo:list){
		total += Integer.parseInt(vo.getCommentcount());
	}
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BLOG</title>
    <link rel="stylesheet" href="../css/jquery-ui.min.css">
    <link rel="stylesheet" href="../css/reset.css">
	<link rel="stylesheet" href="../css/layout.css">
    <link rel="stylesheet" href="../css/common.css">
    <link rel="stylesheet" href="../css/blog.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
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
                <div class="right-blog">
                    <div class="profile">
                        <img src="../memImg/<%=memVO.getProfileimg() %>" alt="" class="profile-img">
                        <div class="introduce-box">
                            <div>
                                <div class="nickname"><%=memVO.getNickname() %></div>
                                <div class="today-box">
                                    <a href="#" class="followerList">
                                        <span>Follower </span>
                                        <span class="follow-total number"><%=memVO.getFollowcount() %></span>
                                    </a>
                                    <div class="today">
                                        <div>Today </div>
                                        <div class="number"><%=memVO.getTodaycount() %></div>
                                    </div>
                                    <div class="total">
                                        <div>Total </div>
                                        <div class="number"><%=memVO.getTotalcount() %></div>
                                    </div>
                                </div>
                            </div>
                            <div class="introduce">
                                <div>Introduce</div>
                                <textarea id="textarea" disabled spellcheck="false" class="input-info introduce-input"><%=memVO.getIntroduction() %></textarea>
                            </div>
                        </div>
                    </div>
                      <div class="follow">
                        <%if(flag && !mail.equals(mailId)){ %>
                        <a id="follow" style="cursor: pointer;">
                            <%if(followFlag) {%>
                        		<span id="follow-span">Following</span>
                                <i class="fas fa-user-check following followcheck"></i>
                            <%}else {%>
                        		<span id="follow-span">Follow</span>
                                <i class="fas fa-user unfollowing followcheck"></i>
                            <%}%>
                        </a>
                        <%} %>
                    </div>
                    <div class="streaming-view">
                        <ul class="containner">
                            <li class="container_list">
                                <a href="#"><img src="../img/main-img1.png" alt="" /></a>
                                <div class="list_title">
                                    <a href="#" class="title">먹방</a>
                                        <span class="title-lower">
                                            <a href="#" class="user">강주희</a>
                                                <span class="view">
                                                    <i class="far fa-play-circle"></i>
                                                    <span>500</span>
                                                </span>
                                        </span>
                                </div>
                            </li>
                            <li class="container_list">
                                <a href="#"><img src="../img/main-img6.png" alt="" /></a>
                                <div class="list_title">
                                    <a href="#" class="title">맛집탐방</a>
                                        <span class="title-lower">
                                            <a href="#" class="user">강주희</a>
                                                <span class="view">
                                                    <i class="far fa-play-circle"></i>
                                                    <span>1,000</span>
                                                </span>
                                        </span>
                                </div>
                            </li>
                            <li class="container_list">
                                <a href="#"><img src="../img/main-img12.png" alt="" /></a>
                                <div class="list_title">
                                    <a href="#" class="title">맛있는 요리</a>
                                        <span class="title-lower">
                                            <a href="#" class="user">강주희</a>
                                                <span class="view">
                                                    <i class="far fa-play-circle"></i>
                                                    <span>750</span>
                                                </span>
                                        </span>
                                </div>
                            </li>
                        </ul>
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
<script src="../js/blog.js"></script>
<script>
<% if(flag){ %>
	const followBtn = document.querySelector(".follow");
	const follow = document.querySelector(".followcheck");
	followBtn.addEventListener("click", function(){
	    const followerTotal = document.querySelector(".follow-total");
	    if(follow.classList.contains("unfollowing")){
	        follow.classList.add("following");
	        follow.classList.remove("unfollowing");
	        follow.classList.add("fa-user-check");
	        follow.classList.remove("fa-user");
	        followerTotal.innerText = followerTotal.innerText * 1 +1 ;
	        followControl('following');
	    }else{
	        follow.classList.remove("following");
	        follow.classList.add("unfollowing");
	        follow.classList.remove("fa-user-check");
	        follow.classList.add("fa-user");
	        followerTotal.innerText = followerTotal.innerText * 1 -1 ;
	        followControl('unfollowing');
	    }
	});
	
	function followControl(cmd){
	    $.ajax({
	        url : '/Blog',
	        data: {
	            cmd : cmd,
	            follow : '<%=mailId%>'
	        },
	        success: function(res){
	        	spanchange();
	        },
	        error : function(xhr){
	            console.log(xhr.status);
	        },
	        dataType: 'json'
	    });
	}
	
	function spanchange() {
		const followSpan = document.querySelector('#follow-span');
		if(followSpan.innerText == "Follow"){
			followSpan.innerText = "Following";
		}else{
			followSpan.innerText = "Follow";
		}
	}
<% }%>

</script>
</body>
</html>