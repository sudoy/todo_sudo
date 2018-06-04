<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="todo.utils.HtmlUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="include/_header.jsp" />

<table class="table">
	<tr>
		<th>#</th>
		<th>題名</th>
		<th>重要度</th>
		<th>期限</th>
	</tr>
<c:forEach var="todo" items="${todos}">
	<tr>
		<td>${todo.id}</td>
		<td><a href="update.html?id=${todo.id}">${todo.title}</a></td>
		<td>${HtmlUtils.formatImportance(todo)}</td>
		<td>${HtmlUtils.formatLimitDate(todo)}</td>
	</tr>
</c:forEach>
</table>

<a href="entry.html" class="btn btn-primary">追 加</a>


<jsp:include page="include/_footer.jsp" />