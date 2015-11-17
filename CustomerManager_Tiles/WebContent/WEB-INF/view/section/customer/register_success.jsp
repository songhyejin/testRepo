<%@ page contentType="text/html;charset=UTF-8" %>
고객을 등록했습니다.<br>
등록한 정보는 다음과 같습니다.<br>
<table border="1" style="width:300px">
	<tr>
		<th>고객 ID</th>
		<td>${requestScope.customer.customerId }</td>
	</tr>
	<tr>
		<th>패스워드</th>
		<td>${requestScope.customer.customerPassword }</td>
	</tr>
	<tr>
		<th>고객 이름</th>
		<td>${requestScope.customer.customerName }</td>
	</tr>
	<tr>
		<th>고객 Email</th>
		<td>${requestScope.customer.customerEmail }</td>
	</tr>
	<tr>
		<th>마일리지</th>
		<td>${requestScope.customer.customerMileage}</td>
	</tr>
</table>
