package com.woori.myapp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.woori.myapp.common.Pager;
import com.woori.myapp.entity.BoardDto;
import com.woori.myapp.repository.BoardDao;

import jakarta.annotation.Resource;

@Controller
public class BoardController {

	// 객체 생성을 xml에 기술한다.
	// 자바소스를 다시 컴파일 안해도 인터페이스로 연결하면 xml파일에서 객체 바꿔치기가 가능하다.
	// BoardDaoImpl 인터페이스가 아닌 객체로 받아오는 이유는?
	@Resource(name="boardDao")
	BoardDao dao;
	
	@GetMapping("/board/list/{pg}")
	public String board_list(Model model, BoardDto dto, 
			@PathVariable("pg") int pg) {
		
		String page = Pager.makePage(10,100,pg); // 한페이지당 표출될 데이터 개수, 전체 개수, 현재 페이지 
	    dto.setPg(pg);
	    
		List<BoardDto> list = dao.getList(dto);
//	    for (int i = 0; i < list.size(); i++) {
//	        System.out.println(list.get(i).getTitle());
//	    }
	    //model-request 객체를 스프링에서 좀 더 업그레이드 시킨 것 
	    model.addAttribute("boardList", list);
	    model.addAttribute("page", page);
	    return "/board/board_list";
	}
	
	@GetMapping("/board/view/{id}")
	public String board_view(Model model, @PathVariable("id")int id) {
	    BoardDto dto = new BoardDto();
	    dto.setSeq(id);
	    BoardDto resultDto = dao.getView(dto);
	    
	    //model-request 객체를 스프링에서 좀 더 업그레이드 시킨 것 
	    model.addAttribute("board", resultDto);
	    return "/board/board_view";
	}
	

}
