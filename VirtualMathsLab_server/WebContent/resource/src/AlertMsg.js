
(function(AH, AM) {
	
	AM.AlertMesModel = function(AlertMsg , AlertFlag) {
		
		AlertFlag = true;
		AlertMsg = "success";
		var AlertMessModel = ''
			AlertMessModel +=  '<div class="container-fluid">'
			+ '<div class="row">'
			+ '<div  class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'
			//Alert modal start
					+ '<div class="modal" id="AlertMessModel">'
					+ '<div class="modal-dialog">'
					+ '<div class="modal-content">'
		             if(AlertFlag == true)
		            	 {
		            	 AlertMessModel += ' <div class="modal-header" style="background:#4CAF50; color:#fff;">'
							+ '   <h4 class="modal-title">Successfull !!</h4>'
							+ '  <button type="button" class="close" data-dismiss="modal">&times;</button>'
							+ '  </div>'
			
							+ '   <div class="modal-body">'
							+ '<span id="AlertMsgStyle">'+AlertMsg+'</span>'
						    + ' </div>'
		            	 }
		             else
		             {
		            	 AlertMessModel += ' <div class="modal-header" style="background:#ce6058; color:#fff;">'
							+ '   <h4 class="modal-title">Warning !!</h4>'
							+ '  <button type="button" class="close" data-dismiss="modal">&times;</button>'
							+ '  </div>'
			
							+ '   <div class="modal-body">'
							+ '<span id="successMsg">'+AlertMsg+'</span>'
						    + ' </div>'
		            	 }
		AlertMessModel += '    <div class="modal-footer">'
						+ '<button type="button" class="btn btn-success" data-dismiss="modal" id="AlertMsgOK">Ok</button>&nbsp;&nbsp;'
//						+ '<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>'
						+ '</div>'
		
					  + '    </div>'
					  + '  </div>'
					  + '</div>'
			//alert modal end
			+ '</div>' // subject_content close
			+ '</div>'// main row close
			+ '</div>' // container close
			
			$("#main-div").append(AlertMessModel);
		
		
		
	}
	
//AM.AlertMesConfirmModel = function() {
//	
//	var AlertComfirmFlag = false;
//		AlertMsg = " Are you sure, you want to continue ?";
//		var AlertMesConfirm = ''
//			AlertMesConfirm +=  '<div class="container-fluid">'
//			+ '<div class="row">'
//			+ '<div  class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'
//			//Alert modal start
//					+ '<div class="modal" id="AlertMesConfirm">'
//					+ '<div class="modal-dialog">'
//					+ '<div class="modal-content">'
//		            
//		            	 +' <div class="modal-header bg-info" style="color:#fff;">'
//							+ '   <h4 class="modal-title">Confirmation !!</h4>'
//							+ '  <button type="button" class="close" data-dismiss="modal">&times;</button>'
//							+ '  </div>'
//			
//							+ '   <div class="modal-body">'
//							+ '<span id="AlertMsgStyle">'+AlertMsg+'</span>'
//						    + ' </div>'
//		            	 
//		            
//		                +'    <div class="modal-footer">'
//						+ '<button type="button" class="btn btn-success" data-dismiss="modal" id="AlertComfirmYes" onClick="AlertComfirmYes()">Yes</button>&nbsp;&nbsp;'
//						+ '<button type="button" class="btn btn-danger" data-dismiss="modal">No</button>'
//						+ '</div>'
//		
//					  + '    </div>'
//					  + '  </div>'
//					  + '</div>'
//			//alert modal end
//			+ '</div>' // subject_content close
//			+ '</div>'// main row close
//			+ '</div>' // container close
//			
//			$("#main-div").append(AlertMesConfirm);
//		
//			AlertComfirmYes = function()
//			{
//				AlertComfirmFlag = true;
//				console.log(AlertComfirmFlag);
//				return AlertComfirmFlag;
//			}
//		
//		
//	}
	
})(com.coep.test.ajaxHandler, com.coep.test.AlertMessage);
