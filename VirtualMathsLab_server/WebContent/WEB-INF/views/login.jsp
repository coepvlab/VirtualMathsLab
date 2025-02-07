
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>Login</title>
<link rel="icon" href="resource/images/VirtualMathsLabLogo.png" />
<!-- Material Design core CSS -->
<link href="resource/css/bootstrap-material-design.css" rel="stylesheet" />
<link href="resource/css/ripples.min.css" rel="stylesheet" />

<link rel="stylesheet" href="resource/css/bootstrap4.5.2.min.css">

<style>
.alert-msg{padding: 5px;
    color: #fff;
    border: 1px solid red;
    background: #ec5c5c;}
.active>h4 {
	color: white !important;
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
    font-weight:bold;
    text-align: center;
font-size:15px;
}
.loginOuter {
	
	background-color: rgba(52, 49, 72, 0.8);;
	height: auto;
	border-radius: 10px;
}

.loginInner {
	background-color: rgba(245, 245, 245, 0.6);
    height: auto;
    margin: 10px;
    border-radius: 10px;
    padding: 10px;
    border: 3px solid darkgray;
}
a:hover{color:#fff;}
 #forgotPwdLink {
 font-weight:bold;
	padding: 10px; 
 	color: #343148ff; 
 	font-size: 18px; 
 	margin: 10px; 

 } 

body{    
  background: url(resource/images/mathsbgn.jpg) ; 

  }

.bodybgcolor {
	background-color:#343148ff;
}

.RegInner h1 {
	font-size: 25px;
    color: #343148ff;
    margin: 10px;
}
h3 {
	padding-bottom: 10px;
	border-bottom: 3px solid #343148ff;
	text-align: center;
	font-size: 20px;
	width: 100%;
}
.loginInner h1 {
	font-size: 18px;
    background-color: #343148ff;
    margin: 5px 20px;
    color: #fff;
    padding: 10px 20px;
    border-radius: 10px;
}
.regLinkDiv{text-align: center;
    /* margin: 0 10%; */
    /* width: 80%; */
    background-color:rgba(245, 245, 245, 0.9);
    border-radius: 10px;
    padding: 15px 0;}
h2 {
font-weight:bold;
	font-size: 20px;
	color: #343148ff;
}

hr {
	width: 100%;
	background-color: #343148ff;
	height: 2px;
}

/* @import url(https://fonts.googleapis.com/css?family=Roboto:300); */

.bglogo {
	background-color: transprant;
	height: 1000px;
	padding-top: 10px;
}

.btnbgcolor {
border-radius: 10px;
	background-color: #343148ff;
}

.login-page {
	width: 360px;
	padding: 8% 0 0;
	margin: auto;
}

nav {
	padding: 20px 0;
}

nav li {
	list-style-type: none;
	float: left;
	width: 70px;
}

img {
width: 25%;
    height: 25%;
    margin: 2% 36%;
    border-radius: 10px;
    background: rgba(255, 255, 255, 0.7);
}

form a {
	background-color: #343148ff;
}

.form {
	position: relative;
	z-index: 1;
	background: #FFFFFF;
	max-width: 360px;
	margin: 0 auto 100px;
	padding: 10px;
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
	margin: 0 0 10px;
	padding: 10px;
	box-sizing: border-box;
	font-size: 14px;
}

.form button {
	font-family: "Roboto", sans-serif;
	text-transform: uppercase;
	outline: 0;
	background-color: #343148ff;
	width: 100%;
	border: 0;
	padding: 15px;
	color: #fff;
	font-size: 14px;
	-webkit-transition: all 0.3 ease;
	transition: all 0.3 ease;
	cursor: pointer;
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
label
{ font-size:13px ; font-weight:bold;}

#signInBtn{font-size: 17px;
    background-color: #343148ff;
    /* margin: 10px 20px; */
    color: #fff;
    width: 40%;
    font-weight: bold;
    padding: 5px 20px;
    border-radius: 10px;}
    
    .Links{font-size: 15px;
    background-color: #343148ff;
    /* margin: 10px 20px; */
    color: #fff;
    width: 100%;
    font-weight: bold;
    padding: 5px 20px;
    border-radius: 10px;}
    @media screen and (max-width:2100px) and (min-width:1550px) {
    .footer {
  width: 100%;
     position: fixed; 
    color: #fff;
   bottom: 0; 
    border-top: 3px solid #343148ff;
    background: rgba(52, 49, 72, 0.8);
    /* z-index: 1030; */
    padding: 5px;
    font-weight:bold;
    text-align: center;
font-size:15px;
}
    } 
    @media screen and (max-width:1550px) and (min-width:768px) {
    .footer {
  font-size:15px;
    margin-top:5px;
    background-color: #343148ff;
    position: relative;
    width: 100%;
    text-align:center;
    color:#fff;
    font-size:16px;
    vertical-align: middle;
    background-color:transprant;
}
}
 @media screen and (max-width:768px) and (min-width:468px) { 
  
img{    width: 25%;
    margin: 0 37%;
    padding: 10px 0;} 
    .footer {  font-size:15px;
    margin-top:5px;
    background-color: #343148ff;
    position: relative;
    width: 100%;
    text-align:center;
    color:#fff;
    vertical-align: middle;
    background-color:transprant;
  }

}  

@media screen and (max-width:468px) and (min-width:300px) {
img{width: 60%;
    margin: 0 20%;
    padding: 10px 0;}
.loginOuter {
    margin-top: 0;
    background-color: rgba(133, 98, 40, 0.7);
    height: auto;
    border-radius: 10px;
    padding: 10px;
}
h2{font-size:18px;}
.RegInner h1 {
font-weight:bold;
    font-size: 18px;
    color: #343148ff;
    margin: 10px;
}
.RegInner a {
    padding: 10px;
    color: #fff;
    font-size: 12px;
    margin: 4px;
    border-radius: 10px;
    background-color: #343148ff;
}
.loginInner {
    background-color: rgba(245, 245, 245, 0.6);
    height:auto;
    margin: 0px;
    border-radius: 10px;
    padding: 40px 0;
    border: 3px solid darkgray;
}
.loginInner h1 {
    font-size: 20px;
    background-color: #343148ff;
    margin: 10px;
    color: #fff;
    padding: 10px 20px;
    border-radius: 10px;
}
	.bglogo {
		background-color:;
		height: 250px;
		padding-top: 30px;
	}
	.bglogo img {
		width: 200px;
		height: 200px;
		margin: 0 auto;
	}

	body {
		height: auto;
	}
	.col-xs-12 {
		text-align: center;
	}
	.text-align-right, .text-align-left{background-color: #343148ff;}
	.footer {
	font-size:15px;
    margin-top:5px;
    background-color: #343148ff;
    position: relative;
    width: 100%;
    text-align:center;
    color:#fff;
    font-size:16px;
    vertical-align: middle;
    background-color:transprant;
    /*padding-bottom:15px;*/
}
#signInBtn{width:100%;}
}
</style>
</head>

<body style="padding: 0px;">
<div class="BodyBGOpacity">
<!-- 	<nav class="" style="background-color: #012333;"> -->
<!-- 		<div class="container-fluid"> -->
<!-- 			<div class="row"> -->
<!-- 				<ul class=" pull-right"> -->
<!-- 					<li><a href="welcome" title="HOME" style="color: #fff;">Home</a></li> -->
<!-- 					 		    <li><a href="login" title="LOGIN">Login</a></li>  -->
<!-- 										<li><a href="registration" title="REGISTER" style="color: #fff;">Register</a></li> -->
<!-- 				</ul> -->
<!-- 			</div> -->


<!-- 		</div> -->
<!-- 	</nav> -->

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12 offset-md-1 col-md-10 offset-lg-1 col-lg-10  offset-xl-1 col-xl-10">
<!-- 			 -->
				<div class=" col-sm-12 offset-md-1 col-md-10 offset-lg-1 col-lg-10  offset-xl-2 col-xl-8">
				<div class="row">
					

					<div class="loginInner col-md-12 ">
						<div class="col-sm-12  col-md-12 col-lg-12 col-xl-12">
						<div class="col-sm-12 offset-md-1 col-md-10 offset-lg-2 col-lg-8 offset-xl-2 col-xl-8">
						<img class="img-responsive"
							src="resource/images/VirtualMathsLabLogo.png"  >
							<h1 class="text-center">LOGIN</h1>
							<hr></hr>
							<div>
								<c:if test="${not empty error}">
									<div class="alert-msg">
										Your login attempt was not successful, try again.<br />
										Caused :
										${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
									</div>
								</c:if>
								<c:if test="${not empty err}">
									<div class="alert-msg">
										Sorry! Please click on the link in the same tab to start the
										test.<br> Please do not open test link in two different
										browsers or devices.
									</div>
								</c:if>
							</div>


							<form id="loginForm" name="loginForm" method="POST"
								action="j_spring_security_check" class="wow bounceIn">
								<div class="error-div t1-margin">
									<label id="lblErrorMessage" class="label-warning l10-padding"></label>
									<label id="lblErrorMsg" class="alert-danger l10-padding">
									</label>
								</div>
								<!-- Using Material Design frm 02-08-2016 so commented previous code-->
								<div
									class="col-sm-12 t1-margin form-group label-floating is-empty">
									<label for="EmailId" class=" col-sm-12 control-label"
										style="color: #000;font-weight:bold;">Registered Email Id or VirtualMathLab Id</label> <input
										type="text"
										class="col-sm-12  height-50 form-control l10-padding "
										name="j_username" id="j_username" autocomplete="off" required>
								</div>
								<div
									class="col-sm-12 t1-margin form-group label-floating is-empty">
									<label for="Password" class="col-sm-12 control-label"
										style="color: #000;font-weight:bold;">Password</label> <input type="password"
										class="col-sm-12 height-50 form-control l10-padding "
										name="j_password" id="j_password" autocomplete="off" required>
								</div>

								<!-- <div class="col-sm-12">
							<input class="form-control col-sm-12 t15-margin login-Controls"
								name='j_username' id="j_username" type="text"
								placeholder="Registered Email">
						</div>
						<div class="col-sm-12">
							<input class="form-control col-sm-12 t15-margin login-Controls"
								name="j_password" id="j_password" type="password"
								placeholder="Password">
						</div> -->


								<!--  <a id="lost-dialog" href="forgotPwdLink" class="forgotPassword-Link login-link text-center" 
            >Forgot Password?</a> -->

								<div class="col-sm-12 t1-margin" align="center">
									<button id="signInBtn" type="submit" title="Sign In">SIGN IN</button>
								</div>
								<%-- 			<a href="registration" ><center >Register Now</center></a> --%>
							</form>

							
									
								
<div class="regLinkDiv "><h3>Don't have an account?</h3><br/>
						<a href="registration" title="REGISTER" class="Links">REGISTER</a>&nbsp;Or&nbsp;<a href="welcome" title="HOME" class="Links">HOME</a>
						
						<br/><br/><a id="forgotPwdLink" href="forgotPwdLink" title="Forgot Password">Forgot Password ?</a><br/></div>
						
							<%-- <div align="center" class="col-sm-12 t1-margin">
	                <center><a href="registration" title="Register Now"><h2 class="">Register Now</h2></a></center>
	            </div>  --%>

						</div>
					</div>
				</div>

			</div>


		</div>
<!-- 		</div> -->
	</div>
	</div>



	<footer class="footer">
		<div class="container-fluid base-padding">
			<div class="row ">
				<div class="col-md-12 ">
<!-- 					<div class="col-md-6 col-xs-12 text-align-left"> -->
<!-- 						Virtual Maths Lab 2020 -->
<!-- 					</div> -->
<br/>
<!-- 					<div class="col-md-6 col-xs-12 text-align-right"> -->
						By COEP Technological University
<!-- 					</div> -->
				</div>
			</div>
		</div>
	</footer>
</div>

  <script src="resource/js/jquery-3.5.min.js"></script>
  <script src="resource/js/popper.min.js"></script>
  <script src="resource/js/bootstrap-4.5.min.js"></script>

	<script src="resource/js/custom.js"></script>
	<!-- form validation js  -->
	<script src="resource/js/formvalidation.min.js"></script>
	<script src="resource/js/bootstrap-validation.min.js"></script>
	<script src="resource/js/material.min.js"></script>
	<script src="resource/core/namespace.js"></script>
	<script src="resource/core/ajaxMngr.js"></script>
	<script src="resource/src/register.js"></script>
<script>
   
      </script>
	<script>
		$(function() {
			$.material.init();
		});
	</script>
</body>



</html>
