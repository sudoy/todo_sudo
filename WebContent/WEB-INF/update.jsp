<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="todo.utils.HtmlUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${pageContext.request.method eq 'POST'}">
	<c:set var="todo" value="${param}" />
</c:if>

<jsp:include page="include/_header.jsp" />

<h4><strong>更新フォーム</strong></h4>
<hr>

<div class="row">
	<form class="form-horizontal" action="update.html?id=${todo.id}" method="post">
		<div class="form-group">
			<label for="title" class="col-sm-2 control-label">題名</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="title" name="title" placeholder="題名" value="${todo.title}">
			</div>
		</div>
		<div class="form-group">
			<label for="detail" class="col-sm-2 control-label">詳細</label>
			<div class="col-sm-10">
				<textarea class="form-control" id="detail" name="detail" placeholder="詳細" rows="3">${todo.detail}</textarea>
			</div>
		</div>
		<div class="form-group">
			<label for="importance3" class="col-sm-2 control-label">重要度</label>
			<div class="col-sm-10">
				<div class="radio">
					<label>
						<input type="radio" name="importance" id="importance3" value="3" ${HtmlUtils.checkImportance(todo.importance, '3')}>
						★★★
					</label>
				</div>
				<div class="radio">
					<label>
						<input type="radio" name="importance" id="importance2" value="2" ${HtmlUtils.checkImportance(todo.importance, '2')}>
						★★
					</label>
				</div>
				<div class="radio">
					<label>
						<input type="radio" name="importance" id="importance1" value="1" ${HtmlUtils.checkImportance(todo.importance, '1')}>
						★
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="limit" class="col-sm-2 control-label">期限</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="limit" name="limit_date" placeholder="期限" value="${HtmlUtils.formatLimitDate(todo)}">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-8">
				<a href="index.html" class="btn btn-default">キャンセル</a>
				<button type="submit" class="btn btn-primary">更 新</button>
			</div>
			<div class="col-sm-2 text-right">
				<a href="delete.html?id=${todo.id}" class="btn btn-danger">削 除</a>
			</div>
		</div>
	</form>
</div>


<jsp:include page="include/_footer.jsp" />