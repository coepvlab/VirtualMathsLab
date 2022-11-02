(function(AH, ST,AP) {

ST.startTestNavFunction = function() {
	var navTestContenthtm = '';
	navTestContenthtm +='<div class="container-fluid base-padding">'
						+'<div class="navbar-header r4_5-padding">'
						+' <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">'
						+'<span class="sr-only">Toggle navigation</span>'
						+' <span class="icon-bar"></span>'
						+' <span class="icon-bar"></span>'
						+' <span class="icon-bar"></span>'
						+'</button>'
						+'<a class="navbar-brand logo-atnavbar-padding" href="#"><img class="img-responsive" src="resource/images/IntegreT_logo.png"></a>'
						+'<a class="navbar-brand first-text-after-logo " href="#">Integra T<br>OLYMPIAD</a>'
						+'</div>'
						+'<div id="navbar" class="navbar-collapse collapse height-4">'
						+'<ul class="nav navbar-nav navbar-left start-end-session-ul">'
						+'<li class="start-end-session-li">'
						+'<b>Session Start</b>'
						+' <span class="l2-padding"><h4 class="accent-blue">12:00 PM</h4></span>'
						+'</li>'
						+'<br>'
						+' <li class="start-end-session-li">'
						+'   <b>Session End</b>'
						+'  <span class="l3-padding"><h4 class="accent-blue">1:30 PM</h4></span>'
						+' </li>'
						+'</ul>'
						+'<ul class="nav navbar-nav navbar-left session-watch-ul">'
						+' <li>'
						+'<span class="icon-clock white-color fontsize-2em"></span>'
						+' <h1 class="white-color time-counter"></h1>'
						+'<h5 class="white-color minutes-rem">Minutes <br>remaining </h5>'
						+'</li>'
						+'</ul>   '             
						+'<div class="pull-right hidden-xs hidden-sm profile-descri-outer-block t2-padding">'
						+' <h3>Hi Johnathan</h3> '                  
						+'</div>'
						+'</div>'
						+'</div>';
		
			$("#navContent").html('');
			$("#navContent").html(navTestContenthtm);
			
		///// Function to start timer when exam start
		    startTimer = function (duration, display) {
				var timer = duration, minutes, seconds;
				setInterval(function () {
					minutes = parseInt(timer / 60, 10);
					seconds = parseInt(timer % 60, 10);
		
					minutes = minutes < 10 ? "0" + minutes : minutes;
					seconds = seconds < 10 ? "0" + seconds : seconds;
		
					display.textContent = minutes + ":" + seconds;
		
					if (--timer < 0) {
						timer = 0;
						showToast.show('Your section time is up.', true);
					}
				}, 1000);
			};	
	};
	
	
	
	ST.testActualQuesArea = function() {
			
	var testQueshtm = '';
	testQueshtm += ' <div clss="row">'
        			+ ' <h1 class="secondary-text-color">GROUP <span id="currQuesGrpId">Q1</span></h1>'
        			+ '<h2 class="prim-text-black-color t15-margin">Reference</h2>'
        			+ '<p class="text-align-justify ref-text">'
        			+ ' Sir Isaac Newton, held the theory that light was made up of tiny particles. In 1678, Dutch physicist, Christiaan Huygens, believed that light was made up of waves vibrating up'
        			+ 'and down perpendicular to the direction of  the light travels, and therefore formulated a way of visualising wave propagation. This became known as \'Huygens\' Principle'
        			+ ' Huygens theory was the successful theory of light  wave motion in three dimensions. Huygen, suggested that light wave peaks form surfaces like the layers of an onion.'
        			+ ' </p>'
        			+ '</div>'
        			+ '<hr />'
        			+ '<div clss="row">'
        			+ '<div id="quesSection" class="col-sm-12 col-md-12 l0-r0-padding">'
        			+ '  <div class="col-sm-3 l0-r0-padding t1-b1-padding">'
        			+ '     <h2 class="prim-text-black-color">Question #<span id="currQGrp">Q1.1</span></h2>'
        			+ ' </div>'
        			+ ' <div class="col-sm-9 l30-r0-padding t1-b1-padding">'
        			+ '     <h3>'
        			+ '         <span class="icon-flag flag-glyph pointer" onclick="tagThisQues(\'currQGrp\')"></span>'
        			+ '         <span data-toggle="tooltip" data-placement="right" class="tooltip-css" title="Tagged question to be answered later">'
        			+ '          <h3 class="cust-clr1 pointer" onclick="tagThisQues(\'currQGrp\')">Tag question</h3>'
        			+ '      </span>'
        			+ '    </h3>'
        			+ ' </div>'
        			+ '  <div class="col-sm-9 col-sm-offset-3 l30-r0-padding custom-height">'
        			+ '     <h1 id="ques" class="secondary-text-color ">'
        			+ '        Which of the following are properties of lightwaves ?'
        			+ ' </h1>'
        			+ '   <h3 id="options" class="display-block t2-padding">'
        			+ '       <span class="col-sm-6 l0-r0-padding">'
        			+ '           <input type="checkbox" name="" value="">Frequency'
        			+ '       </span>'
        			+ '       <span class="col-sm-6">'
        			+ '           <input type="checkbox" name="" value="">Wavelength'
        			+ '      </span>'
        			+ '      <br><br>'
        			+ '      <span class="col-sm-6 l0-r0-padding">'
        			+ '         <input type="checkbox" name="" value="">Weight'
        			+ '     </span>'
        			+ '     <span class="col-sm-6">'
        			+ '         <input type="checkbox" name="" value="">Viscosity'
        			+ '     </span>'
        			+ '   </h3>'
        			+ ' </div>'
        			+ ' </div>'
        			+ ' </div>'
        			+ ' <div clss="row">'
        			+ ' <div class="col-sm-8 col-sm-offset-3 t8-margin l30-r0-padding">'
        			+ '   <span class="col-sm-6 h3-cls secondary-text-color">'
        			+ '      <span class="icon-left-navigation prev-quest"></span>'
        			+ '      Previous<br> Question'
        			+ '  </span>'
        			+ '  <span class="pull-right h3-cls col-sm-6 secondary-text-color text-align-right ">'
        			+ '      Next<span class="icon-right-navigation next-quest"></span>'
        			+ '      <br> Question'
        			+ '   </span>'
        			+ ' </div>'
        			+ '</div>';
	
	$("#testQuesArea").html('');
	$("#testQuesArea").html(testQueshtm);
	
	
	 var tagged = 0;
	 tagThisQues = function (currQGrp) {		 
		 var thisQues = $("#currQGrp").text();
		 thisQues = thisQues.replace('.', '_');
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
	
	};
		
		
	ST.quesListpane = function () {
		var quesListHtm = '';
		quesListHtm +='<div class="col-sm-12 custom-bg">'
            		+ '<span id="sectionName"><h1 class="problem-summary-label ">APPLIED PHYSICS</h1></span>'
            		+ '</div>'

            		
        for(var qu=1;qu<=10;qu++){
        	 if(qu == 1){
	        	quesListHtm += '  <div class="col-sm-12 curr-quest sect-quest pointer" onclick="switchtoThisQues(this,\'qGrp'+qu+'\',\'qTxt'+qu+'\')">'
	        	 }else{
	            quesListHtm += '  <div class="col-sm-12 sect-quest pointer" onclick="switchtoThisQues(this,\'qGrp'+qu+'\',\'qTxt'+qu+'\')">'	 
	        	 }
        quesListHtm += '    <div class="col-sm-5">'
            		+ '         <h3 id="qGrp'+qu+'">Q'+qu+'.1</h3>'
            		+ '       </div>'
            		+ '        <div class="col-sm-5" id="Q'+qu+'1">'
            		+ '            <h3 class="red-color" id="qTxt'+qu+'">Unanswered</h3>'
            		+ '        </div>'
            		+ '<div class="col-sm-2 flag-div">'
                    + '<span id="flag_spanQ'+qu+'_1" class=""></span>'
                    + '</div>'
            		+ '</div>'
        
        }
		
//					<div class="col-sm-12 sect-quest pointer " onclick="switchtoThisQues(this,'qGrp1','qTxt1')">
//			        <div class="col-sm-5">
//			            <h3 id="qGrp1">Q1.1</h3>
//			        </div>
//			        <div class="col-sm-5" id="Q1_1">
//			            <h3 class="green-color" id="qTxt1">Answered</h3>
//			        </div>
//			        </div>
		
		
		
		quesListHtm += '   </div>'
            		+ '   <div class="col-sm-10 col-sm-offset-1 l30-r0-padding">'
            		+ ' <button title="Next Section" id="nextSectBtn" class="let-space btn modify-pbm-btn">NEXT SECTION</button>'
            		+ '</div>';
		
	
		
	$(".test-sect").html('');
	$(".test-sect").html(quesListHtm);
		
		var i = 1;
	    switchtoThisQues = function (currQRow, qGrp, qTxt) {
	
	        $(".pointer").removeClass("curr-quest");
	        $(".prim-blue").removeClass("prim-blue");
	        //make current question row active
	        $(currQRow).addClass("curr-quest");
	        //change quest group number to current clicked quest
	        $("#currQGrp").text($("#" + qGrp).text());
	        //change color of current clicked quest
	        $("#" + qGrp).addClass("prim-blue");
	        //change quest text to current clicked quest
	        $("#currQTxt").text($("#" + qTxt).text());
	        //Update current question group id
	        var currQGrp = $("#" + qGrp).text().split('.');
	        $("#currQuesGrpId").text(currQGrp[0]);
	
	        var mcaOptions = "<span class=\"col-sm-6 l0-r0-padding\">" +
	            "<input class=\"checkboxType\" type=\"checkbox\" id=\"\" name=\"option1\" value=\"Frequency\">Frequency" +
	        "</span>" +
	        "<span class=\"col-sm-6\">" +
	            "<input class=\"checkboxType\" type=\"checkbox\" id=\"\" name=\"option1\" value=\"Wavelength\">Wavelength" +
	        "</span>" +
	        "<br><br>" +
	        "<span class=\"col-sm-6 l0-r0-padding\">" +
	            "<input class=\"checkboxType\" type=\"checkbox\" id=\"\" name=\"option3\" value=\"Weight\">Weight" +
	        "</span>" +
	        "<span class=\"col-sm-6\">" +
	            "<input class=\"checkboxType\" type=\"checkbox\" id=\"\" name=\"option4\" value=\"Viscosity\">Viscosity" +
	        "</span>";
	
	        var truefalseOptions = "<span class=\"col-sm-6 l0-r0-padding\">" +
	            "<input  class=\"radioType\" type=\"radio\" id=\"\" name=\"option1\" value=\"Frequency\">Frequency" +
	        "</span>" +
	        "<span class=\"col-sm-6\">" +
	            "<input  class=\"radioType\" type=\"radio\" id=\"\" name=\"option1\" value=\"Wavelength\">Wavelength" +
	        "</span>";
	
	        var fibOptions = "<span class=\"col-sm-6 l0-r0-padding\">" +
	            "<textarea type=\"\" id=\"\" name=\"option1\" class=\"form-control textAreaType\" value=\"\" placeholder=\"Enter your answer here\"></textarea>" +
	        "</span>";
	
	        var mtpOptions ="<span class=\"col-sm-6 l0-r0-padding\">" +
	            "<input  class=\"radioType\" type=\"radio\" id=\"\" name=\"option1\" value=\"hertz (Hz)\">hertz (Hz)" +
	        "</span>" +
	        "<span class=\"col-sm-6\">" +
	            "<input  class=\"radioType\" type=\"radio\" id=\"\" name=\"option1\" value=\"nanometer (nm)\">nanometer (nm)" +
	        "</span>" +
	        "<span class=\"col-sm-6 l0-r0-padding t1-padding\">" +
	            "<input  class=\"radioType\" type=\"radio\" id=\"\" name=\"option1\" value=\"meter\">meter" +
	        "</span>" +
	        "<span class=\"col-sm-6 t1-padding\">" +
	            "<input  class=\"radioType\" type=\"radio\" id=\"\" name=\"option1\" value=\"newton\">newton" +
	        "</span>";
	
	
	        if (i == 0 || i == 4 || i == 8 || i == 12) {
	            document.getElementById("options").innerHTML = truefalseOptions;
	        } else if (i == 1 || i == 5 || i == 9 || i == 13) {
	            document.getElementById("options").innerHTML = fibOptions;
	        } else if (i == 2 || i == 6 || i == 10 || i == 14) {
	            document.getElementById("options").innerHTML = mtpOptions;
	        }else{
	            document.getElementById("options").innerHTML = mcaOptions;
	        }
	        i++;
	        if (i > 15) {
	            i = 1;
	        }
	        
	      //function to remove flag from question
	        removeFlag = function () {
				var thisQues = $("#currQGrp").text();
				thisQues = thisQues.replace('.', '_');
					if ($("#flag_span" + thisQues).hasClass('icon-flag')) {						
						$("#flag_span" + thisQues).removeClass('icon-flag flag-glyph');	
				}			
			}
			
			$(".checkboxType").change(function(){				
				if($("input.checkboxType:checked").length > 0){
					removeFlag();		//To remove flag once answer is checked
					$("#"+qTxt).removeClass('red-color').addClass('green-color').text('Answered');
				}else
					$("#"+qTxt).removeClass('green-color').addClass('red-color').text('Unanswered');
			});
	
			$(".radioType").change(function(){		
				if($("input.radioType:checked").length > 0){
					removeFlag();		//To remove flag once answer is checked
					$("#"+qTxt).removeClass('red-color').addClass('green-color').text('Answered');
				}else
					$("#"+qTxt).removeClass('green-color').addClass('red-color').text('Unanswered');
			});	
	
			$(".textAreaType").change(function(){
				if (!$.trim($(".textAreaType").val())) {
					$("#"+qTxt).removeClass('green-color').addClass('red-color').text('Unanswered');
				}else{
					removeFlag();		//To remove flag once answer is checked
					$("#"+qTxt).removeClass('red-color').addClass('green-color').text('Answered');
				}
			});
	    }
	};	
		
		
	//Window violation message, copy paste prevention and right click prevention code	
	 getYesButtonOnDialog = function() {
			var text = '<button class="btn btn-success" type="button" id="cfYes">OK</button>';
			return text;
		};
	    
	    clearConfirmDialogContent = function () {    	
	    		$('#cModalLabel').html('');
	    		$('#cdlgBody').html('');
	    		$('#cdlgFoot').html('');
	    		$('#cdialog').modal('hide');
	    };
	        
	    var count = 0;
	    window.addEventListener('blur', function() {
	    	count++;
	    	if(count<=3){
	    		
	    		var htmlText = '';
	    	
	    		htmlText += 'During the test, you are not allowed to go outside the Test Window.'
	    		+'<br/> Your test will be discontinued if you try to leave the Test Window again.';

	    		$('#cModalLabel').html('Warning');
	    		$('#cdlgBody').html(htmlText);
	    		$('#cdlgFoot').html(getYesButtonOnDialog());
	    		$('#cdialog').modal({
	    			show : true
	    		});

	    		$('#cfYes').bind('click', function() {
	    			clearConfirmDialogContent();
	    		});
	    	}else{
	   			window.close();
	    	}
	    });	
	
	
	    $(document).on("contextmenu",function(e){       
	        return false;
	    }); 
	    
	    $(document).on("copy paste cut",function(e){  
	    	return false;
	    });
	
})(com.coep.test.ajaxHandler,com.coep.test.student,com.coep.test.addProblem);