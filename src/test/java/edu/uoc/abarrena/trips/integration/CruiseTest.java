package edu.uoc.abarrena.trips.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.uoc.abarrena.trips.BaseIntegrationTest;
import edu.uoc.abarrena.trips.factory.CruiseFactory;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.request.CreateCruiseDto;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.request.UpdateCruiseDto;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.response.CruiseDto;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.response.Result;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CruiseTest extends BaseIntegrationTest {

    @Test
    public void createCruise_Success() {
        CreateCruiseDto createCruiseDto = CruiseFactory.createCruiseDto();
        ResponseEntity<Result> response = restTemplate.postForEntity("/cruises", createCruiseDto, Result.class);
        Result<Long> result = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Cruise created successfully", result.getMessage());
    }

    @Test
    public void findCruise_Success() {
        ResponseEntity<Result> response = restTemplate.getForEntity("/cruises/1", Result.class);
        Result<CruiseDto> result = new ObjectMapper().convertValue(response.getBody(), new TypeReference<Result<CruiseDto>>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, result.getResponse().getId());
        assertEquals("Cruise 1", result.getResponse().getName());
    }

    @Test
    public void findCruise_NotFound() {
        ResponseEntity<Result> response = restTemplate.getForEntity("/cruises/100", Result.class);
        Result<CruiseDto> result = new ObjectMapper().convertValue(response.getBody(), new TypeReference<Result<CruiseDto>>() {});

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Cruise not found", result.getMessage());
    }

    @Test
    public void updateCruise_Success() {
        Long id = 1L;
        UpdateCruiseDto updateCruiseDto = CruiseFactory.updateCruiseDto(id);
        restTemplate.put("/cruises/" + id, updateCruiseDto);

        ResponseEntity<Result> responseFind = restTemplate.getForEntity("/cruises/" + id, Result.class);
        Result<CruiseDto> result = new ObjectMapper().convertValue(responseFind.getBody(), new TypeReference<Result<CruiseDto>>() {});

        assertEquals(HttpStatus.OK, responseFind.getStatusCode());
        assertEquals(updateCruiseDto.getName(), result.getResponse().getName());
        assertEquals(updateCruiseDto.getDescription(), result.getResponse().getDescription());
        assertEquals(updateCruiseDto.getCapacity(), result.getResponse().getCapacity());
    }
}
