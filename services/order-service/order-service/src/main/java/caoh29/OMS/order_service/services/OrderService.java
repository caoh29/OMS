package caoh29.OMS.order_service.services;

import caoh29.OMS.order_service.clients.InventoryClient;
import caoh29.OMS.order_service.clients.ProductCatalogClient;
import caoh29.OMS.order_service.entities.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderService {
    // Database mock
    private final List<Order> orderList = List.of(
            new Order(
                    "sa15da321",
                    "1",
                    2,
                    "John Doe",
                    "john@email.com",
                    "PENDING"
            ),
            new Order(
                    "ld59oq770",
                    "2",
                    6,
                    "Daniel Smith",
                    "daniel@email.com",
                    "COMPLETED"
            ));

    // Message broker configuration
    @Value("${rabbitmq.exchange.name:orders-exchange}")
    private String exchangeName;
    @Value("${rabbitmq.routing-key.name:orders-routing-key}")
    private String routingKey;
    @Autowired
    private AmqpTemplate amqpTemplate;

    // Clients to call other microservices using RestClient
    @Autowired
    private InventoryClient inventoryClient;
    @Autowired
    private ProductCatalogClient productCatalogClient;


    public List<Order> getOrders() {
        log.info("Fetching all orders");
        return this.orderList;
    }

    // Demo purpose to show how to call other microservices
    public String placeOrder(String sku, Integer quantity) {
        log.info("fetching product details from product-catalog-service");
        var products = productCatalogClient.getProductCatalog();
        var product = products.stream()
                .filter(p -> p.getSku().equals(sku))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));
        log.info("checking inventory for product SKU: {} with quantity: {}", sku, quantity);
        if (inventoryClient.isInStock(product.getSku(), quantity)) {
            log.info("Product is in stock, placing order and sending message to RabbitMQ");
            amqpTemplate.convertAndSend(exchangeName, routingKey, "Order placed successfully for " + quantity + " units of " + product.getName());
            return "Order placed successfully for " + quantity + " units of " + product.getName();
        } else {
            log.warn("Insufficient stock for product SKU: {}, Requested quantity: {}", sku, quantity);
            return "Insufficient stock for " + product.getName() + ", Requested: " + quantity;
        }
    }
}
