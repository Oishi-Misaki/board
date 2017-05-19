package bulletinBoard.dao;

import static bulletinBoard.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bulletinBoard.beans.Post;
import bulletinBoard.exception.SQLRuntimeException;

public class PostDao {
	public List<Post> getPostList(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT id ");
			sql.append(",  name");
			sql.append(" FROM posts");
			sql.append(" GROUP BY id" );

			ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			List<Post> ret = toPostList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	private List<Post> toPostList(ResultSet rs) throws SQLException {

		List<Post> ret = new ArrayList<Post>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");

				Post branch = new Post();
				branch.setId(id);
				branch.setName(name);
				ret.add(branch);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}
