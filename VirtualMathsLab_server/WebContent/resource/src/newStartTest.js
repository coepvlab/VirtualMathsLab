(function(AH, ST, AP) {

//	 ST.baseURL = "http://localhost:8080/VirtualMathsLab/";
//	 ST.baseURL = "https://portal.coepvlab.ac.in/VirtualMathsLab/"

	ST.interval = 0;
	ST.testInstanceVOArr = [];
	ST.tagged = false;
	ST.TISID = 0;
	ST.secFlag = false;
	ST.subTestFlag = false;
	ST.splitter = "_";
	ST.psySecId = 47;
	ST.CT =  ""; // current time
	ST.index = [];
	
	ST.temp1 = '';
	ST.temp2 = '';
	
	ST.temp11 = '';
	ST.temp21 = '';
	
	ST.getYesNoButtonOnDialog = function() {
		var text = '<button class="btn btn-success" type="button" id="cfYes">Yes</button><button class="btn btn-danger"  type="button" id="cfNo">No</button>';
		return text;
	};

	ST.getCloseButtonOnDialog = function() {
		var text = '<button onClick="com.coep.vlab.userProfile.clearDialogContent();"  type="button" class="btn btn-danger">Close</button>';
		return text;
	};

	ST.clearDialogContent = function() {
		$('#myModalLabel').html('');
		$('#dlgBody').html('');
		$('#dlgFoot').html('');
		$('#testdialog').modal('hide');
	};

	ST.clearConfirmDialogContent = function() {
		$('#cModalLabel').html('');
		$('#cdlgBody').html('');
		$('#cdlgFoot').html('');
		$('#cdialog').modal('hide');
	};

	ST.startTestNavFunction = function(testData, sectionId, firstName) {
		console.log(testData);
		var sectionObj = testData.secObj;
		var sId = sectionId;
		var secObjLen = Object.keys(sectionObj).length;

		var sessionEndTime = 0;

		if(testData["RESUME"] == false){
			for (var i = 0; i < secObjLen; i++) {
				sessionEndTime = sessionEndTime + testData.data["time" + testData.sections[i]];
			}
		}else{
			for (var i = 0; i < secObjLen; i++) {
				if(testData.CURRSEC <= testData.sections[i]){
					if(testData.data["RTime" + testData.sections[i]] != undefined){
						sessionEndTime = sessionEndTime + testData.data["RTime" + testData.sections[i]];
					}
				};
			}
		}

		var navTestContenthtm = '';
		navTestContenthtm += '<div class="container-fluid navbar-base-padding height-5">'
				+ '<div class="navbar-header r4_5-padding">'
				+ ' <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">'
				+ '<span class="sr-only">Toggle navigation</span>'
				+ ' <span class="icon-bar"></span>'
				+ ' <span class="icon-bar"></span>'
				+ ' <span class="icon-bar"></span>'
				+ '</button>'
				+ '<a class="navbar-brand logo-atnavbar-padding" href="#"><img class="img-responsive" src="resource/images/IntegreT_logo.png"></a>'
				+ '<a class="navbar-brand first-text-after-logo " href="#">Integrated<br>Technologies (IT)<br>Olympiad</a>'
				+ '</div>'
				+ ' <div id="navbar" class="navbar-collapse collapse height-4_8">'
				+ ' <ul class="nav navbar-nav navbar-left cust1-margin">'
				+ ' <li class="white-color t1_7-padding">'
				+ '    <b id="testName"> '+testData.DEPT+'</b>'
				+ '</li>'
				+ ' </ul>'
				+ '<ul class="nav navbar-nav navbar-left start-end-session-ul l0-r30-padding">'
				+ '<li class="start-end-session-li">' 
				+ '<b>Test Start</b>'
				+ '<span class="l1_3-padding"><h4 id="testStartTime" style="color:#a3fffb ">'
				+ new Date(new Date().getTime()).toLocaleTimeString()
				+ '</h4></span>'
				+ '</li>'
				+ '<br>'
				+ ' <li class="start-end-session-li">'
				+ '   <b>Test End</b>'
				+ ' <span class="l2-padding"><h4 id="testEndTime" style="color:#a3fffb; margin-left:-7px ">'
				+ new Date(new Date().getTime() + sessionEndTime * 60 * 1000).toLocaleTimeString()
				+ '</h4></span>'
				+ ' </li>'
				+ '</ul>'
				+ '<ul class="nav navbar-nav navbar-left session-watch-ul l0-r30-padding">'
				+ ' <li>'
				+ '<span class="icon-clock white-color fontsize-2em timer-elem"></span>'
				+ ' <h1 id="timeClass" class="white-color time-counter timer-elem">'
//				+ testData.data["time" + sectionId]
				
				if(testData.data["SEC" + sId][0].QUES[0].GANSID == undefined){
					navTestContenthtm +=  testData.data["time" + sectionId]
				}
		
		navTestContenthtm +=  '</h1>'
				+ ' <h5 id="timeClass2" class="white-color minutes-rem timer-elem">Minutes <br>remaining </h5>'
				+ '</li>'
				+ '</ul>   '
				+ '<div class="pull-right hidden-xs hidden-sm profile-descri-outer-block t1_7-padding">'
				+ '  <h3 class="white-color">Hi </h3> '
				+ firstName
				+ '</h3> '
				+ '&nbsp;&nbsp;<span id ="logout" class="glyphicon glyphicon-menu-down dropdown-toggle pointer white-color" data-toggle="dropdown" aria-expanded="false" style= "display:none;"></span>'
				+ '<ul class="dropdown-menu right-10" role="menu" aria-labelledby="dropdownMenu1">'
				+ ' <li id="logoutlink" class="dd-menu" role="presentation"><a role="menuitem" tabindex="-1" href="j_spring_security_logout"><span class="glyphicon glyphicon-log-out "></span>&nbsp;&nbsp;Log Out</a></li>'
				+ ' </ul>'
				+ '</div>'
				+ '</div>'
				+ '</div>';

		$("#navContent").html('');
		$("#navContent").html(navTestContenthtm);

		// /// Function to start timer when exam start
		ST.startTimer = function(duration, display) {
			
			var timer = duration, minutes, seconds;
			minutes = 0;
			ST.interval = setInterval(function() {
				minutes = parseInt(timer / 60, 10);
				seconds = parseInt(timer % 60, 10);

				minutes = minutes < 10 ? "0" + minutes : minutes;
				seconds = seconds < 10 ? "0" + seconds : seconds;
				if(minutes > 4){
					$("#timeClass").removeClass('tab blink');
					$("#timeClass2").removeClass('tab blink');
				}
				if(minutes == 4 || minutes < 4){
					$("#timeClass").addClass('tab blink');
					$("#timeClass2").addClass('tab blink');
					display.textContent = minutes + ":" + seconds;
					
					var time = (parseInt(minutes) * 60) + parseInt(seconds);
					ST.CT = time+"-"+sectionId;
					
					if(seconds > 50){
						showToast.show('Hurry up!!! section time is running out..',false);
					}
					
				}else{
					display.textContent = minutes + ":" + seconds;
					var time = (parseInt(minutes) * 60) + parseInt(seconds);
					ST.CT = time+"-"+sectionId;
				}
				
				
				

				if (--timer < 0) {
					timer = 0;
					showToast.show('Your section time is up.', true);
					if (secObjLen == 1) {

						ST.testInstanceVOCall(ST.testInstanceVOArr);
						ST.subTestFlag = true;
						ST.finishTest(testData.TISID,"timeout");
						
//						alert("Your section time is up.");
//						$("#main-div").html(
//								"<h1>Test submitted succesfully</h1>");
						// window.close();
					} else if (secObjLen != 1) {
						var sectionkeys = [];
						if (sectionObj[secObjLen - 1] == sId) {
							ST.testInstanceVOCall(ST.testInstanceVOArr);
							ST.subTestFlag = true;
							ST.finishTest(testData.TISID,"timeout");
							
//							alert("Your section time is up.");
//							$("#main-div").html(
//									"<h1>Test submitted succesfully</h1>");
						} else {
							ST.testInstanceVOCall(ST.testInstanceVOArr);
							// alert("Your section time is up. Press OK to
							// continue with next section");
							
							
							ST.secFlag = true;
							$("#timeClass").removeClass('tab blink');
							$("#timeClass2").removeClass('tab blink');
							$("#timeClass").addClass('white-color time-counter timer-elem');
							$("#timeClass2").addClass('white-color minutes-rem timer-elem');
							$("#nextSectBtn").click();
						}
					}
				}
				ST.saveCurrentTime();
			}, 1000);
		};
		
		
				
	};
	
	ST.CandidateTest2 = function(testData, secId) {
		ST.CandidateTest(testData, secId);
	}

	ST.CandidateTest = function(testData, secId) {
		
		document.title = "Candidate Test";
		
		ST.TISID = testData.TISID;
		var sectionObj = testData.secObj;

		console.log(testData);
		var sId = secId;

		var preNextIndex1 = [];
		var preNextIndex2 = [];
		var preNextqGrpObj = [];
		var preNextqTxtObj = [];
		var preNextqTxtTempObj = [];
		var nextKey = 0;

		var secObjLen = Object.keys(sectionObj).length;

		var sId = secId;

		var spanClass = 'span.text-align-left.pointer.problem-summary-label.h3-cls.display-block';
		var candidateTestHtm = '';
		candidateTestHtm += '  <div id="candidateTest" class="r3_3-margin">'
			+ '<div id="candidateTestForm" name="candidateTestForm">'
				+ '<div class="row">'
				+ '  <div class="col-sm-12  col-md-12 l0-r0-padding">'
				+ ' <div class="col-sm-8 col-md-9 l0-r0-padding testleftside1">'
				+ '<div class="row">'
				
				
				
		if (testData.data["SEC"+sId][0].QGN != undefined) {
			
			candidateTestHtm += '<div class="h4-cls">'
			+'<ol class="breadcrumb cust-ol-margin">'
			+ '   <li>Group <span id="currQuesGrpId">'
			+ testData.data["SEC"+sId][0].QGN 
			+ '</span></li>'
			+ '   <li class="reference-content" ></li>'
//			+ '   <li class="reference-content" ><u><span class="l0-r30-padding pointer" id="flip" >Hide Reference</span></u></li>'
			+ '  </ol></div>'
//			candidateTestHtm += '<h1 class="secondary-text-color"><span id="currQuesGrpId">'
//					+ testData.data["SEC"+sId][0].QGN + '</span></h1>'
		} else {
			candidateTestHtm += '<div class="h4-cls">'
				+'<ol class="breadcrumb cust-ol-margin">'
				+ '   <li>Group <span id="currQuesGrpId">'
				+ '</span></li>'
				+ '   <li class="reference-content" ></li>'
//				+ '   <li class="reference-content" ><u><span class="l0-r30-padding pointer" id="flip" >Hide Reference</span></u></li>'
				+ '  </ol></div>'
//				'<h1 class="secondary-text-color"><span id="currQuesGrpId">Group</span></h1>'
		}
		candidateTestHtm += ''
//			'<h2 class="prim-text-black-color t15-margin">Reference</h2>'

		if (testData.data["SEC"+sId][0].QGC != undefined) {
			candidateTestHtm += '<div id="reference" class="text-align-justify ref-text groupContent comment more  test-question-font-size" id="currQuesGrpContent_Text"  style="max-height: 400px; overflow-y: auto;">'
					+ testData.data["SEC"+sId][0].QGC
					+ '</div>'

					+ '<div id="audioReference" class="col-sm-12 text-align-justify prim-text-black-color aud-ref-text  groupContent"  style="display: none;" hidden>'
					+ '  With reference to above audio clip answer the following question'
					+ '  <div id="audioSrc" class="t1-b1-padding col-sm-11 l0-r0-padding audiosrc-test">'
					+ '    <audio id="audio" src="resource/test.mp3"></audio>'
					+ '    <div id="buttons" class="col-sm-1">'
					+ '        <span id="play_pause" onclick="PlayPause(this.id)" class="pointer fontsize-54 iconplaypause icon-play play_pause"></span>'
					+ '    </div>'
					+ '     <div id="progress" class="col-sm-offset-1 l30-r0-padding">'
					+ '         <div class="col-sm-1 l0-r0-padding" id="time">0.00</div>'
					+ '         <div class="col-sm-1 l0-r0-padding" id="totalTime" align="right">0.00</div>'
					+ '        <div class="col-sm-5 progressOut pointer" id="progressOut">'
					+ '             <div id="progressIn" class="progressIn pointer">'
					+ '            </div>'
					+ '        </div>'
					+ '     </div>'
					+ '  </div>'
					+ '   </div>'

					+ '  <div id="videoQuestionRef" class="col-sm-12 prim-text-black-color aud-ref-text  groupContent" align="center"  style="display: none;" hidden>'
					+ '  With reference to below video clip answer the following question'
					+ ' <div class="row">'
					+ '<div class="col-sm-12 t1-b1-margin" align="center">' 
					+ '  <video id="videoQuestion" controls=""  class="width-50" >'
					+ '     <source  src="http://www.w3schools.com/html/mov_bbb.mp4" type="video/mp4">'
					+ '     Your browser does not support the video tag.'
					+ '  </video>'
					+ '   </div>'
					+ '  </div>'
					+ '   </div>'

					+ '  <div id="imageQuestionRef" class="col-sm-12 text-align-justify prim-text-black-color aud-ref-text  groupContent autoScrollForImg"  style="display: none;" hidden>'
					+ '   With reference to below Images answer the following question'
					
					+ ' <div id="imageSrc" class="t1-b1-padding col-sm-11 l0-r0-padding audiosrc-test">'
					+ '    <img src="resource/images/dummy_1.jpg" id="imgQues" max-width="75%">'
					+ '  </div>'
					
//					+ '  <div class="row">'
//					+ '  <div class="col-sm-12 t1-b1-margin">'
//					+ '  <div id="carousel-example-generic" class="carousel slide width-50" data-ride="carousel">'
//					+ '   <!-- Wrapper for slides -->'
//					+ '   <div class="carousel-inner">'
//					+ '     <div class="item active">'
//					+ '        <img id="imgQues" src="resource/images/dummy1.jpg" class="img-question">'
//					+ '        <div class="carousel-caption">'
//					+ '             '
//					+ '        </div>'
//					+ '    </div>'

//					+ '    </div>'
//					+ '   </div>'
//					+ '  </div>'
//					+ '  </div>'
					+ '  </div>' 
			
		}else{
			
			 
		
			candidateTestHtm += '<div id="reference" class="text-align-justify ref-text groupContent comment more" id="currQuesGrpContent_Text"  style="max-height: 400px; overflow-y: auto;">'
				
				if(testData.data["SEC"+sId][0].QGC != undefined ){
					candidateTestHtm += ""+ testData.data["SEC"+sId][0].QGC
				}else{
					candidateTestHtm += ""
				}
			candidateTestHtm += '</div>'
				
				+ '<div id="audioReference" class="col-sm-12 text-align-justify prim-text-black-color aud-ref-text  groupContent"  style="display: none;" hidden>'
				+ '  With reference to above audio clip answer the following question'
				+ '  <div id="audioSrc" class="t1-b1-padding col-sm-11 l0-r0-padding audiosrc-test">'
				+ '    <audio id="audio" src="resource/test.mp3"></audio>'
				+ '    <div id="buttons" class="col-sm-1">'
				+ '        <span id="play_pause" onclick="PlayPause(this.id)" class="pointer fontsize-54 iconplaypause icon-play play_pause"></span>'
				+ '    </div>'
				+ '     <div id="progress" class="col-sm-offset-1 l30-r0-padding">'
				+ '         <div class="col-sm-1 l0-r0-padding" id="time">0.00</div>'
				+ '         <div class="col-sm-1 l0-r0-padding" id="totalTime" align="right">0.00</div>'
				+ '        <div class="col-sm-5 progressOut pointer" id="progressOut">'
				+ '             <div id="progressIn" class="progressIn pointer">'
				+ '            </div>'
				+ '        </div>'
				+ '     </div>'
				+ '  </div>'
				+ '   </div>'

				+ '  <div id="videoQuestionRef" class="col-sm-12 prim-text-black-color aud-ref-text  groupContent" align="center"  style="display: none;" hidden>'
				+ '  With reference to below video clip answer the following question'
				+ ' <div class="row">'
				+ '<div class="col-sm-12 t1-b1-margin" align="center">' 
				+ '  <video id="videoQuestion" controls=""  class="width-50" >'
				+ '     <source  src="http://www.w3schools.com/html/mov_bbb.mp4" type="video/mp4">'
				+ '     Your browser does not support the video tag.'
				+ '  </video>'
				+ '   </div>'
				+ '  </div>'
				+ '   </div>'

				+ '  <div id="imageQuestionRef" class="col-sm-12 text-align-justify prim-text-black-color aud-ref-text autoScrollForImg groupContent"  style="display: none; " hidden>'
				+ '   With reference to below Images answer the following question'
				
				+ ' <div id="imageSrc" class="t1-b1-padding col-sm-11 l0-r0-padding audiosrc-test">'
				+ '    <img src="resource/images/dummy_1.jpg" id="imgQues" max-width="75%">'
				+ '  </div>'
				
//				+ '  <div class="row">'
//				+ '  <div class="col-sm-12 t1-b1-margin">'
//				+ '  <div id="carousel-example-generic" class="carousel slide width-50" data-ride="carousel">'
//				+ '   <!-- Wrapper for slides -->'
//				+ '   <div class="carousel-inner">'
//				+ '     <div class="item active">'
//				+ '        <img id="imgQues" src="resource/images/dummy1.jpg" class="img-question">'
//				+ '        <div class="carousel-caption">'
//				+ '             '
//				+ '        </div>'
//				+ '    </div>'

//				+ '    </div>'
//				+ '   </div>'
//				+ '  </div>'
//				+ '  </div>'
				+ '  </div>' 
		}

		candidateTestHtm += '<hr />'
				+ '          </div>'
				+ '          <div class="row">'
				+ '             <div id="quesSection" class="col-sm-12 col-md-12 t20-margin l0-r0-padding">'
				+ '                  <div class="col-sm-3 l0-r0-padding t20-margin">'
									
				+ '                      <h2 class="prim-text-black-color font-size-15">Question #<span id="currQGrp">Q1</span></h2>'
				+ '                  </div>'
				+ '                  <div class="col-sm-9 l30-r0-padding t20-margin">'
				+ '                      <h3>'
				+ '  <span id="tagQues" data-toggle="tooltip" data-placement="top" data-original-title="Tagged question to be answered later" title="" class="icon-flag flag-glyph pointer" onclick="tagThisQues(this.id)"></span>'
//				+ '  <span class="icon-flag flag-glyph pointer" id="tagQues" onclick="tagThisQues(this.id)"></span>'
//				+ '                         <span data-toggle="tooltip" data-placement="right" class="tooltip-css" title="Tagged question to be answered later">'
				+ '<span data-toggle="tooltip" data-placement="top" data-original-title="Tagged question to be answered later" title="">'
				+ '                              <h3 class="cust-clr1 pointer" id="tagQues1" onclick="tagThisQues(this.id)">Tag question</h3>'
				+ '                          </span>'
				+ '                      </h3>'
				+ '                  </div>'
				+ '                  <div class="col-sm-12 l0-r0-padding quest-option-div spaceAfterLabel" style="">'
				+ '                      <div id="currQTxt" class="secondary-text-color test-question-font-size" style="margin-top:5px;margin-bottom:5px;">'
			
				+ ' <div id="loaderQuestions" class="loader" style="display: none;">'
				+ '  <div>'
				+ '     <img src="resource/images/ajax-loader.gif">'
				+ '     <div>loading data.. please wait..</div>'
				+ '  </div>'
				+ ' </div>'
            
            
				+ '                          Which of the following are properties of lightwaves ?'
				+ '                      </div>'
				+ '                      <h3 id="options" class="display-block  options-font-size ">'
				+ ' 		<div class="col-sm-12 t20-margin radio radio-primary ">'   //new line
				+ '<label class="cust1-label ">'
				
//				+ '                          <span class="col-sm-6 l0-r0-padding">'
//				+ '                              <input type="checkbox" name="" value="">Frequency'
//				+ '                          </span>'
				+ '<span class="circle  "></span>'
//				+ '<span class="check "></span>'
				+ '</label>'
				+ '</div>'
				+ '<label class="cust1-label ">'
//				+ '                              <input type="checkbox" name="" value="">Wavelength'
				+ '<span class="circle  "></span>'
//				+ '<span class="check "></span>'
				+ '</label>'
				+ '</div>'
				+ '<label class="cust1-label ">'
//				+ '                              <input type="checkbox" name="" value="">Weight'
				+ '<span class="circle  "></span>'
//				+ '<span class="check "></span>'
				+ '</label>'
				+ '</div>'
				+ '<label class="cust1-label ">'
//				+ '                              <input type="checkbox" name="" value="">Viscosity'
				+ '<span class="circle  "></span>'
//				+ '<span class="check "></span>'
				+ '</label>'
				+ '</div>'/// new line end
//				+ '          </div>' 
				+ '                      </h3>'
				+ '                  </div>'
//				+ '              </div>'
//				+ '          </div>'
				
				
//				+ '          <div class="row">'
//				+ '              <div class="col-sm-9 col-sm-offset-3 t8-margin">'
//				+ '                  <div id="switchtoPrevQuesSpan" onclick="com.coep.test.student.switchtoPrevQues()" class="col-sm-6 h3-cls secondary-text-color pointer prev-quest"  style = "display:none">'
//				+ '                      <div class="icon-left-navigation "></div>'
//				+ '                      Previous<br> Question'
//				+ '                  </div>'
//				+ '                  <div id="switchtoNextQuesSpan" onclick="com.coep.test.student.switchtoNextQues()" class="pull-right h3-cls col-sm-6 secondary-text-color text-align-right pointer next-quest">'
//				+ '                      Next<div class="icon-right-navigation "></div>'
//				+ '                      <br> Question'
//				+ '                  </div>'
//				+ '              </div>'
//				+ '          </div>'
//				+ '      </div>'
				
				+ '     <div class="col-sm-4 col-md-3 test-sect">'
				+ '<div  class="col-sm-12 custom-bg height-65">'
				+ ' <span id="sectionName pointer"><h1 class="problem-summary-label font-size-20">'
				+ sectionObj[sId]
				+ '</h1></span>&nbsp; &nbsp;'

		candidateTestHtm += '</div>'
				+ ' <div class="col-sm-12 sect-quest-div l0-r0-padding">'
				
		for (var i = 0; i < testData.data["SEC"+sId].length; i++) {
			if (testData.data["SEC"+sId][i].QUES.length > 1) {

				for (var j = 0; j < testData.data["SEC" + sId][i].QUES.length; j++) {
					preNextIndex1.push(i);
					preNextIndex2.push(j);
					preNextqGrpObj.push('qGrp'+ (i + 1)+ '_'+ (j + 1));
					preNextqTxtObj.push('qTxt'+ (i + 1)+ '_'+ (j + 1));
					preNextqTxtTempObj.push('qTxtTemp'+ (i + 1)+ '_'+ (j + 1));
					
//					col-sm-12 sect-quest pointer 
					candidateTestHtm += '<div class="col-sm-12 sect-quest pointer test-question-font-size qTxtG1Q1 curr-quest sect-qGrp'+(i+1)+'_'+(j+1)+'" tabindex="qGrp'+(i+1)+'_'+(j+1)+'" onclick="switchtoThisQues('
							+ i
							+ ','
							+ j
							+ ',this,\'qGrp'
							+ (i + 1)
							+ '_'
							+ (j + 1)
							+ '\',\'qTxt'
							+ (i + 1)
							+ '_'
							+ (j + 1)
							+ '\',\'qTxtTemp'
							+ (i + 1)
							+ '_'
							+ (j + 1)
							+ '\')">'
							+ ' <div class="col-sm-6 col-md-6 test-question-font-size">'
							+ ' <h3 class="prim-black" id="qGrp'
							+ (i + 1)
							+ '_'
							+ (j + 1)
							+ '">Q'
							+ (i + 1)
							+ '.'
							+ (j + 1)
							+ '</h3></div>'
							+ '<div class="col-sm-6 col-md-6 l0-r0-padding test-question-font-size">'
							+ '<h3 id="qTxt'
							+ (i + 1)
							+ '_'
							+ (j + 1)
							+ '" class="prim-text-black-color" hidden>'
							+ testData.data["SEC"+sId][i].QUES[j].QC
							+ '</h3>'
							+ '<h3 id="qTxtTemp'
							+ (i + 1)
							+ '_'
							+ (j + 1)
							
							//TODO
							if(testData.data["SEC" + sId][i].QUES[j].GANSID != undefined){
								if(testData.data["SEC" + sId][i].QUES[j].ANSTYP == 1 ){
	//							
									for (var q = 0; q < testData.data["SEC" + sId][i].QUES[j].ANS.length; q++) {
									
									var index = testData.data["SEC" + sId][i].QUES[j].ANS[q].ANSID;
									
									for (var r = 0; r < testData.data["SEC" + sId][i].QUES[j].ANS.length; r++) {
										if(index == testData.data["SEC" + sId][i].QUES[j].GANSID[0]["GANSID"+(r+1)]){
											ST.temp11 = '';
											ST.temp11 = '" class="green-color answer-h3 test-question-font-size">'
												+ 'Answered'
											
												break;
	//										console.log(r+1);
										}else{
											ST.temp21 = '';
											ST.temp21 =  '" class="red-color answer-h3 test-question-font-size">'
												+ 'Unanswered'
										}
									}
									
								}
									if(ST.temp11 == ''){
										candidateTestHtm += '" class="red-color answer-h3 test-question-font-size">'
											+ 'Unanswered'
									}else{
										candidateTestHtm += '" class="green-color answer-h3 test-question-font-size">'
											+ 'Answered'
									}
									ST.temp11 = '';	
									ST.temp21 = '';	
								}else{
									if(testData.data["SEC" + sId][i].QUES[j].GANSID == undefined ||  testData.data["SEC" + sId][i].QUES[j].GANSID == 99999999){
										candidateTestHtm += '" class="red-color answer-h3 test-question-font-size">'
										+ 'Unanswered'
	//									console.log("if");
									}else{
										candidateTestHtm += '" class="green-color answer-h3 test-question-font-size">'
										+ 'Answered'
										console.log("else");
									}
								
								
							}
				}else{
					if(testData.data["SEC" + sId][i].QUES[j].GANSID == undefined ||  testData.data["SEC" + sId][i].QUES[j].GANSID == 99999999){
						candidateTestHtm += '" class="red-color answer-h3 test-question-font-size">'
						+ 'Unanswered'
//						console.log("if");
					}else{
						candidateTestHtm += '" class="green-color answer-h3 test-question-font-size">'
						+ 'Answered'
						console.log("else");
					}
				}
					
					
					
								
							
					candidateTestHtm += '</h3></div>'
//							+ '<div class="col-sm-2 flag-div">'
//		                    + '<span id="flag_spanQ'+(i + 1)
//							+ (j + 1)+'" class=""></span>'
////		                    + '</div>'
//							+ '</div>' 
							+ '</div>'
				}
			} else {
//				for (var j = 0; j < testData.data["SEC" + sId][i].QUES.length; j++) {
				preNextIndex1.push(i);
				preNextIndex2.push(0);
				preNextqGrpObj.push('qGrp'+ i);
				preNextqTxtObj.push('qTxt'+ (i + 1));
				preNextqTxtTempObj.push('qTxtTemp'+ (i + 1));
				
				candidateTestHtm += '<div class="col-sm-12 sect-quest pointer test-question-font-size qTxtG1Q1 curr-quest sect-qGrp'+i+'" tabindex="qGrp'+i+'" onclick="switchtoThisQues('
						+ i
						+ ',0,this,\'qGrp'
						+ i
						+ '\',\'qTxt'
						+ (i + 1)
						+ '\',\'qTxtTemp'
						+ (i + 1)
						+ '\')">'
						+ '<div class="col-sm-6 col-md-6 test-question-font-size">'
						+ '<h3 class="prim-black" id="qGrp'
						+ i
						+ '">Q'
						+ (i + 1)
						+ '</h3></div>'
						+ '<div class="col-sm-6 col-md-6 l0-r0-padding test-question-font-size">'
						+ '<h3 id="qTxt'
						+ (i + 1)
						+ '" class="prim-text-black-color" hidden>'
						+ testData.data["SEC"+sId][i].QUES[0].QC
						+ '</h3>'
						+ '</h3>'
						+ '<h3 id="qTxtTemp'
						+ (i + 1)
						
//						+'" class="red-color answer-h3 test-question-font-size" >'
//						+ 'Unanswered'
						if(testData.data["SEC" + sId][i].QUES[0].GANSID == undefined){
							candidateTestHtm += '" class="red-color answer-h3 test-question-font-size">'
							+ 'Unanswered'
//							console.log("if1");
						}else{
							
							//TODO
//							
							
							if(testData.data["SEC" + sId][i].QUES[0].ANSTYP == 1 ){ 
								
							for (var p = 0; p < testData.data["SEC" + sId][i].QUES.length; p++) {
								
								for (var q = 0; q < testData.data["SEC" + sId][i].QUES[p].ANS.length; q++) {
								
								var index = testData.data["SEC" + sId][i].QUES[p].ANS[q].ANSID;
								
								for (var r = 0; r < testData.data["SEC" + sId][i].QUES[p].ANS.length; r++) {
									if(index == testData.data["SEC" + sId][i].QUES[p].GANSID[0]["GANSID"+(r+1)]){
										ST.temp1 = '';
										ST.temp1 = '" class="green-color answer-h3 test-question-font-size">'
											+ 'Answered'
										
											break;
										console.log(r+1);
									}else{
										ST.temp2 = '';
										ST.temp2 =  '" class="red-color answer-h3 test-question-font-size">'
											+ 'Unanswered'
									}
								}
								
							}
								if(ST.temp1 == ''){
									candidateTestHtm += '" class="red-color answer-h3 test-question-font-size">'
										+ 'Unanswered'
										
								}else{
									candidateTestHtm += '" class="green-color answer-h3 test-question-font-size">'
										+ 'Answered'
								}
								ST.temp1 = '';	
								ST.temp2 = '';	
							}
							}else{
								if(testData.data["SEC" + sId][i].QUES[0].GANSID == undefined || testData.data["SEC" + sId][i].QUES[0].GANSID == 99999999){
									candidateTestHtm += '" class="red-color answer-h3 test-question-font-size">'
									+ 'Unanswered'
									console.log("if");
								}else{
									candidateTestHtm += '" class="green-color answer-h3 test-question-font-size">'
									+ 'Answered'
								}
							}
							
//							candidateTestHtm += temp;
//							console.log("else2");
						}
				
				
//						for (var j = 0; j < testData.data["SEC" + sId][i].QUES.length; j++) {
//						if(testData.data["SEC" + sId][i].QUES[j].GANSID == undefined || testData.data["SEC" + sId][i].QUES[j].GANSID == 99999999){
//							candidateTestHtm +=  '" class="red-color answer-h3 test-question-font-size" >'
//						+ 'Unanswered'
//						break;
//						}else{
//							candidateTestHtm +=  '" class="green-color answer-h3 test-question-font-size" >'
//							+ 'Answered'
//							break;
//						}
//						}
				
				
				candidateTestHtm +=  '</h3></div>'
//						+ '<div class="col-sm-2 flag-div">'
//	                    + '<span id="flag_spanQ'+(i + 1)
//						+'" class=""></span>'
////	                    + '</div>'
//						+ '</div>' 
						+ '</div>'
//			}
			}
		}
		 
		candidateTestHtm += '</div>'
			if(Object.keys(sectionObj)[secObjLen - 1] == secId){
				candidateTestHtm +=  '<div class="col-sm-2  pointer" align="right" style="margin: 0 auto;">'
				+ '<a title="FINISH TEST" style=" margin-top: 0px;" onclick="com.coep.test.student.confirmationToSubmitTest('
				+ ST.subTestFlag
				+ ','
				+ sId
				+ ', \'nxt\')" title="FINISH TEST" id="nextSectBtn" class="let-space btn btn-raised modify-pbm-btn bg-green finishtestbtn">FINISH TEST</a>'
				+ '</div>'
			}else{
				candidateTestHtm +=  '<div class="col-sm-2  pointer" align="right" style="margin: 0 auto;">'
				+ '<a title="Next Section" onclick="com.coep.test.student.confirmationToNextSection('
				+ ST.secFlag
				+ ','
				+ sId
				+ ', \'nxt\')" title="Next Section" id="nextSectBtn" class="let-space btn modify-pbm-btn next-sectbtn btn-raised nextsectbtn">NEXT SECTION</a>'
				+ '</div>'
			}
		candidateTestHtm +=  '</div>'
				+ '</div>'
				+ '</div>'
				+ '</div>';

		$("#main-div").html('');
		$("#main-div").html(candidateTestHtm);
		
		
		
//		+ '          <div class="row">'
//		+ '              <div class="col-sm-9 col-sm-offset-3 t8-margin">'
//		+ '                  <div id="switchtoPrevQuesSpan" onclick="com.coep.test.student.switchtoPrevQues()" class="col-sm-6 h3-cls secondary-text-color pointer prev-quest"  style = "display:none">'
//		+ '                      <div class="icon-left-navigation "></div>'
//		+ '                      Previous<br> Question'
//		+ '                  </div>'
//		+ '                  <div id="switchtoNextQuesSpan" onclick="com.coep.test.student.switchtoNextQues()" class="pull-right h3-cls col-sm-6 secondary-text-color text-align-right pointer next-quest">'
//		+ '                      Next<div class="icon-right-navigation "></div>'
//		+ '                      <br> Question'
//		+ '                  </div>'
//		+ '              </div>'
//		+ '          </div>'
//		+ '      </div>'
		
		var footer = '';
		

		footer += '<li class="previous pull-left h3-cls secondary-text-color prevquesli " id="switchtoPrevQuesSpan" ><a class="withripple prevqueslink" href="javascript:com.coep.test.student.switchtoPrevQues()">&larr; Previous</a></li>'
		+ '<li class="next col-sm-offset-6 pull-right h3-cls secondary-text-color text-align-right withripple nextquesli" id="switchtoNextQuesSpan"><a class="withripple nextqueslink" href="javascript:com.coep.test.student.switchtoNextQues()">Next &rarr;</a></li>';
	   
		$("#prevnextquesdiv").html(footer);  
        
		
		
		 //question is not tagged
		 var tagged = 0;
		 tagThisQues = function (currQGrp) {	
			
			 var thisQues = $("#currQGrp").text();
			 thisQues = thisQues.replace('.', '');
		     if ($("#flag_span" + thisQues).hasClass('icon-flag')) {
		    	 ST.tagged = false;
		     	$("#flag_span" + thisQues).removeClass('icon-flag flag-glyph');
		     } else {   
		    	 ST.tagged = true;
				$("#flag_span" + thisQues).addClass('icon-flag flag-glyph');
		     }
		 }
		 
		 
		 
		//Function for tag question	
			
			$('[data-toggle="tooltip"]').tooltip();
		    $('.tooltip-css').mouseleave(function () {
		        $(this).tooltip('hide');
		    });
		
		
			
		
		if(document.getElementById('audio') != undefined){
			
		
		var player = document.getElementById('audio');
		if (player == null) {
			player = "audio#audio";
		}

		PlayPause = function(id) {
			if ($('#' + id).hasClass('icon-play')) {
				player.play();
				$('#' + id).removeClass('icon-play').addClass('icon-pause');
			} else {
				player.pause();
				$('#' + id).removeClass('icon-pause').addClass('icon-play');
			}
		}

		// UPDATE PROGRESS BAR
		updateProgress = function() {
			var progress = $("#progressIn");
			var value = 0;
			var totMin = Math.floor(player.duration / 60);
			var totSec = Math.floor(player.duration % 60);
			
			if(isNaN(totMin) && isNaN(totMin)){
				totMin = 0;
				totSec = 0;
			}
			document.getElementById('totalTime').innerHTML = totMin + ":"
					+ totSec;
			// If duration = infinity set value to 100

			if (player.duration == 'Infinity') {
				value = 100;
			}
			// else if it is > 0 calculate percentage to highlight

			else if (player.currentTime > 0) {
				value = Math
						.floor((100 / player.duration) * player.currentTime);
			}

			// set the width of the progress bar

			progress.css({
				'width' : value + '%'
			})

			if (player.currentTime == player.duration) {
				$("#play_pause").removeClass('icon-pause')
						.addClass('icon-play');
				progress.css('width', '0%');
				player.currentTime = 0;
			}

			// set the new timestamp
			$('#time').html(formatTime(player.currentTime))
		}

		// add event listener for audio time updates
		player.addEventListener("timeupdate", updateProgress, false);

		$('#progressOut').click(function(e) {
			var offset = $(this).offset();
			var relX = Math.floor(e.pageX - offset.left);
			var totWidth = Math.floor($(this).width());
			player.currentTime = (relX / totWidth) * player.duration;
		})

		// Format the audio tag's time stamp
		formatTime = function(seconds) {
			minutes = Math.floor(seconds / 60);
			minutes = (minutes >= 10) ? minutes : "" + minutes;
			seconds = Math.floor(seconds % 60);
			seconds = (seconds >= 10) ? seconds : "0" + seconds;
			return minutes + ":" + seconds;
		}
		
		}
		//
		callRenderSavedSetTestPaper = function() {
			var TestPaperJSONArray = [];
			var savedTCData = {};
			TC.renderSavedSetTestPaper(data, TestPaperJSONArray, savedTCData,
					 sectionObj)
		};

		$(".test-preview-li").addClass("active");

		$('[data-toggle="tooltip"]').tooltip();
		$('.tooltip-css').mouseleave(function() {
			$(this).tooltip('hide');
		});

		showSection = function(thisElement) {
			$(thisElement).next().toggleClass("hidden");
		}
//		var interval = 0;
		
		startTimer1 = function (duration, display,sectionObj) {
			var timer = duration, minutes, seconds;
			
			minutes = 0;
			ST.interval = setInterval(function () {
				minutes = parseInt(timer / 60, 10);
				seconds = parseInt(timer % 60, 10);
	
				minutes = minutes < 10 ? "0" + minutes : minutes;
				seconds = seconds < 10 ? "0" + seconds : seconds;
	
//				display.textContent = minutes + ":" + seconds;
				if(minutes > 4){
					$("#timeClass").removeClass('tab blink');
					$("#timeClass2").removeClass('tab blink');
				}
				if(minutes == 4 || minutes < 4){
					$("#timeClass").addClass('tab blink');
					$("#timeClass2").addClass('tab blink');
					display.textContent = minutes + ":" + seconds;
					
					var time = (parseInt(minutes) * 60) + parseInt(seconds);
					ST.CT = time+"-"+sId;
					
					if(seconds > 50){
						showToast.show('Hurry up!!! section time is running out..',false);
					}
				}else{
					display.textContent = minutes + ":" + seconds;
					var time = (parseInt(minutes) * 60) + parseInt(seconds);
					ST.CT = time+"-"+sId;
				}
				
				if (--timer < 0) {
					timer = 0;
				if(secObjLen == 1){
					ST.testInstanceVOCall(ST.testInstanceVOArr);
					ST.subTestFlag = true;
					ST.finishTest(testData.TISID,"timeout");
					clearInterval(ST.interval);
//					alert("Your section time is up.");
//					$("#main-div").html("<h1>Test submitted succesfully</h1>");
				}else if(secObjLen != 1  || secObjLen == undefined){
						
						if(sectionObj[secObjLen - 1] == sId){
							ST.testInstanceVOCall(ST.testInstanceVOArr);
							ST.subTestFlag = true;
							ST.finishTest(testData.TISID,"timeout");
							
//							alert("Your section time is up.");
							clearInterval(ST.interval);
//							$("#main-div").html("<h1>Test submitted succesfully</h1>");
						}else{
							clearInterval(ST.interval);
							ST.testInstanceVOCall(ST.testInstanceVOArr);
//							setTimeout(function(){ alert("Your section time is up."); }, 2000);
//							alert("Your section time is up. Press OK to continue with next section");
							ST.secFlag = true;
							$("#timeClass").removeClass('tab blink');
							$("#timeClass2").removeClass('tab blink');
							$("#timeClass").addClass('white-color time-counter timer-elem');
							$("#timeClass2").addClass('white-color minutes-rem timer-elem');
							$("#nextSectBtn").click();
						}
			    	}
				}
				ST.saveCurrentTime();
			}, 1000);
		};	
		
		
		// confirmation to go Next Section
		ST.confirmationToNextSection = function(secFlag, key, status) {
			if(ST.secFlag == true){
				ST.secFlag = false;
				selNextSection(key,status);
				
			}else{
				var ms = '';
				// if ($("#dlgBody input:checkbox:checked").length <= 0) {
				// ms = 'You are Withdrawing all Authorities from user.<br/>Are you sure
				// to continue?';
				// } else {
				if(ST.tagged == true){
					ms = 'Some Tagged question are not answer.<br>Do you want to go to next Section?<br> <span style = "color:red; font-size:16px;">Once you contiune you can not go back to the previous section.</span>';
				}else{
					ms = 'Do you want to go to next Section?<br> <span style = "color:red; font-size:16px;" >Once you contiune you can not go back to the previous section.</span>';
				}
				
				// }

				var htmlText = '';
				htmlText += '';
				htmlText += '' + ms + '';

				htmlText += '';

				$('#cModalLabel').html('Confirmation');
				$('#cdlgBody').html(htmlText);
				$('#cdlgFoot').html(ST.getYesNoButtonOnDialog());
				$('#cdialog').modal({
					show : true
				});

				$('#cfYes').bind('click', function() {
					ST.tagged = false;
//					QB.moveQuestionGroupToArchive(QGID, status);
					selNextSection(key,status);
					ST.clearConfirmDialogContent();
				});

				$('#cfNo').bind('click', function() {
					ST.clearConfirmDialogContent();
				});
			}
		}

		// confirmation To Submit test
		ST.confirmationToSubmitTest = function(subTestFlag, key, status) {
//			$("#nextSectBtn").css("display","none");
			$('#nextSectBtn').one('click', function(event) {
				event.preventDefault();
				// do something
				 
//				$(this).prop('disabled', true);
			});
			$('#nextSectBtn').dblclick(function(e) {
				e.preventDefault();
			});
			ST.testInstanceVOCall(ST.testInstanceVOArr);
			if (ST.subTestFlag == true) {
				ST.subTestFlag = false;
				submitTest(key, status ,"timeout");

			} else {
				var ms = '';
				// if ($("#dlgBody input:checkbox:checked").length <= 0) {
				// ms = 'You are Withdrawing all Authorities from user.<br/>Are
				// you sure
				// to continue?';
				// } else {
				if (ST.tagged == true) {
					ms = 'Some Tagged question are not answer.<br>Do you want submit test.';
				} else {
					ms = 'Do you want submit test?';
				}

				var htmlText = '';
				htmlText += '';
				htmlText += '' + ms + '';

				htmlText += '';

				$('#cModalLabel').html('Confirmation');
				$('#cdlgBody').html(htmlText);
				$('#cdlgFoot').html(ST.getYesNoButtonOnDialog());
				$('#cdialog').modal({
					show : true
				});

				$('#cfYes').bind('click', function() {
					$("#nextSectBtn").css("display","none");
					showToast.show('Please wait!!! Test is being submitted....',true);
					$('#cfYes').one('click', function (event) {  
		    	           event.preventDefault();
		    	           //do something
		    	     });
		    		 $('#cfYes').dblclick(function(e){
		    			    e.preventDefault();
		    			  });
					submitTest(key, status,"submit");
					ST.clearConfirmDialogContent();
				});

				$('#cfNo').bind('click', function() {
					$("#nextSectBtn").css("display","block");
					ST.clearConfirmDialogContent();
				});
			}
		}

		selNextSection = function(key, status) {

			ST.testInstanceVOCall(ST.testInstanceVOArr);
			var sectionkeys = [];
			sectionkeys = Object.keys(sectionObj);

			var nxtsectId = 0;

			if (sectionkeys[secObjLen - 1] != sId) {
				clearInterval(ST.interval);
				nxtsectId = sectionkeys.indexOf(sId) + 1;
				nxtsectId = sectionkeys[nxtsectId];
				ST.CandidateTest(testData, nxtsectId);
				var timeDuration = 60 * testData.data["time" + nxtsectId];

				display = document.querySelector('.time-counter');
				startTimer1(timeDuration, display, sectionkeys);
			}
		}

		submitTest = function(key, status,completeTestStatus) {
			$('#nextSectBtn').one('click', function(event) {
				event.preventDefault();
				// do something
				 
//				$(this).prop('disabled', true);
			});
			$('#nextSectBtn').dblclick(function(e) {
				e.preventDefault();
			});
//			$('#nextSectBtn').attr('disabled', true);

			clearInterval(ST.interval);
			$(".time-counter").html("00:00");

			ST.finishTest(testData.TISID, completeTestStatus);
		};

		var i = 1;
		selId = [];
		chkedId = [];

		switchtoThisQues = function(j, k, currQRow, qGrp, qTxt, qTxtTemp) {
			
			ST.saveCurrentTime();
			
			var index = preNextqGrpObj.indexOf(qGrp);
			var tempNext = (index + 1);

			if (tempNext == preNextqGrpObj.length) {
				$("#switchtoNextQuesSpan").hide();
			} else {
				$("#switchtoNextQuesSpan").show();
			}

			if (tempNext == 1) {
				$("#switchtoPrevQuesSpan").hide();
			} else {
				$("#switchtoPrevQuesSpan").show();
			}

			var actuAnsStTime = Date.now();
			var ansType = testData.data["SEC" + sId][j].QUES[k].ANSTYP;

			$("#tagQues").attr('id', $("#" + qGrp).text());
			$("#tagQues1").attr('id', $("#" + qGrp).text());

			$(".pointer").removeClass("curr-quest");
			// make current question row active
			$(".sect-" + qGrp).addClass("curr-quest");

			$(".sect-" + qGrp).attr("tabindex", qGrp).focus();
			// change quest group number to current clicked quest

			$("#currQGrp").text($("#" + qGrp).text());
			// change quest text to current clicked quest
			
//			$("#currQTxt").text($("#" + qTxt).text());
			if (testData.data["SEC"+sId][j].QUES.length > 1) {
				 qtxtTemp = testData.data["SEC"+sId][j].QUES[k].QC;
					$("#currQTxt").html("<h1 class='secondary-text-color test-question-font-size' style='margin-top:5px;margin-bottom:5px;'>"+qtxtTemp.replace(/&lt;/g,'<').replace(/&gt;/g,'>').replace(/&amp;/g,'&')+"</h1>");
					
			}else{
				qtxtTemp = testData.data["SEC"+sId][j].QUES[0].QC;
				$("#currQTxt").html("<h1 class='secondary-text-color test-question-font-size' style='margin-top:5px;margin-bottom:5px;'>"+qtxtTemp.replace(/&lt;/g,'<').replace(/&gt;/g,'>').replace(/&amp;/g,'&')+"</h1>");
			}
			// Update current question group id
			var currQGrp = $("#" + qGrp).text().split('.');
			if (testData.data["SEC" + sId][j].QGN != undefined) {
				$("#currQuesGrpId").text(currQGrp[0]);
			} else {
				$("#currQuesGrpId").text("");
			}

			if (ansType == 1) {

				ST.mediaTypeQuestion(j, k, testData, sId);

				var mcaOptions = "";
				for (var l = 0; l < testData.data["SEC" + sId][j].QUES[k].ANS.length; l++) {
					var index = [];
						
						if(testData.data["SEC" + sId][j].QUES[k].GANSID == undefined ){
							
							mcaOptions += "<div class=\"col-sm-12 t20-margin checkbox l0-r0-padding \"><label>"
								+ "<input class=\"checkboxType\" type=\"checkbox\" id=\""
								+ testData.data["SEC" + sId][j].QUES[k].ANS[l].ANSID
								+ "\" name=\""
								+ testData.data["SEC" + sId][j].QUES[k].ANS[l].TI_ID
								+ "\" value=\""
								+ testData.data["SEC" + sId][j].QUES[k].ANS[l].content
								+ "\">" + "<span class=\"checkbox-material l0-r30-padding\"><span class='check'></span></span>"
								+ testData.data["SEC" + sId][j].QUES[k].ANS[l].content
								+ "</label></div>"
						}else{
							
							mcaOptions += "<div class=\"col-sm-12 t20-margin checkbox l0-r0-padding \"><label>"
								+ "<input class=\"checkboxType\" type=\"checkbox\" id=\""
								+ testData.data["SEC" + sId][j].QUES[k].ANS[l].ANSID
								+ "\" name=\""
								+ testData.data["SEC" + sId][j].QUES[k].ANS[l].TI_ID
								+ "\" value=\""
								+ testData.data["SEC" + sId][j].QUES[k].ANS[l].content
								+ "\">" + "<span class=\"checkbox-material l0-r30-padding\"><span class='check'></span></span>"
								+ testData.data["SEC" + sId][j].QUES[k].ANS[l].content
								+ "</label></div>"
						}
				}
				
					$("#options").html(mcaOptions);
					//TODO
					if(testData.data["SEC" + sId][j].QUES[k].GANSID != undefined){
						if(testData.data["SEC" + sId][j].QUES[k].ANSTYP == 1 ){ 
							for (var p = 0; p < testData.data["SEC" + sId][j].QUES[k].ANS.length; p++) {
								
								var index = $("#"+testData.data["SEC" + sId][j].QUES[k].ANS[p].ANSID+"").attr('id');
								
								for (var q = 0; q < testData.data["SEC" + sId][j].QUES[k].ANS.length; q++) {
									
									if(index == testData.data["SEC" + sId][j].QUES[k].GANSID[0]["GANSID"+(q+1)]){
										$("#"+index).attr('checked','checked');
										$("#"+index).attr('GANSID',testData.data["SEC" + sId][j].QUES[k].GANSID[0]["GANSID"+(q+1)]+"-"+(q+1));
//										console.log(index);
									}
								}
							}
					}
					}
					

				if (testData.data["SEC" + sId][j].QUES[k].selId != undefined) {
					for (var m = 0; m < testData.data["SEC" + sId][j].QUES[k].selId.length; m++) {
						$("#" + testData.data["SEC" + sId][j].QUES[k].selId[m])
								.prop('checked', true);
					}
				}

			} else if (ansType == 4) {

				ST.mediaTypeQuestion(j, k, testData, sId);
				
				var truefalseOptions = "";
				
				if(testData.data["SEC" + sId][j].QUES[k].GANSID == undefined || (testData.data["SEC" + sId][j].QUES[k].GANSID != testData.data["SEC" + sId][j].QUES[k].ANS[0].ANSID && testData.data["SEC" + sId][j].QUES[k].GANSID != testData.data["SEC" + sId][j].QUES[k].ANS[1].ANSID )){
				
				truefalseOptions += "<div class='col-sm-12 t20-margin radio radio-primary'><label class='cust1-label '>"
						+ "<input  class=\"radioType\" type=\"radio\" id=\""
						+ testData.data["SEC" + sId][j].QUES[k].ANS[0].ANSID
						+ "\" name=\"option1\" value=\""
						+ testData.data["SEC" + sId][j].QUES[k].ANS[0].content
						+ "\">"
						+ testData.data["SEC" + sId][j].QUES[k].ANS[0].content
						+ ""
						+ "<span class='circle'></span><span class='check '></span></label></div>"
						
						+ "<div class='col-sm-12 t20-margin radio radio-primary'><label class='cust1-label '>"
						+ "<input  class=\"radioType\" type=\"radio\" id=\""
						+ testData.data["SEC" + sId][j].QUES[k].ANS[1].ANSID
						+ "\" name=\"option1\" value=\""
						+ testData.data["SEC" + sId][j].QUES[k].ANS[1].content
						+ "\">"
						+ testData.data["SEC" + sId][j].QUES[k].ANS[1].content
						+ "" + "<span class='circle'></span><span class='check '></span></label></div>";
				
				}else{
					
					$("#" + qTxtTemp).removeClass('red-color')
					.addClass('green-color').text(
							'Answered');
					
					truefalseOptions += "<div class='col-sm-12 t20-margin radio radio-primary'><label class='cust1-label '>"
						
						if(testData.data["SEC" + sId][j].QUES[k].GANSID == testData.data["SEC" + sId][j].QUES[k].ANS[0].ANSID){
							
							truefalseOptions += "<input  class=\"radioType\" type=\"radio\" id=\""
							+ testData.data["SEC" + sId][j].QUES[k].ANS[0].ANSID
							+ "\" name=\"option1\" value=\""
							+ testData.data["SEC" + sId][j].QUES[k].ANS[0].content
							+ "\" checked>"
							+ testData.data["SEC" + sId][j].QUES[k].ANS[0].content
							+ ""
							+ "<span class='circle'></span><span class='check '></span></label></div>"
							
							+ "<div class='col-sm-12 t20-margin radio radio-primary'><label class='cust1-label '>"
							+ "<input  class=\"radioType\" type=\"radio\" id=\""
							+ testData.data["SEC" + sId][j].QUES[k].ANS[1].ANSID
							+ "\" name=\"option1\" value=\""
							+ testData.data["SEC" + sId][j].QUES[k].ANS[1].content
							+ "\">"
							+ testData.data["SEC" + sId][j].QUES[k].ANS[1].content
							+ "" + "<span class='circle'></span><span class='check '></span></label></div>"
							
						}else if(testData.data["SEC" + sId][j].QUES[k].GANSID == testData.data["SEC" + sId][j].QUES[k].ANS[1].ANSID){
							
							truefalseOptions += "<input  class=\"radioType\" type=\"radio\" id=\""
							+ testData.data["SEC" + sId][j].QUES[k].ANS[0].ANSID
							+ "\" name=\"option1\" value=\""
							+ testData.data["SEC" + sId][j].QUES[k].ANS[0].content
							+ "\" checked>"
							+ testData.data["SEC" + sId][j].QUES[k].ANS[0].content
							+ ""
							+ "<span class='circle'></span><span class='check '></span></label></div>"
							
							+ "<div class='col-sm-12 t20-margin radio radio-primary'><label class='cust1-label '>"
							+ "<input  class=\"radioType\" type=\"radio\" id=\""
							+ testData.data["SEC" + sId][j].QUES[k].ANS[1].ANSID
							+ "\" name=\"option1\" value=\""
							+ testData.data["SEC" + sId][j].QUES[k].ANS[1].content
							+ "\" checked>"
							+ testData.data["SEC" + sId][j].QUES[k].ANS[1].content
							+ "" + "<span class='circle'></span><span class='check '></span></label></div>"
							
						}
							
						
						
					
				}
				
//				truefalseOptions += '<button id="reset">Clear Answer</button>'
					
				$("#options").html(truefalseOptions);
				
				$('#reset').on('click', function() {
					
					clearAnswerForRadioButton();
					
				});	
				

				if (testData.data["SEC" + sId][j].QUES[k].selId != undefined) {
					for (var m = 0; m < testData.data["SEC" + sId][j].QUES[k].selId.length; m++) {
						$("#" + testData.data["SEC" + sId][j].QUES[k].selId[m])
								.prop('checked', true);
					}
				}
			} else if (ansType == 2) {

				ST.mediaTypeQuestion(j, k, testData, sId);

				var fibOptions = "";
				for (var l = 0; l < testData.data["SEC" + sId][j].QUES[k].ANS.length; l++) {
					
					if(testData.data["SEC" + sId][j].QUES[k].GANSID == undefined || testData.data["SEC" + sId][j].QUES[k].GANSID != testData.data["SEC" + sId][j].QUES[k].ANS[l].ANSID){
						
						fibOptions += "<div class='col-sm-12 t20-margin radio radio-primary'><label class='cust1-label '>"
							+ "<input class=\"radioType\" type=\"radio\" id=\""
							+ testData.data["SEC" + sId][j].QUES[k].ANS[l].ANSID
							+ "\" name=\"option1"
							+ "\" value=\""
							+ testData.data["SEC" + sId][j].QUES[k].ANS[l].content
							+ "\">"
							+ testData.data["SEC" + sId][j].QUES[k].ANS[l].content
							+ "" + "<span class='circle'></span><span class='check '></span></label></div>"
					}else{
						
						$("#" + qTxtTemp).removeClass('red-color')
						.addClass('green-color').text(
								'Answered');
						
						fibOptions += "<div class='col-sm-12 t20-margin radio radio-primary'><label class='cust1-label '>"
							+ "<input class=\"radioType\" type=\"radio\" id=\""
							+ testData.data["SEC" + sId][j].QUES[k].ANS[l].ANSID
							+ "\" name=\"option1"
							+ "\" value=\""
							+ testData.data["SEC" + sId][j].QUES[k].ANS[l].content
							+ "\" checked>"
							+ testData.data["SEC" + sId][j].QUES[k].ANS[l].content
							+ "" + "<span class='circle'></span><span class='check '></span></label></div>"
					}
					
					
					

				}
				
				
//				fibOptions += '<button id="reset">Clear Answer</button>'
					
					
				$("#options").html(fibOptions);
				
					$('#reset').on('click', function() {
						
						clearAnswerForRadioButton();
						
					});	
						
						
						
				if (testData.data["SEC" + sId][j].QUES[k].selId != undefined) {
					for (var m = 0; m < testData.data["SEC" + sId][j].QUES[k].selId.length; m++) {
						$("#" + testData.data["SEC" + sId][j].QUES[k].selId[m])
								.prop('checked', true);
					}
				}

			} else if (ansType == 3) {

				ST.mediaTypeQuestion(j, k, testData, sId);

				var mtpOptions = "";
				for (var l = 0; l < testData.data["SEC" + sId][j].QUES[k].ANS.length; l++) {
					
					if(testData.data["SEC" + sId][j].QUES[k].GANSID == undefined || testData.data["SEC" + sId][j].QUES[k].GANSID != testData.data["SEC" + sId][j].QUES[k].ANS[l].ANSID){
						
						mtpOptions += "<div class='col-sm-12 t20-margin radio radio-primary'><label class='cust1-label '>"
							+ "<input class=\"radioType\" type=\"radio\" id=\""
							+ testData.data["SEC" + sId][j].QUES[k].ANS[l].ANSID
							+ "\" name=\"option1"
							+ "\" value=\""
							+ testData.data["SEC" + sId][j].QUES[k].ANS[l].content
							+ "\">"
							+ testData.data["SEC" + sId][j].QUES[k].ANS[l].content
							+ "" + "<span class='circle'></span><span class='check '></span></label></div>"
							
					}else{
						
						$("#" + qTxtTemp).removeClass('red-color')
						.addClass('green-color').text(
								'Answered');
						
						mtpOptions += "<div class='col-sm-12 t20-margin radio radio-primary'><label class='cust1-label '>"
							+ "<input class=\"radioType\" type=\"radio\" id=\""
							+ testData.data["SEC" + sId][j].QUES[k].ANS[l].ANSID
							+ "\" name=\"option1"
							+ "\" value=\""
							+ testData.data["SEC" + sId][j].QUES[k].ANS[l].content
							+ "\"checked>"
							+ testData.data["SEC" + sId][j].QUES[k].ANS[l].content
							+ "" + "<span class='circle'></span><span class='check '></span></label></div>"
					}
					
					
					

				}
				
//				mtpOptions += '<button id="reset">Clear Answer</button>'
				
				$("#options").html(mtpOptions);
				
				$('#reset').on('click', function() {
					
					clearAnswerForRadioButton();
					
				});					
					
				if (testData.data["SEC" + sId][j].QUES[k].selId != undefined) {
					for (var m = 0; m < testData.data["SEC" + sId][j].QUES[k].selId.length; m++) {
						$("#" + testData.data["SEC" + sId][j].QUES[k].selId[m])
								.prop('checked', true);
					}
				}
			} else if (ansType == 5) {

				ST.mediaTypeQuestion(j, k, testData, sId);

				var scaOptions = "";
				for (var l = 0; l < testData.data["SEC" + sId][j].QUES[k].ANS.length; l++) {

					if (sId == ST.psySecId) {
						
					

						var content = testData.data["SEC" + sId][j].QUES[k].ANS[l].content
								.split(ST.splitter);
						
						
						if(testData.data["SEC" + sId][j].QUES[k].GANSID == undefined || testData.data["SEC" + sId][j].QUES[k].GANSID != testData.data["SEC" + sId][j].QUES[k].ANS[l].ANSID){
							scaOptions += "<div class='col-sm-12 t20-margin radio radio-primary'><label class='cust1-label '>"
								+ "<input class=\"radioType\" type=\"radio\" id=\""
								+ testData.data["SEC" + sId][j].QUES[k].ANS[l].ANSID
								+ "\" name=\"option1"
								+ "\" value=\""
								+ content[0]
								+ "\" psyAttr=" + content[1] + ">"
								+ content[0]
								+ ""
								+ "<span class='circle'></span><span class='check '></span></label></div>"
						}else{
							
							
							$("#" + qTxtTemp).removeClass('red-color')
							.addClass('green-color').text(
									'Answered');
							
							
							scaOptions += "<div class='col-sm-12 t20-margin radio radio-primary'><label class='cust1-label '>"
								+ "<input class=\"radioType\" type=\"radio\" id=\""
								+ testData.data["SEC" + sId][j].QUES[k].ANS[l].ANSID
								+ "\" name=\"option1"
								+ "\" value=\""
								+ content[0]
								+ "\" psyAttr=" + content[1] + "\" checked>"
								+ content[0]
								+ ""
								+ "<span class='circle'></span><span class='check '></span></label></div>"
						}
						
							
					} else {
						
						
						
						if(testData.data["SEC" + sId][j].QUES[k].GANSID == undefined || testData.data["SEC" + sId][j].QUES[k].GANSID != testData.data["SEC" + sId][j].QUES[k].ANS[l].ANSID){
							scaOptions += "<div class='col-sm-12 t20-margin radio radio-primary'><label class='cust1-label '>"
								+ "<input class=\"radioType\" type=\"radio\" id=\""
								+ testData.data["SEC" + sId][j].QUES[k].ANS[l].ANSID
								+ "\" name=\"option1"
								+ "\" value=\""
								+ testData.data["SEC" + sId][j].QUES[k].ANS[l].content
								+ "\" >"
								+ testData.data["SEC" + sId][j].QUES[k].ANS[l].content
								+ ""
								+ "<span class='circle'></span><span class='check '></span></label></div>"
						
						}else {
							
							$("#" + qTxtTemp).removeClass('red-color')
							.addClass('green-color').text(
									'Answered');
							
							scaOptions += "<div class='col-sm-12 t20-margin radio radio-primary'><label class='cust1-label '>"
								+ "<input class=\"radioType\" type=\"radio\" id=\""
								+ testData.data["SEC" + sId][j].QUES[k].ANS[l].ANSID
								+ "\" name=\"option1"
								+ "\" value=\""
								+ testData.data["SEC" + sId][j].QUES[k].ANS[l].content
								+ "\" checked>"
								+ testData.data["SEC" + sId][j].QUES[k].ANS[l].content
								+ ""
								+ "<span class='circle'></span><span class='check '></span></label></div>"
						}
						
					}
				}
				
//				scaOptions += '<button id="reset">Clear Answer</button>'
				
				
				$("#options").html(scaOptions);
				
				$('#reset').on('click', function() {
					
					clearAnswerForRadioButton();
					
				});
				
				
				
				if (testData.data["SEC" + sId][j].QUES[k].selId != undefined) {
					for (var m = 0; m < testData.data["SEC" + sId][j].QUES[k].selId.length; m++) {
						$("#" + testData.data["SEC" + sId][j].QUES[k].selId[m])
								.prop('checked', true);
					}
				}
				
			}
			
			
			
			clearAnswerForRadioButton = function() {

				
				$('.radioType').removeAttr('checked');

					 removeFlag(); //To remove flag once
					// answer is checked
					$("#" + qTxtTemp).removeClass('green-color')
							.addClass('red-color').text(
									'Unanswered');
					selId = [];
//					selId.push($("input.radioType:checked")
//							.attr('id'));
					testData.data["SEC" + sId][j].QUES[k].selId = 99999999;
					testData.data["SEC" + sId][j].QUES[k].GANSID = 99999999;
					var testInstanceVO = {};
					testInstanceVO = {
						"testInsId" : testData.data["SEC" + sId][j].QUES[k].TI_ID,
						"quesId" : testData.data["SEC" + sId][j].QUES[k].QID,
						"qGId" : testData.data["SEC" + sId][j].QGID,
						"ansId" : 99999999,
						"tagged" : ST.tagged,
						"actAnsStTime" : actuAnsStTime,
						"actAnsEndTime" : Date.now()
					}
					if (sId == ST.psySecId
							&& $(this).attr('psyAttr') != undefined) {
						testInstanceVO.actualGivenOptionsAnsId = parseInt($(
								this).attr('psyAttr'));
					}

					ST.testInstanceVOArr.push(testInstanceVO);
				
			}
			
			

			i++;
			if (i > 15) {
				i = 1;
			}

			// function to remove flag from question
			removeFlag = function() {
				ST.tagged = false;
				
				var thisQues = $("#currQGrp").text();
				thisQues = thisQues.replace('.', '_');
				if ($("#flag_span" + thisQues).hasClass('icon-flag')) {
					$("#flag_span" + thisQues).removeClass(
							'icon-flag flag-glyph');
				}
			}

			var chk = '';
			$(".checkboxType")
					.change(
							function() {
								if ($("input.checkboxType:checked").length > 0) {
									removeFlag();
									
									$("#" + qTxtTemp).removeClass('red-color')
											.addClass('green-color').text(
													'Answered');
									selId = [];
									$(
											"input:checkbox[class=checkboxType]:checked")
											.each(
													function() {

														selId.push($(this)
																.attr('id'));
														testData.data["SEC"
																+ sId][j].QUES[k].selId = selId;
														var testInstanceVO = {};
														testInstanceVO = {
															"testInsId" : parseInt($(
																	this).attr(
																	'name')),
															"quesId" : testData.data["SEC"
																	+ sId][j].QUES[k].QID,
															"qGId" : testData.data["SEC"
																	+ sId][j].QGID,
															"ansId" : parseInt($(
																	this).attr(
																	'id')),
															"tagged" : ST.tagged,
															"actAnsStTime" : actuAnsStTime,
															"actAnsEndTime" : Date
																	.now()
														}

														ST.testInstanceVOArr
																.push(testInstanceVO);
													});

									$("input:checkbox:not(:checked)")
											.each(
													function() { // unchecked
														
														if(testData.data["SEC" + sId][j].QUES[k].GANSID != undefined ){
															
															var gansId = $(this).attr('gansid');
															
															if(gansId != undefined){
																var gID = gansId.split("-");
																testData.data["SEC" + sId][j].QUES[k].GANSID[0]["GANSID"+gID[1]] = 99999999;
															}
														}
														
														selId.push($(this)
																.attr('id'));
														var index = testData.data["SEC"
																+ sId][j].QUES[k].selId
																.indexOf($(this)
																		.attr(
																				'id'));
														testData.data["SEC"
																+ sId][j].QUES[k].selId
																.splice(index,
																		1);
//														 testData.data["SEC"+sId][j].QUES[k].selId = 99999999;
														var testInstanceVO = {};
														testInstanceVO = {
															"testInsId" : parseInt($(
																	this).attr(
																	'name')),
															"quesId" : testData.data["SEC"
																	+ sId][j].QUES[k].QID,
															"qGId" : testData.data["SEC"
																	+ sId][j].QGID,
															"ansId" : 99999999,
															"tagged" : ST.tagged,
															"actAnsStTime" : actuAnsStTime,
															"actAnsEndTime" : Date
																	.now()
														}

														ST.testInstanceVOArr
																.push(testInstanceVO);
														
													});
								} else {
									$("#" + qTxtTemp)
											.removeClass('green-color')
											.addClass('red-color').text(
													'Unanswered');
									testData.data["SEC" + sId][j].QUES[k].selId = [];
								}
								
								 var ischecked= $(this).is(':checked');
				                    if(!ischecked){
				                    	
				                    	
				                    	
				                    	$("input:checkbox:not(:checked)")
										.each(
												function() { // unchecked
													
													if(testData.data["SEC" + sId][j].QUES[k].GANSID != undefined ){
														
														var gansId = $(this).attr('gansid');
														
														if(gansId != undefined){
															var gID = gansId.split("-");
															testData.data["SEC" + sId][j].QUES[k].GANSID[0]["GANSID"+gID[1]] = 99999999;
														}
													}
													
													selId.push($(this)
															.attr('id'));
													var index = testData.data["SEC"
															+ sId][j].QUES[k].selId
															.indexOf($(this)
																	.attr(
																			'id'));
													testData.data["SEC"
															+ sId][j].QUES[k].selId
															.splice(index,
																	1);
//													 testData.data["SEC"+sId][j].QUES[k].selId = 99999999;
													var testInstanceVO = {};
													testInstanceVO = {
														"testInsId" : parseInt($(
																this).attr(
																'name')),
														"quesId" : testData.data["SEC"
																+ sId][j].QUES[k].QID,
														"qGId" : testData.data["SEC"
																+ sId][j].QGID,
														"ansId" : 99999999,
														"tagged" : ST.tagged,
														"actAnsStTime" : actuAnsStTime,
														"actAnsEndTime" : Date
																.now()
													}

													ST.testInstanceVOArr
															.push(testInstanceVO);
												});
				                    	
				                    	
				                    }
//				                      alert('uncheckd ' + $(this).val());

							});

			$(".radioType")
					.change(
							function() {
								if ($("input.radioType:checked").length > 0) {
									 removeFlag(); //To remove flag once
									// answer is checked
									$("#" + qTxtTemp).removeClass('red-color')
											.addClass('green-color').text(
													'Answered');
									selId = [];
									selId.push($("input.radioType:checked")
											.attr('id'));
									testData.data["SEC" + sId][j].QUES[k].selId = selId;
									var testInstanceVO = {};
									testInstanceVO = {
										"testInsId" : testData.data["SEC" + sId][j].QUES[k].TI_ID,
										"quesId" : testData.data["SEC" + sId][j].QUES[k].QID,
										"qGId" : testData.data["SEC" + sId][j].QGID,
										"ansId" : parseInt($(this).attr('id')),
										"tagged" : ST.tagged,
										"actAnsStTime" : actuAnsStTime,
										"actAnsEndTime" : Date.now()
									}
									if (sId == ST.psySecId
											&& $(this).attr('psyAttr') != undefined) {
										testInstanceVO.actualGivenOptionsAnsId = parseInt($(
												this).attr('psyAttr'));
									}

									ST.testInstanceVOArr.push(testInstanceVO);
								} else
									$("#" + qTxtTemp)
											.removeClass('green-color')
											.addClass('red-color').text(
													'Unanswered');
							});

			// /////// previous/Next question functions ///////////

			ST.switchtoNextQues = function() {
				$("#switchtoPrevQuesSpan").show();
				var index = preNextqGrpObj.indexOf(qGrp);
				var temp = (index + 1);

				if (temp == preNextqGrpObj.length - 1) {
					$("#switchtoNextQuesSpan").hide();
				}

				if (temp == preNextqGrpObj.length) {
					temp = preNextqGrpObj.length - 1;
				}

				switchtoThisQues(preNextIndex1[temp], preNextIndex2[temp],
						"div.col-sm-12.sect-quest.pointer curr-quest",
						preNextqGrpObj[temp], preNextqTxtObj[temp],
						preNextqTxtTempObj[temp]);
				
				ST.saveCurrentTime();
			};

			ST.switchtoPrevQues = function() {
				$("#switchtoNextQuesSpan").show();
				var index = preNextqGrpObj.indexOf(qGrp);
				var temp = (index - 1);

				if (temp == 0) {
					$("#switchtoPrevQuesSpan").hide();
				}

				if (temp == -1) {
					temp = 0;
				}

				switchtoThisQues(preNextIndex1[temp], preNextIndex2[temp],
						"div.col-sm-12.sect-quest.pointer curr-quest",
						preNextqGrpObj[temp], preNextqTxtObj[temp],
						preNextqTxtTempObj[temp]);
				
				ST.saveCurrentTime();
			};

			
			
			
/*			$("#flip").click(function(){
				if (testData.data["SEC" + sId][j].MEDTYP == 1) {
					 $("#reference").slideToggle("slow");
					    if($("#reference").text()!=""){
		                    $(".reference-content").text($(".reference-content").text() == 'Hide Reference' ? 'Show Reference' : 'Hide Reference');
		                }
					 
				}else if (testData.data["SEC" + sId][j].MEDTYP == 2) {
					$("#audioReference").slideToggle("slow");
				    if($("#audioReference").text()!=""){
				    	 $(".reference-content").text($(".reference-content").text() == 'Hide Reference' ? 'Show Reference' : 'Hide Reference');
	                }
					
				}else if (testData.data["SEC" + sId][j].MEDTYP == 3) {
					 $("#videoQuestionRef").slideToggle("slow");
					    if($("#videoQuestionRef").text()!=""){
					    	 $(".reference-content").text($(".reference-content").text() == 'Hide Reference' ? 'Show Reference' : 'Hide Reference');
		                }
				}else if (testData.data["SEC" + sId][j].MEDTYP == 4) {
					 $("#imageQuestionRef").slideToggle("slow");
					    if($("#imageQuestionRef").text()!=""){
					    	 $(".reference-content").text($(".reference-content").text() == 'Hide Reference' ? 'Show Reference' : 'Hide Reference');
		                }
				}
	           
	            
	           
	           
	            if($(this).text()!=""){
	                $(this).text($(this).text() == 'Hide Reference' ? 'Show Reference' : 'Hide Reference');
	            }
	            
	        });*/
			
			
			
			
			
			ST.testInstanceVOCall(ST.testInstanceVOArr);

		}

		if (testData.data["SEC" + sId][0].MED != undefined) {
			if (testData.data["SEC" + sId][0].QUES.length > 1) {
				switchtoThisQues(
						0,
						0,
						"div.col-sm-12.sect-quest.pointer sect-qGrp1_1 curr-quest",
						"qGrp1_1", "qTxt1_1", "qTxtTemp1_1");
			} else {
				switchtoThisQues(0, 0,
						"div.col-sm-12.sect-quest.pointer sect-1 curr-quest ",
						"qGrp0", "qTxt1", "qTxtTemp1");
			}

		} else {
			if (testData.data["SEC" + sId][0].QUES.length > 1) {
				switchtoThisQues(
						0,
						0,
						"div.col-sm-12.sect-quest.pointer sect-qGrp1_1 curr-quest",
						"qGrp1_1", "qTxt1_1", "qTxtTemp1_1");
			} else {
				switchtoThisQues(
						0,
						0,
						"div.col-sm-12.sect-quest.pointer sect-qGrp1 curr-quest",
						"qGrp0", "qTxt1", "qTxtTemp1");
			}
		}

	};

	ST.mediaTypeQuestion = function(j, k, testData, sId) {

		$("#currQuesGrpId").html(testData.data["SEC" + sId][j].QGN);
		if (testData.data["SEC" + sId][j].MEDTYP == 1) {
			
			/*$("#flip").click(function(){
				
	            $("#reference").slideToggle("slow");
//	            $("#audioReference").slideToggle("slow");
//	            $("#videoQuestionRef").slideToggle("slow");
//	            $("#imageQuestionRef").slideToggle("slow");
	            if($(this).text()!=""){
	                $(this).text($(this).text() == 'Hide Reference' ? 'Show Reference' : 'Hide Reference');
	            }
	            
	        });
			*/
			$("#imageQuestionRef").hide();
			$("#videoQuestionRef").hide();
			$("#audioReference").hide();
			if (testData.data["SEC" + sId][j].QGC == undefined) {
				$("#reference").hide();
				$("#reference").html('');
				$("#flip").hide();
			} else {
				$("#flip").show();
				$("#reference").show();
				$("#reference").html(testData.data["SEC" + sId][j].QGC);
			}

			$("#audioReference").hide();
		} else if (testData.data["SEC" + sId][j].MEDTYP == 2) {
			
			/*$("#flip").click(function(){
				
//	            $("#reference").slideToggle("slow");
	            $("#audioReference").slideToggle("slow");
//	            $("#videoQuestionRef").slideToggle("slow");
//	            $("#imageQuestionRef").slideToggle("slow");
	            if($(this).text()!=""){
	                $(this).text($(this).text() == 'Hide Reference' ? 'Show Reference' : 'Hide Reference');
	            }
	        });
			*/
			
			$("#reference").hide();
			$("#imageQuestionRef").hide();
			$("#videoQuestionRef").hide();
			$("#audioReference").show();
			$("#audio").attr("src", ST.baseURL +'/media/getImage?mediaID='+testData.data["SEC" + sId][j].MED+'&questionGroupId='+testData.data["SEC" + sId][j].QGID);
		} else if (testData.data["SEC" + sId][j].MEDTYP == 3) {
			
			/*$("#flip").click(function(){
				
//	            $("#reference").slideToggle("slow");
//	            $("#audioReference").slideToggle("slow");
	            $("#videoQuestionRef").slideToggle("slow");
//	            $("#imageQuestionRef").slideToggle("slow");
	            if($(this).text()!=""){
	                $(this).text($(this).text() == 'Hide Reference' ? 'Show Reference' : 'Hide Reference');
	            }
	        });*/
			
			
			$("#reference").hide();
			$("#audioReference").hide();
			$("#imageQuestionRef").hide();
			$("#videoQuestionRef").show();
			$("#videoQuestion").attr("src", ST.baseURL +'/media/getImage?mediaID='+testData.data["SEC" + sId][j].MED+'&questionGroupId='+testData.data["SEC" + sId][j].QGID);
		} else if (testData.data["SEC" + sId][j].MEDTYP == 4) {
			
		/*	$("#flip").click(function(){
				
//	            $("#reference").slideToggle("slow");
//	            $("#audioReference").slideToggle("slow");
//	            $("#videoQuestionRef").slideToggle("slow");
	            $("#imageQuestionRef").slideToggle("slow");
	            if($(this).text()!=""){
	                $(this).text($(this).text() == 'Hide Reference' ? 'Show Reference' : 'Hide Reference');
	            }
	        });*/
			
			$("#reference").hide();
			$("#audioReference").hide();
			$("#videoQuestionRef").hide();
			$("#imageQuestionRef").show();
//			$("#imgQues").attr("src", testData.data["SEC" + sId][j].MED);
			$("#imgQues").attr("src", ST.baseURL +'/media/getImage?mediaID='+testData.data["SEC" + sId][j].MED+'&questionGroupId='+testData.data["SEC" + sId][j].QGID);
			
			
//			data.SEC29[0].QGID
//			src="'+ AP.baseURL +'/media/getImage?mediaID='+testData.data["SEC" + sId][j].MED+'&questionGroupId='+testData.data["SEC" + sId][j].QGID
			
		}
	};

	ST.renderStudentTest = function(errMsg,testLink,currentRound,startTime,endTime,studResult,studResultRN,paymentDone) {
		$(".add-questions-li").addClass("hidden");
		$("#paymentMsg").hide();
		var htm  = '';
		if (errMsg != "null") {
			 htm = '<div id="row"> <div id="materialDesign" class="col-sm-12 col-md-12 box">'
					+ '     <div class="width-100">'
					+ '         <div class="col-md-12 col-sm-12 text-align-center">'
					/*+ '             <tr align="center">'
					+ '                <td colspan="2">'*/
					+ '<img src="resource/images/VirtualMathsLabLogo.png"  style="width: 180px; height: 180px; margin-top:10px;"/><br />'
					/*+ '<img src="resource/images/ITOTitle.png" style="width: 150px; height: 66px;"/>'*/
					/*+ '                </td>'
					+ '            </tr>'*/
				//	+ '            <tr align="center">'
				//	+ '                <td colspan="2">'
					+ '                    <h1 class="secondary-text-color">'
					+ errMsg
					+ '</h1>'
				//	+ '                 </td>'
				//	+ '             </tr>'
					//+ '             <tr class="height-100px"></tr>'
				//	+ '             <tr align="center">'
					+ '                 <div class="col-sm-12">  <a href="j_spring_security_logout"> <button id="logOutBtn" type="submit" title="Log Out" class="new-start-test-btn" style="color:#fff;">LOGOUT</button></a></div>'
					//+ '             </tr>'
					+ '         </div>'
					+ '      </div>' 
					+ '   </div>  </div>';
			 
		} else if(testLink != "link"){
			$("#paymentMsg").hide();
			 htm = ' <div id="row" style="margin-top:-50px;"><div id="materialDesign" class="col-sm-12 col-sm-12 box">'
					+ '     <div class="width-100">'
					+ '         <div class="col-md-12 col-sm-12 text-align-center">'
					+ '             <div class="text-align-center">'
				
					+ '<img src="resource/images/VirtualMathsLabLogo.png"  style="width: 180px; height: 180px; margin-top:10px;"/><br />'
					
				
					+ '            </div>'
					
					
					+ '                  <B><h2 class="link-round">Test Link for Round <span class="darkbrown">'+currentRound+'</span></h2></B><br/> <span> <h3 class="darkbrown followlink">FOLLOW THIS LINK FOR TEST </h3> <br/> <span class="glyphicon glyphicon-arrow-down darkblue"></span> <br/>'
					+ '         <br/><a href="'+testLink+'" class="alink " target="_blank" style="color:#fe041c;"><span class="testLinkForm">Click here to start the test</span></a> '
					+ '                </span> '
					
					+ '                  <br><br> <br><h2 style="text-align:center" class="testtime"><i>Test will be available from <span  style="color: red;"> '+startTime+' </span> to <span  style="color: red;">'+endTime+' </span> </i></h2> '
					+ '                </span>';
					
//					if(paymentDone == "true"){
//						console.log(paymentDone);
//					htm += ' <br><span class="blink_me"><a style="color:#fe041c; font-size:20px;" href="' + ST.baseURL + '/resource/studyMaterial/dummy.pdf" target="_blank" download="Study_material.pdf">Download study material here</a></span><br/>'
//					}else {
//						htm += '<br><span class="blink_me" style="color:#fe041c; font-size:20px;">If you have not done payment yet, Please make payment to get study material.</span><br/>'
//					}
			
					if(studResult != ""){
						
						htm += '<br><span class="blink_me" ><a href="'+studResult+'" target="_blank" style="color:#fe041c; font-size:20px;"  download="Result.pdf">Click here to get Round '+(studResultRN)+' Result</a> '
							 + '</span> '
							 + '<br><span class="blink_me" id="certificateSpan" hidden><a href="" target="_blank" id="certHref" style="color:#fe041c; font-size:20px;" '
							 + 'download="Certificate.jpeg">Click here to get Certificate for '+(studResultRN)+' Result</a> '
							 + '</span> '
							
					}
					
			 		htm +=  
					 '                  <br>   <a href="j_spring_security_logout"> <button id="logOutBtn" type="submit" title="Log Out" class="new-start-test-btn" style="color:#fff;">LOGOUT</button></a></div>'
				
					
					+ '      </div>' 
					+ '   </div>  </div>';

		}else{
			
			 htm = ' <div id="row"><div id="materialDesign" class="col-sm-12 box">'
					+ '     <div class="width-100">'
					+ '         <div class="col-md-12 col-sm-12 text-align-center">'
					
					+ '<img src="resource/images/it-olympiad2017_logo.png"  style="width: 130px; height: 96px; margin-top: -35px;"/><br />'
					+ '<img src="resource/images/ITOTitle.png" style="width: 180px; height: 53px;"/>'
					
					
					+ '<div	class="col-sm-8 col-md-8 col-md-offset-2 col-sm-offset-2 text-align-justify"><h1 class="secondary-text-color text-align-center t1-b1-padding">'
					+ 'WELCOME!'
					+ '</h2>'
					+ '<span>'
					+ '	<h3 class="prim-text-black-color">The Integrated Technology'
					+ '		(IT) Olympiad is an opportunity for you to test various skills.'
					+ '		The focus of this test is mainly on testing your knowledge of'
					+ '		fundamentals in the respective domain. The strong fundamental'
					+ '		knowledge will work as foundation for the execution of the'
					+ '		project. Based on the competencies and extent of your domain'
					+ '		knowledge the group in which you are working will be awarded a'
					+ '		live project from industry. Please take this test seriously as it'
					+ '		will be a true reflection your capabilities. Do not use any'
					+ '		unfair means, as it will ultimately affect your progress.</h3>'

					+ '</span></div>'
//					+ '                  <br><B><h2>Test Link for Round '+currentRound+'</h2></B><br> <br><span> <h3 class="secondary-text-color"><a href="'+testLink+'" target="_blank">'+testLink+'</a></h3> '
					
					+'<div	class="col-sm-8 col-md-8 col-md-offset-2 col-sm-offset-2 text-align-justify"><h1 class="secondary-text-color text-align-center t1-b1-padding">'
					+'<h3></h3>'
					+ '</div>'
					+ '                </span> </td>'
					/*+ '             </tr>'
					+ '            <tr align="center">'
					+ '                <td colspan="2">'*/
//					+ '                  <br><br><br><h2 style="text-align:center"><i>Test will be available from <span > '+startTime+' PM(Noon)</span> to <span >'+endTime+' PM(Noon)</span> </i></h2> '
				/*	+ '                </span> </td>'
					+ '             </tr>'*/
				/*	+ '             <tr class="height-100px"></tr>'*/
					+ '             <div class="col-md-12 col-sm-12 text-align-center">'
					+ '                  <div class="col-sm-12 ">  <a href="j_spring_security_logout"> <button id="logOutBtn" type="submit" title="Log Out" class="new-start-test-btn" style="color:#fff;">LOGOUT</button></a></div>'
					+ '             </div>' + '         </div>'
					+ '      </div>' 
					+ '   </div>  </div>';
		}

		$("#main-div").html('');
		$("#main-div").html(htm);
		
		document.title = "Virtual Maths Lab 2020";
		fullScreenMode = function() {

			var winObj = window.open("test", "full",
					"dependent=yes, fullscreen=yes, scrollbars=yes, titlebar=yes, width= "
							+ window.screen.width + ", height= "
							+ window.screen.height);
			// w.addEventListener('load', AH.ajaxindicatorstart('loading data..
			// please wait..'), true);
			$(winObj.document).load(function() {
				alert('loaded');
				// do other things
			});
		};

	};

	ST.testInstanceVOCall = function(testInstanceVOArr) {
		if (testInstanceVOArr.length != 0) {
			$.ajax({
				type : "PUT",
				url : ST.baseURL + "testInstance",
				data : JSON.stringify(testInstanceVOArr),
				dataType : 'json',
				contentType : 'application/json',
				success : function(data) {
					ST.tagged = false;
					ST.testInstanceVOArr = [];
					console.log(ST.CT);
				},
				error : function() {
					
					window.location.reload(true);
				}

			});
		}
	}

	ST.finishTest = function(TISID,status) {
		var testJson = {
			"testInstanceStateId" : TISID,
			"status" : "C"
		};
		
		setTimeout(function() {
			$.ajax({
				type : "GET",
				url : ST.baseURL + "testInstance",
				data : "tisId=" + TISID,
				dataType : 'json',
				contentType : 'application/json',
				success : function(data) {
					// alert("done");
					AP.completeTest(testJson, data, status);
					clearInterval(ST.saveCurrentTime());
					// ST.testInstanceVOArr = [];
				},
				error : function() {
					window.location.reload(true);
				}

			});
		}, 2500);

	};

	ST.WindowViolation = function() {

		var WVJson = {
			"testInstanceStateId" : ST.TISID,
			"windowViolationTime" : 1
		};

		$.ajax({
			type : "POST",
			url : ST.baseURL + "testInstance",
			data : JSON.stringify(WVJson),
			dataType : 'json',
			contentType : 'application/json',
			success : function() {
				// alert("wv done");
			},
			error : function() {
				window.location.reload(true);
			}

		});
	};
	
	
	ST.saveCurrentTime = function() {

		var CTJson = {
			"testInstanceStateId" : ST.TISID,
			"currentTestTime" : ST.CT
		};

		$.ajax({
			type : "POST",
			url : ST.baseURL + "testInstanceState/update/currentTime",
			data : JSON.stringify(CTJson),
			dataType : 'json',
			contentType : 'application/json',
			success : function() {
				// alert("wv done");
			},
			error : function() {
				window.location.reload(true);
			}

		});
	};

})(com.coep.test.ajaxHandler, com.coep.test.student, com.coep.test.addProblem);



function blinker() {
    $('.blink_me').fadeOut(500);
    $('.blink_me').fadeIn(500);
}

setInterval(blinker, 1000);