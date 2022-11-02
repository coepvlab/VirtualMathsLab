(function(UF, AM, MJ) {
	
	
	UF.uploadFile = function() {
		
		document.title = "Upload File";
		
		var uploadOptHtm = "<div id='loader'></div>";
		
		
		
		uploadOptHtm += '<div class="row">'
					+ '<div class="col-xl-12 col-md-12 col-sm-12 id="uploadJavaFile>'
						uploadOptHtm += '<h1>Upload JAVA File</h1>'
							
		uploadOptHtm += '<section class="section-preview " style="padding: 0 10px">'
			+ '<div class="form-group row">'
			+ '<input type="file" class="form-control col-xl-10 col-md-10 col-sm-12 " name="uploadJavaFile" id="uploadJavaFile" accept=".xls,.xlsx" title="Upload File">'
			
						uploadOptHtm += '<button title="UPLOAD FILE" id="uploadJavaFileBtn" class="btn btn-dark col-xl-2 col-md-2 col-sm-12 justify-content-center">UPLOAD FILE</button>'
							
		uploadOptHtm += '</div></section></div>'
			
			
			+ '<div id="file_error" class="red-color"></div>'
					+ '</div>'
		
		
		$("#main-div").html('');
		$("#main-div").html(uploadOptHtm);
		
		
		$('#uploadJavaFileBtn').on("click" ,function() {
			
			var loader = '<div class="overlay" id="Loading">'
				 + '<div class="overlay__inner">'
				 + '<div class="overlay__content"><span class="spinner"></span><br/><span class="loading">LOADING....</span></div>'
				 + '</div>'
				 + '</div>'
			$("#loader").html(loader);
			
			(document.getElementById("uploadJavaFile").files.length > 0) ? isFileLinked = 1 : isFileLinked = 0;
			if (isFileLinked == 1) {
				
				var file = $('#uploadJavaFile');
	
				var filename = $.trim(file.val());
	
				var fileSize = ($('#uploadJavaFile')[0].files[0].size / 1024 / 1024);
				
				var formData = new FormData();
				formData.append('file', $('#uploadJavaFile')[0].files[0]);
					formData.append('flag', 1);
				
				if (filename != "" || filename.length != 0) {
					var valid_extensions = /(\.java)$/i;
					
					if (valid_extensions.test(filename)) {
						if (filename != null || filename != undefined){
							if(fileSize <= 5){
								$.ajax({
									url : 'utility/uploadJavaFile',
									type : 'POST',
									data : formData,
									enctype : "multipart/form-data",
//									cache: false,
								    contentType: false,
								    processData: false,
								    dataType : 'json',
								    success: function(data){
								    	$("#uploadJavaFile").val("");
								    	if(data.done == false){
								    		$("#file_error").removeClass("green-color");
								    		$("#file_error").addClass("red-color");
								    		$("#file_error").html('<h4>'+data.msg+'</h4>');
								    		 setTimeout(function(){ $("#Loading").css("display","none"); }, 1000);
								    	}else{
								    		$("#file_error").removeClass("red-color");
								    		$("#file_error").addClass("green-color");
								    		$("#file_error").html('<h4>'+data.msg+'</h4>');
								    		 setTimeout(function(){ $("#Loading").css("display","none"); }, 1000);
								    	}
								    	
								    }
								});
							}
						}
					}else{
						$("#Loading").css("display","none"); 
						$("#file_error").html("<h4> 1) Supported file formats -  '.java' </h4>");
						$("#error_image").css('display', 'block');
				// alert("File size must under 10 mb!");

			}
					
				}
			}else{
				$("#file_error").addClass("red-color");
				$("#file_error").html('<h4>Please select the java to upload..</h4>');
			}
		});
	}
	
	
	
	
	
	UF.getAllJavaFileOutput = function() {
		
		$.ajax({
			type : "GET",
			url : com.coep.test.addProblem.baseURL
					+ "reflection",
			dataType : 'json',
			contentType : 'application/json',
			success : function(data) {
				UF.renderAllFilesOutput(data.data);
			},
			error : function() {
			}

		});
		
	}
	
	
	UF.renderAllFilesOutput = function(data) {
		
//		console.log(data);
		
		var renderHtml = '';
			
		renderHtml += '<div class="overlay" id="Loading">'
		 + '<div class="overlay__inner">'
		 + '<div class="overlay__content"><span class="spinner"></span><br/><span class="loading">LOADING....</span></div>'
		 + '</div>'
		 + '</div>'
		 renderHtml +=  '<h1>PROBLEM STATEMENT DETAILS</h1>'
			 
				+ '<div class="table-responsive">'
				+ '<table id="ProbStatementData" class="table table-striped table-bordered" style="width:100%">'
				+ '<thead>' + ' <tr>'
				+ '<th width="5%" >Sr. No. </th>'
				+ '     <th width="20%">Student Name</th>'
				+ '     <th width="30%">Problem Statement </th>'
				+ '      <th width="50%">Output</th>'
//				+ '      <th width="15%">Answer Type</th>'
//				+ '      <th width="20%">Topics</th>'
//				+ '      <th width="5%" title ="Variation No">Var No</th>'
//				+'	<th width="5%">Action</th>' 
				+ '   </tr>'
				+ '</thead>'
				+ ' <tbody>'
				
				for (var i = 0; i < data.length; i++) {
					
					renderHtml += '<tr>'
					+	'<td width="5%">' + (i+1) + '</td>'
					+'<td width="20%">' + data[i].UNM + '</td>'
					+ '<td width="30%">' + data[i].QUES	+ '</td>'
					+ '<td width="50%">' + data[i].SOL	+ '</td>'
					+ '</tr>'
				}
		
		
		renderHtml += ' </tbody>'

			 +'</table>' + '</div>';
		
			 $("#main-div").html(renderHtml);
		
		$("#main-div").ready(function() {
			var table = $('#ProbStatementData').DataTable({
				"pageLength" : 100,
				// lengthChange: false,
				buttons : [ 'copy', 'excel', 'pdf' ],
			     drawCallback: function(){
			       },
			    	"ordering": false,
			    	"initComplete": function() {
			    	     
			    	     MJ.renderMathJax();
			    	     $("#Loading").css("display","none");
			  }
			}).on('search.dt', function() {
				 MJ.renderMathJax();
				 $("#Loading").css("display","none");
			});
			
		});
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
})( com.coep.test.uploadFile,
		 com.coep.test.AlertMessage, com.coep.test.mathJax);