package edu.uoc.abarrena.trips.infrastructure.repository.mybatis;

import edu.uoc.abarrena.trips.domain.repository.CruiseImageRepository;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper.CruiseImageMapper;

import java.util.HashMap;
import java.util.List;

public class CruiseImageRepositoryImpl implements CruiseImageRepository {

    private final CruiseImageMapper cruiseImageMapper;

    public CruiseImageRepositoryImpl(CruiseImageMapper cruiseImageMapper) {
        this.cruiseImageMapper = cruiseImageMapper;
    }

    @Override
    public void save(Long cruiseId, List<Long> imagesIds) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("cruiseId", cruiseId);
        params.put("imagesIds", imagesIds);
        cruiseImageMapper.save(params);
    }

    @Override
    public List<Long> findByCruiseId(Long cruiseId) {
        return cruiseImageMapper.findByCruiseId(cruiseId);
    }

    @Override
    public void deleteByCruiseId(Long cruiseId) {
        cruiseImageMapper.deleteByCruiseId(cruiseId);
    }
}
