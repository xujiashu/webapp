package com.webapp.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.webapp.domain.User;
import com.webapp.domain.UserLogin;
import com.webapp.service.UserService;

@Controller
public class LoginController {
	@Autowired
	private UserService userservice;
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("userLogin", new UserLogin());
		return "login";
	}
	
	
	@PostMapping("/login")
	public String login(@Valid UserLogin user, BindingResult br,HttpSession session,Model model) {
		if(!br.hasErrors()) {
			User u = null;
			u =userservice.checkUser(user);
			if(u!=null) {
				session.setAttribute("user", u);
				return "index";
			}
		}
		model.addAttribute("faild", "账号或密码不正确");
		return "login";
	}

}
