
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

<!-- Bootstrap core CSS -->
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
  .loginOuter{background-color: #fff; height:600px;}
body{background-image:url("resource/images/Mathematics2.jpg"); background-color:rgba(0, 0, 0, 0.5); background-repeat:no-repeat; background-size:100% 100%; height:1000px;}
  .bodybgcolor{background-color:rgba(253, 251, 251, 0.9);}
  h1{ font-size:40px; color:#012333;}
  h2{ font-size:20px; color:#012333;}
  hr{width:100%; background-color:#012333; height:2px;}

    @import url(https://fonts.googleapis.com/css?family=Roboto:300);

.bglogo{background-color:transprant; height:1000px; padding-top:10px;} 
.btnbgcolor{background-color:#012333; }
.login-page {
  width: 360px;
  padding: 8% 0 0;
  margin: auto;
}
nav{padding:20px 0; }
nav li{ list-style-type: none ;
float:left;
width:70px;
}
img{width:120px ; height:120px;  margin:10px auto;}
form a {background-color:#012333;}
.form {
  position: relative;
  z-index: 1;
  background: #FFFFFF;
  max-width: 360px;
  margin: 0 auto 100px;
  padding: 10px;
  text-align: center;
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
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
 background-color:#012333;
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


@media screen and (max-width:768px)
{

.bglogo{background-color:; height:250px; padding-top:30px;} 
.bglogo img{width:200px ; height:200px;  margin:0 auto;}
}

@media screen and (max-width:768px)
{ body{height:700px;}
.col-xs-12{text-align:center;}

}

</STYLE>
</head>

<body style="padding:0px;">

<nav class="" style="background-color: #012333;">
		<div class="container-fluid">
			<div class="row">
				<ul class=" pull-right">
					<li><a href="welcome" title="HOME" style="color: #fff;">Home</a></li>
					<!--  		    <li><a href="login" title="LOGIN">Login</a></li>  -->
<!-- 					<li><a href="registration" title="REGISTER" style="color: #fff;">Register</a></li> -->
				</ul>
			</div>


		</div>
	</nav>

<div class="container">
	<div class="row">
		<div class="col-md-12 col-sm-12">
		  <div class="col-md-offset-1 col-md-10 loginOuter">
		  <form id="loginForm" name="loginForm" method="POST" action="j_spring_security_check">
			 <div class="error-div t1-margin">
                <label id="lblErrorMessage" class="label-warning l10-padding"></label>
                <label id="lblErrorMsg" class="alert-danger l10-padding"> </label>
            </div> 
            <!-- Using Material Design frm 02-08-2016 so commented previous code-->
             <div class="col-sm-12 t1-margin form-group label-floating is-empty">
                <label for="EmailId" class=" col-sm-12 control-label" style="color:#000;">Registered Contact number</label>
                <input type="text" class="col-sm-12  height-50 form-control l10-padding " 
                name="j_username" id="j_username" required>
            </div>
            <div class="col-sm-12 t1-margin form-group label-floating is-empty">
                <label for="Password" class="col-sm-12 control-label" style="color:#000;">Password</label>
                <input type="password" class="col-sm-12 height-50 form-control l10-padding " 
                name="j_password" id="j_password" required>
            </div> 
           

			<div class="col-sm-12 t1-margin" align="center">
				<button id="signInBtn" type="submit" title="Sign In" class="btnbgcolor height-50 width-100" style="color:#fff; margin-bottom:10px;">SIGN IN</button>
			</div>
<%-- 			<a href="registration" ><center >Register Now</center></a> --%>
		</form>
		  
		  </div>
		</div>
	</div>
</div>


  <div class="col-md-offset-3 col-md-6 ">    
	<div class="container-fluid">
		<div class="row">
		<a class="" href="#"><img
					class="img-responsive" src="resource/images/VirtualMathsLabLogo.png"></a>
			<div class=" col-md-12 ">
				<div class="col-sm-10 col-sm-offset-1 login-OuterDiv bodybgcolor">
					 <h1 class="text-center">LOGIN</h1>
<hr></hr>
					<div>
						<c:if test="${not empty error}">
							<div class="alert alert-danger fade in">
								Your login attempt was not successful, try again.<br /> Caused :
						${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}						
							</div>
						</c:if>
						<c:if test="${not empty err}">
							<div class="alert alert-danger fade in">
								Sorry! Please click on the link in the same tab to start the test.<br>
								Please do not open test link in two different browsers or devices.						
							</div>
						</c:if>
					</div>


		<form id="loginForm" name="loginForm" method="POST" action="j_spring_security_check">
			 <div class="error-div t1-margin">
                <label id="lblErrorMessage" class="label-warning l10-padding"></label>
                <label id="lblErrorMsg" class="alert-danger l10-padding"> </label>
            </div> 
            <!-- Using Material Design frm 02-08-2016 so commented previous code-->
             <div class="col-sm-12 t1-margin form-group label-floating is-empty">
                <label for="EmailId" class=" col-sm-12 control-label" style="color:#000;">Registered Contact number</label>
                <input type="text" class="col-sm-12  height-50 form-control l10-padding " 
                name="j_username" id="j_username" required>
            </div>
            <div class="col-sm-12 t1-margin form-group label-floating is-empty">
                <label for="Password" class="col-sm-12 control-label" style="color:#000;">Password</label>
                <input type="password" class="col-sm-12 height-50 form-control l10-padding " 
                name="j_password" id="j_password" required>
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
				<button id="signInBtn" type="submit" title="Sign In" class="btnbgcolor height-50 width-100" style="color:#fff; margin-bottom:10px;">SIGN IN</button>
			</div>
<%-- 			<a href="registration" ><center >Register Now</center></a> --%>
		</form>

				<div align="right" class="col-sm-12 t1-margin"><hr style="margin-top:20px;"></hr>
	                <center><a href="forgotPwdLink" title="Forgot Password"><h2 class="">Forgot Password</h2></a></center>
	            </div>
	            
	            <%-- <div align="center" class="col-sm-12 t1-margin">
	                <center><a href="registration" title="Register Now"><h2 class="">Register Now</h2></a></center>
	            </div>  --%> 
					
			</div>
			</div>
		</div>
	</div>


</div>

</div>
</div>
	 <footer class="footer">
  <div class="container-fluid base-padding">
  <div class="row ">
    <div class="col-md-12 ">
                    <div class="col-md-6 col-xs-12 text-align-left">
                        <h5>
                           Virtual Maths Lab 2020
                        </h5>
                    </div>

                    <div class="col-md-6 col-xs-12 text-align-right">
                        <h5>
                            By College of Engineering Pune 2017
                        </h5>
                    </div>
    </div>
                </div>
  </div>
 </footer>
	<!-- Bootstrap core JavaScript
               ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->

	<!--  <script>
                   $('[data-toggle="tooltip"]').tooltip();
                   $('.tooltip-css').mouseleave(function () {
                       $(this).tooltip('hide');
                   });
                   $(document).ready(function () {
                       $('#signInBtn').click(function () {
                           $('#loginForm').formValidation({
                               framework: 'bootstrap',
                               icon: {
                                   invalid: 'glyphicon glyphicon-remove',
                                   validating: 'glyphicon glyphicon-refresh'
                               },
                               fields: {
                                   email: {
                                       validators: {
                                           notEmpty: {
                                               message: 'Email is required '
                                           },
                                           emailAddress: {
                                               message: 'The input is not a valid email address'
                                           }
                                       }
                                   },
                                   pass: {
                                       validators: {
                                           notEmpty: {
                                               message: 'Password is required '
                                           },
                                           identical: {
                                               field: 'conPass',
                                               message: 'The password and its confirm are not the same'
                                           }
                                       }
                                   }
                               }
                           })

                       });
                   });
               </script> -->

	<!--   <script type="text/javascript">
               
               logindetails = function(){

            		var j_username = $("#j_username").val();
            		var j_password = $("#j_password").val();
            		
            		
            		alert("In js"+j_username+" "+j_password);
            		
            		$
        			.ajax({
        				type : 'POST',
        				url : 'http://localhost:8080/OnlineTest_REST/j_spring_security_check',
        				data: "j_username="+j_username+"&j_password="+j_password,			
        				contentType : 'application/x-www-form-urlencoded',
        				success : function(data) {

        				},
        				error : function() {

        				}

        			});
            		
            		
            	};
            	
               </script> 
                -->


	<script src="resource/js/jquery-1.11.3.min.js"></script>
	<script src="resource/js/bootstrap.min.js"></script>
	<script src="resource/js/custom.js"></script>
	<!-- form validation js  -->
	<script src="resource/js/formvalidation.min.js"></script>
	<script src="resource/js/bootstrap-validation.min.js"></script>
	<script src="resource/js/material.min.js"></script>
	<script src="resource/core/namespace.js"></script>
	<script src="resource/core/ajaxMngr.js"></script>
	<script src="resource/src/register.js"></script>
<script>
     new WOW().init();
      </script>
	<script>
	   $(function () {
	       $.material.init();
	   });
	</script>
</body>



</html>
