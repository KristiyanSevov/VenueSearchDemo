package com.demo.venue_search.repositories;

import com.demo.venue_search.models.entities.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Integer> {

    //for older version of the solution, unused now
    @Query(value = "SELECT AVG(ts.price) FROM TimeSlot as ts WHERE ts.venue.id = :venueID AND " +
            "FUNCTION('DATE', ts.date) = FUNCTION('DATE', :searchDate) AND " +
            "ts.startHour >= :startHour AND ts.endHour <= :endHour")
    Double findAveragePriceByHoursAndDate(@Param(value = "venueID") int venueID,
                                      @Param(value = "startHour") int startHour,
                                      @Param(value = "endHour") int endHour,
                                      @Param(value = "searchDate") Date searchDate);
}