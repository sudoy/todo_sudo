package todo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.forms.IndexForm;
import todo.service.TodoService;

@WebServlet("/index.html")
public class IndexServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// todoデータを取得
		TodoService service = new TodoService();
		List<IndexForm> form = service.findAllTodos();

		// formオブジェクトに変換してJSPへ渡す
		req.setAttribute("form", form);
		getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
	}
}
