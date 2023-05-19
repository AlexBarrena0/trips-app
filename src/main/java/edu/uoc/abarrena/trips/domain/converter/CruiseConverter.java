package edu.uoc.abarrena.trips.domain.converter;

import edu.uoc.abarrena.trips.domain.model.Cruise;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.CruiseEntity;
import edu.uoc.abarrena.trips.application.dto.request.CreateCruiseDto;
import edu.uoc.abarrena.trips.application.dto.request.UpdateCruiseDto;
import edu.uoc.abarrena.trips.application.dto.response.CruiseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CruiseConverter {

    public static final CruiseConverter INSTANCE = Mappers.getMapper(CruiseConverter.class);

    CruiseDto toDto(Cruise cruise);

    @Mapping(target = "company.id", source = "companyId")
    Cruise toDomain(CreateCruiseDto createCruiseDto);

    Cruise toDomain(UpdateCruiseDto updateCruiseDto);
    @Mapping(target = "company.id", source = "companyId")
    Cruise toDomain(CruiseEntity cruiseEntity);

    @Mapping(target = "companyId", source = "company.id")
    CruiseEntity toEntity(Cruise cruise);
}
