<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta name="description" content="">
<meta name="author" content="">

<title>Registration</title>
<link rel="icon" href="resource/images/VirtualMathsLabLogo.png" />


<!-- Bootstrap core CSS -->
<link href="resource/css/toastr.css" rel="stylesheet"/>
<link rel="stylesheet" href="resource/css/bootstrap4.5.2.min.css">

	<link rel="stylesheet" href="resource/css/jquery-ui.min.css" />
    <link rel="stylesheet" href="resource/css/jquery-ui-timepicker-addon.min.css" />

<link href="resource/css/style.css" rel="stylesheet">
<link href="resource/css/showToast.css" rel="stylesheet">

<style>
body{    
/*   background: linear-gradient(to right,#d7c49eff, #f4f4f4);  */
  background: url(resource/images/mathsbgn.jpg) ;    

  }
.nav-pills .nav-link {
    background: #9e9e9e47;
    margin: 5px;
    border-radius: .25rem;
}
</style>

<script src='https://www.google.com/recaptcha/api.js'></script>

</head>


<script type="text/javascript">

</script>
<body >

	
<!-- 	<nav class="" style="background-color: #012333;"> -->
<!-- 		<div class="container-fluid"> -->
<!-- 			<div class="row"> -->
<!-- 			<a class="navbar-brand" href="#" id="logoimg"><img -->
<!-- 				src="resource/images/VirtualMathsLabLogo.png"></a> -->
<!-- 				<ul class=" pull-right"> -->
<!-- 					<li><a href="welcome" title="HOME" style="color: #fff;">Home</a></li> -->
<!-- 					<li><a href="login" title="LOGIN" style="color: #fff;">Login</a></li>  -->
<!-- <!-- 					<li><a href="registration" title="REGISTER" style="color: #fff;">Register</a></li> --> 
<!-- 				</ul> -->
<!-- 			</div> -->


<!-- 		</div> -->
<!-- 	</nav> -->
<div class="container" >
<div id="CallAfterRegDiv" style="display:none;">
<div class="col-sm-12 offset-md-4 col-md-4 offset-lg-4 col-lg-4  offset-xl-4 col-xl-4"><img class="img-responsive" src="resource/images/VirtualMathsLabLogo.png" style="width:80%; margin:0 10%;"><h2></h2></div>
      <div class="regLinkDiv col-sm-12 offset-md-4 col-md-4 offset-lg-4 col-lg-4  offset-xl-4 col-xl-4"><h3 >Already Registered? </h3>
						<h2><a href="login" title="SIGNIN" class="regSignIn" style="color:#343148;">Sign In</a></h2><i style="color:#fff;">or</i><h2 ><a href="registration" title="REGISTER" style="color:#343148;" class="regSignIn">Register</a></h2>
					</div>
					</div>
  <div class="row" id="registrationDiv">
      <div class="col-sm-12 col-md-12 col-lg-12 col-xl-12">
        <ul class="nav nav-pills">
<!--           <li class="nav-item"> -->
<!--             <a class="nav-link " data-toggle="pill" href="#student" role="tab" aria-controls="pills-flamingo" aria-selected="true">Student Registration</a> -->
<!--           </li> -->
<!--           <li class="nav-item"> -->
<!--             <a class="nav-link" data-toggle="pill" href="#teacher" role="tab" aria-controls="pills-cuckoo" aria-selected="false">Teacher Registration</a> -->
<!--           </li> -->
<!--           <li class="nav-item"> -->
<!--             <a class="nav-link" data-toggle="pill" href="#parent" role="tab" aria-controls="pills-ostrich" aria-selected="false">Parent Registration</a> -->
<!--           </li> -->
          <li class="nav-item">
            <a class="nav-link " data-toggle="pill" href="#Contributor" role="tab" aria-controls="pills-tropicbird" aria-selected="false">Contributor Registration</a>
          </li>
        </ul>
        <div class="tab-content mt-3">
        <div class="tab-pane fade show active" id="reg" role="tabpanel" aria-labelledby="flamingo-tab">
       
        <div class="col-sm-12 offset-md-4 col-md-4 offset-lg-4 col-lg-4  offset-xl-4 col-xl-4"><img class="img-responsive" src="resource/images/VirtualMathsLabLogo.png" style="width:80%; margin:0 10%;"><h2></h2></div>
      <div class="regLinkDiv col-sm-12 offset-md-4 col-md-4 offset-lg-4 col-lg-4  offset-xl-4 col-xl-4"><h3 >Already Registered? </h3>
						<h2 ><a href="login" title="SIGNIN" class="regSignIn">Sign In</a></h2>
					</div>
        </div>
        <div class="tab-pane fade show " id="student" role="tabpanel" aria-labelledby="flamingo-tab">
         
        


<h1>STUDENT REGISTRATION</h1>
				<!-- 			student start -->
			<div id="studRegistraion"  >
			
				<div class="row">
				
				
				
				<div class="col-sm-12 offset-md-1 col-md-10 offset-lg-1 col-lg-10  offset-xl-1 col-xl-10">
					<form id="studRegisForm"  novalidate >
					<div class="row">
					        <div class="form-group col-sm-12 col-md-6 col-xl-6">
								<label class="col-sm-12 control-label">Email</label>
								<div class="col-sm-12">
									<input type="text" id="s_Email" class="form-control"  placeholder="Enter Student Email Id" autocomplete="off" required/>
								<div class="invalid-feedback">Please enter Email Address.</div>
								</div>
							</div>
							<div class="form-group col-sm-12 col-md-6 col-xl-6">
								<label class="col-sm-12 control-label">Parent Email</label>
								<div class="col-sm-12">
									<input type="text" id="s_ParentEmail" class="form-control"  placeholder="Enter Parent Email Id" autocomplete="off" required/>
								<div class="invalid-feedback">Please enter Parent Email</div>
								</div>
							</div>
					</div>
							
					<div class="row">
							<div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">First Name</label>
								<div class="col-sm-12">
									<input type="text"  id="s_fname" class="form-control"  placeholder="Enter first name" autocomplete="off" required/>
								<div class="invalid-feedback">Please enter First name</div>
								</div>
							</div>
							
							<div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">Middle Name</label>
								<div class="col-sm-12">
									<input type="text"  id="s_mname" class="form-control"   placeholder="Enter middle name" autocomplete="off" />
								</div>
							</div>
							
							<div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">Last Name</label>
								<div class="col-sm-12">
									<input type="text" id="s_lname" class="form-control"   placeholder="Enter last name" autocomplete="off" required/>
								<div class="invalid-feedback">Please enter Last Name</div>
								</div>
							</div>
                      </div>    
                      
                      <div class="row">
							<div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">Contact No</label>
								<div class="col-sm-12">
									<input type="number" min="0" id="s_contact" class="form-control" maxlength="10"  placeholder="Enter 10 digit contact number" autocomplete="off" required/>
								<div id="PHerror"></div>
								<div class="invalid-feedback">Please enter Contact No</div>
								</div>
							</div>
							
							<div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">WhatsApp No</label>
								<div class="col-sm-12">
									<input type="number" min="0" id="s_whatsAppNo" class="form-control"  maxlength="10"  placeholder="Enter 10 digit whatsApp number" autocomplete="off" required/>
								<div id="WAerror"></div>
								<div class="invalid-feedback">Please enter Whatsapp No</div>
								</div>
							</div>
							  
                            <div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">Gender</label>
								<div class="col-sm-12">
		                            <select id="s_gender"  title="Select Gender"
										class="form-control" autocomplete="off" required>
										<option value="">-----Select Gender-----</option>
										<option value="1">Male</option>
										<option value="2">Female</option>
									</select>
									<div class="invalid-feedback">Please select Gender</div>
								</div>
							</div>
							
                     </div>

					<div class="row">		
					        <div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">Date of Birth</label>
								<div class="col-sm-12">
