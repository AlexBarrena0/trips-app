package edu.uoc.abarrena.trips.factory;

import edu.uoc.abarrena.trips.domain.model.Booking;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.BookingEntity;

public interface BookingFactory {

    public static Booking bookingDomain(Long expectedId) {
        return new Booking(expectedId, "PENDING", TripFactory.tripDomain(1L), 1L);
    }

    public static BookingEntity bookingEntity(Long expectedId) {
        return new BookingEntity(expectedId, "PENDING", TripFactory.tripEntity(1L), 1L);
    }
}
