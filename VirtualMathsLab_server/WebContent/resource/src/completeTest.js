/**
 * 
 */
(function(AH, AP, ST) {
	$("#timeClass").removeClass('tab blink');
	$("#timeClass2").removeClass('tab blink');
	$("#timeClass").addClass('white-color time-counter timer-elem');
	$("#timeClass2").addClass('white-color minutes-rem timer-elem');
	
	AP.completeTest = function(testJson,data,status) {
		
		$("#QuesTime-Div").hide();
		$("#ul-timer").hide();
		$("#ul-timer").html('');
		$("#QuesTime-Div").html('');
		
		
		clearInterval(ST.saveCurrentTime());
		$("#timeClass").removeClass('tab blink');
		$("#timeClass2").removeClass('tab blink');
		$("#timeClass").addClass('white-color time-counter timer-elem');
		$("#timeClass2").addClass('white-color minutes-rem timer-elem');
		$("#prevnextquesdiv").html('');
		$("#prevnextquesdiv").hide();
		$("#logout").show();
		$.ajax({
			type : "POST",
			url : ST.baseURL + "testInstance",
			data : JSON.stringify(testJson),
			dataType : 'json',
			contentType : 'application/json',
			
			success : function() {
				// alert("done");
				// ST.testInstanceVOArr = [];
			},
			error : function() {
			}

		});
		
		setTimeout(AP.renderAttemped(data,status), 1000);
		
	}

	AP.renderAttemped = function(data,status) {
		$("#prevnextquesdiv").html('');
		$("#prevnextquesdiv").hide();
		var candidateSummaryHtm = ' <div id="row">'
				+ ' <div id="message" class="col-sm-12" >'
				+ ' <div id="onSubmit" class="alert alert-success cust-margin" role="alert" hidden><span class="glyphicon glyphicon-ok-circle  alert-success "></span>&nbsp;&nbsp;Congratulation you have successfully completed the test. Result will be generated soon.</div>'
				+ '  <div id="timeOut" class="alert alert-warning cust-margin" role="alert" hidden><span class="glyphicon glyphicon-remove-circle  alert-warning "></span>&nbsp;&nbsp;Your session has timed out and your answers have been saved successfully. Result will be generated soon.</div>'
				+ ' </div>' + ' </div>'
			
			'<div id="row">'
			
			for(var key in data.TOT){
				candidateSummaryHtm +=  ' <div id="" class="col-sm-4 box">'
				+ '  <table  class="width-100">'
				+ '      <tbody>'
				+ '            <tr align="center">'
				+ '                <td colspan="2">'
				+ '                    <h2 class="sectionHeaders">SECTION</h2>'
				+ '                </td>'
				+ '            </tr>'
				+ '            <tr align="center">'
				+ '                <td colspan="2">'
				+ '                    <h1 class="secondary-text-color">'+key+'</h1>'
				+ '                </td>'
				+ '             </tr>'
				+ '             <tr class="height-60px"></tr>'
				+ '            <tr>'
				+ '                 <td class="col-sm-6"><label class="summary-label">Attempted</label></td>'
				
				if(data.ATT[key] != undefined){
					candidateSummaryHtm += '<td class="col-sm-6 summary-column-value"><label class="attempted-ques">'+data.ATT[key]+'</label> <label class="secondary-text-color back-slash2"> / </label> <label id="totalQuestion" class="secondary-text-color total-ques">' +data.TOT[key]+ '</label></td>'
				}else{
					candidateSummaryHtm += '<td class="col-sm-6 summary-column-value"><label class="attempted-ques">0</label> <label class="secondary-text-color back-slash2"> / </label> <label id="totalQuestion" class="secondary-text-color total-ques">' +data.TOT[key]+ '</label></td>'
				}
				candidateSummaryHtm +=  '</tr>'
					+ '  <tr>'
//				+ '                <td class="col-sm-6"><label class="summary-label">Time Taken</label></td>'
//				+ '                 <td class="col-sm-6 summary-column-value"><label id="answeredQuestion" class="attempted-ques">15 MINS</label></td>'
//				+ '             </tr>'
				+ '         </tbody>'
				+ '     </table> '
				+ ' </div>'
			}

			candidateSummaryHtm +=  '    <div class="col-sm-12 t30-margin " align="right">'
				+ '  <a	 href="j_spring_security_logout"> <button id="logOutBtn" type="submit" title="Log Out" class="btn custom-btn">LOGOUT</button></a>'
				+ '    </div>' + '  </div>';
			
				$("#prevnextquesdiv").html('');
				$("#prevnextquesdiv").hide();
				$("#main-div").html(candidateSummaryHtm);
				
				if(status == "timeout"){
					$("#timeOut").show();
				}else{
					$("#onSubmit").show();
				}
				
				setTimeout(function(){$("#logOutBtn").click(); }, 40000);
	}
})(com.coep.test.ajaxHandler, com.coep.test.addProblem,
		com.coep.test.student);