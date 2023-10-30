package com.likelion.chaewon.service;

import java.util.List;

import com.likelion.chaewon.dto.ScoreDto;

public interface ScoreService {
	
	List<ScoreDto> getList();
	
	void insert(ScoreDto dto);
	ScoreDto getView(int id);
	
	void calculateTotalAndAverage(ScoreDto dto);

}
