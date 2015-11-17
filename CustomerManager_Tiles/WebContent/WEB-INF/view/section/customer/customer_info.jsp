<%@ page contentType="text/html;charset=UTF-8" %>
<h2>조회한 고객정보</h2>
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
<p>
<a href="${initParam.rootPath}/customer/remove.do?customerId=${requestScope.customer.customerId }&pageNo=${param.pageNo}">고객정보삭제</a>
<a href="${initParam.rootPath}/customer/modifyForm.do?customerId=${requestScope.customer.customerId }&pageNo=${param.pageNo}">고객정보수정</a>




















