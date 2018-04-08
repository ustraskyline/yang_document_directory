package org.fkit.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
 * HelloController是一个基于注解的控制器，可以同时处理多个请求动作，且无须实现任何接口
 */
@Controller
public class HelloController{
	private static final Log logger = LogFactory.getLog(HelloController.class);

	//@RequestMapping用来映射请求的URL和对应的方法，此处映射/hello对应hello()方法
	@RequestMapping(value="/hello")
	public ModelAndView hello() {
		logger.info("hello()方法被调用");
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Hello World");
		mv.setViewName("/WEB-INF/content/welcome.jsp");
		
		return mv;
	}

}
