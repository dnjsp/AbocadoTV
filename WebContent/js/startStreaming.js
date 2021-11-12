const startForm = document.querySelector(".start-form");
const streamTitle = document.querySelector(".stream-title");
const streamCategory = document.querySelector(".stream-category");
const streamContent = document.querySelector(".stream-content");
const submitBtn = document.querySelector(".submit-btn");
const mike = document.querySelector("#mike");
let videoEl;

const player = videojs("webcam-id",{
	controls : true,
	playsinline: true,
	muted : true,
	preload : "metadata",
});

submitBtn.addEventListener("click",function(){
    if(streamCategory.value===""){
        streamCategory.focus();
        alert("카테고리를 입력해주세요");
        return;
    }
	webSocket.send(createMessage('create','show5116@naver.com','제목'));
	window.open("http://localhost:5000/");
	startForm.submit();	
});

setTimeout(function(){
	videoEl = document.getElementById("webcam-id_html5_api");
	const startBtn = document.querySelector(".vjs-big-play-button");
	const startBtn2 = document.querySelector(".vjs-poster");
	const stopBtn = document.querySelector(".vjs-play-control");
	const volume = document.querySelector(".vjs-volume-bar");
	startBtn.addEventListener("click",startWebcam);
	startBtn2.addEventListener("click",startWebcam);
	stopBtn.addEventListener("click",function(){
		if(!stopBtn.classList.contains("vjs-playing")){
			startWebcam();
			audioCtx.resume();
		}else{
			audioCtx.suspend();
		}
	});
	videoEl.addEventListener("click",function(){
		if(!stopBtn.classList.contains("vjs-playing")){
			startWebcam();
			audioCtx.resume();
		}else{
			audioCtx.suspend();
		}
	});
	document.addEventListener("mouseup",function(){
		setTimeout(function(){
			if(volume.getAttribute('aria-valuenow')===0){
				audioCtx.suspend();			
				return;
			}
			gainNode.gain.value = volume.getAttribute('aria-valuenow')/100-1	;			
		},100);
	});
},200);

function startWebcam(){
    if(navigator.mediaDevices.getUserMedia){
        navigator.mediaDevices
        .getUserMedia({video:true})
        .then(stream =>{
			if(window.URL){
     	       	videoEl.srcObject = stream;				
				//setTimeout(function(){
				//	window.URL.createObjectURL(stream);
				//},1100);
			}
        })
        .catch(err =>{
            console.log("카메라를 못가져왔다,,", err);
        });
    }
	startSound();
}

const stopWebcam = () => {
    const stream = videoEl.srcObject;
    const tracks = stream.getTracks();

    for(let i = 0; i < tracks.length; i++){
        const track = tracks[i];
        track.stop();
    }
    videoEl.srcObject = null;
};

const audioCtx = new(window.AudioContext || window.webkitAudioContext)();
const gainNode = audioCtx.createGain();
const analyser = audioCtx.createAnalyser();

function makeSound(stream){
	const source = audioCtx.createMediaStreamSource(stream);
	
	const gainConnected = source.connect(gainNode);
	gainConnected.connect(audioCtx.destination);
	source.connect(analyser);
	analyser.connect(audioCtx.destination);
}

function startSound(){
	if(navigator.mediaDevices){
		const constraints = {
			audio : {
				echoCancellation: false,
				noiseSuppression: false,
				autoGainControl: false
			}
		};
		
		navigator.mediaDevices.getUserMedia(constraints)
		.then(stream => {
			//const mediaRecorder = new MediaRecorder(stream);
			audioCtx.resume();
			makeSound(stream);
		});
	}	
}