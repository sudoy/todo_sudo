<%@ page import="java.sql.*,javax.naming.*,javax.sql.*"
    contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html">
<html>
    <head>
    <meta charset="UTF-8">
        <title>データベース接続</title>
    </head>
    <body>
<%
	// context.xmlのリソースへアクセス
    Context initContext = new InitialContext();
    Context envContext  = (Context)initContext.lookup("java:/comp/env");
    DataSource ds = (DataSource)envContext.lookup("todolist_sudo");

    // コネクションプールからコネクションを１つもらう
    Connection con = ds.getConnection();

    // もにょもにょ

    // コネクションを返す
    con.close();

%>データベースの接続に成功
    </body>
</html>
