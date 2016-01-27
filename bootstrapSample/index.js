
var status = 0;

function toggleStatus(){
	//alert("abc");
	if (status%2==1){
		$("#red").css("visibility", "hidden");
		$("#green").css("visibility", "visible");
	}else{
		$("#red").css("visibility", "visible");
		$("#green").css("visibility", "hidden");
	}		
	status++;
}
