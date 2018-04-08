package org.fkit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ModelAttribute2Controller {
	
	//model�������ƺ�ֵ��model.addAttribute()ʵ�֣�����Ҫ�ڷ����м���Model���͵Ĳ���
	//����дֻ��Ϊ���ڵ�����������ǰ���ȵ��ñ��������ֶ���Model����������
	@ModelAttribute
	public void userModel2(
			@RequestParam("loginname") String loginname,
			@RequestParam("password") String password,
			Model model) {
		System.out.println("###userModel2()����������, �ֶ�����û���������");
		model.addAttribute("loginname", loginname);
		model.addAttribute("password", password);
	}
	
	@RequestMapping(value="/login2")
	public String login2() {
		return "result2";
	}
}
