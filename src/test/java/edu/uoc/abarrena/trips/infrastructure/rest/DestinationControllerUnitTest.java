package edu.uoc.abarrena.trips.infrastructure.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.uoc.abarrena.trips.BaseTest;
import edu.uoc.abarrena.trips.application.DestinationService;
import edu.uoc.abarrena.trips.domain.exceptions.DestinationDuplicatedException;
import edu.uoc.abarrena.trips.domain.model.Destination;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.request.CreateDestinationDto;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.response.DestinationDto;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.response.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest(DestinationController.class)
class DestinationControllerUnitTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DestinationService destinationService;

    @Test
    void createDestination_Success() throws Exception {
        CreateDestinationDto createDestinationDto = new CreateDestinationDto("Maldives");
        Destination destination = new Destination(null, "Maldives");
        Long expectedId = 1L;
        when(destinationService.createDestination(destination)).thenReturn(expectedId);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/destinations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(createDestinationDto)))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();
        Result<Long> result = new ObjectMapper().readValue(responseJson, new TypeReference<Result<Long>>() {});

        assertEquals(expectedId, result.getResponse());
        assertEquals("Destination created successfully", result.getMessage());
    }

    @Test
    void createDestination_ErrorDuplicate() throws Exception {
        CreateDestinationDto createDestinationDto = new CreateDestinationDto("Maldives");
        Destination destination = new Destination(null, "Maldives");
        when(destinationService.createDestination(destination)).thenThrow(new DestinationDuplicatedException());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/destinations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(createDestinationDto)))
                        .andExpect(MockMvcResultMatchers.status().isBadRequest())
                        .andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();
        Result result = new ObjectMapper().readValue(responseJson, Result.class);

        assertEquals(false, result.getSuccess());
        assertEquals("Destination already exists", result.getMessage());
    }

    @Test
    void findAllDestinations_Success() throws Exception {
        Destination destination = new Destination(1L, "Maldives");
        Destination destination2 = new Destination(2L, "Red Sea");
        when(destinationService.findAllDestinations()).thenReturn(java.util.List.of(destination, destination2));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/destinations")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();
        Result<List<DestinationDto>> result = new ObjectMapper().readValue(responseJson, new TypeReference<Result<List<DestinationDto>>>() {});

        assertEquals(true, result.getSuccess());
        assertEquals("Maldives", result.getResponse().get(0).getDescription());
        assertEquals("Red Sea", result.getResponse().get(1).getDescription());
    }



}
