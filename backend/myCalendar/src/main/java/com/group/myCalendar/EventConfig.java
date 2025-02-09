package com.group.myCalendar;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.group.myCalendar.model.Event;
import com.group.myCalendar.repository.EventRepository;

@Configuration
public class EventConfig {
    @Bean
    CommandLineRunner commandLineRunner(EventRepository repository) {
        return args -> {
            Calendar start1 = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
            start1.add(Calendar.SECOND, 2);
            Calendar end1 = (Calendar) start1.clone();
            end1.add(Calendar.MINUTE, 45); // Evento di 45 minuti
            Event shortEvent = new Event("Short Meeting", start1, end1);

            Calendar start2 = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
            start2.add(Calendar.SECOND, 10);
            Calendar end2 = (Calendar) start2.clone();
            end2.add(Calendar.DATE, 1); // Evento di 1 giorno
            Event oneDayEvent = new Event("One-Day Conference", "Conference", start2, end2);

            Calendar start3 = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
            start3.add(Calendar.SECOND, 15);
            Calendar end3 = (Calendar) start3.clone();
            end3.add(Calendar.HOUR, 2); // Evento di 2 ore
            Event workshop = new Event("Workshop", "Hands-on coding session", start3, end3);

            Calendar start4 = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
            start4.add(Calendar.SECOND, 20);
            Calendar end4 = (Calendar) start4.clone();
            end4.add(Calendar.HOUR, 5); // Evento di 5 ore
            Event seminar = new Event("Seminar Meeting", "Educational seminar on Java", start4, end4);

            Calendar start5 = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
            start5.add(Calendar.SECOND, 25);
            Calendar end5 = (Calendar) start5.clone();
            end5.add(Calendar.DAY_OF_MONTH, 7); // Evento di una settimana
            Event weekLongEvent = new Event("Week-Long Event", "Bootcamp for developers", start5, end5);

            Calendar start6 = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
            start6.add(Calendar.SECOND, 30);
            Calendar end6 = (Calendar) start6.clone();
            end6.add(Calendar.DAY_OF_MONTH, 2); // Evento di 2 giorni
            Event hackathon = new Event("Hackathon", "Competitive coding event", start6, end6);

            Calendar start7 = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
            start7.add(Calendar.SECOND, 35);
            Calendar end7 = (Calendar) start7.clone();
            end7.add(Calendar.MINUTE, 15); // Evento di 15 minuti
            Event standupMeeting = new Event("Daily Standup", "Team daily progress meeting", start7, end7);

            Calendar start8 = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
            start8.add(Calendar.DAY_OF_YEAR, 10);
            Calendar end8 = (Calendar) start8.clone();
            end8.add(Calendar.HOUR, 12); // Evento di mezza giornata
            Event teamBuilding = new Event("Team Building Event", "Activities to build team spirit", start8, end8);

            Calendar start9 = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
            start9.add(Calendar.SECOND, 45);
            Calendar end9 = (Calendar) start9.clone();
            end9.add(Calendar.DAY_OF_MONTH, 30); // Evento di un mese
            Event monthlyChallenge = new Event("Monthly Challenge", "Challenge for employee productivity", start9,
                    end9);

            Calendar start10 = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"));
            start10.add(Calendar.DAY_OF_YEAR, 2);
            Calendar end10 = (Calendar) start10.clone();
            end10.add(Calendar.YEAR, 1); // Evento di un anno
            Event yearLongProject = new Event("Year-Long Project", "Important company project", start10, end10);

            // Salva tutti gli eventi nel database
            // repository.saveAll(List.of(
            //         shortEvent,
            //         oneDayEvent,
            //         workshop,
            //         seminar,
            //         weekLongEvent,
            //         hackathon,
            //         standupMeeting,
            //         teamBuilding,
            //         monthlyChallenge,
            //         yearLongProject));
        };
    }
}
