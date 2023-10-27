package com.woori.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyInfoController {
	
	// <문제1>
	// controller : MyInfoController
	// url : /myname_input		이름 과 전화번호 입력 받아서 
	// url : /myname_output 	박채원님의 전화번호는 010-0000-0001 입니다. 

	
	@GetMapping("/myname_input")
	public String myname_input() {
		return "myname_input";
	}
	
	@GetMapping("/myname_output")
	public String myname_output(Model model, String name, String phone) {
		
		model.addAttribute("name", name); 
		model.addAttribute("phone", phone);
		
		return "myname_output";
	}
}
