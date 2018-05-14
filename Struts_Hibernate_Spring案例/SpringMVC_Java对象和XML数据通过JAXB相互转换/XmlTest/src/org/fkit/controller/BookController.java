package org.fkit.controller;

import java.io.InputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fkit.domain.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookController {
	
	private static final Log logger = LogFactory.getLog(BookController.class);
	 
	 //@RequestBody : xml数据 -> Book对象
	 @RequestMapping(value="/sendxml", method=RequestMethod.POST)  
	 public void sendxml(@RequestBody Book book) {  
		 logger.info(book);
		 System.out.println("===>" + book);
		 logger.info("接收XML数据成功");
	 }  
	 
	 //@ResponseBody : 将方法返回的对象转换成xml数据返回
	 @RequestMapping(value="/readxml", method=RequestMethod.POST)  
	 public @ResponseBody Book readXml()throws Exception { 
		 /*
		  * 解析xml数据，装配成Book对象，再通过@ResponseBody将对象转换成xml数据返回
		  */
		 // 通过JAXBContext的newInstance方法，传递一个class就可以获得一个上下文
		 JAXBContext context = JAXBContext.newInstance(Book.class);  
		 // 创建一个Unmarshall对象
		 Unmarshaller unmar = context.createUnmarshaller();  
		 InputStream is = this.getClass().getResourceAsStream("/book.xml");
		 // Unmarshall对象的unmarshal方法可以进行xml到Java对象的转换
		 Book book = (Book) unmar.unmarshal(is);  
		 logger.info(book); 
    	 return book;
	 }  

}
