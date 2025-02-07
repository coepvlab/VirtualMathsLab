<!-- <!DOCTYPE HTML > -->
<!-- <HTML> -->
<!-- <HEAD> -->
<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" --%>
<%-- 	pageEncoding="UTF-8"%> --%>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" /> -->
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge" /> -->
<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->


<!-- <script -->
<!-- 	src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS_HTML"> -->
<!-- </script> -->

<!-- <title>Candidate Test</title> -->
<!-- <link rel="icon" href="resource/images/it-olympiad2017_logo.png" /> -->
<!-- <link href="resource/css/bootstrap-material-design.css" rel="stylesheet"> -->
<!-- <link href="resource/css/ripples.min.css" rel="stylesheet"> -->
<!-- <link href="resource/css/bootstrap.min.css" rel="stylesheet"> -->
<!-- <link href="resource/css/latofonts.css" rel="stylesheet"> -->
<!-- <link href="resource/css/latostyle.css" rel="stylesheet"> -->
<!-- <link href='https://fonts.googleapis.com/css?family=Lato' -->
<!-- 	rel='stylesheet' type='text/css'> -->
<!-- <link href='https://fonts.googleapis.com/css?family=Lato:400,300,700' -->
<!-- 	rel='stylesheet' type='text/css'> -->

<!-- <link href="resource/custom.css" rel="stylesheet"> -->
<!-- <link href="resource/css/new-css-for-js.css" rel="stylesheet"> -->
<!-- <link href="resource/candidate.css" rel="stylesheet"> -->
<!-- <link href="resource/css/style.css" rel="stylesheet"> -->
<!-- <link href="resource/css/showToast.css" rel="stylesheet"> -->
<!-- <link href="resource/css/jquery.countdown.css" rel="stylesheet"> -->
<!-- <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed" -->
<!-- 	rel="stylesheet"> -->

<!-- </head> -->

<!-- <style> -->
/* body { */
/* 	-webkit-transition: background-color 1s; */
/* 	transition: background-color 1s; */
/* 	font-family: 'Roboto Condensed', sans-serif; */
/* } */

/* html, body { */
/* 	min-height: 100%; */
/* } */

/* body.loading { */
/* 	background: #f5eee8 */
/* 		url('https://www.creditmutuel.fr/cmne/fr/banques/webservices/nswr/images/loading.gif') */
/* 		no-repeat 50% 50%; */
/* 	-webkit-transition: background-color 0; */
/* 	transition: background-color 0; */
/* 	opacity: 0; */
/* 	-webkit-transition: opacity 0; */
/* 	transition: opacity 0; */
/* } */

/* .navbar, .navbar.navbar-default { */
/* 	background-color: transparent; */
/* } */

/* .navbar-default { */
/* 	background-color: transparent; */
/* 	border: none; */
/* } */

/* styles: { ". */
/* 	MathJax_Display ": {text-align: "left" */
/* } */

/* , */
/* } */
/* @media screen and (max-width:1520px) and (min-width:950px) { */
/* 	nav .ul-width { */
/* 		width: 20%; */
/* 	} */
/* 	nav .li-width { */
/* 		width: 100%; */
/* 		padding: 0; */
/* 		text-align: center; */
/* 	} */
/* 	.user-name { */
/* 		margin: 40px 20px 0 0px; */
/* 		color: #4fb1dd; */
/* 		font-size: 18px; */
/* 	} */
/* 	.hasCountdown, . fontsize-2em { */
/* 		font-size: 20px; */
/* 	} */
/* 	.time-counter { */
/* 		font-size: 30px; */
/* 	} */
/* } */

/* @media screen and (max-width:950px) and (min-width:768px) { */
/* 	.testsection-name { */
/* 		font-size: 18px; */
/* 		padding: 20px 40px 0 150px; */
/* 	} */
/* 	.maindiv-margin { */
/* 		margin-top: 210px; */
/* 		padding: px; */
/* 	} */
/* 	nav .ul-width { */
/* 		width: 19%; */
/* 	} */
/* 	nav .li-width { */
/* 		width: 100%; */
/* 		padding: 0; */
/* 		text-align: center; */
/* 	} */
/* } */

