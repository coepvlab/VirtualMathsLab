(function(OT, OTM, AM) {
	
	
		OT.renderOptions = function(j, k, topicId, ansType, qTxtTemp, testData) {
		
		if(ansType == 5){ //Multiple Correct Answers 

			OT.mediaTypeQuestion(j, k, testData, topicId);

			var mcaOptions = "";
			
			for (var l = 0; l < testData.data["TOPIC"+topicId][j].QUES[k].ANS.length; l++) {
				var index = [];
					
					if(testData.data["TOPIC"+topicId][j].QUES[k].GANSID == undefined ){
						
						mcaOptions += "<div class=\"col-sm-12 checkbox textOpt \"><label>"
							+ "<input class=\"checkboxType\" type=\"checkbox\" id=\""
							+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].ANSID
							+ "\" name=\""
							+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].TI_ID
							+ "\" value=\""
							+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].content
							+ "\">" + "<span class=\"checkbox-material \"><span class='check'></span></span>"
							+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].content
							+ "</label></div>"
					}else{
						
						mcaOptions += "<div class=\"col-sm-12  checkbox textOpt \"><label>"
							+ "<input class=\"checkboxType\" type=\"checkbox\" id=\""
							+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].ANSID
							+ "\" name=\""
							+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].TI_ID
							+ "\" value=\""
							+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].content
							+ "\">" + "<span class=\"checkbox-material \"><span class='check'></span></span>"
							+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].content
							+ "</label></div>"
					}
			}
			
