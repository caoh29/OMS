package caoh29.OMS.product_catalog_service.services;

import caoh29.OMS.product_catalog_service.entities.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductCatalogService {
    private final List<Product> productList = List.of(
            new Product(
                    "1",
                    "a5sg64",
                    "Laptop",
                    "High performance laptop",
                    1200.00
            ),
            new Product(
                    "2",
                    "b7hj89",
                    "Smartphone",
                    "Latest model smartphone with advanced features",
                    800.00
            ));
    public List<Product> getProducts() {
        log.info("Fetching all products from product catalog service");
        return this.productList;
    }
}
