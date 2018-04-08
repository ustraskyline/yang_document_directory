package org.fkit.controller;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fkit.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping(value = "/user")
@SessionAttributes(types= {User.class}, value="user") //将Model中属性名为user的属性放入HttpSession对象中
public class UserController {
	private static List<User> userList;
	private static final Log logger = LogFactory.getLog(UserController.class);

	public UserController() {
		super();
		userList = new ArrayList<User>();
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerForm() {
		logger.info("register Get方法被调用");
		System.out.println("=====>registerForm(), 刚进来时执行的操作, 处理Get请求, 转发到注册表单界面");
		return "registerForm";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(
			@RequestParam("loginname") String loginname,
			@RequestParam("password") String password,
			@RequestParam("username") String username) {
		logger.info("register POST方法被调用");
		System.out.println("=====>register(), 处理Post请求, 实现用户注册, 转发到登录界面");
		
		User user = new User();
		user.setLoginname(loginname);
		user.setPassword(password);
		user.setUsername(username);

		userList.add(user);

		return "loginForm";
	}

	@RequestMapping("/login")
	public String login(
			@RequestParam("loginname") String loginname, 
			@RequestParam("password") String password,
			Model model) {
		logger.info("登录名:" + loginname + "密码:" + password);
		System.out.println("=====>login()处理用户登录, 若匹配成功, 则转发到welcome页面, 匹配不成功则转发到登录页面");

		for (User user : userList) {
			if (user.getLoginname().equals(loginname) && user.getPassword().equals(password)) {
				model.addAttribute("user", user);
				return "welcome";
			}
		}

		return "loginForm";
	}
	
	@RequestMapping("/pathVariableTest/{userid}")
	public void pathVariableTest(
			//若省略value属性，则默认将动态参数绑定到同名参数
			@PathVariable(value="userid") Integer yangid) {
		System.out.println("userid=" + yangid);
	}
	
	@RequestMapping(value="/requestHeaderTest")
	public void requestHeaderTest(
			@RequestHeader("User-Agent") String userAgent,
			@RequestHeader("Accept") String[] accepts) {
		System.out.println("User-Agent = " + userAgent);
		for(String aaa : accepts) {
			System.out.println("accept=" + aaa);
		}
	}
	
	@RequestMapping(value="/cookieValueTest")
	public void cookieValueTest(
			@CookieValue(value="JSESSIONID", defaultValue="") String sessionId) {
		System.out.println("sessionId = " + sessionId);
	}
	
	@RequestMapping(value="testSessionAttributes")
	public String testSessionAttributes(Model model) {
		User user = new User();
		user.setLoginname("yangjia");
		user.setPassword("628425");
		user.setUsername("administrator");
		
		//由于类名前使用了@SessionAttributes("user")注解，所以此model中的user属性会被
		//存储到HttpSession对象中
		model.addAttribute("user", user);
		return "testsessionattributes";
	}
}
