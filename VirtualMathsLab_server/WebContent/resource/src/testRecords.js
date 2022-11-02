(function(AH, TR, AM, AP) {


 TR.getAllTestRecords = function() {
  
	 $("#main-div").html("");	
	 
	  $.ajax({
		    type : "GET",
		    url :  com.coep.test.addProblem.baseURL+"testInstanceStatistics/getAlltestStatisticsRecords",
		    dataType : 'json',
		    contentType : 'application/json',
			    success : function(data) {

				if (data.done != false) {
					console.log(data.data);
					TR.renderAllTestRecords(data.data);
				} else {
					showToast.show(data.msg, false);
				}
			},
		    error : function() {
		    	showToast.show("Something went wrong..", false);
		    }

		   });
	 
 }


 TR.renderAllTestRecords = function(data) {
//	 console.log(data);
	 
	 var recordHtm = '';
	 recordHtm += '<div class="overlay" id="Loading">'
	 + '<div class="overlay__inner">'
	 + '<div class="overlay__content"><span class="spinner"></span><br/><span class="loading">LOADING....</span></div>'
	 + '</div>'
	 + '</div>'
		 +'<hr>'
		 
	 recordHtm += '<h1>TEST  DETAILS</h1>'
		 
			+ '<div class="table-responsive">'
			+ '<table id="TestData" class="table table-striped table-bordered" style="width:100%">'
			+ '<thead>' + ' <tr>'
//			+ '<th width="5%" > </th>'
			+ '<th width="5%" >Sr. No. </th>'
			+ '<th width="5%">Topic Name</th>'
			+ '<th width="30%">Test Name </th>'
			+ '<th width="5%">Correct Answers</th>'
			+ '<th width="15%">Total Questions</th>'
//			+ '<th width="20%">Topics</th>'
//			+ '<th width="5%" title ="Variation No">Var No</th>'
			+ '<th width="5%">Click here for Question Paper</th>' 
			+ '</tr>'
			+ '</thead>'
			+ '<tbody>'
			
	for (var i = 0; i < data.length; i++) {
		
			recordHtm += '<tr  id="'+i+'" >' 
			recordHtm += '<td width="5%">' + (i + 1)
			recordHtm += '</td>'
			+ '<td width="5%">' + data[i].TOPICNM
			+ '</td>'
			+ '<td width="30%" id="MathPreview" >' + data[i].TESTNM
			+ '</td>'
			+ '<td width="10%">' + data[i].PASSNOQUES
			+ '</td>' 
			+ '<td width="15%">' + data[i].TOTALNOQUES
			+ '</td>' 
			
			+'<td width="5%">'
//			+'<a href="#">Click here for question paper' + data[i].TISID + '</a></td>' 
			+ '<span class="resultBtn btn btn-success" onClick="javascript:com.coep.test.AlertMessage.getQuestionPaperOnClick('+i+','+data[i].TISID+')" data-toggle="modal" data-target="#AlertMesConfirm2-'+i+'-'+data[i].TISID+'" title="Click here for Question Paper" class="btn btn-success AcT_ArchIcon" >'+ "Question Paper" + '</span>'
			+'</td>' 
			+ '</tr>'
		}
	 recordHtm += '</tbody>'+'</table>' + '</div>'

	 $("#main-div").html(recordHtm);
	 
		$("#main-div").ready(function() {
			var data_table = $('#TestData').DataTable({
				"pageLength" : 100,
				// lengthChange: false,
				buttons : [ 'copy', 'excel', 'pdf' ],
//				 "order": [[ 3, "asc" ]],
			     drawCallback: function(){
			          $('.paginate_button.page-item:not(.disabled)', this.api().table().container())          
			             .on('click', function(){
			            	 com.coep.test.mathJax.renderMathJax();
			             });       
			       },
//			       "columnDefs": [ {
//			    	      "targets"  : 'no-sort',
//			    	      "orderable": false,
//			    	      "order": []
//			    	    }],
//			    "ordering": false,
		       "initComplete": function() {
//		    	   com.coep.test.mathJax.renderMathJax();
		       }
			}).on('search.dt', function() {
//				 com.coep.test.mathJax.renderMathJax();
			});
			
			
			setTimeout(function(){ $("#Loading").css("display","none"); }, 2000);
		}) //main end
		
		
		  AM.getQuestionPaperOnClick = function(i,TISID) {
			
			var allData = data;
			var tisId = TISID;
			
			  $.ajax({
				    type : "GET",
				    url : com.coep.test.addProblem.baseURL
					+ "testInstanceCompletion/api/get/quetionPaper/"+tisId,
			// data : JSON.stringify(L1Json),
					dataType : 'json',
				    contentType : 'application/json',
					    success : function(data) {

						if (data.done != false) {
							console.log(data);
							setTimeout(function(){ AM.showQuestionPaper(i,TISID, data.data, allData); }, 100);	
						} else {
							showToast.show(data.msg, false);
						}
					},
				    error : function() {
				    	showToast.show("Something went wrong..", false);
				    }

				   });
		} 
		
		
		AM.showQuestionPaper = function(i,TISID,  data, allData) {

				var AlertMesConfirm = ''
					AlertMesConfirm +=  '<div class="container-fluid">'
					+ '<div class="row">'
					+ '<div  class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'
					//Alert modal start
							+ '<div class="modal SolutionModel" id="AlertMesConfirm2-'+i+'-'+TISID+'">'
							+ '<div class="modal-dialog " style="max-width: 75%;">'
							+ '<div class="modal-content">'
				            
				            	 +' <div class="modal-header bg-info" style="color:#fff;">'
									+ ' <h2 >Question Paper </h2>'
									+ '  <button type="button" class="close" data-dismiss="modal">&times;</button>'
									+ '  </div>'
					
									+ '   <div class="modal-body">'
									// body
									+'<hr>'
									
//									 AlertMesConfirm += '<h1>NON-APPROVED QUESTION DETAILS</h1>'
										 AlertMesConfirm += '<div class="table-responsive" >'
											+ '<center><div id="showSelQues" style="display: none;  color: #007bff"></div></center>' // showSelQues
											+ '<table id="nonApprovedQuestionData" class="table table-striped table-bordered" style="width:100%">'
											+ '<thead>' + ' <tr>'
											+ '<th  width="5%"  title="Result"  style="cursor: pointer;">'
//											+'<input type="checkbox"  id="checkAllNonApproved" checkId="" name="checkMultiple" class="checkboxType"  style="cursor: pointer;" /> <label for="checkAllNonApproved"  style="cursor: pointer; color: white;"  >Select all Questions.</label>'
											+' <label for="result"  style="cursor: pointer; color: white;"  >Result</label>'
											+ ' </th>'
//											+ '<th width="5%" >Sr. No. </th>'
											+ '     <th width="90%">Question </th>'
											+ '   </tr>'
											+ '</thead>'
											+ ' <tbody>'

									for (var i = 0; i < data.length; i++) {
//										var topicName = "";
//										for (var q = 0; q < data[i].QGTOPICS.length; q++) {
//											if(q == data[i].QGTOPICS.length - 1){
//												topicName += data[i].QGTOPICS[q].TNO+" - "+data[i].QGTOPICS[q].TNM + " ("+data[i].QGTOPICS[q].TNM1 +")";
//											}else{
//												topicName += data[i].QGTOPICS[q].TNO+" - "+data[i].QGTOPICS[q].TNM + " ("+data[i].QGTOPICS[q].TNM1 +") "+",<br>";
//											}
//										}
										
											AlertMesConfirm += '<tr  >'
												
											AlertMesConfirm += '<td width="5%" id="check-'+i+'">' 
										
											if (data[i].RESULT == true) {
												AlertMesConfirm += '<i class="fa fa-check" aria-hidden="true" style="color: green;"></i>'
											}else{
												AlertMesConfirm += '<i class="fa fa-times" aria-hidden="true" style="color: red;"></i>'
											}
											
																
											AlertMesConfirm += '</td>'

											+ '<td width="90%">'
											+'<div class="row ApproveQueStyle" id="Q'+(i + 1)+'">' // ApproveQue main div start
											
//											AlertMesConfirm += '<div class="col-xl-12 col-md-12 col-sm-12 justify-content-center" ><p class="ApproveDivLabel">Contributor - '+data[i].CBY+'</p></div>'
											
//											AlertMesConfirm += '<div class="col-xl-3 col-md-3 col-sm-12 ApproveDivStyle" >'
//												 +'<span><p class="ApproveDivLabel">Question Type </p>'+data[i].QGTYP+'</span>'
//												 +'</div>' 
//												
//												 +'<div class="col-xl-6 col-md-6 col-sm-12 ApproveDivStyle">'
//												 +'<span><p class="ApproveDivLabel">Assign Topics </p class="ApproveDivLabel">'+topicName+'</span>'
//												 +'</div>'
//												 +'<div class="col-xl-3 col-md-3 col-sm-12 ApproveDivStyle">'
//												+'<p class="ApproveDivLabel">Difficulty level  </p>'
//												+'Level - ' +data[i].QGLVL
//												+'</div>'
												 
											 // Reference start
											 
											 if($.trim(data[i].QGTYP) == "Text" || $.trim(data[i].QGTYP) == "text"){
													
													if($.trim(data[i].QGMEDIA) == ""){
														AlertMesConfirm += ''
													}else{
														AlertMesConfirm += '<div class="col-xl-12 col-md-12 col-sm-12" ><p class="ApproveDivLabel">Reference </p>'+data[i].QGMEDIA+'</div>'
													}
													
													
												}else if($.trim(data[i].QGTYP) == "Image" || $.trim(data[i].QGTYP) == "image"){
													
													AlertMesConfirm += '<div class="col-xl-12 col-md-12 col-sm-12">'
														 +'<p class="ApproveDivLabel">'
														 +' With reference to the follwing Image answer the following question'
														 +' </p>'
														+'<div> <img src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QGMEDIA+'" class="ApproveQueImg" /></div></div><hr/>'
													
												}else if($.trim(data[i].QGTYP) == "Audio" || $.trim(data[i].QGTYP) == "audio"){
													
													AlertMesConfirm += '<div class="col-xl-12 col-md-12 col-sm-12">'
														 +'<p class="ApproveDivLabel">'
														 +' With reference to the follwing clip answer the following question'
														 +' </p>'
														 +' <audio controls class="ApproveQueAudio">'
														 +'  <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QGMEDIA+'" type="audio/mpeg" >'
														 +' Your browser does not support the audio element.'
														 +' </audio>'
														+'</div><hr/>'
														
												}else if($.trim(data[i].QGTYP) == "Video" || $.trim(data[i].QGTYP) == "video"){
													AlertMesConfirm +='<div class="col-xl-12 col-md-12 col-sm-12">'
														+ '<p class="ApproveDivLabel">'
														+ ' With reference to the follwing clip answer the following question'
														+ ' </p>'
														+'  <video controls class="ApproveQueVideo">'
														+ '            <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QGMEDIA+'" type="video/mp4" >'
														+ ' <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QGMEDIA+'" type="video/ogg">'
														+ '            Your browser does not support the video tag.'
														+ '         </video>' 
														+'</div><hr/>'
												}
											 // Reference end
											
											for (var j = 0; j < data[i].QUESTION.length; j++) {
												
														
												
												// actual Que start
												AlertMesConfirm +='<div id="Actual_que" class="col-xl-12 col-md-12 col-sm-12">' //Actual_que start
													
//													AlertMesConfirm +='<div class="row"><div class="col-xl-6 col-md-6 col-sm-12 ApproveDivStyle">'
//														 +'<span><p class="ApproveDivLabel">Answer Type </p>'+data[i].QUESTION[j].QTYP+'</span>'
//														 +'</div>'
//														 
//														 + '<div class="col-xl-6 col-md-6 col-sm-12 ApproveDivStyle" ><p class="ApproveDivLabel">Time</p><i class="fa fa-clock-o" aria-hidden="true" style="color:red;"></i> '+data[i].QUESTION[j].QTIM+' Seconds</div><hr/>'
//														 + '</div>'
													
													+'<div id="MathPreview" class=" actualQuesDiv" >' // actualQue start

													if(data[i].QUESTION.length > 1){
														    AlertMesConfirm += '<div class=""><p class="ApproveDivLabel">Question '+(i+1)+'.'+(j+1)+' </p><b>'+data[i].QUESTION[j].QTXT+'</b></div>'
														}else{
															AlertMesConfirm +=  '<div class=""><p class="ApproveDivLabel">Question '+(i+1)+' </p><b>'+data[i].QUESTION[j].QTXT+'</b></div>'
														}
												AlertMesConfirm +='</div>'
												
												// ans div start
												 +'<div class="row" id="actualAnsDiv">' //  actualAnsDiv start
												  for(var k = 0; k < data[i].QUESTION[j].answers.length; k++){
													  
													  if (data[i].QUESTION[j].QTYPID == 1
																|| data[i].QUESTION[j].QTYPID == 2
																|| data[i].QUESTION[j].QTYPID == 3
																|| data[i].QUESTION[j].QTYPID == 4
																|| data[i].QUESTION[j].QTYPID == 6
																|| data[i].QUESTION[j].QTYPID == 7) {
														  
														  AlertMesConfirm += '<div class="col-xl-6 col-md-6 col-sm-12 OptDiv">'
																	  if(data[i].QUESTION[j].answers[k].ansMedia == "TEXT"){
																		  
																		  if(data[i].QUESTION[j].answers[k].rightAnswer == true){
																			  
																			  AlertMesConfirm +='<div >'         
																			  +'<div >' 
																				  		+'<input type="radio"  id="textOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" name="radioOpt'+(i+1)+'-'+(j+1)+'" class="radiostyle">'
																			  			+ '<label  for="textOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" style="color:green;"  id="MathPreview">'+data[i].QUESTION[j].answers[k].content+'</label>'
																			  			+'</div>'
																			  			+'</div>'
																		  }else{
//																			  if (data[i].QUESTION[j].GIVENANSID == data[i].QUESTION[j].answers[k].ansId) {
//																				  AlertMesConfirm +='<div >'
//																				        +'<div  >' 
//																				  		+'<input type="radio" id="textOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" name="radioOpt'+(i+1)+'-'+(j+1)+'" class="radiostyle">'
//																			  			+ '<label  for="textOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" style="color: #1757b8;"  id="MathPreview">'+data[i].QUESTION[j].answers[k].content+'</label>'
//																			  			+'</div>'
//																			  			+'<i class="fa fa-times" aria-hidden="true" style="color: red;">Given Answer</i>'
//																			  			+'</div>'
//																			}else{
																				  AlertMesConfirm +='<div >'
																				        +'<div  >' 
																				  		+'<input type="radio" id="textOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" name="radioOpt'+(i+1)+'-'+(j+1)+'" class="radiostyle">'
																			  			+ '<label  for="textOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" style="color: #1757b8;"  id="MathPreview">'+data[i].QUESTION[j].answers[k].content+'</label>'
																			  			+'</div>'
																			  			+'</div>'
//																			}
																			
																		  }
																		
																	  }else{ 
																		  
																		  if(data[i].QUESTION[j].answers[k].rightAnswer == true){
																		  AlertMesConfirm +='<div >'
																			  +'<div >' 
																			  +' <input id="imgOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" type="radio" name="radioOpt'+(i+1)+'-'+(j+1)+'"  class="radiostyle" />'
																			  +'<label  for="imgOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'">'
																    			 +' <img src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QUESTION[j].answers[k].content+'" class="ApproveQueImgOption" style="border: 6px solid green;" />'
																    			 +'</label>'
																    			 +'</div>'
																    			 +'</div>'
																		  }else{
																			  AlertMesConfirm +='<div >'
																				  +'<div >' 
																				  +' <input id="imgOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" type="radio" name="radioOpt'+(i+1)+'-'+(j+1)+'" class="radiostyle" />'
																				  +'<label  for="imgOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'">'
																	    			 +' <img src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QUESTION[j].answers[k].content+'" class="ApproveQueImgOption" style="border: 6px solid #1757b8;" />'
																	    			 +'</label>'
																	    			 +'</div>'
																	    			 +'</div>'
																		  }
																	  }
																	 
																  AlertMesConfirm +='</div>'
																	// 
															  } else if(data[i].QUESTION[j].QTYPID == 5 || data[i].QUESTION[j].QTYPID == 8) {

																  AlertMesConfirm += '<div class="col-xl-6 col-md-6 col-sm-12 OptDiv">'
																	  if(data[i].QUESTION[j].answers[k].ansMedia == "TEXT"){
																		  if(data[i].QUESTION[j].answers[k].rightAnswer == true){
																			  AlertMesConfirm +='<div >'    
																			        +'<div >' 
																			  		+'<input type="checkbox"  id="textOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" name="ckeckOpt'+(i+1)+'-'+(j+1)+'" class="radiostyle">'
																		  			+ '<label " for="textOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" style="color: green;"  id="MathPreview">'+data[i].QUESTION[j].answers[k].content+'</label>'
																		  			+'</div>'
																		  			+'</div>'
																		  }else{
																			  AlertMesConfirm +='<div >'
																			        +'<div >' 
																			  		+'<input type="checkbox" " id="textOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" name="checkOpt'+(i+1)+'-'+(j+1)+'" class="radiostyle">'
																		  			+ '<label  for="textOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" style="color: #1757b8;"  id="MathPreview">'+data[i].QUESTION[j].answers[k].content+'</label>'
																		  			+'</div>'
																		  			+'</div>'
																		  }
																		
																	  }else if(data[i].QUESTION[j].answers[k].rightAnswer == true){
																			  AlertMesConfirm +='<div >'
																				  +'<div >'
																				  +' <input id="opt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" type="checkbox" name="checkOpt'+(i+1)+'-'+(j+1)+'" class="radiostyle" />'
																				  +'<label  for="opt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'">'
																	    			 +' <img src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QUESTION[j].answers[k].content+'" class="ApproveQueImgOption" style="border: 6px solid green;"/>'
																	    			 +'</label>'
																	    			 +'</div>'
																	    			 +'</div>'
																			  }else{
																				  AlertMesConfirm +='<div >'
																					  +'<div >'
																					  +' <input id="opt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" type="checkbox" name="checkOpt'+(i+1)+'-'+(j+1)+'" class="radiostyle"/>'
																					  +'<label  for="opt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'">'
																		    			 +' <img src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QUESTION[j].answers[k].content+'" class="ApproveQueImgOption" style="border: 6px solid #1757b8;"/>'
																		    			 +'</label>'
																		    			 +'</div>'
																		    			 +'</div>'
																			  }
																		  
																	 
																  AlertMesConfirm +='</div>'
															  
															  }
												  }
												AlertMesConfirm +='</div><hr/>' //  //  actualAnsDiv start
												// ans div end
												
												// solution div start
													 AlertMesConfirm +=  '<div id="solDiv" class="col-xl-12 col-md-12 col-sm-12">'
														    +'<div class="row">' //inner row start
														    if (data[i].QUESTION[j].QSOLMEDIA == undefined) {
														        AlertMesConfirm += '<div class="col-xl-12 col-md-12 col-sm-12 ApproveDivSolutionStyle"><span class=""><p class="ApproveDivLabel">Solution Text</p class="ApproveDivLabel"> </span>'
													    			 +'<div class="inners">'+data[i].QUESTION[j].QSOLTEXT + '</div></div>'
													    			 
													    			 
															}else{
															     AlertMesConfirm += '<div class="col-xl-6 col-md-6 col-sm-12 ApproveDivSolutionStyle"><span class=""><p class="ApproveDivLabel">Solution Text</p class="ApproveDivLabel"> </span>'
													    			 +'<div class="inners">'+data[i].QUESTION[j].QSOLTEXT + '</div></div>'
													    			 
													    			 if(data[i].QUESTION[j].QSOLTYPE == "IMAGE"){
													    				 
													    				 AlertMesConfirm += '<div class="col-xl-6 col-md-6 col-sm-12 ApproveDivStyle" ><span class=""><p class="ApproveDivLabel">Solution Media</p></span>'
													    				 +'<p>'
																		 +' With reference to the follwing Image answer the question'
																		 +' </p>'
													    				 +'<img src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QUESTION[j].QSOLMEDIA+'"  class="ApproveQueImg" /></div>'
													    				 
													    			 }else if(data[i].QUESTION[j].QSOLTYPE == "AUDIO"){
													    				 AlertMesConfirm += '<div class="col-xl-6 col-md-6 col-sm-12 ApproveDivStyle">'
													    					 +'<span class=""><p class="ApproveDivLabel">Solution Media</p></span>'
													    					 +'<p>'
																			 +' With reference to the follwing clip answer the  question'
																			 +' </p>'
																			 +' <audio controls class="ApproveQueAudio">'
																			 +'  <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QUESTION[j].QSOLMEDIA+'" type="audio/mpeg" >'
																			 +' Your browser does not support the audio element.'
																			 +' </audio>'
																			+'</div>'
													    			 }else if(data[i].QUESTION[j].QSOLTYPE == "VIDEO"){
													    				 AlertMesConfirm +='<div class="col-xl-6 col-md-6 col-sm-12 ApproveDivStyle">'
														    					+'<span class=""><p class="ApproveDivLabel">Solution Media</p></span>'
														 						+ '<p>'
														 						+ ' With reference to the follwing clip answer the question'
														 						+ ' </p>'
														 						+'  <video controls  class="ApproveQueVideo" >'
														 						+ '            <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QUESTION[j].QSOLMEDIA+'" type="video/mp4" >'
														 						+ ' <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QUESTION[j].QSOLMEDIA+'" type="video/ogg">'
														 						+ '            Your browser does not support the video tag.'
														 						+ '         </video>' 
														 						+'</div>'
														    			 }
															}
															
												
												          AlertMesConfirm += '</div>'	//inner row end 
														 AlertMesConfirm +='</div>'
												// solution div end
												// variation No div start	
															 
//												if (data[i].varNo != "") {
//													
//													 if (data[i].varNo != undefined) {
//														 AlertMesConfirm += '<div class="col-xl-12 col-md-12 col-sm-12 justify-content-center" ><p class="ApproveDivLabel">Variation No - '+data[i].varNo+'</p></div>'
//													 }else{
//														 AlertMesConfirm += '<div class="col-xl-12 col-md-12 col-sm-12 justify-content-center" ><p class="ApproveDivLabel">Variation No - Not assigned</p></div>'
//													 }
//
//												} else {
//													AlertMesConfirm += '<div class="col-xl-12 col-md-12 col-sm-12 justify-content-center" ><p class="ApproveDivLabel">Variation No - Not assigned</p></div>'
//												}
											
												//	variation No div start
												
												 AlertMesConfirm +='<hr style="border-top: 8px solid rgba(72, 1, 1, 0.82);"></div>' //Actual_que start
												// acutal que end
												
											}	
											
//													AlertMesConfirm +='<div class="col-xl-12 col-md-12 col-sm-12 justify-content-center">'
//														AlertMesConfirm +=	'<button onclick = "modifyQuesGroup('+  data[i].QGID +',\'Non-Approved\', '+  (i+1) +')"  title="MODIFY QUESTION" class="btn btn-success AcT_ArchIcon float-left"><i class="fa fa-pencil-square-o " aria-hidden="true"></i>&nbsp;MODIFY</button> '
//														+'<button onclick = "javascript:com.coep.test.AlertMessage.confirmationToApproveQues('+  data[i].QGID +',\'Non-Approved\',\'Approval\','+data.length+','+selectedTopicId+')" data-toggle="modal"  title="Approve Question" class="btn btn-dark confModelBtn " >APPROVE</button>'
//														+'<button  onClick="javascript:com.coep.test.AlertMessage.confirmationToArchiveOrActiveNonApprovedQues('+data[i].QGID +',\'archive\',\'Non-Approved\','+data.length+', '+selectedTopicId+')" data-toggle="modal"  title="Archive Question" class="btn btn-danger AcT_ArchIcon float-right confModelArchBtn"><i class="fa fa-archive " aria-hidden="true"></i>&nbsp;ARCHIVE<div id="delete-pre"></div></button>'
//														+'</div>'
												
											AlertMesConfirm +='</div>' // ApproveQue main div end
											AlertMesConfirm +='</td>'
											
														+ '</tr>'
										}
									
									 AlertMesConfirm += '    </tbody>'
									 AlertMesConfirm +=  '</table>' + '</div>'
									 
				
				AlertMesConfirm += '</div>' 
								+ '</div>'
				                + '<div class="modal-footer">'
								+ '<button type="button" class="btn btn-success" data-dismiss="modal"  >OKAY</button>&nbsp;&nbsp;'
//								+ '<button type="button" class="btn btn-danger" data-dismiss="modal">No</button>'
//				                + '<button type="button" class="close" data-dismiss="modal">OKAY</button>'
								+ '</div>'
				
							  + '    </div>'
							  + '  </div>'
							  + '</div>'
					//alert modal end
					+ '</div>' // subject_content close
					+ '</div>'// main row close
					+ '</div>' // container close
					
					$("#main-div").append(AlertMesConfirm);
			     	com.coep.test.mathJax.renderMathJax();
			     	
			     	
//			     	for (var i = 0; i < data.length; i++) {
//			     		for (var j = 0; j < data[i].QUESTION.length; j++) {
//			     			if (data[i].QUESTION[j].RESULT == true) {
//								$("check-"+i+"").html('<i class="fa fa-check" aria-hidden="true" style="color: green;"></i>');
//							}else{
//								$("check-"+i+"").html('<i class="fa fa-times" aria-hidden="true" style="color: red;"></i>');
//							}
//			     		}
//			     	}
		
		}
		
		$("#Loading").css("display","none");
		
}
 


})(com.coep.test.ajaxHandler, com.coep.test.testRecords, com.coep.test.AlertMessage, com.coep.test.addProblem);