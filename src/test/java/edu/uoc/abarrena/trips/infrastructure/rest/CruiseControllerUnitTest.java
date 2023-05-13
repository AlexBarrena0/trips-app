package edu.uoc.abarrena.trips.infrastructure.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.uoc.abarrena.trips.BaseUnitTest;
import edu.uoc.abarrena.trips.application.CruiseService;
import edu.uoc.abarrena.trips.domain.exceptions.EntityNotFoundException;
import edu.uoc.abarrena.trips.domain.model.Cruise;
import edu.uoc.abarrena.trips.factory.CruiseFactory;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.request.CreateCruiseDto;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@WebMvcTest(CruiseController.class)
class CruiseControllerUnitTest extends BaseUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CruiseService cruiseService;

    @Test
    void createCruise_Success() throws Exception {
        CreateCruiseDto createCruiseDto = CruiseFactory.createCruiseDto();
        Cruise cruise = CruiseFactory.cruiseDomain(null);
        Long expectedId = 1L;
        when(cruiseService.createCruise(cruise)).thenReturn(expectedId);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/cruises")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(createCruiseDto)))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();
        Result<Long> result = new ObjectMapper().readValue(responseJson, new TypeReference<Result<Long>>() {});

        assertEquals(expectedId, result.getResponse());
        assertEquals("Cruise created successfully", result.getMessage());
    }

    @Test
    void findCruise_Success() throws Exception {
        Long id = 1L;
        Cruise cruise = CruiseFactory.cruiseDomain(id);
        when(cruiseService.findCruiseById(id)).thenReturn(cruise);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/cruises/1"))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();
        Result<Cruise> result = new ObjectMapper().readValue(responseJson, new TypeReference<Result<Cruise>>() {});

        assertEquals(cruise, result.getResponse());
    }

    @Test
    void findCruise_NotFound() throws Exception {
        when(cruiseService.findCruiseById(1L)).thenThrow(new EntityNotFoundException("Cruise not found"));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/cruises/1")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isNotFound())
                        .andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();
        Result<Cruise> result = new ObjectMapper().readValue(responseJson, new TypeReference<Result<Cruise>>() {});

        assertEquals(false, result.getSuccess());
        assertEquals("Cruise not found", result.getMessage());
    }

    @Test
    void updateCruise_Success() throws Exception {
        Long id = 1L;
        CreateCruiseDto createCruiseDto = CruiseFactory.createCruiseDto();
        Cruise cruise = CruiseFactory.cruiseDomain(id);
        doNothing().when(cruiseService).updateCruise(cruise);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/cruises/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(createCruiseDto)))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();
        Result result = new ObjectMapper().readValue(responseJson, Result.class);

        assertEquals(true, result.getSuccess());
        assertEquals("Cruise updated successfully", result.getMessage());
    }

}
