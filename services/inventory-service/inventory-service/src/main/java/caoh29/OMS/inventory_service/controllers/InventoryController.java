package caoh29.OMS.inventory_service.controllers;

import caoh29.OMS.inventory_service.entities.Product;
import caoh29.OMS.inventory_service.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/")
    public List<Product> getInventory() {
        return this.inventoryService.getInventory();
    }

    @GetMapping("/in-stock")
    public boolean isInStock(
            @RequestParam String sku,
            @RequestParam Integer quantity
    ) {
        return this.inventoryService.isInStock(sku, quantity);
    }
}
