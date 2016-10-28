package com.mum.eventmanagement.event.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mum.eventmanagement.event.service.EventService;
import com.mum.eventmanagement.event.service.LoggedinUserService;
import com.mum.eventmanagement.model.Event;
import com.mum.eventmanagement.model.EventCategory;
import com.mum.eventmanagement.model.Role;
import com.mum.eventmanagement.model.User;
import com.mum.eventmanagement.validator.DateValidator;

@RequestMapping("/event")
@Controller
// @SessionAttributes("user")
public class EventController {
	@Autowired
	EventService eventService;

	@Autowired
	DateValidator dateValidator;

	@RequestMapping(value = "/events")
	public String events(Model model) {
		int pageNumber = 1;

		Page<Event> page = eventService.getEvents(pageNumber);

		int current = page.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, page.getTotalPages());

		model.addAttribute("eventsList", page);
		model.addAttribute("currentIndex", current);
		return "/event/events";
	}

	@RequestMapping("/joinRequests")
	public String joinRequests() {
		return "/event/joinRequests";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addForm(@ModelAttribute @Valid Event newEvent, BindingResult result, Model model,
			RedirectAttributes redirect, HttpServletRequest request) {
		
		System.out.println(newEvent.getImage());
		
		dateValidator.validate(newEvent, result);
		if (result.hasErrors()) {
			return "/event/add";
		} else {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			eventService.addEvent(newEvent,username);			
			MultipartFile image = newEvent.getImage();
			String rootDirectory = request.getServletContext().getRealPath("/");

			System.out.println(rootDirectory);
			
			if (image != null && !image.isEmpty()) {
				try {
					image.transferTo(new File(rootDirectory + "resources\\images\\" + newEvent.getId() + ".jpg"));
				} catch (Exception e) {
					throw new RuntimeException("Event Image saving failed", e);
				}
			}
			redirect.addAttribute("success", "Event Successfully added.");
			return "redirect:/event/add";
		}
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("newEvent", new Event());
		List<EventCategory> categories = eventService.getAllCategories();
		model.addAttribute("categories", categories);
		return "/event/add";
	}

	@RequestMapping(value = "/event/{id}", method = RequestMethod.GET)
	public String event(@PathVariable("id") int eventId, Model model) {
		Event event = this.eventService.findOne(eventId);
		model.addAttribute("event", event);
		return "/event/event";
	}

	@RequestMapping(value = "/events/{pageNumber}", method = RequestMethod.GET)
	public String getRunbookPage(@PathVariable Integer pageNumber, Model model) {
		Page<Event> page = eventService.getEvents(pageNumber);

		int current = page.getNumber() + 1;
		int begin = Math.max(1, current - 1);
		int end = Math.min(begin + 10, page.getTotalPages());

		model.addAttribute("eventsList", page);
		model.addAttribute("currentIndex", current);
		return "/event/events";
	}

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	public DateValidator getDateValidator() {
		return dateValidator;
	}

	public void setEventValidator(DateValidator dateValidator) {
		this.dateValidator = dateValidator;
	}

}
