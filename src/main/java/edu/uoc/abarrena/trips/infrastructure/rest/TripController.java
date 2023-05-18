package edu.uoc.abarrena.trips.infrastructure.rest;

import edu.uoc.abarrena.trips.application.TripService;
import edu.uoc.abarrena.trips.application.converter.TripConverter;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.request.CreateTripDto;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.request.FindTripsFiltersDto;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.request.UpdateTripDto;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.response.Result;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.response.TripDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@RestController
@RequestMapping(TripController.BASE_PATH)
public class TripController {

    public static final String BASE_PATH = "/trips";

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping
    public Result<Long> createTrip(@Valid @RequestBody CreateTripDto createTripDto) {
        log.trace("Creating trip " + createTripDto);

        Long tripId = tripService.createTrip(TripConverter.INSTANCE.toDomain(createTripDto));

        return new Result<Long>(tripId, "Trip created successfully");
    }

    @GetMapping("/{id}")
    public Result<TripDto> findTripById(@PathVariable Long id) {
        log.trace("Retrieving trip by id " + id);

        TripDto trip = TripConverter.INSTANCE.toDto(tripService.findTripById(id));
        return new Result<TripDto>(trip, null);
    }

    @GetMapping
    public Result<List<TripDto>> findTripsByFilters(FindTripsFiltersDto params) {
        log.trace("Retrieving trips by filters " + params);
        List<TripDto> trips;
        if (params.getCruiseId() != null) {
            trips = TripConverter.INSTANCE.toDto(tripService.findTripByCruiseId(params.getCruiseId()));
        } else {
            trips = TripConverter.INSTANCE.toDto(tripService.findTripByDestinationIdAndDateRange(params.getDestinationId(), params.getStartDate(), params.getEndDate()));
        }
        return new Result<List<TripDto>>(trips, null);
    }

    @PutMapping("/{id}")
    public Result<Boolean> updateTrip(@PathVariable Long id, @Valid @RequestBody UpdateTripDto updateTripDto) {
        log.trace("Updating trip " + id + " with " + updateTripDto);

        tripService.updateTrip(TripConverter.INSTANCE.toDomain(updateTripDto));

        return new Result<>(true, "Trip updated successfully");
    }
}
