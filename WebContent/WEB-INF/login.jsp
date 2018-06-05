<%@ page contentType="text/html; charset=utf-8" %>

<jsp:include page="include/_header.jsp" />

</style>
<div class="row">
	<div class="col-lg-12">
		<div class="bs-component">
			<div class="modal">
				<div class="modal-dialog">
					<div class="modal-content">
						<form class="form-horizontal" action="login.html" method="post">
							<div class="modal-header">
								<h4 class="modal-title">ログイン</h4>
							</div>
							<div class="modal-body">
								<jsp:include page="include/_successes.jsp" />
								<jsp:include page="include/_errors.jsp" />

								<div class="form-group">
									<label for="title" class="col-md-4 control-label">メールアドレス</label>
									<div class="col-md-7">
										<input type="text" class="form-control" name="email" placeholder="メールアドレス" value="${param.email}">
									</div>
								</div>
								<div class="form-group">
									<label for="detail" class="col-md-4 control-label">パスワード</label>
									<div class="col-md-7">
										<input type="password" class="form-control" name="password" placeholder="パスワード" value="">
									</div>
								</div>


							</div>
							<div class="modal-footer">
								<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-off"></span> ログイン</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="include/_footer.jsp" />