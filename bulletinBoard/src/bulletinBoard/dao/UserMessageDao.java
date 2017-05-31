package bulletinBoard.dao;

import static bulletinBoard.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import bulletinBoard.beans.UserMessage;
import bulletinBoard.exception.SQLRuntimeException;

public class UserMessageDao {

	public List<UserMessage> getUserMessage(Connection connection, int num, String category, String startDate, String endDate) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT messages.id as id ");
			sql.append(", user_id");
			sql.append(", login_id");
			sql.append(", name");
			sql.append(", branch_id");
			sql.append(", category");
			sql.append(", object");
			sql.append(", text");
			sql.append(", insert_time");
			sql.append(" FROM messages");
			sql.append(" INNER JOIN users" );
			sql.append(" ON messages.user_id = users.id");
			//??
			sql.append(" WHERE insert_time BETWEEN ? AND ?");
			if(!StringUtils.isEmpty(category)){
				sql.append(" AND category =?");
			}
			sql.append(" ORDER BY insert_time DESC limit ?" );

			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, startDate);
			ps.setString(2, endDate);
			if(!StringUtils.isEmpty(category)){
				ps.setString(3, category);
				ps.setInt(4, num);
			}else{
				ps.setInt(3, num);
			}

			ResultSet rs = ps.executeQuery();
			List<UserMessage> ret = toUserMessageList(rs);
			return ret;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserMessage> toUserMessageList(ResultSet rs)
			throws SQLException {

		List<UserMessage> ret = new ArrayList<UserMessage>();
		try {
			while (rs.next()) {
				int message_id = rs.getInt("id");
				int user_id = rs.getInt("user_id");
				String login_id = rs.getString("login_id");
				String name = rs.getString("name");
				int branch_id = rs.getInt("branch_id");
				String category = rs.getString("category");
				String object = rs.getString("object");
				String text = rs.getString("text");
				Timestamp insert_time = rs.getTimestamp("insert_time");

				UserMessage usermessage = new UserMessage();
				usermessage.setMessage_id(message_id);
				usermessage.setUser_id(user_id);
				usermessage.setLogin_id(login_id);
				usermessage.setName(name);
				usermessage.setBranch_id(branch_id);
				usermessage.setCategory(category);
				usermessage.setObject(object);
				usermessage.setText(text);
				usermessage.setInsert_time(insert_time);
				ret.add(usermessage);

			}
			return ret;
		} finally {
			close(rs);
		}
	}
	public List<UserMessage> getCategory(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT messages.id as id ");
			sql.append(",user_id");
			sql.append(", login_id");
			sql.append(", name");
			sql.append(", category");
			sql.append(", object");
			sql.append(", text");
			sql.append(", insert_time");
			sql.append(" FROM messages");
			sql.append(" INNER JOIN users" );
			sql.append(" ON messages.user_id = users.id");
			sql.append(" GROUP BY category" );

			ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			List<UserMessage> ret = toCategoryList(rs);
			return ret;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<UserMessage> toCategoryList(ResultSet rs)
			throws SQLException {

		List<UserMessage> ret = new ArrayList<UserMessage>();
		try {
			while (rs.next()) {
				int message_id = rs.getInt("id");
				int user_id = rs.getInt("user_id");
				String login_id = rs.getString("login_id");
				String name = rs.getString("name");
				String category = rs.getString("category");
				String object = rs.getString("object");
				String text = rs.getString("text");
				Timestamp insert_time = rs.getTimestamp("insert_time");

				UserMessage usermessage = new UserMessage();
				usermessage.setMessage_id(message_id);
				usermessage.setUser_id(user_id);
				usermessage.setLogin_id(login_id);
				usermessage.setName(name);
				usermessage.setCategory(category);
				usermessage.setObject(object);
				usermessage.setText(text);
				usermessage.setInsert_time(insert_time);
				ret.add(usermessage);

			}
			return ret;
		} finally {
			close(rs);
		}
	}
}
