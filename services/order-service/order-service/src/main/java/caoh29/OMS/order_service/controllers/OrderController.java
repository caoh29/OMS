package caoh29.OMS.order_service.controllers;

import caoh29.OMS.order_service.entities.Order;
import caoh29.OMS.order_service.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    // This should be a POST endpoint but for demo purposes it is a GET endpoint
    @GetMapping("/place-order")
    public String placeOrder(
            @RequestParam String sku,
            @RequestParam Integer quantity
    ) {
        return orderService.placeOrder(sku, quantity);
    }
}
