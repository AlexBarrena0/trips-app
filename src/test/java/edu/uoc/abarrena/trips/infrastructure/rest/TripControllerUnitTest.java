package edu.uoc.abarrena.trips.infrastructure.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.uoc.abarrena.trips.BaseUnitTest;
import edu.uoc.abarrena.trips.domain.service.TripService;
import edu.uoc.abarrena.trips.application.rest.TripController;
import edu.uoc.abarrena.trips.domain.exceptions.EntityNotFoundException;
import edu.uoc.abarrena.trips.domain.exceptions.InconsistentDatesException;
import edu.uoc.abarrena.trips.domain.exceptions.OverlappingTripException;
import edu.uoc.abarrena.trips.domain.model.Trip;
import edu.uoc.abarrena.trips.factory.TripFactory;
import edu.uoc.abarrena.trips.application.dto.request.CreateTripDto;
import edu.uoc.abarrena.trips.application.dto.request.UpdateTripDto;
import edu.uoc.abarrena.trips.application.dto.response.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@WebMvcTest(TripController.class)
class TripControllerUnitTest extends BaseUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TripService tripService;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Test
    void createTrip_Success() throws Exception {
        CreateTripDto createTripDto = TripFactory.createTripDto();
        Trip trip = TripFactory.tripCreationDomainWithoutAvailablePlaces(null);
        Long expectedId = 1L;
        when(tripService.createTrip(trip)).thenReturn(expectedId);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/trips")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createTripDto)))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();
        Result<Long> result = objectMapper.readValue(responseJson, new TypeReference<Result<Long>>() {});

        assertEquals(expectedId, result.getResponse());
        assertEquals("Trip created successfully", result.getMessage());
    }

    @Test
    void createTrip_NonExistingDestination() throws Exception {
        CreateTripDto createTripDto = TripFactory.createTripDto();
        Trip trip = TripFactory.tripCreationDomainWithoutAvailablePlaces(null);
        when(tripService.createTrip(trip)).thenThrow(new EntityNotFoundException("Destination not found"));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/trips")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createTripDto)))
                        .andExpect(MockMvcResultMatchers.status().isNotFound())
                        .andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();
        Result<Long> result = objectMapper.readValue(responseJson, new TypeReference<Result<Long>>() {});

        assertEquals(false, result.getSuccess());
        assertEquals("Destination not found", result.getMessage());
    }

    @Test
    void createTrip_NonExistingCruise() throws Exception {
        CreateTripDto createTripDto = TripFactory.createTripDto();
        Trip trip = TripFactory.tripCreationDomainWithoutAvailablePlaces(null);
        when(tripService.createTrip(trip)).thenThrow(new EntityNotFoundException("Cruise not found"));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/trips")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createTripDto)))
                        .andExpect(MockMvcResultMatchers.status().isNotFound())
                        .andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();
        Result<Long> result = objectMapper.readValue(responseJson, new TypeReference<Result<Long>>() {});

        assertEquals(false, result.getSuccess());
        assertEquals("Cruise not found", result.getMessage());
    }

    @Test
    void createTrip_InconsistentDates() throws Exception {
        CreateTripDto createTripDto = TripFactory.createTripDtoWithInconsistentDates();
        Trip trip = TripFactory.tripDomainWithInconsistentDates(null);
        when(tripService.createTrip(trip)).thenThrow(new InconsistentDatesException());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/trips")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createTripDto)))
                        .andExpect(MockMvcResultMatchers.status().isBadRequest())
                        .andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();
        Result<Long> result = objectMapper.readValue(responseJson, new TypeReference<Result<Long>>() {});

        assertEquals(false, result.getSuccess());
        assertEquals("The start date must be before the end date", result.getMessage());
    }

    @Test
    void createCruise_OverlappingTrip() throws Exception {
        CreateTripDto createTripDto = TripFactory.createTripDto();
        Trip trip = TripFactory.tripCreationDomainWithoutAvailablePlaces(null);
        when(tripService.createTrip(trip)).thenThrow(new OverlappingTripException());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/trips")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createTripDto)))
                        .andExpect(MockMvcResultMatchers.status().isBadRequest())
                        .andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();
        Result<Long> result = objectMapper.readValue(responseJson, new TypeReference<Result<Long>>() {});

        assertEquals(false, result.getSuccess());
        assertEquals("The trip dates overlaps with another cruise' trip", result.getMessage());
    }

    @Test
    void findTripById_Success() throws Exception {
        Trip trip = TripFactory.tripDomain(1L);
        when(tripService.findTripById(1L)).thenReturn(trip);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/trips/1"))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();
        Result<Trip> result = objectMapper.readValue(responseJson, new TypeReference<Result<Trip>>() {});

        assertEquals(true, result.getSuccess());
        assertEquals(result.getResponse(), trip);
    }

    @Test
    void findTripById_TripNotFound() throws Exception {
        when(tripService.findTripById(1L)).thenThrow(new EntityNotFoundException("Trip not found"));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/trips/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();
        Result<Trip> result = objectMapper.readValue(responseJson, new TypeReference<Result<Trip>>() {});

        assertEquals(false, result.getSuccess());
        assertEquals("Trip not found", result.getMessage());
    }

    @Test
    void findTripByCruiseId_Success() throws Exception {
        Trip trip = TripFactory.tripDomain(1L);
        when(tripService.findTripByCruiseId(1L)).thenReturn(List.of(trip));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/trips?cruiseId=1"))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();
        Result<List<Trip>> result = objectMapper.readValue(responseJson, new TypeReference<Result<List<Trip>>>() {});

        assertEquals(true, result.getSuccess());
        assertEquals(result.getResponse(), List.of(trip));
    }

    @Test
    void findTripByCruiseId_CruiseIdNotFound() throws Exception {
        when(tripService.findTripByCruiseId(1L)).thenThrow(new EntityNotFoundException("Cruise not found"));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/trips?cruiseId=1"))
                        .andExpect(MockMvcResultMatchers.status().isNotFound())
                        .andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();
        Result<List<Trip>> result = objectMapper.readValue(responseJson, new TypeReference<Result<List<Trip>>>() {});

        assertEquals(false, result.getSuccess());
        assertEquals("Cruise not found", result.getMessage());
    }

    @Test
    void findTripByDestinationIdAndDateRange_Success() throws Exception {
        Trip trip = TripFactory.tripDomain(1L);
        when(tripService.findTripByDestinationIdAndDateRange(1L, LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 2)))
                        .thenReturn(List.of(trip));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/trips?destinationId=1&startDate=2021-01-01&endDate=2021-01-02"))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();
        Result<List<Trip>> result = objectMapper.readValue(responseJson, new TypeReference<Result<List<Trip>>>() {});

        assertEquals(true, result.getSuccess());
        assertEquals(result.getResponse(), List.of(trip));
    }

    @Test
    void findTripByDestinationIdAndDateRange_DestinationIdNotFound() throws Exception {
        when(tripService.findTripByDestinationIdAndDateRange(1L, LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 2)))
                        .thenThrow(new EntityNotFoundException("Destination not found"));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/trips?destinationId=1&startDate=2021-01-01&endDate=2021-01-02"))
                        .andExpect(MockMvcResultMatchers.status().isNotFound())
                        .andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();
        Result<List<Trip>> result = objectMapper.readValue(responseJson, new TypeReference<Result<List<Trip>>>() {});

        assertEquals(false, result.getSuccess());
        assertEquals("Destination not found", result.getMessage());
    }

    @Test
    void updateTrip_Success() throws Exception {
        Long id = 1L;
        UpdateTripDto updateTripDto = TripFactory.updateTripDto(id);
        Trip trip = TripFactory.tripUpdateDomain(id);
        doNothing().when(tripService).updateTrip(trip);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/trips/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateTripDto)))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();
        String responseJson = mvcResult.getResponse().getContentAsString();
        Result result = objectMapper.readValue(responseJson, Result.class);

        assertEquals(true, result.getSuccess());
        assertEquals("Trip updated successfully", result.getMessage());

    }
}
