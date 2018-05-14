package org.fkit.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.fkit.domain.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/json")
public class BookController {
	
	private static final Log logger = LogFactory.getLog(BookController.class);
	
    @RequestMapping(value="/testRequestBody")
    public void setJson(
    		// @RequestBody读取请求体数据，接收JSON格式的数据，并将其转换成对应的数据类型， 然后把数据绑定到方法参数上
    		// 请求体中数据为{id : 1, name : "Spring MVC企业应用实战"}
    		@RequestBody Book book,
    		HttpServletResponse response) throws Exception{
    	// ObjectMapper类是Jackson库的主要类。它提供一些功能执行转换：Java对象 ---> JSON字符串
    	ObjectMapper mapper = new ObjectMapper();
    	// 将book对象转换成json输出
    	System.out.println("book只有2个属性, 对象表示：" + book);
    	System.out.println("book只有2个属性, Json表示：" + mapper.writeValueAsString(book));
    	
    	book.setAuthor("杨佳");
    	
    	response.setContentType("text/html;charset=UTF-8");
    	// 将book对象转换成json写出到客户端，此时book有3个属性
    	response.getWriter().println(mapper.writeValueAsString(book));
    }

}
