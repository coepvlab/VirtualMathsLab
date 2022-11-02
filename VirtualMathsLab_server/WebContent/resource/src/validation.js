var mcacorransinpcnt = 1;
var mcaincansinpcnt = 1;
var fibcorransinpcnt = 1;
var fibincansinpcnt = 1;
var mtpcorranstxtcnt = 1;
var mtpincanstxtcnt = 1;
var scacorransinpcnt = 1;
var scaincansinpcnt = 1;
var scaimgcorransinpcnt = 1; // img sca correct opt
var scaimgincansinpcnt = 1;  // Img sca incorrect opt
var mtpimgcorranstxtcnt = 1;  // Img mtp incorrect opt
var mtpimgincanstxtcnt = 1; // Img mtp incorrect opt
var mcaimgcorransinpcnt = 1; // img mca correct opt
var mcaimgincansinpcnt = 1;  // Img mca incorrect opt
 modifyQuw = false;


function addmcaCorrAnsOpt(mcacorrAnsinpDivId) {
	$(".checkLatexAns").css("right","9px");
	if (mcacorransinpcnt  < 4) {
		// validateaddQuestionGroupForm1();
		
		mcacorransinpcnt++;
		if(modifyQuw == true){
		$("#" + mcacorrAnsinpDivId)
				.append(
						"<div id=\"mcacorrAns"
								+ mcacorransinpcnt
								+ "\" class=\"col-sm-11 col-md-11 col-lg-11 col-xl-11 \" >"
								+ "<h4 style=\"color:#0ec173\">Correct Answer "
								+ mcacorransinpcnt
								+ "</h4>"
								+ "<span onclick=\"removeRecord('mcacorrAnsInp',"
								+ mcacorransinpcnt
								+ ",'"
								+ mcacorrAnsinpDivId
								+ "')\" class=\"closeAnsOption clearer icon-delete rem-corr-ans-glyph\" >X</span>"
								+ "<input type=\"text\" id=\"mcaId"
								+ mcacorransinpcnt
								+ "\" placeholder= \"Correct Answer "
								+ mcacorransinpcnt
								+ "\" name='mcacorransinp' title=\"Correct Answer "
								+ mcacorransinpcnt
								+ "\" class=\"correctAnsBlk form-control hasclear corr-ans\" />"
								
								+ "</div>"
								//+'<button class="checkLatexAns" onclick="typesetInputForMCA1(this)" data-toggle="modal" data-target="#MCAlatexDataAns1">{ L }</button>'	
								 +'<button id="SCACorrect'+mcacorransinpcnt+'" class="checkLatexAnsAxt2" onclick="typesetInputForMCA1(this,'+ mcacorransinpcnt +')" data-toggle="modal" data-target="#latexDataCorrectAnsMCA1">{ L }</button>'
								 //   add que model for multiple Correct Answer start
								+ '<div id="latexDataCorrectAnsMCA1" class="modal fade" role="dialog">' 
								+ '<div class="modal-dialog">'
						
								+ ' <div class="modal-content">'
								+ '<div class="modal-header">'
								+ ' <span class="modal-title">'+mcacorransinpcnt+') Answer Latex Format </span>'
								+ ' <button type="button" class="close" data-dismiss="modal">&times;</button>'
								+ ' </div>'
								
								+ '<div class="modal-body"  style="  width: 500px;  height: auto;  overflow-x: auto;">' //
								+' <div class="scoll-tree">'
								+' <div id="MathPreviewMCAAns1" class="" placeholder="Latex equation" ></div><br/>'
						//		
								+ '	</div>'
								+ ' </div>'
								+ '	<div class="modal-footer">'
								+ '<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color:red; color:#fff;">Close</button>'
								+ '</div>'
								+ '</div>'
								// add que  model for multiple Correct Answer solution end 
				)
				
		}else
			{
			$("#" + mcacorrAnsinpDivId)
			.append(
					"<div id=\"mcacorrAns"
							+ mcacorransinpcnt
							+ "\" class=\"col-sm-11 col-md-11 col-lg-11 col-xl-11 \" >"
							+ "<h4 style=\"color:#0ec173\">Correct Answer "
							+ mcacorransinpcnt
							+ "</h4>"
							+ "<span onclick=\"removeRecord('mcacorrAnsInp',"
							+ mcacorransinpcnt
							+ ",'"
							+ mcacorrAnsinpDivId
							+ "')\" class=\"closeAnsOption clearer icon-delete rem-corr-ans-glyph\" >X</span>"
							+ "<input type=\"text\" id=\"mcaId"
							+ mcacorransinpcnt
							+ "\" placeholder= \"Correct Answer "
							+ mcacorransinpcnt
							+ "\" name='mcacorransinp' title=\"Correct Answer "
							+ mcacorransinpcnt
							+ "\" class=\"correctAnsBlk form-control hasclear corr-ans\" />"
							
							+ "</div>"
							//+'<button class="checkLatexAns" onclick="typesetInputForMCA1(this)" data-toggle="modal" data-target="#MCAlatexDataAns1">{ L }</button>'	
							 +'<button id="SCACorrect'+mcacorransinpcnt+'" class="checkLatexAnsAxt" onclick="typesetInputForMCA1(this,'+ mcacorransinpcnt +')" data-toggle="modal" data-target="#latexDataCorrectAnsMCA1">{ L }</button>'
							 //   add que model for multiple Correct Answer start
							+ '<div id="latexDataCorrectAnsMCA1" class="modal fade" role="dialog">' 
							+ '<div class="modal-dialog">'
					
							+ ' <div class="modal-content">'
							+ '<div class="modal-header">'
							+ ' <span class="modal-title">'+mcacorransinpcnt+') Answer Latex Format </span>'
							+ ' <button type="button" class="close" data-dismiss="modal">&times;</button>'
							+ ' </div>'
							
							+ '<div class="modal-body"  style="  width: 500px;  height: auto;  overflow-x: auto;">' //
							+' <div class="scoll-tree">'
							+' <div id="MathPreviewMCAAns1" class="" placeholder="Latex equation" ></div><br/>'
					//		
							+ '	</div>'
							+ ' </div>'
							+ '	<div class="modal-footer">'
							+ '<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color:red; color:#fff;">Close</button>'
							+ '</div>'
							+ '</div>'
							// add que  model for multiple Correct Answer solution end 
			)
			
	}
		
		// hideShow();
	}
}


// mca - Image add corr option

function addmcaImgCorrAnsOpt(mcaImgcorrAnsinpDivId) {
	if (mcaimgcorransinpcnt  < 4) {
		// validateaddQuestionGroupForm1();
		mcaimgcorransinpcnt++;
		$("#" + mcaImgcorrAnsinpDivId)
				.append(
						"<div id=\"mcaImgcorrAns"
								+ mcaimgcorransinpcnt
								+ "\" class=\"col-sm-12\" >"
								+ "<h4 style=\"color:#0ec173\">Correct Answer "
								+ mcaimgcorransinpcnt
								+ "</h4>"
								+ "<span onclick=\"removeRecord('mcaImgcorrAnsInp',"
								+ mcaimgcorransinpcnt
								+ ",'"
								+ mcaImgcorrAnsinpDivId
								+ "')\" class=\"closeAnsOption clearer icon-delete rem-corr-ans-glyph\" >X</span>"
								
								+ "<input type=\"file\" id=\"mcaImgId"
								+ mcaimgcorransinpcnt
								+ "\" placeholder= \"Incorrect Answer File"
								+ mcaimgcorransinpcnt
								+ "\" name='mcaImgcorransinp' title=\"Incorrect Answer "
								+ mcaimgcorransinpcnt
								+ "\" class=\"correctAnsBlk form-control hasclear corr-ans\" onchange=\"com.coep.test.addProblem.onSelInCorAnsFile(this.id,"+mcaimgcorransinpcnt+")\" />"
								+ '<div id="mcaImgId_error'+mcaimgcorransinpcnt+'" class="red-color"></div>'
								+'<div id="opt-preview-div-mcaImgId'+mcaimgcorransinpcnt+'" class="opt-preview-div-mcaImgId'+mcaimgcorransinpcnt+'"></div>')
		// hideShow();
	}
}

