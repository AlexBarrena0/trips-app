package edu.uoc.abarrena.trips.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    private Long id;
    private String message;
    private NotificationType type;
    private Long userId;
    private Boolean read;
    private HashMap<String, Object> params;

    public Notification(NotificationType type, Long userId, HashMap<String, Object> params) {
        this(type, params);
        this.userId = userId;
        this.read = false;
    }

    public Notification(NotificationType type, HashMap<String, Object> params) {
        this.type = type;
        this.params = params;
    }
}
