package edu.uoc.abarrena.trips.infrastructure.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.uoc.abarrena.trips.DestinationTestFactory;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.request.CreateDestinationRequest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static edu.uoc.abarrena.trips.infrastructure.rest.DestinationController.BASE_PATH;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@Import(DestinationController.class)
@AutoConfigureMockMvc()
class DestinationControllerTest {

    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private MockMvc mvc;

    @Test
    void createDestination() throws Exception {
        CreateDestinationRequest createDestinationRequest = DestinationTestFactory.createDestinationRequest();

        var body = mapper.writeValueAsString(createDestinationRequest);
        mvc.perform(post(BASE_PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .content(body))
            .andExpect(status().isOk());
    }
}
