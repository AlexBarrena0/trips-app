package edu.uoc.abarrena.trips.infrastructure.repository.mybatis;

import edu.uoc.abarrena.trips.domain.model.Destination;
import edu.uoc.abarrena.trips.domain.repository.DestinationRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
class DestinationRepositoryImplTest {

    @Autowired
    DestinationRepository destinationRepository;

    @Test
    public void testSaveAndGetDestination() {
        // Crea una entitat Destination
        Destination destination = new Destination();
        destination.setDescription("Paris");

        // Guardar la entitat a la base de dades
        destinationRepository.save(destination);

        // Recuperar la entitat de la base de dades
        Destination savedDestination = destinationRepository.findById(destination.getId());

        // Verificar que la entitat recuperada sigui la mateixa que la guardada
        assertNotNull(savedDestination);
        assertEquals(destination.getId(), savedDestination.getId());
        assertEquals(destination.getDescription(), savedDestination.getDescription());
    }
}
