<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
String mail = (String)session.getAttribute("mail");
boolean flag = true;
if(mail == null) flag = false;
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Service Center</title>
    <link rel="stylesheet" href="../css/jquery-ui.min.css">
    <link rel="stylesheet" href="../css/reset.css">
    <link rel="stylesheet" href="../css/layout.css">
    <link rel="stylesheet" href="../css/common.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
    <link rel="stylesheet" href="../css/servicecenter.css">
</head>

<body>

    <div class="body__container">
        <header></header>
        <nav></nav>
        <main>
            <div class="main">
                <h1>Service Center</h1>
                <div class="servicecenter">
                    <div class="title">
                        <div class="consult title-box title-click">상담</div>
                        <div class="introduce title-box">소개</div>
                    </div>
                    <div class="service-box">
                        <div class="service">
                            <div class="send">
                                 <form class="mail-box" action="/serviceCenter">
                                    <div class="content">
                                        <select class="category" name="category">
                                            <option value="">카테고리 선택</option>
                                            <option value="신고">신고</option>
                                            <option value="환불">환불</option>
                                            <option value="이용불편">이용불편</option>
                                            <option value="기타">기타</option>
                                        </select>
                                        <textarea name="content" class="input-info" id="textarea" cols="30" rows="10" spellcheck="false"></textarea>
                                    </div>
                                    <div class="send-box">
                                        <span>답변 받을 방법</span>
                                        <div>
                                            <input type="radio" name="chk_info" value="email" checked="checked">이메일
                                            <input type="radio" name="chk_info" value="call">전화
                                            <input type="radio" name="chk_info" value="visit">방문
                                        </div>
                                    </div>
                                    <div class="submit">
                                        <input type="text" name="answer" class="radio-text input-info" placeholder="이메일, 전화번호, 주소 중 한 개 입력">
                                        <button type="button" class="submit-btn">제 출</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div>
                            <div class="introduce-box">
                                <div class="service-introduce-box hidden">
                                    <div class="service-introduce">
                                        <div class="introduce-view">
                                            <button type="button" class="introduce-btn service-btn">서비스 소개</button>
                                            <textarea class="textarea textarea-service hidden" disabled name="" id="textarea" cols="30" rows="10" spellcheck="false">
상처를 치료해줄 사람 어디 없나 가만히 놔두다간 끊임없이 덧나 사랑도 사람도 너무나도 겁나 혼자인게 무서워 난 잊혀질까 두려워 상처를 치료해줄 사람 어디 없나 가만히 놔두다간 끊임없이 덧나 사랑도 사람도 너무나도 겁나 혼자인게 무서워 난 잊혀질까 두려워 언제나 외톨이 맘의 문을 닫고 슬픔을 등에 지고 살아가는 바보 두 눈을 감고 두 귀를 막고 캄캄한 어둠속에 내 자신을 가둬 365일 1년 내내 방황하는 내 영혼을 쫓아 키를 잡은 Jack Sparrow 몰아치는 Hurricane 졸라매는 허리끈에 방향감을 상실하고 길을 잃은 소리꾼 내 안에 숨어 있는 또 다른 나와 싸워 그녀가 떠나갈때 내게 말했었지 너는 곁에 있어도 있는 게 아닌것 같다고 만지면 베어버리는 칼날같은 사람 심장이 얼어붙은 차가웠던 사랑 그래 1분 1초가 사는게 사는게 아냐 매일 매일이 너무나 두려워 M'aider 누가 날 좀 꺼내줘 언제나 외톨이 맘의 문을 닫고 슬픔을 등에 지고 살아가는 바보 두 눈을 감고 두 귀를 막고 캄캄한 어둠속에 내 자신을 가둬 언제나 외톨이 맘의 문을 닫고 슬픔을 등에 지고 살아가는 바보 두 눈을 감고 두 귀를 막고 캄캄한 어둠속에 내 자신을 가둬 아무도 모르게 다가온 이별에 대면했을때 또 다시 혼자가 되는게 두려워 외면했었네 꿈에도 그리던 지나간 시간이 다시금 내게로 되돌아오기를 바라며 간절한 맘으로 밤마다 기도했었네 시위를 당기고 내 손을 떠나간 추억의 화살이 머나먼 과녁을 향해서 한없이 빠르게 날아가 내게로 돌아와 달라고 내 손을 붙잡아 달라고 부르고 불러도 한없이 소리쳐 대봐도 아무런 대답이 없는 널 내 기억 속에서 너라는 사람의 존재를 완전히 지우려 끝없이 몸부림쳐 봐도 매일밤 꿈에서 그대가 나타나 흐르는 눈물을 닦아주는걸 나 어떡하라고 다 끄떡없다고 거짓말 하라고 더는 못 참겠다고 나도 아플 땐 아프다고 슬플땐 슬프다고 얼어 붙은 심장이 자꾸만 내게로 고자질해 정말로 끝이라고 정말로 괜찮다고 꾹 참고 참았던 눈물이 자꾸만 내게로 쏟아지네 상처를 치료해줄 사람 어디 없나 가만히 놔두다가 끊임없이 덧나 사랑도 사람도 너무나도 겁나 혼자인게 무서워 난 잊혀질까 두려워 상처를 치료해줄 사람 어디 없나 가만히 놔두다가 끊임없이 덧나 사랑도 사람도 너무나도 겁나 혼자인게 무서워 난 잊혀질까 두려워 언제나 외톨이 맘의 문을 닫고 슬픔을 등에 지고 살아가는 바보 두 눈을 감고 두 귀를 막고 캄캄한 어둠속에 내 자신을 가둬 언제나 외톨이 맘의 문을 닫고 슬픔을 등에 지고 살아가는 바보 두 눈을 감고 두 귀를 막고 캄캄한 어둠속에 내 자신을 가둬</textarea>
                                        </div>
                                        <div class="introduce-view">
                                            <button type="button" class="introduce-btn streaming-btn">스트리밍 신청 방법</button>
                                            <textarea class="textarea textarea-streaming hidden" disabled name="" id="textarea" cols="30" rows="10" spellcheck="false">
