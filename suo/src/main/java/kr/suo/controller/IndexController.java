package kr.suo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("index")//메인화면
	public String index() {
		return "/index/index";
	}
}
