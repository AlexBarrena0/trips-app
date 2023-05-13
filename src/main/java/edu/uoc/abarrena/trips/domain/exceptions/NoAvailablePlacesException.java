package edu.uoc.abarrena.trips.domain.exceptions;

public class NoAvailablePlacesException extends AbstractBusinessLogicException {

        public NoAvailablePlacesException() {
            super("No available places");
        }
}
