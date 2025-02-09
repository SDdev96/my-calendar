package com.group.myCalendar.service;

import java.text.*;
import java.util.*;

// import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.myCalendar.model.Event;
import com.group.myCalendar.repository.EventRepository;

import jakarta.transaction.Transactional;

@Service
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Optional<Event> getEventById(Long eventId) {
        return eventRepository.findById(eventId);
    }

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getEventsByTitle(String title) {
        return eventRepository.findByTitle(title)
                .orElseThrow(() -> new IllegalStateException("No events found with title: " + title));
    }

    public List<Event> getEventsByDay(String dayOfTheYear) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date day = new Date(sdf.parse(dayOfTheYear).getTime());

            return eventRepository.findByDay(day)
                    .orElseThrow(() -> new IllegalStateException("No events found in the day: " + day));
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formato data non valido, usa yyyy-MM-dd");
        }
    }

    public void addNewEvent(Event event) {
        Optional<Event> eventOptional = eventRepository.findByStart(event.getStart());
        if (eventOptional.isPresent()) {
            throw new IllegalStateException("This date has already been taken");
        }
        eventRepository.save(event);
    }

    public void deleteEvent(Long eventId) {
        boolean exists = eventRepository.existsById(eventId);
        if (!exists) {
            throw new IllegalStateException("Event with id " + eventId + "does not exist");
        }
        eventRepository.deleteById(eventId);
    }

    @Transactional
    public void updateEvent(Long eventId, String eventTitle, String eventDescription, Calendar eventStart,
            Calendar eventEnd) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new IllegalStateException(
                "Event with id " + eventId + " does not exist"));

        if (!Objects.equals(event.getTitle(), eventTitle)) {
            event.setTitle(eventTitle);
        }

        if (!Objects.equals(event.getDescription(), eventDescription)) {
            event.setDescription(eventDescription);
        }

        if (!Objects.equals(event.getStart(), eventStart)) {
            event.setStart(eventStart);
        }

        if (!Objects.equals(event.getEnd(), eventEnd)) {
            event.setEnd(eventEnd);
        }
    }
}
