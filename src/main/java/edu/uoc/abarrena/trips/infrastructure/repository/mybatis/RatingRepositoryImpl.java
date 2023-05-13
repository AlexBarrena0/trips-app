package edu.uoc.abarrena.trips.infrastructure.repository.mybatis;

import edu.uoc.abarrena.trips.domain.repository.RatingRepository;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.RatingEntity;

import java.util.List;

public class RatingRepositoryImpl implements RatingRepository {
    @Override
    public Long save(RatingEntity ratingEntity) {
        return null;
    }

    @Override
    public List<RatingEntity> findByCruiseId(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
