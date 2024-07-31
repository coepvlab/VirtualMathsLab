    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>VirtualMathLab</title>
<link rel="icon" href="resource/images/VirtualMathsLabLogo.png" />
<link rel="stylesheet" href="resource/css/new-home.css" type="text/css">
<link rel="stylesheet" href="resource/css/homePage.css" type="text/css">
<link rel="stylesheet" href="resource/css/testConfig.css" type="text/css">

<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<script src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-mml-chtml.js"></script>
<script src="https://cdn.jsdelivr.net/npm/mathjax@3/es5/input/asciimath.js" charset="UTF-8"></script>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="resource/css/googleFont.css" rel="stylesheet">

<link rel="stylesheet" href="resource/css/dataTables.bootstrap4.min.css" type="text/css">
<link rel="stylesheet" href="resource/css/buttons.bootstrap4.min.css" type="text/css">
<!-- .........new css........ -->
<link href="resource/css/toastr.css" rel="stylesheet"/>
<link rel="stylesheet" href="resource/css/bootstrap4.5.2.min.css">
  <script src="resource/js/jquery-3.5.min.js"></script>
  <script src="resource/js/popper.min.js"></script>
  <script src="resource/js/bootstrap-4.5.min.js"></script>


<link href="resource/css/chosen.css" rel="stylesheet">
<link href="resource/css/chosen.min.css" rel="stylesheet">

<!-- Custom styles for this template -->

<link href="resource/css/font-awesome.css" rel="stylesheet">

<link href="resource/css/tag-selector.css" rel="stylesheet">
<link href="resource/css/jquery-ui-timepicker-addon.css" rel="stylesheet">
<link href="resource/css/jquery-ui.css" rel="stylesheet">

<link href="resource/css/level.css" rel="stylesheet">

<link rel="stylesheet" href="resource/css/bootstrap-select.min.css">
<link href="resource/css/showToast.css" rel="stylesheet">

<script src="resource/js/bootstrap-select.min.js"></script>

<style>
.p-4 {
	padding: 0px;
}

.btnFont {
	    font-weight: 700;
}


.mb-0>a {
	display: block;
	position: relative;
}

.mb-0>a:after {
	content: "\f078"; /* fa-chevron-down */
	font-family: 'FontAwesome';
	position: absolute;
	right: 0;
}

.mb-0>a[aria-expanded="true"]:after {
	content: "\f077"; /* fa-chevron-up */
}

@media screen and (max-width:2000px) and (min-width:767px) {
	.book-color {
		color: #cb638a;
		font-size: 23px;
		margin: -5px 10px 0 0;
	}
	.topicName {
		color: #0e61f9;
		font-size: 19px;
		font-weight: 500;
		font-family: monospace;
	}
		
	.underdev{
		color: #bb2f03;
	    font-weight: bold;
	}
	
	#main-div {
		margin-top: 1px;
	}
}

@media screen and (max-width:768px) {
	#main-div {
		margin-top: 31px;
	}
	.book-color {
		color: #cb638a;
		font-size: 23px;
		margin: -4px 10px 0 0;
	}
	.topicName {
		color: #0e61f9;
		font-size: 16px;
		font-weight: 500;
		font-family: monospace;
	}
	.mb-0>a:after {
		right: -18px;
	}
	#approvedQuestionData_filter {
		padding: 10px 8px 0px 8px;
		position: fixed;
		top: 2px;
		width: 73%;
	}
	#nonApprovedQuestionData_filter {
		padding: 10px 8px 0px 8px;
		position: fixed;
		top: 2px;
		width: 73%;
	}
	div.dataTables_wrapper div.dataTables_filter input {
		margin-left: 2%;
		margin-top: -7px;
		display: inline-block;
		width: 83%;
	}
	
	.StartTestBtn {
			text-align: center;
			color: #fff;
			font-size: 18px;
			font-weight: bold;
			position: fixed;
			bottom: 0;
			    margin: 0;
			width: 95%;
		}
}
</style>
</head>
<!--  onload="getQuestionBankForApprovedNonApproved()" -->
<body class="loading"  onload="noBack();"  onpageshow="if (event.persisted) noBack();" onunload="">
  
