package com.example.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//View 엔진과 연동하기 위해 어노테이션 붙이기 
@Controller
public class MustacheController {
	
	//함수들이 string으로 리턴 -> view엔진에 있는 파일과 연동이 된다.
	@GetMapping("/mustache1")
	public String mustache1() {
		
		return "mustache1"; // templates mustache1.mustache
		// 설정 파일에서(application.properties)에서 나는 머스티치 엔진을 사용할 것이고 
		// 파일 확장자가 html로 올 때 이 것을 머스티치로 받아들이겠다고 설정하기 (mustache->html)
	}
	
	@GetMapping("/myname")
	public String myname() {
		
		return "myname"; // templates myname.html
	}
}
