package com.kubg.service;

import javax.servlet.http.HttpSession;

import com.kubg.domain.MemberVO;

public interface MemberService {

	void signup(MemberVO vo);

	MemberVO signin(MemberVO vo);

	void signout(HttpSession session);

}