<div class="BodyBGOpacity wrapper d-flex align-items-stretch">
<!-- Vertical navbar -->
<nav id="sidebar">
				<div class="custom-menu">
					<button type="button" id="sidebarCollapse" class="btn btn-primary">
	          <i class="fa fa-bars"></i>
	          <span class="sr-only">Toggle Menu</span>
	        </button>
			</div>
				<div class="p-2 pt-5" >
		  		<div id="userdataDiv" class="">
    <div class=""><img src="resource/images/profilePicImg.jpg" style="width:80px; height:80px; margin-bottom: 5px;" class=" rounded-circle img-thumbnail shadow-sm" id="profPic">
    
    <div class="image-upload" >
    <label for="uploadImage">
        <img src="resource/images/updateProfile.png" id="updatePhoto" title="Change profile pic" />
    </label>

    <input  type="file" id="uploadImage" title="Upload File" >
</div>
     
        <span style="font-size:18px; font-weight:bold; color:#cdd7d6;">${FN}</span><br/>
<%--           <span  id="logoutlink" ><a  href="/VirtualMathsLab/logout" style="color: #cdd7d6;"><i class="fa fa-sign-out" aria-hidden="true"></i>&nbsp;Sign out</a></span><br/> --%>
        <span  id="logoutlink" ><a  href='<c:url value="/j_spring_security_logout" />' style="color: #cdd7d6;"><i class="fa fa-sign-out" aria-hidden="true"></i>&nbsp;Sign out</a></span><br/>
    </div>
   <span class="assignedRoles" ></span>
        
  </div>
	        <ul class="list-unstyled components mb-5">
	<s:authorize access="hasAnyRole('ADMIN','MODERATOR','TEACHER','CONTRIBUTOR')">
    <li >
      <a href="javascript:com.coep.test.addProblem.getLevelDetailsToAddQuestionGroup()" class="CloseNav">
                <i class="fa fa-th-large mr-3 text-muted fa-fw"></i>
                Add Questions
            </a>
    </li>
    </s:authorize>
    
       <s:authorize access="hasAnyRole('STUDENT')">
    <li >
      <a href="javascript:com.coep.test.home.getAllTopicToHomePage()" class="CloseNav">
                <i class="fa fa-cubes mr-3 text-muted fa-fw"></i>
                Home
            </a>
    </li>
    </s:authorize>
    
        <s:authorize access="hasAnyRole('STUDENT','ADMIN','CONTRIBUTOR','MODERATOR')">
    <li >
      <a href="javascript:com.coep.test.testRecords.getAllTestRecords()" class="CloseNav">
                <i class="fa fa-cubes mr-3 text-muted fa-fw"></i>
                Dashboard
            </a>
    </li>
    </s:authorize>
    
     <li id="excelmenu">
       <s:authorize access="hasAnyRole('ADMIN','CONTRIBUTOR','MODERATOR')">
	            <a href="#excelSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-cubes mr-3 text-muted fa-fw"></i> Excel Sheet</a>
	            <ul class="collapse list-unstyled " id="excelSubmenu">
                
             <s:authorize access="hasAnyRole('ADMIN','CONTRIBUTOR','MODERATOR')">
		    <li >
		      <a href="javascript:com.coep.test.utilityFile.downloadSampleExcelSheet()"  class="CloseNav">
                <i class="fa fa-th-large mr-3 text-muted fa-fw"></i>
                Download Excelsheet
            </a>
		    </li>
		    </s:authorize>    
                
            <s:authorize access="hasAnyRole('ADMIN','CONTRIBUTOR','MODERATOR')">
 		    <li > 
		       <a href="javascript:com.coep.test.utilityFile.uploadQuesByExcelSheet(2)" class="CloseNav">
                <i class="fa fa-th-large mr-3 text-muted fa-fw"></i>
                Check Excelsheet
            </a>
 		    </li>
		    </s:authorize>
		    
		    <s:authorize access="hasAnyRole('ADMIN','CONTRIBUTOR','MODERATOR')">
		    <li >
		      <a href="javascript:com.coep.test.utilityFile.uploadQuesByExcelSheet(1)" class="CloseNav">
                <i class="fa fa-th-large mr-3 text-muted fa-fw"></i>
                Upload Excelsheet
            </a>
		    </li>
		    </s:authorize>
      
	            </ul>
	             </s:authorize>
	          </li>
    
	          <li id="homemenu">
	             <s:authorize access="hasAnyRole('ADMIN','TEACHER','CONTRIBUTOR','MODERATOR')">
	            <a href="#homeSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-cubes mr-3 text-muted fa-fw"></i> User Questions</a>
	            <ul class="collapse list-unstyled" id="homeSubmenu">
                <s:authorize access="hasAnyRole('ADMIN','TEACHER','CONTRIBUTOR','MODERATOR')">
 		    <li > 
		      <a href="javascript:com.coep.test.questionBank.getQuestionBankOfUser('Active')" class="CloseNav">
		                <i class="fa fa-th-large mr-3 text-muted fa-fw"></i>
		                Active Questions
		            </a>
 		    </li>
		    </s:authorize>
		    
		    <s:authorize access="hasAnyRole('ADMIN','TEACHER','CONTRIBUTOR','MODERATOR')">
		    <li >
		      <a href="javascript:com.coep.test.questionBank.getQuestionBankOfUser('Archived')" class="CloseNav">
		                <i class="fa fa-th-large mr-3 text-muted fa-fw"></i>
		                Archived Questions
		            </a>
		    </li>
		    </s:authorize>
      
	            </ul>
	          </li>
			  </s:authorize>  
			  
			  
			  <li id="pagemenu">
			  <s:authorize access="hasAnyRole('ADMIN','MODERATOR')">
              <a href="#pageSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-cubes mr-3 text-muted fa-fw"></i>All Questions</a>
              <ul class="collapse list-unstyled" id="pageSubmenu">
            
                <s:authorize access="hasAnyRole('ADMIN','MODERATOR')">
 		   	 <li > 
		      <a href="javascript:com.coep.test.questionBank.getAllQuestionsFromQuesBank('Active')" class="CloseNav">
		                <i class="fa fa-th-large mr-3 text-muted fa-fw"></i> 
		                Active Questions
		            </a>
 		   	 </li> 
		    </s:authorize>
                <s:authorize access="hasAnyRole('ADMIN','MODERATOR')">
			   	 <li >
			      <a href="javascript:com.coep.test.questionBank.getAllQuestionsFromQuesBank('Archived')" class="CloseNav">
			                 <i class="fa fa-th-large mr-3 text-muted fa-fw"></i>
			                Archived Questions
			            </a>
			   	 </li>
    			</s:authorize>
              </ul>
	          </li>
			  </s:authorize>  
    
	     <s:authorize access="hasAnyRole('ADMIN')">
			    <li >
			      <a href="javascript:com.coep.test.addTopic.getSubjectListToAddTopic()" class="CloseNav">
			                <i class="fa fa-cubes mr-3 text-muted fa-fw"></i>
			                Add Topic
			            </a>
			    </li>
			    </s:authorize>
	
		<s:authorize access="hasAnyRole('ADMIN')">
			    <li >
			      <a href="javascript:com.coep.test.utilityFile.changeTopicStatusFlag()" class="CloseNav">
			                <i class="fa fa-cubes mr-3 text-muted fa-fw"></i>
			                Activate Topic
			            </a>
			    </li>
			    </s:authorize>	    
			    
