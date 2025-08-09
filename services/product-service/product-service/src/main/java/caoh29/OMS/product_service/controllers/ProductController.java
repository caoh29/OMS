package caoh29.OMS.product_service.controllers;

import caoh29.OMS.product_service.entities.Product;
import caoh29.OMS.product_service.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping()
    public List<Product> getOrders() {
        return this.productService.getProducts();
    }
}
