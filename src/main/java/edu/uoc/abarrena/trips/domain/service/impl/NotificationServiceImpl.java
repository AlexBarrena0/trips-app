package edu.uoc.abarrena.trips.domain.service.impl;

import edu.uoc.abarrena.trips.domain.model.Notification;
import edu.uoc.abarrena.trips.domain.model.NotificationType;
import edu.uoc.abarrena.trips.domain.service.NotificationService;
import edu.uoc.abarrena.trips.infrastructure.kafka.KafkaConstants;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    KafkaTemplate<String, Notification> notificationKafkaTemplate;

    public NotificationServiceImpl(KafkaTemplate<String, Notification> notificationKafkaTemplate) {
        this.notificationKafkaTemplate = notificationKafkaTemplate;
    }

    @Override
    public void sendNotification(Notification notification) {
        if (notification.getType().equals(NotificationType.RESERVATION_PENDING)
                || notification.getType().equals(NotificationType.RESERVATION_CONFIRMED)
                || notification.getType().equals(NotificationType.RESERVATION_REJECTED)) {
            notificationKafkaTemplate.send(KafkaConstants.BOOKINGS_TOPIC + KafkaConstants.SEPARATOR + KafkaConstants.COMMAND_ADD, notification);
        } else if (notification.getType().equals(NotificationType.NEW_TRIP)) {
            notificationKafkaTemplate.send(KafkaConstants.TRIPS_TOPIC + KafkaConstants.SEPARATOR + KafkaConstants.COMMAND_ADD, notification);
        }
    }
}
