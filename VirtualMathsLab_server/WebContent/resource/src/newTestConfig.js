(function(AH, AP, TC) {

	var sectionObj = {};
	TC.tempSecObj = {};
	var getSectionId = [];
	var department = {};
	var examination = {};
	var sectionDetailJSONArr = [];

	var sectionLevelJSON = {};
	
	TC.testLevel = {
		"0" : "Choose Value",
		"1" : "Round 1 (Psychomatric)",
		"2" : "Round 2 (Basic Test)",
		"3" : "Round 3 (Advance Test)"
	}

	TC.renderTestConfig = function() {

		document.title = "Configure Test";
		$("#listofuser-div").hide();
		$("#listofuser-result-div").hide();
		

		getSection = {};
		getSectionId = [];
		testDataMainJSON = {};
		sectionDetailJSONArr = [];
		var testConfightm = '';

		$(".child-menu-quesbank").hide();
		$(".child-menu-configTest").show();
//		$("#listofuser-result-div").hide();

		$("#ConfigTestPapLink").addClass("active");

		testConfightm += '<div class="row">'
				+ ' <div class="col-sm-offset-9 col-sm-12 pos-absolute">'
				+ '   <p class="red-color testpapername hidden">Please enter test paper name</p>'
				+ '  <p class="red-color department-name hidden">Please choose department</p>'
				+ '  <p class="red-color testLvl-name hidden">Please choose Test Level</p>'
				+ '  <p class="red-color section-name hidden">Please select section</p>'
				+ ' <p class="red-color test-startdate hidden">Please select test-start date </p>'
				+ '  <p class="red-color test-enddate hidden">Please select test-end date </p>'
				+ '  <p class="red-color test-datevalidation hidden">test-end date should be greater than test-start date </p>'
				+ ' </div>'
				+ ' <div class="col-md-6 col-sm-12 col-md-offset-3">'
				+ '   <input type="text" id="testPaperName" autofocus="" class="qgroup-input display-inline form-control width-90" placeholder="Name Test Paper"><span class="icontxt-edit icon-text-edit"></span>'
				+ ' </div>'

				+ '  <div class="col-md-12 col-sm-12 l0-r0-padding t2-b2-padding">'
				+ ' <div class="col-md-6 col-sm-12 col-md-offset-3">'
				+ '   <h2 class="secondary-text-color">Choose Department<sup><span class="red-color">*</span></sup></h2>'
				+ '  <select class="form-control select-class" id="dept" name="dept" title="Choose Department">'
				+ '   </select>'
				+ '  </div>'
				+ '  </div>'
				
				+ '  <div class=" col-md-12 col-sm-12 l0-r0-padding t2-b2-padding">'
				+ ' <div class=" col-md-6 col-sm-12 col-md-offset-3">'
				+ '   <h2 class="secondary-text-color">Choose Test Level(ROUND NO)<sup><span class="red-color">*</span></sup></h2>'
				+ '  <select class="form-control select-class" id="testLvl" name="testLvl" title="Choose Test Level">'
				+ '   </select>'
				+ '  </div>'
				+ '  </div>'
				
				
				+ ' <div class="col-sm-12 t15-margin" id="sectionDiv" hidden>'
				+ '  <h2 class="secondary-text-color">Choose Section<sup><span class="red-color">*</span></sup></h2>'
				+ '  <h3>'
				+ '  <div class="row t2-b2-padding" id="sections">'
				+ '      <div class="col-sm-12 t1-b1-padding" id="sectionBlock">'
				+ '     </div>'
				+ ' </div>'
				+ '  </h3>'
				+ '  </div>'

				+ '  <div class="col-sm-12 col-md-6 ">'
				+ '  <h2 class="secondary-text-color" >'
				+ '    Start Date<sup><span class="red-color l0-r30-padding">*</span></sup>'
				+ '    <input type="text" id="testStartDate" class=" datepicker" style="width:;">'

				+ '  </h2>'
				+ ' </div>'

				+ '  <div class="col-sm-12 col-md-6 ">'
				+ '   <h2 class="secondary-text-color">'
				+ '     End Date<sup><span class="red-color l0-r30-padding">*</span></sup>'
				+ '   <input type="text" id="testEndDate" class="datepicker" style="width:;">'
				+ '  </h2>'
				+ '  </div>'

				+ ' <div class="col-sm-12  l0-r0-padding t2-b2-padding">'
				+ '  <!-- //setTest.html -->'
				+ ' <button id="setProbBtn" onclick="com.coep.test.testConfig.confirmationToConfigureTest();"  title="SUBMIT" class="pull-right btn custom-btn">CONTINUE TO SET PROBLEMS</button>'
				+ ' </div>' + ' </div>';

		$("#main-div").html('');
		$("#main-div").css("padding-top", "35px");
		$("#main-div").html(testConfightm);

		$(".datepicker").datepicker({
			closeText : "Close",
			showButtonPanel : true,
			changeMonth : true,
			changeYear : true,
			dateFormat : "yy-mm-dd",
			timeFormat: 'hh:mm tt',
	        	ampm: true,
			yearRange : ":+1",
			minDate : '0',
			// maxDate: '0',
			onSelect : function(e) {
				$(this).attr("autocomplete", "off");
				$(this).datepicker({}).val();

			},
			onClose : function() {
				this.focus();
			}
		}).click(function() {
			$(this).focus();
		});

		TC.confirmationToConfigureTest = function() {

			var ms = '';
			ms = 'Do you want to continue to set problem.';

			var htmlText = '';
			htmlText += '';
			htmlText += '' + ms + '';

			htmlText += '';

			$('#cModalLabel').html('Confirmation');
			$('#cdlgBody').html(htmlText);
			$('#cdlgFoot').html(AP.getYesNoButtonOnDialog());

			var valid = testConfigValidation();

			if (valid) {
				$('#cdialog').modal({
					show : true
				});
			} else {
				return testConfigValidation();
			}

			$('#cfYes').bind('click', function() {
				TC.continueToSetProblemClicked(valid);
				AP.clearConfirmDialogContent();
			});

			$('#cfNo').bind('click', function() {
				AP.clearConfirmDialogContent();
			});
		};
		
		TC.continueToSetProblemClicked = function(valid) {

			var selectedSectJSON = {};
			var configureTestJSON = {};

			if (valid) {

				var testName = $("#testPaperName").val();
				var testStartDate = $("#testStartDate").val();
				var testEndDate = $("#testEndDate").val();
				var deptId = $("#dept").val();
				var testLevelId = $("#testLvl").val();

				department.departmentId = deptId;
				
				examination.examinationId = 1;
				testStartDate = new Date(testStartDate).getTime();
				testEndDate = new Date(testEndDate).getTime();

				$.each($("input[name='sections']:checked"), function() {
					
					sectionObj[$(this).attr('id')] =  $("input:checkbox[class=secCheckboxType]:checked").val();
					sectionObj[$(this).attr('id')] = TC.tempSecObj[$(this).attr('id')];
			     
			    });

				$.each($("input[name='sections']:checked"), function() {
					getSectionId.push($(this).attr('id'));
				});

				// To convert array into seperated by comma string
				selectedSectJSON = getSectionId.join();

				configureTestJSON.name = testName;
				configureTestJSON.startDate = testStartDate;
				configureTestJSON.endDate = testEndDate;
				configureTestJSON.active = 1;
				configureTestJSON.selectedSections = selectedSectJSON;
				configureTestJSON.department = department;
				configureTestJSON.testLevel = testLevelId;

				configureTestJSON.examination = examination;

				AH.configureTest(AP.baseURL + "configureTestPaper", "POST",
						configureTestJSON, sectionObj, deptId);

			} else {
				return testConfigValidation();
			}

		};

		var topics = '';
		for ( var key in AP.departments) {
			topics += "<option value= " + key + ">" + AP.departments[key]
					+ "</option>";
		}

		$("#dept").html(topics);
		$("#dept").change(
				function() {

					var selectedDept = $("#dept").val();
					AH.getSectionsOfSelectedDept(AP.baseURL
							+ "configureTestPaper/" + selectedDept, "GET",
							selectedDept);
				});
		
		
		// new code
		var testLevels = '';
		for ( var key in TC.testLevel) {
			testLevels += "<option value= " + key + ">" + TC.testLevel[key]
					+ "</option>";
		}

		$("#testLvl").html(testLevels);
		$("#testLvl").change(
				function() {

					var selectedTestLevel = $("#testLvl").val();
//					AH.getSectionsOfSelectedDept(AP.baseURL
//							+ "configureTestPaper/" + selectedDept, "GET",
//							selectedDept);
				});
		
		
	};

	/*
	 * To display render data from ajax call in html page
	 */
	TC.renderTestConfigRes = function(data) {
		$("#sectionDiv").show();
		var sectionData = '';
		if (data.length != 0) {
			for (var i = 0; i < data.length; i++) {
				TC.tempSecObj[data[i].SID] = data[i].SN;
				sectionData += '<span class="col-md-3 col-sm-12 col-xs-10 col-xs-offset-1 ">' 
						+ '<input type="checkbox" class="secCheckboxType" style="marign-right:5px;" name="sections" id='
						+ data[i].SID + ' value=' + data[i].SN + '>'
						+ data[i].SN + '  </span>';
				+ '<br/>';
			}
			$("#sectionBlock").html('');
			$("#sectionBlock").html(sectionData);
		} else {
			$("#sectionBlock")
					.html(
							'<h4 class = "hidden-msg">No questions are assigned to sections under this department</h4>');
		}
	};

	TC.renderSetTestPaper = function(data, configureTestJSON, sectionObj,
			deptId) {

		document.title = "Set Test Paper";

		var testPaperHtm = '';

		for (var i = 0; i < data.data.length; i++) {
			testPaperHtm += ' <div class="row tconfig-shadow">'
					+ '<div class="col-sm-12" >'
					+ '  <a data-toggle="collapse" onclick="toggleDropdown('
					+ i
					+ ',this,\'sect'
					+ data.data[i].SID
					+ '\', \'sect'
					+ data.data[i].SID
					+ 'Class\')">'
					+ '   <div class="col-sm-12 cursor-pointer sect-close">'
					+ '      <span class="col-sm-2 l0-r0-padding sect'
					+ data.data[i].SID
					+ 'Class font_name icon-add prim-text-black-color">'
					+ '         <h4 class="midd-blue">&nbsp;&nbsp;CONFIGURE </h4>'
					+ '   </span>'
					+ '   <span class="col-sm-5 l0-r0-padding">'
					+ '      <h2 class=" display-inline prim-text-black-color">Section '
					+ (i + 1)
					+ ' - '
					+ sectionObj[data.data[i].SID]
					+ ' </h2>'
					+ '   </span>'
					+ '  <span class="col-sm-2 l0-r0-padding">'
					+ '      <h4 class="secondary-text-color"> Questions </h4>'
					+ '      <span class="secondary-text-color h1-cls l30-r0-padding" id="sect'
					+ data.data[i].SID
					+ 'totques">0</span>'
					+ '  </span>'
					+ '  <span class="col-sm-3 l0-r0-padding">'
					+ '      <h4 class="secondary-text-color"> Estimated Time </h4><span class="secondary-text-color h1-cls l30-r0-padding" id="sect'
					+ data.data[i].SID
					+ 'tottime">0</span>'
					+ '   </span>'
					+ '</div>'
					+ ' </a>'
					+ ' <div id="sect'
					+ data.data[i].SID
					+ '" class="col-sm-12 collapse sect-child-open ">'
					+ '   <div class="col-sm-12 col-md-8 secondary-text-color">'
					+ '  <label class="h1-cls">NUMBER OF QUESTIONS</label>'
					+ '  <br /><br /><br />'
			var j = 1;

			for ( var key in data.data[i]) {

				if (key != "SID" && data.data[i].done == undefined) {

					var level = key.slice(-1);
					level = parseInt(level);
					testPaperHtm += ' <span class="secondary-text-color h2-cls">Difficulty Level '
							+ (level - 1)
							+ '</span>'
							+ ' <input type="text" autofocus="" id="sect'
							+ data.data[i].SID
							+ 'inp'
							+ j
							+ '" onblur="setValue(this,'
							+ data.data[i][key]
							+ ')" class=" prim-text-black-color h2-cls quest-border" value="0"/>'
							+ '  <div class="input-group cust-inpgrp">'
							+ '    <span id="sect'
							+ data.data[i].SID
							+ 'u'
							+ j
							+ '" onclick="incrQues(this,'
							+ data.data[i][key]
							+ ')" class="glyphicon glyphicon-triangle-top pointer"></span><br/>'
							+ '   <span id="sect'
							+ data.data[i].SID
							+ 'd'
							+ j
							+ '" onclick="decrQues(this,0)" class="glyphicon glyphicon-triangle-bottom pointer"></span>'
							+ '  </div>'

							+ '  <span class="l3-r0-margin midd-blue h3-cls">'
							+ data.data[i][key]
							+ ' Question Groups Found</span>'
							+ '  <br /><br /><br />'
					j++;
				} else if (data.data[i].done == false) {

					testPaperHtm += ' <span class="secondary-text-color h2-cls">No Questions Found</span></br>'

				}

			}

			testPaperHtm += '   </div>'
					+ '   <div class="col-sm-12 col-md-4 secondary-text-color h1-cls" style="padding-top:100px;">'
					+ '   <span class="secondary-text-color display-inline h2-cls">Estimated time for section</span>'
					+ '   <select id="estimatedTime'
					+ data.data[i].SID
					+ '" class="estim-time form-control display-inline select-class" name="estimatedtime" title="Estimated Time">'
					+ '        <option value="0">Choose Time[in Min]</option>'
					+ '   </select>'
					+ '   <br /><br/><br/>'
					+ '  <button id="saveConfig'
					+ data.data[i].SID
					+ '" title="SAVE" onclick="configQues(\'sect'
					+ data.data[i].SID
					+ '\',\'estimatedTime'
					+ data.data[i].SID
					+ '\','
					+ i
					+ ')" class="pull-right btn custom-btn width-10em">SAVE</button>'
					+ '  </div>' + ' </div>' + ' </div>' + '</div>';
		}

		$("#main-div").html('');
		$("#main-div").css("padding-top", "35px");
		$("#main-div").html(testPaperHtm);
		// $("#main-div").html('');

		/** Hide & show section block * */
		toggleDropdown = function(secId, currSect, dataTargetId, glyphiconClass) {
			// Exm a, 'sect4', 'sect4Class'
			if ($('#' + dataTargetId).is(":hidden")) {
				$('#' + dataTargetId).show(
						function() {
							$("." + glyphiconClass).removeClass("icon-add")
									.addClass("icon-substract");
							$(currSect).children().removeClass('sect-close')
									.addClass('sect-open');
						});
				/** append time to estimated time block * */
				for (var option = 1; option <= 30; option++)
					// $(".estim-time").append(
					// "<option>" + option + "</option>");

					$("#estimatedTime" + data.data[secId].SID).append(
							"<option>" + option + "</option>");
			} else {
				$('#' + dataTargetId).hide(
						function() {
							$("." + glyphiconClass).removeClass(
									"icon-substract").addClass("icon-add");
							$(currSect).children().removeClass('sect-open')
									.addClass('sect-close');
						});
			}
		}

		/**
		 * Function to increment questions number here 'maxQues' is maximum
		 * nunber of question level has *
		 */
		incrQues = function(currId, maxQues) {
			currInpVal = currId.id.replace('u', 'inp');
			var Ques = $("#" + currInpVal).val();
			Ques < maxQues ? $("#" + currInpVal).val(++Ques) : $(
					"#" + currInpVal).val(maxQues);
		}

		/**
		 * Function to decrement questions number here 'minQues' is minimum
		 * nunber of question level has *
		 */
		decrQues = function(currId, minQues) {
			currInpVal = currId.id.replace('d', 'inp');
			var Ques = $("#" + currInpVal).val();
			Ques > minQues ? $("#" + currInpVal).val(--Ques) : $(
					"#" + currInpVal).val(minQues);
		}

		/**
		 * Function to set number of questions for respective Levels, if current
		 * value exceeds than max number of question *
		 */
		setValue = function(currId, maxQues) {
			var Ques = $(currId).val();
			if (Ques > maxQues) {
				$(currId).val(maxQues)
			}
			if (Ques < 0) {
				$(currId).val(0)
			}
			if (isNaN(Ques)) {
				alert("Enter valid number only");
				$(currId).val(0)
			}

			if (!Ques) {
				$(currId).val(0)
			}
		}

		configQues = function(sectExpandId, sectEstTimeId, id) {
			var TestPaperJSONArray = [];
			var qGrpErr = false;
			var qGrpCounter = 0;
			var sectionTimeErr = false;
			// var totdifflvl = data.data[id];
			var difflvlQues = [];
			var totQues = 0;
			var i = 1;
			for ( var key in data.data[id]) {
				if (key != "SID" && key != "saved") {
					difflvlQues[i] = $("#" + sectExpandId + "inp" + i).val();
					totQues = totQues + parseInt(difflvlQues[i]);

					if (difflvlQues[i] != 0) {
						qGrpCounter++;
					}
					i++;
				}
			}

			totTime = $("#" + sectEstTimeId).val();

			if (totTime != "0") {
				sectionTimeErr = true;
			}

			if (qGrpCounter == 0 || !sectionTimeErr) {

				if (!$("#" + sectExpandId + "inp1").next().next().children()
						.hasClass("pos-absolute")) {
					$("#" + sectExpandId + "inp1")
							.next()
							.next()
							.append(
									"<div class=\"col-sm-5 col-sm-offset-12 pos-absolute\">"
											+ "<p class=\"red-color qGrpErr hidden\">Select Question Groups</p>"
											+ "<p class=\"red-color sectionTimeErr hidden\">Select Section Time</p>"
											+ "</div>");
				}

				qGrpCounter ? $(".qGrpErr").addClass("hidden") : $(".qGrpErr")
						.removeClass("hidden");
				sectionTimeErr ? $(".sectionTimeErr").addClass("hidden") : $(
						".sectionTimeErr").removeClass("hidden");
				return false;

			} else {

				if (totQues == NaN) {
					$("#" + sectExpandId + "totques").text(0);
					$("#" + sectExpandId + "tottime").text(totTime);
				} else {
					$("#" + sectExpandId + "totques").text(totQues);
					$("#" + sectExpandId + "tottime").text(totTime);
				}

				var totNumQues = 0;
				var totalEstTime = 0;
				$("#totEstTime").text();
				var totalSectons = $(".tconfig-shadow").length;
				for (i = 1; i <= totalSectons; i++) {
					totNumQues = totNumQues
							+ parseInt($("#sect" + i + "totques").text());
					totalEstTime = totalEstTime
							+ parseInt($("#sect" + i + "tottime").text());
				}
				$("#totQues").text(totNumQues);
				$("#totEstTime").text(totalEstTime + " Min");
				// used to collapse this section after clicking save button
				$('#' + sectExpandId).hide(
						function() {
							$("." + sectExpandId + "Class").removeClass(
									"icon-substract").addClass("icon-add");
							$("." + sectExpandId + "Class").parents(
									".sect-open").removeClass('sect-open')
									.addClass('sect-close');
						});
			}
			var savedTC = {};
			var l = 1;
			for ( var key in data.data[id]) {

				var level = key.slice(-1);
				level = parseInt(level);
				if (key != "SID" && key != "saved") {
					if ($("#" + sectExpandId + "inp" + l).val() != 0) {
						var TestPaperJSON = {

							"noOfQuestionGroup" : $(
									"#" + sectExpandId + "inp" + l).val(),
							"sectionTimeLimit" : $("#" + sectEstTimeId).val(),
							"departmentVO" : {
								"departmentId" : deptId
							},
							"sectionVO" : {
								"sectionId" : data.data[id].SID
							},
							"complexityLevel" : {
								"qgComplexityLevelId" : level
							},
							"test" : {
								"testId" : data.TID
							}
						};
						TestPaperJSONArray.push(TestPaperJSON);
					}

					savedTC["LVL" + level] = $("#" + sectExpandId + "inp" + l)
							.val();
					savedTC["STL"] = $("#" + sectEstTimeId).val();
					l++;
				}

			}

			data.data[id].saved = true;

			savedTC["SID"] = data.data[id].SID;
			savedTC["TOTQ"] = totQues;
			// savedTC["TOT"] = totNumQues;
			// savedTC["TET"] =totalEstTime;
			data.data[id].savedTC = savedTC;
			// if (data.data[id].saved == true) {
			//
			// AH.saveTestPaperForSection(AP.baseURL + "setTestPaper", "PUT",
			// TestPaperJSONArray, totQuesBySecId, section);
			// } else {

			if (totQues != 0 && $("#" + sectEstTimeId).val() != "0") {
				AH.saveTestPaperForSection(AP.baseURL + "setTestPaper", "POST",
						TestPaperJSONArray, data, deptId, sectionObj);
			} else {
				showToast.show('', false);
			}
		}

	};

	TC.renderSavedSetTestPaper = function(data, TestPaperJSONArray,
			savedTCData, deptId, sectionObj) {

		var testPaperHtm = '';
		var totalQuestions = 0;
		var toatlEstimateTime = 0;
		for (var i = 0; i < data.data.length; i++) {

			testPaperHtm += ' <div class="row tconfig-shadow">'
					+ '<div class="col-sm-12" >'
					+ '  <a data-toggle="collapse" onclick="toggleDropdown('
					+ i
					+ ',this,\'sect'
					+ data.data[i].SID
					+ '\', \'sect'
					+ data.data[i].SID
					+ 'Class\')">'
					+ '   <div class="col-sm-12 cursor-pointer sect-close">'
					+ '      <span class="col-sm-2 l0-r0-padding sect'
					+ data.data[i].SID
					+ 'Class font_name icon-add prim-text-black-color">'
					+ '         <h4 class="midd-bltue">&nbsp;&nbsp;CONFIGURE </h4>'
					+ '   </span>'
					+ '   <span class="col-sm-5 l0-r0-padding">'
					+ '      <h2 class=" display-inline prim-text-black-color">Section '
					+ (i + 1)
					+ ' - '
					+ sectionObj[data.data[i].SID]
					+ ' </h2>'
					+ '   </span>'
					+ '  <span class="col-sm-2 l0-r0-padding">'
					+ '      <h4 class="secondary-text-color"> Questions </h4>'
					+ '      <span class="secondary-text-color h1-cls l30-r0-padding" id="sect'
					+ data.data[i].SID
			if (data.data[i].saved != undefined && data.data[i].saved == true) {

				totalQuestions = totalQuestions + data.data[i].savedTC.TOTQ;

				testPaperHtm += 'totques">' + (data.data[i].savedTC.TOTQ)
						+ '</span>'
			} else {
				testPaperHtm += 'totques">0</span>'
			}

			testPaperHtm += '  </span>'
					+ '  <span class="col-sm-3 l0-r0-padding">'
					+ '      <h4 class="secondary-text-color"> Estimated Time </h4><span class="secondary-text-color h1-cls l30-r0-padding" id="sect'
					+ data.data[i].SID
			if (data.data[i].saved != undefined && data.data[i].saved == true) {
				testPaperHtm += 'tottime">' + (data.data[i].savedTC.STL)
						+ '</span>'

				toatlEstimateTime = toatlEstimateTime
						+ parseInt(data.data[i].savedTC.STL);
			} else {
				testPaperHtm += 'tottime">0</span>'
			}

			testPaperHtm += '   </span>'
					+ '</div>'
					+ ' </a>'
					+ ' <div id="sect'
					+ data.data[i].SID
					+ '" class="col-sm-12 collapse sect-child-open">'
					+ '   <div class="col-sm-12 col-md-8 secondary-text-color">'
					+ '  <label class="h1-cls">NUMBER OF QUESTIONS</label>'
					+ '  <br /><br /><br />'

			var j = 1;
			for ( var key in data.data[i]) {
				if (data.data[i].saved != undefined
						&& data.data[i].saved == true) {

					if (key != "SID" && key != "saved" && key != "savedTC") {
						var level = key.slice(-1);

						if (data.data[i].savedTC["TCIDLVL" + level] == undefined) {
							data.data[i].savedTC["TCIDLVL" + level] = savedTCData["LVL"
									+ level];
						}
						level = parseInt(level);
						testPaperHtm += ' <span class="secondary-text-color h2-cls">Difficulty Level '
								+ (level - 1)
								+ '</span>'
								+ ' <input type="text" autofocus="" id="sect'
								+ data.data[i].SID
								+ 'inp'
								+ j
								+ '" onblur="setValue(this,'
								+ data.data[i][key]
								+ ')" class="l3-r0-margin prim-text-black-color h2-cls quest-border" value="'
								+ data.data[i].savedTC["LVL" + level]
								+ '"/>'
								+ '  <div class="input-group cust-inpgrp">'
								+ '    <span id="sect'
								+ data.data[i].SID
								+ 'u'
								+ j
								+ '" onclick="incrQues(this,'
								+ data.data[i][key]
								+ ')" class="glyphicon glyphicon-triangle-top pointer"></span><br/>'
								+ '   <span id="sect'
								+ data.data[i].SID
								+ 'd'
								+ j
								+ '" onclick="decrQues(this,0)" class="glyphicon glyphicon-triangle-bottom pointer"></span>'
								+ '  </div>'

								+ '  <span class="l3-r0-margin midd-blue h3-cls">'
								+ data.data[i][key]
								+ ' Question Groups Found</span>'
								+ '  <br /><br /><br />'
						j++;
					}
				} else {

					if (key != "SID" && key != "saved") {
						var level = key.slice(-1);
						level = parseInt(level);
						testPaperHtm += ' <span class="secondary-text-color h2-cls">Difficulty Level '
								+ (level - 1)
								+ '</span>'
								+ ' <input type="text" autofocus="" id="sect'
								+ data.data[i].SID
								+ 'inp'
								+ j
								+ '" onblur="setValue(this,'
								+ data.data[i][key]
								+ ')" class="l3-r0-margin prim-text-black-color h2-cls quest-border" value="0"/>'
								+ '  <div class="input-group cust-inpgrp">'
								+ '    <span id="sect'
								+ data.data[i].SID
								+ 'u'
								+ j
								+ '" onclick="incrQues(this,'
								+ data.data[i][key]
								+ ')" class="glyphicon glyphicon-triangle-top pointer"></span><br/>'
								+ '   <span id="sect'
								+ data.data[i].SID
								+ 'd'
								+ j
								+ '" onclick="decrQues(this,0)" class="glyphicon glyphicon-triangle-bottom pointer"></span>'
								+ '  </div>'

								+ '  <span class="l3-r0-margin midd-blue h3-cls">'
								+ data.data[i][key]
								+ ' Question Groups Found</span>'
								+ '  <br /><br /><br />'
						j++;
					}
				}
			}

			testPaperHtm += '   </div>'
					+ '   <div class="col-sm-12 col-md-4 secondary-text-color h1-cls t10-padding">'
					+ '   <span class="secondary-text-color display-inline h2-cls">Estimated time for section</span>'
					+ '   <select id="estimatedTime'
					+ data.data[i].SID
					+ '" class="estim-time form-control display-inline select-class" name="estimatedtime" title="Estimated Time">'
					+ '        <option value="0">Choose Time[in Min]</option>'
					+ '   </select>'
					+ '   <br />'
					+ '  <button id="saveConfig'
					+ data.data[i].SID
					+ '"  title="SAVE" onclick="configQues(\'sect'
					+ data.data[i].SID
					+ '\',\'estimatedTime'
					+ data.data[i].SID
					+ '\','
					+ i
					+ ')" class="pull-right btn custom-btn width-10em">SAVE</button>'
					+ '  </div>' + ' </div>' + ' </div>' + '</div>';

		}

		testPaperHtm += '  <div class="col-sm-12 t2-b2-padding">'
				+ '<span class="col-sm-3 secondary-text-color h1-cls">TOTAL</span>'
				+ '<span class="col-sm-3 secondary-text-color h4-cls"> Total Questions</span><span class="col-sm-1 secondary-text-color h1-cls" id="totQues">'
				+ totalQuestions
				+ '</span>'
				+ '<span class="col-sm-3 secondary-text-color h4-cls">&nbsp;&nbsp;&nbsp;Total Estimated Time&nbsp;</span><span class="col-sm-1 secondary-text-color h1-cls" id="totEstTime">'
				+ toatlEstimateTime
				+ '</span>'
				+ '  </div>'
				+ ' <div class="col-sm-12 t2-b2-padding" align="center">'
				+ '<button  onclick="preTestPreviewClick();" title="PREVIEW TEST PAPER" class="btn custom-btn">PREVIEW TEST PAPER</button>'
				// <!-- <button id="" type="submit" title="SAVE TEST
				// CONFIGURATION" class="btn custom-btn">SAVE TEST
				// CONFIGURATION</button></a> -->
				+ '</div>'

		$("#main-div").html('');
		$("#main-div").css("padding-top", "35px");
		$("#main-div").html(testPaperHtm);
		// $("#main-div").html('');

		/** Hide & show section block * */
		toggleDropdown = function(secId, currSect, dataTargetId, glyphiconClass) {
			// Exm a, 'sect4', 'sect4Class'
			if ($('#' + dataTargetId).is(":hidden")) {
				$('#' + dataTargetId).show(
						function() {
							$("." + glyphiconClass).removeClass("icon-add")
									.addClass("icon-substract");
							$(currSect).children().removeClass('sect-close')
									.addClass('sect-open');
						});
				/** append time to estimated time block * */
				for (var option = 1; option <= 30; option++)
					// $(".estim-time").append(
					// "<option>" + option + "</option>");
					$("#estimatedTime" + data.data[secId].SID).append(
							"<option>" + option + "</option>");
			} else {
				$('#' + dataTargetId).hide(
						function() {
							$("." + glyphiconClass).removeClass(
									"icon-substract").addClass("icon-add");
							$(currSect).children().removeClass('sect-open')
									.addClass('sect-close');
						});
			}

			if (data.data[secId].saved != undefined
					&& data.data[secId].saved == true) {
				$("#estimatedTime" + data.data[secId].SID).val(
						data.data[secId].savedTC.STL);
			}
		}

		/**
		 * Function to increment questions number here 'maxQues' is maximum
		 * nunber of question level has *
		 */
		incrQues = function(currId, maxQues) {
			currInpVal = currId.id.replace('u', 'inp');
			var Ques = $("#" + currInpVal).val();
			Ques < maxQues ? $("#" + currInpVal).val(++Ques) : $(
					"#" + currInpVal).val(maxQues);
		}

		/**
		 * Function to decrement questions number here 'minQues' is minimum
		 * nunber of question level has *
		 */
		decrQues = function(currId, minQues) {
			currInpVal = currId.id.replace('d', 'inp');
			var Ques = $("#" + currInpVal).val();
			Ques > minQues ? $("#" + currInpVal).val(--Ques) : $(
					"#" + currInpVal).val(minQues);
		}

		/**
		 * Function to set number of questions for respective Levels, if current
		 * value exceeds than max number of question *
		 */
		setValue = function(currId, maxQues) {
			var Ques = $(currId).val();
			if (Ques > maxQues) {
				$(currId).val(maxQues)
			}
			if (Ques < 0) {
				$(currId).val(0)
			}
		}

		configQues = function(sectExpandId, sectEstTimeId, id) {
			var TestPaperJSONArray = [];
			var qGrpErr = false;
			var qGrpCounter = 0;
			var sectionTimeErr = false;
			// var totdifflvl = data.data[id];
			var difflvlQues = [];
			var totQues = 0;
			var i = 1;
			for ( var key in data.data[id]) {
				if (key != "SID" && key != "saved" && key != "savedTC") {
					difflvlQues[i] = $("#" + sectExpandId + "inp" + i).val();
					totQues = totQues + parseInt(difflvlQues[i]);

					if (difflvlQues[i] != 0) {
						qGrpCounter++;
					}
					i++;
				}
			}

			totTime = $("#" + sectEstTimeId).val();

			if (totTime != "0") {
				sectionTimeErr = true;
			}

			if (qGrpCounter == 0 || !sectionTimeErr) {

				if (!$("#" + sectExpandId + "inp1").next().next().children()
						.hasClass("pos-absolute")) {
					$("#" + sectExpandId + "inp1")
							.next()
							.next()
							.append(
									"<div class=\"col-sm-5 col-sm-offset-12 pos-absolute\">"
											+ "<p class=\"red-color qGrpErr hidden\">Select Question Groups</p>"
											+ "<p class=\"red-color sectionTimeErr hidden\">Select Section Time</p>"
											+ "</div>");
				}

				qGrpCounter ? $(".qGrpErr").addClass("hidden") : $(".qGrpErr")
						.removeClass("hidden");
				sectionTimeErr ? $(".sectionTimeErr").addClass("hidden") : $(
						".sectionTimeErr").removeClass("hidden");
				return false;

			} else {

				if (totQues == NaN) {
					$("#" + sectExpandId + "totques").text(0);
					$("#" + sectExpandId + "tottime").text(totTime);
				} else {
					$("#" + sectExpandId + "totques").text(totQues);
					$("#" + sectExpandId + "tottime").text(totTime);
				}

				var totNumQues = 0;
				var totalEstTime = 0;
				$("#totEstTime").text();
				var totalSectons = $(".tconfig-shadow").length;
				for (i = 1; i <= totalSectons; i++) {
					totNumQues = totNumQues
							+ parseInt($("#sect" + i + "totques").text());
					totalEstTime = totalEstTime
							+ parseInt($("#sect" + i + "tottime").text());
				}
				$("#totQues").text(totNumQues);
				$("#totEstTime").text(totalEstTime + " Min");
				// used to collapse this section after clicking save button
				$('#' + sectExpandId).hide(
						function() {
							$("." + sectExpandId + "Class").removeClass(
									"icon-substract").addClass("icon-add");
							$("." + sectExpandId + "Class").parents(
									".sect-open").removeClass('sect-open')
									.addClass('sect-close');
						});
			}
			var savedTC = {};
			var l = 1;
			for ( var key in data.data[id]) {

				var level = key.slice(-1);
				level = parseInt(level);
				if (key != "SID" && key != "saved" && key != "savedTC") {
					if ($("#" + sectExpandId + "inp" + l).val() != 0) {
						var TestPaperJSON = {

							"noOfQuestionGroup" : $(
									"#" + sectExpandId + "inp" + l).val(),
							"sectionTimeLimit" : $("#" + sectEstTimeId).val(),
							"departmentVO" : {
								"departmentId" : deptId
							},
							"sectionVO" : {
								"sectionId" : data.data[id].SID
							},
							"complexityLevel" : {
								"qgComplexityLevelId" : level
							},
							"test" : {
								"testId" : data.TID
							}
						};
						TestPaperJSONArray.push(TestPaperJSON);
					}
					if (data.data[id].saved == true) {
						TestPaperJSON.testConfigId = data.data[id].savedTC["TCIDLVL"
								+ level];
					}
					savedTC["LVL" + level] = $("#" + sectExpandId + "inp" + l)
							.val();
					savedTC["STL"] = $("#" + sectEstTimeId).val();

					l++;
				}
			}

			if (data.data[id].saved != undefined) {
				if (data.data[id].saved == true)
					data.data[id].saved = true;

				savedTC["SID"] = data.data[id].SID;
				savedTC["TOTQ"] = totQues;
				// savedTC["TOT"] = totNumQues;
				// savedTC["TET"] =totalEstTime;
				data.data[id].savedTC = savedTC;
				if (totQues != 0 && $("#" + sectEstTimeId).val() != "0") {
					AH
							.saveTestPaperForSection(AP.baseURL
									+ "setTestPaper", "PUT",
									TestPaperJSONArray, data, deptId,
									sectionObj);
				} else {

				}

			} else {

				data.data[id].saved = true;

				savedTC["SID"] = data.data[id].SID;
				savedTC["TOTQ"] = totQues;
				// savedTC["TOT"] = totNumQues;
				// savedTC["TET"] =totalEstTime;
				data.data[id].savedTC = savedTC;

				if (totQues != 0 && $("#" + sectEstTimeId).val() != "0") {
					AH.saveTestPaperForSection(AP.baseURL + "setTestPaper",
							"POST", TestPaperJSONArray, data, deptId,
							sectionObj);
				} else {
					if (!$("#" + sectExpandId + "inp1").next().next()
							.children().hasClass("pos-absolute")) {
						$("#" + sectExpandId + "inp1")
								.next()
								.next()
								.append(
										"<div class=\"col-sm-5 col-sm-offset-12 pos-absolute\">"
												+ "<p class=\"red-color qGrpErr hidden\">Select Question Groups</p>"
												+ "<p class=\"red-color sectionTimeErr hidden\">Select Section Time</p>"
												+ "</div>");
					}

					qGrpCounter ? $(".qGrpErr").addClass("hidden") : $(
							".qGrpErr").removeClass("hidden");
					sectionTimeErr ? $(".sectionTimeErr").removeClass("hidden")
							: $(".sectionTimeErr").addClass("hidden");
					return false;
				}
			}
		}

		preTestPreviewClick = function() {

			AH.testPreviewClick(AP.baseURL + "setTestPaper/preview", "GET",
					data, sectionObj, deptId);
		}

	};

	TC.renderTestPreview = function(previewData, sectionObj, data, secId,
			deptId) {
		var preNextIndex1 = [];
		var preNextIndex2 = [];
		var preNextqGrpObj = [];
		var preNextqTxtObj = [];
		var nextKey = 0;
		var secObjLen = Object.keys(sectionObj).length;
		var spanClass = 'span.text-align-left.pointer.problem-summary-label.h3-cls.display-block';
		var testPreviewHtm = '';
		testPreviewHtm += ' <div id="tstPreview" class="r3_3-margin">'
				+ '<div class="row">'
				+ '  <div class="col-sm-12  col-md-12 l0-r0-padding">'
				+ '     <div class="col-sm-6  col-md-6 l0-r0-padding ">'

				+ '          <div clss="row">'
		if (previewData.data[0].name != undefined) {
			testPreviewHtm += '<h1 class="secondary-text-color"><span id="currQuesGrpId">'
					+ previewData.data[0].name + '</span></h1>'
		} else {
			testPreviewHtm += '<h1 class="secondary-text-color"><span id="currQuesGrpId">Group</span></h1>'
		}
		testPreviewHtm += '<h2 class="prim-text-black-color t15-margin">Reference</h2>'

		if (previewData.data[0].content != undefined) {
			testPreviewHtm += '<p id="reference" class="text-align-justify ref-text">'
					+ previewData.data[0].content
					+ '</p>'

					+ '<div id="audioReference" class="col-sm-12 text-align-justify prim-text-black-color aud-ref-text" hidden>'
					+ '  With reference to above audio clip answer the following question'
					+ '  <div id="audioSrc" class="t1-b1-padding col-sm-11 l0-r0-padding audiosrc-test">'
					+ '    <audio id="audio" src="resource/test.mp3"></audio>'
					+ '    <div id="buttons" class="col-sm-1">'
					+ '        <span id="play_pause" onclick="PlayPause(this.id)" class="pointer fontsize-54 iconplaypause icon-play"></span>'
					+ '    </div>'
					+ '     <div id="progress" class="col-sm-offset-1 l30-r0-padding">'
					+ '         <div class="col-sm-1 l0-r0-padding" id="time">0.00</div>'
					+ '         <div class="col-sm-1 l0-r0-padding" id="totalTime" align="right">0.00</div>'
					+ '        <div class="col-sm-5" id="progressOut">'
					+ '            <div id="progressIn">'
					+ '            </div>'
					+ '        </div>'
					+ '     </div>'
					+ '  </div>'
					+ '   </div>'

					+ '  <div id="videoQuestionRef" class="col-sm-12 prim-text-black-color aud-ref-text" align="center" hidden>'
					+ '  With reference to below video clip answer the following question'
					+ ' <div class="row">'
					+ ' <div class="col-sm-12 t1-b1-margin" >'
					+ '  <video id="videoQuestion" controls class="width-50" >'
					+ '     <source  src="http://www.w3schools.com/html/mov_bbb.mp4" type="video/mp4">'
					+ '     Your browser does not support the video tag.'
					+ '  </video>'
					+ '   </div>'
					+ '  </div>'
					+ '   </div>'

					+ '  <div id="imageQuestionRef" class="col-sm-12 prim-text-black-color aud-ref-text" align="center" hidden>'
					+ '   With reference to below Images answer the following question'
					+ '  <div class="row">'
					+ '  <div class="col-sm-12 t1-b1-margin">'
					+ '  <div id="carousel-example-generic" class="carousel slide width-50" data-ride="carousel">'
					+ '   <!-- Wrapper for slides -->'
					+ '   <div class="carousel-inner">'
					+ '     <div class="item active">'
					+ '        <img id="imgQues" src="resource/images/dummy1.jpg" class="img-question">'
					+ '        <div class="carousel-caption">'
					+ '             '
					+ '        </div>'
					+ '    </div>'

					+ '    </div>'
					+ '   </div>'
					+ '  </div>'
					+ '  </div>'
					+ '  </div>'

		} else {

			testPreviewHtm += '<p id="reference" class="text-align-justify ref-text">'
				
				if(previewData.data[0].content != undefined ){
					testPreviewHtm += ""+ previewData.data[0].content
				}else{
					testPreviewHtm += ""
				}
			testPreviewHtm += '</p>'
					+ '<div id="audioReference" class="col-sm-12 text-align-justify prim-text-black-color aud-ref-text" hidden>'
					+ '  With reference to above audio clip answer the following question'
					+ '  <div id="audioSrc" class="t1-b1-padding col-sm-11 l0-r0-padding audiosrc-test">'
					+ '    <audio id="audio" src="resource/test.mp3"></audio>'
					+ '    <div id="buttons" class="col-sm-1">'
					+ '        <span id="play_pause" onclick="PlayPause(this.id)" class="pointer fontsize-54 iconplaypause icon-play"></span>'
					+ '    </div>'
					+ '     <div id="progress" class="col-sm-offset-1 l30-r0-padding">'
					+ '         <div class="col-sm-1 l0-r0-padding" id="time">0.00</div>'
					+ '         <div class="col-sm-1 l0-r0-padding" id="totalTime" align="right">0.00</div>'
					+ '        <div class="col-sm-5" id="progressOut">'
					+ '            <div id="progressIn">'
					+ '            </div>'
					+ '        </div>'
					+ '     </div>'
					+ '  </div>'
					+ '   </div>'

					+ '  <div id="videoQuestionRef" class="col-sm-12 prim-text-black-color aud-ref-text" align="center" hidden>'
					+ '  With reference to below video clip answer the following question'
					+ ' <div class="row">'
					+ ' <div class="col-sm-12 t1-b1-margin" >'
					+ '  <video id="videoQuestion" controls class="width-50" >'
					+ '     <source src="http://www.w3schools.com/html/mov_bbb.mp4" type="video/mp4">'
					+ '     Your browser does not support the video tag.'
					+ '  </video>'
					+ '   </div>'
					+ '  </div>'
					+ '   </div>'

					+ '  <div id="imageQuestionRef" class="col-sm-12 prim-text-black-color aud-ref-text" align="center" hidden>'
					+ '   With reference to below Images answer the following question'
					+ '  <div class="row">'
					+ '  <div class="col-sm-12 t1-b1-margin">'
					+ '  <div id="carousel-example-generic" class="carousel slide width-50" data-ride="carousel">'
					+ '   <!-- Wrapper for slides -->'
					+ '   <div class="carousel-inner">'
					+ '     <div class="item active">'
					+ '        <img id="imgQues" src="resource/images/dummy1.jpg" class="img-question">'
					+ '        <div class="carousel-caption">'
					+ '            '
					+ '        </div>'
					+ '    </div>'

					+ '    </div>'
					+ '   </div>'
					+ '  </div>'
					+ '  </div>'
					+ '  </div>'
		}

		testPreviewHtm += '<hr />'
				+ '          </div>'
				+ '          <div clss="row">'
				+ '              <div id="quesSection" class="col-sm-12 col-md-12 t1-b1-padding l0-r0-padding">'
				+ '                  <div class="col-sm-3 l0-r0-padding b2-padding">'
				+ '                      <h2 class="prim-text-black-color">Question #<span id="currQGrp">Q1</span></h2>'
				+ '                  </div>'
				+ '                  <div class="col-sm-9 l30-r0-padding ">'
				+ '                      <h3>'
				+ '                           <span class="icon-flag flag-glyph pointer" id="tagQues" onclick="tagThisQues(this.id)"></span>'
				+ '                          <span data-placement="right" title="Tagged question to be answered later">'
				+ '                             <h3 class="cust-clr1 pointer" id="tagQues1" onclick="tagThisQues(this.id)">Tag question</h3>'
				+ '                          </span>'
				+ '                      </h3>'
				+ '                  </div>'
				+ '                  <div class="col-sm-9 l30-r0-padding custom-height">'
				+ '                      <div id="currQTxt" class="secondary-text-color">'
				+ '                      </div>'
				+ '                      <h3 id="options" class=" display-block t2-padding">'
				+ '                          <span class="col-sm-6 l0-r0-padding">'
				+ '                              <input type="checkbox" name="" value="">Frequency'
				+ '                          </span>'
				+ '                          <span class="col-sm-6">'
				+ '                              <input type="checkbox" name="" value="">Wavelength'
				+ '                          </span>'
				+ '                          <br><br>'
				+ '                          <span class="col-sm-6 l0-r0-padding">'
				+ '                              <input type="checkbox" name="" value="">Weight'
				+ '                          </span>'
				+ '                          <span class="col-sm-6">'
				+ '                              <input type="checkbox" name="" value="">Viscosity'
				+ '                          </span>'
				+ '                      </h3>'
				+ '                  </div>'
				+ '              </div>'
				+ '          </div>'
				+ '          <div clss="row">'
				+ '              <div class="col-sm-9 col-sm-offset-3 t8-margin">'
				+ '                  <span onclick="com.coep.test.testConfig.switchtoPrevQues()" class="col-sm-6 h3-cls secondary-text-color pointer">'
				+ '                      <span class="icon-left-navigation prev-quest"></span>'
				+ '                      Previous<br> Question'
				+ '                  </span>'
				+ '                  <span onclick="com.coep.test.testConfig.switchtoNextQues()" class="pull-right h3-cls col-sm-6 secondary-text-color text-align-right pointer">'
				+ '                      Next<span class="icon-right-navigation next-quest"></span>'
				+ '                      <br> Question'
				+ '                  </span>'
				+ '              </div>'
				+ '          </div>'
				+ '      </div>'
				+ '      <div class="col-sm-5 col-sm-offset-1 col-md-5 test-sect">'
				+ '          <div class="col-sm-12 custom-bg" align="center">'
				+ '              <span id="sectionName pointer">'

				+ '                  <h1 id="sectionNameLbl" class="problem-summary-label display-inline-block ">'
				+ sectionObj[secId]
				+ '</h1>'
				+ '              </span>'
				+ '              &nbsp;&nbsp;'
				+ '              <span onclick="showSection(this)" class="icon-down-arrow pointer"></span>'
				+ '              <div class="hidden col-sm-8 col-sm-offset-3 l30-r0-padding custom-bg text-align-left" id="sectOption">'
		for ( var key in sectionObj) {
			nextKey = key;
			testPreviewHtm += '<li class="sect-option-clr"><span onclick="selSection('
					+ key
					+ ',this)" class="text-align-left pointer problem-summary-label h3-cls display-block">'
					+ sectionObj[key] + '</span></li>'
		}

		testPreviewHtm += '</div>'
				+ ' </div>'
				+ ' <div class="col-sm-12 sect-quest-div l0-r0-padding t2-padding">'
		for (var i = 0; i < previewData.data.length; i++) {
			if (previewData.data[i].QUES.length > 1) {

				for (var j = 0; j < previewData.data[i].QUES.length; j++) {
					preNextIndex1.push(i);
					preNextIndex2.push(j);
					preNextqGrpObj.push('qGrp' + (i + 1) + '_' + (j + 1));
					preNextqTxtObj.push('qTxt' + (i + 1) + '_' + (j + 1));

					testPreviewHtm += '<div class="col-sm-12 sect-quest pointer" onclick="switchtoThisQues('
							+ i
							+ ','
							+ j
							+ ',this,\'qGrp'
							+ (i + 1)
							+ '_'
							+ (j + 1)
							+ '\',\'qTxt'
							+ (i + 1)
							+ '_'
							+ (j + 1)
							+ '\')">'
							+ ' <div class="col-sm-12 ">'
							+ ' <h4 id="qGrp'
							+ (i + 1)
							+ '_'
							+ (j + 1)
							+ '">Q'
							+ (i + 1)
							+ '.'
							+ (j + 1)
							+ '</h4>'
							+ '<h4 id="qTxt'
							+ (i + 1)
							+ '_'
							+ (j + 1)
							+ '" class="prim-text-black-color">'
							+ previewData.data[i].QUES[j].content
							+ '</h4>'
							+ '</div>' + '</div>'
				}
			} else {
				preNextIndex1.push(i);
				preNextIndex2.push(0);
				preNextqGrpObj.push('qGrp' + i);
				preNextqTxtObj.push('qTxt' + (i + 1));

				testPreviewHtm += '<div class="col-sm-12 sect-quest pointer" onclick="switchtoThisQues('
						+ i
						+ ',0,this,\'qGrp'
						+ i
						+ '\',\'qTxt'
						+ (i + 1)
						+ '\')">'
						+ '<div class="col-sm-12 ">'
						+ '<h4 id="qGrp'
						+ i
						+ '">Q'
						+ (i + 1)
						+ '</h4>'
						+ '<h4 id="qTxt'
						+ (i + 1)
						+ '" class="prim-text-black-color">'
						+ previewData.data[i].QUES[0].content
						+ '</h4>'
						+ '</div>' + '</div>'
			}
		}

		testPreviewHtm += '</div>'
				+ '<div class="col-sm-12 t2-b2-padding pointer" align="center">'
				+ '<button onclick="selNextSection('
				+ secId
				+ ', \'pre\')" title="Previous Section" id="prevSectBtn" class="let-space btn modify-pbm-btn prev-sectbtn">PREVIOUS SECTION</button>'
				+ '<button onclick="selNextSection('
				+ secId
				+ ', \'nxt\')" title="Next Section" id="nextSectBtn" class="let-space btn modify-pbm-btn next-sectbtn">NEXT SECTION</button>'
				+ '</div>'

				+ '</div>'
				+ '</div>'

				+ '<div class="col-sm-12 t2-b2-padding" align="center">'
				// TC.renderSavedSetTestPaper = function(data,
				// TestPaperJSONArray,
				// savedTCData, deptId, sectionObj)
				+ '<a href="javascript:callRenderSavedSetTestPaper()"><span class="icon-back1 icon-back"></span><span class="h3-cls secondary-text-color">Back to Configuration</span></a>&nbsp;&nbsp;&nbsp;&nbsp;'
				+ '<a href="#" data-toggle="modal" data-target="#saveTestConfigModal">'
				+ '<span class="icon-tick prim-blue"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="h3-cls secondary-text-color">Save Test Configuration</span>'
				+ '</a>' + '</div>' + '</div>';

		$("#main-div").html('');
		$("#main-div").css("padding-top", "35px");
		$("#main-div").html(testPreviewHtm);

		
		 var tagged = 0;
		 tagThisQues = function (currQGrp) {	
			
			 var thisQues = $("#currQGrp").text();
//			 thisQues = thisQues.replace('.', '_');
		     if ($("#flag_span" + thisQues).hasClass('icon-flag')) {
		     	$("#flag_span" + thisQues).removeClass('icon-flag flag-glyph');
		     } else {   
				$("#flag_span" + thisQues).addClass('icon-flag flag-glyph');
		     }
		 }
		 
		 
		 
		 
		//Function for tag question	
			
			$('[data-toggle="tooltip"]').tooltip();
		    $('.tooltip-css').mouseleave(function () {
		        $(this).tooltip('hide');
		    });
		
		    
		if (document.getElementById('audio') != undefined) {

			var player = document.getElementById('audio');
			if (player == null) {
				player = "audio#audio";
			}

			PlayPause = function(id) {
				if ($('#' + id).hasClass('icon-play')) {
					player.play();
					$('#' + id).removeClass('icon-play').addClass('icon-pause');
				} else {
					player.pause();
					$('#' + id).removeClass('icon-pause').addClass('icon-play');
				}
			}

			// UPDATE PROGRESS BAR
			updateProgress = function() {
				var progress = $("#progressIn");
				var value = 0;
				var totMin = Math.floor(player.duration / 60);
				var totSec = Math.floor(player.duration % 60);
				document.getElementById('totalTime').innerHTML = totMin + ":"
						+ totSec;
				// If duration = infinity set value to 100

				if (player.duration == 'Infinity') {
					value = 100;
				}
				// else if it is > 0 calculate percentage to highlight

				else if (player.currentTime > 0) {
					value = Math.floor((100 / player.duration)
							* player.currentTime);
				}

				// set the width of the progress bar

				progress.css({
					'width' : value + '%'
				})

				if (player.currentTime == player.duration) {
					$("#play_pause").removeClass('icon-pause').addClass(
							'icon-play');
					progress.css('width', '0%');
					player.currentTime = 0;
				}

				// set the new timestamp
				$('#time').html(formatTime(player.currentTime))
			}

			// add event listener for audio time updates
			player.addEventListener("timeupdate", updateProgress, false);

			$('#progressOut').click(function(e) {
				var offset = $(this).offset();
				var relX = Math.floor(e.pageX - offset.left);
				var totWidth = Math.floor($(this).width());
				player.currentTime = (relX / totWidth) * player.duration;
			})

			// Format the audio tag's time stamp
			formatTime = function(seconds) {
				minutes = Math.floor(seconds / 60);
				minutes = (minutes >= 10) ? minutes : "" + minutes;
				seconds = Math.floor(seconds % 60);
				seconds = (seconds >= 10) ? seconds : "0" + seconds;
				return minutes + ":" + seconds;
			}

		}
		//
		callRenderSavedSetTestPaper = function() {
			var TestPaperJSONArray = [];
			var savedTCData = {};
			TC.renderSavedSetTestPaper(data, TestPaperJSONArray, savedTCData,
					deptId, sectionObj)
		};

		$(".test-preview-li").addClass("active");


		showSection = function(thisElement) {
			$(thisElement).next().toggleClass("hidden");
		}
		selSection = function(key, thisElement) {

			$("#sectionNameLbl").text($(thisElement).text());
			$("#sectOption").toggleClass("hidden");
			AH.testPreviewOnSectionClick(AP.baseURL + "setTestPaper/preview",
					"GET", key, data, sectionObj, deptId);
		}

		selNextSection = function(key, status) {
			var sectionkeys = [];
			sectionkeys = Object.keys(sectionObj);
			var nxtsectId = 0;
			if (status == "pre") {
				if (sectionkeys[0] != secId) {
					nxtsectId = sectionkeys
							.indexOf(typeof secId !== 'NaN' ? secId : "\""
									+ secId + "\"") - 1;
					nxtsectId = sectionkeys[nxtsectId];
					AH.testPreviewOnSectionClick(AP.baseURL
							+ "setTestPaper/preview", "GET", nxtsectId, data,
							sectionObj, deptId);
					// $("#sectionNameLbl").text(sectionObj[secId]);
					// $("#sectOption").toggleClass("hidden");
				} else {

				}
			} else {
				if (sectionkeys[secObjLen - 1] != secId) {
					nxtsectId = sectionkeys.indexOf(secId) + 1;
					nxtsectId = sectionkeys[nxtsectId];
					AH.testPreviewOnSectionClick(AP.baseURL
							+ "setTestPaper/preview", "GET", nxtsectId, data,
							sectionObj);
					// $("#sectionNameLbl").text(sectionObj[secId]);
					// $("#sectOption").toggleClass("hidden");
				}
			}

		}

		var i = 1;
		var qtxtTemp ='';
		switchtoThisQues = function(j, k, currQRow, qGrp, qTxt) {

			var ansType = previewData.data[j].QUES[k].ANSTYP;

			$(".pointer").removeClass("curr-quest");
			// make current question row active
			$(currQRow).addClass("curr-quest");
			// change quest group number to current clicked quest
			$("#currQGrp").text($("#" + qGrp).text());
			// change quest text to current clicked quest
			if (previewData.data[j].QUES.length > 1) {
				 qtxtTemp = previewData.data[j].QUES[k].content;
			$("#currQTxt").html("<h1>"+qtxtTemp.replace(/&lt;/g,'<').replace(/&gt;/g,'>').replace(/&amp;/g,'&')+"</h1>");
			
			}else{
				qtxtTemp = previewData.data[j].QUES[0].content;
				$("#currQTxt").html("<h1>"+qtxtTemp.replace(/&lt;/g,'<').replace(/&gt;/g,'>').replace(/&amp;/g,'&')+"</h1>");
			}
//			$("#currQTxt").text($("#" + qTxt).val().replace(/\n/g,"<br />"));
			// Update current question group id
			var currQGrp = $("#" + qGrp).text().split('.');
			$("#currQuesGrpId").text(currQGrp[0]);

			if (ansType == 1) {

				TC.mediaTypeQuestion(j, k, previewData);

				var mcaOptions = "";
				for (var l = 0; l < previewData.data[j].QUES[k].ANS.length; l++) {
					if (l == 0 || l == 2) {
						mcaOptions += "<span id = \"ansSpan\" class=\"col-sm-6 l0-r0-padding\">"
								+ "<input type=\"checkbox\" id=\"\" class=\"checkBoxClass\" name=\"option"
								+ l
								+ "\" value=\""
								+ previewData.data[j].QUES[k].ANS[l].content
								+ "\">"
								+ previewData.data[j].QUES[k].ANS[l].content
								+ "" + "</span>"

						// $("#ansSpan").addClass("col-sm-6 l0-r0-padding");
					} else {
						mcaOptions += "<span id = \"ansSpan\" class=\"col-sm-6\">"
								+ "<input type=\"checkbox\" id=\"\" class=\"checkBoxClass\" name=\"option"
								+ l
								+ "\" value=\""
								+ previewData.data[j].QUES[k].ANS[l].content
								+ "\">"
								+ previewData.data[j].QUES[k].ANS[l].content
								+ "" + "</span>"

						// $("#ansSpan").addClass("col-sm-6");
					}

				}
				$("#options").html(mcaOptions);

			} else if (ansType == 4) {

				TC.mediaTypeQuestion(j, k, previewData);
				var truefalseOptions = "<span class=\"col-sm-6 l0-r0-padding\">"
						+ "<input type=\"radio\" id=\"\" name=\"option1\" value=\""
								+ previewData.data[j].QUES[k].ANS[0].content
								+ "\">true"
						+ "</span>"
						+ "<span class=\"col-sm-6\">"
						+ "<input type=\"radio\" id=\"\" name=\"option1\" value=\""
								+ previewData.data[j].QUES[k].ANS[1].content
								+ "\">false"
						+ "</span>";

				$("#options").html(truefalseOptions);
			} else if (ansType == 2) {

				// TC.mediaTypeQuestion(j, k, previewData);
				// var fibOptions = "<span class=\"col-sm-6 l0-r0-padding\">"
				// + "<textarea type=\"\" id=\"\" name=\"option1\"
				// class=\"form-control\" value=\"\" placeholder=\"Enter your
				// answer here\"></textarea>"
				// + "</span>";
				// $("#options").html(fibOptions);

				TC.mediaTypeQuestion(j, k, previewData);

				var fibOptions = "";
				for (var l = 0; l < previewData.data[j].QUES[k].ANS.length; l++) {

					fibOptions += "<span class=\"col-sm-6 l0-r0-padding\">"
							+ "<input type=\"radio\" id=\"\" name=\"option1"

							+ "\" value=\""
							+ previewData.data[j].QUES[k].ANS[l].content
							+ "\">"
							+ previewData.data[j].QUES[k].ANS[l].content + ""
							+ "</span>"

				}
				$("#options").html(fibOptions);

			} else if (ansType == 3) {

				TC.mediaTypeQuestion(j, k, previewData);

				var mtpOptions = "";
				for (var l = 0; l < previewData.data[j].QUES[k].ANS.length; l++) {

					mtpOptions += "<span class=\"col-sm-6 l0-r0-padding\">"
							+ "<input type=\"radio\" id=\"\" name=\"option1"

							+ "\" value=\""
							+ previewData.data[j].QUES[k].ANS[l].content
							+ "\">"
							+ previewData.data[j].QUES[k].ANS[l].content + ""
							+ "</span>"

				}
				$("#options").html(mtpOptions);

			} else if (ansType == 5) {

				TC.mediaTypeQuestion(j, k, previewData);

				var scaOptions = "";
				for (var l = 0; l < previewData.data[j].QUES[k].ANS.length; l++) {

					scaOptions += "<span class=\"col-sm-6 l0-r0-padding\">"
							+ "<input type=\"radio\" id=\"\" name=\"option1"

							+ "\" value=\""
							+ previewData.data[j].QUES[k].ANS[l].content
							+ "\">"
							+ previewData.data[j].QUES[k].ANS[l].content + ""
							+ "</span>"

				}
				$("#options").html(scaOptions);

			}

			i++;
			if (i > 15) {
				i = 1;
			}

			// /////// previous/Next question functions ///////////

			TC.switchtoNextQues = function() {

				var index = preNextqGrpObj.indexOf(qGrp);
				var temp = (index + 1);

				if (temp == preNextqGrpObj.length) {
					temp = preNextqGrpObj.length - 1;
				}

				switchtoThisQues(preNextIndex1[temp], preNextIndex2[temp],
						"div.col-sm-12.sect-quest.pointer curr-quest",
						preNextqGrpObj[temp], preNextqTxtObj[temp]);
			};

			TC.switchtoPrevQues = function() {

				var index = preNextqGrpObj.indexOf(qGrp);
				var temp = (index - 1);

				if (temp == -1) {
					temp = 0;
				}

				switchtoThisQues(preNextIndex1[temp], preNextIndex2[temp],
						"div.col-sm-12.sect-quest.pointer curr-quest",
						preNextqGrpObj[temp], preNextqTxtObj[temp]);
			};

		}

		if (previewData.data[0].MED != undefined) {
			if (previewData.data[0].QUES.length > 1) {
				switchtoThisQues(0, 0,
						"div.col-sm-12.sect-quest.pointer curr-quest",
						"qGrp1_1", "qTxt1_1");
			}
			switchtoThisQues(0, 0,
					"div.col-sm-12.sect-quest.pointer curr-quest", "qGrp0",
					"qTxt1");
		} else {
			if (previewData.data[0].QUES.length > 1) {
				switchtoThisQues(0, 0,
						"div.col-sm-12.sect-quest.pointer curr-quest",
						"qGrp1_1", "qTxt1_1");
			} else {
				switchtoThisQues(0, 0,
						"div.col-sm-12.sect-quest.pointer curr-quest", "qGrp0",
						"qTxt1");
			}
		}

	};

	TC.mediaTypeQuestion = function(j, k, previewData) {

		$("#currQuesGrpId").html(previewData.data[j].name);
		if (previewData.data[j].MEDTYP == 1) {
			$("#imageQuestionRef").hide();
			$("#videoQuestionRef").hide();
			$("#audioReference").hide();
			if(previewData.data[j].content == undefined){
				$("#reference").show();
				$("#reference").html('');
			}else{
				$("#reference").show();
				$("#reference").html(previewData.data[j].content);
			}
			$("#audioReference").hide();
		} else if (previewData.data[j].MEDTYP == 2) {
			$("#reference").hide();
			$("#imageQuestionRef").hide();
			$("#videoQuestionRef").hide();
			$("#audioReference").show();
			$("#audio").attr("src", AP.baseURL +'/media/getImage?mediaID='+previewData.data[j].MED+'&questionGroupId='+previewData.data[j].QGID);
		} else if (previewData.data[j].MEDTYP == 3) {
			$("#reference").hide();
			$("#audioReference").hide();
			$("#imageQuestionRef").hide();
			$("#videoQuestionRef").show();
			$("#videoQuestion").attr("src", AP.baseURL +'/media/getImage?mediaID='+previewData.data[j].MED+'&questionGroupId='+previewData.data[j].QGID);
		} else if (previewData.data[j].MEDTYP == 4) {
			$("#reference").hide();
			$("#audioReference").hide();
			$("#videoQuestionRef").hide();
			$("#imageQuestionRef").show();
			$("#imgQues").attr("src", AP.baseURL +'/media/getImage?mediaID='+previewData.data[j].MED+'&questionGroupId='+previewData.data[j].QGID);
		}
	};

	TC.renderStudentTest = function() {

		var htm = '';

		htm += '  <div class="row">'
				+ '<div class="col-sm-8 col-md-8 col-md-offset-2 col-sm-offset-2 text-align-justify">'
				+ ' <h1 class="secondary-text-color text-align-center t1-b1-padding">'
				+ ' WELCOME!</h2>'
				+ ' <span>'
				+ '<h3 class="prim-text-black-color">'
				+ '  Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rho.'

				+ 'ncus ut, imperdiet a, venenatis vitae, justo.'
				+ '</h3>'
				+ '</span>'
				+ ' <h1 class="secondary-text-color t1-b1-padding">'
				+ 'INSTRUCTIONS</h2>'
				+ '<span>'
				+ '<h3 class="prim-text-black-color">'
				+ ' The online exam is easy to get through<br />'
				+ ' We assume applicant taking the online test has a basic familiarity with computers and is comfortable with the use of keyboard and mouse'
				+ '</h3>'
				+ '</span><br />'
				+ '<h2 class="secondary-text-color t1-b1-padding">During the Exam</h2>'

				+ '<ol style="padding-left:14px">'
				+ '<li class="b10-margin"><h4 class="prim-text-black-color">The test consists of multiple choice questions (MCQ type). In this, for each given question, the applicant must choose the right, or the closest, answer from among the choices given.</h4></li>'
				+ '<li class="b10-margin"><h4 class="prim-text-black-color">All questions are manadatory.</h4></li>'
				+ '<li class="b10-margin"><h4 class="prim-text-black-color">Flag indicates question to be answered later. </h4></li>'
				+ '<li class="b10-margin"><h4 class="prim-text-black-color">You can navigate and look through the questions within same section.</h4></li>'
				+ '<li class="b10-margin"><h4 class="prim-text-black-color">To proceed to the next section click on the next section button at the bottom. Once done going back to the previous section is not allowed.</h4></li>'
				+ '<li class="b10-margin"><h4 class="prim-text-black-color">Total time allotted for the test is 60 mins. General time scheme for each section. </h4></li>'
				+ '</ol>'
				+ '<div class="input-group t1-b1-padding display-inline-flex">'
				+ '<span class="sec-time">I-10mins</span>'
				+ '<span class="sec-time">II-20-mins</span>'
				+ '<span class="sec-time">III-30-mins</span>'
				+ '</div>'
				+ '</div>'
				+ '<div class="col-sm-12  l0-r0-padding">'
				+ ' <button id="startTestBtn" type="submit" title="SUBMIT" class="pull-right btn custom-btn" onclick = "fullScreenMode()">START TEST</button>'
				+ '</div>' + '</div>';

		$("#main-div").html('');
		$("#main-div").css("padding-top", "35px");
		$("#main-div").html(htm);

		fullScreenMode = function() {

			window.open("test", "full",
					"dependent=yes, fullscreen=yes, scrollbars=yes, titlebar=yes, width= "
							+ window.screen.width + ", height= "
							+ window.screen.height);
		};

	};
	
	TC.renderShowTestClick = function() {
		AH.getAllTests(AP.baseURL
				+ "getAllTest/", "GET");
	}
	
	
	
	
	
	
	TC.renderTestConfigDetails = function(data) {
		$(".child-menu-quesbank").hide();
		$(".child-menu-configTest").hide();		
		$("#listofuser-div").hide();
		var jsonTestConfigDetailsUpdate = {};
		document.title = "Test Configureation Details";
		var renderTestConfigHtm = '';
		var flag = 0;
//		TODO
		
		console.log(data);
		renderTestConfigHtm+= '<h1 class="datkbrown" style="font-weight : bold;">Test Configuration</h1>'
			+'<button id="updateTest" onclick="com.coep.test.testConfig.confirmationToModifyTest();" title="Update Test" class="pull-right btn custom-btn">'
			+'Update Test Configuration</button>'
			+'<button id="CancelModification" onclick="com.coep.test.testConfig.renderShowTestClick();" title="Cancel Update" class="pull-right btn custom-btn" style="margin-right: 8px;">Cancel Updation</button>'
			+'<h2 class="secondary-text-color h3">'+data[0].DEPT+' Department</h2>'
			
			for(var n=1; n<data.length; n++){
				
				+'<div class="container">'
				+'<div class="row">'
				if(flag == 0){
					renderTestConfigHtm += '<div class="col-md-12 col-sm-12  configersection"><p class=" h4">Section - <span class=" h4" >'
						+data[n].SEC+'</span></p></div>  <div class="col-md-4 col-sm-4 secondary-text-color text-align-center h4 bottomborder">Level</div><div class="col-md-4 col-sm-4 secondary-text-color h4 bottomborder text-align-center">Available QuestionGroup</div><div class="col-md-4 col-sm-4 secondary-text-color h4 bottomborder text-align-center">Selected QuestionGroup</div>' 
				}
			
				if((n+1) < data.length){
				if(data[n].SEC == data[n+1].SEC){
					
					flag = 1;
					renderTestConfigHtm 	+='<div class="col-md-4 col-sm-4 text-align-center" class="qgroup-input">'+data[n].COMPLEVEL+'</label></div> <div class="col-md-4 col-sm-4 text-align-center"><label class="qgroup-input">'+data[n].NOQGPAVAIL+'</label></div>'
					+'<div class="col-md-4 col-sm-4 "><input onchange = "com.coep.test.testConfig.checkVal(this.value,'+data[n].NOQGPAVAIL+');" type="text" id="'+n+'" class="qgroup-input" title="'+data[n].CONFID+'" value='+data[n].NOQUESGRP+'><span class="icontxt-edit icon-text-edit"></span></div>'
					
				}else{
					
					flag = 0;
					renderTestConfigHtm 	+='<div class="col-md-4 col-sm-4 text-align-center"><label class="qgroup-input">'+data[n].COMPLEVEL+'</label></div><div class="col-md-4 col-sm-4 text-align-center"><label class="qgroup-input">'+data[n].NOQGPAVAIL+'</label></div>'
					+'<div class="col-md-4 col-sm-4"><input onchange = "com.coep.test.testConfig.checkVal(this.value,'+data[n].NOQGPAVAIL+');" type="text" id="'+n+'" class="qgroup-input" title="'+data[n].CONFID+'" value='+data[n].NOQUESGRP+'><span class="icontxt-edit icon-text-edit"></span></div>'
				}
				}else{
					
					flag = 0;
					renderTestConfigHtm 	+='<div class="col-md-4 col-sm-4 text-align-center"><label class="qgroup-input">'+data[n].COMPLEVEL+'</label></div><div class="col-md-4 col-sm-4 text-align-center"><label class="qgroup-input">'+data[n].NOQGPAVAIL+'</label></div>'
					+'<div class="col-md-4 col-sm-4"><input onchange = "com.coep.test.testConfig.checkVal(this.value,'+data[n].NOQGPAVAIL+');" type="text" id="'+n+'" class="qgroup-input" title="'+data[n].CONFID+'" value='+data[n].NOQUESGRP+'><span class="icontxt-edit icon-text-edit"></span></div>'
				}
			

				+'<div>'
				+'<div>'
				
			}
		
			$("#main-div").html(renderTestConfigHtm);
			
			TC.checkVal = function(qgp , total) {
				if(qgp > total){
					$("#updateTest").prop('disabled', true);
					showToast.show('Please check total question', false);
				}else{
					$("#updateTest").prop('disabled', false);
				}
			}
			
			TC.getDataBeforeConfirmUpdation = function() {
				var confIdJSONArr = [];
				for(var n=1; n<data.length; n++){
					var  confId= $("#"+n).attr('title');
					var confValue = $("#"+n).val();
				 
//					console.log(confId +" = "+ confValue);
					var confIdValJSON = {
						"testConfigId" : confId,
						"noOfQuestionGroup" : confValue
					};
					
					confIdJSONArr.push(confIdValJSON);
				};
			console.log(confIdJSONArr);
			
			AH.updateTestConfig(AP.baseURL + "getTestConfig", "PUT", confIdJSONArr);
			
			
			};

			TC.confirmationToModifyTest = function() {
				var ms = '';
				ms = 'Do you want to update the test configuration?';
				var htmlText = '';
				htmlText += '';
				htmlText += '' + ms + '';
				htmlText += '';

				$('#cModalLabel').html('Confirmation');
				$('#cdlgBody').html(htmlText);
				$('#cdlgFoot').html(AP.getYesNoButtonOnDialog());

					$('#cdialog').modal({
						show : true
					});
					$('#cfYes').bind('click', function() {
						TC.getDataBeforeConfirmUpdation();
						AP.clearConfirmDialogContent();
						TC.renderShowTestClick();
					});

					$('#cfNo').bind('click', function() {
						TC.renderShowTestClick();
						AP.clearConfirmDialogContent();
					});
			};
	}
	
	
	TC.renderShowTest = function(data) {
		$(".child-menu-quesbank").hide();
		$(".child-menu-configTest").hide();		
		$("#listofuser-div").hide();
		$("#listofuser-result-div").hide();
		
		var jsonTestDetailsUpdate = {};
		var upFlag = 0;
		
			document.title = "Show Test";
			var renderShowTestHtm = '';
			
			
			
			renderShowTestHtm += '<div><h1 class="text-align-center darkbrown" style="font-weight : bold;">Available Test</h1><table class="table" style="border:2px solid #790000;"><thead><tr>'
				+'<th style="width:66px;">Sr. No. </th>'
				+'<th>Test Name</th><th>Department</th>'
				+'<th>Start Date</th><th>End Date</th>'
				+'<th style="width:95px;">Active</th>'
//				+'<th>Selected Sections</th>'
				+'<th style="width:115px;">Modify</th>'
				+'<th style="width:115px;">Configure Test</th></tr></thead><tbody>';
			for (var k = 0; k < data.length; k++){
				
//				var optionForModifyHtm = '';
//				var optionForSaveHtm = '';
				
				var status = data[k].ISACT;
					renderShowTestHtm += '<tr><td>'+(k+1)+'.</td><td>'+data[k].TN+'</td><td>'
								  +data[k].DEPT+'</td><td><input disabled style="border:none;" type="text" title="Click here to change date" id="startDate'+k+'" class="datepickerSD'+k+'" value="'
								  +data[k].SD+'"></td><td><input disabled style="border:none;" type="text" title="Click here to change date"  id="endDate'+k+'" class="datepickerED'+k+'" value="'
								  +data[k].ED+'"></td><td>'
								  if(status == 1){
									  renderShowTestHtm +='<input class="activeState'+k+'" type="checkbox" checked disabled>'								  
								  }else {
									  renderShowTestHtm +='<input class="activeState'+k+'" type="checkbox" disabled>'			
								  }
					
					renderShowTestHtm +='</td><td><span  id="optionToModify'+k+'" onClick="modifyTestDetails('+k+')" class="icon-edit-group prim-blue" title="Modify Test" style="cursor:pointer;"></span>'
										+'<span id="optionToSave'+k+'" onClick="updateTestDetails('+k+','+data[k].ID+')" class="green-color" title="Update Test" style="cursor:pointer;" hidden>Save</span>'
										+'&emsp;<span id="cancelUpdate'+k+'" onClick="cancelUpdation('+k+')" title="Cancel"hidden><img src="resource/images/icons/cancel.png" style="width:20px; cursor:pointer;"></span></td>';
					renderShowTestHtm +='<td><span  id="optionToShowConfig'+k+'" onClick="modifyTestConfig('+k+','+data[k].ID+')" class="icon-edit-group prim-blue" title="Show configuration" style="cursor:pointer;"></span></td>'
										+'</tr><tr><td colspan="8" style="border-bottom:2px solid #900000; padding:0 0 0 70px; "><b><span class="darkbrown selectsection" >Selected Sections : </span></b><br/><span class="15pxfont">&emsp;'+data[k].SN+'</span></td></tr>';

					modifyTestConfig = function(k, id) {
//						alert("K" + k +" and id=" + id );
//						
						AH.getTestConfig(AP.baseURL + "getTestConfig", "GET", id);
					}
					
					cancelUpdation = function (k) {
//						console.log(upFlag);
						upFlag = 0;		
						$(".datepickerSD"+k).prop("disabled", true);
						$(".datepickerED"+k).prop("disabled", true);
						$(".activeState"+k).prop("disabled", true);
						$("#optionToSave"+k).hide();
						$("#optionToModify"+k).show();
						$("#cancelUpdate"+k).hide();
						AH.getAllTests(AP.baseURL
								+ "getAllTest/", "GET");
					}
			
					modifyTestDetails = function(k) {
//						console.log(upFlag);
						if(upFlag == 0){
						upFlag = 1;
						var d = data[k].SD;
					    $(".datepickerSD"+k).datetimepicker({  
					        showSecond: false,
					        dateFormat: 'yy-mm-dd',
					        timeFormat: 'hh:mm tt',
					        ampm: true,
					    }).datetimepicker("setDate", d);
					    
					    
					    var d1 = data[k].ED;
					    $(".datepickerED"+k).datetimepicker({  
					        showSecond: false,
					        dateFormat: 'yy-mm-dd',
					        timeFormat: 'hh:mm tt',
					        ampm: true,
					    }).datetimepicker("setDate", d1);	
						
					    
						$(".datepickerSD"+k).prop("disabled", false);
						$(".datepickerED"+k).prop("disabled", false);
						$(".activeState"+k).prop("disabled", false);
						$("#optionToModify"+k).hide();
						$("#cancelUpdate"+k).show();
						$("#optionToSave"+k).show();
						}else{
							showToast.show("Please save current unsaved test.", false);
						}
					}
					
					updateTestDetails = function(k, id) {
//						console.log(upFlag);
						upFlag = 0;
						var startDate = new Date($("#startDate"+k).val()).getTime();
						var endDate = new Date($("#endDate"+k).val()).getTime();
						var checkStatus = '';
						console.log(" ST " + startDate + " ED " + endDate);
						if($(".activeState"+k).prop('checked')){
					        checkStatus = 1;
					    }else{
					    	checkStatus = 0;
					    }
						
						jsonTestDetailsUpdate.testId = id;
						jsonTestDetailsUpdate.startDate = startDate;
						jsonTestDetailsUpdate.endDate = endDate;
						jsonTestDetailsUpdate.active = checkStatus;
						
						$(".datepickerSD"+k).prop("disabled", true);
						$(".datepickerED"+k).prop("disabled", true);
						$(".activeState"+k).prop("disabled", true);
						$("#optionToSave"+k).hide();
						$("#cancelUpdate"+k).hide();
						$("#optionToModify"+k).show();						
						console.log(jsonTestDetailsUpdate);
						
						AH.updateAvailTestDetails(AP.baseURL + "getAllTest", "PUT", jsonTestDetailsUpdate);
					}
			}
						
			renderShowTestHtm+= '</tbody></table></div>';
			
			$("#main-div").html(renderShowTestHtm);
		};

})(com.coep.test.ajaxHandler, com.coep.test.addProblem,
		com.coep.test.testConfig);