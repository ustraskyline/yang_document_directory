package cn.itcast.action;

import com.opensymphony.xwork2.ActionSupport;

//Action类：继承ActionSupport类重写execute()
public class HelloWorldAction extends ActionSupport {
	
	public String execute() {
		return SUCCESS;
	}
}


