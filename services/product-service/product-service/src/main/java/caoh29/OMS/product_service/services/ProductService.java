package caoh29.OMS.product_service.services;

import caoh29.OMS.product_service.entities.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    public List<Product> getProducts() {
        ArrayList<Product> list = new ArrayList<>();
        list.add(new Product(
                "1",
                "Laptop",
                "High performance laptop",
                1200.00,
                50
        ));
        list.add(new Product(
                "2",
                "Smartphone",
                "Latest model smartphone with advanced features",
                800.00,
                100
        ));
        return list;
    }
}
