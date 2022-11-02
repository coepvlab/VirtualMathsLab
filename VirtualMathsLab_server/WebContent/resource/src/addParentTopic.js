(function(AH, AT) {
	
	
	AT.getTopicListToAddParentTopics = function() {

		$.ajax({
			type : "GET",
			url : com.coep.test.addProblem.baseURL
					+ "topic/api/get/topics",
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				AT.selectParOrPre(data);
			},
			error : function() {
			}

		});
	}
	
	AT.selectParOrPre = function(data) {
		
		var parHtm = ''
			parHtm += '<div class="container-fluid">'
					+ '<div class="row">'
					+ '<div id="parent_content" class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'
					+ '<section class="section-preview">'
					+ '<h1>ASSIGN TOPIC</h1>'
					+ '<div class="custom-control custom-radio custom-control-inline">'
					+ '<input type="radio" class="custom-control-input" id="parent" name="topicGroup">'
					+ '<label class="custom-control-label" for="parent">Parent</label>'
					+ '</div>'

					+ '<div class="custom-control custom-radio custom-control-inline">'
					+ '<input type="radio" class="custom-control-input" id="prerequisite" name="topicGroup">'
					+ '<label class="custom-control-label" for="prerequisite">Prerequisite</label>'
					+ '</div>'


					+ '<div class="row">'
					+ '<div id="parentPreDiv" class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'

					+ '</div>'

//					+ '<div id="showLevel" class="col-sm-12 col-md-6 col-lg-6 col-xl-6">'

					+ '</div>' + '</div>' + '</section>'

					+ '</div>' // parent_content close
					+ '</div>'// main row close
					+ '</div>' // container close
					
			$("#main-div").html(parHtm);
		
		
		var parentOrPre = $("input:radio[name=topicGroup]");

		parentOrPre.on("change", function() {
			
			var selected_Id = $('input[name="topicGroup"]:checked').attr('id');

			if (selected_Id == "parent") {
				
					$.ajax({
						type : "GET",
						url : com.coep.test.addProblem.baseURL
								+ "topic/api/get/topicMapping?status=" + 1,
						dataType : 'json',
						contentType : 'application/json',
						success : function(tmdata) {
							AT.assignParent(data, "1", tmdata);
						},
						error : function() {
						}

					});
				
			} else if (selected_Id == "prerequisite") {
				$.ajax({
					type : "GET",
					url : com.coep.test.addProblem.baseURL
							+ "topic/api/get/topicMapping?status=" + 2,
					dataType : 'json',
					contentType : 'application/json',
					success : function(tmdata) {
						AT.assignPrerequisite(data, "2", tmdata);
					},
					error : function() {
					}

				});
				

			}  else {
				
				$("#parentPreDiv").html("");
			}

		});
		
	}
	
	
	
	AT.assignParent = function(data, status, tmdata) {
		if (data.done == false) {
			alert("Please add topics first.");
			$("#parentPreDiv").html("<div class='col-xl-12 col-md-12 col-sm-12 alert alert-danger' style='text-align:center;'>No topics are availables to assign parent..</div>");
		} else {
			var htm = '<div>';
			htm += ''
					+ '<form id="ParentDataForm" novalidate>'
					+ '<div class="form-group" >'
					 + '<label class="col-xl-12 col-md-12 col-sm-12" for="selectParent">Select Parent Topic :</label>'
					+ ' <select class=" form-control col-xl-12 col-md-12 col-sm-12 selectpicker" data-live-search="true"  id="selectParent"  required >'
					+ ' <option value="0"  >Select Parent Topic</option>'

			for (var i = 0; i < data.topicData.length; i++) {
				htm += '' + '  <option value="' + data.topicData[i].TID
						+ '" id ="' + data.topicData[i].TID + '" >'
						+ data.topicData[i].TNO +" - "+ data.topicData[i].TN + ' </option>'
			}

			htm += ' </select>'
					+ '<div id="error" class="invalid-feedback">Please select parent topic.</div>'
					+ '</div>'
					
					+ '<div class="form-group" >'
					 + '<label class="col-xl-12 col-md-12 col-sm-12" for="selectTopics">Select Topics :</label>'
					+ ' <select class=" form-control col-xl-12 col-md-12 col-sm-12 selectpicker show-tick" data-live-search="true"  id="selectTopics"  multiple  required >'

					for (var i = 0; i < data.topicData.length; i++) {
				htm += '' + '  <option value="' + data.topicData[i].TID
						+ '" id ="' + data.topicData[i].TID + '" >'
						+ data.topicData[i].TNO +" - "+ data.topicData[i].TN + ' </option>'
			}

			htm += ' </select>'
					+ '<div id="error" class="invalid-feedback">Please select topics.</div>'
					+ '</div>'

					+ '<button id= "assignTopicBtn" type="submit" class="btn btn-dark">Submit</button>'
					+ '<button type="submit" class="btn btn-dark" id="updateTopicBtn" style="display:none;">Update</button>'
					+ '</div>'

					+ '</form>'
					+ '<div id="showTopics" class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'
					+ '</div>'

			$("#parentPreDiv").html(htm);

			bsSelectValidation();
			$('select').selectpicker();
			
			getRenderDataTableWithTopicDetails(status);
		}
		
		$("#selectParent").on('change', function(){ 
			var TMAr = [];
			for (var a = 0; a < tmdata.topicMap.length; a++) {

				if ($(this).val() == tmdata.topicMap[a].PTMID) {
					TMAr.push(tmdata.topicMap[a].TTMID);
				}
			}

			$('#selectTopics').selectpicker('val', TMAr);
		});
		
		$('#selectTopics').on('change', function(){
		    var selected = $(this).find("option:selected");
		    arrSelected  = [];
		    selected.each(function(){
		    	arrSelected.push(parseInt($(this).val()));
		    });
		});						
				
		
		$("#assignTopicBtn").on(
				"click",
				function(e) {
//					var form1 = $("#ParentDataForm")[0];
//					console.log(form1.properties[0]);
					var form = document.getElementById('ParentDataForm');
					form.addEventListener('submit', function(e) {
						if (form.checkValidity() === false) {
							e.preventDefault();
							e.stopPropagation();
						}
						form.classList.add('was-validated');
					}, false);

					if (form.checkValidity() === true) {

						parentId = $.trim($("#selectParent option:selected")
								.attr('id'));
						
						parentId = parseInt(parentId);

						var topicId = "["
						for (var i = 0; i < arrSelected.length; i++) {
							if(i == (arrSelected.length - 1)){
								topicId += arrSelected[i]+"]"
							}else{
								topicId += arrSelected[i]+","
							}
						}
						
						
//						console.log(""+status+"/"+parentId+"/"+topicId);
							$.ajax({
								type : "POST",
								url : com.coep.test.addProblem.baseURL
										+ "topic/api/mapping/parent/"+status+"/"+parentId+"/"+topicId,
								dataType : 'json',
								contentType : 'application/json',
								success : function(data) {
									getRenderDataTableWithTopicDetails(status);
									$("#selectParent").val(0).change();
									$("#selectTopics").prop('selectedIndex', -1);
									$('.selectpicker').selectpicker('refresh');
									alert(data.msg);
								},
								error : function() {
								}
							});
							
						e.preventDefault();
					}

				}); // submit btn ends here
		
		
	}
	
	getRenderDataTableWithTopicDetails = function(status){
		$.ajax({
			type : "GET",
			url : com.coep.test.addProblem.baseURL
					+ "topic/api/get/topicMapping?status=" + status,
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				AT.renderTopicDetails(data, status);
			},
			error : function() {
			}

		});
	}
	
	AT.renderTopicDetails = function(data, status) {

//		$('#TopicDataForm')[0].reset();
//		$("#TopicDataForm").removeClass('was-validated');
		var renderHtm = '';
//
		renderHtm += '<h1>TOPIC MAPPING DETAILS</h1>'
				+ '<div class="table-responsive">'
				+ '<table id="topicData" class="table table-striped table-bordered" style="width:100%">'
				+ '<thead>' + ' <tr>' 
				if(status == 1){
					renderHtm += '<th width="42%">Parent Topic Name</th>'
					+ '<th width="43%">Topic Name</th>'
				}else if(status == 2){
					renderHtm += '<th width="42%">Topic Name</th>'
					+ '<th width="43%">Prerequisite Topic Name</th>'
				}
				
		renderHtm += '<th width="5%">Action</th>' + '   </tr>' + '</thead>'
				+ ' <tbody>'
		for (var i = 0; i < data.topicMap.length; i++) {
			renderHtm += '<tr>' 
				if(status == 1){
					renderHtm += '<td width="42%">' +  data.topicMap[i].PTMNO + ' - ' + data.topicMap[i].PTMNM + '</td>' 
					+ '<td width="43%">' +  data.topicMap[i].TTMNO + ' - ' + data.topicMap[i].TTMNM + '</td>'
				}else if(status == 2){
					renderHtm +=  '<td width="43%">' +  data.topicMap[i].TTMNO + ' - ' + data.topicMap[i].TTMNM + '</td>'
					+'<td width="42%">' +  data.topicMap[i].PTMNO + ' - ' + data.topicMap[i].PTMNM + '</td>' 
				}
			renderHtm += '<td width="5%">'
					+'<button onClick = "getDataToModify('+ data.topicMap[i].PTMID +','+ data.topicMap[i].TTMID +','+ status +')" type="submit" class="btn btn-success AcT_ArchIcon"><i class="fa fa-pencil-square-o " aria-hidden="true"></i></button>'
					+ '&nbsp;<button onClick = "deleteTopicMappingData('+ data.topicMap[i].TMID +','+ status +')" type="submit" class="btn btn-danger AcT_ArchIcon"><i class="fa fa-trash-o" aria-hidden="true"></i></button>' 
					+ '</td>' + '</tr>'
		}
		renderHtm += '    </tbody>'
//
		+ '</table>' + '</div>'
		$("#showTopics").html(renderHtm);
		$("#showTopics").ready(function() {
			var table = $('#topicData').DataTable({
				"pageLength" : 7,
				// lengthChange: false,
				buttons : [ 'copy', 'excel', 'pdf' ]
			});

			// table.buttons().container()
			// .appendTo( '#example_wrapper .col-md-6:eq(0)' );
		});
		

		getDataToModify = function(PID, TID, status) {
			if (status == 1) {
				
				$("#assignTopicBtn").hide();
				$("#updateTopicBtn").css("display", "block");
				$("#selectParent").val(PID).change();

				var TMAr = [];
				for (var a = 0; a < data.topicMap.length; a++) {

					if (PID == data.topicMap[a].PTMID) {
						TMAr.push(data.topicMap[a].TTMID);
					}
				}

				$('#selectTopics').selectpicker('val', TMAr);
				
				$("#updateTopicBtn").on("click", function(e){
					
						var form1 = $("#ParentDataForm")[0];
//						console.log(form1);
						var form = document.getElementById('ParentDataForm');
						form.addEventListener('submit', function(e) {
							if (form.checkValidity() === false) {
								e.preventDefault();
								e.stopPropagation();
							}
							form.classList.add('was-validated');
						}, false);

						if (form.checkValidity() === true) {
					
					parentId = $.trim($("#selectParent option:selected")
							.attr('id'));
					
					parentId = parseInt(parentId);

					var arrSelected = [];
			        $.each($("#selectTopics option:selected"), function(){            
			        	arrSelected.push($(this).val());
			        });
			        
					var topicId = "["
					for (var i = 0; i < arrSelected.length; i++) {
						if(i == (arrSelected.length - 1)){
							topicId += arrSelected[i]+"]"
						}else{
							topicId += arrSelected[i]+","
						}
					}
					
					var removedTM = TMAr.filter(item=>topicId.indexOf(item)==-1);
				    
//					console.log(""+status+"/"+parentId+"/"+topicId+"/");
					
					parentTMJson = {
							"status" : status.toString(),
							"parentId" : parentId,
							"topicId" : arrSelected,
							"removedTMId" : removedTM,
					}
					
						$.ajax({
							type : "PUT",
							url : com.coep.test.addProblem.baseURL
									+ "topic/api/mapping/parent/update",
							data : JSON.stringify(parentTMJson),
							dataType : 'json',
							contentType : 'application/json',
							success : function(data) {
								getRenderDataTableWithTopicDetails(status);
								$("#selectParent").val(0).change();
								$("#selectTopics").prop('selectedIndex', -1);
								$('.selectpicker').selectpicker('refresh');
								$("#assignTopicBtn").show();
								$("#updateTopicBtn").css("display", "none");
								alert(data.msg);
							},
							error : function() {
							}
						});
					e.preventDefault();
						}
				});
				
			}
			
			if (status == 2) {
				
				$("#assignPreTopicBtn").hide();
				$("#updatePreTopicBtn").css("display", "block");

				$("#selectTopic").val(TID).change();
				var TMAr = [];
				for (var a = 0; a < data.topicMap.length; a++) {

					if (TID == data.topicMap[a].TTMID) {
						TMAr.push(data.topicMap[a].PTMID);
					}
				}
				$('#selectPreTopics').selectpicker('val', TMAr);
				
				$("#updatePreTopicBtn").on("click", function(e){
					
					var form1 = $("#PrerequisiteDataForm")[0];
//					console.log(form1);
					var form = document.getElementById('PrerequisiteDataForm');
					form.addEventListener('submit', function(e) {
						if (form.checkValidity() === false) {
							e.preventDefault();
							e.stopPropagation();
						}
						form.classList.add('was-validated');
					}, false);

					if (form.checkValidity() === true) {
					parentId = $.trim($("#selectTopic option:selected")
							.attr('id'));
					
					parentId = parseInt(parentId);

					var arrSelected = [];
			        $.each($("#selectPreTopics option:selected"), function(){            
			        	arrSelected.push($(this).val());
			        });
			        
					var topicId = "["
					for (var i = 0; i < arrSelected.length; i++) {
						if(i == (arrSelected.length - 1)){
							topicId += arrSelected[i]+"]"
						}else{
							topicId += arrSelected[i]+","
						}
					}
					
					var removedTM = TMAr.filter(item=>topicId.indexOf(item)==-1);
				    
//					console.log(""+status+"/"+parentId+"/"+topicId+"/");
					
					parentTMJson = {
							"status" : status.toString(),
							"parentId" : parentId,
							"topicId" : arrSelected,
							"removedTMId" : removedTM,
					}
					
						$.ajax({
							type : "PUT",
							url : com.coep.test.addProblem.baseURL
									+ "topic/api/mapping/prerequisite/update",
							data : JSON.stringify(parentTMJson),
							dataType : 'json',
							contentType : 'application/json',
							success : function(data) {
								getRenderDataTableWithTopicDetails(status);
								$("#selectTopic").val(0).change();
								$("#selectPreTopics").prop('selectedIndex', -1);
								$('.selectpicker').selectpicker('refresh');
								$("#assignPreTopicBtn").show();
								$("#updatePreTopicBtn").css("display", "none");
								alert(data.msg);
							},
							error : function() {
							}
						});
					e.preventDefault();
					}
				})
			}
		}
		
		deleteTopicMappingData = function(tmId, status){
			$.ajax({
				type : "DELETE",
				url : com.coep.test.addProblem.baseURL
						+ "topic/api/mapping/delete/"+tmId,
				dataType : 'json',
				contentType : 'application/json',
				success : function(data) {
					$('.selectpicker').selectpicker('refresh');
					alert(data.msg);
					getRenderDataTableWithTopicDetails(status);
				},
				error : function() {
				}
			});
		}
	}
	
	
	
	
	
	AT.assignPrerequisite = function(data, status,tmdata) {
		
		if (data.done == false) {
			alert("Please add topics first.");
			$("#parentPreDiv").html("<div class='col-xl-12 col-md-12 col-sm-12 alert alert-danger' style='text-align:center;'>No topics are availables to assign prerequisite..</div>");

		} else {
			var htm = '<div>';
			htm += ''
					+ '<form id="PrerequisiteDataForm" novalidate>'
					+ '<div class="form-group" >'
					 + '<label class="col-xl-12 col-md-12 col-sm-12" for="selectTopic">Select Topic :</label>'
					+ ' <select class=" form-control col-xl-12 col-md-12 col-sm-12 selectpicker" data-live-search="true"  id="selectTopic"  required >'
					+ ' <option value=""  >Select Topic</option>'

			for (var i = 0; i < data.topicData.length; i++) {
				htm += '' + '  <option value="' + data.topicData[i].TID
						+ '" id ="' + data.topicData[i].TID + '" >'
						+ data.topicData[i].TNO +" - "+ data.topicData[i].TN + ' </option>'
			}

			htm += ' </select>'
					+ '<div id="error" class="invalid-feedback">Please select topic.</div>'
					+ '</div>'
					
					+ '<div class="form-group" >'
					 + '<label class="col-xl-12 col-md-12 col-sm-12" for="selectPreTopics">Select Prerequisite Topics :</label>'
					+ ' <select class=" form-control col-xl-12 col-md-12 col-sm-12 selectpicker show-tick" data-live-search="true"  id="selectPreTopics"  multiple  required >'

			for (var i = 0; i < data.topicData.length; i++) {
				htm += '' + '  <option value="' + data.topicData[i].TID
						+ '" id ="' + data.topicData[i].TID + '" >'
						+ data.topicData[i].TNO +" - "+ data.topicData[i].TN + ' </option>'
			}

			htm += ' </select>'
					+ '<div id="error" class="invalid-feedback">Please select prerequisite topics.</div>'
					+ '</div>'

					+ '<button id= "assignPreTopicBtn" type="submit" class="btn btn-dark">Submit</button>'
					+ '<button type="submit" class="btn btn-dark" id="updatePreTopicBtn" style="display:none;">Update</button>'
					+ '</div>'

					+ '</form>'
					+ '<div id="showTopics" class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'
					+ '</div>'

			$("#parentPreDiv").html(htm);

			bsSelectValidation();
			$('select').selectpicker();
			
			getRenderDataTableWithTopicDetails(status);
			
		}
		
		$("#selectTopic").on('change', function(){ 
			var TMAr = [];
			for (var a = 0; a < tmdata.topicMap.length; a++) {
	
				if ($(this).val() == tmdata.topicMap[a].TTMID) {
					TMAr.push(tmdata.topicMap[a].PTMID);
				}
			}
			$('#selectPreTopics').selectpicker('val', TMAr);
		});
		
		$('#selectPreTopics').on('change', function(){
		    var selected = $(this).find("option:selected");
		    arrSelected  = [];
		    selected.each(function(){
		    	arrSelected.push(parseInt($(this).val()));
		    });
		});						
				
		
		$("#assignPreTopicBtn").on(
				"click",
				function(e) {
					var form1 = $("#PrerequisiteDataForm")[0];
//					console.log(form1);
					var form = document.getElementById('PrerequisiteDataForm');
					form.addEventListener('submit', function(e) {
						if (form.checkValidity() === false) {
							e.preventDefault();
							e.stopPropagation();
						}
						form.classList.add('was-validated');
					}, false);

					if (form.checkValidity() === true) {

						topicId = $.trim($("#selectTopic option:selected")
								.attr('id'));
						
						topicId = parseInt(topicId);

						var preId = "["
						for (var i = 0; i < arrSelected.length; i++) {
							if(i == (arrSelected.length - 1)){
								preId += arrSelected[i]+"]"
							}else{
								preId += arrSelected[i]+","
							}
						}
						
//						console.log(""+status+"/"+topicId+"/"+preId);
							$.ajax({
								type : "POST",
								url : com.coep.test.addProblem.baseURL
										+ "topic/api/mapping/prerequisite/"+status+"/"+topicId+"/"+preId,
								dataType : 'json',
								contentType : 'application/json',
								success : function(data) {
									getRenderDataTableWithTopicDetails(status);
									$("#selectTopic").val(0).change();
									$("#selectPreTopics").prop('selectedIndex', -1);
									$('.selectpicker').selectpicker('refresh');
									alert(data.msg);
								},
								error : function() {
								}

							});
						
						e.preventDefault();
					}

				}); // submit btn ends here
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	function bsSelectValidation() {
		if ($("#ParentDataForm").hasClass('was-validated') || $("#PrerequisiteDataForm").hasClass('was-validated')) {
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
	
	
})(com.coep.test.ajaxHandler, com.coep.test.addTopic);