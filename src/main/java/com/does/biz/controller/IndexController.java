package com.does.biz.controller;

import com.does.biz.domain.User;
import com.does.config.auth.LoginUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/")
	public String index(Model model, @LoginUser User user) {
		if (user != null) {
			model.addAttribute("userName", user.getName());
		}
		return "index";
	}

	@GetMapping("/myPage")
	public String myPage(Model model, @LoginUser User user) {
		if (user != null) {
			model.addAttribute("userName", user.getName());
		}
		return "page/myPage";
	}
}