function addfibCorrAnsOpt(fibcorrAnsinpDivId) {
	if (fibcorransinpcnt  < 4) {
		// validateaddQuestionGroupForm1();
		fibcorransinpcnt++;
		$("#" + fibcorrAnsinpDivId)
				.append(
						"<div id=\"fibcorrAns"
								+ fibcorransinpcnt
								+ "\" class=\"col-sm-12\" >"
								+ "<h4 style=\"color:#0ec173\">Correct Answer "
								+ fibcorransinpcnt
								+ "</h4>"
								+ "<span onclick=\"removeRecord('fibcorrAnsInp',"
								+ fibcorransinpcnt
								+ ")\" class=\"closeAnsOption clearer icon-delete rem-corr-ans-glyph\" >X</span>"
								+ "<input type=\"text\" id=\"fibId"
								+ fibcorransinpcnt
								+ "\" placeholder= \"Correct Answer "
								+ fibcorransinpcnt
								+ "\" name='fibcorransinp' title=\"Correct Answer "
								+ fibcorransinpcnt
								+ "\" class=\"correctAnsBlk form-control hasclear corr-ans\" />"
								
								+ "</div>")
		// hideShow();
	}
}
function addmtpCorrAnsTxtOpt(corrAnstxtareaDivId) {
	if (mtpcorranstxtcnt  < 4) {
		mtpcorranstxtcnt++;
		$("#" + corrAnstxtareaDivId)
				.append(
						"<div id=\"mtpcorrAns"
								+ mtpcorranstxtcnt
								+ "\" class=\"col-sm-11 col-md-11 col-lg-11 col-xl-11\" >"
								+ "<h4 style=\"color:#0ec173\">Correct Answer "
								+ mtpcorranstxtcnt
								+ "</h4>"
								+ "<span onclick=\"removeRecord('corrAnsTxt',"
								+ mtpcorranstxtcnt
								+ ")\" class=\"closeAnsOptionMTP clearer icon-delete rem-corr-ans-glyph\" >X</span>"
								+ "<textarea  id=\"mtpId"
								+ mtpcorranstxtcnt
								+ "\"  placeholder= \"Correct Answer "
								+ mtpcorranstxtcnt
								+ "\" name='mtpcorranstxt' title=\"Correct Answer "
								+ mtpcorranstxtcnt
								+ "\" class=\"correctAnsBlk form-control hasclear corr-ans\" ></textarea>"
								
								+ "</div>"
								 +'<button id="SCAWrong'+mtpcorranstxtcnt+'" class="checkLatexAnsAxt" onclick="typesetInputForOptionalMICA1(this,'+ mtpcorranstxtcnt +')" data-toggle="modal" data-target="#latexDataWrongAnsMCA1">{ L }</button>'
								 //   add que model for multiple Correct Answer start
								+ '<div id="latexDataWrongAnsMCA1" class="modal fade" role="dialog">' 
								+ '<div class="modal-dialog">'
						
								+ ' <div class="modal-content">'
								+ '<div class="modal-header">'
								+ ' <span class="modal-title">'+mtpcorranstxtcnt+') Answer Latex Format </span>'
								+ ' <button type="button" class="close" data-dismiss="modal">&times;</button>'
								+ ' </div>'
								
								+ '<div class="modal-body"  style="  width: 500px;  height: auto;  overflow-x: auto;">' //
								+' <div class="scoll-tree">'
								+' <div id="MathPreviewWrongAns1" class="" placeholder="Latex equation" ></div><br/>'
						//		
								+ '	</div>'
								+ ' </div>'
								+ '	<div class="modal-footer">'
								+ '<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color:red; color:#fff;">Close</button>'
								+ '</div>'
								+ '</div>'
								// add que  model for multiple Correct Answer solution end   
				
				
				)
						
		// hideShow();
								
	}
}

// mtp Image option add corr option
function addmtpImgCorrAnsTxtOpt(corrAnstxtareaDivId) {
	if (mtpimgcorranstxtcnt  < 4) {
		mtpimgcorranstxtcnt++;
		$("#" + corrAnstxtareaDivId)
				.append(
						"<div id=\"mtpImgcorrAns"
								+ mtpimgcorranstxtcnt
								+ "\" class=\"col-sm-12\" >"
								+ "<h4 style=\"color:#0ec173\">Correct Answer "
								+ mtpimgcorranstxtcnt
								+ "</h4>"
								+ "<span onclick=\"removeRecord('mtpImgcorrAnsTxt',"
								+ mtpimgcorranstxtcnt
								+ ")\" class=\"closeAnsOption clearer icon-delete rem-corr-ans-glyph\" >X</span>"
								
								+ "<input type=\"file\" id=\"mtpImgId"
								+ mtpimgcorranstxtcnt
								+ "\" placeholder= \"Incorrect Answer File"
								+ mtpimgcorranstxtcnt
								+ "\" name='mtpImgcorranstxt' title=\"Incorrect Answer "
								+ mtpimgcorranstxtcnt
								+ "\" class=\"correctAnsBlk form-control hasclear inc-ans\" onchange=\"com.coep.test.addProblem.onSelInCorAnsFile(this.id,"+mtpimgcorranstxtcnt+")\" />"
								+ '<div id="mtpImgId_error'+mtpimgcorranstxtcnt+'" class="red-color"></div>'
								+'<div id="opt-preview-div-mtpImgId'+mtpimgcorranstxtcnt+'" class="opt-preview-div-mtpImgId'+mtpimgcorranstxtcnt+'"></div>')
		// hideShow();
	}
}


function addscaCorrAnsOpt(scacorrAnsinpDivId) {
	if (scacorransinpcnt  < 4) {
		// validateaddQuestionGroupForm1();
		scacorransinpcnt++;
		$("#" + scacorrAnsinpDivId)
				.append(
						"<div id=\"scacorrAns"
								+ scacorransinpcnt
								+ "\" class=\"col-sm-12\" >"
								+ "<h4 style=\"color:#0ec173\">Correct Answer "
								+ scacorransinpcnt
								+ "</h4>"
								+ "<span onclick=\"removeRecord('scacorrAnsInp',"
								+ scacorransinpcnt
								+ ",'"
								+ scacorrAnsinpDivId
								+ "')\" class=\"closeAnsOption clearer icon-delete rem-corr-ans-glyph\" >X</span>"
								+ "<input type=\"text\" id=\"scaId"
								+ scacorransinpcnt
								+ "\" placeholder= \"Correct Answer "
								+ scacorransinpcnt
								+ "\" name='scacorransinp' title=\"Correct Answer "
								+ scacorransinpcnt
								+ "\" class=\"correctAnsBlk form-control hasclear corr-ans\" />"
								
								+ "</div>")
		// hideShow();
	}
}