<!-- 									<input type="date" id="dob" name="dob" class="form-control" name="birthday" autocomplete="off"/> -->
 								<input id="s_dob"    class="form-control datepicker" required/>
 								<div class="invalid-feedback">Please enter DOB</div>
								</div>
							</div>
							
							<div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">Standard</label>
								<div class="col-sm-12">
		                                 <select id="s_standard"  title="Select standard"
										class="form-control" autocomplete="off" required>
										<option value="">Select Standard</option>
										<option value="1">1st</option>
										<option value="2">2nd</option>
										<option value="3">3rd</option>
										<option value="4">4th</option>
										<option value="5">5th</option>
										<option value="6">6th</option>
										<option value="7">7th</option>
										<option value="8">8th</option>
										<option value="9">9th</option>
										<option value="10">10th</option>
									    </select>
									     <div class="invalid-feedback">Please Select Standard</div>
							    </div>
							   
							 </div>

							
							<div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">Medium</label>
								<div class="col-sm-12">
							           <select id="s_medium"  title="Select Medium"
								         class="form-control medium" autocomplete="off" required>
											<option value="">Select Medium</option>
										</select>
										<div class="invalid-feedback">Please Select Medium</div>
								</div>
							</div>
							
					</div>
					
					 		<div class="form-group">
								<label class="col-sm-12 control-label">School Name</label>
								<div class="col-sm-12">
									<input type="text" id="s_schoolname" class="form-control"  placeholder="Enter school name" autocomplete="off" required/>
								<div class="invalid-feedback">Please enter school name</div>
								</div>
							</div>
								
							<div class="form-group ">
								<label class="col-sm-12 control-label">School Address</label>
								<div class="col-sm-12">
									<input type="text" id="s_schooladdress" class="form-control"   placeholder="Enter school address" autocomplete="off" required/>
								<div class="invalid-feedback">Please School Address</div>
								</div>
							</div>
							

					<div class="row">	
					
					<div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">School Type</label>
								<div class="col-sm-12">
		                                 <select id="s_schoolType" title="Select school type"
										class="form-control" autocomplete="off" required>
										<option value="">School Type</option>
										<option value="1">Private School</option>
										<option value="2">Public School</option>
										<option value="3">Government School</option>
										<option value="4">ZP School</option>
										<option value="5">Corporation School</option>
									    </select>
									    <div class="invalid-feedback">Please select school type</div>
							    </div>
							 </div>
							 
							<div class="form-group col-sm-12 col-md-4 col-xl-4">
							<label class="col-sm-12 control-label">Select City</label>
						<div class="col-sm-12">
								<select   id="s_city" class="form-control city" autocomplete="off" required>
									<option selected="selected" value="">Select City</option>
								
								</select>
								<div class="invalid-feedback">Please Select City</div>
								</div>
							</div>
							
							<div class="form-group col-sm-12 col-md-4 col-xl-4"> 
								<label class="col-sm-12 control-label">Pincode</label>
								<div class="col-sm-12">
									<input type="number" min="0" id="s_pincode" maxlength="6" class="form-control"  autocomplete="off" required/>
								<div class="invalid-feedback">Please enter Pincode</div>
								</div>
 							</div> 
					</div>
							
							
                     <div class="row">       
                            <div class="form-group col-sm-12 col-md-6 col-xl-6">
								<label class="col-sm-12 control-label">Password</label>
								<div class="col-sm-12">
									<input type="password" id="s_pass" minlength="6"  class="form-control"  autocomplete="new-password" required/>
								<div id="Spwd_error"></div>
								<div class="invalid-feedback">Please Enter a password of at least 6 characters</div>
								</div>
							</div>
							
							<div class="form-group col-sm-12 col-md-6 col-xl-6">
								<label class="col-sm-12 control-label">Confirm Password</label>
								<div class="col-sm-12">
									<input type="password" id="s_conPass" minlength="6"  class="form-control"  autocomplete="new-password" required/>
								<div id="error"></div>
								<div  class="invalid-feedback">Please enter Confirm Password of at least 6 characters</div>
								</div>
							</div>
					</div>	
					<div class="form-group">
      <div class="form-check">
        <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>
        <label class="form-check-label" for="invalidCheck">
      Agree to terms and conditions
    </label>
        <div class="invalid-feedback">
          You must agree before submitting.
        </div>
      </div>
    </div>


					<div class="col-md-12" align="center">
					
						<button id="studRegisNowBtn" type="submit" title="Register Now"
							class="btn btn-dark" >
							<h4>REGISTER NOW</h4>
						</button>
						

					</div>
					<div class="col-sm-12" align="center" style="margin-bottom: 40px;">
						<a href="login" title="Sign In"><h2 class="darkbrown">Already
								Registered? Sign in</h2></a>
					</div>


			</form>
				</div>
				</div>

			</div>  <!-- 			student end -->
</div>
        <div class="tab-pane fade" id="teacher" role="tabpanel" aria-labelledby="profile-tab">
         
