package edu.uoc.abarrena.trips.infrastructure.repository.mybatis;

import edu.uoc.abarrena.trips.BaseTest;
import edu.uoc.abarrena.trips.domain.model.Cruise;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.entity.CruiseEntity;
import edu.uoc.abarrena.trips.infrastructure.repository.mybatis.mapper.CruiseMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CruiseRepositoryUnitTest extends BaseTest {

    @Mock
    private CruiseMapper cruiseMapper;

    @InjectMocks
    private CruiseRepositoryImpl cruiseRepository;

    @Test
    void save_saveCruise() {
        Long expectedId = 1L;
        Cruise cruise = new Cruise(expectedId, "Cruise 1", "Cruise 1 description", 10);

        CruiseEntity cruiseEntity = new CruiseEntity(expectedId, "Cruise 1", "Cruise 1 description", 10);
        doNothing().when(cruiseMapper).save(cruiseEntity);

        Long actualId = cruiseRepository.save(cruise);

        assertEquals(expectedId, actualId);
        verify(cruiseMapper).save(cruiseEntity);
    }

    @Test
    void findById_returnCruise() {
        Long id = 1L;
        CruiseEntity cruiseEntity = new CruiseEntity(id, "Cruise 1", "Cruise 1 description", 10);
        when(cruiseMapper.findById(id)).thenReturn(cruiseEntity);

        Cruise actualCruise = cruiseRepository.findById(id);

        Cruise expectedCruise = new Cruise(id, "Cruise 1", "Cruise 1 description", 10);
        assertEquals(expectedCruise, actualCruise);
        verify(cruiseMapper).findById(id);
    }

    @Test
    void updateCruise_updateCruise() {
        Long id = 1L;
        Cruise cruise = new Cruise(id, "Cruise 1", "Cruise 1 description", 10);

        CruiseEntity cruiseEntity = new CruiseEntity(id, "Cruise 1", "Cruise 1 description", 10);
        doNothing().when(cruiseMapper).update(cruiseEntity);

        cruiseRepository.update(cruise);

        verify(cruiseMapper).update(cruiseEntity);
    }
}
