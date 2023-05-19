package edu.uoc.abarrena.trips.domain.exceptions;

public class UpdatedBookingException extends AbstractBusinessLogicException {

        public UpdatedBookingException() {
            super("This booking has been already updated");
        }
}
