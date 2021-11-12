const messageTextArea = document.getElementById("messageTextArea");
const seedImg = document.querySelector(".donateImg");
const nickname = document.querySelector(".nicknameHidden").value;
webSocket.onmessage = function processMessge(message){
    console.log("aa");
    var jsonData = JSON.parse(message.data);
	if(jsonData.command == "send"){
	    if(jsonData.message != null) {
	        messageTextArea.value += jsonData.room + " : " + jsonData.message + "\n"
			messageTextArea.scrollTop = messageTextArea.scrollHeight;
	    }		
	}else if(jsonData.command == "donate"){
		seedImg.classList.remove("hidden");
		seedImg.classList.add("shake-chunk");
		setTimeout(function(){
			seedImg.classList.remove("shake-chunk");
			seedImg.classList.add("hidden");
		},2000);
		speak(`${jsonData.room}님이 ${jsonData.message}씨앗을 후원하였습니다.`, {
                rate: 1,
                pitch: 1.2,
                lang: 'ko-KR'
        })
	}else if(jsonData.command == "end"){
		window.close();
	}
}

function sendMessage(){
    var messageText = document.getElementById("messageText");
	if(messageText.value==""){
		return;
	}
    webSocket.send(createMessage('send',nickname,messageText.value));
    messageText.value = "";
}

function enterkey(){
	if(window.event.keyCode==13) {
		sendMessage();
	}
}

function speak(text, opt_prop) {
            if (typeof SpeechSynthesisUtterance === "undefined" || typeof window.speechSynthesis === "undefined") {
                alert("이 브라우저는 음성 합성을 지원하지 않습니다.")
                return
            }
            
            window.speechSynthesis.cancel() // 현재 읽고있다면 초기화

            const prop = opt_prop || {}

            const speechMsg = new SpeechSynthesisUtterance()
            speechMsg.rate = prop.rate || 1 // 속도: 0.1 ~ 10      
            speechMsg.pitch = prop.pitch || 1 // 음높이: 0 ~ 2
            speechMsg.lang = prop.lang || "ko-KR"
            speechMsg.text = text
            
            // SpeechSynthesisUtterance에 저장된 내용을 바탕으로 음성합성 실행
            window.speechSynthesis.speak(speechMsg)
} 