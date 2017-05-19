package bulletinBoard.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import bulletinBoard.beans.Message;
import bulletinBoard.beans.User;
import bulletinBoard.beans.UserMessage;
import bulletinBoard.service.MessageService;

@WebServlet(urlPatterns = { "/message" })
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		List<UserMessage> categorys = new MessageService().getCategory();
		request.setAttribute("categorys", categorys);

		request.getRequestDispatcher("./message.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();

		User user = (User) session.getAttribute("loginUser");

		Message message = new Message();
		message.setObject(request.getParameter("object"));
		message.setText(request.getParameter("text"));
		if(StringUtils.isEmpty(request.getParameter("category"))){
			message.setCategory(request.getParameter("selectCategory"));
		}else{
			message.setCategory(request.getParameter("category"));
		}
		message.setUserId(user.getId());

		if (isValid(request, messages) == true) {
			new MessageService().register(message);
			session.removeAttribute("message");
			session.removeAttribute("selectCategory");
			session.removeAttribute("category");
			response.sendRedirect("./");
		} else {
			session.setAttribute("message", message);
			session.setAttribute("selectCategory", request.getParameter("selectCategory"));
			session.setAttribute("category", request.getParameter("category"));
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("./message");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String object = request.getParameter("object");
		String text = request.getParameter("text");
		String category = request.getParameter("category");
		String selectCategory = request.getParameter("selectCategory");

		if (StringUtils.isEmpty(object) == true) {
			messages.add("件名を入力してください");
		}
		if (StringUtils.isEmpty(text) == true) {
			messages.add("本文を入力してください");
		}
		if (1000 < text.length()) {
			messages.add("本文は1000文字以下で入力してください");
		}
		if (StringUtils.isEmpty(category) && StringUtils.isEmpty(selectCategory)) {
			messages.add("カテゴリーの選択または追加をしてください");
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
