package com.example.hello;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiveController {
	
	//add?x=5&y=7
	@GetMapping("/add")
	public String add(int x, int y) {
		
		int result = x+y;
		return String.format("%d + %d = %d", x, y, result);
	}
	
	//add2/5/7
	@GetMapping("/add2/{x}/{y}")
	public String add2(@PathVariable("x") int x, @PathVariable("y") int y) {
		
		int result = x+y;
		return String.format("%d + %d = %d", x, y, result);
	}
	

	// <문제 1 처음 시도>
	// url : gugu/4 
	// -> 문자열로 할 경우 속도가 더 느리다
	//	@GetMapping("/gugu/4")
	//	public String gugu() {
	//	
	//		int dan=4
	//		StringBuilder result = new StringBuilder();
	//		
	//		for (int i =1; i <=9; i++) {
	//			result.append(dan + "x" + i + "=" + (dan * i) + "\n");
	//		}
	//		
	//		return result.toString();
	//	}
	
		// StringBuffer와 StringBuilder의 차이는 스레드 동기화 지원 유무 
		// StringBuilder는 스레드 동기화를 지원하지 않는다.
	
	    // <멀티스레드> 
		// : 프로세스를 작은 조각으로 만든다. (각 조각이 스레드가 될 수 있다)
		// : 멀티스레드는 하나의 프로세스를 쪼개서 사용하기 때문에, 동일한 자원(변수)에 동시에 접근하는 문제가 발생한다.
		// <스레드에 대해>
		// : 자바의 경우에는 Thread 라는 클래스를 상속받거나 Runnable 인페이스를 구현하거나 함수를 독립적으로 동작시킬 수 있다.
		// : 네트워크를 사용하거나 용량이 엄청 큰 파일을 읽거나 DB데이터를 처리할 때 스레드를 사용한다.
		// : 스레드 안정성, 동기화 (동시성 문제 해결을 위해 동기화를 지원하고, 동기화를 지원함으로써 쓰레드 안정성을 확보한다)
	
		// -> 따라서 동기화 지원 안한다 -> 스레드 안정성이 떨어진다는 이야기이라서 사용을 피해야 한다.
		// -> 따라서 StringBuilder는 스레드 동기화를 지원하지 않기 때문에, StringBuilder를 사용하자!
		// ex.HashTable -> HahMap
	
	// <문제 1 수정> 
	// url : gugu/4
	@GetMapping("/gugu/{dan}")
	public String gugu(@PathVariable("dan") int dan) {
	
		// String s = "a" + "b" + "c"
		StringBuffer buffer = new StringBuffer();
		
		for (int i =1; i <=9; i++) {
			buffer.append(String.format("%d x %d = %d<br/>", dan, i, dan*i));
		}
		
		return buffer.toString();
	}
	
	// <문제 2>
	// url : gugu2?dan=4
	//	@GetMapping("/gugu2")
	//	public String gugu2(@RequestParam(name="dan") int dan) {
	//	
	//		StringBuilder result = new StringBuilder();
	//		
	//		for (int i =1; i <=9; i++) {
	//			result.append(dan + "x" + i + "=" + (dan * i) + "\n");
	//		}
	//		
	//		return result.toString();
	//	}
	
	
	// <문제 2 수정>
	// url : gugu2?dan=4
	@GetMapping("/gugu2")
	public String gugu2(int dan) {
	
		StringBuffer buffer = new StringBuffer();
		
		for (int i =1; i <=9; i++) {
			buffer.append(String.format("%d x %d = %d<br/>", dan, i, dan*i));
		}
		
		return buffer.toString();
	}
	
	// <POST 방식 3가지>
	// post방식은 html 페이지를 만들어서 post 전송을 하거나 별도의 툴 도움을 받아야 한다. -> postman, insomnia에서 확인 
	// curl : 윈도우, 리눅스 둘 다 가능하다.
	
	// 1. x-www-form-urlencoded : form태그에 post 방식으로 보낼 때
	// 2. form-data: form 태그에 post, enctype="multipart/form-data"
	//	          파일 태그가 작동을 하고 spring에서는 request 객체가 아니고,
	//	          별도의 multipartrequest 객체가 따로 있다
	// 3. raw - json 형식
	@PostMapping("/add_post")
	public String add_post(int x, int y) {
		return String.format("%d + %d = %d", x, y, x+y);
	}
	
	
	// json으로 값을 받기 위해서 @RequestBody 작성 
	// 자바스크립트의 fetch, axios 라이브러리를 주고 받을 때 이런 식으로 사용할 것 
	@PostMapping("/add_json") 
	public String add_json(@RequestBody HashMap<String, String> map) {
		int x =Integer.parseInt(map.get("x"));
		int y =Integer.parseInt(map.get("y"));
		
		return String.format("%d + %d = %d", x, y, x+y);
	}
	
	
	// <문제 3>
	// url : score_json 
	// 전송데이터 : json
	// { "name":"홍길동", "kor":90, "eng":80, "mat":70 }
	@PostMapping("/score_json")
	public HashMap<String, Object> score_json(@RequestBody HashMap<String, String> map) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
	    
		resultMap.put("name", map.get("name"));
		resultMap.put("kor", map.get("kor"));
		resultMap.put("eng", map.get("eng"));
		resultMap.put("math", map.get("math"));

		int total = Integer.parseInt(map.get("kor")) 
					+ Integer.parseInt(map.get("eng"))
					+ Integer.parseInt(map.get("math"));
		
		int avg = total/3;
		
		resultMap.put("total", total);
		resultMap.put("avg", avg);
		
	    return resultMap;
	}
	
	// <문제 4>
	// url : trade_json
	// 전송데이터 : json
	// {
	//	"trade_id":"100", 
	//	"trade":[
	//		{"payment":100}, 
	//		{"payment":200}, 
	//		{"payment":150}, 
	//		{"payment":2700}, 
	//		{"payment":190}
	//	  ] 
	//  }
	@PostMapping("/trade_json")
    public HashMap<String, Object> trade_json(@RequestBody HashMap<String, Object> map) {
		
        @SuppressWarnings("unused") // 경고 무시해주는 어노테이션 
		String trade_id = map.get("trade_id").toString();
        
        @SuppressWarnings("unchecked") // 경고 무시해주는 어노테이션 
		List<HashMap<String, Object>> tradeList = 
        		(List<HashMap<String, Object>>)map.get("trade");

        int sum = 0;
        for (int i=0; i<tradeList.size(); i++) {
        	sum += Integer.parseInt(tradeList.get(i).get("payment").toString());
        }
        
        map.put("sum", sum);
   
        return map;
    }
	
	

}
