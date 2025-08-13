package caoh29.OMS.order_service.services;

import caoh29.OMS.order_service.clients.InventoryClient;
import caoh29.OMS.order_service.clients.ProductCatalogClient;
import caoh29.OMS.order_service.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
//    private final String authToken = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI5TWZlSUJKdzV5Qm1uVVBTVUFYSldhdnc4RUlXWXBscEptSEFIZnJRaXBZIn0.eyJleHAiOjE3NTUwMjE1MjksImlhdCI6MTc1NTAyMTIyOSwianRpIjoidHJydGNjOjA4OTk4YzdhLWY4NzAtMjFlYi00MzQzLTE2ZTBiYzI3MzM0NiIsImlzcyI6Imh0dHA6Ly9hdXRoLXNlcnZlci1rZXljbG9hazo4NDQzL3JlYWxtcy9PTVMtcmVhbG0iLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiZGU2N2ZjYTctOWQzOC00OTFiLWFiZjAtZDBhMWQzZmMwNjkyIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoic2VydmljZTJzZXJ2aWNlLWNsaWVudCIsImFjciI6IjEiLCJhbGxvd2VkLW9yaWdpbnMiOlsiLyoiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iLCJkZWZhdWx0LXJvbGVzLW9tcy1yZWFsbSJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoiZW1haWwgcHJvZmlsZSIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwiY2xpZW50SG9zdCI6IjE3Mi4yOC4wLjEiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJzZXJ2aWNlLWFjY291bnQtc2VydmljZTJzZXJ2aWNlLWNsaWVudCIsImNsaWVudEFkZHJlc3MiOiIxNzIuMjguMC4xIiwiY2xpZW50X2lkIjoic2VydmljZTJzZXJ2aWNlLWNsaWVudCJ9.X9_iMqf3MFSSBZFeeHNrb4zNXCoW3C8AWvLdXiO45cJjYsPgTsIOny6sC4o_BgBqrTfy6Ym851aRihL55Ju2LNiMnGYwqFnTO-L0aXAKLTo_gldEG7TDsDOvO7D6t7T6AiQ4TKfIZDmM2RKWJUncU0I68xSB0d0rXgvUcW7m8wxsZgdtG-YUZwehpF7HfpS-audf1mprURoZI9HOJ2iEcH59VxA-3DaGiCEsEyoIaznGr1TEBdi83dAbIpRsSp72rQPOI0JIF2Wt0_7uLFoWauVfX4vPOdM925DzOq07tF0mP90hQcrLHBPQdoWSkqFvEdTvPA3Wwk8R2s6IT4DrKQ"; // Replace with your actual auth token
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
//        if (this.inventoryClient.isInStock(this.authToken, product.getSku(), quantity)) {
        if (inventoryClient.isInStock(product.getSku(), quantity)) {
            return "Order placed successfully for " + quantity + " units of " + product.getName();
        } else {
            return "Insufficient stock for " + product.getName() + ", Requested: " + quantity;
        }
    }
}
