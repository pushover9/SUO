package kr.suo.service;

import kr.suo.vo.UserVO;

public interface AccountService {

	UserVO userLogin(String user_id);

	UserVO overlab(String user_id);

	void userJoin(UserVO b);

	UserVO findId(UserVO b);

	void updatePw(UserVO ran_pw);

	UserVO findPw(UserVO b);

	UserVO getUser(String id);

	void editUser(UserVO u);

	UserVO searchUser(String user_id);

	UserVO chatUser(UserVO b);

}
