package todo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import todo.forms.LoginForm;
import todo.utils.DBUtils;
import todo.utils.TodoException;

public class AuthService {
	public LoginForm check(LoginForm form) {
		// 関連チェック
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBUtils.getConnection();

			String sql = "SELECT id, email, password, name "
					+ "FROM users "
					+ "WHERE email = ? AND password = MD5(?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, form.getEmail());
			ps.setString(2, form.getPassword());
			rs = ps.executeQuery();

			if (rs.next()) {
				// emailとpasswordが正しいとき

				// ログイン処理
				// （セッションにログイン情報を保存する）
				LoginForm login = new LoginForm();
				login.setId(rs.getInt("id"));
				login.setEmail(rs.getString("email"));
				login.setPassword(rs.getString("password"));
				login.setName(rs.getString("name"));

				return login;

			} else {
				return null;
			}

		} catch (Exception e) {
			throw new TodoException(e);

		} finally {
			DBUtils.close(con, ps, rs);
		}
	}
}
