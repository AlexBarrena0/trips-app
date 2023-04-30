package edu.uoc.abarrena.trips.domain.exceptions;

public class InconsistentDatesException extends AbstractBusinessLogicException {

        public InconsistentDatesException() {
            super("The start date must be before the end date");
        }
}
