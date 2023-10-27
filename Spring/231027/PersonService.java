package com.woori.myapp.service;

import java.util.List;

import com.woori.myapp.dto.PersonDto;

public interface PersonService {
	List<PersonDto> getList();
	
	void insert(PersonDto dto);
	PersonDto getView(int id);
}
