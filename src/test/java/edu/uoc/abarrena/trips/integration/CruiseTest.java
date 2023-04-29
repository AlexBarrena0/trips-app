package edu.uoc.abarrena.trips.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.request.CreateCruiseDto;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.response.CruiseDto;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.response.Result;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CruiseTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void createCruise_Success() {
        CreateCruiseDto createCruiseDto = new CreateCruiseDto("Cruise 1", "Cruise 1 description");

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
}
