package com.mum.eventmanagement.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mum.eventmanagement.model.Resource;

@RequestMapping("/resource")
@Controller
public class ResourceController {

	@Autowired
	ResourceService resourseService;
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = { "/", "/add" }, method = RequestMethod.GET)
	public String addResourceForm(Resource resource, Model model) {
		// model.addAttribute("resource",new Resource());
		return "resource/add";
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String getResource(@Valid @ModelAttribute("resource") Resource resource, BindingResult result,
			RedirectAttributes red)  {
		if (result.hasErrors() || result.getErrorCount() > 0) {
			return "resource/add";
		}
		System.out.println("resou: " + resource);
		try {
			resourseService.save(resource);
			System.out.println("saved");
		} catch (Exception up) {
			up.printStackTrace();
			System.out.println("Transaction Failed!!!");
		}

		red.addFlashAttribute("resource", resource);
		return "redirect:/resource/added";
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/added", method = RequestMethod.GET)
	public String added() {
		return "resource/added";
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listEmployees(Resource resource, Model model) {

		List<Resource> list = resourseService.findAll();
		model.addAttribute("resource", list);

		return "resource/list";
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/listByName", method = RequestMethod.GET)
	public String searchByName(Model model, @RequestParam("name") String resourceName) throws Exception {
		List<Resource> list = resourseService.findByName(resourceName);
		model.addAttribute("resource", list);
		return "resource/listByName";
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/listByType", method = RequestMethod.GET)
	public String searchByType(Model model, @RequestParam("type") String resourceType) throws Exception {
		List<Resource> list = resourseService.findByType(resourceType);
		model.addAttribute("resource", list);
		return "resource/listByType";
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/sidebar", method = RequestMethod.GET)
	public String sidebar() {
		return "resource/sidebar";
	}

}
