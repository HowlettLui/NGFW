package win.TIA202.www.web.service.impl;

import java.util.List;
import java.util.Objects;

import javax.naming.NamingException;

import win.TIA202.www.web.dao.CheckEmailDao;
import win.TIA202.www.web.dao.UserDao;
import win.TIA202.www.web.dao.impl.CheckEmailDaoImpl;
import win.TIA202.www.web.dao.impl.UserDaoImpl;
import win.TIA202.www.web.entity.User;
import win.TIA202.www.web.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDao;
	private CheckEmailDao checkEmailDao;
	
	public UserServiceImpl() throws NamingException {
		userDao = new UserDaoImpl();
		checkEmailDao = new CheckEmailDaoImpl();
	}

	@Override
	public String register(User user) {
		// 資料審查
		try {
			if (checkEmailDao.checkUserEmail(user).getUserId() > 0) {
				return "此帳號已被註冊";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String account = user.getAccount();
		if (account == null) {
			return "請輸入使用者名稱";
		}

		if (account.length() < 5 || account.length() > 30) {
			return "使用者名稱長度請介於 5 ~ 30 之間";
		}

		String password = user.getPassword();
		if (password == null || password.length() < 6 || password.length() > 12) {
			return "密碼長度需介於 6 ~ 12 之間";
		}

		if (Objects.equals(password, user.getConfirmPassword())) {
			return "密碼與確認密碼不符";
		}

		int resultConut = userDao.insert(user);

		return resultConut > 0 ? null : "發生錯誤，請聯絡客服";
	}

	@Override
	public User edit(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeById(Integer user_id) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override	// 檢查email有無被註冊
	public boolean selectByEmail(User user) {
		String email = user.getEmail();
		if (email == null) {
			return true;
		}
		return false;
	}
}
