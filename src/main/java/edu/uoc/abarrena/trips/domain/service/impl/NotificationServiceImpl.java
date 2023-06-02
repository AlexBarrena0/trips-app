package edu.uoc.abarrena.trips.domain.service.impl;

import edu.uoc.abarrena.trips.domain.model.Notification;
import edu.uoc.abarrena.trips.domain.model.NotificationType;
import edu.uoc.abarrena.trips.domain.service.NotificationService;
import edu.uoc.abarrena.trips.infrastructure.kafka.KafkaConstants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    KafkaTemplate<String, Notification> notificationKafkaTemplate;

    private NewTopic tripsAddTopic;

    private NewTopic bookingsAddTopic;

    public NotificationServiceImpl(KafkaTemplate<String, Notification> notificationKafkaTemplate) {
        this.notificationKafkaTemplate = notificationKafkaTemplate;
    }

    @Override
    public void sendNotification(Notification notification) {
        if (notification.getType().equals(NotificationType.RESERVATION_PENDING)) {
            notificationKafkaTemplate.send(KafkaConstants.BOOKINGS_TOPIC + KafkaConstants.SEPARATOR + KafkaConstants.COMMAND_ADD, notification);
        }
    }
}