<%-- 			     <s:authorize access="hasAnyRole('ADMIN')"> --%>
<!-- 			    <li > -->
<!-- 			      <a href="javascript:com.coep.test.utilityFile.demoCall()" class="CloseNav"> -->
<!-- 			                <i class="fa fa-cubes mr-3 text-muted fa-fw"></i> -->
<!-- 			                Demo -->
<!-- 			            </a> -->
<!-- 			    </li> -->
<%-- 			    </s:authorize> --%>

		 <s:authorize access="hasAnyRole('ADMIN')">
			    <li >
			      <a href="javascript:com.coep.test.utilityFile.deleteArchiveQuestions()" class="CloseNav">
			                <i class="fa fa-cubes mr-3 text-muted fa-fw"></i>
			                Delete Archives
			            </a>
			    </li>
	    </s:authorize>
			    
			     <s:authorize access="hasAnyRole('ADMIN')">
			    <li >
			      <a href="javascript:com.coep.test.addTopic.getTopicListToAddParentTopics()" class="CloseNav">
			                <i class="fa fa-cubes mr-3 text-muted fa-fw"></i>
			                Assign Parent Topic
			            </a>
			    </li>
	    </s:authorize>
    
    
    
        <s:authorize access="hasAnyRole('ADMIN','CONTRIBUTOR','MODERATOR')">
		    <li >
		      <a href="javascript:com.coep.test.testConfig.testConfiguration()" class="CloseNav">
                <i class="fa fa-th-large mr-3 text-muted fa-fw"></i>
                Test Configuration
            </a>
		    </li>
		    </s:authorize>
    
    
     	 <s:authorize access="hasAnyRole('ADMIN','CONTRIBUTOR','MODERATOR')">
		    <li >
		      <a href="javascript:com.coep.test.testConfig.testLinkCreation()" class="CloseNav">
                <i class="fa fa-th-large mr-3 text-muted fa-fw"></i>
                Test Link Creation
            </a>
		    </li>
		    </s:authorize>
		    
