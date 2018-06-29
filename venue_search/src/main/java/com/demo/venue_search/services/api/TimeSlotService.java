package com.demo.venue_search.services.api;

import com.demo.venue_search.models.entities.TimeSlot;

import java.util.Date;
import java.util.List;

public interface TimeSlotService {
    Double findAveragePrice(int venueID, int startHour, int endHour, Date searchDate);

    void insertSlotsFor90DaysWithRandomPrices(List<String> venueNames, Date startDate);
}
