
/** Used to Redirect to other page **/
function redirectToPage(){
    	location.href = "addQuestionGroup.html";
      $('#problemDefination').modal('hide');
}


/** by pass validation **/
function removeValidation(formname,formaction){
  $("#"+formname).attr("action", formaction)
    document.getElementById(formname).submit();
}


/* return number from string */
function extractNum(string){
  var regex = /\d+/;
  var divId = string;
  var completeId = divId.match(regex);
  var Id=completeId[0];
  return Id;
}