function addmcaIncAnsInpOpt(mcaincAnsinpDivId) {
	$(".checkLatexAns").css("right","9px");
	if ( mcaincansinpcnt < 4) {
		mcaincansinpcnt++;
		if(modifyQuw == true){
		$("#" + mcaincAnsinpDivId)
				.append(
						"<div id=\"mcaincAns"
								+ mcaincansinpcnt
								+ "\" class=\"col-sm-11 col-md-11 col-lg-11 col-xl-11\" >"
								+ "<h4 style=\"color:#ff0000\">Incorrect Answer "
								+ mcaincansinpcnt
								+ "</h4>"
								+ "<span onclick=\"removeRecord('mcaincAnsInp',"
								+ mcaincansinpcnt
								+ ",'"
								+ mcaincAnsinpDivId
								+ "')\" class=\"closeAnsOption clearer icon-delete rem-inc-ans-glyph\" >X</span>"
								+ "<input type=\"text\" id=\"micaId"
								+ mcaincansinpcnt
								+ "\" placeholder= \"Incorrect Answer "
								+ mcaincansinpcnt
								+ "\" name='mcaincansinp' title=\"Incorrect Answer "
								+ mcaincansinpcnt
								+ "\" class=\"inCorrectAnsBlk form-control hasclear inc-ans\" />"
								
								+ "</div>"
							//	+'<button class="checkLatexAns" onclick="typesetInputForMICA1(this)" data-toggle="modal" data-target="#MCAlatexDataAns1">{ L }</button>'	
								+'<button id="SCAWrong'+mcaincansinpcnt+'" class="checkLatexAnsAxt2" onclick="typesetInputForMICA1(this,'+ mcaincansinpcnt +')" data-toggle="modal" data-target="#latexDataWrongAnsMCA1">{ L }</button>'
								 //   add que model for multiple Correct Answer start
								+ '<div id="latexDataWrongAnsMCA1" class="modal fade" role="dialog">' 
								+ '<div class="modal-dialog">'
						
								+ ' <div class="modal-content">'
								+ '<div class="modal-header">'
								+ ' <span class="modal-title">'+mcaincansinpcnt+') Answer Latex Format </span>'
								+ ' <button type="button" class="close" data-dismiss="modal">&times;</button>'
								+ ' </div>'
								
								+ '<div class="modal-body"  style="  width: 500px;  height: auto;  overflow-x: auto;">' //
								+' <div class="scoll-tree">'
								+' <div id="MathPrevieMCAwWrongAns1" class="" placeholder="Latex equation" ></div><br/>'
						//		
								+ '	</div>'
								+ ' </div>'
								+ '	<div class="modal-footer">'
								+ '<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color:red; color:#fff;">Close</button>'
								+ '</div>'
								+ '</div>'
								// add que  model for multiple Correct Answer solution end 
				)
		}
		else{

			$("#" + mcaincAnsinpDivId)
					.append(
							"<div id=\"mcaincAns"
									+ mcaincansinpcnt
									+ "\" class=\"col-sm-11 col-md-11 col-lg-11 col-xl-11\" >"
									+ "<h4 style=\"color:#ff0000\">Incorrect Answer "
									+ mcaincansinpcnt
									+ "</h4>"
									+ "<span onclick=\"removeRecord('mcaincAnsInp',"
									+ mcaincansinpcnt
									+ ",'"
									+ mcaincAnsinpDivId
									+ "')\" class=\"closeAnsOption clearer icon-delete rem-inc-ans-glyph\" >X</span>"
									+ "<input type=\"text\" id=\"micaId"
									+ mcaincansinpcnt
									+ "\" placeholder= \"Incorrect Answer "
									+ mcaincansinpcnt
									+ "\" name='mcaincansinp' title=\"Incorrect Answer "
									+ mcaincansinpcnt
									+ "\" class=\"inCorrectAnsBlk form-control hasclear inc-ans\" />"
									
									+ "</div>"
								//	+'<button class="checkLatexAns" onclick="typesetInputForMICA1(this)" data-toggle="modal" data-target="#MCAlatexDataAns1">{ L }</button>'	
									+'<button id="SCAWrong'+mcaincansinpcnt+'" class="checkLatexAnsAxt" onclick="typesetInputForMICA1(this,'+ mcaincansinpcnt +')" data-toggle="modal" data-target="#latexDataWrongAnsMCA1">{ L }</button>'
									 //   add que model for multiple Correct Answer start
									+ '<div id="latexDataWrongAnsMCA1" class="modal fade" role="dialog">' 
									+ '<div class="modal-dialog">'
							
									+ ' <div class="modal-content">'
									+ '<div class="modal-header">'
									+ ' <span class="modal-title">'+mcaincansinpcnt+') Answer Latex Format </span>'
									+ ' <button type="button" class="close" data-dismiss="modal">&times;</button>'
									+ ' </div>'
									
									+ '<div class="modal-body"  style="  width: 500px;  height: auto;  overflow-x: auto;">' //
									+' <div class="scoll-tree">'
									+' <div id="MathPrevieMCAwWrongAns1" class="" placeholder="Latex equation" ></div><br/>'
							//		
									+ '	</div>'
									+ ' </div>'
									+ '	<div class="modal-footer">'
									+ '<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color:red; color:#fff;">Close</button>'
									+ '</div>'
									+ '</div>'
									// add que  model for multiple Correct Answer solution end 
					)
			
		}
		// hideShow();
				    
	}
}


// Image - mca incorr option


function addmcaImgIncAnsInpOpt(mcaImgincAnsinpDivId) {
	if ( mcaimgincansinpcnt < 4) {
		mcaimgincansinpcnt++;
		$("#" + mcaImgincAnsinpDivId)
				.append(
						"<div id=\"mcaImgincAns"
								+ mcaimgincansinpcnt
								+ "\" class=\"col-sm-12\" >"
								+ "<h4 style=\"color:#ff0000\">Incorrect Answer "
								+ mcaimgincansinpcnt
								+ "</h4>"
								+ "<span onclick=\"removeRecord('mcaImgincAnsInp',"
								+ mcaimgincansinpcnt
								+ ",'"
								+ mcaImgincAnsinpDivId
								+ "')\" class=\"closeAnsOption clearer icon-delete rem-inc-ans-glyph\" >X</span>"
//								+ "<input type=\"text\" id=\"micaId"
//								+ mcaimgincansinpcnt
//								+ "\" placeholder= \"Incorrect Answer "
//								+ mcaimgincansinpcnt
//								+ "\" name='mcaincansinp' title=\"Incorrect Answer "
//								+ mcaimgincansinpcnt
//								+ "\" class=\"inCorrectAnsBlk form-control hasclear inc-ans\" />"
//								
//								+ "</div>")
								+ "<input type=\"file\" id=\"mImgicaId"
								+ mcaimgincansinpcnt
								+ "\" placeholder= \"Incorrect Answer File"
								+ mcaimgincansinpcnt
								+ "\" name='mcaImgincansinp' title=\"Incorrect Answer "
								+ mcaimgincansinpcnt
								+ "\" class=\"inCorrectAnsBlk form-control hasclear inc-ans\" onchange=\"com.coep.test.addProblem.onSelInCorAnsFile(this.id,"+mcaimgincansinpcnt+")\" />"
								+ '<div id="mImgicaId_error'+mcaimgincansinpcnt+'" class="red-color"></div>'
								+'<div id="opt-preview-div-mImgicaId'+mcaimgincansinpcnt+'" class="opt-preview-div-mImgicaId'+mcaimgincansinpcnt+'"></div>')
		// hideShow();
	}
}

