package com.does.biz.controller;

import com.does.biz.domain.User;
import com.does.config.auth.LoginUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

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

	@GetMapping("/user/login")
	public String login(HttpServletRequest request) {
		String referer = request.getHeader("referer");
		// 이전 경로가 존재하고 /user/login 가 아닌 경우 세션에 이전 경로 저장
		// 이전 경로가 /user/login 이라는 것은 로그인 실패 등의 이유로 이전 경로가 로그인 페이지이라는것인데,
		// 로그인 성공 후 로그인 페이지로 이동하는 것은 로직 에러이므로 이전 경로가 /user/login 이 아닌 경우에 세션에 저장
		if (referer != null && !referer.contains("/user/login")) {
			request.getSession().setAttribute("prevUrl", referer);
		}
		return "user/login";
	}

	@GetMapping("/view")
	public String generalView(Model model, @LoginUser User user) {
		if (user != null) model.addAttribute("userName", user.getName());
		return "page/view";
	}
}