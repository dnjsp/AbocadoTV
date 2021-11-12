const searchForm = document.querySelector(".searchForm");
const searchInput = document.querySelector(".searchInput");
const searchBtn = document.querySelector(".searchButton");

searchInput.addEventListener("keyup",function(){
	if(window.event.keyCode==13) {
       search();
   }
});
searchBtn.addEventListener("click",search);

function search(){
	if(searchInput.value==""){
		alert("검색값이 없습니다.");
		return;
	}
	searchForm.submit();
}