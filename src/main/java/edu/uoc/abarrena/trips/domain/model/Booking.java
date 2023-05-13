package edu.uoc.abarrena.trips.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    private Long id;
    private String status;
    private Trip trip;
    private Long userId;

    public Booking(Long tripId, Long userId) {
        this.trip = new Trip(tripId);
        this.userId = userId;
        this.status = BookingStatus.PENDING.name();
    }
}
