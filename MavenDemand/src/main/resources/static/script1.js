var dialog_org = null;

document.addEventListener("DOMContentLoaded", function () {
    
    document.querySelector("#loginButton1").addEventListener("click", function () {
        validate();
    });
    
    
});
 
function validate() {
	
	//alert("Inside Function");
	//console.write("Inside Function");
	var uname = document.getElementById("uname").value;
	//console.write ( "Fetched uname" );
	//alert(uname);
	var pass = document.getElementById("pass").value;
		 
	    $.ajax({
	        async: "true",
	        type: "POST",
	        url: "http://localhost:8087/login",
	        data: '{ "name": \"' + uname + '\", "pass": \"' + pass + '\"}',
	        headers: {
	            "Content-Type": "application/json"
	        }, 
	        success: function (data) {
	        	window.location.replace("/dashboard");
	        },
	        error: function (data) {
	            
	                $("#serverContent").html(data["responseJSON"]["message"]);
	            
	        }});
	
}

$('#example').DataTable( {
    "ajax": 'http://localhost:8087/getallusers',
    "columnDefs": [ {
        "targets": -1,
        "data": null,
        "defaultContent": "<button>Approve</button>"
    } ]
} );


	
function openNav() {
document.getElementById("mySidenav").style.width = "350px";
}

function closeNav() {
document.getElementById("mySidenav").style.width = "0";
}

var modal = document.querySelector(".modal");
var modalShow = document.querySelector(".modalShow");
var trigger = document.querySelector(".trigger");
var triggerShow = document.querySelector(".triggerShow");
var closeButton = document.querySelector(".close-button");
var closeShowButton = document.querySelector(".closeShow-button");



function toggleModal() {

modal.classList.toggle("show-modal");
//modal.style.display = "none";
//var a = 1;
}

function toggleModalShow() {
modalShow.classList.toggle("show-modalShow");
//modal.style.display = "none";
//var a = 1;
}


function windowOnClick(event) {
if (event.target === modal) {
    toggleModal();
}
else if (event.target === modalShow)
	{
	toggleModalShow();
	}


}



function storeDemand()
{
toggleModal();
 
 // logic to store everything in DB
 var num=(101 * 100) + 1;
 var title = document.getElementById("title").value;
 var desc = document.getElementById("styleDesc").value;
 var status="Active";
 
 $.ajax({
        async: "true",
        type: "POST",
        url: "http://localhost:8087/createDemand",
        data: '{ "id": \"' + num + '\", "title": \"' + title + '\", "desc": \"' + desc + '\", "status": \"' + status + '\"}',
        headers: {
            "Content-Type": "application/json"
        }, 
        success: function (data) {
        	//window.location.replace("/dashboard");
        	alert(data);
        },
        error: function (data) {
            
                $("#serverContent").html(data["responseJSON"]["message"]);
            
        }});

 // on success 
 
// window.alert("Demand Successfully created : ID : 1234");
	 
}

trigger.addEventListener("click", toggleModal);
triggerShow.addEventListener("click", toggleModalShow);
closeButton.addEventListener("click", toggleModal);
closeShowButton.addEventListener("click", toggleModalShow);
window.addEventListener("click", windowOnClick);
