package com.woori.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	// http://www.daum.net => http://127.0.0.1:9000
	@GetMapping("/")
	public String index(Model model) {
		// model 객체가 컨트롤러부터 => html 값을 전달하는 객체이다.
		
		model.addAttribute("myname", "채원"); //해쉬맵처럼 키와 값을 사용 
		//request.setAttribute로 사용했다.
		return "index"; //templates/index.html을 호출한다. 
	}
	
	// GET방식 - header(아주 많은 정보)만 보낸다. 
	// POST방식 - header를 먼저 보내고, 나머지 데이터는 body에 태워보낸다.
	// http://127.0.0.1:9000/add?x=5&y=9
	@GetMapping("/add")
	public String add(Model model, int x, int y) {	
		
		int result = x+y;
		
		model.addAttribute("x", x);		// {{x}}
		model.addAttribute("y", y);		// {{y}}
		model.addAttribute("result", result);
		
		return "add_result"; //templates/add_result.html 파일로 전송 
	}
	
	// 입력용 html문서를 화면에 띄운다.
	@GetMapping("/input")
	public String input() {
		return "input";
	}
	
	// 결과를 처리할 url 처리 
	// 변수를 int x, int y 로 만들어도 되지만 
	// 클래스를 만들어서 객체를 만들어도 된다. -> class Data{int x, int y} Data ~라고 
	@GetMapping("/output")
	public String output(Model model, int x, int y, int z) {
		
		model.addAttribute("x", x);	//model 객체 => 컨트롤러부터 뷰로 값을 전달하기 위한 객체 
		model.addAttribute("y", y);
		model.addAttribute("z", z);
		
		return "output";
	}
}
