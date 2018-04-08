package org.fkit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FormController {
	
	@RequestMapping(value="/{formName}")
	public String loginForm(@PathVariable String formName) {
		System.out.println("===>FormController类的loginForm()方法被调用, 访问者名称为:" + formName);
		return formName;
	}
}
