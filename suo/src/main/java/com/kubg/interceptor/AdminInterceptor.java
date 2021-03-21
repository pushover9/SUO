package com.kubg.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kubg.domain.MemberVO;

public class AdminInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
		    HttpServletResponse response, Object obj) throws Exception {
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO)session.getAttribute("member");
		
		if(member == null) {
			 response.sendRedirect("/member/signin");
			 
			 return false;
			 
			}
		
		if(member.getVerify() != 9) {
			   response.sendRedirect("/shop/list?c=104&l=2");
			   return false;
			  }
		
		
		
			  return true;
  
	}
	
}
