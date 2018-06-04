<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="todo.utils.HtmlUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="include/_header.jsp" />

<h4><strong>更新フォーム</strong></h4>
<hr>

<div class="row">
	<form class="form-horizontal" action="update.html?id=${todo.id}" method="post">
		<div class="form-group">
			<label for="title" class="col-sm-2 control-label">題名</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="title" name="title" placeholder="題名" value="${param.title != null ? param.title : todo.title}">
			</div>
		</div>
		<div class="form-group">
			<label for="detail" class="col-sm-2 control-label">詳細</label>
			<div class="col-sm-10">
				<textarea class="form-control" id="detail" name="detail" placeholder="詳細" rows="3">${param.detail != null ? param.detail : todo.detail}</textarea>
			</div>
		</div>
		<div class="form-group">
			<label for="importance3" class="col-sm-2 control-label">重要度</label>
			<div class="col-sm-10">
				<div class="radio">
					<label>
						<input type="radio" name="importance" id="importance3" value="3" ${HtmlUtils.checkImportance(param.importance != null ? param.importance : todo.importance, '3')}>
						★★★
					</label>
				</div>
				<div class="radio">
					<label>
						<input type="radio" name="importance" id="importance2" value="2" ${HtmlUtils.checkImportance(param.importance != null ? param.importance : todo.importance, '2')}>
						★★
					</label>
				</div>
				<div class="radio">
					<label>
						<input type="radio" name="importance" id="importance1" value="1" ${HtmlUtils.checkImportance(param.importance != null ? param.importance : todo.importance, '1')}>
						★
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="limit" class="col-sm-2 control-label">期限</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="limit" name="limit_date" placeholder="期限" value="${param.limit_date != null ? param.limit_date : HtmlUtils.formatLimitDate(todo)}">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-8">
				<a href="index.html" class="btn btn-default">キャンセル</a>
				<button type="submit" class="btn btn-primary">更 新</button>
			</div>
			<div class="col-sm-2 text-right">
				<button type="submit" class="btn btn-danger">削 除</button>
			</div>
		</div>
	</form>
</div>


<jsp:include page="include/_footer.jsp" />