function addfibIncAnsInpOpt(fibincAnsinpDivId) {
	if (fibincansinpcnt < 4) {
		fibincansinpcnt++;
		
		if (modifyQuw == true) {
			$("#" + fibincAnsinpDivId)
			.append(
					"<div id=\"fibincAns"
							+ fibincansinpcnt
							+ "\" class=\"col-sm-11 col-md-11 col-lg-11 col-xl-11\" >"
							+ "<h4 style=\"color:#ff0000\">Incorrect Answer "
							+ fibincansinpcnt
							+ "</h4>"
							+ "<span onclick=\"removeRecord('fibincAnsInp',"
							+ fibincansinpcnt
							+ ")\" class=\"closeAnsOption clearer icon-delete rem-inc-ans-glyph\" >X</span>"
							+ "<input type=\"text\" id=\"icfibId"
							+ fibincansinpcnt
							+ "\" placeholder= \"Incorrect Answer "
							+ fibincansinpcnt
							+ "\" name='fibincansinp' title=\"Incorrect Answer "
							+ fibincansinpcnt
							+ "\" class=\"inCorrectAnsBlk form-control hasclear inc-ans\" />"
							
							+ "</div>"
							
							+'<button id="IFIBWrong'+fibincansinpcnt+'" class="checkLatexAnsAxt2" onclick="typesetInputForIFIB(this,'+ fibincansinpcnt +')" data-toggle="modal" data-target="#latexDataWrongAnsIFIB1">{ L }</button>'
							 //   add que model for multiple Correct Answer start
							+ '<div id="latexDataWrongAnsIFIB1" class="modal fade" role="dialog">' 
							+ '<div class="modal-dialog">'
					
							+ ' <div class="modal-content">'
							+ '<div class="modal-header">'
							+ ' <span class="modal-title">'+fibincansinpcnt+') Answer Latex Format </span>'
							+ ' <button type="button" class="close" data-dismiss="modal">&times;</button>'
							+ ' </div>'
							
							+ '<div class="modal-body"  style="  width: 500px;  height: auto;  overflow-x: auto;">' //
							+' <div class="scoll-tree">'
							+' <div id="MathPrevieIFIB1" class="" placeholder="Latex equation" ></div><br/>'
					//		
							+ '	</div>'
							+ ' </div>'
							+ '	<div class="modal-footer">'
							+ '<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color:red; color:#fff;">Close</button>'
							+ '</div>'
							+ '</div>'
							// add que  model for multiple Correct Answer solution end 
			)
		}else{
			$("#" + fibincAnsinpDivId)
			.append(
					"<div id=\"fibincAns"
							+ fibincansinpcnt
							+ "\" class=\"col-sm-11 col-md-11 col-lg-11 col-xl-11\" >"
							+ "<h4 style=\"color:#ff0000\">Incorrect Answer "
							+ fibincansinpcnt
							+ "</h4>"
							+ "<span onclick=\"removeRecord('fibincAnsInp',"
							+ fibincansinpcnt
							+ ")\" class=\"closeAnsOption clearer icon-delete rem-inc-ans-glyph\" >X</span>"
							+ "<input type=\"text\" id=\"icfibId"
							+ fibincansinpcnt
							+ "\" placeholder= \"Incorrect Answer "
							+ fibincansinpcnt
							+ "\" name='fibincansinp' title=\"Incorrect Answer "
							+ fibincansinpcnt
							+ "\" class=\"inCorrectAnsBlk form-control hasclear inc-ans\" />"
							
							+ "</div>"
							
							+'<button id="IFIBWrong'+fibincansinpcnt+'" class="checkLatexAnsAxt" onclick="typesetInputForIFIB(this,'+ fibincansinpcnt +')" data-toggle="modal" data-target="#latexDataWrongAnsIFIB1">{ L }</button>'
							 //   add que model for multiple Correct Answer start
							+ '<div id="latexDataWrongAnsIFIB1" class="modal fade" role="dialog">' 
							+ '<div class="modal-dialog">'
					
							+ ' <div class="modal-content">'
							+ '<div class="modal-header">'
							+ ' <span class="modal-title">'+fibincansinpcnt+') Answer Latex Format </span>'
							+ ' <button type="button" class="close" data-dismiss="modal">&times;</button>'
							+ ' </div>'
							
							+ '<div class="modal-body"  style="  width: 500px;  height: auto;  overflow-x: auto;">' //
							+' <div class="scoll-tree">'
							+' <div id="MathPrevieIFIB1" class="" placeholder="Latex equation" ></div><br/>'
					//		
							+ '	</div>'
							+ ' </div>'
							+ '	<div class="modal-footer">'
							+ '<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color:red; color:#fff;">Close</button>'
							+ '</div>'
							+ '</div>'
							// add que  model for multiple Correct Answer solution end 
			)
		}
		
		// hideShow();
//				      if(modifyQuw == true)
//							{$("#IFIBWrong" +mcaincansinpcnt ).removeClass("checkLatexAnsAxt").addClass("checkLatexAnsAxt2");}
//						else
//							{$("#IFIBWrong" +mcaincansinpcnt ).removeClass("checkLatexAnsAxt2").addClass("checkLatexAnsAxt");}
		// hideShow();
	}
}


function addmtpIncAnsTxtOpt(incAnstxtareaDivId) {
	if (mtpincanstxtcnt < 4) {
		mtpincanstxtcnt++;
		if (modifyQuw == true) {
		$("#" + incAnstxtareaDivId)
				.append(
						"<div id=\"mtpincAns"
								+ mtpincanstxtcnt
								+ "\" class=\"col-sm-11 col-md-11 col-lg-11 col-xl-11\" >"
								+ "<h4 style=\"color:#ff0000\">Incorrect Answer "
								+ mtpincanstxtcnt
								+ "</h4>"
								+ "<span onclick=\"removeRecord('incAnsTxt',"
								+ mtpincanstxtcnt
								+ ")\" class=\"closeAnsOptionMTP clearer icon-delete rem-inc-ans-glyph\" >X</span>"
								+ "<textarea  id=\"icmtpId"
								+ mtpincanstxtcnt
								+ "\" placeholder= \"Incorrect Answer "
								+ mtpincanstxtcnt
								+ "\" name='mtpincanstxt' title=\"Incorrect Answer "
								+ mtpincanstxtcnt
								+ "\" class=\"inCorrectAnsBlk form-control hasclear inc-ans\" ></textarea>"
								
								+ "</div>"
								+'<button id="MTPIWrong'+mtpincanstxtcnt+'" class="checkLatexAnsAxt2" onclick="typesetInputForMTPI(this,'+ mtpincanstxtcnt +')" data-toggle="modal" data-target="#latexDataWrongAnsMTP">{ L }</button>'
								 //   add que model for Match the pair Answer start
								+ '<div id="latexDataWrongAnsMTP" class="modal fade" role="dialog">' 
								+ '<div class="modal-dialog">'
						
								+ ' <div class="modal-content">'
								+ '<div class="modal-header">'
								+ ' <span class="modal-title"> Answer Latex Format </span>'
								+ ' <button type="button" class="close" data-dismiss="modal">&times;</button>'
								+ ' </div>'
								
								+ '<div class="modal-body"  style="  width: 500px;  height: auto;  overflow-x: auto;">' //
								+' <div class="scoll-tree">'
								+' <div id="MathPrevieIMTPI" class="" placeholder="Latex equation" ></div><br/>'
						//		
								+ '	</div>'
								+ ' </div>'
								+ '	<div class="modal-footer">'
								+ '<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color:red; color:#fff;">Close</button>'
								+ '</div>'
								+ '</div>'
								// add que  model for Match the pair Answer solution end 				
				)
		}else
			{

			$("#" + incAnstxtareaDivId)
					.append(
							"<div id=\"mtpincAns"
									+ mtpincanstxtcnt
									+ "\" class=\"col-sm-11 col-md-11 col-lg-11 col-xl-11\" >"
									+ "<h4 style=\"color:#ff0000\">Incorrect Answer "
									+ mtpincanstxtcnt
									+ "</h4>"
									+ "<span onclick=\"removeRecord('incAnsTxt',"
									+ mtpincanstxtcnt
									+ ")\" class=\"closeAnsOptionMTP clearer icon-delete rem-inc-ans-glyph\" >X</span>"
									+ "<textarea  id=\"icmtpId"
									+ mtpincanstxtcnt
									+ "\" placeholder= \"Incorrect Answer "
									+ mtpincanstxtcnt
									+ "\" name='mtpincanstxt' title=\"Incorrect Answer "
									+ mtpincanstxtcnt
									+ "\" class=\"inCorrectAnsBlk form-control hasclear inc-ans\" ></textarea>"
									
									+ "</div>"
									+'<button id="MTPIWrong'+mtpincanstxtcnt+'" class="checkLatexAnsAxt" onclick="typesetInputForMTPI(this,'+ mtpincanstxtcnt +')" data-toggle="modal" data-target="#latexDataWrongAnsMTP">{ L }</button>'
									 //   add que model for Match the pair Answer start
									+ '<div id="latexDataWrongAnsMTP" class="modal fade" role="dialog">' 
									+ '<div class="modal-dialog">'
							
									+ ' <div class="modal-content">'
									+ '<div class="modal-header">'
									+ ' <span class="modal-title">'+mtpincanstxtcnt+') Answer Latex Format </span>'
									+ ' <button type="button" class="close" data-dismiss="modal">&times;</button>'
									+ ' </div>'
									
									+ '<div class="modal-body"  style="  width: 500px;  height: auto;  overflow-x: auto;">' //
									+' <div class="scoll-tree">'
									+' <div id="MathPrevieIMTPI" class="" placeholder="Latex equation" ></div><br/>'
							//		
									+ '	</div>'
									+ ' </div>'
									+ '	<div class="modal-footer">'
									+ '<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color:red; color:#fff;">Close</button>'
									+ '</div>'
									+ '</div>'
									// add que  model for Match the pair Answer solution end 				
					)
			
			
			}
		// hideShow();
	}
}

