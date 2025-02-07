(function(AH, AP, UP, UF, QB) {

	UF.getAllUsersToWriteInExcel = function() {
		AH.getUserListToWriteInExcel(AP.baseURL + "utility/getUserList", "GET");
	}

	UF.downloadSampleExcelSheet = function() {
		//		$("#main-div").html(' <a id="downloadFile" href="resource/files/VirtualMathsLab_v1.0.xlsx" download="VirtualMathsLab_v1.0.xlsx" rel="nofollow" target="_blank" class="CloseNav">Download</a>');
		//		$('#downloadFile').click();
		window.location.href = 'resource/files/VML_QuestionGroup_With_Multiple_Question_Sample_Sheet_v1.0.xlsx';
		setTimeout(function() { $("#Loading").css("display", "none"); }, 100);
	}

	UF.renderUserListForDownload = function(data) {

		if (data.done == false) {
			$("#main-div").html('');
			$("#main-div").html("<div class='col-xl-12 col-md-12 col-sm-12 alert alert-danger' style='text-align:center;'>" + data.msg + "</div>");
		} else {
			users = data.users;

			renderUserListHtm = '';
			renderUserListHtm = '<div class="row">'
				+ '<div class="col-xl-12 col-md-12 col-sm-12">'
				+ '<h1>List of Users</h1>'
				+ '<div id="AlluserData" class="table-responsive">'
				+ '<table id="All_userDataTable" class="table table-striped table-bordered" style="width:100%">'
				+ '<thead><tr><th>UserId</th><th>Name</th><th>Email ID</th><th>Phone Number</th><th>Whatsapp Number</th><th>Gender</th>'
				+ '<th>DOB</th><th>City</th><th>Pincode</th><th>Roles</th>'

				+ '<th>Contributor School Name</th><th>Contributor School Address</th><th>Contributor School Type</th><th>Contributor Medium</th>'
				+ '<th>Contributor Experience</th><th>Can Contribute In Latex</th>'

				+ '<th>Teacher School Name</th><th>Teacher School Address</th><th>Teacher School Type</th><th>Teacher Medium</th>'
				+ '<th>Teacher Experience</th><th>Teaching Group</th>'

				+ '<th>Parent Qualification</th><th>Parent Occupation</th><th>Parent Designation</th><th>Parent Service Length</th><th>Parent Company</th>'

				+ '<th>Student School Name</th><th>Student School Address</th><th>Student School Type</th><th>Student Medium</th>'
				+ '<th>Parent Mail Id</th><th>Standard</th><th>Father Name</th><th>Father Age</th><th>Father Qualification</th>'
				+ '<th>Father Occupation</th><th>Father Designation</th><th>Father Company</th>'
				+ '<th>Father Service Length</th><th>Father City</th><th>Mother Name</th><th>Mother Age</th><th>Mother Qualification</th>'
				+ '<th>Mother Occupation</th><th>Mother Designation</th><th>Mother Company</th><th>Mother Service Length</th>'
				+ '<th>Mother City</th>'
				+ '</tr></thead>'
				+ '<tbody>'
			for (i = 0; i < users.length; i++) {

				renderUserListHtm += '<tr><td>' + users[i].UserId + '</td><td>' + users[i].name + '</td><td>' + users[i].emailId + '</td>'
					+ '<td>' + users[i].phoneNo + '</td><td>' + users[i].whtsapp + '</td>'

				if (users[i].gender == 1) {
					renderUserListHtm += '<td>Male</td>'
				} else {
					renderUserListHtm += '<td>Female</td>'
				}
				renderUserListHtm += '<td>' + users[i].dob + '</td><td>' + users[i].city + '</td><td>' + users[i].pincode + '</td>'
					+ '<td>' + Object.keys(users[i].roles) + '</td>'

				if ("CONTRIBUTOR" in data.users[i].roles) {
					renderUserListHtm += '<td>' + users[i].contributor.schoolName + '</td><td>' + users[i].contributor.schoolAdd + '</td><td>' + users[i].contributor.schoolType + '</td><td>' + users[i].contributor.medium + '</td>'
						+ '</td><td>' + users[i].contributor.exp + '</td><td>' + users[i].contributor.canContribInLatex + '</td>'
				} else {
					renderUserListHtm += '<td>NA</td><td>NA</td><td>NA</td><td>NA</td><td>NA</td><td>NA</td>'
				}

				if ("TEACHER" in data.users[i].roles) {
					renderUserListHtm += '<td>' + users[i].teacher.schoolName + '</td><td>' + users[i].teacher.schoolAdd + '</td><td>' + users[i].teacher.schoolType + '</td><td>' + users[i].teacher.medium + '</td>'
						+ '</td><td>' + users[i].teacher.exp + '</td><td>' + users[i].teacher.teachGroup + '</td>'
				} else {
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

				if ("PARENT" in data.users[i].roles) {
					renderUserListHtm += '<td>' + users[i].parent.qualif + '</td><td>' + users[i].parent.occup + '</td><td>' + users[i].parent.desig + '</td><td>'
						+ users[i].parent.servLeng + '</td><td>' + users[i].parent.company + '</td>'
				} else {
					renderUserListHtm += '<td>NA</td><td>NA</td><td>NA</td><td>NA</td><td>NA</td>'
				}

				if ("STUDENT" in data.users[i].roles) {
					renderUserListHtm += '<td>' + users[i].student.schoolName + '</td><td>' + users[i].student.schoolAdd + '</td>'
						+ '<td>' + users[i].student.schoolType + '</td><td>' + users[i].student.medium + '</td>'
						+ '<td>' + users[i].student.parEmailId + '</td><td>' + users[i].student.standard + '</td>'
						+ '<td>' + users[i].student.faname + '</td><td>' + users[i].student.fage + '</td>'
						+ '<td>' + users[i].student.fqualif + '</td><td>' + users[i].student.foccup + '</td>'
						+ '<td>' + users[i].student.fdesig + '</td><td>' + users[i].student.fcompany + '</td>'
						+ '<td>' + users[i].student.fservLeng + '</td><td>' + users[i].student.fcity + '</td>'
						+ '<td>' + users[i].student.maname + '</td><td>' + users[i].student.mage + '</td>'
						+ '<td>' + users[i].student.mqualif + '</td><td>' + users[i].student.moccup + '</td>'
						+ '<td>' + users[i].student.mdesig + '</td><td>' + users[i].student.mcompany + '</td>'
						+ '<td>' + users[i].student.mservLeng + '</td><td>' + users[i].student.mcity + '</td>'
				} else {
					renderUserListHtm += '<td>NA</td><td>NA</td><td>NA</td><td>NA</td><td>NA</td><td>NA</td>'
						+ '<td>NA</td><td>NA</td><td>NA</td><td>NA</td><td>NA</td><td>NA</td>'
						+ '<td>NA</td><td>NA</td><td>NA</td><td>NA</td><td>NA</td><td>NA</td>'
						+ '<td>NA</td><td>NA</td><td>NA</td><td>NA</td>'
				}

				renderUserListHtm += '</tr>'

			}
			renderUserListHtm += '</tbody>'
				+ '</table></div>'
				+ '</div>'
				+ '</div>'

			$("#main-div").html('');
			$("#main-div").html(renderUserListHtm);
			$("#AlluserData").ready(function() {
				var table = $('#All_userDataTable').DataTable({
					"pageLength": 10,
					dom: 'Bfrtip',
					buttons: [{
						extend: 'excelHtml5',
						title: 'Virtual Maths Lab User list'
					},
					{
						extend: 'pdfHtml5',
						title: 'Virtual Maths Lab User list',
						orientation: 'landscape',
						pageSize: 'A0'
					}]
				});


			});
		}


	}

	UF.uploadQuesByExcelSheet = function(taskFlag) {

		document.title = "Upload Excel";

		var uploadOptHtm = "<div id='loader'></div>";



		uploadOptHtm += '<div class="row">'
			+ '<div class="col-xl-12 col-md-12 col-sm-12 id="uploadExcelSheet>'
		if (taskFlag == 1) {
			uploadOptHtm += '<h1>Upload Excel Sheet</h1>'
		} else if (taskFlag == 2) {
			uploadOptHtm += '<h1>Check Excel Sheet</h1>'
		}
		uploadOptHtm += '<section class="section-preview " >'
			+ '<div class="form-group row">'
			+ '<input type="file" style="padding: 3px; margin: 10px" class="form-control col-xl-9 col-md-9 col-sm-12 " name="uploadExcel" id="uploadExcel" accept=".xls,.xlsx" title="Upload File">'

		if (taskFlag == 1) {
			uploadOptHtm += '<button title="UPLOAD EXCEL" id="uploadExcelBtn" class="btn btn-dark col-xl-2 col-md-2 col-sm-12 justify-content-center">UPLOAD EXCEL</button>'
		} else if (taskFlag == 2) {
			uploadOptHtm += '<button title="UPLOAD EXCEL" id="uploadExcelBtn" class="btn btn-dark col-xl-2 col-md-2 col-sm-12 justify-content-center">CHECK EXCEL</button>'
		}
		//				+ '<button title="UPLOAD EXCEL" id="uploadExcelBtn">UPLOAD EXCEL</button>'
		uploadOptHtm += '</div></section></div>'


			+ '<div id="excel_error" class="red-color"></div>'
			+ '</div>'


		$("#main-div").html('');
		$("#main-div").html(uploadOptHtm);


		$('#uploadExcelBtn').on("click", function() {

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
				if (taskFlag == 1) {
					formData.append('flag', 1);
				} else if (taskFlag == 2) {
					formData.append('flag', 2);
				}

				if (filename != "" || filename.length != 0) {
					if (filename != null || filename != undefined) {
						if (fileSize <= 5) {
							$.ajax({
								url: 'utility/uploadQuesExcel',
								type: 'POST',
								data: formData,
								enctype: "multipart/form-data",
								//									cache: false,
								contentType: false,
								processData: false,
								dataType: 'json',
								success: function(data) {
									$("#uploadExcel").val("");
									if (data.done == false) {
										$("#excel_error").removeClass("green-color");
										$("#excel_error").addClass("red-color");
										$("#excel_error").html('<h4>' + data.msg + '</h4>');
										setTimeout(function() { $("#Loading").css("display", "none"); }, 1000);
									} else {
										$("#excel_error").removeClass("red-color");
										$("#excel_error").addClass("green-color");
										$("#excel_error").html('<h4>' + data.msg + '</h4>');
										setTimeout(function() { $("#Loading").css("display", "none"); }, 1000);
									}

								}
							});
						}
					}
				}
			} else {
				$("#Loading").css("display","none");
				$("#Loading").remove();
				$("#excel_error").addClass("red-color");
				$("#excel_error").html('<h4>Please select the excel sheet to upload.</h4>');
			}
		});
	}


	UF.moderatorPage = function() {

	}

	UF.demoCall = function() {

		var uploadOptHtm = "<div id='loader'></div>";



		uploadOptHtm += '<div class="row">'
			+ '<div class="col-xl-12 col-md-12 col-sm-12 id="uploadJavaFile>'
		uploadOptHtm += '<h1>Upload JAVA File</h1>'

		uploadOptHtm += '<section class="section-preview " style="padding: 0 10px">'
			+ '<div class="form-group row">'
			+ '<input type="file" class="form-control col-xl-9 col-md-9 col-sm-12 " name="uploadJavaFile" id="uploadJavaFile" accept=".xls,.xlsx" title="Upload File">'

		uploadOptHtm += '<button title="UPLOAD FILE" id="uploadJavaFileBtn" class="btn btn-dark col-xl-2 col-md-2 col-sm-12 justify-content-center">UPLOAD FILE</button>'

		uploadOptHtm += '</div></section></div>'


			+ '<div id="file_error" class="red-color"></div>'
			+ '</div>'

			+ '<button title="UPLOAD FILE" id="demoBtn" class="btn btn-dark col-xl-2 col-md-2 col-sm-12 justify-content-center">Demo</button>'

		$("#main-div").html(uploadOptHtm);


		$("#demoBtn").on("click", function() {
			AH.demoCall(AP.baseURL + "utility/demoCall", "GET");
		})
	}

	UF.deleteArchiveQuestions = function() {
		var childHtml = '<div class="overlay" id="Loading">'
			 + '<div class="overlay__inner">'
			 + '<div class="overlay__content"><span class="spinner"></span><br/><span class="loading">LOADING...</span></div>'
			 + '</div>'
			 + '</div>'
			 
			 $("#main-div").append(childHtml);
		
		AH.deleteArchiveQuestionsFromDB(AP.baseURL + "questionGroups/archived/delete", "DELETE");
	}

	UF.changeTopicStatusFlag = function() {
		$.ajax({
			type: "GET",
			url: com.coep.test.addProblem.baseURL
				+ "topic/api/get/home/topics",
			dataType: 'json',
			contentType: 'application/json',
			success: function(data) {
				UF.renderAllVerticals(data);
			},
			error: function() {
			}

		});
	}

	UF.renderAllVerticals = function(tpList) {
		var html1 = '<div class="form-group step1 col-sm-12 col-md-12 col-lg-12 col-xl-12" >'
			+ '<label class="col-xl-12 col-md-12 col-sm-12" for="selectTopic">Select Vertical <span class="marathi-text" >(श्रेणी निवडा)</span><i class="fa fa-asterisk " aria-hidden="true"></i></label>'
			+ ' <select class=" form-control col-xl-12 col-md-12 col-sm-12 selectpicker" data-live-search="true"  id="selTopicForFilter11"   >'
		html1 += '' + '  <option value="" id ="0">Select topic</option>'
		for (var i = 0; i < tpList.topicData.length; i++) {
			html1 += '' + '  <option value="' + tpList.topicData[i].TID
				+ '" id ="' + tpList.topicData[i].TID + '" >'
				+ tpList.topicData[i].TNO + " - " + tpList.topicData[i].TN + "&nbsp;(" + tpList.topicData[i].TN1 + ') </option>'
		}

		html1 += ' </select>'
			+ '<div id="error" class="invalid-feedback selectTopic">Please select vertical.</div>'
			+ '</div>'
			+ '<div class="form-group step1" id="variationDiv">'
			+ '<center><button id= "activateTopicBtn" type="submit" class="btn btn-success btnFont">Activate Vertical</button>'
			+ '&emsp;<button id= "deactivateTopicBtn" type="submit" class="btn btn-danger btnFont">Deactivate Vertical</button></center>'
			+ '</div>'

		$("#main-div").html(html1);
		$('select').selectpicker();


		$("#activateTopicBtn").on("click", function() {
			var tid = $("#selTopicForFilter11").find("option:selected").val();
			if (tid != "") {
				$("#error").css("display", "none");
				AH.updateTopicStatusByTID(AP.baseURL + "utility/topic/status/change", "GET", tid, true);
			} else {
				$("#error").css("display", "block");
			}
		})

		$("#deactivateTopicBtn").on("click", function() {
			var tid = $("#selTopicForFilter11").find("option:selected").val();
			if (tid != "") {
				$("#error").css("display", "none");
				AH.updateTopicStatusByTID(AP.baseURL + "utility/topic/status/change", "GET", tid, false);
			} else {
				$("#error").css("display", "block");
			}
		})
	}

//	New development for change of DoD for complete vertical
	UF.getTopicForDoDChange = function(flg) {
		$.ajax({
			type: "GET",
			url: "topic/api/get/main/topics",
			dataType: 'json',
			contentType: 'application/json',
			success: function(data) {
				if (flg == 0) {
					UF.getTopicToChangeDoD(data);
				} else if (flg == 1) {
					UF.getTopicToSetConfiguration(data);
				}else if (flg == 2) {
					UF.getTopicForDODCount(data);
				}
			},
			error: function() {
			}
		});
	}

	UF.getTopicToChangeDoD = function(data) {

		status = "Approved";

		var topicID = 0;
		var variationNum = 0;
		var difficultyLevel = 0;

		// second function code here
		var loader = '<div class="overlay" id="Loading">'
			+ '<div class="overlay__inner">'
			+ '<div class="overlay__content"><span class="spinner"></span><br/><span class="loading">LOADING....</span></div>'
			+ '</div>'
			+ '</div>'

		$("#main-div").html(loader);

		var html1 = '<h1>Change Difficulty Level (DoD)</h1>'
			+ '<div class="form-group step1 col-sm-12 col-md-12 col-lg-12 col-xl-12" >'
			+ '<label class="col-xl-12 col-md-12 col-sm-12" for="selectTopic">Select Vertical <span class="marathi-text" >(श्रेणी निवडा)</span><i class="fa fa-asterisk " aria-hidden="true"></i></label>'
			+ ' <select class=" form-control col-xl-12 col-md-12 col-sm-12 selectpicker" data-live-search="true"  id="selTopicForFilter11"   >'
		html1 += '' + '<option value="" id ="0">Select Vertical</option>'
		for (var i = 0; i < data.topicData.length; i++) {
			html1 += '' + '  <option value="' + data.topicData[i].TNO
				+ '" id ="' + data.topicData[i].TID + '" >'
				+ data.topicData[i].TNO + " - " + data.topicData[i].TN + "&nbsp;(" + data.topicData[i].TN1 + ') </option>'
		}

		html1 += ' </select>'
			+ '<div id="error" class="invalid-feedback selectTopic">Please select topic.</div>'
			+ '</div>'
			+ '<div class="form-group step1" id="variationDiv"></div>'

		$("#main-div").html(html1);
		$('select').selectpicker();

		$("#selTopicForFilter11").on('change', function() {
			var selected = $(this).find("option:selected").val();
			st = status + "_" + selected;
			$.ajax({
				type: "GET",
				url: "questionGroups/api/get/topic/questGroup/" + st,
				dataType: 'json',
				contentType: 'application/json',
				success: function(data) {
					if (data.done == false) {
						$("#main-div").html("<div class='col-xl-12 col-md-12 col-sm-12 alert alert-danger' style='text-align:center;'>No " + status + " questions in the database to display..</div>");
					} else {
						getTopicListDetails(data, status);
					}
				},
				error: function() {
				}
			});
		})

		function getTopicListDetails(data, status) {

			var html = '';
			html += '<div class="form-group step1 col-sm-12 col-md-12 col-lg-12 col-xl-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12" for="selectTopic">Select Topic <span class="marathi-text" >(श्रेणी निवडा)</span><i class="fa fa-asterisk " aria-hidden="true"></i></label>'
				+ ' <select class=" form-control col-xl-12 col-md-12 col-sm-12 selectpicker" data-live-search="true"  id="selTopicForFilter1"   >'
			html += '' + '  <option value="" id ="0">Select topic</option>'
			for (var i = 0; i < data.topicData.length; i++) {
				html += '' + '  <option value="' + data.topicData[i].TID
					+ '" id ="' + data.topicData[i].TID + '" >'
					+ data.topicData[i].TNO + " - " + data.topicData[i].TN + "&nbsp;(" + data.topicData[i].TN1 + ') </option>'
			}

			html += ' </select>'
				+ '<div id="error" class="invalid-feedback selectTopic">Please select topic.</div>'
				+ '</div>'
				+ '<div id="varNoDiv" class="row"> </div>'
				+ '<center><button id= "modifyVarBtn" type="submit" class="btn btn-dark">Submit</button></center>'
				+ '<div id="ques-bank-div"></div>'

			$("#variationDiv").html(html);
			$('select').selectpicker();


			selectedTopicId = 0;
			if (selectedTopicId != undefined) {

				$("#selTopicForFilter1").selectpicker('val', selectedTopicId);
				setTimeout(function() {
					$("#selTopicForFilter1").val(selectedTopicId).change();
				}, 500);
			}

			$("#selTopicForFilter1").on('change', function() {

				topicIdArray = [];
				var selected = $(this).find("option:selected");
				selected.each(function() {
					topicIdArray.push(parseInt($(this).val()));
				});

				var topicId = "["
				for (var i = 0; i < topicIdArray.length; i++) {
					if (i == (topicIdArray.length - 1)) {
						topicId += topicIdArray[i] + "]"
					} else {
						topicId += topicIdArray[i] + ","
					}
				}

				selectedTopicId = parseInt($(this).val());

				topicID = topicId; console.log("method not allowed issue : " + status + " : " + topicId);
				$.ajax({
					type: "POST",
					url: "topic/api/mapping/varNo/" + status + "/" + topicId,
					dataType: 'json',
					contentType: 'application/json',
					success: function(data) {

						if (data.done == true) {
							renderVariationNo(data);
						} else {
							alert(data.msg);
						}
					},
					error: function() {
					}
				});
			});
			
			function findKey(jsonObj, key) {
                var result = null;
                $.each(jsonObj, function(k, v) {
                    if (k === key) {
                        result = v;
                        return false; // Break out of $.each loop
                    }
                    if (typeof v === "object" && v !== null) {
                        result = findKey(v, key);
                        if (result !== null) return false; // Break out of $.each loop
                    }
                });
                return result;
            }

			function renderVariationNo(data) {
				console.log(data);
				var renderVarHtml = '';

				renderVarHtml += '<div class="form-group step1 col-sm-12 col-md-6 col-lg-6 col-xl-6" >'
					+ '<label class="col-xl-12 col-md-12 col-sm-12" for="selectVarNo">Select Variation No </label>'
				renderVarHtml += ' <select class=" form-control col-xl-6 col-md-6 col-sm-6 selectpicker" data-live-search="true"  id="selVarNoForFilter"    >'
				renderVarHtml += '  <option value="" id ="-1">Select Variation Number</option>'

				for (var i = 0; i < data.data[0].VARNO.length; i++) {
					renderVarHtml += ''
						+ '  <option value="'
					if (data.data[0].VARNO[i] == "100") {
						renderVarHtml += 'Not Assigned'
					} else {
						renderVarHtml += data.data[0].VARNO[i]
					}

					renderVarHtml += '" id ="' + data.data[0].VARNO[i] + '" >'
					if (data.data[0].VARNO[i] == "100") {
						renderVarHtml += 'Not Assigned'
					} else {
						renderVarHtml += data.data[0].VARNO[i]
					}
					renderVarHtml += ' </option>'
				}

				renderVarHtml += ' </select>'
					+ '<div id="error" class="invalid-feedback selectTopic">Please select topic.</div>'
					+ '</div>'

				renderVarHtml += '<div class="form-group step1 col-sm-12 col-md-5 col-lg-5 col-xl-5" >'
					+ '<label class="col-xl-12 col-md-12 col-sm-12" for="diffLeveFilter">Current Difficulty Level </label>'
					+ '<span>Level </span><span id="currentDoD"></span>'
					+ '</div>'

				renderVarHtml += '<div class="form-group step1 col-sm-12 col-md-6 col-lg-6 col-xl-6" >'
					+ '<label class="col-xl-12 col-md-12 col-sm-12" for="diffLeveFilter">Select Difficulty Level </label>'
				renderVarHtml += ' <select class=" form-control col-xl-12 col-md-12 col-sm-12 selectpicker" data-live-search="true"  id="diffLeveFilter" >'
				renderVarHtml += '  <option value="" id ="-1">Select Difficulty Level</option>'

				for (var i = 0; i < 5; i++) {
					renderVarHtml += '' + '  <option value="' + (i + 1)
						+ '" id ="' + (i + 1) + '" >Level '
						+ (i + 1) + ' </option>'
				}

				renderVarHtml += ' </select>'
					+ '<div id="error" class="invalid-feedback selectTopic">Please select difficulty level.</div>'
					+ '</div>'

				$("#varNoDiv").html(renderVarHtml);
				$('select').selectpicker();

				$("#selVarNoForFilter").on('change', function() {
					var selected = $("#selVarNoForFilter").find("option:selected").val();
					var keyToFind = "l"+selected;
            		var value = findKey(data.data[0].dt, keyToFind);
					$("#currentDoD").text(value.split(" ")[1]);
					
					varNoArray = [];
					var selected = $(this).find("option:selected");
					selected.each(function() {
						if ($(this).text() == "Select All") {
							for (var i = 0; i < data.data[0].VARNO.length; i++) {
								varNoArray.push(data.data[0].VARNO[i]);
							}
						} else if ($(this).text() == "Select Variation Number") {
							varNoArray.push("0");
						} else if ($(this).text() == "Not Assigned ") {
							varNoArray.push("100");
						} else {
							varNoArray.push(parseInt($(this).val()));
						}
					});

					var varNoId = "["
					for (var i = 0; i < varNoArray.length; i++) {
						if (i == (varNoArray.length - 1)) {
							varNoId += varNoArray[i] + "]"
						} else {
							varNoId += varNoArray[i] + ","
						}
					}
					variationNum = varNoId;
				});

				$("#diffLeveFilter").on('change', function() {
					diffLevel = [];
					var selected = $(this).find("option:selected");
					selected.each(function() {
						if ($(this).text() == "Select All") {
							for (var i = 1; i <= 5; i++) {
								diffLevel.push(i);
							}
						} else if ($(this).text() == "Select Difficulty Level") {
							diffLevel.push("0")
						} else {
							diffLevel.push(parseInt($(this).val()));
						}
					});

					var difflvlId = "["
					for (var i = 0; i < diffLevel.length; i++) {
						if (i == (diffLevel.length - 1)) {
							difflvlId += diffLevel[i] + "]"
						} else {
							difflvlId += diffLevel[i] + ","
						}
					}
					difficultyLevel = difflvlId;
				});
			}

			var ArrayJson = [];
			var modifyDODMasterJson = {};
			$("#modifyVarBtn").click(function() {
				var childHtml = '<div class="overlay" id="Loading">'
					+ '<div class="overlay__inner">'
					+ '<div class="overlay__content"><span class="spinner"></span><br/><span class="loading">LOADING....</span></div>'
					+ '</div>'
					+ '</div>'

				$("#main-div").append(childHtml);

				var verticalNum = parseInt($("#selTopicForFilter11").val());
				var topicVal = $("#selTopicForFilter1").val();
				var variationVal = $("#selVarNoForFilter").val();
				var currentDifficultyVal = "[" + $("#currentDoD").text() + "]";
				var newDifficultyVal = parseInt($("#diffLeveFilter").val());

				tempJson = {};
				tempJson.newDifficultyVal = newDifficultyVal;
				tempJson.currentDifficultyVal = currentDifficultyVal;
				tempJson.variationVal = variationVal;
				tempJson.topicVal = topicVal;
				tempJson.verticalNum = verticalNum;

				$.ajax({
					type: "POST",
					url: "utility/changeDOD/api/newDOD",
					data: JSON.stringify(tempJson),
					dataType: 'json',
					contentType: 'application/json',
					success: function(data) {
						toastr.success(data.msg);
						UF.getTopicForDoDChange(0);
						$("#Loading").css("display", "none");
						$("#Loading").remove();
					},
					error: function() {
					}
				});
			})
		}
	}

	UF.getTopicToSetConfiguration = function(data) {

		status = "Approved";

		var topicID = 0;
		var variationNum = 0;
		var difficultyLevel = 0;

		// second function code here
		var loader = '<div class="overlay" id="Loading">'
			+ '<div class="overlay__inner">'
			+ '<div class="overlay__content"><span class="spinner"></span><br/><span class="loading">LOADING....</span></div>'
			+ '</div>'
			+ '</div>'

		$("#main-div").html(loader);

		var html1 = '<h1>Topic Test Configuration</h1>'
			+ '<div class="form-group step1 col-sm-12 col-md-12 col-lg-12 col-xl-12" >'
			+ '<label class="col-xl-12 col-md-12 col-sm-12" for="selectTopic">Select Vertical <span class="marathi-text" >(श्रेणी निवडा)</span><i class="fa fa-asterisk " aria-hidden="true"></i></label>'
			+ ' <select class=" form-control col-xl-12 col-md-12 col-sm-12 selectpicker" data-live-search="true"  id="selTopicForFilter11"   >'
		html1 += '' + '  <option value="" id ="0">Select topic</option>'
		for (var i = 0; i < data.topicData.length; i++) {
			html1 += '' + '  <option value="' + data.topicData[i].TNO
				+ '" id ="' + data.topicData[i].TID + '" >'
				+ data.topicData[i].TNO + " - " + data.topicData[i].TN + "&nbsp;(" + data.topicData[i].TN1 + ') </option>'
		}

		html1 += ' </select>'
			+ '<div id="error" class="invalid-feedback selectTopic">Please select topic.</div>'
			+ '</div>'
			+ '<div class="form-group step1" id="variationDiv"></div>'

		$("#main-div").html(html1);
		$('select').selectpicker();

		$("#selTopicForFilter11").on('change', function() {
			var selected = $(this).find("option:selected").val();
			st = status + "_" + selected;
			$.ajax({
				type: "GET",
				url: "questionGroups/api/get/topic/questGroup/" + st,
				dataType: 'json',
				contentType: 'application/json',
				success: function(data) {
					if (data.done == false) {
						$("#main-div").html("<div class='col-xl-12 col-md-12 col-sm-12 alert alert-danger' style='text-align:center;'>No " + status + " questions in the database to display..</div>");
					} else {
						getTopicListDetails(data);
					}
				},
				error: function() {
				}
			});
		})

		function getTopicListDetails(data) {

			var html = '';
			html += '<div class="form-group step1 col-sm-12 col-md-12 col-lg-12 col-xl-12" >'
				+ '<label class="col-xl-12 col-md-12 col-sm-12" for="selectTopic">Select Topic <span class="marathi-text" >(श्रेणी निवडा)</span><i class="fa fa-asterisk " aria-hidden="true"></i></label>'
				+ ' <select class=" form-control col-xl-12 col-md-12 col-sm-12 selectpicker" data-live-search="true"  id="selTopicForFilter1"   >'
			html += '' + '  <option value="" id ="0">Select topic</option>'
			for (var i = 0; i < data.topicData.length; i++) {
				html += '' + '  <option value="' + data.topicData[i].TID
					+ '" id ="' + data.topicData[i].TID + '" >'
					+ data.topicData[i].TNO + " - " + data.topicData[i].TN + "&nbsp;(" + data.topicData[i].TN1 + ') </option>'
			}

			html += ' </select>'
				+ '<div id="error" class="invalid-feedback selectTopic">Please select topic.</div>'
				+ '</div>'
				+ '<center><button id= "ConfigureTestBtn" type="submit" class="btn btn-dark">Configure Test</button></center>'
				+ '<div id="ques-bank-div"></div>'

			$("#variationDiv").html(html);
			$('select').selectpicker();

			$("#ConfigureTestBtn").on("click", function() {

				selected = $("#selTopicForFilter1").find("option:selected").val();
				tpName = $("#selTopicForFilter1").find("option:selected").text();

				var childHtml = '<div class="overlay" id="Loading">'
					+ '<div class="overlay__inner">'
					+ '<div class="overlay__content"><span class="spinner"></span><br/><span class="loading">LOADING....</span></div>'
					+ '</div>'
					+ '</div>'

				$("#main-div").append(childHtml);

				var selectedTopicJSON = {};
				var configureTestJSON = {};
				var standard = {};
				var testType = {};

				var seltopicId = [];

				var testStartDate = new Date();
				var testEndDate = new Date();

				seltopicId.push(selected);

				selectedTopicJSON = seltopicId.join();

				standard.stdId = 1;
				testType.testTypeId = 1;

				configureTestJSON.active = 1;
				configureTestJSON.selectedTopics = selectedTopicJSON;
				configureTestJSON.standard = standard;
				configureTestJSON.testType = testType;

				configureTestJSON.startDate = testStartDate;
				configureTestJSON.endDate = testEndDate;

				$.ajax({
					type: "POST",
					url: com.coep.test.addProblem.baseURL + "configureTestPaper/set/configuration",
					data: JSON.stringify(configureTestJSON),
					dataType: 'json',
					contentType: 'application/json',
					success: function(data) {
						if(data.done == true){
							toastr.success(data.msg);
							UF.getTopicForDoDChange(1);
						}else if(data.done == false){
							toastr.error(data.msg);
							UF.getTopicForDoDChange(1);
						}
						$("#Loading").css("display", "none");
						$("#Loading").remove();
					},
					error: function() {
						$("#Loading").css("display", "none");
						$("#Loading").remove();
					}
				});

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
			})
		}
	}
  
  UF.getTopicForDODCount = function(data){
	status = "Approved";

		var topicID = 0;
		var variationNum = 0;
		var difficultyLevel = 0;

		// second function code here
		var loader = '<div class="overlay" id="Loading">'
			+ '<div class="overlay__inner">'
			+ '<div class="overlay__content"><span class="spinner"></span><br/><span class="loading">LOADING....</span></div>'
			+ '</div>'
			+ '</div>'

		$("#main-div").html(loader);

		var html1 = '<h1>Topic Test Configuration</h1>'
			+ '<div class="form-group step1 col-sm-12 col-md-12 col-lg-12 col-xl-12" >'
			+ '<label class="col-xl-12 col-md-12 col-sm-12" for="selectTopic">Select Vertical <span class="marathi-text" >(श्रेणी निवडा)</span><i class="fa fa-asterisk " aria-hidden="true"></i></label>'
			+ ' <select class=" form-control col-xl-12 col-md-12 col-sm-12 selectpicker" data-live-search="true"  id="selTopicForFilter11"   >'
		html1 += '' + '  <option value="" id ="0">Select topic</option>'
		for (var i = 0; i < data.topicData.length; i++) {
			html1 += '' + '  <option value="' + data.topicData[i].TNO
				+ '" id ="' + data.topicData[i].TID + '" >'
				+ data.topicData[i].TNO + " - " + data.topicData[i].TN + "&nbsp;(" + data.topicData[i].TN1 + ') </option>'
		}

		html1 += ' </select>'
			+ '<div id="error" class="invalid-feedback selectTopic">Please select topic.</div>'
			+ '</div>'
			+ '<div class="form-group step1" id="variationDiv"></div>'

		$("#main-div").html(html1);
		$('select').selectpicker();

		$("#selTopicForFilter11").on('change', function() {
			var selected = $(this).find("option:selected").val();
			st = status + "_" + selected;
			var loader = '<div class="overlay" id="Loading">'
			+ '<div class="overlay__inner">'
			+ '<div class="overlay__content"><span class="spinner"></span><br/><span class="loading">LOADING....</span></div>'
			+ '</div>'
			+ '</div>'

		$("#main-div").append(loader);
			
			$.ajax({
				type: "GET",
				url: "questionGroups/api/get/topic/questGroup/" + st,
				dataType: 'json',
				contentType: 'application/json',
				success: function(data) {
					if (data.done == false) {
						$("#main-div").html("<div class='col-xl-12 col-md-12 col-sm-12 alert alert-danger' style='text-align:center;'>No " + status + " questions in the database to display..</div>");
					} else {
						
						sendDataForDODCount(data);
                      
					}
				},
				error: function() {
				}
			});
		})
		
		sendDataForDODCount = function(data){
			
			jsn = {};
			tid = [];
			tno = [];
			tname = [];
			
			for(i=0; i<data.topicData.length; i++){
				tid.push(data.topicData[i].TID);
				tno.push(data.topicData[i].TNO);
				tname.push(data.topicData[i].TN1);
			}
			
			jsn.tid = tid;
			jsn.tno = tno;
			jsn.tname= tname;
			
			$.ajax({
				type: "POST",
				url: "utility/get/varCount/byDOD",
				data:JSON.stringify(jsn),
				dataType: 'json',
				contentType: 'application/json',
				success: function(data) {
					if (data.done == true) {
						UF.renderVarNoByDOD(data);
					}
//						$("#main-div").html("<div class='col-xl-12 col-md-12 col-sm-12 alert alert-danger' style='text-align:center;'>No " + status + " questions in the database to display..</div>");
//					} else {
//						console.log(data);
//						sendDataForDODCount(data);
                      
//					}
				},
				error: function() {
				}
			});
			
			
		}
		
}

UF.renderVarNoByDOD = function(data){
	
	$("#Loading").css("display", "none");
	$("#Loading").remove();
	
	var totCnt = 0; 
	var date = data.data[0].date;
	var htm11 = '';
  	htm11 += '<div class="table-responsive">'
            +'<table id="varDoDCnt" class="table table-bordered" style = "width: 95%;">'
            +'<thead><tr>'
            +'<th>Topic Id - Topic No</th>'
            +'<th>Topic Name </th>'
            +'<th>DOD1</th>'
            +'<th>DOD1 count</th>'
            +'<th>DOD2</th>'
            +'<th>DOD2 count</th>'
            +'<th>DOD3</th>'
            +'<th>DOD3 count</th>'
            +'<th>DOD4</th>'
            +'<th>DOD4 count</th>'
            +'<th>DOD5</th>'
            +'<th>DOD5 count</th>'
            +'<th>Total count</th>'
            +'</tr></thead><tbody>'
	
	for( i =0 ;i<data.data.length;i++){
		totCnt += data.data[i].varCount; 
		var dod1 = data.data[i].dod1;
		var dod2 = data.data[i].dod2;
		var dod3 = data.data[i].dod3;
		var dod4 = data.data[i].dod4;
		var dod5 = data.data[i].dod5;
		
		dod1 = dod1.toString().split(",").join(", ");
		dod2 = dod2.toString().split(",").join(", ");
		dod3 = dod3.toString().split(",").join(", ");
		dod4 = dod4.toString().split(",").join(", ");
		dod5 = dod5.toString().split(",").join(", ");
	  	
			
		htm11 +=   '<tr>'
		    +'<td>'+data.data[i].tid+' - '+ data.data[i].tno +'</td>'
		    +'<td>'+data.data[i].tnm1+" / "+data.data[i].tnm+'</td>'
		    +'<td>'+dod1+'</td>'
		     +'<td>'+data.data[i].dod1Cnt+'</td>'
		     +'<td>'+dod2+'</td>'
		     +'<td>'+data.data[i].dod2Cnt+'</td>'
		     +'<td>'+dod3+'</td>'
		     +'<td>'+data.data[i].dod3Cnt+'</td>'
		     +'<td>'+dod4+'</td>'
		     +'<td>'+data.data[i].dod4Cnt+'</td>'
		     +'<td>'+dod5+'</td>'
		     +'<td>'+data.data[i].dod5Cnt+'</td>'
		     +'<td>'+data.data[i].varCount+'</td>'
		     +'</tr>'
	}
	 htm11 +='<tr><td>Date : '+date+'</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td>Total Count </td><td>'+ totCnt +'</td></tr>'
	         htm11 +='<tbody></table></div>'
	$("#main-div").html(htm11);
	
	$('#varDoDCnt').DataTable({
		paging: true,
                searching: true,
                ordering: true,
                info: true,
                lengthMenu: [5, 10, 25, 50, 75, 100],
                language: {
                    search: "Filter ",
//                    lengthMenu: "Show _MENU_ entries"
                },
                columnDefs: [
                    { targets: 0, visible: true, searchable: true },
                    { targets: [1, 2], orderable: false }
                ],
                dom: 'lBfrtip',
                buttons: [
                    'copy', 'csv', 'excel', 'pdf', 'print'
                ]
	})
}

})(com.coep.test.ajaxHandler, com.coep.test.addProblem, com.coep.test.userProfile, com.coep.test.utilityFile, com.coep.test.questionBank);