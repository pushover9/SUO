package kr.suo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.suo.dao.AccountDAO;
import kr.suo.vo.UserVO;


@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountDAO accountDao;
	@Override
	public UserVO userLogin(String user_id) {
		
		return this.accountDao.userLogin(user_id);
	}
	@Override
	public UserVO overlab(String user_id) {
		
		return this.accountDao.overlab(user_id);
	}
	@Override
	public void userJoin(UserVO b) {
		
		this.accountDao.userJoin(b);
	}
	@Override
	public UserVO findId(UserVO b) {
		return this.accountDao.findId(b);
	}
	public void updatePw(UserVO ran_pw) {
		this.accountDao.updatePw(ran_pw);
	}
	@Override
	public UserVO findPw(UserVO b) {
		
		return this.accountDao.findPw(b);
	}
	@Override
	public UserVO getUser(String id) {
		
		return this.accountDao.getUser(id);
	}
	@Override
	public void editUser(UserVO u) {
		this.accountDao.editUser(u);
	}
	@Override
	public UserVO searchUser(String user_id) {
		
		return this.accountDao.searchUser(user_id);
	}
	@Override
	public UserVO chatUser(UserVO b) {
		
		return this.accountDao.chatUser(b);
	}

}
