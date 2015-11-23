<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Formchecker</title>
	
	    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.js"></script> 
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	
	<!-- Optional theme -->
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
	
	<!-- Latest compiled and minified JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
	<!--[if lt IE 9]>
	      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	    <![endif]-->
	    
 		<script src="//oss.maxcdn.com/jquery.form/3.50/jquery.form.min.js"></script>
 		
 		
 		 <script> 
 		// post-submit callback 
         function processJson(data)  { 
        	 highlightErrors(data.data);
        	 setOkFields(data.okdata);
          
             if (data.status == "success") {
             	alert('ok');
             } 
             console.log(data);
         } 
 		 
 		function highlightErrors(data) {
 			for (var elemName in data) {
 				var elemDiv = $('#form_'+elemName).parent().parent();
 				resetCSSField(elemDiv);
 				elemDiv.addClass( "has-error");
 			}
 		}
 		function setOkFields(data) {
 			for (var elemName in data) {
 				var elemDiv = $('#form_'+elemName).parent().parent();
 				resetCSSField(elemDiv);
 				elemDiv.addClass( "has-success");
 			}
 		}
 		function resetCSSField(field) {
 			field.removeClass("has-error");
 			field.removeClass("has-success");
 		}
 		
        // wait for the DOM to be loaded 
        $(document).ready(function() { 
        	
        	var options = { 
        	        success:       processJson,  // post-submit callback 
        	 
        	        url:       'ajax_receive',         // override for form's 'action' attribute 
        	        dataType: 'json'
        	    }; 
        	
            $('#form_id').ajaxForm(options); 
            
       
        }); 
        
        
    </script> 
</head>
<body>



<div style="width:500px; margin-left:50px">
<h1>Ajax Controller</h1>
<p>Lorem ipsum...</p>
${fc.completeForm}

</div>
</body>
</html>