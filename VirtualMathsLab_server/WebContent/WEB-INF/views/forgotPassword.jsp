
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

<title>Forgot Password</title>
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
<link href="resource/custom.css" rel="stylesheet">
<link href="resource/candidate.css" rel="stylesheet">
<link href="resource/css/style.css" rel="stylesheet">
<script type="text/javascript">
var email = '<%= request.getAttribute("email")%>';
var token = '<%= request.getAttribute("token")%>';
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

  
bodybgcolor{background-color:rgba(253, 251, 251, 0.9);}

  h1{ font-size:27px; color:#343148ff; text-align:center;}
 h2 {
	font-size: 20px;
	color: #343148ff;
	margin: 10px;
}
  .error-class {
	font-size: 13px;
	color: #f76b6b;
}

.form {
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

.form button {
	font-family: "Roboto", sans-serif;
	text-transform: uppercase;
	outline: 0;
	background: #4CAF50;
	width: 100%;
	border: 0;
	padding: 15px;
	color: #FFFFFF;
	font-size: 14px;
	-webkit-transition: all 0.3 ease;
	transition: all 0.3 ease;
	cursor: pointer;
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
	color: #4d4d4d;
	font-size: 12px;
}

.container .info span a {
	color: #000000;
	text-decoration: none;
}

.container .info span .fa {
	color: #EF3B3A;
}
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
.img img {
	width: 22%;
	height: 120px;
	margin: 10px 39%;
}
.btnbg {
	background-color: #343148ff;
    color: #fff;
    width: 50%;
    font-size: 15px;
    padding: 10px 0;
    letter-spacing: 2px;
    font-weight: bold;
    border-radius: 10px;
}
@media screen and (max-width:1000px) and (min-width:769px) {
	.footer {
	position: relative;
}
}
@media screen and (max-width:768px) {
.footer {position:relative;}
	.img img {
		width: 21%;
		height: 120px;
		margin: 10px 39%;
	}
}
@media screen and (max-width:468px) {
.footer {position:relative;}
	.img img {
		width: 30%;
    height: 120px;
    margin: 10px 34%;
	}
}
</STYLE>
</head>

<body>

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
	</nav> -->


	<div class="container-fluid ">
		<div class="row">
			<%-- 		 <form action="checkForgetPassData" method="POST"> --%>
			
				<div class="col-sm-12 offset-md-2 col-md-8 offset-lg-3 col-lg-6  offset-xl-3 col-xl-6">
					<!-- 			<div class="msgblock alert alert-success" id="success-msg"  -->
					<!-- 				style="text-align: center; font-weight: bold;" hidden></div> -->

					<!-- 				<div class="msgblock alert alert-danger" id="fail-msg"  -->
					<!-- 				style="text-align: center; font-weight: bold;" hidden></div> -->
                        
                        
					<div class="col-sm-12  forgotpasswordmargin ">
					<div class="img">
						<img src="resource/images/VirtualMathsLabLogo.png"
							class="image-responsive">
					</div>
					<h1>FORGOT PASSWORD</h1>
						
						<%-- 					<form id="forgotPassForm" name="forgotPassForm"> --%>
						<div class="col-sm-12">
						<label class="forget-heading" >Please enter
							registered Email ID</label>
							<input class="form-control col-sm-12 t15-margin "
								name='phoneNumber' id="phoneNumber" type="text"
								placeholder="Registered Email ID">
								<span id="message"></span>
						</div>
                        
						<div class="col-sm-12" style="margin-top: 30px" align="center">
							<!--                                      <button id="signInBtn" type="submit" title="Sign In" class="btn custom-btn" onclick = "com.coep.test.ajaxHandler.logindetails()">SIGN IN</button> -->
							
							<button id="forgotPass"
								onClick="javacript:com.coep.test.userProfile.forgotPwd()"
								class="btnbg">Submit</button>
						</div>
						<div class="col-sm-12" align="center">
							<a href="login" title="Sign In"><h2>Login</h2></a>
						</div>
						<%-- 					</form>			 --%>
					</div>
				</div>
			
			<%-- 			</form>  --%>
			<%-- 			<form action="checkForgetPassData" method="POST"> --%>
			<!-- 	<div>
				<fieldset>
				<h2>Forget Password</h2>
				<hr class="colorgraph">
				<div class="form-group">
                    <input type="text"	class="form-control input-lg text" id="vlabid" name='vid'   required="required" placeholder="VLab Id"/> 
				</div> 
				
				<div class="form-group">
                    <input type="text" class="form-control input-lg text"  id="emailid" name='emailid'  required="required" placeholder="Email Id"/>
				</div>	
				
				<hr class="colorgraph">
				<div class="row">
					<div class="col-md-12">
                        <button id ="emailId1" class="btn btn-lg btn-success btn-block cta-button" onClick="javacript:com.coep.test.userProfile.forgotPwd()"></button>
					</div>
				</div>
			</fieldset>
			</div> -->
			<%-- 		</form> --%>
		</div>
	</div>

	<footer class="footer">
		<div class="container-fluid base-padding">
			<div class="row ">
				<div class="col-md-12 ">
<!-- 					<div class="col-md-6 col-xs-12 text-align-left"> -->
						Virtual Maths Lab 2020
<!-- 					</div> -->
<br/>
<!-- 					<div class="col-md-6 col-xs-12 text-align-right"> -->
						By College of Engineering Pune 2020
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

	<script src="resource/core/namespace.js"></script>
	<script src="resource/core/ajaxMngr.js"></script>
	<script src="resource/src/addQuestionGroup.js"></script>
	<script src="resource/src/register.js"></script>

</body>
<script type="text/javascript">

var msg = '<%= request.getAttribute("msg")%>';

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
</script>


</html>
