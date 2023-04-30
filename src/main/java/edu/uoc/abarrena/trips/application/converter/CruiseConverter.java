package edu.uoc.abarrena.trips.application.converter;

import edu.uoc.abarrena.trips.domain.model.Cruise;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.CruiseEntity;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.request.CreateCruiseDto;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.request.UpdateCruiseDto;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.response.CruiseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CruiseConverter {

    public static final CruiseConverter INSTANCE = Mappers.getMapper(CruiseConverter.class);

    CruiseDto toDto(Cruise cruise);

    Cruise toDomain(CreateCruiseDto createCruiseDto);

    Cruise toDomain(UpdateCruiseDto updateCruiseDto);

    Cruise toDomain(CruiseEntity cruiseEntity);

    CruiseEntity toEntity(Cruise cruise);
}
