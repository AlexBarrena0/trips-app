package edu.uoc.abarrena.trips.domain.exceptions;

public class DestinationDuplicatedException extends RuntimeException{

        public DestinationDuplicatedException(String message) {
            super(message);
        }
}
