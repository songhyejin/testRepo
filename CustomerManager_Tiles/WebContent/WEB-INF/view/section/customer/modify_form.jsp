<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="${initParam.rootPath }/script/formcheck.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#modifyForm").on("submit", modifyFormCheck);
});
</script>
<h2>고객 정보 수정</h2>
<spring:hasBindErrors name="customer"/>
<form action="${initParam.rootPath}/customer/modify.do" method="post" id="modifyForm">
<input type="hidden" name="pageNo" value="${param.pageNo }">
<table border="1" style="width:500px">
	<tr>
		<th>고객 ID</th>
		<td>
			${requestScope.customer.customerId }
			<input type="hidden" name="customerId" id="customerId"
					value="${requestScope.customer.customerId }">
		</td>
	</tr>
	<tr>
		<th>패스워드</th>
		<td>
			<input type="password" id="customerPassword" name="customerPassword" size="25">
			<span class="errorMessage"><form:errors path="customer.customerPassword" delimiter=" | "/></span>
		</td>
	</tr>
	<tr>
		<th>고객 이름</th>
		<td>
			<input type="text" id="customerName" name="customerName" size="25" value="${requestScope.customer.customerName }">
			<span class="errorMessage"><form:errors path="customer.customerName" delimiter=" | "/></span>	
		</td>
	</tr>
	<tr>
		<th>고객 Email</th>
		<td>
			<input type="text" id="customerEmail" name="customerEmail" size="25" value="${requestScope.customer.customerEmail}">
			<span class="errorMessage"><form:errors path="customer.customerEmail" delimiter=" | "/></span>
		</td>
	</tr>
	<tr>
		<th>고객 마일리지</th>
		<td>
			<input type="text" id="customerMileage" name="customerMileage" size="25" value="${requestScope.customer.customerMileage }">
			<span class="errorMessage"><form:errors path="customer.customerMileage" delimiter=" | "/></span>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="수정"> <input type="reset" value="초기화">
		</td>
	</tr>
</table>
</form>
