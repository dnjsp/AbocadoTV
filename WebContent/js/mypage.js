const pwForm = document.querySelector(".pw-form");
const passwordText = document.querySelector("#password");
const loginBtn = document.querySelector(".login-btn");
const mypage = document.querySelector(".mypage");
const introBtn = document.querySelector(".intro-updatebtn");
const introTxt = document.querySelector(".introduce-input");
const passBtn = document.querySelector(".pass-updatebtn");
const passTxt = document.querySelector(".pass-info");
const nicknameBtn = document.querySelector(".nickname-updatebtn");
const nicknameTxt = document.querySelector(".nickname-info");
const seed = document.querySelector('#seedBtn');
const passImage = document.querySelector(".password-see");
const labelPlaceholers = document.querySelectorAll(".form-text");
const possess = document.querySelector('#posessingBtn');
const passSee = document.querySelector(".passwordsee");
const inputPass = document.querySelector(".pass-info");
const tagAddBtn = document.querySelector(".tag-updatebtn");
const tags = document.querySelectorAll(".tags");
const imgfile = document.querySelector(".imgfile");

const CLASSNAME_HIDDEN = "hidden";
const CLASSNAME_LABELMOVE = "labelmove";

const PASSWORDREG_1 = /[A-Za-z\d#?!@$%^&*-]{8,}$/;
const PASSWORDREG_2 = /(?=.*?[a-z])(?=.*?[A-Z])/;
const PASSWORDREG_3 = /(?=.*?[0-9#?!@$%^&*-])/;

var passFlag = false;

loginBtn.addEventListener('click',function(){
	if(passwordText.value===""){
		passwordText.focus();
		return;
	}
	passCheck();
});
imgfile.addEventListener("change",function(){
	const userimg = document.querySelector(".userimg");
	let files = imgfile.files;
	let file = files[0];
	let reader = new FileReader();
	reader.onload = function(e){
		const newImg = document.createElement("img");
		newImg.src = e.target.result;
		var canvas = document.createElement("canvas");      
        // 캔버스에 업로드된 이미지를 그려줍니다
        canvas.width = 180;
        canvas.height = 180;
        var ctx = canvas.getContext("2d");
		
		setTimeout(function(){
			ctx.drawImage(newImg, 0, 0, 180, 180);
			var dataurl = canvas.toDataURL();
	        userimg.src = dataurl;
			var block = dataurl.split(';');
			var contentType = block[0].split(':')[1];
			var realData = block[1].split(',')[1];
			var blob = base64ToBlob(realData, contentType);
			var formdata = new FormData();
			formdata.append("blob",blob);
			$.ajax({
		      	url:"/Mypage?cmd=imageUpload",
		      	method:"post",
		      	data: formdata,
				processData : false,
        		contentType : false,
		      	success: function(){
		      	},
		      	error: function(xhr){
		         	alert(xhr.status);
		      	},
		      	dataType: 'json'
		   });
		},100);
	}
	reader.readAsDataURL(file);
});

var base64ToBlob = function(base64Data, contentType, sliceSize) {
    contentType = contentType || '';
    sliceSize = sliceSize || 512;

    var byteCharacters = atob(base64Data);
    var byteArrays = [];

    for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {
        var slice = byteCharacters.slice(offset, offset + sliceSize);

        var byteNumbers = new Array(slice.length);
        for (var i = 0; i < slice.length; i++) {
            byteNumbers[i] = slice.charCodeAt(i);
        }

        var byteArray = new Uint8Array(byteNumbers);
        byteArrays.push(byteArray);
    }

    var blob = new Blob(byteArrays, { type: contentType });

    return blob;
}

seed.addEventListener('click',function(){
   window.open('/Mypage?cmd=charge',"_blank","width=400,height=500");
});
introBtn.addEventListener("click",function(){
   update('introduction',introTxt.value);
});
passBtn.addEventListener("click",function(){
	if(passFlag){
	   update('password',passTxt.value);		
	}else{
		alert("비밀번호가 조건에 맞지 않습니다.");
	}
});
nicknameBtn.addEventListener("click",function(){
   update('nickname',nicknameTxt.value);
});
possess.addEventListener('click',function(){
	window.open('/Mypage?cmd=seedList',"_blank","width=400,height=500");
});

function update(target,value){
   const param = {
      cmd : 'update',
	  target : target,
      value : value
   };
   $.ajax({
      url:"/Mypage",
      method:"post",
      data: param,
      success: function(res){
         if(res.sw ==='ok') {
            if(target==='password'){
               passTxt.value="";
            }
            alert("수정성공");
         }else{
            alert("수정실패");
         }
      },
      error: function(xhr){
         alert(xhr.status);
      },
      dataType: 'json'
   });
}

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

passSee.addEventListener("click",function(){
   if(inputPass.type==="password"){
      inputPass.type = "text";
      this.src="../img/passwordSee.jpg";
   }else{
      inputPass.type = "password";
      this.src="../img/passwordNoSee.jpg";
   }
});
passTxt.addEventListener("keyup",  function() {
	const passwordCheck = document.querySelector(".pass-check");
    if(this.value===""){
        passwordCheck.classList.add(CLASSNAME_HIDDEN);
    }else{
        const firstImg = document.querySelector(".first-check");
        const secondImg = document.querySelector(".second-check");
        const lastImg = document.querySelector(".last-check");
        var flag1 = false;
        var flag2 = false;
        var flag3 = false;
        passFlag = false;
        if(PASSWORDREG_1.test(this.value)){
            firstImg.src = "../img/check.jpg"
            flag1 = true;
        }else{
            firstImg.src = "../img/x.jpg"
        }
        if(PASSWORDREG_2.test(this.value)){
            secondImg.src = "../img/check.jpg"
            flag2 = true;
        }else{
            secondImg.src = "../img/x.jpg"
        }
        if(PASSWORDREG_3.test(this.value)){
            lastImg.src = "../img/check.jpg"
            flag3 = true;
        }else{
            lastImg.src = "../img/x.jpg"
        }
        if(flag1&&flag2&&flag3){
            passwordCheck.classList.add(CLASSNAME_HIDDEN);
            passFlag = true;
        }else{
	        passwordCheck.classList.remove(CLASSNAME_HIDDEN);	
		}
    }
});

tagAddBtn.addEventListener("click",function(){
	const tagInput = document.querySelector(".tag-input");
	const tagList = document.querySelector(".tagList");
	if(tagInput.value==""){
		alert("입력이 없습니다.");
		return;
	}
	$.ajax({
    	url:"/Mypage",
    	method:"post",
    	data: {
			cmd : 'tagAdd',
			value : tagInput.value
		},
		success: function(res){
			const tag = document.createElement("div");
			tag.classList.add("tag");
			tag.innerHTML = `
			<label>${tagInput.value}</label>
            <button type="button" id="${res.idx}Tag" class="tags">X</button>`;
			tagList.append(tag);
			const deleteBtn = tag.querySelector(".tags");
			tagDelete(deleteBtn);
		},
	    error: function(xhr){
			console.log(xhr.status);
	    },
	    dataType: 'json'
	});
});

tags.forEach(tag =>{
	tagDelete(tag);
});

function tagDelete(tag){
	tag.addEventListener("click",function(){
		const parent = tag.parentNode;
		$.ajax({
	    	url:"/Mypage",
	    	method:"post",
	    	data: {
				cmd : 'tagDelete',
				value : tag.id
			},
			success: function(res){
				if(res.sw=="ok"){
					parent.remove();
				}else{
					alert("삭제에 실패하였습니다.");
				}
			},
		    error: function(xhr){
				console.log(xhr.status);
		    },
		    dataType: 'json'
		});
	});
}
function enterkey() {
   if(window.event.keyCode==13) {
       passCheck();
   }
}

function passCheck() {
	$.ajax({
    	url:"/Mypage",
    	method:"post",
    	data: {
			cmd : 'passCheck',
			pass : passwordText.value
		},
		success: function(res){
        	if(res.sw ==='ok') {
				pwForm.classList.add(CLASSNAME_HIDDEN);
				mypage.classList.remove(CLASSNAME_HIDDEN);
			}else{
				alert("비밀번호가 틀렸습니다.");	
			}
		},
	    error: function(xhr){
			console.log(xhr.status);
	    },
	    dataType: 'json'
	});
}