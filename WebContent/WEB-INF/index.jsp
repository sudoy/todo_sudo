<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.sql.ResultSet" %>
<%
	ResultSet rs = (ResultSet) request.getAttribute("rs");
%>

<jsp:include page="include/_header.jsp" />

<table class="table">
	<tr>
		<th>#</th>
		<th>題名</th>
		<th>重要度</th>
		<th>期限</th>
	</tr>
<% while(rs.next()) { %>
	<tr>
		<td><%= rs.getString("id") %></td>
		<td><a href="update.html"><%= rs.getString("title") %></a></td>
		<td><%= rs.getString("importance") %></td>
		<td><%= rs.getString("limit_date") %></td>
	</tr>
<% } %>
</table>

<a href="entry.html" class="btn btn-primary">追 加</a>


<jsp:include page="include/_footer.jsp" />