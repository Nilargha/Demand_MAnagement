    function uniqueId() {
        const firstItem = {
            value: "0"
        };
        /*length can be increased for lists with more items.*/
        let counter = "123456789".split('')
            .reduce((acc, curValue, curIndex, arr) => {
                const curObj = {};
                curObj.value = curValue;
                curObj.prev = acc;

                return curObj;
            }, firstItem);
        firstItem.prev = counter;

        return function () {
            let now = Date.now();
            if (typeof performance === "object" && typeof performance.now === "function") {
                now = performance.now().toString().replace('.', '');
            }
            counter = counter.prev;
            return `${now}${Math.random().toString(16).substr(2)}${counter.value}`;
        }
    }




var dialog_org = null;

document.addEventListener("DOMContentLoaded", function () {
    
    document.querySelector("#loginButton1").addEventListener("click", function () {
        validate();
    });
    
    
});

var session = null;



 
function validate() {
	
	//alert("Inside Function");
	//console.write("Inside Function");
	
	//console.write ( "Fetched uname" );
	//alert(uname);
	var pass = document.getElementById("pass").value;
	var uname = document.getElementById("uname").value;
	    $.ajax({
	        async: "true",
	        type: "POST",
	        url: "http://localhost:8087/api/login",
	        data: '{ "name": \"' + uname + '\", "pass": \"' + pass + '\"}',
	        headers: {
	            "Content-Type": "application/json"
	        }, 
	        success: function (data) {
	        	document.cookie = "name="+data;
	        	//window.location.replace("/dashboard");
	        	roleNavigate(data);
	        },
	        error: function (data) {
	            
	                $("#serverContent").html(data["responseJSON"]["message"]);
	            
	        }});
	
}

function roleNavigate(userid)
{
	$.ajax({
        async: "true",
        type: "GET",
        url: "http://localhost:8087/api/getRole",
        data:  {
        	id:userid
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


$('#example').DataTable( {
    "ajax": 'http://localhost:8087/api/getallusers',
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
 //var num=Math.floor((Math.random() * 100000) + 1);
const randomIdGenerator = uniqueId();


var num=randomIdGenerator();
 var title = document.getElementById("title").value;
 var desc = document.getElementById("styleDesc").value;
 var status="Active";
 var name;
 
 var cookieval=document.cookie;
 var cookies = cookieval.split(';');
 for (var i=0;i<1;i++)
	 {
	 name = cookies[i].split('=')[1];
	 
	 }
 
 
 $.ajax({
        async: "true",
        type: "POST",
        url: "http://localhost:8087/api/createDemand",
        data: '{ "id": \"' + num + '\", "title": \"' + title + '\", "desc": \"' + desc + '\", "status": \"' + status + '\", "iduser": \"' +  name + '\"}',
        headers: {
            "Content-Type": "application/json"
        }, 
        success: function (data) {
        	//window.location.replace("/dashboard");
        	alert(data);
        },
        error: function (data) {
            
                alert(data["responseJSON"]["message"]);
            
        }});

 // on success 
 
// window.alert("Demand Successfully created : ID : 1234");
	 
}

trigger.addEventListener("click", toggleModal);

triggerShow.addEventListener("click", toggleModalShow);
closeButton.addEventListener("click", toggleModal);
closeShowButton.addEventListener("click", toggleModalShow);
window.addEventListener("click", windowOnClick);



var globalcookie=document.cookie;

$(document).ready(function() {
    $('#table').DataTable( {
        dom: 'Bfrtip',
        ajax: {
            "type"   : "POST",
            "url"    : 'http://localhost:8087/api/ShowMyDemands',
            "data"   : {
                iduser : globalcookie
            }
            
          },
        columns: [
            { data: "id" },
            { data: "title" },
            { data: "desc" },
            { data: "Status" }
         
           
        ],
        buttons: [
            'copy', 'csv', 'excel', 'pdf', 'print'
        ]
    } );
} );