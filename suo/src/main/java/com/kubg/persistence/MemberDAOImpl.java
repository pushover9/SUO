package com.kubg.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kubg.domain.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSession session;

	@Override
	public void signup(MemberVO vo) {
		session.insert("signup",vo);
		
	}
	@Override
	public MemberVO signin(MemberVO vo) {
		return session.selectOne("signin",vo);
		
	}

	
}
