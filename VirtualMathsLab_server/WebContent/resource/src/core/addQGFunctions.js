(function(AH, AP) {
	
	// add question prerequisite data
	
	 AP.mainData = {};

	 AP.backBtnStatus = "";
	 AP.backBtnQuesId = "";
	 AP.pageIndexNo  = 0;
	 
	 questionGruopJSON = {};
	 
//	 topic = {};
	 topicArr = [];
	 quesGroupMediaLinks = {};
	 subject = {};
	 mediaType = {};
	 questions = [];
	 answerType = {};
	 answers = [];
	 
	 
	 AP.resetAnswerTypeCnt = function() {
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
	}
	 
	   
	AP.getLevelDetailsToAddQuestionGroup = function() {
		
		
		var allData = {};
		var subData = {};
//		var	level1Data = {};
		var	topicData = {};
		var compLevelData = {};
		var mediaTypeData = {};
		var	ansTypeData = {};
		
		 questionGruopJSON = {};
		 
//		 topic = {};
		 topicArr = [];
		 quesGroupMediaLinks = {};
		 subject = {};
		 mediaType = {};
		 questions = [];
		 answers = [];
		 answerType = {};

		
		AP.maxSizeAudio = 3;
		AP.maxSizeVideo = 10;
		AP.maxSizeImage = 3;
		AP.mediaURL = null;
		AP.mediaSolURL = null;
		AP.mediaOptURL = null;
		AP.QGMLID = null;
		// ajax call to get subject
		$.ajax({
			type : "GET",
			url : AP.baseURL
					+ "subject/api/get/subjects",
			// data : JSON.stringify(L1Json),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if(data.done == true){
					subData = data.subData;
					allData.subData = subData;
					getTopicList();
				}
			},
			error : function() {
			}

		});

		
		// ajax call to get all topic list
		
		
		function getTopicList() {
			$.ajax({
				type : "GET",
				url : com.coep.test.addProblem.baseURL
						+ "topic/api/get/topics",
				dataType : 'json',
				contentType : 'application/json',
				success : function(data) {
					topicData = data.topicData;
					allData.topicData = topicData;
					allData.userId = data.UID;
					getComplexityLevels(allData);
				},
				error : function() {
				}

			});
		}
		
		
			// ajax call to get complexityLevels	
		
		function getComplexityLevels(allData) {
			
			$("#resultLoading").remove();
			
			$.ajax({
				type : "GET",
				url : AP.baseURL + "complexitylevel",
				dataType : 'json',
				contentType : 'application/json',
				success : function(data) {
					if (data.done == true) {
						compLevelData = data.data;
						allData.compLevelData = compLevelData;
						getAnsTypeData(allData);
					} else {
						// AP.showCustomAlert("Error", data.msg);
					}
				},
				error : function() {
				}
	
			   });
			}
		
		
		
		
		// ajax call to get anser types
		
		function getAnsTypeData() {
			$("#resultLoading").remove();

			$.ajax({
				type : "GET",
				url : AP.baseURL + "answerType",
				// data : JSON.stringify(L1Json),
				dataType : 'json',
				contentType : 'application/json',
				success : function(data) {
					if(data.done == true){
						ansTypeData = data.data;
						allData.ansTypeData = ansTypeData;
						getMediaType(allData);
					}
				},
				error : function() {
				}

			  });

			}
		// ajax call to get mediaType
		
		function getMediaType(allData) {
			
			$("#resultLoading").remove();
			
				$.ajax({
					type : "GET",
					url : AP.baseURL + "mediaType",
					dataType : 'json',
					contentType : 'application/json',
					success : function(data) {
						if (data.done == true) {
							mediaTypeData = data.data;
							allData.mediaTypeData = mediaTypeData;
							AP.getAllData(allData); 
						} else {
							// AP.showCustomAlert("Error", data.msg);
						}
					},
					error : function() {
					}
		
				});
     		}
	
	}
	
	AP.getAllData = function(allData) {
		
		AP.mainData = allData;
		AP.addQuestionGroup(allData);
		
	}
	
	AP.onSelectTopic = function() {
		
		$(".step7").html("");
		$(".step7a").html("");
		$(".step8").html("");
		$(".step7b").html("");
		$(".step9").hide();
		
		AP.renderQuesType();
	}

	
	
	AP.renderQuesType = function() {
		
		if (AP.mainData.mediaTypeData.length != 0) {
			
			$(".step7").show();
			$(".step7a").html("");
			$(".step7b").html("");
			$(".step8").html("");
			$(".step9").hide();
			
			var htm = '' 
			+'<label class="col-xl-12 col-md-12 col-sm-12" for="selectQuesType">Select Type of Question <span class="marathi-text" >(प्रश्नाचा प्रकार निवडा )</span><i class="fa fa-asterisk " aria-hidden="true"></i></label>'
			+ ' <select class=" form-control col-xl-12 col-md-12 col-sm-12 selectpicker" data-live-search="true" id="quesType"  onchange=\"com.coep.test.addProblem.onSelectQuestionType()\"" required>'
			+ ' <option value="0" disabled selected>Choose Question Type</option>'

			for (var i = 0; i < AP.mainData.mediaTypeData.length; i++) {
				htm += '' + '  <option value="' + AP.mainData.mediaTypeData[i].MTID
						+ '" id ="' + AP.mainData.mediaTypeData[i].MTID + '">'
						+ AP.mainData.mediaTypeData[i].MTN + ' </option>'
			}

			htm += ' </select>'
				
			$("#selectQuesType").html(htm);
			bsSelectValidation();
			$('select').selectpicker();
			$('#selectQuesType').change(bsSelectValidation);
			
			if(!jQuery.isEmptyObject(questionGruopJSON)){
				if(questionGruopJSON.mediaType.mediaTypeId != undefined ){
					$("#quesType").val(questionGruopJSON.mediaType.mediaTypeId).change();
					 $('#quesType').prop('disabled', true);
				}
			}
			
		}else{
			
			$(".step7a").html("");
			$(".step7b").html("");
			$(".step8").html("");
			$(".step9").hide();
			if(jQuery.isEmptyObject(questionGruopJSON)){
				alert(data.msg)
			}
		}
	
	}
	
	AP.onSelectQuestionType = function(){
		quesTypeId = $.trim($("#quesType option:selected").attr('id'));
		
		if (quesTypeId == "1") {
			
				AP.renderQuesGroupReference();
			
		}else if (quesTypeId == "2") {
				
				AP.renderAudioQuesFile(quesTypeId);
			
		}else if (quesTypeId == "3") {
			
				AP.renderVideoQuesFile(quesTypeId);
		
		}else if (quesTypeId == "4") {
			
				AP.renderImageQuesFile(quesTypeId);
			
		}
	}

	
	AP.renderQuesGroupReference = function() {
		
		$(".step7a").show();
		$(".step7b").html("");
		$(".step9").hide();
		
		var htm = '' 
			
		htm +=  '<label class="col-xl-12 col-md-12 col-sm-12">Reference<span class="marathi-text"> (If Required)</sapn></label>'
		+ '<textarea id="quesGroupTxtNoFile"  placeholder="If there are multiple questions based on the one big text then enter that text here..\nएका मोठ्या मजकूरावर आधारित अनेक प्रश्न असल्यास ते मजकूर येथे प्रविष्ट करा." title="Reference" col="8" class="form-control textarea-class"></textarea>'
		+ '<h5 style="float: right;font-size:13px;margin-top: 5px;color: #de4b4b;">( Character limit of 1500 characters ) </h5>'
		
		$("#nonMediaQuesDiv").html(htm);
		
		if(!jQuery.isEmptyObject(questionGruopJSON)){
			if(questionGruopJSON.mediaType.mediaTypeId != undefined &&  questionGruopJSON.mediaType.mediaTypeId == 1){
				if(questionGruopJSON.quesGroupMediaLinks.mediaURLText != " ")
				$("#quesGroupTxtNoFile").val(questionGruopJSON.quesGroupMediaLinks.mediaURLText).change();
			}
		}
		
		AP.renderDiffLevel();
	}
	
	
	
	AP.renderAudioQuesFile = function(quesTypeId) {
		$(".step7a").html("");
		$(".step8").html("");
		$(".step7b").show();
		$(".step9").hide();
		
		var htm = '' 
		htm += '<div id="fileUplaod">' 
			+ '<label class="col-xl-12 col-md-12 col-sm-12">Select Audio File <span class="marathi-text" >(ऑडिओ फाइल निवडा)</span><i class="fa fa-asterisk " aria-hidden="true"></i></label>'
			+ '<input id="queslink" type="file" name="audioReference" title="Upload File" col="8" style="" onchange=\"com.coep.test.addProblem.onSelectAudioFile('+quesTypeId+')\"" class="form-control select-class /" required>'
			+ '<div id="error" class="invalid-feedback queslink_audio1">Please select audio file.</div>'
			+ '<div id="error_audio" class="invalid-feedback queslink_audio2"></div>'
			+ '</div>'
			+'<div id="preview-div"></div>'
			
		$("#mediaQuesDiv").html(htm);
		
		if(!jQuery.isEmptyObject(questionGruopJSON)){
			if(questionGruopJSON.mediaType.mediaTypeId == quesTypeId && questionGruopJSON.quesGroupMediaLinks.mediaURLText != null && questionGruopJSON.quesGroupMediaLinks.mediaURLText != undefined){
				
				$("#fileUplaod").hide();
				$("#repalceBtn").show();
				$("#queslink").attr("qg-media",questionGruopJSON.quesGroupMediaLinks.mediaURLText); 
				AP.previewClick(questionGruopJSON.quesGroupMediaLinks.mediaURLText, questionGruopJSON.mediaType.mediaTypeId);
				AP.renderDiffLevel();
			}
		}
	}
	
	AP.onSelectAudioFile = function(quesTypeId) {
		
		var file = $('#queslink');
		var filename = $.trim(file.val());
		
		if(file != "" && filename != ""){
			
			var formData = new FormData();
			formData.append('file', $('#queslink')[0].files[0]);

			var fileSize = ($('#queslink')[0].files[0].size / 1024 / 1024)

			var valid_extensions = /(\.mp3|\.wav)$/i;
			if (filename != null || filename != undefined) {
				if (valid_extensions.test(filename)
						&& fileSize <= AP.maxSizeAudio) {
					
					AP.uplaodMediaFile(AP.baseURL + "media",
							"POST", formData, quesTypeId);
					$("#error_audio").html("");
					AP.renderDiffLevel();
				} else {
					$("#error_audio")
							.html(
									"<h4> 1)File Size Should Not exceeds  "+AP.maxSizeAudio+" MB. <br> 2)supported file formats - '.mp3' ,'.wav'</h4>");
					$("#error_audio").css('display', 'block');
					// alert("File size must under 3mb!");

				}
			}
			
		}else{
			$(".step8").hide();
		}
	}
	
	AP.renderVideoQuesFile = function(quesTypeId) {
		$(".step7a").html("");
		$(".step8").html("");
		$(".step7b").show();
		$(".step9").hide();
		
		var htm = '' 
			
		htm +=  '<div id="fileUplaod">' 
			+ '<label class="col-xl-12 col-md-12 col-sm-12">Select Video File <span class="marathi-text" >(व्हिडिओ फाइल निवडा)</span><i class="fa fa-asterisk " aria-hidden="true"></i></label>'
			+ '<input id="queslink" type="file" name="videoReference" title="Upload File" col="8" style=""  onchange=\"com.coep.test.addProblem.onSelectVideoFile('+quesTypeId+')\"" class="form-control select-class /" required>'
			+ '<div id="error" class="invalid-feedback queslink_video1">Please select video file.</div>'
			+ '<div id="error_video" class="invalid-feedback queslink_video2"></div>'
			+ '</div>'
			+'<div id="preview-div"></div>'
		$("#mediaQuesDiv").html(htm);
		
		if(!jQuery.isEmptyObject(questionGruopJSON)){
			if(questionGruopJSON.mediaType.mediaTypeId == quesTypeId && questionGruopJSON.quesGroupMediaLinks.mediaURLText != null && questionGruopJSON.quesGroupMediaLinks.mediaURLText != undefined){
				$("#fileUplaod").hide();
				$("#repalceBtn").show();
				$("#queslink").attr("qg-media",questionGruopJSON.quesGroupMediaLinks.mediaURLText); 
				AP.previewClick(questionGruopJSON.quesGroupMediaLinks.mediaURLText, questionGruopJSON.mediaType.mediaTypeId);
				AP.renderDiffLevel();
			}
		}
	}
	
	AP.onSelectVideoFile = function(quesTypeId) {
		
		var file = $('#queslink');
		var filename = $.trim(file.val());
		
		if(file != "" && filename != ""){
			
			var formData = new FormData();
			formData.append('file', $('#queslink')[0].files[0]);

			var fileSize = ($('#queslink')[0].files[0].size / 1024 / 1024)
			
			var valid_extensions = /(\.mp4|\.mpeg)$/i;
				if (filename != null || filename != undefined)
					if (valid_extensions.test(filename)
							&& fileSize <= AP.maxSizeVideo) {
					AP.uplaodMediaFile(AP.baseURL + "media",
							"POST", formData, quesTypeId );
					$("#error_video").html("");
					AP.renderDiffLevel();
				} else {
					$("#error_video")
							.html(
									"<h4> 1)File Size Should Not exceeds "+AP.maxSizeVideo+" MB. <br> 2)supported file formats - '.mpeg' ,'.mp4 '</h4>");
					$("#error_video").css('display', 'block');
					// alert("File size must under 10 mb!");
				}
			
			
		}else{
			$(".step8").html("");
		}
	}
	
	AP.renderImageQuesFile = function(quesTypeId) {
		$(".step7a").html("");
		$(".step8").html("");
		$(".step7b").show();
		$(".step9").hide();
		
		var htm = '' 
			
		htm +=  '<div id="fileUplaod">'
			+'<label class="col-xl-12 col-md-12 col-sm-12">Select Image File <span class="marathi-text" >(इमेज फाइल निवडा)</span><i class="fa fa-asterisk " aria-hidden="true"></i></label>'
			+ '<input id="queslink" type="file" name="imageReference" title="Upload File" col="8" style="" onchange=\"com.coep.test.addProblem.onSelectImageFile('+quesTypeId+')\"" class="form-control select-class /" required>'
			+ '<div id="error" class="invalid-feedback queslink_img1">Please select image file.</div>'
			+ '<div id="error_image" class="invalid-feedback queslink_img2"></div>'
			+ '</div>'
			+'<div id="preview-div"></div>'
		$("#mediaQuesDiv").html(htm);
		
		if(!jQuery.isEmptyObject(questionGruopJSON)){
			if(questionGruopJSON.mediaType.mediaTypeId == quesTypeId && questionGruopJSON.quesGroupMediaLinks.mediaURLText != null && questionGruopJSON.quesGroupMediaLinks.mediaURLText != undefined){
				$("#fileUplaod").hide();
				$("#repalceBtn").show();
				$("#queslink").attr("qg-media",questionGruopJSON.quesGroupMediaLinks.mediaURLText); 
				AP.previewClick(questionGruopJSON.quesGroupMediaLinks.mediaURLText, questionGruopJSON.mediaType.mediaTypeId);
				AP.renderDiffLevel();
			}
		}
	}
	
	AP.onSelectImageFile = function(quesTypeId) {
			
			var file = $('#queslink');
			var filename = $.trim(file.val());
			
			if(file != "" && filename != ""){
				
				var formData = new FormData();
				formData.append('file', $('#queslink')[0].files[0]);

				var fileSize = ($('#queslink')[0].files[0].size / 1024 / 1024)

				var valid_extensions = /(\.png|\.jpeg|\.jpg)$/i;
				if (filename != null || filename != undefined)
					if (valid_extensions.test(filename)
							&& fileSize <= AP.maxSizeImage) {
						
						 AP.uplaodMediaFile(AP.baseURL + "media",
								"POST", formData, quesTypeId);
						
						$("#error_image").html("");
						AP.renderDiffLevel();
					} else {
						$("#error_image")
								.html(
										"<h4> 1)File Size Should Not exceeds "+AP.maxSizeImage+" MB. <br> 2)supported file formats -  '.png' ,'.jpeg ','.jpg'</h4>");
						$("#error_image").css('display', 'block');
						// alert("File size must under 10 mb!");

					}

			}else{
				$(".step8").html("");
			}
		}
		
	
	
	// render difficulty level
	AP.renderDiffLevel = function() {
		$(".step9").hide();
		$(".step8").show();
		
		if (AP.mainData.compLevelData.length != 0) {
			
			var htm = '' 
				
			+'<label class="col-xl-12 col-md-12 col-sm-12" for="diffLevel">Difficulty Level Between 1 to 5<span class="marathi-text" > (काठिण्य  पातळी १ ते ५)</span><i class="fa fa-asterisk " aria-hidden="true"></i> </label>'
			+ ' <select class="form-control col-xl-12 col-md-12 col-sm-12 selectpicker" data-live-search="true" id="diffLevel" onchange=\"com.coep.test.addProblem.onSelectDiffLevel()\""  required>'
			+ ' <option value="0" disabled selected>Choose Difficulty Level</option>'

			for (var i = 0; i < AP.mainData.compLevelData.length; i++) {
				htm += '' + '  <option value="' + AP.mainData.compLevelData[i].CLID
						+ '" id ="' + AP.mainData.compLevelData[i].CLID + '">'
						+ AP.mainData.compLevelData[i].CLN + ' </option>'
			}

			htm += ' </select>'
			+ '<div id="error" class="invalid-feedback diffLevel">Please select difficulty level.</div>'
				

			$("#selectDiffLevel").html(htm);
//			$("#varNo").show(); // variation number display here
			
			bsSelectValidation();
			$('select').selectpicker();
			$('#selectDiffLevel').change(bsSelectValidation);
			
			if(!jQuery.isEmptyObject(questionGruopJSON)){
				if(questionGruopJSON.level != undefined ){
					
					$("#diffLevel").val(questionGruopJSON.level).change();
					$("#varNo").val(questionGruopJSON.varNo);
					
				}
			}
			
		}else{
			
			alert(data.msg)
		}
	
	}
	

	AP.onSelectDiffLevel = function() {
		
		$(".step9").show();
		$(".step10").show();
	}


   AP.previewClick = function(urlPath, quesTypeId) {
	
	   var htm = ''
		   htm += ' '
			   + '<a id="previewBtn"  data-toggle="modal" data-target="#mediaMoel">'
				+ ' <button class="btn btn-info pointer">PREVIEW</button>'
				+ '</a>'
				 + ' <a id="repalceBtn" href="javascript:com.coep.test.addProblem.replaceBtnClick();"  >'
					+ '<button class="btn btn-secondary pointer" >REPLACE</button>'
					+ ' </a>'
				// The Modal
				   +'<div class="modal" id="mediaMoel">'
				   +'<div class="modal-dialog">'
				   +'  <div class="modal-content">'

				   +'    <div class="modal-header">'
				   +'      <h4 class="modal-title">'
				   + '                   <p>'
				   + '                        With reference to the follwing clip answer the following question'
				   + '                   </p>'
				   + '</h4>'
				   +'      <button type="button" class="close" data-dismiss="modal">&times;</button>'
				   +'    </div>'
				   
				
				   if(quesTypeId == 2){
						  // Modal body
						htm  +='    <div class="modal-body">'
							
							 +' <audio controls>'
							
							 +'  <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+urlPath+'" type="audio/mpeg" class="ModelUploadType">'
							 +' Your browser does not support the audio element.'
							 +' </audio>'
						   +'    </div>'
					}else  if(quesTypeId == 3){
						  // Modal body
					
						htm  +='  <video controls class="width-100">'
							+ '            <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+urlPath+'" type="video/mp4" class"ModelUploadType">'
							+ ' <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+urlPath+'" type="video/ogg">'
							+ '            Your browser does not support the video tag.'
							+ '         </video>' 
					}else if(quesTypeId == 4){
					  // Modal body
						htm  +='    <div class="modal-body">'
						   +' <img src="'+ AP.baseURL +'media/get/getImage?mediaID='+urlPath+'" class="popup-img ModelUploadType" />'
						   +'    </div>'
					}
				 

				  // Model footer
	   		htm  +=' <div class="modal-footer">'
				   +'      <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>'
				   +'    </div>'

				   +'  </div>'
				   +'</div>'
				   +'</div>'
				  
	   $("#preview-div").html(htm);
   }
   
   AP.replaceBtnClick = function() {
	   $("#repalceBtn").hide();
	   $("#fileUplaod").show();
	   $("#previewBtn").hide();
	   $("#queslink").val(null);
	   $(".step8").html("");
	   $(".step9").hide();
   }
   
   // this function is use to pop up question's solution 
   AP.previewSolClick = function(urlPath, solMediaType) {
		
	   var htm = ''
		   htm += ' '
			   + '<a id="previewSolBtn"  data-toggle="modal" data-target="#mediaModel">'
				+ ' <button class="btn btn-info pointer">PREVIEW</button>'
				+ '</a>'
				 + ' <a id="repalceSolBtn" href="javascript:com.coep.test.addProblem.replaceSolBtnClick();"  >'
					+ '<button class="btn btn-secondary pointer" >REPLACE</button>'
					+ ' </a>'
				// The Modal
				   +'<div class="modal" id="mediaModel">'
				   +'<div class="modal-dialog">'
				   +'  <div class="modal-content">'

				   +'    <div class="modal-header">'
				   +'      <h4 class="modal-title">'
				   + '                   <p>'
				   + '                        follwing will be the solution'
				   + '                   </p>'
				   + '</h4>'
				   +'      <button type="button" class="close" data-dismiss="modal">&times;</button>'
				   +'    </div>'
				   
				 if(solMediaType == "IMAGE"){
					  // Modal body
						htm  +='    <div class="modal-body">'
						   +' <img src="'+ AP.baseURL +'media/get/getImage?mediaID='+urlPath+'" class="popup-img ModelUploadType" />'
						   +'    </div>'
					}else if(solMediaType == "AUDIO"){
						  // Modal body
						htm  +='    <div class="modal-body">'
							 +' <audio controls>'
							 +'  <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+urlPath+'" type="audio/mpeg" class="ModelUploadType">'
							 +' Your browser does not support the audio element.'
							 +' </audio>'
						     +'    </div>'
					}else  if(solMediaType == "VIDEO"){
						  // Modal body
					
						htm  +='  <video controls class="width-100">'
							+ '            <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+urlPath+'" type="video/mp4" class"ModelUploadType">'
							+ ' <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+urlPath+'" type="video/ogg">'
							+ '            Your browser does not support the video tag.'
							+ '         </video>' 
					}

				  // Model footer
	   		htm  +=' <div class="modal-footer">'
				   +'      <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>'
				   +'    </div>'

				   +'  </div>'
				   +'</div>'
				   +'</div>'
				  
	   $("#sol-preview-div").html(htm);
   }


   AP.replaceSolBtnClick = function() {
	   $("#sol-preview-div").html('');
	   $("#repalceSolBtn").hide();
	   $(".solClass").show();
	   $("#previewSolBtn").hide();
	   $(".solClass").val(null);
   }
   
   
   // this function is use to pop up answer's image option 
   AP.previewOptionClick = function(urlPath, optionId, Id) {
		
	   var htm = ''
		   htm += ' '
			   + '<div class="col-sm-12 offset-md-1 col-md-10 offset-lg-1 col-lg-10 offset-xl-1 col-xl-10""><div class="row"><a id="previewOptBtn-'+optionId+'" class="previewOptBtn-'+optionId+' col-sm-6 col-md-6 col-lg-6"  data-toggle="modal" data-target="#mediaModel-'+optionId+'">'
				+ ' <button class="btn btn-info pointer">PREVIEW</button>'
				+ '</a>'
				 + ' <a id="repalceOptBtn-'+optionId+'" class="'+optionId+' col-sm-6 col-md-6 col-lg-6" optName="'+optionId+'" onclick="javascript:com.coep.test.addProblem.replaceOptBtnClick(this.id);"  >'
					+ ' <button class="btn btn-secondary pointer" >REPLACE</button>'
					+ ' </a></div></div>'
				// The Modal
				   +'<div class="modal" id="mediaModel-'+optionId+'">'
				   +'<div class="modal-dialog">'
				   +'  <div class="modal-content">'

				   +'    <div class="modal-header">'
				   +'      <h4 class="modal-title">'
				   + '                   <p>'
				   + '                        follwing will be the option image'
				   + '                   </p>'
				   + '</h4>'
				   +'      <button type="button" class="close" data-dismiss="modal">&times;</button>'
				   +'    </div>'
				   
					  // Modal body
						htm  +='    <div class="modal-body">'
						   +' <img src="'+ AP.baseURL +'media/get/getImage?mediaID='+urlPath+'" class="popup-img ModelUploadType" />'
						   +'    </div>'
				  // Model footer
	   		htm  +=' <div class="modal-footer">'
				   +'      <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>'
				   +'    </div>'

				   +'  </div>'
				   +'</div>'
				   +'</div>'
				  
	   $("#opt-preview-div-"+optionId).html(htm);
	   
	  
	   AP.replaceOptBtnClick = function(optionsId) {
		   
		   var btnmaneArr = optionsId.split('-');
		   var optId = btnmaneArr[1];
		   $(".opt-preview-div-"+optId).html('');
		   $(".previewOptBtn-"+optId).hide();
		   $("#repalceOptBtn-"+optId).hide(); 
		   $("#"+optId).show();
		   $("#"+optId).attr("img-media","");
		   $("#"+optId).val(null);
	   }
   }


  
	AP.uplaodMediaFile = function(url, methodType, formData , quesTypeId) {

		$.ajax({
			url : url,
			type : methodType,
			data : formData,
			enctype : "multipart/form-data",
			processData : false,
			contentType : false,
			dataType : 'json',
		}).done(function(data) {
			
			if(data.done == true){
				
				$("#fileUplaod").hide();
				$("#repalceBtn").show();
				$("#queslink").attr("qg-media",data.URL); 
				AP.mediaURL = data.URL;
				AP.previewClick(data.URL, quesTypeId);
				
			}else{
				$("#queslink").attr("qg-media",data.URL); 
			}
			

		}).fail(function(jqXHR, textStatus) {
			showToast.show('File upload failed ...', false);
		});
		
	}


	
	AP.uplaodSolMediaFile = function(url, methodType, formData, solId, solMediaType) {

		$.ajax({
			url : url,
			type : methodType,
			data : formData,
			enctype : "multipart/form-data",
			processData : false,
			contentType : false,
			dataType : 'json',
		}).done(function(data) {
			
			if(data.done == true){
				
				$("#"+solId).hide();
				$("#"+solId).attr("sol-media",data.URL);
				$("#repalceSolBtn").show();
				AP.mediaSolURL = data.URL;
				AP.previewSolClick(data.URL, solMediaType);
				$("#"+solId).addClass("solClass");
				
			}else{
				$("#"+solId).attr("sol-media","");
			}
			

		}).fail(function(jqXHR, textStatus) {
			showToast.show('File upload failed ...', false);
		});
		
	}

	
	
	
	/// ajax call to save question group
	
	AP.addToQuestionGroup = function(url, methodType, questionGruopJSON, baseData, backBtnStatus) {
		$.ajax({
			type : methodType,
			url : url,
			data : JSON.stringify(questionGruopJSON),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == false) {
					showToast.show(data.msg, false);
				} else {
					if (data.length != 0) {
						showToast.show(data.msg, true);
						questionGruopJSON.quesGroupMediaLinks.quesGroupMediaLinkId = data.QGMLID;
						AP.modifyQuestionGroup(data.QGID, questionGruopJSON, baseData, backBtnStatus);
					} else {
						// AP.showCustomAlert("Error", "");
					}
				}
			},
			error : function() {
			}

		});
	}
	
	
	// answer types div start here
	//  Singular Correct Answer div
	
	AP.scaDivFunction = function(Id) {

		var sca = '<div id="scaDiv'
				+ Id
				+ '" class="action-divs display-none activeanstype row" >'
				+ ' <div class="corr-ans-par-div col-sm-12 col-md-6 col-lg-6 col-xl-6">'
				+ '<div id="scacorrAnsinpDiv_"  class="row">'
				+ ' <div id="scacorrAns1" class="col-sm-11 col-md-11 col-lg-11 col-xl-11 ">'
				+ ' <h4 class=\"corrans-label\" style=\"color:#0ec173\">Correct Answer 1</h4>'
				+ ' <input type="text" id="scaId1" placeholder="Correct Answer 1" name="scacorransinp" title="Correct Answer 1" class="correctAnsBlk form-control hasclear corr-ans">'
//				+ ' <span onclick="removeRecord(\'scacorrAnsInp \',1,\'scacorrAnsinpDiv_\')" class="clearer icon-delete rem-corr-ans-glyph">X</span>'
				+ '    </div>'
				+'<button class="checkLatexAns" onclick="typesetInputForSCA1(this)" data-toggle="modal" data-target="#latexDataAns1">{ L }</button>'
				+ ' </div>'
				
				+ ' <div class="col-sm-12">'
//				+ ' <button type="button" onclick="addscaCorrAnsOpt(\'scacorrAnsinpDiv_\')" id="addCorrAns" title="Add More Correct Options" class="btn add-more-corrquest">'
//				+ '  <span class="icon-add corr-ans-glyph"></span>'
//				+ ' ADD MORE CORRECT OPTIONS'
//				+ '    </button>'
				+ '  </div>'
				+ '</div>'
				+ ' <div class="inc-ans-par-div col-sm-12 col-md-6 col-lg-6 col-xl-6">'
				+ '  <div id="scaincAnsinpDiv_" class="inc-ans-div row">'
				+ '      <div id="scaincAns1" class="col-sm-11 col-md-11 col-lg-11 col-xl-11 ">'
				+ '          <h4 class="red-color">Incorrect Answer 1</h4>'
				+ '          <input type="text" id="sicaId1" placeholder="Incorrect Answer 1" name="scaincansinp" title="Incorrect Answer 1" class="inCorrectAnsBlk form-control hasclear inc-ans">'
//				+ '          <span onclick="removeRecord(\'scaincAnsInp\',1,\'scaincAnsinpDiv_\')" class="clearer icon-delete rem-inc-ans-glyph">X</span>'
				+ '      </div>'
				+'<button class="checkLatexAns" onclick="typesetInputForSICA1(this)" data-toggle="modal" data-target="#latexDataAns1">{ L }</button>'
				+ '   </div>'
				+ '   <div class="col-sm-12">'
				+ '     <button type="button" onclick="addscaIncAnsInpOpt(\'scaincAnsinpDiv_\')" id="addIncAns" title="Add More Incorrect Options" class="btn btn-danger add-more-incquest">'
				+ '        <span class="icon-add inc-ans-glyph"></span>Add More Incorrect Options'
				+ '   </button>' + '     </div>' 
				
				+ '  </div>' + ' </div>'
				// add que model for Singular Correct Answer start
				+ '<div id="latexDataAns1" class="modal fade" role="dialog">'
				+ '<div class="modal-dialog">'
		
				+ ' <div class="modal-content">'
				+ '<div class="modal-header">'
				+ ' <span class="modal-title">Latex Equation</span>'
				+ ' <button type="button" class="close" data-dismiss="modal">&times;</button>'
				+ ' </div>'
				
				+ '<div class="modal-body"  style="  width: 500px;  height: auto;  overflow-x: auto;">' //
				+' <div class="scoll-tree">'
				+' <div id="MathPreviewCorrectAns1" class="" placeholder="Latex equation" ></div><br/>'
		//		
				+ '	</div>'
				+ ' </div>'
				+ '	<div class="modal-footer">'
				+ '<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color:red; color:#fff;">Close</button>'
				+ '</div>'
				+ '</div>'
				// add que  model for Singular Correct Answer solution end
				

		$('#new-div').html(sca);
		$("#scaDiv" + Id).addClass('activeanstype');
		$("#scaDiv" + Id).show();

	}
	
	// multiple correct Answer
	AP.mcaDivFunction = function(Id) {

		var mca = '<div id="mcaDiv'
				+ Id
				+ '" class="row action-divs display-none activeanstype" >'
				+ ' <div class="corr-ans-par-div col-sm-12 col-md-6 col-xl-6 col-lg-6">'
				+ '<div id="mcacorrAnsinpDiv_" class="inc-ans-div row" >'
				+ ' <div id="mcacorrAns1" class="col-sm-11 col-md-11 col-lg-11 col-xl-11 ">'
				+ ' <h4 class=\"corrans-label\" style=\"color:#0ec173\">Correct Answer 1</h4>'
				+ ' <input type="text" id="mcaId1" placeholder="Correct Answer 1" name="mcacorransinp" title="Correct Answer 1" class="correctAnsBlk form-control hasclear corr-ans">'
//				+ ' <span onclick="removeRecord(\'mcacorrAnsInp \',1,\'mcacorrAnsinpDiv_\')" class="clearer icon-delete rem-corr-ans-glyph"></span>'
				+ '    </div>'
				+'<button class="checkLatexAns" onclick="typesetInputForAddMCA1(this)" data-toggle="modal" data-target="#MCAlatexDataAns1">{ L }</button>'
				+ ' </div>'
				+ ' <div class="col-sm-12">'
				+ ' <button type="button" onclick="addmcaCorrAnsOpt(\'mcacorrAnsinpDiv_\')" id="addCorrAns" title="Add More Correct Options" class="btn btn-success add-more-corrquest">'
				+ '  <span class="icon-add corr-ans-glyph"></span>'
				+ ' Add More Correct Options'
				+ '    </button>'
				+ '  </div>'
				+ '</div>'
				+ ' <div class="inc-ans-par-div col-sm-12 col-md-6 col-xl-6 col-lg-6">'
				+ '  <div id="mcaincAnsinpDiv_" class="inc-ans-div row">'
				+ '      <div id="mcaincAns1" class="col-sm-11 col-md-11 col-lg-11 col-xl-11 ">'
				+ '          <h4 class="red-color">Incorrect Answer 1</h4>'
				+ '          <input type="text" id="micaId1" placeholder="Incorrect Answer 1" name="mcaincansinp" title="Incorrect Answer 1" class="inCorrectAnsBlk form-control hasclear inc-ans">'
//				+ '          <span onclick="removeRecord(\'mcaincAnsInp\',1,\'mcaincAnsinpDiv_\')" class="clearer icon-delete rem-inc-ans-glyph">X</span>'
				+ '      </div>'
				+'<button class="checkLatexAns" onclick="typesetInputForAddMICA1(this)" data-toggle="modal" data-target="#MCAlatexDataAns1">{ L }</button>'
				+ '   </div>'
				+ '   <div class="col-sm-12">'
				+ '     <button type="button" onclick="addmcaIncAnsInpOpt(\'mcaincAnsinpDiv_\')" id="addIncAns" title="Add More Incorrect Options" class="btn btn-danger  add-more-incquest">'
				+ '        <span class="icon-add inc-ans-glyph"></span>Add More Incorrect Options'
				+ '   </button>' + '     </div>' + '  </div>' + ' </div>'
		// add que model for multiple correct Answer start
		+ '<div id="MCAlatexDataAns1" class="modal fade" role="dialog">'
		+ '<div class="modal-dialog">'

		+ ' <div class="modal-content">'
		+ '<div class="modal-header">'
		+ ' <span class="modal-title">1) Answer Latex Equation</span>'
		+ ' <button type="button" class="close" data-dismiss="modal">&times;</button>'
		+ ' </div>'
		
		+ '<div class="modal-body"  style="  width: 500px;  height: auto;  overflow-x: auto;">' 
		+' <div class="scoll-tree">'
		+' <div id="MathPreviewMCA" class="" placeholder="Latex equation" ></div><br/>'
		
		+ '	</div>'
		+ ' </div>'
		+ '	<div class="modal-footer">'
		+ '<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color:red; color:#fff;">Close</button>'
		+ '</div>'
		+ '</div>'
		// add que  model for multiple correct Answer solution end
		
		$('#new-div').html(mca);
		$("#mcaDiv" + Id).addClass('activeanstype');
		$("#mcaDiv" + Id).show();

	}
	
	// fill in blanks
	AP.fibDivFunction = function(Id) {
		// / fill in blanks
		var fib = ' <div id="fibDiv'
				+ Id
				+ '" class="action-divs display-none row" style="display: none;">'
				+ '<div class="corr-ans-par-div col-sm-12 col-md-6 col-xl-6 col-lg-6">'
				+ ' <div id="fibcorrAnsinpDiv_"  class="inc-ans-div row">'
				+ '<div id="fibcorrAns1" class="col-sm-11 col-md-11 col-lg-11 col-xl-11 ">'
				+ '<h4 class=\"corrans-label\" style=\"color:#0ec173\">Correct Answer 1</h4>'
				+ '<input type="text" id=\"fibId1\" placeholder="Correct Answer 1" name="fibcorransinp" title="Correct Answer 1" class="correctAnsBlk form-control hasclear corr-ans">'
//				+ '<span onclick="removeRecord(\'fibcorrAnsInp\',1)" class="clearer icon-delete rem-corr-ans-glyph"></span>'
				+ '</div>'
				+'<button class="checkLatexAns" onclick="typesetInputForFIB1(this)" data-toggle="modal" data-target="#FIBlatexDataAns1">{ L }</button>'
				+ '</div>'
//				+ '<div class="col-sm-12">'
//				+ '<button type="button" onclick="addfibCorrAnsOpt(\'fibcorrAnsinpDiv_\')" id="addCorrAns" title="Add More Correct Options" class="btn add-more-corrquest">'
//				+ '<span class="icon-add corr-ans-glyph"></span>'
//				+ 'ADD MORE CORRECT OPTIONS'
//				+ '</button>'
				+ '</div>'
		//		+ '</div>'

				+ ' <div class="inc-ans-par-div col-sm-12 col-md-6 col-xl-6 col-lg-6">'
				+ ' <div id="fibincAnsinpDiv_" class="inc-ans-div row">'
				+ ' <div id="fibincAns1" class="col-sm-11 col-md-11 col-lg-11 col-xl-11 ">'
				+ ' <h4 class="red-color">Incorrect Answer 1</h4>'
				+ '<input type="text" id=\"icfibId1\" placeholder="Incorrect Answer 1" name="fibincansinp" title="Incorrect Answer 1" class="inCorrectAnsBlk form-control hasclear inc-ans">'
//				+ ' <span onclick="removeRecord(\'fibincAnsInp\',1)" class="clearer icon-delete rem-inc-ans-glyph">X</span>'
				+ '</div>'
				+'<button class="checkLatexAns" onclick="typesetInputForFIBI1(this)" data-toggle="modal" data-target="#FIBlatexDataAns1">{ L }</button>'
				+ '</div>'
				+ '<div class="col-sm-12">'
				+ '<button type="button" onclick="addfibIncAnsInpOpt(\'fibincAnsinpDiv_\')" id="addIncAns" title="Add More Incorrect Options" class="btn btn-danger add-more-incquest">'
				+ '<span class="icon-add inc-ans-glyph"></span>Add More Incorrect Options'
				+ '</button>' + '</div>' + '</div>'
				+ '</div>'
				+ '</div>'
				// add que model for fill in blanks start
				+ '<div id="FIBlatexDataAns1" class="modal fade" role="dialog">'
				+ '<div class="modal-dialog">'

				+ ' <div class="modal-content">'
				+ '<div class="modal-header">'
				+ ' <span class="modal-title">Latex Equation</span>'
				+ ' <button type="button" class="close" data-dismiss="modal">&times;</button>'
				+ ' </div>'
				
				+ '<div class="modal-body"  style="  width: 500px;  height: auto;  overflow-x: auto;">' 
				+' <div class="scoll-tree">'
				+' <div id="MathPreviewFIB" class="" placeholder="Latex equation" ></div><br/>'
				
				+ '	</div>'
				+ ' </div>'
				+ '	<div class="modal-footer">'
				+ '<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color:red; color:#fff;">Close</button>'
				+ '</div>'
				+ '</div>'
				// add que  model for fill in blanks solution end

		$('#new-div').html(fib);
		$('#fibDiv' + Id).addClass('activeanstype');
		$('#fibDiv' + Id).show();

	}

	// match the pair  
	AP.mtpDivFunction = function(Id) {
		var mtp = '<div id="mtpDiv'
			+ Id
			+ '" class="row action-divs display-none" style="display: none;">'
			+ ' <div class="corr-ans-par-div col-sm-12 col-md-6 col-xl-6 col-lg-6">'
			+ '  <div id="corrAnstxtareaDiv_" class="corr-ans-div row">'
			+ ' <div id="mtpcorrAns1" class="col-sm-11 col-md-11 col-lg-11 col-xl-11 ">'
			+ ' <h4 class=\"corrans-label\" style=\"color:#0ec173\">Correct Answer 1</h4>'
			+ ' <textarea type="text" id=\"mtpId1\" placeholder="Correct Answer 1" name="mtpcorranstxt" title="Correct Answer 1" class="correctAnsBlk form-control hasclear corr-ans"></textarea>'
//			+ ' <span onclick="removeRecord(\'corrAnsTxt\',1)" class="clearer icon-delete rem-corr-ans-glyph"></span>'
			+ ' </div>'
			+'<button class="checkLatexAns" onclick="typesetInputForMTP1(this)" data-toggle="modal" data-target="#MTPlatexDataAns1">{ L }</button>'
			+ ' </div>'
			+ '<div class="col-sm-12">'
//			+ '<button type="button" onclick="addmtpCorrAnsTxtOpt(\'corrAnstxtareaDiv_\')" id="addCorrAns" title="Add More Correct Options" class="btn btn-success add-more-corrquest">'
//			+ '<span class="icon-add corr-ans-glyph"></span>'
//			+ 'Add More Correct Options'
//			+ ' </button>'
			+ ' </div>'
			+ ' </div>'
			+ ' <div class="inc-ans-par-div col-sm-12 col-md-6 col-xl-6 col-lg-6">'
			+ ' <div id="incAnstxtareaDiv_" class="inc-ans-div row">'
			+ ' <div id="mtpincAns1" class="col-sm-11 col-md-11 col-lg-11 col-xl-11 ">'
			+ ' <h4 class="red-color">Incorrect Answer 1</h4>'
			+ ' <textarea type="text" id=\"icmtpId1\" placeholder="Incorrect Answer 1" name="mtpincanstxt" title="Incorrect Answer 1" class="inCorrectAnsBlk form-control hasclear inc-ans"></textarea>'
//			+ ' <span onclick="removeRecord(\'incAnsTxt\',1)" class="clearer icon-delete rem-inc-ans-glyph">X</span>'
			+ '</div>'
			+'<button class="checkLatexAns" onclick="typesetInputForMTPI1(this)" data-toggle="modal" data-target="#MTPlatexDataAns1">{ L }</button>'
			+ '</div>'
			+ '<div class="col-sm-12">'
			+ '<button type="button" onclick="addmtpIncAnsTxtOpt(\'incAnstxtareaDiv_\')" id="addIncAns" title="Add More Incorrect Options" class="btn btn-danger add-more-incquest">'
			+ '<span class="icon-add inc-ans-glyph"></span>Add More Incorrect Options'
			+ '</button>' + '</div>' + '</div>' + '</div>'
			// add que model for match the pair  start
			+ '<div id="MTPlatexDataAns1" class="modal fade" role="dialog">'
			+ '<div class="modal-dialog">'

			+ ' <div class="modal-content">'
			+ '<div class="modal-header">'
			+ ' <span class="modal-title">Latex Equation</span>'
			+ ' <button type="button" class="close" data-dismiss="modal">&times;</button>'
			+ ' </div>'
			
			+ '<div class="modal-body"  style="  width: 500px;  height: auto;  overflow-x: auto;">' 
			+' <div class="scoll-tree">'
			+' <div id="MathPreviewMTP" class="" placeholder="Latex equation" ></div><br/>'
			
			+ '	</div>'
			+ ' </div>'
			+ '	<div class="modal-footer">'
			+ '<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color:red; color:#fff;">Close</button>'
			+ '</div>'
			+ '</div>'
			// add que  model for match the pair  solution end

	$('#new-div').html(mtp);
	$('#mtpDiv' + Id).addClass('activeanstype');
	$('#mtpDiv' + Id).show();

}
	// true / fales

	AP.tfDivFunction = function(Id) {
		var tfa = '<div id="truefalseDiv'
			+ Id
			+ '" class="row action-divs display-none activeanstype" style="display: none;">'
			+ '<div class="corr-ans-par-div col-sm-12 col-md-6 col-xl-6 col-lg-6">'
			+ '<div id=\"truefalsecorrAnsinpDiv'
			+ Id
			+ '\" class=\"inc-ans-div row\">'
			+ '<div id=\"truefalsecorrAns'
			+ Id
			+ '\" class=\"col-sm-11 col-md-11 col-lg-11 col-xl-11 \">'
			+ '<h4 class=\"corrans-label\" style=\"color:#0ec173\">Correct Answer</h4>'
			+ '<input type="text" id=\"tfId1\" value="" placeholder="Correct Answer" name="tfcorransinp" title="Correct Answer" class="correctAnsBlk form-control hasclear corr-ans" >'
			+ '</div>'
			+'<button class="checkLatexAns" onclick="typesetInputForTF1(this)" data-toggle="modal" data-target="#TFlatexDataAns1">{ L }</button>'
			+ '</div>'
			+ '</div>'
			+ '<div class=\"inc-ans-par-div col-sm-12 col-md-6 col-xl-6 col-lg-6\">'
			+ '<div id=\"truefalseincAnsinpDiv'
			+ Id
			+ '\" class=\"inc-ans-div row\">'
			+ '<div id=\"truefalseincAns'
			+ Id
			+ '\" class=\"col-sm-11 col-md-11 col-lg-11 col-xl-11 \">'
			+ '<h4 class="red-color">Incorrect Answer</h4>'
			+ '<input type="text" id=\"ictfId1\"  value="" placeholder="Incorrect Answer" name="tfincansinp" title="Incorrect Answer" class="inCorrectAnsBlk form-control hasclear inc-ans" >'
			+ '</div>'
			+'<button class="checkLatexAns" onclick="typesetInputForTFI1(this)" data-toggle="modal" data-target="#TFlatexDataAns1">{ L }</button>'
			+ '</div>' + '</div>' + ' </div>'
			// add que model for true / fales start
			+ '<div id="TFlatexDataAns1" class="modal fade" role="dialog">'
			+ '<div class="modal-dialog">'

			+ ' <div class="modal-content">'
			+ '<div class="modal-header">'
			+ ' <span class="modal-title">Latex Equation</span>'
			+ ' <button type="button" class="close" data-dismiss="modal">&times;</button>'
			+ ' </div>'
			
			+ '<div class="modal-body"  style="  width: 500px;  height: auto;  overflow-x: auto;">' 
			+' <div class="scoll-tree">'
			+' <div id="MathPreviewTF" class="" placeholder="Latex equation" ></div><br/>'
			
			+ '	</div>'
			+ ' </div>'
			+ '	<div class="modal-footer">'
			+ '<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color:red; color:#fff;">Close</button>'
			+ '</div>'
			+ '</div>'
			// add que  model for true / fales solution end

		$('#new-div').html(tfa);
		$('#truefalseDiv' + Id).show();

	}
	
