package com.example.hello;

//Dto - Data Transfer Object
//테이블에 데이터를 넣거나 가져오기 위한 용도로, 보통 하나의 테이블당 하나의 Dto를 만든다
//join의 경우 join이되는 항목을 다 가져와야 함. 따라서 여러 개의 테이블로부터 Dto가 만들어 짐 -> read-only -> VO를 붙이는 경우가 많다.
//읽고 쓰기 용으로 사용하는 클래스의 경우 > DTO를 붙인다.

public class PersonDto {
	
	private int id=1;
	
	private String name="";
	
	private String phone="";
	
	private String email="";
	
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
	
	//생성자 만들 때 주의사항 : 매개변수가 있는 생성자를 만들면 시스템이 만들던 기본 생성자를 안 만든다. 
	// 어디선가 객체 생성을 할 때 PersonDto dto = new PersonDto();
	public PersonDto(int id, String name, String phone, String email) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	
	public PersonDto() {
		super(); // 부모 생성자 호출한다.
	}
	
}
