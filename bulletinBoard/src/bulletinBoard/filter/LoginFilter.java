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

@WebFilter("/*")
public class LoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        HttpSession session = request.getSession(true);
        
        String loginURI = request.getContextPath() + "/login";
        String cssURI = request.getContextPath() + "/css/board.css";

        boolean loggedIn = session != null && session.getAttribute("loginUser") != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        boolean cssRequest = request.getRequestURI().equals(cssURI);

        if (loggedIn || loginRequest || cssRequest) {
            chain.doFilter(request, response);
        } else {
        	List<String> messages = new ArrayList<String>();
			messages.add("ログインしてください。");
			session.setAttribute("errorMessages", messages);
            response.sendRedirect(loginURI);
        }

	}

	@Override
	public void destroy() {}
	@Override
	public void init(FilterConfig arg0) throws ServletException {}
}
