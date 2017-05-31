package bulletinBoard.dao;

import static bulletinBoard.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bulletinBoard.beans.BranchPostUser;
import bulletinBoard.exception.SQLRuntimeException;

public class BranchPostUserDao {
	public List<BranchPostUser> getBranchPostUserList(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT users.id as id ");
			sql.append(", login_id");
			sql.append(", password");
			sql.append(", users.name as name");
			sql.append(", posts.id as post_id");
			sql.append(", posts.name as post_name");
			sql.append(", branches.id as branch_id");
			sql.append(", branches.name as branch_name");
			sql.append(", can_use");
			sql.append(" FROM users");
			sql.append(" INNER JOIN branches" );
			sql.append(" ON branch_id=branches.id");
			sql.append(" INNER JOIN  posts" );
			sql.append(" ON  post_id=posts.id");
			sql.append(" ORDER BY branch_id, post_id " );


			ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			List<BranchPostUser> ret = toBranchPostUserList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<BranchPostUser> toBranchPostUserList(ResultSet rs) throws SQLException {

		List<BranchPostUser> ret = new ArrayList<BranchPostUser>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String login_id = rs.getString("login_id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				int post_id = rs.getInt("post_id");
				String post_name = rs.getString("post_name");
				int branch_id = rs.getInt("branch_id");
				String branch_name = rs.getString("branch_name");
				int can_use = rs.getInt("can_use");

				BranchPostUser branchPostUser = new BranchPostUser();
				branchPostUser.setId(id);
				branchPostUser.setLogin_id(login_id);
				branchPostUser.setPassword(password);
				branchPostUser.setName(name);
				branchPostUser.setPost_id(post_id);
				branchPostUser.setPost_name(post_name);
				branchPostUser.setBranch_id(branch_id);
				branchPostUser.setBranch_name(branch_name);
				branchPostUser.setCan_use(can_use);
				ret.add(branchPostUser);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}
