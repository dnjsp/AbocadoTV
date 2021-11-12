<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KakaoPay</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
<link rel="stylesheet" href="../../css/reset.css">
<link rel="stylesheet" href="../../css/kakaoPayList.css">
</head>
<body>
 <div class="card-body bg-white mt-0 shadow">
		<form method="post" action="/Mypage?cmd=payment" class="kakaopay-form">
            <div class="title">
                <img alt="" src="../../img/abocado1.jpg">
                카카오페이 충전 금액
                <img alt="" src="../../img/abocado2.jpg">
            </div>
            <div class="kakao-box">
                <div class="kakao-label">
                    <label class="box-radio-input"><input type="radio" name="cp_item" value="100"><span>100원</span></label> <br>
                    <label class="box-radio-input"><input type="radio" name="cp_item" value="5000"><span>5,000원</span></label> <br>
                    <label class="box-radio-input"><input type="radio" name="cp_item" value="10000"><span>10,000원</span></label><br>
                    <label class="box-radio-input"><input type="radio" name="cp_item" value="20000"><span>20,000원</span></label><br>
                    <label class="box-radio-input"><input type="radio" name="cp_item" value="30000"><span>30,000원</span></label><br>
                    <label class="box-radio-input"><input type="radio" name="cp_item" value="40000"><span>40,000원</span></label><br>
                    <label class="box-radio-input"><input type="radio" name="cp_item" value="50000"><span>50,000원</span></label>
                </div>
                <p class="guide">카카오페이의 최소 충전금액은 5,000원이며 최대 충전금액은 50,000원 입니다.</p>
                <div class="btn-box">
                    <button type="submit" class="btn btn-lg btn-block btn-custom" id="charge_kakao">충전하기</button>
                </div>
            </div>
		</form>
 </div>
 
 
 
 
 
</body>
<!--  
<script>
const kakao = document.querySelector('#charge_kakao');

kakao.addEventListener('click',function(){
	
});

</script>
-->
</html>