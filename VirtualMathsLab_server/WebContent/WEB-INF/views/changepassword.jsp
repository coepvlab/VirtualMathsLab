
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>Change Password</title>
<link rel="icon" href="resource/images/VirtualMathsLabLogo.png" />
<!-- Bootstrap core CSS -->
<link href="resource/css/toastr.css" rel="stylesheet"/>
<link href="resource/css/bootstrap.min.css" rel="stylesheet">
<link href="resource/css/latofonts.css" rel="stylesheet">
<link href="resource/css/bootstrap.min.css" rel="stylesheet">
<link href="resource/css/latofonts.css" rel="stylesheet">
<link href="resource/css/latostyle.css" rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Lato'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Lato:400,300,700'
	rel='stylesheet' type='text/css'>
<!-- Custom styles for this template -->
<!-- <link href="resource/custom.css" rel="stylesheet"> -->
<link href="resource/candidate.css" rel="stylesheet">
<link href="resource/css/style.css" rel="stylesheet">
<script type="text/javascript">
var email = '<%=request.getAttribute("email")%>';
var token = '<%=request.getAttribute("token")%>';
var url = window.location.href;
var str = url.split("?",2);
if(token == str[0]){
$("#j_username").val(email);	
}

</script>

<style>
.active>h4 {
	color: white !important;
}
</style>


