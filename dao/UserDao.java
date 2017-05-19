package bulletinBoard.dao;

import static bulletinBoard.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import bulletinBoard.beans.User;
import bulletinBoard.exception.SQLRuntimeException;


public class UserDao {

	public void insert(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO users ( ");
			sql.append("login_id");
			sql.append(",password");
			sql.append(", name");
			sql.append(", post_id");
			sql.append(", branch_id");
			sql.append(", can_use");
			sql.append(") VALUES (");
			sql.append(" ?"); // login_id
			sql.append(", ?"); // password
			sql.append(", ?"); // name
			sql.append(", ?"); // post_id
			sql.append(", ?"); // branch_id
			sql.append(", ?"); // can_use
			sql.append(")");
//
			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLogin_id());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setInt(4, user.getPost_id());
			ps.setInt(5, user.getBranch_id());
			ps.setInt(6, user.getCan_use());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void updateUserInfor(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET ");
			sql.append(" login_id=?");
			sql.append(", name=?");
			sql.append(", post_id=?");
			sql.append(", branch_id=?");
			if(!StringUtils.isEmpty(user.getPassword())){
				sql.append(", password=?");
				sql.append(" WHERE id=?");
			}else{
				sql.append(" WHERE id=?");
			}
//
			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLogin_id());
			ps.setString(2, user.getName());
			ps.setInt(3, user.getPost_id());
			ps.setInt(4, user.getBranch_id());
			if(!StringUtils.isEmpty(user.getPassword())){
				ps.setString(5, user.getPassword());
				ps.setInt(6, user.getId());
			}else{
				ps.setInt(5, user.getId());
			}

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	public void delete(Connection connection, String user_id) {

		PreparedStatement ps = null;
		try {
			String sql = "DELETE FROM users WHERE id=?";
			ps = connection.prepareStatement(sql);

			int userId = Integer.parseInt(user_id);
			ps.setInt(1, userId);

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}


	public void updateFlag(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			String sql = "UPDATE users SET can_use =? WHERE id = ?";

			ps = connection.prepareStatement(sql);

			ps.setInt(1, user.getCan_use());
			ps.setInt(2, user.getId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public User getUser(Connection connection, String login_id, String password) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE  login_id = ? AND password = ? AND can_use = ?";

			ps = connection.prepareStatement(sql);
			ps.setString(1, login_id);
			ps.setString(2, password);
			ps.setInt(3, 0);


			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	public User getEditUser(Connection connection, int id) {

		PreparedStatement ps = null;
		try {

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT  ");
			sql.append("id");
			sql.append(", login_id");
			sql.append(", password");
			sql.append(", name");
			sql.append(", post_id");
			sql.append(", branch_id");
			sql.append(", can_use");
			sql.append(" FROM users WHERE  id = ?");

			ps = connection.prepareStatement(sql.toString());
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			List<User> ret = toUserList(rs);
			User ret1 =null;
			if(ret.size()==0){
				ret1 = null;
			}else{
				ret1 = ret.get(0);
			}
			return ret1;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public List<User> getUserList(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users ";

			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<User> ret = toUserList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<User> toUserList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String login_id = rs.getString("login_id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				int post_id = rs.getInt("post_id");
				int branch_id = rs.getInt("branch_id");
				int can_use = rs.getInt("can_use");

				User user = new User();
				user.setId(id);
				user.setLogin_id(login_id);
				user.setPassword(password);
				user.setName(name);
				user.setPost_id(post_id);
				user.setBranch_id(branch_id);
				user.setCan_use(can_use);
				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
