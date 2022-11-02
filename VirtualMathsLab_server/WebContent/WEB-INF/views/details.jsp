<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Current System status</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style>
  .alert {border:1px silid #000;}
  h1 {font-size : 15px;}
  </style>

   <script type="text/javascript">
var TR ='<%=request.getAttribute("TR")%>';
var PD = '<%=request.getAttribute("PD")%>';
var TC = '<%=request.getAttribute("TC")%>';
var TP = '<%=request.getAttribute("TP")%>';
var TTP = '<%=request.getAttribute("TTP")%>';
</script>

</head>
<body>


<div class="container"><br><h2>Current status Details</h2><br>
 <div class="row"> 
 <div class="col-md-6 alert alert-info" >
 <h1>TOTAL NUMBER OF REGISTERED STUDENT AT VirtualMathsLab</h1>
 </div>
 <div class="col-md-3 alert alert-success">
  <h1>${TR}</h1>
 </div>
 <div class="col-md-3"></div> 
 </div>
 
 
  <div class="row border"> 
 <div class="col-md-6 alert alert-info" >
 <h1>PAYMENT DONE BY NUMBER OF STUDENTS</h1>
 </div>
 <div class="col-md-3 alert alert-success">
  <h1>${PD}</h1>
 </div>
 <div class="col-md-3"></div> 
 </div>
 
 
  <div class="row border"> 
 <div class="col-md-6 alert alert-info" >
 <h1>TOTAL TEST COMPLETED</h1>
 </div>
 <div class="col-md-3 alert alert-success">
  <h1>${TC}</h1>
 </div>
 <div class="col-md-3"></div> 
 </div>
 
 
  <div class="row border"> 
 <div class="col-md-6 alert alert-info" >
 <h1>TOTAL TEST IN PROGRESS</h1>
 </div>
 <div class="col-md-3 alert alert-success">
  <h1>${TP}</h1>
 </div>
 <div class="col-md-3"></div> 
 </div>
 
 
  <div class="row border"> 
 <div class="col-md-6 alert alert-info" >
 <h1>CURRENT TEST IN PROGRESS(In last hour period)</h1>
 </div>
 <div class="col-md-3 alert alert-success">
  <h1>${TTP}</h1>
 </div>
 <div class="col-md-3"></div> 
 </div>
 
 
 
 
</div>
<%-- <pre>
<div>TOTAL NUMBER OF REGISTERED STUDENT AT VirtualMathsLab -      ${TR}</div>
<div>PAYMENT DONE BY NUMBER OF STUDENTS -     ${PD}</div>
<div>TOTAL TEST COMPLETED -     ${TC}</div>
<div>TOTAL TEST IN PROGRESS -    ${TP}</div>
<div>CURRENT TEST IN PROGRESS(In last hour period) -  ${TTP}</div>
</pre> --%>
</body>
</html>