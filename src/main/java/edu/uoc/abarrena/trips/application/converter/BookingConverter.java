package edu.uoc.abarrena.trips.application.converter;

import edu.uoc.abarrena.trips.domain.model.Booking;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.BookingEntity;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.request.CreateBookingDto;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.request.UpdateBookingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookingConverter {

    public static final BookingConverter INSTANCE = Mappers.getMapper(BookingConverter.class);

    Booking toDomain(BookingEntity bookingEntity);

    @Mapping(target = "trip.id", source = "tripId")
    Booking toDomain(CreateBookingDto createBookingDto);

    Booking toDomain(UpdateBookingDto updateBookingDto);

    BookingEntity toEntity(Booking booking);
}
