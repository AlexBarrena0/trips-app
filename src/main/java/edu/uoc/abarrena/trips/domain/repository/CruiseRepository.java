package edu.uoc.abarrena.trips.domain.repository;

import edu.uoc.abarrena.trips.domain.model.Cruise;

public interface CruiseRepository {

    public Long save(Cruise cruise);

    public Cruise findById(Long id);

    public void update(Cruise cruise);
}
