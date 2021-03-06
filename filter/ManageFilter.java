package bulletinBoard.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bulletinBoard.beans.User;

@WebFilter("/manage, /edit, /signup")
public class ManageFilter implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);

		User user  = (User) session.getAttribute("loginUser");
		if(user.getPost_id()==3){
			chain.doFilter(request, response);
		}else {
			List<String> messages = new ArrayList<String>();
			messages.add("アクセス権限がありません。");
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("./");
			}
		}

	@Override
	public void destroy() {}
	@Override
	public void init(FilterConfig arg0) throws ServletException {}
}
