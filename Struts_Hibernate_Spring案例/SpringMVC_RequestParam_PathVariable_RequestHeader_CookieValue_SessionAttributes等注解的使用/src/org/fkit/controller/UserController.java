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
@SessionAttributes(types= {User.class}, value="user") //��Model��������Ϊuser�����Է���HttpSession������
public class UserController {
	private static List<User> userList;
	private static final Log logger = LogFactory.getLog(UserController.class);

	public UserController() {
		super();
		userList = new ArrayList<User>();
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerForm() {
		logger.info("register Get����������");
		System.out.println("=====>registerForm(), �ս���ʱִ�еĲ���, ����Get����, ת����ע�������");
		return "registerForm";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(
			@RequestParam("loginname") String loginname,
			@RequestParam("password") String password,
			@RequestParam("username") String username) {
		logger.info("register POST����������");
		System.out.println("=====>register(), ����Post����, ʵ���û�ע��, ת������¼����");
		
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
		logger.info("��¼��:" + loginname + "����:" + password);
		System.out.println("=====>login()�����û���¼, ��ƥ��ɹ�, ��ת����welcomeҳ��, ƥ�䲻�ɹ���ת������¼ҳ��");

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
			//��ʡ��value���ԣ���Ĭ�Ͻ���̬�����󶨵�ͬ������
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
		
		//��������ǰʹ����@SessionAttributes("user")ע�⣬���Դ�model�е�user���Իᱻ
		//�洢��HttpSession������
		model.addAttribute("user", user);
		return "testsessionattributes";
	}
}