//  mtp Image for incorr option

function addmtpImgIncAnsTxtOpt(incAnstxtareaDivId) {
	if (mtpimgincanstxtcnt < 4) {
		mtpimgincanstxtcnt++;
		
		$("#" + incAnstxtareaDivId)
				.append(
						"<div id=\"mtpImgincAns"
								+ mtpimgincanstxtcnt
								+ "\" class=\"col-sm-12\" >"
								+ "<h4 style=\"color:#ff0000\">Incorrect Answer "
								+ mtpimgincanstxtcnt
								+ "</h4>"
								+ "<span onclick=\"removeRecord('mtpImgincAnsTxt',"
								+ mtpimgincanstxtcnt
								+ ")\" class=\"closeAnsOption clearer icon-delete rem-inc-ans-glyph\" >X</span>"
//								+ "<textarea  id=\"icmtpId"
//								+ mtpimgincanstxtcnt
//								+ "\" placeholder= \"Incorrect Answer "
//								+ mtpimgincanstxtcnt
//								+ "\" name='mtpincanstxt' title=\"Incorrect Answer "
//								+ mtpimgincanstxtcnt
//								+ "\" class=\"inCorrectAnsBlk form-control hasclear inc-ans\" ></textarea>"
//								
//								+ "</div>")
								+ "<input type=\"file\" id=\"icmtpImgId"
								+ mtpimgincanstxtcnt
								+ "\" placeholder= \"Incorrect Answer File"
								+ mtpimgincanstxtcnt
								+ "\" name='mtpImgincanstxt' title=\"Incorrect Answer "
								+ mtpimgincanstxtcnt
								+ "\" class=\"inCorrectAnsBlk form-control hasclear inc-ans\" onchange=\"com.coep.test.addProblem.onSelInCorAnsFile(this.id,"+mtpimgincanstxtcnt+")\" />"
								+ '<div id="icmtpImgId_error'+mtpimgincanstxtcnt+'" class="red-color"></div>'
								+'<div id="opt-preview-div-icmtpImgId'+mtpimgincanstxtcnt+'" class="opt-preview-div-icmtpImgId'+mtpimgincanstxtcnt+'"></div>')

		// hideShow();
	}
}

function addscaIncAnsInpOpt(scaincAnsinpDivId) {
	$(".checkLatexAns").css("right","9px");
	if (modifyQuw == true) {
	if ( scaincansinpcnt < 4) {
		scaincansinpcnt++;
		$("#" + scaincAnsinpDivId)
				.append(
						"<div id=\"scaincAns"
								+ scaincansinpcnt
								+ "\" class=\"col-sm-11 col-md-11 col-lg-11 col-xl-11\" >"
								+ "<h4 style=\"color:#ff0000\">Incorrect Answer "
								+ scaincansinpcnt
								+ "</h4>"
								+ "<span onclick=\"removeRecord('scaincAnsInp',"
								+ scaincansinpcnt
								+ ",'"
								+ scaincAnsinpDivId
								+ "')\" class=\"closeAnsOption clearer icon-delete rem-inc-ans-glyph\" >X</span>"
								+ "<input type=\"text\" id=\"sicaId"
								+ scaincansinpcnt
								+ "\" placeholder= \"Incorrect Answer "
								+ scaincansinpcnt
								+ "\" name='scaincansinp' title=\"Incorrect Answer "
								+ scaincansinpcnt
								+ "\" class=\"inCorrectAnsBlk form-control hasclear inc-ans\" />"
								
								+ "</div>"
				                +'<button id="SCAWrong'+scaincansinpcnt+'" class="checkLatexAnsAxt2" onclick="typesetInputForOptionalSICA1(this,'+ scaincansinpcnt +')" data-toggle="modal" data-target="#latexDataWrongAns1">{ L }</button>'
				           //   add que model for Singular Correct Answer start
								+ '<div id="latexDataWrongAns1" class="modal fade" role="dialog">' 
								+ '<div class="modal-dialog">'
						
								+ ' <div class="modal-content">'
								+ '<div class="modal-header">'
								+ ' <span class="modal-title">'+scaincansinpcnt+') Answer Latex Format </span>'
								+ ' <button type="button" class="close" data-dismiss="modal">&times;</button>'
								+ ' </div>'
								
								+ '<div class="modal-body"  style="  width: 500px;  height: auto;  overflow-x: auto;">' //
								+' <div class="scoll-tree">'
								+' <div id="MathPreviewWrongAns1" class="" placeholder="Latex equation" ></div><br/>'
						//		
								+ '	</div>'
								+ ' </div>'
								+ '	<div class="modal-footer">'
								+ '<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color:red; color:#fff;">Close</button>'
								+ '</div>'
								+ '</div>'
								// add que  model for Singular Correct Answer solution end                
				
				
				)
	}
	
	}else
		{
		if ( scaincansinpcnt < 4) {
			scaincansinpcnt++;
			$("#" + scaincAnsinpDivId)
					.append(
							"<div id=\"scaincAns"
									+ scaincansinpcnt
									+ "\" class=\"col-sm-11 col-md-11 col-lg-11 col-xl-11\" >"
									+ "<h4 style=\"color:#ff0000\">Incorrect Answer "
									+ scaincansinpcnt
									+ "</h4>"
									+ "<span onclick=\"removeRecord('scaincAnsInp',"
									+ scaincansinpcnt
									+ ",'"
									+ scaincAnsinpDivId
									+ "')\" class=\"closeAnsOption clearer icon-delete rem-inc-ans-glyph\" >X</span>"
									+ "<input type=\"text\" id=\"sicaId"
									+ scaincansinpcnt
									+ "\" placeholder= \"Incorrect Answer "
									+ scaincansinpcnt
									+ "\" name='scaincansinp' title=\"Incorrect Answer "
									+ scaincansinpcnt
									+ "\" class=\"inCorrectAnsBlk form-control hasclear inc-ans\" />"
									
									+ "</div>"
					                +'<button id="SCAWrong'+scaincansinpcnt+'" class="checkLatexAnsAxt" onclick="typesetInputForOptionalSICA1(this,'+ scaincansinpcnt +')" data-toggle="modal" data-target="#latexDataWrongAns1">{ L }</button>'
					           //   add que model for Singular Correct Answer start
									+ '<div id="latexDataWrongAns1" class="modal fade" role="dialog">' 
									+ '<div class="modal-dialog">'
							
									+ ' <div class="modal-content">'
									+ '<div class="modal-header">'
									+ ' <span class="modal-title">'+scaincansinpcnt+') Answer Latex Format </span>'
									+ ' <button type="button" class="close" data-dismiss="modal">&times;</button>'
									+ ' </div>'
									
									+ '<div class="modal-body"  style="  width: 500px;  height: auto;  overflow-x: auto;">' //
									+' <div class="scoll-tree">'
									+' <div id="MathPreviewWrongAns1" class="" placeholder="Latex equation" ></div><br/>'
							//		
									+ '	</div>'
									+ ' </div>'
									+ '	<div class="modal-footer">'
									+ '<button type="button" class="btn btn-default" data-dismiss="modal" style="background-color:red; color:#fff;">Close</button>'
									+ '</div>'
									+ '</div>'
									// add que  model for Singular Correct Answer solution end                
					
					
					)
		}
		
		}
								//console.log("scaId" + Id);
							
		// hideShow();
	}


