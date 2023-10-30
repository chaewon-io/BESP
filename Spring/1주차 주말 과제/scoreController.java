package com.likelion.chaewon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.likelion.chaewon.dto.ScoreDto;
import com.likelion.chaewon.service.ScoreService;


@Controller
public class ScoreController {
	
	@Autowired
	ScoreService service;
	
	@GetMapping("/score/write")
	public String score_write() {
		return "score_write";
	}
	
	@PostMapping("/score/save")
	public String score_save(ScoreDto dto) {
		
		service.calculateTotalAndAverage(dto);
		service.insert(dto);
		
		return "redirect:/score/list";
	}
	
	@GetMapping("/score/list")
	public String score_list(Model model) {
		
		model.addAttribute("scoreList", service.getList());
		return "score_list";
	}

	@GetMapping("/score/view/{id}")
	public String score_view(Model model, @PathVariable("id") int id) {
		model.addAttribute("view", service.getView(id));
		return "score_view";
	}
	
}
