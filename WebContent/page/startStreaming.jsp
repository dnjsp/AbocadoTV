<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String mail = (String)session.getAttribute("mail");
	boolean flag = true;
	if(mail == null) flag = false;
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
<link rel="stylesheet" href="../css/startStreaming.css">
<link rel="stylesheet" href="../css/layout.css">
<link rel="stylesheet" href="../css/reset.css">
<link href="https://vjs.zencdn.net/7.10.2/video-js.css" rel="stylesheet" />
<link href="https://unpkg.com/@silvermine/videojs-quality-selector/dist/css/quality-selector.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.slim.js" integrity="sha256-HwWONEZrpuoh951cQD1ov2HUK5zA5DwJ1DNUXaM6FsY=" crossorigin="anonymous"></script>
<script src="https://vjs.zencdn.net/7.10.2/video.min.js"></script>
<script src="https://unpkg.com/@silvermine/videojs-quality-selector/dist/js/silvermine-videojs-quality-selector.min.js"></script>
</head>
<body>
    <div class="body__container">
        <header></header>
        <main class="main">
            <form action="/startStreaming.do" class="start-form">
                <label>카테고리 : <input type="text" name="stream-category" class="stream-category"></label><br>
                    <label>제목 : <input type="text" name="stream-title" class="stream-title"></label><br>
                    <label class="comment">내용 : <textarea class="stream-content" name="content" rows="5" cols="33"></textarea></label>
            </form>
		   <video id="webcam-id" class="video-js vjs-default-skin" autoplay></video>
           <button type="button" class="submit-btn">다음</button>
        </main>
        <footer></footer>
        <input type="hidden" class="flag" value="<%=flag%>">
    </div>
<script src="../js/jquery-3.6.0.min.js"></script>
<script src="../js/webSocket.js"></script>
<script src="../js/startStreaming.js"></script>
<script src="../js/layout.js"></script>
</body>
</html>