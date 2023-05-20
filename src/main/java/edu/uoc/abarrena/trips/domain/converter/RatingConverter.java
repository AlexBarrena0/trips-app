package edu.uoc.abarrena.trips.domain.converter;

import edu.uoc.abarrena.trips.application.dto.response.RatingDto;
import edu.uoc.abarrena.trips.domain.model.Rating;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.RatingEntity;
import edu.uoc.abarrena.trips.application.dto.request.CreateRatingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RatingConverter {

    public static final RatingConverter INSTANCE = Mappers.getMapper(RatingConverter.class);

    List<RatingDto> toDto(List<Rating> rating);

    RatingEntity toEntity(Rating rating);

    List<Rating> toModel(List<RatingEntity> ratingEntity);

    Rating toDomain(RatingEntity ratingEntity);

    @Mapping(target = "cruise.id", source = "cruiseId")
    Rating toDomain(CreateRatingDto createRatingDto);

}