<!-- 	Teacher  Registration -->
			<h1>TEACHER REGISTRATION</h1>
			
			<div id="teacherRegistraion" >
         		<div class="row">
				
				
				
				<div class="col-sm-12 offset-md-1 col-md-10 offset-lg-1 col-lg-10  offset-xl-1 col-xl-10">
					<form id="teacherRegisForm" novalidate >
					
					<div class="form-group ">
								<label class="col-sm-12 control-label">Email</label>
								<div class="col-sm-12 col-md-6 col-xl-6">
									<input type="email" id="t_email" class="form-control"   placeholder="Enter email ID" autocomplete="off" required/>
								<div class="invalid-feedback">Please enter Email </div>
								</div>
							</div>
					
					
					<div class="row">	
							<div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">First Name</label>
								<div class="col-sm-12">
									<input type="text"  id="t_fname" class="form-control" placeholder="Enter first name" autocomplete="off" required/>
								<div class="invalid-feedback">Please enter first Name</div>
								</div>
							</div>
							
							<div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">Middle Name</label>
								<div class="col-sm-12">
									<input type="text"  id="t_mname" class="form-control" placeholder="Enter middle name" autocomplete="off"/>
								</div>
							</div>
							
							<div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">Last Name</label>
								<div class="col-sm-12">
									<input type="text" id="t_lname" class="form-control" placeholder="Enter last name" autocomplete="off" required/>
								<div class="invalid-feedback">Please enter Last Name</div>
								</div>
							</div>
                       </div>
                       
                       <div class="row">     
                            <div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">Contact No</label>
								<div class="col-sm-12">
									<input type="number" min="0" id="t_contact" maxlength="10" class="form-control"  placeholder="Enter 10 digit contact number" autocomplete="off" required/>
								<div id="t_PHerror"></div>
								<div class="invalid-feedback">Please enter contact no</div>
								</div>
							</div>
							
							<div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">WhatsApp No</label>
								<div class="col-sm-12">
									<input type="number" min="0" id="t_whatsAppNo" maxlength="10" class="form-control" placeholder="Enter 10 digit whatsApp number" autocomplete="off" required/>
								<div id="t_WAerror"></div>
								<div class="invalid-feedback">Please enter whatsApp no</div>
								</div>
							</div>
							
                            <div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">Gender</label>
								<div class="col-sm-12">
		                            <select id="t_gender" title="Select Gender"
										class="form-control" autocomplete="off" required>
										<option value="">Gender</option>
										<option value="1">Male</option>
										<option value="2">Female</option>
									</select>
									<div class="invalid-feedback">Please select gender</div>
								</div>
							</div>
							
                      </div>
                      <div class="row">
							<div class="form-group col-sm-12 col-md-3 col-xl-3"> 
								<label class="col-sm-12 control-label">Years of Experience</label>
								<div class="col-sm-12">
									<input type="text" id="t_yearsOfExp"  class="form-control"  autocomplete="off" required/>
								<div class="invalid-feedback">Please enter years of Experience</div>
								</div>
 							</div> 
							<div class="form-group col-sm-12 col-md-9 col-xl-9">
								<label class="col-sm-12 control-label">School Name</label>
								<div class="col-sm-12">
									<input type="text" id="t_schoolname" class="form-control"  placeholder="Enter school name" autocomplete="off" required/>
								<div class="invalid-feedback">Please enter school Name</div>
								</div>
							</div>
						</div>
							
						<div class="row">
							<div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">School Type</label>
								<div class="col-sm-12">
		                                 <select id="t_schoolType" title="Select school type"
										class="form-control" autocomplete="off" required>
										<option value="">School Type</option>
										<option value="1">Private School</option>
										<option value="2">Public School</option>
										<option value="3">Government School</option>
										<option value="4">ZP School</option>
										<option value="5">Corporation School</option>
									    </select>
									    <div class="invalid-feedback">Please select school type</div>
							    </div>
							 </div>

							
							<div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">Medium</label>
								<div class="col-sm-12">
							           <select id="t_medium"  title="Select Medium"
								         class="form-control medium" autocomplete="off" required>
											<option value="">Select Medium</option>
										</select>
										<div class="invalid-feedback">Please select medium</div>
								</div>
							</div>	
							
								<div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">Teaching Group</label>
								<div class="col-sm-12">
							           <select id="t_group"  title="Select Teaching Group"
								         class="form-control" autocomplete="off" required>
											<option value="">Select Teaching Group</option>
											<option value="1">I-V</option>
											<option value="2">VI-VII</option>
											<option value="3">VIII- X</option>
										</select>
										<div class="invalid-feedback">Please select teaching group</div>
								</div>
							</div>
								
						</div>	
 							
							<div class="form-group">
								<label class="col-sm-12 control-label">School Address</label>
								<div class="col-sm-12">
									<input type="text" id="t_schooladdress" class="form-control"  placeholder="Enter school address" autocomplete="off" required/>
								<div class="invalid-feedback">Please enter school address</div>
								</div>
							</div>
							

					<div class="row">	
							<div class="form-group col-sm-12 col-md-4 col-xl-4">
							<label class="col-sm-12 control-label ">Select City</label>
						<div class="col-sm-12">
								<select  id="t_city" class="form-control city" autocomplete="off" required>
									<option selected="selected" value="">Select City</option>
								</select>
								<div class="invalid-feedback">Please select city</div>
								</div>
							</div>
							
							<div class="form-group col-sm-12 col-md-4 col-xl-4"> 
								<label class="col-sm-12 control-label">Pincode</label>
								<div class="col-sm-12">
									<input type="number" min="0" id="t_pincode"  class="form-control"  autocomplete="off" required/>
								<div class="invalid-feedback">Please enter pincode</div>
								</div>
 							</div> 

							<div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">Date of Birth</label>
								<div class="col-sm-12">
<!-- 									<input type="date" id="dob" name="dob" class="form-control" name="birthday" autocomplete="off"/> -->
 								<input id="t_dob"   class="form-control datepicker" required/>
 								<div class="invalid-feedback">Please enter DOB</div>
								</div>
							</div>
					</div>
							

					<div class="row">	
                            
                            <div class="form-group col-sm-12 col-md-6 col-xl-6">
								<label class="col-sm-12 control-label">Password</label>
								<div class="col-sm-12">
									<input type="password" id="t_pass" minlength="6" class="form-control"  autocomplete="new-password" required/>
								<div id="Tpwd_error"></div>
								<div class="invalid-feedback">Please enter password of at least 6 characters</div>
								</div>
							</div>
							
							<div class="form-group col-sm-12 col-md-6 col-xl-6">
								<label class="col-sm-12 control-label">Confirm Password</label>
								<div class="col-sm-12">
									<input type="password" id="t_conPass" minlength="6" class="form-control"  autocomplete="new-password" required/>
								<div id="Teacherror"></div>
								<div class="invalid-feedback">Please enter confirm password of at least 6 characters</div>
								</div>
							</div> 
					</div>	
					
					<div class="form-group">
      <div class="form-check">
        <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>
        <label class="form-check-label" for="invalidCheck">
      Agree to terms and conditions
    </label>
        <div class="invalid-feedback">
          You must agree before submitting.
        </div>
      </div>
    </div>


					<div class="col-md-12" align="center">
					
						<button id="teacherRegisNowBtn" type="submit" title="Register Now"
							class="btn btn-dark">
							<h4>REGISTER NOW</h4>
						</button>
						

					</div>
					<div class="col-sm-12" align="center" style="margin-bottom: 40px;">
						<a href="login" title="Sign In"><h2 class="darkbrown">Already
								Registered? Sign in</h2></a>
					</div>


			</form>
				</div>
				</div>
					
			</div>
			
