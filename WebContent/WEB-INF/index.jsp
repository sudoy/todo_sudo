<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="todo.utils.HtmlUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="include/_header.jsp" />

<jsp:include page="include/_successes.jsp" />
<jsp:include page="include/_errors.jsp" />

<table class="table">
	<tr>
		<th>#</th>
		<th>題名</th>
		<th>重要度</th>
		<th>期限</th>
	</tr>
<c:forEach var="f" items="${form}">
	<tr>
		<td>${f.id}</td>
		<td><a href="update.html?id=${f.id}">${f.title}</a></td>
		<td>${HtmlUtils.formatImportance(f.importance)}</td>
		<td>${f.limitDate}</td>
	</tr>
</c:forEach>
</table>

<a href="entry.html" class="btn btn-primary">追 加</a>


<jsp:include page="include/_footer.jsp" />