/* @media screen and (max-width:768px) { */
/* 	.time-counter { */
/* 		font-size: 20px; */
/* 	} */
/* 	.testsection-name { */
/* 		font-size: 15px; */
/* 		padding: 20px 40px 0 150px; */
/* 	} */
/* 	.maindiv-margin { */
/* 		margin-top: 160px; */
/* 		padding: px; */
/* 	} */
/* 	.col-xs-12 { */
/* 		text-align: center; */
/* 	} */
/* 	nav .ul-width { */
/* 		width: 60%; */
/* 	} */
/* 	nav .li-width { */
/* 		width: 100%; */
/* 		padding: 0; */
/* 		text-align: center; */
/* 	} */
/* 	.test-question-font-size { */
/* 		text-align: center; */
/* 	} */
/* } */
<!-- </style> -->

<!-- <body class="loading"> -->

<!-- 	<nav class="navbar navbar-default navbgcolor navbar-fixed-top" -->
<!-- 		id="navContent">Nav contents comes up here</nav> -->

<!-- 	<div class="container-fluid maindiv-margin" id="main-div"></div> -->

<!-- 	<div id="cdialog" class="modal fade in" tabindex="-1" -->
<!-- 		style="padding-left: 17px; z-index: 1111"> -->
<!-- 		<div class="modal-dialog  modal-md" style="border-radius: 5px;"> -->
<!-- 			<div class="modal-content" style="margin-top: 250px"> -->
<!-- 				<div class="modal-header"> -->
<!-- 					<label class="modal-title"></label> -->
<!-- 					<button type="button" class="close" data-dismiss="modal" -->
<!-- 						aria-hidden="true">�</button> -->
<!-- 					<h4 class="modal-title"></h4> -->
<!-- 				</div> -->
<!-- 				<div class="modal-body t1-b1-padding" id="cdlgBody" -->
<!-- 					style="font-size: 14px;"> -->
<!-- 					<p id="taggedQueWarning" class="red-color hide" -->
<!-- 						style="font-size: 14px;">Some 'Tagged' question(s) are -->
<!-- 						unanswered.</p> -->
<!-- 				</div> -->
<!-- 				<div class="modal-footer" id="cdlgFoot"> -->
<!-- 					<a id='cfNo' class="btn btn-warning pull-right  cancel-popup-btn " -->
<!-- 						data-dismiss="modal">No</a> <a id='cfYes' -->
<!-- 						class="btn btn-primary pull-right  finish-test-btn " -->
<!-- 						data-dismiss="modal" onclick="return submitTheAnswer()">Yes</a> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->

<!-- 	<hr> -->

<!-- 	<footer class="footer"> -->
<!-- 		<div class="container-fluid base-padding"> -->
<!-- 			<div class="row "> -->
<!-- 				<div class="col-md-12 " style="background-color: #666;"> -->
<!-- 					<div class="col-md-6 col-xs-12 text-align-left"> -->
<!-- 						<h5>Virtual Maths Lab 2020</h5> -->
<!-- 					</div> -->

<!-- 					<div class="col-md-6 col-xs-12 text-align-right"> -->
<!-- 						<h5>By COEP Technological University</h5> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</footer> -->
<!-- 	<script src="resource/js/jquery-1.11.3.min.js"></script> -->
<!-- 	<script src="resource/js/bootstrap.min.js"></script> -->
<!-- 	<script src="resource/js/custom.js"></script> -->
<!-- 	<script src="resource/js/formvalidation.min.js"></script> -->
<!-- 	<script src="resource/js/bootstrap-validation.min.js"></script> -->
<!-- 	<script src="resource/core/namespace.js"></script> -->
<!-- 	<script src="resource/src/newStartTest.js"></script> -->
<!-- 	<script src="resource/src/newStartTestForGO.js"></script> -->
<!-- 	<script src="resource/src/completeTest.js"></script> -->
<!-- 	<script src="resource/js/showToast.js"></script> -->
<!-- 	<script src="resource/js/material.min.js"></script> -->
<!-- 	<script src="resource/js/ripples.min.js"></script> -->
<!-- 	<script src="resource/js/jquery.countdown.min.js"></script> -->
<!-- 	<script type="text/javascript"> -->
    
