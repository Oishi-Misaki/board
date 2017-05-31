package bulletinBoard.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bulletinBoard.beans.BranchPostUser;
import bulletinBoard.beans.User;
import bulletinBoard.service.ManageService;
import bulletinBoard.service.UserService;

@WebServlet(urlPatterns = { "/manage" })
public class ManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");

		List<BranchPostUser> users = new ManageService().getBranchPostUserList();
		request.setAttribute("users", users);
		request.getRequestDispatcher("./manage.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

			User user = new User();
			user.setId(Integer.parseInt(request.getParameter("user_id")));
			user.setCan_use(Integer.parseInt(request.getParameter("can_use")));

			new UserService().updateFlag(user);
			response.sendRedirect("./manage");

	}
}
