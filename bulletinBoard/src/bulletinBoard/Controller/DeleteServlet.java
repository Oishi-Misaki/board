package bulletinBoard.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import bulletinBoard.beans.UserComment;
import bulletinBoard.beans.UserMessage;
import bulletinBoard.service.CommentService;
import bulletinBoard.service.MessageService;
import bulletinBoard.service.UserService;

@WebServlet (urlPatterns = {"/delete"})
public class DeleteServlet extends HttpServlet{
	private static final long serialVersionUID  = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");

		if(!StringUtils.isEmpty(request.getParameter("deleteMessage"))){
			new MessageService().delete(request.getParameter("deleteMessage"));
			List<UserMessage> categorys = new MessageService().getCategory();
			request.setAttribute("categorys", categorys);
			String category = request.getParameter("category");
		    String startDate=request.getParameter("startDate");
		    String endDate=request.getParameter("endDate");
		    List<UserMessage> message=new MessageService().getUserMessage(category, startDate, endDate);
			request.setAttribute("messages", message);
			request.setAttribute("messageNum", message.size());
			request.setAttribute("category", category);
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			List<UserComment> comments = new CommentService().getUserComment();
			request.setAttribute("comments", comments);
			response.sendRedirect("./");
		}else if(!StringUtils.isEmpty(request.getParameter("deleteComment"))){
			new CommentService().delete(request.getParameter("deleteComment"));
			List<UserMessage> categorys = new MessageService().getCategory();
			request.setAttribute("categorys", categorys);
			String category = request.getParameter("category");
		    String startDate=request.getParameter("startDate");
		    String endDate=request.getParameter("endDate");
		    List<UserMessage> message=new MessageService().getUserMessage(category, startDate, endDate);
			request.setAttribute("messages", message);
			request.setAttribute("messageNum", message.size());
			request.setAttribute("category", category);
			request.setAttribute("startDate", startDate);
			request.setAttribute("endDate", endDate);
			List<UserComment> comments = new CommentService().getUserComment();
			request.setAttribute("comments", comments);
			request.getRequestDispatcher("./index.jsp").forward(request, response);
		}else if(!StringUtils.isEmpty(request.getParameter("user_id"))){
			new UserService().delete(request.getParameter("user_id"));
			response.sendRedirect("./manage");
		}else{
			response.sendRedirect("./");
		}
	}
}
