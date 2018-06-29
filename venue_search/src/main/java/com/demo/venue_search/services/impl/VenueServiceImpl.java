package com.demo.venue_search.services.impl;

import com.demo.venue_search.models.dto.VenueView;
import com.demo.venue_search.models.entities.Venue;
import com.demo.venue_search.repositories.VenuePageRepository;
import com.demo.venue_search.repositories.VenueRepository;
import com.demo.venue_search.services.api.TimeSlotService;
import com.demo.venue_search.services.api.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Set;

@Service
@Transactional
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;
    private final VenuePageRepository venuePageRepository;
    private final TimeSlotService timeSlotService;

    @Autowired
    public VenueServiceImpl(VenueRepository venueRepository,
                            VenuePageRepository venuePageRepository,
                            TimeSlotService timeSlotService) {
        this.venueRepository = venueRepository;
        this.venuePageRepository = venuePageRepository;
        this.timeSlotService = timeSlotService;
    }

    @Override
    public Set<VenueView> findVenues(Date start, int startHour, int endHour, int minCapacity,
                                 String chairType, String tableType, String liveStream) {
        return venueRepository.findVenues(start, startHour, endHour, minCapacity, chairType, tableType, liveStream);
    }

    @Override
    public Page<VenueView> findVenuesByPage(Date start, int startHour, int endHour, int minCapacity,
                                            String chairType, String tableType, String liveStream,
                                            Pageable pageable) {
        return venuePageRepository.findVenues(start, startHour, endHour, minCapacity,
                chairType, tableType, liveStream, pageable);
    }

    @Override
    public void insertTestVenues(int count) {
        for (int i = 0; i < count; i++) {
            Venue venue = new Venue("Test" + i, 100, false, "movable", "fixed");
            venueRepository.save(venue);
        }
    }
}