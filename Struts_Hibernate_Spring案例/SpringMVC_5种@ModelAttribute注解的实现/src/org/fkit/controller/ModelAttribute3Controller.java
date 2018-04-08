package org.fkit.controller;

import java.util.ArrayList;
import java.util.List;

import org.fkit.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ModelAttribute3Controller {
	private static List<User> userList;
	
	public ModelAttribute3Controller() {
		super();
		userList = new ArrayList<User>();
		User user1 = new User("test", "1234556", "测试用户");
		User user2 = new User("admin", "5134324", "管理员");
		
		userList.add(user1);
		userList.add(user2);
	}
	
	public User find(String loginname, String password) {
		for(User user : userList) {
			if(user.getLoginname().equals(loginname) && user.getPassword().equals(password)) {
				return user;
			}
		}
		
		return null;
	}
	
	//model属性名称由方法返回类型隐含表示，此处属性名称为user
	@ModelAttribute
	public User userModel3(
			@RequestParam("loginname") String loginname,
			@RequestParam("password") String password) {
		System.out.println("---->userModel3()被调用");
		return find(loginname, password);
	}
	
	@RequestMapping(value="/login3")
	public String login3() {
		return "result3";
	}
}
