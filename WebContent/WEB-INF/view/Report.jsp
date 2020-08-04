<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/main.css">
</head>
<h1>Report</h1>
<hr>
<form action ="Report" method="GET" id="state">
  <label for="state">State:</label><br>
 <input type="text" name="state" value="${param.state}">
<button type="submit" value="Submit">Submit</button>
</form>
<a href="http://${host}/jasper/download/pdf/reportone.pdf" class="href">Download</a>

${report}

<div class="container">

 <div class="columnTwo">
<form action ="Report" method="GET" id="previous">
 <input type="hidden" name="previous" value="${previous}">
 <input type="hidden" name="previousPushed" value="true">
<button type="submit" value="Submit">Prev</button>
</form>
</div>

 <div class="columnTwo">
<form action ="Report" method="GET" id="next">
 <input type="hidden" name="next" value="${next}">
 <input type="hidden" name="nextPushed" value="true">
<button type="submit" value="Submit">Next</button>
</form>
</div>

</div>



</html>