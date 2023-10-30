package com.likelion.chaewon.dao;

import java.util.List;

import com.likelion.chaewon.dto.ScoreDto;


public interface ScoreDao {
	
	List<ScoreDto> getList();
	
	void insert(ScoreDto dto);
	ScoreDto getView(int id);
	
}
