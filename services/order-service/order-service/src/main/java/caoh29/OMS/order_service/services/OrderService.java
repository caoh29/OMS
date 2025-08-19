package caoh29.OMS.order_service.services;

import caoh29.OMS.order_service.clients.InventoryClient;
import caoh29.OMS.order_service.clients.ProductCatalogClient;
import caoh29.OMS.order_service.entities.Order;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
        return this.orderList;
    }

    // Demo purpose to show how to call other microservices
    public String placeOrder(String sku, Integer quantity) {
        var products = productCatalogClient.getProductCatalog();
        var product = products.stream()
                .filter(p -> p.getSku().equals(sku))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if (inventoryClient.isInStock(product.getSku(), quantity)) {
            amqpTemplate.convertAndSend(exchangeName, routingKey, "Order placed successfully for " + quantity + " units of " + product.getName());
            return "Order placed successfully for " + quantity + " units of " + product.getName();
        } else {
            return "Insufficient stock for " + product.getName() + ", Requested: " + quantity;
        }
    }
}
