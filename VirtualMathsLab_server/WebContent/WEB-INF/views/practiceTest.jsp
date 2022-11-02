<!DOCTYPE HTML >
<HTML>
<HEAD>
<!-- <meta charset="utf-8"> -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />


<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js"></script>
<script src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/input/asciimath.js" charset="UTF-8"></script>

<script
	src="http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS_HTML">
</script>

<title>Practice Test</title>
<!-- Bootstrap core CSS -->
<link href="resource/css/toastr.css" rel="stylesheet"/>
<link rel="icon" href="resource/images/VirtualMathsLabLogo.png" />
<link rel="stylesheet" href="resource/css/new-home.css" type="text/css">
<!-- <link rel="stylesheet" href="resource/css/homePage.css" type="text/css"> -->

  <link rel="stylesheet" href="resource/css/dataTables.bootstrap4.min.css" type="text/css">
<link rel="stylesheet" href="resource/css/buttons.bootstrap4.min.css" type="text/css">
<link rel="stylesheet" href="resource/css/bootstrap4.5.2.min.css">
<link rel="stylesheet" href="resource/css/bootstrap-select.min.css">

<link href="resource/css/font-awesome.css" rel="stylesheet">

<link href="resource/css/showToast.css" rel="stylesheet">

  <link href="resource/css/practiceTestCSS.css" rel="stylesheet">
    <link href="resource/css/praTestCSS.css" rel="stylesheet">
<!-- Custom styles for this template -->
<style>
@media screen and (max-width:768px) and (min-width:200px) {
.base-timer {
		position: fixed;
    width: 86px;
    top: 0;
    z-index: 11111;
    border-radius: 52%;
/*     border: 1px solid #fff; */
    background: #343148;
    right: 24px;
    height: 86px;
    margin: 7px auto;
	}
	.base-timer__label {
		/* position: absolute; */
		width: 87px;
		height: 80px;
		/* top: 0; */
		color: #fff;
		display: flex;
		align-items: center;
		justify-content: center;
		font-size: 27px;
	}
	}
</style>

</head>


<body class="loading" onload="noBack();" onpageshow="if (event.persisted) noBack();" onunload="">
	
	  <div class="container-fluid">
                <div class="row bodybgcolor">
                  		
                  		<div id="practiceTestDiv" class="col-sm-12 col-md-12 col-lg-12  col-xl-12">
                    
                 		<div id="pra-test-heading" class="col-sm-12 col-md-12 col-lg-12  col-xl-12"></div>
                 		
						<div id="pra-test-main-div" class="col-sm-12 col-md-12 col-lg-12  col-xl-12"></div>
						
						
						
                </div>
            </div>
           </div> 
	
	
</body>




<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->


	 <script src="resource/js/toastr.js"></script>
 	 <script src="resource/js/jquery-3.5.min.js"></script>
 	 <script src="resource/js/popper.min.js"></script>
 	 <script src="resource/js/bootstrap-4.5.min.js"></script>
	  <script src="resource/js/bootstrap-select.min.js"></script>
  
  <!-- form validation -->
	<script type="text/javascript" src="resource/js/formvalidation.min.js"></script>
	<script type="text/javascript" src="resource/js/bootstrap-validation.min.js"></script>
		
	<script src="resource/js/jquery.dataTables.min.js"></script>
	<script src="resource/js/dataTables.bootstrap4.min.js"></script>
	<script src="resource/js/dataTables.buttons.min.js"></script>
	<script src="resource/js/buttons.bootstrap4.min.js"></script>
	
<!-- form validation js  -->

	<script src="resource/core/namespace.js"></script>

	<script type="text/javascript" src="resource/src/home.js"></script>
	<script type="text/javascript" src="resource/src/addQuestionGroups.js"></script>
	<script type="text/javascript" src="resource/src/practiceTest.js"></script>
	<script type="text/javascript" src="resource/src/practiceTestTimer.js"></script>
	<script type="text/javascript" src="resource/src/core/practiceTestOptFun.js"></script>
	<script type="text/javascript" src="resource/src/renderMathJax.js"></script>

	<script type="text/javascript" src="resource/js/showToast.js"></script>

	<script type="text/javascript">
    
	var testData = <%=request.getAttribute("data")%>;
	console.log(testData);
	var firstName='<%=request.getAttribute("FN")%>'
	
	if (testData.done == false) {
		
		com.coep.test.practiceTest.practiceTestMessage(testData.msg);

	} else {

		com.coep.test.practiceTest.startpracticeTest(testData, firstName);
	}
	
	
	    window.history.forward();
	    
        function noBack() { 
             window.history.forward(); 
        }
	
</script>


<script type="text/x-mathjax-config">
MathJax.Hub.Config({
  CommonHTML: { linebreaks: { automatic: true } },
  "HTML-CSS": { linebreaks: { automatic: true } },
         SVG: { linebreaks: { automatic: true } }
});
</script>


<script >

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
				  "extendedTimeOut": "1000",
				  "showEasing": "swing",
				  "hideEasing": "linear",
				  "showMethod": "fadeIn",
				  "hideMethod": "fadeOut"
				}

</script>
</body>


<!-- <script src="resource/js/tex-mml-chtml.js"></script> -->
<!-- <script src="resource/js/asciimath.js" charset="UTF-8"></script> -->
</html>
