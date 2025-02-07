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
			+ '<th width="35%">Topic Name</th>'
			+ '<th width="40%">Test Name </th>'
			+ '<th width="5%">Correct Answers</th>'
			+ '<th width="5%">Total Questions</th>'
//			+ '<th width="20%">Topics</th>'
//			+ '<th width="5%" title ="Variation No">Var No</th>'
			+ '<th width="10%">Click here for Question Paper</th>' 
			+ '</tr>'
			+ '</thead>'
			+ '<tbody>'
			
	for (var i = 0; i < data.length; i++) {
		
			recordHtm += '<tr  id="'+i+'" >' 
			recordHtm += '<td width="5%">' + (i + 1)
			recordHtm += '</td>'
			+ '<td width="35%">' + data[i].TOPICNM
			+ '</td>'
			+ '<td width="40%" id="MathPreview" >' + data[i].TESTNM
			+ '</td>'
			+ '<td width="5%">' + data[i].PASSNOQUES
			+ '</td>' 
			+ '<td width="5%">' + data[i].TOTALNOQUES
			+ '</td>' 
			
			+'<td width="10%">'
			+ '<span class="resultBtn btn btn-success" onClick="javascript:com.coep.test.AlertMessage.getQuestionPaperOnClick('+i+','+data[i].TISID+')" data-toggle="modal" data-target="#AlertMesConfirm2-'+i+'-'+data[i].TISID+'" title="Click here for Question Paper" class="btn btn-success AcT_ArchIcon" >'+ "Question <br/> Paper" + '</span>'
			+'</td>' 
			+ '</tr>'
		}
	 recordHtm += '</tbody>'+'</table>' + '</div>'

	 $("#main-div").html(recordHtm);
	 
		$("#main-div").ready(function() {
			var data_table = $('#TestData').DataTable({
				"pageLength" : 100,
				buttons : [ 'copy', 'excel', 'pdf' ],
			     drawCallback: function(){
			          $('.paginate_button.page-item:not(.disabled)', this.api().table().container())          
			             .on('click', function(){
			            	 com.coep.test.mathJax.renderMathJax();
			             });       
			       },
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
				
				var AlertMesConfirm = '';
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
									
										 AlertMesConfirm += '<div class="table-responsive" >'
											+ '<center><div id="showSelQues" style="display: none;  color: #007bff"></div></center>' // showSelQues
											+ '<table id="nonApprovedQuestionData" class="table table-striped table-bordered" style="width:100%">'
											+ '<thead>' + ' <tr>'
											+ '<th  width="5%"  title="Result"  style="cursor: pointer;">'
											+' <label for="result"  style="cursor: pointer; color: white;"  >Result</label>'
											+ ' </th>'
											+ '     <th width="90%">Question </th>'
											+ '   </tr>'
											+ '</thead>'
											+ ' <tbody>'

									for (var i = 0; i < data.length; i++) {
										
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
											
											 // Reference start
											 
											 if($.trim(data[i].QGTYP) == "Text" || $.trim(data[i].QGTYP) == "text"){
													
													if($.trim(data[i].QGMEDIA) == ""){
														AlertMesConfirm += ''
													}else{
														AlertMesConfirm += '<div class="col-xl-12 col-md-12 col-sm-12" ><p class="ApproveDivLabel">Reference </p>'+data[i].QGMEDIA+'</div>'
													}
													
													
												}else if($.trim(data[i].QGTYP) == "Image" || $.trim(data[i].QGTYP) == "image"){
													
													AlertMesConfirm += '<div class="col-xl-6 col-md-6 col-sm-12 ApproveDivStyle">'
														 +'<p class="ApproveDivLabel">'
														 +' Related details/पूरक (संबंधित) माहिती'
														 +' </p>'
														+'<div> <img src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QGMEDIA+'" class="ApproveQueImg" /></div></div><hr/>'
													
												}else if($.trim(data[i].QGTYP) == "Audio" || $.trim(data[i].QGTYP) == "audio"){
													
													AlertMesConfirm += '<div class="col-xl-6 col-md-6 col-sm-12 ApproveDivStyle">'
														 +'<p class="ApproveDivLabel">'
														 +' Related details/पूरक (संबंधित) माहिती'
														 +' </p>'
														 +' <audio controls class="ApproveQueAudio">'
														 +'  <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QGMEDIA+'" type="audio/mpeg" >'
														 +' Your browser does not support the audio element.'
														 +' </audio>'
														+'</div><hr/>'
														
												}else if($.trim(data[i].QGTYP) == "Video" || $.trim(data[i].QGTYP) == "video"){
													AlertMesConfirm +='<div class="col-xl-6 col-md-6 col-sm-12 ApproveDivStyle">'
														+ '<p class="ApproveDivLabel">'
														+ ' Related details/पूरक (संबंधित) माहिती'
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
																				  AlertMesConfirm +='<div >'
																				        +'<div  >' 
																				  		+'<input type="radio" id="textOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" name="radioOpt'+(i+1)+'-'+(j+1)+'" class="radiostyle">'
																			  			+ '<label  for="textOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" style="color: #1757b8;"  id="MathPreview">'+data[i].QUESTION[j].answers[k].content+'</label>'
																			  			+'</div>'
																			  			+'</div>'
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
																		 +' Related details/पूरक (संबंधित) माहिती'
																		 +' </p>'
													    				 +'<img src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QUESTION[j].QSOLMEDIA+'"  class="ApproveQueImg" /></div>'
													    				 
													    			 }else if(data[i].QUESTION[j].QSOLTYPE == "AUDIO"){
													    				 AlertMesConfirm += '<div class="col-xl-6 col-md-6 col-sm-12 ApproveDivStyle">'
													    					 +'<span class=""><p class="ApproveDivLabel">Solution Media</p></span>'
													    					 +'<p>'
																			 +' Related details/पूरक (संबंधित) माहिती'
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
														 						+ ' Related details/पूरक (संबंधित) माहिती'
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
															 
												//	variation No div start
												
												 AlertMesConfirm +='<hr style="border-top: 8px solid rgba(72, 1, 1, 0.82);"></div>' //Actual_que start
												// acutal que end
												
											}	
											
												
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
		}
		
		$("#Loading").css("display","none");
}
 


})(com.coep.test.ajaxHandler, com.coep.test.testRecords, com.coep.test.AlertMessage, com.coep.test.addProblem);