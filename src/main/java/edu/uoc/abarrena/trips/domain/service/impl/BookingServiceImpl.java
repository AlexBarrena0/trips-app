package edu.uoc.abarrena.trips.domain.service.impl;

import edu.uoc.abarrena.trips.domain.exceptions.UpdatedBookingException;
import edu.uoc.abarrena.trips.domain.service.BookingService;
import edu.uoc.abarrena.trips.domain.service.TripService;
import edu.uoc.abarrena.trips.domain.exceptions.EntityNotFoundException;
import edu.uoc.abarrena.trips.domain.exceptions.NoAvailablePlacesException;
import edu.uoc.abarrena.trips.domain.model.Booking;
import edu.uoc.abarrena.trips.domain.model.BookingStatus;
import edu.uoc.abarrena.trips.domain.model.Trip;
import edu.uoc.abarrena.trips.domain.repository.BookingRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    private final TripService tripService;

    public BookingServiceImpl(BookingRepository bookingRepository, TripService tripService) {
        this.bookingRepository = bookingRepository;
        this.tripService = tripService;
    }

    @Override
    public Long bookTrip(Booking booking) throws EntityNotFoundException, NoAvailablePlacesException {
        Trip trip = tripService.findTripById(booking.getTrip().getId());
        if (trip == null) {
            throw new EntityNotFoundException("Trip not found");
        }
        if (trip.getAvailablePlaces() == 0) {
            throw new NoAvailablePlacesException();
        }
        booking.setStatus(BookingStatus.PENDING.name());
        return bookingRepository.save(booking);
    }

    @Override
    public void updateBookingStatus(Booking booking) {
        Booking currentBooking = bookingRepository.findById(booking.getId());
        if (!currentBooking.getStatus().equals(BookingStatus.PENDING.name())) {
            throw new UpdatedBookingException();
        }
        bookingRepository.update(booking);
        if (currentBooking.getStatus().equals(BookingStatus.CONFIRMED.name())) {
            tripService.bookPlace(currentBooking.getTrip().getId());
        }
    }
}
