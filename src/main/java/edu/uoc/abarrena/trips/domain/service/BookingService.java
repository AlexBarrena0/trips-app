package edu.uoc.abarrena.trips.domain.service;

import edu.uoc.abarrena.trips.domain.exceptions.EntityNotFoundException;
import edu.uoc.abarrena.trips.domain.exceptions.NoAvailablePlacesException;
import edu.uoc.abarrena.trips.domain.model.Booking;

public interface BookingService {

    /**
     * Book a trip
     *
     * @param booking the booking to create
     */
    Long bookTrip(Booking booking) throws EntityNotFoundException, NoAvailablePlacesException;

    /**
     * Update booking status
     *
     * @param booking the booking to update
     */
    void updateBookingStatus(Booking booking);
}
