(function(AH, AT) {

	AT.getSubjectListToAddTopic = function() {
		$
				.ajax({
					type : "GET",
					url : com.coep.test.addProblem.baseURL
							+ "subject/api/get/subjects",
					// data : JSON.stringify(L1Json),
					dataType : 'json',
					contentType : 'application/json',
					success : function(data) {
						AT.addTopic(data);
					},
					error : function() {
					}

				});
	}

	AT.addTopic = function(data) {
		document.title = "Add Topic";
		AT.getTopicDetails();
		if (data.subData.length == 0) {

			alert("Please create subject.");
			$("#selectedLevel").html("Please create subject.");

		} else {
			var htm = '<div>';
			htm += '<h1>ADD TOPIC</h1>'

					+ '<form id="TopicDataForm" novalidate>'
					+ '<div class="form-group" >'
					// + '<label class="col-xl-12 col-md-12 col-sm-12"
					// for="selectSubject">Select Subject :</label>'
					+ ' <select class=" form-control col-xl-12 col-md-12 col-sm-12 selectpicker" data-live-search="true"  id="selectSubject"  required disabled>'
					+ ' <option value="" disabled >Choose Subject</option>'

			for (var i = 0; i < data.subData.length; i++) {
				htm += '' + '  <option value="' + data.subData[i].SID
						+ '" id ="' + data.subData[i].SID + '" selected>'
						+ data.subData[i].SN + ' </option>'
			}

			htm += ' </select>'
					+ '<div id="error" class="invalid-feedback">Please select subject.</div>'
					+ '</div>'

					+ '<div class="form-group">'
					+ '<label class="col-xl-12 col-md-12 col-sm-12" for="TopicName">Enter Topic Name(Marathi) :</label>'
					+ '<input type="text" class="form-control col-xl-12 col-md-12 col-sm-12" id= "topicName" name="topicName" placeholder="Enter Topic Name"  required>'
					+ '<div class="invalid-feedback">Please Enter Topic Name.</div>'
					+ '</div>'
					
					+ '<div class="form-group">'
					+ '<label class="col-xl-12 col-md-12 col-sm-12" for="TopicName1">Enter Topic Name(English) :</label>'
					+ '<input type="text" class="form-control col-xl-12 col-md-12 col-sm-12" id= "topicName1" name="topicName1" placeholder="Enter Topic Name"  required>'
					+ '<div class="invalid-feedback">Please Enter Topic Name 1.</div>'
					+ '</div>'

					+ '<div class="form-group">'
					+ '<label class="col-xl-12 col-md-12 col-sm-12" for="topicNo">Enter Topic No :</label>'
					+ '<input type="text" class=" form-control" maxlength="12"  id="topicNo" name="topicNo" placeholder="Enter Topic No" required>'
					+ '<div class="invalid-feedback">Please Enter Topic No in Digits Only.</div>'
					+ '<div id="errmsg" class="red-color"></div>'
					+ '</div>'

					+ '<div class="form-group" >'
					+ '<label class="col-xl-12 col-md-12 col-sm-12" for="selectLevel">Select Level :</label>'
					+ ' <select class=" form-control col-xl-12 col-md-12 col-sm-12 selectpicker" data-live-search="true" name="level" id="selectLevel" name="selectLevel" required disabled>'
					+ ' <option value="0"  >Please enter valid topic number</option>'

			for (var i = 0; i < 6; i++) {
				htm += '' + '  <option value="' + "0" + (i + 1) + '" id ="'
						+ (i + 1) + '" >' + "0" + (i + 1) + ' </option>'
			}

			htm += ' </select>'
					+ '<div id="error" class="invalid-feedback">Please select level.</div>'
					+ '<span id="oldTopicId" hidden> </span>'
					+ '</div>'
					

					+ '<button id= "topicBtn" type="submit" class="btn btn-dark ">Submit</button>'
					+ '<button type="submit" class="btn btn-dark " id="updateBtn" style="display:none;">Update</button>'
					+ '</div>'

					+ '</form>'
					+ '<div id="showTopics" class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'
					+ '</div>'

			$("#main-div").html(htm);

			bsSelectValidation();
			$('select').selectpicker();
			var selector = document.getElementById("selectSubject");
		}

		// called when key is pressed in textbox
		$("#topicNo")
				.keypress(
						function(e) {
							// if the letter is not digit then display error and
							// don't type anything
							if (e.which != 8 && e.which != 0
									&& (e.which < 48 || e.which > 57)) {
								// display error message
								$("#errmsg").html("Digits Only").show()
										.fadeOut("slow");
								$("#TopicDataForm").addClass('was-validated');
								return false;
							}
						});

		$('#topicNo').on('keyup', function() {
//			console.log(this.value.length);

			var len = this.value.length;
			if (len == 2) {
				$("#selectLevel").val("01").change();
			} else if (len == 4) {
				$("#selectLevel").val("02").change();
			} else if (len == 6) {
				$("#selectLevel").val("03").change();
			} else if (len == 8) {
				$("#selectLevel").val("04").change();
			} else if (len == 10) {
				$("#selectLevel").val("05").change();
			} else if (len == 12) {
				$("#selectLevel").val("06").change();
			} else {
				$("#selectLevel").val(0).change();
			}
		});

		$("#topicBtn").on(
				"click",
				function(e) {
					var form1 = $("#TopicDataForm")[0];
					console.log(form1);
					var form = document.getElementById('TopicDataForm');
					form.addEventListener('submit', function(e) {
						if (form.checkValidity() === false) {
							e.preventDefault();
							e.stopPropagation();
						}
						form.classList.add('was-validated');
					}, false);

					if (form.checkValidity() === true) {

						topicName = $.trim($("#topicName").val());
						topicName1 = $.trim($("#topicName1").val());
						topicNo = $.trim($("#topicNo").val());
						levelNo = $.trim($("#selectLevel option:selected")
								.text());

						subjectId = parseInt($.trim($(
								"#selectSubject option:selected").attr('id')));

						var topicNoLen = $('#topicNo').val()
								.replace(/\s+/g, '').length;

						if ((topicNoLen <= 12) && topicNoLen % 2 == 0
								&& topicNo != "00" && topicNo != "0000"
								&& topicNo != "000000" && topicNo != "00000000"
								&& topicNo != "0000000000"
								&& topicNo != "000000000000") {
							AT.checkAvailability(topicNo);
						} else {
							if (topicNo == "00" || topicNo == "0000"
									|| topicNo == "000000"
									|| topicNo == "00000000"
									|| topicNo == "0000000000"
									|| topicNo == "000000000000") {
								alert("Please do not enter zero.")
							} else {
								if (!(topicNoLen % 2 == 0)) {
									alert("Please check topic number.")
								}

							}

						}
						e.preventDefault();
					}

				}); // submit btn ends here

		AT.checkAvailability = function(topicNo) {
			$.ajax({
				type : "GET",
				url : com.coep.test.addProblem.baseURL
						+ "topic/api/chkavl?topicNo=" + topicNo,
				// data : JSON.stringify(L1Json),
				dataType : 'json',
				contentType : 'application/json',
				success : function(data) {
					AT.renderCheckAvailability(data);

				},
				error : function() {
				}

			});
		}

		AT.renderCheckAvailability = function(data) {
			if (data.chkavl) {
				alert("This topic number is already assigned. ");
			} else {
				var topicJson = {
					"topicName" : topicName,
					"topicName1" : topicName1,
					"topicNo" : topicNo,
					"level" : levelNo,
					"subjectId" : subjectId
				};

				$
						.ajax({
							type : "POST",
							url : com.coep.test.addProblem.baseURL
									+ "topic/api/create",
							data : JSON.stringify(topicJson),
							dataType : 'json',
							contentType : 'application/json',
							success : function(data) {
								if (data.done == true) {
									alert(data.msg);
									$("#selectLevel").val(0).change();
									AT.getTopicDetails();
								}
							},
							error : function() {

							}

						});
			}
		};

	} // addTopic ends here

	AT.getTopicDetails = function() {

		$.ajax({
			type : "GET",
			url : com.coep.test.addProblem.baseURL + "topic/api/get/topics",
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				AT.renderTopicsDetails(data);
			},
			error : function() {
			}

		});
	}

	AT.renderTopicsDetails = function(data) {

		// console.log(data);
		$('#TopicDataForm')[0].reset();
		$("#TopicDataForm").removeClass('was-validated');
		var renderHtm = '';

		renderHtm += '<h1>TOPIC DETAILS</h1>'
				+ '<div class="table-responsive">'
				+ '<table id="topicData" class="table table-striped table-bordered" style="width:100%">'
				+ '<thead>' + ' <tr>' + '     <th width="15%">Topic No</th>'
				+ '      <th width="40%">Topic Name(Marathi)</th>'
				+ '      <th width="40%">Topic Name(English)</th>'
				+ '      <th width="10%">Level</th><th width="5%">Action</th>' + '   </tr>' + '</thead>'
				+ ' <tbody>'
		for (var i = 0; i < data.topicData.length; i++) {
			renderHtm += '<tr>' + '<td width="15%">' + data.topicData[i].TNO
					+ '</td>' + '<td width="40%">' + data.topicData[i].TN
					+ '</td>' + '<td width="40%">' + data.topicData[i].TN1
					+ '</td>' + '<td width="10%">' + data.topicData[i].LNO
					+ '</td>' + '<td width="5%">'
					+'<button onClick = "getDataToModify('+ i +')" type="submit" class="btn btn-success AcT_ArchIcon"><i class="fa fa-pencil-square-o " aria-hidden="true"></i></button>'
					+ '&nbsp;<button onClick = "deleteData('+ i +')" type="submit" class="btn btn-danger AcT_ArchIcon"><i class="fa fa-trash-o" aria-hidden="true"></i></button>' 
					+ '</td>' + '</tr>'
		}
		renderHtm += '    </tbody>'

		+ '</table>' + '</div>'
		$("#showTopics").html(renderHtm);
		$("#showTopics").ready(function() {
			var table = $('#topicData').DataTable({
				"pageLength" : 25,
				 dom: 'Bfrtip',
					buttons : [ {
		                extend: 'excelHtml5',
		                title: 'Virtual Maths Lab User list'
		            },
		            {
		                extend: 'pdfHtml5',
		                title: 'Virtual Maths Lab User list',
		                orientation: 'landscape',
	                    pageSize: 'A0'
		            } ]
			
			});

			// table.buttons().container()
			// .appendTo( '#example_wrapper .col-md-6:eq(0)' );
		});

		getDataToModify = function(i) {
			
			$("#topicBtn").css("display", "none");
			$("#updateBtn").css("display", "block");

			$("#topicNo").prop("disabled","disabled");
			$("#topicName").val(data.topicData[i].TN);
			$("#topicName1").val(data.topicData[i].TN1);
			$("#topicNo").val(data.topicData[i].TNO);
			$("#oldTopicId").text(data.topicData[i].TID);

			var len = $("#topicNo").val().length;
			if (len == 2) {
				$("#selectLevel").val("01").change();
			} else if (len == 4) {
				$("#selectLevel").val("02").change();
			} else if (len == 6) {
				$("#selectLevel").val("03").change();
			} else if (len == 8) {
				$("#selectLevel").val("04").change();
			} else if (len == 10) {
				$("#selectLevel").val("05").change();
			} else if (len == 12) {
				$("#selectLevel").val("06").change();
			} else {
				$("#selectLevel").val(0).change();
			}
		}
		
		$("#updateBtn").on("click", function(e){
			
			

			var form1 = $("#TopicDataForm")[0];
			console.log(form1);
			var form = document.getElementById('TopicDataForm');
			form.addEventListener('submit', function(e) {
				if (form.checkValidity() === false) {
					e.preventDefault();
					e.stopPropagation();
				}
				form.classList.add('was-validated');
			}, false);

			if (form.checkValidity() === true) {

			topicName1 = $.trim($("#topicName1").val());
			topicName = $.trim($("#topicName").val());
			topicNo = $.trim($("#topicNo").val());
			levelNo = $.trim($("#selectLevel option:selected").text());
			subjectId = parseInt($.trim($("#selectSubject option:selected").attr('id')));
			
			var topicNoLen = $('#topicNo').val().replace(/\s+/g, '').length;
			
			if ((topicNoLen <= 12) && topicNoLen % 2 == 0
					&& topicNo != "00" && topicNo != "0000"
					&& topicNo != "000000" && topicNo != "00000000"
					&& topicNo != "0000000000"
					&& topicNo != "000000000000") {
				AT.updateTopic();
			} else {
				if (topicNo == "00" || topicNo == "0000"
						|| topicNo == "000000"
						|| topicNo == "00000000"
						|| topicNo == "0000000000"
						|| topicNo == "000000000000") {
					alert("Please do not enter zero.")
				} else {
					if (!(topicNoLen % 2 == 0)) {
						alert("Please check topic number.")
					}
				}
			}
			e.preventDefault();
			}
			
		});
			
		AT.updateTopic = function() {
			var topicJson = {
				"topicId" : $("#oldTopicId").text(),
				"topicName" : topicName,
				"topicName1" : topicName1,
//				"topicNo" : topicNo + "_" + $("#oldTopicId").text(),
				"level" : levelNo,
				"topicNo" : topicNo,
				"subjectId" : subjectId
			};

//			console.log(topicJson);
			$.ajax({
				type : "PUT",
				url : com.coep.test.addProblem.baseURL + "topic/api/update",
				data : JSON.stringify(topicJson),
				dataType : 'json',
				contentType : 'application/json',
				success : function(data) {
//					 if (data.done == true) {
					$("#topicBtn").css("display", "block");
					$("#updateBtn").css("display", "none");
					
						 $("#topicName").val("");
						 $("#topicName1").val("");
						 $("#topicNo").val("");
						 $("#selectLevel").val(0).change();
						 alert(data.msg);
						 AT.getTopicDetails();
//					 }else{
//						 alert(data.msg);
//					 }
				},
				error : function() {

				}
			});
		}	
		
		
		deleteData = function(i){
			console.log(data.topicData[i].TNO);
			
			topicNo = data.topicData[i].TNO;
			
			$.ajax({
				type : "DELETE",
				url : com.coep.test.addProblem.baseURL + "topic/api/delete?topicNo=" + topicNo,
//				data : JSON.stringify(topicJson),
				dataType : 'json',
				contentType : 'application/json',
				success : function(data) {
						 alert(data.msg);
				},
				error : function() {

				}
			});
		}
	}

	function bsSelectValidation() {
		if ($("#TopicDataForm").hasClass('was-validated')) {
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