package com.demo.venue_search.services.impl;

import com.demo.venue_search.models.entities.TimeSlot;
import com.demo.venue_search.models.entities.Venue;
import com.demo.venue_search.repositories.TimeSlotRepository;
import com.demo.venue_search.repositories.VenueRepository;
import com.demo.venue_search.services.api.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
public class TimeSlotServiceImpl implements TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;
    private final VenueRepository venueRepository;

    @Autowired
    public TimeSlotServiceImpl(TimeSlotRepository timeSlotRepository, VenueRepository venueRepository) {
        this.timeSlotRepository = timeSlotRepository;
        this.venueRepository = venueRepository;
    }

    @Override
    public Double findAveragePrice(int venueID, int startHour, int endHour, Date searchDate) {
        return timeSlotRepository.findAveragePriceByHoursAndDate(venueID, startHour, endHour, searchDate);
    }

    @Override
    public void insertSlotsFor90DaysWithRandomPrices(List<String> venueNames, Date startDate) {
        for (String venueName : venueNames) {
            Venue venue = venueRepository.findByName(venueName);
            if (venue == null) {
                throw new IllegalArgumentException("No venue with such id.");
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            for (int i = 0; i < 90; i++) {
                for (int hour = 0; hour < 24; hour++) {
                    BigDecimal price = BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(200.0));
                    Date date = calendar.getTime();
                    TimeSlot timeSlot = new TimeSlot(venue, date, hour, hour + 1, price, true);
                    timeSlotRepository.save(timeSlot);
                }
                calendar.add(Calendar.DAY_OF_YEAR, 1);
            }
        }
    }
}
