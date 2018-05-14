package org.fkit.controller;

import org.fkit.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



/**   
 * @Description: 
 * <br>网站：<a href="http://www.fkit.org">疯狂Java</a> 
 * @author 肖文吉	36750064@qq.com   
 * @version V1.0   
 */

/**
 *  HelloWorldController是一个基于注解的控制器,
 *  可以同时处理多个请求动作，并且无须实现任何接口。
 *  org.springframework.stereotype.Controller注解用于指示该类是一个控制器
 */
@Controller
public class UserController{
	 
	/*
	 * form标签：自动绑定Model中的一个属性值到当前form对应的实体对象，默认为command属性，
	 * 这样就可以在form表单里使用该对象的属性.
	 */
	 @RequestMapping(value="/registerForm",method=RequestMethod.GET)
	 public String registerForm(Model model) {
		 User user = new User("杨佳","male",25);
		 // model中添加属性command，值是user对象，当属性为command时，<form:form>不需要额外的属性
    	 model.addAttribute("command",user);
	     return "registerForm";
	 }
	 
	 @RequestMapping(value="/registerForm2",method=RequestMethod.GET)
	 public String registerForm2(Model model) {
		 User user = new User("untraline","female",21);
		 // model中添加的属性名称要与<form:form>中modelAttribute属性值相同
    	 model.addAttribute("yangjia",user);
	     return "registerForm2";
	 }

}

