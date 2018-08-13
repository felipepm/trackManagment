package com.pm.trackManagment.repository.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="talk")
public class TalkDomain {
	
	@Id
	@GeneratedValue
	private Long id;

	private String name;
	
	private Integer duration;


	public TalkDomain() {
		super();
	}

	public TalkDomain(Long id, String name, Integer duration) {
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
