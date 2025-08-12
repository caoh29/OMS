package caoh29.OMS.product_catalog_service.services;

import caoh29.OMS.product_catalog_service.entities.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCatalogService {
    public List<Product> getProducts() {
        ArrayList<Product> list = new ArrayList<>();
        list.add(new Product(
                "1",
                "a5sg64",
                "Laptop",
                "High performance laptop",
                1200.00
        ));
        list.add(new Product(
                "2",
                "b7hj89",
                "Smartphone",
                "Latest model smartphone with advanced features",
                800.00
        ));
        return list;
    }
}
