package edu.uoc.abarrena.trips.infrastructure.rest;

import edu.uoc.abarrena.trips.application.DestinationService;

import edu.uoc.abarrena.trips.infrastructure.rest.dto.request.CreateDestinationRequest;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.response.Response;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Log4j2
@RestController
@RequestMapping(DestinationController.BASE_PATH)
public class DestinationController {

        public static final String BASE_PATH = "/destinations";

        @Autowired
        private MessageSource messageSource;

        private final DestinationService destinationService;

        public DestinationController(DestinationService destinationService) {
            this.destinationService = destinationService;
        }

        @PostMapping
        public Response<Long> createDestination(@RequestBody CreateDestinationRequest createDestinationRequest) {
            log.trace("Creating destination " + createDestinationRequest);

            Long destinationId = destinationService.createDestination(createDestinationRequest.toDomain());

            return new Response<>(destinationId, "Destination created successfully");
        }
}
