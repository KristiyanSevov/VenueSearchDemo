package com.demo.venue_search.console;

import com.demo.venue_search.services.api.TimeSlotService;
import com.demo.venue_search.services.api.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final VenueService venueService;
    private final TimeSlotService timeSlotService;

    @Autowired
    public ConsoleRunner(VenueService venueService, TimeSlotService timeSlotService) {
        this.venueService = venueService;
        this.timeSlotService = timeSlotService;
    }

    @Override
    public void run(String... args) throws Exception {
//        venueService.insertTestVenues(394);
//        List<String> venueNames = new ArrayList<>();
//        for (int i = 0; i < 394; i++) {
//            venueNames.add("Test" + i);
//        }
//       timeSlotService.insertSlotsFor90DaysWithRandomPrices(venueNames, new Date());
    }
}
