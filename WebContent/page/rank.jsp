<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.vo.TagVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	boolean searchflag = (boolean)request.getAttribute("search");
	String value = (String)request.getAttribute("value");
	List<MemberVO> memList = (List<MemberVO>)request.getAttribute("memList");
	Map<String,List<TagVO>> tagMap = (Map<String,List<TagVO>>)request.getAttribute("tagMap");
	int pageCnt = (int)request.getAttribute("pageCnt");	
	int pagingList = (int)request.getAttribute("pagingList");
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
    <title>RANK</title>
    <link rel="stylesheet" href="../css/jquery-ui.min.css">
    <link rel="stylesheet" href="../css/reset.css">
    <link rel="stylesheet" href="../css/layout.css">
    <link rel="stylesheet" href="../css/common.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
    <link rel="stylesheet" href="../css/rank.css">
</head>
<body>
    <div class="body__container">
        <header></header>
        <nav></nav>
        <main>
            <div class="main">
                <h1>RANK</h1>
                <div>
                    <form class="searchForm" action="/UserList">
                        <div class="searchBox">
                            <input class="searchInput" type="text" name="value" placeholder="Search">
                            <button class="searchButton">search</button>
                        </div>
                        <div class="rank-box">
                            <div class="rank">
                                <div>랭킹 선정 방법</div>
                                <div class="rank-percent">
                                    <div>팔로워 수 30%</div>
                                    <div>좋아요 수 30%</div>
                                    <div>댓글 수 30%</div>
                                    <div>조회 수 10%</div>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="cmd" value="search">
                    </form>
                   	<% for(int i = 0; i < memList.size();i++) {%>
                    <div class="rank-view">
                        <div class="rank-number"><%=i+1 %></div>
                        <a href="/Blog?cmd=blog&mailId=<%=memList.get(i).getMember_mail()%>">
                            <img src="../memImg/<%=memList.get(i).getProfileimg() %>" alt="">
                        </a>
                        <div class="rank-right">
                            <div class="rank-name">
                                <a href="/Blog?cmd=blog&mailId=<%=memList.get(i).getMember_mail()%>"><%=memList.get(i).getNickname() %></a>
                                <a href="#" class="follower">
                                    <span>Follower </span>
                                    <span class="number"><%=memList.get(i).getFollowcount() %></span>
                                </a>
                                <div class="today">
                                    <div>Like </div>
                                    <div class="number"><%=memList.get(i).getLikeCount() %></div>
                                </div>
                            </div>
                            <div>태그
                            	<%if(!tagMap.get(memList.get(i).getMember_mail()).isEmpty()) {
                            		for(TagVO vo : tagMap.get(memList.get(i).getMember_mail())) {%>
                            		<span>#<%=vo.getTag() %>  </span>
                            	<%}} %>
                            </div>
                        </div>
                    </div>
                    <%} %>
                </div>
                <div class="pageList">
                <%if(searchflag) {%>
                <% for(int i = pagingList*10; i < pageCnt; i++){ 
						if(i>pagingList*10+9) break;%>
					<a class="page" href="/UserList?cmd=search&page=<%=i + 1 %>&value=<%=value%>"><%=i+1 %></a>
				<%}}else{for(int i = pagingList*10; i < pageCnt; i++){ 
						if(i>pagingList*10+9) break;%>
					<a class="page" href="/UserList?cmd=ranking&page=<%=i + 1 %>"><%=i+1 %></a>
				<%}} %>
				</div>
            </div>
        </main>
        <footer></footer>
        <input type="hidden" class="flag" value="<%=flag%>">
    </div>
<input type="hidden" class="flag" value="<%=flag%>">
<script src="../js/jquery-3.6.0.min.js"></script>
<script src="../js/jquery-ui.min.js"></script>
<script src="../js/layout.js"></script>
</body>
</html>