<%--     <s:authorize access="hasAnyRole('ADMIN')"> --%>
<!--     <li > -->
<!--       <a href="javascript:com.coep.test.uploadFile.uploadFile()" class="CloseNav"> -->
<!--                 <i class="fa fa-th-large mr-3 text-muted fa-fw"></i> -->
<!--                Upload File -->
<!--             </a> -->
<!--     </li> -->
<%--     </s:authorize> --%>
    
<%--       <s:authorize access="hasAnyRole('ADMIN')"> --%>
<!--     <li > -->
<!--       <a href="javascript:com.coep.test.uploadFile.getAllJavaFileOutput()" class="CloseNav"> -->
<!--                 <i class="fa fa-th-large mr-3 text-muted fa-fw"></i> -->
<!--                Problem Statement Output -->
<!--             </a> -->
<!--     </li> -->
<%--     </s:authorize> --%>
    
    <s:authorize access="hasAnyRole('ADMIN')">
    <li >
      <a href="javascript:com.coep.test.userProfile.manageRoleOfUsers()" class="CloseNav">
                <i class="fa fa-cubes mr-3 text-muted fa-fw"></i>
                Manage Role
            </a>
    </li>
    </s:authorize>
    
    <s:authorize access="hasAnyRole('ADMIN')">
    <li >
      <a href="javascript:com.coep.test.userProfile.checkRequestForContributorRole()" class="CloseNav">
                <i class="fa fa-cubes mr-3 text-muted fa-fw"></i>
                Contributor Request
            </a>
    </li>
    </s:authorize>
    
    <s:authorize access="hasAnyRole('ADMIN')">
    <li >
      <a href="javascript:com.coep.test.userProfile.activateAccountByAdmin()" class="CloseNav">
                <i class="fa fa-cubes mr-3 text-muted fa-fw"></i>
                Activate Account
            </a>
    </li>
    </s:authorize>
    
        <s:authorize access="hasAnyRole('ADMIN')">
    <li >
      <a href="javascript:com.coep.test.utilityFile.getAllUsersToWriteInExcel()" class="CloseNav">
                <i class="fa fa-cubes mr-3 text-muted fa-fw"></i>
                User List
            </a>
    </li>
    </s:authorize>
    
    <s:authorize access="hasAnyRole('ADMIN')">
    <li >
      <a href="javascript:com.coep.test.addSubject.addSubject()" class="CloseNav">
                <i class="fa fa-cubes mr-3 text-muted fa-fw"></i>
                Add Subject
            </a>
    </li>
    </s:authorize>
    
     
    
    <li id="Approvemenu">
	            <s:authorize access="hasAnyRole('ADMIN','MODERATOR')">
	            <a href="#ApproveSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-cubes mr-3 text-muted fa-fw"></i>Moderate Question</a>
	            <ul class="collapse list-unstyled" id="ApproveSubmenu">
                <s:authorize access="hasAnyRole('ADMIN','MODERATOR')">
 		    <li > 
		      <a href="javascript:com.coep.test.questionBank.getTopicForQuestionsToApprove('Non-Approved')" class="CloseNav">
		                 <i class="fa fa-th-large mr-3 text-muted fa-fw"></i>
		                Non Moderated Questions
		            </a>
 		    </li>
		    </s:authorize>
		    
		    <s:authorize access="hasAnyRole('ADMIN','MODERATOR')">
		    <li >
