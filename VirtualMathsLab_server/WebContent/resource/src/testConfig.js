(function(AH, AP, TC) {
	
	TC.testConfiguration = function() {
		
		$("#main-div").html("");	
		
		$.ajax({
			type : "GET",
			url : com.coep.test.addProblem.baseURL
					+ "topic/api/get/topics",
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
//				topicData = data.topicData;
				console.log(data);
				TC.renderTopicsForTestConfiguration(data);
			},
			error : function() {
			}

		});
		
	}
	
	
	
	TC.renderTopicsForTestConfiguration = function(data) {
		
		var topicID = 0;
		var variationNum = 0;
		var variationNumOnly = 0;
		var difficultyLevel = 0;
		
		var html = '';
		
		html +=  '<h1> Test Configuration</h1>'
		html += '<div class="form-group step1 col-sm-12 col-md-12 col-lg-12 col-xl-12" >'
			
//			+ '<label class="col-xl-12 col-md-12 col-sm-12" for="selectTopic">Create Test <span class="marathi-text" ></span><i class="fa fa-asterisk " aria-hidden="true"></i></label>'
//			+ ' <select class=" form-control col-xl-12 col-md-12 col-sm-12 selectpicker" data-live-search="true"  id="selCreateTest"   >'
//			html += '' + '  <option value="" id ="0">Select type</option>'
////			for (var i = 0; i <data.topicData.length; i++) {
//				html += '' + '  <option value="Automatic" id ="1">Automatic</option>'
//				+ '  <option value="Manually" id ="2">Manually </option>'
////			}
//
//		html += ' </select>'
//				+ '<div id="error" class="invalid-feedback selectTestCreate">Please select type.</div>'
//				+ '</div>'		
			
//			html += '<label class="col-xl-12 col-md-12 col-sm-12" for="testName">Enter Test <span class="marathi-text" ></span><i class="fa fa-asterisk " aria-hidden="true"></i></label>'
			html +='<input type="text" class="form-control col-xl-12 col-md-12 col-sm-12 " id= "testName" placeholder="Enter Test Name"  >'
			+ '<div class="invalid-feedback testName">Please Enter Test Name.</div>'
		
			
			+ '<div id="selTopic" style="display:show;" >'		
			+ '<label class="col-xl-12 col-md-12 col-sm-12" for="selectTopic">Select Topic <span class="marathi-text" >(श्रेणी निवडा)</span><i class="fa fa-asterisk " aria-hidden="true"></i></label>'
			+ ' <select class=" form-control col-xl-12 col-md-12 col-sm-12 selectpicker" data-live-search="true"  id="selTopicForFilter"  multiple >' // 
			html += '' + '  <option value="" id ="0">Select topic</option>'
			for (var i = 0; i <data.topicData.length; i++) {
				html += '' + '  <option value="' + data.topicData[i].TID
						+ '" id ="' + data.topicData[i].TID + '" >'
						+ data.topicData[i].TNO +" - "+ data.topicData[i].TN +"&nbsp;("+ data.topicData[i].TN1 + ') </option>'
			}

		html += ' </select>'
					+ '<div id="error" class="invalid-feedback selectTopic">Please select topic.</div>'
					+ '</div>'		
					+ ' </div>'
					
					+ '<div id="varNoDiv" class="row"> </div>'		
				
					+ '<center><button id="testConfigButton" type="submit" class="btn btn-dark">Create Test</button></center>'
					
					
		$("#main-div").html(html);	
		$('select').selectpicker();
		
		
//		$("#selCreateTest").on('change', function(){ 
//			
//			 var selectedTestType =$(this).find("option:selected").attr('id');
////			alert(selectedTestType);
//			if (selectedTestType == "0") {
//				alert("Please select test type");
//				$("#selTopic").hide();
//			}else if (selectedTestType == "1") {
//				$("#selTopic").show(); 
//			}else if (selectedTestType == "2") {
//				$("#selTopic").show();
//			}
//		});
		
		$("#selTopicForFilter").on('change', function(){ 
			 topicIdArray = [];	
			 var selected = $(this).find("option:selected");
			  selected.each(function(){
				  
				  topicIdArray.push(parseInt($(this).val()));
			    });
//			  console.log(topicIdArray);
			  
			  var topicId = "["
					for (var i = 0; i < topicIdArray.length; i++) {
						if(i == (topicIdArray.length - 1)){
							topicId += topicIdArray[i]+"]"
						}else{
							topicId += topicIdArray[i]+","
						}
					}
			  
			  var topicIdNEW = ""
					for (var i = 0; i < topicIdArray.length; i++) {
						if(i == (topicIdArray.length - 1)){
							topicIdNEW += topicIdArray[i]
						}else{
							topicIdNEW += topicIdArray[i]+","
						}
					}
			  console.log(topicIdNEW);
			 selectedTopicId = parseInt($(this).val());
			  
//			  topicID = topicId;
			 topicID = topicIdNEW;
			  status = "Approved";
				$.ajax({
					type : "POST",
					url : com.coep.test.addProblem.baseURL
					+ "topic/api/mapping/varNo/"+status+"/"+topicId,
					dataType : 'json',
					contentType : 'application/json',
					success : function(data) {
						
						if (data.done == true) {
							renderVariationNo(data);
						}else{
								alert(data.msg);
							}
						
					},
					error : function() {
					}
				});
		});
		
		
		
		
		function renderVariationNo(data) {
			console.log(data);
			var renderVarHtml = '';
			
			renderVarHtml += '<div class="form-group step1 col-sm-12 col-md-6 col-lg-6 col-xl-6" >'
			+ '<label class="col-xl-12 col-md-12 col-sm-12" for="selectVarNo">Select Variation No </label>'
			renderVarHtml += ' <select class=" form-control col-xl-12 col-md-12 col-sm-12 selectpicker" data-live-search="true"  id="selVarNoForFilter" multiple >'
				renderVarHtml +='  <option value="" id ="-1">Select Variation Number</option>'
//					renderVarHtml +='  <option value="" id ="0">Select All</option>'
						
			for (var i = 0; i < data.data.length; i++) {				
				for (var j = 0; j < data.data[i].VARNO.length; j++) {
					renderVarHtml += ''
						+ '  <option value="' 
						if (data.data[i].VARNO[j] == "100") {
							renderVarHtml +=  'Not Assigned'
						}else{
							renderVarHtml +=  data.data[i].VARNO[j] +" - "+ data.data[i].TopicNo
						}
				
					renderVarHtml +=  '" id ="' + data.data[i].VARNO[j] + '" >'
						if (data.data[i].VARNO[j] == "100") {
							renderVarHtml +=  'Not Assigned'
						}else{
							renderVarHtml += data.data[i].VARNO[j]+" - "+ data.data[i].TopicNo 
						}
					renderVarHtml += ' </option>'
				}
			}
			
			renderVarHtml += ' </select>'
					+ '<div id="error" class="invalid-feedback selectTopic">Please select topic.</div>'
					+ '</div>'		 
			
//			renderVarHtml += '<div class="form-group step1 col-sm-12 col-md-6 col-lg-6 col-xl-6" >'
//				+ '<label class="col-xl-12 col-md-12 col-sm-12" for="diffLeveFilter">Select Difficulty Level </label>'
//				renderVarHtml += ' <select class=" form-control col-xl-12 col-md-12 col-sm-12 selectpicker" data-live-search="true"  id="diffLeveFilter" multiple >' // 
//					renderVarHtml +='  <option value="" id ="-1">Select Difficulty Level</option>'
//						renderVarHtml +='  <option value="" id ="0">Select All</option>'
//							
//				for (var i = 0; i < 5; i++) {
//					renderVarHtml += '' + '  <option value="' + (i+1)
//							+ '" id ="' + (i+1)+ '" >Level '
//							+ (i+1) + ' </option>'
//				}
//
//				renderVarHtml += ' </select>'
//						+ '<div id="error" class="invalid-feedback selectTopic">Please select difficulty level.</div>'
//						+ '</div>'		
					
					$("#varNoDiv").html(renderVarHtml);
					$('select').selectpicker();
					
					
					$("#selVarNoForFilter").on('change', function(){ 
							 varNoArray = [];	
							 varNoArrayOnly = [];	
							 var selected = $(this).find("option:selected");
							  selected.each(function(){
								  if ($(this).text() == "Select All") {
										  for (var i = 0; i < data.data[0].VARNO.length; i++) {
											  for (var j = 0; j < data.data[i].VARNO.length; j++) {
												  varNoArray.push(data.data[i].TopicNo+"-"+data.data[i].VARNO[j]);
											  }
										}
									} else if ($(this).text() == "Select Variation Number") {
										varNoArray.push("0");
									} else if ($(this).text() == "Not Assigned ") {
										varNoArray.push("100");
									} else {
										console.log($.trim($(this).text()));
//										varNoArray.push(parseInt($(this).val()));
										varNoArray.push($.trim($(this).text()));
										varNoArrayOnly.push(parseInt($(this).val()));
									}
								 
							    });
							  
							  var varNoId = "";
									for (var i = 0; i < varNoArrayOnly.length; i++) {
										if(i == (varNoArrayOnly.length - 1)){
											varNoId += varNoArrayOnly[i]
										}else{
											varNoId += varNoArrayOnly[i]+","
										}
									}
							  
							   varNoIdNEW = ""
									for (var i = 0; i < varNoArray.length; i++) {
										if(i == (varNoArray.length - 1)){
											varNoIdNEW += varNoArray[i]
										}else{
											varNoIdNEW += varNoArray[i]+","
										}
									}
							  
//							  console.log(varNoId);
							  variationNumOnly = varNoId;
							  variationNum = varNoIdNEW
						});
					
					$("#diffLeveFilter").on('change', function(){ 
							 diffLevel = [];	
							 var selected = $(this).find("option:selected");
							  selected.each(function(){
								  if ($(this).text() == "Select All") {
									  for (var i = 1; i <= 5; i++) {
										  diffLevel.push(i);	
										}
								  }else if($(this).text() == "Select Difficulty Level"){
									  diffLevel.push("0")
								  }else{
									  diffLevel.push(parseInt($(this).val()));
								  }
								 
							    });
							  
							  var difflvlId = "["
									for (var i = 0; i < diffLevel.length; i++) {
										if(i == (diffLevel.length - 1)){
											difflvlId += diffLevel[i]+"]"
										}else{
											difflvlId += diffLevel[i]+","
										}
									}
							  
							  difficultyLevel = difflvlId;
					});
					
			}
		
		
		$("#testConfigButton").on(
				"click",
				function(e) {
					
					var loader = '<div class="overlay" id="Loading">'
						 + '<div class="overlay__inner">'
						 + '<div class="overlay__content"><span class="spinner"></span><br/><span class="loading">LOADING....</span></div>'
						 + '</div>'
						 + '</div>'
						 
						 $("#ques-bank-div").html(loader);
					
						var tName = $("#testName").val();
					
						if (variationNum == 0) {
							variationNum = "[0]";
						}
						if (difficultyLevel == 0) {
							difficultyLevel = "[0]";
						}
					
						console.log(""+topicID+"/"+variationNum+"/"+difficultyLevel);
						
						if (topicID == 0) {
							alert("Please select topic");
							 $("#Loading").css("display","none");
						} if (variationNum == "[0]") {
//							alert("Please select variation No and enter time");
							 $("#Loading").css("display","none");
							 $("#ques-bank-div").html("<br><br><div class='col-xl-12 col-md-12 col-sm-12 alert alert-danger' style='text-align:center;'>Please select variation Number and enter time(in seconds)...</div>");
						}else{
							
							var selectedTopicJSON = {};
							var configureTestJSON = {};
							var standard  = {}; 
							var testType = {}; 
							
							var seltopicId = [];
							var selVarNo  = [];
							
							var testStartDate = new Date();
							var testEndDate = new Date();
							
							seltopicId.push(topicID);
							selVarNo.push(variationNum);
//							seltopicId = topicID;
							
//							selectedTopicJSON = seltopicId;
//							seltopicId.push(topicId);
							
							selectedTopicJSON = seltopicId.join();
							selectedVarNoJSON = selVarNo.join();
							
							standard.stdId = 1;
							testType.testTypeId = 5;
							
//							configureTestJSON.name = topicName;
							configureTestJSON.name = tName;
//							configureTestJSON.name = "Test";
							configureTestJSON.active = 1;
							configureTestJSON.selectedTopics = selectedTopicJSON;
							configureTestJSON.varNo = selectedVarNoJSON;
							configureTestJSON.standard = standard;
							configureTestJSON.testType = testType;
							
							configureTestJSON.startDate = testStartDate;
							configureTestJSON.endDate = testEndDate;
							
//							console.log(JSON.stringify(configureTestJSON));
//							console.log(configureTestJSON);
							
							$.ajax({
								type : "POST",
								url : com.coep.test.addProblem.baseURL + "configureTestPaper",
								data : JSON.stringify(configureTestJSON),
								dataType : 'json',
								contentType : 'application/json',
								success : function(data) {

									if (data.done == true) {
										console.log(data);
										TC.renderSetTestPaper(data, configureTestJSON, seltopicId, variationNumOnly );
//										AM.confirmationToStartTest(topicId, topicName, data, homePageFlag);
//										$('#AlertMesConfirm-'+topicId).modal();
										
										
									} else {
//										console.log(data);
//										$("#lastTopicMsg").html(data.msg);
//										$("#moveToNextTest").hide();
//										$("#homePage").show();
////										goToNextTopic.show();
//										toastr.success(data.msg);
										
										$("#Loading").css("display","none");
										$("#Loading").remove();
									}
									$("#Loading").css("display","none");
									$("#Loading").remove();
								},
								error : function() {
									$("#Loading").css("display","none");
									$("#Loading").remove();
								}

							});
						}
							
							
						e.preventDefault();
//					}

				}); // submit btn ends here
		
		
		// test configuration render start
		
		TC.renderSetTestPaper = function(data, configureTestJSON, seltopicId, selVarNo ) {
			
			document.title = "Set Test Paper";
			var testPaperHtm = '';
//			var testPaperHtm = '<div>';
//
//			for (var i = 0; i < data.data.length; i++) {
//				
//				testPaperHtm += '<div id="topic-'+data.data[i].TID+'" style="border-style: solid;">' // div start
//				+'<div>'+data.data[i].TNO +' - '+data.data[i].TNAME+'</div> <br>'
//				if (data.data[i].VARARR.length == 0) {
//					testPaperHtm += '<div>Variation number not selected for this Topic</div>'
//				}else{
//				for (var j = 0; j < data.data[i].VARARR.length; j++) {
//						testPaperHtm += '<div id="var-'+data.data[i].VARARR[j]+'" >'+data.data[i].VARARR[j] +'('+data.data[i].VARNO[j][data.data[i].VARARR[data.data[i].VARARR.indexOf(data.data[i].VARARR[j])]] + ') <input type="text" class="form-control col-xl-6 col-md-6 col-sm-6" id= "varNo-'+data.data[i].VARARR[j]+'" placeholder="Enter no of questions"  >'+' </div>'
//					}
//				}
//				
//				testPaperHtm += '<center><button id="saveTestConfig-'+data.data[i].TID+'" type="submit" class="btn btn-dark" onClick="saveTestConfig('+data.data[i].TID+',this.id,'+i+')">Save</button></center>'
//				testPaperHtm +=	'</div><br><br>'  // div end
//				
//			}
//			
//			testPaperHtm += '</div>'
				
			testPaperHtm +='<div class="card" >'
			for (var i = 0; i < data.data.length; i++) {
				
				testPaperHtm+='<div class="container-fluid" id="topic-'+data.data[i].TID+'">'
				+'<div class="row">'
				
				+'<div class="col-lg-1" id="colstyle">'
				+'<a class="btn" data-toggle="collapse" href="#varNo-'+data.data[i].TID+'" id="firstshow" role="button" aria-expanded="false" aria-controls="collapseExample">'
				+'<img src="resource/images/plus.png">'
				+'</a>'
				+'</div>'
				+'<div class="col-lg-11"  id="colstyle">'
				+'<center data-toggle="collapse" href="#varNo-'+data.data[i].TID+'" id="firstshow" role="button" aria-expanded="false" aria-controls="collapseExample" ><b>'+data.data[i].TNO +' - '+data.data[i].TNAME+'</b></center>'
				+'</div>'
				testPaperHtm +=  '</div>' //row end
				
				
				if (data.data[i].VARARR.length == 0) {
					testPaperHtm += '<div>Variation number not selected for this Topic</div>'
				}else{
					testPaperHtm +='<div class="container-fluid ">'
					+'<div class="collapse" id="varNo-'+data.data[i].TID+'">'	
				for (var j = 0; j < data.data[i].VARARR.length; j++) {
//						testPaperHtm += '<div id="var-'+data.data[i].VARARR[j]+'" >'+data.data[i].VARARR[j] +'('+data.data[i].VARNO[j][data.data[i].VARARR[data.data[i].VARARR.indexOf(data.data[i].VARARR[j])]] + ')'
//						+'<input type="text" class="form-control col-xl-6 col-md-6 col-sm-6" id= "varNo-'+data.data[i].VARARR[j]+'" placeholder="Enter no of questions"  >'+' </div>'
//				testPaperHtm += '<div>'	
				testPaperHtm +='<div class="container-fluid ">'
						+'<div class="collapse" id="varNo-'+data.data[i].TID+'">'
						+'<div class="card card-body  " id="firstsavehide" >'
						+'<div class="container" >'
						
						+'<label for="varNo-'+data.data[i].VARARR[j]+'" class="mb-2 mr-sm-2">'+data.data[i].VARARR[j] +'('+data.data[i].VARNO[j][data.data[i].VARARR[data.data[i].VARARR.indexOf(data.data[i].VARARR[j])]] + ')</label>'
						+'<input type="number" class="form-control mb-2 mr-sm-2" id="varNo-'+data.data[i].VARARR[j]+'" placeholder="Enter no of questions" >'
						
						+'</div>'
						+'</div>'
						+'</div>'
						+'</div>'
					}
				
				testPaperHtm +='<center><button id="saveTestConfig-'+data.data[i].TID+'" type="submit" class="btn btn-dark"  data-toggle="collapse" data-target="#varNo-'+data.data[i].TID+'" onClick="saveTestConfig('+data.data[i].TID+',this.name,'+i+')">SAVE</button></center>'
					+'</div>'
					+'</div>'
				}
				
				testPaperHtm+='</div>' //container-fluid end
					
			}
			
			testPaperHtm+='</div>' //card end
				
			$("#main-div").html('');
			$("#main-div").html(testPaperHtm);
			
			
			
			saveTestConfig = function(topicId, id, index) {
				console.log(id);
				var TestPaperJSONArray = [];
				
				for (var i = 0; i < data.data[index].VARARR.length; i++) {
					
					noOfQues =  $('#varNo-'+data.data[index].VARARR[i]).val();
					console.log(noOfQues);
					var TestPaperJSON = {

							"noOfQuestionGroup" : noOfQues,
							"topicTimeLimit" : 10,
							"varNo" : data.data[index].VARARR[i],
							"topicVO" : {
								"topicId" : topicId
							},
							"standardVO" : {
								"stdId" : 1
							},
							"complexityLevel" : {
								"qgComplexityLevelId" : 1
							},
							"testVO" : {
								"testId" : data.TID
							}
						};
					TestPaperJSONArray.push(TestPaperJSON);
				}
				
				console.log(TestPaperJSONArray);

				$.ajax({
					type : "POST",
					url : com.coep.test.addProblem.baseURL + "setTestPaper",
					data : JSON.stringify(TestPaperJSONArray),
					dataType : 'json',
					contentType : 'application/json',
					success : function(savedTCData) {

						if (savedTCData.done == true) {
							console.log(savedTCData);
							toastr.success(data.msg);
//							$('#'+id).toggle();
//							TC.renderSavedSetTestPaper(data, TestPaperJSONArray,
//									savedTCData, deptId, sectionObj);
						} else {

						}
					},
					error : function() {
					}

				});

			}

		};
		
		// testconfig render end
		
	}
	
	
	TC.testLinkCreation = function() {
		$("#Loading").css("display","none");
		$("#Loading").remove();
		$("#main-div").html("");	
		

		$.ajax({
			type : "GET",
			url : "getAllTest/",
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == false) {

				} else {
					if (data.length != 0) {
						console.log(data);
						TC.renderShowTest(data.data);
					} else {
						showToast.show(
								'Test is not available.',
								false);
					}
				}
			},
			error : function() {

			}
		});
	
	}
	
	
	TC.renderShowTest = function(data) {
		var renderShowTestHtm = '';
		
		renderShowTestHtm += '<div><h1 class="text-align-center darkbrown" style="font-weight : bold;">Available Test</h1>'
			+'<table class="table" style="border:2px solid #026167; overflow-y:auto;" ><thead><tr>'
			+'<th style="">Sr. No. </th>'
			+'<th>Test Name</th>'
			+'<th>TOPIC</th>'
			+'<th>Variation No</th>'
			+'<th>Create Test Link</th>'
			+'</thead></tr>'
			
		for (var k = 0; k < data.length; k++){
			
				renderShowTestHtm += '<tr><td>'+(k+1)+'.</td><td>'+data[k].TN+'</td>'
				var topics = "";
					for (var i = 0; i < data[k].TP.length; i++) {
						topics += data[k].TP[i]+'<br>';
					}
					renderShowTestHtm += '<td>'+topics +'</td>'		
					renderShowTestHtm += '<td>'+data[k].VARNO +'</td>'	
					renderShowTestHtm += '<td><button id="testLinkCreateBtn" type="submit" class="btn btn-dark" onClick="createTestLink('+data[k].ID+')">Create Test Link</button></td>'	
					renderShowTestHtm += '</tr>';
					renderShowTestHtm += '<tr id="testLink-'+data[k].ID+'"></tr>';
				}
				renderShowTestHtm+= '</tbody></table></div>';
				
				$("#main-div").html(renderShowTestHtm);
				
				createTestLink = function(testId) {
					
					$.ajax({
						type : "GET",
						url : com.coep.test.addProblem.baseURL+"/api/create/test/other",		
						data : "testId=" + testId,
						dataType : 'json',
						contentType : 'application/json',
						success : function(data) {
							if(data.done == false){
								showToast.show(data.msg,false);
							}else{
//								com.coep.test.modifyStudentTest.renderTestLevel(data, userId,emailId);
								console.log(data);
								$("#testLink-"+testId+"").html('<a href="'+data.testURL+'" taget="_blank">Click here to open testlink</a>');
							}
						},
						error : function() {
						}

					});
				}
				
				
				
				
				
				
				
				
	}

})(com.coep.test.ajaxHandler, com.coep.test.addProblem,
		com.coep.test.testConfig);