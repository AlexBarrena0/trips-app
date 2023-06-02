package edu.uoc.abarrena.trips.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class  Booking {

    private Long id;
    private String status;
    private Trip trip;
    private Long travelerId;

    public Booking(Long tripId, Long travelerId) {
        this.trip = new Trip(tripId);
        this.travelerId = travelerId;
        this.status = BookingStatus.PENDING.name();
    }
}
