package edu.uoc.abarrena.trips.application.handler;

import edu.uoc.abarrena.trips.application.dto.response.Result;
import edu.uoc.abarrena.trips.domain.exceptions.AbstractBusinessLogicException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class BussinessLogicExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(AbstractBusinessLogicException.class)
    public Result<String> businessLogicException(AbstractBusinessLogicException ex) {
        String message = ex.getMessage();

        return new Result<>(message);
    }
}
