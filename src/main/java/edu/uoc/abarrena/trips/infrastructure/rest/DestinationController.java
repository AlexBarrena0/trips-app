package edu.uoc.abarrena.trips.infrastructure.rest;

import edu.uoc.abarrena.trips.application.DestinationService;
import edu.uoc.abarrena.trips.application.converter.DestinationConverter;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.request.CreateDestinationDto;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.response.DestinationDto;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.response.Result;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        public Result<Long> createDestination(@RequestBody CreateDestinationDto createDestinationDto) {
            log.trace("Creating destination " + createDestinationDto);

            Long destinationId = destinationService.createDestination(DestinationConverter.INSTANCE.toDomain(createDestinationDto));

            return new Result<Long>(destinationId, "Destination created successfully");
        }

        @GetMapping
        public Result<List<DestinationDto>> findAllDestinations() {
            log.trace("Retrieving all destinations");

            List<DestinationDto> destinations = destinationService.findAllDestinations().stream().map(DestinationConverter.INSTANCE::toDto).toList();
            return new Result<List<DestinationDto>>(destinations);
        }

}
