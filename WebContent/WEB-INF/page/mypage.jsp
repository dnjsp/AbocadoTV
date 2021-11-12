<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.TagVO"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
   MemberVO vo = (MemberVO)request.getAttribute("vo");
   String img = (String)request.getAttribute("img");
   String mail = (String)session.getAttribute("mail");
   String intro = vo.getIntroduction();
   if(intro == null){
	   intro = "";
   }
   int sum = (int)request.getAttribute("sum");
   int myBoard = (int)request.getAttribute("myBoard");
   int myComment = (int)request.getAttribute("myComment");
   List<TagVO> tagList = (List<TagVO>)request.getAttribute("tagList");
   boolean flag = true;
   if(mail==null) flag = false;
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>MYPAGE</title>
<link rel="stylesheet" href="../css/jquery-ui.min.css">
<link rel="stylesheet" href="../css/layout.css">
<link rel="stylesheet" href="../css/reset.css">
<link rel="stylesheet" href="../css/mypage.css">
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
            <h1>MYPAGE</h1>
            <div class="pw-form">
               <div class="relative">
                  <input type="password" id="password" name="password" class="form-text" onkeyup="enterkey()">
                  <label for="password" class="text-label">PASSWORD</label>
                  <img src="../img/passwordNoSee.jpg" class="password-see">
              </div>
              <button type="button" class="login-btn">확인</button>
            </div>
            <div class="mypage hidden">
               <img src="../memImg/<%=vo.getProfileimg() %>" class="userimg">
               <div class="image">
                  <form class="image-form" method="post" enctype="multipart/form-data" action="Mypage?cmd=imageUpload">
                     <input type="file" name="filename" class="imgfile" accept="image/*">
                  </form>
               </div>
               <div class="basicinfo">
                  <ul>
                     <li>
                        <div>
                           <span class="page-th">이름 : </span>
                           <span><%=vo.getFirstname() %> <%=vo.getLastname() %></span>
                        </div>
                     </li>
                     <li>
                        <div>
                           <span class="page-th">닉네임 : </span>
                           <input type="text" value="<%=vo.getNickname() %>" class="input-info nickname-info">   
                           <button type="button" class="nickname-updatebtn basicinfo-btn">수정</button>
                        </div>
                     </li>
                     <li>
                        <div class="password-check">
                           <div>
                              <span class="page-th">비밀번호 :  </span>
                              <input type="password" class="input-info pass-info">
                              <button type="button" class="pass-updatebtn basicinfo-btn">수정</button>
                              <img src="../img/passwordNoSee.jpg" class="passwordsee">
                           </div>
                           <div class="pass-check hidden">
                              <ul class="pass-check-ul">
                                 <li><img src="../img/state.jpg" class="first-check">
                                    최소 8개 이상의 문자 포함</li>
                                 <li><img src="../img/state.jpg" class="second-check">
                                    소문자(a-z)와 대문자(A-Z) 모두 포함</li>
                                 <li><img src="../img/state.jpg" class="last-check">
                                    하나 이상의 숫자(0-9) 또는 기호 포함</li>
                              </ul>
                           </div>
                        </div>
                     </li>
                     <li>
                        <div>
                           <span class="page-th">이메일 : </span>
                           <span><%=vo.getMember_mail() %></span>
                        </div>
                     </li>
                     <li>
                        <div>
                           <span class="page-th">생년월일 : </span>
                           <span><%=vo.getRegedent_num() %></span>
                        </div>
                     </li>
                     <li>
                        <div class="tag-box">
                           <div class="tag-btn">
                              <span class="page-th">태그 :</span> 
                              <input type="text" value="" class="input-info tag-input">
                              <button type="button" class="tag-updatebtn basicinfo-btn">추가</button>
                           </div>
                           <div class="tagList">
	                           <%for(TagVO tag : tagList) {%>
	                           		<div class="tag">
		                           		<label><%=tag.getTag() %></label>
		                           		<button type="button" id="<%=tag.getTag_index()%>Tag" class="tags">X</button>
	                           		</div>
	                           <%} %>                           
                           </div>
                        </div>
                     </li>
                     <li>
                        <div class="introduce">
                           <span class="page-th">소개 : </span>
                           <textarea id="textarea" spellcheck="false" class="input-info introduce-input"><%=intro %></textarea>
                           <button type="button" class="intro-updatebtn basicinfo-btn">수정</button>
                        </div>
                     </li>
                     <li>
                        <div class="myseed">
                           <button type="button" id="posessingBtn">내가 가진 씨앗</button>
                           <span> : <%=sum %> 개</span>
                           <button type="button" id="seedBtn">씨앗 충전하기</button>   
                        </div>
                     </li>
                     <li>
                        <a href="/Mypage?cmd=category" class="basicinfo-category">게시판 카테고리 수정</a>
                     </li>
                     <li>
                        <div>
                           <span class="page-th">가입날짜 :</span> 
                           <span><%=vo.getMember_date() %></span>
                        </div>
                     </li>
                     <li>
                        <div>
                           <span class="page-th">내가 쓴 게시글 수 : </span>
                           <span><%=myBoard %></span>
                        </div>
                     </li>
                     <li>
                        <div>
                           <span class="page-th">내가 쓴 댓글 수 : </span>
                           <span><%=myComment %></span>
                        </div>
                     </li>
                  </ul>
               </div>
            </div>
         </div>
      </main>
      <footer></footer>
   </div>
<input type="hidden" class="flag" value="<%=flag%>"> 
<script src="../js/jquery-3.6.0.min.js"></script>
<script src="../js/jquery-ui.min.js"></script>
<script src="../js/layout.js"></script>
<script src="../js/mypage.js"></script>
</body>
</html>