<!-- 		      <a href="javascript:com.coep.test.questionBank.getQuestionsToApprove('Approved')" class="CloseNav"> -->
		           <a href="javascript:com.coep.test.questionBank.getTopicForQuestionsToApprove('Approved')" class="CloseNav">     
		                  <i class="fa fa-th-large mr-3 text-muted fa-fw"></i>
		                 Moderated Questions
		            </a>
		    </li>
		    </s:authorize>
      
	            </ul>
	          </li>
     		</s:authorize>
    <li >
    
<!--     this is to change time for variation no -->
     <li id="ApproveModifymenu">
	            <s:authorize access="hasAnyRole('ADMIN','MODERATOR')">
	            <a href="#ApproveModifySubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-cubes mr-3 text-muted fa-fw"></i>Variation Time Update</a>
	            <ul class="collapse list-unstyled" id="ApproveModifySubmenu">
                <s:authorize access="hasAnyRole('ADMIN','MODERATOR')">
 		    <li > 
		      <a href="javascript:com.coep.test.questionBankModification.getQuestionsToApproveForModification('Non-Approved')" class="CloseNav">
		                 <i class="fa fa-th-large mr-3 text-muted fa-fw"></i>
		                Non Moderated Questions
		            </a>
 		    </li>
		    </s:authorize>
		    
		    <s:authorize access="hasAnyRole('ADMIN','MODERATOR')">
		    <li >
		      <a href="javascript:com.coep.test.questionBankModification.getQuestionsToApproveForModification('Approved')" class="CloseNav">
		                 <i class="fa fa-th-large mr-3 text-muted fa-fw"></i>
		                 Moderated Questions
		            </a>
		    </li>
		    </s:authorize>
      
	            </ul>
	          </li>
     		</s:authorize>
    <li >
    
    
      <a href="javascript:com.coep.test.userProfile.fetchUserProfile()" class="CloseNav">
                <i class="fa fa-cubes mr-3 text-muted fa-fw"></i>
                Profile
            </a>
    </li>
    
     <li >
      <a href="javascript:com.coep.test.userProfile.ChangeUserPassword()" class="CloseNav" >
                <i class="fa fa-cubes mr-3 text-muted fa-fw"></i>
                Change Password
            </a>
    </li>

	        </ul>

	      </div>
    	</nav>
<!-- End vertical navbar -->


   

 <div id="content">
  <!-- Toggle button -->
 
            <div class="container-fluid">
                <div class="row bodybgcolor">
<!--                     <div class="col-sm-12 col-md-12 col-lg-12 col-xl-12"> -->
                 
			<div id="main-div" class="container-fluid base-padding">
			<h1 style="text-align: center;">Welcome </h1>
			<img src="resource/images/VirtualMathsLabLogo.png" class="homeLogoImg">
			           <div class="overlay" id="Loading" style="display:none">
			 <div class="overlay__inner">
			 <div class="overlay__content"><span class="spinner"></span><br/><span class="loading">LOADING....</span></div>
			 </div>
			 </div>
					 
