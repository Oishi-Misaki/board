package bulletinBoard.dao;

import static bulletinBoard.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bulletinBoard.beans.UserComment;
import bulletinBoard.exception.SQLRuntimeException;

public class UserCommentDao {
	public List<UserComment> getUserComment(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT comments.id as id ");
			sql.append(", user_id");
			sql.append(", login_id");
			sql.append(", name");
			sql.append(", text");
			sql.append(", branch_id");
			sql.append(", post_id");
			sql.append(", insert_time");
			sql.append(", message_id");
			sql.append(" FROM comments");
			sql.append(" INNER JOIN users" );
			sql.append(" ON comments.user_id = users.id");
			sql.append(" ORDER BY insert_time DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			List<UserComment> ret = toUserCommentList(rs);
			return ret;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserComment> toUserCommentList(ResultSet rs)
			throws SQLException {

		List<UserComment> ret = new ArrayList<UserComment>();
		try {
			while (rs.next()) {
				int comment_id = rs.getInt("id");
				int user_id = rs.getInt("user_id");
				int  message_id = rs.getInt("message_id");
				String name = rs.getString("name");
				String login_id = rs.getString("login_id");
				int branch_id = rs.getInt("branch_id");
				int post_id = rs.getInt("post_id");
				String text = rs.getString("text");
				Timestamp insert_time = rs.getTimestamp("insert_time");

				UserComment userComment = new UserComment();

				userComment.setComment_id(comment_id);
				userComment.setUser_id(user_id);
				userComment.setMessage_id(message_id);
				userComment.setName(name);
				userComment.setLogin_id(login_id);
				userComment.setBranch_id(branch_id);
				userComment.setPost_id(post_id);
				userComment.setText(text);
				userComment.setInsert_time(insert_time);
				ret.add(userComment);

			}
			return ret;
		} finally {
			close(rs);
		}
	}
}
