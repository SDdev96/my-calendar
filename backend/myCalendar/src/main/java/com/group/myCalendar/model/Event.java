package com.group.myCalendar.model;

import java.util.Calendar;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @SequenceGenerator(name = "event_sequence", sequenceName = "event_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_sequence")
    private Long id;

    @NotNull(message = "Title cannot be null")
    @Size(min = 1, message = "Title cannot be empty")
    @Column(nullable = false)
    private String title;

    @Size(max = 100, message = "Description cannot exceed 100 characters")
    @Column
    private String description;

    @NotNull(message = "Start date cannot be null")
    @Future(message = "Start date must be later than now")
    @Column(name = "start_time", unique = true)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Calendar start;

    @NotNull(message = "End date cannot be null")
    @Future(message = "End date must be later than now")
    @Column(name = "end_time", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Calendar end;

    @Transient
    private Long durationInMillis;

    // CONSTRUCTORS
    public Event() {
    }

    public Event(String title, Calendar start, Calendar end) {
        setTitle(title);
        setStart(start);
        setEnd(end);
    }

    public Event(String title, String description, Calendar start, Calendar end) {
        this(title, start, end);
        setDescription(description);
    }

    // Test only
    public Event(Long id, String title, String description, Calendar start, Calendar end) {
        this(title, description, start, end);
        this.id = id;
    }

    // GETTERS
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Calendar getStart() {
        return start;
    }

    public Calendar getEnd() {
        return end;
    }

    public Long getDurationInMillis() {
        return this.end.getTimeInMillis() - this.start.getTimeInMillis();
    }

    // SETTERS
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().length() == 0) {
            throw new IllegalArgumentException("Title cannot be empty or null");
        }
        this.title = title;
    }

    public void setDescription(String description) {
        if (description != null && description.length() > 100) {
            throw new IllegalArgumentException("Description length cannot be more than 100 characters");
        }
        this.description = (description == null || description.trim() == "") ? null : description;
    }

    public void setStart(Calendar start) {
        final Calendar now = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
        if (start.getTimeInMillis() < now.getTimeInMillis() - 1000 * 60) {
            throw new IllegalArgumentException("Start date cannot be in the past");
        }

        this.start = start;
    }

    public void setEnd(Calendar end) {
        if (end.getTimeInMillis() < start.getTimeInMillis())
            throw new IllegalArgumentException("End date cannot be before start date");

        this.end = end;
    }

    public void setDurationInMillis(Long durationInMillis) {
        this.durationInMillis = durationInMillis;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title=" + title +
                ", description=" + description +
                ", start=" + (start != null ? start.getTime() : "null") +
                ", end=" + (end != null ? end.getTime() : "null") +
                ", duration=" + formattedDuration() +
                '}';
    }

    public String formattedDuration() {
        StringBuilder formattedDuration = new StringBuilder();
        long duration = end.getTimeInMillis() - start.getTimeInMillis();

        long years = duration / (365L * 24 * 60 * 60 * 1000); // 1 anno = 365 giorni
        duration %= (365L * 24 * 60 * 60 * 1000);

        long months = duration / (30L * 24 * 60 * 60 * 1000); // 1 mese = 30 giorni
        duration %= (30L * 24 * 60 * 60 * 1000);

        long days = duration / (24 * 60 * 60 * 1000);
        duration %= (24 * 60 * 60 * 1000);

        long hours = duration / (60 * 60 * 1000);
        duration %= (60 * 60 * 1000);

        long minutes = duration / (60 * 1000);

        if (years > 0)
            formattedDuration.append(years).append("Y");

        if (months > 0)
            formattedDuration.append(months).append("M");

        if (days > 0)
            formattedDuration.append(days).append("D");

        if (hours > 0)
            formattedDuration.append(hours).append("h");

        if (minutes > 0)
            formattedDuration.append(minutes).append("m");

        return formattedDuration.length() == 0 ? "0" : formattedDuration.toString();
    }

    public static void main(String[] args) {

        try {
            // Caso 1: Evento di pochi minuti
            Calendar start1 = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
            System.out.println(start1.getTime());
            Calendar end1 = (Calendar) start1.clone();
            end1.add(Calendar.MINUTE, 45); // Evento di 45 minuti
            Event shortEvent = new Event("Short Meeting", start1, end1);
            System.out.println("Caso 1: Evento breve");
            System.out.println(shortEvent);
        } catch (IllegalArgumentException e) {
            System.out.println("Errore nel caso 1: " + e.getMessage());
        }

        try {
            // Caso 2: Evento di un giorno intero
            Calendar start2 = Calendar.getInstance();
            Calendar end2 = (Calendar) start2.clone();
            end2.add(Calendar.DATE, 1); // Evento di 1 giorno
            Event oneDayEvent = new Event("One-Day Conference", start2, end2);
            System.out.println("\nCaso 2: Evento di un giorno");
            System.out.println(oneDayEvent);
        } catch (IllegalArgumentException e) {
            System.out.println("Errore nel caso 2: " + e.getMessage());
        }

        try {
            // Caso 3: Evento lungo (più anni)
            Calendar start3 = Calendar.getInstance();
            Calendar end3 = (Calendar) start3.clone();
            end3.add(Calendar.YEAR, 3); // Evento di 3 anni
            end3.add(Calendar.MONTH, 2); // Aggiungi 2 mesi
            end3.add(Calendar.DATE, 15); // Aggiungi 15 giorni
            Event longEvent = new Event("Long-Term Project", start3, end3);
            System.out.println("\nCaso 3: Evento lungo");
            System.out.println(longEvent);
        } catch (IllegalArgumentException e) {
            System.out.println("Errore nel caso 3: " + e.getMessage());
        }

        try {
            // Caso 4: Evento con stesso inizio e fine
            Calendar start4 = Calendar.getInstance();
            Calendar end4 = (Calendar) start4.clone(); // Fine identica all'inizio
            Event zeroDurationEvent = new Event("Instant Event", start4, end4);
            System.out.println("\nCaso 4: Evento istantaneo (durata zero)");
            System.out.println(zeroDurationEvent);
        } catch (IllegalArgumentException e) {
            System.out.println("Errore nel caso 4: " + e.getMessage());
        }

        try {
            // Caso 5: Evento futuro
            Calendar start5 = Calendar.getInstance();
            start5.add(Calendar.YEAR, 1); // Evento che inizia tra 1 anno
            Calendar end5 = (Calendar) start5.clone();
            end5.add(Calendar.MONTH, 6); // E dura 6 mesi
            Event futureEvent = new Event("Future Event", start5, end5);
            System.out.println("\nCaso 5: Evento futuro");
            System.out.println(futureEvent);
        } catch (IllegalArgumentException e) {
            System.out.println("Errore nel caso 5: " + e.getMessage());
        }

        try {
            // Caso 6: Evento passato
            Calendar start6 = Calendar.getInstance();
            start6.add(Calendar.YEAR, -2); // Evento iniziato 2 anni fa
            Calendar end6 = (Calendar) start6.clone();
            end6.add(Calendar.MONTH, 3); // E durato 3 mesi
            Event pastEvent = new Event("Past Event", start6, end6);
            System.out.println("\nCaso 6: Evento passato");
            System.out.println(pastEvent);
        } catch (IllegalArgumentException e) {
            System.out.println("\nErrore nel caso 6: " + e.getMessage());
        }

        try {
            // Caso 7: Fine prima dell'inizio
            Calendar start7 = Calendar.getInstance();
            Calendar end7 = (Calendar) start7.clone();
            end7.add(Calendar.HOUR, -1); // Evento con fine 1 ora prima dell'inizio
            Event invalidEvent = new Event("Invalid Event", start7, end7);
            System.out.println("\nCaso 7: Fine prima dell'inizio");
            System.out.println(invalidEvent);
        } catch (IllegalArgumentException e) {
            System.out.println("\nErrore nel caso 7: " + e.getMessage());
        }
    }
}
