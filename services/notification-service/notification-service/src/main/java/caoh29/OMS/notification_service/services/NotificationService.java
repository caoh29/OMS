package caoh29.OMS.notification_service.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {

    public String sendNotification(String message) {
        // Simulate sending a notification
        log.info("Sending notification: " + message);
        return "Notification sent: " + message;
    }

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void consumeMessage(String message) {
        log.info("Received message from RabbitMQ: " + message);
        sendNotification(message);
    }
}
