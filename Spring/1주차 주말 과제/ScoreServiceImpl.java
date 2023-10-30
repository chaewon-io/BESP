package com.likelion.chaewon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.likelion.chaewon.dao.ScoreDao;
import com.likelion.chaewon.dto.ScoreDto;

@Service
public class ScoreServiceImpl implements ScoreService {
	
	@Autowired
	ScoreDao dao;

	@Override
	public List<ScoreDto> getList() {
		return dao.getList();
	}

	@Override
	public void insert(ScoreDto dto) {
		dao.insert(dto);
		
	}

	@Override
	public ScoreDto getView(int id) {
		return dao.getView(id);
	}

    @Override
    public void calculateTotalAndAverage(ScoreDto dto) {
        double korean = Double.parseDouble(dto.getKorean());
        double english = Double.parseDouble(dto.getEnglish());
        double math = Double.parseDouble(dto.getMath());
        
        double total = korean + english + math;
        double average = total / 3.0;
        
        dto.setTotal(total);
        dto.setAverage(average);
    }

}
