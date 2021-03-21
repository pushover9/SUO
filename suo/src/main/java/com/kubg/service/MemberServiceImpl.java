package com.kubg.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kubg.domain.MemberVO;
import com.kubg.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO memberDAO;

	@Override
	public void signup(MemberVO vo) {
		memberDAO.signup(vo);
		
	}

	@Override
	public MemberVO signin(MemberVO vo) {
		return this.memberDAO.signin(vo);
		
	}

	@Override
	public void signout(HttpSession session) {
		session.invalidate();
		
	}


}
