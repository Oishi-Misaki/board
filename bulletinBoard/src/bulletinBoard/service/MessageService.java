package bulletinBoard.service;

import static bulletinBoard.utils.CloseableUtil.*;
import static bulletinBoard.utils.DBUtil.*;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import bulletinBoard.beans.Message;
import bulletinBoard.beans.UserMessage;
import bulletinBoard.dao.MessageDao;
import bulletinBoard.dao.UserMessageDao;


public class MessageService {

	public void register(Message message) {

		Connection connection = null;
		try {
			connection = getConnection();

			MessageDao messageDao = new MessageDao();
			messageDao.insert(connection, message);

			commit(connection);
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
	public void delete(String message_id) {

		Connection connection = null;
		try {
			connection = getConnection();

			MessageDao messageDao = new MessageDao();
			messageDao.delete(connection, message_id);

			commit(connection);
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

	private static final int LIMIT_NUM = 1000;

	public List<UserMessage> getUserMessage(String category, String startDate, String endDate) {

		Connection connection = null;
		try {
			connection = getConnection();

			if(StringUtils.isEmpty(startDate)){
				startDate = "2017-05-01 00:00:00";
			}
			if(StringUtils.isEmpty(endDate)){
				Date date = new Date();
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				endDate = sdf.format(date).toString();
			}

			UserMessageDao userMessageDao = new UserMessageDao();
			List<UserMessage> ret = userMessageDao.getUserMessage(connection, LIMIT_NUM, category, startDate, endDate);

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
