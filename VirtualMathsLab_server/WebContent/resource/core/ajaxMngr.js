(function(AH, AP, QB, TC, UP, UF, AM, PD, ST, PG, CW) {

	AH.getProfileDetails = function(url, methodType){
		$.ajax({
			type : methodType,
			url : url,
//			data : "role=" + role,
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				AH.animateAjaxLoaderStop();
				if (data.length != 0) {
					UP.renderUserProfileDetails(data);
				}
//				UP.fetchUserProfile();
			},
			error : function() {
			}
		});
	}
	
	AH.updateUserProfileData = function(url, methodType, userProfileJson){
		$.ajax({

			type : methodType,
			url : url,
			data : JSON.stringify(userProfileJson),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == false) {
//					UP.renderRegFailHtm(data);
				} else {
					toastr.success(data.msg);
					UP.fetchUserProfile();
					 $('#UserProfileDisplay').show();
						$('#UserProfileEdit').hide();
				}
			},
			error : function() {
			}

		});
	}
	
	AH.getAllUsersExceptStudent = function(url, methodType){

		$.ajax({

			type : methodType,
			url : url,
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.length != 0) {
					UP.renderUsers(data);
				}
			},
			error : function() {
			}
		});
	
	}
	
	AH.manageRoleForUser = function(url, methodType, manageRoleJson){
		$.ajax({
			type : methodType,
			url : url,
			data : JSON.stringify(manageRoleJson),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == false) {
				} else {
					$('#approveRole').modal('hide');				
					toastr.success(data.msg);
					
				}
				UP.manageRoleOfUsers();
			},
			error : function() {
			}
		});
	}
	
	AH.getAllRequestForContributor = function(url, methodType){

		$.ajax({

			type : methodType,
			url : url,
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
					UP.renderContributorRequest(data);
			},
			error : function() {
			}
		});
	}

	AH.approvedContribRequest = function(url, methodType, userId){
		$.ajax({
			type : methodType,
			url : url,
			data : "userId=" + userId,
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
//				AH.animateAjaxLoaderStop();
				if (data.length != 0) {
//					UP.renderUserProfileDetails(data);
					toastr.success(data.msg);
					
				}
				UP.checkRequestForContributorRole();
			},
			error : function() {
			}
		});
	}
	
	AH.getAllPendingAccountActivation = function(url,methodType){

		$.ajax({

			type : methodType,
			url : url,
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
					UP.renderActivationAccountUsers(data);
			},
			error : function() {
			}
		});
	}
	
	AH.getUserListToWriteInExcel = function(url, methodType){

		$.ajax({

			type : methodType,
			url : url,
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
					UF.renderUserListForDownload(data);
			},
			error : function() {
			}
		});
	}

	AH.activateAccount = function(url, methodType, userId){
		$.ajax({
			type : methodType,
			url : url,
			data : "userId=" + userId,
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.length != 0) {
					toastr.success(data.msg);
				}
				UP.activateAccountByAdmin();
			},
			error : function() {
			}
		});
	}
	
	AH.getUserDetails = function(url, methodType) {
		$.ajax({

			type : methodType,
			url : url,
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.length != 0) {
					console.log(data);
					UP.renderUserProfile(data);
				}
			},
			error : function() {
			}

		});
	}
	
	AH.userTest = function(url, methodType) {

		$.ajax({

			type : methodType,
			url : url,
			success : function(data) {
				if (data.length != 0) {
					UP.renderUserTest();
				}
			},
			error : function() {
			}

		});

	};

	AH.userProfileRegistration = function(url, methodType, reUserJson) {

		$.ajax({

			type : methodType,
			url : url,
			data : JSON.stringify(reUserJson),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if(data.done == true){
					UP.renderRegSuccessHtm(data.msg)
					$("#regsuccessMessModel").modal('show');
					
				}else{
					UP.renderRegwarningHtm(data.msg)
					$("#regMessWarningModel").modal('show');
				}
				
				setTimeout(() => {
				window.location.href = "login"; }, 5000);
					
//					$("#studRegisForm")[0].reset();
//				     $("#teacherRegisForm")[0].reset();
//					$("#parentRegisForm")[0].reset();
//					$("#contriRegisForm")[0].reset();
//					$("#studRegisForm").removeClass("was-validated");
//				     $("#teacherRegisForm").removeClass("was-validated");
//					$("#parentRegisForm").removeClass("was-validated");
//					$("#contriRegisForm").removeClass("was-validated");
					 
			},
			error : function() {
			}

		});

	}

	AH.getAllDepartments = function(url, methodType) {
		$.ajax({
			type : methodType,
			url : url,
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == false) {

				} else {
					if (data.length != 0) {
						AP.renderDepartments(data.data);
					} else {
						showToast.show(
								'This department does not have any section.',
								false);
					}
				}
			},
			error : function() {

			}

		});
	}

	
AH.getStates = function(url, methodType, countryid){
		
		$.ajax({

			type : methodType,
			url : url,
			data : "country_id=" + countryid,
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == true) {
					
					UP.renderSelectedState(data);
					
				}
			},
			error : function() {
			}

		});
		
	}
	
	