<!-- 					</div> -->
                </div>
            </div>
           </div> 
		 
   </div>

   </div>

	<script src="resource/js/toastr.js"></script>
	<script src="resource/js/jquery.dataTables.min.js"></script>
	<script src="resource/js/dataTables.bootstrap4.min.js"></script>
	<script src="resource/js/dataTables.buttons.min.js"></script>
	<script src="resource/js/buttons.bootstrap4.min.js"></script>
	<script src="resource/js/jszip.min.js"></script>
	<script src="resource/js/pdfmake.min.js"></script>
	<script src="resource/js/vfs_fonts.js"></script>
	<script src="resource/js/buttons.html5.min.js"></script>
	<script src="resource/js/buttons.print.min.js"></script>
	<script src="resource/js/buttons.colVis.min.js"></script>

	<script type="text/javascript" src="resource/js/jquery-ui.js"></script>
	<script type="text/javascript" src="resource/js/custom.js"></script>

	<script type="text/javascript" src="resource/js/jquery.validate.js"></script>
	<script type="text/javascript" src="resource/js/tag-selector.js" charset="utf-8"></script>
	
	<script type="text/javascript" src="resource/js/chosen.jquery.min.js"></script>
	<script type="text/javascript" src="resource/js/chosen.jquery.js"></script>
	<script type="text/javascript" src="resource/js/jquery-ui-timepicker-addon.js"></script>

	<script type="text/javascript" src="resource/core/namespace.js"></script>
	<script type="text/javascript" src="resource/core/ajaxMngr.js"></script>
	<script type="text/javascript" src="resource/src/register.js"></script>

	<script type="text/javascript" src="resource/src/addQuestionGroups.js"></script>
	<script type="text/javascript" src="resource/src/core/addQGFunctions.js"></script>
	<script type="text/javascript" src="resource/src/addSubject.js"></script>
	<script type="text/javascript" src="resource/src/home.js"></script>
	<script type="text/javascript" src="resource/src/practiceTest.js"></script>
	
	<script type="text/javascript" src="resource/src/otherTest.js"></script>

	<script type="text/javascript" src="resource/src/renderMathJax.js"></script>

	<script type="text/javascript" src="resource/src/addTopic.js"></script>
	<script type="text/javascript" src="resource/src/addParentTopic.js"></script>
	<script type="text/javascript" src="resource/src/validation.js"></script>
	<script type="text/javascript" src="resource/src/userProfile.js"></script>
	<script type="text/javascript" src="resource/src/AlertMsg.js"></script>
	<script type="text/javascript" src="resource/src/utilityFile.js"></script>

	<script type="text/javascript" src="resource/src/questionBank.js"></script>
	<script type="text/javascript" src="resource/src/questionBankModify.js"></script>

	<script type="text/javascript" src="resource/js/showToast.js"></script>

	<script type="text/javascript" src="resource/src/menu.js"></script>
	
	<script type="text/javascript" src="resource/src/uploadFile.js"></script>
	
	<script type="text/javascript" src="resource/src/randerQues.js"></script>
	
	<script type="text/javascript" src="resource/src/testConfig.js"></script>
	
	<script type="text/javascript" src="resource/src/testRecords.js"></script>

	<!-- form validation -->
	<script type="text/javascript" src="resource/js/formvalidation.min.js"></script>
	<script type="text/javascript" src="resource/js/bootstrap-validation.min.js"></script>

<script>

