<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META name="GENERATOR" content="IBM WebSphere Studio">

<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" --%>
<%-- 	pageEncoding="ISO-8859-1"%> --%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!-- <html lang="en"> -->
<!-- <head> -->
<!-- <meta charset="utf-8"> -->
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>गणित ऑलिम्पियाड</title>
<link rel="icon" href="resource/images/it-olympiad2017_logo.png" />
<!-- Bootstrap core CSS -->
<link href="resource/css/bootstrap.min.css" rel="stylesheet">
<link href="resource/css/latofonts.css" rel="stylesheet">
<link href="resource/css/latostyle.css" rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Lato'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Lato:400,300,700'
	rel='stylesheet' type='text/css'>
<!-- Custom styles for this template -->
<link href="resource/custom.css" rel="stylesheet">
<link href="resource/css/style.css" rel="stylesheet">
<link href="resource/css/new-css-for-js.css" rel="stylesheet">
</head>
<style>


.start-text-btn{
	border-radius:10px;
padding: 20px;
 position: fixed;
  bottom: 40px;  
  z-index: 999;
 background-color:#012333;
  width: auto;
  height: auto;
  margin-right: 15px;
 right:10px;
 font-size:18px;
 color:#fff;
 letter-spacing: 2px;
 
}
.font-size{ font-size:18px;}
.fontwt-600 {
	font-weight: 600;
}

.cust-b {
	padding: 10px;
	border-right: 2px solid #cacdcd;
}

.no-border {
	border: none;
}

.font-marathi {
font-size: 15px;
font-weight: normal;
}

p {
	font-size: 16px;
}

.support {
	font-size: 14px;
	color: #790000 !important;
	font-weight: bold;
}

