package bulletinBoard.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import bulletinBoard.service.CommentService;
import bulletinBoard.service.MessageService;
import bulletinBoard.service.UserService;

@WebServlet (urlPatterns = {"/delete"})
public class DeleteServlet extends HttpServlet{
	private static final long serialVersionUID  = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{


		if(!StringUtils.isEmpty(request.getParameter("deleteMessage"))){
			new MessageService().delete(request.getParameter("deleteMessage"));
			response.sendRedirect("./");
		}else if(!StringUtils.isEmpty(request.getParameter("deleteComment"))){
			new CommentService().delete(request.getParameter("deleteComment"));
			response.sendRedirect("./");
		}else if(!StringUtils.isEmpty(request.getParameter("user_id"))){
			new UserService().delete(request.getParameter("user_id"));
			response.sendRedirect("./manage");
		}else{
			response.sendRedirect("./");
		}
	}
}
