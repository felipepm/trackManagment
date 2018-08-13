package com.pm.trackManagment.service.dto;

public class TalkDto {

	private Long id;

	private String name;
	
	private Integer duration;
	

	public TalkDto(Long id, String name, Integer duration) {
		super();
		this.id = id;
		this.name = name;
		this.duration = duration;
	}
	
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getDuration() {
		return duration;
	}
	
}
