package edu.uoc.abarrena.trips.domain.service;

import edu.uoc.abarrena.trips.domain.exceptions.EntityNotFoundException;
import edu.uoc.abarrena.trips.domain.exceptions.NoAvailablePlacesException;
import edu.uoc.abarrena.trips.domain.model.Booking;

public interface BookingService {

    /**
     * Book a trip
     * @param booking the booking to create
     */
    public Long bookTrip(Booking booking) throws EntityNotFoundException, NoAvailablePlacesException;

    /**
     * Update booking status
     * @param bookingId booking id
     * @param status booking status
     */
    public void updateBookingStatus(Booking booking);
}
