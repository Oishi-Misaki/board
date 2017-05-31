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


@WebServlet(urlPatterns = { "/signup" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		request.setCharacterEncoding("UTF-8");
		List<Branch> branches = new BranchService().getBranchList();
		request.setAttribute("branches", branches);
		List<Post> posts = new PostService().getPostList();
		request.setAttribute("posts", posts);

		request.getRequestDispatcher("./signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();
		User user = new User();
		user.setLogin_id(request.getParameter("login_id"));
		user.setPassword(request.getParameter("password1"));
		user.setName(request.getParameter("name"));
		user.setPost_id(Integer.parseInt(request.getParameter("post_id")));
		user.setBranch_id(Integer.parseInt(request.getParameter("branch_id")));
		user.setCan_use(0);
		if (isValid(request, messages) == true) {

			new UserService().register(user);
			response.sendRedirect("./manage");
		} else {
			request.setAttribute("signUpUser", user);
			session.setAttribute("errorMessages", messages);

			List<Branch> branches = new BranchService().getBranchList();
			request.setAttribute("branches", branches);
			List<Post> posts = new PostService().getPostList();
			request.setAttribute("posts", posts);

			request.getRequestDispatcher("./signup.jsp").forward(request, response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String login_id = request.getParameter("login_id");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String name = request.getParameter("name");
		int post_id = Integer.parseInt(request.getParameter("post_id"));
		int branch_id = Integer.parseInt(request.getParameter("branch_id"));

		List<User> users = new ManageService().getUserList();
		if (StringUtils.isEmpty(login_id) == true) {
			messages.add("ログインIDを入力してください");
		}else if(!login_id.matches("\\w{6,20}")){
			messages.add("ログインIDを半角英数字（6-20文字）で入力してください");
		}
		for(User user:users){
			if(user.getLogin_id().equals(login_id)){
				messages.add("ログインIDがすでに存在します");
			}
		}
		if(StringUtils.isEmpty(password1)||StringUtils.isEmpty(password2)){
			messages.add("パスワードを入力してください");
		}else if(!password1.matches("\\w{6,255}")){
			messages.add("パスワードを半角英数字（6-255文字）で入力してください");
		}else if (!password1.equals(password2)) {
			messages.add("パスワードが一致していません");
		}
		if (StringUtils.isEmpty(name) == true) {
			messages.add("名前を入力してください");
		}else if(name.length()>10){
			messages.add("名前を10文字以下で入力してください");
		}
		if (branch_id == 0) {
			messages.add("支店を選択してください");
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

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