//  Image - Singular Correct Answer div  zzz
	
	AP.scaImageDivFunction = function(Id) {

		var scaImg = '<div id="scaImgDiv'
				+ Id
				+ '" class="row action-divs display-none activeanstype" >'
				+ ' <div class="corr-ans-par-div col-sm-12 col-md-6 col-xl-6 col-lg-6">'
				+ '<div id="scaImgcorrAnsinpDiv_"  >'
				+ ' <div id="scaImgcorrAns1" class="col-sm-12">'
				+ ' <h4 class=\"corrans-label\" style=\"color:#0ec173\">Correct Answer 1</h4>'
//				+ ' <input type="text" id="scaId1" placeholder="Correct Answer 1" name="scacorransinp" title="Correct Answer 1" class="correctAnsBlk form-control hasclear corr-ans">'
				+ '<input  type="file"  id="scaImgId1" name="scaImgcorransinp" title="Upload Correct Answer File" col="8" onchange="com.coep.test.addProblem.onSelCorAnsFile(this.id,'+scaimgcorransinpcnt+','+Id+')" class="correctAnsBlk form-control hasclear corr-ans imgOption1" >'
				+ '<div id="scaImgId_error1" class="red-color"></div>'
				+'<div id="opt-preview-div-scaImgId1" class="opt-preview-div-scaImgId1 row"></div>'
				//				+ ' <span onclick="removeRecord(\'scacorrAnsInp \',1,\'scacorrAnsinpDiv_\')" class="clearer icon-delete rem-corr-ans-glyph">X</span>'
				+ '    </div>'
				+ ' </div>'
				+ ' <div class="col-sm-12">'
//				+ ' <button type="button" onclick="addscaCorrAnsOpt(\'scacorrAnsinpDiv_\')" id="addCorrAns" title="Add More Correct Options" class="btn add-more-corrquest">'
//				+ '  <span class="icon-add corr-ans-glyph"></span>'
//				+ ' ADD MORE CORRECT OPTIONS'
//				+ '    </button>'
				+ '  </div>'
				+ '</div>'
				+ ' <div class="inc-ans-par-div col-sm-12 col-md-6 col-xl-6 col-lg-6">'
				+ '  <div id="scaImgincAnsinpDiv_" class="inc-ans-div">'
				+ '      <div id="scaImgincAns1" class="col-sm-12">'
				+ '          <h4 class="red-color">Incorrect Answer 1</h4>'
//				+ '<input type="text" id="sicaId1" placeholder="Incorrect Answer 1" name="scaincansinp" title="Incorrect Answer 1" class="inCorrectAnsBlk form-control hasclear inc-ans">'
				+ '<input  type="file"  id="sImgicaId1" name="scaImgincorransinp" title="Upload incorrect Answer File 1" col="8" onchange="com.coep.test.addProblem.onSelInCorAnsFile(this.id,'+scaimgincansinpcnt+','+Id+')" class="inCorrectAnsBlk form-control hasclear inc-ans imgOption1" >'
				//				+ '          <span onclick="removeRecord(\'scaincAnsInp\',1,\'scaincAnsinpDiv_\')" class="clearer icon-delete rem-inc-ans-glyph">X</span>'
				+ '<div id="sImgicaId_error1" class="red-color"></div>'
				+'<div id="opt-preview-div-sImgicaId1" class="opt-preview-div-sImgicaId1"></div>'
				+ '      </div>'
				+ '   </div>'
				+ '   <div class="col-sm-12">'
				+ '     <button type="button" onclick="addImgscaIncAnsInpOpt(\'scaImgincAnsinpDiv_\')" id="addIncAns" title="Add More Incorrect Options" class="btn btn-danger add-more-incquest">'
//																			   scaincAnsinpDiv_
				+ '        <span class="icon-add inc-ans-glyph"></span>Add More Incorrect Options'
				+ '   </button>' + '     </div>' + '  </div>' + ' </div>';

		$('#new-div').html(scaImg);
		$("#scaImgDiv" + Id).addClass('activeanstype');
		$("#scaImgDiv" + Id).show();

	}
	
	
	
	// Image - true / fales

	AP.tfImageDivFunction = function(Id) {
		var tfa = '<div id="truefalseImgDiv'
			+ Id
			+ '" class="row action-divs display-none activeanstype"  >'
			+ '<div class="corr-ans-par-div col-sm-12 col-md-6 col-xl-6 col-lg-6">'
			+ '<div id=\"truefalseImgcorrAnsinpDiv'
			+ Id
			+ '\" class="col-sm-12">'
			+ '<div id=\"truefalseImgcorrAns'
			+ Id
			+ '\" class=\"col-sm-12\">'
			+ '<h4 class=\"corrans-label\" style=\"color:#0ec173\">Correct Answer</h4>'
//			+ '<input type="text" id=\"tfId1\" value="TRUE" placeholder="Correct Answer" name="tfcorransinp" title="Correct Answer" class="correctAnsBlk form-control hasclear corr-ans" disabled>'
			+ '<input  type="file"  id="tfImgId1" name="tfImgcorransinp" title="Upload Correct Answer File" col="8" onchange="com.coep.test.addProblem.onSelCorAnsFile(this.id,'+1+','+Id+')" class="correctAnsBlk form-control hasclear corr-ans imgOption1" >'
			+ '<div id="tfImgId_error1" class="red-color"></div>'
			+'<div id="opt-preview-div-tfImgId1" class="opt-preview-div-tfImgId1"></div>'
			
			+ '</div>'
			+ '</div>'
			+ '</div>'
			+ '<div class=\"inc-ans-par-div col-sm-6\">'
			+ '<div id=\"truefalseImgincAnsinpDiv'
			+ Id
			+ '\" class=\"inc-ans-div\">'
			+ '<div id=\"truefalseImgincAns'
			+ Id
			+ '\" class=\"col-sm-12\">'
			+ '<h4 class="red-color">Incorrect Answer</h4>'
//			+ '<input type="text" id=\"ictfId1\"  value="FALSE" placeholder="Incorrect Answer" name="tfincansinp" title="Incorrect Answer" class="inCorrectAnsBlk form-control hasclear inc-ans" disabled>'
			+ '<input  type="file"  id="ictfImgId1" name="tfincImgansinp" title="Upload incorrect Answer File 1" col="8" onchange="com.coep.test.addProblem.onSelInCorAnsFile(this.id,'+1+','+Id+')" class="inCorrectAnsBlk form-control hasclear inc-ans imgOption1" >'
			//				+ '          <span onclick="removeRecord(\'scaincAnsInp\',1,\'scaincAnsinpDiv_\')" class="clearer icon-delete rem-inc-ans-glyph">X</span>'
			+ '<div id="ictfImgId_error1" class="red-color"></div>'
			+'<div id="opt-preview-div-ictfImgId1" class="opt-preview-div-ictfImgId1"></div>'
			+ '</div>' + '</div>' + '</div>' + ' </div>';

		$('#new-div').html(tfa);
		$('#truefalseImgDiv' + Id).show();

	}
	
	
	
	
	/// Image - match the pairz  
	AP.mtpImageDivFunction = function(Id) {
		var mtp = '<div id="mtpImgDiv'
			+ Id
			+ '" class="row action-divs display-none activeanstype" style="display: none;">'
			+ ' <div class="corr-ans-par-div col-sm-12 col-md-6 col-xl-6 col-lg-6">'
			+ '  <div id="mtpImgcorrAnstxtareaDiv_" class="corr-ans-div">'
			+ ' <div id="mtpImgcorrAns1" class="col-sm-12">'
			+ ' <h4 class=\"corrans-label\" style=\"color:#0ec173\">Correct Answer 1</h4>'
//			+ ' <textarea type="text" id=\"mtpId1\" placeholder="Correct Answer 1" name="mtpcorranstxt" title="Correct Answer 1" class="correctAnsBlk form-control hasclear corr-ans"></textarea>'
			+ '<input  type="file"  id="mtpImgId1" name="mtpImgcorranstxt" title="Upload Correct Answer File" col="8" onchange="com.coep.test.addProblem.onSelCorAnsFile(this.id,'+mtpimgcorranstxtcnt+','+Id+')" class="correctAnsBlk form-control hasclear corr-ans imgOption1" >'
			+ '<div id="mtpImgId_error1" class="red-color"></div>'
			+'<div id="opt-preview-div-mtpImgId1" class="opt-preview-div-mtpImgId1"></div>'
			
			+ ' </div>'
			+ ' </div>'
			+ '<div class="col-sm-12">'
//			+ '<button type="button" onclick="addmtpImgCorrAnsTxtOpt(\'mtpImgcorrAnstxtareaDiv_\')" id="addCorrAns" title="Add More Correct Options" class="btn btn-success add-more-corrquest">'
//			+ '<span class="icon-add corr-ans-glyph"></span>'
//			+ 'Add More Correct Options'
//			+ ' </button>'
			+ ' </div>'
			+ ' </div>'
			+ ' <div class="inc-ans-par-div col-sm-12 col-md-6 col-xl-6 col-lg-6">'
			+ ' <div id="mtpImgincAnstxtareaDiv_" class="inc-ans-div">'
			+ ' <div id="mtpImgincAns1" class="col-sm-12">'
			+ ' <h4 class="red-color">Incorrect Answer 1</h4>'
//			+ ' <textarea type="text" id=\"icmtpId1\" placeholder="Incorrect Answer 1" name="mtpincanstxt" title="Incorrect Answer 1" class="inCorrectAnsBlk form-control hasclear inc-ans"></textarea>'
			+ '<input  type="file"  id="icmtpImgId1" name="mtpImgincanstxt" title="Upload incorrect Answer File 1" col="8" onchange="com.coep.test.addProblem.onSelInCorAnsFile(this.id,'+mtpimgincanstxtcnt+','+Id+')" class="inCorrectAnsBlk form-control hasclear inc-ans imgOption1" >'
			+ '<div id="icmtpImgId_error1" class="red-color"></div>'
			+'<div id="opt-preview-div-icmtpImgId1" class="opt-preview-div-icmtpImgId1"></div>'
			+ '</div>'
			+ '</div>'
			+ '<div class="col-sm-12">'
			+ '<button type="button" onclick="addmtpImgIncAnsTxtOpt(\'mtpImgincAnstxtareaDiv_\')" id="addIncAns" title="Add More Incorrect Options" class="btn btn-danger add-more-incquest">'
			+ '<span class="icon-add inc-ans-glyph"></span>Add More Incorrect Options'
			+ '</button>' + '</div>' + '</div>' + '</div>';

		$('#new-div').html(mtp);
		$('#mtpImgDiv' + Id).addClass('activeanstype');
		$('#mtpImgDiv' + Id).show();

	}
	
	
	// Image - multiple correct Answer
	AP.mcaImageDivFunction = function(Id) {

		var mca = '<div id="mcaImgDiv'
				+ Id
				+ '" class="row action-divs display-none activeanstype" >'
				+ ' <div class="corr-ans-par-div col-sm-12 col-md-6 col-xl-6 col-lg-6">'
				+ '<div id="mcaImgcorrAnsinpDiv_"  >'
				+ ' <div id="mcaImgcorrAns1" class="col-sm-12">'
				+ ' <h4 class=\"corrans-label\" style=\"color:#0ec173\">Correct Answer 1</h4>'
//				+ ' <input type="text" id="mcaId1" placeholder="Correct Answer 1" name="mcacorransinp" title="Correct Answer 1" class="correctAnsBlk form-control hasclear corr-ans">'
				+ '<input  type="file"  id="mcaImgId1" name="mcaImgcorransinp" title="Upload Correct Answer File" col="8" onchange="com.coep.test.addProblem.onSelCorAnsFile(this.id,'+mcaimgcorransinpcnt+','+Id+')" class="correctAnsBlk form-control hasclear corr-ans imgOption1" >'
				+ '<div id="mcaImgId_error1" class="red-color"></div>'
				+'<div id="opt-preview-div-mcaImgId1" class="opt-preview-div-mcaImgId1 row"></div>'
				
				+ '    </div>'
				+ ' </div>'
				+ ' <div class="col-sm-12">'
				+ ' <button type="button" onclick="addmcaImgCorrAnsOpt(\'mcaImgcorrAnsinpDiv_\')" id="mcaImgaddCorrAns" title="Add More Correct Options" class="btn btn-success add-more-corrquest AddQueMore">'
				+ '  <span class="icon-add corr-ans-glyph"></span>'
				+ ' Add More Correct Options'
				+ '    </button>'
				+ '  </div>'
				+ '</div>'
				+ ' <div class="inc-ans-par-div col-sm-12 col-md-6 col-xl-6 col-lg-6">'
				+ '  <div id="mcaImgincAnsinpDiv_" class="inc-ans-div">'
				+ '      <div id="mcaImgincAns1" class="col-sm-12">'
				+ '          <h4 class="red-color">Incorrect Answer 1</h4>'
//				+ '          <input type="text" id="micaId1" placeholder="Incorrect Answer 1" name="mcaincansinp" title="Incorrect Answer 1" class="inCorrectAnsBlk form-control hasclear inc-ans">'
				+ '<input  type="file"  id="mImgicaId1" name="mcaImgincansinp" title="Upload incorrect Answer File 1" col="8" onchange="com.coep.test.addProblem.onSelInCorAnsFile(this.id,'+mcaimgincansinpcnt+','+Id+')" class="inCorrectAnsBlk form-control hasclear inc-ans imgOption1" >'
				+ '<div id="mImgicaId1_error1" class="red-color"></div>'
				+'<div id="opt-preview-div-mImgicaId1" class="opt-preview-div-mImgicaId1 row"></div>'
				
				+ '      </div>'
				+ '   </div>'
				+ '   <div class="col-sm-12">'
				+ '     <button type="button" onclick="addmcaImgIncAnsInpOpt(\'mcaImgincAnsinpDiv_\')" id="mcaImgaddIncAns" title="Add More Incorrect Options" class="btn btn-danger  add-more-incquest AddQueMore">'
				+ '        <span class="icon-add inc-ans-glyph"></span>Add More Incorrect Options'
				+ '   </button>' + '     </div>' + '  </div>' + ' </div>';

		$('#new-div').html(mca);
		$("#mcaImgDiv" + Id).addClass('activeanstype');
		$("#mcaImgDiv" + Id).show();

	}
	
	
	
	
	AP.onSelCorAnsFile = function(optionId, Id, selAnsTypeId) {
		
		var file = $('#'+optionId);
		var filename = $.trim(file.val());
		
		if(file != "" && filename != ""){
			
			var formData = new FormData();
			formData.append('file', $('#'+optionId)[0].files[0]);

//			var fileSize = ($('input[type=file]')[0].files[0].size / 1024 / 1024)
			var fileSize = ($('#'+optionId)[0].files[0].size / 1024 / 1024)

			var valid_extensions = /(\.png|\.jpeg|\.jpg)$/i;
			if (filename != null || filename != undefined)
				if (valid_extensions.test(filename)
						&& fileSize <= AP.maxSizeImage) {
					
					 AP.uplaodOptionMediaFile(AP.baseURL + "media",
							"POST", formData, optionId, Id);
					
					 if(selAnsTypeId == '6'){
						$("#scaImgId_error"+Id).html("");	
					}else if (selAnsTypeId == '7') {
						$("#mtpImgId_error"+Id).html("");
					}else if (selAnsTypeId == '8') {
						$("#mcaImgId_error"+Id).html(""); // this is for  image mca
 					}
					
					
					
				} else {
					
					 if(selAnsTypeId == '6'){
						$("#scaImgId_error"+Id)
								.html("<h4> 1)File Size Should Not exceeds "+AP.maxSizeImage+" MB. <br> 2)supported file formats -  '.png' ,'.jpeg ','.jpg'</h4>");
						$("#scaImgId_error"+Id).css('display', 'block');
						
					 }else if (selAnsTypeId == '7') {
						 $("#mtpImgId_error"+Id)
							.html("<h4> 1)File Size Should Not exceeds "+AP.maxSizeImage+" MB. <br> 2)supported file formats -  '.png' ,'.jpeg ','.jpg'</h4>");
						 $("#mtpImgId_error"+Id).css('display', 'block');
					 }else if (selAnsTypeId == '8') {
						 $("#mcaImgId_error"+Id)
							.html("<h4> 1)File Size Should Not exceeds "+AP.maxSizeImage+" MB. <br> 2)supported file formats -  '.png' ,'.jpeg ','.jpg'</h4>");
						 $("#mcaImgId_error"+Id).css('display', 'block');
					 }
					 
					 
					 $('#'+optionId).val(null);
					// alert("File size must under 10 mb!");

				}

		}else{
//			$(".step8").html("");
			alert("Please select file");
		}
	
		
	}
	
	
	 
	
	AP.onSelInCorAnsFile = function(optionId,Id,selAnsTypeId) {
		
		var file = $('#'+optionId);
		var filename = $.trim(file.val());
		
		if(file != "" && filename != ""){
			
			var formData = new FormData();
			formData.append('file', $('#'+optionId)[0].files[0]);

//			var fileSize = ($('input[type=file]')[0].files[0].size / 1024 / 1024)
			var fileSize = ($('#'+optionId)[0].files[0].size / 1024 / 1024)

			var valid_extensions = /(\.png|\.jpeg|\.jpg)$/i;
			if (filename != null || filename != undefined)
				if (valid_extensions.test(filename)
						&& fileSize <= AP.maxSizeImage) {
					
					 AP.uplaodOptionMediaFile(AP.baseURL + "media",
								"POST", formData, optionId, Id);
					 
					
					 
//					 $("#sImgicaId_error"+Id).html("");
					 
					 if(selAnsTypeId == '6'){
							$("#sImgicaId_error"+Id).html("");	
						}else if (selAnsTypeId == '7') {
							$("#icmtpImgId_error"+Id).html("");
						}else if (selAnsTypeId == '8') {
							$("#mImgicaId_error"+Id).html(""); // this is for  image mca
	 					}
						
				} else {
					
					 if(selAnsTypeId == '6'){
						$("#sImgicaId_error"+Id)
								.html("<h4> 1)File Size Should Not exceeds "+AP.maxSizeImage+" MB. <br> 2)supported file formats -  '.png' ,'.jpeg ','.jpg'</h4>");
						$("#sImgicaId_error"+Id).css('display', 'block');
						
					 }else if (selAnsTypeId == '7') {
						 $("#icmtpImgId_error"+Id)
							.html("<h4> 1)File Size Should Not exceeds "+AP.maxSizeImage+" MB. <br> 2)supported file formats -  '.png' ,'.jpeg ','.jpg'</h4>");
						 $("#icmtpImgId_error"+Id).css('display', 'block');
					 }else if (selAnsTypeId == '8') {
						 $("#mImgicaId_error"+Id)
							.html("<h4> 1)File Size Should Not exceeds "+AP.maxSizeImage+" MB. <br> 2)supported file formats -  '.png' ,'.jpeg ','.jpg'</h4>");
						 $("#mImgicaId_error"+Id).css('display', 'block');
					 }
					 
					 
					 $('#'+optionId).val(null);
					// alert("File size must under 10 mb!");


				}

		}else{
			alert("Please select file");
		}
	
		
	}
	
	
	AP.uplaodOptionMediaFile =  function(url, methodType, formData, optionId, Id) {

		$.ajax({
			url : url,
			type : methodType,
			data : formData,
			enctype : "multipart/form-data",
			processData : false,
			contentType : false,
			dataType : 'json',
		}).done(function(data) {
			
			if(data.done == true){
				
				$("#"+optionId).hide();
//				$("#repalceSolBtn").show();
				AP.mediaOptURL = data.URL;
				
				$("#"+optionId).attr("img-media",data.URL);
				AP.previewOptionClick(data.URL, optionId, Id);
				
			}else{
				$("#"+optionId).attr("img-media","");
				alert("Something went wrong.. please upload the file again..");
			}
			

		}).fail(function(jqXHR, textStatus) {
			showToast.show('File upload failed ...', false);
		});
		
	}
	
	
	
	
	
	
	AP.removeQuestionFormQuesGrp = function(url, methodType, questionGruopJSON, baseData) {
		$.ajax({
			type : methodType,
			url : url,
			data : JSON.stringify(questionGruopJSON),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == false) {
				} else {
					if (data.length != 0) {
//						flag = 1;
						showToast.show('Question removed from question group',
								true);
//						QuesCount--;
						AP.modifyQuestionGroup(data.QGID, questionGruopJSON, baseData);
					} else {
					}
				}
			},
			error : function() {
			}

		});
	}
	
	
	
