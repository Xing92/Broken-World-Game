package xing.brokenworldserver.service;

import xing.brokenworld_requests.response.LoginUserResponse;
import xing.brokenworldserver.dao.UserDao;

public class UserService {

	private static UserDao userDao = new UserDao();

	public LoginUserResponse loginUser(String login, String password) {

		LoginUserResponse loginUserResponse = userDao.tryLogin(login, password);

		return loginUserResponse;
	}

}
