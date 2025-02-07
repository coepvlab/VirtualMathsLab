(function(AH, AP,  UP) {
   
	
	UP.fetchUserProfile = function() {
		 
		AH.getProfileDetails(AP.baseURL+"userProfile/getUser", "GET");
	}

	UP.renderUserProfileDetails = function(data){
		
		console.log(data,data.roleJson);
		roleCheck = data.roleJson;
		var profileHtm = '';
	
		stdStd = [ "NA", "1st" ,"2nd","3rd","4th","5th","6th","7th","8th","9th","10th"];
		Med = ["NA","English","Marathi","Semi English"];
//		Common Field for any Role Start
		if(data.url !=  undefined){
			ImageSrc = 'media/getProfImage?mediaID='+ data.url;
		}else{
			ImageSrc = 'resource/images/profilePicImg.jpg';
		}
		
		profileHtm = '<div class="row">'
			+'<div class="col-xl-12 col-md-12 col-sm-12" id="UserProfileDisplay" >'
			+'<div class="row">'
//			+'<div class="col-xl-4 col-md-4 col-sm-12" >'
//			+'<h1>Profile Details</h1>'
//			+ '<img src='+ImageSrc+' id="profPic" class="UserProfilePhoto">'
//			 + '<div class="form-group col-xl-12 col-md-12 col-sm-12" >'
//			    + '<label class="col-xl-12 col-md-12 col-sm-12">Upload Picture</label>'
//			    + '<div class="col-sm-12 col-md-12  col-lg-12  col-xl-12 ">'	
//			    + '<input class=" form-control" type="file" id="uploadImage" title="Upload File" >'
//				//+ '<div class="invalid-feedback">Please upload image.</div>'
//				+ '</div>'
//				+ '</div>'
//			
//			
//
//			
//			+'</div>'
			+'<div class="offset-xl-1 col-xl-10 offset-md-1 col-md-10 col-sm-12" >'
			+'<h1>Profile Details</h1>'
			+'<div class="ProfilePhotoStyle"><div class="profileDisplay"><img src='+ImageSrc+' id="profPic" class="UserProfilePhoto">'
			+'<div class="image-uploadS" >'
			+'<label for="uploadImage">'
			+'<img src="resource/images/updateProfile.png" id="updatePhoto" title="Change profile pic" />'
			+'</label>'

			+'<input  type="file" id="uploadImage" title="Upload File" >'
			+'</div>'
			+'<h2><span class="userName">'+data.fname+' '+data.mname+' '+data.lname+'</span></h2>'
			+'<h4><span class="userPJtID">Virtual Math Lab Id : '+data.userId+'</span></h4>'
			+'<center><button class="btn btn-dark" id="EditProfileClick"><i class="fa fa-pencil-square-o" aria-hidden="true"></i>&nbsp;&nbsp;Edit Profile</button></center>'
			+'</div> </div>'
			+'<br/><section class="section-preview row"><span class="btn btn-dark mand" style="">MANDATORY DETAILS</span><div class="styles col-xl-12 col-md-12 col-sm-12"><p>Email:</p>'+data.emailId+'</div>'
//			+'<div class="styles"><p>Full Name:</p>'+data.fname+' '+data.mname+' '+data.lname+'</div>'
			+'<div class="styles col-xl-4 col-md-4 col-sm-12"><p> Phone NO:</p>'+data.ph+'</div>'
			+'<div class="styles col-xl-4 col-md-4 col-sm-12"><p> WhatsApp No :</p>'+data.whatsApp+'</div>'
			if(data.gender == 1){
				profileHtm +='<div class="styles col-xl-4 col-md-4 col-sm-12"><p> Gander:</p>Male</div>'
			}else if(data.gender == 2){
				profileHtm +='<div class="styles col-xl-4 col-md-4 col-sm-12"><p> Gander:</p>FeMale</div>'
			}
		
		profileHtm +='<div class="styles col-xl-4 col-md-4 col-sm-12"><p> DOB:</p>'+data.dob.split(" ",1)+'</div>'
			//city
		    +'<div class="styles col-xl-4 col-md-4 col-sm-12"><p>City:</p><span id="CtyAllUser"></span></div>'
			+'<div class="styles col-xl-4 col-md-4 col-sm-12"><p>PinCode:</p>'+data.pincode+'</div></section>'
			
			
	
				
			if("STUDENT" in roleCheck){		
				profileHtm +='<section class="section-preview row"><span class="btn btn-dark" style="margin: -30px 0 11px 0;font-weight: bold;">STUDENT DETAILS</span><div class="styles col-xl-12 col-md-12 col-sm-12"><p>School Name:</p>'+data.student.schoolName+'</div>'
			//	standard
				+'<div class="styles col-xl-12 col-md-12 col-sm-12"><p>School Address:</p>'+data.student.schoolAdd+'</div>'
				for(i=0; i < stdStd.length; i++)
					{console.log(stdStd[i]);
				if(data.student.standard == i)
					{
					console.log(stdStd.length);
					profileHtm +='<div class="styles col-xl-4 col-md-4 col-sm-12"><p>Standard:</p>'+stdStd[i]+'</div>'
					}
					}
				profileHtm +='<div class="styles col-xl-8 col-md-8 col-sm-12"><p>Parent EmailId:</p>'+data.student.parEmailId+'</div>'
				for(i=0; i < Med.length; i++)
				{
						if(data.student.medium == i)
				{
				
							profileHtm +='<div class="styles col-xl-4 col-md-4 col-sm-12"><p>Medium:</p>'+Med[i]+'</div>'
				}
				}
				profileHtm +='<div class="styles col-xl-4 col-md-4 col-sm-12"><p>School Type:</p>'+data.student.schoolType+'</div>'
			//Medium
		//	S_schType
				+ '<br/><div class="col-xl-12 col-md-12 col-sm-12"><span class="btn btn-dark" style="margin: 11px 0;font-weight: bold;">Father Details</span></div>'
				+'<div class="styles col-xl-8 col-md-8 col-sm-12"><p>student Father Name:</p>'+data.student.faname+'</div>'
				+'<div class="styles col-xl-4 col-md-4 col-sm-12"><p>Age:</p>'+data.student.fage+'</div>'
				+'<div class="styles col-xl-4 col-md-4 col-sm-12"><p>Qualification:</p>'+data.student.fqualif+'</div>'
				+'<div class="styles col-xl-4 col-md-4 col-sm-12"><p>Occupation:</p>'+data.student.foccup+'</div>'
				+'<div class="styles col-xl-4 col-md-4 col-sm-12"><p>Working School/Company:</p>'+data.student.fcompany+'</div>'
				+'<div class="styles col-xl-4 col-md-4 col-sm-12"><p>Designation:</p>'+data.student.fdesig+'</div>'
				+'<div class="styles col-xl-4 col-md-4 col-sm-12"><p>Service Length:</p>'+data.student.fservLeng+'</div>'
				+'<div class="styles col-xl-4 col-md-4 col-sm-12"><p>City:</p><span id="CtyFather"></span></div>'
		//	city
				+ '<br/><div class="col-xl-12 col-md-12 col-sm-12"><span class="btn btn-dark" style="margin:11px 0;font-weight: bold;">Mother Details</span></div>'
				+'<div class="styles col-xl-8 col-md-8 col-sm-12"><p>Mother Name:</p>'+data.student.maname+'</div>'
				+'<div class="styles col-xl-4 col-md-4 col-sm-12"><p>Age:</p>'+data.student.mage+'</div>'
				+'<div class="styles col-xl-4 col-md-4 col-sm-12"><p>Qualification:</p>'+data.student.mqualif+'</div>'
				+'<div class="styles col-xl-4 col-md-4 col-sm-12"><p>Occupation:</p>'+data.student.moccup+'</div>'
				+'<div class="styles col-xl-4 col-md-4 col-sm-12"><p>Working School/Company:</p>'+data.student.mcompany+'</div>'
				+'<div class="styles col-xl-4 col-md-4 col-sm-12"><p>Designation:</p>'+data.student.mdesig+'</div>'
				+'<div class="styles col-xl-4 col-md-4 col-sm-12"><p>Service Length:</p>'+data.student.mservLeng+'</div>'
				+'<div class="styles col-xl-4 col-md-4 col-sm-12"><p>City:</p><span id="CtyMother"></span></div></section>'
		//	city
			}
		if("CONTRIBUTOR" in roleCheck){
			profileHtm +='<section class="section-preview row"><span class="btn btn-dark" style="margin: -30px 0 11px 0;font-weight: bold;">CONTRIBUTOR DETAILS</span><div class="styles col-xl-12 col-md-12 col-sm-12"><p>Contributor School name:</p>'+data.contributor.schoolName+'</div>'
			+'<div class="styles col-xl-12 col-md-21 col-sm-12"><p>School address:</p>'+data.contributor.schoolAdd+'</div>'
	//	C_schType
	//	C_medium
			+'<div class="styles col-xl-4 col-md-4 col-sm-12"><p>School Type:</p>'+data.contributor.schoolType+'</div>'
			
			//+'<div class="styles col-xl-4 col-md-4 col-sm-12"><p>Medium:</p>'+data.contributor.medium+'</div>'
			for(i=0; i < Med.length; i++)
			{
					if(data.contributor.medium == i)
			{
			
						profileHtm +='<div class="styles col-xl-4 col-md-4 col-sm-12"><p>Medium:</p>'+Med[i]+'</div>'
			}
			}
			profileHtm +='<div class="styles col-xl-4 col-md-4 col-sm-12"><p> Years of experience:</p>'+data.contributor.exp+'</div>'
			+'<div class="styles col-xl-12 col-md-12 col-sm-12"><p> Can contribute in Latex Format:</p>'+data.contributor.canContribInLatex+'</div></section>'
	//	C_canContrib
		}
		if("TEACHER" in roleCheck){
			profileHtm +='<section class="section-preview row"><span class="btn btn-dark" style="margin: -30px 0 11px 0;font-weight: bold;">TEACHER DETAILS</span><div class="styles col-xl-12 col-md-12 col-sm-12"><p>School Name:</p>'+data.teacher.schoolName+'</div>'
			+'<div class="styles col-xl-4 col-md-4 col-sm-12"><p>School Address:</p>'+data.teacher.schoolAdd+'</div>'
	//	T_schType
	//	T_medium
			+'<div class="styles col-xl-4 col-md-4 col-sm-12"><p>School Type:</p>'+data.teacher.schoolType+'</div>'
			+'<div class="styles col-xl-4 col-md-4 col-sm-12"><p>Medium:</p>'+data.teacher.medium+'</div>'
			for(i=0; i < Med.length; i++)
			{
					if(data.teacher.medium == i)
			{
			
						profileHtm +='<div class="styles col-xl-4 col-md-4 col-sm-12"><p>Medium:</p>'+Med[i]+'</div>'
			}
			}
			profileHtm +='<div class="styles col-xl-4 col-md-4 col-sm-12"><p>Experience:</p>'+data.teacher.exp+'</div>'
			+'<div class="styles col-xl-4 col-md-4 col-sm-12"><p>Teaching Group:</p>'+data.teacher.teachGroup+'</div></section>'
	//	T_teachGroup
		}
		
		if("PARENT" in roleCheck){
			profileHtm +='<section class="section-preview row"><span class="btn btn-dark" style="margin: -30px 0 11px 0;font-weight: bold;">PARENT DETAILS</span><div class="styles col-xl-12 col-md-12 col-sm-12"><p>Company:</p>'+data.parent.company+'</div>'
			+'<div class="styles col-xl-4 col-md-4 col-sm-12"><p>Occupation:</p>'+data.parent.occup+'</div>'
			+'<div class="styles col-xl-4 col-md-4 col-sm-12"><p>Designation:</p>'+data.parent.desig+'</div>'
			+'<div class="styles col-xl-4 col-md-4 col-sm-12"><p>Qualification :</p>'+data.parent.qualif+'</div>'
			+'<div class="styles col-xl-4 col-md-4 col-sm-12"><p>Service Length:</p>'+data.parent.servLeng+'</div></section>'
		}
		
		profileHtm +='</div></div></div>'
			+'<div class="offset-xl-1 col-xl-10 offset-md-1 col-md-10 col-sm-12" id="UserProfileEdit">'
			+'<h1>Update Profile Details</h1>'
			
			
			+ '<form id="s_UpdateForm" novalidate>'
			+'<br/><section class="section-preview "><span class="btn btn-dark" style="margin: -30px 0 11px 0;font-weight: bold;">MANDATORY DETAILS</span>'
			+'<div class="row">'
			   
			
				+ '<div class="form-group col-xl-6 col-md-6 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">Email Id</label>'
				+ '<input class=" form-control" type="text"  id="s_email"  value="'+data.emailId+'"  required disabled/>'
				+ '<div class="invalid-feedback">Please enter Email.</div>'
				+ '</div>'
			+ '</div>'
			
			+'<div class="row">'	
				+ '<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">First Name</label>'
				+ '<input class=" form-control" type="text"  id="s_fname" value="'+data.fname+'" required />'
				+ '<div class="invalid-feedback">Please enter first Name.</div>'
				+ '</div>'
				
				+ '<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">Middle Name</label>'
				+ '<input class=" form-control" type="text"  id="s_mname" value="'+data.mname+'" required />'
				+ '<div class="invalid-feedback">Please enter middle Name.</div>'
				+ '</div>'
				
				+ '<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">Last Name</label>'
				+ '<input class=" form-control" type="text"  id="s_lname" value="'+data.lname+'" required />'
				+ '<div class="invalid-feedback">Please enter last Name.</div>'
				+ '</div>'
			+ '</div>'	
				
			+'<div class="row">'	
				+ '<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">Contact No</label>'
				+ '<input class=" form-control" type="text"  id="s_ph"  value="'+data.ph+'" required disabled/>'
				+ '<div class="invalid-feedback">Please enter contact no.</div>'
				+ '</div>'
				
				+ '<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">WhatsApp No</label>'
				+ '<input class=" form-control" type="text"  id="s_whtap" value="'+data.whatsApp+'" required />'
				+ '<div class="invalid-feedback">Please enter Whats app no.</div>'
				+ '</div>'
				
				+ '<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">Gender</label>'
				+ '<select class=" form-control" class=" form-control" class=" form-control" class=" form-control" id="s_gender"  title="Select Gender" required>'
				+ '<option value="">Gender</option>'
				if(data.gender == 1){
					profileHtm += '<option value="1" selected>Male</option>'
						+ '<option value="2">Female</option>'
				}else if(data.gender == 2){
					profileHtm += '<option value="1" >Male</option>'
						+ '<option value="2" selected>Female</option>'
				}
			    profileHtm += '</select>'
				+ '<div class="invalid-feedback">Please select gender.</div>'
				+ '</div>'
			
			
//			+'<div class="row">'	
				+ '<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">Date of Birth</label>'
				+ '<input class=" form-control" type="text" class="datepicker" id="s_dob"  value="'+data.dob.split(" ",1)+'" required />'
				+ '<div class="invalid-feedback">Please enter DOB.</div>'
				+ '</div>'
			
				+ '<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">City</label>'
				+ '<select class=" form-control" class=" form-control" class=" form-control" class=" form-control"  id="s_city" class="city" autocomplete="off" required>'
				+ '<option value="">Select City</option>'
				+ '</select>'
				+ '<div class="invalid-feedback">Please select city.</div>'
				+ '</div>'
				
				+ '<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">Pincode</label>'
				+ '<input class=" form-control" type="text"  id="s_pincode" value="'+data.pincode+'" required />'
				+ '<div class="invalid-feedback">Please enter pincode.</div>'
				+ '</div>'
			+ '</section>'	
				
//			Common field for any role end
			
//			role based field start for student account
		if("STUDENT" in roleCheck){		
				
			profileHtm += '<section class="section-preview "><span class="btn btn-dark" style="margin: -30px 0 11px 0;font-weight: bold;">STUDENTS DETAILS</span>'
				+ '<div class="row"><div class="form-group col-xl-12 col-md-12 col-sm-12" ><label class="col-xl-12 col-md-12 col-sm-12">School Name</label>'
				+ '<input class=" form-control" type="text"  id="s_schoolName"  value="'+data.student.schoolName+'" required/>'
				+ '<div class="invalid-feedback">Please enter School name.</div>'
				+ '</div>'
				
				+ '<div class="form-group col-xl-12 col-md-12 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">School Address</label>'
				+ '<input class=" form-control" type="text"  id="s_SchAddress"  value="'+data.student.schoolAdd+'" required/>'
				+ '<div class="invalid-feedback">Please enter School address.</div>'
				+ '</div>'
				
				+ '<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">Standard</label>'
				+ ' <select class="form-control" id="s_standard" required>'
				+ ' <option selected="selected">-----Select-----</option>'
				+ '</select>'
				+ '<div class="invalid-feedback">Please enter Standard.</div>'
				+ '</div>'
				
				+ '<div class="form-group col-xl-8 col-md-8 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">Parent email Id</label>'
				+ '<input class=" form-control" type="text"  id="s_pemail"  value="'+data.student.parEmailId+'" required/>'
				+ '<div class="invalid-feedback">Please enter Email.</div>'
				+ '</div>'
				
				+ '<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">Medium</label>'
				+ ' <select class="form-control" id="s_medium" required>'
				+ '<option selected="selected" value="">-----Select-----</option>'
				+ '<option value="1">English</option>'
				+ '<option value="2">Marathi</option>'
				+ '<option value="3">Semi English</option>'
				+ '</select>'
				+ '<div class="invalid-feedback">Please enter medium</div>'
				+ '</div>'
				
				+ '<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">School Type</label>'
				+ ' <select class="form-control" class="schType" id="S_schType" required>'
				+ ' <option selected="selected">-----Select-----</option>'
				+ '</select>'
				+ '<div class="invalid-feedback">Please enter school type.</div>'
				+ '</div>'
				
				+ '<br/><div class="col-xl-12 col-md-12 col-sm-12"><span class="btn btn-dark" style="margin: 0px 0 11px 0;font-weight: bold;">Father Details</span></div>'
				+'<div class="row">'
				+ '<div class="form-group col-xl-8 col-md-8 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">Father\'s Name</label>'
				+ '<input class=" form-control" type="text" id="s_faname"  value="'+data.student.faname+'" required/>'
				+ '<div class="invalid-feedback">Please enter Name.</div>'
				+ '</div>'
				
				+ '<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">Age</label>'
				+ '<input class=" form-control" type="number" id="s_fage" value="'+data.student.fage+'" required/>'
				+ '<div class="invalid-feedback">Please enter age.</div>'
				+ '</div>'
				
				+ '<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">Qualification</label>'
				+ '<input class=" form-control" type="text" id="s_fqualif"  value="'+data.student.fqualif+'" />'
				+ '<div class="invalid-feedback">Please enter Qualification.</div>'
				+ '</div>'
				
				+ '<div class="form-group col-xl-3 col-md-3 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">Occupation</label>'
				+ '<input class=" form-control" type="text" id="s_foccup"  value="'+data.student.foccup+'" />'
				+ '<div class="invalid-feedback">Please enter Occupation.</div>'
				+ '</div>'
				
				+ '<div class="form-group col-xl-5 col-md-5 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">Name of the School/company</label>'
				+ '<input class=" form-control" type="text" id="s_fcompany"  value="'+data.student.fcompany+'" />'
				+ '</div>'
				
				+ '<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">Designation</label>'
				+ '<input class=" form-control" type="text" id="s_fdesig" value="'+data.student.fdesig+'" />'
				+ '</div>'
				
				+ '<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">length of service</label>'
				+ '<input class=" form-control" type="number" id="s_fserviceLength"  value="'+data.student.fservLeng+'" />'
				+ '</div>'
				
				+ '<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">Location</label>'
				+ '<select class=" form-control" class=" form-control" class=" form-control"   id="fcity" class="city" autocomplete="off" required>'
				+ '<option value="">Select City</option>'
				+ '</select>'
				+ '<div class="invalid-feedback">Please select city.</div>'
				+ '</div>'
				+ '</div>'
				
				+ '<br/><div class="col-xl-12 col-md-12 col-sm-12"><span class="btn btn-dark" style="margin: 0px 0 11px 0;font-weight: bold;">Mother Details</span></div>'
				+'<div class="row">'
				+ '<div class="form-group col-xl-8 col-md-8 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">Mother\'s Name</label>'
				+ '<input class=" form-control" type="text" id="s_moname"  value="'+data.student.maname+'" required/>'
				+ '<div class="invalid-feedback">Please enter name.</div>'
				+ '</div>'
				
				+ '<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">Age</label>'
				+ '<input class=" form-control" type="number" id="s_mage"  value="'+data.student.mage+'" required/>'
				+ '<div class="invalid-feedback">Please enter age.</div>'
				+ '</div>'
				
				+ '<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">Qualification</label>'
				+ '<input class=" form-control" type="text" id="s_mqualif"  value="'+data.student.mqualif+'" />'
				+ '</div>'
				
				+ '<div class="form-group col-xl-3 col-md-3 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">Occupation</label>'
				+ '<input class=" form-control" type="text" id="s_moccup"  value="'+data.student.moccup+'" />'
				+ '</div>'
				
				+ '<div class="form-group col-xl-5 col-md-5 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">Name of the School/company</label>'
				+ '<input class=" form-control" type="text" id="s_mcompany" value="'+data.student.mcompany+'" />'
				+ '</div>'
				
				+ '<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">Designation</label>'
				+ '<input class=" form-control" type="text" id="s_mdesig" value="'+data.student.mdesig+'" />'
				+ '</div>'
				
				+ '<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">length of service</label>'
				+ '<input class=" form-control" type="number" id="s_mserviceLength"  value="'+data.student.mservLeng+'" />'
				+ '</div>'
				
				+ '<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">Location</label>'
				+ '<select class=" form-control" class=" form-control" class=" form-control"   id="mcity" class="city" autocomplete="off" required>'
				+ '<option value="">Select City</option>'
				+ '</select>'
				+ '<div class="invalid-feedback">Please select city.</div>'
				+ '</div>'
				+'</div></div></section>'
		}

//			role based field start for contributor account
		if("CONTRIBUTOR" in roleCheck){
			
			profileHtm += '<section class="section-preview "><span class="btn btn-dark" style="margin: -30px 0 11px 0;font-weight: bold;">CONTRIBUTOR DETAILS</span>'
				+'<div class="row"><div class="form-group col-xl-4 col-md-4 col-sm-12" ><label class="col-xl-12 col-md-12 col-sm-12">School Name</label>'
				+ '<input class=" form-control" type="text"  id="C_schoolName"  value="'+data.contributor.schoolName+'" required/>'
				+ '<div class="invalid-feedback">Please enter school name.</div>'
				+ '</div>'
				
				+ '<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">School Address</label>'
				+ '<input class=" form-control" type="text"  id="C_SchAddress"  value="'+data.contributor.schoolAdd+'" required />'
				+ '<div class="invalid-feedback">Please enter school address.</div>'
				+ '</div>'
				
				+ '<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">School Type</label>'
				+ ' <select class="form-control" class="schType" id="C_schType" required>'
				+ ' <option selected="selected" value="0">-----Select-----</option>'
				+ '</select>'
				+ '<div class="invalid-feedback">Please enter school type.</div>'
				+ '</div>'
				
				+ '<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">Medium</label>'
				+ ' <select class="form-control" id="C_medium" required>'
				+ ' <option selected="selected" value="">-----Select-----</option>'
				+ '<option value="1">English</option>'
				+ '<option value="2">Marathi</option>'
				+ '<option value="3">Semi English</option>'
				+ '</select>'
				+ '<div class="invalid-feedback">Please enter medium</div>'
				+ '</div>'
				
				+ '<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">Years of experience</label>'
				+ '<input class=" form-control" type="number"  id="C_exp"  value="'+data.contributor.exp+'" required/>'
				+ '<div class="invalid-feedback">Please enter experience(years).</div>'
				+ '</div>'
				
				+ '<div class="form-group col-xl-12 col-md-12 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">Can contribute in Latex Format</label>'
				+ ' <select class="form-control" class="schType" id="C_canContrib" required>'
				+ ' <option selected="selected" value="0">-----Select-----</option>'
				+ '</select>'
				+ '<div class="invalid-feedback">Please enter school address.</div>'
				+ '</div></div></section>'
		}
			
//			role based field start for teacher account
		if("TEACHER" in roleCheck){
			
				profileHtm += '<section class="section-preview "><span class="btn btn-dark" style="margin: -30px 0 11px 0;font-weight: bold;">TEACHER DETAILS</span>'
					+'<div class="row"><div class="form-group col-xl-4 col-md-4 col-sm-12" ><label class="col-xl-12 col-md-12 col-sm-12">School Name</label>'
					+ '<input class=" form-control" type="text"  id="T_schoolName"  value="'+data.teacher.schoolName+'" required/>'
					+ '<div class="invalid-feedback">Please enter school name.</div>'
					+ '</div>'
					
					+ '<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
					+ '<label class="col-xl-12 col-md-12 col-sm-12">School Address</label>'
					+ '<input class=" form-control" type="text"  id="T_SchAddress"  value="'+data.teacher.schoolAdd+'" required />'
					+ '<div class="invalid-feedback">Please enter school address.</div>'
					+ '</div>'
					
					+ '<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
					+ '<label class="col-xl-12 col-md-12 col-sm-12">School Type</label>'
					+ ' <select class="form-control" class="schType" id="T_schType" required>'
					+ ' <option selected="selected" value="0">-----Select-----</option>'
					+ '</select>'
					+ '<div class="invalid-feedback">Please enter school type.</div>'
					+ '</div>'
					
					+ '<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
					+ '<label class="col-xl-12 col-md-12 col-sm-12">Medium</label>'
					+ ' <select class="form-control" id="T_medium" required>'
					+ ' <option selected="selected" value="">-----Select-----</option>'
					+ '<option value="1">English</option>'
					+ '<option value="2">Marathi</option>'
					+ '<option value="3">Semi English</option>'
					+ '</select>'
					+ '<div class="invalid-feedback">Please enter medium</div>'
					+ '</div>'
					
					+ '<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
					+ '<label class="col-xl-12 col-md-12 col-sm-12">Years of experience</label>'
					+ '<input class=" form-control" type="number"  id="T_exp"  value="'+data.teacher.exp+'" required/>'
					+ '<div class="invalid-feedback">Please enter experience(years).</div>'
					+ '</div>'
				+'<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">Teaching Group</label>'
				+ ' <select class="form-control" id="T_teachGroup" required>'
				+ ' <option selected="selected" value="0">-----Select-----</option>'
				+ '</select>'
				+ '<div class="invalid-feedback">Please enter teaching group.</div>'
				+ '</div></div></section>'
		}
//			role based field start for parent account
		if("PARENT" in roleCheck){
			
			profileHtm +=  '<section class="section-preview "><span class="btn btn-dark" style="margin: -30px 0 11px 0;font-weight: bold;">PARENT DETAILS</span>'
				+'<div class="row"><div class="form-group col-xl-4 col-md-4 col-sm-12" ><label class="col-xl-12 col-md-12 col-sm-12">Qualification</label>'
				+ '<input class=" form-control" type="text"  id="P_qualif"  value="'+data.parent.qualif+'" required/>'
				+ '<div class="invalid-feedback">Please enter Qualification.</div>'
				+ '</div>'
				
				+ '<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">Occupation</label>'
				+ '<input class=" form-control" type="text"  id="P_occup"  value="'+data.parent.occup+'" required/>'
				+ '<div class="invalid-feedback">Please enter Occupation.</div>'
				+ '</div>'
				
				+ '<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">Designation</label>'
				+ '<input class=" form-control" type="text"  id="P_desig" value="'+data.parent.desig+'" required/>'
				+ '<div class="invalid-feedback">Please enter Designation.</div>'
				+ '</div>'
				
				+ '<div class="form-group col-xl-8 col-md-8 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">Name of the School/company</label>'
				+ '<input class=" form-control" type="text"  id="P_company"  value="'+data.parent.company+'" required/>'
				+ '<div class="invalid-feedback">Please enter School/company name .</div>'
				+ '</div>'
				
				+ '<div class="form-group col-xl-4 col-md-4 col-sm-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12">Length of service</label>'
				+ '<input class=" form-control" type="number"  id="P_exp"  value="'+data.parent.servLeng+'" required/>'
				+ '<div class="invalid-feedback">Please enter service length .</div>'
				+ '</div></div></section>'
			
		}
				
			profileHtm += '<div class=" col-xl-12 col-md-12 col-sm-12"><button id="updateProfileBtn" type="submit" title="Update" class="btn btn-dark float-right">Update</button></div>'
				+'</form>'
				+'</div>'
				+ '</div>'
				
		$("#main-div").html('');
		$("#main-div").html(profileHtm);
		
		var city = data.cities.data;
		var cities = {};

		for (var i = 0; i < city.length; i++) {
			    
			cities[city[i].CIID] = city[i].CIN;
		}
		
//		city render for all user
		   var city1 = '';
		   for ( var key in cities) {
			   if(data.city == key){
				   
				   $("#CtyAllUser").append(cities[key]); 
				   city1 += "<option selected value= " + key + ">" + cities[key] + "</option>";
				  
			   }else{
				   city1 += "<option value= " + key + ">" + cities[key] + "</option>";
			   }
		   }
		   $("#s_city").append(city1); 
		  
		
		if("teacher" in data){
			teachingGrpArr = ["I-V", "VI-VII", "VIII- X"];
			teachingGrpHtm = '';
			teachingGrpHtm = '<option value="0">-----Select-----</option>'
			for(a = 0; a < teachingGrpArr.length; a++){
				teachingGrpHtm += '<option>'+teachingGrpArr[a]+'</option>'
			}
			$("#T_teachGroup").html(teachingGrpHtm);
			$("#T_teachGroup").val(data.teacher.teachGroup).change();
		}
		
		if("contributor" in data){
		canContribArr = ["I am Interested to learn Latex code and provide question in latex coded form","I will provide question without latex coded form"];
		canContribHtm = '';
		canContribHtm = '<option value="0">-----Select-----</option>'
			for(a = 0; a < canContribArr.length; a++){
				canContribHtm += '<option>'+canContribArr[a]+'</option>'
			}
		
		$("#C_canContrib").html(canContribHtm);
		$("#C_canContrib").val(data.contributor.canContribInLatex).change();
		}
		
			schoolTypeArr = ["Private School", "Public School", "Government School", "ZP School", "Corporation School"];
			schoolTypeHtm = '';
			schoolTypeHtm = '<option value="0">-----Select-----</option>'
			for(a = 0; a < schoolTypeArr.length; a++){
				schoolTypeHtm += '<option>'+schoolTypeArr[a]+'</option>'
			}
			
			if("teacher" in data){
				$("#T_schType").html(schoolTypeHtm);
				$("#T_schType").val(data.teacher.schoolType).change();
				$("#T_medium").val(data.teacher.medium).change();
				
			}
			if("contributor" in data){
				$("#C_schType").html(schoolTypeHtm);
				$("#C_schType").val(data.contributor.schoolType).change();
				$("#C_medium").val(data.contributor.medium).change();
				
			}
			if("student" in data){
				$("#S_schType").html(schoolTypeHtm);
				$("#S_schType").val(data.student.schoolType).change();
				$("#s_medium").val(data.student.medium).change();
			}
		
			
		if("student" in data){	
//			city render for father information student account
			var city2 = '';
			for (var key in cities) {
				 if(data.student.fcity == key){
						$("#CtyFather").append(cities[key]); 
						
				 }
			
				city2 += "<option value= " + key + ">" + cities[key] + "</option>";
			}
			  
//			city render for mother information student account
			var city3 = '';
			for (var key in cities) {
				if(data.student.mcity == key){
					$("#CtyMother").append(cities[key]); 
					
			 }
			
				city3 += "<option value= " + key + ">" + cities[key] + "</option>";
			}
			
			standardArr = ["1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th", "10th"];
			
			standardHtm = '';
			standardHtm = '<option value="0">-----Select-----</option>'
			for(b = 0; b < standardArr.length; b++){
					standardHtm += '<option  value="'+(b+1)+'">'+standardArr[b]+'</option>'
			}
			$("#s_standard").html(standardHtm);
			$("#s_standard").val(data.student.standard).change();
			$("#fcity").append(city2); 
			
			$("#mcity").append(city3); 
			$("#fcity").val(data.student.fcity).change();
			$("#mcity").val(data.student.mcity).change();
		}
		
//		Prifle picture upload starts
		$('#uploadImage').change(function() {
					(document.getElementById("uploadImage").files.length > 0) ? isFileLinked = 1 : isFileLinked = 0;
					checkLength(this, 'filetype');
		});
		
		$('#EditProfileClick').click(function() {
			 $('#UserProfileDisplay').hide();
			$('#UserProfileEdit').show();
		});
		
	//	var mediaId = "";
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
					$(".UserProfilePhoto").attr("src", "media/getProfImage?mediaID=" + data.URL);
					$("#profPic").attr("src", "media/getProfImage?mediaID=" + data.URL);
					
				}
				
			});

			return mediaId;
		}
