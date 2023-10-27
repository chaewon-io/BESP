package com.woori.myapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.woori.myapp.dto.PersonDto;

//controller에서 -> 직접 dao 컨택가능 
// 				-> 테이블이 3개 이상 
//			    -> 트랜잭션 처리를 하고자 할 때 트랜잭션 처리를 컨트롤러에서 하게 되면 컨트롤러가 하는 일이 너무 많음
// 				컨트롤러 => 모델로 부터 데이터를 받아서 뷰단에 넘겨주는 일을 해야함 -> 서비스(트랜잭션 처리 등등 제반의 복잡한 가공작업을 해야함) -> 따라서 서비스가 각의 dao를 호출하는 거로 해야함 
// 				1개의 컨트롤러 => 여러개의 서비스 가질 수 있음 => 각각의 서비스가 여러 개의 dao를 소유한다.

@Repository  // 안쓰면 객체가 만들어지지 않음 -> why?
public class PersonDaoImpl implements PersonDao {

	List<PersonDto> perList = new ArrayList<PersonDto>();
	public PersonDaoImpl() {
		
		perList.add(new PersonDto(1, "홍길동", "010-0000-0001", "hong@ddd.com"));
		perList.add(new PersonDto(2, "임꺽정", "010-0000-0002", "lim@ddd.com"));
		perList.add(new PersonDto(3, "장길산", "010-0000-0003", "jang@ddd.com"));
		perList.add(new PersonDto(4, "일지매", "010-0000-0004", "lli@ddd.com"));
		perList.add(new PersonDto(5, "홍경래", "010-0000-0005", "hkl@ddd.com"));
		
	}
	@Override
	public List<PersonDto> getList() {
		
		return perList;
	}
	
	@Override
	public void insert(PersonDto dto) {
		this.perList.add(dto);
		
	}
	
	@Override
	public PersonDto getView(int id) {
		return perList.get(id-1);
		
	}

}