<!-- 			teacher end -->			
		
		 
        </div>
        <div class="tab-pane fade" id="parent" role="tabpanel" aria-labelledby="ostrich-tab">
         
					
<!-- 			Project Sponsor / Industrial Mentor Registration. -->
			
			<h1>PARENT REGISTRATION</h1>
		<div id="IndMentorRegistraion" >

				<div class="row">
				
				
				
				<div class="col-sm-12 offset-md-1 col-md-10 offset-lg-1 col-lg-10  offset-xl-1 col-xl-10">
					<form id="parentRegisForm" novalidate>
					
					<div class="form-group ">
								<label class="col-sm-12 col-md-6 col-xl-6 control-label">Email</label>
								<div class="col-sm-12 col-md-6 col-xl-6">
									<input type="email" id="p_email" class="form-control" placeholder="Enter email ID" autocomplete="off" required/>
								<div class="invalid-feedback">Please enter Email </div>
								</div>
							</div>
							
							<div class="row">
							<div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">First Name</label>
								<div class="col-sm-12">
									<input type="text"  id="p_fname" class="form-control"  placeholder="Enter first name" autocomplete="off" required/>
								<div class="invalid-feedback">Please enter first name </div>
								</div>
							</div>
							
							<div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">Middle Name</label>
								<div class="col-sm-12">
									<input type="text"  id="p_mname" class="form-control"  placeholder="Enter middle name" autocomplete="off"/>
								</div>
							</div>
						
							<div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">Last Name</label>
								<div class="col-sm-12">
									<input type="text" id="p_lname" class="form-control" placeholder="Enter last name" autocomplete="off" required/>
								<div class="invalid-feedback">Please enter middle name </div>
								</div>
							</div>
                  </div> 
							
				<div class="row">	
					       <div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">Contact No</label>
								<div class="col-sm-12">
									<input type="number" min="0" id="p_contact" class="form-control"  placeholder="Enter 10 digit contact number" autocomplete="off" required/>
								<div id="p_PHerror"></div>
								<div class="invalid-feedback">Please enter Contact no </div>
								</div>
							</div>
							
							<div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">WhatsApp No</label>
								<div class="col-sm-12">
									<input type="number" min="0" id="p_whatsAppNo" class="form-control"  placeholder="Enter 10 digit whatsApp number" autocomplete="off" required/>
								<div id="p_WAerror"></div>
								<div class="invalid-feedback">Please enter Whatsapp no </div>
								</div>
							</div>
							
							<div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">Gender</label>
								<div class="col-sm-12">
		                            <select id="p_gender"  title="Select Gender"
										class="form-control" autocomplete="off" required>
										<option value="">Gender</option>
										<option value="1">Male</option>
										<option value="2">Female</option>
									</select>
								<div class="invalid-feedback">Please select gender </div>
								</div>
							</div>
					</div>		
					
                            <div class="form-group "> 
								<label class="col-sm-12 col-md-12 col-xl-12 control-label">Name of the School/company</label>
								<div class="col-sm-12 col-md-12 col-xl-12" >
									<input type="text" id="p_company"  class="form-control"  autocomplete="off" required/>
								<div class="invalid-feedback">Please enter School/Company </div>
								</div>
 							</div>
 							
				<div class="row">		
							<div class="form-group col-sm-12 col-md-4 col-xl-4"> 
								<label class="col-sm-12 control-label">Qualification</label>
								<div class="col-sm-12">
									<input type="text" id="p_qualif"  class="form-control"  autocomplete="off" required/>
								<div class="invalid-feedback">Please enter Qualification </div>
								</div>
 							</div>
							
							<div class="form-group col-sm-12 col-md-4 col-xl-4"> 
								<label class="col-sm-12 control-label">Occupation</label>
								<div class="col-sm-12">
									<input type="text" id="p_occupation"  class="form-control"  autocomplete="off" required/>
								<div class="invalid-feedback">Please enter Occupation </div>
								</div>
 							</div>
 							
 							<div class="form-group col-sm-12 col-md-4 col-xl-4"> 
								<label class="col-sm-12 control-label">Designation</label>
								<div class="col-sm-12">
									<input type="text" id="p_desig"  class="form-control" autocomplete="off" required/>
								<div class="invalid-feedback">Please enter Designation </div>
								</div>
 							</div> 
 				</div>			
 							
 		<div class="row">
 							<div class="form-group col-sm-12 col-md-4 col-xl-4">
							<label class="col-sm-12 control-label">Select City</label>
							<div class="col-sm-12">
								<select   id="p_city" class="form-control city" autocomplete="off" required>
									<option selected="selected" value="">Select City</option>
								</select>
								<div class="invalid-feedback">Please select city </div>
								</div>
							</div>
							
							<div class="form-group col-sm-12 col-md-4 col-xl-4"> 
								<label class="col-sm-12 control-label">Pincode</label>
								<div class="col-sm-12">
									<input type="number" min="0" id="p_pincode"  class="form-control"  autocomplete="off" required/>
								<div class="invalid-feedback">Please enter Pincode </div>
								</div>
 							</div>
							
							<div class="form-group col-sm-12 col-md-4 col-xl-4"> 
								<label class="col-sm-12 control-label">Length of service</label>
								<div class="col-sm-12">
									<input type="number" id="p_service" min="0" class="form-control"  autocomplete="off" required/>
								<div class="invalid-feedback">Please enter length of service</div>
								</div>
 							</div> 
                   </div> 
                   
                  <div class="row">
							<div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">Date of Birth</label>
								<div class="col-sm-12">
