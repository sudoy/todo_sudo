package todo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.utils.DBUtils;

@WebServlet("/delete.html")
public class DeleteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		// reqの内容を変数に入れる
		String id = req.getParameter("id");

		// バリデーションチェック
		List<String> errors = validate(id);
		if(errors.size() > 0){
			resp.sendRedirect("index.html");
			return;
		}

		Connection con = null;
		PreparedStatement ps = null;
		try {
			// Connectionの取得
			con = DBUtils.getConnection();

			// SQL実行
			String sql = ""
					+ "DELETE FROM todos "
					+ "WHERE id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);

			ps.executeUpdate();

			// index.htmlへ遷移
			resp.sendRedirect("index.html");

		} catch (Exception e) {
			throw new ServletException(e);

		} finally {
			// Cnnectionクローズ
			DBUtils.close(con, ps);
		}
	}

	private List<String> validate(String id) {
		List<String> errors = new ArrayList<>();

		// idの必須
		if (id == null || id.equals("")) {
			// エラーが発生
			errors.add("不正なアクセスです。");
		}

		return errors;
	}
}
