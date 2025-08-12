package caoh29.OMS.order_service.services;

import caoh29.OMS.order_service.entities.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    public List<Order> getOrders() {
        ArrayList<Order> list = new ArrayList<>();
        list.add(new Order(
                "sa15da321",
                "1",
                2,
                "John Doe",
                "john@email.com",
                "PENDING"
        ));
        list.add(new Order(
                "ld59oq770",
                "2",
                6,
                "Daniel Smith",
                "daniel@email.com",
                "COMPLETED"
        ));
        return list;
    }
}