//			$("#"+qTxtTemp).removeClass('quesPanelnotchecked')
//			.addClass('quesPanelchecked');
			
				$("#options").html(mcaOptions);
				//TODO
				
				if(testData.data["TOPIC"+topicId][j].QUES[k].GANSID != undefined){
					if(testData.data["TOPIC"+topicId][j].QUES[k].ANSTYP == 5 ){ 
						for (var p = 0; p < testData.data["TOPIC"+topicId][j].QUES[k].ANS.length; p++) {
							
							var index = $("#"+testData.data["TOPIC"+topicId][j].QUES[k].ANS[p].ANSID+"").attr('id');
							
							for (var q = 0; q < testData.data["TOPIC"+topicId][j].QUES[k].ANS.length; q++) {
								
								if(index == testData.data["TOPIC"+topicId][j].QUES[k].GANSID[0]["GANSID"+(q+1)]){
									$("#"+index).attr('checked','checked');
									$("#"+index).attr('GANSID',testData.data["TOPIC"+topicId][j].QUES[k].GANSID[0]["GANSID"+(q+1)]+"-"+(q+1));
								}
							}
						}
					}
				}
				

			if (testData.data["TOPIC"+topicId][j].QUES[k].selId != undefined) {
				for (var m = 0; m < testData.data["TOPIC"+topicId][j].QUES[k].selId.length; m++) {
					$("#" + testData.data["TOPIC"+topicId][j].QUES[k].selId[m])
							.prop('checked', true);
				}
			}

				
		}else if (ansType == 4) { // true or false
			OT.mediaTypeQuestion(j, k, testData, topicId);
			
			var truefalseOptions = "";
			
			if(testData.data["TOPIC"+topicId][j].QUES[k].GANSID == undefined || (testData.data["TOPIC"+topicId][j].QUES[k].GANSID != testData.data["TOPIC"+topicId][j].QUES[k].ANS[0].ANSID && testData.data["TOPIC"+topicId][j].QUES[k].GANSID != testData.data["TOPIC"+topicId][j].QUES[k].ANS[1].ANSID )){
			
			truefalseOptions += "<div class='col-sm-12 radio radio-primary textOpt'><label class='cust1-label '>"
					+ "<input  class=\"radioType\" type=\"radio\" id=\""
					+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[0].ANSID
					+ "\" name=\"option1\" value=\""
					+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[0].content
					+ "\">"
					+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[0].content
					+ ""
					+ "<span class='circle'></span><span class='check '></span></label></div>"
					
					+ "<div class='col-sm-12 radio radio-primary textOpt'><label class='cust1-label '>"
					+ "<input  class=\"radioType\" type=\"radio\" id=\""
					+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[1].ANSID
					+ "\" name=\"option1\" value=\""
					+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[1].content
					+ "\">"
					+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[1].content
					+ "" + "<span class='circle'></span><span class='check '></span></label></div>";
			
			}else{
				
//				$("#" + qTxtTemp).removeClass('red-color')
//				.addClass('green-color').text(
//						'Answered');
				
//				$("#"+qTxtTemp).html('<i id="qgCheck'+(j+1)+'_'+(k+1)+'"  class="fa fa-check green-color" aria-hidden="true"></i>');
				
//				$("#"+qTxtTemp).removeClass('fa fa-times-circle red-color')
//				.addClass('fa fa-check green-color').text(
//						'Answered');
				$("#"+qTxtTemp).removeClass('quesPanelnotchecked')
				.addClass('quesPanelchecked');
				
				truefalseOptions += "<div class='col-sm-12 radio radio-primary textOpt'><label class='cust1-label '>"
					
					if(testData.data["TOPIC"+topicId][j].QUES[k].GANSID == testData.data["TOPIC"+topicId][j].QUES[k].ANS[0].ANSID){
						
						truefalseOptions += "<input  class=\"radioType\" type=\"radio\" id=\""
						+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[0].ANSID
						+ "\" name=\"option1\" value=\""
						+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[0].content
						+ "\" checked>"
						+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[0].content
						+ ""
						+ "<span class='circle'></span><span class='check '></span></label></div>"
						
						+ "<div class='col-sm-12 radio radio-primary textOpt'><label class='cust1-label '>"
						+ "<input  class=\"radioType\" type=\"radio\" id=\""
						+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[1].ANSID
						+ "\" name=\"option1\" value=\""
						+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[1].content
						+ "\">"
						+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[1].content
						+ "" + "<span class='circle'></span><span class='check '></span></label></div>"
						
					}else if(testData.data["TOPIC"+topicId][j].QUES[k].GANSID == testData.data["TOPIC"+topicId][j].QUES[k].ANS[1].ANSID){
						
						truefalseOptions += "<input  class=\"radioType\" type=\"radio\" id=\""
						+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[0].ANSID
						+ "\" name=\"option1\" value=\""
						+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[0].content
						+ "\" checked>"
						+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[0].content
						+ ""
						+ "<span class='circle'></span><span class='check '></span></label></div>"
						
						+ "<div class='col-sm-12 radio radio-primary textOpt'><label class='cust1-label '>"
						+ "<input  class=\"radioType\" type=\"radio\" id=\""
						+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[1].ANSID
						+ "\" name=\"option1\" value=\""
						+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[1].content
						+ "\" checked>"
						+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[1].content
						+ "" + "<span class='circle'></span><span class='check '></span></label></div>"
						
					}
						
			}
			
//			truefalseOptions += '<button id="reset" class="btn btn-dark" ><i class="fa fa-undo" aria-hidden="true"></i><b class="hideFormobile">Clear Answer</b></button>'
				
			$("#options").html(truefalseOptions);
			
//			$('#reset').on('click', function() {
//				
//				clearAnswerForRadioButton();
//				
//			});	
			

			if (testData.data["TOPIC"+topicId][j].QUES[k].selId != undefined) {
				for (var m = 0; m < testData.data["TOPIC"+topicId][j].QUES[k].selId.length; m++) {
					$("#" + testData.data["TOPIC"+topicId][j].QUES[k].selId[m])
							.prop('checked', true);
				}
			}
			
		} else if (ansType == 2) { // Fill In The Blanks


			OT.mediaTypeQuestion(j, k, testData, topicId);

			var fibOptions = "";
			for (var l = 0; l < testData.data["TOPIC"+topicId][j].QUES[k].ANS.length; l++) {
				
				if(testData.data["TOPIC"+topicId][j].QUES[k].GANSID == undefined || testData.data["TOPIC"+topicId][j].QUES[k].GANSID != testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].ANSID){
					
					fibOptions += "<div class='col-sm-12 radio radio-primary textOpt'><label class='cust1-label '>"
						+ "<input class=\"radioType\" type=\"radio\" id=\""
						+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].ANSID
						+ "\" name=\"option1"
						+ "\" value=\""
						+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].content
						+ "\">"
						+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].content
						+ "" + "<span class='circle'></span><span class='check '></span></label></div>"
				}else{
					
//					$("#" + qTxtTemp).removeClass('red-color')
//					.addClass('green-color').text(
//							'Answered');
					
//					$("#"+qTxtTemp).html('<i id="qgCheck'+(j+1)+'_'+(k+1)+'"  class="fa fa-check green-color" aria-hidden="true"></i>');
					
//					$("#"+qTxtTemp).removeClass('fa fa-times-circle red-color')
//					.addClass('fa fa-check green-color').text(
//							'Answered');
					$("#"+qTxtTemp).removeClass('quesPanelnotchecked')
					.addClass('quesPanelchecked');
					
					fibOptions += "<div class='col-sm-12 radio radio-primary textOpt'><label class='cust1-label '>"
						+ "<input class=\"radioType\" type=\"radio\" id=\""
						+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].ANSID
						+ "\" name=\"option1"
						+ "\" value=\""
						+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].content
						+ "\" checked>"
						+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].content
						+ "" + "<span class='circle'></span><span class='check '></span></label></div>"
				}
				
			}
			
			