@media screen and (max-width:768px)
{ 
.headline-margin{margin-top:70px;}
.col-xs-12{text-align:center;}
.footer{ background-color: #d6d5d5; border-top:2px solid #012333;}
.start-text-btn{
	border-radius:10px;
padding: 15px;
 position: fixed;
  bottom: 60px;  
  right:5px;
 font-size:18px;
 
}
}

</style>
<body style="padding-bottom: 100px;">
<%-- 
	<nav class="navbar navbar-default navbar-fixed-top navbar-bg-color ">
	<div class="container-fluid base-padding height-5">
		<div class="navbar-header r4_5-padding">
			<span class="navbar-brand logo-atnavbar-padding" href="#"><img
				class="img-responsive" src="resource/images/IntegreT_logo.png"></span>
			<span class="navbar-brand first-text-after-logo ">Integrated<br>Technologies
				(IT)<br>Olympiad
			</span>
		</div>

		<ul class="nav navbar-nav navbar-right" style="display: flex">
			<li class="dropdown">
				<div
					class="pull-left hidden-xs hidden-sm profile-descri-outer-block">
					<h3>Hi ${FN}</h3>
					<!-- 						<h5 class="text-align-right">user cp</h5> -->
				</div> <!-- <a href="#" > --> <img id="userImage"
				src="resource/images/user.png"
				class="profile-pic  display-inline-block pointer t1-margin"
				data-toggle="dropdown" aria-expanded="false" title="Hi ${FN}">


									<span 					class="icon-down-arrow-prof icon-down-arrow dropdown-toggle pointer"
									data-toggle="dropdown" aria-expanded="false"></span> <!-- </a> -->

				<!-- Link or button to toggle dropdown -->
				<ul class="dropdown-menu" role="menu"
					aria-labelledby="dropdownMenu1">
					<!-- 						<li id="profilelink" class="dd-menu" role="presentation"><a -->
					<!-- 							role="menuitem" tabindex="-1" -->
					<!-- 							href="javascript:com.coep.test.userProfile.onMyProfileClick();"><span -->
					<!-- 								class="glyphicon glyphicon-user "></span>&nbsp;&nbsp;My Profile</a></li> -->
					<li id="ChangePWDLink" class="dd-menu" role="presentation"><a
						role="menuitem" tabindex="-1" href="ChangePasswordLink"><span
							class="glyphicon  glyphicon-lock "></span>&nbsp;&nbsp;Change
							Password</a></li>
<%-- 					<li id="logoutlink" class="dd-menu" role="presentation"><a --%>
<%-- 						role="menuitem" tabindex="-1" href="j_spring_security_logout"><span --%>
<%-- 							class="glyphicon glyphicon-log-out "></span>&nbsp;&nbsp;Log Out</a></li> --%>
				</ul>
			</li>
		</ul>



	</div>




	</nav>

 --%>
 <section class="logout">
<!--     <div class="logout"> -->
<%--                     <h3 class="white-color">Hi! ${FN}</h3> --%>
<!--                     &nbsp;&nbsp;<span class="glyphicon glyphicon-menu-down dropdown-toggle pointer white-color" data-toggle="dropdown" aria-expanded="false"></span> -->
<!--                     <ul class="dropdown-menu right-10" role="menu" aria-labelledby="dropdownMenu1"> -->
<!--                         <li id="ChangePWDLink" class="dd-menu" role="presentation"><a role="menuitem" tabindex="-1" href="ChangePasswordLink"><span class="glyphicon glyphicon-lock "></span>&nbsp;&nbsp;Change Password</a></li> -->
<!--                         <li id="logoutlink" class="dd-menu" role="presentation"><a role="menuitem" tabindex="-1" href="/VirtualMathsLab/logout"><span class="glyphicon glyphicon-log-out "></span>&nbsp;&nbsp;Log Out</a></li> -->
<!--                     </ul> -->
<!--                 </div> -->
<!--     </section> -->
	<div class="container-fluid base-padding " id="main-div">
		<div class="row">


			<script>

    //Predefined Supporte Browsers and versions
    var ourBrowser = [{ 'name': 'Chrome', 'version': 51 }, { 'name': 'Firefox', 'version': 47 }, { 'name': 'Safari', 'version': 9 }, { 'name': 'Internet Explorer', 'version': 10 }, { 'name': 'Internet Explorer', 'version': 11 }];
    var browsers = get_browser();
    var browserCompatibility = 0;

    //Check Users Browsers and Its Version and return it
    function get_browser() {
        var ua = navigator.userAgent, tem, M = ua.match(/(opera|chrome|safari|firefox|msie|trident(?=\/))\/?\s*(\d+)/i) || [];
        if (/trident/i.test(M[1])) {
            tem = /\brv[ :]+(\d+)/g.exec(ua) || [];
            return { name: 'Internet Explorer', version: (tem[1] || '') };
        }
        if (M[1] === 'Chrome') {
            tem = ua.match(/\bOPR\/(\d+)/)
            if (tem != null) { return { name: 'Opera', version: tem[1] }; }
        }
        M = M[2] ? [M[1], M[2]] : [navigator.appName, navigator.appVersion, '-?'];
        if ((tem = ua.match(/version\/(\d+)/i)) != null) { M.splice(1, 1, tem[1]); }
        return {
            name: M[0],
            version: M[1]
        };
    }

    //Check Users Browsers and Its Version and return true/false
    function browserCompatibilityResult() {
        $.each(ourBrowser, function (index, ourBrowsers) {
            if (browsers.name == ourBrowsers.name && browsers.version >= ourBrowsers.version) {
                browserCompatibility = 1;
            }
        });
    }

</script>



			<div
				class="col-sm-8 col-md-8 col-md-offset-2 col-sm-offset-2 text-align-justify headline-margin">
				<h2 class="headline">
					WELCOME!
					</h2>
					<hr/>
					<span>
						<p class="font-chng">The <b>VIRTUAL MATHS LAB 2020</b> is an opportunity for you to test various mathematical skills. 
						The focus of this test is mainly on testing your knowledge of fundamentals in the respective mathematical domain. 
						The strong fundamental knowledge will work as foundation for the execution of the further tests. 
						Please take this test seriously as it will be a true reflection your capabilities. 
						Do not use any unfair means, as it will ultimately affect your progress.</p>

					</span>
					
					<h2 class="headline">|| स्वागतम ||</h2><hr/>
						<div class="font-marathi" lang="hi" style="line-height: 2;">
					<span class=""><b>" गणित ऑलिम्पियाड "</b></span> ही तुम्हा सर्वांसाठी तुमचे गणितातील कौशल्य सिध्द करण्याची सुवर्णसंधी आहे. सदर संधीचा तुम्ही सुयोग्य वापर करून व त्यातील कौशल्ये आत्मसात करून तुम्ही पुढील वाटचाल करू शकाल. सदर संगणकीय परीक्षा तुम्ही आत्मविश्वासाने व पूर्णपणे सचोटीने दिल्यास आपली गणित विषयातील प्रगती तुम्हाला जोखता येईल. मी तुम्हा सर्वांना या संगणकीय परीक्षेसाठी शुभेच्छा देतो.
					</div>
					<br/>
					<br />
					<h2 class="headline">INSTRUCTIONS</h2><hr></hr>
						<ul>
							<li class="b10-margin"><p>The online exam is a commonly used tool by many Institutes.</p></li>
							<li class="b10-margin"><p>We assume applicant taking the online test has a basic familiarity with computers and is 
							comfortable with the use of keyboard and mouse.</p></li>
							<li class="b10-margin"><p>There are various sections for the test and each section contains multiple questions. 
							The time is allotted for each question wherein you have to solve the questions as they appear. Once the time has elapsed you can't change/view the questions for that section.</p></li>
							<li class="b10-margin"><p>You are advised to refrain from unfair means and appear for this examination with utmost sincerity.</p></li>
						</ul>
						
					<h2 class="headline">महत्वाच्या सुचना</h2><hr></hr>
						<div class="font-marathi" lang="hi" style="line-height: 1.5;">
						<ul>
						<li class="b10-margin">संगणकीय परिक्षा ही आधुनिक युगात वापरली जाणारी महत्त्वाची पध्दती आहे.
						<li class="b10-margin">सदर परीक्षेसाठी लागणारे संगणकाचे ज्ञान तुम्हाला अवगत असेल अशी आमची खात्री आहे.
						<li class="b10-margin">सदर परीक्षेसाठी विविध प्रश्न विचारले जाणार असुन प्रत्येक प्रश्नाला विशिष्ट वेळ असणार आहे सदरची वेळ संपण्याच्या आधी तुम्हाला उत्तर द्यायचे आहे वेळ संपल्यानंतर तुम्हाला उत्तर बदलता येणार नाही.
						<li class="b10-margin">कोणत्याही प्रकारची अनैतिक पध्दतीचा वापर आपण या परिक्षेत करणार नाही अशी आम्हाला खात्री आहे.
						</ul>
						</div>
					<br/>
					<h2 class="headline">DURING THE EXAM</h2><hr></hr>
					<ol>
						<li class="b10-margin"><p>The test consists of multiple-choice questions (MCQ type). In this, for each given question, the applicant must choose the right, or the closest, answer from among the choices given.</p></li>
						<li class="b10-margin"><p>All questions are mandatory.</p></li>
						<li class="b10-margin"><p>Green flag indicates question is answered.</p></li>
						<li class="b10-margin"><p>To proceed to the next question click on the next button at the bottom. Once it is clicked going back to the previous question is not allowed.</p></li>
					</ol>
					
					<h2 class="headline">परीक्षा चालू असताना</h2><hr></hr>
					<div class="font-marathi" lang="hi" style="line-height: 1.5;">
					<ol>
					<li class="b10-margin">या परीक्षेतील प्रश्न हे <b class="font-chng">" बहुपर्यायी "</b> स्वरुपाचे असून तुम्हाला त्याचे अचूक उत्तर द्यायचे आहे.त्यासाठी योग्य पर्यायावर <span style="color:red">" क्लीक "</span> करा.
					<li class="b10-margin">सर्व प्रश्नांची उत्तरे आपल्याला दयावयाचे आहेत.
					<li class="b10-margin">तुमच्या उजव्या बाजूस असलेल्या रकान्यामध्ये उत्तरे दिलेले प्रश्न  <span style="color:green">" हिरव्या रंगाने "</span> दिसतील.
					<li class="b10-margin">पुढील प्रश्नास जाण्यासाठी  <span style="color:red">" NEXT "</span> बटण दाबा. एकदा दिलेल्या प्रश्नाला परत जाता येणार नाही. 
					</ol>
					</div>
					<hr/>
					<p class="hidden-msg">Note: You can view  e score card  indicating your performance after 24 hours of examination. The same can be downloaded by signing in your account.</p>
			</div>
			<br />

			<!-- <div id="sectInfo"
				class="input-group display-inline-flex col-sm-8 col-md-8 col-md-offset-2 col-sm-offset-2">
											<span class="sec-time">I-10mins</span> <span class="sec-time">II-20-mins</span>
											<span class="sec-time">III-30-mins</span>
			</div>
 -->
			<br />
			<br />
			<table
				class="input-group display-inline-flex col-sm-8 col-md-8 col-md-offset-2 col-sm-offset-2">
				<tr>
					<td class="t1-padding"><span class="support">Supported
							Browsers:</span></td>
					<td class="t1-padding"><img
						src="resource/images/icons/chrome.png" class="l15-margin"
						title="Google Chrome (Version 51+)" width="30" /><b
						class="cust-b"> ( 51+ ) </b> <img
						src="resource/images/icons/mozilla.png" class="l15-margin"
						title="Mozilla Firefox (Version 47+)" width="30" /><b
						class="cust-b"> ( 47+ ) </b> <img
						src="resource/images/icons/safari.png" class="l15-margin"
						title="Safari (Version 9+)" width="30" /><b class="cust-b"> (
							9+ ) </b> <img src="resource/images/icons/internet-explorer10.png"
						class="l15-margin" title="Internet Explorer (Version 10)"
						width="30" /><b class="cust-b"> ( 10 ) </b> <img
						src="resource/images/icons/internet-explorer11.png"
						class="l15-margin" title="Internet Explorer (Version 11)"
						width="30" /><b class="cust-b no-border"> ( 11 ) </b></td>

				</tr>
				<tr>
					<td class="t1-padding"><span class="support">Supported
							Resolution:</span></td>
					<td class="t1-padding">
						<h3 class="support l15-margin">Greater than 1366
							* 768</h3>
					</td>
				</tr>
				<tr>
					<td class="t1-padding"><strong></strong> <span
						class="support">Supported Operating Systems:</span></td>
					<td class="t1-padding"><img
						src="resource/images/icons/windows.png" class="l15-margin"
						title="Windows" width="30" /> <img
						src="resource/images/icons/windows7.png" class="l15-margin"
						title="Windows 7" width="30" /> <img
						src="resource/images/icons/windows8.png" class="l15-margin"
						title="Windows 8" width="30" /> <img
						src="resource/images/icons/mac.png" class="l15-margin"
						title="Mac OS" width="30" /><img
						src="resource/images/icons/CentOS.png" class="l15-margin"
						title="CentOS" width="30" /><img
						src="resource/images/icons/Ubuntu.png" class="l15-margin"
						title="Ubuntu" width="30" /></td>
				</tr>
			</table>








			<div id="checkCompatibilityModal"
				class="modal fade check-compatibility-modal" tabindex="-1"
				style="padding-left: 17px; z-index: 1111">
				<div class="modal-dialog  modal-lg" style="border-radius: 5px;">
					<div class="modal-content" style="margin-top: 250px">
						<div class="modal-header">
							<label class="modal-title">BROWSER COMPATIBILITY CHECK </label>
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">x</button>
							<h4 class="modal-title"></h4>
						</div>
						<div class="modal-body t1-b1-padding">

							<div class="row">
								<div class="col-sm-12">
									<span class="recommend-browsers-data">
										<p class="prim-blue">Supported Browsers and Browsers
											Version Are :</p>
										<p class="prim-text-black-color fontwt-600"
											id="recommendedBrowsers">
											<img src="resource/images/icons/chrome.png"
												class="l15-margin" title="Google Chrome (Version 51+)"
												width="35" /><b class="cust-b"> ( 51+ ) </b> <img
												src="resource/images/icons/mozilla.png" class="l15-margin"
												title="Mozilla Firefox (Version 47+)" width="35" /><b
												class="cust-b"> ( 47+ ) </b> <img
												src="resource/images/icons/safari.png" class="l15-margin"
												title="Safari (Version 9+)" width="35" /><b class="cust-b">
												( 9+ ) </b> <img
												src="resource/images/icons/internet-explorer10.png"
												class="l15-margin" title="Internet Explorer (Version 10)"
												width="35" /><b class="cust-b"> ( 10 ) </b> <img
												src="resource/images/icons/internet-explorer11.png"
												class="l15-margin" title="Internet Explorer (Version 11)"
												width="35" /><b class="cust-b no-border"> ( 11 ) </b>
										</p>
									</span> <span class="user-browser-data">
										<p class="prim-blue">Your Browser and Browser Version :</p>
										<p class="prim-text-black-color fontwt-600 l15-margin"
											id="userBrowsers">
											<script type="text/javascript">
                                    document.write(browsers.name + "&nbsp;:&nbsp;");
                                    document.write(browsers.version);
                                </script>
										</p>
									</span> <span class="user-browser-data">
										<p class="prim-blue">Supported Resolution :</p>
										<p class="prim-text-black-color fontwt-600 l15-margin"
											id="userBrowsers">Greater than 1366 * 768</p>
									</span>
								</div>
							</div>

							<div class="row">
								<div class="col-sm-12">

									<div
										class="alert alert-warning cust-margin browser-compatibility t20-margin"
										role="alert" id="browserCompatibilityError">
										<span class="glyphicon glyphicon-remove-circle"></span>&nbsp;&nbsp;You
										don't have either Recommended browser or version installed.
										Please ensure that you have recommended browser & version and
										try again.
									</div>
								</div>
							</div>

						</div>
						<div class="modal-footer t1-b1-padding">
							<span class="pull-right"> <a
								class="btn btn-warning  cancel-popup-btn " data-dismiss="modal">Ok</a>
							</span>
						</div>


					</div>
				</div>
			</div>


			<!-- 			<div class="col-sm-12  l0-r0-padding"> -->
			<!-- 				<button id="startTestBtn" type="submit" title="SUBMIT" -->
			<!-- 					class="pull-right btn custom-btn" onclick="FullScreenMode()">START -->
			<!-- 					TEST</button> -->
			<!-- 			</div> -->
           
            <!-- <div class="row">
			<div class="col-md-2 col-xs-6 col-md-offset-8 col-xs-offset-3 "  >
				<a href="javascript:FullScreenMode()"><button id="startTestBtn"
						type="submit" title="START TEST" class="btn custom-btn2">START
						TEST</button></a>
			</div>
			</div> -->
		</div>



	</div>

<div class=" "  >
				<a href="javascript:FullScreenMode()"><button id="startTestBtn"
						type="submit" title="START TEST" class="start-text-btn">START
						TEST</button></a>
			</div>

	 <footer class="footer">
  <div class="container-fluid base-padding">
  <div class="row " style="background-color: #666;">
    <div class="col-md-12 ">
                    <div class="col-md-6 col-xs-12 text-align-left">
                        <h5>
                            Virtual Maths Lab 2020
                        </h5>
                    </div>

                    <div class="col-md-6 col-xs-12 text-align-right">
                        <h5>
                            By COEP Technological University
                        </h5>
                    </div>
    </div>
                </div>
  </div>
 </footer>

	<!-- Placed at the end of the document so the pages load faster 
     
     launchIntoFullscreen(document.documentElement)
     
     -->
	<script src="resource/js/jquery-1.11.3.min.js"></script>
	<script src="resource/js/bootstrap.min.js"></script>
	<script src="resource/js/custom.js"></script>

	<!--  <script src="js/addproblem.js"></script> -->
	<!-- form validation js  -->
	<script src="resource/js/formvalidation.min.js"></script>
	<script src="resource/js/bootstrap-validation.min.js"></script>


	<script src="resource/core/namespace.js"></script>
	<script src="resource/core/ajaxMngr.js"></script>
	<script src="resource/src/addQuestionGroup.js"></script>
	<script src="resource/src/newStartTest.js"></script>

	<script type="text/javascript">
                $(".browser-compatibility").addClass("hidden");
                browserCompatibilityResult();
                if (!browserCompatibility) {
                    $("#browserCompatibilityError").removeClass("hidden");
                    //$(".finish-test-btn").attr("disabled", "disabled");
                }
            </script>
	<script type="text/javascript">
	var gender = '<%=request.getAttribute("GENDER")%>';

		if (gender == '1') {
			$("#userImage").attr("src", "resource/images/male.png");
		} else if (gender == '2') {
			$("#userImage").attr("src", "resource/images/female.jpg");
		} else {
			$("#userImage").attr("src", "resource/images/male.png");
		}

		var data =	<%=request.getAttribute("secData")%>;
		var htm = '';
		for ( var key in data) {

			htm += '<span class="sec-time" style="text-align: center;"><b>'
					+ key + ' - ' + data[key] + ' mins</b></span>&nbsp;';
		}
		$("#sectInfo").html(htm);

		function launchIntoFullscreen(element) {
			if (element.requestFullscreen) {
				element.requestFullscreen();
			} else if (element.mozRequestFullScreen) {
				element.mozRequestFullScreen();
			} else if (element.webkitRequestFullscreen) {
				element.webkitRequestFullscreen();
			} else if (element.msRequestFullscreen) {
				element.msRequestFullscreen();
			}
		}
	</script>

	<script type="text/javascript">
		function FullScreenMode() {
			if (browserCompatibility) {
				$('#startTestBtn').one('click', function(event) {
					event.preventDefault();
					//do something
					$(this).prop('disabled', true);
				});
				$('#startTestBtn').dblclick(function(e) {
					e.preventDefault();
				});
				$('#startTestBtn').attr('disabled', true);
				window
						.open(
								"test",
								"full",
								"dependent=yes, location=no, fullscreen=yes, scrollbars=yes, titlebar=yes, width= "
										+ window.screen.width
										+ ", height= "
										+ window.screen.height);
				setTimeout(function() {
					window.open("home", "_self", "full")
				}, 1000);

			} else {
				$('#checkCompatibilityModal').modal('show');
				return false;
			}

		}
	</script>

	<script type="text/javascript">
		/*  window.onload = function () {
		     if (typeof history.pushState === "function") {
		         history.pushState("jibberish", null, null);
		         window.onpopstate = function () {
		             history.pushState('newjibberish', null, null);
		             // Handle the back (or forward) buttons here
		             // Will NOT handle refresh, use onbeforeunload for this.
		         };
		     }
		     else {
		         var ignoreHashChange = true;
		         window.onhashchange = function () {
		             if (!ignoreHashChange) {
		                 ignoreHashChange = true;
		                 window.location.hash = Math.random();
		                 // Detect and redirect change here
		                 // Works in older FF and IE9
		                 // * it does mess with your hash symbol (anchor?) pound sign
		                 // delimiter on the end of the URL
		             }
		             else {
		                 ignoreHashChange = false;
		             }
		         };
		     }
		 }; */

		if (window.history && window.history.pushState) {

			$(window).on('popstate', function() {
				var hashLocation = location.hash;
				var hashSplit = hashLocation.split("#!/");
				var hashName = hashSplit[1];

				if (hashName !== '') {
					var hash = window.location.href;
					if (hash != '') {
						window.location.href = hash;
						//               alert('Back button was pressed.');
					}
				}
			});

			window.history.pushState('forward', null, '');
		}
	</script>

</body>
</html>
