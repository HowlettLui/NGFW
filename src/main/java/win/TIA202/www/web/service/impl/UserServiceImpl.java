package win.TIA202.www.web.service.impl;

import java.util.Objects;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import win.TIA202.www.web.dao.UserDao;
import win.TIA202.www.web.entity.User;
import win.TIA202.www.web.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

//	public UserServiceImpl() throws NamingException {
//		userDao = new UserDaoImpl();
//	}

	@Override
	public User register(User user) {

		// 資料檢核 or 資料審查
		String account = user.getAccount();
		String password = user.getPassword();
		
		if (userDao.selectByAccount(account) != null) {
			user.setMessage("此使用者名稱已被註冊");
			user.setSuccessfully(false);
			return user;
		}

		if (account == null || account.length() < 5 || account.length() > 30) {
			user.setMessage("使用者名稱長度請介於 5 ~ 30 之間");
			user.setSuccessfully(false);
			return user;
		}

		if (password == null || password.length() < 6 || password.length() > 12) {
			user.setMessage("密碼長度需介於 6 ~ 12 之間");
			user.setSuccessfully(false);
			return user;
		}

		if (!Objects.equals(password, user.getPassword())) {
			user.setMessage("密碼與確認密碼不符");
			user.setSuccessfully(false);
			return user;
		}

		if (userDao.selectByEmail(user.getEmail()) != null) {
			user.setMessage("此電子信箱已被註冊");
			user.setSuccessfully(false);
			return user;
		}
		
		user.setRoleId(13);
		user.setStatus(false);
		int resultConut = userDao.insert(user);
		if(resultConut < 0) {
			user.setMessage("註冊失敗");
			user.setSuccessfully(false);
			return user;
			
		}else {
			user.setMessage("註冊成功");
			user.setSuccessfully(true);
			return user;
		}
// -----------------------------------------------------------------------------------------------		
//		if (user.getName() == null) {
//			user.setMessage("使用者名稱未輸入");
//			user.setSuccessfully(false);
//			return user;
//		}
//
//		if (user.getPassword() == null) {
//			user.setMessage("密碼未輸入");
//			user.setSuccessfully(false);
//			return user;
//		}
//
//		if (user.getEmail() == null) {
//			user.setMessage("電子信箱未輸入");
//			user.setSuccessfully(false);
//			return user;
//		}
//
//		try {
//			if (userDao.selectByEmail(user.getEmail()) != null) {
//				user.setMessage("此電子信箱已被註冊");
//				user.setSuccessfully(false);
//				return user;
//			}
//
//			user.setRoleId(2);
//			final int resultCount = userDao.insert(user);
//			if (resultCount < 1) {
//				user.setMessage("註冊錯誤，請聯絡管理員!");
//				user.setSuccessfully(false);
//				return user;
//			}
//
//			user.setMessage("註冊成功");
//			user.setSuccessfully(true);
//			return user;
//		} catch (Exception e) {
//			e.printStackTrace();
//			user.setMessage("註冊錯誤，請聯絡客服");
//			user.setSuccessfully(false);
//			return user;
//		}
	}

	@Override
	public User login(User user) {
		final String email = user.getEmail();
		final String password = user.getPassword();
		
		if (email == null || email.isEmpty()) {
			user.setSuccessfully(false);
			user.setMessage("請輸入電子信箱");
			return user;
		}

		if (password == null || password.isEmpty()) {
			user.setSuccessfully(false);
			user.setMessage("請輸入密碼");
			return user;
		}
		user = userDao.selectForLogin(email, password);
		if (user == null) {
			user = new User();
			user.setMessage("電子信箱或密碼錯誤");
			user.setSuccessfully(false);
			return user;
		}
		user.setMessage("登入成功");
		user.setSuccessfully(true);
		return user;
	}
}
