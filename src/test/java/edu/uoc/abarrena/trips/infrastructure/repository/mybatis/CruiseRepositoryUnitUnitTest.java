package edu.uoc.abarrena.trips.infrastructure.repository.mybatis;

import edu.uoc.abarrena.trips.BaseUnitTest;
import edu.uoc.abarrena.trips.domain.model.Cruise;
import edu.uoc.abarrena.trips.factory.CruiseFactory;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.CruiseEntity;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper.CruiseMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CruiseRepositoryUnitUnitTest extends BaseUnitTest {

    @Mock
    private CruiseMapper cruiseMapper;

    @InjectMocks
    private CruiseRepositoryImpl cruiseRepository;

    @Test
    void save_saveCruise() {
        Long expectedId = 1L;
        Cruise cruise = CruiseFactory.cruiseDomain(expectedId);

        CruiseEntity cruiseEntity = CruiseFactory.cruiseEntity(expectedId);
        doNothing().when(cruiseMapper).save(cruiseEntity);

        Long actualId = cruiseRepository.save(cruise);

        assertEquals(expectedId, actualId);
    }

    @Test
    void findById_returnCruise() {
        Long id = 1L;
        CruiseEntity cruiseEntity = CruiseFactory.cruiseEntity(id);
        when(cruiseMapper.findById(id)).thenReturn(cruiseEntity);

        Cruise actualCruise = cruiseRepository.findById(id);

        Cruise expectedCruise = CruiseFactory.cruiseDomain(id);
        assertEquals(expectedCruise, actualCruise);
    }

    @Test
    void update_updateCruise() {
        Long id = 1L;
        Cruise cruise = CruiseFactory.cruiseDomain(id);

        CruiseEntity cruiseEntity = CruiseFactory.cruiseEntity(id);
        doNothing().when(cruiseMapper).update(cruiseEntity);

        cruiseRepository.update(cruise);
    }
}
