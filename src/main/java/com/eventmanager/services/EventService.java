package com.eventmanager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventmanager.models.Attendee;
import com.eventmanager.models.Event;
import com.eventmanager.repo.AttendeeRepository;
import com.eventmanager.repo.EventRepository;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private AttendeeRepository attendeeRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ActivityLogService activityLogService;

    public Event createEvent(Event event) {
        activityLogService.logActivity("Created event: " + event.getTitle());
        return eventRepository.save(event);
    }

    public List<Event> listEvents() {
        return eventRepository.findAll();
    }

    public Event getEvent(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public void deleteEvent(Long id) {
        Event event = getEvent(id);
        activityLogService.logActivity("Deleted event: " + event.getTitle());
        eventRepository.deleteById(id);
    }

    public void addAttendee(Long eventId, Attendee attendee) {
        Event event = getEvent(eventId);
        attendee.setEvent(event);
        attendeeRepository.save(attendee);
        emailService.sendEmail(attendee.getEmail(), "Event RSVP Confirmation", "You have successfully RSVP'd for: " + event.getTitle());
        activityLogService.logActivity("Attendee " + attendee.getName() + " RSVP'd to event: " + event.getTitle());
    }

    public void sendReminder(Long eventId) {
        Event event = getEvent(eventId);
        for (Attendee attendee : event.getAttendees()) {
            emailService.sendEmail(attendee.getEmail(), "Event Reminder", "Reminder for event: " + event.getTitle());
        }
        activityLogService.logActivity("Reminder sent for event: " + event.getTitle());
    }
}