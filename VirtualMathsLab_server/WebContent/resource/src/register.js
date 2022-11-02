(function(AH, AP, QB, UP) {

	UP.tosterData = function()
	{
		
		

			
	}
	UP.forgotPwd = function() {

		var emailId = $("#phoneNumber").val();

		if (emailId == "") {
			$("#message").addClass("error-class")
					.html("Please enter registered contact number");
		} else {
			$.ajax({

				type : "POST",
				url : AP.baseURL + "checkForgetPassData/"
						+ emailId,
				dataType : 'json',
				contentType : 'application/json',
				success : function(data) {
					if(data.done == true)
						{
					toastr.success(data.msg);
					$("#message").html('');
					$('#phoneNumber').val('');
						}else
							{
							toastr.error(data.msg);
							$("#message").html('');
							$('#phoneNumber').val('');
							}
				},
				error : function() {
				}
			});
		}
		
	};
	
	UP.changPassword = function() {

		var oldpwd = $("#oldpwd").val();
		var newpwd = $("#newpwd").val();
		var confirmpwd = $("#confirmpwd").val();
		
			$.ajax({

				type : "POST",
				url : AP.baseURL + "confirmPassData/"+newpwd,
				dataType : 'json',
				contentType : 'application/json',
				success : function(data) {
					if(data.done == true)
					{
						
						$('#oldpwd').val('');
						$('#newpwd').val('');
						$('#confirmpwd').val('');
						$('#changePwdheading').html('');
						$("#changepasswordForm").hide();
						$('.forget-heading').addClass('alert alert-success').html(data.msg)
					}else
						{
						toastr.error(data.msg);
						
						}
					
					
				},
				error : function() {
				}
			});	
	};
	
	var registrationUserJson = {};
	var updateUserJson = {};

	UP.registerStudent = function() {
		
		regUserJson = {};
		studJson = {};
		finalJson = {};
		
		var email = $("#s_Email").val();
		var fname = $("#s_fname").val();
		var mname = $("#s_mname").val();
		var lname = $("#s_lname").val();
		var contact = $("#s_contact").val();
		var whatsAppNo = $("#s_whatsAppNo").val();
		var gen = $("#s_gender").val();
		var pass = $("#s_pass").val();
		var pincode = $("#s_pincode").val();
		var cityName = $("#s_city").val();
		var dob = new Date($("#s_dob").val());		
		var dob_long = dob.getTime();

		var parEmail = $("#s_ParentEmail").val();
		var schoolName = $("#s_schoolname").val();
		var schoolType =  $("#s_schoolType option:selected").text();
		var address = $("#s_schooladdress").val();
		var standard = $("#s_standard").val();
		var medium = $("#s_medium").val();

		regUserJson.emailId = email;
		regUserJson.firstName = fname;
		regUserJson.middleName = mname;
		regUserJson.lastName = lname;
		regUserJson.phoneNumber = parseInt(contact);
		regUserJson.whatsAppNo = whatsAppNo;
		regUserJson.gender = parseInt(gen);
		regUserJson.password = pass;
		regUserJson.pincode = parseInt(pincode);
		regUserJson.cityId = parseInt(cityName);
		regUserJson.stateId = 22;
		regUserJson.dateOfBirth = dob_long;
		regUserJson.userType = "Student";
		

		studJson.parEmailId = parEmail;
		studJson.schoolName = schoolName;
		studJson.schoolType = schoolType;
		studJson.schoolAdd = address;
		studJson.standard = standard;
		studJson.mediumId = parseInt(medium);
		
		finalJson.user = regUserJson;
		finalJson.stud = studJson;
		finalJson.student = "Stud";
		
		AH.userProfileRegistration(AP.baseURL+"userProfile/api/user/create", "POST",
				finalJson);
	};
	
UP.registerContributor = function() {
	regUserJson = {};
	contribJson = {};
	finalJson = {};
	
		var email = $("#email").val();
		var fname = $("#fname").val();
		var mname = $("#mname").val();
		var lname = $("#lname").val();
		var contact = $("#contact").val();
		var whatsAppNo = $("#whatsAppNo").val();		
		var gen = $("#gender").val();
		var pass = $("#password").val();
		var pincode = $("#pincode").val();
		var cityId = $("#city").val();
		var dob = new Date($("#dob").val());		
		var dob_long = dob.getTime();
		
		var schoolName = $("#schoolname").val(); 
		var schoolAdd = $("#schooladdress").val();	
		var schoolType =  $("#schoolType option:selected").text();
		var medium = $("#medium").val();
		var yearsOfExp = $("#yearsOfExp").val();		
		var contribType = $("#contribType option:selected" ).text();
		
		regUserJson.emailId = email;
		regUserJson.firstName = fname;
		regUserJson.middleName = mname;
		regUserJson.lastName = lname;
		regUserJson.phoneNumber = contact;
		regUserJson.whatsAppNo = whatsAppNo;
		regUserJson.gender = parseInt(gen);
		regUserJson.password = pass;
		regUserJson.pincode = parseInt(pincode);
		regUserJson.cityId = parseInt(cityId);
		regUserJson.stateId = 22;
		regUserJson.dateOfBirth = dob_long;
		regUserJson.userType = "Contributor";
		
		contribJson.schoolName = schoolName;
		contribJson.schoolAdd = schoolAdd;
		contribJson.schoolType = schoolType;
		contribJson.mediumId = parseInt(medium);
		contribJson.experience = yearsOfExp;
		contribJson.canContributeInLatex = contribType;
		
		finalJson.user = regUserJson;
		finalJson.cont = contribJson;
		finalJson.contrib = "Cont";
		
		AH.userProfileRegistration(AP.baseURL+"userProfile/api/user/create", "POST",
				finalJson);
	};
	
	UP.registerTeacher = function() {
		regUserJson = {};
		teachJson = {};
		finalJson = {};
		
		var email = $("#t_email").val();
		var fname = $("#t_fname").val();
		var mname = $("#t_mname").val();
		var lname = $("#t_lname").val();
		var contact = $("#t_contact").val();
		var whatsAppNo = $("#t_whatsAppNo").val();
		var gen = $("#t_gender").val();
		var pass = $("#t_pass").val();
		var pincode = $("#t_pincode").val();
		var cityId = $("#t_city").val();
		var dob = new Date($("#t_dob").val());		
		var dob_long = dob.getTime();	
		
		var schoolName = $("#t_schoolname").val(); 
		var schoolAdd = $("#t_schooladdress").val();
		var schoolType =  $("#t_schoolType option:selected").text();// $("#schoolType").val();
		var medium = $("#t_medium").val();
		var yearsOfExp = $("#t_yearsOfExp").val();
		var t_group = $("#t_group option:selected" ).text();

		regUserJson.emailId = email;
		regUserJson.firstName = fname;
		regUserJson.middleName = mname;
		regUserJson.lastName = lname;
		regUserJson.phoneNumber = contact;
		regUserJson.whatsAppNo = whatsAppNo;
		regUserJson.gender = parseInt(gen);
		regUserJson.password = pass;
		regUserJson.pincode = parseInt(pincode);
		regUserJson.cityId = parseInt(cityId);
		regUserJson.stateId = 22;
		regUserJson.dateOfBirth = dob_long;
		regUserJson.userType = "Teacher";
		
		teachJson.schoolName = schoolName;
		teachJson.schoolAdd = schoolAdd;
		teachJson.schoolType = schoolType;
		teachJson.mediumId = parseInt(medium);
		teachJson.experience = yearsOfExp;
		teachJson.teachingGroup = t_group;
		
		finalJson.user = regUserJson;
		finalJson.teach = teachJson;
		finalJson.teacher = "teach";
		
		AH.userProfileRegistration(AP.baseURL+"userProfile/api/user/create", "POST",
				finalJson);
	};
	
	
	UP.registerParent = function() {
		regUserJson = {};
		parJson = {};
		finalJson = {};
		
		var email = $("#p_email").val();
		var fname = $("#p_fname").val();
		var mname = $("#p_mname").val();
		var lname = $("#p_lname").val();
		var contact = $("#p_contact").val();
		var whatsAppNo = $("#p_whatsAppNo").val();
		var gen = $("#p_gender").val();
		var pass = $("#p_pass").val();
		var pincode = $("#p_pincode").val();
		var cityId = $("#p_city").val();
		var dob = new Date($("#p_dob").val());		
		var dob_long = dob.getTime();
		
		var qualification = $("#p_qualif").val();
		var occupation = $("#p_occupation").val();
		var company = $("#p_company").val();
		var serviceLength = $("#p_service").val(); 
		var designation = $("#p_desig").val();
		
		regUserJson.emailId = email;
		regUserJson.firstName = fname;
		regUserJson.middleName = mname;
		regUserJson.lastName = lname;
		regUserJson.phoneNumber = contact;
		regUserJson.whatsAppNo = whatsAppNo;
		regUserJson.gender = parseInt(gen);
		regUserJson.password = pass;
		regUserJson.cityId = parseInt(cityId);
		regUserJson.stateId = 22;
		regUserJson.pincode = parseInt(pincode);
		regUserJson.dateOfBirth = dob_long;
		regUserJson.userType = "Parent";
		
		parJson.qualification = qualification;
		parJson.occupation = occupation;
		parJson.companyName = company;
		parJson.serviceLength = serviceLength;
		parJson.designation = designation;
		
		finalJson.user = regUserJson;
		finalJson.par = parJson;
		finalJson.parent = "par";

		AH.userProfileRegistration(AP.baseURL+"userProfile/api/user/create", "POST",
				finalJson);
	};
	
	UP.renderRegSuccessHtm = function(AlertMsg){
		var AlertMessModel = ''
			AlertMessModel +=  '<div class="container-fluid">'
			+ '<div class="row">'
			+ '<div  class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'
			//Alert modal start
					+ '<div class="modal" id="regsuccessMessModel">'
					+ '<div class="modal-dialog">'
					+ '<div class="modal-content">'
		             
		            	 +' <div class="modal-header" style="background:#4CAF50; color:#fff;">'
							+ '   <h4 class="modal-title">Successfull !!</h4>'
							+ '  <button type="button" class="close" data-dismiss="modal">&times;</button>'
							+'  </div>'
		            	 
		            + ' <div class="modal-body">'
			+ '<span id="successMsg">'+AlertMsg+'</span>'
			+' </div>'
			+'<div class="modal-footer">'
						+ '<button type="button" class="btn btn-success" data-dismiss="modal" id="AlertMsgdOK" onClick="RegPageSuccess()">Ok</button>&nbsp;&nbsp;'
						+ '</div>'
		
					  + '    </div>'
					  + '  </div>'
					  + '</div>'
			//alert modal end
			+ '</div>' // subject_content close
			+ '</div>'// main row close
			+ '</div>' // container close
			
			$("#registrationDiv").append(AlertMessModel);
		
		RegPageSuccess = function()
		{
			$("#CallAfterRegDiv").css("display","block");
			 $("#registrationDiv").css("display","none");
		}
	};
	
	
	
	UP.renderRegwarningHtm = function(AlertMsg){
		var AlertMessModel = ''
			AlertMessModel +=  '<div class="container-fluid">'
			+ '<div class="row">'
			+ '<div  class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'
			//Alert modal start
					+ '<div class="modal" id="regMessWarningModel">'
					+ '<div class="modal-dialog">'
					+ '<div class="modal-content">'
		             
		            	 +' <div class="modal-header" style="background:#ce6058; color:#fff;">'
							+ '   <h4 class="modal-title">Warning !!</h4>'
							+ '  <button type="button" class="close" data-dismiss="modal">&times;</button>'
							+'  </div>'
		            	 
		            + ' <div class="modal-body">'
			+ '<span id="successMsg">'+AlertMsg+'</span>'
			+' </div>'
			+'<div class="modal-footer">'
						+ '<button type="button" class="btn btn-success" data-dismiss="modal" id="AlertMsgdOK">Ok</button>&nbsp;&nbsp;'
						+ '</div>'
		
					  + '    </div>'
					  + '  </div>'
					  + '</div>'
			//alert modal end
			+ '</div>' // subject_content close
			+ '</div>'// main row close
			+ '</div>' // container close
			
			$("#registrationDiv").append(AlertMessModel);
		
		
	};
	
	
	UP.renderRegFailHtm = function(data){
			
			var reghtm = '';
			reghtm +='<h2 class="error-class hidden-msg">*'
				+data.msg
				+'</h2>'; 
			
			$(".hidden-msg").html('');
			$(".hidden-msg").html(reghtm);
			
			showToast.show(data.msg,false);
			
			
		};
	

	UP.updateUser = function() {

		var userid = $("#userid").val();
		var emailid = $("#email").val();
		var fname = $("#fname").val();
		var lname = $("#lname").val();
		var pass = $("#pass").val();
		var compass = $("#compass").val();
		var university = $("#university").val();
		var college = $("#college").val();
		var department = $("#deptName").val();
		var contact = $("#contact").val();

		var dob = $("#dob").val();
		var gen = $("#gender").val();
		var city = $("#city").val();
		var state = $("#state").val();
		var country = $("#country").val();

		// updateUserJson.userId = userid;
		updateUserJson.emailId = emailid;
		updateUserJson.firstName = fname;
		updateUserJson.lastName = lname;
		updateUserJson.password = pass;
		updateUserJson.confirmpassword = compass;
		updateUserJson.university = university;
		updateUserJson.college = college;
		updateUserJson.department = department;
		updateUserJson.mobNo = contact;

		updateUserJson.city = city;
		updateUserJson.state = state;
		updateUserJson.country = country;
		updateUserJson.dob = dob;
		updateUserJson.gender = gen;

		AH.userProfileRegistration(AP.baseURL + "userProfile", "PUT",
				updateUserJson);

	};

	UP.onMyProfileClick = function() {
		AH.getUserDetails(AP.baseURL + "userProfile", "GET");
	};

	UP.renderUserProfile = function(data) {

		var profileHtm = '';

		profileHtm += '<div class="row">'
				+ '<div class="col-sm-6 col-md-4 col-md-offset-4 col-sm-offset-3">'
				+ '<h2 class="prim-text-black-color t1-b1-padding">Edit Profile</h2>'
				+ '<div id="editProfile" class="t1-b1-padding">'
				+ '<form id="editProfileForm" action="#" name="editProfileForm" method="post">'
				+ '<div class="col-sm-12 l0-r0-padding t1-b1-padding">'
				+ '<input type="text" autofocus="" id="email" name="email" class="regis form-control" placeholder="Email ID" value="'
				+ data.EMAIL
				+ '"/>'
				+ '</div>'
				+ '<div class="col-sm-6 l0-r0-padding">'
				+ '<input type="text" autofocus=""  id="fname" name="fname" class="regis form-control" placeholder="First Name" value="'
				+ data.FN
				+ '"/>'
				+ '</div>'
				+ '<div class="col-sm-6 r0-padding">'
				+ '<input type="text" autofocus="" id="lname" name="lname" class="regis form-control" placeholder="Last Name" value="'
				+ data.LN
				+ '"/>'
				+ '</div>'
				+ ' <div class="col-sm-12 l0-r0-padding t1-b1-padding">'
				+ '<label>Gender</label><select id="gender" name="gender" title="Select Gender" class="regis form-control">'
				+ '  <option value="">Gender</option>'
				+ '   <option selected value="male">Male</option>'
				+ ' <option value="female">Female</option>'
				+ ' <option value="other">Other</option>'
				+ '</select>'
				+ ' </div>'
				+ ' <div class="col-sm-12 l0-r0-padding ">'
				+ ' <label>Date of Birth</label><input type="text" id="dob" name="dob" class="regis datepicker pointer form-control" placeholder="Select Date of Birth" />'
				+ '  </div>'
				+ '   <div class=" col-sm-12 l0-r0-padding t1-b1-padding">'
				+ ' <label>Country</label><input type="text" id="country" name="country" disabled class="regis form-control" value="India" placeholder="country" />'
				+ ' </div>'
				+ '<div class="col-sm-6 l0-r0-padding">'
				+ '<label>State</label><select id="state" name="state" class="regis form-control" placeholder="Select State">'
				+ ' <option value="">State</option>'
				+ '<option value="1">Andaman &amp; Nicobar</option>'
				+ '<option value="2">Andhra Pradesh</option>'
				+ '<option value="3">Arunachal Pradesh</option>'
				+ '<option value="4">Assam</option>'
				+ '<option value="5">Bihar</option>'
				+ '<option value="6">Chandigarh</option>'
				+ '<option value="7">Chhattisgarh</option>'
				+ ' <option value="8">Dadra &amp; Nagar Haveli</option>'
				+ '<option value="9">Daman &amp; Diu</option>'
				+ '<option value="10">Delhi</option>'
				+ '<option value="11">Goa</option>'
				+ '<option value="12">Gujarat</option>'
				+ '<option value="13">Haryana</option>'
				+ '<option value="14">Himachal Pradesh</option>'
				+ '<option value="15">Jammu &amp; Kashmir</option>'
				+ '<option value="16">Jharkhand</option>'
				+ '  <option value="17">Karnataka</option>'
				+ '<option value="18">Kerala</option>'
				+ '<option value="19">Lakshadweep</option>'
				+ ' <option value="20">Madhya Pradesh</option>'
				+ ' <option value="21">Maharashtra</option>'
				+ ' <option value="22">Manipur</option>'
				+ ' <option value="23">Meghalaya</option>'
				+ '<option value="24">Mizoram</option>'
				+ ' <option value="25">Nagaland</option>'
				+ ' <option value="26">Orissa</option>'
				+ '<option value="27">Pondicherry</option>'
				+ '<option value="28">Punjab</option>'
				+ '<option value="29">Rajasthan</option>'
				+ ' <option value="30">Sikkim</option>'
				+ ' <option value="31">Tamil Nadu</option>'
				+ ' <option value="32">Telangana</option>'
				+ ' <option value="33">Tripura</option>'
				+ ' <option value="34">Uttar Pradesh</option>'
				+ '<option value="35">Uttaranchal</option>'
				+ '<option value="36">West Bengal</option>'
				+ '</select>'
				+ '</div>'
				+ '  <div class="col-sm-6 r0-padding">'
				+ '<label>City</label><select  id="city" name="city" class="regis form-control" placeholder="Select City">'
				+ '<option value="">City</option>'
				+ ' <option value="1">Pune</option>'
				+ '<option value="2">Aurangabad</option>'
				+ '</select>'
				+ '</div>'

				+ '<div class=" col-sm-12 l0-r0-padding t1-b1-padding">'
				+ '<input type="password" id="pass" disabled name="pass" class="regis form-control" placeholder="Password" value="'
				+ data.PWD
				+ '"/>'
				+ '</div>'
				+ '<div class="col-sm-12 l0-r0-padding ">'
				+ '<input type="password" id="compass" disabled name="conpass" class="regis form-control" placeholder="Confirm Password" value="'
				+ data.CPWD
				+ '"/>'
				+ '</div>'
				+ '<div class="col-sm-12 l0-r0-padding t1-b1-padding">'
				+ ' <input type="text" autofocus="" id="university" name="university" class="regis form-control" placeholder="Tenured University" value="'
				+ data.UNI
				+ '"/>'
				+ '</div>'
				+ '<div class="col-sm-12 l0-r0-padding">'
				+ '<input type="text" autofocus="" id="college" name="college" class="regis form-control" placeholder="Tenured College" value="'
				+ data.CLG
				+ '"/>'
				+ '</div>'
				+ '<div class="col-sm-12 l0-r0-padding t1-b1-padding">'
				+ '<select id="deptName" name="dept" title="Choose Department" class="regis form-control">'
				+ ' <option value="">Select Department</option>'
		for ( var key in AP.departments) {
			if (key != "0") {
				profileHtm += "<option value= " + key + ">"
						+ AP.departments[key] + "</option>";
			}
		}
		profileHtm += ' </select>'
				+ ' </div>'
				+ '<div class="col-sm-12 l0-r0-padding">'
				+ '   <input type="text" autofocus=""  id="contact" name="contact" class="regis form-control" placeholder="Contact Number" value="'
				+ data.MOB
				+ '"/>'
				+ '</div>'

				+ ' <div class="col-md-12 t2-b2-padding" align="center">'
				+ '      <button id="editProfileBtn" type="submit" title="Save Changes" class=" btn custom-btn h4-cls">Save Changes</button>'
				+ ' </div>' + ' </div>' + '</div>';

		$("#main-div").html('');
		$("#main-div").html(profileHtm);

		$("#deptName option").filter(function() {
			// may want to// use $.trim in here return $(this).val() == data.DEPT;
		}).attr('selected', true);

		$("#gender option").filter(function() {
			// may want to// use $.trim in here return $(this).val() == data.GEN;
		}).attr('selected', true);

		$("#city option").filter(function() {
			// may want to// use $.trim in here return $(this).val() == data.CITY;
		}).attr('selected', true);

		$("#country option").filter(function() {
			// may want to// use $.trim in here return $(this).val() == data.CON;
		}).attr('selected', true);

		$("#state option").filter(function() {
			// may want to// use $.trim in here return $(this).val() == data.ST;
		}).attr('selected', true);

		var temp = data.DOB;
		$("#dob").val(temp.slice(0, 10));

		$(".datepicker").datepicker({
			closeText : "Close",
			showButtonPanel : true,
			changeMonth : true,
			changeYear : true,
			dateFormat : "yy-mm-dd",
			yearRange : "-100:+0",

			onSelect : function(e) {
				$(this).attr("autocomplete", "off");
				$(this).datepicker({}).val();

			},
			onClose : function() {
				this.focus();
			}
		}).click(function() {
			$(this).focus()
		});
	}
	
	
	
	UP.renderSelectedState = function(data) {
		
		   var state = data;
			var states = {};

			for (var i = 0; i < state.data.length; i++) {
				    
				states[state.data[i].SID] = state.data[i].SN;
				   }
				   
				   var states1 = '';
				   for ( var key in states) {
					   states1 += "<option value= " + key + ">" + states[key]
				      + "</option>";
				   }
				   $("#stateId").append(states1);  
		};
		
		
		
		UP.renderForSelectedState = function(data) {
			
			   var city = data;
				var cities = {};

				for (var i = 0; i < city.data.length; i++) {
					    
					cities[city.data[i].CIID] = city.data[i].CIN;
					   }
					   
					   var city1 = '';
					   for ( var key in cities) {
						   city1 += "<option value= " + key + ">" + cities[key]
					      + "</option>";
					   }
					   $(".city").append(city1); 				  
			};
			
			
			$("#btnIdSubmit").bind('click', function(){
				
				var file = $('#schoolId');

				var filename = $.trim(file.val());

				var formData = new FormData();
				formData.append('file', $('input[type=file]')[0].files[0]);
				
				if(filename == null || filename == ""){
					showToast.show("Please select file to upload",
							false);
				}else{
					var fileSize = ($('input[type=file]')[0].files[0].size / 1024 / 1024);
					var valid_extensions = /(\.png|\.jpeg|\.jpg)$/i;
					if (filename != null || filename != undefined || filename != "") {
						if (valid_extensions.test(filename)
							&& fileSize <= AP.maxSizeImage) {
							var mediaId = AH.schoolIdMediaFileUplaod(AP.baseURL + "media", "POST", formData);
						} else {
							showToast.show("File Size Should Not exceeds 3 MB and supported file formats - '.png' ,'.jpeg ','.jpg'",false);
						}
					}
				}
			});
			
			
		UP.showIdProofImage = function(URL){
			$("#schoolIdImg").html('<img src='+com.coep.test.addProblem.baseURL +'/media/userSchoolId/getImage?mediaID='+URL+'/>');
			$("schoolId").val();
			$("#uploadId").hide();
		}	
		
		UP.displayIdUploadDiv = function(){
			$("#uploadId").show();
			$("#schoolId").val();
		}
		
		$(".closeUpload").bind("click", function(){
			$("#schoolId").val();
			$("#uploadId").hide();
		});
			
			

})(com.coep.test.ajaxHandler, com.coep.test.addProblem,
		com.coep.test.questionBank, com.coep.test.userProfile);
