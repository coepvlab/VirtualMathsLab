(function(HM, AM, PT) {

	HM.itoFlag = false;
	HM.topicDetails = {};
	
	HM.getAllTopicToHomePage = function(itoStud, topicArray) {
//		alert(topicArray);
		// ajax call to get all topic list
		
			$.ajax({
				type : "GET",
				url : com.coep.test.addProblem.baseURL
						+ "topic/api/get/home/topics",
				dataType : 'json',
				contentType : 'application/json',
				success : function(data) {
					HM.homePage(data, itoStud, topicArray);
				},
				error : function() {
				}

			});
		
	}
	
	
	HM.homePage = function(data, itoStud, topicArray) {
		
//		console.log(data);
		
		 var tArray = [];
		 tArray = topicArray.split(",");
		 var names = "";
		for (var i = 0; i < tArray.length; i++) {
			
			var TopicId = parseInt(tArray[i]);
			$.ajax({
				type : "GET",
				url : com.coep.test.addProblem.baseURL + "topic/api/get/name?topicId=" + TopicId,
				dataType : 'json',
				contentType : 'application/json',
				success : function(data) {
//					console.log("data: "+data);
					if (data.done == true) {
						names +=  data.TN +"*"+data.TN1 +"*"+ data.TID +"!!";
					}else{
//						alert("as");
					}
					
//					console.log(names);
//					HM.rendChildTopic(data, TopicId, parentId, index, itoStud);
				},
				error : function() {

				}
			});
			
			
		}
		
			setTimeout(function(){ AM.confirmationFromHomePage(names, tArray) }, 700); 
//		}
	
//		AM.confirmationFromHomePage(names, tArray);
		
		if (itoStud != "ito@gmail.com") {
			var htm = '';
			
			htm +='<h1 class="text-center ">Virtual Maths Lab</h1>'
				
			+'<hr class="mb-0">'
			htm +='<div id="accordion" class="accordion md-accordion accordion-blocks">'// accordion div start
//			console.log(data);
				
			for(var i = 0; i < data.topicData.length; i++){
				
				if(data.topicData[i].status == true){
					htm +='  <div class="card">' // card div start
					+'    <div class="card-header area" id="heading-'+data.topicData[i].TID+'-'+data.topicData[i].LNO+'" >'
					+'      <h5 class="mb-0">'
					+'        <a class="collapsed" role="button" data-toggle="collapse" href="#collapse-'+data.topicData[i].TID+'-'+data.topicData[i].LNO+'" aria-expanded="false" aria-controls="collapse-'+data.topicData[i].TID+'-'+data.topicData[i].LNO+'" id="'+data.topicData[i].TID+'-'+data.topicData[i].LNO+'" onClick="getChild('+data.topicData[i].TID+',this.id,'+(i+1)+')">'
					+'<i class="fa fa-book fas fa-2x  float-left book-color" aria-hidden="true"></i>'
					+ '<span class=" topicName"> '+data.topicData[i].TN +' ('+ $.trim(data.topicData[i].TN1) +')</span>'
					+'        </a>'
					+'      </h5>'
					+'    </div>'
					+'    <div id="collapse-'+data.topicData[i].TID+'-'+data.topicData[i].LNO+'" class="collapse"  data-parent="#accordion" aria-labelledby="heading-'+data.topicData[i].TID+'-'+data.topicData[i].LNO+'" >' // collapse 1  start
					+'      <div class="card-body" id="parentdiv-'+data.topicData[i].TID+'-'+data.topicData[i].LNO+'" >' // card-body start
					+ (i+1) +" - "+ data.topicData[i].TN1
					
					+'  </div>' // card div end
					+'    </div>'//collpase end
					+'  </div>' // collapse 1 end
				}else{
					htm +='  <div class="card">' // card div start
					+'    <div class="card-header area" id="heading-'+data.topicData[i].TID+'-'+data.topicData[i].LNO+'" >'
					+'      <h5 class="mb-0">'
					+'        <a class="collapsed" role="button" data-toggle="collapse" href="#collapse-'+data.topicData[i].TID+'-'+data.topicData[i].LNO+'" aria-expanded="false" aria-controls="collapse-'+data.topicData[i].TID+'-'+data.topicData[i].LNO+'" id="'+data.topicData[i].TID+'-'+data.topicData[i].LNO+'" onClick="getChild('+data.topicData[i].TID+',this.id,'+(i+1)+')">'
					+'<i class="fa fa-book fas fa-2x  float-left book-color" aria-hidden="true"></i>'
					+ '<span class=" topicName"> '+data.topicData[i].TN +' ('+ $.trim(data.topicData[i].TN1) +')</span>'
					+'        </a>'
					+'      </h5>'
					+'    </div>'
					+'    <div id="collapse-'+data.topicData[i].TID+'-'+data.topicData[i].LNO+'" class="collapse"  data-parent="#accordion" aria-labelledby="heading-'+data.topicData[i].TID+'-'+data.topicData[i].LNO+'" >' // collapse 1  start
//					+'      <div class="card-body" id="parentdiv-'+data.topicData[i].TID+'-'+data.topicData[i].LNO+'" >' // card-body start
//					+ (i+1) +" - "+ data.topicData[i].TN1
//					
//					+'  </div>' // card div end

					+ '<div class="card-body">' // card-body start
					+ '<span class="underdev">Under development</span>'
					+ '</div>' // card div end

					+'    </div>'//collpase end
					+'  </div>' // collapse 1 end
				}
			}
			
			htm +='</div>'// accordion div end

			$("#main-div").html(htm);
			
			
		}else{
				// following  code is for intech olympiad students
			
			var htm = '';
			htm +='<h1 class="text-center ">Virtual Maths Lab</h1>'
				
			+'<hr class="mb-0">'
			htm +='<div id="accordion" class="accordion md-accordion accordion-blocks">'// accordion div start
				
//			for(var i = 0; i< data.topicData.length; i++){
				htm +='  <div class="card">' // card div start
				+'    <div class="card-header area top-'+data.topicData[10].TID+'-'+data.topicData[10].LNO+'" " id="heading-'+data.topicData[10].TID+'-'+data.topicData[10].LNO+'" >'
				+'      <h5 class="mb-0">'
				+'        <a class="collapsed" role="button" data-toggle="collapse" href="#collapse-'+data.topicData[10].TID+'-'+data.topicData[10].LNO+'" aria-expanded="false" aria-controls="collapse-'+data.topicData[10].TID+'-'+data.topicData[10].LNO+'" id="'+data.topicData[10].TID+'-'+data.topicData[10].LNO+'" rel="'+itoStud+'" onClick="getChild('+data.topicData[10].TID+',this.id,'+(10+1)+',this.rel)">'  
				+'<i class="fa fa-book fas fa-2x  float-left book-color" aria-hidden="true"></i>'
				+ '<span class=" topicName"> '+data.topicData[10].TN +' ('+ $.trim(data.topicData[10].TN1) +')</span>'
				+'        </a>'
				+'      </h5>'
				+'    </div>'
				+'    <div id="collapse-'+data.topicData[10].TID+'-'+data.topicData[10].LNO+'" class="collapse"  data-parent="#accordion" aria-labelledby="heading-'+data.topicData[10].TID+'-'+data.topicData[10].LNO+'" >' // collapse 1  start
				+'      <div class="card-body" id="parentdiv-'+data.topicData[10].TID+'-'+data.topicData[10].LNO+'" >' // card-body start
				
				+ (10+1) +" - "+ data.topicData[10].TN1
				
				+'  </div>' // card div end
				+'    </div>'//collpase end
				+'  </div>' // collapse 1 end
				
//			}
			
			htm +='</div>'// accordion div end

				$("#main-div").html(htm);
			
		}
		
		
		
		
	
	
		getChild = function(TopicId, parentId, index, itoStud) {
			
			$.ajax({
				type : "GET",
				url : com.coep.test.addProblem.baseURL + "topic/api/level?topicId=" + TopicId,
				dataType : 'json',
				contentType : 'application/json',
				success : function(data) {
//					await sleep(1000);
//					await new Promise(r => setTimeout(r, 2000));
//					window.setTimeout(function(){
//					alert(index);
						HM.rendChildTopic(data, TopicId, parentId, index, itoStud) ;  
//		                  }, 1000);
					
				},
				error : function() {

				}
			});
		}
		
		
		HM.rendChildTopic = function(data, TopicId, parentId, index, itoStud) {
			console.log(data);
			var childHtml = '';
			
//			if (itoStud != "ito@gmail.com" && HM.itoFlag == true) {
				
			if(data.done != false){
			
				childHtml += '<div id="accordion-'+TopicId+'">' //accordion-1  start
				
				for(var i = 0; i< data.data.length; i++){
					
					childHtml +='<div class="card">'
						+'<div class="card-header childArea-'+data.data[i].LNO+'" style="" id="heading-'+(i+1)+'-'+data.data[i].LNO+'">'
						+'<h5 class="mb-0">'
						+'<a class="collapsed" role="button" data-toggle="collapse" href="#collapse-'+(i+1)+'-'+data.data[i].LNO+'" aria-expanded="false" aria-controls="collapse-'+(i+1)+'-'+data.data[i].LNO+'"  id="'+data.data[i].TID+'-'+data.data[i].LNO+'" onClick="getChild('+data.data[i].TID+',this.id,'+(i+1)+')">'
//						+''+index+' > '+ (i+1) +" - "+ data.data[i].TN +" ( "+ data.data[i].TN1 +" ) "
						if (data.data[i].RDNO != undefined) {
							childHtml +=" "+ data.data[i].RTN +" ("+ $.trim(data.data[i].RTN1) +") "
						}else{
							childHtml +=" "+ data.data[i].TN +" ("+ $.trim(data.data[i].TN1) +") "
						}
					
					childHtml +='</a>'
						+'</h5>'
						+'</div>'
						
						childHtml +='<div id="collapse-'+(i+1)+'-'+data.data[i].LNO+'" class="collapse" data-parent="#accordion-'+TopicId+'" aria-labelledby="heading-'+(i+1)+'-'+data.data[i].LNO+'">'
						+' <div class="card-body" id="parentdiv-'+data.data[i].TID+'-'+data.data[i].LNO+'">'
						+'<div class="row"><div class="col-xl-6 col-md-6 col-sm-12 topicTestDivStyle1">'
						+ data.data[i].TN +' ('+ $.trim(data.data[i].TN1) +')'
						+ '</div>'
						+ '<div class="col-xl-6 col-md-6 col-sm-12 topicTestDivStyle2" >'
						
						if (data.data[i].RDNO != undefined) {
//							childHtml += '<button id="mockEligibilityBtn" onClick="javascript:com.coep.test.home.checkEligibiglity('+data.data[i].TID+','+data.data[i].TNO+')" class="btn btn-primary" title="Start Mock Test"><span class="marathi-text" >मॉक टेस्टची पात्रता तपासा</span><br>Check for Mock Test Eligibility</button>'
							childHtml += '<button type="button" class="btn btn-success" onClick="javascript:com.coep.test.home.createPracticeTest('+data.data[i].RDNO +',\''+data.data[i].RTN+'*'+''+data.data[i].RTN1+''+'\')" data-toggle="modal" data-target="#AlertMesConfirm-'+data.data[i].TID+'"   title="Start Practice Test"> <span class="marathi-text" >सराव सुरू करा</span><br>Start Practice Test</button>&nbsp;&nbsp;'
						}else{
//							childHtml += '<button id="mockEligibilityBtn" onClick="javascript:com.coep.test.home.checkEligibiglity('+data.data[i].TID+','+data.data[i].TNO+')" class="btn btn-primary" title="Start Mock Test"><span class="marathi-text" >मॉक टेस्टची पात्रता तपासा</span><br>Check for Mock Test Eligibility</button>'
							childHtml += '<button type="button" class="btn btn-success" onClick="javascript:com.coep.test.home.createPracticeTest('+data.data[i].TID +',\''+data.data[i].TN+'*'+''+data.data[i].TN1+''+'\')" data-toggle="modal" data-target="#AlertMesConfirm-'+data.data[i].TID+'"   title="Start Practice Test"> <span class="marathi-text" >सराव सुरू करा</span><br>Start Practice Test</button>&nbsp;&nbsp;'	
						}
						
					childHtml += '</div>' 
						+'</div>' ///row end
						+'</div>'
						+'</div>'
						
						+'</div>'
				}
				
				childHtml +='    </div>'//accordion-1  end
					
					$("#parentdiv-"+parentId).html(childHtml);
				
//					setTimeout(console.log(""), 1000); 
					$("#Loading").css("display","none");
					
				}
		}
		
	}
	
	HM.checkEligibiglity = function(topicId, topicNo) {
		$.ajax({
			type: "GET",
			url : com.coep.test.addProblem.baseURL
					+ "mockTest/generate/tag/"+topicId+"/"+topicNo,
			dataType: 'json',
			contentType: 'application/json',
			success: function(data) {
				console.log(data);
			},
			error: function() {

			}
		});
	}
	
	AM.confirmationFromHomePage = function(names, tArray) {

		$("#Loading").css("display","none");
		
		if (names != "") {
		var topicNames = names.split("!!");
		
		var AlertComfirmFlag = false;
			AlertMsg = "Some earlier topic/s need your attention. They are pending and are as follows...<br>या आधीचे काही विभाग अजून अपुरे आहेत. तुम्ही ते पूर्ण करणे गरजेचे आहे<br><br>"
				for (var i = 0; i < topicNames.length; i++) {
					if (topicNames[i] != "") {                               																																												//data.data[i].TN1+''+'\')  +''+'\'
						AlertMsg +="<div><span class='red-color'>"+topicNames[i].split("*")[0]+" ("+topicNames[i].split("*")[1]+")"+"</span><div><center><button type='button' class='btn btn-success' style='margin-top: 10px;font-size: 18px;font-weight: bold;' data-dismiss='modal' onClick='javascript:com.coep.test.home.openTopic("+topicNames[i].split("*")[2]+",\""+topicNames[i]+""+"\")'>Start Practice Test</button></center></div> </div><br><br> ";
					}
					
				}
			
			var AlertMesConfirm = ''
				AlertMesConfirm +=  '<div class="container-fluid">'
				+ '<div class="row">'
				+ '<div  class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'
				//Alert modal start
						+ '<div class="modal" id="AlertMesConfirm">'
						+ '<div class="modal-dialog">'
						+ '<div class="modal-content">'
			            
			            	 +' <div class="modal-header bg-info ">'
								+ '   <h4 class="modal-title ModelHeaderStyle">Confirmation !!</h4>'
//								+ '  <button type="button" class="close" data-dismiss="modal">&times;</button>'
								+ '  </div>'
				
								+ '   <div class="modal-body">'
								+ '<span id="AlertMsgStyle">'+AlertMsg+'</span>'
							    + ' </div>'
			                +'    <div class="modal-footer">'
//			                + '<button type="button" class="btn btn-success" data-dismiss="modal" id="AlertComfirmYes" onClick="AlertComfirmYes()">Yes<span class="marathi-text" > ( होय )</span></button>&nbsp;&nbsp;'
			                + '<div>If you do not want to continue with above topics. press </div>'
			                + '<button type="button" class="btn btn-danger" data-dismiss="modal">No<span class="marathi-text" > ( नाही )</span></button>'
							+ '</div>'
			
						  + '    </div>'
						  + '  </div>'
						  + '</div>'
				//alert modal end
				+ '</div>' // subject_content close
				+ '</div>'// main row close
				+ '</div>' // container close
				
			
				$("#main-div").append(AlertMesConfirm);
			
			
			
				setTimeout(function() {
						$('#AlertMesConfirm').modal({
							backdrop: 'static',
						    keyboard: false 
						});
					},1000);
			
			}
				AlertComfirmYes = function()
				{
					AlertComfirmFlag = true;
					return AlertComfirmFlag;
				}
			
		
		}
	
	
	HM.openTopic = function(topicId, TN) {
		console.log(TN);
//		$.ajax({
//			type : "GET",
//			url : com.coep.test.addProblem.baseURL + "topic/api/get/parent?topicId=" + topicId,
//			dataType : 'json',
//			contentType : 'application/json',
//			success : function(data) {
//				console.log(data);
//				if (data.done == true) {
//					HM.renderOpenTopic(data.data, data.TNODATA ,topicId);
//				}
//				
//			},
//			error : function() {
//
//			}
//		});
		
		HM.createPracticeTest(topicId, TN , true);
//		childHtml += '<button type="button" class="btn btn-success" onClick="javascript:com.coep.test.home.createPracticeTest('+data.data[i].TID +',\''+data.data[i].TN+'*'+''+data.data[i].TN1+''+'\')" data-toggle="modal" data-target="#AlertMesConfirm-'+data.data[i].TID+'"   title="Start Practice Test"> <span class="marathi-text" >सराव सुरू करा</span><br>Start Practice Test</button>&nbsp;&nbsp;'	
		
	}
	
	
	HM.renderOpenTopic = function(data, TopicNoData, topicId) {

		var j = data.length;
		for (var i =  (data.length - 1);  i >= 0;  i--) {
//			alert(data[i][j]);
//			var temp = data[i][j].split("-");
//			setTimeout($("#"+data[i][j]).click(), 1000); 
		
			$("#"+data[i][j]).click();
			
//			getChild(temp[0],data[i][j] ,TopicNoData[temp[0]],"ito@gmail.com");
			j--;
			
//			if (data.length > 1) {
//				break;
//			}
		}
		
	}
	
	HM.createPracticeTest = function(topicId, topicName,  homePageFlag) {
		
		
		var childHtml = '<div class="overlay" id="Loading">'
			 + '<div class="overlay__inner">'
			 + '<div class="overlay__content"><span class="spinner"></span><br/><span class="loading">LOADING....</span></div>'
			 + '</div>'
			 + '</div>'
			 
			 $("#main-div").append(childHtml);
		
		var selectedTopicJSON = {};
		var configureTestJSON = {};
		var standard  = {}; 
		var testType = {}; 
		
		var seltopicId = [];
		
		var testStartDate = new Date();
		var testEndDate = new Date();
		
		seltopicId.push(topicId);
		
		selectedTopicJSON = seltopicId.join();
		
		standard.stdId = 1;
		testType.testTypeId = 1;
		
		configureTestJSON.name = topicName;
		configureTestJSON.active = 1;
		configureTestJSON.selectedTopics = selectedTopicJSON;
		configureTestJSON.standard = standard;
		configureTestJSON.testType = testType;
		
		configureTestJSON.startDate = testStartDate;
		configureTestJSON.endDate = testEndDate;
		
//		console.log(JSON.stringify(configureTestJSON));
//		console.log(configureTestJSON);
		
		$.ajax({
			type : "POST",
			url : com.coep.test.addProblem.baseURL + "configureTestPaper",
			data : JSON.stringify(configureTestJSON),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {

				if (data.done == true) {
					
					AM.confirmationToStartTest(topicId, topicName, data, homePageFlag);
					$('#AlertMesConfirm-'+topicId).modal();
					
				} else {
//					console.log(data);
					$("#lastTopicMsg").html(data.msg);
					$("#moveToNextTest").hide();
					$("#homePage").show();
//					goToNextTopic.show();
					toastr.success(data.msg);
					
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

	}
	
	AM.confirmationToStartTest = function(topicId, topicName, data, homePageFlag) {

		if (!homePageFlag) {
			$("#Loading").css("display","none");
			var topicNames = topicName.split("*");
			
			var AlertComfirmFlag = false;
				AlertMsg = "To start a practice test on topic <span class='red-color'>"+topicNames[1]+"</span> click on the Yes button. <br><br><span class='red-color'>"+topicNames[0]+"</span> श्रेणी वर सराव चाचणी सुरू करण्यासाठी होय बटणावर क्लिक करा.";
				var AlertMesConfirm = ''
					AlertMesConfirm +=  '<div class="container-fluid">'
					+ '<div class="row">'
					+ '<div  class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'
					//Alert modal start
							+ '<div class="modal" id="AlertMesConfirm-'+topicId+'">'
							+ '<div class="modal-dialog">'
							+ '<div class="modal-content">'
				            
				            	 +' <div class="modal-header bg-info ">'
									+ '   <h4 class="modal-title ModelHeaderStyle">Confirmation !!</h4>'
									+ '  <button type="button" class="close" data-dismiss="modal">&times;</button>'
									+ '  </div>'
					
									+ '   <div class="modal-body">'
									+ '<span id="AlertMsgStyle">'+AlertMsg+'</span>'
								    + ' </div>'
				                +'    <div class="modal-footer">'
				                + '<button type="button" class="btn btn-success" data-dismiss="modal" id="AlertComfirmYes" onClick="AlertComfirmYes()">Yes<span class="marathi-text" > ( होय )</span></button>&nbsp;&nbsp;'
								+ '<button type="button" class="btn btn-danger" data-dismiss="modal" onClick="AlertComfirmNo()" >No<span class="marathi-text" > ( नाही )</span></button>'
								+ '</div>'
				
							  + '    </div>'
							  + '  </div>'
							  + '</div>'
					//alert modal end
					+ '</div>' // subject_content close
					+ '</div>'// main row close
					+ '</div>' // container close
					
				
					$("#main-div").append(AlertMesConfirm);
					$("#practiceTestDiv").append(AlertMesConfirm);
//					HM.createPracticeTest(topicId, topicName, data);
				
					AlertComfirmYes = function()
					{
						AlertComfirmFlag = true;
						HM.startPracticeTest(topicId, topicName, data);
						return AlertComfirmFlag;
					}
					
					AlertComfirmNo = function()
					{
						AlertComfirmFlag = true;
						window.location.href ="home";
						return AlertComfirmFlag;
					}
			}else{
				HM.startPracticeTest(topicId, topicName, data);
			}
		
		}
	
	
	
	HM.startPracticeTest = function(topicId, topicName, data) {
		var testTypeId = 1; // practice test typeId
		PT.createPracticeTest(topicId, topicName, testTypeId, data );
	}
	
	
	HM.goToHomePage = function() {
//		window.location.reload(true);
		window.location.href ="home";
	}

})(com.coep.test.home, com.coep.test.AlertMessage,com.coep.test.practiceTest);