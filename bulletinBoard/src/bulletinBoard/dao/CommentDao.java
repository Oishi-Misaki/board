package bulletinBoard.dao;

import static bulletinBoard.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bulletinBoard.beans.Comment;
import bulletinBoard.exception.SQLRuntimeException;

public class CommentDao {
	public void insert(Connection connection, Comment comment) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO comments ( ");
			sql.append("text");
			sql.append(", insert_time");
			sql.append(", user_id");
			sql.append(", message_id");
			sql.append(") VALUES (");
			sql.append("?"); // text
			sql.append(", CURRENT_TIMESTAMP"); // insert_time]
			sql.append(", ?"); // user_id
			sql.append(", ?");// message_id
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, comment.getText());
			ps.setInt(2, comment.getUser_id());
			ps.setInt(3, comment.getMessage_id());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	public void delete(Connection connection, String comment_id) {

		PreparedStatement ps = null;
		try {
			String sql = "DELETE FROM comments WHERE id=?";
			ps = connection.prepareStatement(sql);

			int comentId = Integer.parseInt(comment_id);
			ps.setInt(1, comentId);

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
