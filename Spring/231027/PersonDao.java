package com.woori.myapp.dao;

import java.util.List;

import com.woori.myapp.dto.PersonDto;

// 객체 생성을 못한다. => 가급적 클래스간의 연결고리는 인터페이스로 하자 
// MVC : M-model (디비에 데이터 읽고 쓰기)
// 보통 dao는 테이블 하나당 만든다 
public interface PersonDao {
	
	List<PersonDto> getList(); // 함수의 header 부분만 기술하고, 실제 내용은 클래스에 기술한다.
	
	void insert(PersonDto dto);
	PersonDto getView(int id);
}
