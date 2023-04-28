package edu.uoc.abarrena.trips.domain.exceptions;

public class DestinationDuplicatedException extends AbstractBusinessLogicException{

        public DestinationDuplicatedException(String message) {
            super(message);
        }
}
