package kr.suo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.naver.test.NaverLoginBO;


@Controller
@RequestMapping("/main/*")
public class MainController {
	
	
	@RequestMapping("/statistics")
	public String statistics() {
		return "/main/statistics";
	}
	
	@RequestMapping("/search")
	public String main() {
		return "/main/search";
	}

	@RequestMapping("/join")
	public String join() {
		return "/main/join";
	}
	
	@RequestMapping("/chatting")
	public String chatting() {
		return "/main/chatting";
	}
}
