package org.fkit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/*
 * DispatcherServlet�ַ�������ᱻ�ַ�����Ӧ�����java�࣬��Spring mvc�г�ΪHandle,
 * ��2.5�汾֮ǰ������Handle��Ψһ������ʵ��Controller�ӿڣ��ӿ�ʵ�������ʵ��handleRequest()������
 * handleRequest()����������ҵ������󣬱��뷵��һ������ģ�Ͷ������ͼ·����ModelAndView����
 *
 * ʵ��Controller�ӿڵ���ֻ�ܴ���һ����һ��������
 */
public class HelloController implements Controller {
	private static final Log logger = LogFactory.getLog(HelloController.class);

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("handleRequest����");
		 
		//����׼�����ص�ModelAndView���󣬸ö���ͨ������������ͼ����ģ������
		ModelAndView mv = new ModelAndView();
		
		//���ģ�����ݣ������������POJO����
		mv.addObject("message", "Hello World!");
		//�����߼���ͼ������ͼ����������ݸ����ֽ������������ͼҳ��
		mv.setViewName("/WEB-INF/content/welcome.jsp");
		return mv;
	}

}
