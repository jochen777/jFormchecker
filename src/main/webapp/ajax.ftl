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
             // for normal html responses, the first argument to the success callback 
             // is the XMLHttpRequest object's responseText property 
          
             // if the ajaxForm method was passed an Options Object with the dataType 
             // property set to 'xml' then the first argument to the success callback 
             // is the XMLHttpRequest object's responseXML property 
          
             // if the ajaxForm method was passed an Options Object with the dataType 
             // property set to 'json' then the first argument to the success callback 
             // is the json data object returned by the server 
          
             alert('status: ' + data.message); 
         } 
 		 
        // wait for the DOM to be loaded 
        $(document).ready(function() { 
        	
        	var options = { 
        	        //target:        '#output1',   // target element(s) to be updated with server response 
        	        success:       processJson,  // post-submit callback 
        	 
        	        // other available options: 
        	        url:       'ajax_receive',         // override for form's 'action' attribute 
        	        //type:      type        // 'get' or 'post', override for form's 'method' attribute 
        	        //dataType:  null        // 'xml', 'script', or 'json' (expected server response type) 
        	        //clearForm: true        // clear all form fields after successful submit 
        	        //resetForm: true        // reset the form after successful submit 
        	 
        	        // $.ajax options can be used here too, for example: 
        	        //timeout:   3000 
        	        
        	        	dataType: 'json'
        	        
        	        
        	    }; 
        	
            // bind 'myForm' and provide a simple callback function 
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