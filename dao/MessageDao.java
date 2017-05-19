package bulletinBoard.dao;

import static bulletinBoard.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bulletinBoard.beans.Message;
import bulletinBoard.exception.SQLRuntimeException;

public class MessageDao {

	public void insert(Connection connection, Message message) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO messages ( ");
			sql.append("object");
			sql.append(", text");
			sql.append(", category");
			sql.append(", insert_time");
			sql.append(", user_id");
			sql.append(") VALUES (");
			sql.append("?"); // object
			sql.append(", ?"); // text
			sql.append(", ?"); // category
			sql.append(", CURRENT_TIMESTAMP"); // insert_time]
			sql.append(", ?");// user_id
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, message.getObject());
			ps.setString(2, message.getText());
			ps.setString(3, message.getCategory());
			ps.setInt(4, message.getUserId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	public void delete(Connection connection, String message_id) {

		PreparedStatement ps = null;
		try {
			String sql = "DELETE FROM messages WHERE id=?";
			ps = connection.prepareStatement(sql);

			int messageId = Integer.parseInt(message_id);
			ps.setInt(1, messageId);

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}
