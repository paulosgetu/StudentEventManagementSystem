package com.mum.eventmanagement.event;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import com.mum.eventmanagement.model.Event;

public class EventControllerTest {

	@Test
	public void shouldShowPagedEvents() throws Exception {
		/*
		 * List<Event> expectedEvents = createEventList(5); EventRepository
		 * mockRepository = mock(EventRepository.class);
		 * when(mockRepository.findEvents(238900,
		 * 50)).thenReturn(expectedEvents); EventController controller = new
		 * EventController(mockRepository); MockMvc mockMvc =
		 * standaloneSetup(controller) .setSingleView(new
		 * InternalResourceView("/WEB-INF/views/Events.jsp")).build();
		 * mockMvc.perform(get("/Events?max=238900&count=50")).andExpect(view().
		 * name("Events")) .andExpect(model().attributeExists("EventList"))
		 * .andExpect(model().attribute("EventList",
		 * hasItems(expectedEvents.toArray())));
		 */ }
}
