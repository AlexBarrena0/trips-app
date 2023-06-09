package edu.uoc.abarrena.trips.application.rest;

import edu.uoc.abarrena.trips.application.dto.request.CreateCruiseDto;
import edu.uoc.abarrena.trips.application.dto.request.UpdateCruiseDto;
import edu.uoc.abarrena.trips.application.dto.response.CruiseDto;
import edu.uoc.abarrena.trips.application.dto.response.Result;
import edu.uoc.abarrena.trips.domain.converter.CruiseConverter;
import edu.uoc.abarrena.trips.domain.service.CruiseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping(CruiseController.BASE_PATH)
public class CruiseController {

    public static final String BASE_PATH = "/cruises";

    private final CruiseService cruiseService;

    public CruiseController(CruiseService cruiseService) {
        this.cruiseService = cruiseService;
    }

    @PostMapping
    public Result<Long> createCruise(@RequestBody CreateCruiseDto createCruiseDto) {
        log.trace("Creating cruise " + createCruiseDto);

        Long cruiseId = cruiseService.createCruise(CruiseConverter.INSTANCE.toDomain(createCruiseDto));

        return new Result<Long>(cruiseId, "Cruise created successfully");
    }

    @GetMapping("/{id}")
    public Result<CruiseDto> findCruiseById(@PathVariable Long id) {
        log.trace("Retrieving cruise " + id);

        CruiseDto cruiseDto = CruiseConverter.INSTANCE.toDto(cruiseService.findCruiseById(id));

        return new Result<CruiseDto>(cruiseDto, null);
    }

    @GetMapping
    public Result<List<CruiseDto>> findCruisesByCompanyId(@RequestParam Long companyId) {
        log.trace("Retrieving cruises by company id " + companyId);

        List<CruiseDto> cruises = CruiseConverter.INSTANCE.toDto(cruiseService.findByCompanyId(companyId));

        return new Result<List<CruiseDto>>(cruises, null);
    }

    @PutMapping("/{id}")
    public Result<Boolean> updateCruise(@PathVariable Long id, @RequestBody UpdateCruiseDto updateCruiseDto) {
        log.trace("Updating cruise " + id);

        cruiseService.updateCruise(CruiseConverter.INSTANCE.toDomain(updateCruiseDto));

        return new Result<>(true, "Cruise updated successfully");
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> deleteCruise(@PathVariable Long id) {
        log.trace("Deleting cruise " + id);

        cruiseService.deleteCruise(id);

        return new Result<>(true, "Cruise deleted successfully");
    }
}
