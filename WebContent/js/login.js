const labelPlaceholers = document.querySelectorAll(".form-text");
const loginForm = document.querySelector(".login-form");
const mailText = document.querySelector("#mail");
const passwordText = document.querySelector("#password");
const passImage = document.querySelector(".password-see");
const loginBtn = document.querySelector(".login-btn");
const joinButton = document.querySelector(".join-button");
const forgotPwButton = document.querySelector(".forgot-button");

const CLASSNAME_HIDDEN = "hidden";
const CLASSNAME_LABELMOVE = "labelmove";

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

passImage.addEventListener("click",function() {
    if(passwordText.type==="password"){
        passwordText.type = "text";
        this.src="../img/passwordSee.jpg";
    }else{
        passwordText.type = "password";
        this.src="../img/passwordNoSee.jpg";
    }
});

loginBtn.addEventListener("click",function(){
   loginSubmit();
});

function loginSubmit(){
 	if(mailText.value===""){
        mailText.focus();
        return;
    }else if(passwordText.value===""){
        passwordText.focus();
        return;
    }
    loginForm.submit();
}

function enterkey() {
    if(window.event.keyCode==13) {
        loginSubmit();
    }
}

joinButton.addEventListener("click",function(){
    location.href="/Main?cmd=signup";
});
forgotPwButton.addEventListener("click",function(){
	window.open("/page/forgotpw.html","_blank","width=650,height=600");
});

setTimeout(function(){
	mailText.focus();
},10);



Kakao.init('439db4f31d3b3084d239a307954f9b65'); //발급받은 키 중 javascript키를 사용해준다.
console.log(Kakao.isInitialized()); // sdk초기화여부판단
//카카오로그인
function kakaoLogin() {
    Kakao.Auth.login({
      success: function (response) {
        Kakao.API.request({
          url: '/v2/user/me',
          success: function (response) {
        	  console.log(response)
          },
          fail: function (error) {
            console.log(error)
          },
        })
      },
      fail: function (error) {
        console.log(error)
      },
    })
  }
  