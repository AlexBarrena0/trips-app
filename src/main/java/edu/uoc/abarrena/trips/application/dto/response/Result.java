package edu.uoc.abarrena.trips.application.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Result<T> {
    protected Boolean success;
    protected String message;
    protected T response;

    public Result(String message) {
        this.success = false;
        this.message = message;
    }

    public Result(T response, String message) {
        this.success = true;
        this.response = response;
        this.message = message;
    }
}
