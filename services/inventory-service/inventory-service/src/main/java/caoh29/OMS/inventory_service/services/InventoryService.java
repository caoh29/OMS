package caoh29.OMS.inventory_service.services;

import caoh29.OMS.inventory_service.entities.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class InventoryService {
    public List<Product> getInventory() {
        ArrayList<Product> list = new ArrayList<>();
        list.add(new Product(
                "INV-456",
                "a5sg64",
                45,
                12,
                33,
                10,
                100
        ));
        list.add(new Product(
                "INV-456",
                "b7hj89",
                21,
                6,
                15,
                5,
                100
        ));
        return list;
    }
}
