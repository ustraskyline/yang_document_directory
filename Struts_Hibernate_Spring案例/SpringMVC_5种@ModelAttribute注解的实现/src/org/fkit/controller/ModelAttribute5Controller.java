package org.fkit.controller;

import org.fkit.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ModelAttribute5Controller {

	//model属性名称为value的值，model属性值为方法返回值
	@ModelAttribute("user")
	public User userModel5(
			@RequestParam("loginname") String loginname,
			@RequestParam("password") String password) {
		
		User user = new User();
		user.setLoginname(loginname);
		user.setPassword(password);
		
		return user;
	}
	
	@RequestMapping(value="/login5")
	public String login5(
			//用@ModelAttribute("user")修饰参数，表示此处的user对象就是userModel5()方法返回的User对象
			@ModelAttribute("user") User user) {
		user.setUsername("管理员");
		
		return "result5";
	}
}
