<%@ page contentType="text/html; charset=utf-8" %>

<jsp:include page="include/_header.jsp" />

<table class="table">
	<tr>
		<th>#</th>
		<th>題名</th>
		<th>重要度</th>
		<th>期限</th>
	</tr>
	<tr>
		<td>1</td>
		<td><a href="update.html">テストテスト</a></td>
		<td>★★★</td>
		<td>2015/06/20</td>
	</tr>
	<tr>
		<td>2</td>
		<td><a href="update.html">テストテスト</a></td>
		<td>★</td>
		<td>2015/06/22</td>
	</tr>
	<tr>
		<td>3</td>
		<td><a href="update.html">テストテスト</a></td>
		<td>★★★</td>
		<td>2015/06/20</td>
	</tr>
	<tr>
		<td>4</td>
		<td><a href="update.html">テストテスト</a></td>
		<td>★★</td>
		<td></td>
	</tr>
</table>

<a href="entry.html" class="btn btn-primary">追 加</a>


<jsp:include page="include/_footer.jsp" />