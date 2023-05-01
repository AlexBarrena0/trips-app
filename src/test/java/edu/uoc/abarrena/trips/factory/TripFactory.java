package edu.uoc.abarrena.trips.factory;

import edu.uoc.abarrena.trips.domain.model.Cruise;
import edu.uoc.abarrena.trips.domain.model.Destination;
import edu.uoc.abarrena.trips.domain.model.Trip;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.TripEntity;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.request.CreateTripDto;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.request.UpdateTripDto;

import java.time.LocalDate;

public interface TripFactory {

    public static Trip tripDomain(Long expectedId) {
        return new Trip(
                expectedId,
                "Route 1",
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 10),
                10,
                1000.0,
                CruiseFactory.cruiseDomain(1L),
                DestinationFactory.destinationDomain(1L));
    }

    public static Trip tripCreationDomain(Long expectedId) {
        return new Trip(
                expectedId,
                "Route 1",
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 10),
                10,
                1000.0,
                new Cruise(1L),
                new Destination(1L));
    }

    public static Trip tripUpdateDomain(Long expectedId) {
        return new Trip(
                expectedId,
                "Route 1",
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 10),
                10,
                1000.0,
                null,
                null);
    }

    public static TripEntity tripEntity(Long expectedId) {
        return new TripEntity(
                expectedId,
                "Route 1",
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 10),
                10,
                1000.0,
                CruiseFactory.cruiseEntity(1L),
                DestinationFactory.destinationEntity(1L));
    }

    public static Trip tripDomainWithInconsistentDates(Long expectedId) {
        return new Trip(
                expectedId,
                "Route 1",
                LocalDate.of(2023, 1, 10),
                LocalDate.of(2023, 1, 1),
                10,
                1000.0,
                new Cruise(1L),
                new Destination(1L));
    }

    public static CreateTripDto createTripDto() {
        return new CreateTripDto(
                "Route 1",
                LocalDate.of(2023, 2, 1),
                LocalDate.of(2023, 2, 10),
                10,
                1000.0,
                1L,
                1L);
    }

    public static CreateTripDto createTripDtoWithInconsistentDates() {
        return new CreateTripDto(
                "Route 1",
                LocalDate.of(2023, 1, 10),
                LocalDate.of(2023, 1, 1),
                10,
                1000.0,
                1L,
                1L);
    }

    public static UpdateTripDto  updateTripDto(Long expectedId) {
        return new UpdateTripDto(
                expectedId,
                "Route 1",
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 10),
                10,
                1000.0);
    }

    public static CreateTripDto createTripDtoWithNonExistingDestination() {
        return new CreateTripDto(
                "Route 1",
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 10),
                10,
                1000.0,
                1L,
                3L);
    }

    public static CreateTripDto createTripDtoWithNonExistingCruise() {
        return new CreateTripDto(
                "Route 1",
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 10),
                10,
                1000.0,
                2L,
                1L);
    }

    public static CreateTripDto createTripDtoWithOverlappingTrip() {
        return new CreateTripDto(
                "Route 1",
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 10),
                10,
                1000.0,
                1L,
                1L);
    }
}
