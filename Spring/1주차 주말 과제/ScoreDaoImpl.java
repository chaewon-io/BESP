package com.likelion.chaewon.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.likelion.chaewon.dto.ScoreDto;

@Repository
public class ScoreDaoImpl implements ScoreDao{
	
	List<ScoreDto> sList = new ArrayList<ScoreDto>();
	
	@Override
	public List<ScoreDto> getList() {
		return sList;
	}
	
	@Override
	public void insert(ScoreDto dto) {
		this.sList.add(dto);
		
	}
	
	@Override
	public ScoreDto getView(int id) {
		return sList.get(id-1);
	}

}
