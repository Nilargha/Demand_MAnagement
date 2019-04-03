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
	var email = document.getElementById("uname").value;
	    $.ajax({
	        async: "true",
	        type: "POST",
	        url: "http://localhost:8087/api/login",
	        data: '{ "email": \"' + email + '\", "pass": \"' + pass + '\"}',
	        headers: {
	            "Content-Type": "application/json"
	        }, 
	        success: function (data) {
	        	document.cookie = "name="+data;
	        	//window.location.replace("/dashboard");
	        	roleNavigate(data); 
	        	//alert("the user name is  "+ data);
	        },
	        error: function (data) {
	        	
	        	alert(data["responseJSON"]["message"]);
	            
	               // $("#serverContent").html(data["responseJSON"]["message"]);
	            
	        }});
	
}

function roleNavigate(username)
{
	$.ajax({
        async: "true",
        type: "GET",
        url: "http://localhost:8087/api/getRole",
        data:  {
        	uname:username
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
        	else if(data == "4")
    		{
    		window.location.replace("/tpdldasboard");
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
//var modalEdit = document.querySelector(".modalEdit");
var trigger = document.querySelector(".trigger");
var triggerShow = document.querySelector(".triggerShow");
//var triggerEdit = document.querySelector(".triggerEdit");
var closeButton = document.querySelector(".close-button");
var closeShowButton = document.querySelector(".closeShow-button");
//var closeEditButton = document.querySelector(".closeEdit-button");



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

/*function toggleModalEdit() {
    modalEdit.classList.toggle("show-modalEdit");
   //modal.style.display = "none";
   //var a = 1;
}*/


function windowOnClick(event) {
if (event.target === modal) {
    toggleModal();
}
else if (event.target === modalShow)
	{
	toggleModalShow();
	}
/*else if (event.target === modalEdit)
{
toggleModalEdit();
}*/



}

function GetFormattedDate() {
    var todayTime = new Date();
    var month = todayTime .getMonth();
    var day = todayTime .getDate();
    var year =todayTime .getFullYear();
    return day + "/" + month + "/" + year;
}



$("#createDem").click(function(event){
	
	var newStr;
	
	
	 $.ajax({
	        async: "true",
	        type: "GET",
	        url: "http://localhost:8087/api/getDemId",
	        data: null,
	        headers: {
	            "Content-Type": "application/json"
	        }, 
	        success: function (data) {
	        	//window.location.replace("/dashboard");
	        	alert(" LAST DEMAND ID IS : " + data );
	        	var str = data.substring(3);
	        	var id = parseInt(str);
	        	id = id + 1;
	        	newStr = "DEM"+id;
	        	alert(newStr);
	        	
	        	createDemand(newStr);
	        	
	        	
	        },
	        error: function (data) {
	            
	                alert(data["responseJSON"]["message"]);
	            
	        }});
});

function createDemand(newStr)
	{
	toggleModal();
	var name;
	var zone = $('#zone option:selected').val();
	//var currentDate = new Date();

	
	 var requestor = $('#requestor').val();
	 
	 var entity = $("#entity").val();
	 var createDt = GetFormattedDate();
	 var projectNm = $("#projectNm").val();
	 var projectMngr = $("#projectMngr").val();
	 var title = $("#title").val();
	 var shrtDesc = $("#shrtDesc").val();
	 var lngDesc = $("#styleDesc").val();
	 var type = $("#type").val();
	 var mngdServReqd = $("#mngdServReqd").val();
	 var dvlryDt = $("#dvlryDt").val();
	 var mngdServGoLive = $("#mngdServGoLive").val();
	 var styleDesc = $("#styleDesc").val();
	 var statusID="AK";
	 var phaseID="DI";
	 var assignedTo ="";
	 var assignedTeam = "CCOE";
	 
	 var newStyleDesc = jsonEscape(styleDesc);
	
	 
	 
	 var cookieval=document.cookie;
	 var cookies = cookieval.split(';');
	 for (var i=0;i<1;i++)
		 {
		 name = cookies[i].split('=')[1];
		 
		 }
	 alert(statusID + "  "+ phaseID );
	 
	 alert ('yayyy');
	 
	 $.ajax({
	        async: "true",
	        type: "POST",
	        url: "http://localhost:8087/api/createDemand",
	        data: '{ "id": \"' + newStr + '\", "title": \"' + title + '\", "createdBy": \"' + name + '\", "statusID": \"' + statusID + '\", "phaseID": \"' +  phaseID + '\", "zoneId": \"' + zone + '\", "entity": \"' +  entity + '\", "projectName": \"' + projectNm + '\", "projectManager": \"' +  projectMngr + '\", "shortDesc": \"' + shrtDesc + '\", "longDesc": \"' +  newStyleDesc + '\", "demandType": \"' + type + '\", "managedServiceRequired": \"' +  mngdServReqd + '\", "deliveryDate": \"' +  dvlryDt + '\", "goLiveDate": \"' +  mngdServGoLive + '\", "assignedTo": \"' +  assignedTo + '\", "assignedTeam": \"' +  assignedTeam + '\"}',
	        headers: {
	            "Content-Type": "application/json"
	        }, 
	        success: function (data) {
	        	//window.location.replace("/dashboard");
	        	alert(" Demand successfully created . YOUR DEMAND ID IS : " + newStr );
	        	var file = $("#FileDesc").val();
	        	var newfile = jsonEscape(file);
	        	uploadfunction(newStr,name, newfile);
	        },
	        error: function (data) {
	            
	                alert(data["responseJSON"]["message"]);
	            
	        }});
	}


function jsonEscape(styleDesc)  {
	    return styleDesc.replace(/\n/g, " ").replace(/\r/g, "\\\\r").replace(/\t/g, "\\\\t");
	}

	 // on success 
	 
	// window.alert("Demand Successfully created : ID : 1234");
		 

	
	
	/*var your_selected_value = $('#select option:selected').val();
	$.ajax({
	  type: "POST",
	  url: "your_url",
	  data: {selected: your_selected_value},
	  success: function(data) {
	    // Stuff
	  },
	  error: function(data) {
	    // Stuff
	  }
	});*/




/*function storeDemand()
{
toggleModal();
 
 // logic to store everything in DB
 //var num=Math.floor((Math.random() * 100000) + 1);
const randomIdGenerator = uniqueId();


var num=randomIdGenerator();
 var title = document.getElementById("title").value;
 var desc = document.getElementById("styleDesc").value;
 var desc = document.getElementById("styleDesc").value;
 var desc = document.getElementById("styleDesc").value;
 var desc = document.getElementById("styleDesc").value;
 var desc = document.getElementById("styleDesc").value;
 var desc = document.getElementById("styleDesc").value;
 var desc = document.getElementById("styleDesc").value;
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
        	alert(" Demand successfully created . YOUR DEMAND ID IS : " + num );
        	uploadfunction(num);
        },
        error: function (data) {
            
                alert(data["responseJSON"]["message"]);
            
        }});

 // on success 
 
// window.alert("Demand Successfully created : ID : 1234");
	 
}
*/
function uploadfunction(id,name,newfile){
	
	var demid=id;
	
	var formData = new FormData();
	formData.append('file',document.getElementById("upload").files[0]);
	//var form = document.getElementById("upload").files[0];
//	 var formData = new FormData(form);
	 //data.append("id",demid); 
	 alert("in upload funcion"+formData);
	 $.ajax({
         type: "POST",
         enctype: 'multipart/form-data',
         url: "http://localhost:8087/api/upload/"+demid+"/"+name+"/"+newfile,
         data: formData,
         processData: false,
         contentType: false,
         cache: false,
         timeout: 600000,
         success: function (data) {

             $("#result").text(data);
             console.log("SUCCESS : ", data);
             $("#btnSubmit").prop("disabled", false);

         },
         error: function (e) {

             $("#result").text(e.responseText);
            // console.log("ERROR : ", e);
             $("#btnSubmit").prop("disabled", false);

         }
     });
	
}

trigger.addEventListener("click", toggleModal);

triggerShow.addEventListener("click", toggleModalShow);
//triggerEdit.addEventListener("click", toggleModalEdit);
closeButton.addEventListener("click", toggleModal);
closeShowButton.addEventListener("click", toggleModalShow);
//closeEditButton.addEventListener("click", toggleModalEdit);
window.addEventListener("click", windowOnClick);



var globalcookie=document.cookie;

$(document).ready(function() {
	
	
	
    $('#table').DataTable( {
        dom: 'Bfrtip',
        ajax: {
            "type"   : "POST",
            "url"    : 'http://localhost:8087/api/ShowMyDemands',
            "data"   : {
            	UserID : globalcookie
            }
            
          },
          columnDefs : [ {
          	
              "targets": -1,
              "data": null,
               
              
          } ],

        buttons: [
            'copy', 'csv', 'excel', 'pdf', 'print'
        ]
    } );
} );