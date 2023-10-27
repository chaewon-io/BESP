package com.woori.myapp.dto;

// dto - db 테이블의 데이터를 담아두기 위한 용도이다.

public class PersonDto {

	int id=1;
	private String name="";
	private String phone="";
	private String email="";
	
	public PersonDto() //기본생성자 
	{
		super();
		
	}
	
	public PersonDto(int id, String name, String phone, String email) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	
}
