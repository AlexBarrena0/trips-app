package edu.uoc.abarrena.trips.infrastructure.repository.mybatis;

import edu.uoc.abarrena.trips.domain.repository.CruiseImageRepository;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper.CruiseImageMapper;

import java.util.List;

public class CruiseImageRepositoryImpl implements CruiseImageRepository {

    private final CruiseImageMapper cruiseImageMapper;

    public CruiseImageRepositoryImpl(CruiseImageMapper cruiseImageMapper) {
        this.cruiseImageMapper = cruiseImageMapper;
    }

    @Override
    public void save(Long cruiseId, List<Long> imageIds) {
        cruiseImageMapper.save(cruiseId, imageIds);
    }

    @Override
    public List<Long> findByCruiseId(Long cruiseId) {
        return cruiseImageMapper.findByCruiseId(cruiseId);
    }

    @Override
    public void deleteByImageId(Long imageId) {
        cruiseImageMapper.deleteByImageId(imageId);
    }
}
