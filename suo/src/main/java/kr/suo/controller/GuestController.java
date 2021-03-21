package kr.suo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.suo.vo.GuestVO;

@Controller
public class GuestController {
	@RequestMapping("guest_write")
	public void guest_write() {
		
	}
	@PostMapping("guest_ok")
	public String board_write_ok(@RequestParam("title") int title, @RequestParam("cont") int cont, Model m) {
		m.addAttribute("title",title);
		m.addAttribute("cont",cont);
		
		return "guest_ok";
	}
}
