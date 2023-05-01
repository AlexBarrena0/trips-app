package edu.uoc.abarrena.trips.application.converter;

import edu.uoc.abarrena.trips.domain.model.Trip;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.TripEntity;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.request.CreateTripDto;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.request.UpdateTripDto;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.response.TripDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TripConverter {

    public static final TripConverter INSTANCE = Mappers.getMapper(TripConverter.class);

    TripDto toDto(Trip trip);

    List<TripDto> toDto(List<Trip> trip);

    @Mapping(target = "destination.id", source = "destinationId")
    @Mapping(target = "cruise.id", source = "cruiseId")
    Trip toDomain(CreateTripDto createTripDto);

    Trip toDomain(UpdateTripDto updateTripDto);

    Trip toDomain(TripEntity tripEntity);

    List<Trip> toDomain(List<TripEntity> tripEntity);

    TripEntity toEntity(Trip trip);
}