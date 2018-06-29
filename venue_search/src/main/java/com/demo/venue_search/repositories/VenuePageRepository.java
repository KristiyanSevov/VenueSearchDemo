package com.demo.venue_search.repositories;

import com.demo.venue_search.models.dto.VenueView;
import com.demo.venue_search.models.entities.Venue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface VenuePageRepository extends PagingAndSortingRepository<Venue, Integer> {

    @Query(value = "SELECT new com.demo.venue_search.models.dto.VenueView(v.name, AVG(ts.price), v.capacity, " +
            "v.chairType, v.tableType, v.liveStream) " +
            "FROM TimeSlot as ts JOIN ts.venue as v " +
            "WHERE v.capacity >= :capacity AND " +
            "(:chairType = 'any' OR v.chairType = :chairType) AND " +
            "(:tableType = 'any' OR v.tableType = :tableType) AND " +
            "(:liveStream = 'any' OR v.liveStream = (CASE WHEN :liveStream = 'true' THEN 1 ELSE 0 END)) AND " +
            "FUNCTION('DATE', ts.date) = FUNCTION('DATE', :startDate) AND " +
            "ts.startHour >= :startHour AND " +
            "ts.endHour <= :endHour AND " +
            "ts.isAvailable = true " +
            "GROUP BY v.id " +
            "HAVING COUNT(ts.id) = :endHour - :startHour")
    Page<VenueView> findVenues(@Param(value = "startDate") Date start,
                               @Param(value = "startHour") int startHour,
                               @Param(value = "endHour") int endHour,
                               @Param(value = "capacity") int minCapacity,
                               @Param(value = "chairType") String chairType,
                               @Param(value = "tableType") String tableType,
                               @Param(value = "liveStream") String liveStream,
                               Pageable pageable);
}
