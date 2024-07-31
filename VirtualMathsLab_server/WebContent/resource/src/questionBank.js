(function(AH, AP, QB, AM, MJ) {
	
	topicIdArray = [];	
	
	QB.chkToSelectAll = false;
	
	QB.getTopicDetailsToGetQuesBank = function() {
		
		var allData = {};
		var subData = {};
		var	topicData = {};
		var compLevelData = {};
		var mediaTypeData = {};
		var	ansTypeData = {};
		
		 questionGruopJSON = {};

		 //topic = {};
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
							QB.getAllDataForQuesBank(allData); 
						} else {
							// AP.showCustomAlert("Error", data.msg);
						}
					},
					error : function() {
					}
		
				});
     		}
	
	}
	
//	QB.topicDataForApprovedQues = {};
//	QB.getTopicListDetailsFromHOME = function(data) {
//		QB.topicDataForApprovedQues = data;
//	}
	
	QB.getAllDataForQuesBank = function(allData) {
		AP.mainData = allData;
	}
	
	
	
	QB.getQuestionBankOfUser = function(status) {

		// ajax call to get question groups for user
		
		QB.getTopicDetailsToGetQuesBank();
		
		$.ajax({
			type : "GET",
			url : AP.baseURL
					+ "questionGroups/api/get/user/questGroups/"+status,
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if(data.done == true){
					if(status == "Active"){
						
						QB.renderActiveQuestionGroupsForUser(data.data);
						
					}else{
						
						QB.renderArchivedQuestionGroupsForUser(data.data);
					}
					
				}else{
					$("#main-div").html("<div class='col-xl-12 col-md-12 col-sm-12 alert alert-danger' style='text-align:center;'>No questions in the database to display....</div>");
				}
			},
			error : function() {
			}

		});
		
	}
	
	
	AM.confirmationToArchiveOrActive = function(QGID,status) {
		var AlertComfirmFlag = false;
			AlertMsg = " Are you sure, you want to "+status+" this question ?";
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
					QB.moveQuestionGroupToArchiveOrActive(QGID, status);
					return AlertComfirmFlag;
				}
			
		}
	
	
	
	QB.moveQuestionGroupToArchiveOrActive = function(QGID, status) {

		$.ajax({
			type : "DELETE",
			url : AP.baseURL + "questionGroups/" + QGID,
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == false) {

					showToast.show(data.msg, false);
				} else {
					if (data.length != 0) {
						showToast.show(data.msg, true);
						if(status == 'archive'){
							QB.getQuestionBankOfUser('Active');
						}else{
							QB.getQuestionBankOfUser('Archived');
						}
						

					} else {
						showToast.show(data.msg, false);
					}
				}
			},
			error : function() {
			}

		});
	
	}
	
	
	AM.confirmationToArchiveOrActiveApprovedQues = function(QGID, status, appStatus ) {

		var AlertMesConfirm = '';
		
		 if(selectedQuesGrps.length == 0){
			 
			 
			 var AlertComfirmFlag = false;
				AlertMsg = " Are you sure, you want to "+status+" this question ?";
				 AlertMesConfirm = '';
					AlertMesConfirm +=  '<div class="container-fluid">'
					+ '<div class="row">'
					+ '<div  class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'
					//Alert modal start
							+ '<div class="modal" id="AlertMesConfirmSingle">'
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
						QB.moveQuestionGroupToArchiveOrActiveApprovedQues(QGID, status, appStatus, QGIDArr, "single");
						return AlertComfirmFlag;
					}
					
				

		 }else{
		
			 var QGIDArr = "["
					for (var a = 0; a < selectedQuesGrps.length; a++) {
						if(a == (selectedQuesGrps.length - 1)){
							QGIDArr += selectedQuesGrps[a]+"]"
						}else{
							QGIDArr += selectedQuesGrps[a]+","
						}
					}
			 

			 var AlertComfirmFlag = false;

			 
			 mulAlertMsg =  " Are you sure, you want to "+status+" "+selectedQuesGrps.length+"  questions ?";
			 
				 AlertMesConfirm = '';
					AlertMesConfirm +=  '<div class="container-fluid">'
					+ '<div class="row">'
					+ '<div  class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'
					//Alert modal start
							+ '<div class="modal" id="AlertMesConfirmMuliple-'+selectedQuesGrps.length+'">'
							+ '<div class="modal-dialog">'
							+ '<div class="modal-content">'
				            
				            	 +' <div class="modal-header bg-info" style="color:#fff;">'
									+ '   <h4 class="modal-title">Confirmation !!</h4>'
									+ '  <button type="button" class="close" data-dismiss="modal">&times;</button>'
									+ '  </div>'
					
									+ '   <div class="modal-body">'
									+ '<span id="AlertMsgStyle">'+mulAlertMsg+'</span>'
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
						QB.moveQuestionGroupToArchiveOrActiveApprovedQues(QGID, status, appStatus, QGIDArr,"multiple");
						return AlertComfirmFlag;
					}
		 
		 }
		
		}
	
	
	
	QB.moveQuestionGroupToArchiveOrActiveApprovedQues = function(QGID, status, appStatus, QGIDArr, flag) {

		if(flag == "single"){
			$.ajax({
				type : "DELETE",
				url : AP.baseURL + "questionGroups/" + QGID,
				dataType : 'json',
				contentType : 'application/json',
				success : function(data) {
					if (data.done == false) {

						showToast.show(data.msg, false);
					} else {
						if (data.length != 0) {
							showToast.show(data.msg, true);
							if(status == 'archive'){
								QB.getQuestionsToApprove(appStatus, 0 , 0, 0)
							}else{
								QB.getQuestionsToApprove(appStatus, 0 , 0, 0);
							}
							

						} else {
							showToast.show(data.msg, false);
						}
					}
				},
				error : function() {
				}

			});
		}else{

//			if (selectAllToArchive == true) {}else{
				
				$.ajax({
					type : "DELETE",
					url : AP.baseURL
					+ "questionGroups/archived/multiple/"+QGIDArr,
					data : "QGIDArr=" + QGIDArr,
					dataType : 'json',
					contentType : 'application/json',
					success : function(data) {
						if (data.done == false) {

							showToast.show(data.msg, false);
						} else {
							if (data.length != 0) {
								showToast.show(data.msg, true);
								if(status == 'archive'){
									QB.getQuestionsToApprove(appStatus, 0 , 0, 0)
								}else{
									QB.getQuestionsToApprove(appStatus, 0 , 0, 0);
								}
								

							} else {
								showToast.show(data.msg, false);
							}
						}
					},
					error : function() {
					}

				});

		
		}
	
		
	}
	
	
	
	AM.confirmationToDeleteQues = function(QGID, status) {

		var AlertComfirmFlag = false;
			AlertMsg = " Are you sure, you want to "+status+" this question permanently..?";
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
					QB.deleteQuestionGroupFromArchived(QGID, status);
					return AlertComfirmFlag;
				}
			
		
	}
	
	
	QB.deleteQuestionGroupFromArchived = function(QGID, status) {
		$.ajax({
			type : "DELETE",
			url : AP.baseURL + "questionGroups/delete/archived/" + QGID,
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == false) {

					showToast.show(data.msg, false);
				} else {
					if (data.length != 0) {
						showToast.show(data.msg, true);
						if(status == 'delete'){
							modifyQuw == true;
							QB.getAllQuestionsFromQuesBank('Archived');
						}
						

					} else {
						showToast.show(data.msg, false);
					}
				}
			},
			error : function() {
			}

		});
	}
	
	
	
	QB.renderActiveQuestionGroupsForUser = function(data) {

		var quesHtm = '';
		 quesHtm += '<div class="overlay" id="Loading">'
		 + '<div class="overlay__inner">'
		 + '<div class="overlay__content"><span class="spinner"></span><br/><span class="loading">LOADING....</span></div>'
		 + '</div>'
		 + '</div>'
		 quesHtm += '<button id="btn-show-all-children" type="button" class="btn btn-dark">Expand All</button>'
			 +'<button id="showActiveQuesGroupsForUser" type="button" class="btn btn-dark">Show Question Groups</button>'
			 +'<hr>'
			 
		 quesHtm += '<h1>ACTIVE QUESTION  DETAILS</h1>'
			 
				+ '<div class="table-responsive">'
				+ '<table id="QuestionData" class="table table-striped table-bordered" style="width:100%">'
				+ '<thead>' + ' <tr>'
				+ '<th width="5%" > </th>'
				+ '<th width="5%" >Sr. No. </th>'
				+ '     <th width="5%">Question Type</th>'
				+ '     <th width="30%">Question </th>'
				+ '      <th width="5%">Time (Seconds)</th>'
				+ '      <th width="15%">Answer Type</th>'
				+ '      <th width="20%">Topics</th>'
				+ '      <th width="5%" title ="Variation No">Var No</th>'
				+'	<th width="5%">Action</th>' 
				+ '   </tr>'
				+ '</thead>'
				+ ' <tbody>'
		var srNo = 0;
		for (var i = 0; i < data.length; i++) {
			
			var topicName = "";
			for (var q = 0; q < data[i].QGTOPICS.length; q++) {
					topicName += data[i].QGTOPICS[q].TNO+" - "+data[i].QGTOPICS[q].TNM +" ("+data[i].QGTOPICS[q].TNM1+") "+",<br>";
			}
			
			for (var j = 0; j < data[i].QUESTION.length; j++) {
				quesHtm += '<tr  id="'+i+'-'+j+'" >' 
				+'<td  width="5%" class="details-control"  ></td>'
				if(data[i].QUESTION.length > 1){
					quesHtm += '<td width="5%">Q' + (srNo + 1)+'.'+(j+1)
				}else{
					quesHtm += '<td width="5%">Q' + (srNo + 1)
				}
				
				quesHtm += '</td>'
				+ '<td width="5%">' + data[i].QGTYP
				+ '</td>'
				+ '<td width="30%" id="MathPreview" >' + data[i].QUESTION[j].QTXT
				+ '</td>'
				+ '<td width="10%">' + data[i].QUESTION[j].QTIM
				+ '</td>' 
				+ '<td width="15%">' + data[i].QUESTION[j].QTYP
				+ '</td>' 
				
				+'<td width="20%">' + topicName + '</td>' 
				+'<td width="5%">' + data[i].varNo + '</td>' 
				
				+'<td width="5%">'
				+'<span onclick = "modifyQuesGroup('+  data[i].QGID +',\'Active\')"  title="MODIFY QUESTION" class="btn btn-success AcT_ArchIcon"><i class="fa fa-pencil-square-o " aria-hidden="true"></i></span> '
							+ '<span  onClick="javascript:com.coep.test.AlertMessage.confirmationToArchiveOrActive('+data[i].QGID +',\'archive\')" data-toggle="modal" data-target="#AlertMesConfirm" title="Archive Question" class="btn btn-danger AcT_ArchIcon"><i class="fa fa-archive " aria-hidden="true"></i><a data-toggle="modal" data-target="#archiveModel"></a><div id="delete-pre"></div></span>' 
							+ '</td>' + '</tr>'
			}
			
			if( data[i].QUESTION.length != 0){
				srNo++;
			}
			
		}
		 quesHtm += '    </tbody>'

		+ '</table>' + '</div>'
		
		 $("#main-div").html(quesHtm);
		 
			$("#main-div").ready(function() {
				
				var table = $('#QuestionData').DataTable({
					"pageLength" : 50,
					// lengthChange: false,
					buttons : [ 'copy', 'excel', 'pdf' ],
				     drawCallback: function(){
				          $('.paginate_button.page-item:not(.disabled)', this.api().table().container())          
				             .on('click', function(){
				            	 com.coep.test.mathJax.renderMathJax();
				             });       
				       },
				    	"ordering": false,
				}).on('search.dt', function() {
					 MJ.renderMathJax();
				});
				
				 	$('#btn-show-all-children').on('click', function(){
				        
				 		// Expand row details
				 		if($(this).text() == "Expand All"){
				 			$(this).text("Collapse All");
				 		}else{
				 			$(this).text("Expand All");
				 		}
				 		
				 		
				        table.rows(':not(.parent)').nodes().to$().find('td:first-child').trigger('click');
				    });

				
				function format (d) {
				    var k = d.split("-");
				    var l = k[0];
				    var m = k[1];
				    
				    var ansHtm = '';
				    
				    ansHtm += '<div><table cellpadding="5" cellspacing="0" border="0" class="ExpandTables">'
							    
				    	ansHtm += '<thead><th width="8%">Sr. No.</th>'
					    		 + '<th>Option</th>'
					    		 + '<th>Right Or Wrong</th>'
					    		 + '<th>Option Type</th>'
					    		 + '</thead><tbody>'
	
					   for(var p = 0; p < data[l].QUESTION[m].answers.length; p++){
					    	ansHtm += 	'<tr>'
					    		 +'<td>'+(p+1)+'</td>'
					    		 if(data[l].QUESTION[m].answers[p].ansMedia == "TEXT"){
					    			 ansHtm +='<td  id="MathPreview">'+data[l].QUESTION[m].answers[p].content+'</td>'
					    		 }else{
					    			 ansHtm +='<td>'
					    			 +' <img src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[l].QUESTION[m].answers[p].content+'" class="optionImg" />'
					    			 +'</td>'
					    		 }
						         
						         if(data[l].QUESTION[m].answers[p].rightAnswer == true){
						        	 ansHtm += '<td> <i class="fa fa-check-circle" aria-hidden="true" style="color:green;"></i></td>'
						         }else{
						        	 ansHtm += '<td><i class="fa fa-times-circle-o" aria-hidden="true" style="color:red;"></i></td>'
						         }
						        	 
					    	 ansHtm += '<td>'+data[l].QUESTION[m].answers[p].ansMedia+'</td>'
					    	 
					    	
					    	 ansHtm += '</tr>'
					    		 
					    }
					        
				    	ansHtm +=  '</tbody></table></div>';
				    	
				    	
				    	ansHtm +=  '<div id="solutionDiv" class="row">'
				    		
				    			 ansHtm += '<div class="col-sm-12 col-md-6 col-lg-6 col-xl-6"><div class="heading">Solution Text</div>'
				    			 +'<div class="inners">'+data[l].QUESTION[m].QSOLTEXT + '</div></div>'
				    			 
				    			 if(data[l].QUESTION[m].QSOLTYPE == "IMAGE"){
				    				 
				    				 ansHtm += '<div class="col-sm-12 col-md-6 col-lg-6 col-xl-6" ><div class="heading">Solution Media</div>'
				    				 +'<div class="inners"><p>'
									 +' With reference to the following Image answer the question'
									 +' </p>'
				    				 +'<img src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[l].QUESTION[m].QSOLMEDIA+'"  class="popup-img" /></div>'
				    			 }else if(data[l].QUESTION[m].QSOLTYPE == "AUDIO"){
				    				 ansHtm += '<div class="col-sm-12 col-md-6 col-lg-6 col-xl-6">'
				    					 +'<div class="heading">Solution Media</div>'
				    					 +'<div class="inners"><p>'
										 +' With reference to the following clip answer the  question'
										 +' </p>'
										 +' <audio controls>'
										 +'  <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[l].QUESTION[m].QSOLMEDIA+'" type="audio/mpeg"  class="">'
										 +' Your browser does not support the audio element.'
										 +' </audio>'
										+'</div>'
				    			 }else if(data[l].QUESTION[m].QSOLTYPE == "VIDEO"){
				    				 ansHtm +='<div class="col-sm-12 col-md-6 col-lg-6 col-xl-6">'
				    					+'<div class="heading">Solution Media</div>'
				 						+ '<div class="inners"><p>'
				 						+ ' With reference to the following clip answer the question'
				 						+ ' </p>'
				 						+'  <video controls s >'
				 						+ '            <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[l].QUESTION[m].QSOLMEDIA+'" type="video/mp4"  class"">'
				 						+ ' <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[l].QUESTION[m].QSOLMEDIA+'" type="video/ogg">'
				 						+ '            Your browser does not support the video tag.'
				 						+ '         </video>' 
				 						+'</div>'
				    			 }
				    		+'</div>' 
				    		+'</div>'
				        return ansHtm;
				       
				}
				
				
				   $('#QuestionData tbody').on('click', 'td.details-control', function () {
				        var tr = $(this).closest('tr');
				        var row = table.row( tr );
				 
				        if ( row.child.isShown() ) {
				            // This row is already open - close it
				            row.child.hide();
				            tr.removeClass('shown');
				        }
				        else {
				            // Open this row
				            row.child( format(row.id()) ).show();
				            MJ.renderMathJax();
				            tr.addClass('shown');
				            
				        }
				    } );
				   
				   
				 
				   
				   $('#showActiveQuesGroupsForUser').on('click',  function () {
					   
					   var renderQGHtm = '';

						renderQGHtm += '<button id="btn-show-all-children" type="button" class="btn btn-dark">Expand All</button>'
							 +'<button id="showOnlyQuestions" type="button" class="btn btn-dark">Show Questions</button>'
						+'<hr>'
						
						renderQGHtm += '<h1>QUESTION GROUP DETAILS</h1>'
								+ '<div class="table-responsive">'
								+ '<table id="QGData" class="table table-striped table-bordered" style="width:100%">'
								+ '<thead>' + ' <tr>'
								+ '<th width="10%" > </th>'
								+ '     <th width="10%">Question Group</th>'
								+ '      <th width="10%">Question Definition</th>'
								+ '      <th width="65%">Topics</th><th width="5%">Action</th>' + '   </tr>' + '</thead>'
								+ ' <tbody>'
						for (var i = 0; i < data.length; i++) {
							renderQGHtm += '<tr  id="'+i+'" >' 
									+'<td  width="10%" class="details-control"  ></td>'
									+ '<td width="10%">' + data[i].QGTYP
									+ '</td>' + '<td width="10%">' 
									if(data[i].NME  != undefined){
										renderQGHtm += ''+data[i].NME +'<br>'
									}else{
										renderQGHtm += 'No Name <br>' 
									}
									
									renderQGHtm +=  data[i].QUESTION.length + ' Questions'
									var topicName = "";
									for (var j = 0; j < data[i].QGTOPICS.length; j++) {
										
											topicName += data[i].QGTOPICS[j].TNO+" - "+data[i].QGTOPICS[j].TNM +",<br>";
									}
									renderQGHtm += '</td>' + '<td width="65%">' + topicName
									renderQGHtm += '</td>' + '<td width="5%">'
									+'<span onclick = "modifyQuesGroup('+  data[i].QGID +',\'Active\')"  title="MODIFY QUESTION" class="btn btn-success AcT_ArchIcon"><i class="fa fa-pencil-square-o " aria-hidden="true"></i></span> '
									+ '<span  onClick="javascript:com.coep.test.AlertMessage.confirmationToArchiveOrActive('+data[i].QGID +',\'archive\')" data-toggle="modal" data-target="#AlertMesConfirm" title="Archive Question" class="btn btn-danger AcT_ArchIcon"><i class="fa fa-archive " aria-hidden="true"></i><a data-toggle="modal" data-target="#archiveModel"></a><div id="delete-pre"></div></span>' 
									+ '</td>' + '</tr>'
						}
						renderQGHtm += '    </tbody>'

						+ '</table>' + '</div>'
						$("#main-div").html(renderQGHtm);
						$("#main-div").ready(function() {
							var table = $('#QGData').DataTable({
								"pageLength" : 50,
								// lengthChange: false,
								buttons : [ 'copy', 'excel', 'pdf' ],
							     drawCallback: function(){
							          $('.paginate_button.page-item:not(.disabled)', this.api().table().container())          
							             .on('click', function(){
							            	 com.coep.test.mathJax.renderMathJax();
							             });       
							       },
							    	"ordering": false,
							}).on('search.dt', function() {
								 MJ.renderMathJax();
							});
							
							
							 	$('#btn-show-all-children').on('click', function(){
							        
							 		// Expand row details
							 		if($(this).text() == "Expand All"){
							 			$(this).text("Collapse All");
							 		}else{
							 			$(this).text("Expand All");
							 		}
							 		
							 		
							        table.rows(':not(.parent)').nodes().to$().find('td:first-child').trigger('click');
							    });

							
							function format (d) {
							    var k = d;
							    var quesHtm = '';
							    
							    if(data[k].QUESTION.length == 0){
							    	
							    	return "<div class='col-xl-12 col-md-12 col-sm-12 alert alert-danger' style='text-align:center;'>This question group is empty.. Please add qustion</div>";
							    	
							    }else{
							    	 quesHtm += '<table cellpadding="5" cellspacing="0" border="0" class="ExpandTables">'
										    
								    	 quesHtm += '<thead><th>Sr. No</th>'
								    		 + '<th>Question</th>'
								    		 + '<th>Time(seconds)</th>'
								    		 + '<th>Question Type</th></thead>'
								    for(var m = 0; m < data[k].QUESTION.length; m++){
								    	quesHtm += 	'<tbody><tr>'
								    		 +'<td>Q'+(parseInt(k)+1)+'.'+''+(m+1)+'</td>'
									         +'<td>'+data[k].QUESTION[m].QTXT+'</td>'
									         
									         +'<td>'+data[k].QUESTION[m].QTIM+'</td>'
									         +'<td>'+data[k].QUESTION[m].QTYP+'</td>'
								    		 +'</tr></tbody>'
								    }
								        
								    quesHtm +=  '</table>';
							    }
							        
							        return quesHtm;
							        
							}
							
							
							   $('#QGData tbody').on('click', 'td.details-control', function () {
							        var tr = $(this).closest('tr');
							        var row = table.row( tr );
							 
							        if ( row.child.isShown() ) {
							            // This row is already open - close it
							            row.child.hide();
							            tr.removeClass('shown');
							        }
							        else {
							            // Open this row
							        	
							            row.child( format(row.id()) ).show();
							            tr.addClass('shown');
							            MJ.renderMathJax();
							        }
							    } );
							   
						});
						
						
						  $('#showOnlyQuestions').on('click',function () {
								 QB.getQuestionBankOfUser('Active');
							 });
				   });
				   
				   MJ.renderMathJax();
				   
				   $("div.dataTables_wrapper div.dataTables_filter input").removeClass("form-control-sm");
					$("div.dataTables_wrapper div.dataTables_filter label").addClass("col-md-12 col-xl-12");
					$("div.dataTables_wrapper div.dataTables_filter input").addClass("col-md-12 col-xl-12");
					//$("#Loading").css("display","none");
					 setTimeout(function(){ $("#Loading").css("display","none"); }, 2000);
			});
		 
		
		modifyQuesGroup = function(QGID, backStatus) {
			modifyQuw = true;
			AP.QGMLID = null;			
			$.ajax({
				type : "GET",
				url : AP.baseURL
				+ "questionGroups/",
				data : "QGID=" + QGID,
				dataType : 'json',
				contentType : 'application/json',
				success : function(data) {
					
					if (data.done == false) {
						showToast.show(data.msg, false);
					} else {
						if (data.done == true) {
							modifyQuw = true;
							questionGruopJSON = data.data;
							AP.QGMLID = data.data.quesGroupMediaLinks.quesGroupMediaLinkId;
							
							if(data.data.quesGroupMediaLinks.quesUsage == "question"){
								AP.mediaURL = data.data.quesGroupMediaLinks.mediaURLText;	
							}
													
							AP.modifyQuestionGroup(QGID, data.data, AP.mainData, backStatus);
							$("#laoding").removeClass("spinner-border");
						} else {
							showToast.show('', false);
						}
					}
				},
				error : function() {
				}
			});
		} // modify fun ends
		
		
		
		
	}// function end
	

	QB.renderArchivedQuestionGroupsForUser = function(data) {
		
		 var quesHtm = '';
		 quesHtm += '<div class="overlay" id="Loading">'
		 + '<div class="overlay__inner">'
		 + '<div class="overlay__content"><span class="spinner"></span><br/><span class="loading">LOADING....</span></div>'
		 + '</div>'
		 + '</div>'
		 quesHtm += '<button id="btn-show-all-children" type="button" class="btn btn-dark">Expand All</button>'
			 +'<button id="showArchiveQuesGroupsForUser" type="button" class="btn btn-dark">Show Question Groups</button>'
		 +'<hr>'
		 
		 quesHtm += '<h1>ARCHIVE QUESTION  DETAILS</h1>'
			 
				+ '<div class="table-responsive">'
				+ '<table id="QuestionData" class="table table-striped table-bordered" style="width:100%">'
				+ '<thead>' + ' <tr>'
				+ '<th width="5%" > </th>'
				+ '<th width="5%" >Sr. No. </th>'
				+ '     <th width="5%">Question Type</th>'
				+ '     <th width="30%">Question </th>'
				+ '      <th width="5%">(Seconds)</th>'
				+ '      <th width="30%">Question Type</th>'
				+ '      <th width="20%">Topics</th>'
				+ '      <th width="5%" title="Variation No">Var No</th>'
				+'	<th width="5%">Action</th>' 
				+ '   </tr>'
				+ '</thead>'
				+ ' <tbody>'
		var srNo = 0;
		for (var i = 0; i < data.length; i++) {
			var topicName = "";
			for (var q = 0; q < data[i].QGTOPICS.length; q++) {
				topicName += data[i].QGTOPICS[q].TNO+" - "+data[i].QGTOPICS[q].TNM +" ("+data[i].QGTOPICS[q].TNM1+") "+",<br>";
			}
			
			for (var j = 0; j < data[i].QUESTION.length; j++) {
				quesHtm += '<tr  id="'+i+'-'+j+'" >' 
				+'<td  width="5%" class="details-control"  ></td>'
				if(data[i].QUESTION.length > 1){
					quesHtm += '<td width="5%">Q' + (srNo + 1)+'.'+(j+1)
				}else{
					quesHtm += '<td width="5%">Q' + (srNo + 1)
				}
				
				quesHtm += '</td>'
				+ '<td width="5%">' + data[i].QGTYP
				+ '</td>'
				+ '<td width="30%"  id="MathPreview">' + data[i].QUESTION[j].QTXT
				+ '</td>'
				+ '<td width="5%">' + data[i].QUESTION[j].QTIM
				+ '</td>' 
				+ '<td width="30%">' + data[i].QUESTION[j].QTYP
				+ '</td>' 

				+'<td width="20%">' + topicName + '</td>' 
				+'<td width="5%">' + data[i].varNo + '</td>' 
				
				+'<td width="5%">'
				+'<span onclick = "modifyQuesGroup('+  data[i].QGID +',\'Archived\')"  title="MODIFY QUESTION" class="btn btn-success AcT_ArchIcon"><i class="fa fa-pencil-square-o " aria-hidden="true"></i></span> '
							+ '<span  onClick="javascript:com.coep.test.AlertMessage.confirmationToArchiveOrActive('+data[i].QGID +',\'active\')" data-toggle="modal" data-target="#AlertMesConfirm" title="Active Question" class="btn btn-success AcT_ArchIcon"><i class="fa fa-undo" aria-hidden="true" ></i><a data-toggle="modal" data-target="#archiveModel"></a><div id="delete-pre"></div></span>' 
							+ '</td>' + '</tr>'
			}
			

			if( data[i].QUESTION.length != 0){
							srNo++;
			}
		}
		
		 quesHtm += '    </tbody>'

		+ '</table>' + '</div>'
		$("#main-div").html(quesHtm);
		 
			$("#main-div").ready(function() {
				var table = $('#QuestionData').DataTable({
					"pageLength" : 50,
					// lengthChange: false,
					buttons : [ 'copy', 'excel', 'pdf' ],
				     drawCallback: function(){
				          $('.paginate_button.page-item:not(.disabled)', this.api().table().container())          
				             .on('click', function(){
				            	 com.coep.test.mathJax.renderMathJax();
				             });       
				       },
				    	"ordering": false,
				}).on('search.dt', function() {
					 MJ.renderMathJax();
				});
				
				 	$('#btn-show-all-children').on('click', function(){
				        
				 		// Expand row details
				 		if($(this).text() == "Expand All"){
				 			$(this).text("Collapse All");
				 		}else{
				 			$(this).text("Expand All");
				 		}
				 		
				 		
				        table.rows(':not(.parent)').nodes().to$().find('td:first-child').trigger('click');
				    });

				
				function format (d) {
				    var k = d.split("-");
				    var l = k[0];
				    var m = k[1];
				    
				    var ansHtm = '';
				    
				    ansHtm += '<div><table cellpadding="5" cellspacing="0" border="0" class="ExpandTables">'
							    
				    	ansHtm += '<thead><th width="8%">Sr. No.</th>'
					    		 + '<th>Option</th>'
					    		 + '<th>Right Or Wrong</th>'
					    		 + '<th>Option Type</th></thead><tbody>'
					    		
					    for(var p = 0; p < data[l].QUESTION[m].answers.length; p++){
					    	ansHtm += 	'<tr>'
					    		 +'<td>'+(p+1)+'</td>'
					    		 if(data[l].QUESTION[m].answers[p].ansMedia == "TEXT"){
					    			 ansHtm +='<td  id="MathPreview">'+data[l].QUESTION[m].answers[p].content+'</td>'
					    		 }else{
					    			 ansHtm +='<td>'
					    			 +' <img src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[l].QUESTION[m].answers[p].content+'" class="optionImg" />'
					    			 +'</td>'
					    		 }
						         
						         if(data[l].QUESTION[m].answers[p].rightAnswer == true){
						        	 ansHtm += '<td> <i class="fa fa-check-circle" aria-hidden="true" style="color:green;"></i></td>'
						         }else{
						        	 ansHtm += '<td><i class="fa fa-times-circle-o" aria-hidden="true" style="color:red;"></i></td>'
						         }
						        	 
					    	 ansHtm += '<td>'+data[l].QUESTION[m].answers[p].ansMedia+'</td>'
					    		 +'</tr>'
					    }
					        
				    ansHtm +=  '</tbody></table></div>';
				    
				    
				    ansHtm +=  '<div id="solutionDiv" class="row">'
			    		
		    			 ansHtm += '<div class="col-sm-12 col-md-6 col-lg-6 col-xl-6"><div class="heading">Solution Text</div>'
		    			 +'<div class="inners">'+data[l].QUESTION[m].QSOLTEXT + '</div></div>'
		    			 
		    			 if(data[l].QUESTION[m].QSOLTYPE == "IMAGE"){
		    				 
		    				 ansHtm += '<div class="col-sm-12 col-md-6 col-lg-6 col-xl-6" ><div class="heading">Solution Media</div>'
		    				 +'<div class="inners"><p>'
							 +' With reference to the following Image answer the question'
							 +' </p>'
		    				 +'<img src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[l].QUESTION[m].QSOLMEDIA+'"  class="popup-img" /></div>'
		    			 }else if(data[l].QUESTION[m].QSOLTYPE == "AUDIO"){
		    				 ansHtm += '<div class="col-sm-12 col-md-6 col-lg-6 col-xl-6">'
		    					 +'<div class="heading">Solution Media</div>'
		    					 +'<div class="inners"><p>'
								 +' With reference to the following clip answer the  question'
								 +' </p>'
								 +' <audio controls>'
								 +'  <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[l].QUESTION[m].QSOLMEDIA+'" type="audio/mpeg"  class="">'
								 +' Your browser does not support the audio element.'
								 +' </audio>'
								+'</div>'
		    			 }else if(data[l].QUESTION[m].QSOLTYPE == "VIDEO"){
		    				 ansHtm +='<div class="col-sm-12 col-md-6 col-lg-6 col-xl-6">'
		    					+'<div class="heading">Solution Media</div>'
		 						+ '<div class="inners"><p>'
		 						+ ' With reference to the following clip answer the question'
		 						+ ' </p>'
		 						+'  <video controls s >'
		 						+ '            <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[l].QUESTION[m].QSOLMEDIA+'" type="video/mp4"  class"">'
		 						+ ' <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[l].QUESTION[m].QSOLMEDIA+'" type="video/ogg">'
		 						+ '            Your browser does not support the video tag.'
		 						+ '         </video>' 
		 						+'</div>'
		    			 }
		    		+'</div>' 
		    		+'</div>'
				    
				        return ansHtm;
				}
				
				
				   $('#QuestionData tbody').on('click', 'td.details-control', function () {
				        var tr = $(this).closest('tr');
				        var row = table.row( tr );
				 
				        if ( row.child.isShown() ) {
				            // This row is already open - close it
				            row.child.hide();
				            tr.removeClass('shown');
				        }
				        else {
				            // Open this row
				        	
				            row.child( format(row.id()) ).show();
				            tr.addClass('shown');
				            MJ.renderMathJax();
				        }
				       
				    } );
				   
				   
				   $('#showArchiveQuesGroupsForUser').on('click',  function () {
					   
					   var renderQGHtm = '';

						renderQGHtm += '<button id="btn-show-all-children" type="button" class="btn btn-dark">Expand All</button>'
							+'<button id="showOnlyQuestions" type="button" class="btn btn-dark">Show Questions</button>'
							+'<hr>'
							
						renderQGHtm += '<h1>QUESTION GROUP DETAILS</h1>'
								+ '<div class="table-responsive">'
								+ '<table id="QGData" class="table table-striped table-bordered" style="width:100%">'
								+ '<thead>' + ' <tr>'
								+ '<th width="10%" > </th>' 	
								+ '     <th width="10%">Question Group</th>'
								+ '      <th width="10%">Question Definition</th>'
								+ '      <th width="65%">Topics</th><th width="5%">Action</th>' + '</tr>' + '</thead>'
								+ ' <tbody>'
						for (var i = 0; i < data.length; i++) {
							renderQGHtm += '<tr  id="'+i+'" >' 
								+'<td  width="10%" class="details-control"  ></td>'
									+ '<td width="10%">' + data[i].QGTYP
									+ '</td>' + '<td width="10%">' 
									if(data[i].NME  != undefined){
										renderQGHtm += ''+data[i].NME +'<br>'
									}else{
										renderQGHtm += 'No Name <br>' 
									}
									
									renderQGHtm +=  data[i].QUESTION.length + ' Questions'
									var topicName = "";
									for (var j = 0; j < data[i].QGTOPICS.length; j++) {
										
											topicName += data[i].QGTOPICS[j].TNO+" - "+data[i].QGTOPICS[j].TNM +",<br>";
									}
									renderQGHtm += '</td>' + '<td width="65%">' + topicName
									renderQGHtm += '</td>' + '<td width="5%">'
									+'<span onclick = "modifyQuesGroup('+  data[i].QGID +',\'Archived\')"  title="MODIFY QUESTION" class="btn btn-success AcT_ArchIcon"><i class="fa fa-pencil-square-o " aria-hidden="true"></i></span> '
									+ '<span  onClick="javascript:com.coep.test.AlertMessage.confirmationToArchiveOrActive('+data[i].QGID +',\'active\')" data-toggle="modal" data-target="#AlertMesConfirm"  title="Active Question" class="btn btn-success AcT_ArchIcon"><i class="fa fa-undo" aria-hidden="true" ></i></span>' 
									+ '</td>' + '</tr>'
						}
						renderQGHtm += '    </tbody>'

						+ '</table>' + '</div>'
						$("#main-div").html(renderQGHtm);
						$("#main-div").ready(function() {
							var table = $('#QGData').DataTable({
								"pageLength" : 50,
								// lengthChange: false,
								buttons : [ 'copy', 'excel', 'pdf' ],
							     drawCallback: function(){
							          $('.paginate_button.page-item:not(.disabled)', this.api().table().container())          
							             .on('click', function(){
							            	 com.coep.test.mathJax.renderMathJax();
							             });       
							       },
							    	"ordering": false,
							}).on('search.dt', function() {
								 MJ.renderMathJax();
							});

							$('#btn-show-all-children').on('click', function(){
						        
						 		// Expand row details
						 		if($(this).text() == "Expand All"){
						 			$(this).text("Collapse All");
						 		}else{
						 			$(this).text("Expand All");
						 		}
						 		
						 		
						        table.rows(':not(.parent)').nodes().to$().find('td:first-child').trigger('click');
						    });
							
							function format (d) {
								 var k = d;
							    var quesHtm = '';
							    
							    if(data[k].QUESTION.length == 0){
							    	
							    	return "<div class='col-xl-12 col-md-12 col-sm-12 alert alert-danger' style='text-align:center;'>This question group is empty.. Please add qustion</div>";
							    	
							    }else{
							    	 quesHtm += '<table cellpadding="5" cellspacing="0" border="0" class="ExpandTables">'
										    
								    	 quesHtm += '<thead><th>Sr. No</th>'
								    		 + '<th>Question</th>'
								    		 + '<th>Time(seconds)</th>'
								    		 + '<th>Question Type</th></thead>'
								    for(var m = 0; m < data[k].QUESTION.length; m++){
								    	quesHtm += 	'<tbody><tr>'
								    		 +'<td>Q'+(parseInt(k)+1)+'.'+''+(m+1)+'</td>'
									         +'<td>'+data[k].QUESTION[m].QTXT+'</td>'
									         
									         +'<td>'+data[k].QUESTION[m].QTIM+'</td>'
									         +'<td>'+data[k].QUESTION[m].QTYP+'</td>'
								    		 +'</tr></tbody>'
								    }
								        
								    quesHtm +=  '</table>';
							    }
							        
							        return quesHtm;
							        
							}
							   $('#QGData tbody').on('click', 'td.details-control', function () {
							        var tr = $(this).closest('tr');
							        var row = table.row( tr );
							 
							        if ( row.child.isShown() ) {
							            // This row is already open - close it
							            row.child.hide();
							            tr.removeClass('shown');
							        }
							        else {
							            // Open this row
							        	
							            row.child( format(row.id()) ).show();
							            tr.addClass('shown');
							            MJ.renderMathJax();
							        }
							    } );
						
						});
						
						
						$('#showOnlyQuestions').on('click',function () {
							
							QB.getQuestionBankOfUser('Archived');
						});
				   });
				   
				   MJ.renderMathJax();
				  
				   $("div.dataTables_wrapper div.dataTables_filter input").removeClass("form-control-sm");
					$("div.dataTables_wrapper div.dataTables_filter label").addClass("col-md-12 col-xl-12");
					$("div.dataTables_wrapper div.dataTables_filter input").addClass("col-md-12 col-xl-12");
					// $("#Loading").css("display","none");
					 setTimeout(function(){ $("#Loading").css("display","none"); }, 2000);
			});
		 
		
		modifyQuesGroup = function(QGID, backStatus) {
			modifyQuw = true;
			AP.QGMLID = null;			
			$.ajax({
				type : "GET",
				url : AP.baseURL
				+ "questionGroups/",
				data : "QGID=" + QGID,
				dataType : 'json',
				contentType : 'application/json',
				success : function(data) {
					if (data.done == false) {
						showToast.show(data.msg, false);
					} else {
						if (data.done == true) {
							modifyQuw = true; 
							
							questionGruopJSON = data.data;
							AP.QGMLID = data.data.quesGroupMediaLinks.quesGroupMediaLinkId;
							
							if(data.data.quesGroupMediaLinks.quesUsage == "question"){
								AP.mediaURL = data.data.quesGroupMediaLinks.mediaURLText;	
							}
													
							AP.modifyQuestionGroup(QGID, data.data, AP.mainData, backStatus);
							
						} else {
							showToast.show('', false);
						}
					}
				},
				error : function() {
				}
			});
		}
		
		

	}
	
	
	/// This function is for Admin to view all the questions from database
	
	QB.getAllQuestionsFromQuesBank = function(status) {
		
		var loader = '<div class="overlay" id="Loading">'
			 + '<div class="overlay__inner">'
			 + '<div class="overlay__content"><span class="spinner"></span><br/><span class="loading">LOADING....</span></div>'
			 + '</div>'
			 + '</div>'
			 
			 $("#main-div").html(loader);

		// ajax call to get question groups for user
		
		QB.getTopicDetailsToGetQuesBank();
		
		$.ajax({
			type : "GET",
			url : AP.baseURL
					+ "questionGroups/api/get/all/questGroups/"+status,
			// data : JSON.stringify(L1Json),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if(data.done == true){
					if (status == "Active") {
						modifyQuw = true;
						QB.renderActiveQuestionGroup(data.data);
						
					}else{
						modifyQuw = true;
						QB.renderarchivedQuestionGroup(data.data);
					}
					
				}else{
					$("#main-div").html("<div class='col-xl-12 col-md-12 col-sm-12 alert alert-danger' style='text-align:center;'>No questions in the database to display....</div>");
				}
			},
			error : function() {
			}

		});
		
	}
	
	
	
	
	// active question groups
	QB.renderActiveQuestionGroup = function(data) {
		
		 var quesHtm = '';
		 quesHtm += '<div class="overlay" id="Loading">'
		 + '<div class="overlay__inner">'
		 + '<div class="overlay__content"><span class="spinner"></span><br/><span class="loading">LOADING....</span></div>'
		 + '</div>'
		 + '</div>'
		 quesHtm += '<button id="btn-show-all-children" type="button" class="btn btn-dark">Expand All</button>'
			 +'<button id="showActiveQuesGroups" type="button" class="btn btn-dark">Show Question Groups</button>'
		+'<hr>'
		 
		 quesHtm += '<h1>ACTIVE QUESTION DETAILS</h1>'
			
				+ '<div class="table-responsive">'
				+ '<table id="QuestionData" class="table table-striped table-bordered" style="width:100%">'
				+ '<thead>' + ' <tr>'
				+ '<th width="5%" > </th>'
				+ '<th width="5%" >Sr. No. </th>'
				+ '     <th width="5%">Question Type</th>'
				+ '     <th width="30%">Question </th>'
				+ '      <th width="5%">Time (Seconds)</th>'
				+ '      <th width="10%">Answer Type</th>'
				+ '      <th width="25%">Topics</th>'
				+ '      <th width="5%" title="Variation No">Var No</th>'
				+ '      <th width="10%">Contributor</th>'
				+'	<th width="5%">Action</th>' 
				+ '   </tr>'
				+ '</thead>'
				+ ' <tbody>'
		var srNo = 0;		
		for (var i = 0; i < data.length; i++) {
			var topicName = "";
			for (var q = 0; q < data[i].QGTOPICS.length; q++) {
				topicName += data[i].QGTOPICS[q].TNO+" - "+data[i].QGTOPICS[q].TNM +" ("+data[i].QGTOPICS[q].TNM1+") "+",<br>";
			}
			
			for (var j = 0; j < data[i].QUESTION.length; j++) {
				quesHtm += '<tr  id="'+i+'-'+j+'" >' 
				+'<td  width="5%" class="details-control"  ></td>'
				if(data[i].QUESTION.length > 1){
					quesHtm += '<td width="5%">Q' + (srNo + 1)+'.'+(j+1)
				}else{
					quesHtm += '<td width="5%">Q' + (srNo + 1)
				}
				
				quesHtm += '</td>'
				+ '<td width="5%">' + data[i].QGTYP
				+ '</td>'
				+ '<td width="30%"  id="MathPreview">' + data[i].QUESTION[j].QTXT
				+ '</td>'
				+ '<td width="5%">' + data[i].QUESTION[j].QTIM
				+ '</td>' 
				+ '<td width="10%">' + data[i].QUESTION[j].QTYP
				+ '</td>' 
				
				+'<td width="25%">' + topicName + '</td>' 
				+'<td width="5%">' + data[i].varNo + '</td>' 
				+'<td width="10%">' + data[i].CBY + '</td>' 
				+'<td width="5%">'
				+'<span onclick = "modifyQuesGroup('+  data[i].QGID +',\'Active-All\')"  title="MODIFY QUESTION" class="btn btn-success AcT_ArchIcon"><i class="fa fa-pencil-square-o " aria-hidden="true"></i></span> '
							+ '<span  onClick="javascript:com.coep.test.AlertMessage.confirmationToArchiveOrActive('+data[i].QGID +',\'archive\')" data-toggle="modal" data-target="#AlertMesConfirm" title="Archive Question" class="btn btn-danger AcT_ArchIcon "><i class="fa fa-archive " aria-hidden="true"></i><a data-toggle="modal" data-target="#archiveModel"></a><div id="delete-pre"></div></span>' 
							+ '</td>' + '</tr>'
			}
			if( data[i].QUESTION.length != 0){
				srNo++;
			}
		}
		 quesHtm += '    </tbody>'

		+ '</table>' + '</div>'
		$("#main-div").html(quesHtm);
		 
			$("#main-div").ready(function() {
				var table = $('#QuestionData').DataTable({
					"pageLength" : 50,
					// lengthChange: false,
					buttons : [ 'copy', 'excel', 'pdf' ],
				     drawCallback: function(){
				          $('.paginate_button.page-item:not(.disabled)', this.api().table().container())          
				             .on('click', function(){
				            	 com.coep.test.mathJax.renderMathJax();
				             });       
				       },
				    	"ordering": false,
				}).on('search.dt', function() {
					 MJ.renderMathJax();
				});
				
				 	$('#btn-show-all-children').on('click', function(){
				        
				 		// Expand row details
				 		if($(this).text() == "Expand All"){
				 			$(this).text("Collapse All");
				 		}else{
				 			$(this).text("Expand All");
				 		}
				 		
				 		
				        table.rows(':not(.parent)').nodes().to$().find('td:first-child').trigger('click');
				    });

				
				function format (d) {
				    var k = d.split("-");
				    var l = k[0];
				    var m = k[1];
				    
				    var ansHtm = '';
				    
				    ansHtm += '<div><table cellpadding="5" cellspacing="0" border="0" class="ExpandTables">'
							    
				    	ansHtm += '<thead><th width="8%">Sr. No.</th>'
					    		 + '<th>Option</th>'
					    		 + '<th>Right Or Wrong</th>'
					    		 + '<th>Option Type</th></thead><tbody>'
					    		
					    for(var p = 0; p < data[l].QUESTION[m].answers.length; p++){
					    	ansHtm += 	'<tr>'
					    		 +'<td>'+(p+1)+'</td>'
					    		 if(data[l].QUESTION[m].answers[p].ansMedia == "TEXT"){
					    			 ansHtm +='<td  id="MathPreview">'+data[l].QUESTION[m].answers[p].content+'</td>'
					    		 }else{
					    			 ansHtm +='<td>'
					    			 +' <img src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[l].QUESTION[m].answers[p].content+'" class="optionImg" />'
					    			 +'</td>'
					    		 }
						         
						         if(data[l].QUESTION[m].answers[p].rightAnswer == true){
						        	 ansHtm += '<td> <i class="fa fa-check-circle" aria-hidden="true" style="color:green;"></i></td>'
						         }else{
						        	 ansHtm += '<td><i class="fa fa-times-circle-o" aria-hidden="true" style="color:red;"></i></td>'
						         }
						        	 
					    	 ansHtm += '<td>'+data[l].QUESTION[m].answers[p].ansMedia+'</td>'
					    		 +'</tr>'
					    }
					        
				    	ansHtm +=  '</tbody></table></div>';
				    	
				    	
				    	  ansHtm +=  '<div id="solutionDiv" class="row">'
					    		
				    			 ansHtm += '<div class="col-sm-12 col-md-6 col-lg-6 col-xl-6"><div class="heading">Solution Text</div>'
				    			 +'<div class="inners">'+data[l].QUESTION[m].QSOLTEXT + '</div></div>'
				    			 
				    			 if(data[l].QUESTION[m].QSOLTYPE == "IMAGE"){
				    				 
				    				 ansHtm += '<div class="col-sm-12 col-md-6 col-lg-6 col-xl-6" ><div class="heading">Solution Media</div>'
				    				 +'<div class="inners"><p>'
									 +' With reference to the following Image answer the question'
									 +' </p>'
				    				 +'<img src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[l].QUESTION[m].QSOLMEDIA+'"  class="popup-img" /></div>'
				    			 }else if(data[l].QUESTION[m].QSOLTYPE == "AUDIO"){
				    				 ansHtm += '<div class="col-sm-12 col-md-6 col-lg-6 col-xl-6">'
				    					 +'<div class="heading">Solution Media</div>'
				    					 +'<div class="inners"><p>'
										 +' With reference to the following clip answer the  question'
										 +' </p>'
										 +' <audio controls>'
										 +'  <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[l].QUESTION[m].QSOLMEDIA+'" type="audio/mpeg"  class="">'
										 +' Your browser does not support the audio element.'
										 +' </audio>'
										+'</div>'
				    			 }else if(data[l].QUESTION[m].QSOLTYPE == "VIDEO"){
				    				 ansHtm +='<div class="col-sm-12 col-md-6 col-lg-6 col-xl-6">'
				    					+'<div class="heading">Solution Media</div>'
				 						+ '<div class="inners"><p>'
				 						+ ' With reference to the following clip answer the question'
				 						+ ' </p>'
				 						+'  <video controls s >'
				 						+ '            <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[l].QUESTION[m].QSOLMEDIA+'" type="video/mp4"  class"">'
				 						+ ' <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[l].QUESTION[m].QSOLMEDIA+'" type="video/ogg">'
				 						+ '            Your browser does not support the video tag.'
				 						+ '         </video>' 
				 						+'</div>'
				    			 }
				    		+'</div>' 
				    		+'</div>'
				        return ansHtm;
				        
				}
				
				
				   $('#QuestionData tbody').on('click', 'td.details-control', function () {
				        var tr = $(this).closest('tr');
				        var row = table.row( tr );
				 
				        if ( row.child.isShown() ) {
				            // This row is already open - close it
				            row.child.hide();
				            tr.removeClass('shown');
				        }
				        else {
				            // Open this row
				        	
				            row.child( format(row.id()) ).show();
				            tr.addClass('shown');
				            MJ.renderMathJax();
				        }
				    } );
				   
				   
				   $('#showActiveQuesGroups').on('click',  function () {
					   
					   var renderQGHtm = '';

						renderQGHtm += '<button id="btn-show-all-children" type="button" class="btn btn-dark">Expand All</button>'
							+'<button id="showOnlyQuestions" type="button" class="btn btn-dark">Show Questions</button>'
							+'<hr>'
							
						renderQGHtm += '<h1>QUESTION GROUP DETAILS</h1>'
								+ '<div class="table-responsive">'
								+ '<table id="QGData" class="table table-striped table-bordered" style="width:100%">'
								+ '<thead>' + ' <tr>'
								+ '<th width="10%" > </th>' 	
								+ '     <th width="10%">Question Group</th>'
								+ '      <th width="10%">Question Definition</th>'
								+ '      <th width="55%">Topics</th>'
								+ '      <th width="10%">Created By</th>'
								+ '		<th width="5%">Action</th>'
								+ '   </tr>' + '</thead>'
								+ ' <tbody>'
						for (var i = 0; i < data.length; i++) {
							renderQGHtm +=  '<tr  id="'+i+'" >' 
								+'<td  width="10%" class="details-control"  ></td>'
									+ '<td width="10%">' + data[i].QGTYP
									+ '</td>' + '<td width="10%">' 
									if(data[i].NME  != undefined){
										renderQGHtm += ''+data[i].NME +'<br>'
									}else{
										renderQGHtm += 'No Name <br>' 
									}
									
									renderQGHtm +=  data[i].QUESTION.length + ' Questions'
									var topicName = "";
									for (var j = 0; j < data[i].QGTOPICS.length; j++) {
										
											topicName += data[i].QGTOPICS[j].TNO+" - "+data[i].QGTOPICS[j].TNM +",<br>";
									}
									renderQGHtm += '</td>' + '<td width="55%">' + topicName
									renderQGHtm += '</td>' + '<td width="10%">' + data[i].CBY
									renderQGHtm += '</td>' + '<td width="5%">'
									+'<span onclick = "modifyQuesGroup('+  data[i].QGID +',\'Active-All\')"  title="MODIFY QUESTION" class="btn btn-success AcT_ArchIcon"><i class="fa fa-pencil-square-o " aria-hidden="true"></i></span> '
									+ '<span onClick="javascript:com.coep.test.AlertMessage.confirmationToArchiveOrActive('+data[i].QGID +',\'archive\')" data-toggle="modal" data-target="#AlertMesConfirm"   title="Archive Question" class="btn btn-danger AcT_ArchIcon"><i class="fa fa-archive " aria-hidden="true"></i></span>' 
									+ '</td>' + '</tr>'
						}
						renderQGHtm += '    </tbody>'

						+ '</table>' + '</div>'
						$("#main-div").html(renderQGHtm);
						$("#main-div").ready(function() {
							var table = $('#QGData').DataTable({
								"pageLength" : 50,
								// lengthChange: false,
								buttons : [ 'copy', 'excel', 'pdf' ],
							     drawCallback: function(){
							          $('.paginate_button.page-item:not(.disabled)', this.api().table().container())          
							             .on('click', function(){
							            	 com.coep.test.mathJax.renderMathJax();
							             });       
							       },
							    	"ordering": false,
							}).on('search.dt', function() {
								 MJ.renderMathJax();
							});

							$('#btn-show-all-children').on('click', function(){
						        
						 		// Expand row details
						 		if($(this).text() == "Expand All"){
						 			$(this).text("Collapse All");
						 		}else{
						 			$(this).text("Expand All");
						 		}
						 		
						 		
						        table.rows(':not(.parent)').nodes().to$().find('td:first-child').trigger('click');
						    });
							
							function format (d) {
								 var k = d;
							    var quesHtm = '';
							    
							    if(data[k].QUESTION.length == 0){
							    	
							    	return "<div class='col-xl-12 col-md-12 col-sm-12 alert alert-danger' style='text-align:center;'>This question group is empty.. Please add qustion</div>";
							    	
							    }else{
							    	 quesHtm += '<table cellpadding="5" cellspacing="0" border="0" class="ExpandTables">'
										    
								    	 quesHtm += '<thead><th>Sr. No</th>'
								    		 + '<th>Question</th>'
								    		 + '<th>Time(seconds)</th>'
								    		 + '<th>Question Type</th></thead>'
								    for(var m = 0; m < data[k].QUESTION.length; m++){
								    	quesHtm += 	'<tbody><tr>'
								    		 +'<td>Q'+(parseInt(k)+1)+'.'+''+(m+1)+'</td>'
									         +'<td>'+data[k].QUESTION[m].QTXT+'</td>'
									         
									         +'<td>'+data[k].QUESTION[m].QTIM+'</td>'
									         +'<td>'+data[k].QUESTION[m].QTYP+'</td>'
								    		 +'</tr></tbody>'
								    }
								        
								    quesHtm +=  '</table>';
							    }
							   
							        
							        return quesHtm;
							        
							}
							   $('#QGData tbody').on('click', 'td.details-control', function () {
							        var tr = $(this).closest('tr');
							        var row = table.row( tr );
							 
							        if ( row.child.isShown() ) {
							            // This row is already open - close it
							            row.child.hide();
							            tr.removeClass('shown');
							        }
							        else {
							            // Open this row
							        	
							            row.child( format(row.id()) ).show();
							            tr.addClass('shown');
							            MJ.renderMathJax();
							        }
							    } );

						});
					
						$('#showOnlyQuestions').on('click',function () {
							QB.getQuestionBankOfUser('Active');
						});
				   });
				   
				   MJ.renderMathJax();
				   $("div.dataTables_wrapper div.dataTables_filter input").removeClass("form-control-sm");
					$("div.dataTables_wrapper div.dataTables_filter label").addClass("col-md-12 col-xl-12");
					$("div.dataTables_wrapper div.dataTables_filter input").addClass("col-md-12 col-xl-12");
				   //$("#Loading").css("display","none");
					 setTimeout(function(){ $("#Loading").css("display","none"); }, 2000);
			});
		 
		
		modifyQuesGroup = function(QGID, backStatus) {
			modifyQuw = true;
			AP.QGMLID = null;			
			$.ajax({
				type : "GET",
				url : AP.baseURL
				+ "questionGroups/",
				data : "QGID=" + QGID,
				dataType : 'json',
				contentType : 'application/json',
				success : function(data) {
					if (data.done == false) {
						showToast.show(data.msg, false);
					} else {
						if (data.done == true) {
							modifyQuw = true;
							questionGruopJSON = data.data;
							AP.QGMLID = data.data.quesGroupMediaLinks.quesGroupMediaLinkId;
							
							if(data.data.quesGroupMediaLinks.quesUsage == "question"){
								AP.mediaURL = data.data.quesGroupMediaLinks.mediaURLText;	
							}
													
							AP.modifyQuestionGroup(QGID, data.data, AP.mainData, backStatus);
						} else {
							showToast.show('', false);
						}
					}
				},
				error : function() {
				}
			});
		}
		
		

	}
	
	// archived question groups
	QB.renderarchivedQuestionGroup = function(data) {
		
		 var quesHtm = '';
		 quesHtm += '<div class="overlay" id="Loading">'
		 + '<div class="overlay__inner">'
		 + '<div class="overlay__content"><span class="spinner"></span><br/><span class="loading">LOADING....</span></div>'
		 + '</div>'
		 + '</div>'
		 quesHtm += '<button id="btn-show-all-children" type="button" class="btn btn-dark">Expand All</button>'
			 +'<button id="showArchiveQuesGroups" type="button" class="btn btn-dark">Show Question Groups</button>'
		+'<hr>'
		 
		 quesHtm += '<h1>ARCHIVE QUESTION  DETAILS</h1>'
			
				+ '<div class="table-responsive">'
				+ '<table id="QuestionData" class="table table-striped table-bordered" style="width:100%">'
				+ '<thead>' + ' <tr>'
				+ '<th width="5%" > </th>'
				+ '<th width="5%" >Sr. No. </th>'
				+ '     <th width="5%">Question Type</th>'
				+ '     <th width="30%">Question </th>'
				+ '      <th width="5%">Time (Seconds)</th>'
				+ '      <th width="20%">Question Type</th>'
				+ '      <th width="20%">Topics</th>'
				+ '      <th width="5%" title="Variation Number" >Var No</th>'
				+ '      <th width="10%">Contributor</th>'
				+'	<th width="5%">Action</th>' 
				+ '   </tr>'
				+ '</thead>'
				+ ' <tbody>'
		var srNo = 0;
		for (var i = 0; i < data.length; i++) {
			var topicName = "";
			for (var q = 0; q < data[i].QGTOPICS.length; q++) {
				topicName += data[i].QGTOPICS[q].TNO+" - "+data[i].QGTOPICS[q].TNM +" ("+data[i].QGTOPICS[q].TNM1+") "+",<br>";
			}
		
			for (var j = 0; j < data[i].QUESTION.length; j++) {
				quesHtm += '<tr  id="'+i+'-'+j+'" >' 
				+'<td  width="5%" class="details-control"  ></td>'
				if(data[i].QUESTION.length > 1){
					quesHtm += '<td width="5%">Q' + (srNo + 1)+'.'+(j+1)
				}else{
					quesHtm += '<td width="5%">Q' + (srNo + 1)
				}
				
				quesHtm += '</td>'
				+ '<td width="5%">' + data[i].QGTYP
				+ '</td>'
				+ '<td width="30%" id="MathPreview">' + data[i].QUESTION[j].QTXT
				+ '</td>'
				+ '<td width="5%">' + data[i].QUESTION[j].QTIM
				+ '</td>' 
				+ '<td width="20%">' + data[i].QUESTION[j].QTYP
				+ '</td>' 
				
				+'<td width="20%">' + topicName + '</td>' 
				+'<td width="5%">' + data[i].varNo + '</td>' 
				+'<td width="10%">' + data[i].CBY + '</td>' 
				+'<td width="5%">'
				+'<span onclick = "modifyQuesGroup('+  data[i].QGID +',\'Archived-All\')"  title="MODIFY QUESTION" class="btn btn-success AcT_ArchIcon"><i class="fa fa-pencil-square-o " aria-hidden="true"></i></span> '
							+ '<span  onClick="javascript:com.coep.test.AlertMessage.confirmationToArchiveOrActive('+data[i].QGID +',\'active\')" data-toggle="modal" data-target="#AlertMesConfirm" title="Active Question" class="btn btn-success AcT_ArchIcon"><i class="fa fa-undo" aria-hidden="true" ></i><a data-toggle="modal" data-target="#archiveModel"></a><div id="delete-pre"></div></span>' 
							+ '</td>' + '</tr>'
			}
			
			if( data[i].QUESTION.length != 0){
				srNo++;
			}
		}
		 quesHtm += '    </tbody>'

		+ '</table>' + '</div>'
		$("#main-div").html(quesHtm);
		 
			$("#main-div").ready(function() {
				var table = $('#QuestionData').DataTable({
					"pageLength" : 50,
					// lengthChange: false,
					buttons : [ 'copy', 'excel', 'pdf' ],
				     drawCallback: function(){
				          $('.paginate_button.page-item:not(.disabled)', this.api().table().container())          
				             .on('click', function(){
				            	 com.coep.test.mathJax.renderMathJax();
				             });       
				       },
				    	"ordering": false,
				}).on('search.dt', function() {
					 MJ.renderMathJax();
				});
				
				 	$('#btn-show-all-children').on('click', function(){
				        
				 		// Expand row details
				 		if($(this).text() == "Expand All"){
				 			$(this).text("Collapse All");
				 		}else{
				 			$(this).text("Expand All");
				 		}
				 		
				 		
				        table.rows(':not(.parent)').nodes().to$().find('td:first-child').trigger('click');
				    });

				
				function format (d) {
				    var k = d.split("-");
				    var l = k[0];
				    var m = k[1];
				    
				    var ansHtm = '';
				    
				    ansHtm += '<div><table cellpadding="5" cellspacing="0" border="0" class="ExpandTables">'
							    
				    	ansHtm += '<thead><th width="8%">Sr. No.</th>'
					    		 + '<th>Option</th>'
					    		 + '<th>Right Or Wrong</th>'
					    		 + '<th>Option Type</th></thead><tbody>'
					    		
					    for(var p = 0; p < data[l].QUESTION[m].answers.length; p++){
					    	ansHtm += 	'<tr>'
					    		 +'<td>'+(p+1)+'</td>'
					    		 if(data[l].QUESTION[m].answers[p].ansMedia == "TEXT"){
					    			 ansHtm +='<td id="MathPreview">'+data[l].QUESTION[m].answers[p].content+'</td>'
					    		 }else{
					    			 ansHtm +='<td>'
					    			 +' <img src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[l].QUESTION[m].answers[p].content+'" class="optionImg" />'
					    			 +'</td>'
					    		 }
						         
						         if(data[l].QUESTION[m].answers[p].rightAnswer == true){
						        	 ansHtm += '<td> <i class="fa fa-check-circle" aria-hidden="true" style="color:green;"></i></td>'
						         }else{
						        	 ansHtm += '<td><i class="fa fa-times-circle-o" aria-hidden="true" style="color:red;"></i></td>'
						         }
						        	 
					    	 ansHtm += '<td>'+data[l].QUESTION[m].answers[p].ansMedia+'</td>'
					    		 +'</tr>'
					    }
					        
				    ansHtm +=  '</tbody></table></div>';
				    
				    ansHtm +=  '<div id="solutionDiv" class="row">'
			    		
		    			 ansHtm += '<div class="col-sm-12 col-md-6 col-lg-6 col-xl-6"><div class="heading">Solution Text</div>'
		    			 +'<div class="inners">'+data[l].QUESTION[m].QSOLTEXT + '</div></div>'
		    			 
		    			 if(data[l].QUESTION[m].QSOLTYPE == "IMAGE"){
		    				 
		    				 ansHtm += '<div class="col-sm-12 col-md-6 col-lg-6 col-xl-6" ><div class="heading">Solution Media</div>'
		    				 +'<div class="inners"><p>'
							 +' With reference to the following Image answer the question'
							 +' </p>'
		    				 +'<img src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[l].QUESTION[m].QSOLMEDIA+'"  class="popup-img" /></div>'
		    			 }else if(data[l].QUESTION[m].QSOLTYPE == "AUDIO"){
		    				 ansHtm += '<div class="col-sm-12 col-md-6 col-lg-6 col-xl-6">'
		    					 +'<div class="heading">Solution Media</div>'
		    					 +'<div class="inners"><p>'
								 +' With reference to the following clip answer the  question'
								 +' </p>'
								 +' <audio controls>'
								 +'  <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[l].QUESTION[m].QSOLMEDIA+'" type="audio/mpeg"  class="">'
								 +' Your browser does not support the audio element.'
								 +' </audio>'
								+'</div>'
		    			 }else if(data[l].QUESTION[m].QSOLTYPE == "VIDEO"){
		    				 ansHtm +='<div class="col-sm-12 col-md-6 col-lg-6 col-xl-6">'
		    					+'<div class="heading">Solution Media</div>'
		 						+ '<div class="inners"><p>'
		 						+ ' With reference to the following clip answer the question'
		 						+ ' </p>'
		 						+'  <video controls s >'
		 						+ '            <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[l].QUESTION[m].QSOLMEDIA+'" type="video/mp4"  class"">'
		 						+ ' <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[l].QUESTION[m].QSOLMEDIA+'" type="video/ogg">'
		 						+ '            Your browser does not support the video tag.'
		 						+ '         </video>' 
		 						+'</div>'
		    			 }
		    		+'</div>' 
		    		+'</div>'
				        return ansHtm;
				}
				
				
				   $('#QuestionData tbody').on('click', 'td.details-control', function () {
				        var tr = $(this).closest('tr');
				        var row = table.row( tr );
				 
				        if ( row.child.isShown() ) {
				            // This row is already open - close it
				            row.child.hide();
				            tr.removeClass('shown');
				        }
				        else {
				            // Open this row
				        	
				            row.child( format(row.id()) ).show();
				            tr.addClass('shown');
				            MJ.renderMathJax();
				        }
//				        $("#Loading").css("display","none");
				        setTimeout(function(){ $("#Loading").css("display","none"); }, 2000);
				    } );
				   
				   
				   $('#showArchiveQuesGroups').on('click',  function () {
					  
					   var renderQGHtm = '';

						renderQGHtm += '<button id="btn-show-all-children" type="button" class="btn btn-dark">Expand All</button>'
							+'<button id="showOnlyQuestions" type="button" class="btn btn-dark">Show Questions</button>'
							+'<hr>'
							
						renderQGHtm += '<h1>QUESTION GROUP DETAILS</h1>'
								+ '<div class="table-responsive">'
								+ '<table id="QGData" class="table table-striped table-bordered" style="width:100%">'
								+ '<thead>' + ' <tr>'
								+ '<th width="10%" > </th>' 	
								+ '     <th width="10%">Question Group</th>'
								+ '      <th width="10%">Question Definition</th>'
								+ '      <th width="55%">Topics</th>'
								+ '      <th width="10%">Created By</th>'
								+'		<th width="5%">Action</th>'
								+ '   </tr>' + '</thead>'
								+ ' <tbody>'
						for (var i = 0; i < data.length; i++) {
							renderQGHtm +=  '<tr  id="'+i+'" >' 
								 	+'<td  width="10%" class="details-control"  ></td>'
									+ '<td width="10%">' + data[i].QGTYP
									+ '</td>' + '<td width="10%">' 
									if(data[i].NME  != undefined){
										renderQGHtm += ''+data[i].NME +'<br>'
									}else{
										renderQGHtm += 'No Name <br>' 
									}
									
									renderQGHtm +=  data[i].QUESTION.length + ' Questions'
									var topicName = "";
									for (var j = 0; j < data[i].QGTOPICS.length; j++) {
										
											topicName += data[i].QGTOPICS[j].TNO+" - "+data[i].QGTOPICS[j].TNM +",<br>";
									}
									renderQGHtm += '</td>' + '<td width="55%">' + topicName
									renderQGHtm += '</td>' + '<td width="10%">' + data[i].CBY
									renderQGHtm += '</td>' + '<td width="5%">'
									+'<span onclick = "modifyQuesGroup('+  data[i].QGID +',\'Archived-All\')"  title="MODIFY QUESTION" class="btn btn-success AcT_ArchIcon"><i class="fa fa-pencil-square-o " aria-hidden="true"></i></span> '
									+ '<span onClick="javascript:com.coep.test.AlertMessage.confirmationToArchiveOrActive('+data[i].QGID +',\'active\')" data-toggle="modal" data-target="#AlertMesConfirm"   title="Active Question" class="btn btn-success AcT_ArchIcon"><i class="fa fa-undo" aria-hidden="true" ></i></span>' 
									+ '<span onClick="javascript:com.coep.test.AlertMessage.confirmationToDeleteQues('+data[i].QGID +',\'delete\')" data-toggle="modal" data-target="#AlertMesConfirm"   title="Delete Question" class="btn btn-danger AcT_ArchIcon"><i class="fa fa fa-trash" aria-hidden="true" ></i></span>' 
									+ '</td>' + '</tr>'
						}
						renderQGHtm += '    </tbody>'

						+ '</table>' + '</div>'
						$("#main-div").html(renderQGHtm);
						$("#main-div").ready(function() {
							var table = $('#QGData').DataTable({
								"pageLength" : 50,
								// lengthChange: false,
								buttons : [ 'copy', 'excel', 'pdf' ],
							     drawCallback: function(){
							          $('.paginate_button.page-item:not(.disabled)', this.api().table().container())          
							             .on('click', function(){
							            	 com.coep.test.mathJax.renderMathJax();
							             });       
							       },
							    	"ordering": false,
							}).on('search.dt', function() {
								 MJ.renderMathJax();
							});
							
							
							$('#btn-show-all-children').on('click', function(){
						        
						 		// Expand row details
						 		if($(this).text() == "Expand All"){
						 			$(this).text("Collapse All");
						 		}else{
						 			$(this).text("Expand All");
						 		}
						 		
						 		
						        table.rows(':not(.parent)').nodes().to$().find('td:first-child').trigger('click');
						    });
							
							function format (d) {
								 var k = d;
							    var quesHtm = '';
							    
							    if(data[k].QUESTION.length == 0){
							    	
							    	return "<div class='col-xl-12 col-md-12 col-sm-12 alert alert-danger' style='text-align:center;'>This question group is empty.. Please add qustion</div>";
							    	
							    }else{
							    	
							    	 quesHtm += '<table cellpadding="5" cellspacing="0" border="0" class="ExpandTables">'
										    
								    	 quesHtm += '<thead><th>Sr. No</th>'
								    		 + '<th>Question</th>'
								    		 + '<th>Time(seconds)</th>'
								    		 + '<th>Question Type</th></thead>'
								    for(var m = 0; m < data[k].QUESTION.length; m++){
								    	quesHtm += 	'<tbody><tr>'
								    		 +'<td>Q'+(parseInt(k)+1)+'.'+''+(m+1)+'</td>'
									         +'<td>'+data[k].QUESTION[m].QTXT+'</td>'
									         
									         +'<td>'+data[k].QUESTION[m].QTIM+'</td>'
									         +'<td>'+data[k].QUESTION[m].QTYP+'</td>'
								    		 +'</tr></tbody>'
								    }
								        
								    quesHtm +=  '</table>';
							    }
							        
							        return quesHtm;
							}
							   $('#QGData tbody').on('click', 'td.details-control', function () {
							        var tr = $(this).closest('tr');
							        var row = table.row( tr );
							 
							        if ( row.child.isShown() ) {
							            // This row is already open - close it
							            row.child.hide();
							            tr.removeClass('shown');
							        }
							        else {
							            // Open this row
							        	
							            row.child( format(row.id()) ).show();
							            tr.addClass('shown');
							            MJ.renderMathJax();
							        }
							    } );

						});
						
						 $('#showOnlyQuestions').on('click',function () {
							   QB.getQuestionBankOfUser('Archived');
							 
						 });
				   });
				   
				   MJ.renderMathJax();
				   $("div.dataTables_wrapper div.dataTables_filter input").removeClass("form-control-sm");
					$("div.dataTables_wrapper div.dataTables_filter label").addClass("col-md-12 col-xl-12");
					$("div.dataTables_wrapper div.dataTables_filter input").addClass("col-md-12 col-xl-12");
				  // $("#Loading").css("display","none");
					 setTimeout(function(){ $("#Loading").css("display","none"); }, 2000);
			});
		 
		
		modifyQuesGroup = function(QGID, backStatus) {
			modifyQuw = true;
			AP.QGMLID = null;			
			$.ajax({
				type : "GET",
				url : AP.baseURL
				+ "questionGroups/",
				data : "QGID=" + QGID,
				dataType : 'json',
				contentType : 'application/json',
				success : function(data) {
					if (data.done == false) {
						showToast.show(data.msg, false);
					} else {
						if (data.done == true) {
							modifyQuw = true;
							questionGruopJSON = data.data;
							AP.QGMLID = data.data.quesGroupMediaLinks.quesGroupMediaLinkId;
							
							if(data.data.quesGroupMediaLinks.quesUsage == "question"){
								AP.mediaURL = data.data.quesGroupMediaLinks.mediaURLText;	
							}
													
							AP.modifyQuestionGroup(QGID, data.data, AP.mainData, backStatus);
						} else {
							showToast.show('', false);
						}
					}
				},
				error : function() {
				}
			});
		}
		
	}
	
	/// following code will be use for moderator to approve questions
	QB.topicList= {};
	QB.getTopicForQuestionsToApprove = function(status) {

		$.ajax({
			type: "GET",
			url: com.coep.test.addProblem.baseURL
				+ "topic/api/get/main/topics",
			dataType: 'json',
			contentType: 'application/json',
			success: function(data) {
				QB.topicList = data;
				QB.getQuestionsToApprove(status);

			},
			error: function() {
			}

		});
	}
	
	QB.getQuestionsToApprove = function(status, quesID, pageIndexNo, selectedTopicId) {
//		QB.getQuestionsToApprove = function(status, quesID, pageIndexNo) { //  before adding selectedTopicId 
		// ajax call to get question groups for user
		
//		var topicDetails = {};
		
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
			
					var name = "";
					if (status == "Non-Approved") {
						name = "NON-MODERATED"
					}else{
						name = "MODERATED"
					}
			
			tpList = {};
			tpList = QB.topicList;
			
			if (status == "Approved") {
				
//				jQuery.isEmptyObject(anyObjectIncludingJSON)
//				if (jQuery.isEmptyObject(QB.topicDataForApprovedQues)) {
	
					var	html1 = '<h1>'+ name + ' QUESTION DETAILS</h1>' 
					 	+'<div class="form-group step1 col-sm-12 col-md-12 col-lg-12 col-xl-12" >'
						+ '<label class="col-xl-12 col-md-12 col-sm-12" for="selectTopic">Select Vertical <span class="marathi-text" >(श्रेणी निवडा)</span><i class="fa fa-asterisk " aria-hidden="true"></i></label>'
						+ ' <select class=" form-control col-xl-12 col-md-12 col-sm-12 selectpicker" data-live-search="true"  id="selTopicForFilter11"   >'
						html1 += '' + '  <option value="" id ="0">Select topic</option>'
						for (var i = 0; i <tpList.topicData.length; i++) {
							html1 += '' + '  <option value="' + tpList.topicData[i].TNO
									+ '" id ="' + tpList.topicData[i].TID + '" >'
									+ tpList.topicData[i].TNO +" - "+ tpList.topicData[i].TN +"&nbsp;("+ tpList.topicData[i].TN1 + ') </option>'
						}

					html1 += ' </select>'
								+ '<div id="error" class="invalid-feedback selectTopic">Please select topic.</div>'
								+ '</div>'	
								+'<div class="form-group step1" id="variationDiv"></div>'	

								
					$("#main-div").html(html1);
					$('select').selectpicker();
			
//				}else{
//					getTopicListDetails(QB.topicDataForApprovedQues, selectedTopicId);
//				}
				
				
			}else{
				var	html1 = '<h1>'+ name + ' QUESTION DETAILS</h1>' 
					 	+'<div class="form-group step1 col-sm-12 col-md-12 col-lg-12 col-xl-12" >'
						+ '<label class="col-xl-12 col-md-12 col-sm-12" for="selectTopic">Select Topic <span class="marathi-text" >(श्रेणी निवडा)</span><i class="fa fa-asterisk " aria-hidden="true"></i></label>'
						+ ' <select class=" form-control col-xl-12 col-md-12 col-sm-12 selectpicker" data-live-search="true"  id="selTopicForFilter11"   >'
						html1 += '' + '  <option value="" id ="0">Select topic</option>'
						for (var i = 0; i <tpList.topicData.length; i++) {
							html1 += '' + '  <option value="' + tpList.topicData[i].TNO
									+ '" id ="' + tpList.topicData[i].TID + '" >'
									+ tpList.topicData[i].TNO +" - "+ tpList.topicData[i].TN +"&nbsp;("+ tpList.topicData[i].TN1 + ') </option>'
						}

					html1 += ' </select>'
								+ '<div id="error" class="invalid-feedback selectTopic">Please select topic.</div>'
								+ '</div>'	
								+'<div class="form-group step1" id="variationDiv"></div>'	

								
					$("#main-div").html(html1);
					$('select').selectpicker();
				
				
//				$.ajax({
//					type : "GET",
//					url : com.coep.test.addProblem.baseURL
//					+ "questionGroups/api/get/topic/questGroups/"+status,
//					dataType : 'json',
//					contentType : 'application/json',
//					success : function(data) {
//						
//						if (data.done == false) {
//							$("#main-div").html("<div class='col-xl-12 col-md-12 col-sm-12 alert alert-danger' style='text-align:center;'>No "+status+" questions in the database to display..</div>");
//						}else{
//							getTopicListDetails(data, selectedTopicId);
//						}
//						
//					},
//					error : function() {
//					}
//
//				});
			}
			
				$("#selTopicForFilter11").on('change', function(){ 
						 var selected = $(this).find("option:selected").val();
						 st = status + "_" + selected;
					$.ajax({
						type : "GET",
						url : com.coep.test.addProblem.baseURL
						+ "questionGroups/api/get/topic/questGroup/"+st,
						dataType : 'json',
						contentType : 'application/json',
						success : function(data) {
							
							if (data.done == false) {
								$("#main-div").html("<div class='col-xl-12 col-md-12 col-sm-12 alert alert-danger' style='text-align:center;'>No "+status+" questions in the database to display..</div>");
							}else{
								getTopicListDetails(data, selectedTopicId);
							}
							
						},
						error : function() {
						}

					});
					})
				
				
				function getTopicListDetails(data, selectedTopicId) {
					
					var name = "";
					var html = '';
					if (status == "Non-Approved") {
						name = "NON-APPROVED"
					}else{
						name = "APPROVED"
					}
//					html +=  '<h1>'+ name + ' QUESTION DETAILS</h1>'
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
								
								
								+ '<center><button id= "assignTopicBtn" type="submit" class="btn btn-dark">Submit</button></center>'
								
								+ '<div id="ques-bank-div"></div>'
								
					$("#variationDiv").html(html);
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
							renderVarHtml += ' <select class=" form-control col-xl-12 col-md-12 col-sm-12 selectpicker" data-live-search="true"  id="diffLeveFilter" multiple >'
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
					
					$("#assignTopicBtn").on(
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
								
									console.log(""+topicID+"/"+variationNum+"/"+difficultyLevel);
									
									if (topicID == 0) {
										alert("Please select topic");
										 $("#Loading").css("display","none");
									}else{
										$.ajax({
											type : "POST",
											url : com.coep.test.addProblem.baseURL
													+ "questionGroups/api/getData/"+status+"/"+topicID+"/"+variationNum+"/"+difficultyLevel,
											dataType : 'json',
											contentType : 'application/json',
											success : function(data) {
												if(data.done == true){
													if (status == "Non-Approved") {
														modifyQuw = true;
														QB.renderNonApprovedQuestionGroup(data.data, quesID, pageIndexNo, selectedTopicId);
														
													}else{
														modifyQuw = true;
														QB.renderApprovedQuestionGroup(data.data, quesID, pageIndexNo, selectedTopicId);
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
	
	
	
	// non approved questions for moderator
	
	QB.renderNonApprovedQuestionGroup = function(data, quesID, pageIndexNo, selectedTopicId) {
		
//		console.log(data);
		var quesHtm = '';
		 
		+'<hr>'
		 quesHtm += '<div class="overlay" id="Loading">'
			 + '<div class="overlay__inner">'
			 + '<div class="overlay__content"><span class="spinner"></span><br/><span class="loading">LOADING....</span></div>'
			 + '</div>'
			 + '</div>' 
//		 quesHtm += '<h1>NON-APPROVED QUESTION DETAILS</h1>'
			 quesHtm += '<div class="table-responsive" >'
				+ '<center><div id="showSelQues" style="display: none;  color: #007bff"></div></center>' // showSelQues
				+ '<table id="nonApprovedQuestionData" class="table table-striped table-bordered" style="width:100%">'
				+ '<thead>' + ' <tr>'
				+ '<th  width="5%"  title="Select All"  style="cursor: pointer;">'
				+'<input type="checkbox"  id="checkAllNonApproved" checkId="" name="checkMultiple" class="checkboxType"  style="cursor: pointer;" /> <label for="checkAllNonApproved"  style="cursor: pointer; color: white;"  >Select all Questions.</label>'
				+' </th>'
				+ '<th width="5%" >Sr. No. </th>'
				+ '     <th width="90%">Question </th>'
				+ '   </tr>'
				+ '</thead>'
				+ ' <tbody>'

		for (var i = 0; i < data.length; i++) {
			var topicName = "";
			for (var q = 0; q < data[i].QGTOPICS.length; q++) {
				if(q == data[i].QGTOPICS.length - 1){
					topicName += data[i].QGTOPICS[q].TNO+" - "+data[i].QGTOPICS[q].TNM + " ("+data[i].QGTOPICS[q].TNM1 +")";
				}else{
					topicName += data[i].QGTOPICS[q].TNO+" - "+data[i].QGTOPICS[q].TNM + " ("+data[i].QGTOPICS[q].TNM1 +") "+",<br>";
				}
			}
			
			
				quesHtm += '<tr  >'
				+'<td   width="5%" id="checkBox'+data[i].QGID+'">'
				+'<div class="" >'
				+'<label>'
				+'<input type="checkbox"  id="checkMultiple'+(i+1)+'" checkId="'+data[i].QGID+'" name="checkMultiple" class="checkboxType" />'
				+'<i class="input-helper"></i>'
				+'</label>'
				+'</div>'
				
				+'</td>'
				quesHtm += '<td width="5%">Q' + (i + 1)

				quesHtm += '</td>'

				+ '<td width="90%">'
				+'<div class="row ApproveQueStyle" id="Q'+(i + 1)+'">' // ApproveQue main div start
				
				quesHtm += '<div class="col-xl-12 col-md-12 col-sm-12 justify-content-center" ><p class="ApproveDivLabel">Contributor - '+data[i].CBY+'</p></div>'
				
				quesHtm += '<div class="col-xl-3 col-md-3 col-sm-12 ApproveDivStyle" >'
					 +'<span><p class="ApproveDivLabel">Question Type </p>'+data[i].QGTYP+'</span>'
					 +'</div>' 
					
					 +'<div class="col-xl-6 col-md-6 col-sm-12 ApproveDivStyle">'
					 +'<span><p class="ApproveDivLabel">Assign Topics </p class="ApproveDivLabel">'+topicName+'</span>'
					 +'</div>'
					 +'<div class="col-xl-3 col-md-3 col-sm-12 ApproveDivStyle">'
					+'<p class="ApproveDivLabel">Difficulty level  </p>'
					+'Level - ' +data[i].QGLVL
					+'</div>'
					 
				 // Reference start
				 
				 if($.trim(data[i].QGTYP) == "Text" || $.trim(data[i].QGTYP) == "text"){
						
						if($.trim(data[i].QGMEDIA) == ""){
							quesHtm += ''
						}else{
							quesHtm += '<div class="col-xl-12 col-md-12 col-sm-12" ><p class="ApproveDivLabel">Reference </p>'+data[i].QGMEDIA+'</div>'
						}
						
						
					}else if($.trim(data[i].QGTYP) == "Image" || $.trim(data[i].QGTYP) == "image"){
						
						quesHtm += '<div class="col-xl-12 col-md-12 col-sm-12">'
							 +'<p class="ApproveDivLabel">'
							 +' With reference to the following Image answer the following question'
							 +' </p>'
							+'<div> <img src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QGMEDIA+'" class="ApproveQueImg" /></div></div><hr/>'
						
					}else if($.trim(data[i].QGTYP) == "Audio" || $.trim(data[i].QGTYP) == "audio"){
						
						quesHtm += '<div class="col-xl-12 col-md-12 col-sm-12">'
							 +'<p class="ApproveDivLabel">'
							 +' With reference to the following clip answer the following question'
							 +' </p>'
							 +' <audio controls class="ApproveQueAudio">'
							 +'  <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QGMEDIA+'" type="audio/mpeg" >'
							 +' Your browser does not support the audio element.'
							 +' </audio>'
							+'</div><hr/>'
							
					}else if($.trim(data[i].QGTYP) == "Video" || $.trim(data[i].QGTYP) == "video"){
						quesHtm +='<div class="col-xl-12 col-md-12 col-sm-12">'
							+ '<p class="ApproveDivLabel">'
							+ ' With reference to the following clip answer the following question'
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
					quesHtm +='<div id="Actual_que" class="col-xl-12 col-md-12 col-sm-12">' //Actual_que start
						
						quesHtm +='<div class="row"><div class="col-xl-6 col-md-6 col-sm-12 ApproveDivStyle">'
							 +'<span><p class="ApproveDivLabel">Answer Type </p>'+data[i].QUESTION[j].QTYP+'</span>'
							 +'</div>'
							 
							 + '<div class="col-xl-6 col-md-6 col-sm-12 ApproveDivStyle" ><p class="ApproveDivLabel">Time</p><i class="fa fa-clock-o" aria-hidden="true" style="color:red;"></i> '+data[i].QUESTION[j].QTIM+' Seconds</div><hr/>'
							 + '</div>'
						
						+'<div id="MathPreview" class=" actualQuesDiv" >' // actualQue start

						if(data[i].QUESTION.length > 1){
							    quesHtm += '<div class=""><p class="ApproveDivLabel">Question '+(i+1)+'.'+(j+1)+' </p><b>'+data[i].QUESTION[j].QTXT+'</b></div>'
							}else{
								quesHtm +=  '<div class=""><p class="ApproveDivLabel">Question '+(i+1)+' </p><b>'+data[i].QUESTION[j].QTXT+'</b></div>'
							}
					quesHtm +='</div>'
					
					// ans div start
					 +'<div class="row" id="actualAnsDiv">' //  actualAnsDiv start
					  for(var k = 0; k < data[i].QUESTION[j].answers.length; k++){
						  
						  if (data[i].QUESTION[j].QTYPID == 1
									|| data[i].QUESTION[j].QTYPID == 2
									|| data[i].QUESTION[j].QTYPID == 3
									|| data[i].QUESTION[j].QTYPID == 4
									|| data[i].QUESTION[j].QTYPID == 6
									|| data[i].QUESTION[j].QTYPID == 7) {
							  quesHtm += '<div class="col-xl-6 col-md-6 col-sm-12 OptDiv">'
										  if(data[i].QUESTION[j].answers[k].ansMedia == "TEXT"){
											  if(data[i].QUESTION[j].answers[k].rightAnswer == true){
												  quesHtm +='<div >'         
												  +'<div >' 
													  		+'<input type="radio"  id="textOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" name="radioOpt'+(i+1)+'-'+(j+1)+'" class="radiostyle">'
												  			+ '<label  for="textOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" style="color:green;"  id="MathPreview">'+data[i].QUESTION[j].answers[k].content+'</label>'
												  			+'</div>'
												  			+'</div>'
											  }else{
												  quesHtm +='<div >'
												        +'<div  >' 
												  		+'<input type="radio" id="textOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" name="radioOpt'+(i+1)+'-'+(j+1)+'" class="radiostyle">'
											  			+ '<label  for="textOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" style="color: #1757b8;"  id="MathPreview">'+data[i].QUESTION[j].answers[k].content+'</label>'
											  			+'</div>'
											  			+'</div>'
											  }
											
										  }else{ 
											  
											  if(data[i].QUESTION[j].answers[k].rightAnswer == true){
											  quesHtm +='<div >'
												  +'<div >' 
												  +' <input id="imgOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" type="radio" name="radioOpt'+(i+1)+'-'+(j+1)+'"  class="radiostyle" />'
												  +'<label  for="imgOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'">'
									    			 +' <img src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QUESTION[j].answers[k].content+'" class="ApproveQueImgOption" style="border: 6px solid green;" />'
									    			 +'</label>'
									    			 +'</div>'
									    			 +'</div>'
											  }else{
												  quesHtm +='<div >'
													  +'<div >' 
													  +' <input id="imgOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" type="radio" name="radioOpt'+(i+1)+'-'+(j+1)+'" class="radiostyle" />'
													  +'<label  for="imgOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'">'
										    			 +' <img src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QUESTION[j].answers[k].content+'" class="ApproveQueImgOption" style="border: 6px solid #1757b8;" />'
										    			 +'</label>'
										    			 +'</div>'
										    			 +'</div>'
											  }
										  }
										 
									  quesHtm +='</div>'
										// 
								  } else if(data[i].QUESTION[j].QTYPID == 5 || data[i].QUESTION[j].QTYPID == 8) {

									  quesHtm += '<div class="col-xl-6 col-md-6 col-sm-12 OptDiv">'
										  if(data[i].QUESTION[j].answers[k].ansMedia == "TEXT"){
											  if(data[i].QUESTION[j].answers[k].rightAnswer == true){
												  quesHtm +='<div >'    
												        +'<div >' 
												  		+'<input type="checkbox"  id="textOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" name="ckeckOpt'+(i+1)+'-'+(j+1)+'" class="radiostyle">'
											  			+ '<label " for="textOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" style="color: green;"  id="MathPreview">'+data[i].QUESTION[j].answers[k].content+'</label>'
											  			+'</div>'
											  			+'</div>'
											  }else{
												  quesHtm +='<div >'
												        +'<div >' 
												  		+'<input type="checkbox" " id="textOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" name="checkOpt'+(i+1)+'-'+(j+1)+'" class="radiostyle">'
											  			+ '<label  for="textOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" style="color: #1757b8;"  id="MathPreview">'+data[i].QUESTION[j].answers[k].content+'</label>'
											  			+'</div>'
											  			+'</div>'
											  }
											
										  }else if(data[i].QUESTION[j].answers[k].rightAnswer == true){
												  quesHtm +='<div >'
													  +'<div >'
													  +' <input id="opt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" type="checkbox" name="checkOpt'+(i+1)+'-'+(j+1)+'" class="radiostyle" />'
													  +'<label  for="opt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'">'
										    			 +' <img src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QUESTION[j].answers[k].content+'" class="ApproveQueImgOption" style="border: 6px solid green;"/>'
										    			 +'</label>'
										    			 +'</div>'
										    			 +'</div>'
												  }else{
													  quesHtm +='<div >'
														  +'<div >'
														  +' <input id="opt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" type="checkbox" name="checkOpt'+(i+1)+'-'+(j+1)+'" class="radiostyle"/>'
														  +'<label  for="opt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'">'
											    			 +' <img src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QUESTION[j].answers[k].content+'" class="ApproveQueImgOption" style="border: 6px solid #1757b8;"/>'
											    			 +'</label>'
											    			 +'</div>'
											    			 +'</div>'
												  }
											  
										 
									  quesHtm +='</div>'
								  
								  }
					  }
					quesHtm +='</div><hr/>' //  //  actualAnsDiv start
					// ans div end
					
					// solution div start
						 quesHtm +=  '<div id="solDiv" class="col-xl-12 col-md-12 col-sm-12">'
							    +'<div class="row">' //inner row start
							    if (data[i].QUESTION[j].QSOLMEDIA == undefined) {
							        quesHtm += '<div class="col-xl-12 col-md-12 col-sm-12 ApproveDivSolutionStyle"><span class=""><p class="ApproveDivLabel">Solution Text</p class="ApproveDivLabel"> </span>'
						    			 +'<div class="inners">'+data[i].QUESTION[j].QSOLTEXT + '</div></div>'
						    			 
						    			 
								}else{
								     quesHtm += '<div class="col-xl-6 col-md-6 col-sm-12 ApproveDivSolutionStyle"><span class=""><p class="ApproveDivLabel">Solution Text</p class="ApproveDivLabel"> </span>'
						    			 +'<div class="inners">'+data[i].QUESTION[j].QSOLTEXT + '</div></div>'
						    			 
						    			 if(data[i].QUESTION[j].QSOLTYPE == "IMAGE"){
						    				 
						    				 quesHtm += '<div class="col-xl-6 col-md-6 col-sm-12 ApproveDivStyle" ><span class=""><p class="ApproveDivLabel">Solution Media</p></span>'
						    				 +'<p>'
											 +' With reference to the following Image answer the question'
											 +' </p>'
						    				 +'<img src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QUESTION[j].QSOLMEDIA+'"  class="ApproveQueImg" /></div>'
						    				 
						    			 }else if(data[i].QUESTION[j].QSOLTYPE == "AUDIO"){
						    				 quesHtm += '<div class="col-xl-6 col-md-6 col-sm-12 ApproveDivStyle">'
						    					 +'<span class=""><p class="ApproveDivLabel">Solution Media</p></span>'
						    					 +'<p>'
												 +' With reference to the following clip answer the  question'
												 +' </p>'
												 +' <audio controls class="ApproveQueAudio">'
												 +'  <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QUESTION[j].QSOLMEDIA+'" type="audio/mpeg" >'
												 +' Your browser does not support the audio element.'
												 +' </audio>'
												+'</div>'
						    			 }else if(data[i].QUESTION[j].QSOLTYPE == "VIDEO"){
						    				 quesHtm +='<div class="col-xl-6 col-md-6 col-sm-12 ApproveDivStyle">'
							    					+'<span class=""><p class="ApproveDivLabel">Solution Media</p></span>'
							 						+ '<p>'
							 						+ ' With reference to the following clip answer the question'
							 						+ ' </p>'
							 						+'  <video controls  class="ApproveQueVideo" >'
							 						+ '            <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QUESTION[j].QSOLMEDIA+'" type="video/mp4" >'
							 						+ ' <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QUESTION[j].QSOLMEDIA+'" type="video/ogg">'
							 						+ '            Your browser does not support the video tag.'
							 						+ '         </video>' 
							 						+'</div>'
							    			 }
								}
								
					
					          quesHtm += '</div>'	//inner row end 
							 quesHtm +='</div>'
					// solution div end
					// variation No div start	
								 
					if (data[i].varNo != "") {
						
						 if (data[i].varNo != undefined) {
							 quesHtm += '<div class="col-xl-12 col-md-12 col-sm-12 justify-content-center" ><p class="ApproveDivLabel">Variation No - '+data[i].varNo+'</p></div>'
						 }else{
							 quesHtm += '<div class="col-xl-12 col-md-12 col-sm-12 justify-content-center" ><p class="ApproveDivLabel">Variation No - Not assigned</p></div>'
						 }

					} else {
						quesHtm += '<div class="col-xl-12 col-md-12 col-sm-12 justify-content-center" ><p class="ApproveDivLabel">Variation No - Not assigned</p></div>'
					}
				
					//	variation No div start
					
					+'<hr style="border-top: 8px solid rgba(72, 1, 1, 0.82);"></div>' //Actual_que start
					// acutal que end
					
				}	
				
						quesHtm +='<div class="col-xl-12 col-md-12 col-sm-12 justify-content-center">'
							quesHtm +=	'<button onclick = "modifyQuesGroup('+  data[i].QGID +',\'Non-Approved\', '+  (i+1) +')"  title="MODIFY QUESTION" class="btn btn-success AcT_ArchIcon float-left"><i class="fa fa-pencil-square-o " aria-hidden="true"></i>&nbsp;MODIFY</button> '
							+'<button onclick = "javascript:com.coep.test.AlertMessage.confirmationToApproveQues('+  data[i].QGID +',\'Non-Approved\',\'Approval\','+data.length+','+selectedTopicId+')" data-toggle="modal"  title="Approve Question" class="btn btn-dark confModelBtn " >APPROVE</button>'
							+'<button  onClick="javascript:com.coep.test.AlertMessage.confirmationToArchiveOrActiveNonApprovedQues('+data[i].QGID +',\'archive\',\'Non-Approved\','+data.length+', '+selectedTopicId+')" data-toggle="modal"  title="Archive Question" class="btn btn-danger AcT_ArchIcon float-right confModelArchBtn"><i class="fa fa-archive " aria-hidden="true"></i>&nbsp;ARCHIVE<div id="delete-pre"></div></button>'
							+'</div>'
					
				quesHtm +='</div>' // ApproveQue main div end
				quesHtm +='</td>'
				
							+ '</tr>'
			}
		
		 quesHtm += '    </tbody>'

		+ '</table>' + '</div>'
		$("#ques-bank-div").html(quesHtm);
		 
		 var pageIndex = 0; // this is to get pagination number
		 
		 $("#ques-bank-div").ready(function() {
			 
				var table = $('#nonApprovedQuestionData').DataTable({
//					 "pagingType": "full_numbers",
					"pageLength" : 50,
					// lengthChange: false,
					buttons : [ 'copy', 'excel', 'pdf' ],
					 "dom": '<"top"i>rt<"bottom"flp><"clear">',
				     drawCallback: function(){
				          $('.paginate_button.page-item:not(.disabled)', this.api().table().container())          
				             .on('click', function(){
				            	 com.coep.test.mathJax.renderMathJax();
				             });       
				       },
				    	"ordering": false,
				       "initComplete": function() {
				    	     
				    	     MJ.renderMathJax();
				    	   
				  			 $("#nonApprovedQuestionData_paginate a[data-dt-idx='"+pageIndexNo+"']").click();
				  			 setTimeout(function(){ 
				  				 $("#Loading").css("display","none");
				  				 $(window).scrollTop($('#Q'+quesID).offset().top);
				  			 }, 1000);
				       }
				
				}).on('search.dt', function() {
					 MJ.renderMathJax();
				});
				
				$("div.dataTables_wrapper div.dataTables_filter input").removeClass("form-control-sm");
				$("div.dataTables_wrapper div.dataTables_filter label").addClass("col-md-12 col-xl-12");
				$("div.dataTables_wrapper div.dataTables_filter input").addClass("col-md-12 col-xl-12");
				
				 $("#checkAllNonApproved").click(function(){
					 
					 
					 if (data.length == 1 ) {
							var htmlCheck =  "<b>This "+data.length+" question on this page is selected.</b> ";
//							alert(0)
							htmlCheck += '<br><button onclick = "javascript:com.coep.test.AlertMessage.confirmationToApproveQues('+  0 +',\'Non-Approved\',\'Approval\','+data.length+','+selectedTopicId+')" data-toggle="modal"  title="Approve Question" class="btn btn-dark confModelBtn " >APPROVE THIS QUSETION</button>'
							
						}else{
							 var htmlCheck =  "<b>All "+data.length+" questions on this page are selected.</b>";
//							 htmlCheck += '<input type="checkbox"  id="checkAllQuesToApprove" name="checkMultiple" class="checkboxType" > <label for="checkAllQuesToApprove" style="color: slateblue;">&nbsp;Select all '+data.length+' Questions.</label>'
							 htmlCheck += '<br><button onclick = "javascript:com.coep.test.AlertMessage.confirmationToApproveQues('+ 0 +',\'Non-Approved\',\'Approval\','+data.length+','+selectedTopicId+')" data-toggle="modal"  title="Approve Question" class="btn btn-dark confModelBtn " >APPROVE ALL QUSETIONS</button>'
						}
					 
					 $("#showSelQues").html(htmlCheck);
					 
					    $('input:checkbox').not(this).prop('checked', this.checked);
						 $('input[type=checkbox]').each(function () {

								
							 var ischecked= $(this).is(':checked');
							 if(!ischecked){
								 var uncheckedId = parseInt($(this).attr('checkId'));
								 selectedQuesGrps.splice( $.inArray(uncheckedId,selectedQuesGrps) , 1 );
								 
								 if(selectedQuesGrps.length == 0){
									 $(".confModelBtn").attr("data-target","#AlertMesConfirmSingle");
									 $(".confModelArchBtn").attr("data-target","#AlertMesConfirmSingle");
									 
								 }else{
									 $(".confModelBtn").attr("data-target","#AlertMesConfirmMuliple-"+selectedQuesGrps.length);
									 $(".confModelArchBtn").attr("data-target","#AlertMesConfirmMulipleArchive-"+selectedQuesGrps.length);
								 }
								 
								 QB.chkToSelectAll = false;
								 $("#showSelQues").hide();
							 }else{
								 
								 var temp = 0;
								 for(var t = 0; t < selectedQuesGrps.length; t++){
									 if($(this).attr('checkId') == selectedQuesGrps[t]){
										 temp++;
									 }
								 }
								 
								 if(temp == 0){
									 if(!isNaN(parseInt($(this).attr('checkId')))){
										 selectedQuesGrps.push(parseInt($(this).attr('checkId')));
										 $(".confModelBtn").attr("data-target","#AlertMesConfirmMuliple-"+selectedQuesGrps.length);
										 $(".confModelArchBtn").attr("data-target","#AlertMesConfirmMulipleArchive-"+selectedQuesGrps.length);
									 }
								 }
								 QB.chkToSelectAll = true;
								 $("#showSelQues").show();
							 }
						 });
						 
						 
						
						 
//						 $("#showSelQues").append(htmlCheck);
						 
						 
//							$('#checkAllQuesToApprove').on('change', function() {
////							    $('#show').html(this.checked ? this.value : '');
////								alert(this.checked ? this.value : '');
//								if (this.checked) {
////									alert(this.checked);
//									QB.chkToSelectAll = true;
//								}else{
//									alert(this.checked);
//									QB.chkToSelectAll = false;
//								}
////								this.checked ? this.value : '' selectAllToApprove
//							});
						 
					});
				 

				 $('#nonApprovedQuestionData_paginate').on( 'click', function () {
						 var a = $(this).find('li.active').find('a').data("dt-idx"); 
						 pageIndex = a;
				 } );
				 
				 
		 });// function ends here
		 
		 
		 $(".confModelBtn").attr("data-target","#AlertMesConfirmSingle");
		 $(".confModelArchBtn").attr("data-target","#AlertMesConfirmSingle");
		 
		 selectedQuesGrps = new Array();	
		 
		 $(".checkboxType").change(function() {
			
				 var ischecked= $(this).is(':checked');
				 if(!ischecked){
					 var uncheckedId = parseInt($(this).attr('checkId'));
					 selectedQuesGrps.splice( $.inArray(uncheckedId,selectedQuesGrps) ,1 );
					 
					 if(selectedQuesGrps.length == 0){
						 $(".confModelBtn").attr("data-target","#AlertMesConfirmSingle");
						 $(".confModelArchBtn").attr("data-target","#AlertMesConfirmSingle");
					 }else{
						 $(".confModelBtn").attr("data-target","#AlertMesConfirmMuliple-"+selectedQuesGrps.length);
						 $(".confModelArchBtn").attr("data-target","#AlertMesConfirmMulipleArchive-"+selectedQuesGrps.length);
					 }
				 }else{
					 
					 var temp = 0;
					 for(var t = 0; t < selectedQuesGrps.length; t++){
						 if($(this).attr('checkId') == selectedQuesGrps[t]){
							 temp++;
						 }
					 }
					 
					 if(temp == 0){
						 if(!isNaN(parseInt($(this).attr('checkId')))){
							 selectedQuesGrps.push(parseInt($(this).attr('checkId')));
							 $(".confModelBtn").attr("data-target","#AlertMesConfirmMuliple-"+selectedQuesGrps.length);
							 $(".confModelArchBtn").attr("data-target","#AlertMesConfirmMulipleArchive-"+selectedQuesGrps.length);
						 }
					 }
					
				 }
							
			});
		 
		 
		 modifyQuesGroup = function(QGID, backStatus, quesID) {
			 modifyQuw = true;
				AP.QGMLID = null;			
				$.ajax({
					type : "GET",
					url : AP.baseURL
					+ "questionGroups/",
					data : "QGID=" + QGID,
					dataType : 'json',
					contentType : 'application/json',
					success : function(data) {
						if (data.done == false) {
							showToast.show(data.msg, false);
						} else {
							if (data.done == true) {
								modifyQuw = true;
								questionGruopJSON = data.data;
								AP.QGMLID = data.data.quesGroupMediaLinks.quesGroupMediaLinkId;
								
								if(data.data.quesGroupMediaLinks.quesUsage == "question"){
									AP.mediaURL = data.data.quesGroupMediaLinks.mediaURLText;	
								}
														
								AP.modifyQuestionGroup(QGID, data.data, AP.mainData, backStatus, quesID, pageIndex);
							} else {
								showToast.show('', false);
							}
						}
					},
					error : function() {
					}
				});
			}
		 
		 
		 
		 AM.confirmationToApproveQues = function(QGID, status, Approvestatus, totalQues, selectedTopicId) {
			 	
				var AlertMesConfirm = '';
				
				 if(selectedQuesGrps.length == 0){
					 
					 var AlertComfirmFlag = false;
						AlertMsg = " Are you sure, you want to "+Approvestatus+" this question ?";
						 AlertMesConfirm = '';
							AlertMesConfirm +=  '<div class="container-fluid">'
							+ '<div class="row">'
							+ '<div  class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'
							//Alert modal start
									+ '<div class="modal" id="AlertMesConfirmSingle">'
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
//								approveQuestion(QGID, status, selectedTopicId);
								
								var selectAllToApprove = false;
								approveQuestion(QGID, status, selectAllToApprove, selectedTopicId);
								return AlertComfirmFlag;
							}

				 }else{
				
					 var QGIDArr = "["
							for (var a = 0; a < selectedQuesGrps.length; a++) {
								if(a == (selectedQuesGrps.length - 1)){
									QGIDArr += selectedQuesGrps[a]+"]"
								}else{
									QGIDArr += selectedQuesGrps[a]+","
								}
							}
					 

					 var AlertComfirmFlag = false;
//					 console.log(selectedQuesGrps.length);
					 
					 if (QB.chkToSelectAll == true) {
						 if (totalQues == 1) {
							 mulAlertMsg =  "  This "+selectedQuesGrps.length+" question on this page is selected for "+Approvestatus+".";
						}else{
							 mulAlertMsg =  "  All "+totalQues+" questions on this page are selected for "+Approvestatus+".";
						}
						 
					}else{
						 if (selectedQuesGrps.length == 1) {
							 mulAlertMsg =  "  This "+selectedQuesGrps.length+" question on this page is selected for "+Approvestatus+".";
							
						 }else{
							 mulAlertMsg =  "  All "+selectedQuesGrps.length+" questions on this page are selected for "+Approvestatus+"<br>";
						 }
						
					}
					 
						 AlertMesConfirm = '';
							AlertMesConfirm +=  '<div class="container-fluid">'
							+ '<div class="row">'
							+ '<div  class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'
							//Alert modal start
									+ '<div class="modal" id="AlertMesConfirmMuliple-'+selectedQuesGrps.length+'">'
									+ '<div class="modal-dialog">'
									+ '<div class="modal-content">'
						            
											+' <div class="modal-header bg-info" style="color:#fff;">'
											+ '   <h4 class="modal-title">Confirmation !!</h4>'
											+ '  <button type="button" class="close" data-dismiss="modal">&times;</button>'
											+ '  </div>'
							
											+ '   <div class="modal-body">'
											+ '<span id="AlertMsgStyle">'+mulAlertMsg+'</span>'
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
							
							var selectAllToApprove = false;
							
							if (QB.chkToSelectAll == true) {
								selectAllToApprove = true;
							}else{
								selectAllToApprove = false;
							}
							
//							$('#checkAllQuesToApprove').on('change', function() {
////								alert(this.checked ? this.value : '');
//								if (this.checked) {
//									selectAllToApprove = true;
//								}else{
//									selectAllToApprove = false;
//								}
////								this.checked ? this.value : '' selectAllToApprove
//							});
							
							AlertComfirmYes = function(){
								AlertComfirmFlag = true;
								approveQuestion(QGID, status, selectAllToApprove, selectedTopicId);
								return AlertComfirmFlag;
							}
				 
				 	}
					
				}
		 
		 
		 // this function is to approve the question group
		 approveQuestion = function(QGID, backStatus, selectAllToApprove, selectedTopicId) {
				
			 var loader = '<div class="overlay" id="Loading">'
				 + '<div class="overlay__inner">'
				 + '<div class="overlay__content"><span class="spinner"></span><br/><span class="loading">LOADING....</span></div>'
				 + '</div>'
				 + '</div>'
				 
				 $("#ques-bank-div").html(loader);
			 
			 if(selectedQuesGrps.length == 0){
				 AP.QGMLID = null;			
					$.ajax({
						type : "GET",
						url : AP.baseURL
						+ "questionGroups/approve",
						data : "QGID=" + QGID,
						dataType : 'json',
						contentType : 'application/json',
						success : function(data) {
							if (data.done == false) {
								showToast.show(data.msg, false);
								$("#Loading").css("display","none");
							} else {
								if (data.done == true) {
									modifyQuw = true;
									showToast.show(data.msg, true);
									QB.getQuestionsToApprove(backStatus, 0, 0, selectedTopicId);
								} else {
									showToast.show('', false);
								}
								$("#Loading").css("display","none");
							}
						},
						error : function() {
							$("#Loading").css("display","none");
						}
					}); 
			 }else{
				 
				 if (selectAllToApprove == true) {
					 var QGIDArr = "["
							for (var a = 0; a < data.length; a++) {
								if(a == (data.length - 1)){
									QGIDArr += data[a].QGID+"]"
								}else{
									QGIDArr +=  data[a].QGID+","
								}
							}
//					 console.log(QGIDArr);
					 
						$.ajax({
							type : "GET",
							url : AP.baseURL
							+ "questionGroups/approve/multiple/"+QGIDArr,
							dataType : 'json',
							contentType : 'application/json',
							success : function(data) {
								if (data.done == false) {
									showToast.show(data.msg, false);
									$("#Loading").css("display","none");
									QB.chkToSelectAll = false;
								} else {
									if (data.done == true) {
										modifyQuw = true;
										showToast.show(data.msg, true);
										QB.getQuestionsToApprove(backStatus, 0 , 0, selectedTopicId);
										QB.chkToSelectAll = false;
									} else {
										showToast.show('', false);
									}
									selectAllToApprove = false;
									$("#Loading").css("display","none");
									QB.chkToSelectAll = false;
								}
							},
							error : function() {
								$("#Loading").css("display","none");
							}
						}); 
				}else{
					 
					 var QGIDArr = "["
							for (var a = 0; a < selectedQuesGrps.length; a++) {
								if(a == (selectedQuesGrps.length - 1)){
									QGIDArr += selectedQuesGrps[a]+"]"
								}else{
									QGIDArr += selectedQuesGrps[a]+","
								}
							}
					 
						$.ajax({
							type : "GET",
							url : AP.baseURL
							+ "questionGroups/approve/multiple/"+QGIDArr,
							dataType : 'json',
							contentType : 'application/json',
							success : function(data) {
								if (data.done == false) {
									showToast.show(data.msg, false);
									$("#Loading").css("display","none");
									QB.chkToSelectAll = false;
								} else {
									if (data.done == true) {
										modifyQuw = true;
										showToast.show(data.msg, true);
										QB.getQuestionsToApprove(backStatus, 0 , 0, selectedTopicId);
										
									} else {
										showToast.show('', false);
									}
									
									$("#Loading").css("display","none");
									QB.chkToSelectAll = false;
								}
							},
							error : function() {
								$("#Loading").css("display","none");
								QB.chkToSelectAll = false;
							}
						}); 
				}
				
			 
				 
			 }
				
			}
		 
		 
		 
			AM.confirmationToArchiveOrActiveNonApprovedQues = function(QGID, status, appStatus, totalQues, selectedTopicId ) {

				var AlertMesConfirm = '';
				
				 if(selectedQuesGrps.length == 0){
					 
					 
					 var AlertComfirmFlag = false;
						AlertMsg = " Are you sure, you want to "+status+" this question ?";
						 AlertMesConfirm = '';
							AlertMesConfirm +=  '<div class="container-fluid">'
							+ '<div class="row">'
							+ '<div  class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'
							//Alert modal start
									+ '<div class="modal" id="AlertMesConfirmSingle">'
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
								QB.moveQuestionGroupToArchiveOrActiveNonApprovedQues(QGID, status, appStatus, "single", false, selectedTopicId);
							    	
								return AlertComfirmFlag;
							}
							
						

				 }else{
				
					 var QGIDArr = "["
							for (var a = 0; a < selectedQuesGrps.length; a++) {
								if(a == (selectedQuesGrps.length - 1)){
									QGIDArr += selectedQuesGrps[a]+"]"
								}else{
									QGIDArr += selectedQuesGrps[a]+","
								}
							}
					 

					 var AlertComfirmFlag = false;

					 
//					 mulAlertMsg =  " Are you sure, you want to "+status+" "+selectedQuesGrps.length+"  questions ?";
					 
//					 if (selectedQuesGrps.length == 1) {
//						 mulAlertMsg =  "  This "+selectedQuesGrps.length+" question on this page is selected for "+status+"<br>";
//						 mulAlertMsg += '';
//					}else if(selectedQuesGrps.length == data.length){
//						 mulAlertMsg =  "  All "+selectedQuesGrps.length+" questions on this page are selected for "+status+"<br>";
//					}else{
//						 mulAlertMsg =  "  All "+selectedQuesGrps.length+" questions on this page are selected for "+status+"<br/><br/>";
//						 mulAlertMsg += '<input type="checkbox"  id="checkAllQuesToArchive" name="checkMultiple" class="checkboxType" > <label for="checkAllQuesToArchive" style="color: slateblue;">&nbsp;Select all '+totalQues+' Questions for '+status+'</label>'
//					}
					 
					 if (QB.chkToSelectAll == true) {
						 if (totalQues == 1) {
							 mulAlertMsg =  "  This "+selectedQuesGrps.length+" question on this page is selected for "+status+".";
						}else{
							 mulAlertMsg =  "  All "+totalQues+" questions on this page are selected for "+status+".";
						}
						 
					}else{
						 if (selectedQuesGrps.length == 1) {
							 mulAlertMsg =  "  This "+selectedQuesGrps.length+" question on this page is selected for "+status+".";
							
						 }else{
							 mulAlertMsg =  "  All "+selectedQuesGrps.length+" questions on this page are selected for "+status+"<br>";
						 }
						
					}
					 
					 
						 AlertMesConfirm = '';
							AlertMesConfirm +=  '<div class="container-fluid">'
							+ '<div class="row">'
							+ '<div  class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'
							//Alert modal start
									+ '<div class="modal" id="AlertMesConfirmMulipleArchive-'+selectedQuesGrps.length+'">'
									+ '<div class="modal-dialog">'
									+ '<div class="modal-content">'
						            
						            	 +' <div class="modal-header bg-info" style="color:#fff;">'
											+ '   <h4 class="modal-title">Confirmation !!</h4>'
											+ '  <button type="button" class="close" data-dismiss="modal">&times;</button>'
											+ '  </div>'
							
											+ '   <div class="modal-body">'
											+ '<span id="AlertMsgStyle">'+mulAlertMsg+'</span>'
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
						
							var selectAllToArchive = false;
							
							if (QB.chkToSelectAll == true) {
								
								selectAllToArchive = true;
								
							}else{
								selectAllToArchive = false;
							}
//							$('#checkAllQuesToArchive').on('change', function() {
//
//								if (this.checked) {
//									selectAllToArchive = true;
//								}else{
//									selectAllToArchive = false;
//								}
//							});
							
							
							AlertComfirmYes = function()
							{
								AlertComfirmFlag = true;
								QB.moveQuestionGroupToArchiveOrActiveNonApprovedQues(QGID, status, appStatus, "multiple", selectAllToArchive, selectedTopicId);
								return AlertComfirmFlag;
							}
				 
				 }
				
				}
			
			
			QB.moveQuestionGroupToArchiveOrActiveNonApprovedQues = function(QGID, status, appStatus, flag, selectAllToArchive, selectedTopicId) {

				 var loader = '<div class="overlay" id="Loading">'
					 + '<div class="overlay__inner">'
					 + '<div class="overlay__content"><span class="spinner"></span><br/><span class="loading">LOADING....</span></div>'
					 + '</div>'
					 + '</div>'
					 
					 $("#ques-bank-div").html(loader);
				
				if(flag == "single"){
					$.ajax({
						type : "DELETE",
						url : AP.baseURL + "questionGroups/" + QGID,
						dataType : 'json',
						contentType : 'application/json',
						success : function(data) {
							if (data.done == false) {

								showToast.show(data.msg, false);
								$("#Loading").css("display","none");
								QB.chkToSelectAll = false;
							} else {
								if (data.length != 0) {
									showToast.show(data.msg, true);
									if(status == 'archive'){
										QB.getQuestionsToApprove(appStatus, 0 , 0, selectedTopicId);
									}else{
										QB.getQuestionsToApprove(appStatus,  0 , 0, selectedTopicId);
									}
									
								} else {
									showToast.show(data.msg, false);
								}
								
								$("#Loading").css("display","none");
								QB.chkToSelectAll = false;
							}
						},
						error : function() {
							$("#Loading").css("display","none");
							QB.chkToSelectAll = false;
						}

					});
				}else{

					if (selectAllToArchive == true) {
						
						var QGIDArr = "["
							for (var a = 0; a < data.length; a++) {
								if(a == (data.length - 1)){
									QGIDArr += data[a].QGID+"]"
								}else{
									QGIDArr +=  data[a].QGID+","
								}
							}
						
						$.ajax({
							type : "DELETE",
							url : AP.baseURL
							+ "questionGroups/archived/multiple/"+QGIDArr,
							data : "QGIDArr=" + QGIDArr,
							dataType : 'json',
							contentType : 'application/json',
							success : function(data) {
								if (data.done == false) {

									showToast.show(data.msg, false);
									$("#Loading").css("display","none");
									QB.chkToSelectAll = false;
									
								} else {
									if (data.length != 0) {
										showToast.show(data.msg, true);
										if(status == 'archive'){
											QB.getQuestionsToApprove(appStatus, 0 , 0, selectedTopicId)
										}else{
											QB.getQuestionsToApprove(appStatus, 0 , 0, selectedTopicId);
										}
									} else {
										showToast.show(data.msg, false);
									}
									$("#Loading").css("display","none");
									QB.chkToSelectAll = false;
								}
							},
							error : function() {
								$("#Loading").css("display","none");
								QB.chkToSelectAll = false;
							}

						});
						
					}else{
						
						var QGIDArr = "["
							for (var a = 0; a < selectedQuesGrps.length; a++) {
								if(a == (selectedQuesGrps.length - 1)){
									QGIDArr += selectedQuesGrps[a]+"]"
								}else{
									QGIDArr += selectedQuesGrps[a]+","
								}
							}
						
						$.ajax({
							type : "DELETE",
							url : AP.baseURL
							+ "questionGroups/archived/multiple/"+QGIDArr,
							data : "QGIDArr=" + QGIDArr,
							dataType : 'json',
							contentType : 'application/json',
							success : function(data) {
								if (data.done == false) {

									showToast.show(data.msg, false);
									$("#Loading").css("display","none");
									QB.chkToSelectAll = false;
								} else {
									if (data.length != 0) {
										showToast.show(data.msg, true);
										if(status == 'archive'){
											QB.getQuestionsToApprove(appStatus, 0 , 0, selectedTopicId)
										}else{
											QB.getQuestionsToApprove(appStatus, 0 , 0, selectedTopicId);
										}

									} else {
										showToast.show(data.msg, false);
									}
									QB.chkToSelectAll = false;
									$("#Loading").css("display","none");
								}
							},
							error : function() {
								$("#Loading").css("display","none");
								QB.chkToSelectAll = false;
							}

						});
					}
				
				
				}
			
				
			}
		 
		 MJ.renderMathJax();
		 
		
		
	} // non app fun ends here
	
	
	
	
	// approved questions for moderator
	QB.renderApprovedQuestionGroup = function(data, quesID, pageIndexNo, selectedTopicId) {
		
		var quesHtm = '';
		 
		+'<hr>'
		 
//		 quesHtm += '<h1>APPROVED QUESTION DETAILS</h1>'
		quesHtm += '<div class="overlay" id="Loading" style="display:block">'
			 + ' <div class="overlay__inner">'
			 + '<div class="overlay__content"><span class="spinner"></span><br/><span class="loading">LOADING....</span></div>'
			 + '</div>'
			 + '</div>'
				+ '<div class="table-responsive">'
				+ '<center><div id="showSelQues" style="display: none; color: #007bff"></div></center>' // showSelQues
				+ '<table id="approvedQuestionData" class="table table-striped table-bordered" style="width:100%">'
				+ '<thead>' + ' <tr>'
				+ '<th  width="5%" title="Select All" style="cursor: pointer;">'
				+'<input type="checkbox"  id="checkAllApproved" checkId="" name="checkMultiple" class="checkboxType"  style="cursor: pointer;" title="Select All"/><label for="checkAllApproved"  style="cursor: pointer; color: white;" >Select all Questions.</label>'
				+' </th>'
				+ '<th width="5%" >Sr. No. </th>'
				+ '     <th width="90%">Question </th>'
				+ '   </tr>'
				+ '</thead>'
				+ ' <tbody>'

		for (var i = 0; i < data.length; i++) {
			var topicName = "";
			for (var q = 0; q < data[i].QGTOPICS.length; q++) {
				if(q == data[i].QGTOPICS.length - 1){
					topicName += data[i].QGTOPICS[q].TNO+" - "+data[i].QGTOPICS[q].TNM + " ("+data[i].QGTOPICS[q].TNM1 +")";
				}else{
					topicName += data[i].QGTOPICS[q].TNO+" - "+data[i].QGTOPICS[q].TNM + " ("+data[i].QGTOPICS[q].TNM1 +") "+",<br>";
				}
			}
			
			
		quesHtm += '<tr  >'
				+'<td   width="5%">'
				
				+'<div class="" >'
				+'<label>'
				+'<input type="checkbox"  id="checkMultiple'+(i+1)+'" checkId="'+data[i].QGID+'" name="checkMultiple" class="checkboxType"/>'
				+'<i class="input-helper"></i>'
				+'</label>'
				+'</div>'
				
				+'</td>'
				quesHtm += '<td width="5%">Q' + (i + 1)

				quesHtm += '</td>'

				+ '<td width="90%">'
				+'<div class="row ApproveQueStyle" id="Q'+(i + 1)+'">' // ApproveQue main div start
				
			
				
				 +'<div class="col-xl-6 col-md-6 col-sm-12 justify-content-center" ><p class="ApproveDivLabel">Contributor - '+data[i].CBY+' </p></div>'
				 +'<div class="col-xl-6 col-md-6 col-sm-12 justify-content-center" ><p class="ApproveDivLabel">Approved By - '+data[i].ABY+' </p></div>'
				
				
				quesHtm += '<div class="col-xl-3 col-md-3 col-sm-12 ApproveDivStyle" >'
					 +'<span><p class="ApproveDivLabel">Question Type </p>'+data[i].QGTYP+'</span>'
					 +'</div>' 
				
					 +'<div class="col-xl-6 col-md-6 col-sm-12 ApproveDivStyle">'
					 +'<span><p class="ApproveDivLabel">Assign Topics</p>'+topicName+'</span>'
					 +'</div>'
					 
					 +'<div class="col-xl-3 col-md-3 col-sm-12 ApproveDivStyle">'
						+'<p class="ApproveDivLabel">Difficulty level  </p>'
						+'Level - ' +data[i].QGLVL
						+'</div>'
					 
					 
				 // Reference start
				 
				 if($.trim(data[i].QGTYP) == "Text" || $.trim(data[i].QGTYP) == "text"){
						
						if($.trim(data[i].QGMEDIA) == "" ){
							quesHtm += ''
						}else{
							quesHtm += '<div class="col-xl-12 col-md-12 col-sm-12" ><p class="ApproveDivLabel">Reference </p>'+data[i].QGMEDIA+'</div>'
						}
						
						
					}else if($.trim(data[i].QGTYP) == "Image"  || $.trim(data[i].QGTYP) == "image"){
						
						quesHtm += '<div class="col-xl-12 col-md-12 col-sm-12">'
							 +'<p class="ApproveDivLabel">'
							 +' With reference to the following Image answer the following question'
							 +' </p>'
							+'<div> <img src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QGMEDIA+'" class="ApproveQueImg" /></div></div><hr/>'
						
					}else if($.trim(data[i].QGTYP) == "Audio" || $.trim(data[i].QGTYP) == "audio"){
						
						quesHtm += '<div class="col-xl-12 col-md-12 col-sm-12">'
							 +'<p class="ApproveDivLabel">'
							 +' With reference to the following clip answer the following question'
							 +' </p>'
							 +' <audio controls class="ApproveQueAudio">'
							 +'  <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QGMEDIA+'" type="audio/mpeg" >'
							 +' Your browser does not support the audio element.'
							 +' </audio>'
							+'</div><hr/>'
							
					}else if($.trim(data[i].QGTYP) == "Video" || $.trim(data[i].QGTYP) == "Video"){
						quesHtm +='<div class="col-xl-12 col-md-12 col-sm-12">'
							+ '<p class="ApproveDivLabel">'
							+ ' With reference to the following clip answer the following question'
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
					quesHtm +='<div id="Actual_que" class="col-xl-12 col-md-12 col-sm-12">' //Actual_que start
						
						quesHtm +='<div class="row"><div class="col-xl-6 col-md-6 col-sm-12 ApproveDivStyle">'
							 +'<span><p class="ApproveDivLabel">Answer Type </p>'+data[i].QUESTION[j].QTYP+'</span>'
							 +'</div>'
							 
							 + '<div class="col-xl-6 col-md-6 col-sm-12 ApproveDivStyle" ><p class="ApproveDivLabel">Time</p><i class="fa fa-clock-o" aria-hidden="true" style="color:red;"></i> '+data[i].QUESTION[j].QTIM+' Seconds</div><hr/>'
							 + '</div>'
						
						+'<div id="MathPreview" class="actualQuesDiv" >' // actualQue start
						 if(data[i].QUESTION.length > 1){
							    quesHtm += '<div class=""><p class="ApproveDivLabel">Question '+(i+1)+'.'+(j+1)+' </p><b>'+data[i].QUESTION[j].QTXT+'</b></div>'
							}else{
								quesHtm +=  '<div class=""><p class="ApproveDivLabel">Question '+(i+1)+' </p><b>'+data[i].QUESTION[j].QTXT+'</b></div>'
							}
					quesHtm +='</div>'
					
					// ans div start
					 +'<div class="row" id="actualAnsDiv">' //  actualAnsDiv start
					  for(var k = 0; k < data[i].QUESTION[j].answers.length; k++){
						  
						  if (data[i].QUESTION[j].QTYPID == 1
									|| data[i].QUESTION[j].QTYPID == 2
									|| data[i].QUESTION[j].QTYPID == 3
									|| data[i].QUESTION[j].QTYPID == 4
									|| data[i].QUESTION[j].QTYPID == 6
									|| data[i].QUESTION[j].QTYPID == 7) {
							  quesHtm += '<div class="col-xl-6 col-md-6 col-sm-12 OptDiv">'
										  if(data[i].QUESTION[j].answers[k].ansMedia == "TEXT"){
											  if(data[i].QUESTION[j].answers[k].rightAnswer == true){
												  quesHtm +='<div >'         
												  +'<div >' 
													  		+'<input type="radio"  id="textOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" name="radioOpt'+(i+1)+'-'+(j+1)+'" class="radiostyle">'
												  			+ '<label  for="textOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" style="color:green;"  id="MathPreview">'+data[i].QUESTION[j].answers[k].content+'</label>'
												  			+'</div>'
												  			+'</div>'
											  }else{
												  quesHtm +='<div >'
												        +'<div  >' 
												  		+'<input type="radio" id="textOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" name="radioOpt'+(i+1)+'-'+(j+1)+'" class="radiostyle">'
											  			+ '<label  for="textOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" style="color: #1757b8;"  id="MathPreview">'+data[i].QUESTION[j].answers[k].content+'</label>'
											  			+'</div>'
											  			+'</div>'
											  }
											
										  }else{ 
											  
											  if(data[i].QUESTION[j].answers[k].rightAnswer == true){
											  quesHtm +='<div >'
												  +'<div >' 
												  +' <input id="imgOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" type="radio" name="radioOpt'+(i+1)+'-'+(j+1)+'"  class="radiostyle" />'
												  +'<label  for="imgOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'">'
									    			 +' <img src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QUESTION[j].answers[k].content+'" class="ApproveQueImgOption" style="border: 6px solid green;" />'
									    			 +'</label>'
									    			 +'</div>'
									    			 +'</div>'
											  }else{
												  quesHtm +='<div >'
													  +'<div >' 
													  +' <input id="imgOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" type="radio" name="radioOpt'+(i+1)+'-'+(j+1)+'"  class="radiostyle" />'
													  +'<label  for="imgOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'">'
										    			 +' <img src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QUESTION[j].answers[k].content+'" class="ApproveQueImgOption" style="border: 6px solid #1757b8;" />'
										    			 +'</label>'
										    			 +'</div>'
										    			 +'</div>'
											  }
										  }
										 
									  quesHtm +='</div>'
										 
								  } else if(data[i].QUESTION[j].QTYPID == 5 || data[i].QUESTION[j].QTYPID == 8) {

									  quesHtm += '<div class="col-xl-6 col-md-6 col-sm-12 OptDiv">'
										  if(data[i].QUESTION[j].answers[k].ansMedia == "TEXT"){
											  if(data[i].QUESTION[j].answers[k].rightAnswer == true){
												  quesHtm +='<div >'    
												        +'<div >' 
												  		+'<input type="checkbox"  id="textOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" name="ckeckOpt'+(i+1)+'-'+(j+1)+'" class="radiostyle">'
											  			+ '<label " for="textOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" style="color: green;"  id="MathPreview">'+data[i].QUESTION[j].answers[k].content+'</label>'
											  			+'</div>'
											  			+'</div>'
											  }else{
												  quesHtm +='<div >'
												        +'<div >' 
												  		+'<input type="checkbox" " id="textOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" name="checkOpt'+(i+1)+'-'+(j+1)+'" class="radiostyle">'
											  			+ '<label  for="textOpt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" style="color: #1757b8;"  id="MathPreview">'+data[i].QUESTION[j].answers[k].content+'</label>'
											  			+'</div>'
											  			+'</div>'
											  }
											
										  }else if(data[i].QUESTION[j].answers[k].rightAnswer == true){
												  quesHtm +='<div >'
													  +'<div >'
													  +' <input id="opt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" type="checkbox" name="checkOpt'+(i+1)+'-'+(j+1)+'"  class="radiostyle" />'
													  +'<label  for="opt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'">'
										    			 +' <img src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QUESTION[j].answers[k].content+'" class="ApproveQueImgOption" style="border: 6px solid green;"/>'
										    			 +'</label>'
										    			 +'</div>'
										    			 +'</div>'
												  }else{
													  quesHtm +='<div >'
														  +'<div >'
														  +' <input id="opt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'" type="checkbox" name="checkOpt'+(i+1)+'-'+(j+1)+'" class="radiostyle" />'
														  +'<label  for="opt'+(i+1)+'-'+(j+1)+'-'+(k+1)+'">'
											    			 +' <img src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QUESTION[j].answers[k].content+'" class="ApproveQueImgOption" style="border: 6px solid #1757b8;"/>'
											    			 +'</label>'
											    			 +'</div>'
											    			 +'</div>'
												  }
										 
									  quesHtm +='</div>'
								  
								  }
					  }
					quesHtm +='</div><hr/>' //   actualAnsDiv start
					// ans div end
					
					// solution div start
						 quesHtm +=  '<div id="solDiv" class="col-xl-12 col-md-12 col-sm-12">'
							    +'<div class="row">' //inner row start
								    
							    if (data[i].QUESTION[j].QSOLMEDIA == undefined) {
							        quesHtm += '<div class="col-xl-12 col-md-12 col-sm-12 ApproveDivSolutionStyle"><span class=""><p class="ApproveDivLabel">Solution Text</p class="ApproveDivLabel"> </span>'
						    			 +'<div class="inners">'+data[i].QUESTION[j].QSOLTEXT + '</div></div>'
								}else{
							    
							    quesHtm += '<div class="col-xl-6 col-md-6 col-sm-12 ApproveDivSolutionStyle"><span class=""><p class="ApproveDivLabel">Solution Text</p> </span>'
					    			 +'<div class="inners">'+data[i].QUESTION[j].QSOLTEXT + '</div></div>'
					    			 
					    			 if(data[i].QUESTION[j].QSOLTYPE == "IMAGE"){
					    				 
					    				 quesHtm += '<div class="col-xl-6 col-md-6 col-sm-12 ApproveDivStyle" ><span class=""><p class="ApproveDivLabel">Solution Media</p></span>'
					    				 +'<p>'
										 +' With reference to the following Image answer the question'
										 +' </p>'
					    				 +'<img src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QUESTION[j].QSOLMEDIA+'"  class="ApproveQueImg" /></div>'
					    				 
					    			 }else if(data[i].QUESTION[j].QSOLTYPE == "AUDIO"){
					    				 quesHtm += '<div class="col-xl-6 col-md-6 col-sm-12 ApproveDivStyle">'
					    					 +'<span class=""><p class="ApproveDivLabel">Solution Media</p></span>'
					    					 +'<p>'
											 +' With reference to the following clip answer the  question'
											 +' </p>'
											 +' <audio controls class="ApproveQueAudio">'
											 +'  <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QUESTION[j].QSOLMEDIA+'" type="audio/mpeg" >'
											 +' Your browser does not support the audio element.'
											 +' </audio>'
											+'</div>'
					    			 }else if(data[i].QUESTION[j].QSOLTYPE == "VIDEO"){
					    				 quesHtm +='<div class="col-xl-6 col-md-6 col-sm-12 ApproveDivStyle">'
						    					+'<span class=""><p class="ApproveDivLabel">Solution Media</p></span>'
						 						+ '<p>'
						 						+ ' With reference to the following clip answer the question'
						 						+ ' </p>'
						 						+'  <video controls  class="ApproveQueVideo" >'
						 						+ '            <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QUESTION[j].QSOLMEDIA+'" type="video/mp4" >'
						 						+ ' <source src="'+ AP.baseURL +'media/get/getImage?mediaID='+data[i].QUESTION[j].QSOLMEDIA+'" type="video/ogg">'
						 						+ '            Your browser does not support the video tag.'
						 						+ '         </video>' 
						 						+'</div>'
						    			 }
								}
					          quesHtm += '</div>'	//inner row end 
							 quesHtm +='</div>'
					// solution div end
								 
					// variation No div start			
					 if (data[i].varNo != "") {
						 if (data[i].varNo != undefined) {
							 quesHtm += '<div class="col-xl-12 col-md-12 col-sm-12 justify-content-center" ><p class="ApproveDivLabel">Variation No - '+data[i].varNo+'</p></div>'
						 }else{
							 quesHtm += '<div class="col-xl-12 col-md-12 col-sm-12 justify-content-center" ><p class="ApproveDivLabel">Variation No - Not assigned</p></div>'
						 }
							
						}else{
							quesHtm += '<div class="col-xl-12 col-md-12 col-sm-12 justify-content-center" ><p class="ApproveDivLabel">Variation No - Not assigned</p></div>'
						}
					//	variation No div start			 
								 
					 quesHtm += '<hr style="border-top: 8px solid rgba(72, 1, 1, 0.82);"></div>' //Actual_que start
					// acutal que end
					
				}	
				
				
						quesHtm +='<div class="col-xl-12 col-md-12 col-sm-12 justify-content-center">'
						+'<button onclick = "modifyQuesGroup('+  data[i].QGID +',\'Approved\', '+  (i+1) +')"  title="MODIFY QUESTION" class="btn btn-success AcT_ArchIcon float-left"><i class="fa fa-pencil-square-o " aria-hidden="true"></i>&nbsp;MODIFY</button> '
						+'<button onclick = "javascript:com.coep.test.AlertMessage.confirmationToNonApproveQues('+  data[i].QGID +',\'Approved\',\'Non-Approved\','+data.length+','+selectedTopicId+')"  data-toggle="modal" data-target="#AlertMesConfirm" class="btn btn-dark confModelBtn" >MOVE TO NON-APPROVED</button>'
						+ '<button onClick="javascript:com.coep.test.AlertMessage.confirmationToArchiveApprovedQues('+data[i].QGID +',\'archive\',\'Approved\','+data.length+','+selectedTopicId+')" data-toggle="modal" data-target="#AlertMesConfirm" title="Archive Question" class="btn btn-danger AcT_ArchIcon float-right confModelArchBtn"><i class="fa fa-archive " aria-hidden="true"></i>&nbsp;ARCHIVE<a data-toggle="modal" data-target="#archiveModel"></a><div id="delete-pre"></div></button>'
						+'</div>'
					
				quesHtm +='</div>' // ApproveQue main div end
				quesHtm +='</td>'
				
							+ '</tr>'
			}
		
		 quesHtm += '    </tbody>'

		+ '</table>' + '</div>'
		$("#ques-bank-div").html(quesHtm);
		 
		 var pageIndex = 0; // this is to get pagination number
		 
		 $("#ques-bank-div").ready(function() {
				var table = $('#approvedQuestionData').DataTable({
					"pageLength" : 50,
					// lengthChange: false,
					buttons : [ 'copy', 'excel', 'pdf' ],
					 "dom": '<"top"i>rt<"bottom"flp><"clear">',
				     drawCallback: function(){
				          $('.paginate_button.page-item:not(.disabled)', this.api().table().container())          
				             .on('click', function(){
				            	 com.coep.test.mathJax.renderMathJax();
				             });       
				       },
				    	"ordering": false,
				    	 
				    	"initComplete": function() {
					
			    	   	 MJ.renderMathJax();
			  			
			    	   	 $("#approvedQuestionData_paginate a[data-dt-idx='"+pageIndexNo+"']").click();
			  			 setTimeout(function(){ 
			  				$("#Loading").css("display","none"); 
			  				 $(window).scrollTop($('#Q'+quesID).offset().top);
			  			 }, 1000);
			  }
				
				
				}).on('search.dt', function() {
					 MJ.renderMathJax();
				});
				
				$("div.dataTables_wrapper div.dataTables_filter input").removeClass("form-control-sm");
				$("div.dataTables_wrapper div.dataTables_filter label").addClass("col-md-12 col-xl-12");
				$("div.dataTables_wrapper div.dataTables_filter input").addClass("col-md-12 col-xl-12");
				
				 setTimeout(function(){ $("#Loading").css("display","none"); }, 2000);
				 
				 $("#checkAllApproved").click(function(){
					 
					 
					 if (data.length == 1 ) {
							var htmlCheck =  "<b>This "+data.length+" question on this page is selected.</b> "
							 htmlCheck += '<br><button onclick = "javascript:com.coep.test.AlertMessage.confirmationToNonApproveQues('+ 0 +',\'Approved\',\'Non-Approved\','+data.length+','+selectedTopicId+')"  data-toggle="modal" data-target="#AlertMesConfirm" class="btn btn-dark confModelBtn" >MOVE TO NON-APPROVED</button>';
							
						}else{
							 var htmlCheck =  "<b>All "+data.length+" questions on this page are selected.</b>"
//							 htmlCheck += '<input type="checkbox"  id="checkAllQuesToApprove" name="checkMultiple" class="checkboxType" > <label for="checkAllQuesToApprove" style="color: slateblue;">&nbsp;Select all '+data.length+' Questions.</label>'
							 htmlCheck += '<br><button onclick = "javascript:com.coep.test.AlertMessage.confirmationToNonApproveQues('+ 0 +',\'Approved\',\'Non-Approved\','+data.length+','+selectedTopicId+')"  data-toggle="modal" data-target="#AlertMesConfirm" class="btn btn-dark confModelBtn" >MOVE ALL TO NON-APPROVED</button>';
								
						}
					 
					 $("#showSelQues").html(htmlCheck);
					 
					 
					    $('input:checkbox').not(this).prop('checked', this.checked);
						 $('input[type=checkbox]').each(function () {

								
							 var ischecked= $(this).is(':checked');
							 if(!ischecked){
								 var uncheckedId = parseInt($(this).attr('checkId'));
								 selectedQuesGrps.splice( $.inArray(uncheckedId,selectedQuesGrps) , 1 );
								 
								 if(selectedQuesGrps.length == 0){
									 $(".confModelBtn").attr("data-target","#AlertMesConfirmSingle");
									 $(".confModelArchBtn").attr("data-target","#AlertMesConfirmSingle");
									 
								 }else{
									 $(".confModelBtn").attr("data-target","#AlertMesConfirmMuliple-"+selectedQuesGrps.length);
									 $(".confModelArchBtn").attr("data-target","#AlertMesConfirmMulipleArchive-"+selectedQuesGrps.length);
								 }
								 
								 QB.chkToSelectAll = false;
								 $("#showSelQues").hide();
							 }else{
								 
								 var temp = 0;
								 for(var t = 0; t < selectedQuesGrps.length; t++){
									 if($(this).attr('checkId') == selectedQuesGrps[t]){
										 temp++;
									 }
								 }
								 
								 if(temp == 0){
									 if(!isNaN(parseInt($(this).attr('checkId')))){
										 selectedQuesGrps.push(parseInt($(this).attr('checkId')));
										 $(".confModelBtn").attr("data-target","#AlertMesConfirmMuliple-"+selectedQuesGrps.length);
										 $(".confModelArchBtn").attr("data-target","#AlertMesConfirmMulipleArchive-"+selectedQuesGrps.length);
									 }
									
								 }
								 QB.chkToSelectAll = true;
								 $("#showSelQues").show();
							 }
							 
							 
//							if (data.length == 1 ) {
//									var htmlCheck =  "<b>This "+data.length+" question on this page is selected.</b> ";
////									htmlCheck += '<button onclick = "javascript:com.coep.test.AlertMessage.confirmationToApproveQues('+  data[i].QGID +',\'Non-Approved\',\'Approval\','+data.length+')" data-toggle="modal"  title="Approve Question" class="btn btn-dark confModelBtn " >APPROVE</button>';
//							}else{
//								 var htmlCheck =  " <b> All "+data.length+" questions on this page are selected.</b> ";
////								 htmlCheck += '<button onclick = "javascript:com.coep.test.AlertMessage.confirmationToApproveQues('+  data[i].QGID +',\'Non-Approved\',\'Approval\','+data.length+')" data-toggle="modal"  title="Approve Question" class="btn btn-dark confModelBtn " >APPROVE</button>';
//							}
//							 
//							 $("#showSelQues").html(htmlCheck);
							 
							 
//								$('#checkAllQuesToNonApprove').on('change', function() {
////								    $('#show').html(this.checked ? this.value : '');
////									alert(this.checked ? this.value : '');
//									if (this.checked) {
//										alert(this.checked);
//										QB.chkToSelectAll = true;
//									}else{
//										alert(this.checked);
//										QB.chkToSelectAll = false;
//									}
////									this.checked ? this.value : '' selectAllToApprove
//								});
								
						
						 });
						 

					});
				 
				 
				 $('#approvedQuestionData_paginate').on( 'click', function () {
					 var a = $(this).find('li.active').find('a').data("dt-idx"); 
					
					 pageIndex = a;
			 
			 } );
				 
		 });// function ends here
		 
		 
		 $(".confModelBtn").attr("data-target","#AlertMesConfirmSingle");
		 $(".confModelArchBtn").attr("data-target","#AlertMesConfirmSingle");
		 
		 selectedQuesGrps = new Array();
		 
		 $(".checkboxType").change(function() {
			
				 var ischecked= $(this).is(':checked');
				 if(!ischecked){
					 var uncheckedId = parseInt($(this).attr('checkId'));
					 selectedQuesGrps.splice($.inArray(uncheckedId,
										selectedQuesGrps), 1);
					 
					 if(selectedQuesGrps.length == 0){
						 $(".confModelBtn").attr("data-target","#AlertMesConfirmSingle");
						 $(".confModelArchBtn").attr("data-target","#AlertMesConfirmSingle");
					 }else{
						 $(".confModelBtn").attr("data-target","#AlertMesConfirmMuliple-"+selectedQuesGrps.length);
						 $(".confModelArchBtn").attr("data-target","#AlertMesConfirmMulipleArchive-"+selectedQuesGrps.length);
					 }
					 
				 }else{
					 var temp = 0;
					 for(var t = 0; t < selectedQuesGrps.length; t++){
						 if($(this).attr('checkId') == selectedQuesGrps[t]){
							 temp++;
						 }
					 }
					 
					 if(temp == 0){
						 if(!isNaN(parseInt($(this).attr('checkId')))){
							 selectedQuesGrps.push(parseInt($(this).attr('checkId')));
							 $(".confModelBtn").attr("data-target","#AlertMesConfirmMuliple-"+selectedQuesGrps.length);
							 $(".confModelArchBtn").attr("data-target","#AlertMesConfirmMulipleArchive-"+selectedQuesGrps.length);
						 }
						
					 }
					
				 }
							
			});
		 
		 
		 modifyQuesGroup = function(QGID, backStatus, quesID) {
			 modifyQuw = true;
				AP.QGMLID = null;			
				$.ajax({
					type : "GET",
					url : AP.baseURL
					+ "questionGroups/",
					data : "QGID=" + QGID,
					dataType : 'json',
					contentType : 'application/json',
					success : function(data) {
						if (data.done == false) {
							showToast.show(data.msg, false);
						} else {
							if (data.done == true) {
								modifyQuw = true;
								questionGruopJSON = data.data;
								AP.QGMLID = data.data.quesGroupMediaLinks.quesGroupMediaLinkId;
								
								if(data.data.quesGroupMediaLinks.quesUsage == "question"){
									AP.mediaURL = data.data.quesGroupMediaLinks.mediaURLText;	
								}
														
								AP.modifyQuestionGroup(QGID, data.data, AP.mainData, backStatus, quesID, pageIndex);
							} else {
								showToast.show('', false);
							}
						}
					},
					error : function() {
					}
				});
			}
		 
		 
		 
		 // confirmation to non approve
		 
		 AM.confirmationToNonApproveQues = function(QGID, status, Approvestatus, totalQues, selectedTopicId) {

				var AlertMesConfirm = '';
				
				 if(selectedQuesGrps.length == 0){
					 
					 
					 var AlertComfirmFlag = false;
						AlertMsg = " Are you sure, you want to "+Approvestatus+" this question ?";
						 AlertMesConfirm = '';
							AlertMesConfirm +=  '<div class="container-fluid">'
							+ '<div class="row">'
							+ '<div  class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'
							//Alert modal start
									+ '<div class="modal" id="AlertMesConfirmSingle">'
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
							
						AlertComfirmYes = function() {
							AlertComfirmFlag = true;
							selectAllToNonApprove = false;
							approveQuestion(QGID, status, selectAllToNonApprove, selectedTopicId);
							return AlertComfirmFlag;
						}

				 }else{
				
					 var QGIDArr = "["
							for (var a = 0; a < selectedQuesGrps.length; a++) {
								if(a == (selectedQuesGrps.length - 1)){
									QGIDArr += selectedQuesGrps[a]+"]"
								}else{
									QGIDArr += selectedQuesGrps[a]+","
								}
							}
					 

					 var AlertComfirmFlag = false;

//					 mulAlertMsg =  " Are you sure, you want to "+Approvestatus+" "+selectedQuesGrps.length+"  questions ?";
					
//					 if (selectedQuesGrps.length == 1) {
//						 mulAlertMsg =  "  This "+selectedQuesGrps.length+" question on this page is selected for "+Approvestatus+"<br>";
//						 mulAlertMsg += '';
//					}else if(selectedQuesGrps.length == data.length){
//						 mulAlertMsg =  "  All "+selectedQuesGrps.length+" questions on this page are selected for "+Approvestatus+"<br>";
//					}else{
//						 mulAlertMsg =  "  All "+selectedQuesGrps.length+" questions on this page are selected for "+Approvestatus+"<br/><br/>";
//						 mulAlertMsg += '<input type="checkbox"  id="checkAllQuesToNonApprove" name="checkMultiple" class="checkboxType" > <label  for="checkAllQuesToNonApprove" style="color: slateblue;">&nbsp;Select all '+totalQues+' Questions for '+Approvestatus+'</label>'
//					}
				
					 
					 if (QB.chkToSelectAll == true) {
						 if (totalQues == 1) {
							 mulAlertMsg =  "  This "+selectedQuesGrps.length+" question on this page is selected for "+Approvestatus+".";
							 
						}else{
							 mulAlertMsg =  "  All "+totalQues+" questions on this page are selected for "+Approvestatus+".";
						}
						 
					}else{
						 if (selectedQuesGrps.length == 1) {
							 mulAlertMsg =  "  This "+selectedQuesGrps.length+" question on this page is selected for "+Approvestatus+".";
							
						 }else{
							 mulAlertMsg =  "  All "+selectedQuesGrps.length+" questions on this page are selected for "+Approvestatus+"<br>";
						 }
						
					}
						 AlertMesConfirm = '';
							AlertMesConfirm +=  '<div class="container-fluid">'
							+ '<div class="row">'
							+ '<div  class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'
							//Alert modal start
									+ '<div class="modal" id="AlertMesConfirmMuliple-'+selectedQuesGrps.length+'">'
									+ '<div class="modal-dialog">'
									+ '<div class="modal-content">'
						            
						            	 +' <div class="modal-header bg-info" style="color:#fff;">'
											+ '   <h4 class="modal-title">Confirmation !!</h4>'
											+ '  <button type="button" class="close" data-dismiss="modal">&times;</button>'
											+ '  </div>'
							
											+ '   <div class="modal-body">'
											+ '<span id="AlertMsgStyle">'+mulAlertMsg+'</span>'
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
						
							var selectAllToNonApprove = false;
							
							if (QB.chkToSelectAll == true) {
								selectAllToNonApprove = true;
							}else{
								selectAllToNonApprove = false;
							}
							
//							$('#checkAllQuesToNonApprove').on('change', function() {
//
//								if (this.checked) {
//									selectAllToNonApprove = true;
//								}else{
//									selectAllToNonApprove = false;
//								}
//							});
							AlertComfirmYes = function(){
								AlertComfirmFlag = true;
								approveQuestion(QGID, status, selectAllToNonApprove, selectedTopicId);
								return AlertComfirmFlag;
							}
				 
				 	}
				
					
				}
		 
		 
		 // this function is to approve the question group
		 approveQuestion = function(QGID, backStatus, selectAllToNonApprove, selectedTopicId) {
			 
			 var loader = '<div class="overlay" id="Loading">'
				 + '<div class="overlay__inner">'
				 + '<div class="overlay__content"><span class="spinner"></span><br/><span class="loading">LOADING....</span></div>'
				 + '</div>'
				 + '</div>'
				 
				 $("#ques-bank-div").html(loader);
			 
			 if(selectedQuesGrps.length == 0){
				 AP.QGMLID = null;			
					$.ajax({
						type : "GET",
						url : AP.baseURL
						+ "questionGroups/approve",
						data : "QGID=" + QGID,
						dataType : 'json',
						contentType : 'application/json',
						success : function(data) {
							if (data.done == false) {
								showToast.show(data.msg, false);
								QB.chkToSelectAll = false;
								$("#Loading").css("display","none");
							} else {
								if (data.done == true) {
									modifyQuw = true;
									showToast.show(data.msg, true);
									QB.getQuestionsToApprove(backStatus , 0 , 0, selectedTopicId);
								} else {
									showToast.show('', false);
								}
								QB.chkToSelectAll = false;
								$("#Loading").css("display","none");
							}
						},
						error : function() {
							QB.chkToSelectAll = false;
							$("#Loading").css("display","none");
						}
					}); 
			 }else{
				 
				 if (selectAllToNonApprove == true) {
					 
					 var QGIDArr = "["
							for (var a = 0; a < data.length; a++) {
								if(a == (data.length - 1)){
									QGIDArr += data[a].QGID+"]"
								}else{
									QGIDArr +=  data[a].QGID+","
								}
							}
					 
						$.ajax({
							type : "GET",
							url : AP.baseURL
							+ "questionGroups/approve/multiple/"+QGIDArr,
							dataType : 'json',
							contentType : 'application/json',
							success : function(data) {
								if (data.done == false) {
									showToast.show(data.msg, false);
									QB.chkToSelectAll = false;
									$("#Loading").css("display","none");
								} else {
									if (data.done == true) {
										modifyQuw = true;
										showToast.show(data.msg, true);
										QB.getQuestionsToApprove(backStatus, 0 , 0, selectedTopicId);
									} else {
										showToast.show('', false);
									}
									QB.chkToSelectAll = false;
									selectAllToNonApprove = false;
									$("#Loading").css("display","none");
								}
							},
							error : function() {
								QB.chkToSelectAll = false;
								$("#Loading").css("display","none");
							}
						}); 
						
				 }else{
					 
					 var QGIDArr = "["
							for (var a = 0; a < selectedQuesGrps.length; a++) {
								if(a == (selectedQuesGrps.length - 1)){
									QGIDArr += selectedQuesGrps[a]+"]"
								}else{
									QGIDArr += selectedQuesGrps[a]+","
								}
							}
					 
						$.ajax({
							type : "GET",
							url : AP.baseURL
							+ "questionGroups/approve/multiple/"+QGIDArr,
							dataType : 'json',
							contentType : 'application/json',
							success : function(data) {
								if (data.done == false) {
									showToast.show(data.msg, false);
									QB.chkToSelectAll = false;
									$("#Loading").css("display","none");
								} else {
									if (data.done == true) {
										modifyQuw = true;
										showToast.show(data.msg, true);
										QB.getQuestionsToApprove(backStatus, 0 , 0, selectedTopicId);
									} else {
										showToast.show('', false);
									}
									QB.chkToSelectAll = false;
									$("#Loading").css("display","none");
								}
							},
							error : function() {
								QB.chkToSelectAll = false;
								$("#Loading").css("display","none");
							}
						}); 
				 }
				
			 
				 
			 }
				 
			}
		 
		 
		 
		 AM.confirmationToArchiveApprovedQues = function(QGID, status, appStatus, totalQues, selectedTopicId) {

				var AlertMesConfirm = '';
				
				 if(selectedQuesGrps.length == 0){
					 
					 var AlertComfirmFlag = false;
						AlertMsg = " Are you sure, you want to "+status+" this question ?";
						 AlertMesConfirm = '';
							AlertMesConfirm +=  '<div class="container-fluid">'
							+ '<div class="row">'
							+ '<div  class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'
							//Alert modal start
									+ '<div class="modal" id="AlertMesConfirmSingle">'
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
								QB.moveQuestionGroupToArchiveApprovedQues(QGID, status, appStatus, "single", false, selectedTopicId);
								return AlertComfirmFlag;
							}
							
						

				 }else{
				
					 var QGIDArr = "["
							for (var a = 0; a < selectedQuesGrps.length; a++) {
								if(a == (selectedQuesGrps.length - 1)){
									QGIDArr += selectedQuesGrps[a]+"]"
								}else{
									QGIDArr += selectedQuesGrps[a]+","
								}
							}
					 

					 var AlertComfirmFlag = false;

					 
//					 mulAlertMsg =  " Are you sure, you want to "+status+" "+selectedQuesGrps.length+"  questions ?";
					 
//					 if (selectedQuesGrps.length == 1) {
//						 mulAlertMsg =  "  This "+selectedQuesGrps.length+" question on this page is selected for "+status+"<br>";
//						 mulAlertMsg += '';
//					}else if(selectedQuesGrps.length == data.length){
//						 mulAlertMsg =  "  All "+selectedQuesGrps.length+" questions on this page are selected for "+status+"<br>";
//					}else{
//						 mulAlertMsg =  "  All "+selectedQuesGrps.length+" questions on this page are selected for "+status+"<br/><br/>";
//						 mulAlertMsg += '<input type="checkbox"  id="checkAllQuesToArchive" name="checkMultiple" class="checkboxType" > <label  for="checkAllQuesToArchive" style="color: slateblue;">&nbsp;Select all '+totalQues+' Questions for '+status+'</label>'
//					}
					 
					 if (QB.chkToSelectAll == true) {
						 if (totalQues == 1) {
							 mulAlertMsg =  "  This "+selectedQuesGrps.length+" question on this page is selected for "+status+".";
						}else{
							 mulAlertMsg =  "  All "+totalQues+" questions on this page are selected for "+status+".";
						}
						 
					}else{
						 if (selectedQuesGrps.length == 1) {
							 mulAlertMsg =  "  This "+selectedQuesGrps.length+" question on this page is selected for "+status+".";
							
						 }else{
							 mulAlertMsg =  "  All "+selectedQuesGrps.length+" questions on this page are selected for "+status+"<br>";
						 }
						
					}
					 
						 AlertMesConfirm = '';
							AlertMesConfirm +=  '<div class="container-fluid">'
							+ '<div class="row">'
							+ '<div  class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'
							//Alert modal start
									+ '<div class="modal" id="AlertMesConfirmMulipleArchive-'+selectedQuesGrps.length+'">'
									+ '<div class="modal-dialog">'
									+ '<div class="modal-content">'
						            
						            	 +' <div class="modal-header bg-info" style="color:#fff;">'
											+ '   <h4 class="modal-title">Confirmation !!</h4>'
											+ '  <button type="button" class="close" data-dismiss="modal">&times;</button>'
											+ '  </div>'
							
											+ '   <div class="modal-body">'
											+ '<span id="AlertMsgStyle">'+mulAlertMsg+'</span>'
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
						
							var selectAllToArchive = false;
							
							if (QB.chkToSelectAll == true) {
								selectAllToArchive = true;
							}else{
								selectAllToArchive = false;
							}
//							$('#checkAllQuesToArchive').on('change', function() {
//
//								if (this.checked) {
//									selectAllToArchive = true;
//								}else{
//									selectAllToArchive = false;
//								}
//							});
//							
							
							AlertComfirmYes = function()
							{
								AlertComfirmFlag = true;
								QB.moveQuestionGroupToArchiveApprovedQues(QGID, status, appStatus, "multiple", selectAllToArchive, selectedTopicId);
								return AlertComfirmFlag;
							}
				 
				 }
				
				}
			
			
			QB.moveQuestionGroupToArchiveApprovedQues = function(QGID, status, appStatus, flag, selectAllToArchive, selectedTopicId) {

				 var loader = '<div class="overlay" id="Loading">'
					 + '<div class="overlay__inner">'
					 + '<div class="overlay__content"><span class="spinner"></span><br/><span class="loading">LOADING....</span></div>'
					 + '</div>'
					 + '</div>'
					 
					 $("#ques-bank-div").html(loader);
				
				if(flag == "single"){
					$.ajax({
						type : "DELETE",
						url : AP.baseURL + "questionGroups/" + QGID,
						dataType : 'json',
						contentType : 'application/json',
						success : function(data) {
							if (data.done == false) {

								showToast.show(data.msg, false);
								$("#Loading").css("display","none");
								QB.chkToSelectAll = false;
							} else {
								if (data.length != 0) {
									showToast.show(data.msg, true);
									if(status == 'archive'){
										QB.getQuestionsToApprove(appStatus, 0 , 0, selectedTopicId)
									}else{
										QB.getQuestionsToApprove(appStatus, 0 , 0, selectedTopicId);
									}
									
								} else {
									showToast.show(data.msg, false);
								}
								
								$("#Loading").css("display","none");
								QB.chkToSelectAll = false;
							}
						},
						error : function() {
							$("#Loading").css("display","none");
							QB.chkToSelectAll = false;
						}

					});
				}else{

					if (selectAllToArchive == true) {
						
						var QGIDArr = "["
							for (var a = 0; a < data.length; a++) {
								if(a == (data.length - 1)){
									QGIDArr += data[a].QGID+"]"
								}else{
									QGIDArr +=  data[a].QGID+","
								}
							}
						
						$.ajax({
							type : "DELETE",
							url : AP.baseURL
							+ "questionGroups/archived/multiple/"+QGIDArr,
							data : "QGIDArr=" + QGIDArr,
							dataType : 'json',
							contentType : 'application/json',
							success : function(data) {
								if (data.done == false) {
									showToast.show(data.msg, false);
									$("#Loading").css("display","none");
									QB.chkToSelectAll = false;
								} else {
									if (data.length != 0) {
										showToast.show(data.msg, true);
										if(status == 'archive'){
											QB.getQuestionsToApprove(appStatus, 0 , 0, selectedTopicId)
										}else{
											QB.getQuestionsToApprove(appStatus, 0 , 0, selectedTopicId);
										}
									} else {
										showToast.show(data.msg, false);
									}
									$("#Loading").css("display","none");
									QB.chkToSelectAll = false;
								}
							},
							error : function() {
								$("#Loading").css("display","none");
								QB.chkToSelectAll = false;
							}

						});
						
					}else{
						
						var QGIDArr = "["
							for (var a = 0; a < selectedQuesGrps.length; a++) {
								if(a == (selectedQuesGrps.length - 1)){
									QGIDArr += selectedQuesGrps[a]+"]"
								}else{
									QGIDArr += selectedQuesGrps[a]+","
								}
							}
						
						$.ajax({
							type : "DELETE",
							url : AP.baseURL
							+ "questionGroups/archived/multiple/"+QGIDArr,
							data : "QGIDArr=" + QGIDArr,
							dataType : 'json',
							contentType : 'application/json',
							success : function(data) {
								if (data.done == false) {
									showToast.show(data.msg, false);
									$("#Loading").css("display","none");
									QB.chkToSelectAll = false;
								} else {
									if (data.length != 0) {
										showToast.show(data.msg, true);
										if(status == 'archive'){
											QB.getQuestionsToApprove(appStatus, 0 , 0, selectedTopicId)
										}else{
											QB.getQuestionsToApprove(appStatus, 0 , 0, selectedTopicId);
										}

									} else {
										showToast.show(data.msg, false);
									}
									QB.chkToSelectAll = false;
									$("#Loading").css("display","none");
									
								}
							},
							error : function() {
								QB.chkToSelectAll = false;
								$("#Loading").css("display","none");
							}

						});
					}
				
				
				}
			
				
			}
		 
		 
		 MJ.renderMathJax();
		 
		 setTimeout(function(){  $(window).scrollTop($('#Q'+quesID).offset().top); }, 1000);
		
	}
	
	
	
	
})(com.coep.test.ajaxHandler, com.coep.test.addProblem,
		com.coep.test.questionBank, com.coep.test.AlertMessage, com.coep.test.mathJax);