//		Prifle picture upload end
		
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
	      })
	      $("#UserProfile").ready(function() {
//			alert("student");
			var form = document.getElementById('s_UpdateForm');
//			for(var i=0; i<= form.length; i++)
//       	 {console.log(form.elements[i].value);}
			

		});
//	      Update profile information for all acoount button start
	      $("#updateProfileBtn").on("click", function(e){
//	    	  console.log("alert")
	    	 
         var form = document.getElementById('s_UpdateForm');
			form.addEventListener('submit', function(e) {
				if (form.checkValidity() === false) {
					e.preventDefault();
					e.stopPropagation();
				}
				form.classList.add('was-validated');
				e.preventDefault();
			}, false);

			if (form.checkValidity() == true) {
			 
	    	  userProfileJson = {};
	    	
	    	  var dob = new Date($("#s_dob").val()).getTime();		
	    	  
//	    	  Common fields start
	    	  userProfileJson.s_email = data.emailId;
			  userProfileJson.s_fname = $("#s_fname").val();
			  userProfileJson.s_mname = $("#s_mname").val();
			  userProfileJson.s_lname = $("#s_lname").val();
			  userProfileJson.s_whtap = $("#s_whtap").val();
			  userProfileJson.s_gender = $("#s_gender").val();
			  userProfileJson.s_pincode = $("#s_pincode").val();
			  userProfileJson.s_city = $("#s_city").val();
			  userProfileJson.s_dob = dob;
//			  Common fields end
	    	  
//			  student role fields
	    	  if("STUDENT" in roleCheck){
	    		  studentJson = {};
		    		studentJson.s_pemail = $("#s_pemail").val();
		    		studentJson.s_schoolName = $("#s_schoolName").val();
		    		studentJson.s_SchAddress = $("#s_SchAddress").val();
		    		studentJson.s_schType = $("#S_schType").val();
		    		studentJson.s_standard = $("#s_standard").val();
	    		  	studentJson.s_medium = parseInt($("#s_medium").val());
		    		studentJson.s_faname = $("#s_faname").val();
		    		studentJson.s_fage = $("#s_fage").val();
		    		studentJson.s_fqualif = $("#s_fqualif").val();
		    		studentJson.s_foccup = $("#s_foccup").val();
		    		studentJson.s_fcompany = $("#s_fcompany").val();
		    		studentJson.s_fdesig = $("#s_fdesig").val();
		    		studentJson.s_fserviceLength = $("#s_fserviceLength").val();
		    		studentJson.fcity = $("#fcity").val();
		    		studentJson.s_moname = $("#s_moname").val();
		    		studentJson.s_mage = $("#s_mage").val();
		    		studentJson.s_mqualif = $("#s_mqualif").val();
		    		studentJson.s_moccup = $("#s_moccup").val();
		    		studentJson.s_mcompany = $("#s_mcompany").val();
		    		studentJson.s_mdesig = $("#s_mdesig").val();
		    		studentJson.s_mserviceLength = $("#s_mserviceLength").val();
		    		studentJson.mcity = $("#mcity").val();
		    		userProfileJson.student = studentJson;
	    	  }
	    	  
//	    	  teacher role fields
	    	  if("TEACHER" in roleCheck){
	    		  teacherJson = {};
	    		  teacherJson.T_teachGroup = $("#T_teachGroup").val();
	    		  teacherJson.T_schoolName = $("#T_schoolName").val();
	    		  teacherJson.T_SchAddress = $("#T_SchAddress").val();
	    		  teacherJson.T_schType = $("#T_schType").val();
	    		  teacherJson.T_medium = parseInt($("#T_medium").val());
	    		  teacherJson.T_exp =	$("#T_exp").val();
	    		  userProfileJson.teacher = teacherJson;
	    	  }
	    	  
//	    	  contributor role fields
	    	  if("CONTRIBUTOR" in roleCheck){
	    		  contributorJson = {};
	    		  contributorJson.C_schoolName = $("#C_schoolName").val();
	    		  contributorJson.C_SchAddress = $("#C_SchAddress").val();
	    		  contributorJson.C_schType = $("#C_schType").val();
	    		  contributorJson.C_medium = parseInt($("#C_medium").val());
	    		  contributorJson.C_exp =	$("#C_exp").val();
	    		  contributorJson.C_canContrib = $("#C_canContrib option:selected").text();
	    		  userProfileJson.contributor = contributorJson;
	    	  }
	    	  
//	    	  parent role fields
	    	  if("PARENT" in roleCheck){
	    			parentJson = {};
	    			parentJson.P_qualif = $("#P_qualif").val();
	    			parentJson.P_occup = $("#P_occup").val();
	    			parentJson.P_desig = $("#P_desig").val();
	    			parentJson.P_company = $("#P_company").val();
	    			parentJson.P_exp = parseInt($("#P_exp").val());
	    			userProfileJson.parent = parentJson;
	    	  }
	    	  console.log(userProfileJson);
	    	  AH.updateUserProfileData(AP.baseURL+"userProfile/updateUser", "POST", userProfileJson);
				e.preventDefault();
			}
	      })
	      $('#UserProfileEdit').hide();
	}
	
	UP.manageRoleOfUsers = function(){
		AH.getAllUsersExceptStudent(AP.baseURL+"userProfile/fetchUsersExceptStudent", "GET");
	}
	
	UP.renderUsers = function(data){
		
		renderUserListForRoleApprovalHtm = '';
		
		renderUserListForRoleApprovalHtm = '<div class="row">'
			+'<div class="col-xl-12 col-md-12 col-sm-12">'
			+'<h1>Manage Role</h1>'
						//+ '<div id="showRoles"></div>'
						+ '<div id="userDetails" class="table-responsive"><table id="userDetailsTable" class="table table-striped table-bordered" style="width:100%"><thead><tr><th>Name</th><th>Email ID</th><th>Action</th></tr></thead>'
						+ ' <tbody>'
					for(i=0; i < data.userDetails.length; i++){
						renderUserListForRoleApprovalHtm+= '<tr><td>'
							+data.userDetails[i].name
							+'</td><td>'
							+data.userDetails[i].emailId
							+'</td><td>'
							+'<button onClick="getApproveRole('+i+','+data.userDetails[i].userId+')" type="submit" title="Approve Role" class="btn btn-dark btnsyle">Approve Role</button>'
							+'</td></tr>'
					}
		renderUserListForRoleApprovalHtm+= ' </tbody>'
			renderUserListForRoleApprovalHtm+= '</table></div>'
							+ '</div>'
							+ '</div>'
							//user approve role modal start
							+ '<div class="modal" id="approveRole">'
							+ '<div class="modal-dialog">'
							+ '<div class="modal-content">'

								+ ' <div class="modal-header">'
								+ '   <h4 class="modal-title">Approve Role</h4>'
								+ '  <button type="button" class="close" data-dismiss="modal">&times;</button>'
								+ '  </div>'

								+ '   <div id="showRoles" class="modal-body">'
								
							    + ' </div>'
						
								+ '    <div class="modal-footer">'
								//+ '<button type="button" class="btn btn-primary" data-dismiss="modal">Ok</button>&nbsp;&nbsp;'
								+ '<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>'
								+ '</div>'

							  + '    </div>'
							  + '  </div>'
							  + '</div>'
							//user approve role modal end
		$("#main-div").html('');
		$("#main-div").html(renderUserListForRoleApprovalHtm);
		$("#userDetails").ready(function() {
			var table = $('#userDetailsTable').DataTable({
				"pageLength" : 10,
				// lengthChange: false,
				buttons : [ 'copy', 'excel', 'pdf' ]
			});

			// table.buttons().container()
			// .appendTo( '#example_wrapper .col-md-6:eq(0)' );
		});

		getApproveRole = function(i, userId) {
			
			roleListHtm = '';
			rolesAr = [];

			for (j = 0; j < data.userDetails[i].roles.length; j++) {
				rolesAr[j] = data.userDetails[i].roles[j].roleName;
				
			}

			roleListHtm = '<p>Select role for "'+data.userDetails[i].emailId+'"</p>'
			for (i = 0; i < data.roleArr.length; i++) {
				role = data.roleArr[i];
				status = checkInRoleList(role);

				if (status == "true") {
					roleListHtm += '<br/><input type="checkbox" checked name="' + data.role[i].roleName + '" id="' + data.role[i].roleName + '">&nbsp;&nbsp;'
							+ data.role[i].roleName
				} else if (status == "false") {
					roleListHtm += '<br/><input type="checkbox" name="' + data.role[i].roleName + '" id="' + data.role[i].roleName + '">&nbsp;&nbsp;'
							+ data.role[i].roleName
				}
			}
			
			roleListHtm += '<br/><br/><button id="modifyRoleBtn" type="submit" title="Modify Role" class="btn btn-success">Modify Role</button>'
						//+'<button id="btnCancel" type="submit" title="Cancel" class="btn custom-btn">Cancel</button>'
			$("#approveRole").modal('show');
			$("#showRoles").html('');
			$("#showRoles").html(roleListHtm);

			function checkInRoleList(role) {
				return rolesAr.includes(role);
			}
			
			$("#modifyRoleBtn").bind("click", function(){
				checkRoleJson = {};
				checkJsonAddArr = [];
				checkJsonDelArr = [];
				for(i = 0; i < data.role.length; i++){
					st = checkInRoleList($("#"+data.role[i].roleName).attr('name'));
					if($("#"+data.role[i].roleName).is(":checked") == true){
						checkJsonAddArr.push($("#"+data.role[i].roleName).attr('name'));
					}
				}
				
				checkRoleJson.add = checkJsonAddArr;
				checkRoleJson.userId = userId;
				
				AH.manageRoleForUser(AP.baseURL+"userProfile/manageRole", "POST", checkRoleJson);
			})
		}
	}
	
	UP.checkRequestForContributorRole = function(){
		AH.getAllRequestForContributor(AP.baseURL+"userProfile/getContributorRequest", "GET");
	}
	

		UP.renderContributorRequest = function(data) {
		renderContribUserHtm = '';

		renderContribUserHtm = '<h1>Approve Contributor Request</h1><div class="row">'
			
		if (data.done == true) {
			renderContribUserHtm += '<div id="ApproveContributorData" class="table-responsive"><table id="ApproveContributorTable" class="table table-striped table-bordered" style="width:100%"><thead><tr><th>Name</th><th>Email ID</th><th>Action</th></tr></thead>'
				+ ' <tbody>'
				for (i = 0; i < data.users.length; i++) {
				renderContribUserHtm += '<tr><td>'
						+ data.users[i].name
						+ '</td><td>'
						+ data.users[i].emailId
						+ '</td><td>'
						+ '<button onClick="getApproveRequest('
						+ data.users[i].userId
						+ ')" type="submit" title="Approve Request" class="btn btn-dark btnsyle">Approve</button>'
						//+ '<button onClick="" type="submit" title="Reject Request" class="btn custom-btn">Reject</button>'
						+ '</td></tr>'
			}
			renderContribUserHtm += '</tbody></table></div>'
		} else {
			renderContribUserHtm += "<div class='col-xl-12 col-md-12 col-sm-12 alert alert-danger ' style='text-align:center;'>"+data.msg+"</div>"
		}
		renderContribUserHtm += '</div>'

		$("#main-div").html('');
		$("#main-div").html(renderContribUserHtm);
		$("#ApproveContributorData").ready(function() {
			var table = $('#ApproveContributorTable').DataTable({
				"pageLength" : 10,
				// lengthChange: false,
				buttons : [ 'copy', 'excel', 'pdf' ]
			});

			// table.buttons().container()
			// .appendTo( '#example_wrapper .col-md-6:eq(0)' );
		});
		
		getApproveRequest = function(userId) {
			AH.approvedContribRequest(AP.baseURL
					+ "userProfile/approvedContributorRequest", "GET", userId);
		}
	}
		
	UP.activateAccountByAdmin = function(){
		AH.getAllPendingAccountActivation(AP.baseURL+"userProfile/getPendingAccountActivation", "GET");
	}

	UP.renderActivationAccountUsers = function(data){
		renderAccountActUserHtm = '';

		renderAccountActUserHtm = '<h1>Active Account</h1><div class="row">'
		if (data.done == true) {
			renderAccountActUserHtm += '<div id="ActiveAccountData" class="table-responsive"><table id="ActiveAccountTable" class="table table-striped table-bordered" style="width:100%"><thead><tr><th>Name</th><th>Email ID</th><th>Action</th></tr></thead>'
				+ ' <tbody>'
				for (i = 0; i < data.users.length; i++) {
				renderAccountActUserHtm += '<tr><td>'
						+ data.users[i].name
						+ '</td><td>'
						+ data.users[i].emailId
						+ '</td><td>'
						+ '<button onClick="getActivateAccount('
						+ data.users[i].userId
						+ ')" type="submit" title="Activate account" class="btn btn-dark btnsyle">Activate</button>'
						+ '</td></tr>'
			}
			renderAccountActUserHtm += '</tbody></table></div>'
		} else {
			renderAccountActUserHtm += "<div class='col-xl-12 col-md-12 col-sm-12 alert alert-danger ' style='text-align:center;'>"+data.msg+"</div>"
		}
		renderAccountActUserHtm += '</div>'

		$("#main-div").html('');
		$("#main-div").html(renderAccountActUserHtm);
		$("#ActiveAccountData").ready(function() {
			var table = $('#ActiveAccountTable').DataTable({
				paging: true,
                searching: true,
                ordering: true,
                info: true,
                lengthMenu: [5, 10, 25, 50, 75, 100],
                dom: 'Bfrtip',
                buttons: [
                    'copy', 'csv', 'excel', 'pdf', 'print'
                ]
			});

			// table.buttons().container()
			// .appendTo( '#example_wrapper .col-md-6:eq(0)' );
		});
		getActivateAccount = function(userId) {
			AH.activateAccount(AP.baseURL
					+ "userProfile/activateAccount", "GET", userId);
		}
	}
	UP.ChangeUserPassword = function(){

		ChangePasswordHtm = '';
		ChangePasswordHtm = '<h1>Change Password</h1><div class="row" id="changepasswordDiv" >'
			+'<div class="col-sm-12 offset-md-2 col-md-8 offset-lg-2 col-lg-8  offset-xl-2 col-xl-8">'
+'<div class="forget-heading" ></div>'
			+'<form id="changepasswordForm" name="changepasswordForm" method="POST">'
			
			+'<div class=" col-sm-12 l0-r0-padding t1-b0-padding ">'
						

			+'<span class="" id="message">Enter Old Password</span> '
			+'<input type="password" name="oldpwd" id="oldpwd" class="regis form-control" placeholder="Old Password" /><br />'
			+'</div>'

			+'<div class=" col-sm-12 l0-r0-padding t1-b0-padding">'
			+'<span class="" id="message">Enter New Password</span>' 
			+'<input type="password" name="newpwd" id="newpwd" class="regis form-control" placeholder="New Password" /><br />'
			+'</div>'

			+'<div class=" col-sm-12 l0-r0-padding t1-b0-padding">'
			+'<span class="" id="message">Confirm New Password</span>' 
			+'<input type="password" name="confirmpwd" id="confirmpwd" class="regis form-control" placeholder="Re-enter New Password" /><br />'
			+'</div>'
			+'<div class="col-sm-12" align="center">'
			+'<button id="changepassword" class=" btn btn-dark ">Submit</button>'
			
					
			+'</form>'
			+'</div>'
			ChangePasswordHtm += '</div>'

				$("#main-div").html('');
				$("#main-div").html(ChangePasswordHtm);
				$('#changepasswordDiv').ready(function() {
   
					$("#changepasswordForm").validate({
						errorClass : "error-class",

						// Specify the validation rules
						rules : {

							oldpwd : "required",
							newpwd :  {
								required: true,
							      minlength: 6
							      },
							confirmpwd : {
								required: true,
							      minlength: 6,
								equalTo : "#newpwd"
							}
						},

						// Specify the validation error messages
						messages : {

							oldpwd : "Please enter old password",
							newpwd : {
								required : "Please Enter a password of at least 6 characters "
							},
							confirmpwd : {
								required : "Please provide a confirm password of at least 6 characters ",
								message : "password and confirm password must be same"
							},

						},
						submitHandler : function(form) {
							com.coep.test.userProfile.changPassword();
						}
					});

				});
	}
})(com.coep.test.ajaxHandler, com.coep.test.addProblem , com.coep.test.userProfile);
