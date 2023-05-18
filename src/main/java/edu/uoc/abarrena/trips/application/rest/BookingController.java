package edu.uoc.abarrena.trips.application.rest;

import edu.uoc.abarrena.trips.domain.service.BookingService;
import edu.uoc.abarrena.trips.domain.converter.BookingConverter;
import edu.uoc.abarrena.trips.application.dto.request.CreateBookingDto;
import edu.uoc.abarrena.trips.application.dto.request.UpdateBookingDto;
import edu.uoc.abarrena.trips.application.dto.response.Result;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping(BookingController.BASE_PATH)
public class BookingController {

    public static final String BASE_PATH = "/bookings";

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public Result<Long> bookTrip(@RequestBody CreateBookingDto createBookingDto) {
        log.trace("Creating booking " + createBookingDto);

        Long bookingId = bookingService.bookTrip(BookingConverter.INSTANCE.toDomain(createBookingDto));

        return new Result<>(bookingId, "Booking created successfully");
    }

    @PatchMapping
    public Result<Boolean> updateBooking(@RequestBody UpdateBookingDto updateBookingDto) {
        log.trace("Updating booking " + updateBookingDto);

        bookingService.updateBookingStatus(BookingConverter.INSTANCE.toDomain(updateBookingDto));

        return new Result<>(true, "Booking updated successfully");
    }
}
