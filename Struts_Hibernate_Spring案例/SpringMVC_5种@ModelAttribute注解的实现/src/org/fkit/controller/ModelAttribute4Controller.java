package org.fkit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ModelAttribute4Controller {
	
	//此方法跳转到的视图是/login4.jsp, Model中封装到的属性为("username", "admin")
	@RequestMapping(value="/login4")  //此处value属性值既是映射的路径，又是下一步跳转的视图名称
	@ModelAttribute(value="yourrole") //属性名称为username
	public String login4() {
		return "murderer"; //此处的返回值不是视图名称，而是model属性的值
	}
	
}
