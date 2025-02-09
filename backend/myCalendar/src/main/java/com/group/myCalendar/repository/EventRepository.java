package com.group.myCalendar.repository;

// import java.time.LocalDate;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.group.myCalendar.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    // 1. Ricerca per titolo
    Optional<List<Event>> findByTitle(String title);

    // 2. Ricerca esatta per data di inizio (yyyy-MM-ddThh:mm:ss...)
    @Query("SELECT e FROM Event e WHERE e.start = :start")
    Optional<Event> findByStart(@Param("start") Calendar start);

    // 3. Ricerca gli eventi del giorno (yyyy-MM-dd)
    @Query("SELECT e FROM Event e WHERE CAST(e.start AS date) = :dayOfTheYear")
    Optional<List<Event>> findByDay(@Param("dayOfTheYear") Date dayOfTheYear);

}