const categoryWrap = document.querySelector(".category-wrap");
const categories = document.querySelector(".categories");
const mainCategory = document.querySelectorAll(".main-category");
const subCategory = document.querySelectorAll(".sub-category");
const addBtn = document.querySelector(".add-category");
const chaneBtn = document.querySelector(".change-btn");

const CLASSNAME_HIDDEN = "hidden";

mainCategory.forEach(category =>{
    btnview(category);
    mainBtn(category);
});

subCategory.forEach(category =>{
    btnview(category);
    subBtn(category);
});

addBtn.addEventListener("click",function(){
    createCategory();
});

chaneBtn.addEventListener("click",function(){
	const cancelBtn = categoryWrap.querySelector(".cancelBtn");
	if(cancelBtn!=null){
		alert("작업을 완료하고 눌러주세요.");
	}
	start();
	const modifyCategories = document.querySelectorAll(".category");
	for(var i = 0;i<modifyCategories.length;i++){
		if(modifyCategories[i].classList.length===2){
			const span = modifyCategories[i].querySelector("span");
			if(modifyCategories[i].classList.contains("main-category")){
				insertCategory('B',span.innerText,i+1,modifyCategories[i]);
			}else{
				insertCategory('S',span.innerText,i+1,modifyCategories[i]);
			}
		}else{
			const span = modifyCategories[i].querySelector("span");
			const idx = modifyCategories[i].classList.item(2);
			if(modifyCategories[i].classList.contains("main-category")){
				updateCategory(idx,'B',span.innerText,i+1);
			}else{
				updateCategory(idx,'S',span.innerText,i+1);
			}
			for (var j=0;j<arr.length;j++){
				if((modifyCategories[i].classList.item(2)+"")===(arr[j]+"")){
					arr.splice(j,1);
					j--;
				}
			}
		}
	}
	for(var i=0;i<arr.length;i++){
		deleteCategory(arr[i]);
	}
	end();
});

function btnview(category){
    const buttons = category.querySelector(".buttons");
    category.addEventListener("mouseover",function(){
        buttons.classList.remove(CLASSNAME_HIDDEN);
    });
    category.addEventListener("mouseout",function(){
        buttons.classList.add(CLASSNAME_HIDDEN);
    });
}

function createCategory(){
    const li = document.createElement("li");
    li.classList.add("bundle-group");
    li.innerHTML = 
    `<div class="category main-category">
        <input type="text">
        <div class="buttons">
        <button class="cancelBtn" type="button">취소</button>
        <button class="confirmBtn" type="button" disabled ="true">확인</button>
        </div>
    </div>`;
    categories.append(li);
    const cancelBtn = li.querySelector(".cancelBtn");
    const confirmBtn = li.querySelector(".confirmBtn");
    const text = li.querySelector("input");
    
    text.focus();
    text.addEventListener("keyup",function(){
        if(text.value===""){
            confirmBtn.disabled="true";
        }else{
            confirmBtn.removeAttribute("disabled");
            if(window.event.keyCode==13){
                makeMain(li,text);
            }
        }
    });
    cancelBtn.addEventListener("click",function(){
        li.remove();
    });
    confirmBtn.addEventListener("click",function(){
        makeMain(li,text);
    });
    sort();
}

function mainBtn(category){
    const listImg = category.querySelector(".list-img");
    const addSubBtn = category.querySelector(".addBtn");
    const updateBtn = category.querySelector(".updateBtn");
    const removeBtn = category.querySelector(".removeBtn");
    const parent = category.parentNode;
    const subList = parent.querySelector("ul");

    listImg.addEventListener("click",function(){
        category.classList.toggle("sub-hidden");
        listImg.classList.toggle("rotateimg90");
        $(subList).slideToggle();
    });
	updateBtn.addEventListener("click",function(){
		const span = category.querySelector("span");
		category.innerHTML = `
        <input type="text" value="${span.innerText}">
        <div class="buttons">
            <button class="cancelBtn" type="button">취소</button>
            <button class="confirmBtn" type="button" disabled ="true">확인</button>
        </div>`;
		const cancelBtn = category.querySelector(".cancelBtn");
		const confirmBtn = category.querySelector(".confirmBtn");
		const text = category.querySelector("input");
		text.focus();
        text.addEventListener("keyup",function(){
            if(text.value===""){
                confirmBtn.disabled="true";
            }else{
                confirmBtn.removeAttribute("disabled");
                if(window.event.keyCode==13){
                    updateMain(category,text);
                }
            }
        });
		cancelBtn.addEventListener("click",function(){
			text.value = span.innerText;
			updateMain(category,text);
		});
		confirmBtn.addEventListener("click",function(){
			updateMain(category,text);
		});
	});
    addSubBtn.addEventListener("click",function(event){
        const ul = event.target.parentNode.parentNode.parentNode.querySelector("ul");
        const li = document.createElement("li");
        li.classList.add("category");
        li.classList.add("sub-category");
        li.innerHTML = `
        <input type="text">
        <div class="buttons">
            <button class="cancelBtn" type="button">취소</button>
            <button class="confirmBtn" type="button" disabled ="true">확인</button>
        </div>`;
        ul.append(li);
        const cancelBtn = li.querySelector(".cancelBtn");
        const confirmBtn = li.querySelector(".confirmBtn");
        const text = li.querySelector("input");
        text.focus();
        text.addEventListener("keyup",function(){
            if(text.value===""){
                confirmBtn.disabled="true";
            }else{
                confirmBtn.removeAttribute("disabled");
                if(window.event.keyCode==13){
                    makeSub(li,text);
                }
            }
        });
        cancelBtn.addEventListener("click",function(){
            li.remove();
        });
        confirmBtn.addEventListener("click",function(){
            makeSub(li);
        });
    });
    removeBtn.addEventListener("click",function(event){
        event.target.parentNode.parentNode.parentNode.remove();
    });
}

