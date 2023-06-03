package edu.uoc.abarrena.trips.domain.service.impl;

import edu.uoc.abarrena.trips.domain.exceptions.EntityNotFoundException;
import edu.uoc.abarrena.trips.domain.exceptions.NoAvailablePlacesException;
import edu.uoc.abarrena.trips.domain.exceptions.UpdatedBookingException;
import edu.uoc.abarrena.trips.domain.model.*;
import edu.uoc.abarrena.trips.domain.repository.BookingRepository;
import edu.uoc.abarrena.trips.domain.service.BookingService;
import edu.uoc.abarrena.trips.domain.service.NotificationService;
import edu.uoc.abarrena.trips.domain.service.TripService;
import edu.uoc.abarrena.trips.domain.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    private final TripService tripService;

    private final UserService userService;

    private final NotificationService notificationService;

    public BookingServiceImpl(BookingRepository bookingRepository, TripService tripService, UserService userService, NotificationService notificationService) {
        this.bookingRepository = bookingRepository;
        this.tripService = tripService;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    @Override
    public Long bookTrip(Booking booking) throws EntityNotFoundException, NoAvailablePlacesException {
        Trip trip = tripService.findTripById(booking.getTrip().getId());
        if (trip == null) {
            throw new EntityNotFoundException("Trip not found");
        }
        Traveler traveler = userService.getTraveler(booking.getTravelerId());
        if (traveler == null) {
            throw new EntityNotFoundException("Traveler not found");
        }
        if (trip.getAvailablePlaces() == 0) {
            throw new NoAvailablePlacesException();
        }
        booking.setStatus(BookingStatus.PENDING.name());
        Long id = bookingRepository.save(booking);
        notificationService.sendNotification(new Notification(NotificationType.RESERVATION_PENDING, trip.getCruise().getCompany().getId()));
        return id;
    }

    @Override
    public void updateBookingStatus(Booking booking) {
        Booking currentBooking = bookingRepository.findById(booking.getId());
        if (!currentBooking.getStatus().equals(BookingStatus.PENDING.name())) {
            throw new UpdatedBookingException();
        }
        bookingRepository.update(booking);
        Trip trip = tripService.findTripById(currentBooking.getTrip().getId());
        if (currentBooking.getStatus().equals(BookingStatus.CONFIRMED.name())) {
            tripService.bookPlace(currentBooking.getTrip().getId());
            notificationService.sendNotification(new Notification(NotificationType.RESERVATION_CONFIRMED, trip.getCruise().getCompany().getId()));
        } else if (currentBooking.getStatus().equals(BookingStatus.REJECTED.name())) {
            notificationService.sendNotification(new Notification(NotificationType.RESERVATION_REJECTED, trip.getCruise().getCompany().getId()));
        }
    }
}
