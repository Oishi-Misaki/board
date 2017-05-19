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

import bulletinBoard.beans.Comment;
import bulletinBoard.beans.User;
import bulletinBoard.beans.UserComment;
import bulletinBoard.beans.UserMessage;
import bulletinBoard.service.CommentService;
import bulletinBoard.service.MessageService;

@WebServlet(urlPatterns = { "/index.jsp" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();
		List<UserMessage> categorys = new MessageService().getCategory();
		request.setAttribute("categorys", categorys);

		String category = request.getParameter("category");
		if(session.getAttribute("category")!=null){
			category =(String) session.getAttribute("category");
			session.removeAttribute("category");
		}
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		List<UserMessage> messages=new MessageService().getUserMessage(category, startDate, endDate);

		request.setAttribute("messages", messages);
		request.setAttribute("category", category);
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);

		List<UserComment> comments = new CommentService().getUserComment();
		request.setAttribute("comments", comments);

		request.getRequestDispatcher("./home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {

		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();


		User user = (User) session.getAttribute("loginUser");
		Comment comment = new Comment();
		comment.setUser_id(user.getId());
		comment.setText(request.getParameter("text"));
		comment.setMessage_id(Integer.parseInt(request.getParameter("message_id")));

		if (isValid(request, messages) == true) {
			new CommentService().register(comment);
			session.removeAttribute("comment");
			response.sendRedirect("./");
		} else {
			session.setAttribute("comment", comment);
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("./");
		}

	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String text = request.getParameter("text");

		if (StringUtils.isEmpty(text) == true) {
			messages.add("メッセージを入力してください");
		}
		if (500 < text.length()) {
			messages.add("500文字以下で入力してください");
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}