package com.demo.venue_search.services.api;

import com.demo.venue_search.models.dto.VenueView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface VenueService {
    Set<VenueView> findVenues(Date date, int startHour, int endHour, int minCapacity,
                              String chairType, String tableType, String liveStream);

    Page<VenueView> findVenuesByPage(Date start, int startHour, int endHour, int minCapacity,
                                     String chairType, String tableType, String liveStream, Pageable pageable);

    void insertTestVenues(int count);
}