//	AP.modifiedQuestionDivFun = function(questionGruopJSON, i) {
//		
//	}
	
	
	/// renders answer types after clicking on edit question
	
	
	
	AP.scaAnswerType = function(answers, Id) {

		var sca1 = '';
		var sca2 = '';
		var sca11 = '';
		var sca22 = '';
		var sca = '<div id="scaDiv' + Id
				+ '" class="row action-divs display-none activeanstype" >'
				+ '<div class="corr-ans-par-div col-sm-12 col-md-6 col-xl-6 col-lg-6">'
				+ '<div id="scacorrAnsinpDiv' + Id + '"  >' + '</div>'
				+ '<div id="corr-ans-par-div" class="col-sm-12">' + '</div>'
				+ '</div>'
				+ '<div class="inc-ans-par-div col-sm-12 col-md-6 col-xl-6 col-lg-6 ">'
				+ '<div id="scaincAnsinpDiv' + Id + '" class="inc-ans-div">'
				+ '</div>' + '<div id="inc-ans-par-div" class="col-sm-12">'
				+ '</div>' + '</div>' + '</div>';

		scacorransinpcnt = 0;
		scaincansinpcnt = 0;

		for (var j = 0; j < answers.length; j++) {

			var aId = (j + 1);

			if (answers[j].rightAnswer == true) {
				scacorransinpcnt++;
				sca1 += '<div class="row"><div id="scacorrAns'
						+ scacorransinpcnt
						+ '" class="col-sm-11 col-md-11 col-lg-11 col-xl-11 ">'

						+ '<h4 class=\"corrans-label\" style="color:#0ec173">Correct Answer '
						+ scacorransinpcnt
						+ '</h4>'
						+ '<input type="text" id="scaId'
						+ scacorransinpcnt
						+ '" value="'
						+ answers[j].content
						+ '" placeholder="Correct Answer '
						+ scacorransinpcnt
						+ '" name="scacorransinp" title="Correct Answer '
						+ scacorransinpcnt
						+ '" class="correctAnsBlk form-control hasclear corr-ans">'
//						+ '<span onclick="removeRecord(\'scacorrAnsInp\','
//						+ scacorransinpcnt
//						+ ',\'scacorrAnsinpDiv'
//						+ Id
//						+ '\')" class="clearer icon-delete rem-corr-ans-glyph">X</span>'
						+ '</div>'
						  +'<button id="id="SCACorrect'+scacorransinpcnt+'"" class="checkLatexAns" onclick="typesetInputForCMofifyOptionalSICA1(this,'+ scacorransinpcnt +')" data-toggle="modal" data-target="#latexDataSCAModifyAns1">{ L }</button></div>'

			}
			if (answers[j].rightAnswer == false) {
				scaincansinpcnt++;
				sca2 += '<div id="scaincAns'
						+ scaincansinpcnt
						+ '" class="col-sm-11 col-md-11 col-lg-11 col-xl-11 ">'
						+ '<h4 class="red-color">Incorrect Answer '
						+ scaincansinpcnt
						+ '</h4>'
						
						+ "<span onclick=\"removeRecord('scaincAnsInp',"
						+ scaincansinpcnt
						+ ",'scaincAnsinpDiv"
						+ Id
						+ "')\" class=\"closeAnsOption clearer icon-delete rem-corr-ans-glyph\" >X</span>"
						
						+ '<input type="text" id="sicaId'
						+ scaincansinpcnt
						+ '" value="'
						+ answers[j].content
						+ '" placeholder="Incorrect Answer '
						+ scaincansinpcnt
						+ '" name="scaincansinp" title="Incorrect Answer '
						+ scaincansinpcnt
						+ '" class="inCorrectAnsBlk form-control hasclear inc-ans">'
//						+ '<span onclick="removeRecord(\'scaincAnsInp\','
//						+ scaincansinpcnt
//						+ ',\'scaincAnsinpDiv'
//						+ Id
//						+ '\')" class="clearer icon-delete rem-inc-ans-glyph">X</span>'
						+ ' </div>'
						  +'<button id="SCAWrong'+scaincansinpcnt+'" class="checkLatexAnsAxt2" onclick="typesetInputForWMofifyOptionalSICA1(this,'+ scaincansinpcnt +')" data-toggle="modal" data-target="#latexDataSCAModifyAns1">{ L }</button>'

						
						  
			}
			
		}

//		sca11 += '<button type="button" onclick="addscaCorrAnsOpt(\'scacorrAnsinpDiv'
//				+ Id
//				+ '\')" id="addCorrAns" title="Add More Correct Options" class="btn btn-success add-more-corrquest ">'
//				+ '<span class="icon-add corr-ans-glyph"></span>'
//				+ 'Add More Correct Options' + '</button>';

		sca22 += '<button type="button" onclick="addscaIncAnsInpOpt(\'scaincAnsinpDiv'
				+ Id
				+ '\')" id="addIncAns" title="Add More Incorrect Options" class="btn btn-danger add-more-incquest">'
				+ '<span class="icon-add inc-ans-glyph"></span>Add More Incorrect Options'
				+ '</button>'

	//   add que modify model for Singular Correct Answer start
		+ '<div id="latexDataSCAModifyAns1" class="modal fade" role="dialog">' 
		+ '<div class="modal-dialog">'

		+ ' <div class="modal-content">'
		+ '<div class="modal-header">'
		+ ' <span class="modal-title">Latex Equation</span>'
		+ ' <button type="button" class="close" data-dismiss="modal">&times;</button>'
		+ ' </div>'
		
		+ '<div class="modal-body"  style="  width: 500px;  height: auto;  overflow-x: auto;">' //
		+' <div class="scoll-tree">'
		+' <div id="MathPreview" class="" placeholder="Latex equation" ></div><br/>'
//		
		+ '	</div>'
		+ ' </div>'
		+ '	<div class="modal-footer">'
		+ '<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color:red; color:#fff;">Close</button>'
		+ '</div>'
		+ '</div>'
		// add que modify model for Singular Correct Answer solution end  
		
		$('#new-div').html(sca);
		$('#scacorrAnsinpDiv' + Id).html(sca1);
		$('#corr-ans-par-div').html(sca11);
		$('#scaincAnsinpDiv' + Id).html(sca2);
		$('#inc-ans-par-div').html(sca22);
		$("#scaDiv" + Id).addClass('activeanstype');
		$("#scaDiv" + Id).show();
		

	};
	
	
	
	/// sca Image question
	
	AP.scaImgAnswerType = function(answers, Id) {

		var sca1 = '';
		var sca2 = '';
		var sca11 = '';
		var sca22 = '';
		var sca = '<div id="scaImgDiv' + Id
				+ '" class="row action-divs display-none activeanstype" >'
				+ '<div class="corr-ans-par-div col-sm-12 col-md-6 col-xl-6 col-lg-6">'
				+ '<div id="scaImgcorrAnsinpDiv' + Id + '"  >' + '</div>'
				+ '<div id="corr-ans-par-div" class="col-sm-12">' + '</div>'
				+ '</div>'
				+ '<div class="inc-ans-par-div col-sm-12 col-md-6 col-xl-6 col-lg-6">'
				+ '<div id="scaImgincAnsinpDiv' + Id + '" class="inc-ans-div">'	+ '</div>' 
				+ '<div id="inc-ans-par-div" class="col-sm-12">'+ '</div>' 
				+ '</div>'
				+ '</div>';

		 scaimgcorransinpcnt = 0;
		 scaimgincansinpcnt = 0; 

		for (var j = 0; j < answers.length; j++) {

			var aId = (j + 1);

			if (answers[j].rightAnswer == true) {
				scaimgcorransinpcnt++;
				sca1 += '<div id="scaImgcorrAns'
						+ scaimgcorransinpcnt
						+ '" class="col-sm-12">'

						+ '<h4 class=\"corrans-label\" style="color:#0ec173">Correct Answer '
						+ scaimgcorransinpcnt
						+ '</h4>'
						+ '<input type="file" id="scaImgId'
						+ scaimgcorransinpcnt
						+ '" value="'
						+ answers[j].content
						+ '" placeholder="Correct Answer '
						+ scaimgcorransinpcnt
						+ '" name="scaImgcorransinp" title="Correct Answer '
						+ scaimgcorransinpcnt
						+ '" onchange="com.coep.test.addProblem.onSelCorAnsFile(this.id,'+scaimgcorransinpcnt+')" img-media="'+ answers[j].content+'" class="correctAnsBlk form-control hasclear corr-ans">'
//						+ '<span onclick="removeRecord(\'scacorrAnsInp\','
//						+ scaimgcorransinpcnt
//						+ ',\'scacorrAnsinpDiv'
//						+ Id
//						+ '\')" class="clearer icon-delete rem-corr-ans-glyph">X</span>'
						+ '<div id="scaImgId_error'+scaimgcorransinpcnt+'" class="red-color"></div>'
						+'<div id="opt-preview-div-scaImgId'+scaimgcorransinpcnt+'" class="opt-preview-div-scaImgId'+scaimgcorransinpcnt+'"></div>'
						+ '</div>';

			}
			if (answers[j].rightAnswer == false) {
				scaimgincansinpcnt++;
				sca2 += '<div id="scaImgincAns'
						+ scaimgincansinpcnt
						+ '" class="col-sm-12">'
						+ '<h4 class="red-color">Incorrect Answer '
						+ scaimgincansinpcnt
						+ '</h4>'
						+ "<span onclick=\"removeRecord('scaimgincAnsInp',"
						+ scaimgincansinpcnt
						+ ",'scaImgincAnsinpDiv"
						+ Id
						+ "')\" class=\"closeAnsOption clearer icon-delete rem-inc-ans-glyph\" >X</span>"
						+ '<input type="file" id="sImgicaId'
						+ scaimgincansinpcnt
						+ '" value="'
						+ answers[j].content
						+ '" placeholder="Incorrect Answer File '
						+ scaimgincansinpcnt
						+ '" name="scaImgincansinp" title="Incorrect Answer File'
						+ scaimgincansinpcnt
						+ '"  onchange="com.coep.test.addProblem.onSelInCorAnsFile(this.id,'+scaimgincansinpcnt+')" img-media="'+ answers[j].content+'" class="inCorrectAnsBlk form-control hasclear inc-ans">'
//						+ '<span onclick="removeRecord(\'scaincAnsInp\','
//						+ scaimgincansinpcnt
//						+ ',\'scaincAnsinpDiv'
//						+ Id
//						+ '\')" class="clearer icon-delete rem-inc-ans-glyph">X</span>'
						+ '<div id="sImgicaId_error'+scaimgincansinpcnt+'" class="red-color"></div>'
						+'<div id="opt-preview-div-sImgicaId'+scaimgincansinpcnt+'" class="opt-preview-div-sImgicaId'+scaimgincansinpcnt+'"></div>'
						+ ' </div>';

			}

		}

//		sca11 += '<button type="button" onclick="addscaCorrAnsOpt(\'scacorrAnsinpDiv'
//				+ Id
//				+ '\')" id="addCorrAns" title="Add More Correct Options" class="btn add-more-corrquest">'
//				+ '<span class="icon-add corr-ans-glyph"></span>'
//				+ 'ADD MORE CORRECT OPTIONS' + '</button>';

		sca22 += '<button type="button" onclick="addImgscaIncAnsInpOpt(\'scaImgincAnsinpDiv'
				+ Id
				+ '\')" id="addIncAns" title="Add More Incorrect Options" class="btn btn-danger add-more-incquest">'
				+ '<span class="icon-add inc-ans-glyph"></span>Add More Incorrect Options'
				+ '</button>';

		$('#new-div').html(sca);
		$('#scaImgcorrAnsinpDiv' + Id).html(sca1);
		$('#corr-ans-par-div').html(sca11);
		$('#scaImgincAnsinpDiv' + Id).html(sca2);
		$('#inc-ans-par-div').html(sca22);
		$("#scaImgDiv" + Id).addClass('activeanstype');
		$("#scaImgDiv" + Id).show();

//		$("#repalceOptBtn-").show();

		 scaimgcorransinpcnt = 0;
		 scaimgincansinpcnt = 0; /// zz
		for (var op = 0; op < answers.length; op++) {

//			var aId = (op + 1);
			
			if (answers[op].rightAnswer == true) {
				scaimgcorransinpcnt++;
				$("#scaImgId"+scaimgcorransinpcnt).hide();
				$("#repalceOptBtn-scaImgId"+scaimgcorransinpcnt).show();
				AP.previewOptionClick(answers[op].content, "scaImgId"+scaimgcorransinpcnt, Id);

			}
			if (answers[op].rightAnswer == false) {
				scaimgincansinpcnt++;
				$("#sImgicaId"+scaimgincansinpcnt).hide();
				$("#repalceOptBtn-sImgicaId"+scaimgincansinpcnt).show();
				AP.previewOptionClick(answers[op].content, "sImgicaId"+scaimgincansinpcnt, Id);
			}
		}
	};
	
	
	AP.fibAnswerType = function(answers, Id) {

		var fib1 = '';
		var fib2 = '';
		var fib11 = '';
		var fib22 = '';
		
		var fib = '<div id="fibDiv' + Id
		+ '" class="row action-divs display-none activeanstype" >'
		+ '<div class="corr-ans-par-div col-sm-12 col-md-6 col-xl-6 col-lg-6">'
		+ '<div id="fibcorrAnsinpDiv' + Id + '"  >' + '</div>'
		+ '<div id="corr-ans-par-div" class="col-sm-12">' + '</div>'
		+ '</div>'
		+ '<div class="inc-ans-par-div col-sm-12 col-md-6 col-xl-6 col-lg-6">'
		+ '<div id="fibincAnsinpDiv' + Id + '" class="inc-ans-div">'	+ '</div>' 
		+ '<div id="inc-ans-par-div" class="col-sm-12">'+ '</div>' 
		+ '</div>'
		+ '</div>';

		fibcorransinpcnt = 0;
		fibincansinpcnt = 0;

		for (var j = 0; j < answers.length; j++) {

			var aId = (j + 1);

			if (answers[j].rightAnswer == true) {
				fibcorransinpcnt++;
				fib1 += '<div id="fibcorrAns'
						+ fibcorransinpcnt
						+ '" class="col-sm-11 col-md-11 col-xl-11 col-lg-11">'

						+ '<h4 class=\"corrans-label\" style="color:#0ec173">Correct Answer '
						+ fibcorransinpcnt
						+ '</h4>'
						+ '<span onclick="removeRecord(\'fibcorrAnsInp\','
						+ fibcorransinpcnt
						+ ',\'fibcorrAnsinpDiv'
						+ Id
						+ '\')" class="closeAnsOption clearer icon-delete rem-corr-ans-glyph">X</span>'
						+ '<input type="text" id="fibId'
						+ fibcorransinpcnt
						+ '" value="'
						+ answers[j].content
						+ '" placeholder="Correct Answer '
						+ fibcorransinpcnt
						+ '" name="fibcorransinp" title="Correct Answer '
						+ fibcorransinpcnt
						+ '" class="correctAnsBlk form-control hasclear corr-ans">'
//						+ '<span onclick="removeRecord(\'fibcorrAnsInp\','
//						+ fibcorransinpcnt
//						+ ',\'fibcorrAnsinpDiv'
//						+ Id
//						+ '\')" class="clearer icon-delete rem-corr-ans-glyph">X</span>'
						+ '</div>'
						+'<button id="IFIBCorrect'+fibcorransinpcnt+'" class="checkLatexAnsAxt2" onclick="typesetInputForCMofifyOptionalFIB(this,'+ fibcorransinpcnt +')" data-toggle="modal" data-target="#latexDatafibModifyAns1">{ L }</button>'

			}

			if (answers[j].rightAnswer == false) {
				fibincansinpcnt++;
				fib2 += '<div id="fibincAns'
						+ fibincansinpcnt
						+ '" class="col-sm-11 col-md-11 col-xl-11 col-lg-11">'
						+ '<h4 class="red-color">Incorrect Answer '
						+ fibincansinpcnt
						+ '</h4>'
						+ '<span onclick="removeRecord(\'fibincAnsInp\','
						+ fibincansinpcnt
						+ ',\'fibincAnsinpDiv'
						+ Id
						+ '\')" class="closeAnsOption clearer icon-delete rem-corr-ans-glyph">X</span>'
						+ '<input type="text" id="micaId'
						+ fibincansinpcnt
						+ '" value="'
						+ answers[j].content
						+ '" placeholder="Incorrect Answer '
						+ fibincansinpcnt
						+ '" name="fibincansinp" title="Incorrect Answer '
						+ fibincansinpcnt
						+ '" class="inCorrectAnsBlk form-control hasclear inc-ans">'
//						+ '<span onclick="removeRecord(\'fibincAnsInp\','
//						+ fibincansinpcnt
//						+ ',\'fibincAnsinpDiv'
//						+ Id
//						+ '\')" class="clearer glyphicon glyphicon-remove rem-inc-ans-glyph">X</span>'
						+ ' </div>'
						  +'<button id="IFIBWrong'+fibincansinpcnt+'" class="checkLatexAnsAxt2" onclick="typesetInputForWMofifyOptionalFIB(this,'+ fibincansinpcnt +')" data-toggle="modal" data-target="#latexDatafibModifyAns1">{ L }</button>'
//						  +'<button id="IFIBWrong'+fibincansinpcnt+'" class="checkLatexAnsAxt" onclick="typesetInputForIFIB(this,'+ fibincansinpcnt +')" data-toggle="modal" data-target="#latexDataWrongAnsIFIB1">{ L }</button>'
			
	}

		}

//		fib11 += '<button type="button" onclick="addfibCorrAnsOpt(\'fibcorrAnsinpDiv'
//				+ Id
//				+ '\')" id="addCorrAns" title="Add More Correct Options" class="btn btn-success add-more-corrquest">'
//				+ '<span class="glyphicon glyphicon-plus corr-ans-glyph"></span>'
//				+ 'Add More Correct Options' + '</button>';

		fib22 += '<button type="button" onclick="addfibIncAnsInpOpt(\'fibincAnsinpDiv'
				+ Id
				+ '\')" id="addIncAns" title="Add More Incorrect Options" class="btn btn-danger  add-more-incquest">'
				+ '<span class="glyphicon glyphicon-plus inc-ans-glyph"></span>Add More Incorrect Options'
				+ '</button>'
			//   add que modify model for Singular Correct Answer start
				+ '<div id="latexDatafibModifyAns1" class="modal fade" role="dialog">' 
				+ '<div class="modal-dialog">'

				+ ' <div class="modal-content">'
				+ '<div class="modal-header">'
				+ ' <span class="modal-title">Latex Equation</span>'
				+ ' <button type="button" class="close" data-dismiss="modal">&times;</button>'
				+ ' </div>'
				
				+ '<div class="modal-body"  style="  width: 500px;  height: auto;  overflow-x: auto;">' //
				+' <div class="scoll-tree">'
				+' <div id="MathPreviewfib" class="" placeholder="Latex equation" ></div><br/>'
//				
				+ '	</div>'
				+ ' </div>'
				+ '	<div class="modal-footer">'
				+ '<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color:red; color:#fff;">Close</button>'
				+ '</div>'
				+ '</div>'
				// add que modify model for Singular Correct Answer solution end  

		$('#new-div').html(fib);
		$('#fibcorrAnsinpDiv' + Id).html(fib1);
		$('#corr-ans-par-div').html(fib11);
		$('#fibincAnsinpDiv' + Id).html(fib2);
		$('#inc-ans-par-div').html(fib22);
		$("#fibDiv" + Id).addClass('activeanstype');
		$("#fibDiv" + Id).show();
		
	}

	AP.mtpAnswerType = function(answers, Id) {

		var mtp1 = '';
		var mtp2 = '';
		var mtp11 = '';
		var mtp22 = '';
		var mtp = '<div id="mtpDiv' + Id
				+ '" class="row action-divs display-none activeanstype " >'
				+ '<div class="corr-ans-par-div col-sm-12 col-md-6 col-xl-6 col-lg-6">'
				+ '<div id="corrAnstxtareaDiv' + Id + '"  >' + '</div>'
				+ '<div id="corr-ans-par-div" class="col-sm-12">' + '</div>'
				+ '</div>'
				+ '<div class="inc-ans-par-div col-sm-12 col-md-6 col-xl-6 col-lg-6">'
				+ '<div id="incAnstxtareaDiv' + Id + '" class="inc-ans-div">'
				+ '</div>' + '<div id="inc-ans-par-div" class="col-sm-12">'
				+ '</div>' + '</div>' + '</div>';

		mtpcorranstxtcnt = 0;
		mtpincanstxtcnt = 0;

		for (var j = 0; j < answers.length; j++) {

			var aId = (j + 1);

			if (answers[j].rightAnswer == true) {
				mtpcorranstxtcnt++;
				mtp1 += '<div id="mtpcorrAns'
						+ mtpcorranstxtcnt
						+ '" class="col-sm-11 col-md-11 col-xl-11 col-lg-11">'

						+ '<h4 class=\"corrans-label\" style="color:#0ec173">Correct Answer '
						+ mtpcorranstxtcnt
						+ '</h4>'
						+ '<span onclick="removeRecord(\'corrAnsTxt\','
						+ mtpcorranstxtcnt
						+ ',\'corrAnstxtareaDiv'
						+ Id
						+ '\')" class="closeAnsOptionMTP clearer icon-delete rem-corr-ans-glyph">X</span>'
						
						+ '<textarea type="text" id="mtpId'
						+ mtpcorranstxtcnt
						+ '" value="'
						+ answers[j].content
						+ '" placeholder="Correct Answer '
						+ mtpcorranstxtcnt
						+ '" name="mtpcorranstxt" title="Correct Answer '
						+ mtpcorranstxtcnt
						+ '" class="correctAnsBlk form-control hasclear corr-ans">'
						+ answers[j].content
						+ '</textarea>'
//						+ '<span onclick="removeRecord(\'corrAnsTxt\','
//						+ mtpcorranstxtcnt
//						+ ',\'corrAnstxtareaDiv'
//						+ Id
//						+ '\')" class="clearer icon-delete rem-corr-ans-glyph">X</span>'
						+ '</div>'
						 +'<button id="MTPCorrect'+ mtpincanstxtcnt +'" class="checkLatexAnsAxt2" onclick="typesetInputForCMofifyOptionalMTPI(this,'+ mtpcorranstxtcnt +')" data-toggle="modal" data-target="#latexDataMTPModifyAns1">{ L }</button></div>'
			}

			if (answers[j].rightAnswer == false) {
				mtpincanstxtcnt++;
				mtp2 += '<div id="mtpincAns'
						+ mtpincanstxtcnt
						+ '" class="col-sm-11 col-md-11 col-xl-11 col-lg-11">'
						+ '<h4 class="red-color">Incorrect Answer '
						+ mtpincanstxtcnt
						+ '</h4>'
						+ '<span onclick="removeRecord(\'incAnsTxt\','
						+ mtpincanstxtcnt
						+ ',\'incAnstxtareaDiv'
						+ Id
						+ '\')" class="closeAnsOptionMTP clearer icon-delete rem-corr-ans-glyph">X</span>'
						
						+ '<textarea type="text" id="micaId'
						+ mtpincanstxtcnt
						+ '" value="'
						+ answers[j].content
						+ '" placeholder="Incorrect Answer '
						+ mtpincanstxtcnt
						+ '" name="mtpincanstxt" title="Incorrect Answer '
						+ mtpincanstxtcnt
						+ '" class="inCorrectAnsBlk form-control hasclear inc-ans">'
						+ answers[j].content
						+ '</textarea>'
//						+ '<span onclick="removeRecord(\'incAnsTxt\','
//						+ mtpincanstxtcnt
//						+ ',\'incAnstxtareaDiv'
//						+ Id
//						+ '\')" class="clearer icon-delete rem-inc-ans-glyph">X</span>'
						+ ' </div>'
						 +'<button id="MTPIWrong'+ mtpincanstxtcnt +'" class="checkLatexAnsAxt2" onclick="typesetInputForWMofifyOptionalMTPI(this,'+ mtpincanstxtcnt +')" data-toggle="modal" data-target="#latexDataMTPModifyAns1">{ L }</button></div>'
			}

		}

//		mtp11 += '<button type="button" onclick="addmtpCorrAnsTxtOpt(\'corrAnstxtareaDiv'
//				+ Id
//				+ '\')" id="addCorrAns" title="Add More Correct Options" class="btn btn-success add-more-corrquest">'
//				+ '<span class="icon-add corr-ans-glyph"></span>'
//				+ 'Add More Correct Options' + '</button>';

		mtp22 += '<button type="button" onclick="addmtpIncAnsTxtOpt(\'incAnstxtareaDiv'
				+ Id
				+ '\')" id="addIncAns" title="Add More Incorrect Options" class="btn btn-danger add-more-incquest">'
				+ '<span class="icon-add inc-ans-glyph"></span>Add More Incorrect Options'
				+ '</button>'
			//   add que modify model for Singular Correct Answer start
				+ '<div id="latexDataMTPModifyAns1" class="modal fade" role="dialog">' 
				+ '<div class="modal-dialog">'

				+ ' <div class="modal-content">'
				+ '<div class="modal-header">'
				+ ' <span class="modal-title">Latex Equation</span>'
				+ ' <button type="button" class="close" data-dismiss="modal">&times;</button>'
				+ ' </div>'
				
				+ '<div class="modal-body"  style="  width: 500px;  height: auto;  overflow-x: auto;">' //
				+' <div class="scoll-tree">'
				+' <div id="MathPreviewMTP" class="" placeholder="Latex equation" ></div><br/>'
//				
				+ '	</div>'
				+ ' </div>'
				+ '	<div class="modal-footer">'
				+ '<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color:red; color:#fff;">Close</button>'
				+ '</div>'
				+ '</div>'
				// add que modify model for Singular Correct Answer solution end  

		$('#new-div').html(mtp);
		$('#corrAnstxtareaDiv' + Id).html(mtp1);
		$('#corr-ans-par-div').html(mtp11);
		$('#incAnstxtareaDiv' + Id).html(mtp2);
		$('#inc-ans-par-div').html(mtp22);
		$("#mtpDiv" + Id).addClass('activeanstype');
		$("#mtpDiv" + Id).show();

	}
	
	
	
	/// Image - mtp modify
	
	AP.mtpImgAnswerType = function(answers, Id) {

		var mtp1 = '';
		var mtp2 = '';
		var mtp11 = '';
		var mtp22 = '';
		var mtp = '<div id="mtpImgDiv' + Id
				+ '" class="row action-divs display-none activeanstype " >'
				+ '<div class="corr-ans-par-div col-sm-12 col-md-6 col-xl-6 col-lg-6">'
				+ '<div id="mtpImgcorrAnstxtareaDiv' + Id + '"  >' + '</div>'
				+ '<div id="corr-ans-par-div" class="col-sm-12">' + '</div>'
				+ '</div>'
				+ '<div class="inc-ans-par-div col-sm-12 col-md-6 col-xl-6 col-lg-6">'
				+ '<div id="mtpImgincAnstxtareaDiv' + Id + '" class="inc-ans-div">'
				+ '</div>' + '<div id="inc-ans-par-div" class="col-sm-12">'
				+ '</div>' + '</div>' + '</div>';

		mtpimgcorranstxtcnt = 0;
		mtpimgincanstxtcnt = 0;

		for (var j = 0; j < answers.length; j++) {

			var aId = (j + 1);

			if (answers[j].rightAnswer == true) {
				mtpimgcorranstxtcnt++;
				mtp1 += '<div id="mtpImgcorrAns'
						+ mtpimgcorranstxtcnt
						+ '" class="col-sm-12">'

						+ '<h4 class=\"corrans-label\" style="color:#0ec173">Correct Answer '
						+ mtpimgcorranstxtcnt
						+ '</h4>'
						+ "<span onclick=\"removeRecord('mtpImgcorrAnsTxt',"
						+ mtpimgcorranstxtcnt
						+ ")\" class=\"closeAnsOption clearer icon-delete rem-corr-ans-glyph\" >X</span>"
						
						+ '<input type="file" id="mtpImgId'
						+ mtpimgcorranstxtcnt
						+ '" value="'
						+ answers[j].content
						+ '" placeholder="Correct Answer '
						+ mtpimgcorranstxtcnt
						+ '" name="mtpImgcorranstxt" title="Correct Answer '
						+ mtpimgcorranstxtcnt
						+ '" onchange="com.coep.test.addProblem.onSelCorAnsFile(this.id,'+mtpimgcorranstxtcnt+')" img-media="'+ answers[j].content+'" class="correctAnsBlk form-control hasclear corr-ans">'
						
						+ '<div id="mtpImgId_error'+mtpimgcorranstxtcnt+'" class="red-color"></div>'
						+'<div id="opt-preview-div-mtpImgId'+mtpimgcorranstxtcnt+'" class="opt-preview-div-mtpImgId'+mtpimgcorranstxtcnt+'"></div>'
						+ '</div>';
			}

			if (answers[j].rightAnswer == false) {
				mtpimgincanstxtcnt++;
				mtp2 += '<div id="mtpImgincAns'
						+ mtpimgincanstxtcnt
						+ '" class="col-sm-12">'
						+ '<h4 class="red-color">Incorrect Answer '
						+ mtpimgincanstxtcnt
						+ '</h4>'
						+ "<span onclick=\"removeRecord('mtpImgincAnsTxt',"
						+ mtpimgincanstxtcnt
						+ ")\" class=\"closeAnsOption clearer icon-delete rem-inc-ans-glyph\" >X</span>"

						+ '<input type="file" id="icmtpImgId'
						+ mtpimgincanstxtcnt
						+ '" value="'
						+ answers[j].content
						+ '" placeholder="Incorrect Answer File '
						+ mtpimgincanstxtcnt
						+ '" name="mtpImgincanstxt" title="Incorrect Answer File'
						+ mtpimgincanstxtcnt
						+ '"  onchange="com.coep.test.addProblem.onSelInCorAnsFile(this.id,'+mtpimgincanstxtcnt+')" img-media="'+ answers[j].content+'" class="inCorrectAnsBlk form-control hasclear inc-ans">'

						+ '<div id="icmtpImgId_error'+mtpimgincanstxtcnt+'" class="red-color"></div>'
						+'<div id="opt-preview-div-icmtpImgId'+mtpimgincanstxtcnt+'" class="opt-preview-div-icmtpImgId'+mtpimgincanstxtcnt+'"></div>'
						+ ' </div>';
			}

		}

//		mtp11 += '<button type="button" onclick="addmtpImgCorrAnsTxtOpt(\'mtpImgcorrAnstxtareaDiv'
//				+ Id
//				+ '\')" id="mtpImgaddCorrAns" title="Add More Correct Options" class="btn btn-success add-more-corrquest">'
//				+ '<span class="icon-add corr-ans-glyph"></span>'
//				+ 'Add More Correct Options' + '</button>';

		mtp22 += '<button type="button" onclick="addmtpImgIncAnsTxtOpt(\'mtpImgincAnstxtareaDiv'
				+ Id
				+ '\')" id="mtpImgaddIncAns" title="Add More Incorrect Options" class="btn btn-danger add-more-incquest">'
				+ '<span class="icon-add inc-ans-glyph"></span>Add More Incorrect Options'
				+ '</button>';

		$('#new-div').html(mtp);
		$('#mtpImgcorrAnstxtareaDiv' + Id).html(mtp1);
		$('#corr-ans-par-div').html(mtp11);
		$('#mtpImgincAnstxtareaDiv' + Id).html(mtp2);
		$('#inc-ans-par-div').html(mtp22);
		$("#mtpImgDiv" + Id).addClass('activeanstype');
		$("#mtpImgDiv" + Id).show();
		
		mtpimgcorranstxtcnt = 0;
		mtpimgincanstxtcnt = 0;   
		for (var op = 0; op < answers.length; op++) {

			if (answers[op].rightAnswer == true) {
				mtpimgcorranstxtcnt++;
				$("#mtpImgId"+mtpimgcorranstxtcnt).hide();
				$("#repalceOptBtn-mtpImgId"+mtpimgcorranstxtcnt).show();
				AP.previewOptionClick(answers[op].content, "mtpImgId"+mtpimgcorranstxtcnt, Id);

			}
			if (answers[op].rightAnswer == false) {
				mtpimgincanstxtcnt++;
				$("#icmtpImgId"+mtpimgincanstxtcnt).hide();
				$("#repalceOptBtn-icmtpImgId"+mtpimgincanstxtcnt).show();
				AP.previewOptionClick(answers[op].content, "icmtpImgId"+mtpimgincanstxtcnt, Id);
			}
		}

	}
	
	

	AP.tfAnswerType = function(answers, Id) {

		var tf1 = '';
		var tf2 = '';

		var tfa = '<div id="truefalseDiv' + Id
				+ '" class="row action-divs display-none activeanstype" >'
				+ '<div class="corr-ans-par-div col-sm-12 col-md-6 col-xl-6 col-lg-6">'
				+ '<div id=\"truefalsecorrAnsinpDiv' + Id + '\" >' + '</div>'
				+ '</div>'
				+ '<div class="inc-ans-par-div col-sm-12 col-md-6 col-xl-6 col-lg-6">'
				+ '<div id=\"truefalseincAnsinpDiv' + Id
				+ '\" class=\"inc-ans-div\">' + '</div>' + '</div>' + '</div>';

		for (var j = 0; j < answers.length; j++) {

			var aId = (j + 1);

			if (answers[j].rightAnswer == true) {

				tf1 += '<div id=\"truefalsecorrAns'
						+ Id
						+ '\" class="col-sm-11 col-md-11 col-xl-11 col-lg-11">'
						+ '<h4 class=\"corrans-label\" style="color:#0ec173">Correct Answer</h4>'
						+ '<input type="text" id="tfId1" value="'
						+ answers[j].content
						+ '" placeholder="Correct Answer" name="tfcorransinp" title="Correct Answer" class="correctAnsBlk form-control hasclear corr-ans" >'
						+ '</div>'
						 +'<button id="MTPCorrect'+ mtpincanstxtcnt +'" class="checkLatexAnsAxt2" onclick="typesetInputForTF1(this)" data-toggle="modal" data-target="#latexDataTF">{ L }</button></div>'

			}
			if (answers[j].rightAnswer == false) {

				tf2 += '<div id=\"truefalseincAns'
						+ Id
						+ '\"  class="col-sm-11 col-md-11 col-xl-11 col-lg-11">'
						+ '<h4 class="red-color">Incorrect Answer</h4>'
						+ '<input type="text" id="ictfId1" value="'
						+ answers[j].content
						+ '" placeholder="Incorrect Answer" name="tfincansinp" title="Incorrect Answer" class="inCorrectAnsBlk form-control hasclear inc-ans" >'
						+ ' </div>'
						 +'<button id="MTPCorrect'+ mtpincanstxtcnt +'" class="checkLatexAnsAxt2" onclick="typesetInputForTFI1(this)" data-toggle="modal" data-target="#latexDataTF">{ L }</button></div>'

			}

		}

	//   add que modify model for Singular Correct Answer start
		tf1 += '<div id="latexDataTF" class="modal fade" role="dialog">' 
		+ '<div class="modal-dialog">'

		+ ' <div class="modal-content">'
		+ '<div class="modal-header">'
		+ ' <span class="modal-title">Latex Equation</span>'
		+ ' <button type="button" class="close" data-dismiss="modal">&times;</button>'
		+ ' </div>'
		
		+ '<div class="modal-body"  style="  width: 500px;  height: auto;  overflow-x: auto;">' //
		+' <div class="scoll-tree">'
		+' <div id="MathPreviewTF" class="" placeholder="Latex equation" ></div><br/>'
//		
		+ '	</div>'
		+ ' </div>'
		+ '	<div class="modal-footer">'
		+ '<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color:red; color:#fff;">Close</button>'
		+ '</div>'
		+ '</div>'
		// add que modify model for Singular Correct Answer solution end  
		$('#new-div').html(tfa);
		$('.corr-ans-par-div').html(tf1);
		// $('#corr-ans-par-div').html(fib11);
		$('.inc-ans-par-div').html(tf2);
		// $('#inc-ans-par-div').html(fib22);
		$("#truefalseDiv" + Id).addClass('activeanstype');
		$("#truefalseDiv" + Id).show();

	}

	
	/// Image - true or false 
	
	AP.tfImgAnswerType =  function(answers, Id) {

		var tf1 = '';
		var tf2 = '';

		var tfa = '<div id="truefalseImgDiv' + Id
				+ '" class="row action-divs display-none activeanstype" >'
				+ '<div class="corr-ans-par-div col-sm-12 col-md-6 col-xl-6 col-lg-6">'
				+ '<div id=\"truefalseImgcorrAnsinpDiv' + Id + '\" >' + '</div>'
				+ '</div>'
				+ '<div class="inc-ans-par-div col-sm-12 col-md-6 col-xl-6 col-lg-6">'
				+ '<div id=\"truefalseImgincAnsinpDiv' + Id
				+ '\" class=\"inc-ans-div\">' + '</div>' + '</div>' + '</div>';

		for (var j = 0; j < answers.length; j++) {

			var aId = (j + 1);

			if (answers[j].rightAnswer == true) {

				tf1 += '<div id=\"truefalseImgcorrAns'
						+ Id
						+ '\" class="col-sm-12">'
						+ '<h4 class=\"corrans-label\" style=\"color:#0ec173\">Correct Answer</h3>'
//						+ '<input type="text" id="tfId1" value="'
//						+ answers[j].content
//						+ '" placeholder="Correct Answer" name="tfcorransinp" title="Correct Answer" class="correctAnsBlk form-control hasclear corr-ans" disabled>'
						+ '<input  type="file"  id="tfImgId1" name="tfImgcorransinp" title="Upload Correct Answer File" col="8" onchange="com.coep.test.addProblem.onSelCorAnsFile(this.id,'+1+','+Id+')" img-media="'+ answers[j].content+'" class="correctAnsBlk form-control hasclear corr-ans imgOption1" >'
						+ '<div id="tfImgId_error1" class="red-color"></div>'
						+'<div id="opt-preview-div-tfImgId1" class="opt-preview-div-tfImgId1"></div>'
						+ '</div>';

			}
			if (answers[j].rightAnswer == false) {

				tf2 += '<div id=\"truefalseImgincAns'
						+ Id
						+ '\"  class="col-sm-12">'
						+ '<h4 class="red-color">Incorrect Answer</h4>'
//						+ '<input type="text" id="ictfId1" value="'
//						+ answers[j].content
//						+ '" placeholder="Incorrect Answer" name="tfincansinp" title="Incorrect Answer" class="inCorrectAnsBlk form-control hasclear inc-ans" disabled>'
						+ '<input  type="file"  id="ictfImgId1" name="tfincImgansinp" title="Upload incorrect Answer File 1" col="8" onchange="com.coep.test.addProblem.onSelInCorAnsFile(this.id,'+1+','+Id+')" img-media="'+ answers[j].content+'" class="inCorrectAnsBlk form-control hasclear inc-ans imgOption1" >'
						+ '<div id="ictfImgId_error1" class="red-color"></div>'
						+'<div id="opt-preview-div-ictfImgId1" class="opt-preview-div-ictfImgId1"></div>'
						+ ' </div>';

			}

		}

		$('#new-div').html(tfa);
		$('.corr-ans-par-div').html(tf1);
		$('.inc-ans-par-div').html(tf2);
		$("#truefalseImgDiv" + Id).addClass('activeanstype');
		$("#truefalseImgDiv" + Id).show();

		for (var op = 0; op < answers.length; op++) {

			if (answers[op].rightAnswer == true) {
				
				$("#tfImgId1").hide();
				$("#repalceOptBtn-tfImgId1").show();
				AP.previewOptionClick(answers[op].content, "tfImgId1", Id);

			}
			if (answers[op].rightAnswer == false) {
				
				$("#ictfImgId1").hide();
				$("#repalceOptBtn-ictfImgId1").show();
				AP.previewOptionClick(answers[op].content, "ictfImgId1", Id);
			}
		}
		
	}

	
	
	AP.mcaAnswerType = function(answers, Id) {

		var mca1 = '';
		var mca2 = '';
		var mca11 = '';
		var mca22 = '';
		var mca = '<div id="mcaDiv' + Id
				+ '" class="row action-divs display-none activeanstype" >'
				+ '<div class="corr-ans-par-div col-sm-12 col-md-6 col-xl-6 col-lg-6">'
				+ '<div id="mcacorrAnsinpDiv' + Id + '"  >' + '</div>'
				+ '<div id="corr-ans-par-div" class="col-sm-12">' + '</div>'
				+ '</div>'
				+ '<div class="inc-ans-par-div col-sm-12 col-md-6 col-xl-6 col-lg-6">'
				+ '<div id="mcaincAnsinpDiv' + Id + '" class="inc-ans-div">'
				+ '</div>' + '<div id="inc-ans-par-div" class="col-sm-12">'
				+ '</div>' + '</div>' + '</div>';

		mcacorransinpcnt = 0;
		mcaincansinpcnt = 0;

		for (var j = 0; j < answers.length; j++) {

			var aId = (j + 1);

			if (answers[j].rightAnswer == true) {
				mcacorransinpcnt++;
				mca1 += '<div id="mcacorrAns'
						+ mcacorransinpcnt
						+ '" class="col-sm-11 col-md-11 col-lg-11 col-xl-11">'

						+ '<h4 class=\"corrans-label\" style=\"color:#0ec173\">Correct Answer '
						+ mcacorransinpcnt
						+ '</h4>'
						+ '<span onclick="removeRecord(\'mcacorrAnsInp\','
						+ mcacorransinpcnt
						+ ',\'mcacorrAnsinpDiv'
						+ Id
						+ '\')" class="closeAnsOption clearer icon-delete rem-corr-ans-glyph">X</span>'
						+ '<input type="text" id="mcaId'
						+ mcacorransinpcnt
						+ '" value="'
						+ answers[j].content
						+ '" placeholder="Correct Answer '
						+ mcacorransinpcnt
						+ '" name="mcacorransinp" title="Correct Answer '
						+ mcacorransinpcnt
						+ '" class="correctAnsBlk form-control hasclear corr-ans">'
						
						+ '</div>'
						 +'<button id="SCACorrect'+mcacorransinpcnt+'" class="checkLatexAnsAxt2" onclick="typesetInputForCMofifyOptionalMCA1(this,'+ mcacorransinpcnt +')" data-toggle="modal" data-target="#latexDataMCAModifyAns1">{ L }</button>'

			}
			if (answers[j].rightAnswer == false) {
				mcaincansinpcnt++;
				mca2 += '<div id="mcaincAns'
						+ mcaincansinpcnt
						+ '" class="col-sm-11 col-md-11 col-lg-11 col-xl-11">'
						+ '<h4 class="red-color">Incorrect Answer '
						+ mcaincansinpcnt
						+ '</h4>'
						+ '<span onclick="removeRecord(\'mcaincAnsInp\','
						+ mcaincansinpcnt
						+ ',\'mcaincAnsinpDiv'
						+ Id
						+ '\')" class="closeAnsOption clearer icon-delete rem-corr-ans-glyph">X</span>'
						+ '<input type="text" id="micaId'
						+ mcaincansinpcnt
						+ '" value="'
						+ answers[j].content
						+ '" placeholder="Incorrect Answer '
						+ mcaincansinpcnt
						+ '" name="mcaincansinp" title="Incorrect Answer '
						+ mcaincansinpcnt
						+ '" class="inCorrectAnsBlk form-control hasclear inc-ans">'
						
						+ ' </div>'
						 +'<button id="SCAWrong'+mcaincansinpcnt+'" class="checkLatexAnsAxt2" onclick="typesetInputForWMofifyOptionalMCA1(this,'+ mcaincansinpcnt +')" data-toggle="modal" data-target="#latexDataMCAModifyAns1">{ L }</button>'

			}

		}

		mca11 += '<button type="button" onclick="addmcaCorrAnsOpt(\'mcacorrAnsinpDiv'
				+ Id
				+ '\')" id="addCorrAns" title="Add More Correct Options" class="btn btn-success add-more-corrquest">'
				+ '<span class="icon-add corr-ans-glyph"></span>'
				+ 'Add More Correct Options' + '</button>'

		mca22 += '<button type="button" onclick="addmcaIncAnsInpOpt(\'mcaincAnsinpDiv'
				+ Id
				+ '\')" id="addIncAns" title="Add More Incorrect Options" class="btn btn-danger add-more-incquest">'
				+ '<span class="icon-add inc-ans-glyph"></span>Add More Incorrect Options'
				+ '</button>'
				
			//   add que modify model for Singular Correct Answer start
				+ '<div id="latexDataMCAModifyAns1" class="modal fade" role="dialog">' 
				+ '<div class="modal-dialog">'

				+ ' <div class="modal-content">'
				+ '<div class="modal-header">'
				+ ' <span class="modal-title">Latex Equation</span>'
				+ ' <button type="button" class="close" data-dismiss="modal">&times;</button>'
				+ ' </div>'
				
				+ '<div class="modal-body"  style="  width: 500px;  height: auto;  overflow-x: auto;">' //
				+' <div class="scoll-tree">'
				+' <div id="MathPreview" class="" placeholder="Latex equation" ></div><br/>'
//				
				+ '	</div>'
				+ ' </div>'
				+ '	<div class="modal-footer">'
				+ '<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color:red; color:#fff;">Close</button>'
				+ '</div>'
				+ '</div>'
				// add que modify model for Singular Correct Answer solution end  

		$('#new-div').html(mca);
		$('#mcacorrAnsinpDiv' + Id).html(mca1);
		$('#corr-ans-par-div').html(mca11);
		$('#mcaincAnsinpDiv' + Id).html(mca2);
		$('#inc-ans-par-div').html(mca22);
		$("#mcaDiv" + Id).addClass('activeanstype');
		$("#mcaDiv" + Id).show();

	};


	
	/// Image - mca modify
	
	AP.mcaImgAnswerType = function(answers, Id) {

		var mca1 = '';
		var mca2 = '';
		var mca11 = '';
		var mca22 = '';
		var mca = '<div id="mcaImgDiv' + Id
				+ '" class="row action-divs display-none activeanstype" >'
				+ '<div class="corr-ans-par-div col-sm-12 col-md-6 col-xl-6 col-lg-6">'
				+ '<div id="mcaImgcorrAnsinpDiv' + Id + '"  >' + '</div>'
				+ '<div id="corr-ans-par-div" class="col-sm-12">' + '</div>'
				+ '</div>'
				+ '<div class="inc-ans-par-div col-sm-12 col-md-6 col-xl-6 col-lg-6">'
				+ '<div id="mcaImgincAnsinpDiv' + Id + '" class="inc-ans-div">'
				+ '</div>' + '<div id="inc-ans-par-div" class="col-sm-12">'
				+ '</div>' + '</div>' + '</div>';

		mcaimgcorransinpcnt = 0;
		mcaimgincansinpcnt = 0;

		for (var j = 0; j < answers.length; j++) {

			var aId = (j + 1);

			if (answers[j].rightAnswer == true) {  
				mcaimgcorransinpcnt++;
				mca1 += '<div id="mcaImgcorrAns'
						+ mcaimgcorransinpcnt
						+ '" class="col-sm-12">'

						+ '<h4 class=\"corrans-label\" style=\"color:#0ec173\">Correct Answer '
						+ mcaimgcorransinpcnt
						+ '</h4>'
						
						+ "<span onclick=\"removeRecord('mcaImgcorrAnsInp',"
						+ mcaimgcorransinpcnt
						+ ",'mcaImgcorrAnsinpDiv"
						+ Id
						+ "')\" class=\"closeAnsOption clearer icon-delete rem-corr-ans-glyph\" >X</span>"
						
					
						+ '<input type="file" id="mcaImgId'
						+ mcaimgcorransinpcnt
						+ '" value="'
						+ answers[j].content
						+ '" placeholder="Correct Answer '
						+ mcaimgcorransinpcnt
						+ '" name="mcaImgcorransinp" title="Correct Answer '
						+ mcaimgcorransinpcnt
						+ '" onchange="com.coep.test.addProblem.onSelCorAnsFile(this.id,'+mcaimgcorransinpcnt+')" img-media="'+ answers[j].content+'" class="correctAnsBlk form-control hasclear corr-ans">'
						+ '<div id="mcaImgId_error'+mcaimgcorransinpcnt+'" class="red-color"></div>'
						+'<div id="opt-preview-div-mcaImgId'+mcaimgcorransinpcnt+'" class="opt-preview-div-mcaImgId'+mcaimgcorransinpcnt+'"></div>'
						+ '</div>';
			}
			
			if (answers[j].rightAnswer == false) {
				mcaimgincansinpcnt++;
				mca2 += '<div id="mcaImgincAns'
						+ mcaimgincansinpcnt
						+ '" class="col-sm-12">'
						+ '<h4 class="red-color">Incorrect Answer '
						+ mcaimgincansinpcnt
						+ '</h4>'
						+ "<span onclick=\"removeRecord('mcaImgincAnsInp',"
						+ mcaimgincansinpcnt
						+ ",'mcaImgincAnsinpDiv"
						+ Id
						+ "')\" class=\"closeAnsOption clearer icon-delete rem-inc-ans-glyph\" >X</span>"
						
						+ '<input type="file" id="mImgicaId'
						+ mcaimgincansinpcnt
						+ '" value="'
						+ answers[j].content
						+ '" placeholder="Incorrect Answer File '
						+ mcaimgincansinpcnt
						+ '" name="mcaImgincansinp" title="Incorrect Answer File'
						+ mcaimgincansinpcnt
						+ '"  onchange="com.coep.test.addProblem.onSelInCorAnsFile(this.id,'+mcaimgincansinpcnt+')" img-media="'+ answers[j].content+'" class="inCorrectAnsBlk form-control hasclear inc-ans">'
						+ '<div id="mImgicaId_error'+mcaimgincansinpcnt+'" class="red-color"></div>'
						+'<div id="opt-preview-div-mImgicaId'+mcaimgincansinpcnt+'" class="opt-preview-div-mImgicaId'+mcaimgincansinpcnt+'"></div>'
						+ ' </div>';

			}

		}

		mca11 += '<button type="button" onclick="addmcaImgCorrAnsOpt(\'mcaImgcorrAnsinpDiv'
				+ Id
				+ '\')" id="mcaImgaddCorrAns" title="Add More Correct Options" class="btn btn-success add-more-corrquest">'
				+ '<span class="icon-add corr-ans-glyph"></span>'
				+ 'Add More Correct Options' + '</button>';

		mca22 += '<button type="button" onclick="addmcaImgIncAnsInpOpt(\'mcaImgincAnsinpDiv'
				+ Id
				+ '\')" id="mcaImgaddIncAns" title="Add More Incorrect Options" class="btn btn-danger add-more-incquest">'
				+ '<span class="icon-add inc-ans-glyph"></span>Add More Incorrect Options'
				+ '</button>';

		$('#new-div').html(mca);
		$('#mcaImgcorrAnsinpDiv' + Id).html(mca1);
		$('#corr-ans-par-div').html(mca11);
		$('#mcaImgincAnsinpDiv' + Id).html(mca2);
		$('#inc-ans-par-div').html(mca22);
		$("#mcaImgDiv" + Id).addClass('activeanstype');
		$("#mcaImgDiv" + Id).show();
		
		
		mcaimgcorransinpcnt = 0;
		mcaimgincansinpcnt = 0;
		
		for (var op = 0; op < answers.length; op++) {

			if (answers[op].rightAnswer == true) {
				mcaimgcorransinpcnt++;
				$("#mcaImgId"+mcaimgcorransinpcnt).hide();
				$("#repalceOptBtn-mcaImgId"+mcaimgcorransinpcnt).show();
				AP.previewOptionClick(answers[op].content, "mcaImgId"+mcaimgcorransinpcnt, Id);

			}
			if (answers[op].rightAnswer == false) {
				mcaimgincansinpcnt++;
				$("#mImgicaId"+mcaimgincansinpcnt).hide();
				$("#repalceOptBtn-mImgicaId"+mcaimgincansinpcnt).show();
				AP.previewOptionClick(answers[op].content, "mImgicaId"+mcaimgincansinpcnt, Id);
			}
		}
		

	};
	
	
	
	
	

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




})(com.coep.test.ajaxHandler, com.coep.test.addProblem);