/// zzz imgge option sca

function addImgscaIncAnsInpOpt(scaimgincAnsinpDivId) {
	if ( scaimgincansinpcnt < 4) {
		scaimgincansinpcnt++;
		$("#" + scaimgincAnsinpDivId)
				.append(
						"<div id=\"scaImgincAns"
								+ scaimgincansinpcnt
								+ "\" class=\"col-sm-12\" >"
								+ "<h4 style=\"color:#ff0000\">Incorrect Answer "
								+ scaimgincansinpcnt
								+ "</h4>"
								+ "<span onclick=\"removeRecord('scaimgincAnsInp',"
								+ scaimgincansinpcnt
								+ ",'"
								+ scaimgincAnsinpDivId
								+ "')\" class=\"closeAnsOption clearer icon-delete rem-inc-ans-glyph\" >X</span>"
								+ "<input type=\"file\" id=\"sImgicaId"
								+ scaimgincansinpcnt
								+ "\" placeholder= \"Incorrect Answer File"
								+ scaimgincansinpcnt
								+ "\" name='scaImgincansinp' title=\"Incorrect Answer "
								+ scaimgincansinpcnt
								+ "\" class=\"inCorrectAnsBlk form-control hasclear inc-ans\" onchange=\"com.coep.test.addProblem.onSelInCorAnsFile(this.id,"+scaimgincansinpcnt+")\" />"
								+ '<div id="sImgicaId_error'+scaimgincansinpcnt+'" class="red-color"></div>'
								+'<div id="opt-preview-div-sImgicaId'+scaimgincansinpcnt+'" class="opt-preview-div-sImgicaId'+scaimgincansinpcnt+'"></div>')
								

		// hideShow(); + '<input  type="file"  id="sImgicaId1" name="scaImgincorransinp" title="Upload incorrect Answer File 1" col="8" onchange="com.coep.test.addProblem.onSelInCorAnsFile()" class="inCorrectAnsBlk form-control hasclear inc-ans" >'
	}
}



$(document).ready(function() {
	// hideShow();
});

