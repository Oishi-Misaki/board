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

import bulletinBoard.beans.Branch;
import bulletinBoard.beans.Post;
import bulletinBoard.beans.User;
import bulletinBoard.service.BranchService;
import bulletinBoard.service.ManageService;
import bulletinBoard.service.PostService;
import bulletinBoard.service.UserService;

@WebServlet(urlPatterns = { "/edit" })
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();
		if(request.getParameter("user_id") != null){
			session.setAttribute("user_id",request.getParameter("user_id"));
		}
		String user_idSt = session.getAttribute("user_id").toString();
		int user_id = new Integer(user_idSt);
		User editUser = new UserService().getEditUserList(user_id);
		session.setAttribute("login_id", editUser.getLogin_id());
		request.setAttribute("editUser", editUser);
		List<Branch> branches = new BranchService().getBranchList();
		request.setAttribute("branches", branches);
		List<Post> posts = new PostService().getPostList();
		request.setAttribute("posts", posts);

		request.getRequestDispatcher("./edit.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();
		if (isValid(request, messages) == true) {

			User user = new User();
			String user_idSt = session.getAttribute("user_id").toString();
			int user_id = new Integer(user_idSt);
			user.setId(user_id);
			user.setLogin_id(request.getParameter("login_id"));
			user.setPassword(request.getParameter("password1"));
			user.setName(request.getParameter("name"));
			user.setPost_id(Integer.parseInt(request.getParameter("post_id")));
			user.setBranch_id(Integer.parseInt(request.getParameter("branch_id")));
			new UserService().updateUserInfor(user);
			session.removeAttribute("user_id");
			session.removeAttribute("login_id");
			response.sendRedirect("./manage");
		} else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("./edit");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String login_id = request.getParameter("login_id");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String name = request.getParameter("name");
		int post_id = Integer.parseInt(request.getParameter("post_id"));
		int branch_id = Integer.parseInt(request.getParameter("branch_id"));

		HttpSession session = request.getSession();
		List<User> users = new ManageService().getUserList();

		if (StringUtils.isEmpty(login_id) == true) {
			messages.add("ログインIDを入力してください");
		}
		for(User user:users){
			int i=0;
			if(user.getLogin_id().equals(login_id)){
				i++;
			}
			if(login_id.equals(session.getAttribute("login_id"))){
				i--;
			}
			if(i == 1){
				messages.add("ログインIDがすでに存在します");
			}
		}
		if (StringUtils.isEmpty(login_id) == true) {
			messages.add("ログインIDを入力してください");
		}else if(!login_id.matches("\\w{6,20}")){
			messages.add("ログインIDを半角英数字（6-20文字）で入力してください");
		}
		if (!password1.equals(password2)) {
			messages.add("パスワードが一致していません");
		}
		if (StringUtils.isEmpty(name) == true) {
			messages.add("名前を入力してください");
		}
		if (post_id == 0) {
			messages.add("部署・役職を選択してください");
		}else if((branch_id==1&&post_id==3)||(branch_id ==1&&post_id==4)){
			messages.add("支店と部署・役職の組み合わせが不正です");
		}else if((branch_id==2&&post_id==1)||(branch_id ==2&&post_id==2)){
			messages.add("支店と部署・役職の組み合わせが不正です");
		}else if((branch_id==3&&post_id==1)||(branch_id ==3&&post_id==2)){
			messages.add("支店と部署・役職の組み合わせが不正です");
		}else if((branch_id==4&&post_id==1)||(branch_id ==4&&post_id==2)){
			messages.add("支店と部署・役職の組み合わせが不正です");
		}
		if (branch_id == 0) {
			messages.add("支店を選択してください");
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
