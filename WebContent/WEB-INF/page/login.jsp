<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Abocado TV</title>
    <link rel="stylesheet" href="../css/jquery-ui.min.css">
    <link rel="stylesheet" href="../css/reset.css">
    <link rel="stylesheet" href="../css/common.css">
    <link rel="stylesheet" href="../css/layout.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
    <link rel="stylesheet" href="../css/login.css">
</head>

<%
	String mail = (String)session.getAttribute("mail");
	String errMsg = (String)session.getAttribute("errMsg");
	
    boolean flag = true;	
	boolean saveFlag = true;

	if(errMsg == null) errMsg = "";
	if(mail==null) flag = false;
	
    Cookie[] c = request.getCookies();
	String cookieVal = "";
	
    if(c != null) {
		for(Cookie i : c) {
			if(i.getName().equals("saveMail")) {
				cookieVal = i.getValue();
			}
		}
	}
	
    if(cookieVal ==""){
		saveFlag = false;
	}
    
    session.invalidate();
%>

<body>
    <div class="body__container">
        <header></header>
        <nav></nav>
        <main>
            <div class="main">
                <h1>LOGIN</h1>
				<div class="errMsg"><%=errMsg %></div>
				
                <div class="loginbox">
                    <form action="<%=request.getContextPath()%>/login" method="post" class="login-form">
                        <div class="input-data">
                            <div class="relative">
                                <input type="text" id="mail" name="mail" class="form-text" spellcheck="false"
                                <% if(saveFlag) { %> value=<%= cookieVal%> <% }%> >
                                <label for="mail" class="text-label" onkeyup="enterkey()">E-MAIL</label>
                            </div>
                            <div class="relative">
                                <input type="password" id="password" name="password" class="form-text" onkeyup="enterkey()">
                                <label for="password" class="text-label" onkeyup="enterkey()">PASSWORD</label>
                                <img src="../img/passwordNoSee.jpg" class="password-see">
                            </div>
                        </div>
                        <div class="save-id">
                            <input class="savelogin" type="checkbox" id="save-id" class="keepLogin" name="saveMail"
                            <% if(saveFlag) { %> checked <% }%>>
                            <label for="save-id">아이디 저장</label>
                        </div>
                        <button type="button" class="login-btn">LOGIN</button>
                    </form>
                    <div class="right-login">
                        <div class="naver-login">
                            <a href="#"><img class="naver" src="../img/naver.png" alt="네이버계정으로 로그인">네이버계정으로 로그인</a>
                        </div>
                        <div class="kakao-login" onclick="kakaoLogin();">
                            <a href="javascript:void(0)"><img class="kakao" src="../img/kakao.png" alt="카카오계정으로 로그인">카카오계정으로 로그인</a>
                        </div>
                        <div class="right-button">
                            <button class="join-button" type="button">SIGN UP</button>
                            <button class="forgot-button" type="button">Forgot PW?</button>
                        </div>
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
<script src="../js/login.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
</body>
</html>