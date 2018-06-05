

$( document ).ready(function() {
	$("#submit_button").on("click", function() {
	    registerUser();
	   
	});
	

});


function registerUser(){
	var arr = { 
			id: 0,
			firstname: $('#first_input').val(), 
			lastname: $('#last_input').val(),
			email: $('#phone_input').val(),
			phone: $('#email_input').val(),
			birth: $('#date_input').val(),
			verified: false
			
		};
	console.log(JSON.stringify(arr));
$.ajax({
	method:"POST",
    url: '/LawnBuzz/v1/registerFpUser',
    contentType: "application/json",
	
	data: JSON.stringify(arr),
	dataType:'text',
    success: function(msg) {
    	var str = JSON.stringify(msg);
    	if(str.indexOf("true")>-1){
    		showSuccess("Success: "+str);
    	}else{
    		showError("Error: "+str);
    	}
    }
});
}