<!-- 									<input type="date" id="dob" name="dob" class="form-control" name="birthday" autocomplete="off" required/> -->
 								<input id="p_dob"   class="form-control datepicker" required/>
 								<div class="invalid-feedback">Please enter DOB </div>
								</div>
							</div>
							
                            
                            <div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">Password</label>
								<div class="col-sm-12">
									<input type="password" id="p_pass" minlength="6" class="form-control"  autocomplete="new-password" required/>
								<div id="Ppwd_error"></div>
								<div class="invalid-feedback">Please enter Password of at least 6 characters</div>
								</div>
							</div>
							
							<div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">Confirm Password</label>
								<div class="col-sm-12">
									<input type="password" id="p_conPass" minlength="6" class="form-control" autocomplete="new-password" required/>
								<div id="parterror"></div>
								<div class="invalid-feedback">Please enter Conform password of at least 6 characters</div>
								</div>
							</div> 
							
					</div> 		 
							
					<div class="form-group">
      <div class="form-check">
        <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>
        <label class="form-check-label" for="invalidCheck">
      Agree to terms and conditions
    </label>
        <div class="invalid-feedback">
          You must agree before submitting.
        </div>
      </div>
    </div>



					<div class="col-md-12" align="center">
					
						<button id="parentRegisNowBtn" type="submit" title="Register Now"
							class="btn btn-dark">
							<h4>REGISTER NOW</h4>
						</button>
						

					</div>
					<div class="col-sm-12" align="center" style="margin-bottom: 40px;">
						<a href="login" title="Sign In"><h2 class="darkbrown">Already
								Registered? Sign in</h2></a>
					</div>


			</form>
				</div>
				</div>
 				
			
			</div>
			<!-- 			three end -->	
		
        </div>
        <div class="tab-pane fade" id="Contributor" role="tabpanel" aria-labelledby="tropicbird-tab">
         <h1>CONTRIBUTOR REGISTRATION</h1>
			<div id="contriRegistraion" >
			
			<div class="row">
				
				
				
				<div class="col-sm-12 offset-md-1 col-md-10 offset-lg-1 col-lg-10  offset-xl-1 col-xl-10">
					<form id="contriRegisForm" novalidate>
				
					
					<div class="form-group">
								<label class="col-sm-12 col-md-6 col-xl-6 control-label">Email</label>
								<div class="col-sm-12 col-md-6 col-xl-6 ">
									<input type="email" id="email" class="form-control"   placeholder="Enter email ID" autocomplete="off" required/>
								<div class="invalid-feedback">Please enter Email </div>
								</div>
							</div> 
				<div class="row">			
							<div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">First Name</label>
								<div class="col-sm-12">
									<input type="text"  id="fname" class="form-control" placeholder="Enter first name" autocomplete="off" required/>
								<div class="invalid-feedback">Please enter First name </div>
								</div>
							</div>
							
							<div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">Middle Name</label>
								<div class="col-sm-12">
									<input type="text"  id="mname" class="form-control"  placeholder="Enter middle name" autocomplete="off"/>
								</div>
							</div>
							
							<div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">Last Name</label>
								<div class="col-sm-12">
									<input type="text" id="lname" class="form-control"  placeholder="Enter last name" autocomplete="off" required/>
								<div class="invalid-feedback">Please enter Last Name </div>
								</div>
							</div>
                    </div>   
                    
				<div class="row">	
					<div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">Contact No</label>
								<div class="col-sm-12">
									<input type="number" min="0" id="contact" class="form-control"  placeholder="Enter 10 digit contact number" autocomplete="off" required/>
								<div id="c_PHerror"></div>
								<div class="invalid-feedback">Please enter Contact no </div>
								</div>
							</div>
							
							<div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">WhatsApp No</label>
								<div class="col-sm-12">
									<input type="number" min="0" id="whatsAppNo" class="form-control" placeholder="Enter 10 digit whatsApp number" autocomplete="off" required/>
								<div id="c_WAerror"></div>
								<div class="invalid-feedback">Please enter WhatsApp no </div>
								</div>
							</div>
							
							 <div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">Gender</label>
								<div class="col-sm-12">
		                            <select id="gender"  title="Select Gender"
										class="form-control" autocomplete="off" required>
										<option value="">Gender</option>
										<option value="1">Male</option>
										<option value="2">Female</option>
									</select>
									<div class="invalid-feedback">Please select gender </div>
								</div>
							</div>
				</div>			
				     
           
							<div class="form-group">
								<label class="col-sm-12 col-md-12 col-xl-12 control-label">School Name</label>
								<div class="col-sm-12 col-md-12 col-xl-12">
									<input type="text" id="schoolname" class="form-control"  placeholder="Enter school name" autocomplete="off" required/>
								<div class="invalid-feedback">Please enter School name </div>
								</div>
							</div>
				
				<div class="row">			
							<div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">School Type</label>
								<div class="col-sm-12">
		                                 <select id="schoolType" title="Select school type"
										class="form-control" autocomplete="off" required>
										<option value="">School Type</option>
										<option value="1">Private School</option>
										<option value="2">Public School</option>
										<option value="3">Government School</option>
										<option value="4">ZP School</option>
										<option value="5">Corporation School</option>
									    </select>
									    <div class="invalid-feedback">Please select school type </div>
							    </div>
							 </div>

							
							<div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">Medium</label>
								<div class="col-sm-12">
							           <select id="medium"  title="Select Medium"
								         class="form-control medium" autocomplete="off" required>
											<option value="">Select Medium</option>
										</select>
										<div class="invalid-feedback">Please select medium </div>
								</div>
							</div>	
							
								
								
							<div class="form-group col-sm-12 col-md-4 col-xl-4"> 
								<label class="col-sm-12 control-label">Years of Experience</label>
								<div class="col-sm-12">
									<input type="text" min="0" id="yearsOfExp"  class="form-control"  autocomplete="off" required />
								<div class="invalid-feedback">Please enter Years of experience </div>
								</div>
 							</div> 
 					
 					</div>
 						
							<div class="form-group">
								<label class="col-sm-12 col-md-12 col-xl-12 control-label">School Address</label>
								<div class="col-sm-12 col-md-12 col-xl-12">
									<input type="text" id="schooladdress" class="form-control"  placeholder="Enter school address" autocomplete="off" required/>
								<div class="invalid-feedback">Please enter school address </div>
								</div>
							</div>
							

					<div class="row">
						
							<div class="form-group col-sm-12 col-md-4 col-xl-4">
							<label class="col-sm-12 control-label">Select City</label>
						<div class="col-sm-12">
								<select  id="city" class="form-control city" autocomplete="off" required>
									<option selected="selected" value="">Select City</option>
								</select>
								<div class="invalid-feedback">Please select city</div>
								</div>
							</div>
							
							<div class="form-group col-sm-12 col-md-4 col-xl-4"> 
								<label class="col-sm-12 control-label">Pincode</label>
								<div class="col-sm-12">
									<input type="number" min="0" id="pincode"  class="form-control" name="pincode" autocomplete="off" required/>
								<div class="invalid-feedback">Please enter Pincode </div>
								</div>
 							</div> 

							<div class="form-group col-sm-12 col-md-4 col-xl-4">
								<label class="col-sm-12 control-label">Date of Birth</label>
								<div class="col-sm-12">
