package caoh29.OMS.order_service.controllers;

import caoh29.OMS.order_service.entities.Order;
import caoh29.OMS.order_service.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public List<Order> getOrders() {
        return this.orderService.getOrders();
    }
}
