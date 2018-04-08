package org.fkit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ModelAttribute1Controller {

	//被@ModelAttribute注释的方法会在Controller类每个方法执行前被执行，
	//用来向Model中设置属性值, value的值即为绑定到Model中属性的名称，方法的返回值为属性值
	@ModelAttribute("loginname")
	public String userModel1(@RequestParam("loginname") String loginname) {
		System.out.println("###>ModelAttribute1Controller类中userModel1()方法被调用, 设置Model的属性值为:" + loginname);
		return loginname;
	}

	@RequestMapping(value = "/login1")
	public String login1() {
		return "result1";
	}
}
