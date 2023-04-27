package edu.uoc.abarrena.trips.infrastructure.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Response<T> {
    protected Boolean success;
    protected String message;
    protected T response;

    public Response(T response) {
        this.success = true;
        this.response = response;
    }

    public Response(String message) {
        this.success = false;
        this.message = message;
    }

    public Response(T response, String message) {
        this.success = true;
        this.response = response;
        this.message = message;
    }
}
