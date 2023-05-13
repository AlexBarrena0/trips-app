package edu.uoc.abarrena.trips.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.uoc.abarrena.trips.BaseIntegrationTest;
import edu.uoc.abarrena.trips.factory.TripFactory;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.request.CreateTripDto;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.request.UpdateTripDto;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.response.Result;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.response.TripDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TripTest extends BaseIntegrationTest {

    private ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Test
    void createTrip_Success() {
        CreateTripDto createTripDto = TripFactory.createTripDtoIntegration();
        ResponseEntity<Result> response = restTemplate.postForEntity("/trips", createTripDto, Result.class);
        Result<Long> result = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Trip created successfully", result.getMessage());
    }

    @Test
    void createTrip_NonExistingDestination() {
        CreateTripDto createTripDto = TripFactory.createTripDtoWithNonExistingDestination();
        ResponseEntity<Result> response = restTemplate.postForEntity("/trips", createTripDto, Result.class);
        Result<Long> result = response.getBody();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Destination not found", result.getMessage());
    }

    @Test
    void createTrip_NonExistingCruise() {
        CreateTripDto createTripDto = TripFactory.createTripDtoWithNonExistingCruise();
        ResponseEntity<Result> response = restTemplate.postForEntity("/trips", createTripDto, Result.class);
        Result<Long> result = response.getBody();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Cruise not found", result.getMessage());
    }

    @Test
    void createTrip_InconsistentDates() {
        CreateTripDto createTripDto = TripFactory.createTripDtoWithInconsistentDates();
        ResponseEntity<Result> response = restTemplate.postForEntity("/trips", createTripDto, Result.class);
        Result<Long> result = response.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("The start date must be before the end date", result.getMessage());
    }

    @Test
    void createTrip_OverlappingTrip() {
        CreateTripDto createTripDto = TripFactory.createTripDtoWithOverlappingTrip();
        ResponseEntity<Result> response = restTemplate.postForEntity("/trips", createTripDto, Result.class);
        Result<Long> result = response.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("The trip dates overlaps with another cruise' trip", result.getMessage());
    }

    @Test
    void findTripById_Success() {
        ResponseEntity<Result> response = restTemplate.getForEntity("/trips/1", Result.class);
        Result<TripDto> result = objectMapper.convertValue(response.getBody(), new TypeReference<Result<TripDto>>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, result.getResponse().getId());
        assertEquals("Route 1", result.getResponse().getRoute());
        assertEquals(1L, result.getResponse().getCruise().getId());
        assertEquals(result.getResponse().getAvailablePlaces(), result.getResponse().getCruise().getCapacity());
    }

    @Test
    void findTripById_TripNotFound() {
        ResponseEntity<Result> response = restTemplate.getForEntity("/trips/999", Result.class);
        Result<TripDto> result = objectMapper.convertValue(response.getBody(), new TypeReference<Result<TripDto>>() {});

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Trip not found", result.getMessage());
    }

    @Test
    void findTripByCruiseId_Success() {
        ResponseEntity<Result> response = restTemplate.getForEntity("/trips?cruiseId=1", Result.class);
        Result<List<TripDto>> result = objectMapper.convertValue(response.getBody(), new TypeReference<Result<List<TripDto>>>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, result.getResponse().size());
        assertEquals("Route 1", result.getResponse().get(0).getRoute());
        assertEquals(1L, result.getResponse().get(0).getCruise().getId());
        assertEquals("Route 2", result.getResponse().get(1).getRoute());
        assertEquals(1L, result.getResponse().get(1).getCruise().getId());
    }

    @Test
    void findTripByCruiseId_CruiseIdNotFound() {
        ResponseEntity<Result> response = restTemplate.getForEntity("/trips?cruiseId=999", Result.class);
        Result<List<TripDto>> result = objectMapper.convertValue(response.getBody(), new TypeReference<Result<List<TripDto>>>() {});

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Cruise not found", result.getMessage());
    }

    @Test
    void findTripByDestinationIdAndDateRange_Success() {
        ResponseEntity<Result> response = restTemplate.getForEntity("/trips?destinationId=1&startDate=2023-01-01&endDate=2023-12-31", Result.class);
        Result<List<TripDto>> result = objectMapper.convertValue(response.getBody(), new TypeReference<Result<List<TripDto>>>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, result.getResponse().size());
        assertEquals("Route 1", result.getResponse().get(0).getRoute());
        assertEquals(1L, result.getResponse().get(0).getCruise().getId());
    }

    @Test
    void findTripByDestinationIdAndDateRange_DestinationIdNotFound() {
        ResponseEntity<Result> response = restTemplate.getForEntity("/trips?destinationId=999&startDate=2023-01-01&endDate=2023-12-31", Result.class);
        Result<List<TripDto>> result = objectMapper.convertValue(response.getBody(), new TypeReference<Result<List<TripDto>>>() {});

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Destination not found", result.getMessage());
    }

    @Test
    void updateTrip_Success() {
        Long id = 1L;
        UpdateTripDto updateTripDto = TripFactory.updateTripDto(id);

        restTemplate.put("/trips/" + id, updateTripDto);

        ResponseEntity<Result> response = restTemplate.getForEntity("/trips/" + id, Result.class);
        Result<TripDto> result = objectMapper.convertValue(response.getBody(), new TypeReference<Result<TripDto>>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updateTripDto.getRoute(), result.getResponse().getRoute());
        assertEquals(updateTripDto.getStartDate(), result.getResponse().getStartDate());
        assertEquals(updateTripDto.getEndDate(), result.getResponse().getEndDate());
        assertEquals(updateTripDto.getPrice(), result.getResponse().getPrice());
        assertEquals(updateTripDto.getPrice(), result.getResponse().getPrice());
    }


}
