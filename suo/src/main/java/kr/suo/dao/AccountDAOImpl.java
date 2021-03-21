package kr.suo.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.suo.vo.UserVO;

@Repository
public class AccountDAOImpl implements AccountDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public UserVO userLogin(String user_id) {
		
		return this.sqlSession.selectOne("user_login",user_id);
	}

	@Override
	public UserVO overlab(String user_id) {
		
		return this.sqlSession.selectOne("overlab",user_id);
	}

	@Override
	public void userJoin(UserVO b) {
		
		this.sqlSession.insert("userJoin",b);
	}

	@Override
	public UserVO findId(UserVO b) {
		
		return this.sqlSession.selectOne("find_id",b);
	}

	@Override
	public void updatePw(UserVO ran_pw) {
		
		this.sqlSession.update("update_pw",ran_pw);
	}

	@Override
	public UserVO findPw(UserVO b) {
		
		return this.sqlSession.selectOne("find_pw",b);
	}

	@Override
	public UserVO getUser(String id) {
		
		return this.sqlSession.selectOne("edit_get",id);
	}

	@Override
	public void editUser(UserVO u) {
		this.sqlSession.update("edit_user",u);
		
	}

	@Override
	public UserVO searchUser(String user_id) {
		
		return this.sqlSession.selectOne("search_user",user_id);
	}

	@Override
	public UserVO chatUser(UserVO b) {
		
		return this.sqlSession.selectOne("chat_user",b);
	}

}
