package com.woori.myapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.woori.myapp.dto.PersonDto;
import com.woori.myapp.service.PersonService;

@Controller
public class PersonController {
	
	//DI 상황 
	@Autowired
	PersonService service;
	
	@GetMapping("/person/list")
	public String person_list(Model model) {
		List<HashMap<String, Object>> sList = 
				new ArrayList<HashMap<String, Object>>();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name","홍길동");
		map.put("age", 23);
		sList.add(map);
		
		map = new HashMap<String, Object>();
		map.put("name","임꺽정");
		map.put("age", 27);
		sList.add(map);
		
		model.addAttribute("title", "머스티치 for문 연습");
		model.addAttribute("sList", sList);
		
		return "person_list"; // 함수의 반환값은 string이어야 함. -> @Controller 일 때 
	}
	
	@GetMapping("/person/list2")
	public String person_list2(Model model) {
		
		// 서비스를 통해서 데이터를 가져와서 모델에 저장해서 보낸다.
		model.addAttribute("personList", service.getList());
		return "person_list2";
	}

	@GetMapping("/person/write")
	public String person_write() {
		return "person_write";
	}
	
	@PostMapping("/person/save")
	public String person_save(PersonDto dto) {
		service.insert(dto);
		// redirect => html 페이지에 데이터를 입력 => save에서 저장
		// person_list() 직접 호출 가능하다. 근데 /person/list url
		// redirect:/person/list => /person/list 
		// ajax => json 데이터만 보내고 말아야 한다. -> 따라서 받는 쪽에서 자바스크립트를 사용해서 전달한다. (페이지 이동을 시켜야 함)  
		return "redirect:/person/list2";
	}
	
	// /person/view/1
	@GetMapping("/person/view/{id}")
	public String person_view(Model model, @PathVariable("id") int id) {
		model.addAttribute("view", service.getView(id));
		return "person_view";
	}
	
	@PostMapping("/person/save2")
	@ResponseBody
	// 기본적으로 $.ajax 는 자기가 데이터를 보낼 때는 Json이 아니고,
	// text로 보내서 수신할 때 PersonDto로 받아도 된다.
	// 그러나 fetch 함수나 axios는 디폴트로 json으로 받기 때문에 
	// 파라미터 앞에 @RequestBody를 써줘야 한다.
	public HashMap<String, Object> person_save2(PersonDto dto) {
		
		service.insert(dto);
		
		HashMap<String, Object> resultMap = 
				new HashMap<String, Object>();
		
		resultMap.put("result", "success");
		resultMap.put("name", dto.getName());
		resultMap.put("phone", dto.getPhone());
		resultMap.put("email", dto.getEmail());
		
		return resultMap;
	}
	
}
