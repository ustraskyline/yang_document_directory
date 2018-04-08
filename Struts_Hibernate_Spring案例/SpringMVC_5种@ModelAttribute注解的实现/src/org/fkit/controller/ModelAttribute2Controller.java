package org.fkit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ModelAttribute2Controller {
	
	//model属性名称和值由model.addAttribute()实现，必须要在方法中加入Model类型的参数
	//这样写只是为了在调用其他方法前都先调用本方法，手动向Model中设置属性
	@ModelAttribute
	public void userModel2(
			@RequestParam("loginname") String loginname,
			@RequestParam("password") String password,
			Model model) {
		System.out.println("###userModel2()方法被调用, 手动填充用户名和密码");
		model.addAttribute("loginname", loginname);
		model.addAttribute("password", password);
	}
	
	@RequestMapping(value="/login2")
	public String login2() {
		return "result2";
	}
}
