package edu.uoc.abarrena.trips.application.mapper;

import edu.uoc.abarrena.trips.domain.model.Destination;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.DestinationEntity;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.request.CreateDestinationDto;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.response.DestinationDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DestinationMapper {

    public static final DestinationMapper INSTANCE = Mappers.getMapper(DestinationMapper.class);

    DestinationDto toDto(Destination destination);

    Destination toDomain(DestinationDto destinationDto);

    Destination toDomain(DestinationEntity destinationEntity);

    Destination toDomain(CreateDestinationDto createDestinationDto);



}


