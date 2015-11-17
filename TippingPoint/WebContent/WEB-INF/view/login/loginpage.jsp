<%@ page contentType="text/html;charset=UTF-8"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style>
table.login {
    margin-left: auto;
    margin-right: auto;
}
</style>
<script type="text/javascript" src="/TippingPoint/script/jquery.js"></script>
<script type="text/javascript">

function loginSucces(){
	
	history.back();
	
}

$(document).ready(function loginCheck(){
	$("#loginBtn").on("click", function(){
		var param = "id" + "=" + $("#tpfId").val() + "&" +"pw" + "="+ $("#tpfPw").val();
		$.ajax({
			"url" : "/login.do", //로그인 클래스
			"type" : "POST",
			"data" : param,// ID 및 password
			"dataType" : "json",//응답 데이터 타입 text,json,jsonp,xml
			"beforeSend":function(){// 아이디
				if(!$("#tpfId").val()){
					alert("ID를 입력해 주세요");
					$("#tpfId").focus();
					return false;
				}
				if(!$("#tpfPw").val()){
					alert("비밀번호를 입력해 주세요");
					$("#tpfPw").focus();
					return false;
				}
				
			},
			"success" : function(response){//로그인이 성공했는지 확인							
				if(response=="1")
				{
					loginSuccess();
				}
				else
				{
					alert("아이디 또는 비번이 틀렸습니다. 다시 입력하세요.")
					return false;
				}	
			},
			"error" : function(){
					alert("문의하세요"); //어떻게 처리할지 확인합시다
			}

		})
	});
});	
$(document).ready(function goRegister(){
	$("#registerBtn").on("click", function(){
		location.href="/TippingPoint/tpfunder/register_form.tp"  // 페이지 이동...
	});
});

	
</script>
</head>
<body>
	<br>
	<form name="form" id = "form" action = "/session.do">
		<P align="center">
			<FONT size="6"><B>로그인 페이지</B></FONT>
		</P>
		<table class="login" border="1">
			<tr align="center">
				<td width="100">아이디</td>
				<td><input type="text" id="tpfId" name="id" size="12" maxlength="20"></td>
			</tr>

			<tr align="center">
				<td>비밀번호</td>
				<td><input type="password" id="tpfPw" name="pw" size="12" maxlength="15"></td>
			</tr>

			<tr>
				<td colspan="2" align="center" height="30">
					<input type="button" value="로그인" id="loginBtn"> 
					<input type="button" value="회원 가입" id="registerBtn">
					<input type="button" value="비번찾기" id="passwordCheckBtn">
				</td>
			</tr>
		</table>
	</form> 
</body>
</html>