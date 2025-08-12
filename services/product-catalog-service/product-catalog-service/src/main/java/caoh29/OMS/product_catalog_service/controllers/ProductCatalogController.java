package caoh29.OMS.product_catalog_service.controllers;

import caoh29.OMS.product_catalog_service.entities.Product;
import caoh29.OMS.product_catalog_service.services.ProductCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product-catalog")
public class ProductCatalogController {
    @Autowired
    private ProductCatalogService productCatalogService;

    @GetMapping("/")
    public List<Product> getProductCatalog() {
        return this.productCatalogService.getProducts();
    }
}
