package bulletinBoard.service;

import static bulletinBoard.utils.CloseableUtil.*;
import static bulletinBoard.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bulletinBoard.beans.BranchPostUser;
import bulletinBoard.beans.User;
import bulletinBoard.beans.UserMessage;
import bulletinBoard.dao.BranchPostUserDao;
import bulletinBoard.dao.UserDao;
import bulletinBoard.dao.UserMessageDao;


public class ManageService {

	private static final int LIMIT_NUM = 1000;

	public List<User> getUserList() {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			List<User> ret = userDao.getUserList(connection, LIMIT_NUM);

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
	public List<BranchPostUser> getBranchPostUserList() {

		Connection connection = null;
		try {
			connection = getConnection();

			BranchPostUserDao branchPostUserDao = new BranchPostUserDao();
			List<BranchPostUser> ret = branchPostUserDao.getBranchPostUserList(connection, LIMIT_NUM);

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
	public List<UserMessage> getCategory() {

		Connection connection = null;
		try {
			connection = getConnection();

			UserMessageDao categoryDao = new UserMessageDao();
			List<UserMessage> ret = categoryDao.getCategory(connection, LIMIT_NUM);

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
