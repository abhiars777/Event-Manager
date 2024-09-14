package com.eventmanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.eventmanager.models.Attendee;
import com.eventmanager.models.Event;
import com.eventmanager.services.EventService;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;
    
    @GetMapping("/")
    public String listEvents(Model model) {
        List<Event> events = eventService.listEvents();
        model.addAttribute("events", events);
        return "events";
    }

    @GetMapping("/event/{id}")
    public String viewEvent(@PathVariable Long id, Model model) {
        Event event = eventService.getEvent(id);
        model.addAttribute("event", event);
        return "event-details";  // Refers to 'event-details.html'
    }

    @GetMapping("/create-event")
    public String createEventForm(Model model) {
        model.addAttribute("event", new Event());
        return "create-event";  // Refers to 'create-event.html'
    }

    @PostMapping("/create-event")
    public String createEvent(@ModelAttribute Event event) {
        eventService.createEvent(event);
        return "redirect:/";
    }

    @PostMapping("/event/{id}/add-attendee")
    public String addAttendee(@PathVariable Long id, @ModelAttribute Attendee attendee) {
        eventService.addAttendee(id, attendee);
        return "redirect:/event/" + id;
    }

    @PostMapping("/event/{id}/reminder")
    public String sendReminder(@PathVariable Long id) {
        eventService.sendReminder(id);
        return "redirect:/event/" + id;
    }

}
