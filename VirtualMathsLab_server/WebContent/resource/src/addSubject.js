(function(AH, AS) {
	
	AS.addSubject = function() {
		
		document.title = "Add Subject";
		var htm = ''
		htm +=  '<div class="container-fluid">'
			+ '<div class="row">'
			+ '<div id="subject_content" class="col-sm-12 col-md-12 col-lg-12 col-xl-12">'
			+ '<h1>ADD SUBJECT</h1>'
			+ '<form id="SubjectDataForm" novalidate>'
			+'<div class="row">'
			
			+ '<div class="form-group col-xl-8 col-md-8 col-sm-12">'
			+ '<label class="col-xl-12 col-md-12 col-sm-12" for="sub1Name">Enter Subject Name :</label>'
			+ '<input type="text" class="form-control  noSpace" id= "subName" placeholder="Enter Subject Name" name="subName"  required>'
			+ '<div class="invalid-feedback">Please Subject Name.</div>'
			+ '</div>'
			+'<div class="col-xl-4 col-md-4 col-sm-12" style="margin-top: 34px;">'
			+ '<button id= "subBtn" type="submit" class="btn btn-primary ">Submit</button>'
			+'</div>'
			
			+'</div>'
			+ '</form>'

			+ '</div>'
			+ '</div>' // subject_content close
			+ '</div>'// main row close
			+ '</div>' // container close
			
			$("#main-div").html(htm);
		
		$('.noSpace').keyup(function() {
			 this.value = this.value.replace(/\s/g,'');
			});

		
		$("#subBtn").click(function(e) {
			
			var form = document.getElementById('SubjectDataForm');
			form.addEventListener('submit', function(e) {
				if (form.checkValidity() === false) {
					e.preventDefault();
					e.stopPropagation();
				}
				form.classList.add('was-validated');
			}, false);

			if (form.checkValidity() === true) {

				AS.craeteSubject();
				e.preventDefault();

			}

		});
		
		
		AS.craeteSubject = function() {
			
			subName = $("#subName").val();
			
			var subjectJson = {
					"subName" : subName,
				};

			$.ajax({
				type : "POST",
				url : com.coep.test.addProblem.baseURL
						+ "subject/api/create",
				 data : JSON.stringify(subjectJson),
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
	
})(com.coep.test.ajaxHandler, com.coep.test.addSubject);
