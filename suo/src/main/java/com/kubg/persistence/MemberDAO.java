package com.kubg.persistence;

import com.kubg.domain.MemberVO;

public interface MemberDAO {

	void signup(MemberVO vo);

	MemberVO signin(MemberVO vo);



}
