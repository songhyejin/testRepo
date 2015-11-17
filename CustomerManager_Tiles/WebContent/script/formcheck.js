var idDuplicated = false;


function regModFormCheck(){
	if(!$("#customerId").val()){
		$("#customerId").focus();
		alert("고객 ID는 필수 입력사항입니다.");
		return false;
	}
	if(!$("#customerPassword").val()){
		$("#customerPassword").focus();
		alert("고객 패스워드는 필수 입력사항입니다.");
		return false;
	}
	if(!$("#customerName").val()){
		$("#customerName").focus();
		alert("고객이름은 필수 입력사항입니다.");
		return false;
	}
	if(!$("#customerEmail").val()){
		$("#customerEmail").focus();
		alert("Email 주소는 필수 입력사항입니다.");
		return false;
	}
	return true;
}

function registerFormCheck(){
	if(idDuplicated){
		alert("사용할 수 없는 ID입니다.");
		return false;
	}
	return regModFormCheck();
}

function modifyFormCheck(){
	if(!regModFormCheck){
		return false;//false가 리턴 된 경우 리턴 해서 전송이 안되도록 처리한다. 
	}
	if(!$("#customerMileage").val()){
		$("#customerMileage").focus();
		alert("고객 마일리지는 필수 입력사항입니다.");
		return false;
	}
	
	if(window.isNaN($("#customerMileage").val())||window.parseInt($("#customerMileage").val())<0){
		$("#customerMileage").focus();
		alert("고객 마일리지는 양수만 입력가능합니다.");
		return false;
	}
	
}