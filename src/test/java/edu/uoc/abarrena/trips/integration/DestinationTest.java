package edu.uoc.abarrena.trips.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.uoc.abarrena.trips.BaseIntegrationTest;
import edu.uoc.abarrena.trips.factory.DestinationFactory;
import edu.uoc.abarrena.trips.application.dto.request.CreateDestinationDto;
import edu.uoc.abarrena.trips.application.dto.response.DestinationDto;
import edu.uoc.abarrena.trips.application.dto.response.Result;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DestinationTest extends BaseIntegrationTest {

    @Test
    public void createDestination_Success() {
        CreateDestinationDto createDestinationDto = DestinationFactory.createDestinationDto();

        ResponseEntity<Result> response = restTemplate.postForEntity("/destinations", createDestinationDto, Result.class);
        Result<Long> result = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Destination created successfully", result.getMessage());
    }

    @Test
    public void testCreateDestination_ErrorDuplicate() {
        CreateDestinationDto createDestinationDto = DestinationFactory.createExistingDestinationDto();

        ResponseEntity<Result> response = restTemplate.postForEntity("/destinations", createDestinationDto, Result.class);
        Result<Long> result = response.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Destination already exists", result.getMessage());
    }

    @Test
    public void findAllDestinations_Success() {
        ResponseEntity<Result> response = restTemplate.getForEntity("/destinations", Result.class);
        Result<List<DestinationDto>> result = new ObjectMapper().convertValue(response.getBody(), new TypeReference<Result<List<DestinationDto>>>() {});;

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, result.getResponse().size());
    }


}
