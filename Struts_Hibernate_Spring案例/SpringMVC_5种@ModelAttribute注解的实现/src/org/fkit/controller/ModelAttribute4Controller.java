package org.fkit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ModelAttribute4Controller {
	
	//�˷�����ת������ͼ��/login4.jsp, Model�з�װ��������Ϊ("username", "admin")
	@RequestMapping(value="/login4")  //�˴�value����ֵ����ӳ���·����������һ����ת����ͼ����
	@ModelAttribute(value="yourrole") //��������Ϊusername
	public String login4() {
		return "murderer"; //�˴��ķ���ֵ������ͼ���ƣ�����model���Ե�ֵ
	}
	
}
