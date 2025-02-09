package com.group.myCalendar.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.group.myCalendar.model.Event;
import com.group.myCalendar.service.EventService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "api/v1/events") // http://localhost:8080/api/v1/events
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> getEvents(@RequestParam(required = false) String title,
            @RequestParam(required = false) String dayOfTheYear) {
        if (title != null) {
            return eventService.getEventsByTitle(title);
        } else if (dayOfTheYear != null) {
            return eventService.getEventsByDay(dayOfTheYear);
        } else {
            return eventService.getEvents();
        }
    }

    @GetMapping(path = "/{eventId}")
    public Optional<Event> getEventById(@PathVariable("eventId") Long eventId) {
        return eventService.getEventById(eventId);
    }

    @PostMapping
    public void regirestNewEvent(@RequestBody Event event) {
        eventService.addNewEvent(event);
    }

    @DeleteMapping(path = "{eventId}")
    public void deleteEvent(@PathVariable("eventId") Long eventId) {
        eventService.deleteEvent(eventId);
    }

    @PutMapping(path = "/{eventId}")
    public void updateEvent(
            @PathVariable("eventId") Long eventId,
            @RequestBody Event event) {
        eventService.updateEvent(eventId, event.getTitle(), event.getDescription(), event.getStart(), event.getEnd());
    }
}