/** Used to hide and show cross button in input box* */
// function hideShow(){
// $(".hasclear, .clearer").hover(function () {
// $(".clearer").show();
// });
// $(".hasclear, .clearer").mouseout(function () {
// $(".clearer").hide();
// });
// }
/** Used to remove record from UI * */
function removeRecord(rowId, count, parentDivId) {
	var cnt;
	var cnfrmYes = confirm("Are you sure you want to delete ?");
	if (cnfrmYes == true) {
		if (rowId == "mcacorrAnsInp") {
			var totDivCnt = mcacorransinpcnt;
			if (count < mcacorransinpcnt) {
				var nextDivId;
				for (cnt = count; cnt < totDivCnt; cnt++) {
					nextDivId = cnt + 1;
//					alert("#" + parentDivId + " #mcacorrAns" + nextDivId);
					var nextDivVal = $(
							"#" + parentDivId + " #mcacorrAns" + nextDivId
									+ " :input").val();
					$("#" + parentDivId + " #mcacorrAns" + cnt + " :input")
							.val(nextDivVal);
				}
				$("#" + parentDivId + " #mcacorrAns" + nextDivId).remove();
				$("#" + parentDivId + " #SCACorrect" + nextDivId).remove();
				mcacorransinpcnt--;
			} else if (count == mcacorransinpcnt) {
				$("#" + parentDivId + " #mcacorrAns" + count).remove();
				$("#" + parentDivId + " #SCACorrect" + count).remove();
				mcacorransinpcnt--;
			}
		}	if (rowId == "mcaImgcorrAnsInp") {
			var totDivCnt = mcaimgcorransinpcnt;
			if (count < mcaimgcorransinpcnt) {
				var nextDivId;
				for (cnt = count; cnt < totDivCnt; cnt++) {
					nextDivId = cnt + 1;
//					alert("#" + parentDivId + " #mcacorrAns" + nextDivId);
					var nextDivVal = $(
							"#" + parentDivId + " #mcaImgcorrAns" + nextDivId
									+ " :input").val();
					$("#" + parentDivId + " #mcaImgcorrAns" + cnt + " :input")
							.val(nextDivVal);
				}
				$("#" + parentDivId + " #mcaImgcorrAns" + nextDivId).remove();
				mcaimgcorransinpcnt--;
			} else if (count == mcaimgcorransinpcnt) {
				$("#" + parentDivId + " #mcaImgcorrAns" + count).remove();
				mcaimgcorransinpcnt--;
			}
		}  else if (rowId == "scacorrAnsInp") {
			var totDivCnt = scacorransinpcnt;
			if (count < scacorransinpcnt) {
				var nextDivId;
				for (cnt = count; cnt < totDivCnt; cnt++) {
					nextDivId = cnt + 1;
//					alert("#" + parentDivId + " #scacorrAns" + nextDivId);
					var nextDivVal = $(
							"#" + parentDivId + " #scacorrAns" + nextDivId
									+ " :input").val();
					$("#" + parentDivId + " #scacorrAns" + cnt + " :input")
							.val(nextDivVal);
				}
				$("#" + parentDivId + " #scacorrAns" + nextDivId).remove();
				scacorransinpcnt--;
			} else if (count == scacorransinpcnt) {
				$("#" + parentDivId + " #scacorrAns" + count).remove();
				scacorransinpcnt--;
			}
		} 
		else if (rowId == "fibcorrAnsInp") {
			var totDivCnt = fibcorransinpcnt;
			if (count < fibcorransinpcnt) {
				var nextDivId;
				for (cnt = count; cnt < totDivCnt; cnt++) {
					nextDivId = cnt + 1;
					var nextDivVal = $("#fibcorrAns" + nextDivId + " :input")
							.val();
					$("#fibcorrAns" + cnt + " :input").val(nextDivVal);
				}
				$("#fibcorrAns" + nextDivId).remove();
				fibcorransinpcnt--;
			} else if (count == fibcorransinpcnt) {
				$("#fibcorrAns" + count).remove();
				fibcorransinpcnt--;
			}
		} else if (rowId == "corrAnsTxt") {
			var totDivCnt = mtpcorranstxtcnt;
			if (count < mtpcorranstxtcnt) {
				var nextDivId
				for (cnt = count; cnt < totDivCnt; cnt++) {
					nextDivId = cnt + 1;
					var nextDivVal = $("#mtpcorrAns" + nextDivId + " :input")
							.val();
					$("#mtpcorrAns" + cnt + " :input").val(nextDivVal);
				}
				$("#mtpcorrAns" + nextDivId).remove();
				mtpcorranstxtcnt--;
			} else if (count == mtpcorranstxtcnt) {
				$("#mtpcorrAns" + count).remove();
				mtpcorranstxtcnt--;
			}
		}else if (rowId == "mtpImgcorrAnsTxt") {
			var totDivCnt = mtpimgcorranstxtcnt;
			if (count < mtpimgcorranstxtcnt) {
				var nextDivId
				for (cnt = count; cnt < totDivCnt; cnt++) {
					nextDivId = cnt + 1;
					var nextDivVal = $("#mtpImgcorrAns" + nextDivId + " :input")
							.val();
					$("#mtpImgcorrAns" + cnt + " :input").val(nextDivVal);
				}
				$("#mtpImgcorrAns" + nextDivId).remove();
				mtpimgcorranstxtcnt--;
			} else if (count == mtpimgcorranstxtcnt) {
				$("#mtpImgcorrAns" + count).remove();
				mtpimgcorranstxtcnt--;
			}
		} else if (rowId == "mcaincAnsInp") {
			var totDivCnt = mcaincansinpcnt;
			if (count < mcaincansinpcnt) {
				var nextDivId
				for (cnt = count; cnt < totDivCnt; cnt++) {
					nextDivId = cnt + 1;
					var nextDivVal = $(
							"#" + parentDivId + " #mcaincAns" + nextDivId
									+ " :input").val();
					$("#" + parentDivId + " #mcaincAns" + cnt + " :input").val(
							nextDivVal);
				}
				$("#" + parentDivId + " #mcaincAns" + nextDivId).remove();
				$("#" + parentDivId + " #SCAWrong" + nextDivId).remove();
				mcaincansinpcnt--;
			} else if (count == mcaincansinpcnt) {
				$("#" + parentDivId + " #mcaincAns" + count).remove();
				$("#" + parentDivId + " #SCAWrong" + count).remove();
				mcaincansinpcnt--;
			}
		}else if (rowId == "mcaImgincAnsInp") {
			var totDivCnt = mcaimgincansinpcnt;
			if (count < mcaimgincansinpcnt) {
				var nextDivId
				for (cnt = count; cnt < totDivCnt; cnt++) {
					nextDivId = cnt + 1;
					var nextDivVal = $(
							"#" + parentDivId + " #mcaImgincAns" + nextDivId
									+ " :input").val();
					$("#" + parentDivId + " #mcaImgincAns" + cnt + " :input").val(
							nextDivVal);
				}
				$("#" + parentDivId + " #mcaImgincAns" + nextDivId).remove();
				mcaimgincansinpcnt--;
			} else if (count == mcaimgincansinpcnt) {
				$("#" + parentDivId + " #mcaImgincAns" + count).remove();
				mcaimgincansinpcnt--;
			}
		}else if (rowId == "scaincAnsInp") {
			var totDivCnt = scaincansinpcnt;
			if (count < scaincansinpcnt) {
				var nextDivId
				for (cnt = count; cnt < totDivCnt; cnt++) {
					nextDivId = cnt + 1;
					var nextDivVal = $(
							"#" + parentDivId + " #scaincAns" + nextDivId
									+ " :input").val();
					$("#" + parentDivId + " #scaincAns" + cnt + " :input").val(
							nextDivVal);
					console.log("SCAWrong for" +scaincansinpcnt);
				}
				$("#" + parentDivId + " #scaincAns" + nextDivId).remove();
				$("#" + parentDivId + " #SCAWrong" + nextDivId).remove();
				scaincansinpcnt--;
			} else if (count == scaincansinpcnt) {
				$("#" + parentDivId + " #scaincAns" + count).remove();
				$("#" + parentDivId + " #SCAWrong" + count).remove();
				scaincansinpcnt--;
			}
		} 
		else if (rowId == "fibincAnsInp") {
			var totDivCnt = fibincansinpcnt;
			if (count < fibincansinpcnt) {
				var nextDivId
				for (cnt = count; cnt < totDivCnt; cnt++) {
					nextDivId = cnt + 1;
					var nextDivVal = $("#fibincAns" + nextDivId + " :input")
							.val();
					$("#fibincAns" + cnt + " :input").val(nextDivVal);
				}
				$("#fibincAns" + nextDivId).remove();
				$("#IFIBWrong" + nextDivId).remove();
				fibincansinpcnt--;
			} else if (count == fibincansinpcnt) {
				$("#fibincAns" + count).remove();
				$("#IFIBWrong" + count).remove();
				fibincansinpcnt--;
			}
		} else if (rowId == "incAnsTxt") {
			var totDivCnt = mtpincanstxtcnt;
			if (count < mtpincanstxtcnt) {
				var nextDivId
				for (cnt = count; cnt < totDivCnt; cnt++) {
					nextDivId = cnt + 1;
					var nextDivVal = $("#mtpincAns" + nextDivId + " :input")
							.val();
					$("#mtpincAns" + cnt + " :input").val(nextDivVal);
				}
				$("#mtpincAns" + nextDivId).remove();
				$("#MTPIWrong" + nextDivId).remove();
				
				mtpincanstxtcnt--;
			} else if (count == mtpincanstxtcnt) {
				$("#mtpincAns" + count).remove();
				$("#MTPIWrong" + count).remove();
				mtpincanstxtcnt--;
			}
		}else if (rowId == "mtpImgincAnsTxt") {
			var totDivCnt = mtpimgincanstxtcnt;
			if (count < mtpimgincanstxtcnt) {
				var nextDivId
				for (cnt = count; cnt < totDivCnt; cnt++) {
					nextDivId = cnt + 1;
					var nextDivVal = $("#mtpImgincAns" + nextDivId + " :input")
							.val();
					$("#mtpImgincAns" + cnt + " :input").val(nextDivVal);
				}
				$("#mtpImgincAns" + nextDivId).remove();
				mtpimgincanstxtcnt--;
			} else if (count == mtpimgincanstxtcnt) {
				$("#mtpImgincAns" + count).remove();
				mtpimgincanstxtcnt--;
			}
		}
		else if (rowId == "scaimgincAnsInp") {  ///  zzz
			var totDivCnt = scaimgincansinpcnt;
			if (count < scaimgincansinpcnt) {
				var nextDivId
				for (cnt = count; cnt < totDivCnt; cnt++) {
					nextDivId = cnt + 1;
					var nextDivVal = $(
							"#" + parentDivId + " #scaImgincAns" + nextDivId
									+ " :input").val();
					$("#" + parentDivId + " #scaImgincAns" + cnt + " :input").val(
							nextDivVal);
				}
				$("#" + parentDivId + " #scaImgincAns" + nextDivId).remove();
				scaimgincansinpcnt--;
			} else if (count == scaimgincansinpcnt) {
				$("#" + parentDivId + " #scaImgincAns" + count).remove();
				scaimgincansinpcnt--;
			}
		} 
	} else {
		return false;
	}
}

function validateAnsType(activeAnsDivId) {
	 var flag = 0;

	 
	 var imgOption = activeAnsDivId.includes("Img");
	 
	 if (imgOption == true) {
		 $("div#" + activeAnsDivId + " input").each(function () {
		        if ($(this).attr('img-media') == "") {
		            flag = 1;
		        }
		    });
	}else{
		$("div#" + activeAnsDivId + " input").each(function () {
	        if ($.trim($(this).val()).length == 0) {
	            flag = 1;
	        }
	    });
	}
	    

	    $("div#" + activeAnsDivId + " textarea").each(function () {
	        if ($.trim($(this).val()).length == 0) {
	            flag = 1;
	        }
	    });

	    if (flag == 1) {
	        return false;
	    }
	    return true;
}



