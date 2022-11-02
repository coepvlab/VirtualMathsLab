(function(PT, TM, AM, MJ, AP, HM) {
	
	PT.baseURL = "http://localhost:8080/VirtualMathsLab/";
//	PT.baseURL = "https://portal.coepvlab.ac.in/VirtualMathsLab/"
//	PT.baseURL = "http://192.168.1.35:8080/VirtualMathsLab/";
//	PT.baseURL = "http://ec2-13-233-206-54.ap-south-1.compute.amazonaws.com:8080/VirtualMathsLab/";
	
	PT.subTestFlag = false;
	PT.testInstanceVOArr = [];
	PT.TISID = 0;
	PT.CT =  ""; // current time
	PT.TESTDATA = {};
	var fristQues = "";
	PT.quesPaper = '';
	
	PT.temp1 = '';
	PT.temp2 = '';
	PT.temp11 = '';
	PT.temp21 = '';
	
	PT.topicName = [];
	
//	answerType = {};
		
	PT.ansData = {};
		
	function getAnsTypeData() {
//		$("#resultLoading").remove();
		ansTypeData = {};
		
		$.ajax({
			type : "GET",
			url : AP.baseURL + "answerType",
			// data : JSON.stringify(L1Json),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if(data.done == true){
					ansTypeData = data.data;
					PT.ansData = ansTypeData;
					console.log(ansTypeData);
//					allData.ansTypeData = ansTypeData;
//					getMediaType(allData);
				}
			},
			error : function() {
			}

		  });

	}
	
	
	
	PT.createPracticeTest = function(topicId, topicName, testTypeId, data ) {
		
		$('body, html').scrollTop(0);
		$("#content").css({"margin-left":"0%"});
		$("#sidebar .custom-menu .btn").addClass({"margin-left":"-8px"});
		$("#sidebar").addClass("active");
		var compLevel = parseInt(data.COMPLEVEL);
		var testId = data.TID;
//		alert(compLevel);
		$.ajax({
			type : "GET",
			url : com.coep.test.addProblem.baseURL
					+ "prcaticeTest/api/create/"+topicId+"/"+testTypeId+"/"+testId+"/"+compLevel,
			// data : JSON.stringify(L1Json),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				$("#main-div").css("padding-bottom:100px");
				if(data.done == true){
					
					var instrHtm = '';
					
					instrHtm += '<div id="instruction_div">'
					
						+'<h1 >'
//						+'<span >TOPIC: </span><b id="talkbubble"></b><span >'+topicName.split("*")[0]+'</span><b id="talkbubble"></b><span >'
						+'<div class="col-sm-12 col-md-12 col-lg-12 col-xl-12" id="topicDetails" style="text-align: left;">TOPIC<i class="fa fa-angle-double-right TopicArrow" aria-hidden="true"></i>'+topicName.split("*")[0]+'<i class="fa fa-angle-double-right TopicArrow" aria-hidden="true"></i> ('+topicName.split("*")[1]+')</div>'
//						+'<div class="col-sm-12 col-md-12 col-lg-12 col-xl-12 hideFormobile" id="topicDetails" style="" ><span >TOPIC</span><b id="talkbubble"></b><span >'+topicName.split("*")[0]+'</span><b id="talkbubble"></b><span > '+topicName.split("*")[1]+'</span></div>'
//						+'<div class="col-sm-12 col-md-12 col-lg-12 col-xl-12 hideFormBigscreen" id="topicDetails" style="text-align: left;" ><span >TOPIC :-'+topicName.split("*")[0]+' -> '+topicName.split("*")[1]+'</span></div>'
						+'</h1>'
						
						+'<div class="row">'
						
						+'<div class="col-sm-12 col-md-6 col-xl-6">'
						+'<h1 >'
					+'WELCOME!'
					+'</h1>'
					+'<hr/>'
					
					+'	<span>'
					+'		<p >The <b>Virtual Math Lab</b> is an opportunity for you to test various mathematical skills. '
					+'		The focus of this test is mainly on testing your knowledge of fundamentals in the respective mathematical domain. '
					+'		The strong fundamental knowledge will work as foundation for the execution of the further tests. '
					+'		Please take this test seriously as it will be a true reflection your capabilities. '
					+'		Do not use any unfair means, as it will ultimately affect your progress.</p>'

					+'	</span>'
					+'</div>'
					+'<div class="col-sm-12 col-md-6 col-xl-6">'
					
					+'	<h1>|| स्वागतम ||</h1><hr/>'
					+'		<div class="font-marathi" lang="hi" style="line-height: 2;">'
					+'	<span class=""><b>"व्हर्च्युअल मॅथ्स लॅब"</b></span> ही तुम्हा सर्वांसाठी तुमचे गणितातील कौशल्य सिध्द करण्याची सुवर्णसंधी आहे. सदर संधीचा तुम्ही सुयोग्य वापर करून व त्यातील कौशल्ये आत्मसात करून तुम्ही पुढील वाटचाल करू शकाल. सदर संगणकीय परीक्षा तुम्ही आत्मविश्वासाने व पूर्णपणे सचोटीने दिल्यास आपली गणित विषयातील प्रगती तुम्हाला जोखता येईल. मी तुम्हा सर्वांना या संगणकीय परीक्षेसाठी शुभेच्छा देतो.'
					+'	</div>'
//					+'	<br/>'
//					+'	<br />'
					+'</div>'
					+'<div class="col-sm-12 col-md-6 col-xl-6">'
					+'	<h1 >INSTRUCTIONS</h1><hr></hr>'
					+'		<ul>'
					+'			<li ><p>The online exam is a commonly used tool by many Institutes.</p></li>'
					+'			<li ><p>We assume applicant taking the online test has a basic familiarity with computers and is '
					+'			comfortable with the use of keyboard and mouse.</p></li>'
					+'			<li ><p>There are various sections for the test and each section contains multiple questions. '
					+'			The time is allotted for each question wherein you have to solve the questions as they appear. Once the time has elapsed you cant change/view the question.</p></li>'
					+'			<li ><p>You are advised to refrain from unfair means and appear for this examination with utmost sincerity.</p></li>'
					+'	</ul>'
					+'</div>'
					+'<div class="col-sm-12 col-md-6 col-xl-6">'		
					+'<h1 >महत्वाच्या सुचना</h1><hr></hr>'
					+'	<div class="font-marathi" lang="hi" style="line-height: 1.5;">'
					+'		<ul>'
					+'		<li >संगणकीय परिक्षा ही आधुनिक युगात वापरली जाणारी महत्त्वाची पध्दती आहे.'
					+'		<li >सदर परीक्षेसाठी लागणारे संगणकाचे ज्ञान तुम्हाला अवगत असेल अशी आमची खात्री आहे.'
					+'		<li >सदर परीक्षेसाठी विविध प्रश्न विचारले जाणार असुन प्रत्येक प्रश्नाला विशिष्ट वेळ असणार आहे सदरची वेळ संपण्याच्या आधी तुम्हाला उत्तर द्यायचे आहे वेळ संपल्यानंतर तुम्हाला उत्तर बदलता येणार नाही.'
					+'		<li >कोणत्याही प्रकारची अनैतिक पध्दतीचा वापर आपण या परिक्षेत करणार नाही अशी आम्हाला खात्री आहे.'
					+'		</ul>'
					+'		</div>'
//					+'	<br/>'
					+'</div>'
					+'<div class="col-sm-12 col-md-6 col-xl-6">'
					+'	<h1 >DURING THE EXAM</h1><hr></hr>'
					+'	<ol>'
					+'		<li ><p>The test consists of multiple-choice questions (MCQ type). In this, for each given question, the applicant must choose the right, or the closest, answer from among the choices given.</p></li>'
					+'		<li ><p>All questions are mandatory.</p></li>'
					+'		<li ><p>Green flag indicates question is answered.</p></li>'
					+'		<li ><p>To proceed to the next question click on the next button at the bottom. </p></li>'
//					Once it is clicked going back to the previous question is not allowed.
					+'	</ol>'
					+'</div>'
					+'<div class="col-sm-12 col-md-6 col-xl-6">'
					
					+'	<h1 >परीक्षा चालू असताना</h1><hr></hr>'
					+'	<div class="font-marathi" lang="hi" style="line-height: 1.5; padding-bottom: 19px;">'
					+'	<ol>'
					+'	<li >या परीक्षेतील प्रश्न हे <b class="font-chng">" बहुपर्यायी "</b> स्वरुपाचे असून तुम्हाला त्याचे अचूक उत्तर द्यायचे आहे. त्यासाठी योग्य पर्यायावर <span style="color:red">" क्लीक "</span> करा.'
					+'	<li >सर्व प्रश्नांची उत्तरे आपल्याला दयावयाचे आहेत.'
					+'	<li >तुमच्या उजव्या बाजूस असलेल्या रकान्यामध्ये उत्तरे दिलेले प्रश्न  <span style="color:green">" हिरव्या रंगाने "</span> दिसतील.'
					+'<li >पुढील प्रश्नास जाण्यासाठी  <span style="color:red">" NEXT "</span> बटण दाबा.'
//					 एकदा दिलेल्या प्रश्नाला परत जाता येणार नाही. 
					+'	</ol>'
					
					+'</div>'
					
					+'	</div>'
					+'	<hr/>'
//					+'	<p class="hidden-msg">Note: You can view  e score card indicating your performance after 24 hours of examination. The same can be downloaded by signing in your account.</p>'
					+'</div>'
					+'</div>'
					+'<div class=" StartTestBtn"><a href="'+data.testURL+'" class="alink test-link" style="color: #fff;">Click here to start the test</a></div>'
					
					$("#main-div").html(instrHtm);
					$("#practiceTestDiv").html(instrHtm);
				}else{
//					$("#main-div").html("<div class='col-xl-12 col-md-12 col-sm-12 alert alert-danger' style='text-align:center;'>No questions in the database to display....</div>");
				}
			},
			error : function() {
			}

		});
		

	}
	
	/// Actual Test code starts from here
	
	PT.practiceTestHeading = function(firstName, topicName, topicId, testTime) {
		
		 preNextIndex1 = [];
		 preNextIndex2 = [];
		 preNextqGrpPanelObj = [];
		 preNextqTxtObj = [];
		 preNextqTxtTempObj = [];
		 preNextqGrpObj = [];

//		 var topicNm = topicName.split("-");
		 var heading = ''; 
		 var topicNm = topicName.split(">>");
		 
		heading +=	'<div class="row practicetestheader">'  // row start
			
			//+'<div class=" practicetestheader">'
			+'<div class="col-sm-8 col-md-5 col-lg-5"><span class="testlogo "><img src="resource/images/VirtualMathsLabLogoWithName.png"></span>'
			+'<span class="projectname ">Virtual Maths Lab </span><br/>'
			+'<span class="testType ">Practice Test</span>'
			+'</div>'
			
			
			+'<div class="testTimer col-sm-4 col-md-2 col-lg-2"><h5 id="praTestTimer" ></h5></div>'
			+'<div class="studentDetails col-sm-12 col-md-5 offset-lg-1 col-lg-4 "><span class="Studentname">Hi! '+firstName+'</span></div>'
			+'<div class="col-sm-12 col-md-12 col-lg-12 FnameMobile">Hi! '+firstName+'</div>' 
			

			//+'</div>'	
			
		
		+'</div>' // row end
		
		$("#pra-test-heading").html(heading);
//		alert(parseInt(testTime));
		TM.practiceTestTimer(parseInt(testTime), topicId);
	}
	
	PT.practiceTestMessage = function(msg) {
		$("#pra-test-main-div").html("<div class='col-xl-12 col-md-12 col-sm-12 alert alert-danger' style='text-align:center;'>"+msg+"</div>");
	}
	
	
	PT.startpracticeTest = function(testData, firstName) {
		console.log(testData);
		
		PT.TESTDATA = testData;
		PT.TISID = testData.TISID;
		console.log(testData);
		
		var testTime = testData.data["time" + testData.data.TOPICID];
		
		PT.practiceTestHeading(firstName, testData.topics, testData.TOPICS[0], testTime);
		
		
		PT.practiceTestMainDiv(testData);
	}
	
	
	PT.practiceTestMainDiv = function(testData) {
		
		var topicId = testData.TOPICS[0]; // This is because in practice test has only one topic at at time
		
		PT.renderActualQuestionDiv(testData, topicId);			
//		PT.switchtoThisQues(testData, topicId);
	}
	
	
	PT.renderActualQuestionDiv = function(testData, topicId) {
		$("#content").css({"margin-left":"0%"});
		$("#sidebar .custom-menu .btn").addClass({"margin-left":"-8px"});
		$("#sidebar").addClass("active");
		var topicNm = testData.topics.split(">>"); 
		PT.topicName = topicNm[0].split("(");
		var quesGroupHtml = '';
		quesGroupHtml  += ''//  question number panel div start
			+'<div class="row">'  // left question section div start
			
			+'<div class="col-sm-12 col-md-9 col-lg-10 col-xl-10">' 
			+'<div class="row">'
			+'<div id="alert" class="col-sm-12 col-md-12 col-lg-12 col-xl-12 alert alert-danger" style="text-align: center; padding: 10px 0;">'
			+'Your time is up. Your test has finished. '
			+'</div>'
			+'<div class="col-sm-12 col-md-12 col-lg-12 col-xl-12 hideFormobile" id="topicDetails" ><span >TOPIC</span><b id="talkbubble"></b><span >'+topicNm[0]+'</span><b id="talkbubble"></b><span > '+topicNm[1]+'</span></div>'
			+'<div class="col-sm-12 col-md-12 col-lg-12 col-xl-12 hideFormBigscreen" id="topicDetails" style="text-align: left;" >TOPIC <i class="fa fa-angle-double-right TopicArrow" aria-hidden="true"></i>'+topicNm[0]+' <i class="fa fa-angle-double-right TopicArrow" aria-hidden="true"></i> '+topicNm[1]+'</div>'
			+'<div id="quesPannelDiv" >'
		for (var i = 0; i < testData.data["TOPIC"+topicId].length; i++) {
			
			if(testData.data["TOPIC"+topicId][i].QUES.length > 1){
				
				
				for (var j = 0; j < testData.data["TOPIC" + topicId][i].QUES.length; j++) {
					
					preNextIndex1.push(i);
					preNextIndex2.push(j);
					preNextqGrpPanelObj.push('qgCheck'+ (i + 1)+ '_'+ (j + 1));
					preNextqGrpObj.push('qGrp'+ (i + 1)+ '_'+ (j + 1));
					preNextqTxtTempObj.push('quesNo_'+ (i + 1)+ '_'+ (j + 1));
					
					quesGroupHtml  +='<span id="quesNo_'+(i+1)+'_'+(j+1)+'" onclick="com.coep.test.practiceTest.switchtoThisQues('+i+ ',' +j+ ',\'qGrp'	+ (i + 1)+ '_'+ (j + 1)	+ '\',\'quesNo_'+ (i + 1)+ '_'+ (j + 1)	+ '\','+topicId+')" tabindex="qGrp'+(i+1)+'_'+(j+1)+'"' 
					
					// ttt
					
					if(testData.data["TOPIC" + topicId][i].QUES[j].GANSID != undefined){

						if(testData.data["TOPIC" + topicId][i].QUES[j].ANSTYP == 5 ){
							for (var q = 0; q < testData.data["TOPIC" + topicId][i].QUES[j].ANS.length; q++) {
							
							var index = testData.data["TOPIC" + topicId][i].QUES[j].ANS[q].ANSID;
							
							for (var r = 0; r < testData.data["TOPIC" + topicId][i].QUES[j].ANS.length; r++) {
								if(index == testData.data["TOPIC" + topicId][i].QUES[j].GANSID[0]["GANSID"+(r+1)]){
									PT.temp11 = '';
									PT.temp11 = ' class="quesPanel quesPanelchecked">'
									
										break;
								}else{
									PT.temp21 = '';
									PT.temp21 =  ' class="quesPanel">'
								}
							}
							
						}
							if(PT.temp11 == ''){
								quesGroupHtml += ' class="quesPanel">'
							}else{
								quesGroupHtml += '  class="quesPanel quesPanelchecked">'
							}
							PT.temp11 = '';	
							PT.temp21 = '';	
						}else if (testData.data["TOPIC" + topicId][i].QUES[j].ANSTYP == 8 ) {

							for (var q = 0; q < testData.data["TOPIC" + topicId][i].QUES[j].ANS.length; q++) {
							
							var index = testData.data["TOPIC" + topicId][i].QUES[j].ANS[q].ANSID;
							
							for (var r = 0; r < testData.data["TOPIC" + topicId][i].QUES[j].ANS.length; r++) {
								if(index == testData.data["TOPIC" + topicId][i].QUES[j].GANSID[0]["GANSID"+(r+1)]){
									PT.temp11 = '';
									PT.temp11 = ' class="quesPanel quesPanelchecked">'
									
										break;
								}else{
									PT.temp21 = '';
									PT.temp21 =  ' class="quesPanel">'
								}
							}
							
						}
							if(PT.temp11 == ''){
								quesGroupHtml += ' class="quesPanel">'
							}else{
								quesGroupHtml += '  class="quesPanel quesPanelchecked">'
							}
							PT.temp11 = '';	
							PT.temp21 = '';	
						
						}else{
							if(testData.data["TOPIC" + topicId][i].QUES[j].GANSID == undefined ||  testData.data["TOPIC" + topicId][i].QUES[j].GANSID == 99999999){
								quesGroupHtml += '  class="quesPanel ">'
							}else{
								quesGroupHtml += '  class="quesPanel quesPanelchecked">'
							}
					}
		
					}else{
						if(testData.data["TOPIC" + topicId][i].QUES[j].GANSID == undefined ||  testData.data["TOPIC" + topicId][i].QUES[j].GANSID == 99999999){
							quesGroupHtml += '  class="quesPanel ">'
//							+ 'Unanswered'
						}else{
							quesGroupHtml += ' class="quesPanel quesPanelchecked">'
//							+ 'Answered'
						}
					} // new
				
					// ttt
					
		quesGroupHtml += 'Q'+(i+1)+'.'+(j+1)
					+'<i id="qgCheck'+(i+1)+'_'+(j+1)+'"  ></i>'
					+'</span>'

				   }
			} else {
				
					preNextIndex1.push(i);
					preNextIndex2.push(0);
					preNextqGrpPanelObj.push('qgCheck'+ (i + 1));
					preNextqGrpObj.push('qGrp'+ (i + 1));
					preNextqTxtTempObj.push('quesNo_'+ (i + 1));
					
					quesGroupHtml  +='<span id="quesNo_'+(i+1)+'" onclick="com.coep.test.practiceTest.switchtoThisQues('+i+ ',0,\'qGrp'+ (i + 1)+ '\',\'quesNo_'+ (i + 1)+ '\','+topicId+')"  tabindex="qGrp'+i+'"'
					
					if(testData.data["TOPIC" + topicId][i].QUES[0].GANSID == undefined){
						quesGroupHtml += 'class="quesPanel">'
//						+ 'Unanswered'
					}else{
						
						if(testData.data["TOPIC" + topicId][i].QUES[0].ANSTYP == 5 ){ 
							
						for (var p = 0; p < testData.data["TOPIC" + topicId][i].QUES.length; p++) {
							
							for (var q = 0; q < testData.data["TOPIC" + topicId][i].QUES[p].ANS.length; q++) {
							
							var index = testData.data["TOPIC" + topicId][i].QUES[p].ANS[q].ANSID;
							
							for (var r = 0; r < testData.data["TOPIC" + topicId][i].QUES[p].ANS.length; r++) {
								if(index == testData.data["TOPIC" + topicId][i].QUES[p].GANSID[0]["GANSID"+(r+1)]){
									PT.temp1 = '';
									PT.temp1 = ' class="quesPanel quesPanelchecked">'
										break;
								}else{
									PT.temp2 = '';
									PT.temp2 =  ' class="quesPanel">'
								}
							}
							
						}
							if(PT.temp1 == ''){
								quesGroupHtml += ' class="quesPanel ">'
									
							}else{
								quesGroupHtml += ' class="quesPanel quesPanelchecked">'
							}
							PT.temp1 = '';	
							PT.temp2 = '';	
						}
					}else if(testData.data["TOPIC" + topicId][i].QUES[0].ANSTYP == 8 ){ 
							
						for (var p = 0; p < testData.data["TOPIC" + topicId][i].QUES.length; p++) {
							
							for (var q = 0; q < testData.data["TOPIC" + topicId][i].QUES[p].ANS.length; q++) {
							
							var index = testData.data["TOPIC" + topicId][i].QUES[p].ANS[q].ANSID;
							
							for (var r = 0; r < testData.data["TOPIC" + topicId][i].QUES[p].ANS.length; r++) {
								if(index == testData.data["TOPIC" + topicId][i].QUES[p].GANSID[0]["GANSID"+(r+1)]){
									PT.temp1 = '';
									PT.temp1 = ' class="quesPanel quesPanelchecked">'
										break;
								}else{
									PT.temp2 = '';
									PT.temp2 =  ' class="quesPanel">'
								}
							}
							
						}
							if(PT.temp1 == ''){
								quesGroupHtml += ' class="quesPanel ">'
									
							}else{
								quesGroupHtml += ' class="quesPanel quesPanelchecked">'
							}
							PT.temp1 = '';	
							PT.temp2 = '';	
						}
					}else{
							if(testData.data["TOPIC" + topicId][i].QUES[0].GANSID == undefined || testData.data["TOPIC" + topicId][i].QUES[0].GANSID == 99999999){
								quesGroupHtml += ' class="quesPanel ">'
							}else{
								quesGroupHtml += ' class="quesPanel quesPanelchecked">'
							}
						}
						
					}
					
					
//					+'class="quesPanel">'
		quesGroupHtml +='Q'+(i+1)
					+'<i id="qgCheck'+(i+1)+'" ></i>'
					+'</span>'
					
			}
			
			
		}
		
		
		quesGroupHtml  +='</div>' //question number panel div end
			
			+ '<div id="currQTxt" class="row">' // actual question div start
			+'<div id="qGrpNo" class="col-sm-2 col-md-1 col-lg-1  col-xl-1"></div>' 
			
			+'<section class="row">'
			
			+'<div id="MathPreview" class="col-sm-12 col-md-6 col-lg-7 col-xl-7 quesText TextQue"></div>' // question text
				+'<div id="quesGroupMedia" class="col-sm-12 col-md-6 col-lg-5  col-xl-5">' // quesGroupMedia start
			
			// text reference start
				+'<div id="reference"  style="max-height: 400px; overflow-y: auto;"></div>'
		
				// audio player start
				+'<div id="audioReference" class="col-sm-12 " style="width: 100%;">'
				
				+'</div>'// audio player start
			
				// video player start
				+'<div id="videoQuestionRef" class="col-sm-12 " align="">'
				
				+'</div>'// video player end
				
				// image reference start
				+'<div id="imageQuestionRef" class="col-sm-12 "  >'
				
				+'</div>'// image reference end
			
		
		+'</div>'// quesGroupMedia end
			+'<div id="options" class="col-sm-12 col-md-12 col-lg-12  col-xl-12"></div>'
			
			+'<div id="pra-test-footer" class="col-sm-12 col-md-12 col-lg-12  col-xl-12"></div>'
			+'</section>'
		+'</div>' // actual question div end
		
		+'</div>' 
		+'</div>' 
		
		+'<div id="InfoPannelDiv" class="col-sm-12 col-md-3 col-lg-2  col-xl-2"> '
		+'<h1>Information <span class="marathi-text" >( माहिती )</span></h1>'
//		+'<span class="quesPanel">1</span> Question Paper'
		+ '<div  onClick="javascript:com.coep.test.AlertMessage.showQuestionPaperClick()" data-toggle="modal" data-target="#AlertMesConfirm3" title="Show Question Paper" id="QuePaperHeader"><i class="fa fa-question-circle" aria-hidden="true"></i> Question Paper <span class="marathi-text" >(प्रश्नपत्रिका)</span></div>' 
		+'<br/>'
		+'<p style="font-size:15px;"><span class="quesPanel quesPanelchecked" style="padding:18px 20px; margin-bottom:-26px; font-size: 9px;"> </span> Answered <br/><span class="marathi-text" style="margin-left: 50px;">( उत्तर दिलेले )</span></p>'
		+'<p style="font-size:15px;"><span class="quesPanel" style="padding:18px 20px; margin-bottom:-26px; font-size: 9px;"></span> Not answered <br/><span class="marathi-text" style="margin-left: 50px;">( उत्तर दिलेले नाही )</span></p>'
		
		+'<hr>'

		quesGroupHtml +=  '<span onClick="javascript:com.coep.test.AlertMessage.confirmationToSubmitTest('
				+ PT.subTestFlag
				+ ','
				+ topicId
				+ ', \'nxt\')" title="FINISH TEST" id="submitBtn" class="btn btn-success"  data-toggle="modal" data-target="#AlertMesConfirm">FINISH TEST <span class="marathi-text hideFormobile" >( परीक्षा समाप्त करा)</span></span>'
				
		+'</div>'
		
		+'</div>' // left question section div end
		
			
		$("#pra-test-main-div").html(quesGroupHtml);	
		
		$("#alert").hide();
		
		var footer = '<div id="" class="">' // nextPrevButtonDiv  div start
			+'<span class="previous" id="switchtoPrevQuesSpan" ><a class="btn btn-info" href="javascript:com.coep.test.practiceTest.switchtoPrevQues()" style=""> <i class="fa fa-arrow-circle-left" aria-hidden="true"></i> <b class="hideFormobile">Previous <span class="marathi-text" >( मागचा प्रश्न )</span></b></a></span>'
			

//			footer +=  '<span onClick="javascript:com.coep.test.AlertMessage.confirmationToSubmitTest('
//					+ PT.subTestFlag
//					+ ','
//					+ topicId
//					+ ', \'nxt\')" title="FINISH TEST" id="submitBtn" class="btn btn-success"  data-toggle="modal" data-target="#AlertMesConfirm">FINISH TEST <span class="marathi-text hideFormobile" >( परीक्षा समाप्त करा)</span></span>'
					
					+ '<span class="next" id="switchtoNextQuesSpan"><a class="btn btn-info" href="javascript:com.coep.test.practiceTest.switchtoNextQues()"><b class="hideFormobile">Next <span class="marathi-text" >( पुढील प्रश्न )</span></b> <i class="fa fa-arrow-circle-right" aria-hidden="true"></i></a></span>'
		+'</div>' // nextPrevButtonDiv  div end
		
		$("#pra-test-footer").html(footer);	
		
		if (testData.data["TOPIC" + topicId][0].QUES.length > 1) {
			
//			alert(testData.data["TOPIC" + topicId][0].QUES.length);
			PT.switchtoThisQues(0, 0, 'qGrp1_1', 'quesNo_1_1',topicId);
			MJ.renderMathJax();
			fristQues = "quesNo_1_1";
			setTimeout(function(){ PT.callFirstQues(); }, 1000); 
		} else {
//			alert("In");
			PT.switchtoThisQues(0, 0, 'qGrp1','quesNo_1', topicId);
			MJ.renderMathJax();
			fristQues = "quesNo_1";
			setTimeout(function(){ PT.callFirstQues(); }, 1000); 
		}
		
	}
	
	PT.callFirstQues = function() {
		$("#"+fristQues).click();
	}
	
	selId = [];
	chkedId = [];
	
	
	
	PT.switchtoThisQues = function(j, k, qGrp, qTxtTemp, topicId) {
		
		if (j == 0) { // for fisrt question
			$("#switchtoPrevQuesSpan").hide();
		}else{
			$("#switchtoPrevQuesSpan").show();
		}
		var len = testData.data["TOPIC" + topicId].length;
		if (j == (len - 1)) { // for fisrt question
			$("#switchtoNextQuesSpan").hide();
		}else{
			$("#switchtoNextQuesSpan").show();
		}
		
		
		var actuAnsStTime = Date.now();
		var ansType = testData.data["TOPIC" + topicId][j].QUES[k].ANSTYP;
		
		
		if (testData.data["TOPIC"+topicId][j].QUES.length > 1) {
			
			qtxtTemp = testData.data["TOPIC"+topicId][j].QUES[k].QC;
			
//			PT.quesPaper += qtxtTemp +'<br/>' ;
				
			$("#quesGroupMedia").html();
			$(".quesText").html('<div class="" style="margin-top:5px;margin-bottom:5px;"></b>'+qtxtTemp.replace(/&lt;/g,"<").replace(/&gt;/g,">").replace(/&amp;/g,"&")+'</b></div>');
			$("#qGrpNo").html("Q"+(j+1)+"."+(k+1)+"");
			MJ.renderMathJax();
			
		}else{
			
			$("#quesGroupMedia").html();
			qtxtTemp = testData.data["TOPIC"+topicId][j].QUES[0].QC;
			
//			PT.quesPaper += qtxtTemp +'<br/>' ;
			
			$(".quesText").html( '<div class="" style="margin-top:5px;margin-bottom:5px;"><b>'+qtxtTemp.replace(/&lt;/g,"<").replace(/&gt;/g,">").replace(/&amp;/g,"&")+'</b></div>');
			$("#qGrpNo").html("Q"+(j+1)+"");
			MJ.renderMathJax();
			
		}
		
		
		// function to render all types of option
		PT.renderOptions(j, k, topicId, ansType, qTxtTemp, testData);
		com.coep.test.mathJax.renderMathJax();
		
		/// previous question button
		PT.switchtoPrevQues = function() {
			
			$("#switchtoNextQuesSpan").show();
			var index = preNextqGrpObj.indexOf(qGrp);
			var temp = (index - 1);

			if (temp == 0) {
				$("#switchtoPrevQuesSpan").hide();
			}

			if (temp == -1) {
				temp = 0;
			}

			PT.switchtoThisQues(preNextIndex1[temp], preNextIndex2[temp],
					preNextqGrpObj[temp], preNextqTxtTempObj[temp], topicId);
			
			PT.saveCurrentTime();
		}
		
		/// next question button
		PT.switchtoNextQues = function() {
			
			$("#switchtoPrevQuesSpan").show();
			var index = preNextqGrpObj.indexOf(qGrp);
			var temp = (index + 1);

			if (temp == preNextqGrpObj.length - 1) {
				$("#switchtoNextQuesSpan").hide();
			}

			if (temp == preNextqGrpObj.length) {
				temp = preNextqGrpObj.length - 1;
			}

			PT.switchtoThisQues(preNextIndex1[temp], preNextIndex2[temp],
					preNextqGrpObj[temp], preNextqTxtTempObj[temp], topicId);
			 
			PT.saveCurrentTime();
		}
		
		
		$(".checkboxType")
				.change(
						function() {
							if ($("input.checkboxType:checked").length > 0) {
								
								$("#"+qTxtTemp).removeClass('quesPanelnotchecked')
								.addClass('quesPanelchecked');
								
								selId = [];
								$(
										"input:checkbox[class=checkboxType]:checked")
										.each(
												function() {

													selId.push($(this)
															.attr('id'));
													testData.data["TOPIC"
															+ topicId][j].QUES[k].selId = selId;
													var testInstanceVO = {};
													testInstanceVO = {
														"testInsId" : parseInt($(
																this).attr(
																'name')),
														"quesId" : testData.data["TOPIC"
																+ topicId][j].QUES[k].QID,
														"qGId" : testData.data["TOPIC"
																+ topicId][j].QGID,
														"ansId" : parseInt($(
																this).attr(
																'id')),
														"actAnsStTime" : actuAnsStTime,
														"actAnsEndTime" : Date.now()
													}

													PT.testInstanceVOArr
															.push(testInstanceVO);
												});

								$("input:checkbox:not(:checked)")
										.each(
												function() { // unchecked
													
													if(testData.data["TOPIC" + topicId][j].QUES[k].GANSID != undefined ){
														
													}
													
													selId.push($(this)
															.attr('id'));
													var index = testData.data["TOPIC"
															+ topicId][j].QUES[k].selId
															.indexOf($(this)
																	.attr(
																			'id'));
													testData.data["TOPIC"
															+ topicId][j].QUES[k].selId
															.splice(index,
																	1);
//													 testData.data["TOPIC"+topicId][j].QUES[k].selId = 99999999;
													var testInstanceVO = {};
													testInstanceVO = {
														"testInsId" : parseInt($(
																this).attr(
																'name')),
														"quesId" : testData.data["TOPIC"
																+ topicId][j].QUES[k].QID,
														"qGId" : testData.data["TOPIC"
																+ topicId][j].QGID,
														"ansId" : 99999999,
//														"tagged" :PT.tagged,
														"actAnsStTime" : actuAnsStTime,
														"actAnsEndTime" : Date.now()
													}

													PT.testInstanceVOArr
															.push(testInstanceVO);
													
												});
							} else {

								$("#"+qTxtTemp).removeClass('quesPanelchecked')
								.addClass('quesPanelnotchecked');
								
								testData.data["TOPIC" + topicId][j].QUES[k].selId = [];
							}
							
							 var ischecked= $(this).is(':checked');
			                    if(!ischecked){
			                    	
			                    	$("input:checkbox:not(:checked)")
									.each(
											function() { // unchecked
												
												if(testData.data["TOPIC" + topicId][j].QUES[k].GANSID != undefined ){
													
												}
												
												selId.push($(this)
														.attr('id'));
												var index = testData.data["TOPIC"
														+ topicId][j].QUES[k].selId
														.indexOf($(this)
																.attr(
																		'id'));
												testData.data["TOPIC"
														+ topicId][j].QUES[k].selId
														.splice(index,
																1);
//												 testData.data["TOPIC"+topicId][j].QUES[k].selId = 99999999;
												var testInstanceVO = {};
												testInstanceVO = {
													"testInsId" : parseInt($(
															this).attr(
															'name')),
													"quesId" : testData.data["TOPIC"
															+ topicId][j].QUES[k].QID,
													"qGId" : testData.data["TOPIC"
															+ topicId][j].QGID,
													"ansId" : 99999999,
//													"tagged" :PT.tagged,
													"actAnsStTime" : actuAnsStTime,
													"actAnsEndTime" : Date.now()
												}

												PT.testInstanceVOArr.push(testInstanceVO);
											});
			                    	
			                    	
			                    }

						});

		$(".radioType")
		.change(
				function() {
					if ($("input.radioType:checked").length > 0) {

						$("#"+qTxtTemp).removeClass('quesPanelnotchecked')
						.addClass('quesPanelchecked');
						
						selId = [];
						selId.push($("input.radioType:checked")
								.attr('id'));
						testData.data["TOPIC" + topicId][j].QUES[k].selId = selId;
						var testInstanceVO = {};
						testInstanceVO = {
							"testInsId" : testData.data["TOPIC" + topicId][j].QUES[k].TI_ID,
							"quesId" : testData.data["TOPIC" + topicId][j].QUES[k].QID,
							"qGId" : testData.data["TOPIC" + topicId][j].QGID,
							"ansId" : parseInt($(this).attr('id')),
							"actAnsStTime" : actuAnsStTime,
							"actAnsEndTime" : Date.now()
						}

						PT.testInstanceVOArr.push(testInstanceVO);
					} else {
						$("#" + qTxtTemp).removeClass(
								'quesPanelnotchecked').addClass(
								'quesPanelchecked');
					}
				});
		
		
		
		PT.testInstanceVOCall(PT.testInstanceVOArr);

		PT.saveCurrentTime();
		
		
	}
	
	
	
	PT.mediaTypeQuestion = function(j, k, testData, topicId) {
		
		if (testData.data["TOPIC" + topicId][j].MEDTYP == 1) {

			$("#imageQuestionRef").hide();
			$("#videoQuestionRef").hide();
			$("#audioReference").hide();
			if (testData.data["TOPIC" + topicId][j].QGC == undefined || testData.data["TOPIC" + topicId][j].QGC == "") {
				$("#reference").hide();
				$("#reference").html('');
			} else {
				$("#reference").show();
				$("#reference").html(testData.data["TOPIC" + topicId][j].QGC);
			}
			
			$(".TextQue").removeClass("col-sm-12 col-md-6 col-lg-7 col-xl-7").addClass("col-sm-12 col-md-12 col-lg-12 col-xl-12 ");
			
		}else if (testData.data["TOPIC" + topicId][j].MEDTYP == 2) {
			
			$("#reference").hide();
			$("#imageQuestionRef").hide();
			$("#videoQuestionRef").hide();
			
			
			var audioHtm = '<audio controls style="width:100%;">'
			+'<source id="audio" src="'+PT.baseURL +'media/getImage?mediaID='+testData.data["TOPIC" + topicId][j].MED+'&questionGroupId='+testData.data["TOPIC" + topicId][j].QGID+'" type="audio/mpeg">'
			
			+'<p class="ErrerMsgI_V">Your browser does not support the audio element.</p>'
			+'</audio>'
			
//			$("#audio").attr("src",  );
			$("#audioReference").html(audioHtm);
			$("#audioReference").show();
			
			$(".TextQue").removeClass("col-sm-12 col-md-12 col-lg-12 col-xl-12").addClass("col-sm-12 col-md-6 col-lg-7 col-xl-7");
			
		} else if (testData.data["TOPIC" + topicId][j].MEDTYP == 3) {
			
			$("#reference").hide();
			$("#audioReference").hide();
			$("#imageQuestionRef").hide();
			
			var videoHtm ='<video id="videoQuestion" controls=""  class="width-50"  width="400" controls>'
			+'<source src="'+PT.baseURL +'media/getImage?mediaID='+testData.data["TOPIC" + topicId][j].MED+'&questionGroupId='+testData.data["TOPIC" + topicId][j].QGID+'" type="video/mp4">'
			+'<p class="ErrerMsgI_V">Your browser does not support HTML video.</p>'
			+'</video>'
			$("#videoQuestionRef").html(videoHtm);
			$("#videoQuestionRef").show();
//			$("#videoQuestion").attr("src", );
			$(".TextQue").removeClass("col-sm-12 col-md-12 col-lg-12 col-xl-12").addClass("col-sm-12 col-md-6 col-lg-7 col-xl-7");
		} else if (testData.data["TOPIC" + topicId][j].MEDTYP == 4) {
			
			
			$("#reference").hide();
			$("#audioReference").hide();
			$("#videoQuestionRef").hide();
			
			var imgHtml = '<p class="reffText">With reference to below Images answer the following question</p>'
			+'<div id="imageSrc" class="">'// 
			+'<img src="'+PT.baseURL +'media/getImage?mediaID='+testData.data["TOPIC" + topicId][j].MED+'&questionGroupId='+testData.data["TOPIC" + topicId][j].QGID+'" id="imgQues" max-width="75%">' // class="ApproveQueImg" question images
			+'</div>'
			
			$("#imageQuestionRef").html(imgHtml);
			$("#imageQuestionRef").show();
//			$("#imgQues").attr("src", );
			
			$(".TextQue").removeClass("col-sm-12 col-md-12 col-lg-12 col-xl-12").addClass("col-sm-12 col-md-6 col-lg-7 col-xl-7");
			
		}
		
		
	}
	
	
	AM.showQuestionPaperClick  = function() {
		
//		var quesPaper = PT.quesPaper ;
		
		var testData = 	PT.TESTDATA;
		
		var topicID = testData.TOPICS[0];
		
//		console.log(testData);
		
		var quesPaperHtm = '';
		
		for (var i = 0; i < testData.data["TOPIC"+topicID].length; i++) {
			
			for (var j = 0; j < testData.data["TOPIC" + topicID][i].QUES.length; j++) {
				if(testData.data["TOPIC"+topicID][i].QUES.length > 1){
					quesPaperHtm += '<div class="QuePaperModelque">Q' + (i + 1)+'.'+(j+1) + ' ) '+  testData.data["TOPIC" + topicID][i].QUES[j].QC
				}else{
					quesPaperHtm += '<div class="QuePaperModelque">Q' + (i + 1)+ ' )'+  testData.data["TOPIC" + topicID][i].QUES[j].QC
				}
				
				quesPaperHtm += '</div>'
			}
		}
		
		var AlertMesConfirm = ''
			AlertMesConfirm +=  '<div class="container-fluid">'
			+ '<div class="row">'
			+ '<div  class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'
			//Alert modal start
					+ '<div class="modal QuePaperModel" id="AlertMesConfirm3">'
					+ '<div class="modal-dialog">'
					+ '<div class="modal-content">'
		            
		            	 +' <div class="modal-header bg-info" style="color:#fff;">'
							+ '   <h4 class="modal-title">Question Paper <span class="marathi-text" >(प्रश्नपत्रिका)</span></h4>'
							+ '  <button type="button" class="close" data-dismiss="modal">&times;</button>'
							+ '  </div>'
			
							+ '   <div class="modal-body">'
							+ '<span id="AlertMsgStyle">'+quesPaperHtm+'</span>'
						    + ' </div>'
		                +'    <div class="modal-footer">'
						+ '<button type="button" class="btn btn-success" data-dismiss="modal" id="" onClick="">Okay</button>&nbsp;&nbsp;'
//						+ '<button type="button" class="btn btn-danger" data-dismiss="modal">No</button>'
						+ '</div>'
		
					  + '    </div>'
					  + '  </div>'
					  + '</div>'
			//alert modal end
			+ '</div>' // subject_content close
			+ '</div>'// main row close
			+ '</div>' // container close
			
			$("#pra-test-main-div").append(AlertMesConfirm);
		    com.coep.test.mathJax.renderMathJax();
//			AlertComfirmYes = function()
//			{
//				TM.onFinishTest();
//				AlertComfirmFlag = true;
//				PT.submitTest(subTestFlag, key, status);
//				return AlertComfirmFlag;
//			}
		
	}
	
	
	AM.confirmationToSubmitTest = function(subTestFlag, key, status) {
		var AlertComfirmFlag = false;
			AlertMsg = 	 'Do you want to submit the test? <span class="marathi-text" >( तुम्हाला चाचणी परीक्षा समाप्त करायची आहे का? )</span> ';
			var AlertMesConfirm = ''
				AlertMesConfirm +=  '<div class="container-fluid">'
				+ '<div class="row">'
				+ '<div  class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'
				//Alert modal start
						+ '<div class="modal" id="AlertMesConfirm">'
						+ '<div class="modal-dialog">'
						+ '<div class="modal-content">'
			            
			            	 +' <div class="modal-header bg-info" style="color:#fff;">'
								+ '   <h4 class="modal-title">Confirmation <span class="marathi-text" >( पुष्टीकरण )</span> !!</h4>'
								+ '  <button type="button" class="close" data-dismiss="modal">&times;</button>'
								+ '  </div>'
				
								+ '   <div class="modal-body">'
								+ '<span id="AlertMsgStyle">'+AlertMsg+'</span>'
							    + ' </div>'
			                +'    <div class="modal-footer">'
							+ '<button type="button" class="btn btn-success" data-dismiss="modal" id="AlertComfirmYes" onClick="AlertComfirmYes()">Yes <span class="marathi-text" > ( होय )</span></button>&nbsp;&nbsp;'
							+ '<button type="button" class="btn btn-danger" data-dismiss="modal">No <span class="marathi-text" > ( नाही )</span></button>'
							+ '</div>'
			
						  + '    </div>'
						  + '  </div>'
						  + '</div>'
				//alert modal end
				+ '</div>' // subject_content close
				+ '</div>'// main row close
				+ '</div>' // container close
				
				$("#pra-test-main-div").append(AlertMesConfirm);
			
				AlertComfirmYes = function()
				{
					TM.onFinishTest();
					AlertComfirmFlag = true;
					
					PT.saveCurrentTime();
					PT.submitTest(subTestFlag, key, status);
					return AlertComfirmFlag;
				}
			
		}
	
	
	PT.submitTest = function(subTestFlag, key, status) {
		
		var loader = '<div class="overlay" id="Loading">'
//			+'<img src="resource/images/giphy.gif" class="laoderimg">'
			 + '<div class="overlay__inner">'
			 + '<div class="overlay__content"><span class="spinner"></span><br/><span class="loading">SUBMITTING....</span></div>'
			 + '</div>'
			 + '</div>'
			 
			 $("#practiceTestDiv").append(loader);
		
		PT.testInstanceVOCall(PT.testInstanceVOArr);
		
		completeTestStatus = status;
		
		$('#submitBtn').one('click', function(event) {
			event.preventDefault();
			// do something
//			$(this).prop('disabled', true);
		});
		$('#submitBtn').dblclick(function(e) {
			e.preventDefault();
		});

		PT.finishTest(testData.TISID, completeTestStatus);
	}
	
	
	
	
	PT.testInstanceVOCall = function(testInstanceVOArr) {
		
		if (testInstanceVOArr.length != 0) {
			$.ajax({
				type : "PUT",
				url : PT.baseURL + "testInstance",
				data : JSON.stringify(testInstanceVOArr),
				dataType : 'json',
				contentType : 'application/json',
				success : function(data) {
					PT.testInstanceVOArr = [];
				},
				error : function() {
					
					window.location.reload(true);
				}

			});
		}
	}
	
	
	PT.finishTest = function(TISID,status) {
		
		PT.saveCurrentTime();
		
		var testJson = {
			"testInstanceStateId" : TISID,
			"status" : "C"
		};
		
		setTimeout(function() {
			$.ajax({
				type : "GET",
				url : PT.baseURL + "testInstance",
				data : "tisId=" + TISID,
				dataType : 'json',
				contentType : 'application/json',
				success : function(data) {
					
					PT.completeTest(testJson, data, status, TISID);
					
					// PT.testInstanceVOArr = [];
				},
				error : function() {
//					window.location.reload(true);
				}

			});
		}, 2500);

	};
	
	
	PT.completeTest = function(testJson, summaryData, status, TISID) {
		clearInterval(PT.saveCurrentTime());
		
		$.ajax({
			type : "POST",
			url : PT.baseURL + "testInstance",
			data : JSON.stringify(testJson),
			dataType : 'json',
			contentType : 'application/json',
			
			success : function(data) {
				
				if (data.done == true) {
					PT.summaryReport(summaryData, TISID);
				}
			
			},
			error : function() {
			}

		});
		
//		setTimeout(AP.renderAttemped(data,status), 1000);
		
	}
	
	
	
	PT.saveCurrentTime = function() {

		var CTJson = {
			"testInstanceStateId" : PT.TISID,
			"currentTestTime" : PT.CT
		};

		$.ajax({
			type : "POST",
			url : PT.baseURL + "testInstanceState/update/currentTime",
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
	
	
	
	
	PT.summaryReport = function(data, tISID) {
//		console.log(data);

		var topicNm = data.TOPICNM.topicNm.split(">>");
		
		$("#pra-test-heading").hide();
		$("#pra-test-footer").hide();
		$("#pra-test-main-div").html('');
		
		var candidateSummaryHtm = '<h1 style="font-size:22px;">CANDIDATE SUMMARY <span class="marathi-text" >( विद्यार्थीचा सारांश )</span></h1><div class="row"><div id="message" class="col-sm-12 col-md-12 col-lg-12 col-xl-12 " style="margin-top: 9px; text-align: center;">'
				+ ' <div id="onSubmit" class="alert alert-success cust-margin" role="alert" style=" "><B>&nbsp;&nbsp;Congratulation!! you have completed the test.<br/><span class="marathi-text" >( अभिनंदन!! तुम्ही चाचणी परीक्षा पूर्ण केली आहे. )</span></B></div>'
//				+ ' <div id="timeOut" class="alert alert-warning cust-margin" role="alert" hidden><span class="glyphicon glyphicon-remove-circle  alert-warning "></span><B>&nbsp;&nbsp;Your session has timed out and your answers has been saved successfully..</B></div>'
				+ ' </div>' 
			
			for(var key in data.TOT){
			candidateSummaryHtm +=  ' <div class="col-sm-12 col-md-12 col-lg-12 col-xl-12 " >'
				
				+'<div class="row">'
//				+'<div class="col-sm-12 col-md-12 col-lg-12 col-xl-12" id="topicDetails" style=""><span >TOPIC</span><b id="talkbubble"></b><span >'+topicNm[0]+'</span><b id="talkbubble"></b><span > '+topicNm[1]+'</span>'
				+'<div class="col-sm-12 col-md-12 col-lg-12 col-xl-12 hideFormobile" id="topicDetails" style="" ><span >TOPIC</span><b id="talkbubble"></b><span >'+topicNm[0]+'</span><b id="talkbubble"></b><span > '+topicNm[1]+'</span></div>'
				+'<div class="col-sm-12 col-md-12 col-lg-12 col-xl-12 hideFormBigscreen" id="topicDetails" style="text-align: left;" ><span >TOPIC <i class="fa fa-angle-double-right TopicArrow" aria-hidden="true"></i>'+topicNm[0]+' <i class="fa fa-angle-double-right TopicArrow" aria-hidden="true"></i> '+topicNm[1]+'</span></div>'
				
			candidateSummaryHtm +='</div>'
//				+'</div>'
//				+'</div>'
				+'</div>'
			}

				candidateSummaryHtm += '<div id="testReportDetails">'
				+ '</div>'
				$("#pra-test-main-div").html(candidateSummaryHtm);
				
				var userId = data.userId;
				
				PT.MoveDatafromInstaceToCompletion(PT.baseURL+"testInstanceToCompletionForUser?userId="+userId+"&tISID="+tISID, "MoveDataToCompletion", userId, tISID);
	
	}
	
	
	  PT.MoveDatafromInstaceToCompletion = function(url, userId, tISID){
		
				$.ajax({
						type : "POST",
						url : url,
						data : "userId=" + userId + "&tISID="
								+ tISID,
						dataType : 'json',
						contentType : 'application/json',
						success : function(data) {
							if (data.done != false) {
		//						console.log(data);
								PT.showTestReport(PT.TESTDATA, data.USERANSDATA, data.ANSDATA, data.GivenTIME,  data.TakenTIME, data);
//								showToast.show(data.msg, true);
			
							} else {
								showToast.show(data.msg, false);
							}
						},
						error : function() {
			
						}
				});
			  
		
	}
	
	  

	  
	  
	PT.showTestReport = function(data, userAnsData, ansData, GivenTIME, TakenTIME, PERDATA) {
//		console.log(PERDATA);
		console.log(data);
		
		getAnsTypeData();

		testData = data;
		
		var topicID = data.TOPICS[0];
		
		var quesHtm = '';
		quesHtm += 	'<hr>'
		quesHtm += '<h1>QUESTION PAPER DETAILS <span class="marathi-text" >( प्रश्नपत्रिका तपशील )</span></h1>'
			 	
			    + '<div id="ansCntDiv">'
			    + '<span class="btn btn-primary"> Total Questions <span class="marathi-text" >( एकूण प्रश्न )</span> - '
			    + (ansData.CORRANS + ansData.INCORRANS)+' / '+(ansData.CORRANS + ansData.INCORRANS + ansData.UNANS)
			 	+ '</span>'
			 	+ '<span class="btn btn-success"> Correct Answers <span class="marathi-text" >( बरोबर उत्तरे )</span> - '
			 	+ ansData.CORRANS
			 	+ '</span>'
			 	+ '<span class="btn btn-danger"> Incorrect Answers <span class="marathi-text" >( चुकीची उत्तरे )</span> - '
			 	+ ansData.INCORRANS
			 	+ '</span>'
			 	+ '<span class="btn btn-info"> Unanswered <span class="marathi-text" >( उत्तर दिलेले नाही )</span> - '
			 	+ ansData.UNANS
			 	+ '</span>'
			 	
				+ '<span class="btn btn-info"> Allotted Time <span class="marathi-text" >( दिलेला वेळ )</span> - '
			 	+ GivenTIME + " Minutes"
			 	+ '</span>'
			 	
			 	+ '<span class="btn btn-info"> Time Consumed <span class="marathi-text" >( घेतलेला वेळ )</span> - '
			 	+ TakenTIME
			 	+ '</span>'
			 	
			 	+ '<div style="text-align:right; font-size:15px;">'
			 	+ '<span style="color: red; text-align:left;">Note:</span> For answer click on Incorrect in RESULT column.'
			 	+ '</div>'
			 	
			 	+ '</div>'
			 	
				+ '<div class="table-responsive">'
				+ '<table id="AnswerPaperData" class="table table-striped table-bordered" style="width:100%">'
				+ '<thead>' + ' <tr>'
//				+ '<th width="15%"> Action</th>'
				+ '<th  width="5%">Q. No. </th>'
				+ '<th class="questionCol" width="70%">Question </th>'
				+ '<th width="10%">Result</th>' 
				
				+ ' </tr>'
				+ ' </thead>'
				+ ' <tbody>'
				
			var cnt = 0;
			for (var i = 0; i < testData.data["TOPIC"+topicID].length; i++) {
				
			for (var j = 0; j < testData.data["TOPIC" + topicID][i].QUES.length; j++) {
				
				if (userAnsData[cnt].USERANS != "Correct") {
					quesHtm += '<tr  id="'+i+'-'+j+'" >' 
					
//					quesHtm += '<td width="15%" >' 
					
//						+ '<span  onClick="javascript:com.coep.test.AlertMessage.showSolutionOnClick('+i +','+j +',\'active\')" data-toggle="modal" data-target="#AlertMesConfirm2-'+i+"-"+j+'" title="Show Solution" class="btn btn-success AcT_ArchIcon">Click here for Solution <br>उत्तरासाठी येथे क्लिक करा</span>' 
//						+ '<span  onClick="javascript:com.coep.test.AlertMessage.showSolutionOnClick('+i +','+j +',\'active\')" data-toggle="modal" data-target="#AlertMesConfirm2-'+i+"-"+j+'" title="Show Solution" class="btn btn-success AcT_ArchIcon">Click here for Solution <br>उत्तरासाठी येथे क्लिक करा</span>' 
						
//						+ '</td>'
						
						if(testData.data["TOPIC"+topicID][i].QUES.length > 1){
							quesHtm += '<td width="5%" style="text-align: center;">Q' + (i + 1)+'.'+(j+1)
						}else{
							quesHtm += '<td width="5%" style="text-align: center;">Q' + (i + 1)
						}
						
					
					quesHtm += '</td>'
					+ '<td  id="MathPreview" class="questionCol" width="70%">' + testData.data["TOPIC" + topicID][i].QUES[j].QC
					+ '</td>'
					
					+'<td width="10%">'
					
					+ '<span class="resultBtn btn btn-success" onClick="javascript:com.coep.test.AlertMessage.showSolutionOnClick('+i +','+j +',\'active\')" data-toggle="modal" data-target="#AlertMesConfirm2-'+i+"-"+j+'" title="Click here for Solution (उत्तरासाठी येथे क्लिक करा)" class="btn btn-success AcT_ArchIcon" style="">'+ userAnsData[cnt].USERANS + '</span>'
					+ '</td>' + '</tr>'
					
				}
				cnt++;
			}
		}
			
		 quesHtm += '</tbody>'
			 		+ '</table>' 
		
		// passedFaild message 
		+ '<hr>' 
		+ '<br>' 
//		+ '<div ></div>'
		+ '<div id="lastTopicMsg" class="green-color">'+PERDATA.PassedFailedMsg+'</div>'
		
		// restart the test 
		var topicNmForCurrTest = data.topics.split(">>"); 
		var currTopicName = topicNmForCurrTest[1].split("(");
		quesHtm +=  '<div><button id="moveToNextTest" type="button" class="btn btn-success" onClick="javascript:com.coep.test.home.createPracticeTest('+topicID +',\''+currTopicName[0]+'*'+''+currTopicName[1].replace(')', '')+''+'\')" data-toggle="modal" data-target="#AlertMesConfirm-'+topicID+'"   title="Start Practice Test"> <span class="marathi-text" >पुढील सराव सुरू करा</span><br>Start Next Test</button></div>&nbsp;&nbsp;'	
		quesHtm +=  '<div><button id="homePage" type="button" class="btn btn-success" style="display:none;" onClick="javascript:com.coep.test.home.goToHomePage()"   title="Ho to Home Page"> Go to Home Page</button></div>&nbsp;&nbsp;'
//		quesHtm +=  '<div><button id="goToNextTopic" type="button" class="btn btn-success" style="display:none;" onClick="javascript:com.coep.test.home.goToNextTopic()"   title="Ho to next Topic"> Go to Next Topic</button></div>&nbsp;&nbsp;'
		
		
		+ '</div>'
		$("#testReportDetails").html(quesHtm);
		 
			$("#main-div").ready(function() {
				 com.coep.test.mathJax.renderMathJax();
				var data_table = $('#AnswerPaperData').DataTable({
					"pageLength" : 100,
					// lengthChange: false,
					buttons : [ 'copy', 'excel', 'pdf' ],
					 "order": [[ 3, "asc" ]],
				     drawCallback: function(){
				          $('.paginate_button.page-item:not(.disabled)', this.api().table().container())          
				             .on('click', function(){
				            	 com.coep.test.mathJax.renderMathJax();
				             });       
				       },
				       "columnDefs": [ {
				    	      "targets"  : 'no-sort',
				    	      "orderable": false,
				    	      "order": []
				    	    }],
//				    "ordering": false,
			       "initComplete": function() {
			    	   com.coep.test.mathJax.renderMathJax();
			       }
				}).on('search.dt', function() {
					 com.coep.test.mathJax.renderMathJax();
				});
				
				data_table.order( [3,'asc'] ).draw();
				
				 com.coep.test.mathJax.renderMathJax();
			}) //main end
		 
		 
		 var w = $(window).width();
		    if(w < 768){ 
		    	$(".questionCol").hide();
		    }else{
		    	$(".questionCol").show();
		    }
		    
		     $(window).resize(function() {
       			var w = $(window).width();
			    if(w < 768){ 
			    	$(".questionCol").hide();
			    }else{
			    	$(".questionCol").show();
			    }
   			 });
		    
			  AM.showSolutionOnClick = function(i,j, temp) {
				
			    var l = i;
			    var m = j;
			    
				
					var AlertMesConfirm = ''
						AlertMesConfirm +=  '<div class="container-fluid">'
						+ '<div class="row">'
						+ '<div  class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'
						//Alert modal start
								+ '<div class="modal SolutionModel" id="AlertMesConfirm2-'+i+"-"+j+'">'
//													id="AlertMesConfirm
								+ '<div class="modal-dialog ">'
								+ '<div class="modal-content">'
					            
					            	 +' <div class="modal-header bg-info" style="color:#fff;">'
										+ '   <h4 class="modal-title">Solution </h4>'
										+ '  <button type="button" class="close" data-dismiss="modal">&times;</button>'
										+ '  </div>'
						
										+ '   <div class="modal-body">'
//										+ '<span id="AlertMsgStyle">'+AlertMsg+'</span>'
										
										if(testData.data["TOPIC"+topicID][l].QUES.length > 1){
											AlertMesConfirm += '<span class="Qlable" >Q' + (l + 1)+'.'+(m+1) +' </span>&nbsp;:&nbsp;'
										}else{
											AlertMesConfirm += '<span class="Qlable" >Q' + (l + 1) + ' </span>&nbsp;:&nbsp;'
										}
					
										AlertMesConfirm += '<span  id="MathPreview">' + testData.data["TOPIC" + topicID][l].QUES[m].QC 
//										 data.TOPIC226[0].MEDTYP
										+ '</span><hr>'
										
										 if(testData.data["TOPIC" + topicID][l].MEDTYP == 1){
												AlertMesConfirm += '<span  id="MathPreview">' + testData.data["TOPIC" + topicID][l].QGC 
										 }else if (testData.data["TOPIC" + topicID][l].MEDTYP == 2) { // audio
											 AlertMesConfirm += '<div class="col-xl-6 col-md-6 col-sm-12 ApproveDivStyle">'
//						    					 +'<p>'
//												 +' With reference to the follwing clip answer the  question'
//												 +' </p>'
												 +' <audio controls class="ApproveQueAudio">'
												 +'  <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+testData.data["TOPIC" + topicID][l].QGC+'" type="audio/mpeg" >'
												 +' Your browser does not support the audio element.'
												 +' </audio>'
												 +'</div>'
									     }else if (testData.data["TOPIC" + topicID][l].MEDTYP == 3) { // video

									    	 AlertMesConfirm +='<div class="col-xl-6 col-md-6 col-sm-12 ApproveDivStyle">'
//							 						+ '<p>'
//							 						+ ' With reference to the follwing clip answer the question'
//							 						+ ' </p>'
							 						+'  <video controls  class="ApproveQueVideo" >'
							 						+ '            <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+testData.data["TOPIC" + topicID][l].QGC+'" type="video/mp4" >'
							 						+ ' <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+testData.data["TOPIC" + topicID][l].QGC+'" type="video/ogg">'
							 						+ '            Your browser does not support the video tag.'
							 						+ '         </video>' 
							 						+'</div>'
							    			 
										 }else if (testData.data["TOPIC" + topicID][l].MEDTYP == 4) { // Image
//											 AlertMesConfirm += '<div class="col-xl-6 col-md-6 col-sm-12 ApproveDivStyle" ><span class=""></span>'
//								    				 +'<p>'
//													 +' With reference to the follwing Image answer the question'
//													 +' </p>'
											 AlertMesConfirm +='<img src="'+ AP.baseURL +'media/get/getImage?mediaID='+testData.data["TOPIC" + topicID][l].QGC+'"  class="ApproveQueImg" /></div>'
										 }
										
										
										AlertMesConfirm += '<hr><div> <b>Answer Type : <i>'
										+ PT.ansData[((testData.data["TOPIC" + topicID][l].QUES[m].ANSTYP) - 1)].ATN 
										+ '</i></b></div><hr>'
										+ '<div class="table-responsive">'
										+ '<table cellpadding="5" cellspacing="0" border="0" class="ExpandTables table table-striped table-bordered dataTable no-footer">'
										    
									    	+'<thead><th style="width:10%">Sr. No.</th>'
										    		 + '<th style="width:75%">Option</th>'
										    		 + '<th style="width:15%">Right Or Wrong</th>'
//										    		 + '<th>Option Type</th>'
										    		 + '</thead><tbody>'
//										    		 testData.data["TOPIC" + topicID][i].QUES[j]
//										    		 data.TOPIC4[0].QUES[0].ANS[0].content data.TOPIC4[0].QUES[0].ANSTYP
										    		 var ii = 0; //this is for multiple correct option
										    for(var p = 0; p < testData.data["TOPIC" + topicID][l].QUES[m].ANS.length; p++){
										    	AlertMesConfirm += 	'<tr>'
										    		 +'<td style="width:10%">'+(p+1)+'</td>'
										    		 if(testData.data["TOPIC" + topicID][l].QUES[m].ANS[p].ansMedia == "TEXT"){
										    			 AlertMesConfirm +='<td  style="width:75%" id="MathPreview">'+testData.data["TOPIC" + topicID][l].QUES[m].ANS[p].content+'</td>'
										    		 }else{
										    			 AlertMesConfirm +='<td style="width:75%">'
										    			 +' <img src="'+ AP.baseURL +'media/get/getImage?mediaID='+testData.data["TOPIC" + topicID][l].QUES[m].ANS[p].content+'" class="optionImg" />'
										    			 +'</td>'
										    		 }
											         
											         if(testData.data["TOPIC" + topicID][l].QUES[m].ANS[p].RANS == true){
											        	 AlertMesConfirm += '<td style="width:15%"> <i class="fa fa-check-circle" aria-hidden="true" style="color:green;"></i></td>'
											         }else{
											        	 
											        	 if (testData.data["TOPIC" + topicID][l].QUES[m].selId == undefined) {
											        		 AlertMesConfirm += '<td style="width:15%"><i class="fa fa-times-circle-o" aria-hidden="true" style="color:red;"></i></td>'
														}else{
															
															if (testData.data["TOPIC" + topicID][l].QUES[m].selId.length == 1) {
																	 if (testData.data["TOPIC" + topicID][l].QUES[m].selId[0] == testData.data["TOPIC" + topicID][l].QUES[m].ANS[p].ANSID) {
														        		 AlertMesConfirm += '<td style="width:15%"><i class="fa fa-times-circle-o" aria-hidden="true" style="color:blue;"></i> (Selected Answer)</td>'
																	}else{
																		 AlertMesConfirm += '<td style="width:15%"><i class="fa fa-times-circle-o" aria-hidden="true" style="color:red;"></i></td>'
																	}
															 }else{
//																 for (var ii = 0; ii < testData.data["TOPIC" + topicID][l].QUES[m].selId.length; ii++) {
																	 if (testData.data["TOPIC" + topicID][l].QUES[m].selId[ii] == testData.data["TOPIC" + topicID][l].QUES[m].ANS[p].ANSID) {
														        		 AlertMesConfirm += '<td style="width:15%"><i class="fa fa-times-circle-o" aria-hidden="true" style="color:blue;"></i> (Selected Answer)</td>'
																	}else{
																		 AlertMesConfirm += '<td style="width:15%"><i class="fa fa-times-circle-o" aria-hidden="true" style="color:red;"></i></td>'
																	}
//																}
																 if (ii < testData.data["TOPIC" + topicID][l].QUES[m].selId.length) {
																	 ii++;
																 }
																	
															 }
														}
											        	 
							        	
											         }
											     
//										  	 ansHtm += '<td>'+testData.data["TOPIC" + topicID][l].QUES[m].ANS[p].ansMedia+'</td>'
										    		 +'</tr>'
										   }
       
					AlertMesConfirm +=  '</tbody></table></div>'
					
						
						AlertMesConfirm +=  '<div class="col-sm-12 col-md-12 col-lg-12 col-xl-12" id="solutionDiv" >'
					    		
							AlertMesConfirm += '<div ><div class="heading">Solution</div>'
			    			 +'<div class="inners">'+testData.data["TOPIC" + topicID][l].QUES[m].QSOLTEXT + '</div></div>'
			    			 
			    			 if(testData.data["TOPIC" + topicID][l].QUES[m].QSOLTYPE == "IMAGE"){
			    				 
			    				 AlertMesConfirm += '<div class="col-sm-12 col-md-12 col-lg-12 col-xl-12" ><div class="heading">Solution Media</div>'
			    				 +'<div class="inners"><p>'
								 +' With reference to the follwing Image answer the question'
								 +' </p>'
			    				 +'<img src="'+ AP.baseURL +'media/get/getImage?mediaID='+testData.data["TOPIC" + topicID][l].QUES[m].QSOLMEDIA+'"  class="popup-img" /></div>'
			    			 }else if(testData.data["TOPIC" + topicID][l].QUES[m].QSOLTYPE == "AUDIO"){
			    				 AlertMesConfirm += '<div class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'
			    					 +'<div class="heading">Solution Media</div>'
			    					 +'<div class="inners"><p>'
									 +' With reference to the follwing clip answer the  question'
									 +' </p>'
									 +' <audio controls>'
									 +'  <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+testData.data["TOPIC" + topicID][l].QUES[m].QSOLMEDIA+'" type="audio/mpeg"  class="">'
									 +' Your browser does not support the audio element.'
									 +' </audio>'
									+'</div>'
			    			 }else if(testData.data["TOPIC" + topicID][l].QUES[m].QSOLTYPE == "VIDEO"){
			    				 AlertMesConfirm +='<div class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'
			    					+'<div class="heading">Solution Media</div>'
			 						+ '<div class="inners"><p>'
			 						+ ' With reference to the follwing clip answer the question'
			 						+ ' </p>'
			 						+'  <video controls  >'
			 						+ '            <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+testData.data["TOPIC" + topicID][l].QUES[m].QSOLMEDIA+'" type="video/mp4"  class"">'
			 						+ ' <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+testData.data["TOPIC" + topicID][l].QUES[m].QSOLMEDIA+'" type="video/ogg">'
			 						+ '            Your browser does not support the video tag.'
			 						+ '         </video>' 
			 						+'</div>'
			    			 }
					
					
					AlertMesConfirm += '</div>' 
						
									  
						+ ' </div>'
					                +'    <div class="modal-footer">'
									+ '<button type="button" class="btn btn-success" data-dismiss="modal"  >OKAY</button>&nbsp;&nbsp;'
//									+ '<button type="button" class="btn btn-danger" data-dismiss="modal">No</button>'
//					                + '  <button type="button" class="close" data-dismiss="modal">OKAY</button>'
									+ '</div>'
					
								  + '    </div>'
								  + '  </div>'
								  + '</div>'
						//alert modal end
						+ '</div>' // subject_content close
						+ '</div>'// main row close
						+ '</div>' // container close
						
						$("#practiceTestDiv").append(AlertMesConfirm);
				     	com.coep.test.mathJax.renderMathJax();
					
				} 
			
			
			$("#Loading").css("display","none");
	}

	
})(com.coep.test.practiceTest, com.coep.test.practiceTestTimer, com.coep.test.AlertMessage, com.coep.test.mathJax, com.coep.test.addProblem, com.coep.test.home);