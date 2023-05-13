package edu.uoc.abarrena.trips.infrastructure.repository.mybatis;

import edu.uoc.abarrena.trips.BaseUnitTest;
import edu.uoc.abarrena.trips.domain.model.Booking;
import edu.uoc.abarrena.trips.factory.BookingFactory;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.BookingEntity;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper.BookingMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class BookingRepositoryUnitTest extends BaseUnitTest {

    @Mock
    private BookingMapper bookingMapper;

    @InjectMocks
    private BookingRepositoryImpl bookingRepository;

    @Test
    void save_saveBooking() {
        Long expectedId = 1L;
        Booking booking = BookingFactory.bookingDomain(expectedId);

        BookingEntity bookingEntity = BookingFactory.bookingEntity(expectedId);
        doNothing().when(bookingMapper).save(bookingEntity);

        Long actualId = bookingRepository.save(booking);

        assertEquals(expectedId, actualId);
    }

    @Test
    void findById_returnBooking() {
        Long id = 1L;
        BookingEntity bookingEntity = BookingFactory.bookingEntity(id);
        when(bookingMapper.findById(id)).thenReturn(bookingEntity);

        Booking actualBooking = bookingRepository.findById(id);

        Booking expectedBooking = BookingFactory.bookingDomain(id);
        assertEquals(expectedBooking, actualBooking);
    }

    @Test
    void update_updateBooking() {
        Long id = 1L;
        Booking booking = BookingFactory.bookingDomain(id);

        BookingEntity bookingEntity = BookingFactory.bookingEntity(id);
        doNothing().when(bookingMapper).update(bookingEntity);

        bookingRepository.update(booking);
    }
}