function saveValidation(Id) {

    errFlag = true;
    qgFlag = true;

//    var level2Id = $("#selectLevel2 option:selected").attr('id');
    
//    if(level2Id == undefined){
//    	errFlag = false;
//    	qgFlag = false;
//    	 $(".selectLevel1").removeClass("invalid-feedback"); 
//    	 $(".selectLevel1").addClass("red-color"); 
//    }
    
    var quesType = $('#quesType option:selected').attr('id');
    
    if(quesType == undefined){
    	errFlag = false;
    	qgFlag = false;
    	$(".quesType").removeClass("invalid-feedback"); 
    	 $(".quesType").addClass("red-color"); 
    }
    
    var level = $('#diffLevel option:selected').val(); // difficulty level
    
    var selected_Id = $('input[type=radio][name="solutionGroup'+Id+'"]:checked').length;
    
    
    if(level == undefined){
    	errFlag = false;
    	qgFlag = false;
    	 $(".diffLevel").removeClass("invalid-feedback"); 
    	 $(".diffLevel").addClass("red-color"); 
    }
    
    if(qgFlag == false){
    	$("#saveQuesGroupErr").removeClass("hidden");	
    }
//    if($("select option:selected").val() == "0"){
//    	alert("Please select all the fields in the question group");
//    } 
    
//   
    
    if ($.trim($("#questText" + Id).val()).length == 0) {
        errFlag = false;
    }
    else if ($("#ansType" + Id).val() == "0") {
        errFlag = false;
    }
    
//    else if (selected_Id == 0) {
//        errFlag = false;
//    }
    
    else if (selected_Id == 1) {
    	if($(".questSol").val() == ""){
    		errFlag = false;
    	}
    }
    
    else if ($("#quetSolText" + Id).val() == "") {
        errFlag = false;
    }
    
    else if ($("#defTime" + Id).val() == "") {
        errFlag = false;
    }

    if (errFlag && validateAnsType($('div #actAnsTypeBlock' + Id).children('div #new-div').children('div .activeanstype').attr('id'))) {
        return true;
    } else {
        $("#saveQuesErr" + Id).removeClass("hidden");
        return false;

    }
}




function saveModifiedValidation(Id, quesSolValidate) {

    errFlag = true;
    qgFlag = true;

//    var level2Id = $("#selectLevel2 option:selected").attr('id');
    
//    if(level2Id == undefined){
//    	errFlag = false;
//    	qgFlag = false;
//    	 $(".selectLevel1").removeClass("invalid-feedback"); 
//    	 $(".selectLevel1").addClass("red-color"); 
//    }
    
    var quesType = $('#quesType option:selected').attr('id');
    
    if(quesType == undefined){
    	errFlag = false;
    	qgFlag = false;
    	$(".quesType").removeClass("invalid-feedback"); 
    	 $(".quesType").addClass("red-color"); 
    }
    
    var level = $('#diffLevel option:selected').val(); // difficulty level
    
    var selected_Id = $('input[type=radio][name="solutionGroup'+Id+'"]:checked').length;
    
    if(level == undefined){
    	errFlag = false;
    	qgFlag = false;
    	 $(".diffLevel").removeClass("invalid-feedback"); 
    	 $(".diffLevel").addClass("red-color"); 
    }
    
    if(qgFlag == false){
    	$("#saveQuesGroupErr").removeClass("hidden");	
    }
//    if($("select option:selected").val() == "0"){
//    	alert("Please select all the fields in the question group");
//    } 
    
//   
    
    if ($.trim($("#questText" + Id).val()).length == 0) {
        errFlag = false;
    }
    
//    ()
    
    else if ($("#ansType" + Id).val() == "0") {
        errFlag = false;
    }
    
    else if ($("#quetSolText" + Id).val() == "") {
        errFlag = false;
    }
    
    else if (selected_Id == 1) {
    	if($(".questSol").attr("sol-media") == ""){
    		errFlag = false;
    	}
    }
    
//    else if (quesSolValidate == "2") {
//    	if ($("#quetSolText" + Id).val() == "") {
//    		errFlag = false;
//    	}
//    }
    
    
    
    else if ($("#defTime" + Id).val() == "") {
        errFlag = false;
    }

    if (errFlag && validateAnsType($('div #actAnsTypeBlock' + Id).children('div #new-div').children('div .activeanstype').attr('id'))) {
        return true;
    } else {
        $("#saveQuesErr" + Id).removeClass("hidden");
        return false;

    }
}




function saveQuestionGroupValidations() {
	
	var quesGrpName = $('#qgrpName').val();
	
//	var level1Id = $("#selectLevel1 option:selected").attr('id');
//	var level2Id = $("#selectLevel2 option:selected").attr('id');
//	var level3Id = $("#selectLevel3 option:selected").attr('id');
//	var level4Id = $("#selectLevel4 option:selected").attr('id');
//	var level5Id = $("#selectLevel5 option:selected").attr('id');
//	var level6Id = $("#selectLevel6 option:selected").attr('id');
	var topicId = $("#selectTopic option:selected").val();
	
	var quesType = $('#quesType option:selected').attr('id');
	
	var level = $('#diffLevel option:selected').val(); // difficulty level
	
	
	var varNo = $('#varNo').val();

//	 	 level1Id =""?$(".selectLevel1").removeClass("hidden"):$(".selectLevel1").addClass("hidden");
//		 level2Id =""?$(".selectLevel1").removeClass("hidden"):$(".selectLevel1").addClass("hidden"); 
//		 level3Id =""?$(".selectLevel1").removeClass("hidden"):$(".selectLevel1").addClass("hidden"); 
//		 level4Id =""?$(".selectLevel1").removeClass("hidden"):$(".selectLevel1").addClass("hidden"); 
//		 level5Id =""?$(".selectLevel1").removeClass("hidden"):$(".selectLevel1").addClass("hidden"); 
//		 level6Id =""?$(".selectLevel1").removeClass("hidden"):$(".selectLevel1").addClass("hidden"); 
		
	topicId =""?$(".selectTopic").removeClass("hidden"):$(".selectTopic").addClass("hidden");
		 quesType = ""?$(".quesType").removeClass("hidden"):$(".quesType").addClass("hidden");
		 quesType = undefined?$(".quesType").removeClass("hidden"):$(".quesType").addClass("hidden");
		 
		 level = ""?$(".diffLevel").removeClass("hidden"):$(".diffLevel").addClass("hidden");// difficulty level
		
		 if ( varNo ==  "") {
			 $(".varNoerrmsg").removeClass("invalid-feedback").addClass("invalid-feedback-var");
		}else{
			 $(".varNoerrmsg").removeClass("invalid-feedback-var").addClass("invalid-feedback");
		}
//		 varNo =  ""?$(".varNoerrmsg").removeClass("hidden"):$(".varNoerrmsg").addClass("invalid-feedback-var");// difficulty level
		 
		 
		 
		 if ( topicId == "" || quesType == "" || level == "" || varNo == "") {
		        return false;
		    } else {
		        return true;

		    }
}




//level 1 validation

function level1Validation() {

    errFlag = true;
    
    level1Name = $.trim($("#level1Name").val());
    level1No =  $.trim($("#level1No").val());

    
    level1Name==""?$(".level1Name").removeClass("hidden"):$(".level1Name").addClass("hidden");
    level1No==""?$(".level1No").removeClass("hidden"):$(".level1No").addClass("hidden");

    if (level1Name == "" || level1No == "") {
        return false;
    } else {
        return true;

    }
}