<!-- 									<input type="date" id="dob" name="dob" class="form-control" name="birthday" autocomplete="off"/> -->
 								<input id="dob"  name="dob"   class="form-control datepicker" required/>
 								<div class="invalid-feedback">Please enter DOB </div>
								</div>
							</div>
							
                      </div>
                      
                      <div class="row">
                       <div class="form-group col-sm-12 col-md-12 col-xl-12">
								<label class="col-sm-12 control-label">Question contributor type</label>
								<div class="col-sm-12 col-md-12 col-xl-12">
		                            <select id="contribType"  title="Select contributor type"
										class="form-control" autocomplete="off" required>
										<option value="">-----Select-----</option>
										<option value="1">I am Interested to learn Latex code and provide question in latex coded form</option>
										<option value="2">I will provide question without latex coded form</option>
									</select>
									<div class="invalid-feedback">Please select contributor type </div>
									<div id="contribTypeMsg" hidden="true" style="color: green;">Latex code training sessions will be made available by us free of cost.You will get informed regarding such sessions.</div>
								</div>
							</div>
							</div>
                      
                      <div class="row">      
                            <div class="form-group col-sm-12 col-md-6 col-xl-6">
								<label class="col-sm-12 control-label">Password</label>
								<div class="col-sm-12">
									<input type="password" id="password" minlength="6" class="form-control" name="password" autocomplete="new-password" required/>
								<div id="Cpwd_error"></div>
								<div class="invalid-feedback">Please enter password of at least 6 characters</div>
								</div>
							</div>
							
							<div class="form-group col-sm-12 col-md-6 col-xl-6">
								<label class="col-sm-12 control-label">Confirm Password</label>
								<div class="col-sm-12">
									<input type="password" id="confirmPassword" minlength="6" class="form-control" name="confirmpassword" autocomplete="new-password" required/>
								<div id="conerror"></div>
								<div class="invalid-feedback">Please enter confirm password of at least 6 characters</div>
								</div>
							</div> 
					</div>		
					<div class="form-group">
      <div class="form-check">
        <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>
        <label class="form-check-label" for="invalidCheck">
      Agree to terms and conditions
    </label>
        <div class="invalid-feedback">
          You must agree before submitting.
        </div>
      </div>
    </div>



					<div class="col-md-12" align="center">
					
						<button id="contriRegisNowBtn" type="submit" title="Register Now"
							class="btn btn-dark">
							<h4>REGISTER NOW</h4>
						</button>
						

					</div>
					<div class="col-sm-12" align="center" style="margin-bottom: 40px;">
						<a href="login" title="Sign In"><h2 class="darkbrown">Already
								Registered? Sign in</h2></a>
					</div>


			</form>
				</div>
				</div>
			</div>


			</div>
			
<!-- 			four end -->
			 </div>
      </div>        
      </div>
  </div>
</div>	
		

	  <script src="resource/js/toastr.js"></script>
	  <script src="resource/js/jquery-3.5.min.js"></script>
  <script src="resource/js/popper.min.js"></script>
  <script src="resource/js/bootstrap-4.5.min.js"></script>

	<script src="resource/js/jquery-ui.min.js"></script>
    <script src="resource/js/jquery-ui-timepicker-addon.min.js"></script>
    
	<script src="resource/js/custom.js"></script>

	<!--  <script src="js/addproblem.js"></script> -->
	<!-- form validation js  -->
	<script src="resource/js/formvalidation.min.js"></script>
	<script src="resource/js/bootstrap-validation.min.js"></script>
	<script src="resource/js/jquery.validate.js"></script>
	<!--  <script src="resource/js/location.js"></script> -->

	<script src="resource/js/showToast.js"></script>
	<!-- <script src="resource/js/jquery.js"></script>   -->

	<script src="resource/core/namespace.js"></script>
	<script src="resource/core/ajaxMngr.js"></script>
	<!--      <script src="resource/src/questionBank.js"></script> -->
	<!--      <script src="resource/src/addProblem.js"></script> -->
	<script src="resource/src/addQuestionGroups.js"></script>
	<script type="text/javascript" src="resource/src/register.js"></script>
	<script src="resource/src/menu.js"></script>



	<script>
	
	$("#studRegisNowBtn").click(function(e) {
		var form = document.getElementById('studRegisForm');
		form.addEventListener('submit', function(e) {
			if (form.checkValidity() === false) {
				e.preventDefault();
				e.stopPropagation();
			}
			form.classList.add('was-validated');
			e.preventDefault();
		}, false);
		
		if (form.checkValidity() == true && STDConPwdValidation() == true && STDPhnValidation() == true && STDWAValidation() == true) {
			console.log("true");
			e.preventDefault();
com.coep.test.userProfile.registerStudent();
			
		}
		
	});
	 
	
$("#teacherRegisNowBtn").click(function(e) {
	   
		var form = document.getElementById('teacherRegisForm');
		form.addEventListener('submit', function(e) {
			if (form.checkValidity() === false ) {
				e.preventDefault();
				e.stopPropagation();
			}
			form.classList.add('was-validated');
			e.preventDefault();
		}, false);

		if (form.checkValidity() == true && TeaConPwdValidation() == true && TECPhnValidation() == true && TECWAValidation() == true) {
console.log("true");
e.preventDefault();
com.coep.test.userProfile.registerTeacher();

		}

	});
	
  
$("#parentRegisNowBtn").click(function(e) {
	
	var form = document.getElementById('parentRegisForm');
	form.addEventListener('submit', function(e) {
		if (form.checkValidity() === false) {
			e.preventDefault();
			e.stopPropagation();
		}
		form.classList.add('was-validated');
		e.preventDefault();
	}, false);

	if (form.checkValidity() == true && ParConPwdValidation() == true && PERPhnValidation() == true && PERWAValidation() == true  ) {
// console.log("true");
e.preventDefault();
com.coep.test.userProfile
.registerParent();

	}

});
   
  $("#contriRegisNowBtn").click(function(e) {
  	
  	var form = document.getElementById('contriRegisForm');
  	form.addEventListener('submit', function(e) {
  		if (form.checkValidity() === false) {
  			e.preventDefault();
  			e.stopPropagation();
  		}
  		form.classList.add('was-validated');
  		e.preventDefault();
  	}, false);

  	if (form.checkValidity() == true && ContConPwdValidation() == true &&  CONPhnValidation() == true && COMWAValidation() == true ) {
//   console.log("true");
  e.preventDefault();
  com.coep.test.userProfile
	.registerContributor();

  	}

  });
  
   $("#s_contact").on('change', function() {  STDPhnValidation(); });
  
   
	function STDPhnValidation() {
		
		var STDPHN = $("#s_contact").val().length;
	   
	    console.log(STDPHN);
	    if(STDPHN > 10 || STDPHN < 10){ 
	    //	$("#studRegisForm").addClass('was-validated')
		        $("#s_contact").addClass("error-text");
		        $("#PHerror").html('');
   	     $("#PHerror").html("<span class='error'>Phone number must be 10 digit only</span>");
   	     return false;
		      }
		      else {$("#PHerror").html('');
		    	  $("#s_contact").removeClass("error-text");
		    	  return true;
		      }
	    }
	
	 $("#s_whatsAppNo").on('change', function() {  STDWAValidation(); });
	 
