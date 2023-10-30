package com.likelion.chaewon.dto;

public class ScoreDto {
	
	int id=1;
	private String name="";
	private String korean="";
	private String english="";
	private String math="";
	private Double total = null;
	private Double average = null;
	
	public ScoreDto()
	{
		super();
		
	}

	public ScoreDto(int id, String name, String korean, String english, String math, Double total, Double average) {
		super();
		this.id = id;
		this.name = name;
		this.korean = korean;
		this.english = english;
		this.math = math;
		this.total = total;
		this.average = average;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKorean() {
		return korean;
	}

	public void setKorean(String korean) {
		this.korean = korean;
	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public String getMath() {
		return math;
	}

	public void setMath(String math) {
		this.math = math;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(Double average) {
		this.average = average;
	}
	
}
