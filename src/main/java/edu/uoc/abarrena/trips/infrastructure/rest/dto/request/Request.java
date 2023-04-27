package edu.uoc.abarrena.trips.infrastructure.rest.dto.request;

public interface Request<T> {

    T toDomain();
}