function STDWAValidation() {
	 var STDWPN = $("#s_whatsAppNo").val().length;
	    if(STDWPN > 10 || STDWPN < 10){ 
	    //	$("#studRegisForm").addClass('was-validated')
		        $("#s_whatsAppNo").addClass("error-text");
   	  $("#WAerror").html('');
	     $("#WAerror").html("<span class='error'>WhatsApp number must be 10 digit only</span>");
   	     return false;
		      }
		      else {
		      $("#WAerror").html('');
		    	  $("#s_whatsAppNo").removeClass("error-text");
		    	  return true;
		    
		      }
	    }
	$("#s_conPass").on('change', function() {  STDConPwdValidation(); });
	
	$("#s_pass").on('change', function() {  STDConPwdValidation(); });
	
	function STDConPwdValidation() {
		 // if ($("#studRegisForm").hasClass('was-validated') ) {
   
	var pwd = $("#s_pass").val();
    var confirmPwd = $("#s_conPass").val();
   
    if(pwd !== confirmPwd && confirmPwd !== pwd ){ 
    	//$("#studRegisForm").addClass('was-validated')
		     //   $(el).closest(".form-group").find(".valid-feedback").removeClass("d-block");
		        $("#s_conPass").addClass("error-text");
		        $("#error").html('');
		        $("#s_pass").addClass("error-text");
		        $("#Spwd_error").html('');
		        
    	     $("#error").html("<span class='error'>Password & Confirm password not match.</span>");
    	     $("#Spwd_error").html("<span class='error'>Password & Confirm password not match.</span>");
    	     return false;
		      }
		      else {$("#error").html('');
		    	  $("#s_conPass").removeClass("error-text");
		    	  $("#Spwd_error").html('');
		    	  $("#s_pass").removeClass("error-text");
		    	  return true;
		     //   $(el).closest(".form-group").find(".valid-feedback").addClass("d-block");
		      }
        
    
		  //}
		}
	

	   $("#t_contact").on('change', function() {  TECPhnValidation(); });
	  
	   
		function TECPhnValidation() {
			
			var STDPHN = $("#t_contact").val().length;
		   
		    console.log(STDPHN);
		    if(STDPHN > 10 || STDPHN < 10 ){ 
		    //	$("#studRegisForm").addClass('was-validated')
			        $("#t_contact").addClass("error-text");
			        $("#t_PHerror").html('');
	   	     $("#t_PHerror").html("<span class='error'>Phone number must be 10 digit only</span>");
	   	     return false;
			      }
			      else {$("#t_PHerror").html('');
			    	  $("#t_contact").removeClass("error-text");
			    	  return true;
			      }
		    }
		
		 $("#t_whatsAppNo").on('change', function() {  TECWAValidation(); });
		
	function TECWAValidation() {
		 var STDWPN = $("#t_whatsAppNo").val().length;
		    if(STDWPN > 10 || STDWPN < 10 ){ 
		    //	$("#studRegisForm").addClass('was-validated')
			        $("#t_whatsAppNo").addClass("error-text");
	   	  $("#t_WAerror").html('');
		     $("#t_WAerror").html("<span class='error'>WhatsApp number must be 10 digit only</span>");
	   	     return false;
			      }
			      else {
			      $("#t_WAerror").html('');
			    	  $("#t_whatsAppNo").removeClass("error-text");
			    	  return true;
			    
			      }
		    }
	
	$("#t_pass").on('change', function() {  TeaConPwdValidation(); });
	
	$("#t_conPass").on('change', function() {  TeaConPwdValidation(); });
	function TeaConPwdValidation() {
		 // if ($("#studRegisForm").hasClass('was-validated') ) {

	var pwd = $("#t_pass").val();
    var confirmPwd = $("#t_conPass").val();
    if(pwd !== confirmPwd && confirmPwd !== pwd){ 
    	//$("#teacherRegisForm").addClass('was-validated')
		     //   $(el).closest(".form-group").find(".valid-feedback").removeClass("d-block");
    	
		        $("#t_conPass").addClass("error-text");
		        $("#Teacherror").html('');
    	     $("#t_pass").addClass("error-text");
		        $("#Tpwd_error").html('');
		        $("#Teacherror").html("<span class='error'>Password & Confirm password not match</span>");
 	        $("#Tpwd_error").html("<span class='error'>Password & Confirm password not match</span>");
    	     return false;
		      }
		      else {$("#Teacherror").html('');
		    	  $("#t_conPass").removeClass("error-text");
		    	  $("#Tpwd_error").html('');
		    	  $("#t_pass").removeClass("error-text");
		    	  return true;
		     //   $(el).closest(".form-group").find(".valid-feedback").addClass("d-block");
		      }

		  //}
		}
	
	$("#p_contact").on('change', function() {  PERPhnValidation(); });
	  
	   
	function PERPhnValidation() {
		
		var STDPHN = $("#p_contact").val().length;
	   
	    console.log(STDPHN);
	    if(STDPHN > 10 || STDPHN < 10){ 
	    //	$("#studRegisForm").addClass('was-validated')
		        $("#p_contact").addClass("error-text");
		        $("#p_PHerror").html('');
   	     $("#p_PHerror").html("<span class='error'>Phone number must be 10 digit only</span>");
   	     return false;
		      }
		      else {$("#p_PHerror").html('');
		    	  $("#p_contact").removeClass("error-text");
		    	  return true;
		      }
	    }
	
	 $("#p_whatsAppNo").on('change', function() {  PERWAValidation(); });
	  