$("#main-div").ready(function(){
    //block will be loaded with element with id myid is ready in dom
    var roles = '<%=request.getAttribute("roles")%>';
<%-- 		var itoStud = '<%=request.getAttribute("itoStud")%>'; --%>
		
		Rdata = JSON.parse(roles);
		
//     console.log(com.coep.test.questionBank.topicDataForApprovedQues);
    
//     if (Rdata.roles != "STUDENT") {
//     	var status = "Approved"
//     		$.ajax({
//     			type : "GET",
//     			url : com.coep.test.addProblem.baseURL
//     			+ "questionGroups/api/get/topic/questGroups/"+status,
//     			dataType : 'json',
//     			contentType : 'application/json',
//     			success : function(data) {
    				
//     				if (data.done == false) {
// //    	 				$("#main-div").html("<div class='col-xl-12 col-md-12 col-sm-12 alert alert-danger' style='text-align:center;'>No "+status+" questions in the database to display..</div>");
//     				}else{
// //    	 				getTopicListDetails(data, selectedTopicId);
//     					com.coep.test.questionBank.getTopicListDetailsFromHOME(data);
//     				}
    				
//     			},
//     			error : function() {
//     			}

//     		});
// 	}else{
// // 		alert("student");
// 	}
	
		
})

</script>


<script type="text/javascript" type="text/javascript">
$('#uploadImage').on("change" ,function() {
	(document.getElementById("uploadImage").files.length > 0) ? isFileLinked = 1 : isFileLinked = 0;
	checkLength(this, 'filetype');
});

var mediaId = "";
checkLength = function(inputValue, param2) {
	inputVal = $(inputValue).val();
	inputVal = $.trim(inputVal);
	if (param2 == 'filetype') {
		if (isFileLinked == 1) {
		
			var file = $('#uploadImage');
		
			var filename = $.trim(file.val());
		
			var formData = new FormData();
			formData.append('file', $('input[type=file]')[0].files[0]);
		
			if (filename != "" || filename.length != 0) {
				if (filename != null || filename != undefined)
		
					mediaId = MediaFileUplaod("media/profImage", "POST", formData);
			}
		}
	}
};

MediaFileUplaod = function(url, methodType, formData) {
	$.ajax({
	url : url,
	type : methodType,
	data : formData,
	enctype : "multipart/form-data",
	processData : false,
	contentType : false,
	dataType : 'json',
	}).done(function(data) {
	
	if (data.done == false) {
	} else {
		mediaId = data.URL;
		$("#profPic").attr("src", "media/getProfImage?mediaID=" + data.URL);
		//UP.fetchUserProfile();
	}
	//UP.fetchUserProfile();
	}).complete(function() {
	});
	
	return mediaId;
}

		<%-- var role = '<%=request.getAttribute("role")%>'; --%>
		var url = '<%=request.getAttribute("URL")%>';
		var roles = '<%=request.getAttribute("roles")%>';
		var itoStud = '<%=request.getAttribute("itoStud")%>';
		
		var topicArray = '<%=request.getAttribute("TOPICARRAY")%>';
		
		Rdata = JSON.parse(roles);
		
		var roleJson = {};
		for(var i = 0; i < Rdata.roles.length;i++){
			roleJson[i] = Rdata.roles[i];
		}
		rolesHtm = '<b>ASSIGNED ROLES : </b> '
			
		for (var key in roleJson) {
			if(key != Rdata.roles.length-1)
				rolesHtm += roleJson[key] +', '
			else{
				rolesHtm += roleJson[key]
			}
		}
		$(".assignedRoles").append(rolesHtm);
		
		if(url !=  'null'){
			$("#profPic").attr("src", "media/getProfImage?mediaID=" + url);
		} 
		
</script>
	
	
<script>

$('select').selectpicker();
(function($) {

	"use strict";

	var fullHeight = function() {

		$('.js-fullheight').css('height', $(window).height());
		$(window).resize(function(){
			$('.js-fullheight').css('height', $(window).height());
		});

	};
	fullHeight();

	$('#sidebarCollapse').on('click', function () {
      $('#sidebar').toggleClass('active');
      ManuONclick();
  });
	
		$('.CloseNav').on('click', function () {
		      $('#sidebar').toggleClass('active');
		      ManuONclick();
		      $("#Loading").css("display","block");
		     // $('#excelSubmenu').hide();
		  });
		
		

})(jQuery);