AH.getCities = function(url, methodType, stateid){
		
		$.ajax({

			type : methodType,
			url : url,
			data : "state_id=" + stateid,
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == true) {
					UP.renderForSelectedState(data);
					
				}
			},
			error : function() {
			}

		});
		
	}

	AH.getComplexityLevel = function(url, methodType) {
		$.ajax({
			type : methodType,
			url : url,
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == false) {
					// AP.showCustomAlert("Error", data.msg);
				} else {
					if (data.length != 0) {
						AP.renderComplexityLevel(data.data);
					} else {
						showToast.show(
								'This department does not have any section.',
								false);
					}
				}
			},
			error : function() {
			}

		});
	}

	AH.getSectionsOfSelectedTopic = function(url, methodType,
			questionGruopJSON, flag) {
		$
				.ajax({
					type : methodType,
					url : url,
					dataType : 'json',
					contentType : 'application/json',
					success : function(data) {
						if (data.done == false) {
							// AP.showCustomAlert("Error", data.msg);
						} else {
							if (data.length != 0) {
								// AP.renderSectionsToAssignProblem(data.data);
								AP.renderSectionsToDefineProblem(data.data,
										questionGruopJSON, flag);
							} else {

								showToast
										.show(
												'This department does not have any section assigned.',
												false);
							}
						}
					},
					error : function() {
					}

				});
	}
	AH.getSectionsOfSelectedTopicForQuestionBank = function(url, methodType) {
		$
				.ajax({
					type : methodType,
					url : url,
					dataType : 'json',
					contentType : 'application/json',
					success : function(data) {
						if (data.done == false) {
						} else {
							if (data.length != 0) {
								QB.renderSectionsToDefineProblem(data.data);
							} else {

								showToast
										.show(
												'This department does not have any section assigned.',
												false);
							}
						}
					},
					error : function() {
					}

				});
	}
	//Modified Question Group
	AH.moveToQuestionBank = function(url, methodType, questionGruopJSON, flag) {
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
						//						console.log(data);
						showToast.show(data.msg, true);
						flag = 1;
						AP.modifyQuestionGroup(data.QGID, questionGruopJSON,
								flag);
					} else {
						// AP.showCustomAlert("Error", "");
					}
				}
			},
			error : function() {
			}

		});
	}

	// fetch active question default
	AH.fetchQuestionBank = function(url, methodType, status) {

		$.ajax({
			type : methodType,
			url : url,
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == false) {

					$("#main-div").html('');
					showToast.show("No Record Found", false);
				} else {
					if (data.length != 0) {
						if (status == "Active") {

							QB.renderActiveQuestionGroup(data.data);
						} else {

							QB.renderarchivedQuestionGroup(data.data);
						}
					} else {
						$("#main-div").html('');

						showToast.show("No Record Found", false);
					}
				}
			},
			error : function() {
			}

		});

	}

	// move question group to archive
	AH.moveQuestionGroupToArchive = function(url, methodType, status) {
		$.ajax({
			type : methodType,
			url : url,
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == false) {

					showToast.show(data.msg, false);
				} else {
					if (data.length != 0) {
						showToast.show(data.msg, true);
						// AP.showCustomAlert("Information", data.msg)
						QB.fetchQuestionBank(status);

					} else {
						showToast.show(data.msg, false);
					}
				}
			},
			error : function() {
			}

		});
	}

	// refine Search On Question Group Bank
	AH.refineSearchOnQuestionBank = function(url, methodType, selectedValues) {
		$.ajax({
			type : methodType,
			url : url,
			dataType : 'json',
			data : "refineJson=" + JSON.stringify(selectedValues),
			contentType : 'application/json',
			success : function(data) {
				if (data.done == false) {
					showToast.show(data.msg, false);
				} else {
					if (data.length != 0) {

						if (selectedValues.status == false) {
							QB.clearConfirmDialogContent();
							QB.renderActiveQuestionGroup(data.data);
						} else {
							QB.clearConfirmDialogContent();
							QB.renderarchivedQuestionGroup(data.data);
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

	AH.moveToQuesBank = function(url, methodType, defineProb) {
		$.ajax({
			type : methodType,
			url : url,
			data : JSON.stringify(defineProb),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == false) {

					showToast.show(data.msg, false);
				} else {
					if (data.length != 0) {

						showToast.show(data.msg, true);

					} else {
						showToast.show(data.msg, false);
					}
				}
			},
			error : function() {
			}

		});
	};

	// upload media while add question group
	AH.excelFileUplaod = function(url, methodType, formData, flag) {

		var id;

		$.ajax({
			url : url,
			type : methodType,
			data : formData,
			enctype : "multipart/form-data",
			processData : false,
			contentType : false,
			dataType : 'json',
		}).done(function(data) {
			AP.mediaId = data.mediaId;
			AP.mediaContent = data.content;
			if (flag == 1) {
				AP.saveQues('id', data.mediaId, data.content, data.URL);
			} else {
				AP.modifiedQuestionGrp(data.mediaId, data.content, data.URL);
			}

		}).fail(function(jqXHR, textStatus) {
			showToast.show('File upload failed ...', false);
		});

		return id;
	}

	// upload Question Using excel sheet
	AH.uploadQuestionUsingExcel = function(url, methodType, formData) {

		$.ajax({
			url : url,
			type : methodType,
			data : formData,
			enctype : "multipart/form-data",
			processData : false,
			contentType : false,
			dataType : 'json',
		}).done(function(data) {

			QB.renderUploadQuestion();

		}).fail(function(jqXHR, textStatus) {
			// alert('File upload failed ...');
			showToast.show('File upload failed ...', false);

			$("div.failure").css('display', 'none');
			$("div.failure").html("File upload failed ...");
			$("div.failure").fadeIn(300).delay(2000).fadeOut(1000);
		});
	}

	AH.removeQuestionFormQuesGrp = function(url, methodType, questionGruopJSON,
			flag) {
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
						flag = 1;
						showToast.show('Question removed from question group',
								true);
						AP.modifyQuestionGroup(data.QGID, questionGruopJSON,
								flag);
					} else {
					}
				}
			},
			error : function() {
			}

		});
	}

	AH.getQuestionGroupForModify = function(url, methodType, QGID) {

		$.ajax({
			type : methodType,
			url : url,
			data : "QGID=" + QGID,
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == false) {
					showToast.show(data.msg, false);
				} else {
					if (data.done == true) {

						AP.mediaId = data.data.media.mediaId;
						AP.mediaContent = data.data.media.name;
						var flag = 1;
						AP.modifyQuestionGroup(QGID, data.data, flag);

					} else {
						showToast.show('', false);
					}
				}
			},
			error : function() {
			}

		});
	}

	// ajax loader

		AH.ajaxindicatorstart = function(text) {
	
			if (jQuery('body').find('#resultLoading').attr('id') != 'resultLoading') {
				jQuery('body')
						.append(
								'<div id="resultLoading" style="display:none"><div><img src="resource/images/ajax-loader.gif"><div>'
										+ text
										+ '</div></div><div class="bg"></div></div>');
			}
	
			jQuery('#resultLoading').css({
				'width' : '100%',
				'height' : '100%',
				'position' : 'fixed',
				'z-index' : '10000000',
				'top' : '0',
				'left' : '0',
				'right' : '0',
				'bottom' : '0',
				'margin' : 'auto'
			});
	
			jQuery('#resultLoading .bg').css({
				'background' : '#000000',
				'opacity' : '0.7',
				'width' : '100%',
				'height' : '100%',
				'position' : 'absolute',
				'top' : '0'
			});
	
			jQuery('#resultLoading>div:first').css({
				'width' : '250px',
				'height' : '75px',
				'text-align' : 'center',
				'position' : 'fixed',
				'top' : '0',
				'left' : '0',
				'right' : '0',
				'bottom' : '0',
				'margin' : 'auto',
				'font-size' : '16px',
				'z-index' : '10',
				'color' : '#ffffff'
	
			});
	
			jQuery('#resultLoading .bg').height('100%');
			jQuery('#resultLoading').fadeIn(300);
			jQuery('body').css('cursor', 'wait');
		}
		function ajaxindicatorstop() {
			jQuery('#resultLoading .bg').height('100%');
			jQuery('#resultLoading').fadeOut(300);
			jQuery('body').css('cursor', 'default');
		}
		AH.animateAjaxLoaderStart = function() {
			AH.ajaxindicatorstart('loading data.. please wait..');
			
			
	//		setTimeout(AH.animateAjaxLoaderStop('loading data.. please wait..'), 30000);
			// $('#ajax-loader').show();
		}
	
		AH.animateAjaxLoaderStop = function() {
			AH.ajaxindicatorstop();
	
		};
		AH.ajaxindicatorstop = function() {
			jQuery('#resultLoading .bg').height('100%');
			jQuery('#resultLoading').fadeOut(300);
			jQuery('body').css('cursor', 'default');
		}
		
		
		$.ajaxSetup({
	
					// your ajax code
					beforeSend : function() {
						//AH.animateAjaxLoaderStart('loading data.. please wait..');
					},
					complete : function() {
						AH.animateAjaxLoaderStop();
					},
					statusCode : {
	
						307 : function() {
							// Temporary Redirected
							AH
									.showCustomAlert(
											"You are not authorised on server, Login required",
											"");
						},
						302 : function() {
							// Temporary Redirected
							AH.showCustomAlert("",
									"You were inactive for long time. Login again");
						},
	
						401 : function() {
							AH
									.showCustomAlert("",
											"You are logged out on server 401. Please login again.");
	
							window.setTimeout(1000, function() {
								window.location.href = EF.context + '/auth/login';
							});
						}
					}
				});
