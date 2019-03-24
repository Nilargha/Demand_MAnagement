function checkUser()
{
	var name;
	var cookieval=document.cookie;
	 var cookies = cookieval.split(';');
	 for (var i=0;i<1;i++)
		 {
		 name = cookies[i].split('=')[1];
		 
		 }
		if (name == null)
			{
			//alert("USER INVALID");
			window.location.replace("/login");
			
			}
	}

/* to clear all cookies */
function deleteCookie () {
	alert("deleteCookie");
	var cookies = document.cookie.split("; ");
    for (var c = 0; c < cookies.length; c++) {
        var d = window.location.hostname.split(".");
        while (d.length > 0) {
            var cookieBase = encodeURIComponent(cookies[c].split(";")[0].split("=")[0]) + '=; expires=Thu, 01-Jan-1970 00:00:01 GMT; domain=' + d.join('.') + ' ;path=';
            var p = location.pathname.split('/');
            document.cookie = cookieBase + '/';
            while (p.length > 0) {
                document.cookie = cookieBase + p.join('/');
                p.pop();
            };
            d.shift();
        }
    }
    /* to clear browser history */
    var url = window.location.href;
    window.history.go(-window.history.length);
    window.location.href = url;
    window.location.replace("/login");
}

function checkLogin()
{
	
	var name;
	var cookieval=document.cookie;
	 var cookies = cookieval.split(';');
	 for (var i=0;i<1;i++)
		 {
		 name = cookies[i].split('=')[1];
		 
		 }
		if (name != null)
			{
			
			alert("USER ALREADY LOGGED IN ! ");

			$.ajax({
		        async: "true",
		        type: "GET",
		        url: "http://localhost:8087/api/getRole",
		        data:  {
		        	id:name
		        },

		        headers: {
		            "Content-Type": "application/json"
		        }, 
		        success: function (data) {
		        	if(data == "2"){
		        		window.location.replace("/dashboard");
		        	}
		        	else if(data == "3")
		        		{
		        		window.location.replace("/approverdashboard");
		        		}
		        	
		        	
		        },
		        error: function (data) {
		            
		                $("#serverContent").html(data["responseJSON"]["message"]);
		            
		        }});


			
			}
	
	}