$( document ).ready(function() {
	ManuONchange();
});


  
	$(window).on('resize', function(){
		
		ManuONchange();
	
	});
	function ManuONclick()
	{
		if($(document).width()>991)
	{
  	  	if($("#sidebar").hasClass("active"))
    		{
			$("#content").css({"margin-left":"0%"})
			$("#sidebar .custom-menu .btn").css({"margin-left":"-38px"})
			$("#nonApprovedQuestionData_filter").css("width","92%");
			$("#approvedQuestionData_filter").css("width","92%");
			$("#QuestionData_filter").css("width","92%");
    		}
		else
			{
				$("#content").css({"margin-left":"250px"})
				$("#sidebar .custom-menu .btn").css({"margin-left":"-8px"})
				$("#nonApprovedQuestionData_filter").css("width","77%");
				$("#approvedQuestionData_filter").css("width","77%");
				$("#QuestionData_filter").css("width","77%");
			}
		}
		else{
		if($("#sidebar").hasClass("active"))
			{
		$("#content").css({"margin-left":"250px"})
		$("#sidebar .custom-menu .btn").css({"margin-left":"-38px"})
		$("#nonApprovedQuestionData_filter").css("width","92%");
		$("#approvedQuestionData_filter").css("width","92%");
		$("#QuestionData_filter").css("width","92%");
			}
		else
			{
		$("#content").css({"margin-left":"0%"})
		$("#sidebar .custom-menu .btn").css({"margin-left":"-8px"})
		$("#nonApprovedQuestionData_filter").css("width","77%");
		$("#approvedQuestionData_filter").css("width","77%");
		$("#QuestionData_filter").css("width","77%");
			}
		}
	}
	function ManuONchange()
	{
		if($(document).width()>991)
		 {
	if($("#sidebar").hasClass("active"))
		{
		  $("#content").css({"margin-left":"0px"})
		 // $("#sidebar .custom-menu .btn").css({"margin-left":"-38px"})
		}
			else
			$("#content").css({"margin-left":"250px"})
		 }
	else
	 {
	 if($("#sidebar").hasClass("active"))
	  	  $("#content").css({"margin-left":"250px"})
	  		else
	  			{
	  		$("#content").css({"margin-left":"0px"})
	  	//	$("#sidebar .custom-menu .btn").css({"margin-left":"-38px"})
	  			}
	 }
	}
</script>

<script type="text/x-mathjax-config">
MathJax.Hub.Config({
  CommonHTML: { linebreaks: { automatic: true } },
  "HTML-CSS": { linebreaks: { automatic: true } },
         SVG: { linebreaks: { automatic: true } }
});
</script>

<script type="text/javascript">

if (roles.includes("STUDENT")) {
	window.onload = function () {
		com.coep.test.home.getAllTopicToHomePage(itoStud, topicArray);
	}
}


</script>

<script type="text/javascript">
var data =	<%=request.getAttribute("topicData")%>;

// console.log(data);

if (data != null) {
// 	alert(data);
// setTimeout(function(){ window.open("test?testId="+data.testId,	"full",	"dependent=yes, location=no, fullscreen=yes, scrollbars=yes, titlebar=yes, width= "+ window.screen.width+ ", height= "+ window.screen.height);}, 200);
if (data.testTypeId == 1) { // Practice Test Id
	
	setTimeout(function(){ window.open("test?testId="+data.testId,	"_self",	"dependent=no, location=no, fullscreen=yes, scrollbars=yes, titlebar=yes, width= "+ window.screen.width+ ", height= "+ window.screen.height);}, 10);
}else if (data.testTypeId == 5) {// Other Test Id
	
	setTimeout(function(){ window.open("othertest?testId="+data.testId+"&testTypeId="+data.testTypeId,	"_self",	"dependent=no, location=no, fullscreen=yes, scrollbars=yes, titlebar=yes, width= "+ window.screen.width+ ", height= "+ window.screen.height);}, 10);
}


}

window.history.forward();
function noBack() { 
     window.history.forward(); 
}


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
</html>