(function(AH, AP, AM, MJ) {

//	AP.baseURL = "http://192.168.1.35:8080/VirtualMathsLab/";
	AP.baseURL = "https://portal.coepvlab.ac.in/VirtualMathsLab/"
//	AP.baseURL = "http://192.168.1.63:8080/VirtualMathsLab/";
//	AP.baseURL = "http://ec2-13-233-206-54.ap-south-1.compute.amazonaws.com:8080/VirtualMathsLab/";
	
	
	// add question group starts 
	AP.addQuestionGroup = function(data) {
		
		document.title = "Add Question Group";
		
		$("#main-div").html("<div id='addQuestionGroup'></div><div id='addNewQG' ><button id='addNewQGBtn' class='btn btn-dark float-right' >Add New Question</button></div>");
		
		var qgHtm = ''
			qgHtm += '<div class="container-fluid">'
					+ '<div class="row">'
					+ '<div id="addQuestion_content" class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'
					+ '<section class="section-preview">'
					
					+ '<div id="QuestionGroupDataForm" >'  // form
					
					+ '<h1>ADD QUESTION GROUP</h1>'
					
					+ '<div class="row">'
					+ '<div class="form-group col-sm-12 col-md-6 col-lg-6 col-xl-6">'
					+ '<input type="text" class="form-control col-xl-12 col-md-12 col-sm-12 hidden" id= "qgrpName" placeholder="Enter Question Group Name"  >'
					+ '<div class="invalid-feedback qgrpName">Please enter question group name.</div>'
					+ '</div>'

					// select subject					
					+ '<div class="form-group col-sm-12 col-md-6 col-lg-6 col-xl-6" >'
					+ ' <select class=" form-control col-xl-12 col-md-12 col-sm-12 selectpicker" data-live-search="true"  id="selectSubject" required disabled>'
					+ ' <option value=""  selected>Choose Subject</option>'

					for (var i = 0; i < data.subData.length; i++) {
						qgHtm +=  '  <option value="' + data.subData[i].SID
								+ '" id ="' + data.subData[i].SID + '" selected>'
								+ data.subData[i].SN + ' </option>'
					}
					qgHtm += ' </select>'
							+ '<div id="error" class="invalid-feedback selectSubject">Please select subject.</div>'
							+ '</div>'

							+ '<div class="form-group step1 col-sm-12 col-md-12 col-lg-12 col-xl-12" "topicSelect">'
							+ '<label class="col-xl-12 col-md-12 col-sm-12" for="selectTopic">Select Topic <span class="marathi-text" >(श्रेणी निवडा)</span><i class="fa fa-asterisk " aria-hidden="true"></i></label>'
							+ ' <select class=" form-control col-xl-12 col-md-12 col-sm-12 selectpicker" data-live-search="true"  id="selectTopic"  onchange=\"com.coep.test.addProblem.onSelectTopic()\"" multiple required >'
//							+ ' <option value=""  >Select Topic</option>'

					for (var i = 0; i < data.topicData.length; i++) {
						qgHtm += '' + '  <option value="' + data.topicData[i].TID
								+ '" id ="' + data.topicData[i].TID + '" >' 
								+ data.topicData[i].TNO +" - "+ data.topicData[i].TN +"&nbsp;("+ data.topicData[i].TN1 + ') </option>'
					}

					qgHtm += ' </select>'
							+ '<div id="error" class="invalid-feedback selectTopic">Please select topic.</div>'
							+ '</div>'		
							
		
					
					+ '<div class="form-group step7 col-sm-12 col-md-12 col-lg-12 col-xl-12" id= "selectQuesType" >'
					
					+ '</div>'
					+'</div>'	// row close	
					
          				+'<div class="row">'
					+ '<div class="form-group step7a col-sm-12 col-md-12 col-lg-12 col-xl-12" id= "nonMediaQuesDiv">'
					
					
					+ '</div>'
					
					
					+ '<div class="form-group step7b col-sm-12 col-md-12 col-lg-12 col-xl-12" id= "mediaQuesDiv">'
					
					+ '</div>'
					
					+ '<div class="form-group step8 col-sm-12 col-md-12 col-lg-12 col-xl-12" id= "selectDiffLevel">'
					
					+ '</div>'
					
					+ '<div class="form-group step10 col-sm-12 col-md-12 col-lg-12 col-xl-12 hidden" id= "variationNo" >'
					+'<label class="col-xl-12 col-md-12 col-sm-12" for="varNo">Enter Variation Number<span class="marathi-text" ></span> </label>'
					+ '<input type="text" maxlength="3" class="form-control col-xl-12 col-md-12 col-sm-12 " id= "varNo" placeholder="Enter Variation Number" name="varNo" />'
					+ '<div id="error" class="invalid-feedback varNoerrmsg">Please enter question variation number.</div>'
					+ '</div>'
					+'</div>'	// row close		 
				 
					+ '<button id= "qgroupBtn" type="submit" class="btn btn-dark step9"  >Save Question Group</button>'
					
					+ '</div>'  // form
					+ '</section>' 
					
					+ '</div>' // addQuestion_content close
					+ '</div>'// main row close
					+ '</div>' // container close
		
		
		$("#addQuestionGroup").html(qgHtm);
		$('select').selectpicker();
//		$('#topicSelect').change(bsSelectValidation);
		$(".step9").hide();
		
		
		
		$("#selectTopic").on('change', function(){ 
			 topicIdArr = [];	
			 var selected = $(this).find("option:selected");
			 
			  selected.each(function(){
				  topicIdArr.push(parseInt($(this).val()));
			    });
		});
		
		
		$("#addNewQGBtn").on("click", function(e){
			AP.getLevelDetailsToAddQuestionGroup();
		});
		
		
		$("#varNo").keypress(
				function(e) {
					// if the letter is not digit then display error and
					// don't type anything
					if (e.which != 8 && e.which != 0
							&& (e.which < 48 || e.which > 57)) {
						// display error message
						$("#varNoerrmsg").html("Digits Only").show().fadeOut(
								"slow");
						return false;
					}
				});
		
		
		$("#qgroupBtn").on("click", function(e){

			if (saveQuestionGroupValidations()) {
				
			
				var quesGrpName = $('#qgrpName').val();
				
//				var topicId = $("#selectTopic option:selected").val();
				topicArr = [];
				for (var i = 0; i < topicIdArr.length; i++) {
					var topicId = topicIdArr[i];
					var topic = {};
					topic.topicId = topicId;
					topicArr.push(topic);
				}
				
			
				var quesType = $('#quesType option:selected').attr('id');
				var mediaTypeId = quesType;
				var content = '';
				var type = $('#quesType option:selected').text();
				
				if (quesType == 1) {
					content = $('#quesGroupTxtNoFile').val();
					quesGroupMediaLinks.mediaURLText = content;
				}else{
					quesGroupMediaLinks.mediaURLText = $("#queslink").attr("qg-media");
				}

			
				quesGroupMediaLinks.quesUsage = "question";
				
				var level = $('#diffLevel option:selected').val(); // difficulty level
				var subjectId = $('#selectSubject option:selected').val();
				
				var varNo =  $('#varNo').val();
				
				var createdBy = data.userId;
				
				mediaType.mediaTypeId = mediaTypeId;
				subject.subjectId = subjectId;

				questionGruopJSON.name = quesGrpName;
				
				questionGruopJSON.topic = topicArr;
				questionGruopJSON.quesGroupMediaLinks = quesGroupMediaLinks;
				questionGruopJSON.type = type;
				
				questionGruopJSON.subject = subject;
				questionGruopJSON.mediaType = mediaType;
				questionGruopJSON.level = level;
				
				questionGruopJSON.createdBy = createdBy;
				
				questionGruopJSON.varNo = varNo; 
				
				console.log(JSON.stringify(questionGruopJSON));
				console.log(questionGruopJSON);
				AP.addToQuestionGroup(AP.baseURL + "questionGroups", "POST",
						questionGruopJSON, AP.mainData);
				
			}

		});


	} // addQuestionGroup ends here
	
	
	
	/// modify question group starts here
	
	AP.modifyQuestionGroup = function(QGID, questionGruopJSON, data, backStatus, quesID , pageIndex) {
		
		console.log(pageIndex);
		AP.backBtnStatus = backStatus;
		AP.pageIndexNo = pageIndex;
		
		if(quesID != undefined){
			AP.backBtnQuesId = quesID;
		}
		
		
		questionGruopJSON.questionGroupId = QGID;
		
		$("#main-div").html("");
		$("#main-div").html("<div id='backFunction' style='margin-bottom: 5px;'><button class='btn btn-dark' onclick='com.coep.test.addProblem.goBackBtn();'><i class='fa fa-backward' aria-hidden='true'></i> &nbsp;Back</button></div><div id='addQuestionGroup' ></div><button id='addNewQGBtn' class='btn btn-dark float-right' >Add New Question</button></div>");
		
		var qgHtm = ''
			qgHtm += '<div class="container-fluid">'
					+ '<div class="row">'
					+ '<div id="addQuestion_content" class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'
				
					
					+ '<div id="QuestionGroupDataForm" novalidate>'
					+ '<div class="row">'
					+ '<section class="section-preview" id="modifiedQuesgroup">'
					 + '<span id="saveQuesGroupErr" class=" hidden l30-r0-padding red-color">*All fields are manadatory.<br>Please fill all fields on this questionGroup.</span>'
					+ '<h1>ADD QUESTION GROUP</h1>'
					
					+ '<div class="row">'
					+ '<div class="form-group col-sm-12 col-md-12 col-lg-12 col-xl-12">'
					+ '<input type="text" class="form-control col-xl-12 col-md-12 col-sm-12 hidden" id= "qgrpName" placeholder="Enter Question Group Name" name="qgrpName" required>'
					+ '<div class="invalid-feedback qgrpName">Please enter question group name.</div>'
					+ '</div>'

					// select subject					
					+ '<div class="form-group col-sm-12 col-md-12 col-lg-12 col-xl-12" >'
//					+ '<label class="col-xl-12 col-md-12 col-sm-12" for="selectSubject">Select Subject *</label>'
					+ ' <select class=" form-control col-xl-12 col-md-12 col-sm-12 selectpicker" data-live-search="true"  id="selectSubject" required disabled>'
					+ ' <option value=""  selected>Choose Subject</option>'

					for (var i = 0; i < data.subData.length; i++) {
						qgHtm +=  '  <option value="' + data.subData[i].SID
								+ '" id ="' + data.subData[i].SID + '" selected>'
								+ data.subData[i].SN + ' </option>'
					}
					qgHtm += ' </select>'
							+ '<div id="error" class="invalid-feedback selectSubject">Please select subject.</div>'
							+ '</div>'

							
					+ '<div class="form-group step1 col-sm-12 col-md-12 col-lg-12 col-xl-12" "topicSelect">'
					+ '<label class="col-xl-12 col-md-12 col-sm-12" for="selectTopic">Select Topic <span class="marathi-text" >(श्रेणी निवडा)</span><i class="fa fa-asterisk " aria-hidden="true"></i></label>'
					+ ' <select class=" form-control col-xl-12 col-md-12 col-sm-12 selectpicker" data-live-search="true"  id="selectTopic"  onchange=\"com.coep.test.addProblem.onSelectTopic()\"" multiple  >'
//					+ ' <option value=""  >Select Topic</option>'

					for (var i = 0; i < data.topicData.length; i++) {
						qgHtm += '' + '  <option value="' + data.topicData[i].TID
								+ '" id ="' + data.topicData[i].TID + '" >'
								+ data.topicData[i].TNO +" - "+ data.topicData[i].TN +"&nbsp;("+ data.topicData[i].TN1 + ') </option>'
					}

					qgHtm += ' </select>'
							+ '<div id="error" class="invalid-feedback selectTopic">Please select topic.</div>'
							+ '</div>'		
									
					+ '<div class="form-group step7 col-sm-12 col-md-12 col-lg-12 col-xl-12" id= "selectQuesType"  >'
					
					+ '</div>'
					+' </div>'	// row close	
					
          			+'<div class="row">'
					+ '<div class="form-group step7a col-sm-12 col-md-12 col-lg-12 col-xl-12" id= "nonMediaQuesDiv">'
					
					
					+ '</div>'
					
					
					+ '<div class="form-group step7b col-sm-12 col-md-12 col-lg-12 col-xl-12" id= "mediaQuesDiv">'
					
					+ '</div>'
					
					+ '<div class="form-group step8 col-sm-12 col-md-12 col-lg-12 col-xl-12" id= "selectDiffLevel">'
					
					+ '</div>'
					
					+ '<div class="form-group step10 col-sm-12 col-md-12 col-lg-12 col-xl-12 hidden" id= "variationNo">'
					+'<label class="col-xl-12 col-md-12 col-sm-12" for="varNo">Enter Variation Number<span class="marathi-text" ></span></label>'
					+ '<input type="text" maxlength="3" class="form-control col-xl-12 col-md-12 col-sm-12 " id= "varNo" placeholder="Enter Variation Number" name="varNo" >'
					+ '<div class="invalid-feedback varNoerrmsg">Please enter question variation number.</div>'
					+ '</div>'
					
					
					+'</div>'	// row close		 
				 
					+ '<button id= "modiftyQGroupBtn" type="submit" class="btn btn-primary step9" style= "display:none;">Modify Question Group</button>'
					+ '</section>' 
					
					
					+ '<section class="section-preview" id="addActualQuestions" >'
					
					+ '<input type="checkbox" id="checkLatex" name="checkLatex" class="CheckLatexstyle">'
					+ '<label for="checkLatex" style="color: darkgreen; font-size: 14px;">&nbsp; Click here to check question in LaTeX format (LaTeX स्वरूपात प्रश्न तपासण्यासाठी येथे क्लिक करा) </label><br>'
					+ ' <div id="latexCheckBlock" class="col-sm-12 t1-b1-padding l0-r0-padding">' // check latex equation div
					+ '</div>'
					
					+ ' <div id="questionBlock" >' // add actual question

					+ '</div>'
					
					if (questionGruopJSON.questions == undefined) {
						qgHtm += '<button title="Add more question" id="addActualQuest" onclick="com.coep.test.addProblem.addActualQuestPreClick('+QGID+');" type="button" class=" btn btn-dark"><span class="icon-add midd-blue"></span>&nbsp;&nbsp;Add Questions</button>'
					}else{
						qgHtm += '<button title="Add more question" id="addActualQuest" onclick="com.coep.test.addProblem.addActualQuestPreClick('+QGID+');" type="button" class=" btn btn-dark "><span class="icon-add midd-blue"></span>&nbsp;&nbsp;Add More Questions</button>'
					}
					
					qgHtm += '</section>' 
					
					
					+'</div>'	// row close
					+ '</div>' // form
					
					
					+ '</div>' // addQuestion_content close
					+ '</div>'// main row close
					+ '</div>' // container close
		
		
		$("#addQuestionGroup").html(qgHtm);
		$('select').selectpicker();
		
		if( AP.backBtnStatus == "" ||  AP.backBtnStatus == undefined){
			$("#backFunction").hide();
		}
		
		$("#addNewQGBtn").on("click", function(e){
			AP.getLevelDetailsToAddQuestionGroup();
		});
		
		
		$("#varNo").keypress(
				function(e) {
					// if the letter is not digit then display error and
					// don't type anything
					if (e.which != 8 && e.which != 0
							&& (e.which < 48 || e.which > 57)) {
						// display error message
						$("#varNoerrmsg").html("Digits Only").show().fadeOut(
								"slow");
						return false;
					}
				});
		
		
		$("#modiftyQGroupBtn").on("click", function(e){
			
			
			var len = $( "#questionBlock" ).children().length;
			
			if(len != 0){
				

				if (saveQuestionGroupValidations()) {
					
				
					var quesGrpName = $('#qgrpName').val();
					
//					var topicId = $("#selectTopic option:selected").val();
					topicArr = [];
					for (var i = 0; i < topicIdArr.length; i++) {
						var topicId = topicIdArr[i];
						var topic = {};
						topic.topicId = topicId;
						topicArr.push(topic);
					}
					
				
					var quesType = $('#quesType option:selected').attr('id');
					var mediaTypeId = quesType;
					var content = '';
					var type = $('#quesType option:selected').text();
					
					
					if(AP.QGMLID != null){
						quesGroupMediaLinks.quesGroupMediaLinkId = AP.QGMLID;
					}
					
					if (quesType == 1) {
						content = $('#quesGroupTxtNoFile').val();
						quesGroupMediaLinks.mediaURLText = content;
					}else{
						quesGroupMediaLinks.mediaURLText = $("#queslink").attr("qg-media");
					}

				
					quesGroupMediaLinks.quesUsage = "question";
					
					var level = $('#diffLevel option:selected').val(); // difficulty level
					var subjectId = $('#selectSubject option:selected').val();

					var varNo =  $('#varNo').val();
					
					mediaType.mediaTypeId = mediaTypeId;
					subject.subjectId = subjectId;

					questionGruopJSON.name = quesGrpName;
					
					questionGruopJSON.topic = topicArr;
					questionGruopJSON.quesGroupMediaLinks = quesGroupMediaLinks;
					questionGruopJSON.type = type;
					
					questionGruopJSON.subject = subject;
					questionGruopJSON.mediaType = mediaType;
					questionGruopJSON.level = level;
				
					questionGruopJSON.varNo = varNo; 
					
					console.log(JSON.stringify(questionGruopJSON));
					console.log(questionGruopJSON);
					AP.addToQuestionGroup(AP.baseURL + "questionGroups", "PUT",
							questionGruopJSON, AP.mainData, AP.backBtnStatus);
					
				}

//				$("#editQues"+len).click();
//				$(".saveModifiedQuest").click();
			}else{


				if (saveQuestionGroupValidations()) {
					
				
					var quesGrpName = $('#qgrpName').val();
					
//					var topicId = $("#selectTopic option:selected").val();
					topicArr = [];
					for (var i = 0; i < topicIdArr.length; i++) {
						var topicId = topicIdArr[i];
						var topic = {};
						topic.topicId = topicId;
						topicArr.push(topic);
					}
					
				
					var quesType = $('#quesType option:selected').attr('id');
					var mediaTypeId = quesType;
					var content = '';
					var type = $('#quesType option:selected').text();
					
					
					if(AP.QGMLID != null){
						quesGroupMediaLinks.quesGroupMediaLinkId = AP.QGMLID;
					}
					
					if (quesType == 1) {
						content = $('#quesGroupTxtNoFile').val();
						quesGroupMediaLinks.mediaURLText = content;
					}else{
						quesGroupMediaLinks.mediaURLText = $("#queslink").attr("qg-media");
					}

				
					quesGroupMediaLinks.quesUsage = "question";
					
					var level = $('#diffLevel option:selected').val(); // difficulty level
					var subjectId = $('#selectSubject option:selected').val();

					var varNo =  $('#varNo').val();
					
					mediaType.mediaTypeId = mediaTypeId;
					subject.subjectId = subjectId;

					questionGruopJSON.name = quesGrpName;
					
					questionGruopJSON.topic = topicArr;
					questionGruopJSON.quesGroupMediaLinks = quesGroupMediaLinks;
					questionGruopJSON.type = type;
					
					questionGruopJSON.subject = subject;
					questionGruopJSON.mediaType = mediaType;
					questionGruopJSON.level = level;
				
					questionGruopJSON.varNo = varNo; 
					
					console.log(JSON.stringify(questionGruopJSON));
					console.log(questionGruopJSON);
					AP.addToQuestionGroup(AP.baseURL + "questionGroups", "PUT",
							questionGruopJSON, AP.mainData, AP.backBtnStatus);
					
				}

			
			}
			
		});
		
		if(!jQuery.isEmptyObject(questionGruopJSON)){
			if(questionGruopJSON.topic.length != 0 ){
				 topicArray = [];
				 topicIdArr = [];
				for(var i = 0; i < questionGruopJSON.topic.length; i++){
					topicArray.push(questionGruopJSON.topic[i].topicId);
					topicIdArr.push(questionGruopJSON.topic[i].topicId);
				}
				 
			}
		}
		
		$("#selectTopic").selectpicker("val", topicArray);
		
		$("#selectTopic").on('change', function(){ 
			 topicIdArr = [];	
			 var selected = $(this).find("option:selected");
			  selected.each(function(){
				  
				  topicIdArr.push(parseInt($(this).val()));
			    });
			 
		});
		

		$('#checkLatex').change(function() {
	        if(this.checked) {
	        	var latexChk = ''// laTEX check start
					latexChk += '<div class="form-group">'
				    +'<label for="comment" class="secondary-text-color">Enter Question in latex format:</label>'
					+'<textarea class="form-control latexCheck" rows="5" id="MathInput"></textarea>'
				      
				    +'</div>'
				    +'<button type="button" id="RanderQue" class="btn btn-dark" onclick = typesetInput(this) data-toggle="modal" data-target="#latexData">Check Equation</button>'
				    +'<button type="button" class="btn btn-info" title="Copy to Clipboard" style="margin-left: 3px;" onclick=copyToClipboard("MathInput")>'
				    +'<i class="fa fa-clipboard " ></i> &nbsp;Copy text '
				    +'</button>'	
				    +'<div class="form-group">'
				    +'</div>'
				    // laTEX check end
				    
				  //latex format div start
					+ '<div id="latexData" class="modal fade" role="dialog">'
					+ '<div class="modal-dialog">'

					+ ' <div class="modal-content">'
					+ '<div class="modal-header">'
					+ ' <span class="modal-title">Latex Equation</span>'
					+ ' <button type="button" class="close" data-dismiss="modal">&times;</button>'
					+ ' </div>'
					
					+ '<div class="modal-body"  style="  width: 500px;  height: auto;  overflow-x: auto;">' //
					+' <div class="scoll-tree">'
					+' <div id="MathPreview" class="" placeholder="Latex equation" ></div><br/>' //style="font-size : 25px; scroll : auto;"
//					+' <div id="MathPreview2" class="" placeholder="Latex equation" ></div><br/>' //style="font-size : 25px; scroll : auto;"
					+ '	</div>'
					+ ' </div>'
					+ '	<div class="modal-footer">'
					+ '<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color:red; color:#fff;">Close</button>'
					+ '</div>'
					+ '</div>'
					
					
					
//					//latex format div start
					
					
					
					
					

					+ '	 </div>'
					+ '	</div>'
					
			$("#latexCheckBlock").html(latexChk);
	        	
	        }else{
	        	$("#latexCheckBlock").html("");
	        }
	    });
		
		
		 copyToClipboard = function(elementId) {

			  // Create an auxiliary hidden input
			  var aux = document.createElement("input");

			  // Get the text from the element passed into the input
			  
			  aux.setAttribute("value", document.getElementById(elementId).value.replace(/\n/g, '<br>\n'));

			  // Append the aux input to the body
			  document.body.appendChild(aux);
			  
			  
			  // Highlight the content
			  aux.select();

			  // Execute the copy command
			  document.execCommand("copy");

			  // Remove the input from the body
			  document.body.removeChild(aux);

			}

			 log = function(){
				console.log('---')
			}
	
//		$('#selectTopic').change(bsSelectValidation);
		
		 $('#modifiedQuesgroup').addClass("col-sm-12 col-md-4 col-lg-4 col-xl-4");
		 $('#addActualQuestions').addClass("col-sm-12 col-md-8 col-lg-8 col-xl-8");
			
		
		AP.addActualQuestPreClick = function(QGID) {
			AP.addMoreQuestClick(questionGruopJSON, QGID);
		}
			
		 $("#qgrpName").val(questionGruopJSON.name);
		
		 AP.onSelectTopic();
		
		
			if (questionGruopJSON.questions != undefined) {
				AP.addCollapseDiv(questionGruopJSON);
			}
			
	}
	
	
	AP.addMoreQuestClick =  function(questionGroupJSON, QGID) {
		
		$("#modiftyQGroupBtn").hide();
		
		if (!$(".actAnsTypeBlock")[0]) {
			
		 var i = 0;	
		 var QuesCount = 1;
		 
		 if (questionGruopJSON.questions != undefined) {

				i = questionGruopJSON.questions.length;
				QuesCount = (i + 1);
			}	

		var newQuestionDiv = AP.addActualQuesDiv(questionGruopJSON);

		var newQuesDiv = newQuestionDiv.replace(/_/g, QuesCount);
		$("#questionBlock").append(newQuesDiv);
		
		
		$("#defTime" + QuesCount).keypress(
				function(e) {
					// if the letter is not digit then display error and
					// don't type anything
					if (e.which != 8 && e.which != 0
							&& (e.which < 48 || e.which > 57)) {
						// display error message
						$("#errmsg").html("Digits Only").show().fadeOut(
								"slow");
						return false;
					}
				});

		var ansType = '';
		for (var i = 0; i < AP.mainData.ansTypeData.length; i++) {
			ansType += '' + '  <option value="' + AP.mainData.ansTypeData[i].ATID
					+ '" id ="' + AP.mainData.ansTypeData[i].ATID + '">'
					+ AP.mainData.ansTypeData[i].ATN + ' </option>'
		}
		
		$("#ansType" + QuesCount).append(ansType);
		
		var selSolution = $("input:radio[name=solutionGroup"+QuesCount+"]");
		
		var solCnt = QuesCount;
		
		selSolution.on("change", function() {
			
			 selectedSol_Id = $('input[name="solutionGroup'+solCnt+'"]:checked').attr('id');

			 var solHTM = '';
			
			if (selectedSol_Id == "solMediaImg"+solCnt) {
				
				$("#solutionDiv"+solCnt).removeClass("hidden");
				solHTM +='<input  type="file"  id="questImgSol'+solCnt+'" class="questSol" title="Upload Solution File" col="8" onchange="com.coep.test.addProblem.onSelSolutionFile(this.id,'+solCnt+')" required>'
					+ '<div class="invalid-feedback questSol'+solCnt+'">Please select solution file.</div>'
					+ '<div id="sol_image'+solCnt+'" class="red-color"></div>'
					+'<div id="sol-preview-div"></div>'
				$("#solutionDiv"+solCnt).html(solHTM);

			} else if (selectedSol_Id == "solMediaAud"+solCnt) {

				$("#solutionDiv"+solCnt).removeClass("hidden");
				solHTM +='<input  type="file"  id="questAudSol'+solCnt+'" class="questSol" title="Upload Solution File" col="8" onchange="com.coep.test.addProblem.onSelSolutionFile(this.id,'+solCnt+')" required>'
					+ '<div class="invalid-feedback questSol'+solCnt+'">Please select solution file.</div>'
					+ '<div id="sol_image'+solCnt+'" class="red-color"></div>'
					+'<div id="sol-preview-div"></div>'
				$("#solutionDiv"+solCnt).html(solHTM);
				
			} else if (selectedSol_Id == "solMediaVid"+solCnt) {

				$("#solutionDiv"+solCnt).removeClass("hidden");
				solHTM +='<input  type="file"  id="questVidSol'+solCnt+'" class="questSol" title="Upload Solution File" col="8" onchange="com.coep.test.addProblem.onSelSolutionFile(this.id,'+solCnt+')" required>'
					+ '<div class="invalid-feedback questSol'+solCnt+'">Please select solution file.</div>'
					+ '<div id="sol_image'+solCnt+'" class="red-color"></div>'
					+'<div id="sol-preview-div"></div>'
				$("#solutionDiv"+solCnt).html(solHTM);
				
			}   else {
				
				$("#solutionDiv"+solCnt).html("");
			}

		});
		
		$('#reset').on('click', function() {
			
			$('.radioType').prop('checked',false);
			$("#solutionDiv"+solCnt).html("");
		});	
		
		QuesCount++;
		
		
		AP.selAnsType = function(ansTypeId) {
			
			modifyQuw = false;
			if(modifyQuw == true)
			{$("#SCAWrong" +scaincansinpcnt ).removeClass("checkLatexAnsAxt").addClass("checkLatexAnsAxt2");}
		else
			{$("#SCAWrong" +scaincansinpcnt ).removeClass("checkLatexAnsAxt2").addClass("checkLatexAnsAxt");}
			var regex = /\d+/;
			var divId = ansTypeId;

			var completeId = divId.match(regex);
			var Id = completeId[0];
			if(ansTypeId == '0') {
				// Singular correct Answer
				AP.resetAnswerTypeCnt();
				$('#new-div').html("<div class='col-xl-12 col-md-12 col-sm-12 alert alert-danger' style='text-align:center;'>Please select Answer Type..</div>");
				
			}else if (ansTypeId == '1') {
				// Singular correct Answer
				AP.resetAnswerTypeCnt();
				AP.scaDivFunction(Id);
				
			} else if (ansTypeId == '2') {
				// fill in blanks
				AP.resetAnswerTypeCnt();
				AP.fibDivFunction(Id);

			} else if (ansTypeId == '3') {

				// match the pair
				AP.resetAnswerTypeCnt();
				AP.mtpDivFunction(Id);

			} else if (ansTypeId == '4') {
				// true / fales
				AP.resetAnswerTypeCnt();
				AP.tfDivFunction(Id);
			} else if (ansTypeId == '5') {
				
				AP.resetAnswerTypeCnt();
				AP.mcaDivFunction(Id);
				// multiple correct Answer
			
			} else if (ansTypeId == '6') {
				AP.resetAnswerTypeCnt();
				AP.scaImageDivFunction(Id);
				// Image - singular correct Answer
			
			} else if (ansTypeId == '7') {
				AP.resetAnswerTypeCnt();
//				AP.tfImageDivFunction(Id); 	// Image - true / fales - dont delete it, may use in future
				AP.mtpImageDivFunction(Id);
			
			}else if (ansTypeId == '8') {
				AP.resetAnswerTypeCnt();
				AP.mcaImageDivFunction(Id);
				// Image - Multiple correct Answer
			}
			
		}
		
		
		
		AP.onSelSolutionFile = function(solId, Id) {
			
			if(solId.includes("Img")){
				var file = $('#'+solId);
				var filename = $.trim(file.val());
				
				if(file != "" && filename != ""){
					
					var formData = new FormData();
					formData.append('file', $('#'+solId)[0].files[0]);

					var fileSize = ($('#'+solId)[0].files[0].size / 1024 / 1024)

					var valid_extensions = /(\.png|\.jpeg|\.jpg)$/i;
					if (filename != null || filename != undefined)
						if (valid_extensions.test(filename)
								&& fileSize <= AP.maxSizeImage) {
							
							 AP.uplaodSolMediaFile(AP.baseURL + "media",
									"POST", formData, solId, "IMAGE");
							
							$("#sol_image"+Id).html("");
						} else {
							$("#sol_image"+Id)
									.html(
											"<h4> 1)File Size Should Not exceeds "+AP.maxSizeImage+" MB. <br> 2)supported file formats -  '.png' ,'.jpeg ','.jpg'</h4>");
							$("#sol_image"+Id).css('display', 'block');
							$('#'+solId).val(null);
							// alert("File size must under 10 mb!");

						}

				}else{
					alert("Please select image file");
				}
				
			}else if(solId.includes("Aud")){
				var file = $('#'+solId);
				var filename = $.trim(file.val());
				
				if(file != "" && filename != ""){
					
					var formData = new FormData();
					formData.append('file', $('#'+solId)[0].files[0]);

					var fileSize = ($('#'+solId)[0].files[0].size / 1024 / 1024)

					var valid_extensions = /(\.mp3|\.wav)$/i;
					if (filename != null || filename != undefined)
						if (valid_extensions.test(filename)
								&& fileSize <= AP.maxSizeImage) {
							
							 AP.uplaodSolMediaFile(AP.baseURL + "media",
									"POST", formData, solId, "AUDIO");
							
							$("#sol_image"+Id).html("");
						} else {
							$("#sol_image"+Id)
									.html(
											"<h4>1)File Size Should Not exceeds  "+AP.maxSizeAudio+" MB. <br> 2)supported file formats - '.mp3' ,'.wav'</h4>");
							$("#sol_image"+Id).css('display', 'block');
							$('#'+solId).val(null);
							// alert("File size must under 10 mb!");

						}

				}else{
					alert("Please select Audio file");
				}
				
			}else if(solId.includes("Vid")){
				var file = $('#'+solId);
				var filename = $.trim(file.val());
				
				if(file != "" && filename != ""){
					
					var formData = new FormData();
					formData.append('file', $('#'+solId)[0].files[0]);

					var fileSize = ($('#'+solId)[0].files[0].size / 1024 / 1024)

					var valid_extensions = /(\.mp4|\.mpeg)$/i;
					if (filename != null || filename != undefined)
						if (valid_extensions.test(filename)
								&& fileSize <= AP.maxSizeImage) {
							
							 AP.uplaodSolMediaFile(AP.baseURL + "media",
									"POST", formData, solId, "VIDEO");
							
							$("#sol_image"+Id).html("");
						} else {
							$("#sol_image"+Id)
									.html(
											"<h4>1)File Size Should Not exceeds "+AP.maxSizeVideo+" MB. <br> 2)supported file formats - '.mpeg' ,'.mp4 '</h4>");
							$("#sol_image"+Id).css('display', 'block');
							$('#'+solId).val(null);
							// alert("File size must under 10 mb!");

						}

				}else{
					alert("Please select video file");
				}
			}
			
			
		}
		
		} else {
			 alert("Please save current question/s");
		}
		
		 
	}
	
	
	
	AP.addActualQuesDiv = function(questionGruopJSON) {
			var newQuestionDiv = '';
			 newQuestionDiv +=  ''
				 
				 +'<div class="row">'
			        + '<section class="section-preview col-sm-12 col-md-12 col-lg-12 col-xl-12" id="actAnsTypeBlockSection_" >'			        
			        + '<div id="saveQuesErr_" class=" hidden alert alert-danger red-color" style="font-size: 15px;">*All fields are manadatory. Please fill all fields and save this question</div>'
			        + '<div id="actAnsTypeBlock_" class="col-sm-12 col-md-12 col-lg-12 col-xl-12 actAnsTypeBlock">'// actAnsTypeBlock_ start
			        +'<div class="row">'
			        + '<div class="form-group col-sm-12 col-md-2 col-lg-2 col-xl-2 addqueNo">'
			        + '<span id="questNo_" type="text" class=""  disabled="">Q_.</span>'
			        +'</div>'
			        
				    + '<div class="form-group form-group col-sm-12  col-md-9 col-lg-9 col-xl-9">'
					+ '<textarea id="questText_" type="text" autofocus="" class="form-control ActQuestion latexCheck" placeholder="Enter Question Text ( इथे प्रत्यक्ष गणित - म्हणजे प्रश्न - लिहायचा आहे )" required></textarea><i class="fa fa-asterisk asteriskForInput" aria-hidden="true"></i>'
					+ '<div class="invalid-feedback">Please enter question  name.</div>'
					+ '</div>'
					
					
					    + '<div class="form-group form-group col-sm-12  col-md-1 col-lg-1 col-xl-1">'
						+ '<button type="button" id="RanderQue" class="btn btn-dark" onclick="typesetInputAct(this)" data-toggle="modal" data-target="#latexData2">Check</button>'
						+ '</div>'
//						+'<div id="actQueInLatex">'
					+ '</div>'
					
					+ '<div class="form-group col-sm-12 col-md-12 col-lg-12 col-xl-12">'
					+ '<label class="">Select Answer Type <span class="marathi-text" >( इथे उत्तर कोणत्या प्रकारचे आहे ते निवडायचे आहे - म्हणजे फक्त एकच उत्तर बरोबर, एका पेक्षा जास्त उत्तरे बरोबर, चूक-बरोबर, जोड्या जुळवा  )</span><i class="fa fa-asterisk" aria-hidden="true"></i></label>'
					+ '<select onchange="com.coep.test.addProblem.selAnsType(this.value)" class="form-control select-class" id="ansType_"  title="Select Answer Type" required>'
					+ ' <option value="0"  selected>Choose Answer Type</option>'
					+ '</select>'
					+ '<div  class="invalid-feedback">Please select answer type.</div>'
					+ '</div>'
						
						
						// new div to add select answer type
						+ '<div id="new-div" >'
						+ "</div>" // new div end here
						
						// this div to add question solution
						
						+ '<div class="form-group col-sm-12 col-md-12 col-lg-12 col-xl-12">'
						
						+ '<label>Enter solution text <span class="marathi-text" >(उत्तराचे मजकूर)</span><i class="fa fa-asterisk" aria-hidden="true"></i></label><br/>'
						+ '<textarea id="quetSolText_" type="text" autofocus="" class="form-control AddQueSolLatex latexCheck" placeholder="Enter Solution in Text" style="margin-top: 0px;margin-bottom: 0px;height: 113px;" required></textarea><i class="fa fa-asterisk asteriskForInput" aria-hidden="true"></i>'
						+ '<div class="invalid-feedback questSolText">Please enter question solution in text format.</div>'
						
					
						+ '<label>Select solution media type <span class="marathi-text" >(उत्तरामध्ये जर इमेज, ऑडिओ, व्हिडिओ असेल तर खलील पर्याय निवडा.)</span><button type="button" id="RanderQue" class="btn btn-dark float-right" onclick="typesetInputAddQueSol(this)" data-toggle="modal" data-target="#latexData2">Check</button></label><br/>'
						+ '<div class="custom-control custom-radio custom-control-inline">'
						
						+ '<input type="radio" class="custom-control-input radioType" id="solMediaImg_" name="solutionGroup_">'
						+ '<label class="custom-control-label" for="solMediaImg_" style="font-size: 15px;">Image</label>'
						+ '</div>'

						+ '<div class="custom-control custom-radio custom-control-inline">'
						+ '<input type="radio" class="custom-control-input radioType" id="solMediaAud_" name="solutionGroup_">'
						+ '<label class="custom-control-label" for="solMediaAud_" style="font-size: 15px;">Audio</label>'
						+ '</div>'
						
						+ '<div class="custom-control custom-radio custom-control-inline">'
						+ '<input type="radio" class="custom-control-input radioType" id="solMediaVid_" name="solutionGroup_">'
						+ '<label class="custom-control-label" for="solMediaVid_" style="font-size: 15px;">Video</label>'
						+ '</div>'
						+ '<span id="reset" class="btn btn-danger">Clear</span>'						
						+ '</div>'
						
						
						+ '<div class="form-group col-sm-12  col-md-12 col-lg-12 col-xl-12 hidden" id="solutionDiv_" >'
						+ '</div>' // ends here
						
						+ '<div class="form-group col-sm-12 col-md-12 col-lg-12 col-xl-12">'
						+ '<label>Enter Time[in Seconds] <span class="marathi-text" >(गणित सोडवण्यासाठी दिलेला वेळ सेकंदात)</span> </label><br/>'
						+ '<input type="text" id= "defTime_" maxlength="4"  class="form-control col-xl-12 col-md-12 col-sm-12"  placeholder="Enter Time[in Seconds]"  required><i class="fa fa-asterisk asteriskForInput" aria-hidden="true"></i>'
						+ '<div id="errmsg" class="red-color"></div>'
						+ '</div>'
						
						+ '<div class="col-sm-12 " >'
						
						+ '&nbsp;&nbsp;<span onclick="saveActQues(this.id)" title="SAVE QUESTION" id="saveQues_" class="btn btn-success float-right"><i class="fa fa-floppy-o" aria-hidden="true"></i></span>'
						+ '&nbsp;&nbsp;<span  id="removeQues_" onclick="com.coep.test.AlertMessage.confirmationToDeleteModifiedQues(this.id)"   data-toggle="modal" data-target="#AlertMesConfirm" title="REMOVE QUESTION" class="btn btn-danger deleteQue float-right"><i class="fa fa-trash " aria-hidden="true"></i></span>'
						+ '</div>'
						
						+ '</div>' // actAnsTypeBlock_ close
						+ '</section>'
						+ '</div>'// main row close
						
						
						
						// add que model start
						+ '<div id="latexData2" class="modal fade" role="dialog">'
						+ '<div class="modal-dialog">'

						+ ' <div class="modal-content">'
						+ '<div class="modal-header">'
						+ ' <span class="modal-title">Latex Equation</span>'
						+ ' <button type="button" class="close" data-dismiss="modal">&times;</button>'
						+ ' </div>'
						
						+ '<div class="modal-body"  style="  width: 500px;  height: auto;  overflow-x: auto;">' //
						+' <div class="scoll-tree">'
						+' <div id="MathPreview2" class="" placeholder="Latex equation" ></div><br/>' //style="font-size : 25px; scroll : auto;"
//						+' <div id="MathPreview2" class="" placeholder="Latex equation" ></div><br/>' //style="font-size : 25px; scroll : auto;"
						+ '	</div>'
						+ ' </div>'
						+ '	<div class="modal-footer">'
						+ '<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color:red; color:#fff;">Close</button>'
						+ '</div>'
						+ '</div>'
						
						//add que model end
						
//						// add que model for Singular Correct Answer start
//						+ '<div id="latexDataAns1" class="modal fade" role="dialog">'
//						+ '<div class="modal-dialog">'
//				
//						+ ' <div class="modal-content">'
//						+ '<div class="modal-header">'
//						+ ' <span class="modal-title">Latex Equation</span>'
//						+ ' <button type="button" class="close" data-dismiss="modal">&times;</button>'
//						+ ' </div>'
//						
//						+ '<div class="modal-body"  style="  width: 500px;  height: auto;  overflow-x: auto;">' //
//						+' <div class="scoll-tree">'
//						+' <div id="MathPreviewCorrectAns1" class="" placeholder="Latex equation" ></div><br/>'
//				//		
//						+ '	</div>'
//						+ ' </div>'
//						+ '	<div class="modal-footer">'
//						+ '<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color:red; color:#fff;">Close</button>'
//						+ '</div>'
//						+ '</div>'
//						// add que  model for Singular Correct Answer solution end
						
						
						saveActQues = function(id) {
							AP.saveQues(id, questionGruopJSON);
						}
						removeQues = function(id) {
							if(questionGruopJSON.questions != undefined){
								AP.removeEditQues(id, questionGruopJSON, questionGruopJSON.questions.length);
							}else{
								AP.removeEditQues(id, questionGruopJSON, 0);
							}
							
						};
			 
			 return newQuestionDiv;
	}
	
	// MODIFIED QUESTIONdIV
		
	AP.addModifiedQuesDiv = function(questionGruopJSON, i) {
		var newQuestionDiv = '';
		 newQuestionDiv +=  ''
			 // modify
			 +'<div class="row">'
		        + '<section class="section-preview col-sm-12 col-md-12 col-lg-12 col-xl-12" id="actAnsTypeBlockSection_" >'
		        + '<div id="saveQuesErr_" class=" hidden alert alert-danger red-color" style="font-size: 15px;">*All fields are manadatory. Please fill all fields and save this question</div>'
		        + '<div id="actAnsTypeBlock_" class="col-sm-12 col-md-12 col-lg-12 col-xl-12 actAnsTypeBlock">'// actAnsTypeBlock_ start
		        +'<div class="row">'
		        + '<div class="form-group col-sm-12 col-md-2 col-lg-2 col-xl-2 addqueNo">'
		        + '<span id="questNo_" type="text" class=""  disabled="">Q_.</span>'
		        +'</div>'
			    + '<div class="form-group form-group col-sm-12  col-md-9 col-lg-9 col-xl-9">'
				+ '<textarea id="questText_" type="text" autofocus="" class=" form-control ModifyQuestion" placeholder="Enter Question Text ( इथे प्रत्यक्ष गणित - म्हणजे प्रश्न - लिहायचा आहे )" required></textarea><i class="fa fa-asterisk asteriskForInput" aria-hidden="true"></i>'
				+ '<div class="" id="questText_"></div>'
				+ '<div class="invalid-feedback">Please enter question group name.</div>'
				+ '</div>'
				
				
				 + '<div class="form-group form-group col-sm-12  col-md-1 col-lg-1 col-xl-1">'
				+ '<button type="button" id="RanderQue" class="btn btn-dark" onclick="typesetInputActModify(this)" data-toggle="modal" data-target="#latexData3">Check</button>'
				+ '</div>'
				+ '</div>'
					
					
				+ '<div class="form-group col-sm-12 col-md-12 col-lg-12 col-xl-12">'
				+ '<label class="">Select Answer Type <span class="marathi-text" >( इथे उत्तर कोणत्या प्रकारचे आहे ते निवडायचे आहे - म्हणजे फक्त एकच उत्तर बरोबर, एका पेक्षा जास्त उत्तरे बरोबर, चूक-बरोबर, जोड्या जुळवा  )</span><i class="fa fa-asterisk " aria-hidden="true"></i></label>'
				+ '<select onchange="com.coep.test.addProblem.selAnsType(this.value)" class="form-control select-class" id="ansType_"  title="Select Answer Type" required disabled>'
				+ ' <option value="0"  selected>Choose Answer Type</option>'
				+ '</select>'
				+ '<div  class="invalid-feedback">Please select answer type.</div>'
				+ '</div>'
					
					
					// new div to add select answer type
					+ '<div id="new-div" class="col-sm-12 t1-b1-padding l0-r0-padding">'
					+ "</div>" // new div end here
					
					
					// this div to add question solution
					
					+ '<div class="form-group col-sm-12 col-md-12 col-lg-12 col-xl-12">'
					
					+ '<label>Enter solution text <span class="marathi-text" >(उत्तराचे मजकूर)</span><i class="fa fa-asterisk" aria-hidden="true"></i></label><br/>'
					+ '<textarea id="quetSolText_" type="text" autofocus="" class="form-control ModifyQueSolLatex latexCheck" placeholder="Enter Solution in Text" style="margin-top: 0px;margin-bottom: 0px;height: 113px;" required></textarea><i class="fa fa-asterisk asteriskForInput" aria-hidden="true"></i>'
					+ '<div class="invalid-feedback questSolText">Please enter question solution in text format.</div>'
					
					
					+ '<label>Select solution media type <span class="marathi-text" >(उत्तरामध्ये जर इमेज, ऑडिओ, व्हिडिओ असेल तर खलील पर्याय निवडा.)</span><button type="button" id="RanderQue" class="btn btn-dark float-right" onclick="typesetInputModifyQueSol(this)" data-toggle="modal" data-target="#latexData3">Check</button></label><br/>'
					+ '<div class="custom-control custom-radio custom-control-inline">'
					
					+ '<input type="radio" class="custom-control-input radioType" id="solMediaImg_" name="solutionGroup_">'
//					
					+ '<label class="custom-control-label" for="solMediaImg_" style="font-size: 15px;">Image</label>'
					+ '</div>'

					+ '<div class="custom-control custom-radio custom-control-inline">'
					+ '<input type="radio" class="custom-control-input radioType" id="solMediaAud_" name="solutionGroup_">'
					+ '<label class="custom-control-label" for="solMediaAud_" style="font-size: 15px;">Audio</label>'
					+ '</div>'
					
					+ '<div class="custom-control custom-radio custom-control-inline">'
					+ '<input type="radio" class="custom-control-input radioType" id="solMediaVid_" name="solutionGroup_">'
					+ '<label class="custom-control-label" for="solMediaVid_" style="font-size: 15px;">Video</label>'
					+ '</div>'
					+ '<span id="reset" class="btn btn-danger">Clear</span>'
					+ '</div>'
					
					+ '<div class="form-group form-group col-sm-12  col-md-12 col-lg-12 col-xl-12 hidden" id="solutionDiv_" >'

					+ '</div>' // ends here
					
					
					+ '<div class="form-group col-sm-12 col-md-12 col-lg-12 col-xl-12">'
					+ '<label>Enter Time[in Seconds]<span class="marathi-text" >(गणित सोडवण्यासाठी दिलेला वेळ सेकंदात)</span></label><br/>'
					+ '<input type="text" id= "defTime_" maxlength="4" class="form-control col-xl-12 col-md-12 col-sm-12"  placeholder="Enter Time[in Seconds]"  required><i class="fa fa-asterisk asteriskForInput" aria-hidden="true"></i>'
					+ '<div class="invalid-feedback">Please enter time [in Seconds].</div>'
					+ '<div id="errmsg" class="red-color"></div>'
					+ '</div>'
					
					+ '<div class="col-sm-12 " >'
					
					+ '&nbsp;&nbsp;<span onclick="saveModifiedQues(this.id)" title="SAVE QUESTION" id="saveQues_" class="btn btn-success float-right saveModifiedQuest"><i class="fa fa-floppy-o" aria-hidden="true"></i></span>'
					+ '&nbsp;&nbsp;<span  id="removeQues_" onclick="com.coep.test.AlertMessage.confirmationToDeleteModifiedQues(this.id)"  data-toggle="modal" data-target="#AlertMesConfirm" title="REMOVE QUESTION" class="btn btn-danger deleteQue float-right"><i class="fa fa-trash " aria-hidden="true"></i></span>'
					+ '</div>'
					
					+ '</div>' // actAnsTypeBlock_ close
					+ '</section>'
					+ '</div>'// main row close
					
					// modify latex  model
					+ '<div id="latexData3" class="modal fade" role="dialog">'
					+ '<div class="modal-dialog">'

					+ ' <div class="modal-content">'
					+ '<div class="modal-header">'
					+ ' <span class="modal-title">Latex Equation</span>'
					+ ' <button type="button" class="close" data-dismiss="modal">&times;</button>'
					+ ' </div>'
					
					+ '<div class="modal-body"  style="  width: 500px;  height: auto;  overflow-x: auto;">' //
					+' <div class="scoll-tree">'
					+' <div id="MathPreview2" class="" placeholder="Latex equation" ></div><br/>' //style="font-size : 25px; scroll : auto;"
//					+' <div id="MathPreview2" class="" placeholder="Latex equation" ></div><br/>' //style="font-size : 25px; scroll : auto;"
					+ '	</div>'
					+ ' </div>'
					+ '	<div class="modal-footer">'
					+ '<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color:red; color:#fff;">Close</button>'
					+ '</div>'
					+ '</div>'
					
					
					saveModifiedQues = function(id) {
						AP.saveModifiedQuestion(id, questionGruopJSON, i);
					};

					removeQues = function(id) {
						AP.removeEditQues(id, questionGruopJSON, i);
					};
		 return newQuestionDiv;
	}
	
	AM.confirmationToDeleteModifiedQues = function(id) {

		var AlertComfirmFlag = false;
			AlertMsg = " Are you sure, you want to delete this question ?";
			var AlertMesConfirm = ''
				AlertMesConfirm +=  '<div class="container-fluid">'
				+ '<div class="row">'
				+ '<div  class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'
				//Alert modal start
						+ '<div class="modal" id="AlertMesConfirm">'
						+ '<div class="modal-dialog">'
						+ '<div class="modal-content">'
			            
			            	 +' <div class="modal-header bg-info" style="color:#fff;">'
								+ '   <h4 class="modal-title">Confirmation !!</h4>'
								+ '  <button type="button" class="close" data-dismiss="modal">&times;</button>'
								+ '  </div>'
				
								+ '   <div class="modal-body">'
								+ '<span id="AlertMsgStyle">'+AlertMsg+'</span>'
							    + ' </div>'
			                +'    <div class="modal-footer">'
							+ '<button type="button" class="btn btn-success" data-dismiss="modal" id="AlertComfirmYes" onClick="AlertComfirmYes()">Yes</button>&nbsp;&nbsp;'
							+ '<button type="button" class="btn btn-danger" data-dismiss="modal">No</button>'
							+ '</div>'
			
						  + '    </div>'
						  + '  </div>'
						  + '</div>'
				//alert modal end
				+ '</div>' // subject_content close
				+ '</div>'// main row close
				+ '</div>' // container close
				
				$("#main-div").append(AlertMesConfirm);
			
				AlertComfirmYes = function()
				{
					AlertComfirmFlag = true;
					removeQues(id);
					return AlertComfirmFlag;
				}
			
			
		
	}
	
	
	// add collapse div here
	
	AP.addCollapseDiv = function(questionGruopJSON) {
		var collapseDiv = '';

		if (questionGruopJSON.questions != undefined) {

			for (var i = 0; i < questionGruopJSON.questions.length; i++) {

				cnt = (i + 1);
				id = "saveQues" + cnt;
				var regex = /\d+/;
				var divId = id;
				var completeId = divId.match(regex);

				var Id = completeId[0];

				mcacorransinpcnt = 1;
				mcaincansinpcnt = 1;
				fibcorransinpcnt = 1;
				fibincansinpcnt = 1;
				mtpcorranstxtcnt = 1;
				mtpincanstxtcnt = 1;
				scacorransinpcnt = 1;
				scaincansinpcnt = 1;
				 scaimgcorransinpcnt = 1;
				 scaimgincansinpcnt = 1; 
				 mtpimgcorranstxtcnt = 1;  
				 mtpimgincanstxtcnt = 1;
				 mcaimgcorransinpcnt = 1;
				 mcaimgincansinpcnt = 1;  
			
				collapseDiv += '<div id="collapseDiv'
						+ Id
						+ '" >'
						+ '<section class="section-preview row" >'
						+ '<div class="form-group col-sm-12 col-md-2 col-lg-2 col-xl-2 addqueNo">'
						+ '<span>Q'
						+ Id
						+ '.</span>'
						+'</div>'
						+ '<div class="form-group col-sm-12 col-md-9 col-lg-9 col-xl-9 ">'
						+ '<span id="collapseDivQues'
						+ Id
						+ '" class="prim-text-black-color"  id="MathPreview">'
						+ questionGruopJSON.questions[i].content
						+ '</span>'
						+ '</div>'
						+ '<div class="form-group col-sm-12 col-md-1 col-lg-1 col-xl-1">'
						+ '<div class="row">'
						+ '<span onclick=\"editQue(this.id)\" title="MODIFY QUESTION" id="editQues'
						+ Id
						+ '" class="AlliconS col-sm-12 col-md-12 col-lg-12 col-xl-12 btn btn-success editQueClass"><i class="fa fa-pencil-square-o " aria-hidden="true"></i></span>'
						//+'</div>'
						//+ '<div class="form-group col-sm-12 col-md-2 col-lg-2 col-xl-2 AlliconS">'
						+ '<span id="removeQues' 
						+ Id
						+ '" onClick=\"javascript:com.coep.test.AlertMessage.confirmationToDeleteQuestion(this.id,'
						+ i
						+ ')\"  data-toggle="modal" data-target="#AlertMesConfirm" title="REMOVE QUESTION" class="AlliconS col-sm-12 col-md-12 col-lg-12 col-xl-12 btn btn-danger"><i class="fa fa-trash " aria-hidden="true"></i></span>'
						+ '</div></div></section></div>';
				// end text question

			}
			
		}
		

		$("#questionBlock").append(collapseDiv);
		
		MJ.renderMathJax();
		
		editQue = function(id) {
			
			AP.editQues(id, questionGruopJSON);
			//edited
		}
		
		removeEditQues = function(id, i) {
			AP.removeEditQues(id, questionGruopJSON, i);
		}

	}
	
	
	
	AM.confirmationToDeleteQuestion = function(id, i) {
		var AlertComfirmFlag = false;
			AlertMsg = " Are you sure, you want to delete this question ?";
			var AlertMesConfirm = ''
				AlertMesConfirm +=  '<div class="container-fluid">'
				+ '<div class="row">'
				+ '<div  class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'
				//Alert modal start
						+ '<div class="modal" id="AlertMesConfirm">'
						+ '<div class="modal-dialog">'
						+ '<div class="modal-content">'
			            
			            	 +' <div class="modal-header bg-info" style="color:#fff;">'
								+ '   <h4 class="modal-title">Confirmation !!</h4>'
								+ '  <button type="button" class="close" data-dismiss="modal">&times;</button>'
								+ '  </div>'
				
								+ '   <div class="modal-body">'
								+ '<span id="AlertMsgStyle">'+AlertMsg+'</span>'
							    + ' </div>'
			                +'    <div class="modal-footer">'
							+ '<button type="button" class="btn btn-success" data-dismiss="modal" id="AlertComfirmYes" onClick="AlertComfirmYes()">Yes</button>&nbsp;&nbsp;'
							+ '<button type="button" class="btn btn-danger" data-dismiss="modal">No</button>'
							+ '</div>'
			
						  + '    </div>'
						  + '  </div>'
						  + '</div>'
				//alert modal end
				+ '</div>' // subject_content close
				+ '</div>'// main row close
				+ '</div>' // container close
				
				$("#main-div").append(AlertMesConfirm);
			
				AlertComfirmYes = function()
				{
					AlertComfirmFlag = true;
					removeEditQues(id, i);
					return AlertComfirmFlag;
				}
			
			
		}
	
	AP.removeEditQues = function(id, questionGruopJSON, i) {

		var Id = extractNum(id);
		if (questionGruopJSON.questions == undefined) {
			$("#actAnsTypeBlockSection" + Id).remove();
		} else {
			AP.postRemoveEditQues(id, questionGruopJSON, i);
		}
		
		
	};
	
	
	AP.postRemoveEditQues = function(id, questionGruopJSON, i) {
		var Id = extractNum(id);
		// $( "#answerTypeSubBlock"+Id ).remove();
		if (i == 0 && questionGruopJSON.questions.length == 1) {
			questions = [];
			delete questionGruopJSON["questions"];
			
		} else {
			questionGruopJSON.questions.splice(i, 1);
		}
		

		AP.removeQuestionFormQuesGrp(AP.baseURL + "questionGroups", "PUT",
				questionGruopJSON, AP.mainData, AP.backBtnStatus);
	}
	
	
	AP.editQues = function(id, questionGruopJSON) {
		
		AP.renderMultipleQuestionToModify(id, questionGruopJSON);
	}
	
	
	
	AP.renderMultipleQuestionToModify = function(id, questionGruopJSON) {

		$("#modiftyQGroupBtn").hide();
		modifyQuw = true;

		var regex = /\d+/;
		var divId = id;
		var completeId = divId.match(regex);

		var Id = completeId[0];
		var i = parseInt(Id);
		cnt = i;
		i = i - 1;


		if (!$(".actAnsTypeBlock")[0]) {

			var ansType = '';

			
			var newQuestionDiv = AP.addModifiedQuesDiv(questionGruopJSON,i);
			var newQuesDiv = newQuestionDiv.replace(/_/g, cnt);
			
			$("#collapseDiv" + Id).replaceWith(newQuesDiv);
			
			$("#defTime" + cnt).keypress(
					function(e) {
						// if the letter is not digit then display error and
						// don't type anything
						if (e.which != 8 && e.which != 0
								&& (e.which < 48 || e.which > 57)) {
							// display error message
							$("#errmsg").html("Digits Only").show().fadeOut(
									"slow");
							return false;
						}
					});
			
			
			var quesText = questionGruopJSON.questions[i].content;

			$("textarea#questText" + cnt).text(quesText);
			
			$("textarea#quetSolText" + cnt).text(questionGruopJSON.questions[i].solution);
			
			
			var ansType = '';
			for (var j = 0; j < AP.mainData.ansTypeData.length; j++) {
				ansType += '' + '  <option value="' + AP.mainData.ansTypeData[j].ATID
						+ '" id ="' + AP.mainData.ansTypeData[j].ATID + '">'
						+ AP.mainData.ansTypeData[j].ATN + ' </option>'
			}
			$("#ansType" + cnt).append(ansType);
			
		
			
			AP.selAnsType = function(ansTypeId) {
				
				modifyQuw = false;
				if(modifyQuw == true)
				{$("#SCAWrong" +scaincansinpcnt ).removeClass("checkLatexAnsAxt").addClass("checkLatexAnsAxt2");}
			else
				{$("#SCAWrong" +scaincansinpcnt ).removeClass("checkLatexAnsAxt2").addClass("checkLatexAnsAxt");}
				var regex = /\d+/;
				var divId = ansTypeId;

				var completeId = divId.match(regex);
				var Id = completeId[0];
				if (ansTypeId == '0') {
					// Singular correct Answer
					AP.resetAnswerTypeCnt();
					$('#new-div').html("<div class='col-xl-12 col-md-12 col-sm-12 alert alert-danger' style='text-align:center;'>Please select Answer Type..</div>");
					
				} else	if (ansTypeId == '1') {
					// Singular correct Answer
					AP.resetAnswerTypeCnt();
					AP.scaDivFunction(Id);
					
				} else if (ansTypeId == '2') {
					// fill in blanks
					AP.resetAnswerTypeCnt();
					AP.fibDivFunction(Id);

				} else if (ansTypeId == '3') {

					// match the pair
					AP.resetAnswerTypeCnt();
					AP.mtpDivFunction(Id);

				} else if (ansTypeId == '4') {
					// true / fales
					AP.resetAnswerTypeCnt();
					AP.tfDivFunction(Id);
				} else if (ansTypeId == '5') {
					
					AP.resetAnswerTypeCnt();
					AP.mcaDivFunction(Id);
					// multiple correct Answer
				
				} else if (ansTypeId == '6') {
					AP.resetAnswerTypeCnt();
					AP.scaImageDivFunction(Id);
					// Image - singular correct Answer
				
				} else if (ansTypeId == '7') {
					AP.resetAnswerTypeCnt();
//					AP.tfImageDivFunction(Id); 	// Image - true / fales - dont delete it, may use in future
					AP.mtpImageDivFunction(Id);
				
				}else if (ansTypeId == '8') {
					AP.resetAnswerTypeCnt();
					AP.mcaImageDivFunction(Id);
					// Image - Multiple correct Answer
				}
				
			}
			

			AP.onSelSolutionFile = function(solId, Id) {
				
				if(solId.includes("Img")){
					var file = $('#'+solId);
					var filename = $.trim(file.val());
					
					if(file != "" && filename != ""){
						
						var formData = new FormData();
						formData.append('file', $('#'+solId)[0].files[0]);

						var fileSize = ($('#'+solId)[0].files[0].size / 1024 / 1024)

						var valid_extensions = /(\.png|\.jpeg|\.jpg)$/i;
						if (filename != null || filename != undefined)
							if (valid_extensions.test(filename)
									&& fileSize <= AP.maxSizeImage) {
								
								 AP.uplaodSolMediaFile(AP.baseURL + "media",
										"POST", formData, solId, "IMAGE");
								
								$("#sol_image"+Id).html("");
							} else {
								$("#sol_image"+Id)
										.html(
												"<h4> 1)File Size Should Not exceeds "+AP.maxSizeImage+" MB. <br> 2)supported file formats -  '.png' ,'.jpeg ','.jpg'</h4>");
								$("#sol_image"+Id).css('display', 'block');
								$('#'+solId).val(null);
								// alert("File size must under 10 mb!");

							}

					}else{
						alert("Please select image file");
					}
					
				}else if(solId.includes("Aud")){
					var file = $('#'+solId);
					var filename = $.trim(file.val());
					
					if(file != "" && filename != ""){
						
						var formData = new FormData();
						formData.append('file', $('#'+solId)[0].files[0]);

						var fileSize = ($('#'+solId)[0].files[0].size / 1024 / 1024)

						var valid_extensions = /(\.mp3|\.wav)$/i;
						if (filename != null || filename != undefined)
							if (valid_extensions.test(filename)
									&& fileSize <= AP.maxSizeImage) {
								
								 AP.uplaodSolMediaFile(AP.baseURL + "media",
										"POST", formData, solId, "AUDIO");
								
								$("#sol_image"+Id).html("");
							} else {
								$("#sol_image"+Id)
										.html(
												"<h4>1)File Size Should Not exceeds  "+AP.maxSizeAudio+" MB. <br> 2)supported file formats - '.mp3' ,'.wav'</h4>");
								$("#sol_image"+Id).css('display', 'block');
								$('#'+solId).val(null);
								// alert("File size must under 10 mb!");

							}

					}else{
						alert("Please select Audio file");
					}
					
				}else if(solId.includes("Vid")){
					var file = $('#'+solId);
					var filename = $.trim(file.val());
					
					if(file != "" && filename != ""){
						
						var formData = new FormData();
						formData.append('file', $('#'+solId)[0].files[0]);

						var fileSize = ($('#'+solId)[0].files[0].size / 1024 / 1024)

						var valid_extensions = /(\.mp4|\.mpeg)$/i;
						if (filename != null || filename != undefined)
							if (valid_extensions.test(filename)
									&& fileSize <= AP.maxSizeImage) {
								
								 AP.uplaodSolMediaFile(AP.baseURL + "media",
										"POST", formData, solId, "VIDEO");
								
								$("#sol_image"+Id).html("");
							} else {
								$("#sol_image"+Id)
										.html(
												"<h4>1)File Size Should Not exceeds "+AP.maxSizeVideo+" MB. <br> 2)supported file formats - '.mpeg' ,'.mp4 '</h4>");
								$("#sol_image"+Id).css('display', 'block');
								$('#'+solId).val(null);
								// alert("File size must under 10 mb!");

							}

					}else{
						alert("Please select video file");
					}
				}
				
			}
			
			
			$('#reset').on('click', function() {
				
				$('.radioType').prop('checked',false);
				$("#solutionDiv"+cnt).html("");
				
			});	
			
			
			$("#ansType" + cnt + " option")
					.filter(
							function() {
								ansTypeId = "ansType" + cnt;

								if (questionGruopJSON.questions[i].answerType.answerTypeId == '1') {
									AP.scaAnswerType(
											questionGruopJSON.questions[i].answers,
											Id);
								} else if (questionGruopJSON.questions[i].answerType.answerTypeId == '2') {
									AP.fibAnswerType(
											questionGruopJSON.questions[i].answers,
											Id);
								} else if (questionGruopJSON.questions[i].answerType.answerTypeId == '3') {
									AP.mtpAnswerType(
											questionGruopJSON.questions[i].answers,
											Id);
								} else if (questionGruopJSON.questions[i].answerType.answerTypeId == '4') {
									AP.tfAnswerType(
											questionGruopJSON.questions[i].answers,
											Id);
								}else if (questionGruopJSON.questions[i].answerType.answerTypeId == '5') {
									AP.mcaAnswerType(
											questionGruopJSON.questions[i].answers,
											Id);
								}else if (questionGruopJSON.questions[i].answerType.answerTypeId == '6') {
									AP.scaImgAnswerType(
											questionGruopJSON.questions[i].answers,
											Id);
								}else if (questionGruopJSON.questions[i].answerType.answerTypeId == '7') {
//									AP.tfImgAnswerType(
//											questionGruopJSON.questions[i].answers,
//											Id);  // Image - true or false, may use in future
									AP.mtpImgAnswerType(
											questionGruopJSON.questions[i].answers,
											Id);
								}else if (questionGruopJSON.questions[i].answerType.answerTypeId == '8') {
									AP.mcaImgAnswerType(
											questionGruopJSON.questions[i].answers,
											Id);
								}
								return $(this).val() == questionGruopJSON.questions[i].answerType.answerTypeId;
							}).attr('selected', true);


			$("#defTime" + cnt ).val(questionGruopJSON.questions[i].time);
			
			$("textarea#quetSolText" + cnt).text(questionGruopJSON.questions[i].solText);
			
			var solHTM = ''; 
			
			if (questionGruopJSON.questions[i].solType == "IMAGE") {
				
				$("#solMediaImg"+cnt).prop('checked',true);
				solHTM +='<input  type="file"  id="questImgSol'+cnt+'" class="questSol solClass" title="Upload Solution File" col="8" sol-media="'+questionGruopJSON.questions[i].solMedia+'" onchange="com.coep.test.addProblem.onEditSelSolutionFile(this.id,'+cnt+')" required><i class="fa fa-asterisk asteriskForInput" aria-hidden="true"></i>'
				+ '<div class="invalid-feedback questSol'+cnt+'">Please select solution file.</div>'
				+ '<div id="sol_image'+cnt+'" class="red-color"></div>'
				+'<div id="sol-preview-div"></div>'
				
				$("#solutionDiv"+cnt).html(solHTM);
				$("#solutionDiv"+cnt).removeClass("hidden");
				
				var selSolId = "sol_image"+cnt;
				$(".questSol").hide();
				$("#repalceSolBtn").show();
				
				AP.previewSolClick(questionGruopJSON.questions[i].solMedia, "IMAGE" );
				
			}else if (questionGruopJSON.questions[i].solType == "AUDIO") {
				$("#solMediaAud"+cnt).prop('checked',true);
				solHTM +='<input  type="file"  id="questAudSol'+cnt+'" class="questSol solClass" title="Upload Solution File" col="8" sol-media="'+questionGruopJSON.questions[i].solMedia+'" onchange="com.coep.test.addProblem.onEditSelSolutionFile(this.id,'+cnt+')" required><i class="fa fa-asterisk asteriskForInput" aria-hidden="true"></i>'
				+ '<div class="invalid-feedback questSol'+cnt+'">Please select solution file.</div>'
				+ '<div id="sol_image'+cnt+'" class="red-color"></div>'
				+'<div id="sol-preview-div"></div>'
				
				$("#solutionDiv"+cnt).html(solHTM);
				$("#solutionDiv"+cnt).removeClass("hidden");
				
				var selSolId = "sol_image"+cnt;
				$(".questSol").hide();
				$("#repalceSolBtn").show();
				AP.previewSolClick(questionGruopJSON.questions[i].solMedia, "AUDIO" );
				
			}else if (questionGruopJSON.questions[i].solType == "VIDEO") {
				$("#solMediaVid"+cnt).prop('checked',true);
				solHTM +='<input  type="file"  id="questVidSol'+cnt+'" class="questSol solClass" title="Upload Solution File" col="8" sol-media="'+questionGruopJSON.questions[i].solMedia+'" onchange="com.coep.test.addProblem.onEditSelSolutionFile(this.id,'+cnt+')" required><i class="fa fa-asterisk asteriskForInput" aria-hidden="true"></i>'
				+ '<div class="invalid-feedback questSol'+cnt+'">Please select solution file.</div>'
				+ '<div id="sol_image'+cnt+'" class="red-color"></div>'
				+'<div id="sol-preview-div"></div>'
				
				$("#solutionDiv"+cnt).html(solHTM);
				$("#solutionDiv"+cnt).removeClass("hidden");
				
				var selSolId = "sol_image"+cnt;
				$(".questSol").hide();
				$("#repalceSolBtn").show();
				AP.previewSolClick(questionGruopJSON.questions[i].solMedia, "VIDEO" );
				
			}
			
			
			AP.onEditSelSolutionFile = function(solId, Id) {
				
				var file = $('#'+solId);
				var filename = $.trim(file.val());
				
				if(file != "" && filename != ""){
					
					var formData = new FormData();
					formData.append('file', $('#'+solId)[0].files[0]);

					var fileSize = ($('#'+solId)[0].files[0].size / 1024 / 1024)

					var valid_extensions = /(\.png|\.jpeg|\.jpg)$/i;
					if (filename != null || filename != undefined)
						if (valid_extensions.test(filename)
								&& fileSize <= AP.maxSizeImage) {
							
							 AP.uplaodSolMediaFile(AP.baseURL + "media",
									"POST", formData, solId);
							
							$("#sol_image"+Id).html("");
							
						} else {
							$("#sol_image"+Id)
									.html(
											"<h4> 1)File Size Should Not exceeds "+AP.maxSizeImage+" MB. <br> 2)supported file formats -  '.png' ,'.jpeg ','.jpg'</h4>");
							$("#sol_image"+Id).css('display', 'block');
							$('#'+solId).val(null);
						}

				}else{
					alert("Please select image file");
				}
			}
			
			var selModSolution = $("input:radio[name=solutionGroup"+cnt+"]");
			
			selModSolution.on("change", function() {
				
				 selectedModSol_Id = $('input[name="solutionGroup'+cnt+'"]:checked').attr('id');

				 var solModHTM = '';
				
				if (selectedModSol_Id == "solMediaImg"+cnt) {
					$("#solutionDiv"+cnt).removeClass("hidden");
					solModHTM +='<input  type="file"  id="questImgSol'+cnt+'" class="questSol solClass" title="Upload Solution File" col="8" sol-media=""  onchange="com.coep.test.addProblem.onSelSolutionFile(this.id,'+cnt+')" required>'
						+ '<div class="invalid-feedback questSol'+cnt+'">Please select solution file.</div>'
						+ '<div id="sol_image'+cnt+'" class="red-color"></div>'
						+'<div id="sol-preview-div"></div>'
					$("#solutionDiv"+cnt).html(solModHTM);
				} else if (selectedModSol_Id == "solMediaAud"+cnt) {

					$("#solutionDiv"+cnt).removeClass("hidden");
					solModHTM +='<input  type="file"  id="questAudSol'+cnt+'" class="questSol solClass" title="Upload Solution File" col="8" sol-media=""  onchange="com.coep.test.addProblem.onSelSolutionFile(this.id,'+cnt+')" required>'
						+ '<div class="invalid-feedback questSol'+cnt+'">Please select solution file.</div>'
						+ '<div id="sol_image'+cnt+'" class="red-color"></div>'
						+'<div id="sol-preview-div"></div>'
					$("#solutionDiv"+cnt).html(solModHTM);
					
				}else if (selectedModSol_Id == "solMediaVid"+cnt) {

					$("#solutionDiv"+cnt).removeClass("hidden");
					solModHTM +='<input  type="file"  id="questVidSol'+cnt+'" class="questSol solClass" title="Upload Solution File" col="8" sol-media=""  onchange="com.coep.test.addProblem.onSelSolutionFile(this.id,'+cnt+')" required>'
						+ '<div class="invalid-feedback questSol'+cnt+'">Please select solution file.</div>'
						+ '<div id="sol_image'+cnt+'" class="red-color"></div>'
						+'<div id="sol-preview-div"></div>'
					$("#solutionDiv"+cnt).html(solModHTM);
					
				}   else {
					
					$("#solutionDiv"+cnt).html("");
				}

			});
			

		} else {
			 alert("Please save current question/s");
//			$("#cdlgBody")
//					.html(
//							'<a href="#" class="primary-blue" class="close" data-dismiss="modal"><h4>Please save this/current question</h4></a>');
//			$("#cdialog").modal({
//				show : true
//			});
		}
	}
	
	
	
	AP.saveQues = function(saveQuesId, questionGruopJSON) {
		
		var regex = /\d+/;
		var divId = saveQuesId;

		var completeId = divId.match(regex);
		var Id = completeId[0];
		
		
	if (saveValidation(Id)) { 
		
		mcacorransinpcnt = 1;
		mcaincansinpcnt = 1;
		fibcorransinpcnt = 1;
		fibincansinpcnt = 1;
		mtpcorranstxtcnt = 1;
		mtpincanstxtcnt = 1;
		scacorransinpcnt = 1;
		scaincansinpcnt = 1;
		scaimgcorransinpcnt = 1;
		scaimgincansinpcnt = 1;
		mtpimgcorranstxtcnt = 1;  
		mtpimgincanstxtcnt = 1; 
		mcaimgcorransinpcnt = 1; 
		mcaimgincansinpcnt = 1; 
		
		$("#modiftyQGroupBtn").show();
		
		var quesGrpName = $('#qgrpName').val();

		topicArr = [];
		for (var ll = 0; ll < topicIdArr.length; ll++) {
			var topicId = topicIdArr[ll];
			var topic = {};
			topic.topicId = topicId;
			topicArr.push(topic);
		}
		
		var quesType = $('#quesType option:selected').attr('id');
		var mediaTypeId = quesType;
		var content = '';
		var type = $('#quesType option:selected').text();
		
		
		if (quesType == 1) {
			content = $('#quesGroupTxtNoFile').val();
			quesGroupMediaLinks.mediaURLText = content;
		}else{
			quesGroupMediaLinks.mediaURLText = $("#queslink").attr("qg-media");
		}


		quesGroupMediaLinks.quesUsage = "question";
		
		var solText =  $("#quetSolText"+Id).val();	
		
		var selectedSolId = $('input[name="solutionGroup'+Id+'"]:checked').attr('id'); // to get solution type text or image
		
		var solType = null;
		var questSolMedia = null;	
	
		
		if (selectedSolId == "solMediaImg"+Id) {
			
			solType = "IMAGE";
			questSolMedia = $("#questImgSol"+Id).attr("sol-media"); 
			
		}else if (selectedSolId == "solMediaAud"+Id) {
			
			solType = "AUDIO";
			questSolMedia = $("#questAudSol"+Id).attr("sol-media");
		}else if (selectedSolId == "solMediaVid"+Id) {
			
			solType = "VIDEO";
			questSolMedia = $("#questVidSol"+Id).attr("sol-media");
		}
		
		
		var level = $('#diffLevel option:selected').val(); // difficulty level
		var subjectId = $('#selectSubject option:selected').val();
		
		var quesText = $("#questText" + Id).val();
		var quesTime = $('#defTime' + Id ).val();
		
		var answerTypeId = $('#ansType' + Id + ' option:selected').val();
		
		mediaType.mediaTypeId = mediaTypeId;
		subject.subjectId = subjectId;
		
		var quesTxtContent = quesText;

		var tempValues = {};
		
		if(questionGruopJSON.questions != undefined){
			var answerType = {};
			answerType.answerTypeId = answerTypeId;
			questions = questionGruopJSON.questions;
		}else{
			var answerType = {};
			answerType.answerTypeId = answerTypeId;
		}
		
		var answers = [];
		var media = null; // this is for answer type whether it has image options or text
		
		if (answerTypeId == '6' || answerTypeId == '7' || answerTypeId == '8') {
			 media = "IMAGE";
		}else {
			media = "TEXT";
		}

		$(".correctAnsBlk").each(function() {
			var th = $(this);
			if(answerTypeId == "6"  || answerTypeId == '7' || answerTypeId == '8'){
				
				tempValues[th.attr('img-media')] = th.attr('img-media');
				answers.push({
					"content" : tempValues[th.attr('img-media')],
					"rightAnswer" : true,
					"media" : media
				})
			}else{
				tempValues[th.attr('id')] = th.val();
				answers.push({
					"content" : tempValues[th.attr('id')],
					"rightAnswer" : true,
					"media" : media
				})
			}
			
		});

		$(".inCorrectAnsBlk").each(function() {
			var th = $(this);
			
			if(answerTypeId == "6"  || answerTypeId == '7' || answerTypeId == '8'){
				tempValues[th.attr('img-media')] = th.attr('img-media');
				answers.push({
					"content" : tempValues[th.attr('img-media')],
					"rightAnswer" : false,
					"media" : media
				})
			}else{
				tempValues[th.attr('id')] = th.val();
				answers.push({
					"content" : tempValues[th.attr('id')],
					"rightAnswer" : false,
					"media" : media
				})
			}
			
		});
		

		questions.push({
			"content" : quesTxtContent,
			"solText" : solText,
			"solMedia" : questSolMedia,
			"solType" : solType,
			"time" : quesTime,
			"answerType" : answerType,
			"answers" : answers
		})

		questionGruopJSON.name = quesGrpName;
		
		questionGruopJSON.topic = topicArr;

		if(AP.QGMLID != null){
			quesGroupMediaLinks.quesGroupMediaLinkId = AP.QGMLID;
		}
		questionGruopJSON.quesGroupMediaLinks = quesGroupMediaLinks;
		
		questionGruopJSON.type = type;
		
		questionGruopJSON.subject = subject;
		questionGruopJSON.mediaType = mediaType;
		questionGruopJSON.level = level;
		questionGruopJSON.questions = questions;

		console.log(questionGruopJSON);
		console.log(JSON.stringify(questionGruopJSON));
		AP.addToQuestionGroup(AP.baseURL + "questionGroups", "PUT",
				questionGruopJSON, AP.mainData, AP.backBtnStatus);
		}
	}
	
	
	
	AP.saveModifiedQuestion = function(id, questionGruopJSON, i) {
		
		var Id = extractNum(id);
		var quesSolValidate = null;
		
		if($.trim($("#sol-preview-div").html()) != ''){
			quesSolValidate = "1";
		}else{
			quesSolValidate = "2";
		}
			
		if (saveQuestionGroupValidations()) {
			
		if (saveModifiedValidation(Id, quesSolValidate)) {  // not done yet

			var regex = /\d+/;
			var divId = id;
			var completeId = divId.match(regex);
			var Id = completeId[0];

			$("#modiftyQGroupBtn").show();
			
			var quesGrpName = $('#qgrpName').val();
			
			topicArr = [];
			for (var ii = 0; ii < topicIdArr.length; ii++) {
				var topicId = topicIdArr[ii];
				var topic = {};
				topic.topicId = topicId;
				topicArr.push(topic);
			}
			
			var quesType = $('#quesType option:selected').attr('id');
			var mediaTypeId = quesType;
			var content = '';
			var type = $('#quesType option:selected').text();
			
			if (quesType == 1) {
				content = $('#quesGroupTxtNoFile').val();
				quesGroupMediaLinks.mediaURLText = content;
			}else{
				quesGroupMediaLinks.mediaURLText = $("#queslink").attr("qg-media");
			}

			quesGroupMediaLinks.quesUsage = "question";
		
			var solText =  $("#quetSolText"+Id).val();
			
			var selectedSolId = $('input[name="solutionGroup'+Id+'"]:checked').attr('id'); // to get solution type text or image
			
			var solType = null;
			var questSolMedia = null;	
			
			if (selectedSolId == "solMediaImg"+Id) {
				
				solType = "IMAGE";
				questSolMedia = $("#questImgSol"+Id).attr("sol-media"); 
				
			}else if (selectedSolId == "solMediaAud"+Id) {
				
				solType = "AUDIO";
				questSolMedia = $("#questAudSol"+Id).attr("sol-media");

			}else if (selectedSolId == "solMediaVid"+Id) {
				
				solType = "VIDEO";
				questSolMedia = $("#questVidSol"+Id).attr("sol-media");
			}
			
			var level = $('#diffLevel option:selected').val(); // difficulty level
			
			var subjectId = $('#selectSubject option:selected').val();
			
			var quesText = $("#questText" + Id).val();
			var quesTime = $('#defTime' + Id ).val();
			
			var quesTxtContent = quesText;

			var answerTypeId = $('#ansType' + Id + ' option:selected').val();

			mediaType.mediaTypeId = mediaTypeId;
			subject.subjectId = subjectId;
			
			var tempValues = {};

			var modifiedAnswers = [];
			
			
			var media = null; // this is for answer type whether it has image options or text
			
			if (answerTypeId == '6' || answerTypeId == '7' || answerTypeId == '8') {
				 media = "IMAGE";
			}else {
				media = "TEXT";
			}

			
			$(".correctAnsBlk").each(function() {
				var th = $(this);
				if(answerTypeId == "6" || answerTypeId == '7' || answerTypeId == '8'){
					tempValues[th.attr('img-media')] = th.attr('img-media');
						modifiedAnswers.push({
							"content" : tempValues[th.attr('img-media')],
							"rightAnswer" : true,
							"media" : media
						})
				}else{
					tempValues[th.attr('id')] = th.val();
					modifiedAnswers.push({
						"content" : tempValues[th.attr('id')],
						"rightAnswer" : true,
						"media" : media
					})
				}
			});

			$(".inCorrectAnsBlk").each(function() {
				var th = $(this);
				if(answerTypeId == "6" || answerTypeId == '7' || answerTypeId == '8'){
					tempValues[th.attr('img-media')] = th.attr('img-media');
						modifiedAnswers.push({
							"content" :tempValues[th.attr('img-media')],
							"rightAnswer" : false,
							"media" : media
						})
				 }else{
					 tempValues[th.attr('id')] = th.val();
						modifiedAnswers.push({
							"content" : tempValues[th.attr('id')],
							"rightAnswer" : false,
							"media" : media
						})
				 }
			});

			var modifiedQuestion = [];

			if (questionGruopJSON.questions != undefined) {
				if (questionGruopJSON.questions[i] == undefined) {
					
					var answerType = {};
					answerType.answerTypeId = answerTypeId;
					
					modifiedQuestion = questionGruopJSON.questions;

					modifiedQuestion.push({
						"content" : quesTxtContent,
						"solText" : solText,
						"solMedia" : questSolMedia,
						"solType" : solType,
						"time" : quesTime,
						"answerType" : answerType,
						"answers" : modifiedAnswers
					});

				} else {
					
					var answerType = {};
					answerType.answerTypeId = answerTypeId;
					
					questionGruopJSON.questions[i].content = quesTxtContent;
					questionGruopJSON.questions[i].solText = solText;
					questionGruopJSON.questions[i].solMedia = questSolMedia;
					questionGruopJSON.questions[i].solType = solType;
					questionGruopJSON.questions[i].time = quesTime;
					questionGruopJSON.questions[i].answerType = answerType;
					questionGruopJSON.questions[i].answers = modifiedAnswers;

					modifiedQuestion = questionGruopJSON.questions;

				}
			} else {
				
				var answerType = {};
				answerType.answerTypeId = answerTypeId;
				
				modifiedQuestion.push({
					"content" : quesTxtContent,
					"solText" : solText,
					"solMedia" : questSolMedia,
					"time" : quesTime,
					"answerType" : answerType,
					"answers" : modifiedAnswers
				});
			}

			var varNo =  $('#varNo').val();
			questionGruopJSON.varNo = varNo; 
			
			questionGruopJSON.name = quesGrpName;
			
			questionGruopJSON.topic = topicArr;
			
			if(AP.QGMLID != null){
				quesGroupMediaLinks.quesGroupMediaLinkId = AP.QGMLID;
			}
			questionGruopJSON.quesGroupMediaLinks = quesGroupMediaLinks;
			
			questionGruopJSON.type = type;
			
			questionGruopJSON.subject = subject;
			questionGruopJSON.mediaType = mediaType;
			questionGruopJSON.level = level;
			questionGruopJSON.questions = modifiedQuestion;

			console.log(questionGruopJSON);
			AP.addToQuestionGroup(AP.baseURL + "questionGroups", "PUT",
					questionGruopJSON, AP.mainData, AP.backBtnStatus);

			}
		}
	}
	
	
	
	
	AP.goBackBtn = function() {
		if(AP.backBtnStatus == "Active"){
			$("#backFunction").show();
			com.coep.test.questionBank.getQuestionBankOfUser('Active');
//			AP.backBtnStatus = "";
		}else if(AP.backBtnStatus == "Archived"){
			$("#backFunction").show();
			com.coep.test.questionBank.getQuestionBankOfUser('Archived');
//			AP.backBtnStatus = "";
		}if(AP.backBtnStatus == "Active-All"){
			$("#backFunction").show();
			com.coep.test.questionBank.getAllQuestionsFromQuesBank('Active');
//			AP.backBtnStatus = "";
		}else if(AP.backBtnStatus == "Archived-All"){
			$("#backFunction").show();
			com.coep.test.questionBank.getAllQuestionsFromQuesBank('Archived');
//			AP.backBtnStatus = "";
		}else if(AP.backBtnStatus == "Non-Approved"){
			$("#backFunction").show();
			com.coep.test.questionBank.getQuestionsToApprove('Non-Approved', AP.backBtnQuesId, AP.pageIndexNo);
//			AP.backBtnStatus = "";
		}else if(AP.backBtnStatus == "Approved"){
			$("#backFunction").show();
			com.coep.test.questionBank.getQuestionsToApprove('Approved', AP.backBtnQuesId, AP.pageIndexNo);
//			AP.backBtnStatus = "";
		}
	}
	
	function bsSelectValidation() {
	 if ($("#QuestionGroupDataForm").hasClass('was-validated') ) {
		    $(".selectpicker").each(function (i, el) {
		      if ($(el).is(":invalid")) {
		        $(el).closest(".form-group").find(".valid-feedback").removeClass("d-block");
		        $(el).closest(".form-group").find(".invalid-feedback").addClass("d-block");
		      }
		      else {
		        $(el).closest(".form-group").find(".invalid-feedback").removeClass("d-block");
		        $(el).closest(".form-group").find(".valid-feedback").addClass("d-block");
		      }
		    });
		  }
	}
	
	
	
	

	
	
	
	
	
})(com.coep.test.ajaxHandler, com.coep.test.addProblem, com.coep.test.AlertMessage, com.coep.test.mathJax);