var idDuplicated = false;


function regModFormCheck(){
	if(!$("#tpfId").val()){
		$("#tpfId").focus();
		alert("고객 ID는 필수 입력사항입니다.");
		return false;
	}
	if(!$("#tpfPassword").val()){
		$("#tpfPassword").focus();
		alert("고객 패스워드는 필수 입력사항입니다.");
		return false;
	}
	if(!$("#tpfName").val()){
		$("#tpfName").focus();
		alert("고객이름은 필수 입력사항입니다.");
		return false;
	}
	if(!$("#tpfEmail").val()){
		$("#tpfEmail").focus();
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
