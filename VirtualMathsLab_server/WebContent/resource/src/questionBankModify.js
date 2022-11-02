(function(AH, AP, QB, AM, MJ, QM) {
	
	
	QM.getQuestionsToApproveForModification = function(status, selectedTopicId) {
		
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
			
			QB.getTopicDetailsToGetQuesBank();
			
			if (status == "Approved") {
				

					$.ajax({
						type : "GET",
						url : com.coep.test.addProblem.baseURL
						+ "questionGroups/api/get/topic/questGroups/"+status,
						dataType : 'json',
						contentType : 'application/json',
						success : function(data) {
							
							if (data.done == false) {
								$("#main-div").html("<div class='col-xl-12 col-md-12 col-sm-12 alert alert-danger' style='text-align:center;'>No "+status+" questions in the database to display..</div>");
							}else{
								getTopicListDetailsForModification(data, selectedTopicId);
							}
							
						},
						error : function() {
						}

					});
				
				
			}else{
				$.ajax({
					type : "GET",
					url : com.coep.test.addProblem.baseURL
					+ "questionGroups/api/get/topic/questGroups/"+status,
					dataType : 'json',
					contentType : 'application/json',
					success : function(data) {
						
						if (data.done == false) {
							$("#main-div").html("<div class='col-xl-12 col-md-12 col-sm-12 alert alert-danger' style='text-align:center;'>No "+status+" questions in the database to display..</div>");
						}else{
							getTopicListDetailsForModification(data, selectedTopicId);
						}
						
					},
					error : function() {
					}

				});
			}
			
				function getTopicListDetailsForModification(data, selectedTopicId) {
					
					var name = "";
					var html = '';
					if (status == "Non-Approved") {
						name = "NON-APPROVED"
					}else{
						name = "APPROVED"
					}
					html +=  '<h1>'+ name + ' QUESTION DETAILS</h1>'
					html += '<div class="form-group step1 col-sm-12 col-md-12 col-lg-12 col-xl-12" >'
						+ '<label class="col-xl-12 col-md-12 col-sm-12" for="selectTopic">Select Topic <span class="marathi-text" >(श्रेणी निवडा)</span><i class="fa fa-asterisk " aria-hidden="true"></i></label>'
						+ ' <select class=" form-control col-xl-12 col-md-12 col-sm-12 selectpicker" data-live-search="true"  id="selTopicForFilter"   >'
						html += '' + '  <option value="" id ="0">Select topic</option>'
						for (var i = 0; i <data.topicData.length; i++) {
							html += '' + '  <option value="' + data.topicData[i].TID
									+ '" id ="' + data.topicData[i].TID + '" >'
									+ data.topicData[i].TNO +" - "+ data.topicData[i].TN +"&nbsp;("+ data.topicData[i].TN1 + ') </option>'
						}

					html += ' </select>'
								+ '<div id="error" class="invalid-feedback selectTopic">Please select topic.</div>'
								+ '</div>'		
						
//								+ '<div class="form-group col-sm-12 col-md-12 col-lg-12 col-xl-12">'
//								+ '<input type="text" class="form-control col-xl-12 col-md-12 col-sm-12 hidden" id= "qgrpName" placeholder="Enter Question Group Name" name="qgrpName" required>'
								+ '<div id="varNoDiv" class="row"> </div>'
							
//								+ '</div>'
								
								
								+ '<center><button id= "modifyTopicBtn" type="submit" class="btn btn-dark">Update Time</button></center>'
								
								+ '<div id="ques-bank-div"></div>'
								
					$("#main-div").html(html);
					$('select').selectpicker();
					
//					var selectedTopicId = "";
					
					if(selectedTopicId != undefined ){
						$("#selTopicForFilter").selectpicker('val', selectedTopicId);
						 setTimeout(function(){ 
							 $("#selTopicForFilter").val(selectedTopicId).change();
			  			 }, 500);
						
					}
					
					$("#selTopicForFilter").on('change', function(){ 
						 topicIdArray = [];	
						 var selected = $(this).find("option:selected");
						  selected.each(function(){
							  
							  topicIdArray.push(parseInt($(this).val()));
						    });
//						  console.log(topicIdArray);
						  
						  var topicId = "["
								for (var i = 0; i < topicIdArray.length; i++) {
									if(i == (topicIdArray.length - 1)){
										topicId += topicIdArray[i]+"]"
									}else{
										topicId += topicIdArray[i]+","
									}
								}
						  
//						  console.log(topicId);
						 selectedTopicId = parseInt($(this).val());
//						  alert(selectedTopicId);
						  
						  topicID = topicId;
						  
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
						
						var renderVarHtml = '';
						
						renderVarHtml += '<div class="form-group step1 col-sm-12 col-md-6 col-lg-6 col-xl-6" >'
						+ '<label class="col-xl-12 col-md-12 col-sm-12" for="selectVarNo">Select Variation No </label>'
						renderVarHtml += ' <select class=" form-control col-xl-12 col-md-12 col-sm-12 selectpicker" data-live-search="true"  id="selVarNoForFilter"    >'
							renderVarHtml +='  <option value="" id ="-1">Select Variation Number</option>'
								renderVarHtml +='  <option value="" id ="0">Select All</option>'
									
						for (var i = 0; i < data.data[0].VARNO.length; i++) {
							renderVarHtml += ''
									+ '  <option value="' 
									if (data.data[0].VARNO[i] == "100") {
										renderVarHtml +=  'Not Assigned'
									}else{
										renderVarHtml +=  data.data[0].VARNO[i]
									}
									
							renderVarHtml +=  '" id ="' + data.data[0].VARNO[i] + '" >'
									if (data.data[0].VARNO[i] == "100") {
										renderVarHtml +=  'Not Assigned'
									}else{
										renderVarHtml +=  data.data[0].VARNO[i]
									}
							renderVarHtml += ' </option>'
						}

						renderVarHtml += ' </select>'
								+ '<div id="error" class="invalid-feedback selectTopic">Please select topic.</div>'
								+ '</div>'		 
						
								
								
						renderVarHtml += '<div class="form-group step1 col-sm-12 col-md-6 col-lg-6 col-xl-6" >'
							+ '<label class="col-xl-12 col-md-12 col-sm-12" for="diffLeveFilter">Select Difficulty Level </label>'
							renderVarHtml += ' <select class=" form-control col-xl-12 col-md-12 col-sm-12 selectpicker" data-live-search="true"  id="diffLeveFilter"   multiple >'
								renderVarHtml +='  <option value="" id ="-1">Select Difficulty Level</option>'
									renderVarHtml +='  <option value="" id ="0">Select All</option>'
										
							for (var i = 0; i < 5; i++) {
								renderVarHtml += '' + '  <option value="' + (i+1)
										+ '" id ="' + (i+1)+ '" >Level '
										+ (i+1) + ' </option>'
							}

							renderVarHtml += ' </select>'
									+ '<div id="error" class="invalid-feedback selectTopic">Please select difficulty level.</div>'
									+ '</div>'		
								
							
								
							renderVarHtml +=  '<div class="form-group col-sm-12 col-md-12 col-lg-12 col-xl-12">'
								+ '<label>Enter Time[in Seconds] <span class="marathi-text" >(गणित सोडवण्यासाठी दिलेला वेळ सेकंदात)</span> </label><br/>'
								+ '<input type="text" id="defTime" maxlength="4"  class="form-control col-xl-12 col-md-12 col-sm-12"  placeholder="Enter Time[in Seconds]"  required><i class="fa fa-asterisk asteriskForInput" aria-hidden="true"></i>'
								+ '<div id="errmsg" class="red-color"></div>'
								+ '</div>'
								
								
								$("#varNoDiv").html(renderVarHtml);
								$('select').selectpicker();
								
								$("#selVarNoForFilter").on('change', function(){ 
										 varNoArray = [];	
										 var selected = $(this).find("option:selected");
										  selected.each(function(){
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
//										  console.log("varNo ="+ varNoArray);
										  
										  var varNoId = "["
												for (var i = 0; i < varNoArray.length; i++) {
													if(i == (varNoArray.length - 1)){
														varNoId += varNoArray[i]+"]"
													}else{
														varNoId += varNoArray[i]+","
													}
												}
										  
//										  console.log(varNoId);
										  variationNum = varNoId;
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
					
					$("#modifyTopicBtn").on(
							"click",
							function(e) {
								
								
								
								var loader = '<div class="overlay" id="Loading">'
									 + '<div class="overlay__inner">'
									 + '<div class="overlay__content"><span class="spinner"></span><br/><span class="loading">LOADING....</span></div>'
									 + '</div>'
									 + '</div>'
									 
									 $("#ques-bank-div").html(loader);
								
								
									if (variationNum == 0) {
										variationNum = "[0]";
									}
									if (difficultyLevel == 0) {
										difficultyLevel = "[0]";
									}
									
									var getTime =  $("#defTime").val();
									var time = "["+getTime+"]";
									
									console.log(""+topicID+"/"+variationNum+"/"+difficultyLevel+"/"+time);
									
									if (time == "[undefined]" && variationNum == "[0]") {
										
									}
									
									if (topicID == 0) {
										alert("Please select topic");
										 $("#Loading").css("display","none");
									} if (time == "[]" && variationNum == "[0]") {
//										alert("Please select variation No and enter time");
										 $("#Loading").css("display","none");
										 $("#ques-bank-div").html("<br><br><div class='col-xl-12 col-md-12 col-sm-12 alert alert-danger' style='text-align:center;'>Please select variation Number and enter time(in seconds)...</div>");
									}else{
										
									
										$.ajax({
											type : "POST",
											url : com.coep.test.addProblem.baseURL
													+ "questionGroups/api/getData/"+status+"/"+topicID+"/"+variationNum+"/"+difficultyLevel+"/"+time,
											dataType : 'json',
											contentType : 'application/json',
											success : function(data) {
												if(data.done == true){
													if (status == "Non-Approved") {
														modifyQuw = true;
														showToast.show(data.msg, true);
//														QB.renderNonApprovedQuestionGroup(data.data, quesID, pageIndexNo, selectedTopicId);
														 $("#Loading").css("display","none");
														 $("#ques-bank-div").html("<br><br><div class='col-xl-12 col-md-12 col-sm-12 alert alert-success' style='text-align:center;'> "+data.msg+"..</div>");
													}else{
														modifyQuw = true;
														showToast.show(data.msg, true);
//														QB.renderApprovedQuestionGroup(data.data, quesID, pageIndexNo, selectedTopicId);
														 $("#Loading").css("display","none");
														 $("#ques-bank-div").html("<br><br><div class='col-xl-12 col-md-12 col-sm-12 alert alert-success' style='text-align:center;'> "+data.msg+"..</div>");
													}
													
												}else{
													$("#ques-bank-div").html("<div class='col-xl-12 col-md-12 col-sm-12 alert alert-danger' style='text-align:center;'>No "+status+" questions in the database to display..</div>");
												}
											},
											error : function() {
											}
										});
									}
										
										
									e.preventDefault();
//								}

							}); // submit btn ends here
					
				}
				
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
})(com.coep.test.ajaxHandler, com.coep.test.addProblem,
		com.coep.test.questionBank, com.coep.test.AlertMessage, com.coep.test.mathJax, com.coep.test.questionBankModification);
