package edu.uoc.abarrena.trips.domain.exceptions;

public class OverlappingTripException extends AbstractBusinessLogicException {

        public OverlappingTripException() {
            super("The trip dates overlaps with another cruise' trip");
        }
}
