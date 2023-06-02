package edu.uoc.abarrena.trips.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    private Long id;
    private String message;
    private NotificationType type;
    private Long userId;
    private Boolean read;

    public Notification(NotificationType type, Long userId) {
        this.type = type;
        this.userId = userId;
        this.read = false;
    }
}
