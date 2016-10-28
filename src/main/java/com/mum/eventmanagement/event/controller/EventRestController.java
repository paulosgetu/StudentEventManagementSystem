package com.mum.eventmanagement.event.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mum.eventmanagement.event.service.EventService;
import com.mum.eventmanagement.event.service.LoggedinUserService;
import com.mum.eventmanagement.model.Event;
import com.mum.eventmanagement.model.User;

@RequestMapping("/event")
@RestController
public class EventRestController {

	@Autowired
	EventService eventService;
	@Autowired
	LoggedinUserService loggedinUserService;

	@RequestMapping(value = "/event/like", method = RequestMethod.POST)
	public void addLike(@RequestParam int eventId, @RequestParam String username) {
		this.eventService.addLike(eventId, username);
	}

	@RequestMapping(value = "/event/join", method = RequestMethod.POST)
	public void addJoinRequest(@RequestParam int eventId, @RequestParam String username) {
		this.eventService.addJoin(eventId, username);
	}

	@RequestMapping(value = "/event/cancelJoin", method = RequestMethod.POST)
	public void cancelJoinRequest(@RequestParam int eventId, @RequestParam String username) {
		this.eventService.cancelJoin(eventId, username);
	}

	@RequestMapping(value = "/event/accept", method = RequestMethod.POST)
	public void acceptRequest(@RequestParam int eventId, @RequestParam int userId) {
		System.out.println("Eror is here");
		this.eventService.acceptRequest(eventId,userId);		
	}
	@RequestMapping(value = "/event/deny", method = RequestMethod.POST)
	public void denyRequest(@RequestParam int eventId, @RequestParam int userId) {
		System.out.println("Eror is here");
		this.eventService.denyRequest(eventId,userId);		
	}

}
