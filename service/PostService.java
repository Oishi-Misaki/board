package bulletinBoard.service;

import static bulletinBoard.utils.CloseableUtil.*;
import static bulletinBoard.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bulletinBoard.beans.Post;
import bulletinBoard.dao.PostDao;

public class PostService {
	public List<Post> getPostList() {

		Connection connection = null;
		try {
			connection = getConnection();

			PostDao postDao = new PostDao();
			List<Post> ret = postDao.getPostList(connection);

			commit(connection);
			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
}