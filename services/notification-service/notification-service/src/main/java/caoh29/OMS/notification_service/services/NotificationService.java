package caoh29.OMS.notification_service.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class NotificationService {

    private static final Logger LOGGER = Logger.getLogger(NotificationService.class.getName());

    public String sendNotification(String message) {
        // Simulate sending a notification
        return "Notification sent: " + message;
    }

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void consumeMessage(String message) {
        LOGGER.info("Received message from RabbitMQ: " + message);
        sendNotification(message);
    }
}
