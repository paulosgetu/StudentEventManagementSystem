package com.mum.eventmanagement.event.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mum.eventmanagement.event.repository.CategoryRepository;
import com.mum.eventmanagement.event.repository.EventRepository;
import com.mum.eventmanagement.event.repository.UserRepository;
import com.mum.eventmanagement.model.Event;
import com.mum.eventmanagement.model.EventCategory;
import com.mum.eventmanagement.model.Role;
import com.mum.eventmanagement.model.User;

@Transactional
@Service
public class EventServiceImp implements EventService {
	@Autowired
	EventRepository repository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CategoryRepository categoriesRepository;
	@Autowired
	LoggedinUserService loggedinUserService;

	private static final int PAGE_SIZE = 10;

	@Override
	public Event findOne(int id) {
		return repository.findOne(id);
	}

	public Page<Event> getEvents(Integer pageNumber) {
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.DESC, "startTime");
		return repository.findAll(request);
	}


	@Override
	public List<EventCategory> getAllCategories() {
		// TODO Auto-generated method stub
		return categoriesRepository.findAll();
	}

	@PreAuthorize("#username == authentication.name")
	public void addLike(int eventId, String username) {
		User user = loggedinUserService.findByEmail(username);
		Event event = findOne(eventId);
		event.like(user);
		repository.save(event);
	}

	@PreAuthorize("#username == authentication.name")
	public void addJoin(int eventId, String username) {
		User user = loggedinUserService.findByEmail(username);
		Event event = findOne(eventId);
		event.addJoinRequest(user);
		repository.save(event);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void approveJoinRequest(int eventId, String username) {
		User user = loggedinUserService.findByEmail(username);
		Event event = findOne(eventId);
		event.approveJoinRequest(user);
		repository.save(event);
	}

	@PreAuthorize("#username == authentication.name")
	@Override
	public void cancelJoin(int eventId, String username) {
		// TODO Auto-generated method stub
		User user = loggedinUserService.findByEmail(username);
		Event event = findOne(eventId);
		event.cancelJoinRequest(user);
		repository.save(event);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Override
	public void acceptRequest(int eventId, int userid) {
		// TODO Auto-generated method stub
		User user = userRepository.findOne(userid);
		Event event = findOne(eventId);
		event.approveJoinRequest(user);
		repository.save(event);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Override
	public void denyRequest(int eventId, int userid) {
		User user = userRepository.findOne(userid);
		Event event = findOne(eventId);
		event.denyJoinRequest(user);
		repository.save(event);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")	
	@Override
	public void addEvent(Event newEvent, String requesterUsername) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = loggedinUserService.findByEmail(username);
		newEvent.setCreatedBy(user);
		//if the user adding the event has ROLE_ADMIN role the event will be automatically approved
		if (user.getRole().equals(Role.ROLE_ADMIN)) {
			newEvent.setApprovedBy(user);
		}
		repository.save(newEvent);				
	}

}
