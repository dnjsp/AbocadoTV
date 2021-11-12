const passwordText = document.querySelector("#email");
const labelPlaceholers = document.querySelectorAll(".form-text");
const mailText = document.querySelector("#email");
const loginBtn = document.querySelector(".login-btn");

const CLASSNAME_HIDDEN = "hidden";
const CLASSNAME_LABELMOVE = "labelmove";

const MAILREG = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

labelPlaceholers.forEach(labelPlaceholder =>{
    labelPlaceholder.addEventListener("focus",function() {
        const label = this.nextSibling.nextSibling;
        label.classList.add(CLASSNAME_LABELMOVE);
        label.style.color = "#215C2f";
    })
    labelPlaceholder.addEventListener("blur",function() {
        const label = this.nextSibling.nextSibling;
        if(this.value===""){
            label.classList.remove(CLASSNAME_LABELMOVE);
            label.style.color = "#808080";
            label.style.color = "#808080";
        }
    })
 });

mailText.addEventListener("keyup", function() {
    const img = document.querySelector(`.email-form img`);
    if(MAILREG.test(mailText.value)){
        img.classList.remove(CLASSNAME_HIDDEN);
    }else{
        img.classList.add(CLASSNAME_HIDDEN);
    }
});

loginBtn.addEventListener("click",submit);
 
function enterkey() {
    if(window.event.keyCode==13) {
		submit();
    }
}

function submit(){
	if(mailText.value===""){
		mailText.focus();
		return;
	}
    if(!MAILREG.test(mailText.value)){
		mailText.focus();
		alert("제대로된 메일 형식이 아닙니다.");
		return;
	}
	mailText.disabled="true";
	loginBtn.disabled="true";
	$.ajax({
		type:'POST',
		url:'/signUp',
		data: {
			cmd : 'temporary',
			mail : mailText.value
		},	
		dataType: 'json',
		success: function(res){
			if(res.sw==="ok"){
				alert("임시비밀번호 발급에 성공하셨습니다.");
				window.close();	
			}else{
				alert("임시비밀번호 발급에 실패하셨습니다.");					
				window.close();	
			}
		},
		error: function(request,status,error){
            alert('code:'+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
	});
}