//			fibOptions += '<button id="reset" class="btn btn-dark" ><i class="fa fa-undo" aria-hidden="true"></i> <b class="hideFormobile">Clear Answer</b></button>'
				
				
			$("#options").html(fibOptions);
			
//				$('#reset').on('click', function() {
//					
//					clearAnswerForRadioButton();
//					
//				});	
					
					
					
			if (testData.data["TOPIC"+topicId][j].QUES[k].selId != undefined) {
				for (var m = 0; m < testData.data["TOPIC"+topicId][j].QUES[k].selId.length; m++) {
					$("#" + testData.data["TOPIC"+topicId][j].QUES[k].selId[m])
							.prop('checked', true);
				}
			}

			
		} else if (ansType == 3) { // Match the Pairs

			OT.mediaTypeQuestion(j, k, testData, topicId);

			var mtpOptions = "";
			for (var l = 0; l < testData.data["TOPIC"+topicId][j].QUES[k].ANS.length; l++) {
				
				if(testData.data["TOPIC"+topicId][j].QUES[k].GANSID == undefined || testData.data["TOPIC"+topicId][j].QUES[k].GANSID != testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].ANSID){
					
					mtpOptions += "<div class='col-sm-12 radio radio-primary textOpt'><label class='cust1-label '>"
						+ "<input class=\"radioType\" type=\"radio\" id=\""
						+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].ANSID
						+ "\" name=\"option1"
						+ "\" value=\""
						+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].content
						+ "\">"
						+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].content
						+ "" + "<span class='circle'></span><span class='check '></span></label></div>"
						
				}else{
					
//					$("#"+qTxtTemp).removeClass('fa fa-times-circle red-color')
//					.addClass('fa fa-check green-color').text(
//							'Answered');
					$("#"+qTxtTemp).removeClass('quesPanelnotchecked')
					.addClass('quesPanelchecked');
					
//					$("#"+qTxtTemp).html('<i id="qgCheck'+(j+1)+'_'+(k+1)+'"  class="fa fa-check green-color" aria-hidden="true"></i>');
					
					
					mtpOptions += "<div class='col-sm-12 radio radio-primary textOpt'><label class='cust1-label '>"
						+ "<input class=\"radioType\" type=\"radio\" id=\""
						+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].ANSID
						+ "\" name=\"option1"
						+ "\" value=\""
						+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].content
						+ "\"checked>"
						+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].content
						+ "" + "<span class='circle'></span><span class='check '></span></label></div>"
				}

			}
			
//			mtpOptions += '<button id="reset" class="btn btn-dark" ><i class="fa fa-undo" aria-hidden="true"></i> <b class="hideFormobile">Clear Answer</b></button>'
			
			$("#options").html(mtpOptions);
			
