package edu.uoc.abarrena.trips.domain.exceptions;

public class PendingTripsException extends AbstractBusinessLogicException {
    public PendingTripsException() {
        super("Cruise cannot be deleted because it has pending trips");
    }
}
