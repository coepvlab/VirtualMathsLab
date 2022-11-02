(function(AH, MT, ST) {
	
	
	MT.getAllDepartment = function(){
		$("#main-div").html('');
		$("#listofuser-div").show();
		AH.getAllDepartmentList(ST.baseURL+"departments", "GET");
		
	}
	
	
    MT.showAllDepartmentList = function(departmentJson){
    	$("#main-div").html('');
    	$("#listofuser-result-div").html('');
 		
        var len = Object.keys(departmentJson.data).length;
  		
  		var deptlistHtm = '';
  		deptlistHtm += '<div  style=" margin: 50px 50px 50px 0px;"><legend class="darkbrown text-align-center" style="font-weight:bold;">Select Department</legend>'
//			+'<a href="'+ ST.baseURL +'media/getResultPdf?mediaID=596f0888b0c2f98cf94b1c18&userId=6552&testlevel=1" target="_blank">Download</a>'	
  			+'<div class="row">'
			+'<div class="col-sm-12 col-md-6 col-md-offset-3">'
  			+'<select name="deptListID" id="deptListID" class="form-control select-class"  title="Choose Value" style="margin-top: 20px; width:100%;">'
					+ '<option value=\"-1\">Choose Value</option>';
  	   +'</div> </div>'
			for (var l = 0; l < len; l++){
				deptlistHtm += '<option value="'+departmentJson.data[l].DID+'">'+departmentJson.data[l].DN+'</option>';
			}
			deptlistHtm+='</select></div><div id = "grpDetails"></div></div>';
		
			deptlistHtm+='</div>';
			$("#listofuser-div").html(deptlistHtm);
			
			$("#listofuser-div select").chosen();
			   
		    $("select#deptListID").on('change', function(){
			     var userId = $("#deptListID").val();
			     if(userId == "-1"){
			    	 $("#main-div").html('');
			     }else{
			    	
			    
			 var sectionHtm = '<div id="sectionDetails"><legend class="darkbrown text-align-center" style="font-weight:bold;">Add Section Name</legend><br>'
				 +'<div class="row">'
					+'<div class="col-sm-12 col-md-10 col-md-offset-1">'          
				 +'<input class="qgroup-input" type="text" title="Add Section Name" id="sectionID" name="sectionID" style="width:90%;"><span class="glyphicon glyphicon-edit"></span>'
				   +'</div> '       
			
				   +'<div class="col-sm-12 col-md-2 col-md-offset-8">'    
			     sectionHtm +='<br><br><br><button class="btn btn-raised custom-btn" id="addSection"'
			    	 		+' onclick="com.coep.test.modifyStudentTest.addSection()">Add Section</button>'
			    	        +'</div>'
			     }
			     +'</div> </div>'
			     $("#main-div").html(sectionHtm);
		    });
  		
    	
    }
	
    
    MT.addSection =	function (){
 	   
 	   if($("#sectionID").val() != ''){
 	   
 	   var deptID =  $("#deptListID").val();
 		 var department = {};
 		 department.departmentId = deptID;
 		 var secName =   $("#sectionID").val();
 	   
 	   var secJSON = {};
 		 secJSON.name = secName;
 		 secJSON.department = department;
 		 console.log(secJSON);
 		AH.addSection(ST.baseURL+'sections', 'POST', secJSON);
 	   }else{
 		   
 		   showToast.show('Enter Section Name',false); 
 		   
 	   }
 	};
 	
	
	
	MT.getAllLinkGeneratedStudentList = function() {
		$("#main-div").html('');
		$(".child-menu-quesbank").hide();
		$(".child-menu-configTest").hide();		
		$("#listofGroup-div").hide();
		$("#listofuser-result-div").hide();
		$("#listofuser-div").show();
		
		AH.getAllLinkGeneratedStudentListfromTestInstanceState(ST.baseURL+"api/create/getAll/linkGeneratedStudentList","GET");
	}
	
	MT.allStudentList = function(userListJon){
		
	   $("#main-div").html('');
		
       var len = Object.keys(userListJon.data).length;
		
		var userlistHtm = '';
		
		userlistHtm += '<div  style=" margin: 50px 50px 50px 0px;"><legend class="darkbrown text-align-center" style="font-weight:bold;">Select Student</legend>'
//			+'<a href="'+ ST.baseURL +'media/getResultPdf?mediaID=596f0888b0c2f98cf94b1c18&userId=6552&testlevel=1" target="_blank">Download</a>'	
			+'<div class="row">'
			+'<div class="col-sm-12 col-md-6 col-md-offset-3">'
			+'<select name="userListID" id="userListID" class="form-control select-class"  title="Choose Value" style="margin-top: 20px; ">'
					+ '<option value=\"-1\">Choose Value</option>';
		    +'</div> </div>'
			for (var l = 0; l < len; l++){
				userlistHtm += '<option value="'+userListJon.data[l].UID+'">'+userListJon.data[l].EMAIL+'-'+userListJon.data[l].UID+'</option>';
			}
			userlistHtm+='</select></div><div id = "grpDetails"></div></div>';
		
			userlistHtm+='</div>';
			$("#listofuser-div").html(userlistHtm);
			
			$("#listofuser-div select").chosen();
			   
		    $("select#userListID").on('change', function(){
			     var userId = $("#userListID").val();
			     if(userId == "-1"){
			    	 $("#main-div").html('');
			     }else{
			    	 AH.getuserTestInstanceDetails(ST.baseURL+"api/create/getAll/userTestInfo","GET",userId);
			     }
		    });
	}
	
	MT.renderAllDetails = function(userData, userId) {
		var upFlag = 0;
		document.title = "Student Test Details";
		if(userData.done != false ){
			var len = Object.keys(userData.data).length != 0
			var testDetailHtm = '<div id="userdetails"><legend class="datkbrown" style="font-weight:bold;">Test Details</legend><br>'
				+'<table class="table"><thead><tr><th >Status</th>'
				+'<th>Start Date</th>'
				+'<th>End Date</th>'
				+'<th>PDF Status</th>'
				+'<th>Test Level</th>'
				+'<th>Department</th>'
				+'<th style="width:115px;">Modify</th>'
				+'</tr></thead><tbody>'
				for (var i = 0; i < Object.keys(userData.data).length; i++) {
					
					testDetailHtm  +=  '<tr id="'+userData.data[i].TESTID+'">'
					
					+'<td><input disabled style="border:none;" type="text" title="Click here to modify status" id="status'+i+'" class="status'+i+'"'
					+'value="'+userData.data[i].STATUS+'"></td>'
					+'<td><input disabled style="border:none;" type="text" title="Click here to modify start date" id="sd'+i+'" class="sd'+i+'"'
					+'value="'+userData.data[i].STARTDATE+'"></td>'
					+'<td><input disabled style="border:none;" type="text" title="Click here to modify end date" id="ed'+i+'" class="ed'+i+'"'
					+'value="'+userData.data[i].ENDDATE+'"></td>'
					+'<td><span>'+userData.data[i].PDFSTATUS+'</span></td>'
					+'<td><span>'+userData.data[i].TESTLEVEL+' - '+userData.data[i].TESTNAME+'</span></td>'
					+'<td><span>'+userData.data[i].DEPT+'</span></td>'
					+'<td><span onClick="modifyTest('+i+')" class="icon-edit-group prim-blue" title="Modify Test" id="modifyTestDetail'+i+'"  style="cursor:pointer;"></span>'
					+'<span onClick="updateTestDetails('+i+','+userData.data[i].TEST_IINST_ID+')" class="green-color" title="Update Test" id="updateTestDetail'+i+'" style="cursor:pointer;" hidden>Save</span>'
					+'&emsp;<span id="cancelUpdate'+i+'" onClick="cancelUpdation('+i+')" title="Cancel" hidden><img src="resource/images/icons/cancel.png" style="width:20px; cursor:pointer;"></span></td></tr>';
					
					cancelUpdation = function (i) {
						upFlag = 0;	
						$(".sd"+i).prop("disabled", true);
						$(".ed"+i).prop("disabled", true);
						$(".status"+i).prop("disabled", true);
						$("#updateTestDetail"+i).hide();
						$("#modifyTestDetail"+i).show();
						$("#cancelUpdate"+i).hide();
						AH.getuserTestInstanceDetails(ST.baseURL+"api/create/getAll/userTestInfo","GET",userId);
					}
					
					modifyTest = function(i){
						if(upFlag == 0){
						upFlag = 1;	
						var d = userData.data[i].STARTDATE
					    $(".sd"+i).datetimepicker({  
					        showSecond: false,
					        dateFormat: 'yy-mm-dd',
					        timeFormat: 'hh:mm tt',
					        ampm: true,
					    }).datetimepicker("setDate", d);
					    
						 var d1 = userData.data[i].ENDDATE;
						  $(".ed"+i).datetimepicker({  
						      showSecond: false,
						      dateFormat: 'yy-mm-dd',
						      timeFormat: 'hh:mm tt',
						        ampm: true,
						  }).datetimepicker("setDate", d1);
						
						$(".status"+i).prop("disabled", false);
						$(".sd"+i).prop("disabled", false);
						$(".ed"+i).prop("disabled", false);
						$("#modifyTestDetail"+i).hide();
						$("#updateTestDetail"+i).show();
						$("#cancelUpdate"+i).show();
						}else{
							showToast.show("Please save current unsaved record.", false);
						}
					}
					
					updateTestDetails = function(i, id) {
						upFlag = 0;	
						var jsonTestUpdate = {};
						
						var status = $("#status"+i).val();
						var startDate = new Date($("#sd"+i).val()).getTime();
						var endDate = new Date($("#ed"+i).val()).getTime();
						
						jsonTestUpdate.testInstanceStateId = id;
						jsonTestUpdate.status = status;
						jsonTestUpdate.plannedStartTime = startDate;
						jsonTestUpdate.plannedTestEndTime = endDate;
						
						AH.updateTestInstanceStateDetails(ST.baseURL + "updateTestInstanceState", "PUT", jsonTestUpdate, userId);
				       
						$(".sd"+i).prop("disabled", true);
						$(".ed"+i).prop("disabled", true);
						$(".status"+i).prop("disabled", true);
						$("#updateTestDetail"+i).hide();
						$("#modifyTestDetail"+i).show();
						$("#cancelUpdate"+i).hide();
					}
				}
				
			testDetailHtm  += '</tbody></table></div>';
			
			$("#main-div").html(testDetailHtm);
		}else{
			$("#main-div").html('<span class="red-color text-align-center"><h3>NO TEST LINK IS GENERATED FOR THIS USER</h3></span>');
		}
	}
	
})(com.coep.test.ajaxHandler, com.coep.test.modifyStudentTest, com.coep.test.student);