//			$('#reset').on('click', function() {
//				
//				clearAnswerForRadioButton();
//				
//			});					
				
			if (testData.data["TOPIC"+topicId][j].QUES[k].selId != undefined) {
				for (var m = 0; m < testData.data["TOPIC"+topicId][j].QUES[k].selId.length; m++) {
					$("#" + testData.data["TOPIC"+topicId][j].QUES[k].selId[m])
							.prop('checked', true);
				}
			}
		} else if (ansType == 1) { // Singular Correct Answer
			
			OT.mediaTypeQuestion(j, k, testData, topicId);

			var scaOptions = "";
			for (var l = 0; l < testData.data["TOPIC"+topicId][j].QUES[k].ANS.length; l++) {

					
					if(testData.data["TOPIC"+topicId][j].QUES[k].GANSID == undefined || testData.data["TOPIC"+topicId][j].QUES[k].GANSID != testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].ANSID){
						scaOptions += "<div class='col-sm-12 radio radio-primary textOpt'><label class='cust1-label '>"
							+ "<input class=\"radioType\" type=\"radio\" id=\""
							+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].ANSID
							+ "\" name=\"option1"
							+ "\" value=\""
							+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].content
							+ "\" >"
							+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].content
							+ ""
							+ "<span class='circle'></span><span class='check '></span></label></div>"
					
					}else {
						
//						$("#" + qTxtTemp).removeClass('red-color')
//						.addClass('green-color').text(
//								'Answered');
//						$("#"+qTxtTemp).html('<i id="qgCheck'+(j+1)+'_'+(k+1)+'"  class="fa fa-check green-color" aria-hidden="true"></i>');
						
//						$("#"+qTxtTemp).removeClass('fa fa-times-circle red-color')
//						.addClass('fa fa-check green-color').text(
//								'Answered');
						
						$("#"+qTxtTemp).removeClass('quesPanelnotchecked')
						.addClass('quesPanelchecked');
						
						scaOptions += "<div class='col-sm-12 radio radio-primary textOpt'><label class='cust1-label '>"
							+ "<input class=\"radioType\" type=\"radio\" id=\""
							+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].ANSID
							+ "\" name=\"option1"
							+ "\" value=\""
							+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].content
							+ "\" checked>"
							+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].content
							+ ""
							+ "<span class='circle'></span><span class='check '></span></label></div>"
					}
					
			}
			
//			scaOptions += '<button id="reset" class="btn btn-dark" ><i class="fa fa-undo" aria-hidden="true"></i> <b class="hideFormobile">Clear Answer</b></button>'
			
			$("#options").html(scaOptions);
			
//			$('#reset').on('click', function() {
//				
//				clearAnswerForRadioButton();
//				
//			});
			
			
			
			if (testData.data["TOPIC"+topicId][j].QUES[k].selId != undefined) {
				for (var m = 0; m < testData.data["TOPIC"+topicId][j].QUES[k].selId.length; m++) {
					$("#" + testData.data["TOPIC"+topicId][j].QUES[k].selId[m])
							.prop('checked', true);
				}
			}
			
		
		}else if (ansType == 6) { // Image - Singular Correct Answer
			
			OT.mediaTypeQuestion(j, k, testData, topicId);

			var imgScaOptions = "";
			for (var l = 0; l < testData.data["TOPIC"+topicId][j].QUES[k].ANS.length; l++) {

					
					if(testData.data["TOPIC"+topicId][j].QUES[k].GANSID == undefined || testData.data["TOPIC"+topicId][j].QUES[k].GANSID != testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].ANSID){
						imgScaOptions += "<div class='col-sm-12 radio radio-primary textOpt'><label class='cust1-label '>"
//							+ "<input class=\"radioType\" type=\"radio\" id=\""
//							+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].ANSID
//							+ "\" name=\"option1"
//							+ "\" value=\""
//							+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].content
//							+ "\" >"
//							+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].content
//							+ ""
							 +" <input  id=\"" 
							 + 	testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].ANSID
							 + "\" type=\"radio\" name=\"option1"  + "\"  class=\"radioType\" />"
							 +"<label  for='" 
							 + testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].ANSID
							 +"'>"
							 +"<img src='"+OT.baseURL+"media/get/getImage?mediaID="+testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].content+"'  />"
