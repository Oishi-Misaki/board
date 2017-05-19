package bulletinBoard.dao;

import static bulletinBoard.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bulletinBoard.beans.Branch;
import bulletinBoard.exception.SQLRuntimeException;

public class BranchDao {
	public List<Branch> getBranchList(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT id ");
			sql.append(", name");
			sql.append(" FROM branches");
			sql.append(" GROUP BY id" );

			ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			List<Branch> ret = toBranchList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	private List<Branch> toBranchList(ResultSet rs) throws SQLException {

		List<Branch> ret = new ArrayList<Branch>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");

				Branch branch = new Branch();
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
