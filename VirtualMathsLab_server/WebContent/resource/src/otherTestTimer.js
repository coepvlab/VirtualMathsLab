(function(OT, OTM, AM) {
	
OTM.otherTestTimer = function(duration, topicId) {
	
//	alert(topicId);
	const FULL_DASH_ARRAY = 283;
	const WARNING_THRESHOLD = 10;
	const ALERT_THRESHOLD = 5;

	const COLOR_CODES = {
	  info: {
	    color: "green"
	  },
	  warning: {
	    color: "orange",
	    threshold: WARNING_THRESHOLD
	  },
	  alert: {
	    color: "red",
	    threshold: ALERT_THRESHOLD
	  }
	};

	const TIME_LIMIT = duration;
	let timePassed = 0;
	let timeLeft = TIME_LIMIT;
	let timerInterval = null;
	let remainingPathColor = COLOR_CODES.info.color;

	document.getElementById("praTestTimer").innerHTML = `
	<div class="base-timer">
	  <svg class="base-timer__svg" viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
	    <g class="base-timer__circle">
	      <circle class="base-timer__path-elapsed" cx="50" cy="50" r="45"></circle>
	      <path
	        id="base-timer-path-remaining"
	        stroke-dasharray="283"
	        class="base-timer__path-remaining ${remainingPathColor}"
	        d="
	          M 50, 50
	          m -45, 0
	          a 45,45 0 1,0 90,0
	          a 45,45 0 1,0 -90,0
	        "
	      ></path>
	    </g>
	  </svg>
	  <span id="base-timer-label" class="base-timer__label">${formatTime(
	    timeLeft
	  )}</span>
	</div>
	`;

	startTimer();

	function onTimesUp() {
		 $('#alert').show();
		 setTimeout(function () {
	            // Closing the alert
	            $('#alert').alert('close');
	           
	            OT.finishTest(testData.TISID,"timeout");
	        }, 5000);
		 
		OT.testInstanceVOCall(OT.testInstanceVOArr);
		OT.subTestFlag = true;
		
		 timePassed = timePassed += 1;
		    timeLeft = TIME_LIMIT - timePassed;
		    if (timeLeft == -1) {
		    	timeLeft = 0;
		    	OT.CT = timeLeft+"-"+topicId; // this is to save current time of the test
			}else{
				OT.CT = timeLeft+"-"+topicId; // this is to save current time of the test
			}
		
		
		clearInterval(timerInterval);
		OT.saveCurrentTime();
//		alert("Your time is up. Your test has finished.");
//		setTimeout(function () {
//			
//			  }, 1000);
	}
	
	OTM.onFinishTest = function() {
//		alert("clear Interval");
		clearInterval(timerInterval);
	};

	function startTimer() {
	  timerInterval = setInterval(() => {
	    timePassed = timePassed += 1;
	    timeLeft = TIME_LIMIT - timePassed;
	    
//		OT.CT = timeLeft+"-"+topicId; // this is to save current time of the test
	    if (timeLeft == -1) {
	    	timeLeft = 0;
	    	OT.CT = timeLeft+"-"+topicId; // this is to save current time of the test
		}else{
			OT.CT = timeLeft+"-"+topicId; // this is to save current time of the test
		}
//	    console.log("In - "+OT.CT);
	    document.getElementById("base-timer-label").innerHTML = formatTime(
	      timeLeft
	    );
	    setCircleDasharray();
	    setRemainingPathColor(timeLeft);

	    if (timeLeft === 0) {
	      onTimesUp();
	    }
//	    OT.saveCurrentTime();
	  }, 1000);
	}

	function formatTime(time) {
	  const minutes = Math.floor(time / 60);
	  let seconds = time % 60;

	  if (seconds < 10) {
	    seconds = `0${seconds}`;
	  }

	  return `${minutes}:${seconds}`;
	}

	function setRemainingPathColor(timeLeft) {
	  const { alert, warning, info } = COLOR_CODES;
	  if (timeLeft <= alert.threshold) {
	    document
	      .getElementById("base-timer-path-remaining")
	      .classList.remove(warning.color);
	    document
	      .getElementById("base-timer-path-remaining")
	      .classList.add(alert.color);
	  } else if (timeLeft <= warning.threshold) {
	    document
	      .getElementById("base-timer-path-remaining")
	      .classList.remove(info.color);
	    document
	      .getElementById("base-timer-path-remaining")
	      .classList.add(warning.color);
	  }
	}

	function calculateTimeFraction() {
	  const rawTimeFraction = timeLeft / TIME_LIMIT;
	  return rawTimeFraction - (1 / TIME_LIMIT) * (1 - rawTimeFraction);
	}

	function setCircleDasharray() {
	  const circleDasharray = `${(
	    calculateTimeFraction() * FULL_DASH_ARRAY
	  ).toFixed(0)} 283`;
	  document
	    .getElementById("base-timer-path-remaining")
	    .setAttribute("stroke-dasharray", circleDasharray);
	}

}





})(com.coep.test.otherTest, com.coep.test.otherTestTimer, com.coep.test.AlertMessage);