<STYLE>
@import url(https://fonts.googleapis.com/css?family=Roboto:300);

.error-class {
	font-size: 13px;
	color: #f76b6b;
}
.forget-heading {width: 80%;
    margin: 0 auto; text-align:center;}
.footer {
	width: 100%;
	 position: fixed; 
	color: #fff;
	 bottom: 0; 
	border-top: 3px solid #343148ff;
	background: rgba(52, 49, 72, 0.8);
	/* z-index: 1030; */
	padding: 5px;
	font-weight: bold;
	text-align: center;
	font-size: 15px;
}



.bodybgcolor {
	background-color: rgba(253, 251, 251, 0.9);
padding: 0 0 50px 0;
}



.container-fluid /* .form {
	position: relative;
	z-index: 1;
	background: #FFFFFF;
	max-width: 360px;
	margin: 0 auto 100px;
	padding: 45px;
	text-align: center;
	box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0
		rgba(0, 0, 0, 0.24);
}
 */ 
.form input {
	font-family: "Roboto", sans-serif;
	outline: 0;
	background: #f2f2f2;
	width: 100%;
	border: 0;
	margin: 0 0 15px;
	padding: 15px;
	box-sizing: border-box;
	font-size: 14px;
}

form button {
	font-family: "Roboto", sans-serif;
	text-transform: uppercase;
	outline: 0;
	width: 100%;
	height: 50px;
	border: 0;
	padding: 15px;
	color: #FFFFFF;
	font-size: 14px;
	-webkit-transition: all 0.3 ease;
	transition: all 0.3 ease;
	cursor: pointer;
}

form {
	color: #343148ff;
}

.form button:hover, .form button:active, .form button:focus {
	background: #43A047;
}

.form .message {
	margin: 15px 0 0;
	color: #b3b3b3;
	font-size: 12px;
}

.form .message a {
	color: #4CAF50;
	text-decoration: none;
}

.form .register-form {
	display: none;
}

* /
.container {
	position: relative;
	z-index: 1;
	max-width: 300px;
	margin: 0 auto;
}

.container:before, .container:after {
	content: "";
	display: block;
	clear: both;
}

.container .info {
	margin: 50px auto;
	text-align: center;
}

.container .info h1 {
	margin: 0 0 15px;
	padding: 0;
	font-size: 36px;
	font-weight: 300;
	color: #1a1a1a;
}

.container .info span {
	color: #343148ff;
	font-size: 12px;
}

.container .info span a {
	color: #000000;
	text-decoration: none;
}

.container .info span .fa {
	color: #EF3B3A;
}

.img {
	position:;
	width: 100%;
	height: auto;
	padding: 0px 0;
	margin-bottom: 5px;
	:
}

.img img {
	width: 22%;
	height: 120px;
	margin: 10px 39%;
}

h1 {
	font-size: 28px;
	color: #343148ff;
}

h2 {
	font-size: 20px;
	color: #343148ff;
	margin: 10px;
}



.btnbg {
	background-color: #343148ff;
    color: #fff;
    width: 50%;
    font-size: 15px;
    /* padding: 10px; */
    letter-spacing: 2px;
    font-weight: bold;
    border-radius: 10px;
}
@media screen and (max-width:2000px) and (min-width:1500px) {
	.footer {
	width: 100%;
	position: fixed;
	color: #fff;
	 bottom: 0; 
	border-top: 3px solid #343148ff;
	background: rgba(52, 49, 72, 0.8);
	/* z-index: 1030; */
	padding: 5px;
	font-weight: bold;
	text-align: center;
	font-size: 15px;
}
}
@media screen and (max-width:768px) {
	.img img {
		width: 21%;
		height: 120px;
		margin: 10px 39%;
	}
	.footer{position: relative;}
}
@media screen and (max-width:468px) {
	.img img {
		width: 30%;
    height: 120px;
    margin: 10px 34%;
	}
	.footer{position: relative;}
}
</STYLE>
</head>

<body class="">

	<!-- <nav class="navbar navbar-default navbar-bg-color navbar-fixed-top">
		<div class="container-fluid navbar-base-padding height-5">
			<div class="navbar-header r4_5-padding">
				<a class="navbar-brand logo-atnavbar-padding" href="#"><img
					class="img-responsive" src="resource/images/IntegreT_logo.png"></a>
				<a class="navbar-brand first-text-after-logo " href="#">Integrated<br>Technologies
					(IT)<br>Olympiad
				</a>
			</div>

		</div>
	</nav>
 -->

	<div class="container-fluid  ">

		<div class="row">

			<%-- 		 <form action="checkForgetPassData" method="POST"> --%>

			<div
				class="col-sm-12 offset-md-2 col-md-8 offset-lg-3 col-lg-6  offset-xl-3 col-xl-6">

				<!-- 			<div class="msgblock alert alert-success" id="success-msg"  -->
				<!-- 				style="text-align: center; font-weight: bold;" hidden></div> -->

				<!-- 				<div class="msgblock alert alert-danger" id="fail-msg"  -->
				<!-- 				style="text-align: center; font-weight: bold;" hidden></div> -->

				<div class="col-sm-12 login-OuterDiv bodybgcolor">
					<div class="img">
						<img src="resource/images/VirtualMathsLabLogo.png"
							class="image-responsive">
					</div>
<h1 class="text-center" id="changePwdheading">CHANGE PASSWORD</h1>
					<div class="forget-heading" ></div>
					<form id="changepasswordForm" name="changepasswordForm"
						method="POST">
						<div class="col-sm-12">

							<div class=" col-sm-12 l0-r0-padding t1-b0-padding ">
								

								<span class="" id="message">Enter Old Password</span> <input
									type="password" name="oldpwd" id="oldpwd"
									class="regis form-control" placeholder="Old Password" /><br />
							</div>




							<div class=" col-sm-12 l0-r0-padding t1-b0-padding">
								<span class="" id="message">Enter New Password</span> <input
									type="password" name="newpwd" id="newpwd"
									class="regis form-control" placeholder="New Password" /><br />
							</div>




							<div class=" col-sm-12 l0-r0-padding t1-b0-padding">
								<span class="" id="message">Confirm New Password</span> <input
									type="password" name="confirmpwd" id="confirmpwd"
									class="regis form-control" placeholder="Re-enter New Password" /><br />
							</div>



							<!--  <hr></hr> -->

							<div class="col-sm-12" align="center">
								<!--                                      <button id="signInBtn" type="submit" title="Sign In" class="btn custom-btn" onclick = "com.coep.test.ajaxHandler.logindetails()">SIGN IN</button> -->

								<button id="changepassword" class=" btnbg ">Submit</button>



							</div>
							
					</form>
					
				</div>
				<h1></h1>
<div class="col-sm-12" align="center"
								style="margin-bottom: 50px;">
								<a href="home" title="Sign In"><h2 class="">Back</h2></a>
							</div>

			</div>
		</div>
	</div>
	
	<footer class="footer">
		<div class="container-fluid base-padding">
			<div class="row ">
				<div class="col-md-12 ">
					<!-- 					<div class="col-md-6 col-xs-12 text-align-left"> -->
					Virtual Maths Lab 2020
					<!-- 					</div> -->
					<br />
					<!-- 					<div class="col-md-6 col-xs-12 text-align-right"> -->
					By COEP Technological University
					<!-- 					</div> -->
				</div>
			</div>
		</div>
	</footer>

	<script src="resource/js/jquery-1.11.3.min.js"></script>
	<script src="resource/js/bootstrap.min.js"></script>
	<script src="resource/js/custom.js"></script>
	<script src="resource/js/toastr.js"></script>
	<!-- form validation js  -->
	<script src="resource/js/formvalidation.min.js"></script>
	<script src="resource/js/bootstrap-validation.min.js"></script>
	<script src="resource/js/jquery.validate.js"></script>

	<script src="resource/core/namespace.js"></script>
	<script src="resource/core/ajaxMngr.js"></script>
	<script src="resource/src/addQuestionGroup.js"></script>
	<script src="resource/src/register.js"></script>

</body>
<script type="text/javascript">
toastr.options = {
		  "closeButton": true,
		  "debug": false,
		  "newestOnTop": false,
		  "progressBar": true,
		  "positionClass": "toast-bottom-right",
		  "preventDuplicates": false,
		  "onclick": null,
		  "showDuration": "300",
		  "hideDuration": "1000",
		  "timeOut": "5000",
		  "extendedTimeOut": "2000",
		  "showEasing": "swing",
		  "hideEasing": "linear",
		  "showMethod": "fadeIn",
		  "hideMethod": "fadeOut"
		}

var msg = '<%=request.getAttribute("msg")%>';

	$(document).ready(function() {

		$("#changepasswordForm").validate({
			errorClass : "error-class",

			// Specify the validation rules
			rules : {

				oldpwd : "required",
				newpwd : "required",
				confirmpwd : {
					required : true,
					equalTo : "#newpwd"
				}
			},

			// Specify the validation error messages
			messages : {

				oldpwd : "Please enter old password",
				newpwd : {
					required : "Please enter new password"
				},
				confirmpwd : {
					required : "Please provide a confirm password",
					message : "password and confirm password must be same"
				},

			},
			submitHandler : function(form) {
				com.coep.test.userProfile.changPassword();
			}
		});

	});
</script>


</html>