// 	var body = document.getElementsByTagName('body')[0];
// 	var removeLoading = function() {
// 		setTimeout(function() {
// 			body.className = body.className.replace(/loading/, '');
// 		}, 2000);
// 	};
// 	removeLoading();


<%-- 	var testData = <%= request.getAttribute("data")%>; --%>
<%-- 	var firstName='<%=request.getAttribute("FN")%>' --%>
   
// 		window.onload = function () {

// 			if(testData["done"] == false){
// 				console.log(testData["done"]);
// 				$("#main-div").html("<h1 class='secondary-text-color'>"+ testData["msg"]+ "</h1>");
				
// 				}else if(testData["RESUME"] == false){
				
// 					if(testData["GANIT"] == false){
// 						var sectionId = testData.sections[0];
						 
// 						com.coep.test.student.CandidateTest(testData,sectionId);
// 						com.coep.test.student.startTestNavFunction(testData,sectionId,firstName);
						
// 						var timeDuration = 60 * testData.data["time"+sectionId]; 
// 						display = document.querySelector('.time-counter');
// 						com.coep.test.student.startTimer(timeDuration, display);
		
// 					}else{
						
// 						var sectionId = testData.sections[0];
// 						com.coep.test.student.startTestNavFunctionForGO(testData,sectionId,firstName); 
// 						com.coep.test.student.CandidateTestForGO(testData,sectionId);
						
// 						var timeDuration = 60 * testData["TT"] + 600000; 
// 						display = document.querySelector('.time-counter');
// 						com.coep.test.startTestGO.startTimer(timeDuration, display);
// 					}
					
// 					}else if(testData["RESUME"] == true){
					
// 						if(testData["GANIT"] == false){
							
// 							var sectionId = testData["CURRSEC"];
							 
// 							com.coep.test.student.CandidateTest2(testData,sectionId);
							
// 							com.coep.test.student.startTestNavFunction(testData,sectionId,firstName);
							
// 							var timeDuration =  testData.data["time"+sectionId]; 
// 							display = document.querySelector('.time-counter');
// 							com.coep.test.student.startTimer(timeDuration, display);
							
// 						}else{
							
// 							var sectionId = testData["CURRSEC"];
// 							com.coep.test.student.startTestNavFunctionForGO(testData,sectionId,firstName); 
// 							com.coep.test.student.CandidateTestForGO2(testData,sectionId);
							
// 							var timeDuration =  testData.data["time"+sectionId]; 
// 							display = document.querySelector('.time-counter');
// 							com.coep.test.startTestGO.startTimer(timeDuration, display);
// 						}
							
// 			};
			
// 	}
					
<!-- 				</script> -->

<!-- 	<script type="text/javascript"> -->

// getYesButtonOnDialog = function() {
// 		var text = '<button class="btn btn-success" type="button" id="cfYes">OK</button>';
// 		return text;
// 	};
   
//    clearConfirmDialogContent = function () {    	
//    		$('#cModalLabel').html('');
//    		$('#cdlgBody').html('');
//    		$('#cdlgFoot').html('');
//    		$('#cdialog').modal('hide');
//    };
       
<!-- </script> -->

<!-- 	<script type="text/x-mathjax-config"> -->
MathJax.Hub.Config({
  CommonHTML: { linebreaks: { automatic: true } },
  "HTML-CSS": { linebreaks: { automatic: true } },
         SVG: { linebreaks: { automatic: true } }
});
<!-- </script> -->

<!-- </body> -->
<!-- </html> -->
