package com.pm.trackManagment.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pm.trackManagment.exception.TalkAlreadExistsException;
import com.pm.trackManagment.exception.TalkNotFoundException;
import com.pm.trackManagment.service.ITalkService;
import com.pm.trackManagment.service.dto.TalkDto;

@RestController
public class TalkRestController {
	

	@Autowired
	private ITalkService talkService;

	@GetMapping(value="/talks",
				produces=MediaType.APPLICATION_JSON_VALUE)
	public List<TalkDto> retrieveAllTalks() {
		return talkService.list();
	}
	
	@GetMapping("/talks/{id}")
	public Resource<TalkDto> retrieveTalk(@PathVariable long id) {
		TalkDto talkDto = null;
		
		try {
			talkDto = talkService.get(id);
		} catch (TalkNotFoundException e) {
			
		}
		
		Resource<TalkDto> resource = new Resource<TalkDto>(talkDto);
		
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllTalks());

		resource.add(linkTo.withRel("all-talks"));

		return resource;
	}
	
	@DeleteMapping("/talks/{id}")
	public ResponseEntity<Object> deleteTalks(@PathVariable long id) {
		
		try {
			talkService.delete(id);
			
			return ResponseEntity.ok().build();
		} catch (TalkNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/talks")
	public ResponseEntity<Object> createTalk(@RequestBody TalkDto talkDto) {

		try {
			TalkDto savedTalk = talkService.create(talkDto);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(savedTalk.getId()).toUri();

			return ResponseEntity.created(location).build();
		} catch (TalkAlreadExistsException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/talks/{id}")
	public ResponseEntity<Object> updateStudent(@RequestBody TalkDto talkDto) {

		try {
			talkService.update(talkDto);

			return ResponseEntity.noContent().build();
		} catch (TalkNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
}
