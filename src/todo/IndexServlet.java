package todo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.beans.Todo;
import todo.utils.DBUtils;
import todo.utils.Utils;

@WebServlet("/index.html")
public class IndexServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// ログインチェック
		if(!Utils.checkLogin(req, resp)) {
			return;
		}

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBUtils.getConnection();

			String sql = ""
					+ "SELECT id, title, detail, importance, limit_date "
					+ "FROM todos "
					+ "ORDER BY id";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			List<Todo> todos = new ArrayList<>();
			while (rs.next()) {
				Todo todo = new Todo(
						rs.getInt("id"),
						rs.getString("title"),
						rs.getString("detail"),
						rs.getInt("importance"),
						Utils.date2LocalDate(rs.getDate("limit_date")));
				todos.add(todo);
			}

			req.setAttribute("todos", todos);
			getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);

		} catch (Exception e) {
			throw new ServletException(e);

		} finally {
			DBUtils.close(con, ps, rs);
		}
	}
}
