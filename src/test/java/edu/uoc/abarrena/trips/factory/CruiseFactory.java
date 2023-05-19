package edu.uoc.abarrena.trips.factory;

import edu.uoc.abarrena.trips.domain.model.Cruise;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.CruiseEntity;
import edu.uoc.abarrena.trips.application.dto.request.CreateCruiseDto;
import edu.uoc.abarrena.trips.application.dto.request.UpdateCruiseDto;

public interface CruiseFactory {

    public static Cruise cruiseDomain(Long expectedId) {
        return new Cruise(expectedId, "Cruise 1", "Cruise 1 description", 10, 1L);
    }

    public static CruiseEntity cruiseEntity(Long expectedId) {
        return new CruiseEntity(expectedId, "Cruise 1", "Cruise 1 description", 10);
    }

    public static CreateCruiseDto createCruiseDto() {
        return new CreateCruiseDto("Cruise 1", "Cruise 1 description", 10, 1L);
    }

    public static UpdateCruiseDto updateCruiseDto(Long expectedId) {
        return new UpdateCruiseDto(expectedId, "Cruise 1 updated", "Cruise 1 description updated", 15);
    }
}