function PERWAValidation() {
	 var STDWPN = $("#p_whatsAppNo").val().length;
	    if(STDWPN > 10 || STDWPN < 10 ){ 
	    //	$("#studRegisForm").addClass('was-validated')
		        $("#p_whatsAppNo").addClass("error-text");
   	  $("#p_WAerror").html('');
	     $("#p_WAerror").html("<span class='error'>WhatsApp number must be 10 digit only</span>");
   	     return false;
		      }
		      else {
		      $("#p_WAerror").html('');
		    	  $("#p_whatsAppNo").removeClass("error-text");
		    	  return true;
		    
		      }
	    }
	$("#p_pass").on('change', function() {  ParConPwdValidation(); });
	
	$("#p_conPass").on('change', function() {  ParConPwdValidation(); });
	function ParConPwdValidation() {
		 // if ($("#studRegisForm").hasClass('was-validated') ) {

	var pwd = $("#p_pass").val();
    var confirmPwd = $("#p_conPass").val();
    if(pwd !== confirmPwd && confirmPwd !== pwd){ 
    	//$("#parentRegisForm").addClass('was-validated')
		     //   $(el).closest(".form-group").find(".valid-feedback").removeClass("d-block");
    	
		        $("#p_conPass").addClass("error-text");
		        $("#parterror").html('');
		        $("#p_pass").addClass("error-text");
		        $("#Ppwd_error").html('');
    	     $("#parterror").html("<span class='error'>Password & Confirm password not match</span>");
    	     $("#Ppwd_error").html("<span class='error'>Password & Confirm password not match</span>");
    	     return false;
		      }
		      else {$("#parterror").html('');
		    	  $("#p_conPass").removeClass("error-text");
		    	  $("#Ppwd_error").html('');
		    	  $("#p_pass").removeClass("error-text");
		    	  return true;
		     //   $(el).closest(".form-group").find(".valid-feedback").addClass("d-block");
		      }

		  //}
		}
	
	$("#contact").on('change', function() {  CONPhnValidation(); });
	  
	   
	function CONPhnValidation() {
		
		var STDPHN = $("#contact").val().length;
	   
// 	    console.log(STDPHN);
	    if(STDPHN > 10 || STDPHN < 10 ){ 
	    //	$("#studRegisForm").addClass('was-validated')
		        $("#contact").addClass("error-text");
		        $("#c_PHerror").html('');
   	     $("#c_PHerror").html("<span class='error'>Phone number must be 10 digit only</span>");
   	     return false;
		      }
		      else {$("#c_PHerror").html('');
		    	  $("#contact").removeClass("error-text");
		    	  return true;
		      }
	    }
	
	 $("#whatsAppNo").on('change', function() {  COMWAValidation(); });
	 
function COMWAValidation() {
	 var STDWPN = $("#whatsAppNo").val().length;
	    if(STDWPN > 10 || STDWPN < 10 ){ 
	    //	$("#studRegisForm").addClass('was-validated')
		        $("#whatsAppNo").addClass("error-text");
   	  $("#c_WAerror").html('');
	     $("#c_WAerror").html("<span class='error'>WhatsApp number must be 10 digit only</span>");
   	     return false;
		      }
		      else {
		      $("#c_WAerror").html('');
		    	  $("#whatsAppNo").removeClass("error-text");
		    	  return true;
		    
		      }
	    }
	    
    $("#password").on('change', function() {  ContConPwdValidation(); });
    
	$("#confirmPassword").on('change', function() {  ContConPwdValidation(); });
	function ContConPwdValidation() {
		 // if ($("#studRegisForm").hasClass('was-validated') ) {

	var pwd = $("#password").val();
    var confirmPwd = $("#confirmPassword").val();
    if(pwd !== confirmPwd && confirmPwd !== pwd){ 
    	//$("#contriRegisForm").addClass('was-validated')
		     //   $(el).closest(".form-group").find(".valid-feedback").removeClass("d-block");
		        $("#confirmPassword").addClass("error-text");
		        $("#conerror").html('');
		        $("#password").addClass("error-text");
		        $("#Cpwd_error").html('');
    	     $("#conerror").html("<span class='error'>Password & Confirm password not match</span>");
    	     $("#Cpwd_error").html("<span class='error'>Password & Confirm password not match</span>");
    	     return false;
		      }
		      else {$("#conerror").html('');
		    	  $("#confirmPassword").removeClass("error-text");
		    	  $("#Cpwd_error").html('');
		    	  $("#password").removeClass("error-text");
		    	  return true;
		     //   $(el).closest(".form-group").find(".valid-feedback").addClass("d-block");
		      }

		  //}
		}
		
		 
		  
		  
		  
		 
 
	</script>


<script type="text/javascript">
$('.nav-item').on('click',function(){
	
	$("#studRegisForm")[0].reset();
    $("#teacherRegisForm")[0].reset();
	$("#parentRegisForm")[0].reset();
	$("#contriRegisForm")[0].reset();
})
</script>
	<script>

	/** Funtion to set class active **/
	
  $(document).ready(function () {  
		
	  com.coep.test.ajaxHandler.getCities(
			  com.coep.test.addProblem.baseURL + "city", "GET", 22);
	  
	  $(".datepicker").datepicker({
          closeText: "Close",
          showButtonPanel: true,
          changeMonth: true,
          changeYear: true,
          dateFormat: "yy-mm-dd",
          yearRange: "-100:+0",
          
          onSelect: function (e) {
              $(this).attr("autocomplete", "off");
              $(this).datepicker({}).val();

          },
          onClose: function () {
              this.focus();
          }
      }).click(function () { $(this).focus() });
	  
		var medium='<%=request.getAttribute("medium")%>';
		data = JSON.parse(medium);
		var medium1 = {};
		for (var i = 0; i < data.data.length; i++) {
		 medium1[data.data[i].MID] = data.data[i].MN;
		}
		
		var med = '';
		for ( var key in medium1) {
		 med += "<option value= " + key + ">" + medium1[key]+ "</option>";
		}
		$(".medium").append(med);
  });
	
</script>						
  						
	

	<script type="text/javascript">
	$("#contribType").bind("change", function(){
		if($("#contribType").val() == 1){
// 			console.log($("#contribType").val());
			$("#contribTypeMsg").prop("hidden", false);
		}else{
// 			console.log($("#contribType").val());
			$("#contribTypeMsg").prop("hidden", true);
		}
	})
	
		
// 		function blinker() {
// 		    $('.blink_me').fadeOut(500);
// 		    $('.blink_me').fadeIn(500);
// 		}

// 		setInterval(blinker, 2000);
	</script>
	<!-- <div class="g-recaptcha" data-sitekey="6LdEj2AUAAAAAPM9QwJbpXq9iuXW59zaV-BtsY9Z"></div> -->

</body>
</html>