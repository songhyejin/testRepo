<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
  
  <script>
  $(function() {
    $( "#datepicker" ).datepicker({
      changeMonth: true,
      changeYear: true,
      dateFormat : "yy-mm-dd",
      yearRange : "1900:c"
    });
  });
  </script>
  
  <script>
    function button() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = ''; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    fullAddr = data.roadAddress;

                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    fullAddr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
                if(data.userSelectedType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('tpfZipcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('tpfAddress').value = fullAddr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById('tpfAddreesD').focus();
            }
        }).open();
    }
</script>

<style type="text/css">
table{
	width: 400px;
}
table, td{
	border: 1px solid black;
	border-collapse: collapse;
	padding: 2px;
}
table.register {
    margin-left: auto;
    margin-right: auto;
}
</style>
</head>
<body>
<form action="/TippingPoint/register_form.tp" method="post">
	<table class="register">
		<tr>
			<td width="100px">ID</td>
			<td><input type="text" name="tpfId" style="width:150px; height:15px;"></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="tpfName" style="width:150px; height:15px;"></td>
		</tr>
		<tr>
			<td>패스워드</td>
			<td><input type="password" name="tpfPassword" style="width:150px; height:15px;"></td>
		</tr>
		<tr>
			<td>생년월일</td>
			<td>
				<input type="text" id="datepicker">
			</td>
		</tr>
		<tr>
			<td>성별</td>
			<td>
			<label>남성 - <input type="radio" name="tpfGender" value="남성"></label>
			여성 - <input type="radio" name="tpfGender" value="여성">
			</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="tpfEmail" style="width:200px; height:15px;"></td>
		</tr>
		<tr>
			<td>우편번호</td>
			<td><input type="text" id="tpfZipcode" placeholder="우편번호" style="width:50px; height:15px;"> <input type="button" onclick="button()" value="우편번호 찾기"></td>
		</tr>
		<tr>
			<td>주소</td>
			<td><input type="text" id="tpfAddress" placeholder="주소" style="width:200px; height:15px;"></td>
		</tr>
		<tr>
			<td>상세주소</td>
			<td><input type="text" id="tpfAddreesD" placeholder="상세주소" style="width:200px; height:15px;"></td>
		</tr>
		<tr>
			<td>휴대폰번호</td>
			<td>
				<select>
					<option>010</option>
					<option>011</option>
					<option>016</option>
					<option>018</option>
					<option>019</option>
				</select>
				-
				<input type="text" name="tpfPhoneNum1" style="width:50px; height:15px;"> - <input type="text" name="tpfPhoneNum2" style="width:50px; height:15px;">
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="등록">
			</td>
		</tr>
	</table>
</form>
</body>
</html>