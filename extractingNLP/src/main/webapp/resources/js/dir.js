
var userid = new URLSearchParams(window.location.search).get('userid');
var did = new URLSearchParams(window.location.search).get('did');

console.log("userid: " + userid + " did: " + did);

$(document).on('click', '#addFolder', function() {
	var dname = prompt("Please enter a name for the new directory.");
	//alert(dname);
	if(dname != null){
		var pdid;
		if(did == "root"){
		    pdid = -1;
		 }
		
		$.ajax(
			    {
			       url: "http://localhost:8080/extractingNLP/dir/add/"+pdid+"/"+dname+"/"+userid,
			       type: 'GET',
			       contentType: "application/json; charset=utf-8",
			       data: {},
			       dataType: 'text'
			    }
			 ).done((data) => {
				 alert("new directory success!");
			});
	}
});