상처를 치료해줄 사람 어디 없나 가만히 놔두다간 끊임없이 덧나 사랑도 사람도 너무나도 겁나 혼자인게 무서워 난 잊혀질까 두려워 상처를 치료해줄 사람 어디 없나 가만히 놔두다간 끊임없이 덧나 사랑도 사람도 너무나도 겁나 혼자인게 무서워 난 잊혀질까 두려워 언제나 외톨이 맘의 문을 닫고 슬픔을 등에 지고 살아가는 바보 두 눈을 감고 두 귀를 막고 캄캄한 어둠속에 내 자신을 가둬 365일 1년 내내 방황하는 내 영혼을 쫓아 키를 잡은 Jack Sparrow 몰아치는 Hurricane 졸라매는 허리끈에 방향감을 상실하고 길을 잃은 소리꾼 내 안에 숨어 있는 또 다른 나와 싸워 그녀가 떠나갈때 내게 말했었지 너는 곁에 있어도 있는 게 아닌것 같다고 만지면 베어버리는 칼날같은 사람 심장이 얼어붙은 차가웠던 사랑 그래 1분 1초가 사는게 사는게 아냐 매일 매일이 너무나 두려워 M'aider 누가 날 좀 꺼내줘 언제나 외톨이 맘의 문을 닫고 슬픔을 등에 지고 살아가는 바보 두 눈을 감고 두 귀를 막고 캄캄한 어둠속에 내 자신을 가둬 언제나 외톨이 맘의 문을 닫고 슬픔을 등에 지고 살아가는 바보 두 눈을 감고 두 귀를 막고 캄캄한 어둠속에 내 자신을 가둬 아무도 모르게 다가온 이별에 대면했을때 또 다시 혼자가 되는게 두려워 외면했었네 꿈에도 그리던 지나간 시간이 다시금 내게로 되돌아오기를 바라며 간절한 맘으로 밤마다 기도했었네 시위를 당기고 내 손을 떠나간 추억의 화살이 머나먼 과녁을 향해서 한없이 빠르게 날아가 내게로 돌아와 달라고 내 손을 붙잡아 달라고 부르고 불러도 한없이 소리쳐 대봐도 아무런 대답이 없는 널 내 기억 속에서 너라는 사람의 존재를 완전히 지우려 끝없이 몸부림쳐 봐도 매일밤 꿈에서 그대가 나타나 흐르는 눈물을 닦아주는걸 나 어떡하라고 다 끄떡없다고 거짓말 하라고 더는 못 참겠다고 나도 아플 땐 아프다고 슬플땐 슬프다고 얼어 붙은 심장이 자꾸만 내게로 고자질해 정말로 끝이라고 정말로 괜찮다고 꾹 참고 참았던 눈물이 자꾸만 내게로 쏟아지네 상처를 치료해줄 사람 어디 없나 가만히 놔두다가 끊임없이 덧나 사랑도 사람도 너무나도 겁나 혼자인게 무서워 난 잊혀질까 두려워 상처를 치료해줄 사람 어디 없나 가만히 놔두다가 끊임없이 덧나 사랑도 사람도 너무나도 겁나 혼자인게 무서워 난 잊혀질까 두려워 언제나 외톨이 맘의 문을 닫고 슬픔을 등에 지고 살아가는 바보 두 눈을 감고 두 귀를 막고 캄캄한 어둠속에 내 자신을 가둬 언제나 외톨이 맘의 문을 닫고 슬픔을 등에 지고 살아가는 바보 두 눈을 감고 두 귀를 막고 캄캄한 어둠속에 내 자신을 가둬</textarea>
                                        </div>
                                        <div class="introduce-view">
                                            <button type="button" class="introduce-btn goods-btn">상품 이용 방법</button>
                                            <textarea class="textarea textarea-goods hidden" disabled name="" id="textarea" cols="30" rows="10" spellcheck="false">
