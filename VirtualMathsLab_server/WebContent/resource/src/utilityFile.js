(function(AH, AP, UP, UF) {

	UF.getAllUsersToWriteInExcel = function(){
		AH.getUserListToWriteInExcel(AP.baseURL+"utility/getUserList", "GET");
	}
	
	UF.downloadSampleExcelSheet = function() {
//		$("#main-div").html(' <a id="downloadFile" href="resource/files/VirtualMathsLab_v1.0.xlsx" download="VirtualMathsLab_v1.0.xlsx" rel="nofollow" target="_blank" class="CloseNav">Download</a>');
//		$('#downloadFile').click();
		window.location.href = 'resource/files/VirtualMathsLab_v1.0.xlsx';
		 setTimeout(function(){ $("#Loading").css("display","none"); }, 100);
	} 
	
	UF.renderUserListForDownload = function(data){

		if(data.done == false){
			$("#main-div").html('');
			$("#main-div").html("<div class='col-xl-12 col-md-12 col-sm-12 alert alert-danger' style='text-align:center;'>"+data.msg+"</div>");
		}else{
			users = data.users;
			
			renderUserListHtm = '';
			renderUserListHtm = '<div class="row">'
				+ '<div class="col-xl-12 col-md-12 col-sm-12">'
				+ '<h1>List of Users</h1>'
				+ '<div id="AlluserData" class="table-responsive">'
				+ '<table id="All_userDataTable" class="table table-striped table-bordered" style="width:100%">'
				+ '<thead><tr><th>UserId</th><th>Name</th><th>Email ID</th><th>Phone Number</th><th>Whatsapp Number</th><th>Gender</th>'
				+ '<th>DOB</th><th>City</th><th>Pincode</th><th>Roles</th>'
				
				+'<th>Contributor School Name</th><th>Contributor School Address</th><th>Contributor School Type</th><th>Contributor Medium</th>'
				+'<th>Contributor Experience</th><th>Can Contribute In Latex</th>'
				
				+'<th>Teacher School Name</th><th>Teacher School Address</th><th>Teacher School Type</th><th>Teacher Medium</th>'
				+'<th>Teacher Experience</th><th>Teaching Group</th>'
				
				+'<th>Parent Qualification</th><th>Parent Occupation</th><th>Parent Designation</th><th>Parent Service Length</th><th>Parent Company</th>'
				
				+'<th>Student School Name</th><th>Student School Address</th><th>Student School Type</th><th>Student Medium</th>'
				+'<th>Parent Mail Id</th><th>Standard</th><th>Father Name</th><th>Father Age</th><th>Father Qualification</th>'
				+'<th>Father Occupation</th><th>Father Designation</th><th>Father Company</th>'
				+ '<th>Father Service Length</th><th>Father City</th><th>Mother Name</th><th>Mother Age</th><th>Mother Qualification</th>'
				+ '<th>Mother Occupation</th><th>Mother Designation</th><th>Mother Company</th><th>Mother Service Length</th>'
				+ '<th>Mother City</th>'			
				+ '</tr></thead>'
				+ '<tbody>'	
			for(i=0; i < users.length; i++){
				
				renderUserListHtm += '<tr><td>'+ users[i].UserId + '</td><td>'+ users[i].name + '</td><td>'+ users[i].emailId + '</td>'
							+ '<td>'+ users[i].phoneNo + '</td><td>'+ users[i].whtsapp + '</td>'
							
					if(users[i].gender == 1){
						renderUserListHtm +='<td>Male</td>'
					}else{
						renderUserListHtm +='<td>Female</td>'
					}
				renderUserListHtm += '<td>'+ users[i].dob + '</td><td>'+ users[i].city + '</td><td>'+ users[i].pincode + '</td>'			
							+ '<td>'+ Object.keys(users[i].roles) +'</td>'
							
				if("CONTRIBUTOR" in data.users[i].roles){
					renderUserListHtm += '<td>'+ users[i].contributor.schoolName + '</td><td>'+ users[i].contributor.schoolAdd + '</td><td>'+ users[i].contributor.schoolType + '</td><td>'+ users[i].contributor.medium + '</td>'
									+ '</td><td>'+ users[i].contributor.exp + '</td><td>'+ users[i].contributor.canContribInLatex + '</td>'
				}else{
					renderUserListHtm += '<td>NA</td><td>NA</td><td>NA</td><td>NA</td><td>NA</td><td>NA</td>'
				}		
							
				if("TEACHER" in data.users[i].roles){
					renderUserListHtm += '<td>'+ users[i].teacher.schoolName + '</td><td>'+ users[i].teacher.schoolAdd + '</td><td>'+ users[i].teacher.schoolType + '</td><td>'+ users[i].teacher.medium + '</td>'
									+ '</td><td>'+ users[i].teacher.exp + '</td><td>'+ users[i].teacher.teachGroup + '</td>'
				}else{
					renderUserListHtm += '<td>NA</td><td>NA</td><td>NA</td><td>NA</td><td>NA</td><td>NA</td>'
				}			
							
							
//				if("CONTRIBUTOR" in data.users[i].roles || "TEACHER" in data.users[i].roles || "STUDENT" in data.users[i].roles){
//					renderUserListHtm += '<td>'+ users[i].schoolName + '</td><td>'+ users[i].schoolAdd + '</td><td>'+ users[i].medium + '</td>'
//				}else{
//					renderUserListHtm += '<td>NA</td><td>NA</td><td>NA</td>'
//				}
//				
//				if("CONTRIBUTOR" in data.users[i].roles || "TEACHER" in data.users[i].roles){
//					renderUserListHtm += '<td>'+ users[i].schoolType + '</td><td>'+ users[i].exp + '</td>'
//				}else{
//					renderUserListHtm += '<td>NA</td><td>NA</td>'
//				}
//				
//				if("CONTRIBUTOR" in data.users[i].roles){
//					renderUserListHtm += '<td>'+ users[i].canContribInLatex + '</td>'
//				}else{
//					renderUserListHtm += '<td>NA</td>'
//				}
//				
//				if("TEACHER" in data.users[i].roles){
//					renderUserListHtm += '<td>'+ users[i].teachGroup + '</td>'
//				}else{
//					renderUserListHtm += '<td>NA</td>'
//				}
				
				if("PARENT" in data.users[i].roles){
					renderUserListHtm += '<td>'+ users[i].parent.qualif + '</td><td>'+ users[i].parent.occup + '</td><td>'+ users[i].parent.desig + '</td><td>'
								+ users[i].parent.servLeng + '</td><td>'+ users[i].parent.company + '</td>'
				}else{
					renderUserListHtm += '<td>NA</td><td>NA</td><td>NA</td><td>NA</td><td>NA</td>'
				}
				
				if("STUDENT" in data.users[i].roles){
					renderUserListHtm += '<td>'+ users[i].student.schoolName + '</td><td>'+ users[i].student.schoolAdd + '</td>'
								+ '<td>'+ users[i].student.schoolType + '</td><td>'+ users[i].student.medium + '</td>'
								+ '<td>'+ users[i].student.parEmailId + '</td><td>'+ users[i].student.standard + '</td>'
								+ '<td>'+ users[i].student.faname + '</td><td>'+ users[i].student.fage + '</td>'
								+ '<td>'+ users[i].student.fqualif + '</td><td>'+ users[i].student.foccup + '</td>'
								+ '<td>'+ users[i].student.fdesig + '</td><td>'+ users[i].student.fcompany + '</td>'
								+ '<td>'+ users[i].student.fservLeng + '</td><td>'+ users[i].student.fcity + '</td>'
								+ '<td>'+ users[i].student.maname + '</td><td>'+ users[i].student.mage + '</td>'
								+ '<td>'+ users[i].student.mqualif + '</td><td>'+ users[i].student.moccup + '</td>'
								+ '<td>'+ users[i].student.mdesig + '</td><td>'+ users[i].student.mcompany + '</td>'
								+ '<td>'+ users[i].student.mservLeng + '</td><td>'+ users[i].student.mcity + '</td>'
				}else{
					renderUserListHtm += '<td>NA</td><td>NA</td><td>NA</td><td>NA</td><td>NA</td><td>NA</td>'
								+ '<td>NA</td><td>NA</td><td>NA</td><td>NA</td><td>NA</td><td>NA</td>'
								+ '<td>NA</td><td>NA</td><td>NA</td><td>NA</td><td>NA</td><td>NA</td>'
								+ '<td>NA</td><td>NA</td><td>NA</td><td>NA</td>'
				}
				
				renderUserListHtm +='</tr>'
				
			}
			renderUserListHtm += '</tbody>'
				+ '</table></div>'
				+ '</div>'
				+ '</div>'
				
			$("#main-div").html('');
			$("#main-div").html(renderUserListHtm);
			$("#AlluserData").ready(function() {
				var table = $('#All_userDataTable').DataTable({
					"pageLength" : 10,
					 dom: 'Bfrtip',
					buttons : [ {
		                extend: 'excelHtml5',
		                title: 'Virtual Maths Lab User list'
		            },
		            {
		                extend: 'pdfHtml5',
		                title: 'Virtual Maths Lab User list',
		                orientation: 'landscape',
	                    pageSize: 'A0'
		            } ]
				});

				 
			});
		}
		
		
	}
	
	UF.uploadQuesByExcelSheet = function(taskFlag){
			
			document.title = "Upload Excel";
			
			var uploadOptHtm = "<div id='loader'></div>";
			
			
			
			uploadOptHtm += '<div class="row">'
						+ '<div class="col-xl-12 col-md-12 col-sm-12 id="uploadExcelSheet>'
						if(taskFlag == 1){
							uploadOptHtm += '<h1>Upload Excel Sheet</h1>'
						}else if(taskFlag == 2){
							uploadOptHtm += '<h1>Check Excel Sheet</h1>'
						}
			uploadOptHtm += '<section class="section-preview " style="padding: 0 10px">'
				+ '<div class="form-group row">'
				+ '<input type="file" class="form-control col-xl-10 col-md-10 col-sm-12 " name="uploadExcel" id="uploadExcel" accept=".xls,.xlsx" title="Upload File">'
				
						if(taskFlag == 1){
							uploadOptHtm += '<button title="UPLOAD EXCEL" id="uploadExcelBtn" class="btn btn-dark col-xl-2 col-md-2 col-sm-12 justify-content-center">UPLOAD EXCEL</button>'
						}else if(taskFlag == 2){
							uploadOptHtm += '<button title="UPLOAD EXCEL" id="uploadExcelBtn" class="btn btn-dark col-xl-2 col-md-2 col-sm-12 justify-content-center">CHECK EXCEL</button>'
						}
//				+ '<button title="UPLOAD EXCEL" id="uploadExcelBtn">UPLOAD EXCEL</button>'
			uploadOptHtm += '</div></section></div>'
				
				
				+ '<div id="excel_error" class="red-color"></div>'
						+ '</div>'
			
			
			$("#main-div").html('');
			$("#main-div").html(uploadOptHtm);
			
			
			$('#uploadExcelBtn').on("click" ,function() {
				
				var loader = '<div class="overlay" id="Loading">'
					 + '<div class="overlay__inner">'
					 + '<div class="overlay__content"><span class="spinner"></span><br/><span class="loading">LOADING....</span></div>'
					 + '</div>'
					 + '</div>'
				$("#loader").html(loader);
				
				(document.getElementById("uploadExcel").files.length > 0) ? isFileLinked = 1 : isFileLinked = 0;
				if (isFileLinked == 1) {
					
					var file = $('#uploadExcel');
		
					var filename = $.trim(file.val());
		
					var fileSize = ($('#uploadExcel')[0].files[0].size / 1024 / 1024);
					
					var formData = new FormData();
					formData.append('file', $('#uploadExcel')[0].files[0]);
					if(taskFlag == 1){
						formData.append('flag', 1);
					}else if(taskFlag == 2){
						formData.append('flag', 2);
					}
					
					if (filename != "" || filename.length != 0) {
						if (filename != null || filename != undefined){
							if(fileSize <= 5){
								$.ajax({
									url : 'utility/uploadQuesExcel',
									type : 'POST',
									data : formData,
									enctype : "multipart/form-data",
//									cache: false,
								    contentType: false,
								    processData: false,
								    dataType : 'json',
								    success: function(data){
								    	$("#uploadExcel").val("");
								    	if(data.done == false){
								    		$("#excel_error").removeClass("green-color");
								    		$("#excel_error").addClass("red-color");
								    		$("#excel_error").html('<h4>'+data.msg+'</h4>');
								    		 setTimeout(function(){ $("#Loading").css("display","none"); }, 1000);
								    	}else{
								    		$("#excel_error").removeClass("red-color");
								    		$("#excel_error").addClass("green-color");
								    		$("#excel_error").html('<h4>'+data.msg+'</h4>');
								    		 setTimeout(function(){ $("#Loading").css("display","none"); }, 1000);
								    	}
								    	
								    }
								});
							}
						}
					}
				}else{
					$("#excel_error").addClass("red-color");
					$("#excel_error").html('<h4>Please select the excel sheet to upload..</h4>');
				}
			});
		}
	
	
	UF.moderatorPage = function(){
		console.log("moderator account");
	}
	
	UF.demoCall = function(){
//		console.log("Hey");

		var uploadOptHtm = "<div id='loader'></div>";
		
		
		
		uploadOptHtm += '<div class="row">'
					+ '<div class="col-xl-12 col-md-12 col-sm-12 id="uploadJavaFile>'
						uploadOptHtm += '<h1>Upload JAVA File</h1>'
							
		uploadOptHtm += '<section class="section-preview " style="padding: 0 10px">'
			+ '<div class="form-group row">'
			+ '<input type="file" class="form-control col-xl-10 col-md-10 col-sm-12 " name="uploadJavaFile" id="uploadJavaFile" accept=".xls,.xlsx" title="Upload File">'
			
						uploadOptHtm += '<button title="UPLOAD FILE" id="uploadJavaFileBtn" class="btn btn-dark col-xl-2 col-md-2 col-sm-12 justify-content-center">UPLOAD FILE</button>'
							
		uploadOptHtm += '</div></section></div>'
			
			
			+ '<div id="file_error" class="red-color"></div>'
					+ '</div>'
			
			 + '<button title="UPLOAD FILE" id="demoBtn" class="btn btn-dark col-xl-2 col-md-2 col-sm-12 justify-content-center">Demo</button>'
			 
			$("#main-div").html(uploadOptHtm);

			
			$("#demoBtn").on("click", function(){
				AH.demoCall(AP.baseURL+"utility/demoCall", "GET");
			})
	}
	
	UF.deleteArchiveQuestions = function(){
		AH.deleteArchiveQuestionsFromDB(AP.baseURL+"questionGroups/archived/delete", "DELETE");
	}
	
	UF.changeTopicStatusFlag = function(){
		$.ajax({
				type : "GET",
				url : com.coep.test.addProblem.baseURL
						+ "topic/api/get/home/topics",
				dataType : 'json',
				contentType : 'application/json',
				success : function(data) {
					UF.renderAllVerticals(data);
				},
				error : function() {
				}

			});
	}
	
	UF.renderAllVerticals = function(tpList){
		var	html1 = '<div class="form-group step1 col-sm-12 col-md-12 col-lg-12 col-xl-12" >'
					+ '<label class="col-xl-12 col-md-12 col-sm-12" for="selectTopic">Select Vertical <span class="marathi-text" >(श्रेणी निवडा)</span><i class="fa fa-asterisk " aria-hidden="true"></i></label>'
					+ ' <select class=" form-control col-xl-12 col-md-12 col-sm-12 selectpicker" data-live-search="true"  id="selTopicForFilter11"   >'
					html1 += '' + '  <option value="" id ="0">Select topic</option>'
					for (var i = 0; i <tpList.topicData.length; i++) {
						html1 += '' + '  <option value="' + tpList.topicData[i].TID
							+ '" id ="' + tpList.topicData[i].TID + '" >'
							+ tpList.topicData[i].TNO +" - "+ tpList.topicData[i].TN +"&nbsp;("+ tpList.topicData[i].TN1 + ') </option>'
					}

					html1 += ' </select>'
							+ '<div id="error" class="invalid-feedback selectTopic">Please select vertical.</div>'
							+ '</div>'	
							+'<div class="form-group step1" id="variationDiv">'	
							+ '<center><button id= "activateTopicBtn" type="submit" class="btn btn-success btnFont">Activate Vertical</button>'
							+ '&emsp;<button id= "deactivateTopicBtn" type="submit" class="btn btn-danger btnFont">Deactivate Vertical</button></center>'
							+ '</div>'
							
					$("#main-div").html(html1);
					$('select').selectpicker();
					
					
					$("#activateTopicBtn").on("click", function(){
						 var tid = $("#selTopicForFilter11").find("option:selected").val();
						if(tid != ""){
							$("#error").css("display", "none");
							AH.updateTopicStatusByTID(AP.baseURL + "utility/topic/status/change", "GET", tid, true);
						}else { 
							$("#error").css("display", "block");
						}
					})
					
					$("#deactivateTopicBtn").on("click", function(){
						 var tid = $("#selTopicForFilter11").find("option:selected").val();
						if(tid != ""){
							$("#error").css("display", "none");
							AH.updateTopicStatusByTID(AP.baseURL + "utility/topic/status/change", "GET", tid, false);
						}else { 
							$("#error").css("display", "block");
						}
					})
					
	}
	
	
	
})(com.coep.test.ajaxHandler, com.coep.test.addProblem , com.coep.test.userProfile, com.coep.test.utilityFile);