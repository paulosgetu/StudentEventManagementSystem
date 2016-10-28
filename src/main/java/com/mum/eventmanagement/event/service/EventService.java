package com.mum.eventmanagement.event.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mum.eventmanagement.model.Event;
import com.mum.eventmanagement.model.EventCategory;
import com.mum.eventmanagement.model.User;

public interface EventService {

	Event findOne(int id);

	Page<Event> getEvents(Integer pageNumber);
	
	List<EventCategory> getAllCategories();
	
	void addLike(int eventId, String username);
	
	void addJoin(int eventId, String username);

	void approveJoinRequest(int eventId, String username);
	
	void cancelJoin(int eventId, String username);

	void acceptRequest(int eventId, int userid);

	void denyRequest(int eventId, int userid);

	void addEvent(Event event,String username);
	
}
