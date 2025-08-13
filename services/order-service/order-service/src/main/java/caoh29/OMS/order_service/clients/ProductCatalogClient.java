package caoh29.OMS.order_service.clients;

import caoh29.OMS.order_service.entities.Product;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface ProductCatalogClient {
    @GetExchange("/api/product-catalog/")
    List<Product> getProductCatalog();
}