//	 test configuration
	
	
	AH.getSectionsOfSelectedDept = function(url, methodType, selectedDept) {

		$.ajax({
			type : methodType,
			url : url,
			data : selectedDept,
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {

				if (data.done == false) {
					showToast.show(data.msg, false);
				} else {
					if (data.length != 0) {
						// console.log(data);
						TC.renderTestConfigRes(data.data);
					} else {
						showToast.show(
								'This department does not have any section.',
								false);
					}
				}
			},
			error : function() {
			}

		});
	}

	AH.getDiffLevelQuestionCountBySectionId = function(url, methodType,
			section, sectionId, i, selectedDept, testId, flag) {
		// alert("in ajax " +section);
		$.ajax({
			type : methodType,
			url : url,
			data : "sectionId=" + sectionId,
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (flag == 0) {
					TC.renderSetTestPaperRes(data, i, section, sectionId,
							selectedDept, testId);
				} else {
					TC.renderSavedTestExpand(data, i, section, sectionId,
							selectedDept, testId);
				}
			},

			error : function() {
			}

		});
	}

	AH.getQCountSavedDataBySecIdTstId = function(url, methodType, section,
			sectionId, i, selectedDept, testId, flag) {
		$.ajax({
			type : methodType,
			url : url,
			data : "sectionId=" + sectionId + "&testId =" + testId,
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (flag == 0) {

					if (data.savedQues.length != 0)
						TC.renderSetTestPaperRes(data.data, i, section,
								sectionId, selectedDept, testId);
				} else {
					TC.renderSavedTestExpand(data.data, i, section, sectionId,
							selectedDept, testId);
				}
			},

			error : function() {
			}

		});
	}

	AH.configureTest = function(url, methodType, configureTestJSON, sectionObj,
			deptId) {

		$.ajax({
			type : methodType,
			url : url,
			data : JSON.stringify(configureTestJSON),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {

				if (data.done == true) {
					$("#ConfigTestPapLink").attr({
						"href" : "#",
						"title" : "Can not configure test again"
					});
					$("#ConfigTestPapLink").removeClass("active");
					TC.renderSetTestPaper(data, configureTestJSON, sectionObj,
							deptId);
				} else {
				}

			},
			error : function() {
			}

		});

	};

	AH.saveTestPaperForSection = function(url, methodType, TestPaperJSONArray,
			data, deptId, sectionObj) {
		$.ajax({
			type : methodType,
			url : url,
			data : JSON.stringify(TestPaperJSONArray),
			dataType : 'json',
			contentType : 'application/json',
			success : function(savedTCData) {

				if (savedTCData.done == true) {
					TC.renderSavedSetTestPaper(data, TestPaperJSONArray,
							savedTCData, deptId, sectionObj);
				} else {

				}
			},
			error : function() {
			}

		});

	};

	AH.testPreviewClick = function(url, methodType, data1, sectionObj, deptId) {

		var sectionId = Object.keys(sectionObj)[0];
		var testId = data1.TID;
		$.ajax({
			type : methodType,
			url : url,
			data : "sectionId=" + sectionId + "&testId=" + testId,
			dataType : 'json',
			contentType : 'application/json',
			success : function(previewData) {
				TC.renderTestPreview(previewData, sectionObj, data1, sectionId,
						deptId);
			},

			error : function() {
			}

		});
	};
	// end ajax loader

	AH.testPreviewOnSectionClick = function(url, methodType, sectionId, data1,
			sectionObj, deptId) {

		var testId = data1.TID;
		$.ajax({
			type : methodType,
			url : url,
			data : "sectionId=" + sectionId + "&testId=" + testId,
			dataType : 'json',
			contentType : 'application/json',
			success : function(previewData) {
				TC.renderTestPreview(previewData, sectionObj, data1, sectionId,
						deptId);
			},

			error : function() {
			}

		});
	};

	AH.getQuestionsForTest = function(url, methodType) {
		$.ajax({
			type : methodType,
			url : url,
			dataType : 'json',
			contentType : 'application/json',
			success : function(previewData) {

			},

			error : function() {
			}

		});
	}

