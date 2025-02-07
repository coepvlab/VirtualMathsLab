<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Instructions</title>
<link rel="icon" href="resource/images/it-olympiad2017_logo.png" />
    <!-- Bootstrap core CSS -->
    <link href="resource/css/bootstrap.min.css" rel="stylesheet">
    <link href="resource/css/latofonts.css" rel="stylesheet">
    <link href="resource/css/latostyle.css" rel="stylesheet">
<!--     <link href='https://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'> -->
<!--     <link href='https://fonts.googleapis.com/css?family=Lato:400,300,700' rel='stylesheet' type='text/css'> -->
    <!-- Custom styles for this template -->
    <link href="resource/custom.css" rel="stylesheet">
    <link href="resource/css/style.css" rel="stylesheet">
</head>

<style>
@media screen and (max-width:768px)
{ body{height:700px;}
.col-xs-12{text-align:center;}

}
</style>


<body>

    <nav class="navbar navbar-default navbar-bg-color">
        <div class="container-fluid base-padding height-4_5">
            <div class="navbar-header r4_5-padding">
                <a class="navbar-brand logo-atnavbar-padding" href="#"><img class="img-responsive" src="resource/images/IntegreT_logo.png"></a>
                <a class="navbar-brand first-text-after-logo " href="#">Integra T<br>OLYMPIAD</a>
            </div>
        </div>
    </nav>


    <div class="container-fluid base-padding " id="main-div">
        <div class="row">
            <div class="col-sm-8 col-md-8 col-md-offset-2 col-sm-offset-2 text-align-justify">
                <h1 class="secondary-text-color text-align-center t1-b1-padding">
                    WELCOME!</h2>
                    <span>
                        <h3 class="prim-text-black-color">
                            Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rho.

                            ncus ut, imperdiet a, venenatis vitae, justo.
                        </h3>
                    </span>
                    <h1 class="secondary-text-color t1-b1-padding">
                        INSTRUCTIONS</h2>
                        <span>
                            <h3 class="prim-text-black-color">
                                The online exam is easy to get through<br />
                                We assume applicant taking the online test has a basic familiarity with computers and is comfortable with the use of keyboard and mouse
                            </h3>
                        </span><br />
                        <h2 class="secondary-text-color t1-b1-padding">During the Exam</h2>

                        <ol style="padding-left:14px">
                            <li class="b10-margin"><h4 class="prim-text-black-color">The test consists of multiple choice questions (MCQ type). In this, for each given question, the applicant must choose the right, or the closest, answer from among the choices given.</h4></li>
                            <li class="b10-margin"><h4 class="prim-text-black-color">All questions are manadatory.</h4></li>
                            <li class="b10-margin"><h4 class="prim-text-black-color">Flag indicates question to be answered later. </h4></li>
                            <li class="b10-margin"><h4 class="prim-text-black-color">You can navigate and look through the questions within same section.</h4></li>
                            <li class="b10-margin"><h4 class="prim-text-black-color">To proceed to the next section click on the next section button at the bottom. Once done going back to the previous section is not allowed.</h4></li>
                            <li class="b10-margin"><h4 class="prim-text-black-color">Total time allotted for the test is 60 mins. General time scheme for each section. </h4></li>
                        </ol>
                        <div class="input-group t1-b1-padding display-inline-flex">
                            <span class="sec-time">I-10mins</span>
                            <span class="sec-time">II-20-mins</span>
                            <span class="sec-time">III-30-mins</span>
                        </div>
            </div>
            <div class="col-sm-12  l0-r0-padding">
                <!-- <a href="candidatetest.html"><button id="startTestBtn" type="submit" title="SUBMIT" class="pull-right btn custom-btn">START TEST</button></a> -->
                 <button id="startTestBtn" type="submit" title="SUBMIT" class="pull-right btn custom-btn" onclick = "FullScreenMode()">START TEST</button>
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
    
    function launchIntoFullscreen(element) {  	
    	 	if(element.requestFullscreen) {
    	    element.requestFullscreen();
    	  } else if(element.mozRequestFullScreen) {
    	    element.mozRequestFullScreen();
    	  } else if(element.webkitRequestFullscreen) {
    	    element.webkitRequestFullscreen();
    	  } else if(element.msRequestFullscreen) {
    	    element.msRequestFullscreen();
    	  }
    	}
    
    </script>
    
    <script type="text/javascript"> 
    
    function FullScreenMode(){
    	
      window.open("resumeTest", "full", "dependent=yes, fullscreen=yes, scrollbars=yes, titlebar=yes, width= " + window.screen.width +", height= " + window.screen.height);
//        $("#main-div").html("");
       $('#startTestBtn').attr('disabled' , true);
       setTimeout(function(){ window.open("home", "_self", "full")}, 10000);
<%--        <%response.sendRedirect("home");%> --%>
       
   	}
       
      
   
        
       
		   

    </script>
   
</body>
</html>
