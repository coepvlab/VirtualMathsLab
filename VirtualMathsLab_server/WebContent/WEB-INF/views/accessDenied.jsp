<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="resource/custom.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Access Denied</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="resource/images/it-olympiad2017_logo.png" />
<!-- Bootstrap core CSS -->
<link href="resource/css/bootstrap.min.css" rel="stylesheet">

<link href="resource/css/latofonts.css" rel="stylesheet">
<link href="resource/css/latostyle.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="resource/custom.css" rel="stylesheet">
<link href="resource/css/style.css" rel="stylesheet">
<link href="resource/css/jquery-ui.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-default navbar-bg-color navbar-fixed-top">
	<div class="container-fluid navbar-base-padding height-5">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand logo-atnavbar-padding" href="#"><img
				class="img-responsive" src="resource/images/IntegreT_logo.png" /></a>
				<a class="navbar-brand first-text-after-logo " href="#">Integrated<br>Technologies (IT)<br>Olympiad
				</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
				<div class="pull-right hidden-xs hidden-sm profile-descri-outer-block t1_7-padding">
                    <h3 class="white-color">Hi ${FN}</h3>
                    &nbsp;&nbsp;<span class="glyphicon glyphicon-menu-down dropdown-toggle pointer white-color" data-toggle="dropdown" aria-expanded="false"></span>
                    <ul class="dropdown-menu right-10" role="menu" aria-labelledby="dropdownMenu1">
                        
                        <li id="logoutlink" class="dd-menu" role="presentation"><a role="menuitem" tabindex="-1" href="/VirtualMathsLab/logout"><span class="glyphicon glyphicon-log-out "></span>&nbsp;&nbsp;Log Out</a></li>
                    </ul>
                </div>
		</div>
		<a
			class="pull-left hidden-lg hidden-md hidden-sm profile-descri-outer-block-xs"
			href="#"> <img src="resource/images/dummy.png"
			class="profile-pic"><span
			class="icon-down-arrow-prof icon-down-arrow"></span>
		</a>
	</div>
	
	 </nav>


	<div id="row">
		<div id="materialDesign" class="col-sm-12 box">
			<table class="width-100">
				<tbody>
					<tr align="center">
						<td colspan="2"><img
							src="resource/images/it-olympiad2017_logo.png"
							style="width: 150px; height: 150px;" /><br /> <img
							src="resource/images/ITOTitle.png"
							style="width: 150px; height: 66px;" /></td>
					</tr>
					<tr align="center">
						<td colspan="2">
							<h1 class="secondary-text-color">Sorry you are not eligible for this round. Best of luck for your future..!!!</h1>
						</td>
					</tr>
					<tr class="height-100px"></tr>
					<tr align="center">
<!-- 						<td class="col-sm-12"><a href="j_spring_security_logout"> -->
<!-- 								<button id="logOutBtn" type="submit" title="Log Out" -->
<!-- 									class="btn custom-btn">LOGOUT</button> -->
<!-- 						</a></td> -->
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	
	<script type="text/javascript"  src="resource/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="resource/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="resource/js/jquery-ui.js"></script>
	<script type="text/javascript" src="resource/js/custom.js"></script>
	<script type="text/javascript" src="resource/js/qbank.js"></script>
	<script type="text/javascript"  src="resource/js/jquery.validate.js"></script>

	<script type="text/javascript" src="resource/core/namespace.js"></script>
	<script type="text/javascript" src="resource/core/ajaxMngr.js"></script>

	<!-- form validation js  -->
	<script type="text/javascript" src="resource/js/formvalidation.min.js"></script>
	<script type="text/javascript" src="resource/js/bootstrap-validation.min.js"></script>
</body>
</html>