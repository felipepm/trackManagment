package com.pm.trackManagment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.trackManagment.exception.TalkAlreadExistsException;
import com.pm.trackManagment.exception.TalkNotFoundException;
import com.pm.trackManagment.repository.ITalkRepository;
import com.pm.trackManagment.repository.domain.TalkDomain;
import com.pm.trackManagment.service.dto.TalkDto;

@Service
public class TalkService implements ITalkService {
	
	@Autowired
	private ITalkRepository repository;

	public TalkDto create(TalkDto talkDTO) {		
		if( getByName(talkDTO.getName()) != null ) {
			throw new TalkAlreadExistsException("Talk alread exists!");
		} else {
			TalkDomain talkDomain = parseToRepository(talkDTO);
			
			talkDomain = repository.save(talkDomain);
			
			TalkDto talkDTOSaved = parseToService(talkDomain);
			
			return talkDTOSaved;
		}
	}

	public TalkDto update(TalkDto talkDTO) {		
		if (get(talkDTO.getId()) == null) {
			throw new TalkNotFoundException("Talk not found");
		} else {
			TalkDomain talkDomain = parseToRepository(talkDTO);
			
			talkDomain = repository.save(talkDomain);
			
			TalkDto talkDTOSaved = parseToService(talkDomain);
			
			return talkDTOSaved;
		}
	}

	public void delete(long id) {
		TalkDto talkDto = get(id);
		
		repository.delete(talkDto.getId());
	}
	
	public TalkDto getByName(String name) {
		TalkDomain talkDomain = repository.findByName(name);
		return parseToService(talkDomain);
	}

	public TalkDto get(long id) {
		TalkDomain talkDomain = repository.findOne(id);
		
		if (talkDomain == null) {
			throw new TalkNotFoundException("Talk not found");
		} else {
			TalkDto talkDTOOut = parseToService(talkDomain);
			
			return talkDTOOut;
		}
	}

	public List<TalkDto> list() {
		List<TalkDomain> talkDomainList = repository.findAll();
		
		List<TalkDto> talkDTOList = new ArrayList<TalkDto>();
		for (TalkDomain talkDomainOut : talkDomainList) {
			TalkDto talkDTOOut = parseToService(talkDomainOut);
			talkDTOList.add(talkDTOOut);
		}

		return talkDTOList;
	}

	private TalkDto parseToService(TalkDomain talkDomain) {
		TalkDto talkDTO = new TalkDto(talkDomain.getId(), talkDomain.getName(), talkDomain.getDuration());
		return talkDTO;
	}
	
	private TalkDomain parseToRepository(TalkDto talkDTO) {
		TalkDomain talkDomain = new TalkDomain(talkDTO.getId(), talkDTO.getName(), talkDTO.getDuration());
		return talkDomain;
	}
	
}
