package edu.uoc.abarrena.trips.domain.repository;

import edu.uoc.abarrena.trips.domain.model.Booking;

public interface BookingRepository {

    /**
     * Save a booking
     * @param booking to save
     * @return the id of the saved booking
     */
    public Long save(Booking booking);

    /**
     * Finds a booking by its id
     * @param id of the booking to find
     * @return the booking with the given id
     */
    public Booking findById(Long id);

    /**
     * Update a booking
     * @param booking the booking to update
     */
    public void update(Booking booking);
}
