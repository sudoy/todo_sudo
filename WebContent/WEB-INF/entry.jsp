<%@ page contentType="text/html; charset=utf-8" %>

<jsp:include page="include/_header.jsp" />


<h4><strong>登録フォーム</strong></h4>
<hr>

<div class="row">
	<form class="form-horizontal" action="index.html" method="post">
		<div class="form-group">
			<label for="title" class="col-sm-2 control-label">題名</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="title" placeholder="題名">
			</div>
		</div>
		<div class="form-group">
			<label for="detail" class="col-sm-2 control-label">詳細</label>
			<div class="col-sm-10">
				<textarea class="form-control" id="detail" placeholder="詳細" rows="3"></textarea>
			</div>
		</div>
		<div class="form-group">
			<label for="importance3" class="col-sm-2 control-label">重要度</label>
			<div class="col-sm-10">
				<div class="radio">
					<label>
						<input type="radio" name="importance" id="importance3" value="option1" checked>
						★★★
					</label>
				</div>
				<div class="radio">
					<label>
						<input type="radio" name="importance" id="importance2" value="option2">
						★★
					</label>
				</div>
				<div class="radio disabled">
					<label>
						<input type="radio" name="importance" id="importance1" value="option3" disabled>
						★
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="limit" class="col-sm-2 control-label">期限</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="limit" placeholder="期限">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">キャンセル</button>
				<button type="submit" class="btn btn-primary">追 加</button>
			</div>
		</div>
	</form>
</div>

<jsp:include page="include/_footer.jsp" />