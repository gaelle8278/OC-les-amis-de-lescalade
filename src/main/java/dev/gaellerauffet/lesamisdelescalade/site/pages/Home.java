package dev.gaellerauffet.lesamisdelescalade.site.pages;

import javax.inject.Named;


@Named
public class Home {
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String showHello() {
		return "Hello";
	}
}
