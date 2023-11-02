package com.woori.myapp;

import org.springframework.web.util.HtmlUtils;
//주고 받을 정보 먼저 추가하기 -> 일반 dto -> 필요한 필드가 있으면 추가한다. 
public class Message {
	
	private String username="";
	private String message="";
	private String userid=""; 	//JSON 만들어서 보낸다.
	
	@Override
	public String toString() {
		
		return HtmlUtils.htmlEscape(username + " " + message + " " + userid);
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public Message(String username, String message, String userid) {
		super();
		this.username = username;
		this.message = message;
		this.userid = userid;
	}
	
	// 혹시 모르니까 기본 생성자 
	public Message()
	{}
	
	
}
