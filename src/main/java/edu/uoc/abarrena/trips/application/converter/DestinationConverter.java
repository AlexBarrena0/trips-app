package edu.uoc.abarrena.trips.application.converter;

import edu.uoc.abarrena.trips.domain.model.Destination;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.DestinationEntity;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.request.CreateDestinationDto;
import edu.uoc.abarrena.trips.infrastructure.rest.dto.response.DestinationDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DestinationConverter {

    public static final DestinationConverter INSTANCE = Mappers.getMapper(DestinationConverter.class);

    DestinationDto toDto(Destination destination);

    Destination toDomain(DestinationEntity destinationEntity);

    List<Destination> toDomain(List<DestinationEntity> destinationEntity);

    Destination toDomain(CreateDestinationDto createDestinationDto);

    DestinationEntity toEntity(Destination destination);



}