//							 +"<img src=\""+OT.baseURL+"\"media/get/getImage?mediaID="+testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].content+" class=\"\" />"
				    		 +"</label>"
				    			 
							
							+ "<span class='circle'></span><span class='check '></span></label></div>"
					
					}else {
						
						
						$("#"+qTxtTemp).removeClass('quesPanelnotchecked')
						.addClass('quesPanelchecked');
						
						imgScaOptions += "<div class='col-sm-12 radio radio-primary textOpt'><label class='cust1-label '>"
//							+ "<input class=\"radioType\" type=\"radio\" id=\""
//							+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].ANSID
//							+ "\" name=\"option1"
//							+ "\" value=\""
//							+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].content
//							+ "\" checked>"
//							+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].content
//							+ ""
							
							 +" <input  id=\"" 
							 + 	testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].ANSID
							 + "\" type=\"radio\" name=\"option1"  + "\"  class=\"radioType\" />"
							 +"<label  for='" 
							 + testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].ANSID
							 +"'>"
							
							// +' <img src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QUESTION[j].answers[k].content+'" class="ApproveQueImgOption" style="border: 6px solid green;" />'
							 +"<img src='"+OT.baseURL+"media/get/getImage?mediaID="+testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].content+"'  />"
//							 +"<img src=\""+OT.baseURL+"\"media/get/getImage?mediaID="+testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].content+" class=\"\" />"
				    		 +"</label>"
							+ "<span class='circle'></span><span class='check '></span></label></div>"
					}
					
			}
			
//			imgScaOptions += '<button id="reset" class="btn btn-dark" ><i class="fa fa-undo" aria-hidden="true"></i> <b class="hideFormobile">Clear Answer</b></button>'
			
			$("#options").html(imgScaOptions);
			
//			$('#reset').on('click', function() {
//				
//				clearAnswerForRadioButton();
//				
//			});
			
			
			
			if (testData.data["TOPIC"+topicId][j].QUES[k].selId != undefined) {
				for (var m = 0; m < testData.data["TOPIC"+topicId][j].QUES[k].selId.length; m++) {
					$("#" + testData.data["TOPIC"+topicId][j].QUES[k].selId[m])
							.prop('checked', true);
				}
			}
			
		
		}else if (ansType == 7) { // Image - Match The Pair
			
			OT.mediaTypeQuestion(j, k, testData, topicId);

			var imgMtpOptions = "";
			for (var l = 0; l < testData.data["TOPIC"+topicId][j].QUES[k].ANS.length; l++) {

					
					if(testData.data["TOPIC"+topicId][j].QUES[k].GANSID == undefined || testData.data["TOPIC"+topicId][j].QUES[k].GANSID != testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].ANSID){
						imgMtpOptions += "<div class='col-sm-12 radio radio-primary textOpt'><label class='cust1-label '>"
							
							 +" <input  id=\"" 
							 + 	testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].ANSID
							 + "\" type=\"radio\" name=\"option1"  + "\"  class=\"radioType\" />"
							 +"<label  for='" 
							 + testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].ANSID
							 +"'>"
							 +"<img src='"+OT.baseURL+"media/get/getImage?mediaID="+testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].content+"'  />"
				    		 +"</label>"
				    			 
							
							+ "<span class='circle'></span><span class='check '></span></label></div>"
					
					}else {
						
						
						$("#"+qTxtTemp).removeClass('quesPanelnotchecked')
						.addClass('quesPanelchecked');
						
						imgMtpOptions += "<div class='col-sm-12 radio radio-primary textOpt'><label class='cust1-label '>"
							
							 +" <input  id=\"" 
							 + 	testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].ANSID
							 + "\" type=\"radio\" name=\"option1"  + "\"  class=\"radioType\" />"
							 +"<label  for='" 
							 + testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].ANSID
							 +"'>"
							 +"<img src='"+OT.baseURL+"media/get/getImage?mediaID="+testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].content+"'  />"
				    		 +"</label>"
							+ "<span class='circle'></span><span class='check '></span></label></div>"
					}
					
			}
			
//			imgMtpOptions += '<button id="reset" class="btn btn-dark" ><i class="fa fa-undo" aria-hidden="true"></i> <b class="hideFormobile">Clear Answer</b></button>'
			
			$("#options").html(imgMtpOptions);
			
