package edu.uoc.abarrena.trips.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.uoc.abarrena.trips.factory.DestinationFactory;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.request.CreateDestinationDto;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.response.DestinationDto;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class DestinationTest {

    @Autowired
    private TestRestTemplate restTemplate;

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
