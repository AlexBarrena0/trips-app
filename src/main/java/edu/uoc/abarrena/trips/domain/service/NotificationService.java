package edu.uoc.abarrena.trips.domain.service;

import edu.uoc.abarrena.trips.domain.model.Notification;

public interface NotificationService {

    /**
     * Send a notification
     *
     * @param notification the notification to send
     */
    void sendNotification(Notification notification);
}