//			$('#reset').on('click', function() {
//				
//				clearAnswerForRadioButton();
//				
//			});
			
			
			
			if (testData.data["TOPIC"+topicId][j].QUES[k].selId != undefined) {
				for (var m = 0; m < testData.data["TOPIC"+topicId][j].QUES[k].selId.length; m++) {
					$("#" + testData.data["TOPIC"+topicId][j].QUES[k].selId[m])
							.prop('checked', true);
				}
			}
			
		
		}else if (ansType == 8) { // Image - Multiple Correct Answers 

			OT.mediaTypeQuestion(j, k, testData, topicId);

			var imgMcaOptions = "";
			
			for (var l = 0; l < testData.data["TOPIC"+topicId][j].QUES[k].ANS.length; l++) {
				var index = [];
					
					if(testData.data["TOPIC"+topicId][j].QUES[k].GANSID == undefined ){
						
						imgMcaOptions += "<div class=\"col-sm-12 checkbox  \"><label>"
							
							 +" <input  class=\"checkboxType\" id=\"" 
							 + 	testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].ANSID
							 + "\" type=\"checkbox\"  name=\"" + 
							 + testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].TI_ID
							 + "\" />"
							 +"<label  for='" 
							 + testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].ANSID
							 +"'>"
							 +"<img src='"+OT.baseURL+"media/get/getImage?mediaID="+testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].content+"'  />"
				    		 +"</label>"
							
							+ "<span class=\"checkbox-material \"><span class='check'></span></span>"
//							+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].content
							+ "</label></div>"
					}else{
						
						imgMcaOptions += "<div class=\"col-sm-12  checkbox  \"><label>"
							
							+" <input class=\"checkboxType\"  id=\"" 
							+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].ANSID
							+ "\" type=\"checkbox\"  name=\"" + 
							+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].TI_ID
							+"\"  />"
							+"<label  for='" 
							+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].ANSID
							+"'>"
							+"<img src='"+OT.baseURL+"media/get/getImage?mediaID="+testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].content+"'  />"
				    		+"</label>"
				    		 
							+ "<span class=\"checkbox-material \"><span class='check'></span></span>"
//							+ testData.data["TOPIC"+topicId][j].QUES[k].ANS[l].content
							+ "</label></div>"
					}
			}
			
			
				$("#options").html(imgMcaOptions);
				//TODO
				
				if(testData.data["TOPIC"+topicId][j].QUES[k].GANSID != undefined){
					if(testData.data["TOPIC"+topicId][j].QUES[k].ANSTYP == 8 ){ 
						for (var p = 0; p < testData.data["TOPIC"+topicId][j].QUES[k].ANS.length; p++) {
							
							var index = $("#"+testData.data["TOPIC"+topicId][j].QUES[k].ANS[p].ANSID+"").attr('id');
							
							for (var q = 0; q < testData.data["TOPIC"+topicId][j].QUES[k].ANS.length; q++) {
								
								if(index == testData.data["TOPIC"+topicId][j].QUES[k].GANSID[0]["GANSID"+(q+1)]){
									$("#"+index).attr('checked','checked');
									$("#"+index).attr('GANSID',testData.data["TOPIC"+topicId][j].QUES[k].GANSID[0]["GANSID"+(q+1)]+"-"+(q+1));
								}
							}
						}
				}
				}
				

			if (testData.data["TOPIC"+topicId][j].QUES[k].selId != undefined) {
				for (var m = 0; m < testData.data["TOPIC"+topicId][j].QUES[k].selId.length; m++) {
					$("#" + testData.data["TOPIC"+topicId][j].QUES[k].selId[m])
							.prop('checked', true);
				}
			}

				
		}

		
	
	clearAnswerForRadioButton = function() {

			$('.radioType').removeAttr('checked');
			$('.radioType').prop('checked', false);
			
				
			// answer is checked
				$("#"+qTxtTemp).removeClass('quesPanelchecked')
				.addClass('quesPanelnotchecked');
				
				selId = [];
				testData.data["TOPIC"+topicId][j].QUES[k].selId = 99999999;
//				testData.data["TOPIC"+topicId][j].QUES[k].GANSID = 99999999;
				var testInstanceVO = {};
				testInstanceVO = {
					"testInsId" : testData.data["TOPIC"+topicId][j].QUES[k].TI_ID,
					"quesId" : testData.data["TOPIC"+topicId][j].QUES[k].QID,
					"qGId" : testData.data["TOPIC"+topicId][j].QGID,
					"ansId" : 99999999,
					"actAnsStTime" : Date.now(),
					"actAnsEndTime" : Date.now()
				}
			

				OT.testInstanceVOArr.push(testInstanceVO);
			
		
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


})(com.coep.test.otherTest, com.coep.test.otherTestTimer, com.coep.test.AlertMessage);