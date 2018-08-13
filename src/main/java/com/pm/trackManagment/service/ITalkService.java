package com.pm.trackManagment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pm.trackManagment.service.dto.TalkDto;

@Service
public interface ITalkService {

	public TalkDto create(TalkDto talkDTO);
	
	public TalkDto update(TalkDto talkDTO);
	
	public void delete(long id);
	
	public TalkDto get(long id);
	
	public List<TalkDto> list();
}
