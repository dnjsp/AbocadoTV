<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="kr.or.ddit.vo.StreamingVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String mail = (String)session.getAttribute("mail");
	List<StreamingVO> list = (List<StreamingVO>)request.getAttribute("streamingList");
	MemberVO memVO = (MemberVO)request.getAttribute("memberVO");
	boolean flag = true;	
	if(mail==null) flag = false;
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>StreamingList</title>
    <link rel="stylesheet" href="../css/jquery-ui.min.css">
    <link rel="stylesheet" href="../css/reset.css">
    <link rel="stylesheet" href="../css/layout.css">
    <link rel="stylesheet" href="../css/common.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
    <link rel="stylesheet" href="../css/streamingList.css">
</head>
<body>
    <div class="body__container">
        <header></header>
        <nav></nav>
        <main>
            <div class="main">
                <h1>STREAMING LIST</h1>
                <div>
                	<%if(!list.isEmpty()){ %>
                    <div class="streaming-list">
                        <div class="rank-list">
                            <div class="rank-view">
                                <a href="/Streaming?cmd=see&memberMail=<%=memVO.getMember_mail()%>">
                                    <img class="rank-img" src="../memImg/<%=memVO.getProfileimg() %>" alt="">
                                </a>
                                <div class="rank-right">
                                    <div class="rank-name">
                                        <a href="/Streaming?cmd=see&memberMail=<%=memVO.getMember_mail()%>" class="title"><%=memVO.getNickname() %></a>
                                            <span class="title-lower">
                                                <a href="#" class="user"><%=memVO.getFollowcount() %></a>
                                                    <span class="view">
                                                        <i class="fas fa-user-friends"></i>
                                                        <span><%=list.get(0).getStreaming_category() %></span>
                                                    </span>
                                            </span>
                                    </div>
                                </div>
                            </div> 
                        </div>
                    </div>
                    <%} else{%>
                    <div>
                    	<span style="margin-left: 40%;">현재 생방송중인 방송이 없습니다.</span>
                    </div>
                    <%} %>
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