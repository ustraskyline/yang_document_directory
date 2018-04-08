package org.fkit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/*
 * DispatcherServlet分发的请求会被分发给对应处理的java类，在Spring mvc中称为Handle,
 * 在2.5版本之前，开发Handle的唯一方法是实现Controller接口，接口实现类必须实现handleRequest()方法，
 * handleRequest()方法处理完业务请求后，必须返回一个包含模型对象和视图路径的ModelAndView对象
 *
 * 实现Controller接口的类只能处理一个单一的请求动作
 */
public class HelloController implements Controller {
	private static final Log logger = LogFactory.getLog(HelloController.class);

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("handleRequest调用");
		 
		//创建准备返回的ModelAndView对象，该对象通常包含返回视图名和模型数据
		ModelAndView mv = new ModelAndView();
		
		//添加模型数据，可以是任意的POJO对象
		mv.addObject("message", "Hello World!");
		//设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面
		mv.setViewName("/WEB-INF/content/welcome.jsp");
		return mv;
	}

}
