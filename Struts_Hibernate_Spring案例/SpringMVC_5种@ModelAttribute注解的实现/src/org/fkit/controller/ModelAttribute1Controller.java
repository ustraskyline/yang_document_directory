package org.fkit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ModelAttribute1Controller {

	//��@ModelAttributeע�͵ķ�������Controller��ÿ������ִ��ǰ��ִ�У�
	//������Model����������ֵ, value��ֵ��Ϊ�󶨵�Model�����Ե����ƣ������ķ���ֵΪ����ֵ
	@ModelAttribute("loginname")
	public String userModel1(@RequestParam("loginname") String loginname) {
		System.out.println("###>ModelAttribute1Controller����userModel1()����������, ����Model������ֵΪ:" + loginname);
		return loginname;
	}

	@RequestMapping(value = "/login1")
	public String login1() {
		return "result1";
	}
}
