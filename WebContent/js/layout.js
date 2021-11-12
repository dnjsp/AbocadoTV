const flag = document.querySelector(".flag").value;

$(document).ready(function(){
    $("header").load("../page/header.jsp");
});
$(document).ready(function(){
    $("nav").load("../page/nav.html");
});
$(document).ready(function(){
    $("footer").load("../page/footer.html");
});

function layout(){
	const loginBtn = document.querySelector(".login");
    const toggleBtn = document.querySelectorAll(".toggle");
    const broadcast = document.querySelector(".broadcast");
    const onair = document.querySelector(".onair");
    const login = document.querySelector(".login");
    const logo = document.querySelector('.logo');
    const arrowbox = document.querySelector('.arrow-box');

    const images = [
        '../img/logo1.jpg',
        '../img/logo2.jpg'
    ];
    const choiceImg = images[Math.floor(Math.random()*images.length)];
   
    toggleBtn.forEach(btn =>{
        
        btn.addEventListener("click",function(){
            const navigation = document.querySelector('.nav-list');
            $(navigation).toggle("slide",500);
        });
    });
    
    logo.src = choiceImg;
    if(flag == 'true'){
		console.log("aa");
	    login.src = "../img/logout.jpg";
	    onair.classList.remove("hidden");
	    broadcast.classList.remove("hidden");
        arrowbox.classList.remove("hidden");
	   	loginBtn.addEventListener("click", function(){
		    location.replace("/Main?cmd=logout");
		});

    }else{    
		loginBtn.addEventListener("click", function(){
			console.log("aa");
		    location.href="/Main?cmd=login";
		});    
    }
}
    
setTimeout(layout,200);
