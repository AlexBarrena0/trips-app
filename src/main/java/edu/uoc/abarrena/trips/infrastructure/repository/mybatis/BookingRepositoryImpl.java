package edu.uoc.abarrena.trips.infrastructure.repository.mybatis;

import edu.uoc.abarrena.trips.application.converter.BookingConverter;
import edu.uoc.abarrena.trips.domain.model.Booking;
import edu.uoc.abarrena.trips.domain.repository.BookingRepository;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.BookingEntity;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper.BookingMapper;

public class BookingRepositoryImpl implements BookingRepository {

    private final BookingMapper bookingMapper;

    public BookingRepositoryImpl(BookingMapper bookingMapper) {
        this.bookingMapper = bookingMapper;
    }

    @Override
    public Long save(Booking booking) {
        BookingEntity bookingEntity = BookingConverter.INSTANCE.toEntity(booking);
        bookingMapper.save(bookingEntity);
        return bookingEntity.getId();
    }

    @Override
    public Booking findById(Long id) {
        return BookingConverter.INSTANCE.toDomain(bookingMapper.findById(id));
    }

    @Override
    public void update(Booking booking) {
        BookingEntity bookingEntity = BookingConverter.INSTANCE.toEntity(booking);
        bookingMapper.update(bookingEntity);
    }
}
