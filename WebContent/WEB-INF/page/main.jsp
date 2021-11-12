<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   MemberVO vo = (MemberVO)request.getAttribute("vo");
   String img = (String)request.getAttribute("img");
   String mail = (String)session.getAttribute("mail");
	boolean flag = true;	
	if(mail==null) flag = false;
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Abocado TV</title>
    <link rel="stylesheet" href="../css/jquery-ui.min.css">
    <link rel="stylesheet" href="../css/reset.css">
	<link rel="stylesheet" href="../css/layout.css">
    <link rel="stylesheet" href="../css/common.css">
    <link rel="stylesheet" href="../css/main.css">
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
                <div class="sliding-box">
                    <img class="left" src="../img/arrow.jpg" alt="다음" />
                    <div class="sliding-view">
                        <h2>랭킹 순</h2>
                        <ul class="containner">
                            <li class="container_list">
                                <a href="/error.html"><img src="../../img/main-img4.png" alt="" /></a>
                                <div class="list_title">
                                    <a href="#" class="title">간단한 간식 만들기</a>
                                        <span class="title-lower">
                                            <a href="#" class="user">강주희</a>
                                                <span class="view">
                                                    <i class="fas fa-user-friends"></i>
                                                    <span>5,000</span>
                                                </span>
                                        </span>
                                </div>
                            </li>
                            <li class="container_list">
                                <a href="#"><img src="../../img/main-img2.png" alt="" /></a>
                                <div class="list_title">
                                    <a href="#" class="title">스테이크 요리</a>
                                        <span class="title-lower">
                                            <a href="#" class="user">귀염둥이</a>
                                                <span class="view">
                                                    <i class="fas fa-user-friends"></i>
                                                    <span>3,000</span>
                                                </span>
                                        </span>
                                </div>
                            </li>
                            <li class="container_list">
                                <a href="#"><img src="../../img/main-img1.png" alt="" /></a>
                                <div class="list_title">
                                    <a href="#" class="title">양배추계란 요리법</a>
                                        <span class="title-lower">
                                            <a href="#" class="user">조워네</a>
                                                <span class="view">
                                                    <i class="fas fa-user-friends"></i>
                                                    <span>500</span>
                                                </span>
                                        </span>
                                </div>
                            </li>
                            <li class="container_list">
                                <a href="#"><img src="../../img/main-img3.png" alt="" /></a>
                                <div class="list_title">
                                    <a href="#" class="title">양배추와 사과</a>
                                        <span class="title-lower">
                                            <a href="#" class="user">유영진</a>
                                                <span class="view">
                                                    <i class="fas fa-user-friends"></i>
                                                    <span>1,000</span>
                                                </span>
                                        </span>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <img class="right" src="../img/arrow.jpg" alt="이전" />
                </div>
                <div class="sliding-box">
                    <img class="left" src="../img/arrow.jpg" alt="다음" />
                    <div class="sliding-view">
                        <h2>좋아요 순</h2>
                        <ul class="containner">
                            <li class="container_list">
                                <a href="#"><img src="../../img/main-img5.png" alt="" /></a>
                                <div class="list_title">
                                    <a href="#" class="title">계란볶음밥</a>
                                        <span class="title-lower">
                                            <a href="#" class="user">임태희</a>
                                                <span class="view">
                                                    <i class="fas fa-user-friends"></i>
                                                    <span>500</span>
                                                </span>
                                        </span>
                                </div>
                            </li>
                            <li class="container_list">
                                <a href="#"><img src="../../img/main-img6.png" alt="" /></a>
                                <div class="list_title">
                                    <a href="#" class="title">요리하는 콩순이</a>
                                        <span class="title-lower">
                                            <a href="#" class="user">강주희</a>
                                                <span class="view">
                                                    <i class="fas fa-user-friends"></i>
                                                    <span>9,000</span>
                                                </span>
                                        </span>
                                </div>
                            </li>
                            <li class="container_list">
                                <a href="#"><img src="../../img/main-img7.png" alt="" /></a>
                                <div class="list_title">
                                    <a href="#" class="title">감자튀김과 치즈소스</a>
                                        <span class="title-lower">
                                            <a href="#" class="user">영진이</a>
                                                <span class="view">
                                                    <i class="fas fa-user-friends"></i>
                                                    <span>100</span>
                                                </span>
                                        </span>
                                </div>
                            </li>
                            <li class="container_list">
                                <a href="#"><img src="../../img/main-img8.png" alt="" /></a>
                                <div class="list_title">
                                    <a href="#" class="title">양배추 손질법</a>
                                        <span class="title-lower">
                                            <a href="#" class="user">원혜</a>
                                                <span class="view">
                                                    <i class="fas fa-user-friends"></i>
                                                    <span>50</span>
                                                </span>
                                        </span>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <img class="right" src="../img/arrow.jpg" alt="이전" />
                </div>
                <div class="sliding-box">
                    <img class="left" src="../img/arrow.jpg" alt="다음" />
                    <div class="sliding-view">
                        <h2>카테고리 순</h2>
                        <ul class="containner">
                            <li class="container_list">
                                <a href="#"><img src="../../img/main-img9.png" alt="" /></a>
                                <div class="list_title">
                                    <a href="#" class="title">가지요리 이렇게 하세요</a>
                                        <span class="title-lower">
                                            <a href="#" class="user">유뇨주</a>
                                                <span class="view">
                                                    <i class="fas fa-user-friends"></i>
                                                    <span>50,000</span>
                                                </span>
                                        </span>
                                </div>
                            </li>
                            <li class="container_list">
                                <a href="#"><img src="../../img/main-img10.png" alt="" /></a>
                                <div class="list_title">
                                    <a href="#" class="title">치즈파이 먹방</a>
                                        <span class="title-lower">
                                            <a href="#" class="user">이이난</a>
                                                <span class="view">
                                                    <i class="fas fa-user-friends"></i>
                                                    <span>7,000</span>
                                                </span>
                                        </span>
                                </div>
                            </li>
                            <li class="container_list">
                                <a href="#"><img src="../../img/main-img11.png" alt="" /></a>
                                <div class="list_title">
                                    <a href="#" class="title">제목</a>
                                        <span class="title-lower">
                                            <a href="#" class="user">나제로</a>
                                                <span class="view">
                                                    <i class="fas fa-user-friends"></i>
                                                    <span>1,000</span>
                                                </span>
                                        </span>
                                </div>
                            </li>
                            <li class="container_list">
                                <a href="#"><img src="../../img/main-img12.png" alt="" /></a>
                                <div class="list_title">
                                    <a href="#" class="title">또띠아 먹방</a>
                                        <span class="title-lower">
                                            <a href="#" class="user">조원혜</a>
                                                <span class="view">
                                                    <i class="fas fa-user-friends"></i>
                                                    <span>4,200</span>
                                                </span>
                                        </span>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <img class="right" src="../img/arrow.jpg" alt="이전" />
                </div>
            </div>
        </main>
        <footer></footer>
    </div>
<input type="hidden" class="flag" value="<%=flag%>"> 
<script src="../js/jquery-3.6.0.min.js"></script>
<script src="../js/jquery-ui.min.js"></script>
<script src="../js/layout.js"></script>
<script src="../js/main.js"></script>
</body>
</html>