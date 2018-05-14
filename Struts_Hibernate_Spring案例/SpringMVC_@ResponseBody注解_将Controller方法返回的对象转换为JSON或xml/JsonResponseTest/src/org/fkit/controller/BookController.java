package org.fkit.controller;

import java.util.ArrayList;
import java.util.List;
import org.fkit.domain.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/json")
public class BookController {
	
	
    @RequestMapping(value="/testRequestBody")
    // @ResponseBody注解用于将Controller方法返回的对象, 通过适当的消息转换器转换为指定格式后, 再写入到Response对象的body数据区(即响应体)
    // 一般在返回的数据不是html标签的页面, 而是其他某种格式的数据时(json, xml)使用. 此处作用为将List集合转换为json字符串.
    @ResponseBody
    public Object getJson() {
    	List<Book> list = new ArrayList<Book>();
    	list.add(new Book(1,"Spring MVC企业应用实战","肖文吉"));
    	list.add(new Book(2,"轻量级JavaEE企业应用实战","李刚"));
    	return list;
    }

}
