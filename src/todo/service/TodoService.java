package todo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import todo.forms.EntryForm;
import todo.forms.IndexForm;
import todo.forms.UpdateForm;
import todo.utils.DBUtils;
import todo.utils.HtmlUtils;
import todo.utils.TodoException;
import todo.utils.Utils;

public class TodoService {
	/**
	 * 全てのTodoを取得する
	 * @return
	 */
	public List<IndexForm> findAllTodos() {
		List<IndexForm> list = new ArrayList<>();

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


			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String detail = rs.getString("detail");
				int importance = rs.getInt("importance");
				Date limitDate = rs.getDate("limit_date");

				IndexForm form = new IndexForm();
				form.setId(id);
				form.setTitle(title);
				form.setDetail(detail);
				form.setImportance(importance);
				form.setLimitDate(Utils.date2String(limitDate));

				list.add(form);
			}

			return list;

		} catch (Exception e) {
			throw new TodoException(e);

		} finally {
			DBUtils.close(con, ps, rs);
		}
	}

	/**
	 * DBへ登録する
	 * @param form
	 * @return
	 * @throws TodoException
	 */
	public int register(EntryForm form) throws TodoException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			// Connectionの取得
			con = DBUtils.getConnection();

			// SQL実行
			String sql = ""
					+ "INSERT INTO todos (title, detail, importance, limit_date) "
					+ "VALUES (?, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, form.getTitle());
			ps.setString(2, form.getDetail());
			ps.setString(3, form.getImportance());
			ps.setString(4, form.getLimitDate().equals("") ? null : form.getLimitDate());

			return ps.executeUpdate();

		} catch (Exception e) {
			throw new TodoException(e);

		} finally {
			// Cnnectionクローズ
			DBUtils.close(con, ps);
		}
	}

	/**
	 * Todoを1つ取得する
	 * @param form
	 * @throws TodoException
	 */
	public UpdateForm getTodo(int id) throws TodoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBUtils.getConnection();

			String sql = ""
					+ "SELECT id, title, detail, importance, limit_date "
					+ "FROM todos "
					+ "WHERE id = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if(!rs.next()) {
				return null;
			}

			UpdateForm form = new UpdateForm();
			form.setId(id);
			form.setTitle(rs.getString("title"));
			form.setDetail(rs.getString("detail"));
			form.setImportance(rs.getString("importance"));
			form.setLimitDate(HtmlUtils.formatLimitDate(rs.getDate("limit_date")));

			return form;

		} catch (Exception e) {
			throw new TodoException(e);

		} finally {
			DBUtils.close(con, ps, rs);
		}
	}

	/**
	 * Todoを更新する
	 * @param form
	 * @throws TodoException
	 */
	public void update(UpdateForm form) throws TodoException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			// Connectionの取得
			con = DBUtils.getConnection();

			// SQL実行
			String sql = ""
					+ "UPDATE todos SET "
					+ "title = ?, detail = ?, importance = ?, limit_date = ? "
					+ "WHERE id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, form.getTitle());
			ps.setString(2, form.getDetail());
			ps.setString(3, form.getImportance());
			ps.setString(4, form.getLimitDate().equals("") ? null : form.getLimitDate());
			ps.setInt(5, form.getId());

			ps.executeUpdate();

		} catch (Exception e) {
			throw new TodoException(e);

		} finally {
			// Cnnectionクローズ
			DBUtils.close(con, ps);
		}
	}

	/**
	 * Todoを削除する
	 * @param id
	 * @throws TodoException
	 */
	public void destroy(int id) throws TodoException {
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
			ps.setInt(1, id);

			ps.executeUpdate();

		} catch (Exception e) {
			throw new TodoException(e);

		} finally {
			// Cnnectionクローズ
			DBUtils.close(con, ps);
		}
	}
}