sort();
function sort(){
    $(".sort").sortable({
        cursor: "move",
        handle: ".drag-img"
    });
    $(".sort").disableSelection();
}

function makeMain(li,text){
    li.innerHTML = 
    `<div class="category main-category">
        <img class="list-img" src="../img/list.jpg">
        <img class="drag-img" src="../img/drag.jpg">
        <span>${text.value}</span>
        <div class="buttons hidden">
            <button class="addBtn" type="button">추가</button>
            <button class="updateBtn" type="button">수정</button>
            <button class="removeBtn" type="button">삭제</button>
        </div>
    </div>
    <ul class="sort">
    </ul>`;
    const category = li.querySelector(".category");
    btnview(category);
    mainBtn(category);
}

function updateMain(category,text){
	category.innerHTML = `
	<img class="list-img" src="../img/list.jpg">
    <img class="drag-img" src="../img/drag.jpg">
    <span>${text.value}</span>
    <div class="buttons hidden">
        <button class="addBtn" type="button">추가</button>
		<button class="updateBtn" type="button">수정</button>
        <button class="removeBtn" type="button">삭제</button>
    </div>`;
    btnview(category);
    mainBtn(category);
}

function makeSub(li,text){
    li.innerHTML = `
    <img class="drag-img" src="../img/drag.jpg">
    <span>${text.value}</span>
    <div class="buttons hidden">
        <button class="updateBtn" type="button">수정</button>
        <button class="removeBtn" type="button">삭제</button>
    </div>`;
    btnview(li);
    subBtn(li);
    sort();
}

function subBtn(category){
    const updateBtn = category.querySelector(".updateBtn");
    const removeBtn = category.querySelector(".removeBtn");

    removeBtn.addEventListener("click",function(){
        category.remove();
    });
	updateBtn.addEventListener("click",function(){
		const span = category.querySelector("span");
		category.innerHTML = `
        <input type="text" value="${span.innerText}">
        <div class="buttons">
            <button class="cancelBtn" type="button">취소</button>
            <button class="confirmBtn" type="button" disabled ="true">확인</button>
        </div>`;
		const cancelBtn = category.querySelector(".cancelBtn");
		const confirmBtn = category.querySelector(".confirmBtn");
		const text = category.querySelector("input");
		text.focus();
        text.addEventListener("keyup",function(){
            if(text.value===""){
                confirmBtn.disabled="true";
            }else{
                confirmBtn.removeAttribute("disabled");
                if(window.event.keyCode==13){
                    makeSub(category,text);
                }
            }
        });
		cancelBtn.addEventListener("click",function(){
			text.value = span.innerText;
			makeSub(category,text);
		});
		confirmBtn.addEventListener("click",function(){
			makeSub(category,text);
		});
	});
}

function insertCategory(BS,value,order,category){
	$.ajax({
		type:'POST',
		url:'/Category',
		data: {
			cmd : 'insert',
			value : value,
			order : order,
			BS : BS
		},	
		dataType: 'json',
		success: function(res){
			if(res.sw=="ok"){
				category.classList.add(res.idx);
			}
		},
		error: function(request,status,error){
            alert('code:'+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
	});
}

function deleteCategory(idx){
	$.ajax({
		type:'POST',
		url:'/Category',
		data: {
			cmd : 'delete',
			idx : idx
		},	
		dataType: 'json',
		success: function(res){
		},
		error: function(request,status,error){
            alert('code:'+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
	});
}

function updateCategory(idx,BS,value,order){
	$.ajax({
		type:'POST',
		url:'/Category',
		data: {
			cmd : 'update',
			idx : idx,
			value : value,
			order : order,
			BS : BS
		},	
		dataType: 'json',
		success: function(res){
		},
		error: function(request,status,error){
            alert('code:'+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
	});
}

function start(){
	$.ajax({
		type:'POST',
		url:'/Category',
		data: {
			cmd : 'start'
		},	
		success: function(res){
			if(res.sw !=="ok"){
				alert("수정에 문제가 생겼습니다.");			
			}
		},
		error: function(request,status,error){
            alert('code:'+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        },
		dataType : 'json'
	});
}

function end(){
	$.ajax({
		type:'POST',
		url:'/Category',
		data: {
			cmd : 'end'
		},	
		success: function(res){
			if(res.sw==="ok"){
				alert("수정이 완료되었습니다.");			
			}else{
				alert("수정에 실패하였습니다.");
			}
		},
		error: function(request,status,error){
            alert('code:'+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        },
		dataType : 'json'
	});
}