//	AH.chatMediaFileUplaod = function(url, methodType, formData, userGroupId) {
//
//		var id;
//
//		$.ajax({
//			url : url,
//			type : methodType,
//			data : formData,
//			enctype : "multipart/form-data",
//			processData : false,
//			contentType : false,
//			dataType : 'json',
//		}).done(function(data) {
//			
//			if (data.done == false) {
//				showToast.show('File upload failed ...', false);
//			}else{
//				
//				CW.saveMediaMessage(userGroupId, data.FILENAME, data.URL)
//				
//			}
//			
//			
//		}).fail(function(jqXHR, textStatus) {
//			showToast.show('File upload failed ...', false);
//		});
//
//		return id;
//	}
	
	


	AH.saveProjectDetails = function(url, methodType, projectDetailsJson) {
		$.ajax({
			type : methodType,
			url : url,
			data : JSON.stringify(projectDetailsJson),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == false) {
					showToast.show(data.msg, false);
				} else {

					showToast.show(data.msg, true);
					$("#proTitle").val('');
					$("#proDesc").val('');
					$("#proDeliv").val('');
					AH.getAllProjectListForAdmin(url, "GET");

				}
			},
			error : function() {
			}

		});
	}

	AH.getAllProjectListForAdmin = function(url, methodType) {
		$.ajax({
			type : methodType,
			url : url,
			//			data : JSON.stringify(projectDetailsJson),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == false) {
					showToast.show(data.msg, false);
				} else {

					com.coep.test.project.renderAllProjectList(data);

				}
			},
			error : function() {
			}

		});
	}

	AH.modifyProjectByProjectId = function(url, methodType, modifiedProjJSON) {
		$.ajax({
			type : methodType,
			url : url,
			data : JSON.stringify(modifiedProjJSON),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == false) {
					showToast.show(data.msg, false);
				} else {

					$("#proTitle").val('');
					$("#proDesc").val('');
					$("#proDeliv").val('');

					$("#updateProjectBtn").hide();
					$("#saveToProjectListBtn").show();
					showToast.show(data.msg, true);
					AH.getAllProjectListForAdmin(url, "GET");
				}
			},
			error : function() {
			}

		});
	}

	AH.getAllProjectListForStudent = function(url, methodType) {
		$.ajax({
			type : methodType,
			url : url,
			//			data : JSON.stringify(projectDetailsJson),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == false) {
					showToast.show(data.msg, false);
				} else {

					com.coep.test.project.renderAllProjectListToStudent(data);

				}
			},
			error : function() {
			}

		});
	}
	
	AH.getAllProjectDecriptionForStudent = function(url, methodType) {
		$.ajax({
			type : methodType,
			url : url,
			//			data : JSON.stringify(projectDetailsJson),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == false) {
					showToast.show(data.msg, false);
				} else {

					com.coep.test.project.renderAllProjectDecriptionToStudent(data);

				}
			},
			error : function() {
			}

		});
	}

	AH.saveUsersProjectPreferences = function(url, methodType,
			userPreferencesJSON) {
		$.ajax({
			type : methodType,
			url : url,
			data : JSON.stringify(userPreferencesJSON),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == false) {
					showToast.show(data.msg, false);
				} else {
					showToast.show(data.msg, true);

					com.coep.test.project.dataOfUserPreference(data);
					
//					window.location.reload(true);
					//					com.coep.test.project.renderAllProjectListToStudent(data);
//					ST.clearConfirmDialogContent();

				}
			},
			error : function() {
			}

		});
	}

	AH.updateUsersProjectPreferences = function(url, methodType,
			updateUserPreferencesJSON) {
		$.ajax({
			type : methodType,
			url : url,
			data : JSON.stringify(updateUserPreferencesJSON),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == false) {
					showToast.show(data.msg, false);
				} else {
					showToast.show(data.msg, true);

					//					com.coep.test.project.dataOfUserPreference(data)
					//					com.coep.test.project.renderAllProjectListToStudent(data);
					//					ST.clearConfirmDialogContent();

				}
			},
			error : function() {
			}

		});
	}

	AH.getUserProjectPreferences = function(url, methodType) {
		$.ajax({
			type : methodType,
			url : url,
			//			data : JSON.stringify(updateUserPreferencesJSON),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == false) {
					//					showToast.show(data.msg, false);
				} else {

					//					showToast.show(data.msg, true);
					com.coep.test.project.getUserPreference(data);
					//					com.coep.test.project.renderAllProjectListToStudent(data);
					//					ST.clearConfirmDialogContent();

				}
			},
			error : function() {
			}

		});
	}

	AH.getAllProjectList = function(url, methodType) {
		$.ajax({
			type : methodType,
			url : url,
			//			data : JSON.stringify(projectDetailsJson),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == false) {
					showToast.show(data.msg, false);
				} else {

					com.coep.test.projectGroup.renderAllProjectList(data);

				}
			},
			error : function() {
			}

		});
	}

	AH.getUsersProjectPrefForSelectedProject = function(url, methodType) {
		$.ajax({
			type : methodType,
			url : url,
			//				data : JSON.stringify(projectDetailsJson),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == false) {
					showToast.show(data.msg, false);
					$("#userLists").hide();
					$("#userListsDetails").hide();
				} else {
					
					com.coep.test.projectGroup
							.renderUsersListForSelectedPref(data);

				}
			},
			error : function() {
			}

		});
	}

	AH.deleteMsgFromChat = function(url, methodType, msgId) {
		$.ajax({
			type : methodType,
			url : url,
			//				data : JSON.stringify(projectDetailsJson),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == false) {
					showToast.show(data.msg, false);
					$("#userLists").hide();
				} else {
					
					showToast.show(data.msg, true);
					com.coep.test.chat.removeDeletedMsg(msgId);

				}
			},
			error : function() {
			}

		});
	}
	
	
	
	AH.getAllStudentsListToCreateGroup  = function(url, methodType) {
		$.ajax({
			type : methodType,
			url : url,
			//				data : JSON.stringify(projectDetailsJson),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == false) {
					showToast.show(data.msg, false);
//					$("#userLists").hide();
				} else {
					com.coep.test.projectGroup.renderAllUsersList(data);
//					showToast.show(data.msg, true);
//					com.coep.test.chat.removeDeletedMsg(msgId);

				}
			},
			error : function() {
			}

		});
	}
	
	
	AH.createUserGroup = function(url, methodType,createGroupJSON) {
		$.ajax({
			type : methodType,
			url : url,
			data : JSON.stringify(createGroupJSON),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == false) {
					showToast.show(data.msg, false);
//					$("#userLists").hide();
				} else {
//					com.coep.test.projectGroup.renderAllUsersList(data);
					showToast.show(data.msg, true);
					com.coep.test.projectGroup.getStudentsListWithoutAlocatedGruop();
					$("#grpTitle").val('');
					$("#projectTitle").val("-1");
					$('#selUsers').tagit('removeAll');
					
				}
			},
			error : function() {
			}

		});
	}
	
	
		AH.getUserChatGroupList = function(url, methodType){
		
		$.ajax({
			
			type : methodType,
			url : url,
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.length != 0) {
					
					com.coep.test.userChatGroup.renderUserChatGroupList(data);
					
				} else {

					showToast.show(data.msg, false);

				}
			},
			error : function() {
			}
			
		});		
		
	}
		
   
		AH.getUserChatGroupListtoGetGroupMembers = function(url, methodType){
			   
			   $.ajax({
			    
			    type : methodType,
			    url : url,
			    dataType : 'json',
			    contentType : 'application/json',
			    success : function(data) {
			     if (data.length != 0) {
			      
			      com.coep.test.userChatGroup.renderUserChatGroupListtoGetGroupMembers(data);
			      
			     } else {

			      showToast.show(data.msg, false);

			     }
			    },
			    error : function() {
			    }
			    
			   });  
			   
			  }
		
	
	AH.getAllProjectGroupsList = function(url, methodType) {
		$.ajax({
			type : methodType,
			url : url,
			//	data : JSON.stringify(projectDetailsJson),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == false) {
					showToast.show(data.msg, false);
					$("#main-div").html('');
				} else {
					com.coep.test.projectGroup.allProjectGroupsList(data);
				}
			},
			error : function() {
			}

		});
	}
	
	
	
	
	AH.getAllLinkGeneratedStudentListfromTestInstanceState = function(url, methodType){
		
		$.ajax({
			type : methodType,
			url : url,
			//	data : JSON.stringify(projectDetailsJson),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == false) {
					showToast.show(data.msg, false);
					$("#main-div").html('');
				} else {
					com.coep.test.modifyStudentTest.allStudentList(data);
				}
			},
			error : function() {
			}

		});
	}
	
	
	AH.getAllLinkGeneratedStudentListfromTestInstanceStateToShowResult = function(url, methodType){
		
		$.ajax({
			type : methodType,
			url : url,
			//	data : JSON.stringify(projectDetailsJson),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == false) {
					showToast.show(data.msg, false);
					$("#main-div").html('');
				} else {
					com.coep.test.showStudentResult.allStudentListToShowResult(data);
				}
			},
			error : function() {
			}

		});
	}
	
	
	
	
	AH.getLeveltoShowResult = function(url, methodType, userId) {
		$.ajax({
			url : url,
			type : methodType,				
			data : "userId=" + userId,
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if(data.done != false){
				com.coep.test.showStudentResult.renderLevel(data, userId);
				}
				else{
					showToast.show(
							'TEST IS NOT COMPLETED YET FOR ANY ROUND',
							false);
//					com.coep.test.showStudentResult.renderLevel(data, userId);
					
				}
				
			},
			error : function() {
			}

		});
	}
	
	
	
	AH.getSectionFromTestId = function(url, methodType, userId, testId){
		
		$.ajax({
			url : url,
			type : methodType,				
			data : "testId=" + testId+"&userId="+ userId,
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
//				if(data.done != false){
					com.coep.test.showStudentResult.showResults(data);
//				}else{
//					showToast.show(data.msg, false);
//					com.coep.test.showStudentResult.showResults(data);
//				}
			},
			error : function() {
			}

		});
		
	}
	
	AH.getuserTestInstanceDetails = function(url, methoType, userId){
		$.ajax({
			type : methoType,
			url : url,		
			data : "userId=" + userId,
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				com.coep.test.modifyStudentTest.renderAllDetails(data, userId);
				
			},
			error : function() {
			}

		});
		
	}
	
	
	
	AH.updateTestInstanceStateDetails = function(url, methoType, jsonTestUpdate, userId){
		
		
		$.ajax({
			type : methoType,
			url : url,			
			data :JSON.stringify(jsonTestUpdate),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if(data.done == true){
					
				showToast.show(data.msg,true);
				AH.getuserTestInstanceDetails(com.coep.test.addProblem.baseURL+"api/create/getAll/userTestInfo","GET",userId);
				
				}else{
					
					showToast.show(data.msg,false);
					
				}
				
				
			},
			error : function() {
			}

		});
		
	}
	
	
	
	AH.updateUserGroup = function(url, methodType,createGroupJSON) {
		$.ajax({
			type : methodType,
			url : url,
			data : JSON.stringify(createGroupJSON),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == false) {
					showToast.show(data.msg, false);
				} else {
//					com.coep.test.projectGroup.renderAllUsersList(data);
					showToast.show(data.msg, true);
					com.coep.test.projectGroup.getStudentsListWithoutAlocatedGruop();
					
					$("#pgList").val("-1");
					$("#grpDetails").html('');
					com.coep.test.projectGroup.refreshUserGroupListAfterUpdation();
//					$("#newTitle").val('');
//					$("#projectTitle").val("-1");
//					$('#selUsers').tagit('removeAll');
				}
			},
			error : function() {
			}

		});
	}
	
	
	
	
	 AH.windowBeforeUnload = function(url, methodType){
		  
		  $.ajax({

		   type : methodType,
		   url : url,
		  // data : JSON.stringify(json),
		   dataType : 'json',
		   contentType : 'application/json',
		   success : function(data) {
		    if (data.done == true) {
		    
		     console.log(data);
		     
		    }
		   },
		   error : function() {
		   }

		  });
		  
		  
		  
		 }
		 
		 
		
	AH.getAllTests = function(url, methodType) {
		$.ajax({
			type : methodType,
			url : url,
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == false) {

				} else {
					if (data.length != 0) {
						TC.renderShowTest(data.data);
					} else {
						showToast.show(
								'Test is not available.',
								false);
					}
				}
			},
			error : function() {

			}

		});
	}	 
	
	 AH.updateAvailTestDetails = function(url, methodType, AllTestUpdateDatajson) {

			$.ajax({
				type : methodType,
				url : url,
				data : JSON.stringify(AllTestUpdateDatajson),
				dataType : 'json',
				contentType : 'application/json',
				success : function(data) {
					if (data.done == false) {

					} else {
						if (data.length != 0) {
							showToast.show('Test details updated', true);
							AH.getAllTests(AP.baseURL
									+ "getAllTest/", "GET");
						} else {
							showToast.show(
									'Test updation failed.',
									false);
						}
					}
				},
				error : function() {

				}

			});
		
		}
	 
	 
	 AH.getTestConfig = function(url, methodType, testId) {

			$.ajax({
				type : methodType,
				url : url,
				data : "testId=" + testId,
				dataType : 'json',
				contentType : 'application/json',
				success : function(data) {
					if (data.done == false) {
						showToast.show(data.msg, false);
					} else {
						if (data.length != 0) {
						TC.renderTestConfigDetails(data.data);
					} else {
						showToast.show(
								'Test configuration is not available.',
								false);
					}
					
					}
				},
				error : function() {

				}
			});
		};
	 
	AH.updateTestConfig = function(url, methodType, confIdJSONArr) {
		$.ajax({
			type : methodType,
			url : url,
			data : JSON.stringify(confIdJSONArr),
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if(data.length !=0) {
				showToast.show(
						data.msg,
						true);
				}else{
					showToast.show(data.msg, false);
				}
				
			},
			error : function() {
			}

		});

	}; 
	 
	 AH.getMediaUrlForImage = function(url, methodType, media, questionGroupId){
		
		$.ajax({
			type : methodType,
			url : url,
			data : "mediaID=" + media + "&questionGroupId=" + questionGroupId,
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				console.log(data);
				var mediaimageurl = data.urlpath;
				console.log(mediaimageurl);
				com.coep.test.addProblem.mediaUrlForImage = mediaimageurl;
				
				console.log(com.coep.test.addProblem.mediaUrlForImage);
//				return mediaImageUrl;
			},

			error : function() {
			}

		});
	 
	 }
	 
	 AH.createPdfForSingleUser = function(url, methodType, userId, testLevel){
		 

			$.ajax({
				url : url,
				type : methodType,	
				data : "userId=" + userId + "&testLevel=" + testLevel,
				dataType : 'json',
				contentType : 'application/json',
				success : function(data) {
					console.log(data);
					if(data.done != false){
					showToast.show('Reports Created Succesfully', true);
					com.coep.test.showStudentResult.getGeneratedStudentResult(1, userId, testLevel);
					}else{
						
					showToast.show('Something Went Wrong.. Please, Try Latter', false);
						
					}
				},

				error : function() {
				}

			});
	 }
	 
	 
	 AH.getTestInstanceStateByUserIdAndTestLevel = function(url, methodType, userId, testLevel){
		 

			$.ajax({
				url : url,
				type : methodType,	
				data : "userId=" + userId + "&testLevel=" + testLevel,
				dataType : 'json',
				contentType : 'application/json',
				success : function(data) {
					console.log(data);
					if(data.done != false){
						com.coep.test.showStudentResult.getGeneratedStudentResultToDownload(data);
					}else{
						showToast.show('Something went wrong', false);
					}
				},

				error : function() {
				}

			});
	 }
	 
	 AH.getAllDepartmentList = function(url, methodType) {
		   $.ajax({
		    type : methodType,
		    url : url,
		    dataType : 'json',
		    contentType : 'application/json',
		    success : function(data) {
		     if (data.done == false) {

		     } else {
		      if (data.length != 0) {
		       com.coep.test.modifyStudentTest.showAllDepartmentList(data);
		      } else {
		       showToast.show(
		         'Department List is not avaliable',
		         false);
		      }
		     }
		    },
		    error : function() {

		    }

		   });
		  }
		  
		  AH.addSection = function(url, methodType, secJSON) {
		   $.ajax({
		    type : methodType,
		    url : url,
		    dataType : 'json',
		    data : JSON.stringify(secJSON),
		    contentType : 'application/json',
		    success : function(data) {
		     if (data.done != false) {
		      showToast.show('Section added successfully',true);
		      com.coep.test.modifyStudentTest.getAllDepartment();

		     } else {
		      showToast.show('Section addition failed',false);
		      com.coep.test.modifyStudentTest.getAllDepartment();
		     }
		    },
		    error : function() {

		    }

		   });
		  }
	 
	 
		  AH.updateAllTestInstanceStateDates = function(url, methodType, jsoninstanceUpdate){
			  
			  $.ajax({
				    type : methodType,
				    url : url,
				    dataType : 'json',
				    data : JSON.stringify(jsoninstanceUpdate),
				    contentType : 'application/json',
				    success : function(data) {
				     if (data.done != false) {
				      showToast.show(data.msg,true);

				     } else {
				      showToast.show(data.msg,false);
				    
				     }
				    },
				    error : function() {

				    }

				   });
		  }
		  
		  AH.deleteAllRecords = function(url, methodType){
			  $.ajax({
				    type : methodType,
				    url : url,
				    contentType : 'application/json',
				    success : function(data) {
				     if (data.done != false) {
				    	 
				      showToast.show('All Records Deleted Successfully',true);
				      

				     } else {
				      showToast.show('No Record Found to Delete',false);
				    
				     }
				    },
				    error : function() {

				    }

				   });
			  
		  }
	 
		  AH.dltAllRec = function(url, methodType, userId, testLevel){
			  $.ajax({
				    type : methodType,
				    url : url,
				    data : "userId=" + userId+"&testLevel="+testLevel,
					dataType : 'json',
				    contentType : 'application/json',
				    success : function(data) {
				     if (data.done != false) {
				    	 
				      showToast.show('All Records Deleted Successfully',true);
				      $("#userInstancedetails").html('');
	
				     } else {
				    	 
				      showToast.show('No Record Found to Delete',false);

				     }
				    },
				    error : function() {

				    }
				   });
		  }
		  
		  
		  
		  AH.getAllInstanceRecordsFromUserId = function(url, methodType, userId, testLevel){
			  $.ajax({
				    type : methodType,
				    url : url,
				    data : "userId=" + userId+"&testLevel="+testLevel,
					dataType : 'json',
				    contentType : 'application/json',
				    success : function(data) {
								    	 
				    	 com.coep.test.modifyStudentTest.renderAllInstanceDetails(data, userId);
				  
				    },
				    error : function() {

				    }

				   });
			  
		  }
		  
		  
			AH.getTestLevelFromInstanceFromUserId = function(url, methoType, userId,emailId){
				
				$.ajax({
					type : methoType,
					url : url,		
					data : "userId=" + userId,
					dataType : 'json',
					contentType : 'application/json',
					success : function(data) {
						
						com.coep.test.modifyStudentTest.renderTestLevel(data, userId,emailId);
						
					},
					error : function() {
					}

				});
				
			}
			
			
			
			  AH.moveDataToCompletionForSelectedStudent = function(url, methodType, userId, testLevel){
				  $.ajax({
					    type : methodType,
					    url : url,
					    data : "userId=" + userId+"&testLevel="+testLevel,
						dataType : 'json',
					    contentType : 'application/json',
					    success : function(data) {
					     if (data.done != false) {
					    	 
					      showToast.show(data.msg,true);
		
					     } else {
					    	 
					      showToast.show(data.msg,false);

					     }
					    },
					    error : function() {
					    	
					    }
					   });
			  }
			  
			  
			 
		  
			  AH.deleteStatisticsfromId = function(url, methodType, instanceStatisticsId, userId){
				  $.ajax({
					    type : methodType,
					    url : url,
					    data : "instanceStatisticsId=" + instanceStatisticsId+"&userId="+userId,
						dataType : 'json',
					    contentType : 'application/json',
					    success : function(data) {
					     if (data.done != false) {
					    	 
					      showToast.show('All Records Deleted Successfully',true);
					      $("#main-div").html('');
		
					     } else {
					      showToast.show('No Record Found to Delete',false);

					     }
					    },
					    error : function() {

					    }
					   });
			  }
		 
	 
			  AH.moveRecordFromInstanceToComp = function(url, methodType, emailIds){
				  $.ajax({
					    type : methodType,
					    url : url,
					    contentType : 'application/json',
					    success : function(data) {
					    	
					     var obj = JSON.parse(data);
					     
					     if (obj.done != false) {
					    	 
					      showToast.show(obj.msg,true);
					      alert(obj.msg);

					     } else {
					    	 
					      showToast.show(obj.msg,false);
					      alert(obj.msg);
					     }
					    },
					    error : function() {

					    }

					   });
				  
			  }
		 
			  AH.resetTest = function(url, methodType, userId, testLevel){
				  $.ajax({
					    type : methodType,
					    url : url,
					    data : "userId=" + userId+"&testLevel="+testLevel,
						dataType : 'json',
					    contentType : 'application/json',
					    success : function(data) {
					     if (data.done != false) {
					    	 
					      showToast.show('All Records Deleted Successfully',true);
					      $("#userInstancedetails").html('');
		
					     } else {
					    	 
					      showToast.show('No Record Found to Delete',false);

					     }
					    },
					    error : function() {

					    }
					   });
			  }
			  
			  AH.updateTestInstanceStateToResetTest = function(url, methoType, jsonTestUpdate, userId){
					
					$.ajax({
						type : methoType,
						url : url,			
						data :JSON.stringify(jsonTestUpdate),
						dataType : 'json',
						contentType : 'application/json',
						success : function(data) {
							if(data.done == true){
								
							showToast.show(data.msg,true);
							AH.getuserTestInstanceDetails(com.coep.test.addProblem.baseURL+"api/create/getAll/userTestInfo","GET",userId);
							
							}else{
								
								showToast.show(data.msg,false);
								
							}
							
							
						},
						error : function() {
						}

					});	  
			  }
	 
	 
	 		   
		  AH.getStatisticsData = function(url, methodType) {
			   $.ajax({
			    type : methodType,
			    url : url,
			    contentType : 'application/json',
			    success : function(data) {
//			    	console.log(data);
			    	var data1 = JSON.parse(data);
			     if (data1.done == true) {
			    	 com.coep.test.showStatistics.totalRegisteredStudents(data1);
			     } else {
			      showToast.show(data1.msg,false);
			     }
			    },
			    error : function() {

			    }

			   });
			  }
	 
		  AH.getStatisticsDataForStat = function(url, methodType, testLevel, filterVal) {
			   $.ajax({
			    type : methodType,
			    url : url,
			    data : "testLevel=" + testLevel,
			    contentType : 'application/json',
			    success : function(data) {
			    	var data1 = JSON.parse(data);
			     if (data1.done == true) {
			    	 com.coep.test.showStatistics.totalStudentInPState(data1, filterVal);

			     } else {
			      showToast.show(data1.msg,false);
			     }
			    },
			    error : function() {

			    }

			   });
			  }
		  
		  
		  AH.updateStudInstanceStateId = function(url, methodType, tstInstStateJSON) {
			   $.ajax({
				   type : methodType,
					url : url,
					data : JSON.stringify(tstInstStateJSON),
					dataType : 'json',
					contentType : 'application/json',
				    success : function(data) {
				     if (data.done == true) {
				    	 showToast.show(data.msg,true);

				     } else {
				      showToast.show(data.msg,false);
				     }
				    },
				    error : function() {

				    }

				   });
				  }
		  
		  AH.getStatisticsDataForCompletedTestTimeBound = function(url, methodType, testLevel, filterVal, startDate, endDate) {
			   $.ajax({
				   type : methodType,
					url : url,
					data :  "testLevel=" + testLevel + "&startDate=" + startDate + "&endDate=" + endDate,
					dataType : 'json',
					contentType : 'application/json',
				    success : function(data1) {
				    	console.log(data1);
					     if (data1.done == true) {
					    	 com.coep.test.showStatistics.totalStudentInPState(data1, filterVal);

					     } else {
					      showToast.show(data1.msg,false);
					     }
					    },
				    error : function() {

				    }

				   });
				  }
		  
		  
		  AH.getStatisticsDataForStatForLastHr = function(url, methodType, testLevel, filterVal) {
			   $.ajax({
			    type : methodType,
			    url : url,
			    data : "testLevel=" + testLevel,
			    contentType : 'application/json',
			    success : function(data) {
			    	var data1 = JSON.parse(data);
			     if (data1.done == true) {
			    	 com.coep.test.showStatistics.totalStudentInPState(data1, filterVal);

			     } else {
			      showToast.show(data1.msg,false);
			     }
			    },
			    error : function() {

			    }

			   });
			  }
	 
		  AH.schoolIdMediaFileUplaod = function(url, methodType, formData) {
				$.ajax({
					url : url,
					type : methodType,
					data : formData,
					enctype : "multipart/form-data",
					processData : false,
					contentType : false,
					dataType : 'json',
				}).done(function(data) {
					
					if (data.done == false) {
						showToast.show('File upload failed ...', false);
						
					}else{
						com.coep.test.ajaxHandler.updateUserWithSchoolIdMediaURL(com.coep.test.addProblem.baseURL + "userProfile/updateUser",
								"PUT", data.URL);
						
					}
				})
			}
		  


		AH.updateUserWithSchoolIdMediaURL = function(url, methodType, schoolId) {
		$.ajax({
			type : methodType,
			url : url,
			data : schoolId,
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done != false) {
					showToast.show(data.msg, true);
					UP.showIdProofImage(data.URL);
				} else {
					showToast.show('No Record Found to update', false);
				}
			},
			error : function() {

			}
		});
	}
		
	AH.schoolIdMediaFileCheck = function(url, methodType){
		$.ajax({
			type : methodType,
			url : url,
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.mediaId != "null") {
					$("#schoolIdImg").html('<img src='+com.coep.test.addProblem.baseURL +'/media/userSchoolId/getImage?mediaID='+data.mediaId+'/>');	
					$("#schoolId").val();
					$("#uploadId").hide();
					
				} else {
//					console.log("mediaId : " + data.mediaId); 
				}
				if(data.payment == true){
//					console.log(data);
					if(data.std == 1){
						$("#studyMaterial").html('<center><a href="'+com.coep.test.addProblem.baseURL+'resource/downloads/Group A(VII-VIII)_question_booklet.pdf" download="Study_Material_GroupA.pdf">DOWNLOAD STUDY MATERIAL GROUP A</a></center>');
					}else{
						$("#studyMaterial").html('<center><a href="'+com.coep.test.addProblem.baseURL+'resource/downloads/Group B(IX-X)_question_booklet.pdf" download="Study_Material_GroupB.pdf">DOWNLOAD STUDY MATERIAL GROUP B</a></center>');
					}
					$("#studyMaterial").show();
				}
			},
			error : function() {

			}
		});
	
	}
		
	AH.getAllRegisteredUser = function(url, methodType){
		$.ajax({
			type : methodType,
			url : url,
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				if (data.done == true) {
					com.coep.test.userProfile.fetchAllUserToApproveUser(data);
				} else {
//					console.log("mediaId : " + data.mediaId); 
				}
			},
			error : function() {

			}
		});
	}
	
	AH.unlockAccounts = function(url, tp, userId) {
		$.ajax({
			type : tp,
			url : url,
			data : JSON.stringify(userId),
			dataType : 'json',
			contentType : 'application/json',

			success : function(data) {
				if (data == null) {
					showToast.show(data.msg,false);
				} else {
					AP.unlockAccountSuccess(data);
				}
			},
			error : function() {
				alert("error");
			}
		});
	};
	
	
	AH.getAllSchoolIdCardDetails = function(url, methodType) {
		$.ajax({
			type : methodType,
			url : url,
			dataType : 'json',
			contentType : 'application/json',

			success : function(data) {
				if (data == null) {
					showToast.show(data.msg,false);
				} else {
					UP.renderAllStudentIdCardDetails(data);
				}
			},
			error : function() {
				alert("error");
			}
		});
	};
	
	AH.demoCall = function(url, methodType) {
		$.ajax({
			type : methodType,
			url : url,
			dataType : 'json',
			contentType : 'application/json',

			success : function(data) {
				if (data == null) {
					showToast.show(data.msg,false);
				} else {
//					UP.renderAllStudentIdCardDetails(data);
				}
			},
			error : function() {
				alert("error");
			}
		});
	}; 
	
	AH.deleteArchiveQuestionsFromDB = function(url, methodType) { 
		$.ajax({
			type : methodType,
			url : url,
			dataType : 'json',
			contentType : 'application/json',

			success : function(data) {
				$("#Loading").css("display","none");
				$("#Loading").remove();
				if (data == null) {
					showToast.show(data.msg, false);
				} else {
					$("#Loading").css("display","none");
					$("#Loading").remove();
					showToast.show(data.msg, true);
//					location.reload();
				}
			},
			error : function() {
				$("#Loading").css("display","none");
				$("#Loading").remove();
				alert("error");
			}
		});
	};
	
	AH.updateTopicStatusByTID = function(url, methodType, tid, flag){
		 
		$.ajax({
			type : methodType,
			url : url,
			data : "tid=" + tid + "&flag=" + flag,
			dataType : 'json',
			contentType : 'application/json',

			success : function(data) {
				
				if (data == null) {
					showToast.show(data.msg, false);
				} else {
					showToast.show(data.msg, true);
					$("#Loading").css("display", "none");
//					location.reload();
				}
			},
			error : function() {
				alert("error");
			}
		});
	}
	
	 
})(com.coep.test.ajaxHandler, com.coep.test.addProblem,
		com.coep.test.questionBank, com.coep.test.testConfig,
		com.coep.test.userProfile, com.coep.test.utilityFile, com.coep.test.AlertMessage);