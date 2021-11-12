const CLASSNAME_HIDDEN = "hidden";

const consult = document.querySelector(".consult");
const introduce = document.querySelector(".introduce");
const service = document.querySelector(".service");
const serviceIntroduce = document.querySelector(".service-introduce");
const serviceBtn = document.querySelector(".service-btn");
const streamingBtn = document.querySelector(".streaming-btn");
const goodsBtn = document.querySelector(".goods-btn");
const textareaService = document.querySelector(".textarea-service");
const textareaStreaming = document.querySelector(".textarea-streaming");
const textareaGoods = document.querySelector(".textarea-goods");
const serviceBox = document.querySelector(".service-introduce-box");
const mailBox = document.querySelector(".mail-box");
const submitBtn = document.querySelector(".submit-btn");

consult.addEventListener("click", function() {
    consult.classList.add("title-click");
    introduce.classList.remove("title-click");
    service.classList.remove("hidden");
    serviceBox.classList.add("hidden");
});

introduce.addEventListener("click", function() {
    introduce.classList.add("title-click");
    consult.classList.remove("title-click");
    service.classList.add("hidden");
    serviceBox.classList.remove("hidden");
});

document.getElementById("textarea").style.fontSize = '12.5px';

serviceBtn.addEventListener("click", function(){
    $(textareaService).slideToggle(500);
});
streamingBtn.addEventListener("click", function(){
    $(textareaStreaming).slideToggle(500);
});
goodsBtn.addEventListener("click", function(){
    $(textareaGoods).slideToggle(500);
});

submitBtn.addEventListener("click",function(){ 
	const category = document.querySelector(".category");
    const inputinfos = document.querySelectorAll(".input-info");
	if(category.value==""){
		alert("카테고리를 선택해주세요.");
		return;
	}
    inputinfos.forEach(inputinfo => {
        if(inputinfo.value==""){
            alert("내용을 입력해주세요.");
            return;
        }
    });
	mailBox.submit();
});