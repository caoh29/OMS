package caoh29.OMS.inventory_service.services;

import caoh29.OMS.inventory_service.entities.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class InventoryService {
    private final List<Product> productList = List.of(
            new Product(
                    "INV-456",
                    "a5sg64",
                    45,
                    12,
                    33,
                    10,
                    100
            ),
            new Product(
                    "INV-456",
                    "b7hj89",
                    21,
                    6,
                    15,
                    5,
                    100
            ));
    public List<Product> getInventory() {
        log.info("Fetching all products in inventory");
        return this.productList;
    }

    public boolean isInStock(String sku, Integer quantity) {
        log.info("Checking stock for SKU: {}, Quantity: {}", sku, quantity);
        return this.productList.stream()
                .filter(product -> product.getSku().equals(sku))
                .anyMatch(product -> product.getQuantityAvailable() >= quantity);
    }
}
