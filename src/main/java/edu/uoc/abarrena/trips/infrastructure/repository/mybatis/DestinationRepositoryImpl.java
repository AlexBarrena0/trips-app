package edu.uoc.abarrena.trips.infrastructure.repository.mybatis;

import edu.uoc.abarrena.trips.domain.model.Destination;
import edu.uoc.abarrena.trips.domain.repository.DestinationRepository;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.DestinationEntity;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper.DestinationMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DestinationRepositoryImpl implements DestinationRepository {

    private final DestinationMapper destinationMapper;

    public DestinationRepositoryImpl(DestinationMapper destinationMapper) {
        this.destinationMapper = destinationMapper;
    }
    @Override
    public Long save(Destination destination) {
        DestinationEntity destinationEntity = DestinationEntity.fromDomain(destination);
        destinationMapper.save(destinationEntity);
        return destinationEntity.getId();
    }

    @Override
    public Destination findById(Long id) {
        return destinationMapper.findById(id).toDomain();
    }

    @Override
    public Destination findByName(String name) {
        return destinationMapper.findByDescription(name).toDomain();
    }

    @Override
    public List<Destination> findAll() {
        return destinationMapper.findAll().stream().map(DestinationEntity::toDomain).toList();
    }
}