상처를 치료해줄 사람 어디 없나 가만히 놔두다간 끊임없이 덧나 사랑도 사람도 너무나도 겁나 혼자인게 무서워 난 잊혀질까 두려워 상처를 치료해줄 사람 어디 없나 가만히 놔두다간 끊임없이 덧나 사랑도 사람도 너무나도 겁나 혼자인게 무서워 난 잊혀질까 두려워 언제나 외톨이 맘의 문을 닫고 슬픔을 등에 지고 살아가는 바보 두 눈을 감고 두 귀를 막고 캄캄한 어둠속에 내 자신을 가둬 365일 1년 내내 방황하는 내 영혼을 쫓아 키를 잡은 Jack Sparrow 몰아치는 Hurricane 졸라매는 허리끈에 방향감을 상실하고 길을 잃은 소리꾼 내 안에 숨어 있는 또 다른 나와 싸워 그녀가 떠나갈때 내게 말했었지 너는 곁에 있어도 있는 게 아닌것 같다고 만지면 베어버리는 칼날같은 사람 심장이 얼어붙은 차가웠던 사랑 그래 1분 1초가 사는게 사는게 아냐 매일 매일이 너무나 두려워 M'aider 누가 날 좀 꺼내줘 언제나 외톨이 맘의 문을 닫고 슬픔을 등에 지고 살아가는 바보 두 눈을 감고 두 귀를 막고 캄캄한 어둠속에 내 자신을 가둬 언제나 외톨이 맘의 문을 닫고 슬픔을 등에 지고 살아가는 바보 두 눈을 감고 두 귀를 막고 캄캄한 어둠속에 내 자신을 가둬 아무도 모르게 다가온 이별에 대면했을때 또 다시 혼자가 되는게 두려워 외면했었네 꿈에도 그리던 지나간 시간이 다시금 내게로 되돌아오기를 바라며 간절한 맘으로 밤마다 기도했었네 시위를 당기고 내 손을 떠나간 추억의 화살이 머나먼 과녁을 향해서 한없이 빠르게 날아가 내게로 돌아와 달라고 내 손을 붙잡아 달라고 부르고 불러도 한없이 소리쳐 대봐도 아무런 대답이 없는 널 내 기억 속에서 너라는 사람의 존재를 완전히 지우려 끝없이 몸부림쳐 봐도 매일밤 꿈에서 그대가 나타나 흐르는 눈물을 닦아주는걸 나 어떡하라고 다 끄떡없다고 거짓말 하라고 더는 못 참겠다고 나도 아플 땐 아프다고 슬플땐 슬프다고 얼어 붙은 심장이 자꾸만 내게로 고자질해 정말로 끝이라고 정말로 괜찮다고 꾹 참고 참았던 눈물이 자꾸만 내게로 쏟아지네 상처를 치료해줄 사람 어디 없나 가만히 놔두다가 끊임없이 덧나 사랑도 사람도 너무나도 겁나 혼자인게 무서워 난 잊혀질까 두려워 상처를 치료해줄 사람 어디 없나 가만히 놔두다가 끊임없이 덧나 사랑도 사람도 너무나도 겁나 혼자인게 무서워 난 잊혀질까 두려워 언제나 외톨이 맘의 문을 닫고 슬픔을 등에 지고 살아가는 바보 두 눈을 감고 두 귀를 막고 캄캄한 어둠속에 내 자신을 가둬 언제나 외톨이 맘의 문을 닫고 슬픔을 등에 지고 살아가는 바보 두 눈을 감고 두 귀를 막고 캄캄한 어둠속에 내 자신을 가둬</textarea>
                                        </div>  
                                    </div>
                                </div>  
                            </div>
                        </div>
                    </div>
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
<script src="../js/servicecenter.js"></script>
</body>
</html>