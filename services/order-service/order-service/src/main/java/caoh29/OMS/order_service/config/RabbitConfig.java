package caoh29.OMS.order_service.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Value("${rabbitmq.queue.name:orders-queue}")
    private String queueName;
    @Value("${rabbitmq.exchange.name:orders-exchange}")
    private String exchangeName;
    @Value("${rabbitmq.routing-key.name:orders-routing-key}")
    private String routingKey;

    @Bean
    public Queue queue() {
        return new Queue(queueName, true); // Durable queue
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(exchangeName, true, false); // Durable exchange
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(topicExchange()).with(routingKey);
    }

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin admin = new RabbitAdmin(connectionFactory);
        // This ensures that queues, exchanges, and bindings are declared at startup
        admin.setAutoStartup(true);
        return admin;
    }
}
